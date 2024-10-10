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
   
    <table class="table table-bordered table-hover table-striped">
    <tr>
        <td class="head" colspan="7">
        <form  action="${pageContext.request.contextPath }/testPersonnel/select.action" method="post">
        <select class="form-control wid" name="teamId" >
				<c:forEach items="${teamList }" var="team">
					<option value="${team.id }" ${team.id eq param.teamId?'selected':''}>${team.name
						}</option>
				</c:forEach>
				
			</select>
			
		        <p style="float: left;margin-left: 10px;font-size: 15px;margin-top: 3px">姓名</p><input class="form-control wid" type="text" name="stuname"> 
		        <p style="float: left;margin-left: 10px;font-size: 15px;margin-top: 3px">手机号</p><input class="form-control wid" type="text" name="phone"> 
		        
       <input style="margin-left: 10px " class="btn btn-warning" type="submit" value="查询" > 
       </form>
        
       </td>
      </tr>
      <tr>
        <th class="head">序号</th>
        <th class="head">手机号</th>
        <th class="head">姓名</th>
        <th class="head">性别</th>
        <th class="head">出生日期</th>
        <th class="head">状态</th>
        <th class="head">操作</th>
      </tr>
      <c:forEach var="m" varStatus="vs" items="${testPersonnel }">
        <tr>
          <td>${vs.index+1 }</td>
          <td>${m.phone }</td>
          <td>${m.user.name }</td>
          <td>${m.gender eq 'F'?'女':'男'}</td>
          <td>${m.birthDate }</td>
          <td>${m.user.statusDesc }</td>
          <td>
            <a class="button" href="${pageContext.request.contextPath }/testPersonnel/view.action?id=${m.id }">查看</a>
            <a class="button" href="${pageContext.request.contextPath }/testPersonnel/edit.action?id=${m.id }">修改</a>
            <a class="button" href="${pageContext.request.contextPath }/testPersonnel/view.action?id=${m.id }&delete=1">删除</a>
          </td>
        </tr>
      </c:forEach>
    </table>
  </div>
</body>
</html>