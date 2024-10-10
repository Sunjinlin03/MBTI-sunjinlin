package com.qst.service;

import com.qst.entity.Exam;
import com.qst.entity.Schedule;
import com.qst.entity.TestPersonnel;

import java.util.List;

public interface IExamService {
    List<Schedule> findScheduleByTestPersonnel(TestPersonnel personnel);
    Exam begin(TestPersonnel personnel,int scheduleId);
    void end(Exam exam);
}
