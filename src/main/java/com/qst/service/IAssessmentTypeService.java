package com.qst.service;

import com.qst.entity.AssessmentType;

import java.util.List;

public interface IAssessmentTypeService {
    //获取所有类型信息
    List<AssessmentType> findAllAssessmentType();
    //根据Id获取详细类型信息
    AssessmentType findTypeById(int id);

    //添加信息
    void insertType(AssessmentType as);
    //根据id删除类型数据
    void deleteTypeById(int id);

    //修改
    void updateType(AssessmentType as);
}
