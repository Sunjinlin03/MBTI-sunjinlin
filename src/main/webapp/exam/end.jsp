<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/res/styles.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/res/jquery.min.js"></script>
<style type="text/css">
	.div1{
		
		width: 45%;
		float: left;
	}
	.div2{
		
		width: 54%;
		float: left;
	}
</style>
</head>
<body>
		<div class="div1">
		<h2 style="margin-left: 50px">你的职业性格测试结果为:${score}</h2><br>
			<img alt="性格图片" src="${pageContext.request.contextPath }/img/${score}.jpg">
	</div>
	<div class="div2">
		<%String path=(String)request.getAttribute("examresult"); %>
			<jsp:include page="<%=path %>"></jsp:include>
	</div>
</body>
</html>

