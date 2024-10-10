package com.qst.service;

import com.qst.entity.User;

//定义登录方法
public interface IUserService {

    //接口可以定义抽象方法，还可以写常量
    public User login(String login, String password);
    void changePassword(int userId,String oldPassword,String newPassword);
}
