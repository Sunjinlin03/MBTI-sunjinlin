package com.qst.action.user;

import com.qst.BaseServlet;
import com.qst.RequestUtil;
import com.qst.WebUtil;
import com.qst.service.IUserAdminService;
import com.qst.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

//@WebServlet("/user/delete.action")
public class DeleteServlet extends BaseServlet {
    IUserAdminService userAdminService= ServiceFactory.getService(IUserAdminService.class);

    @Override
    protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取前台传递的id
        int id= RequestUtil.getInt(request,"id");

        //删除id对应的用户信息
        userAdminService.deleteUserById(id);
        //重定向跳转返回列表页面(删除后更新数据)
        WebUtil.redirect(request,response,"/user/list.action");
    }
}
