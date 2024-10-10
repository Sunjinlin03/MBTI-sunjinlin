package com.qst.action.user;

import com.qst.BaseServlet;
import com.qst.WebUtil;
import com.qst.entity.Team;
import com.qst.service.ITeamService;
import com.qst.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

//@WebServlet("/user/showReg.do")
public class ShowRegServlet extends BaseServlet {
    private ITeamService teamService= ServiceFactory.getService(ITeamService.class);


    @Override
    protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Team> teamList=teamService.findAll();
            request.setAttribute("teamList",teamList);
            WebUtil.forward(request,response,"/user/reg.jsp");
        }catch (Exception ex){
            addError(request,ex.getMessage());
        }
    }
}
