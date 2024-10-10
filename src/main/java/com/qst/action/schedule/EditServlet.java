package com.qst.action.schedule;

import com.qst.BaseServlet;
import com.qst.RequestUtil;
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
import java.util.List;

//@WebServlet("/schedule/edit.action")
public class EditServlet extends BaseServlet {

    private IScheduleService scheduleService = ServiceFactory.getService(IScheduleService.class);
    private IAssessmentTypeService assessmentService = ServiceFactory.getService(IAssessmentTypeService.class);
    private ITeamService teamService = ServiceFactory.getService(ITeamService.class);


    @Override
    protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = getCurrentUser(request);
        Integer id = RequestUtil.getInt(request, "id");
        Schedule schedule = (Schedule) request.getAttribute("schedule");
        if (schedule == null) {
            schedule = scheduleService.findById(id);
            request.setAttribute("schedule", schedule);
        }

        List<Team> teamList = teamService.findByCreator(user.getId());
        request.setAttribute("teamList", teamList);
        List<AssessmentType> assessmentList = assessmentService.findAllAssessmentType();
        request.setAttribute("assessmentList", assessmentList);
        WebUtil.forward(request, response, "/schedule/edit.jsp");
    }
}
