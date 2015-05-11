<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>${title}</title>
<jsp:include page="../header.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/images/js/FusionCharts/FusionCharts.js"></script>
<style type="text/css">
	#reports{overflow:hidden;}
	#reports div{float:left;margin:2px 5px;}
	#rptCategoryDetails{float:right;width:400px;}
	table{border-collapse:collapse;margin:2px;width:100%;}
	thead{text-align:center;}
	table,th,td{border: 1px solid black;font-family: "Arial", "Helvetica", "Verdana", "sans-serif";font-size: 12px;}
	td{padding:2px;vertical-align:middle;}
	.td_number{text-align:right;}
</style>
</head>
<body>
<div class="main_content">
<jsp:include page="../menubar.jsp"/>
<div id="accBooks" style="text-align:left;width:1024px;<c:if test="${!empty sessionScope.currentAccBook}">display:none;</c:if>">
	<c:if test="${!empty accBookList}">
		<ul>
		<c:forEach items="${accBookList}" var="book">
			<li><input type="radio" name="accBookId" value="${book.accBookId}" <c:if test="${!empty sessionScope.currentAccBook && sessionScope.currentAccBook.accBookId == book.accBookId}">checked=\"checked\"</c:if> <c:if test="${null == book.accBookName || empty book.accBookName}">disabled=\"disabled\"</c:if>>${book.accBookName}[${book.description}] 
				<!-- <a href="editaccount.htm?accountNo=${account.accountNo}">编辑</a><a href="delaccount.htm?accountNo=${account.accountNo}">删除</a> --></li>
		</c:forEach>
		</ul>
		<form action="chooseaccbook.htm" name="chooseAccbookForm" method="POST">
			<input type="hidden" value="" name="accBookId">
		</form>
	</c:if>
	<hr>
</div>
<div style="text-align:left;width:1024px;">
	<a href="javascript:void(0);" class="btn btn-default btn-sm" id="btnChangeBook"><span class="glyphicon glyphicon-refresh"></span> 切换账簿</a>当前账簿：${sessionScope.currentAccBook.accBookName}<a href="account/journal.htm" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-arrow-left"></span> 返回</a>
	<div id="reports">
		<div id="rptYearly">The Accounting Report Yearly!</div>
		<div id="rptMonthly">The Accounting Report Monthly!</div>
		<div id="rptCategoryMonthly">The Accounting Report Monthly By Category!</div>
		<div id="rptCategoryDetails"></div>
	</div>
</div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
<script>
$(function(){
	$(":radio[name=accBookId]").click(function(){
		var form = $("form[name=chooseAccbookForm]");
		$(form).find(":hidden[name=accBookId]").val($(this).val());
		$(form).submit();
	});
	
	$("#btnChangeBook").click(function(){
		$("#accBooks").toggle();
	});
	
	var yearlyReport = new FusionCharts({
		swfUrl:"${pageContext.request.contextPath}/images/js/FusionCharts/charts/Column2D.swf", 
		      id: "rptYearlyId", 
		      width: "500", 
		      height:"375", 
		      debugMode : false
		});
	yearlyReport.setJSONUrl("getyearlydatas.htm");
	yearlyReport.render("rptYearly");
	
	var monthlyReport = new FusionCharts({
		swfUrl:"${pageContext.request.contextPath}/images/js/FusionCharts/charts/Column2D.swf", 
		      id: "rptMonthlyId", 
		      width: "500", 
		      height:"375", 
		      debugMode : false
		});
	monthlyReport.setJSONUrl("getmonthlydatas.htm");
	monthlyReport.render("rptMonthly");
	
	var cateMonthlyReport = new FusionCharts({
		swfUrl:"${pageContext.request.contextPath}/images/js/FusionCharts/charts/Pie2D.swf", 
		      id: "rptCategoryMonthlyId", 
		      width: "600", 
		      height:"400", 
		      debugMode : false
		});
	cateMonthlyReport.setJSONUrl("getmothlydatascate.htm");
	cateMonthlyReport.render("rptCategoryMonthly");
});

function refreshMonthlyRpt(year){
	var chartReference = FusionCharts("rptMonthlyId");
	chartReference.setJSONUrl("getmonthlydatas.htm?year="+year);
	
	$.ajax({
		type:'POST',
		url:'${pageContext.request.contextPath}/getjournalminmonthforyear.htm',
		dataType:'json',
		data:{"year":year},
		success:function(data){
			if(data.month){
				refreshMainCategoryRpt(data.month);
			}
		},
		error:function(){
		}
	});
}

function refreshMainCategoryRpt(month){
	var chartReference = FusionCharts("rptCategoryMonthlyId");
	chartReference.setJSONUrl("getmothlydatascate.htm?month="+month);
	refreshMainCategoryDetailRpt(month, null);
}

function refreshMainCategoryDetailRpt(month, category){
	var obj =  $("#rptCategoryDetails");
	$(obj).empty();
	if(month && category){
		$.ajax({
			type:'POST',
			url:'${pageContext.request.contextPath}/getmothlycatedetails.htm',
			dataType:'json',
			data:{"month":month, "category":category},
			success:function(data){
				if(data.details){
					$(obj).append("<table></table>");
					$(obj).find('table').append("<thead><tr><td>日期</td><td>项目</td><td>单位</td><td>数量</td><td>单价</td><td>优惠价</td><td>金额</td></tr></thead>");
					if(obj){
						$(data.details).each(function(){
							op = "<tr>";
							op += "<td>"+$(this).attr('journalDate')+"</td>";
							op += "<td>"+$(this).attr('item')+"</td>";
							op += "<td>"+($(this).attr('uom')==null?"":$(this).attr('uom'))+"</td>";
							op += "<td class=\"td_number\">"+$(this).attr('quantity')+"</td>";
							op += "<td class=\"td_number\">"+$(this).attr('unitPrice')+"</td>";
							op += "<td class=\"td_number\">"+($(this).attr('discountPrice')==null?"":$(this).attr('discountPrice'))+"</td>";
							op += "<td class=\"td_number\">"+Number($(this).attr('amount')).toFixed(2)+"</td>";
							//op += "<td>"+($(this).attr('specification')==null?"":$(this).attr('specification'))+"</td>";
							//op += "<td>"+($(this).attr('brand')==null?"":$(this).attr('brand'))+"</td>";
							//op += "<td>"+($(this).attr('description')==null?"":$(this).attr('description'))+"</td>";
							op+="</tr>";
							$(obj).find('table').append(op);
						});
					}
				}
			},
			error:function(){
			}
		});
	}
}
</script>
</html>