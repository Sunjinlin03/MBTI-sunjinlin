package com.qst.action.team;

import com.qst.BaseServlet;
import com.qst.ExamException;
import com.qst.WebUtil;
import com.qst.pojo.User;
import com.qst.service.ITeamService;
import com.qst.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/team/list.action6")
public class ListServlet extends BaseServlet {

    private ITeamService classTeamService = ServiceFactory.getService(ITeamService.class);

    @Override
    protected void doAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 获取当前登录的用户
        User user = getCurrentUser(req);
        System.out.println("获取到用户类型"+user.getType());
        try {
            if (user.getType() == 1) {
                req.setAttribute("teamList", classTeamService.findAll());
            } else {
                req.setAttribute("teamList",
                        classTeamService.findByCreator(user.getId()));
            }

        } catch (ExamException ex) {
            addError(req, ex.getMessage());
        }
        WebUtil.forward(req, resp, "/team/list.jsp");
    }
}
