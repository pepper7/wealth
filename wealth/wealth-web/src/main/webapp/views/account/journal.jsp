<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>${title}</title>
<jsp:include page="../header.jsp"/>
<style type="text/css">
	td{padding:2px;vertical-align:middle;}
	.red{color:#FF3333}
	.yellow{color:#FFCC66;}
	.green{color:#33CC66;}
	.black{color:black;}
	.td_number{text-align:right;}
</style>
</head>
<body>
<div class="main_content">
	<jsp:include page="../menubar.jsp"/>
	<jsp:include page="../message.jsp"/>
	<div id="accBooks" style="text-align:left;width:1024px;<c:if test="${!empty sessionScope.currentAccBook}">display:none;</c:if>">
		<c:if test="${!empty accBookList}">
			<form action="chooseaccbook.htm" name="chooseAccbookForm" method="POST">
			<c:forEach items="${accBookList}" var="book">
				<div class="radio">
				<label><input type="radio" name="accBookId" value="${book.accBookId}" <c:if test="${!empty sessionScope.currentAccBook && sessionScope.currentAccBook.accBookId == book.accBookId}">checked=\"checked\"</c:if> <c:if test="${null == book.accBookName || empty book.accBookName}">disabled=\"disabled\"</c:if>>${book.accBookName}[${book.description}]</label> 
				</div>
			</c:forEach>
			</form>
		</c:if>
		<a href="newaccbook.htm" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-plus"></span> 创建账簿</a>
		<a href="categorymgr.htm" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-plus"></span> 分类管理</a>
		<hr>
	</div>
	<div style="text-align:left;width:1024px;">
	<c:if test="${!empty sessionScope.currentAccBook}">
		<p><a href="javascript:void(0);" class="btn btn-default btn-sm" id="btnChangeBook"><span class="glyphicon glyphicon-refresh"></span> 切换账簿</a> 当前账簿：${sessionScope.currentAccBook.accBookName} 
			<a href="newjournal.htm" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-pencil"></span> 记账</a>
			<a href="${pageContext.request.contextPath}/reports.htm" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-list-alt"></span> 统计报表</a></p>
		<!-- 
		<form action="">
			<input type="file" name=""/> <input class="button icon" type="submit" value="导入日记账"/>
		</form>
		-->
		<form action="searchjournal.htm" name="searchJournalForm" method="post" class="form-inline">
			<input type="hidden" name="accBookId" value="${sessionScope.currentAccBook.accBookId}">
			<div class="form-group">
				<label class="control-label" for="searchMonth">月份：</label>
			</div>
			<div class="form-group">
				<input class="form-control" name="searchMonth" value="<c:if test='${!empty month}'>${month}</c:if>" readonly>
			</div>
			<button class="btn btn-primary btn-sm" type="submit"><span class="glyphicon glyphicon-search"></span> 查询</button>
			<a href="${pageContext.request.contextPath}/journalMonthlyPdf.htm?month=<c:if test='${!empty month}'>${month}</c:if>" class="btn btn-primary btn-sm" id="btnPrint" target="_blank"><span class="glyphicon glyphicon-print"></span> 打印</a>
		</form>
		
		<c:if test="${!empty journalList}">
			<table class="table table-striped table-bordered">
				<thead><tr><td>序号</td><td>日期</td><td>项目</td><td>单位</td><td>数量</td><td>单价</td><td>优惠价</td><td>金额</td><td>规格</td><td>品牌</td><td>说明</td><td>操作</td></tr></thead>
			<c:forEach items="${journalList}" var="journal" varStatus="status">
			<tr><td>${status.index + 1}</td>
				<c:if test="${null == prev_jdate || prev_jdate != journal.journalDate}">
					<c:if test="${journal.dailyCount > 1 }">
						<td rowspan="${journal.dailyCount }">${journal.journalDate}</td>
					</c:if>
					<c:if test="${journal.dailyCount == 1}">
						<td>${journal.journalDate}</td>
					</c:if>
				</c:if>
				<td>${journal.item}</td>
				<td>${journal.uom}</td>
				<td class="td_number">${journal.quantity}</td>
				<td class="td_number"><fmt:formatNumber value="${journal.unitPrice}" pattern="#.00"/></td>
				<td class="td_number"><fmt:formatNumber value="${journal.discountPrice}" pattern="#.00"/></td>
				<td class="td_number <c:if test="${journal.amount > 500}">red</c:if>">
					<fmt:formatNumber value="${journal.amount}" pattern="#.00"/>
				</td>
				<td>${journal.specification}</td>
				<td>${journal.brand}</td>
				<td>${journal.description}</td>
				<td><a href="editjournal.htm?journalId=${journal.journalId}" class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-pencil"></span> 编辑</a>
				<a href="deletejournal.htm?journalId=${journal.journalId}" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-trash"></span> 删除</a></td>
			</tr>
			<c:set var="prev_jdate" value="${journal.journalDate}"/>
			</c:forEach>
			<c:if test="${!empty total}">
				<tfoot><tr><td>合计</td><td></td><td></td><td></td><td></td><td></td><td></td>
				<td class="td_number"><fmt:formatNumber value="${total}" pattern="#.00"/></td>
				<td></td><td></td><td></td></tr></tfoot>
			</c:if>
			</table>
		</c:if>
	</c:if>
</div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
<script>
$(function(){
	$(":radio[name=accBookId]").click(function(){
		var form = $("form[name=chooseAccbookForm]");
		$(form).submit();
	});
	
	$(":text[name=searchMonth]").datetimepicker({
		language:'zh-CN',
		format:'yyyy-mm',
		startView:3,
		minView:3,
		maxView:4,
		todayHighlight:true
	});
	
	$("#btnChangeBook").click(function(){
		$("#accBooks").toggle();
	});
});	
</script>
</html>