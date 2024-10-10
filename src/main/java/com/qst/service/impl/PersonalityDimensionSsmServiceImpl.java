package com.qst.service.impl;

import com.qst.ExamException;
import com.qst.mapper.DimensionMapper;
import com.qst.pojo.PersonalityDimension;
import com.qst.service.PersonalityDimensionSsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonalityDimensionSsmServiceImpl implements PersonalityDimensionSsmService {
    @Autowired
    private DimensionMapper dimensionMapper;

    @Override
    public List<PersonalityDimension> findAllDimensionByAssessment(int id) {
        return dimensionMapper.findAllByAssessment(id);
    }
//
    @Override
    public PersonalityDimension findById(int id) {
        return dimensionMapper.findById(id);
    }
//
    @Override
    public void addDimension(PersonalityDimension personalityDimension) {
       PersonalityDimension temp = dimensionMapper.findByIdAssessmentIdAndTitle(personalityDimension);
       if (temp == null || temp.getId() == null) {
           dimensionMapper.insert(personalityDimension);
       }else {
           throw new ExamException("相关维度名称已存在");
       }
    }
//
    @Override
    public void updateDimension(PersonalityDimension personalityDimension) {

            dimensionMapper.update(personalityDimension);

    }
//
    @Override
    public void deleteDimension(Integer id) {
        dimensionMapper.deleted(id);
    }

    @Override
    public List<PersonalityDimension> findAllDimension() {
        return dimensionMapper.findAllDimension();
    }
}
