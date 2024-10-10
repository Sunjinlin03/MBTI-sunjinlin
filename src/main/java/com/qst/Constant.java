package com.qst;

import java.util.Arrays;

public class Constant {
	public static final String CURRENT_USER = "current_user";//当前用户
	public static final String CURRENT_QUESTION = "current_question";
	public static final String CURRENT_TESTPERSONNEL = "current_TestPersonnel";
	public static final String CURRENT_EXAM = "current_exam";

	public static String join(String[] strs) {
		if (strs.length == 0) {
			return null;
		}
		String s = Arrays.toString(strs);
		return s.substring(1, s.length() - 1);

	}
}
