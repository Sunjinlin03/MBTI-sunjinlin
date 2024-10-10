package com.qst.service.impl;

import com.qst.ExamException;
import com.qst.dao.AssessmentTypeDao;
import com.qst.dao.DAOFactory;
import com.qst.entity.AssessmentType;
import com.qst.service.IAssessmentTypeService;

import java.util.List;

public class AssessmentTypeServiceImpl implements IAssessmentTypeService {

    //注入工厂方法
    private AssessmentTypeDao assessmentTypeDAO= DAOFactory.getDao(AssessmentTypeDao.class);

    @Override
    public List<AssessmentType> findAllAssessmentType() {
        return assessmentTypeDAO.findAll();
    }

    @Override
    public AssessmentType findTypeById(int id) {
        return assessmentTypeDAO.findTypeById(id);
    }


    //添加


    @Override
    public void insertType(AssessmentType as) {
        AssessmentType temp=assessmentTypeDAO.findByTitle(as.getTitle());
        if (temp!=null){
            throw new ExamException("考核类型已经存在");
        }
        assessmentTypeDAO.insertType(as);
    }

    //删除方法重写
    @Override
    public void deleteTypeById(int id) {
        AssessmentType assessmentType=assessmentTypeDAO.findTypeById(id);
        assessmentTypeDAO.deleteTypeById(id);
    }


    //修改方法重写

    @Override
    public void updateType(AssessmentType as) {
        AssessmentType temp=assessmentTypeDAO.findByTitle(as.getTitle());
        if (temp!=null&&temp.getId()!=as.getId()){
            throw new ExamException("该考核类型已经存在");
        }
        assessmentTypeDAO.updateType(as);
    }

}
