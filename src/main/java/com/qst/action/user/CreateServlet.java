package com.qst.action.user;

import com.qst.BaseServlet;
import com.qst.WebUtil;
import com.qst.pojo.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

//@WebServlet("/user/create.action")
public class CreateServlet extends BaseServlet {

    @Override
    protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user=new User();
        user.setStatus(1);
        user.setType(1);
        request.setAttribute("user",user);
        WebUtil.forward(request,response,"/user/create.jsp");
    }
}
