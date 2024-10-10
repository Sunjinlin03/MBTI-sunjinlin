<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath }/css/commonCss.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/table.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/res/styles.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/input.css" />
<title></title>
</head>
<body>
  <div style="padding: 10px;">
     <a href="${pageContext.request.contextPath }/team/list.action" class="button"><button class="btn btn-warning" style="margin-bottom: 10px">返回列表</button></a>
    <%@ include file="/inc/msg.jsp"%>
    <form action="${pageContext.request.contextPath }/testPersonnel/add.action" method="post" class="table" style="width: 80%;">
    	<input type="hidden" name="type" value="4" >
			<input type="hidden" name="status" value="1" >
      <table class="table table-bordered table-hover table-striped">
        <tr>
          <td class="head">姓名</td>
          <td>
            <input type="text" name="name" >
          </td>
        </tr>
        <tr>
        <td class="head">考核类型</td>
        <c:if test="${empty param.teamId}">
            <td>${teamId}<input type="hidden" value="${teamId }" name="teamId"></td>
        </c:if>
        <c:if test="${not empty param.teamId}">
            <td>${param.teamId}<input type="hidden" value="${param.teamId }" name="teamId"></td>
        </c:if> 
      </tr>
        <tr>
          <td class="head">手机号</td>
          <td>
            <input type="text" name="login" >
          </td>
        </tr>
        <tr>
          <td class="head">性别</td>
          <td>
            <input type="radio" name="gender" value="M" checked>
            男
            <input type="radio" name="gender" value="F">
            女
            
          </td>
        </tr>
        <tr>
          <td class="head">生日</td>
          <td>
    		<input type="date" name="birthDate">
          </td>
        </tr>

        <tr>
         <td colspan="2" class="edit"><input class="btn btn-warning" type="submit" value="保存" /><input class="btn btn-warning" type="reset" value="重置" /></td>
        </tr>
      </table>
    </form>
  </div>
</body>
</html>