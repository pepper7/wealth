<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>${result.title}</title>
<jsp:include page="../includes.jsp"/>
</head>
<body>
	<div>
		<form action="addaccbook.htm" name="addAccBookForm" method="POST">
			<fieldset>
				账簿名称<input name="accBookName">
				描述<input name="description">
			</fieldset>
			<input type="submit" value="提交">
		</form>
	</div>
</body>
</html>