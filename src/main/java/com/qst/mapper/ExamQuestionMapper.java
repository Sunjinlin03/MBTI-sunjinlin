package com.qst.mapper;

import com.qst.pojo.ExamQuestion;

public interface ExamQuestionMapper {

    void deleteByExam(Integer id);

    void save(ExamQuestion eq);
}
