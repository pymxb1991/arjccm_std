<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>危化品车辆布控记录管理</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
<link href="${ctxStatic}/form/css/formTable.css" rel="stylesheet" />
<script type="text/javascript">
	$(document).ready(
			function() {

				//关闭弹框事件
				$('#btnCancel').click(function() {
					parent.layer.close(parent.layerIndex);
				})

				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										$("#btnSubmit").attr("disabled", true);
					loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#btnSubmit").removeAttr('disabled');
					$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
								});
			});
</script>
</head>
<body>
	<br />
	<form:form id="inputForm" modelAttribute="dangerousCarControl"
		action="${ctx}/dangerous/dangerousCarControl/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />

		<table>
			<tr>
				<td><label class="control-label">车牌号码：</label>
					<div class="controls">
						<form:input path="plateNumber" htmlEscape="false" maxlength="255"
							class="input-xlarge required" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div></td>
				<td><label class="control-label">布控范围：</label>
					<div class="controls">
						<form:select path="controllerScope" class="input-xlarge required">
							<form:option value="" label="请选择" />
							<form:options items="${fenceList}"
										  itemLabel="fenceName" itemValue="id" htmlEscape="false"/>
						</form:select>
<%--						<form:input path="controllerScope" htmlEscape="false"--%>
<%--							maxlength="255" class="input-xlarge required" />--%>
						<span class="help-inline"><font color="red">*</font> </span>
					</div></td>
			</tr>
			<tr>
				<td><label class="control-label">布控开始时间：</label>
					<div class="controls">
						<input name="startTime" type="text" readonly="readonly"
							maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${dangerousCarControl.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
					</div></td>
				<td><label class="control-label">布控结束时间：</label>
					<div class="controls">
						<input name="endTime" type="text" readonly="readonly"
							maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${dangerousCarControl.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
					</div></td>
			</tr>
			<tr>
				<td><label class="control-label">布控等级：</label>
					<div class="controls">
						<form:select path="controllerLevel" class="input-xlarge">
							<form:option value="" label="请选择" />
							<form:options items="${fns:getDictList('ccm_control_level')}"
								itemLabel="label" itemValue="value" htmlEscape="false"
								class="required" />
						</form:select>
					</div></td>
				<td><label class="control-label">布控类型：</label>
					<div class="controls">
						<form:select path="controllerType" class="input-xlarge">
							<form:option value="" label="请选择" />
							<form:options items="${fns:getDictList('fence_alarm_rule')}"
								itemLabel="label" itemValue="value" htmlEscape="false"
								class="required" />
						</form:select>

					</div></td>
			</tr>
			<tr>
				<td><label class="control-label">设备编号：</label>
					<div class="controls">
						<form:input path="deviceCode" htmlEscape="false" maxlength="255" class="input-xlarge required" />
					<span class="help-inline"><font color="red">*</font> </span>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2"><label class="control-label">布控原因：</label>
					<div class="controls">
						<form:textarea path="controllerReason" htmlEscape="false" rows="4"
							maxlength="255" class="input-xlarge " />
					</div></td>
			</tr>
		</table>

		<div class="form-actions">
			<shiro:hasPermission name="bayonet:ccmCarBayonet:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;
				<input id="btnCancel" class="btn btn-danger" type="button"
					value="关闭" />&nbsp;</shiro:hasPermission>
		</div>
	</form:form>
</body>
</html>