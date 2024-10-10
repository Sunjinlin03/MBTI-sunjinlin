package com.qst.dao;


import com.qst.Db;
import com.qst.ExamException;
import com.qst.entity.AssessmentType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssessmentTypeDao {

    public List<AssessmentType> findAll() {
        String sql = "select id,title,cost,status from assessments";
        List<AssessmentType> assessmentTypes = new ArrayList<>();
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                assessmentTypes.add(createAssessmentType(rs));
            }
        } catch (SQLException ex) {
            throw new ExamException(sql, ex);
        }
        return assessmentTypes;
    }

    //通过名字获取考核类型信息
    public AssessmentType findByTitle(String title) {
        String sql = "select id,title,cost,status from assessments where title=?";
        AssessmentType as = null;
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, title);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                as = createAssessmentType(set);
            }
        } catch (SQLException ex) {
            throw new ExamException(sql, ex);
        }
        return as;

    }

    //通过id获取信息
    public AssessmentType findTypeById(int id) {
        String sql = "select id,title,cost,status from assessments where id=?";
        AssessmentType assessmentType = null;//确保在变量声明时给予它一个初始值
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // 设置 SQL 语句中的参数, 这一行将 id 参数设置到 SQL 查询中。这确保了查询条件能够根据传入的 id 正确执行，从而获取相应的 AssessmentType 对象
            statement.setObject(1, id);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                assessmentType = createAssessmentType(rs);
            }
        } catch (SQLException ex) {
            throw new ExamException(sql, ex);
        }
        return assessmentType;
    }


    //添加
    public int insertType(AssessmentType as) {
        String sql = "INSERT INTO assessments(title,cost,status) VALUES(?,?,?)";
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setObject(1, as.getTitle());
            statement.setObject(2, as.getCost());
            statement.setObject(3, as.getStatus());
            statement.executeUpdate();
            as.setId(Db.getGeneratedInt(statement));
            return as.getId();
        } catch (SQLException ex) {
            throw new ExamException(sql, ex);
        }
    }

    //删除方法
    public void deleteTypeById(int id) {
        String sql = "delete from assessments where id=?";
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new ExamException(sql, ex);
        }
    }


    //修改方法
    public void updateType(AssessmentType as) {
        String sql = "update assessments set title=?,cost=?,status=? where id=?";
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, as.getTitle());
            statement.setObject(2, as.getCost());
            statement.setObject(3, as.getStatus());
            statement.setObject(4, as.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new ExamException(sql, ex);
        }
    }


    private AssessmentType createAssessmentType(ResultSet rs) throws SQLException {
        AssessmentType assessmentType = new AssessmentType();
        assessmentType.setId(rs.getInt("id"));
        assessmentType.setTitle(rs.getString("title"));
        assessmentType.setCost(rs.getDouble("cost"));
        assessmentType.setStatus(rs.getInt("status"));
        return assessmentType;
    }

}
