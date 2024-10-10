package com.qst.service;

import com.qst.pojo.Schedule;
import com.qst.pojo.User;

import java.util.List;

public interface IScheduleSsmService {
    List<Schedule> findByCreator(User user);

    Schedule findById(Integer id);

    void saveSchedule(Schedule schedule);

    void updateSchedule(Schedule schedule);

    void deleteSchedule(Integer id);

}
