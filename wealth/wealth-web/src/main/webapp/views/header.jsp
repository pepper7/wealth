<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<li><a href="${pageContext.request.contextPath}/index.htm">登录</a></li>
				<li><a href="${pageContext.request.contextPath}/index.htm">注销</a></li>
			</ul>
		</div>
	</div>
	</div>
</nav>
</header>