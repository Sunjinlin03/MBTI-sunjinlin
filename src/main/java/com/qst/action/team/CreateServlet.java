package com.qst.action.team;

import com.qst.BaseServlet;
import com.qst.ExamException;
import com.qst.WebUtil;
import com.qst.entity.Team;
import com.qst.service.ITeamService;
import com.qst.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/team/create.action6")
public class CreateServlet extends BaseServlet {
    private ITeamService classTeamService = ServiceFactory.getService(ITeamService.class);

    @Override
    protected void doAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Team team = new Team();
        team.setStatus(1);
        try {
            req.setAttribute("team", team);
        } catch (ExamException ex) {
            addError(req, ex.getMessage());
        }
        WebUtil.forward(req, resp, "/team/create.jsp");
    }
}
