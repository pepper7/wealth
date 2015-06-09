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
	<div style="text-align:left;">
		<form action="addblog.htm" name="addBlogForm" method="POST" class="form-horizontal" enctype="multipart/form-data">
			<fieldset>
				<div class="form-group">
					<div class="row">
						<label class="col-sm-2 control-label" for="accBookName">标题:</label>
						<div class="col-sm-8">
						<input name="title" class="form-control"><span class="necessary"> *</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
					<label class="col-sm-2 control-label" for="description">内容:</label>
					<div class="col-sm-8">
						<textarea name="content" id="content" rows="30"></textarea>
						<script type="text/javascript">
		        			var editor = UE.getEditor('content');
		    			</script>
					</div>
					</div>
				</div>
			</fieldset>
			<button class="btn btn-primary btn-sm" type="submit"><span class="glyphicon glyphicon-floppy-disk"></span> 创建</button>
			<a href="${pageContext.request.contextPath}/blog/index.htm" class="btn btn-danger btn-sm" type="button"><span class="glyphicon glyphicon-arrow-left"></span> 取消</a>
		</form>
	</div>
	</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>