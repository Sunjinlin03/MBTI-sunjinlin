package com.qst.dao;

import com.qst.Db;
import com.qst.ExamException;
import com.qst.entity.TestPersonnel;
import com.qst.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//创建Dao接口原来通过ID查找到数据库中存在的参测人员信息，然后将查询到的数据封装成一个对象返回
public class TestPersonnelDao {
    private static final String select ="select users.id as id,login,name,passwd,type,status,last_login,phone" +
            ",gender,email,birthdate,team_id from users join testPersonnel on users.id=testPersonnel.id";
    private TestPersonnel create(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setLogin(rs.getString("login"));
        user.setName(rs.getString("name"));
        user.setPasswd(rs.getString("passwd"));
        user.setType(rs.getInt("type"));
        user.setStatus(rs.getInt("status"));
        TestPersonnel testPersonnel = new TestPersonnel();
        testPersonnel.setId(user.getId());
        testPersonnel.setPhone(rs.getString("phone"));
        testPersonnel.setGender(rs.getString("gender"));
        testPersonnel.setBirthDate(rs.getDate("birthdate"));
        testPersonnel.setTeamId(rs.getInt("team_id"));
        testPersonnel.setUser(user);
        return testPersonnel;
    }

    public TestPersonnel findById(int id) throws SQLException {
        String sql = select +" where testPersonnel.id=?";
        TestPersonnel testPersonnel =null;

        Connection conn = Db.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setObject(1,id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            testPersonnel = create(rs);
        }
        rs.close();
        return testPersonnel;
    }

    public List<TestPersonnel> findByTeam(int id) throws SQLException {
        String sql = select;
        if(id>0)sql += " where team_id=?";
        try (Connection connection = Db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            if(id>0)stmt.setObject(1, id);
            List<TestPersonnel> users = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(create(rs));
            }
            rs.close();
            return users;
        }
    }

    public List<TestPersonnel> query(int teamId ,String name,String stdNo) throws SQLException{
        String sql = select + " where team_id=?";
        if(!"".equals(name)) sql +=" and name=?";
        if(!"".equals(stdNo)) sql +=" and phone=?";
        try (Connection connection = Db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, teamId);
            if(!"".equals(name))stmt.setObject(2, name);
            else if(!"".equals(stdNo))stmt.setObject(2, stdNo);
            if(!"".equals(name)&&!"".equals(stdNo))stmt.setObject(3, stdNo);
            List<TestPersonnel> users = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(create(rs));
            }
            rs.close();
            return users;
        }catch (SQLException ex) {
            System.out.print(ex.getMessage());
            throw new ExamException("不存在该学生");
        }
    }

    public void insert(TestPersonnel s) throws SQLException {
        String sql = "insert into testPersonnel(id,phone,gender,birthdate,team_id) values(?,?,?,?,?)";
        try (Connection connection = Db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, s.getId());
            stmt.setObject(2, s.getPhone());
            stmt.setObject(3, s.getGender());
            stmt.setObject(4, s.getBirthDate());
            stmt.setObject(5, s.getTeamId());
            stmt.executeUpdate();
        }
    }

    public void update(TestPersonnel s) throws SQLException {
        String sql = "update testPersonnel set phone=?,gender=?,birthdate=?  where id=?";
        try (Connection connection = Db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(4, s.getId());
            stmt.setObject(1, s.getPhone());
            stmt.setObject(2, s.getGender());
            stmt.setObject(3, s.getBirthDate());
            stmt.executeUpdate();
        }
    }

    public TestPersonnel findByphone(String phone) throws SQLException {
        String sql = select + "where phone=?";
        TestPersonnel testPersonnel = null;
        try (Connection connection = Db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, phone);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                testPersonnel = create(rs);
            }
            rs.close();
        }
        return testPersonnel;
    }

    public void delete(int id) throws SQLException {
        String sql = "delete from testPersonnel  where id=?";
        try (Connection connection = Db.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }
    }
}
