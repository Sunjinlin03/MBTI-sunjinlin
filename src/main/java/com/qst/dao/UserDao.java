package com.qst.dao;

import com.qst.Db;
import com.qst.ExamException;
import com.qst.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//用于完成用户的登录功能
public class UserDao {

    //通过登录用户名获取用户对象(查找数据库字段)
    public com.qst.entity.User findByLogin(String login) {
        String sql = "select id,login,name,passwd,type,status from users where login=?";
        //jdbc语法糖
        try (Connection conn = Db.getConnection();//创建连接
             PreparedStatement stmt = conn.prepareStatement(sql)) {//获取一个statement（预编译的statement）
            stmt.setObject(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return createUser(rs);
            }
            return null;
        } catch (SQLException ex) {
            throw new ExamException(sql, ex);
        }
    }

    //通过Id获取用户对象（重复调用）

    public com.qst.entity.User findById(int id) {
        String sql = "select id,login,name,passwd,type,status from users where id=?";
        try (Connection conn = Db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return createUser(rs);
            }
            return null;
        } catch (SQLException ex) {
            throw new ExamException(sql, ex);
        }
    }

    //修改最后登录时间
    public void updateLastLogin(Integer userId, Timestamp lastLogin) {
        String sql = "update users set last_login=? where id=?";
        try (Connection conn = Db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, lastLogin);
            stmt.setObject(2, userId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new ExamException(sql, ex);
        }
    }

    public User createUser(ResultSet rs) throws SQLException {
        return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
    }

    //添加findAll()方法用来查询数据库中所有存在的用户的数据，然后将其返回
    public List<User> findAll() {
        String sql = "select id,login,name,passwd,type,status from users where type<4";
        List<User> users = new ArrayList<>();

        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                users.add(createUser(rs));
            }
        } catch (SQLException ex) {
            throw new ExamException(sql, ex);
        }
        return users;
    }


    //添加新用户信息
    public int insert(User u) {
        String sql = "insert into users(login,passwd,name,type,status) values(?,?,?,?,?)";
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setObject(1, u.getLogin());
            statement.setObject(2, u.getPasswd());
            statement.setObject(3, u.getName());
            statement.setObject(4, u.getType());
            statement.setObject(5, u.getStatus());
            statement.executeUpdate();
            u.setId(Db.getGeneratedInt(statement));
            return u.getId();
        } catch (SQLException ex) {
            throw new ExamException(sql, ex);
        }
    }

    //修改用户信息方法
    public void updateUser(User user) {
        String sql = "update users set Login=?,name=?,type=?,status=? where id=?";
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, user.getLogin());
            statement.setObject(2, user.getName());
            statement.setObject(3, user.getType());
            statement.setObject(4, user.getStatus());
            statement.setObject(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new ExamException(sql, ex);
        }
    }

    //删除用户方法
    public void delUserById(int userId) {
        String sql = "delete from users where id=?";
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, userId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new ExamException(sql, ex);
        }
    }


    //修改密码方法
    public void updatePasswd(int userId, String passwd) {
        String sql = "update users set passwd=? where id=?";
        try (Connection connection = Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setObject(1, passwd);
            statement.setObject(2, userId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new ExamException(sql, ex);
        }
    }



}
