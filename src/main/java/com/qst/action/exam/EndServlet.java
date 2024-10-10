package com.qst.action.exam;

import com.qst.BaseServlet;
import com.qst.ExamException;
import com.qst.WebUtil;
import com.qst.entity.Exam;
import com.qst.service.IAssessmentTypeService;
import com.qst.service.IExamService;
import com.qst.service.IScheduleService;
import com.qst.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static com.qst.Constant.CURRENT_EXAM;

//@WebServlet("/exam/end.action")
public class EndServlet extends BaseServlet {
    private IAssessmentTypeService assessmentService = ServiceFactory.getService(IAssessmentTypeService.class);
    private IExamService examService =ServiceFactory.getService(IExamService.class);
    private IScheduleService scheduleService = ServiceFactory.getService(IScheduleService.class);

    @Override
    protected void doAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Exam exam = (Exam) req.getSession().getAttribute(CURRENT_EXAM);
        try {
            examService.end(exam);
            StringBuffer stf=new StringBuffer();
            stf.append("../analysis/");
            stf.append(exam.getResult());
            stf.append(".jsp");
            req.setAttribute("examresult", stf.toString());
            req.setAttribute("score",exam.getResult());
        } catch (ExamException ex) {
            ex.printStackTrace();
            req.setAttribute("score", "您未能成功交卷，请重新交卷");
        }
        WebUtil.forward(req, resp, "/exam/end.jsp");


    }
}
