package com.qst.service.impl;

import com.qst.mapper.AssessmentMapper;
import com.qst.pojo.AssessmentType;
import com.qst.service.AssessmentSsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AssessmentSsmServiceImpl implements AssessmentSsmService {

    @Autowired
    private AssessmentMapper assessmentMapper;

    @Override
    public List<AssessmentType> findAllAssessment() {
        return assessmentMapper.findAllAssessment();
    }

    @Override
    public AssessmentType findById(int id) {
        return assessmentMapper.findById(id);
    }

    @Override
    public void updateAssessmentService(AssessmentType assessment) {
         assessmentMapper.update(assessment);
    }
//
    @Override
    public void deleteAssessment(Integer id) {
        assessmentMapper.deleted(id);
    }
//
    @Override
    public void saveAssessment(AssessmentType assessment) {
        assessmentMapper.insert(assessment);
    }
}
