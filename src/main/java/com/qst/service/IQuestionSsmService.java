package com.qst.service;

import com.qst.controller.QuestionQueryParam;
import com.qst.pojo.Choice;
import com.qst.pojo.PersonalityDimension;
import com.qst.pojo.Question;

import java.util.List;

public interface IQuestionSsmService {
    List<Question> find(QuestionQueryParam param);

    Question findById(int id);

    List<Choice> findChoices(Integer id);

    List<PersonalityDimension> findDimensionByQuestion(Integer id);

    void delete(int id);

    void save(Question question, List<Choice> choices);

    List<Integer> findDimensionIdByQuestion(int qid);

    void attachDimension(int qid, int[] pids);
}
