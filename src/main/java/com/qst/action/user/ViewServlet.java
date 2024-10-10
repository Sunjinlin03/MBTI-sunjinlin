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

//@WebServlet("/user/view.action")
public class ViewServlet extends BaseServlet {
    private IUserAdminService userAdminService= ServiceFactory.getService(IUserAdminService.class);

    @Override
    protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //拿到前端传来的参数id
        int id= RequestUtil.getInt(request,"id");
        //通过id获取用户详细
        User user=userAdminService.findUserById(id);
        request.setAttribute("user",user);
        //设置页面参数，跳转至相应页面
        WebUtil.forward(request,response,"/user/view.jsp");
    }
}
