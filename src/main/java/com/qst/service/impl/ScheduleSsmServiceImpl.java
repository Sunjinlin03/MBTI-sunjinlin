package com.qst.service.impl;

import com.qst.mapper.AssessmentMapper;
import com.qst.mapper.ScheduleMapper;
import com.qst.mapper.TeamMapper;
import com.qst.mapper.UserMapper;
import com.qst.pojo.Schedule;
import com.qst.pojo.User;
import com.qst.service.IScheduleSsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ScheduleSsmServiceImpl implements IScheduleSsmService {
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private AssessmentMapper assessmentMapper;
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Schedule> findByCreator(User user) {
        List<Schedule>scheduleList = scheduleMapper.findByCreatorId(user.getId());
        for (Schedule h : scheduleList){
            h.setCreator(user);
            h.setAssessmentType(assessmentMapper.findById(h.getAssessmentId()));
            h.setTeam(teamMapper.findById(h.getTeamId()));
        }
        return scheduleList;
    }

    @Override
    public Schedule findById(Integer id) {
        Schedule sh=scheduleMapper.findById(id);
        sh.setCreator(userMapper.findUserById(sh.getCreatorId()));
        sh.setAssessmentType(assessmentMapper.findById(sh.getAssessmentId()));
        sh.setTeam(teamMapper.findById(sh.getTeamId()));
        return sh;
    }

    @Override
    public void saveSchedule(Schedule schedule) {
        scheduleMapper.save(schedule);
    }

    @Override
    public void updateSchedule(Schedule schedule) {
        scheduleMapper.update(schedule);
    }

    @Override
    public void deleteSchedule(Integer id) {
        scheduleMapper.delete(id);
    }
}
