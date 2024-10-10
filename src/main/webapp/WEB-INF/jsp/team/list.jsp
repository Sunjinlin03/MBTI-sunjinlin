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
    <a href="${pageContext.request.contextPath }/team/create.action" class="button"><button style="margin-bottom: 10px;" class="btn btn-warning">添加批次</button></a>
    <%@ include file="/inc/msg.jsp"%>
    <table class="table table-bordered table-hover table-striped">
      <tr>
        <th>序号</th>
        <th>批次名称</th>
        <th>批次创建时间</th>
        <th>状态</th>
        <th>操作</th>
      </tr>
      <c:forEach var="m" varStatus="vs" items="${teamList }">
        <tr>
          <td>${vs.index+1 }</td>
          <td>${m.name }</td>
          <td>${m.beginYear }</td>
          <c:if test="${m.status==1 }">
          <td>正常</td>
          </c:if>
          <c:if test="${m.status==2}">
          <td>禁用</td>
          </c:if>
          <td>
            <a class="button" href="${pageContext.request.contextPath }/team/view.action?id=${m.id }">查看</a>
            <a class="button" href="${pageContext.request.contextPath }/team/edit.action?id=${m.id }">修改</a>
            <a class="button" href="${pageContext.request.contextPath }/team/view.action?id=${m.id }&delete=1">删除</a>
            <a class="button" href="${pageContext.request.contextPath }/testPersonnel/list.action?tid=${m.id }">查看参测人员</a>
            <a class="button" href="${pageContext.request.contextPath }/testPersonnel/create.jsp?teamId=${m.id }">添加参测人员</a>
            <a class="button" href="${pageContext.request.contextPath }/testPersonnel/import.action?tid=${m.id }">导入参测人员</a>
            
          </td>
        </tr>
      </c:forEach>
    </table>
  </div>
</body>
</html>