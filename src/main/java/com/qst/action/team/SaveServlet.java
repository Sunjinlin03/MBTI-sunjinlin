package com.qst.action.team;

import com.qst.BaseServlet;
import com.qst.ExamException;
import com.qst.RequestUtil;
import com.qst.WebUtil;
import com.qst.entity.Team;
import com.qst.service.ITeamService;
import com.qst.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/team/save.action6")
public class SaveServlet extends BaseServlet {
    private ITeamService classTeamService = ServiceFactory.getService(ITeamService.class);

    @Override
    protected void doAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Team t = new Team();
        t.setName(RequestUtil.getString(req, "name"));
        t.setBeginYear(RequestUtil.getDate(req, "beginYear"));
        t.setStatus(RequestUtil.getInt(req, "status"));
        t.setCreatorId(getCurrentUser(req).getId());// 设置创建者
        try {
            classTeamService.saveTeam(t);
            WebUtil.redirect(req, resp, "/team/view.action?id=" + t.getId());
        } catch (ExamException ex) {
            addError(req, ex.getMessage());
            req.setAttribute("team", t);
            WebUtil.forward(req, resp, "/team/create.jsp");
        }
    }
}
