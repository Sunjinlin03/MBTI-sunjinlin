package com.qst.mapper;

import com.qst.pojo.AssessmentType;

import java.util.List;

public interface AssessmentMapper {


    List<AssessmentType> findAllAssessment();

    AssessmentType findById(int id);

    void deleted(Integer id);

    void insert(AssessmentType assessment);

    void update(AssessmentType assessment);
}
