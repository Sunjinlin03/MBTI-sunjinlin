package com.qst.service;

import com.qst.entity.PersonalityDimension;

import java.util.ArrayList;
import java.util.List;

public interface IDimensionService {
    List<PersonalityDimension> findDimensionByAssessmentId(int assessmentId);

    PersonalityDimension findDimensionById(int id);
    void saveDimension(PersonalityDimension pd);
    void updateDimension(PersonalityDimension pd);
    void deleteDimension(int id);
}
