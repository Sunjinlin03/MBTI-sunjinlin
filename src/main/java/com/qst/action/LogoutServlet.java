package com.qst.action;

import com.qst.BaseServlet;
import com.qst.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//用于完成用户的退出
//@WebServlet("/logout.action")
public class LogoutServlet extends BaseServlet {
    @Override
    protected void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //使session失效
        req.getSession().invalidate();
        //跳转到登录页面
        WebUtil.redirect(req,resp,"/login.jsp");
    }
}
