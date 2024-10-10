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
		<a href="${pageContext.request.contextPath }/schedule/list.action" class="button"><button class="btn btn-warning" style="margin-bottom: 10px">返回列表</button></a>
		<%@ include file="/inc/msg.jsp"%>
		<table class="table table-bordered table-hover table-striped" style="width: 90%;">


			<tr>
				<td class="head">批次名称</td>
				<td>${schedule.team.name }</td>
			</tr>
			<tr>
				<td class="head">科目名称</td>
				<td>${schedule.assessmentType.title }</td>
			</tr>
			<tr>
				<td class="head">开始日期</td>
				<td>${schedule.beginDate }</td>
			</tr>
			<tr>
				<td class="head">结束日期</td>
				<td>${schedule.endDate }</td>
			</tr>
			<tr>
				<td class="head">考试时长</td>
				<td>${schedule.duration }</td>
			</tr>
			<tr>
			<td class="head">试题数量</td>
			<td>${schedule.questionNumber }</td>
			</tr>
			<tr>
			<td class="head">创建时间</td>
			<td>${schedule.createDate }</td>
			</tr>
			
			<tr>
				<td class="head">状态</td>
				<td>${schedule.statusDesc }</td>
			</tr>

			<c:if test="${param.delete eq 1 }">
				<tr>
					<td colspan="2" style="text-align: center;"><a
						href="${pageContext.request.contextPath }/schedule/delete.action?id=${schedule.id }" class="button">确认删除</a></td>
				</tr>
			</c:if>
		</table>
	</div>
</body>
</html>