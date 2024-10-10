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
  <a href="${pageContext.request.contextPath }/testPersonnel/list.action?tid=${testPersonnel.teamId }" class="button"><button class="btn btn-warning" style="margin-bottom: 10px">返回列表</button></a>
    <%@ include file="/inc/msg.jsp"%>
    <form action="${pageContext.request.contextPath }/testPersonnel/update.action" method="post" class="table" style="width: 80%;">
      <input type="hidden" name="id" value="${testPersonnel.id }" />
      <table class="table table-bordered table-hover table-striped">
        <tr>
          <td class="head">姓名</td>
          <td>
            <input type="text" name="name" value="${testPersonnel.user.name }">
          </td>
        </tr>
        <tr>
          <td class="head">手机号</td>
          <td>
            <input type="text" name="phone" value="${testPersonnel.phone }">
          </td>
        </tr>
        <tr>
          <td class="head">性别</td>
          <td>
            <input type="radio" name="gender" value="M" checked>
            男
            <input type="radio" name="gender" value="F"
              ${testPersonnel.gender =="F"?'checked':'' }>
            女
          </td>
        </tr>
        <tr>
          <td class="head">出生日期</td>
          <td>
            <input type="text" name="birthDate" value="${testPersonnel.birthDate }" required="required" placeholder=“日期格式：yyyy-MM-dd”>
          </td>
        </tr>
        <tr>
          <td class="head">状态</td>
          <td>
            <input type="radio" name="status" value="1" checked>
            正常
            <input type="radio" name="status" value="2"
              ${testPersonnel.user.status eq 2?'checked':'' }>
            禁用
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