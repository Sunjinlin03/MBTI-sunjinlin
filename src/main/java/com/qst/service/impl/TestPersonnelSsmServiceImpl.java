package com.qst.service.impl;

import com.qst.ExamException;
import com.qst.mapper.TestPersonnelMapper;
import com.qst.mapper.UserMapper;
import com.qst.pojo.TestPersonnel;
import com.qst.pojo.User;
import com.qst.service.ITestPersonnelSsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
public class TestPersonnelSsmServiceImpl implements ITestPersonnelSsmService {

    @Autowired
    private TestPersonnelMapper testPersonnelMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public TestPersonnel findById(int id) {
        TestPersonnel ts = testPersonnelMapper.findById(id);
        ts.setUser(userMapper.findUserById(id));
        return ts;
    }

    @Override
    public List<TestPersonnel> findByTeamId(int id) {
        List<TestPersonnel> testPersonnelList = testPersonnelMapper.findByTeamId(id);
        for (TestPersonnel testPersonnel : testPersonnelList) {
            testPersonnel.setUser(userMapper.findUserById(testPersonnel.getId()));
        }
        return testPersonnelList;
    }


    @Override
    public List<TestPersonnel> query(int teamId, String name, String phone) {
        return testPersonnelMapper.query(teamId, name, phone);
    }


    @Override
    public void addTestPersonnel(TestPersonnel s) {

        User u = s.getUser();
        if (u.getType() == 0) {
            u.setType(4);
        }

        u.setStatus(1);
        if (u.getPasswd() == null || u.getPasswd().equals("")) {
            u.setPasswd(User.PASSWORD);
        }

        u.setLogin(s.getPhone());//手机号作为默认的登录名
        userMapper.insertUser(u);
        s.setId(u.getId());
        testPersonnelMapper.addTestPersonnel(s);

    }


    @Override
    public void updateTestPersonnel(TestPersonnel ts) {
        User user = ts.getUser();
        User u = userMapper.findUserById(ts.getId());

        if (u != null) {
            u.setName(user.getName());
            if (user.getStatus() != 0) {
                u.setStatus(user.getStatus());
            }
            userMapper.updateUser(u);
        } else {
            // 处理u为null的情况，比如记录日志或抛出异常
            throw new ExamException("User not found with id: " + ts.getId());
        }

        testPersonnelMapper.updateTestPersonnel(ts);
    }

    @Override
    public TestPersonnel deleteTestPersonnel(int id) {
        User user = userMapper.findUserById(id);
        if (user.getLastLogin() != null) {
            throw new ExamException("该账户已启用，无法删除");
        }
        TestPersonnel testPersonnel = testPersonnelMapper.findById(id);
        testPersonnelMapper.deleteTestPersonnel(id);
        userMapper.delUserById(id);

        testPersonnel.setUser(user);
        return testPersonnel;
    }

    @Override
    public void importTestPersonnel(List<TestPersonnel> testPersonnelList) {
        try {
            for (int i = 0; i < testPersonnelList.size(); i++) {
                TestPersonnel s = testPersonnelList.get(i);
                TestPersonnel temp = testPersonnelMapper.findByPhone(s.getPhone());
                if (temp == null) {
                    addTestPersonnel(s);
                } else {
                    if (temp.getTeamId()!=s.getTeamId()) {
                        throw new ExamException("导入第" + (i + 1) + "个参测人员出错,该手机号"
                                + s.getPhone() + "已在其他参测人员注册");
                    }
                    s.setId(temp.getId());
                    updateTestPersonnel(s);
                }
            }
        }catch (Exception ex){
            throw new ExamException("导入参测人员出错",ex);
        }
    }
}