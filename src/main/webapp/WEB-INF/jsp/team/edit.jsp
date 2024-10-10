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
    <form action="${pageContext.request.contextPath }/team/update.action" method="post" class="table" style="width: 80%;">
      <input type="hidden" name="id" value="${team.id }">
      <table class="table table-bordered table-hover table-striped">
        <tr>
          <td class="head">批次名称</td>
          <td>
            <input type="text" name='name' value="${team.name }">
          </td>
        </tr>
        <tr>
          <td class="head">批次创建时间</td>
          <td>
            <input type="text" name="beginYear" value="${team.beginYear }">
          </td>
        </tr>
        <tr>
          <td class="head">批次状态</td>
          <td>
            <label> <input type="radio" name="status" value="2" checked>
              禁用
            </label> <label> <input type="radio" name="status" value="1"
                ${1 eq team.status?'checked':'' }> 正常
            </label>
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