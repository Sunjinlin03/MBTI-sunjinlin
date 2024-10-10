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
    <form action="${pageContext.request.contextPath }/question/list.action" method="post">
      <select class="form-control wid" name="assessmentId">
        <c:forEach items="${assessmentList }" var="sj">
          <option value="${sj.id }" ${sj.id eq query.assessmentId?'selected':''}>${sj.title
            }</option>
        </c:forEach>
      </select>
      <select class="form-control wid" name="status">
        <option value="2">可用</option>
        <option value="1" ${query.status eq 1?'selected':'' }>不可用</option>
      </select>
      <select class="form-control wid" name="type">
        <option value="1" selected>JP维度</option>
        <option value="2" ${query.type eq 2?'selected':'' }>TF维度</option>
        <option value="3" ${query.type eq 3?'selected':'' }>SN维度</option>
        <option value="4" ${query.type eq 4?'selected':'' }>EI维度</option>
      </select>
      <input class="btn btn-warning" style="margin-left: 10px;margin-bottom: 3px" type="submit" name="list" value="查看本考核类型考题" />
      <input class="btn btn-warning" style="margin-left: 10px;margin-bottom: 3px" type="submit" name="create" value="添加本考核类型考题" />
    </form>
    <%@ include file="/inc/msg.jsp"%>
    <table class="table table-bordered table-hover table-striped">
      <tr>
        <th>序号</th>
        <th>题目内容</th>
        <th>操作</th>
      </tr>
      <c:forEach var="p" varStatus="vs" items="${questionList }">
        <tr class="body">
          <td>${vs.index+1 }</td>
          <td>${p.title }</td>
          <td><a class="button" href="${pageContext.request.contextPath }/question/view.action?id=${p.id }">查看</a> <a
            class="button" href="${pageContext.request.contextPath }/question/edit.action?id=${p.id }">修改</a> <a
            class="button" href="${pageContext.request.contextPath }/question/view.action?id=${p.id }&delete=1">删除</a></td>
        </tr>
      </c:forEach>
    </table>
  </div>
</body>
</html>