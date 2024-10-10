package com.qst.service.impl;

import com.qst.ExamException;
import com.qst.dao.*;
import com.qst.entity.Schedule;
import com.qst.entity.User;
import com.qst.service.IScheduleService;

import java.sql.SQLException;
import java.util.List;

public class ScheduleServiceImpl implements IScheduleService {

    private ScheduleDao scheduleDao= DAOFactory.getDao(ScheduleDao.class);
    private AssessmentTypeDao assessmentTypeDao=DAOFactory.getDao(AssessmentTypeDao.class);
    private TeamDao teamDao=DAOFactory.getDao(TeamDao.class);
    private UserDao userDao=DAOFactory.getDao(UserDao.class);


    @Override
    public List<Schedule> findByCreator(User user) {
        try {
            List<Schedule> scheduleList=scheduleDao.findByCreateId(user.getId());
            for (Schedule sh: scheduleList){
                sh.setCreator(user);
                sh.setAssessmentType(assessmentTypeDao.findTypeById(sh.getAssessmentId()));
                sh.setTeam(teamDao.findById(sh.getTeamId()));
            }
            return scheduleList;
        }catch (SQLException ex){
            throw new ExamException("查询考试安排出错",ex);
        }
    }


    @Override
    public Schedule findById(Integer id) {
        try {
            Schedule sh=scheduleDao.findById(id);
            sh.setCreator(userDao.findById(sh.getCreatorId()));
            sh.setAssessmentType(assessmentTypeDao.findTypeById(sh.getAssessmentId()));
            sh.setTeam(teamDao.findById(sh.getTeamId()));
            return sh;
        }catch (SQLException ex){
            throw new ExamException("查询测试安排出错",ex);
        }
    }

    @Override
    public void saveSchedule(Schedule sh) {
        try {
            scheduleDao.save(sh);
        }catch (SQLException ex){
            throw new ExamException("添加测试安排出错",ex);
        }
    }

    @Override
    public void updateSchedule(Schedule h) {
        try {
            scheduleDao.update(h);
        } catch (SQLException e) {
            throw new ExamException("更新测试安排出错", e);
        }

    }

    @Override
    public Schedule deleteSchedule(Integer id) {

        try {
            Schedule sche = scheduleDao.findById(id);
            if (sche.getStatus() > 1) {
                throw new ExamException("只有未开始或作废的安排才能删除");
            }
            scheduleDao.delete(sche.getId());
            return sche;
        } catch (SQLException e) {
            throw new ExamException("删除测试安排出错", e);
        }
    }
}
