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
	<c:if test="${!empty blog}">
	<div>
		<h3>${blog.title}</h3>
		<div style="text-align:left;">${blog.content}</div>
	</div>
	</c:if>
	<a href="index.htm" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-arrow-left"></span> 返回</a>
	</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>