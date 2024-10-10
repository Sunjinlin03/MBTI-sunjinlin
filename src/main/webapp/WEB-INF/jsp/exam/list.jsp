<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

		<%@ include file="/inc/msg.jsp"%>
		<table class="table table-bordered table-hover table-striped">
			<tr>
				<th>序号</th>
				<th>考核类型</th>
				<th>开始日期</th>
				<th>结束日期</th>
				<th>考试时长</th>
				<th>结果</th>
				<th>操作</th>
			</tr>
			<c:forEach var="u" varStatus="vs" items="${scheduleList }">
				<tr class="body">
					<td>${vs.index+1 }</td>
					<td>${u.assessmentType.title }</td>
					<td>${u.beginDate }</td>
					<td>${u.endDate }</td>
					<td>${u.duration }</td>
					<td>${empty u.extras['exam']?'未考':u.extras['exam'].result }</td>
					<td>
					<!-- 
					<c:if test="${empty u.extras['exam'] }">
							<a class="button" href="exam/begin.action?id=${u.id }">开始考试</a>
						</c:if></td>
						 -->
						 <c:if test="${not empty u.extras['exam'].result  }">
						 	<a class="button" href="${pageContext.request.contextPath }/exam/result.action?id=${u.extras['exam'].id }">查看结果</a>
						 </c:if>
						 <c:if test="${u.status eq 2 and empty u.extras['exam'] }">
							<a class="button" href="${pageContext.request.contextPath }/exam/begin.action?id=${u.id }">开始考试</a>
						</c:if></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>