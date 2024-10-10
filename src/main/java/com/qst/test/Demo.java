package com.qst.test;

import com.qst.util.SuperJdbcDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

/**
 * Servlet implementation class Demo
 */

//接收请求
@WebServlet("/Demo")
public class Demo extends HttpServlet {
    private static final long serialVersionUID=1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        //输出一个字节
        //PrintWriter属于I/O，java的I/O分为输入输出，字符流和字节流4种
        PrintWriter out=resp.getWriter();
        Connection conn= SuperJdbcDao.getConnection();
        out.println("conn:"+conn);
    }
}
