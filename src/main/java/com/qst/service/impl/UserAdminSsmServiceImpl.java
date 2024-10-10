package com.qst.service.impl;

import com.qst.ExamException;
import com.qst.mapper.UserMapper;
import com.qst.pojo.User;
import com.qst.service.IUserAdminSsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAdminSsmServiceImpl implements IUserAdminSsmService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findUsers() {

        List<User> all = userMapper.findAll();
        List<User> allWithlastLogin = userMapper.findAllWithlastLogin();
        List<User> all2 = userMapper.findAll2();
        return all;
    }

    @Override
    public User findUserById(int id) {

//        Set ids=new HashSet<>();
//        ids.add(id);
//        ids.add(2);
//        List<User> list=userMapper.getUserByIds(ids);

        return userMapper.findUserById(id);
    }

    @Override
    public void saveUser(User u) {
        User temp = userMapper.findByLogin(u.getLogin());

//        User user1=new User();
//        user1.setLogin(user1.getLogin());
//        List<User> list=userMapper.getUserByCondition(user1);
//        User tempUser=null;
//
//        if (list.size()>0){
//            tempUser=list.get(0);
//        }


        if (temp != null&& u.getLogin().equals(temp.getLogin())) {
            throw new ExamException("该登录名已经存在！");
        }
//        userMapper.insertUser(u);
        userMapper.insertUser(u);
    }


    //调用UserDao.update修改方法
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUserById(int id) {
        userMapper.delUserById(id);
    }

    @Override
    public void resetPassword(int id) {
        User userById=findUserById(id);

        userById.setPasswd(User.PASSWORD);
        userMapper.resetPwdByUser(userById);
    }


}

