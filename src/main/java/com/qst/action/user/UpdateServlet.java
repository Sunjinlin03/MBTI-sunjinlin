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


//该类为判断是否可以更改成功的逻辑和跳转至用户详细信息页面
//@WebServlet("/user/update.action")
public class UpdateServlet extends BaseServlet {
//   注入依赖
    private IUserAdminService userAdminService= ServiceFactory.getService(IUserAdminService.class);
    @Override
    protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user=new User();
        user.setId(RequestUtil.getInt(request,"id"));
        user.setLogin(RequestUtil.getString(request,"login"));
        user.setName(RequestUtil.getString(request,"name"));
        user.setType(RequestUtil.getInt(request,"type"));
        user.setStatus(RequestUtil.getInt(request,"status"));
        try {
            userAdminService.updateUser(user);
            addMessage(request,"用户信息已更改到数据库");
            WebUtil.redirect(request,response,"/user/view.action?id="+user.getId());
        }catch (ExamException ex){
            request.setAttribute("user",user);
            addError(request,ex.getMessage());
            WebUtil.forward(request,response,"/user/edit.jsp");
        }


    }
}
