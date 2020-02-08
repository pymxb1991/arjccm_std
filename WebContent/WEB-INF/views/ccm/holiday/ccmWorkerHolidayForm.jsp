<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节假日管理管理</title>
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
	</script>
	<link href="/arjccm/static/bootstrap/2.3.1/css_input/input_Custom.css" type="text/css" rel="stylesheet">
</head>
<body>
	<ul class="nav nav-tabs">
		<%-- <li><a href="${ctx}/holiday/ccmWorkerHoliday/">节假日管理列表</a></li> --%>
		<li class="active"><a href="${ctx}/holiday/ccmWorkerHoliday/form?id=${ccmWorkerHoliday.id}">节假日管理<shiro:hasPermission name="holiday:ccmWorkerHoliday:edit">${not empty ccmWorkerHoliday.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="holiday:ccmWorkerHoliday:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="ccmWorkerHoliday" action="${ctx}/holiday/ccmWorkerHoliday/${not empty ccmWorkerHoliday.id?'update':'save'}" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group" style="padding-top: 8px">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>假日名称：</label>
			<div class="controls">
				<form:input path="holidayName" htmlEscape="false" maxlength="64" class="input-xlarge required"/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>开始时间：</label>
			<div class="controls">
				<input name="holidayBegin" id="holidayBegin" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${ccmWorkerHoliday.holidayBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({maxDate: '#F{$dp.$D(\'holidayEnd\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>结束时间：</label>
			<div class="controls">
				<input name="holidayEnd" id="holidayEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${ccmWorkerHoliday.holidayEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({minDate:'#F{$dp.$D(\'holidayBegin\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="holiday:ccmWorkerHoliday:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			${not empty ccmWorkerHoliday.id?'<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/> ':''}
		</div>
	</form:form>
</body>
</html>