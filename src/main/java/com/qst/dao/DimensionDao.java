package com.qst.dao;

import com.qst.Db;
import com.qst.ExamException;
import com.qst.entity.PersonalityDimension;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DimensionDao {

    public List<PersonalityDimension> findByAssessmentId(int assessmentId) {
        String sql;
        if (assessmentId > 0) {
            // SQL 查询包含参数占位符
            sql = "select id, title, depict, assessment_id from personality_dimension where assessment_id = ?";
        } else {
            // SQL 查询不包含参数占位符
            sql = "select id, title, depict, assessment_id from personality_dimension";
        }

        List<PersonalityDimension> dimensions = new ArrayList<>();

        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            if (assessmentId > 0) {
                // 仅当 SQL 包含参数占位符时才设置参数
                statement.setInt(1, assessmentId); // 使用 setInt() 代替 setObject() 更为合适
            }

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                dimensions.add(createDimension(rs));
            }
            rs.close();
            return dimensions;
        } catch (SQLException ex) {
            throw new ExamException(sql, ex);
        }
    }


    public PersonalityDimension findById(int id) {
        String sql = "select id,title,depict,assessment_id from personality_dimension where id=?";
        PersonalityDimension pd = null;
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                pd = createDimension(rs);
            }
        } catch (SQLException ex) {
            throw new ExamException(sql, ex);
        }
        return pd;
    }


    public List<PersonalityDimension> findDimensionByQuestion(int questionId){
        String sql="select id,title,depict,assessment_id from personality_dimension where id in(select dimension_id from question_dimension where question_id=?)";
        List<PersonalityDimension> pds=new ArrayList<>();
        try (Connection connection= Db.getConnection();
             PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setObject(1,questionId);
            ResultSet rs=statement.executeQuery();
            while (rs.next()){
                pds.add(createDimension(rs));
            }
            rs.close();
            return pds;
        }catch (SQLException ex){
            throw new ExamException(sql,ex);
        }

    }




    public int insertDimension(PersonalityDimension pd){
        String sql="insert into personality_dimension(title,depict,assessment_id) values (?,?,?)";
        try (Connection connection=Db.getConnection();
             PreparedStatement statement=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            statement.setObject(1,pd.getTitle());
            statement.setObject(2,pd.getDepict());
            statement.setObject(3,pd.getAssessmentId());
            statement.executeUpdate();
            pd.setId(Db.getGeneratedInt(statement));

            return pd.getId();
        }catch (SQLException ex){
            throw new ExamException(sql,ex);
        }

    }

    public PersonalityDimension findByAssessmentIdAndTitle(int assessmentId,String title){
        String sql="select id,title,depict,assessment_id from personality_dimension where assessment_id=? and title=?";
        PersonalityDimension dimension=null;
        try (Connection connection=Db.getConnection();
             PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setObject(1,assessmentId);
            statement.setObject(2,title);

            ResultSet rs=statement.executeQuery();

            if (rs.next()){
                dimension=createDimension(rs);
            }
            rs.close();

        }catch (SQLException ex){
            throw new ExamException(sql,ex);
        }
        return dimension;
    }


    public void updateDimension(PersonalityDimension pd){
        String sql="update personality_dimension set title=?,depict=?,assessment_id=? where id=?";
        try (Connection connection=Db.getConnection();
             PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setObject(1,pd.getTitle());
            statement.setObject(2,pd.getDepict());
            statement.setObject(3,pd.getAssessmentId());
            statement.setObject(4,pd.getId());
            statement.executeUpdate();
        }catch (SQLException ex){
            throw new ExamException(sql,ex);
        }
    }


    public void deleteDimension(int id){
        String sql="delete from personality_dimension where id=?";
        try (Connection connection=Db.getConnection();
             PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setObject(1,id);
            statement.executeUpdate();
        }catch (SQLException ex){
            throw new ExamException(sql,ex);
        }
    }


    private PersonalityDimension createDimension(ResultSet rs) throws SQLException {
        PersonalityDimension dimension = new PersonalityDimension();
        dimension.setId(rs.getInt("id"));
        dimension.setTitle(rs.getString("title"));
        dimension.setDepict(rs.getString("depict"));
        dimension.setAssessmentId(rs.getInt("assessment_id"));
        return dimension;
    }


}
