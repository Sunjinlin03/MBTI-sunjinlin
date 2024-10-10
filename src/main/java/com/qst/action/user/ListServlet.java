package com.qst.action.user;

import com.qst.BaseServlet;
import com.qst.WebUtil;
import com.qst.entity.User;
import com.qst.service.IUserAdminService;
import com.qst.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

//@WebServlet("/user/list.action")
public class ListServlet extends BaseServlet {
    //注入依赖
    private IUserAdminService userAdminService= ServiceFactory.getService(IUserAdminService.class);


    @Override
    protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList=userAdminService.findUsers();
        request.setAttribute("userList",userList);
        //跳转到用户列表页面
        WebUtil.forward(request,response,"/user/list.jsp");

 }

}
