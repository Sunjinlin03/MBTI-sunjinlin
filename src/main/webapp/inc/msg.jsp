<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty _error_key }">
	<div class="error">${_error_key }</div>
	<c:remove var="_error_key" scope="session" />
</c:if>
<c:if test="${not empty _message_key }">
	<div class="message">${_message_key }</div>
	<c:remove var="_message_key" scope="session" />
</c:if>