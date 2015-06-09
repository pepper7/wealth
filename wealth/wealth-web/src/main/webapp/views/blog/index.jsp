<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<jsp:include page="../header.jsp"/>
</head>
<body>
<div class="main_content">
	<jsp:include page="../message.jsp"/>
	<div style="text-align:left;width:1024px;">
	<p><a href="newblog.htm" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-plus"></span> 撰写博文</a></p>
		<c:if test="${!empty blogList}">
			<table class="table table-striped table-bordered">
				<thead><tr><td>序号</td><td>标题</td><td>日期</td><td>操作</td></tr></thead>
				<c:forEach items="${blogList}" var="blog" varStatus="status">
				<tr><td>${status.index + 1}</td>
					<td><a href="blogdetail.htm?blogId=${blog.blogId}">${blog.title}</a></td>
					<td></td>
					<td><a href="editblog.htm?blogId=${blog.blogId}" class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-pencil"></span> 编辑</a>
					<a href="deleteblog.htm?blogId=${blog.blogId}" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-trash"></span> 删除</a></td>
				</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>