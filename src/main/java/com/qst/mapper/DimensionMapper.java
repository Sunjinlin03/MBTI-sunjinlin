package com.qst.mapper;


import com.qst.pojo.PersonalityDimension;

import java.util.List;

public interface DimensionMapper {


    List<PersonalityDimension> findAllByAssessment(int id);

    PersonalityDimension findById(int id);

    void deleted(Integer id);

    void update(PersonalityDimension personalityDimension);

    PersonalityDimension findByIdAssessmentIdAndTitle(PersonalityDimension personalityDimension);

    void insert(PersonalityDimension personalityDimension);

    List<PersonalityDimension> findAllDimension();
}
