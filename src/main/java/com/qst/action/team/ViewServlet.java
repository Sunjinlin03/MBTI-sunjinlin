package com.qst.action.team;

import com.qst.BaseServlet;
import com.qst.ExamException;
import com.qst.RequestUtil;
import com.qst.WebUtil;
import com.qst.service.ITeamService;
import com.qst.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/team/view.action6")
public class ViewServlet extends BaseServlet {

    private ITeamService teamService = ServiceFactory.getService(ITeamService.class);

    @Override
    protected void doAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer id = RequestUtil.getInt(req, "id");
        try {
            req.setAttribute("team", teamService.findById(id));
        } catch (ExamException ex) {
            addError(req, ex.getMessage());
        }
        WebUtil.forward(req, resp, "/team/view.jsp");
    }
}
