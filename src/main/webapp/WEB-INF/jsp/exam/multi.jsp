<%@page import="com.qst.entity.Choice"%>
<%@page import="com.qst.entity.ExamQuestion"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<table class="exam-content">
	<tr>
		<td class="header" colspan="2">复选题,(本题分数：${q.score })</td>
	</tr>
	<tr>
		<td class="header"><b>题目内容：</b></td>
		<td class="coontent">${q.question.title }</td>
	</tr>
	<c:forEach var="c" items="${q.question.choices }" varStatus="s">
		<tr>
			<td class="header">选项${s.index+1 }：</td>
			<td class="content"><input type="checkbox" name="answer" value="${c.id }"
				${c.extras['checked']}> ${c.title }</td>
		</tr>
	</c:forEach>

</table>