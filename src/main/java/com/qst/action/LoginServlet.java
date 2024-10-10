package com.qst.action;

import com.qst.*;
import com.qst.entity.TestPersonnel;
import com.qst.entity.User;
import com.qst.service.ITestPersonnelService;
import com.qst.service.ITestPersonnelSsmService;
import com.qst.service.IUserService;
import com.qst.service.ServiceFactory;
import com.qst.service.impl.TestPersonnelSsmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//web结构的四层?
//servlet用来做网页跳转，端到服务器，从一个servlet跳转到另一个servlet
//接收前端发送的请求
//@WebServlet("/login.action")
public class LoginServlet extends BaseServlet {
    private IUserService userService = ServiceFactory.getService(IUserService.class);
    private ITestPersonnelService testPersonnelService = ServiceFactory.getService(ITestPersonnelService.class);




    //doAction表示
    @Override
    protected void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = RequestUtil.getString(req, "login");
        String password = RequestUtil.getString(req, "password");

        try {int menu = RequestUtil.getInt(req, "menu");
            User user = userService.login(login, password);
            req.getSession().setAttribute(Constant.CURRENT_USER, user);
            if (user.getType() == 4) {
//                TestPersonnel tpn = testPersonnelService.findById(user.getId());
                TestPersonnel tpn = testPersonnelService.findById(user.getId());
                //赋值一个set值，值为当前用户
                req.getSession().setAttribute(Constant.CURRENT_TESTPERSONNEL, tpn);
            }
            if (menu == 0) {
                //menu来自login.jsp里的hidden字段
                //为0时，菜单为静态代码，非权限管理，为测试人员初级阶段任务
                WebUtil.redirect(req, resp, "/frame.jsp");
            } else {
                //菜单为数据库读取，受权限控制，作为高级任务实现
                WebUtil.redirect(req, resp, "/index.jsp");
            }

        } catch (ExamException ex) {
            System.out.println(ex.getMessage());
            addError(req, ex.getMessage());

            req.setAttribute("login", login);
            WebUtil.forward(req, resp, "/login.jsp");
        }
    }
}
