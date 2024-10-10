<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath }/css/commonJs.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/css/commonCss.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/table.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/res/styles.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/input.css" />
</head>
<body>
  <div style="padding: 10px;">
    <%@ include file="/inc/msg.jsp"%>
    <form action="${pageContext.request.contextPath }/password.action" method="post" class="table" style="width: 80%;">
      <table class="grid left-grid">
        <tr>
          <td class="head">原密码:</td>
          <td>
            <input class="form-control wid" type="password" name="oldPassword" value="${login }">
          </td>
        </tr>
        <tr>
          <td class="head">新密码：</td>
          <td>
            <input class="form-control wid" type="password" name="newPassword">
          </td>
        </tr>
        <tr>
          <td class="head">重复一遍：</td>
          <td>
            <input class="form-control wid" type="password" name="comfirm">
          </td>
        </tr>
        <tr>
          <td style="text-align: center;" colspan="2">
            <input class="btn btn-warning" type="submit" value="修改密码" />
          </td>
        </tr>
      </table>
    </form>
  </div>
</body>
</html>