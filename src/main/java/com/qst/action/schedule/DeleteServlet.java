package com.qst.action.schedule;

import com.qst.BaseServlet;
import com.qst.ExamException;
import com.qst.RequestUtil;
import com.qst.WebUtil;
import com.qst.service.IScheduleService;
import com.qst.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

//@WebServlet("/schedule/delete.action")
public class DeleteServlet extends BaseServlet {

    private IScheduleService scheduleService = ServiceFactory.getService(IScheduleService.class);

    @Override
    protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = RequestUtil.getInt(request, "id");
        try {

            scheduleService.deleteSchedule(id);
        } catch (ExamException ex) {
            ex.printStackTrace();
            addError(request, ex.getMessage());
        }
        WebUtil.redirect(request, response, "/schedule/list.action");
    }

    }
