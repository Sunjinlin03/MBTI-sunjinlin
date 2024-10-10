package com.qst.dao;

import com.qst.Db;
import com.qst.ExamException;
import com.qst.entity.Choice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChoiceDao {


    public List<Choice> findByQuestion(Integer id) {
        String sql = "select id,title,hint,checked,question_id from choices where question_id=?";
        List<Choice> list = new ArrayList<>();
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(create(rs));
            }
            rs.close();

        } catch (SQLException ex) {
            throw new ExamException(sql, ex);
        }

        return list;
    }


    public void insertChoice(Choice ch) {
        String sql = "insert into choices(question_id,title,hint,checked) values (?,?,?,?)";
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setObject(1, ch.getQuestionId());
            statement.setObject(2, ch.getTitle());
            statement.setObject(3, ch.getHint());
            statement.setBoolean(4, ch.isChecked());
            statement.executeUpdate();
            ch.setId(Db.getGeneratedInt(statement));
        } catch (SQLException ex) {
            throw new ExamException(sql, ex);
        }
    }


    public void updateChoice(Choice ch){
        String sql="update choices set title=?,hint=?,checked=? where id=?";
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setObject(1, ch.getTitle());
            statement.setObject(2, ch.getHint());
            statement.setObject(3, ch.isChecked());
            statement.setObject(4, ch.getId());
            statement.executeUpdate();
        }catch (SQLException ex){
            throw new ExamException(sql,ex);
        }
    }


    //删除题目选项
    public void deleteByQuestion(int id){
        String sql="delete from choices where question_id=? order by id";
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1,id);
            statement.executeUpdate();

        }catch (SQLException ex){
            throw new ExamException(sql,ex);
        }
        }

    private Choice create(ResultSet rs) throws SQLException {
        Choice ch = new Choice();
        ch.setId(rs.getInt("id"));
        ch.setTitle(rs.getString("title"));
        ch.setHint(rs.getString("hint"));
        ch.setChecked(rs.getBoolean("checked"));
        ch.setQuestionId(rs.getInt("question_id"));
        return ch;
    }
}
