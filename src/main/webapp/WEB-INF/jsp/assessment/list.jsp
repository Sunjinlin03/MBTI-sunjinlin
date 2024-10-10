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
    <%@ include file="/inc/msg.jsp"%>
    <a href="${pageContext.request.contextPath }/assessment/create.action" class="button"><button style="margin-bottom: 10px;" class="btn btn-warning">添加测评类型</button></a>
    <table class="table table-bordered table-hover table-striped">
      <tr>
        <th>序号</th>
        <th>类型名称</th>
        <th>状态</th>
        <th>费用</th>
        <th>操作</th>
      </tr>
      <c:forEach var="sj" varStatus="vs" items="${assessmentList }">
        <tr>
          <td>${vs.index+1 }</td>
          <td>${sj.title }</td>
          <td>${sj.status eq 1 ?'在用':'作废' }</td>
          <td>${sj.cost }</td>
          <td>
            <a class="button" href="${pageContext.request.contextPath }/assessment/view.action?id=${sj.id }">查看</a> <a
              class="button" href="${pageContext.request.contextPath }/assessment/edit.action?id=${sj.id }">修改</a> <a
              class="button" href="${pageContext.request.contextPath }/assessment/view.action?id=${sj.id }&delete=1">删除</a> <a
              class="button" href="${pageContext.request.contextPath }/dimension/list.action?id=${sj.id }">性格维度</a>
          </td>
        </tr>
      </c:forEach>
    </table>
  </div>
</body>
</html>