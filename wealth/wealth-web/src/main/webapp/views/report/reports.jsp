<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>${result.title}</title>
<jsp:include page="../includes.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/images/js/FusionCharts/FusionCharts.js"></script>
<style type="text/css">
	#reports{overflow:hidden;}
	#reports div{float:left;margin:2px 5px;}
</style>
</head>
<body>
<div style="text-align:left;width:1024px;">
	当前账簿：${sessionScope.currentAccBook.accBookName}<a href="index.htm" class="button icon arrowleft"><span>返回</span></a>
	<div id="reports">
		<div id="rptYearly">The Accounting Report Yearly!</div>
		<div id="rptMonthly">The Accounting Report Monthly!</div>
		<div id="rptCategoryMonthly">The Accounting Report Monthly By Category!</div>
	</div>
</div>
</body>
<script>
$(function(){
	var yearlyReport = new FusionCharts({
		swfUrl:"${pageContext.request.contextPath}/images/js/FusionCharts/charts/Column2D.swf", 
		      id: "rptYearlyId", 
		      width: "400", 
		      height:"300", 
		      debugMode : false
		});
	yearlyReport.setJSONUrl("getyearlydatas.htm");
	yearlyReport.render("rptYearly");
	
	var monthlyReport = new FusionCharts({
		swfUrl:"${pageContext.request.contextPath}/images/js/FusionCharts/charts/Column2D.swf", 
		      id: "rptMonthlyId", 
		      width: "400", 
		      height:"300", 
		      debugMode : false
		});
	monthlyReport.setJSONUrl("getmonthlydatas.htm");
	monthlyReport.render("rptMonthly");
	
	var cateMonthlyReport = new FusionCharts({
		swfUrl:"${pageContext.request.contextPath}/images/js/FusionCharts/charts/Pie2D.swf", 
		      id: "rptCategoryMonthlyId", 
		      width: "600", 
		      height:"350", 
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
}
</script>
</html>