<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/res/styles.css" />
</head>
<body>
	<div style="padding: 10px;">
		<a href="${pageContext.request.contextPath }/schedule/create.action" class="button"><button style="margin-bottom: 10px;" class="btn btn-warning">添加测试安排</button></a>
		<%@ include file="/inc/msg.jsp"%>
		<table class="grid" style="width: 90%;">
			<tr>
				<td class="head">序号</td>
				<td class="head">学号</td>
				<td class="head">姓名</td>
				<td class="head">考试时间</td>
				<td class="head">交卷时间</td>
				<td class="head">分数</td>

			</tr>
			<c:forEach var="u" varStatus="vs" items="${testPersonnel }">
				<c:set var="m" value="${u.extras['exam'] }" />
				<tr class="body">
					<td>${vs.index+1 }</td>
					<td>${u.phone }</td>
					<td>${u.user.name }</td>
					<td>${m.beginTime }</td>
					<td>${m.endTime }</td>
					<td><c:if test="${empty m }">缺考</c:if> <c:if test="${not empty m }"> ${m.score }</c:if></td>


				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>