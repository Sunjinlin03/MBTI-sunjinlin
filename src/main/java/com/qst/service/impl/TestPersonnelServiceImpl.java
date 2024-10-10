package com.qst.service.impl;

import com.qst.ExamException;
import com.qst.dao.DAOFactory;
import com.qst.dao.TestPersonnelDao;
import com.qst.dao.UserDao;
import com.qst.entity.TestPersonnel;
import com.qst.entity.User;
import com.qst.service.ITestPersonnelService;

import java.sql.SQLException;
import java.util.List;

//ITestPersonnelService接口的实现类
public class TestPersonnelServiceImpl implements ITestPersonnelService {
    private TestPersonnelDao personDao=DAOFactory.getDao(TestPersonnelDao.class);
    private UserDao userDao= DAOFactory.getDao(UserDao.class);

    public TestPersonnel findById(int id) {
        try {
            TestPersonnel stu=personDao.findById(id);
            stu.setUser(userDao.findById(id));
            return stu;
        }catch (SQLException ex){
            throw new ExamException("数据库查询出错",ex);
        }
    }

    @Override
    public List<TestPersonnel> findByTeamId(int tid) {
        try {
            List<TestPersonnel> testPersonnelList = personDao.findByTeam(tid);
            for (TestPersonnel testPersonnel : testPersonnelList) {
                testPersonnel.setUser(userDao.findById(testPersonnel.getId()));
            }
            return testPersonnelList;
        }catch (SQLException ex){
            throw new ExamException("数据库查询出错");
        }
    }


    @Override
    public List<TestPersonnel> findByTeam(int id) {
        try {
            return personDao.findByTeam(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<TestPersonnel> query(int teamId, String name, String stdNo) {
        try {
            return personDao.query(teamId,name,stdNo);
        }catch (SQLException ex){
            throw new ExamException("不存在该参测人员");
        }
    }

    @Override
    public void addTestPersonnel(TestPersonnel s) {
        try {
            User u = s.getUser();
            if (u.getType() == 0) {
                u.setType(4);
            }

            u.setStatus(1);
            if (u.getPasswd() == null || u.getPasswd().equals("")) {
                u.setPasswd(User.PASSWORD);
            }

            u.setLogin(s.getPhone());// 学号作为默认登录名
            userDao.insert(u);
            s.setId(u.getId());
            personDao.insert(s);
        } catch (SQLException ex) {
            throw new ExamException("添加参测人员出错", ex);
        }
    }

    @Override
    public void importTestPersonnel(List<TestPersonnel> testPersonnelList) {
        try {
            for (int i = 0; i < testPersonnelList.size(); i++) {
                TestPersonnel s = testPersonnelList.get(i);
                TestPersonnel temp = personDao.findByphone(s.getPhone());
                if (temp == null)
                {
                    addTestPersonnel(s);
                } else
                {
                    if (temp.getTeamId() != s.getTeamId()) {
                        throw new ExamException("导入第" + (i + 1) + "个参测人员出错,该手机号"
                                + s.getPhone() + "已在其他参测人员注册");
                    }
                    s.setId(temp.getId());
                    updateTestPersonnel(s);

                }
            }
        } catch (SQLException ex) {
            throw new ExamException("导入参测人员出错", ex);
        }
    }

    @Override
    public void updateTestPersonnel(TestPersonnel s) {
        try {
            User user = s.getUser();
            User u = userDao.findById(s.getId());
            u.setName(user.getName());
            if (user.getStatus() != 0) {
                u.setStatus(user.getStatus());
            }
            userDao.updateUser(u);
            personDao.update(s);
        } catch (SQLException ex) {
            throw new ExamException("更新参测人员出错", ex);
        }
    }


    @Override
    public TestPersonnel deleteTestPersonnel(int id) {
        try {

            User user = userDao.findById(id);
            if (user.getLastLogin() != null) {
                throw new ExamException("该账户已启用，不能删除");
            }
            TestPersonnel testPersonnel = personDao.findById(id);
            personDao.delete(id);
            userDao.delUserById(id);

            testPersonnel.setUser(user);
            return testPersonnel;
        } catch (SQLException e) {
            throw new ExamException("删除用户出错", e);
        }
    }
}
