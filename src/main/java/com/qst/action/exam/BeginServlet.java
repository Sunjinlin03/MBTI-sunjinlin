package com.qst.action.exam;

import com.qst.BaseServlet;
import com.qst.RequestUtil;
import com.qst.WebUtil;
import com.qst.entity.Exam;
import com.qst.entity.TestPersonnel;
import com.qst.service.IAssessmentTypeService;
import com.qst.service.IExamService;
import com.qst.service.IScheduleService;
import com.qst.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static com.qst.Constant.CURRENT_EXAM;
import static com.qst.Constant.CURRENT_TESTPERSONNEL;

//@WebServlet("/exam/begin.action")
public class BeginServlet extends BaseServlet {
    private IAssessmentTypeService assessmentTypeService= ServiceFactory.getService(IAssessmentTypeService.class);
    private IExamService examService=ServiceFactory.getService(IExamService.class);
    private IScheduleService scheduleService=ServiceFactory.getService(IScheduleService.class);


    @Override
    protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TestPersonnel testPersonnel = (TestPersonnel) request.getSession().getAttribute(CURRENT_TESTPERSONNEL);
        int scheduleId= RequestUtil.getInt(request,"id");
        try {
            // 生成某个考生的试题：包含考生信息和试题集合
            Exam exam=examService.begin(testPersonnel,scheduleId);
            request.getSession().setAttribute(CURRENT_EXAM, exam);
            WebUtil.redirect(request,response,"/exam/exam.action");
        }catch (Exception ex){
            ex.printStackTrace();
            addError(request,ex.getMessage());
            WebUtil.redirect(request,response,"/exam/list.action");
        }
    }
}
