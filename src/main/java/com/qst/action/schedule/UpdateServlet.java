package com.qst.action.schedule;

import com.qst.BaseServlet;
import com.qst.ExamException;
import com.qst.RequestUtil;
import com.qst.WebUtil;
import com.qst.entity.Schedule;
import com.qst.pojo.User;
import com.qst.service.IScheduleService;
import com.qst.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

//@WebServlet("/schedule/update.action")
public class UpdateServlet extends BaseServlet {
    private IScheduleService scheduleService = ServiceFactory.getService(IScheduleService.class);

    @Override
    protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Schedule schedule = new Schedule();
        try {
            User user = getCurrentUser(request);
            schedule.setId(RequestUtil.getInt(request, "id"));
            schedule.setBeginDate(RequestUtil.getString(request, "beginDate"));
            schedule.setEndDate(RequestUtil.getString(request, "endDate"));
            schedule.setDuration(RequestUtil.getInt(request, "duration"));
            schedule.setCreateDate(RequestUtil.getDate(request, "createDate"));
            schedule.setStatus(RequestUtil.getInt(request, "status"));
            schedule.setAssessmentId(RequestUtil.getInt(request, "assessmentId"));
            schedule.setTeamId(RequestUtil.getInt(request, "teamId"));
            schedule.setQuestionNumber(RequestUtil.getInt(request, "questionNumber"));
            schedule.setCreatorId(user.getId());
            scheduleService.updateSchedule(schedule);
            WebUtil.redirect(request, response, "/schedule/view.action?id=" + schedule.getId());
        } catch (ExamException ex) {
            ex.printStackTrace();
            addError(request, ex.getMessage());
            request.setAttribute("schedule", schedule);
            WebUtil.forward(request, response, "/schedule/update.action");
        }
    }

    }