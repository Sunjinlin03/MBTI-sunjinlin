package com.qst.action.question;

import com.qst.BaseServlet;
import com.qst.RequestUtil;
import com.qst.WebUtil;
import com.qst.entity.Question;
import com.qst.service.IQuestionService;
import com.qst.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/question/delete.action")
public class DeleteServlet extends BaseServlet {
    private IQuestionService questionService= ServiceFactory.getService(IQuestionService.class);

    @Override
    protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= RequestUtil.getInt(request,"id");
        Question qs=questionService.findById(id);
        try {
            questionService.delete(id);
            addMessage(request,"题目已删除");
        }catch (Exception ex){
            addError(request,ex.getMessage());
        }
        String url="/question/list.action?assessmentId="+ qs.getAssessmentId()+"&status="+qs.getStatus()+"&type="+qs.getType();
        WebUtil.redirect(request,response,url);
    }
}
