package com.qst.action.schedule;

import com.qst.BaseServlet;
import com.qst.WebUtil;
import com.qst.entity.AssessmentType;
import com.qst.entity.Schedule;
import com.qst.entity.Team;
import com.qst.pojo.User;
import com.qst.service.IAssessmentTypeService;
import com.qst.service.IScheduleService;
import com.qst.service.ITeamService;
import com.qst.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//@WebServlet("/schedule/create.action")
public class CreateServlet extends BaseServlet {
    private IScheduleService scheduleService = ServiceFactory.getService(IScheduleService.class);
    private IAssessmentTypeService assessmentTypeService = ServiceFactory.getService(IAssessmentTypeService.class);
    private ITeamService teamService = ServiceFactory.getService(ITeamService.class);

    @Override
    protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = getCurrentUser(request);
        Schedule schedule = (Schedule) request.getAttribute("schedule");
        if (schedule == null) {
            schedule = new Schedule();
            Date today = new Date(System.currentTimeMillis());
            SimpleDateFormat stf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            schedule.setBeginDate(stf.format(today));
            schedule.setEndDate(stf.format(today));

            request.setAttribute("schedule", schedule);
        }

        List<Team> teamList = teamService.findByCreator(user.getId());
        request.setAttribute("teamList", teamList);
        List<AssessmentType> assessmentTypeList = assessmentTypeService.findAllAssessmentType();
        request.setAttribute("assessmentList", assessmentTypeList);

        WebUtil.forward(request, response, "/schedule/create.jsp");
    }
}
