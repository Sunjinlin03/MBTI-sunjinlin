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

@WebServlet("/team/update.action6")
public class UpdateServlet extends BaseServlet {
    private ITeamService classTeamService = ServiceFactory.getService(ITeamService.class);

    @Override
    protected void doAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 将表单数据组装为Team对象
        Team t = new Team();
        t.setId(RequestUtil.getInt(req, "id"));
        t.setName(RequestUtil.getString(req, "name"));
        t.setBeginYear(RequestUtil.getDate(req, "beginYear"));
        t.setStatus(RequestUtil.getInt(req, "status"));
        try {
            classTeamService.updateTeam(t);
            WebUtil.redirect(req, resp, "/team/view.action?id=" + t.getId());
        } catch (ExamException ex) {
            addError(req, ex.getMessage());
            req.setAttribute("team", t);
            WebUtil.forward(req, resp, "/team/edit.jsp");
        }
    }
}
