package com.qst.mapper;

import com.qst.controller.QuestionQueryParam;
import com.qst.pojo.Choice;
import com.qst.pojo.PersonalityDimension;
import com.qst.pojo.Question;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface QuestionMapper {

    List<Question> find(QuestionQueryParam param);

    List<Question> find2(QuestionQueryParam param);

    Question findById(int id);

    List<Choice> findChoices(Integer id);

    List<PersonalityDimension> findDimensionByQuestion(Integer id);

    void delete(int id);

    void insertQuestion(Question question);

    void detachDimension(int id);

    void attachDimension(int id, int p);

    //@Select("select dimension_id from question_dimension where question_id=#{id}")
    List<Integer> findDimensionId(int id);

}
