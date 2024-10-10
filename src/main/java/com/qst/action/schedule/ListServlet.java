package com.qst.action.schedule;

import com.qst.BaseServlet;
import com.qst.WebUtil;
import com.qst.entity.Schedule;
import com.qst.entity.User;
import com.qst.service.IScheduleService;
import com.qst.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

//@WebServlet("/schedule/list.action")
public class ListServlet extends BaseServlet {
    private IScheduleService scheduleService= ServiceFactory.getService(IScheduleService.class);

//    @Override
//    protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        User user=getCurrentUser(request);
//
//        List<Schedule> scheduleList=scheduleService.findByCreator(user);
//        request.setAttribute("scheduleList",scheduleList);
//
//        WebUtil.forward(request,response,"/schedule/list.jsp");
//    }
}
