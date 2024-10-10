<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML >
<html style="overflow-y: hidden;">
<head>
<meta charset="utf-8">
<%@ include file="/inc/head.jsp"%>
<title>MBTI职业性格测试系统</title>
</head>
<body style="overflow: hidden">
	<header class="Hui-header cl">
		<a class="Hui-logo l" href="${pageContext.request.contextPath }"><img src="${pageContext.request.contextPath }/res/images/logo1.png" style="width: 300px;height: 45px"></a> 
		<span class="Hui-subtitle l"></span>
		
		<!-- 判断是否为空，为空跳转至登录login页面-->
		<span class="Hui-userbox">
		<span class="c-white">
		<c:if test="${empty current_user }">
		
		<a href="${pageContext.request.contextPath }/exam/user/showReg.do">注册</a>
		<a href="${pageContext.request.contextPath }/exam/login.jsp">登录</a>
		
		</c:if></span>
		<span class="c-white"> 
		<c:if test="${not empty current_user }">您好,${current_user.name }</c:if>

		</span> <c:if test="${not empty current_user }">
				<a class="btn btn-out radius ml-10" href="${pageContext.request.contextPath }/logout.action" title="退出"> 
				<i class="icon-off"></i>
					退出
				</a>
			</c:if> </span> <a aria-hidden="false" class="Hui-nav-toggle" id="nav-toggle" href="#">
			</a>
	</header>
	<div class="cl Hui-main">
		<aside class="Hui-aside" style="">
			<div class="menu_dropdown bk_2">
			<c:if test="${current_user.type!=4 }">
				<dl id="menu-user">
					<dt>
						<a _href="${pageContext.request.contextPath }/user/list.action" href="javascript:;"> <i class="icon-user"></i> 用户管理<b></b>
						</a>
					</dt>
				</dl>
				<dl id="menu-comments">
					<dt>
						<a _href="${pageContext.request.contextPath }/assessment/list.action" href="javascript:;"> <i class="icon-comments"></i> 考核类型管理<b></b>
						</a>
					</dt>
				</dl>
				<dl id="menu-article">
					<dt>
						<a _href="${pageContext.request.contextPath }/dimension/list.action" href="javascript:void(0)"> <i class="icon-edit"></i> 性格维度管理<b></b>
						</a>
					</dt>
				</dl>
				<dl id="menu-picture">
					<dt>
						<a _href="${pageContext.request.contextPath }/question/list.action" href="javascript:void(0)"> <i class="icon-picture"></i>
							题目管理<b></b>
						</a>
					</dt>
				</dl>
				<dl id="menu-product">
					<dt>
						<a _href="${pageContext.request.contextPath }/team/list.action" href="javascript:void(0)"> <i class="icon-beaker"></i> 批次管理<b></b>
						</a>
					</dt>
				</dl>
				<dl id="menu-product">
					<dt>
						<a _href="${pageContext.request.contextPath }/testPersonnel/list.action" href="javascript:void(0)"> <i class="icon-beaker"></i> 参测人员管理<b></b>
						</a>
					</dt>
				</dl>
				<dl id="menu-product">
					<dt>
						<a _href="${pageContext.request.contextPath }/schedule/list.action" href="javascript:void(0)"> <i class="icon-beaker"></i>
							测试安排<b></b>
						</a>
					</dt>
				</dl>
				</c:if>
				<c:if test="${current_user.type==4 }">
				<dl id="menu-product">
					<dt>
						<a _href="${pageContext.request.contextPath }/exam/list.action" href="javascript:void(0)"> <i class="icon-beaker"></i> 我的测试<b></b>
						</a>
					</dt>
				</dl>
				</c:if>
				<dl id="menu-admin">
					<dt>
						<a _href="${pageContext.request.contextPath }/password.action" href="javascript:void(0)"> <i class="icon-key"></i> 更改密码<b></b>
						</a>
					</dt>
				</dl>
			</div>
		</aside>
		<div class="dislpayArrow">
			<a class="pngfix" href="javascript:;"></a>
		</div>
		<section class="Hui-article">
			<div id="Hui-tabNav" class="Hui-tabNav">
				<div class="Hui-tabNav-wp">
					<ul id="min_title_list" class="acrossTab cl">
						<li class="active"><span title="我的桌面" data-href="welcome.html">我的桌面</span><em></em></li>
					</ul>
				</div>
				<div class="Hui-tabNav-more btn-group">
					<a id="js-tabNav-prev" class="btn radius btn-default btn-small" href="javascript:;"> <i
						class="icon-step-backward"></i>
					</a> <a id="js-tabNav-next" class="btn radius btn-default btn-small" href="javascript:;"> <i
						class="icon-step-forward"></i>
					</a>
				</div>
			</div>
			<div id="iframe_box" class="Hui-articlebox">
				<div class="show_iframe">
					<div style="display: none" class="loading"></div>
					<iframe scrolling="yes" frameborder="0" src="${pageContext.request.contextPath }/welcome.jsp"></iframe>
				</div>
			</div>
		</section>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath }/res/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/res/js/Validform_v5.3.2_min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/res/layer/layer.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/res/js/H-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/res/js/H-ui.admin.js"></script>

</body>
</html>