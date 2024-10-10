package com.qst.action.user;

import com.qst.BaseServlet;
import com.qst.Constant;
import com.qst.RequestUtil;
import com.qst.WebUtil;
import com.qst.pojo.User;
import com.qst.service.IUserAdminService;
import com.qst.service.IUserService;
import com.qst.service.ServiceFactory;
import com.qst.service.impl.UserAdminServiceImpl;
import com.qst.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

//@WebServlet("/password.action")
public class PasswordServlet extends BaseServlet {
    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebUtil.forward(req,resp,"/password.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute(Constant.CURRENT_USER);
        String oldPassword=RequestUtil.getString(req,"oldPassword");
        String newPassword=RequestUtil.getString(req,"newPassword");
        String confirm=RequestUtil.getString(req,"comfirm");
        if (!confirm.equals(newPassword)){
            addError(req,"两次密码输入不一致");
            WebUtil.forward(req,resp,"/password.jsp");
        }else {
            try {
                userService.changePassword(currentUser.getId(),oldPassword,newPassword);
                addMessage(req,"登录密码已更改，请退出重新登录");
                WebUtil.redirect(req,resp,"/passwordmsg.jsp");
            }catch (Exception ex){
                addError(req,ex.getMessage());
                WebUtil.forward(req,resp,"/password.jsp");
            }
        }
    }
}
