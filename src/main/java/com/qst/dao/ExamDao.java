package com.qst.dao;

import com.qst.Db;
import com.qst.entity.Exam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExamDao {
    private static final String select = "select id,personnel_id,schedule_id,begin_time,end_time,result from exams ";

    public List<Exam> findByTestPersonnel(Integer id) throws SQLException {
        String sql = select + " where personnel_id=?";
        List<Exam> exams = new ArrayList<>();
        Connection conn = Db.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setObject(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            exams.add(create(rs));
        }
        return exams;
    }


    public Exam findByTestPersonnelAndSchedule(Integer id, Integer id2) throws SQLException {
        String sql = select + " where personnel_id=? and schedule_id=?";
        Exam exam = null;
        try (Connection conn = Db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.setObject(2, id2);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                exam = create(rs);
            }
            rs.close();
        }
        return exam;
    }

    private Exam create(ResultSet rs) throws SQLException {
        Exam m = new Exam();
        m.setId(rs.getInt("id"));
        m.setBeginTime(rs.getTimestamp("begin_time"));
        m.setEndTime(rs.getTimestamp("end_time"));
        m.setScheduleId(rs.getInt("schedule_id"));
        m.setResult(rs.getString("result"));
        m.setTestPersonnelId(rs.getInt("personnel_id"));
        return m;
    }


    public void updateExam(Exam m) throws SQLException {
        String sql = "update exams set end_time=?,result=?  where id=?";
        try (Connection conn = Db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, m.getEndTime());
            stmt.setObject(2, m.getResult());
            stmt.setObject(3, m.getId());
            stmt.executeUpdate();
        }
    }


    public void saveExam(Exam m) throws SQLException {
        String sql = "insert into exams(personnel_id,schedule_id,begin_time,end_time,result) values(?,?,?,?,?)";
        try (Connection conn = Db.getConnection();
             PreparedStatement stmt = conn
                     .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setObject(1, m.getTestPersonnelId());
            stmt.setObject(2, m.getScheduleId());
            stmt.setObject(3, m.getBeginTime());
            stmt.setObject(4, m.getEndTime());
            stmt.setObject(5, m.getResult());
            stmt.executeUpdate();
            m.setId(Db.getGeneratedInt(stmt));
        }
    }

}