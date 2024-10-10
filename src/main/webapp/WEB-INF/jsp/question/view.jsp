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
    <%@ include file="/inc/msg.jsp"%>
    <a
      href="${pageContext.request.contextPath }/question/list.action?assessmentId=${question.assessmentId }&type=${question.type}&status=${question.status}"
      class="button"><button class="btn btn-warning" style="margin-bottom: 10px">返回列表</button></a>
    <table class="table table-bordered table-hover table-striped" style="width: 90%;">
      <caption>题目信息</caption>
      <tr>
        <td class="head">题目内容:</td>
        <td>${question.title }</td>
      </tr>
      <tr>
        <td class="head">解答提示：</td>
        <td>${question.hint }</td>
      </tr>
    </table>
    <table class="table table-bordered table-hover table-striped" style="width: 90%;">
      <caption>选项信息</caption>
      <tr>
        <td class="head">选项序号</td>
        <td class="head">选项内容</td>
        <td class="head">是否正确</td>
      </tr>
      <c:forEach var="i" begin="0" end="3">
        <tr>
          <td>选项${i+1 }</td>
          <td>${choices[i].title }</td>
          <td>${choices[i].checked?'是':'' }</td>
        </tr>
      </c:forEach>
    </table>
    <table class="table table-bordered table-hover table-striped" style="width: 90%;">
      <caption>性格维度信息</caption>
      <tr>
        <td class="head">性格维度名称</td>
        <td class="head">性格维度说明</td>
      </tr>
      <c:forEach var="p" items="${dimension }">
        <tr>
          <td>${p.title }</td>
          <td>${p.depict }</td>
        </tr>
      </c:forEach>
       <c:if test="${param.delete eq 1 }">
       <tr>
       <td colspan="2" style="text-align: center">
      <a href="${pageContext.request.contextPath }/question/delete.action?id=${question.id }&assessmentId=${question.assessmentId}"
        class="button">确认删除</a>
        </td>
        </tr>
    </c:if>
    </table>
   
  </div>
</body>
</html>