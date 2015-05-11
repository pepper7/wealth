<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="nav nav-tabs">
	<li <c:if test="${navMenu == 'AccBook'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/account/index.htm">账簿</a></li>
	<li <c:if test="${navMenu == 'Journal'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/account/journal.htm">记账</a></li>
	<li <c:if test="${navMenu == 'Report'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/reports.htm">报表</a></li>
	<li <c:if test="${navMenu == 'Category'}">class="active"</c:if>><a href="${pageContext.request.contextPath}/account/categorymgr.htm">分类</a></li>
</ul>