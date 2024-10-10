package com.qst.service;

import com.qst.pojo.User;

//定义登录方法
public interface IUserSsmService {

    //接口可以定义抽象方法，还可以写常量
    User login(String login, String password);
    void changePassword(int userId,String oldPassword,String newPassword);
}
