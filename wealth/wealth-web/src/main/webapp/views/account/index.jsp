<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>${result.title}</title>
<jsp:include page="../includes.jsp"/>
<style type="text/css">
	table{border-collapse:collapse;margin:2px;width:100%;}
	thead{text-align:center;}
	table,th,td{border: 1px solid black;font-family: "Arial", "Helvetica", "Verdana", "sans-serif";font-size: 12px;}
	td{padding:2px;vertical-align:middle;}
	.red{color:#FF3333}
	.yellow{color:#FFCC66;}
	.green{color:#33CC66;}
	.black{color:black;}
	.td_number{text-align:right;}
</style>
</head>
<body>
	<div id="accBooks" style="text-align:left;width:1024px;<c:if test="${!empty sessionScope.currentAccBook}">display:none;</c:if>">
		<c:if test="${!empty result.accBookList}">
			<ul>
			<c:forEach items="${result.accBookList}" var="book">
				<li><input type="radio" name="accBookId" value="${book.accBookId}" <c:if test="${!empty sessionScope.currentAccBook && sessionScope.currentAccBook.accBookId == book.accBookId}">checked=\"checked\"</c:if> <c:if test="${null == book.accBookName || empty book.accBookName}">disabled=\"disabled\"</c:if>>${book.accBookName}[${book.description}] 
					<!-- <a href="editaccount.htm?accountNo=${account.accountNo}">编辑</a><a href="delaccount.htm?accountNo=${account.accountNo}">删除</a> --></li>
			</c:forEach>
			</ul>
			<form action="chooseaccbook.htm" name="chooseAccbookForm" method="POST">
				<input type="hidden" value="" name="accBookId">
			</form>
		</c:if>
		<a href="newaccbook.htm" class="button icon add"><span>创建账簿</span></a>
		<hr>
	</div>
<div style="text-align:left;width:1024px;">
	<c:if test="${!empty sessionScope.currentAccBook}">
		<p><a href="javascript:void(0);" class="button icon loop" id="btnChangeBook"><span>切换账簿</span></a> 当前账簿：${sessionScope.currentAccBook.accBookName} <a href="newjournal.htm" class="button icon edit"><span>记账</span></a><a href="reports.htm" class="button icon tag"><span>统计报表</span></a></p>
		<!-- 
		<form action="">
			<input type="file" name=""/> <input class="button icon" type="submit" value="导入日记账"/>
		</form>
		-->
		<form action="searchJournal.htm" name="searchJournalForm" method="post">
			<input type="hidden" name="accBookId" value="${sessionScope.currentAccBook.accBookId}">
			查询月份：<input name="searchMonth" value="<c:if test='${!empty result.month}'>${result.month}</c:if>">
			<a href="javascript:void(0);" class="button icon search" id="btnSearch">查询</a>
			<a class="button icon log" href="reportmonthlypdf.htm?month=<c:if test='${!empty result.month}'>${result.month}</c:if>" id="btnPrint" target="_blank">打印</a>
		</form>
		
		<c:if test="${!empty result.journalList}">
			<table>
				<thead><tr><td>序号</td><td>日期</td><td>项目</td><td>单位</td><td>数量</td><td>单价</td><td>优惠价</td><td>金额</td><td>规格</td><td>品牌</td><td>说明</td><td>操作</td></tr></thead>
			<c:forEach items="${result.journalList}" var="journal" varStatus="status">
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
				<td><a href="editjournal.htm?journalId=${journal.journalId}" class="button icon edit"><span>编辑</span></a>
				<a href="deletejournal.htm?journalId=${journal.journalId}" class="button icon trash"><span>删除</span></a></td>
			</tr>
			<c:set var="prev_jdate" value="${journal.journalDate}"/>
			</c:forEach>
			<c:if test="${!empty result.total}">
				<tfoot><tr><td>合计</td><td></td><td></td><td></td><td></td><td></td><td></td>
				<td class="td_number"><fmt:formatNumber value="${result.total}" pattern="#.00"/></td>
				<td></td><td></td><td></td></tr></tfoot>
			</c:if>
			</table>
		</c:if>
	</c:if>
</div>

</body>
<script>
$(function(){
	$(":radio[name=accBookId]").click(function(){
		var form = $("form[name=chooseAccbookForm]");
		$(form).find(":hidden[name=accBookId]").val($(this).val());
		$(form).submit();
	});
	$(":text[name=searchMonth]").datepicker({
        buttonImageOnly: false,
		dateFormat:'yy-mm'
	});
	$("#btnSearch").click(function(){
		$(this).parent().submit();
	});
	
	$("#btnChangeBook").click(function(){
		$("#accBooks").toggle();
	});
});	
</script>
</html>