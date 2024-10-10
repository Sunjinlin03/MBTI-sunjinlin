<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath }/css/commonCss.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/table.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/res/styles.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/input.css" />
</head>
<body>
	<div style="padding: 10px;">
		<a href="${pageContext.request.contextPath }/schedule/create.action" class="button"><button style="margin-bottom: 10px;" class="btn btn-warning">添加测试安排</button></a>
		<%@ include file="/inc/msg.jsp"%>
		<table class="table table-bordered table-hover table-striped">
			<tr>
				<th class="head">序号</th>
				<th class="head">考核批次</th>
				<th class="head">考核类型</th>
				<th class="head">开始日期</th>
				<th class="head">结束日期</th>
				<th class="head">考试时长</th>
				<th class="head">试题数量</th>
				<th class="head">创建时间</th>
				<th class="head">状态</th>			
				<th class="head">操作</th>
			</tr>
			<c:forEach var="u" varStatus="vs" items="${scheduleList }">
				<tr class="body">
					<td>${vs.index+1 }</td>
					<td>${u.team.name }</td>
					<td>${u.assessmentType.title }</td>
					<td>${u.beginDate }</td>
					<td>${u.endDate }</td>
					<td>${u.duration }</td>
					<td>${u.questionNumber }</td>
					<td>${u.createDate }</td>
					<td>${u.statusDesc }</td>
					<td><a class="button" href="${pageContext.request.contextPath }/schedule/view.action?id=${u.id }">查看</a> <a class="button"
						href="${pageContext.request.contextPath }/schedule/edit.action?id=${u.id }">修改</a> <a class="button"
						href="${pageContext.request.contextPath }/schedule/view.action?id=${u.id }&delete=1">删除</a> <c:if test="${u.status eq 3 }">
							<a class="button" href="${pageContext.request.contextPath }/schedule/score.action?id=${u.id }">考试成绩</a>
						</c:if></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>