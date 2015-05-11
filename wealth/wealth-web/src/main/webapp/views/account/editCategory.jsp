<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
		<form:form modelAttribute="category" action="savecategory.htm" name="editCategoryForm" method="POST" class="form-horizontal">
			<input name="enumType" type="hidden" value="${category.enumType}">
			<%-- <input name="enumId" type="hidden" value="${category.enumId}"> --%>
			<fieldset>
				<div class="form-group">
					<div class="row">
						<label class="col-sm-2 control-label" for="enumId">分类编号:</label>
						<div class="col-sm-4">
						<input name="enumId" value="${category.enumId}" class="form-control" readonly><span class="necessary"> *</span><span class="label label-warning"><form:errors path="enumId"/></span>
						</div>
					</div>
				</div>
				<div class="form-group">
				<div class="row">
					<label class="col-sm-2 control-label" for="enumName">分类名称:</label>
					<div class="col-sm-4">
					<input name="enumName" value="${category.enumName}" class="form-control"><span class="necessary"> *</span><span class="label label-warning"><form:errors path="enumName"/></span>
					</div>
				</div>
				</div>
				<div class="form-group">
				<div class="row">
					<label class="col-sm-2 control-label" for="parentEnumId">父分类:</label>
					<div class="col-sm-4">
					<input name="parentEnumId" value="${category.parentEnumId}" class="form-control"><form:errors path="parentEnumId"/>
					</div>
				</div>
				</div>
				<div class="form-group">
					<div class="row">
						<label class="col-sm-2 control-label" for="description">描述:</label>
						<div class="col-sm-4">
						<input name="description" value="${category.description}" class="form-control"><form:errors path="description"/>
					</div>
				</div>
				</div>
			</fieldset>
			<button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-floppy-disk"></span> 保存</button>
			<a href="${pageContext.request.contextPath}/account/categorymgr.htm" class="btn btn-danger btn-sm" type="button"><span class="glyphicon glyphicon-arrow-left"></span> 取消</a>
		</form:form>
	</div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>