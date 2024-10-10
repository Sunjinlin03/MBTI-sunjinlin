package com.qst.action.schedule;

import com.qst.BaseServlet;
import com.qst.RequestUtil;
import com.qst.WebUtil;
import com.qst.entity.Schedule;
import com.qst.service.IScheduleService;
import com.qst.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

//@WebServlet("/schedule/view.action")
public class ViewServlet extends BaseServlet {
    private IScheduleService scheduleService= ServiceFactory.getService(IScheduleService.class);


    @Override
    protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id= RequestUtil.getInt(request,"id");
        Schedule schedule=scheduleService.findById(id);
        request.setAttribute("schedule",schedule);
        WebUtil.forward(request,response,"/schedule/view.jsp");
    }
}
