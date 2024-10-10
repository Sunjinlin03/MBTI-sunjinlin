package com.qst;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//url是一个静态（常量（不需要修改的））的变量
//静态变量如何调用：通过类调用

public class Db {
	private static String url = "jdbc:mysql://localhost:3306/mbti?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true";
	private static String userName = "root";
	private static String password = "root";

	//静态代码块（此块在类加载时就加载）
	static {
		
			try {
				com.mysql.cj.jdbc.Driver.class.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}

	public static Connection getConnection() throws SQLException {

		Connection conn = DriverManager.getConnection(url, userName, password);
		conn.setAutoCommit(true);// 开启自动提交模式
		return conn;
	}

	public static int getGeneratedInt(Statement stmt) throws SQLException {
		ResultSet rs = stmt.getGeneratedKeys();
		rs.next();
		return rs.getInt(1);

	}

}
