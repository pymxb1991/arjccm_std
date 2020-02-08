<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>值班批量添加</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					$("#btnSubmit").attr("disabled", true);
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#btnSubmit").removeAttr('disabled');
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			$("td").css({"padding":"15px 8px"});
			$("td").css({"border-bottom":"0px dashed #CCCCCC"});
		});
	</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="ccmWorkBeonduty" action="${ctx}/work/ccmWorkBeonduty/copySave" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table style="padding: 10px; width: 90%;">
		<tr>
			<td class="pad" width="30%"><span class="help-inline"><font color="red">*</font> </span>选择被复制时间：</td>
			<td class="pad">
				<input name="beginMonths" type="text" readonly="readonly" maxlength="20" class="Wdate required" style="width: 150px"
					value="<fmt:formatDate value="${ccmWorkBeonduty.beginMonths}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>

			</td>
		</tr>
		<tr>
			<td class="pad"><span class="help-inline"><font color="red">*</font> </span>选择复制添加到：</td>
			<td class="pad">
				<input name="endMonths" type="text" readonly="readonly" maxlength="20" class="Wdate required" style="width: 150px"
					value="<fmt:formatDate value="${ccmWorkBeonduty.endMonths}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>

			</td>
		</tr>
		<tr>
			<td class="pad"></td>
			<td class="pad" colspan="2">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="批量添加"/>
			</td>
		</tr>
		</table>
	</form:form>
</body>
</html>