<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<jsp:include page="header.jsp"/>
</head>
<body>
<div class="main_content">
	<jsp:include page="message.jsp"/>
	<div>
	<form:form modelAttribute="userLogin" action="${pageContext.request.contextPath}/userlogin.htm" name="loginForm" method="post" class="form-horizontal">
		<fieldset>
		<div class="form-group">
		<div class="row">
			<label class="col-sm-2 control-label" for="userLoginId">登录账户:</label>
			<div class="col-sm-4"><input name="userLoginId" value="<c:if test='${!empty userLogin && !empty userLogin.userLoginId}'>${userLogin.userLoginId}</c:if>" class="form-control"><span class="label label-warning"><form:errors path="userLoginId"/></span></div>
		</div>
		</div>
		<div class="form-group">
		<div class="row">
			<label class="col-sm-2 control-label" for="password">登录密码:</label>
			<div class="col-sm-4"><input type="password" name="password" class="form-control"><span class="label label-warning"><form:errors path="password"/></span></div>
		</div>
		</div>
		</fieldset>
		<button type="submit" class="btn btn-primary btn-sm">登录</button>
	</form:form>
	</div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>