package com.qst;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {
	public static void forward(HttpServletRequest req,
			HttpServletResponse resp, String url) throws ServletException,
			IOException {
		url = resp.encodeURL(url);
		req.getRequestDispatcher(url).forward(req, resp);
	}

	public static void redirect(HttpServletRequest req,
			HttpServletResponse resp, String url) throws IOException {
		if (url.startsWith("/")) {
			url = req.getServletContext().getContextPath() + url;
		}
		url = resp.encodeRedirectURL(url);
		resp.sendRedirect(url);
		// 如果是服务器处理 / 就代表项目根，如果是浏览器处理，就是服务器根路径
	}

	public static void redirect(HttpServletResponse resp, String url)
			throws IOException {
		url = resp.encodeRedirectURL(url);
		resp.sendRedirect(url);
	}

}
