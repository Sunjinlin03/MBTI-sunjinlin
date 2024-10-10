<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/res/editer/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/res/editer/plugins/code/prettify.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/css/commonCss.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/table.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/res/styles.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/input.css" />
	<script charset="utf-8" src="${pageContext.request.contextPath }/res/editer/kindeditor.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath }/res/editer/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath }/res/editer/plugins/code/prettify.js"></script>
<title></title>
</head>
<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="title"]', {
				cssPath : './res/editer/plugins/code/prettify.css',
				uploadJson : '../jsp/upload_json.jsp',
				fileManagerJson : '../jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
<body>
  <div style="padding: 10px;">
    <a
      href="${pageContext.request.contextPath }/question/list.action?assessmentId=${question.assessmentId }&type=${question.type}&status=${question.status}"
      class="button"><button class="btn btn-warning" style="margin-bottom: 10px">返回列表</button></a>
    <%@ include file="/inc/msg.jsp"%>
    <form action="${pageContext.request.contextPath }/question/save.action" method="post" class="table" style="width: 80%;">
      <input type="hidden" name="assessmentId" value="${assessment.id }" />
      <table class="table table-bordered table-hover table-striped">
        <tr>
          <td class="head">考核类型：</td>
          <td>
            <input type="text" value="${assessment.title }" readonly="readonly" />
          
          </td>
        </tr>
        <tr>
          <td class="head">题干：</td>
          <td>
            <textarea name="title" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;">
            
            ${question.title }
            
            </textarea>
          </td>
        </tr>
        <tr>
          <td class="head">难度：</td>
          <td>
            <input type="text" name="difficulty" value="${question.difficulty }" />
            <span class="tip">1~9之间</span>
          </td>
        </tr>
        <tr>
          <td class="head">类型</td>
          <td>
            <input type="radio" name='type' value="1" checked />
            JP维度
            <input type="radio" name='type' value="2" ${question.type eq 2?'checked':'' } />
           TF维度
            <input type="radio" name='type' value="3" ${question.type eq 3?'checked':'' } />
            SN维度
            <input type="radio" name='type' value="4" ${question.type eq 4?'checked':'' } />
            EL维度
          </td>
        </tr>
        <c:forEach var="i" begin="0" end="3">
          <tr>
            <td class="head">选项${i+1 }：</td>
            <td>
              <input type="text" name="title[${i }]" value='${choices[i].title }'
                style="width: 400px;" />
              <input type="checkbox" name="checked[${i }]"
                ${choices[i].checked?'checked':'' } value='1' />
              正确选项
            </td>
          </tr>
        </c:forEach>
        <tr>
          <td class="head">提示：</td>
          <td>
            <textarea name="hint">${question.hint }</textarea>
          </td>
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