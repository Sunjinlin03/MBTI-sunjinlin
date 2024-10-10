package com.qst.action.question;

import com.qst.BaseServlet;
import com.qst.RequestUtil;
import com.qst.WebUtil;
import com.qst.entity.AssessmentType;
import com.qst.entity.Choice;
import com.qst.entity.PersonalityDimension;
import com.qst.entity.Question;
import com.qst.service.IAssessmentTypeService;
import com.qst.service.IQuestionService;
import com.qst.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

//@WebServlet("/question/view.action")
public class ViewServlet extends BaseServlet {

    private IAssessmentTypeService assessmentTypeService= ServiceFactory.getService(IAssessmentTypeService.class);
    private IQuestionService questionService=ServiceFactory.getService(IQuestionService.class);


    @Override
    protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Question question=questionService.findById(RequestUtil.getInt(request,"id"));
        List<Choice> choices=questionService.findChoices(question.getId());
        AssessmentType assessment=assessmentTypeService.findTypeById(question.getAssessmentId());
        List<PersonalityDimension> dimension =questionService.findDimensionByQuestion(question.getId());
        request.setAttribute("assessment",assessment);
        request.setAttribute("question",question);
        request.setAttribute("choices",choices);
        request.setAttribute("dimension",dimension);

        WebUtil.forward(request,response,"/question/view.jsp");
    }
}
