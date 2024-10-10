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
	<div class="from1" style="padding: 10px;">
		<a href="${pageContext.request.contextPath }/user/list.action" class="button"><button class="btn btn-warning" style="margin-bottom: 10px">返回列表</button></a>
		<%@ include file="/inc/msg.jsp"%>
		<form action="${pageContext.request.contextPath }/user/update.action" method="post" class="table"
			style="width: 80%;">
			<input type="hidden" name="id" value="${user.id }">
			<table class="table table-bordered table-hover table-striped">
				<tr>
					<td class="head">登录名称</td>
					<td><input type="text" name="login" value="${user.login }"></td>
				</tr>
				<tr>
					<td class="head">真实姓名</td>
					<td><input type="text" name="name" value="${user.name }"></td>
				</tr>
				<tr>
					<td class="head">用户类型</td>
					<td><input type="radio" name="type" value="1"
						${1 eq user.type?'checked':'' }>用户管理员 <input type="radio"
						name="type" value="2" ${2 eq user.type?'checked':'' }>题库管理员
						<input type="radio" name="type" value="3"
						${3 eq user.type?'checked':'' }>题库使用者</td>
				</tr>
				<tr>
					<td class="head">用户状态</td>
					<td><input type="radio" name="status" value="0"
						${0 eq user.status?'checked':'' }>禁用 <input type="radio"
						name="status" value="1" ${1 eq user.status?'checked':'' }>正常
					</td>
				</tr>
				<tr >
					<td colspan="2" class="edit"><input type="submit" class="btn btn-warning" value="保存"/><input class="btn btn-warning" type="reset" value="重置" /></td>
				</tr>
			</table>
		</form>

	</div>
</body>
</html>