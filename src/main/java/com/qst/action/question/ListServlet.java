package com.qst.action.question;

import com.qst.BaseServlet;
import com.qst.RequestUtil;
import com.qst.WebUtil;
import com.qst.entity.AssessmentType;
import com.qst.entity.Question;
import com.qst.service.IAssessmentTypeService;
import com.qst.service.IQuestionService;
import com.qst.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

//@WebServlet("/question/list.action")
public class ListServlet extends BaseServlet {
    private IAssessmentTypeService assessmentTypeService = ServiceFactory.getService(IAssessmentTypeService.class);
    private IQuestionService questionService = ServiceFactory.getService(IQuestionService.class);


    @Override
    protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submitButton = RequestUtil.getString(request, "create");

        if (submitButton.length() > 0) {
            doCreate(request, response);
        } else {
            doList(request, response);
        }
    }

    protected void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<AssessmentType> assessmentList = assessmentTypeService.findAllAssessmentType();
        request.setAttribute("assessmentList", assessmentList);

        QuestionQueryParam param = QuestionQueryParam.create(request);
        request.setAttribute("query", param);
        if (param.getAssessmentId()>0){
            List<Question> questionList = questionService.find(param);
            request.setAttribute("questionList", questionList);
        }

        WebUtil.forward(request, response, "/question/list.jsp");
    }


    protected void doCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QuestionQueryParam param = QuestionQueryParam.create(req);
        req.setAttribute("assessment", assessmentTypeService.findTypeById(param.getAssessmentId()));
        Question qs=new Question();
        qs.setType(param.getType());
        qs.setDifficulty(4);
        req.setAttribute("question",qs);
        WebUtil.forward(req,resp,"/question/create.jsp");


    }
}
