<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>${title}</title>
<jsp:include page="../includes.jsp"/>
</head>
<body>
	<div>
		<c:if test="${!empty sessionScope.currentAccBook}">
			<form action="savejournal.htm" name="editJournalForm" method="POST">
				<input type="hidden" name="accBookId" value="${journal.accBookId}">
				<input type="hidden" name="journalId" value="${journal.journalId}">
				<fieldset>
				<table>
					<tr><td>总类<select name="topCategory">
						<option value="">&nbsp;</option>
						<c:if test="${!empty topCategoryList}">
							<c:forEach items="${topCategoryList}" var="topCategory">
								<option value="${topCategory.enumId}" <c:if test="${!empty journal.topCategory && journal.topCategory == topCategory.enumId}">selected='selected'</c:if>>${topCategory.enumName }</option>
							</c:forEach>
						</c:if>
					</select></td>
					<td>大类<select name="mainCategory">
						<option value="">&nbsp;</option>
						<c:if test="${!empty mainCategoryList}">
							<c:forEach items="${mainCategoryList}" var="mainCategory">
								<option value="${mainCategory.enumId }" <c:if test="${!empty journal.mainCategory && journal.mainCategory == mainCategory.enumId}">selected='selected'</c:if>>${mainCategory.enumName }</option>
							</c:forEach>
						</c:if>
						</select></td>
					<td>小类<select name="subCategory">
						<option value="">&nbsp;</option>
						<c:if test="${!empty subCategoryList}">
							<c:forEach items="${subCategoryList}" var="subCategory">
								<option value="${subCategory.enumId }" <c:if test="${!empty journal.subCategory && journal.subCategory == subCategory.enumId}">selected='selected'</c:if>>${subCategory.enumName }</option>
							</c:forEach>
						</c:if>
						</select></td>
					<td></td></tr>
					<tr><td>日期<input name="journalDatetime" value="${journal.journalDate}"><span class="necessary"> *</span></td><td>项目<input name="item" value="${journal.item}"><span class="necessary"> *</span></td><td>数量<input name="quantity" value="${journal.quantity}"><span class="necessary"> *</span></td></tr>
					<tr><td>单价<input name="unitPrice" value="${journal.unitPrice}"><span class="necessary"> *</span></td><td>优惠价<input name="discountPrice" value="${journal.discountPrice}"></td><td>金额<input name="amount" value="${journal.amount}"><span class="necessary"> *</span></td></tr>
					<tr><td>单位<input name="uom" value="${journal.uom}"></td><td>规格<input name="specification" value="${journal.specification}"></td><td>品牌<input name="brand" value="${journal.brand}"></td></tr>
					<tr><td>地点<input name="address" value="${journal.address}"></td><td>条码<input name="barcode" value="${journal.barcode}"></td><td></td></tr>	
					<tr><td colspan="3">描述<input name="description" value="${journal.description}"></td></tr>
				</table>
				</fieldset>
				<input type="submit" class="button" value="提交">
			</form>
		</c:if>
	</div>
</body>
<script>
$(function(){
	$(":text[name=journalDatetime]").datepicker({
		showOn: 'button',
        buttonImageOnly: false,
		dateFormat:'yy-mm-dd'
	});
	
	$("select[name=topCategory],select[name=mainCategory]").change(function(){
		var obj = $(this);
		var id = obj.val();
		var subObj;
		if("topCategory" == obj.attr("name")){
			subObj = $("select[name=mainCategory]");
			$("select[name=subCategory]").empty();
		}else if("mainCategory" == obj.attr("name")){
			subObj = $("select[name=subCategory]");
		}
		$(subObj).empty();
		$.ajax({
			type:'POST',
			url:'${pageContext.request.contextPath}/getCategories.htm',
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

});
</script>
</html>