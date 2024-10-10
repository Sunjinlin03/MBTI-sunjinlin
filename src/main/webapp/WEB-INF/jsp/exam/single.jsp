
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<tr>
	<td class="header"><b>题目内容：</b></td>
	<td class="coontent">${q.question.title }</td>
</tr>
   <%int index=0; %>
<c:forEach var="c" items="${q.question.choices }" varStatus="s">
	<tr>
	<c:if test="${not empty c.title}">
		<td class="header">选项<%=index+1 %>：</td>
		<td class="content"><input type="radio" name="answer" 
		value="${c.id }"  ${c.extras['checked']}>
			${c.title }</td>
			<%index++; %>
			</c:if>
	</tr>
</c:forEach>


</table>