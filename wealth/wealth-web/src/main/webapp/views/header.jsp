<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="includes.jsp"/>
<header>
<nav class="navbar navbar-default">
	<div class="containt">
	<div class="navbar-header">
		<a href="${pageContext.request.contextPath}/index.htm" title="Wealth - Salt.org">
			<img alt="salt" src="${pageContext.request.contextPath}/images/logo-icon-48x48.png">
		</a>
	</div>
	<div class="container-fluid">
		<div class="collapse navbar-collapse">
			<p class="navbar-text">Wealth</p>
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${empty sessionScope['userLogin-salt'] }">
					<li><a href="${pageContext.request.contextPath}/login.htm">登录</a></li>
				</c:if>
				<c:if test="${!empty sessionScope['userLogin-salt'] }">
					<li><a href="javascript:void(0);">${sessionScope["userLogin-salt"].userLoginId}</a></li>
					<li><a href="${pageContext.request.contextPath}/logout.htm">注销</a></li>
				</c:if>
			</ul>
		</div>
	</div>
	</div>
</nav>
</header>