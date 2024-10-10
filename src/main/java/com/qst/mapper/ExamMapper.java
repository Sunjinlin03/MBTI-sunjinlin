package com.qst.mapper;

import com.qst.pojo.Exam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamMapper {
    List<Exam>findByTestPersonnel(int id);

    Exam findByTestPersonnelAndSchedule(@Param("studentId") Integer studentId,
                                        @Param("scheduleId") Integer scheduleId);

    void updateExam(Exam exam);

    void saveExam(Exam exam);
}
