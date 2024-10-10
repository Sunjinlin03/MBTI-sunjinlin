package com.qst.controller;

import com.qst.BaseServlet;
import com.qst.Constant;
import com.qst.RequestUtil;
import com.qst.WebUtil;
import com.qst.pojo.TestPersonnel;
import com.qst.pojo.User;
import com.qst.service.ITestPersonnelSsmService;
import com.qst.service.IUserSsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    private IUserSsmService userSsmService;
    @Autowired
    private ITestPersonnelSsmService testPersonnelSsmService;

    @RequestMapping("login.action")
    public String login(HttpServletRequest req,
                        @RequestParam("login") String login,
                        @RequestParam("password") String password,
                        @RequestParam("menu") int menu) {
        User user = userSsmService.login(login, password);
        req.getSession().setAttribute(Constant.CURRENT_USER, user);
        if (user.getType() == 4) {
            TestPersonnel tpn = testPersonnelSsmService.findById(user.getId());
            //赋值一个set值，值为当前用户
            req.getSession().setAttribute(Constant.CURRENT_TESTPERSONNEL, tpn);
        }
        if (menu == 0) {
            return "/frame";
        } else {
            return "/index";
        }

    }

    @RequestMapping("logout.action")
    public String logout(HttpSession session) {
        session.removeAttribute(Constant.CURRENT_USER);
        return "login";
    }

    @RequestMapping("passwd.action")
    public String password() {
        return "password";
    }

    @RequestMapping("password.action")
    public String updatePassword(HttpSession session,
                                 @RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("comfirm") String confirm) {
        User currentUser = (User) session.getAttribute(Constant.CURRENT_USER);
        if (!confirm.equals(newPassword)){
            session.setAttribute(BaseServlet.ERROR_KEY,"两次密码输入不一致");
            return "password";
        }else {
                userSsmService.changePassword(currentUser.getId(),oldPassword,newPassword);
                session.setAttribute(BaseServlet.MSG_KEY,"登录密码已更改，请退出重新登录");
                return "passwordmsg";
        }

    }


}
