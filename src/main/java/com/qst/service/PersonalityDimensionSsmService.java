package com.qst.service;

import com.qst.pojo.PersonalityDimension;

import java.util.List;

public interface PersonalityDimensionSsmService {
    List<PersonalityDimension> findAllDimensionByAssessment(int id);
//
    PersonalityDimension findById(int id);
//
    void addDimension(PersonalityDimension personalityDimension);
//
    void updateDimension(PersonalityDimension personalityDimension);
//
    void deleteDimension(Integer id);

    List<PersonalityDimension> findAllDimension();
}
