package com.qst.action.exam;

import com.qst.BaseServlet;
import com.qst.RequestUtil;
import com.qst.WebUtil;
import com.qst.entity.Choice;
import com.qst.entity.Exam;
import com.qst.entity.ExamQuestion;
import com.qst.service.IQuestionService;
import com.qst.service.ServiceFactory;
import com.qst.service.impl.QuestionServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.qst.Constant.CURRENT_EXAM;

//@WebServlet("/exam/exam.action")
public class ExamServlet extends BaseServlet {

    private IQuestionService questionService = ServiceFactory.getService(IQuestionService.class);

    @Override
    protected void doAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Exam exam = (Exam) req.getSession().getAttribute(CURRENT_EXAM);

        int[] answer = RequestUtil.getIntArray(req, "answer");

        ExamQuestion eq = exam.getQuestion();
        eq.setAnswer(answer);

        int index = RequestUtil.getInt(req, "index");// -1：前一题，－２　后一题，　其他：指定下标
        if (index == -2) // 后一题
        {
            exam.setQuestionIndex(exam.getQuestionIndex() + 1);
        } else if (index == -1)// 前一题
        {
            exam.setQuestionIndex(exam.getQuestionIndex() - 1);
        }else if (index == -3)// 交卷
        {
            WebUtil.forward(req, resp, "/exam/end.action");
        } else // 指定下标的题
        {
            exam.setQuestionIndex(index);
        }
        eq = exam.getQuestion();

        // 第一次加载题目时，题目没有选项，因为生成题目时，没有生成选项
        List<Choice> choices = eq.getQuestion().getChoices();
        if (choices == null) {
            choices = questionService.findChoices(eq.getQuestionId());
            Collections.shuffle(choices);
            eq.getQuestion().setChoices(choices);
        } else {
            // 确定哪个choice在页面上应该被选中
            for (Choice ch : choices) {
                ch.getExtras().remove("checked");
                if (eq.getAnswer() != null) {
                    for (int ans : eq.getAnswer()) {
                        if (ans == ch.getId()) {
                            ch.getExtras().put("checked", "checked");
                            break;
                        }
                    }
                }
            }

        }

        WebUtil.forward(req, resp, "/exam/exam.jsp");

    }

}
