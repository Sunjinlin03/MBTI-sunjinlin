package com.qst.action.exam;

import com.qst.BaseServlet;
import com.qst.Constant;
import com.qst.WebUtil;
import com.qst.entity.Schedule;
import com.qst.entity.TestPersonnel;
import com.qst.service.IAssessmentTypeService;
import com.qst.service.IExamService;
import com.qst.service.IScheduleService;
import com.qst.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

//@WebServlet("/exam/list.action")
public class ListServlet extends BaseServlet {
    private IAssessmentTypeService assessmentService = ServiceFactory.getService(IAssessmentTypeService.class);
    private IExamService examService = ServiceFactory.getService(IExamService.class);
    private IScheduleService scheduleService = ServiceFactory.getService(IScheduleService.class);

    @Override
    protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TestPersonnel stu = (TestPersonnel) request.getSession().getAttribute(Constant.CURRENT_TESTPERSONNEL);
        List<Schedule> scheduleList = examService.findScheduleByTestPersonnel(stu);
        request.setAttribute("scheduleList", scheduleList);
        WebUtil.forward(request, response, "/exam/list.jsp");
    }


}
