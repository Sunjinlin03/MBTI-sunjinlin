package com.qst.service.impl;

import com.qst.ExamException;
import com.qst.dao.DAOFactory;
import com.qst.dao.UserDao;
import com.qst.entity.User;
import com.qst.service.IUserService;

import java.sql.Timestamp;

//IUserService接口的实现类,在实现类中添加了login()方法完成用户的登录
public class UserServiceImpl implements IUserService {
    //注入工厂方法dao
// 静态的工厂方法   private UserDao userDao=new UserDao(); 与下面的写法等价
    private UserDao userDAO= DAOFactory.getDao(UserDao.class);

    @Override
    public User login(String login, String password) {
        User user=userDAO.findByLogin(login);
        String msg=null;
        if (user==null){
            msg="用户不存在";
        }else if (user.getStatus()!=1){
            msg="账号已被禁用，不能登录";
        }else if (!user.getPasswd().equals(password)){
            msg="密码错误";
        }
        if (msg!=null){
            throw new ExamException(msg);
        }
        userDAO.updateLastLogin(user.getId(),new Timestamp(System.currentTimeMillis()));
        return user;
    }

    @Override
    public void changePassword(int userId, String oldPassword, String newPassword) {
        User user=userDAO.findById(userId);
        if (!user.getPasswd().equals(oldPassword)){
            throw new ExamException("原密码错误");
        }
        userDAO.updatePasswd(userId,newPassword);
    }

}
