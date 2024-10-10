package com.qst.action.question;

import com.qst.BaseServlet;
import com.qst.Constant;
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
import java.util.List;

@WebServlet("/question/update.action")
public class UpdateServlet extends BaseServlet {
    private IAssessmentTypeService assessmentTypeService = ServiceFactory.getService(IAssessmentTypeService.class);
    private IQuestionService questionService = ServiceFactory.getService(IQuestionService.class);

    @Override
    protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Question question= QuestionHelper.createQuestion(request);
        List<Choice> choices= QuestionHelper.createChoice(request);
        try {
            User currenUser= (User) request.getSession().getAttribute(Constant.CURRENT_USER);
            question.setUserId(currenUser.getId());
            questionService.update(question,choices);
            addMessage(request,"题目已经保存至数据库中，请关联性格维度");
            WebUtil.redirect(request,response,"/question/dimension.action?method=show&id="+question.getId());

        }catch (Exception ex){
            addError(request,ex.getMessage());
            request.setAttribute("question",question);
            request.setAttribute("choices",choices);
            request.setAttribute("assessment",assessmentTypeService.findTypeById(question.getAssessmentId()));
            WebUtil.forward(request,response,"/question/edit.jsp");
        }


    }
}
