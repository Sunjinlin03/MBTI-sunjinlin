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
    <a href="${pageContext.request.contextPath }/team/list.action" class="button"><button class="btn btn-warning" style="margin-bottom: 10px">返回列表</button></a>
    <%@ include file="/inc/msg.jsp"%>
    <table class="table table-bordered table-hover table-striped" style="width: 90%;">
      <tr>
        <td class="head">批次名称</td>
        <td>${team.name }</td>
      </tr>
      <tr>
        <td class="head">批次创建时间</td>
        <td>${team.beginYear }</td>
      </tr>
      <tr>
        <td class="head">批次状态</td>
        <td><c:if test="${team.status==1 }">
        	正常
        </c:if>
        <c:if test="${team.status==2 }">
        	禁用
        </c:if></td>
      </tr>
      <c:if test="${param.delete eq 1 }">
        <tr>
          <td colspan="2" style="text-align: center;">
            <a href="${pageContext.request.contextPath }/team/delete.action?id=${team.id}">确认删除?</a>
          </td>
        </tr>
      </c:if>
    </table>
  </div>
</body>
</html>