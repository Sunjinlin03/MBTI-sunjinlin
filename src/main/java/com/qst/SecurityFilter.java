package com.qst;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qst.pojo.User;

@WebFilter("*.action")
public class SecurityFilter extends BaseFilter {

	private Map<String, Integer> paths = new HashMap<>();

	public SecurityFilter() {
		// 1 2 3 4 分别是数据库user表里的type对应的字段
		paths.put("/user", 1);
		paths.put("/schedule", 2);
		paths.put("/question", 2);
		paths.put("/dimension", 2);

		paths.put("/team", 3);
		paths.put("/testPersonnel", 3);
		paths.put("/schedule", 3);

		paths.put("/exam", 4);
	}

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		String contextPath = req.getServletContext().getContextPath();// /exam
		String uri = req.getRequestURI();// /exam/logout.action
											// /exam/user/list.action
		uri = uri.substring(contextPath.length());// /logout.action，/user/list.action

		// 从索引为1的位置开始，查找"/"的索引，即排除第一个“/”，找到第2个“/”所在的位置
		// 功能模块都带有自己功能模块的标记：即/user /assessment
		int idx = uri.indexOf("/", 1);
		if (idx == -1) {
			chain.doFilter(req, resp);
			return;
		}
		String prefix = uri.substring(0, idx);// 取出前缀/user 或者 /assessment
		User currentUser = (User) req.getSession().getAttribute(
				Constant.CURRENT_USER);
		if (currentUser == null) {
			WebUtil.redirect(req, resp, "/login.jsp");
			return;
		} else if (!currentUser.getLogin().equals("admin")) {

			if (paths.containsKey(prefix)) {// 如果paths的key包含/user
				if (paths.get(prefix) != currentUser.getType()) {
					WebUtil.redirect(req, resp, "/deny.jsp");
					return;
				}
			}
		}

		chain.doFilter(req, resp);
	}
}
