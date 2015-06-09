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
	<jsp:include page="../menubar.jsp"/>
	<jsp:include page="../message.jsp"/>
	<div>
		<c:if test="${!empty accountingBook }">
		<form action="upload.htm" name="uploadForm" method="POST" class="form-horizontal">
			<input type="hidden" name="accBookId" value="${accountingBook.accBookId}">
			<fieldset>
				<div class="form-group">
					<div class="row">
						<label class="col-sm-2 control-label" for="accBookName">账簿名称:</label>
						<div class="col-sm-4"><p class="form-control-static text-left">${accountingBook.accBookName}</p></div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
					<label class="col-sm-2 control-label" for="accBookName">上传图标:</label>
					<div class="col-sm-4">
						<input type="file" name="iconFile">
					</div>
					</div>
				</div>
			</fieldset>
			<button class="btn btn-primary btn-sm" type="submit"><span class="glyphicon glyphicon-floppy-disk"></span> 上传</button>
			<a href="${pageContext.request.contextPath}/account/index.htm" class="btn btn-danger btn-sm" type="button"><span class="glyphicon glyphicon-arrow-left"></span> 取消</a>
		</form>
		</c:if>
	</div>
	</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>