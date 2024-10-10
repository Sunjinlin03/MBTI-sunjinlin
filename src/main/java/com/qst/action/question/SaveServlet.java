package com.qst.action.question;

import com.qst.BaseServlet;
import com.qst.Constant;
import com.qst.RequestUtil;
import com.qst.WebUtil;
import com.qst.entity.Choice;
import com.qst.entity.Question;
import com.qst.pojo.User;
import com.qst.service.IAssessmentTypeService;
import com.qst.service.IQuestionService;
import com.qst.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@WebServlet("/question/save.action")
public class SaveServlet extends BaseServlet {
    private IAssessmentTypeService assessmentTypeService = ServiceFactory.getService(IAssessmentTypeService.class);
    private IQuestionService questionService = ServiceFactory.getService(IQuestionService.class);

    @Override
    protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Question question=QuestionHelper.createQuestion(request);
        List<Choice> choices=QuestionHelper.createChoice(request);
        try {
            User currentUser=(User)request.getSession().getAttribute(Constant.CURRENT_USER);
            question.setUserId(currentUser.getId());
            questionService.save(question,choices);
            addMessage(request,"试题保存在数据库中");
            WebUtil.redirect(request,response,"/question/dimension.action?id="+question.getId());
        }catch (Exception ex){
            System.out.println(ex);
            addError(request,ex.getMessage());
            request.setAttribute("question",question);
            request.setAttribute("choices",choices);
            request.setAttribute("assessment",assessmentTypeService.findTypeById(question.getAssessmentId()));
            WebUtil.forward(request,response,"/question/create.jsp");
        }
    }




}
