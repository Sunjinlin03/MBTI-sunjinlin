package com.qst.action.user;

import com.qst.*;
import com.qst.dao.DAOFactory;
import com.qst.dao.UserDao;
import com.qst.entity.TestPersonnel;
import com.qst.entity.User;
import com.qst.service.ITestPersonnelService;
import com.qst.service.IUserAdminService;
import com.qst.service.IUserService;
import com.qst.service.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

//@WebServlet("/user/reg.do")
public class RegServlet extends BaseServlet {
    private IUserAdminService userAdminService= ServiceFactory.getService(IUserAdminService.class);
    private ITestPersonnelService testPersonnelService=ServiceFactory.getService(ITestPersonnelService.class);
    private UserDao dao= DAOFactory.getDao(UserDao.class);

    @Override
    protected void doAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = new User();
        req.getParameter("");
        user.setId(RequestUtil.getInt(req, "id"));
        user.setLogin(RequestUtil.getString(req, "login"));
        user.setPasswd(RequestUtil.getString(req, "passwd"));
        user.setName(RequestUtil.getString(req, "name"));
        user.setType(RequestUtil.getInt(req, "type"));
        user.setStatus(RequestUtil.getInt(req, "status"));
        TestPersonnel s = new TestPersonnel();
        s.setUser(user);
        s.setGender(RequestUtil.getString(req, "gender"));
        s.setPhone(RequestUtil.getString(req, "login"));
        s.setTeamId(RequestUtil.getInt(req, "teamId"));
        try {
            if(dao.findByLogin(s.getPhone())!=null){
                addMessage(req, "该手机号已被注册！");
                WebUtil.redirect(req,resp,"/user/showReg.do");
            }else{
                testPersonnelService.addTestPersonnel(s);
                addMessage(req, "用户信息已保存");
                req.getSession().setAttribute(Constant.CURRENT_USER, user);
                req.getSession().setAttribute(Constant.CURRENT_TESTPERSONNEL, s);
                WebUtil.redirect(req, resp, "/login.jsp");}
        } catch (ExamException ex) {
            req.setAttribute("user", user);
            addError(req, ex.getMessage());
            // req.setAttribute("ERROR_KEY", ex.getMessage());
            WebUtil.forward(req, resp, "/user/create.jsp");
        }
    }
}
