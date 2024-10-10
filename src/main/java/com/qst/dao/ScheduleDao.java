package com.qst.dao;

import com.qst.Db;
import com.qst.entity.Schedule;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDao {
    private static final String select = "select id,begin_date,end_date,duration,status,creator_id,assessment_id,team_id,create_date,question_number from schedules";

    public List<Schedule> findByCreateId(Integer id) throws SQLException {
        String sql = select + " where creator_id=? order by team_id,assessment_id";
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            List<Schedule> list = new ArrayList<>();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(create(rs));
            }
            rs.close();
            return list;
        }
    }


    public Schedule findById(Integer id) throws SQLException {
        String sql = select + " where id=?";
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, id);
            Schedule sh = null;
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                sh = create(rs);
            }
            rs.close();
            return sh;
        }
    }


    public void save(Schedule sh) throws SQLException{
        String sql="insert into schedules(begin_date,end_date,duration,create_date,status,creator_id,assessment_id,team_id,question_number) values (?,?,?,?,?,?,?,?,?)";

        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setObject(1, sh.getBeginDate());
            statement.setObject(2, sh.getEndDate());
            statement.setObject(3, sh.getDuration());
            statement.setObject(4, sh.getCreateDate());
            statement.setObject(5, sh.getStatus());
            statement.setObject(6, sh.getCreatorId());
            statement.setObject(7, sh.getAssessmentId());
            statement.setObject(8, sh.getTeamId());
            statement.setObject(9, sh.getQuestionNumber());
            statement.executeUpdate();
            sh.setId(Db.getGeneratedInt(statement));
        }
    }


    public void update(Schedule h) throws SQLException {
        String sql = "update  schedules set begin_date=?,end_date=?,duration=?,create_date=?,status=?,assessment_id=?,team_id=?,question_number=? where id=?";
        try (Connection conn = Db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, h.getBeginDate());
            stmt.setObject(2, h.getEndDate());
            stmt.setObject(3, h.getDuration());
            stmt.setObject(4, h.getCreateDate());
            stmt.setObject(5, h.getStatus());
            stmt.setObject(6, h.getAssessmentId());
            stmt.setObject(7, h.getTeamId());
            stmt.setObject(8, h.getQuestionNumber());
            stmt.setObject(9, h.getId());

            stmt.executeUpdate();
        }

    }

    public void delete(Integer id) throws SQLException {
        String sql = "delete from schedules where id=?";
        try (Connection conn = Db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }
    }


    public List<Schedule> findByTeamId(int teamId) throws SQLException {
        String sql = select + " where team_id=? order by id desc";
        List<Schedule> list = new ArrayList<>();
        try (Connection conn = Db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, teamId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(create(rs));
            }
            rs.close();
            return list;
        }
    }

    public Schedule create(ResultSet rs) throws SQLException {
        Schedule sh = new Schedule();
        sh.setId(rs.getInt("id"));
        sh.setBeginDate(rs.getString("begin_date"));
        sh.setEndDate(rs.getString("end_date"));
        sh.setDuration(rs.getInt("duration"));
        sh.setCreateDate(rs.getDate("create_date"));
        sh.setQuestionNumber(rs.getInt("question_number"));
        sh.setStatus(rs.getInt("status"));
        sh.setCreatorId(rs.getInt("creator_id"));
        sh.setAssessmentId(rs.getInt("assessment_id"));
        sh.setTeamId(rs.getInt("team_id"));
        return sh;
    }

}
