<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>经济运行数据-年管理</title>
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
		});
		function saveForm(){
			var years = $("#years").val();
			if(years.length!=0){
				years = years.substring(0,4);
				years = years+"-01";
				$("#years").val(years);
			}
			$("#inputForm").submit();
			
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/know/ccmEconomicsYear/">数据列表</a></li>
		<li class="active"><a href="${ctx}/know/ccmEconomicsYear/form?id=${ccmEconomicsYear.id}">数据<shiro:hasPermission name="know:ccmEconomicsYear:edit">${not empty ccmEconomicsYear.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="know:ccmEconomicsYear:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ccmEconomicsYear" action="${ctx}/know/ccmEconomicsYear/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">年：</label>
			<div class="controls">
				<input name="years" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required" id="years"
					value="<fmt:formatDate value="${ccmEconomicsYear.years}" pattern="yyyy"/>"
					onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务目标（亿元）：</label>
			<div class="controls">
				<form:input path="goal" htmlEscape="false" class="input-xlarge  number" maxlength="7"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工业总产值（亿元）：</label>
			<div class="controls">
				<form:input path="industrial" htmlEscape="false" class="input-xlarge  number" maxlength="7"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">固定资产投资（亿元）：</label>
			<div class="controls">
				<form:input path="invest" htmlEscape="false" class="input-xlarge  number" maxlength="7"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">招商引资（亿元）：</label>
			<div class="controls">
				<form:input path="imports" htmlEscape="false" class="input-xlarge  number" maxlength="7"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商业零售（亿元）：</label>
			<div class="controls">
				<form:input path="retail" htmlEscape="false" class="input-xlarge  number" maxlength="7"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">生产总值（亿元）：</label>
			<div class="controls">
				<form:input path="production" htmlEscape="false" class="input-xlarge  number" maxlength="7"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="know:ccmEconomicsYear:edit"><input id="btnSubmit" class="btn btn-primary" onclick="saveForm()" type="button" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>