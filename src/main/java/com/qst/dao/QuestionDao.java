package com.qst.dao;

import com.qst.Db;
import com.qst.ExamException;
import com.qst.action.question.QuestionQueryParam;
import com.qst.entity.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {

    public List<Question> find(QuestionQueryParam param) {
        String sql;
        if (param.getAssessmentId() > 0) {
            sql = "select id,assessment_id,user_id,type,title,status,difficulty,hint from questions where assessment_id=? and status=? and type=? order by id desc ";
        } else {
            sql = "select id,assessment_id,user_id,type,title,status,difficulty,hint from questions order by id desc ";
        }
        List<Question> questionList = new ArrayList<>();
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            if (param.getAssessmentId() > 0) {
                statement.setObject(1, param.getAssessmentId());
                statement.setObject(2, param.getStatus());
                statement.setObject(3, param.getType());
            }
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                questionList.add(create(rs));
            }

            rs.close();

        } catch (SQLException ex) {
            throw new ExamException();
        }

        return questionList;
    }

    public int findCountByDimension(int dimensionId) {
        String sql = "select count(*) from question_dimension where dimension_id=?";
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, dimensionId);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            throw new ExamException(sql, ex);
        }
    }


    public Question findById(int id) {
        String sql = "select id,assessment_id,user_id,type,title,status,difficulty,hint from questions where id=?";
        Question question = null;
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                question = create(rs);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ExamException(sql, ex);
        }
        return question;
    }

    public void insertQuestion(Question qs) {
        String sql = "insert into questions(title,hint,type,status,difficulty,assessment_id,user_id) values (?,?,?,?,?,?,?)";

        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setObject(1, qs.getTitle());
            statement.setObject(2, qs.getHint());
            statement.setObject(3, qs.getType());
            statement.setObject(4, qs.getStatus());
            statement.setObject(5, qs.getDifficulty());
            statement.setObject(6, qs.getAssessmentId());
            statement.setObject(7, qs.getUserId());
            statement.executeUpdate();

            qs.setId(Db.getGeneratedInt(statement));
            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                int qid = rs.getInt(1);
                qs.setId(qid);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ExamException(sql, ex);
        }
    }


    public void detachDimension(int id) {
        String sql = "delete from question_dimension where question_id=?";
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new ExamException(sql, ex);
        }
    }


    public void attachDimension(int qid, int pid) {
        String sql = "insert into question_dimension(question_id,dimension_id) values (?,?)";
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, qid);
            statement.setObject(2, pid);
            statement.executeUpdate();

        } catch (SQLException ex) {
            throw new ExamException(sql, ex);
        }
    }


    public List<Integer> findDimension(int questionId) {
        String sql = "select dimension_id from question_dimension where question_id=?";
        List<Integer> lists = new ArrayList<>();
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, questionId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                lists.add(rs.getInt(1));
            }
            rs.close();
        } catch (SQLException ex) {
            throw new ExamException(sql, ex);
        }
        return lists;

    }

    //修改
    public void updateQuestion(Question qs) {
        String sql = "update questions set title=?,hint=?,type=?,status=?,difficulty=? where id=?";
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, qs.getTitle());
            statement.setObject(2, qs.getHint());
            statement.setObject(3, qs.getType());
            statement.setObject(4, qs.getStatus());
            statement.setObject(5, qs.getDifficulty());
            statement.setObject(6, qs.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new ExamException(sql, ex);
        }

    }

    //判断是否在使用
    public boolean isUsed(int id){
        String sql="select 1 from paper_questions where question_id=? limit 1";
        boolean used=false;
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1,id);
            ResultSet rs=statement.executeQuery();
            if (rs.next()){
                used=true;
            }
            rs.close();
        }catch (SQLException ex){
            throw new ExamException(sql,ex);
        }
        return used;
    }

    public void delete(int id){
        String sql="delete from questions where id=?";
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1,id);
            statement.executeUpdate();

        }catch (SQLException ex){
            throw new ExamException(sql,ex);
        }
        }


    private Question create(ResultSet rs) throws SQLException {
        Question qs = new Question();
        qs.setDifficulty(rs.getInt("difficulty"));
        qs.setHint(rs.getString("hint"));
        qs.setId(rs.getInt("id"));
        qs.setStatus(rs.getInt("status"));
        qs.setAssessmentId(rs.getInt("assessment_id"));
        qs.setTitle(rs.getString("title"));
        qs.setType(rs.getInt("type"));
        qs.setUserId(rs.getInt("user_id"));
        return qs;
    }
}
