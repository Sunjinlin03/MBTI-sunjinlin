<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/res/styles.css" />
<title>用户注册</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/res/js/jquery.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/res/css/login.css" type="text/css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/res/font/font-awesome.min.css" />
</head>
<script type="text/javascript">
function reg()
{
	//alert(1);
	var login = $("#login").val();
	//alert(login);
	var password = $("#password").val();
	//alert(password);
	var repassword = $("#repassword").val();
	var name = $("#name").val();
		if(login=="")
			{
				alert("请输入手机号码");
				return;
			}
		if(name=="")
		{
			alert("请输入姓名");
			return;
		}
		if(password!=repassword)
			{
				
				alert("两次密码不一致，请重新输入");
				return;
			}
	//alert(password!=repassword);	
	//alert($("#regForm"));	
	$("#regForm")[0].submit();
	}
	
	function back()
	{
		window.location.href="${pageContext.request.contextPath }/login.jsp";
	}
</script>
<body class="registerpage">
	
	<div class="register1">
		<div class="register-top">
		</div>
		<form id = "regForm" action="${pageContext.request.contextPath }/user/reg.do" method="post">
			<div class="form-div">
				<span class="ziti">手 &nbsp;机&nbsp;&nbsp;&nbsp;号：</span>
				<input id="login" type="text" name="login">
			</div>
			<div class="form-div">
				<span class="ziti">密 &nbsp;&nbsp;&nbsp;&nbsp;码：</span>
				<input id="password" type="password" name="passwd">
			</div>
			<div class="form-div">
				<span class="ziti-1">确认密码：</span>
				<input id="repassword" type="password" name="repasswd">
			</div>
			<div class="form-div">
				<span class="ziti">姓 &nbsp;&nbsp;&nbsp;&nbsp;名：</span>
				<input id="name" type="text" name="name">
			</div>
			<div class="form-div-1">
				<span class="ziti">性 &nbsp;&nbsp;&nbsp;&nbsp;别：</span>
				<input type="radio" name="gender" value="M" checked style="margin-top: 5px;"><span style="font-size: 20px;">男</span>
           
            <input type="radio" name="gender" value="F"><span style="font-size: 20px;">女</span>
			</div>
			<div class="form-div">
				<span class="ziti">批 &nbsp;&nbsp;&nbsp;&nbsp;次：</span>
				<select name="teamId" style="width: 170px;height: 25px;border: none;margin-top: 2px;border-radius: 5px;">
				<c:forEach items="${teamList }" var="team">
					<option value="${team.id }" >${team.name
						}</option>
				</c:forEach>
			</select>
			</div>
			
			<div class="form-bottom">
			
				<div style=" margin-top: 80px;height: 30px;">
					<a href="javascript:reg()"><img src="${pageContext.request.contextPath }/res/images/register007.png" style="width: 120px;height: 35px;margin-left: 50px;"></a>
					<a href="javascript:back()"><img src="${pageContext.request.contextPath }/res/images/register008.png" style="width: 120px;height: 35px;margin-left: 50px;"></a>
				</div>
				<br>
				<h5  style="color: red;"><%@ include file="/inc/msg.jsp"%></h5>
			</div>
		</form>
		</div>
	</div>
</body>
</html>