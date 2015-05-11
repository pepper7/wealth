<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>${title}</title>
<jsp:include page="../header.jsp"/>
</head>
<body>
	<div class="main_content">
	<jsp:include page="../menubar.jsp"/>
	<jsp:include page="../message.jsp"/>
	<div>
		<form action="addaccbook.htm" name="addAccBookForm" method="POST" class="form-horizontal">
			<fieldset>
				<div class="form-group">
					<div class="row">
					<label class="col-sm-2 control-label" for="accBookName">账簿名称:</label>
					<div class="col-sm-4">
						<input name="accBookName" class="form-control"><span class="necessary"> *</span>
					</div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
					<label class="col-sm-2 control-label" for="description">描述:</label>
					<div class="col-sm-4">
						<input name="description" class="form-control">
					</div>
					</div>
				</div>
			</fieldset>
			<button class="btn btn-primary btn-sm" type="submit"><span class="glyphicon glyphicon-floppy-disk"></span> 创建</button>
			<a href="${pageContext.request.contextPath}/account/index.htm" class="btn btn-danger btn-sm" type="button"><span class="glyphicon glyphicon-arrow-left"></span> 取消</a>
		</form>
	</div>
	</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>