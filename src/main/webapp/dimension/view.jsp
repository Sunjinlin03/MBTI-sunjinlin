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
    <a href="${pageContext.request.contextPath }/dimension/list.action?id=${dimension.assessmentId }" class="button"><button class="btn btn-warning" style="margin-bottom: 10px">返回列表</button></a>
    <table class="table table-bordered table-hover table-striped" style="width: 90%;">
      <tr>
        <td class="head">性格维度名称:</td>
        <td>${dimension.title }</td>
      </tr>
      <tr>
        <td class="head">性格维度说明：</td>
        <td>${dimension.depict }</td>
      </tr>
      <c:if test="${param.delete eq 1 }">
        <tr>
          <td colspan="2" style="text-align: center;">
            <a href="${pageContext.request.contextPath }/dimension/delete.action?id=${dimension.id }&assessmentId=${dimension.assessmentId}"
              class="button">确认删除</a>
          </td>
        </tr>
      </c:if>
    </table>
  </div>
</body>
</html>