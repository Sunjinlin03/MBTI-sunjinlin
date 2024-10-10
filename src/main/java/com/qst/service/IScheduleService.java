package com.qst.service;

import com.qst.entity.Schedule;
import com.qst.entity.User;

import java.util.List;

public interface IScheduleService {
    List<Schedule> findByCreator(User user);
    Schedule findById(Integer id);
    void saveSchedule(Schedule sh);
    void updateSchedule(Schedule sh);
    Schedule deleteSchedule(Integer id);
}
