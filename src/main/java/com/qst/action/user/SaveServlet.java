package com.qst.action.user;

import com.qst.BaseServlet;
import com.qst.ExamException;
import com.qst.RequestUtil;
import com.qst.WebUtil;
import com.qst.entity.User;
import com.qst.service.IUserAdminService;
import com.qst.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

//@WebServlet("/user/save.action")
public class SaveServlet extends BaseServlet {
    private IUserAdminService userAdminService = ServiceFactory.getService(IUserAdminService.class);

    @Override
    protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        request.getParameter("");
        user.setId(RequestUtil.getInt(request, "id"));
        user.setLogin(RequestUtil.getString(request, "login"));
        user.setPasswd(RequestUtil.getString(request, "passwd"));
        user.setName(RequestUtil.getString(request, "name"));
        user.setType(RequestUtil.getInt(request, "type"));
        user.setStatus(RequestUtil.getInt(request, "status"));

        try {
            userAdminService.saveUser(user);
            addMessage(request, "用户信息已保存");
            WebUtil.redirect(request, response, "/user/view.action?id=" + user.getId());
        } catch (ExamException ex) {
            request.setAttribute("user", user);
            addError(request, ex.getMessage());
            WebUtil.forward(request, response, "/user/create.jsp");
        }


    }
}
