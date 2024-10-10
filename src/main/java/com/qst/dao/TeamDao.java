package com.qst.dao;

import com.qst.Db;
import com.qst.entity.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDao {
    private Team create(ResultSet rs) throws SQLException{
        Team t=new Team();
        t.setId(rs.getInt("id"));
        t.setName(rs.getString("name"));
        t.setBeginYear(rs.getDate("begin_year"));
        t.setStatus(rs.getInt("status"));
        t.setCreatorId(rs.getInt("creator_id"));
        return t;
    }



    public Team findById(int teamId) {
        String sql = "select * from class_teams where id = ?";
        Team team = new Team();
        try {
            Connection conn = Db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, teamId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                team = create(rs);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return team;
    }

    public List<Team> findByCreator(Integer creator_id) throws SQLException{
       String sql="select id,name,begin_year,status,creator_id from class_teams where creator_id=?";
       List<Team> teams=new ArrayList<>();
       try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1,creator_id);
            ResultSet rs=statement.executeQuery();
            while (rs.next()){
                teams.add(create(rs));
            }
            rs.close();
            return teams;
        }

    }

    public List<Team> findAll() throws SQLException {
        String sql = "select id,name,begin_year,status,creator_id from class_teams ";
        try (Connection conn = Db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            List<Team> lists = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lists.add(create(rs));
            }
            rs.close();
            return lists;
        }

    }

    public void save(Team t) throws SQLException {
        String sql = "insert into class_teams(name,begin_year,status,creator_id) values(?,?,?,?)";
        try (Connection conn = Db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS)) {
            stmt.setObject(1, t.getName());
            stmt.setObject(2, t.getBeginYear());
            stmt.setObject(3, t.getStatus());
            stmt.setObject(4, t.getCreatorId());
            stmt.executeUpdate();
            t.setId(Db.getGeneratedInt(stmt));
        }

    }

    public void update(Team t) throws SQLException {
        String sql = "update class_teams set name=?,begin_year=?,status=? where id=?";
        try (Connection conn = Db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, t.getName());
            stmt.setDate(2, (Date) t.getBeginYear());
            stmt.setObject(3, t.getStatus());
            stmt.setObject(4, t.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "delete from class_teams where id=?";
        try (Connection conn = Db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }
    }
}
