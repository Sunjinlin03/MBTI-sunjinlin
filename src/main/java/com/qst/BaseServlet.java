package com.qst;

import com.qst.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class BaseServlet extends HttpServlet {
    public static final String MSG_KEY = "_message_key";
    public static final String ERROR_KEY = "_error_key";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        doAction(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doAction(req, resp);
    }

    //doAction可以实现doget和dopost方法(doGet和doPost都会调用doAction方法实现统一)
    protected void doAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    }

    protected void addMessage(HttpServletRequest req, String msg) {
        req.getSession().setAttribute(MSG_KEY, msg);
    }

    protected void addError(HttpServletRequest req, String msg) {
        req.getSession().setAttribute(ERROR_KEY, msg);
    }

    public User getCurrentUser(HttpServletRequest req) {

        return (User) req.getSession().getAttribute(Constant.CURRENT_USER);
    }

}
