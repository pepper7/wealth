<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<c:if test="${success == 'success'}">
		<div class='alert alert-success'>
			<a href="#" class="close" data-dismiss="alert">&times; </a>
			<strong><span class="glyphicon glyphicon-ok-sign"></span> Success!</strong> ${msg}
		</div>
	</c:if>
	<c:if test="${success == 'warning'}">
		<div class='alert alert-warning'>
			<a href="#" class="close" data-dismiss="alert">&times; </a>
			<strong><span class="glyphicon glyphicon-warning-sign"></span> Warning!</strong> ${msg}
		</div>
	</c:if>
	<c:if test="${success == 'error'}">
		<div class='alert alert-danger'>
			<a href="#" class="close" data-dismiss="alert">&times; </a>
			<strong><span class="glyphicon glyphicon-exclamation-sign"></span> Error!</strong> ${msg}
		</div>
	</c:if>
	<c:if test="${success == 'info'}">
		<div class='alert alert-info'>
			<a href="#" class="close" data-dismiss="alert">&times; </a>
			<strong><span class="glyphicon glyphicon-info-sign"></span> Info!</strong> ${msg}
		</div>
	</c:if>
</div>
<script type="text/javascript">
	$(function(){
		$("div[class*=alert-]").alert();
	});
</script>
