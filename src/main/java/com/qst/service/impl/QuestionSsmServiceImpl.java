package com.qst.service.impl;

import com.qst.ExamException;
import com.qst.controller.QuestionQueryParam;
import com.qst.mapper.ChoiceMapper;
import com.qst.mapper.QuestionMapper;
import com.qst.pojo.Choice;
import com.qst.pojo.PersonalityDimension;
import com.qst.pojo.Question;
import com.qst.service.IQuestionSsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionSsmServiceImpl implements IQuestionSsmService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private ChoiceMapper choiceMapper;

    //问题列表的查看
    @Override
    public List<Question> find(QuestionQueryParam param) {
        List<Question> questions;
        int aId = param.getAssessmentId();
        if (aId>0) {
            questions = questionMapper.find(param);
        } else {
            questions = questionMapper.find2(param);
        }
        return questions;
    }

    //通过id查找题目
    @Override
    public Question findById(int id) {
        return questionMapper.findById(id);
    }

    //通过题目id查找题目答案
    @Override
    public List<Choice> findChoices(Integer id) {
        return questionMapper.findChoices(id);
    }

    //通过题目id查找题目对应的性格维度信息
    @Override
    public List<PersonalityDimension> findDimensionByQuestion(Integer id) {
        return questionMapper.findDimensionByQuestion(id);
    }

    //删除试题
    @Override
    public void delete(int id) {
        questionMapper.delete(id);
    }

    //添加新的试题
    @Override
    public void save(Question question, List<Choice> choices) {
        isChoiceValid(question.getType(), choices);
        question.setStatus(2);
        questionMapper.insertQuestion(question);
        for (Choice ch : choices) {
            ch.setQuestionId(question.getId());
            choiceMapper.insertChoice(ch);
        }
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
    public List<Integer> findDimensionIdByQuestion(int id) {
        return questionMapper.findDimensionId(id);
    }


    @Override
    public void attachDimension(int qid, int[] pids) {
        questionMapper.detachDimension(qid);
        for (int p:pids) {
            questionMapper.attachDimension(qid,p);
        }
    }


}
