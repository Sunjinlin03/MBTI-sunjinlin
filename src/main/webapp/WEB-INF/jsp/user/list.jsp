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
    <a href="${pageContext.request.contextPath }/user/create.action" ><button style="margin-bottom: 10px;" class="btn btn-warning">添加用户</button></a>
    <%@ include file="/inc/msg.jsp"%>
    <table class="table table-bordered table-hover table-striped">
      <tr>
        <th>序号</th>
        <th>登录名</th>
        <th>真实姓名</th>
        <th>类型</th>
        <th>状态</th>
        <th>操作</th>
      </tr>
      <c:forEach var="u" varStatus="vs" items="${userList }">
        <tr >
          <td>${vs.index+1 }</td>
          <td>${u.login }</td>
          <td>${u.name }</td>
          <td>${u.typeDesc }</td>
          <td>${u.statusDesc }</td>
          <td><a class="button" href="${pageContext.request.contextPath }/user/view.action?id=${u.id }">查看</a> <a
            class="button" href="${pageContext.request.contextPath }/user/edit.action?id=${u.id }">修改</a> <a class="button"
            href="${pageContext.request.contextPath }/user/view.action?id=${u.id }&delete=1">删除</a> <a class="button"
            href="${pageContext.request.contextPath }/user/password.action?id=${u.id }">重置密码</a></td>
        </tr>
      </c:forEach>
    </table>
  </div>
</body>
</html>