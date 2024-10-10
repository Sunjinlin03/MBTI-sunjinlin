package com.qst.service.impl;

import com.qst.ExamException;
import com.qst.action.question.QuestionQueryParam;
import com.qst.dao.ChoiceDao;
import com.qst.dao.DAOFactory;
import com.qst.dao.DimensionDao;
import com.qst.dao.QuestionDao;
import com.qst.entity.Choice;
import com.qst.entity.PersonalityDimension;
import com.qst.entity.Question;
import com.qst.service.IQuestionService;

import java.util.List;

public class QuestionServiceImpl implements IQuestionService {

    private QuestionDao questionDAO = DAOFactory.getDao(QuestionDao.class);
    private DimensionDao dimensionDAO = DAOFactory.getDao(DimensionDao.class);
    private ChoiceDao choiceDAO = DAOFactory.getDao(ChoiceDao.class);


    @Override
    public List<Question> find(QuestionQueryParam param) {

        List<Question> questions = questionDAO.find(param);
        return questions;
    }


    @Override
    public Question findById(int id) {
        return questionDAO.findById(id);
    }

    @Override
    public List<Choice> findChoices(Integer id) {
        return choiceDAO.findByQuestion(id);
    }

    @Override
    public List<PersonalityDimension> findDimensionByQuestion(Integer id) {

        return dimensionDAO.findDimensionByQuestion(id);
    }

    //校验输入信息
    private void isChoiceValid(int type, List<Choice> choices) {
        if (choices == null || choices.isEmpty()) {
            throw new ExamException("选项列表为空");
        }
        int count = 0;
        for (Choice ch : choices) {
            if (ch.isChecked()) {
                count++;
            }
        }
        if (count == 0) {
            throw new ExamException("请选择本题的正确答案");
        }
        if (type == 1 && count != 1) {
            throw new ExamException("单选题只能有一个正确答案");
        }
        if (type == 2 && count < 2) {
            throw new ExamException("多选题至少要有两个正确答案");
        }
    }

    @Override
    public void save(Question qs, List<Choice> choices) {
        isChoiceValid(qs.getType(), choices);
        qs.setStatus(2);
        questionDAO.insertQuestion(qs);
        for (Choice ch : choices) {
            ch.setQuestionId(qs.getId());
            choiceDAO.insertChoice(ch);
        }
    }

    @Override
    public void attachDimension(int questionId, int[] dimension) {
        questionDAO.detachDimension(questionId);
        for (int p : dimension) {
            questionDAO.attachDimension(questionId, p);
        }
    }


    @Override
    public List<Integer> findDimensionIdByQuestion(int id) {
        return questionDAO.findDimension(id);
    }


    @Override
    public void update(Question qs, List<Choice> choices) {
        isChoiceValid(qs.getType(),choices);
        questionDAO.updateQuestion(qs);
        for (Choice ch:choices){
            ch.setQuestionId(qs.getId());
            choiceDAO.updateChoice(ch);
        }
    }


    @Override
    public void delete(int questionId) {
        if (questionDAO.isUsed(questionId)){
            throw new ExamException("使用中的题目，不能被删除");
        }
        questionDAO.detachDimension(questionId);
        choiceDAO.deleteByQuestion(questionId);
        questionDAO.delete(questionId);

    }



}
