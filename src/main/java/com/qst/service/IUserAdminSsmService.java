package com.qst.service;

import com.qst.pojo.User;

import java.util.List;

public interface IUserAdminSsmService {

    //获取所有用户信息
    List<User> findUsers();

    //根据用户id获取用户信息
    User findUserById(int id);

    //添加用户数据
    void saveUser(User u);

    //修改用户信息
    void updateUser(User user);

    //删除用户
    void deleteUserById(int id);

    //重置密码
    void resetPassword(int id);
}
