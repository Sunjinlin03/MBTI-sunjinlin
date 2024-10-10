package com.qst.action.user;

import com.qst.BaseServlet;
import com.qst.RequestUtil;
import com.qst.WebUtil;
import com.qst.entity.User;
import com.qst.service.IUserAdminService;
import com.qst.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

//此类用于跳转到修改页面
//@WebServlet("/user/edit.action")
public class EditServlet extends BaseServlet {
    //注入依赖
    private IUserAdminService userAdminService= ServiceFactory.getService(IUserAdminService.class);

    @Override
    protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= RequestUtil.getInt(request,"id");
        User user=userAdminService.findUserById(id);
        request.setAttribute("user",user);
        WebUtil.forward(request,response,"/user/edit.jsp");
    }
}
