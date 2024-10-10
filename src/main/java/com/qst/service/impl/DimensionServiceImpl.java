package com.qst.service.impl;

import com.qst.ExamException;
import com.qst.dao.DAOFactory;
import com.qst.dao.DimensionDao;
import com.qst.dao.QuestionDao;
import com.qst.entity.AssessmentType;
import com.qst.entity.PersonalityDimension;
import com.qst.service.IDimensionService;

import java.util.List;

public class DimensionServiceImpl implements IDimensionService {
    private DimensionDao dimensionDao= DAOFactory.getDao(DimensionDao.class);


    @Override
    public List<PersonalityDimension> findDimensionByAssessmentId(int assessmentId) {
        return dimensionDao.findByAssessmentId(assessmentId);
    }


    @Override
    public PersonalityDimension findDimensionById(int id) {
        return dimensionDao.findById(id);
    }


    @Override
    public void saveDimension(PersonalityDimension pd) {
        PersonalityDimension temp=dimensionDao.findByAssessmentIdAndTitle(pd.getAssessmentId(),pd.getTitle());
        if (temp==null){
            dimensionDao.insertDimension(pd);
        }else {
            throw new ExamException("性格维度名称已经存在");
        }
    }

    @Override
    public void updateDimension(PersonalityDimension pd) {
        PersonalityDimension temp=dimensionDao.findByAssessmentIdAndTitle(pd.getAssessmentId(),pd.getTitle());
        if (temp==null || temp.getId()==pd.getId()){
            dimensionDao.updateDimension(pd);
        }else {
            throw new ExamException("本性格维度名称已经存在");
        }
    }

    private QuestionDao questionDAO=DAOFactory.getDao(QuestionDao.class);
    @Override
    public void deleteDimension(int id) {
        int count=questionDAO.findCountByDimension(id);
        if (count>0){
            throw new ExamException("本知识点已录入考题，不能在删除");
        }
        dimensionDao.deleteDimension(id);
    }
}
