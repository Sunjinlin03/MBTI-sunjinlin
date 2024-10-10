<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath }/css/commonCss.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/res/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/table.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/res/styles.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/input.css" />
<style type="text/css">
	.div1{
		
		width:60%;
		margin-left: 20%;
		margin-top: 3%;
	}
</style>
</head>
<body>
<div class="div1">
<img alt="无法显示此图片" src="${pageContext.request.contextPath }//img/top01.png" width="100%" >
	<div style="padding: 10px;">
	
		<%@ include file="/inc/msg.jsp"%>		
		<c:set var="q" value="${current_exam.question}" scope="page" />
		<c:set var="questionIndex" value="${current_exam.questionIndex}" scope="page" />
		<c:if test="${not empty q }">
			<form id="exam-form" method="post" action="${pageContext.request.contextPath }/exam/exam.action">
				<input type="hidden" name="index" />
				第${questionIndex+1}题
				<table class="exam-content table table-bordered table-hover table-striped">
					<c:if test="${q.question.type eq 1 }">
						<%@ include file="single.jsp"%>
					</c:if>
					<c:if test="${q.question.type eq 2 }">
						<%@ include file="single.jsp"%>
					</c:if>
					<c:if test="${q.question.type eq 3 }">
						<%@ include file="single.jsp"%>
					</c:if>
					<c:if test="${q.question.type eq 4 }">
						<%@ include file="single.jsp"%>
					</c:if>
				</table>
				<div class="exam-panel">
                    <br/>
					<button class="btn btn-warning"  style="margin-bottom: 4px;margin-left: 400px;" idx='-1' >上一题</button>
					<button class="btn btn-warning" style="margin-bottom: 4px;margin-left: 20px" idx='-2'>下一题</button>
					
					<br/>
                    <br/>
                    
					<table border="1" class="table table-bordered table-hover table-striped">
					<tr><td>
					<c:forEach var="p" varStatus="s" items="${current_exam.examQuestions }">
					
					
						<button class="exam-panel-cell " 
						idx='${s.index }' style="height:40px;width:40px;background-color: ${empty p.answer?'white':'yellow'}">${s.index+1}</button>
						<c:if test="${(s.index+1)%20 eq 0 }">
						</td></tr><tr><td>
					</c:if>
					
					</c:forEach>
                    <br/>
                    <br/>
                    <br/>
                    <br/>
					<button class="btn btn-warning"  idx='-3'>交卷</button>
					
				</div>
			</form>
		</c:if>

	</div>
</div>
	<script type="text/javascript">
		jQuery(function($) {
			$('div.exam-panel button').on('click', function() {
				$('input[type=hidden]').val($(this).attr('idx'));
				$('#exam-form').get(0).submit();
			});
		});
	</script>
</body>
</html>

