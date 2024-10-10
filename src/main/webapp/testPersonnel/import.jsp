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
  <a href="${pageContext.request.contextPath }/team/list.action" class="button"><button class="btn btn-warning" style="margin-bottom: 10px">返回列表</button></a><br>
    <a href="${pageContext.request.contextPath }/testPersonnel/student.txt">参测人员文件格式demo</a>
    <form action="${pageContext.request.contextPath }/testPersonnel/import.action" method="post" 
    enctype="multipart/form-data"
      class="table" style="width: 80%;">
      <input type="hidden" name="tid" value="${param.tid }">
      <input  type="file" name="file">
      <input class="btn btn-warning" style="margin-top: 15px" type="submit" value="导入" />
    </form>
  </div>
</body>
</html>