package com.qst.service;

import com.qst.action.question.QuestionQueryParam;
import com.qst.entity.Choice;
import com.qst.entity.PersonalityDimension;
import com.qst.entity.Question;

import java.util.List;

public interface IQuestionService {
    List<Question> find(QuestionQueryParam param);
    Question findById(int id);
    List<Choice> findChoices(Integer id);
    List<PersonalityDimension> findDimensionByQuestion(Integer id);
    void save(Question qs,List<Choice> choices);
    void attachDimension(int questionId,int[] dimension);
    List<Integer> findDimensionIdByQuestion(int id);
    void update(Question qs,List<Choice> choices);
    void delete(int questionId);
}
