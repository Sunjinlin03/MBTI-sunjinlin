package com.qst.dao;

import com.qst.Db;
import com.qst.entity.ExamQuestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class ExamQuestionDao {

    public void deleteByExam(Integer examId) throws SQLException {

        String sql = "delete from  exam_questions where exam_id=?";
        try (Connection conn = Db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, examId);
            stmt.executeUpdate();
        }
    }

    public void save(ExamQuestion q) throws SQLException {

        String sql = "insert into exam_questions(exam_id,question_id,personnel_id,answer,result,score) values(?,?,?,?,?,?)";
        try (Connection conn = Db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, q.getExamId());
            stmt.setObject(2, q.getQuestionId());
            stmt.setObject(3, q.getStudentId());

            stmt.setObject(4, Arrays.toString(q.getAnswer()));
            stmt.setObject(5, q.isRight());
            stmt.setObject(6, q.getScore());
            stmt.executeUpdate();
        }
    }
}
