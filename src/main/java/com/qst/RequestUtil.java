package com.qst;

import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

	public static String getString(HttpServletRequest req, String name) {
		String str = req.getParameter(name);
		str = isBlank(str) ? "" : str.trim();//trim()删去头部或尾部的空格
		return str;
	}

	public static int getInt(HttpServletRequest req, String name) {
		try {
			return Integer.parseInt(RequestUtil.getString(req, name));
		} catch (Exception ex) {
			return 0;
		}
	}

	public static int[] getIntArray(HttpServletRequest req, String name) {
		String[] strs = req.getParameterValues(name);
		if (strs == null) {
			return null;
		}
		int[] array = new int[strs.length];
		for (int i = 0; i < strs.length; i++) {
			array[i] = Integer.parseInt(strs[i]);
		}
		return array;
	}

	public static double getDouble(HttpServletRequest req, String name) {
		try {
			return Double.parseDouble(RequestUtil.getString(req, name));
		} catch (Exception ex) {
			return 0.0;
		}
	}

	public static Date getDate(HttpServletRequest req, String name) {
		try {
			return Date.valueOf(RequestUtil.getString(req, name));
		} catch (Exception ex) {
			return null;
		}
	}

	public static Timestamp getTimestamp(HttpServletRequest req, String name) {
		try {
			return Timestamp.valueOf(RequestUtil.getString(req, name));
		} catch (Exception ex) {
			return null;
		}
	}

	private static boolean isBlank(String str) {
		return str == null || str.trim().length() == 0;
	}
}
