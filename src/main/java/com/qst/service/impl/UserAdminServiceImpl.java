package com.qst.service.impl;

import com.qst.ExamException;
import com.qst.dao.DAOFactory;
import com.qst.dao.UserDao;
import com.qst.entity.User;
import com.qst.service.IUserAdminService;

import java.util.List;

public class UserAdminServiceImpl implements IUserAdminService {
    private UserDao userDAO = DAOFactory.getDao(UserDao.class);

    @Override
    public List<User> findUsers() {
        return userDAO.findAll();
    }

    @Override
    public User findUserById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public void saveUser(User u) {
        User temp = userDAO.findByLogin(u.getLogin());
        if (temp != null) {
            throw new ExamException("该登录名已经存在！");
        }
        userDAO.insert(u);
    }


    //调用UserDao.update修改方法
    @Override
    public void updateUser(User user) {
        User temp = userDAO.findByLogin(user.getLogin());
        if (temp != null && temp.getId() != user.getId()) {
            throw new ExamException("登录名已经存在了");
        }
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUserById(int id) {
        User user = userDAO.findById(id); // 根据用户 ID 从数据库中查找用户
        if (user == null) {// 如果用户不存在（即查找结果为 null）
            return; // 直接返回，不执行任何删除操作
        }
        if (user.getLastLogin() != null) {// 如果用户的 lastLogin 属性不为 null
            throw new ExamException("该用户不能被删除");// 抛出自定义异常，表示该用户不能被删除
        }
        userDAO.delUserById(id); // 否则，调用 DAO 层方法删除该用户
    }

    @Override
    public void resetPassword(int id) {
        userDAO.updatePasswd(id, User.PASSWORD);

    }


}
