<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>${title}</title>
<jsp:include page="../header.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/images/js/FusionCharts/FusionCharts.js"></script>
<style type="text/css">
	#reports{overflow:hidden;}
	#reports div{float:left;margin:2px 5px;}
</style>
</head>
<body>
<div class="main_content">
<jsp:include page="../menubar.jsp"/>
	<div>
		<c:if test="${!empty sessionScope.currentAccBook}">
			<form action="addjournal.htm" name="addJournalForm" method="POST">
				<input type="hidden" name="accBookId" value="${sessionScope.currentAccBook.accBookId}">
				<fieldset>
				<table class="table table-striped table-bordered">
					<tr><td>总类<select name="topCategory" title="" data-toggle="tooltip" data-placement="bottom">
						<option value="">&nbsp;</option>
						<c:if test="${!empty topCategoryList}">
							<c:forEach items="${topCategoryList}" var="topCategory">
								<option value="${topCategory.enumId }">${topCategory.enumName }</option>
							</c:forEach>
						</c:if>
					</select></td><td>大类<select name="mainCategory" title="" data-toggle="tooltip" data-placement="bottom"></select></td><td>小类<select name="subCategory" title="" data-toggle="tooltip" data-placement="bottom"></select></td></tr>
					<tr><td>日期<input name="journalDatetime"><span class="necessary"> *</span></td><td>项目<input name="item"><span class="necessary"> *</span></td><td>数量<input name="quantity"><span class="necessary"> *</span></td></tr>
					<tr><td>单价<input name="unitPrice"><span class="necessary"> *</span></td><td>优惠价<input name="discountPrice"></td><td>金额<input name="amount"><span class="necessary"> *</span></td></tr>	
					<tr><td>单位<input name="uom"></td><td>规格<input name="specification"></td><td>品牌<input name="brand"></td></tr>
					<tr><td>地点<input name="address"></td><td>条码<input name="barcode"></td><td></td></tr>
					<tr><td colspan="3">描述<input name="description"></td></tr>
				</table>
				</fieldset>
				<button class="btn btn-primary btn-sm" type="submit"><span class="glyphicon glyphicon-floppy-disk"></span> 添加</button>
				<a href="${pageContext.request.contextPath}/account/journal.htm" class="btn btn-danger btn-sm" type="button"><span class="glyphicon glyphicon-arrow-left"></span> 取消</a>
			</form>
		</c:if>
	</div>
	<div id="reports">
		<div id="rptPriceHis">The History Price Report!</div>
	</div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
<script>
$(function(){
	$(":text[name=journalDatetime]").datetimepicker({
		format:'yyyy-mm-dd',
		minView:2,
		maxView:2,
		todayHighlight:true
	});
	//$(":text[name=journalDatetime]").datetimepicker("setDate",new Date());
	
	$("select[name=topCategory],select[name=mainCategory]").change(function(){
		var obj = $(this);
		var id = obj.val();
		var subObj;
		if("topCategory" == obj.attr("name")){
			subObj = $("select[name=mainCategory]");
			$("select[name=subCategory]").empty();
			$("select[name=subCategory]").attr('data-original-title','');
		}else if("mainCategory" == obj.attr("name")){
			subObj = $("select[name=subCategory]");
		}
		$(subObj).empty();
		$(subObj).attr('data-original-title','');
		$.ajax({
			type:'POST',
			url:'${pageContext.request.contextPath}/account/getCategories.htm',
			dataType:'json',
			data:{"parentEnumId":id},
			success:function(data){
				if(data.categoryList){
					var op = "";
					if(subObj){
						$(subObj).append("<option value=\"\">&nbsp;</option>");
						$(data.categoryList).each(function(){
								op = "<option value=\""+$(this).attr('enumId')+"\">";
								op += $(this).attr('enumName');
								op+="</option>";
							$(subObj).append(op);
						});
					}
				}
			},
			error:function(){
			}
		});
	});
	
	$("select[name=mainCategory],select[name=subCategory]").change(function(){
		var obj = $(this);
		var id = obj.val();
		$.ajax({
			type:'POST',
			url:'${pageContext.request.contextPath}/account/getCategories.htm',
			dataType:'json',
			data:{"enumId":id},
			success:function(data){
				if(data.categoryList){
					$(data.categoryList).each(function(){
						$(obj).attr('data-original-title',$(this).attr('description'));
						$(obj).tooltip();
					});
				}
			},
			error:function(){
				$(obj).attr('data-original-title','');
			}
		});
	});
	
	$(":text[name=quantity]").change(function(){
		var value = Number($.trim($(this).val())).toFixed(3);
		if(isNaN(value)||''==$.trim($(this).val())||value<=0){
			value= '';
			$(":text[name=amount]").val('');
		} else{
			if(Number($(":text[name=discountPrice]").val())>0){
				$(":text[name=amount]").val(Number(value * Number($(":text[name=discountPrice]").val())).toFixed(2));
			}else if(Number($(":text[name=unitPrice]").val())>0){
				$(":text[name=amount]").val(Number(value * Number($(":text[name=unitPrice]").val())).toFixed(2));
			}
		}
		$(this).val(value);
	});
	
	$(":text[name=discountPrice]").change(function(){
		var value = Number($.trim($(this).val())).toFixed(2);
		if(value > 0){
			$(this).val(value);
			if(Number($(":text[name=quantity]").val())>0){
				$(":text[name=amount]").val(Number(value * Number($(":text[name=quantity]").val())).toFixed(2));
			}
		} else {
			value= '';
			$(this).val(value);
			$(":text[name=unitPrice]").change();
		}
	});
	
	$(":text[name=unitPrice]").change(function(){
		var value = Number($.trim($(this).val())).toFixed(2);
		if(isNaN(value)||''==$.trim($(this).val())){
			value= '';
		} else{
			if(value<0){
				value= 0.0;
			}
			if(!Number($(":text[name=discountPrice]").val())>0){
				if(Number($(":text[name=quantity]").val())>0){
					$(":text[name=amount]").val(Number(value * Number($(":text[name=quantity]").val())).toFixed(2));
				}
			}
		}
		$(this).val(value);
	});
	
	$(":text[name=amount]").change(function(){
		var amount = Number($.trim($(this).val()));
		if(isNaN(amount) || amount == 0||amount<0){
			$(this).val('');
			return false;
		} else {
			if(Number($.trim($(":text[name=discountPrice]").val()))>0){
				$(":text[name=quantity]").val(Number(amount/Number($(":text[name=discountPrice]").val())).toFixed(3));
			}else if(Number($.trim($(":text[name=unitPrice]").val()))>0){
				$(":text[name=quantity]").val(Number(amount/Number($(":text[name=unitPrice]").val())).toFixed(3));
			}
			$(this).val(amount);
		}
	});

	var hisReport = new FusionCharts({
		swfUrl:"${pageContext.request.contextPath}/images/js/FusionCharts/charts/Line.swf", 
		      id: "rptPriceHisId", 
		      width: "400", 
		      height:"300", 
		      debugMode : false
		});
	hisReport.setJSONUrl("${pageContext.request.contextPath}/getpricehisdatas.htm");
	hisReport.render("rptPriceHis");
	
	$(":text[name=item]").change(function(){
		if($.trim($(this).val()) != ""){
			refreshPriceHisRpt($.trim($(this).val()));
		}else{
			refreshPriceHisRpt();
		}
	});
});

function refreshPriceHisRpt(item){
	var chartReference = FusionCharts("rptPriceHisId");
	chartReference.setJSONUrl("${pageContext.request.contextPath}/getpricehisdatas.htm?item="+item);
}
</script>
</html>