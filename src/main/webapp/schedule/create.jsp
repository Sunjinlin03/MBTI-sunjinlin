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
		<a href="${pageContext.request.contextPath }/schedule/list.action" class="button"><button class="btn btn-warning" style="margin-bottom: 10px">返回列表</button></a>
		<form action="${pageContext.request.contextPath }/schedule/save.action" method="post" class="table" style="width: 80%;">
			<table class="table table-bordered table-hover table-striped">
				<tr>
					<td class="head">考试科目</td>
					<td><select name="assessmentId">
							<c:forEach items="${assessmentList }" var="s">
								<option value="${s.id }" ${s.id eq schedule.assessmentId ?'selected':'' }>${s.title }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td class="head">考试批次</td>
					<td><select name="teamId">
							<c:forEach items="${teamList }" var="s">
								<option value="${s.id }" ${s.id eq schedule.teamId ?'selected':'' }>${s.name }</option>
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<td class="head">开考日期</td>
					<td>
                        
                        <input class="form-control" type="datetime-local" step="01" name="beginDate" value="2021/01/12 21:14:07" style="width: 200px;">
                    </td>
				</tr>
				<tr>
					<td class="head">结束日期</td>
					<td>
                        <!-- <input type="text" name="endDate" placeholder="格式:yyyy-MM-dd HH:mm" > -->
                         <input class="form-control" type="datetime-local" step="01" name="endDate" value="2021/01/12 21:14:07" style="width: 200px;">
                    </td>
				</tr>
				<tr>
					<td class="head">考试时长</td>
					<td><input type="text" name="duration" value="${schedule.duration }">分钟</td>
				</tr>
				<tr>
					<td class="head">创建时间</td>
					<td><input type="date" name="createDate"></td>
				</tr>
				<tr>
					<td class="head">试题数量</td>
					<td><input type="text" name="questionNumber" placeholder="试题的数量必须为4的倍数"></td>
				</tr>
				<tr>
					<td colspan="2" class="edit">
            <input class="btn btn-warning" type="submit" value="保存" />
            <input class="btn btn-warning" type="reset" value="重置" />
          </td>
				</tr>
			</table>
		</form>

	</div>
</body>
</html>