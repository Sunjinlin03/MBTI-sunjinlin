<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath }/css/commonCss.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/table.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/res/styles.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/input.css" />
<title></title>
</head>
<body>
	<div style="padding: 10px;">
        <a href="${pageContext.request.contextPath }/user/list.action" class="button"><button class="btn btn-warning" style="margin-bottom: 10px">返回列表</button></a>
         <%@ include file="/inc/msg.jsp"%>
		<form action="${pageContext.request.contextPath }/user/save.action" method="post" class="table"
			style="width: 80%;">
			<table class="table table-bordered table-hover table-striped">
				<tr>
					<td class="head">登录名称</td>
					<td><input type="text" name="login"></td>
				</tr>
				<tr>
					<td class="head">密码</td>
					<td><input type="password" name="passwd"></td>
				</tr>
				<tr>
					<td class="head">真实姓名</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td class="head">用户类型</td>
					<td><input type="radio" name="type" value="1"checked }>用户管理员
						<input type="radio" name="type" value="2">题库管理员 <input
						type="radio" name="type" value="3">题库使用者</td>
				</tr>
				<tr>
					<td class="head">用户状态</td>
					<td><input type="radio" name="status" value="0">禁用
						<input type="radio" name="status" value="1" checked>正常</td>
				</tr>
				<tr>
					<td colspan="2" class="edit"><input class="btn btn-warning" type="submit" value="保存" /><input class="btn btn-warning" type="reset" value="重置" /></td>
				</tr>
			</table>
		</form>

	</div>
</body>
</html>