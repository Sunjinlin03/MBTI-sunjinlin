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

@WebServlet("/team/delete.action6")
public class DeleteServlet extends BaseServlet {
    private ITeamService classTeamService = ServiceFactory.getService(ITeamService.class);

    @Override
    protected void doAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = RequestUtil.getInt(req, "id");
        try {
            classTeamService.deleteTeam(id);
            addMessage(req, "批次已删除");
        } catch (ExamException ex) {
            addError(req, ex.getMessage());
        }
        WebUtil.redirect(req, resp, "/team/list.action");
    }
}
