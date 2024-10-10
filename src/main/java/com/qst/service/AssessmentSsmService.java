package com.qst.service;

import com.qst.pojo.AssessmentType;

import java.util.List;

public interface AssessmentSsmService {
    List<AssessmentType> findAllAssessment();

    AssessmentType findById(int id);
//
    void updateAssessmentService(AssessmentType assessment);
//
    void deleteAssessment(Integer id);
//
    void saveAssessment(AssessmentType assessment);

}
