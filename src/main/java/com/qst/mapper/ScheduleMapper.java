package com.qst.mapper;

import com.qst.pojo.Schedule;

import java.util.List;

public interface ScheduleMapper {

    List<Schedule> findByCreatorId(Integer id);

    Schedule findById(Integer id);

    void save(Schedule schedule);

    void update(Schedule schedule);

    void delete(Integer id);

    List<Schedule>findByTeamId(int teamId);
}
