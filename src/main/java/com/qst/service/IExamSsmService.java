package com.qst.service;

import com.qst.pojo.Exam;
import com.qst.pojo.Schedule;
import com.qst.pojo.TestPersonnel;

import java.util.List;

public interface IExamSsmService {

    List<Schedule> findScheduleByTestPersonnel(TestPersonnel testPersonnel);
    Exam begin(TestPersonnel testPersonnel,int scheduleId);

    void end(Exam exam);
}
