<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>${title}</title>
<jsp:include page="../header.jsp"/>
<style type="text/css">
	/* table{border-collapse:collapse;margin:2px;width:100%;}*/
	td{padding:2px;vertical-align:middle;}
	.red{color:#FF3333}
	.yellow{color:#FFCC66;}
	.green{color:#33CC66;}
	.black{color:black;}
	.td_number{text-align:right;}
</style>
</head>
<body>
<div class="main_content">
	<jsp:include page="../menubar.jsp"/>
	<jsp:include page="../message.jsp"/>
	<div style="text-align:left;width:1024px;">
		<p><a href="newaccbook.htm" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-plus"></span> 创建账簿</a></p>
		<form action="searchaccbook.htm" name="searchAccBookForm" method="post" class="form-inline">
			<div class="form-group">
			<label class="control-label" for="accBookName">账簿名称：</label>
			</div>
			<div class="form-group">
				<input name="accBookName" value="<c:if test='${!empty param.accBookName}'>${param.accBookName}</c:if>" class="form-control">
			</div>
			<button class="btn btn-primary btn-sm" type="submit"><span class="glyphicon glyphicon-search"></span> 查询</button>
		</form>
		<c:if test="${!empty accbookList}">
			<table class="table table-striped table-bordered">
				<thead><tr><td>序号</td><td>名称</td><td>说明</td><td>操作</td></tr></thead>
				<c:forEach items="${accbookList}" var="accbook" varStatus="status">
				<tr><td>${status.index + 1}</td>
					<td>${accbook.accBookName}</td>
					<td>${accbook.description}</td>
					<td><a href="editaccbook.htm?accBookId=${accbook.accBookId}" class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-pencil"></span> 编辑</a>
					<a href="deleteaccbook.htm?accBookId=${accbook.accBookId}" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-trash"></span> 删除</a></td>
				</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>