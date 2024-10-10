<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML >
<html style="overflow-y: hidden;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>请登录</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/res/js/jquery.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/res/css/login.css" type="text/css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/res/font/font-awesome.min.css" />
</head>
<script type="text/javascript">
function aaa()
{
	$('#loginForm')[0].action="login.action";
	$('#loginForm')[0].submit();
}
function reg()
{
	//alert($('#loginForm')[0].method);
	//用.do是为了避免被登录验证的过滤器过滤
	$('#loginForm')[0].action="user/showReg.do";
	$('#loginForm')[0].submit();
}

</script>
<body class="loginpage">
	<div class="loginbox">
		<div class="loginboxinner">
			
			<div class="from1">
			
				<form id="loginForm" method="post">
			<input type="hidden" name="menu" id="menu" value="1" />
					
				<div class="username">
				
					<div class="usernameinner">
						<input type="text" name="login" id="username" style="font-size: 20px" placeholder="请输入你的手机号" value='${login }' />
					</div>
				</div>
				<div style="margin-top: -25px;"><img src="${pageContext.request.contextPath }/res/images/line.png" style="width: 280px;"></div>
				<div class="password">
					<div class="passwordinner">
						<input type="password" name="password" style="font-size: 20px" id="password" placeholder="请输入你的密码" />
					</div>
				</div>
				<div style="margin-top: -25px;"><img src="${pageContext.request.contextPath }/res/images/line.png" style="width: 280px;"></div>
				<div style="margin-top: 8px">
					<a href="javascript:aaa()"><img src="${pageContext.request.contextPath }/res/images/sub.png" style="width: 280px;height: 35px"></a>
				</div>
			<div style="margin-top: 8px">
					<a href="javascript:reg()"><img src="${pageContext.request.contextPath }/res/images/reg.png" style="width: 280px;height: 35px"></a>
				</div>
			<div  style="display:none">
				</div>
				
			</form> 
			<h5 style="color: red"><%@ include file="/inc/msg.jsp"%></h5>
			</div>
		</div>
		<!--loginboxinner-->
	</div>
	<!--loginbox-->
	<div class="loginbox" style="display: none;">
	<table class="grid" style="width: 100%;">
      <tr>
        <td class="head">登录名</td>
        <td class="head">类型</td>
        <td class="head">可使用的模块</td>
      </tr>
        <tr class="body">
          <td>admin</td>
          <td>用户管理员</td>
          <td>用户管理，更改密码</td>
        </tr>
        <tr class="body">
          <td>operator</td>
          <td>题库管理员</td>
          <td>科目管理，知识点管理<br>考题管理，更改密码</td>
        </tr>
        <tr class="body">
          <td>teacher</td>
          <td>题库使用者</td>
          <td>批次管理,考试安排</td>
        </tr>
        <tr class="body">
          <td>201101002</td>
          <td>考试者</td>
          <td>我的考试</td>
        </tr>
    </table>
    </div>
</body>
</html>