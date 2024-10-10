package com.qst.util;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SuperJdbcDao {

	private static String url = null;
	private static String username = null;
	private static String pwd = null;

	static {
		url = "jdbc:mysql://localhost:3306/mbti?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true";
		username = "root";
		pwd = "123456";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取数据库链接对象的方法，
	 * 
	 * @return Connection 对象 或者 null（异常）
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, username, pwd);
		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * 关闭相关jdbc资源的方法。
	 * 
	 * @param rst
	 *            需要关闭的 结果集操作装置
	 * @param st
	 *            需要关闭的 指令操作装置
	 * @param con
	 *            需要关闭的 数据库链接
	 */
	public static void closeJDBC(ResultSet rst, Statement st, Connection con) {

		if (rst != null) {

			try {
				rst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				st = null;
			}
		}

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				con = null;
			}
		}

	}

	/**
	 * 
	 * @param table
	 * @param rowCount
	 * @return
	 */
	public static int getPageCountByRowCount(String table, int rowCount) {

		Connection c = getConnection();

		if (c == null) {
			return -1;
		}

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = c.prepareStatement("select count(*) from " + table);
			rs = ps.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				if (count > 0) {
					if (count % rowCount == 0) {
						return count / rowCount;
					}
					return ((count / rowCount) + 1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeJDBC(rs, ps, c);
		}
		return -1;
	}

	/**
	 * 用于子类继承或直接使用的 查询方法。
	 * 
	 * @param cl指定的实体类
	 *            类文件
	 * @param sql
	 *            指定 查询语句
	 * @param args
	 *            sql语句的参数（可选）<br>
	 * @return 封装有 相关查询结果的list
	 * @实例 String sql = "select * from User where id=? and name=?"; <br>
	 *     exe_Query(User.class, sql, id, name); <br>
	 *     exe_Query(User.class, sql)
	 */
	public static List exe_Query(Class cl, String sql, Object... args) {

		// 获取数据库连接
		Connection con = getConnection();
		if (con == null) {
			return null;
		}
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// 获取指令操作装置
			ps = con.prepareStatement(sql);

			// 如果有代位符"?" 则进行赋值操作
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}

			// 执行操作 获取 结果集（操作装置）
			rs = ps.executeQuery();
			// 通过 参数 获取 指定类的 相关自定义 方法
			// 内省 Introspection
			Method[] mtds = cl.getMethods();
			// 筛选方法，集中管理 “set”方法
			Map<String, Method> mts = new HashMap<String, Method>();
			for (int i = 0; i < mtds.length; i++) {
				String nm = mtds[i].getName();
				if (nm.indexOf("set") == 0) {
					mts.put(nm.substring(3).toLowerCase(), mtds[i]);
				}
			}
			// 获取到 rs 所操作的 表 的 原始信息集合
			ResultSetMetaData rmd = rs.getMetaData();
			// 表 的 字段（列）个数
			int columnCount = rmd.getColumnCount();
			System.out.println("columnCount:" + columnCount);
			List list = new ArrayList();
			while (rs.next()) {

				Object entity = null;
				try {
					// 根据 形参 实例化一个对象（空参数）
					entity = cl.newInstance();
					// System.out.println("*************************");
					// 遍历 一行 每一个字段（列）
					for (int i = 0; i < columnCount; i++) {
						// System.out.println((i+1));
						// 得到列名
						String cn = rmd.getColumnName(i + 1).toLowerCase();
						// System.out.println((i+1)+":"+cn);
						// 通过列名 获取 set方法
						Method mt = mts.get(cn);
						// 由 对象 entity 执行 set 方法，为属性赋值
						// System.out.println(rs.getObject(i+1).getClass()+":"+rs.getObject(i+1));
						if (mt != null) {
							mt.invoke(entity, rs.getObject(i + 1));
						} else {
							throw new Exception("未找到属性为:" + cn + "的set方法");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 将 封装有 属性值得 对象（entity）存入 集合中
				list.add(entity);
			}
			// 返回集合给方法调用者
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭相关数据库资源
			closeJDBC(rs, ps, con);
		}
		return null;

	}

/*	// 实例
	private static void exe_QueryTest() {
		String sql1 = "select * from User where id=?";
		int id = 1;
		// 一个参数
		List<User> list = exe_Query(User.class, sql1, id);

		String sql2 = "select * from User where id=? and name=?";
		id = 1;
		String name = "tom";
		// 多个参数
		List<User> list1 = exe_Query(User.class, sql2, id, name);

	}*/

	/**
	 * 
	 * @param cl
	 *            实体类
	 * @param sql
	 *            如果带参数select * from user where id = ?
	 * @param args
	 *            几个问号，这里就传入几个值
	 * @return
	 */
	public static Object exe_QueryOne(Class cl, String sql, Object... args) {

		List al = exe_Query(cl, sql, args);
		return al != null && al.size() > 0 ? al.get(0) : null;
	}

	/**
	 * 用于子类继承后 直接使用的 数据更新（增删改）方法
	 * 
	 * @param sql指定的sql语句
	 *            （可带占位符‘？’）
	 * @param args
	 *            任意个 object 对象（为 '?' 赋值）
	 * @return 操作结果 true or false
	 */
	public static boolean exe_Update(String sql, Object... args) {
		// 获取连接对象，并判断是否可用
		Connection con = getConnection();
		if (con == null) {
			return false;
		}
		PreparedStatement ps = null;
		try {
			// 通过链接对象获取 预处理 ps 装置
			// 为 ps 准备 sql 语句
			ps = con.prepareStatement(sql);
			// 如果有占位符‘？’，则根据 数组需要（位置）赋值
			System.out.println("args.length" + args.length);
			if (args != null && args.length > 0) {
				for (int i = 0; i < args.length; i++) {
					System.out.println("args[i]" + args[i]);
					ps.setObject(i + 1, args[i]);
				}
			}
			// 执行操作返回 结果
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭相关 jdbc 资源
			closeJDBC(null, ps, con);
		}
		return false;
	}

	/**
	 * 这个方法不太实用
	 * 
	 * @param type
	 * @param table
	 * @param properties
	 *            可变参数<br>
	 *            DB_Property dp = new DB_Property();
	 * @return
	 */
	public static List getInfoByProperties(Class type, String table,
			DB_Property... properties) {

		String sql = "select *from " + table + " where 1=1 ";
		Object[] args = new Object[properties.length];
		for (int i = 0; i < properties.length; i++) {
			DB_Property p = properties[i];
			sql = sql + p.getAon() + " " + p.getCol() + " " + p.getEq() + " ?";
			args[i] = p.getValue();
		}
		return exe_Query(type, sql, args);
	}

	private static void getInfoByPropertiesTest() {
		DB_Property dp = new DB_Property();
		dp.setAon("and");
		dp.setCol("id");
		dp.setEq("=");
		dp.setValue("1");
	}

	/**
	 * 用于子类继承或直接使用 的 分页方法
	 * 
	 * @param type
	 *            返回集合中的实体类类型
	 * @param table
	 *            指定 要分页的表名称
	 * @param pageNum
	 *            指定要查看的页码
	 * @param rowCount
	 *            每页行数
	 * @return 封装有查询结果的list 对象。
	 */
	public static List findInfoByPageNumAndRowcount(Class type, String table,
			int pageNum, int rowCount) {

		String sql = "select *from " + table + "" + " limit "
				+ ((pageNum - 1) * rowCount) + " ," + rowCount;

		return exe_Query(type, sql);

	}

/*	public static void main(String[] args) {
		exe_QueryTest();
	}*/
}
