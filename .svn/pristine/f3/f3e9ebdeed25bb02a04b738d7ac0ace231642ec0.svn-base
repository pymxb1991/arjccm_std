<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>车辆布控记录管理</title>
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
										var beginAlarmDate = $("#startTime").val();
										var endAlarmDate = $("#endTime").val();
									    beginDateNew = Date.parse(new Date(beginAlarmDate.replace(/-/g, "/")));
									    endDateNew = Date.parse(new Date(endAlarmDate.replace(/-/g, "/")));
									    var time = endDateNew - beginDateNew;
									    if (time < 0) {
									    	$.jBox.tip('开始时间必须小于结束时间!');
									        return;
									    } 
									    
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
	<form:form id="inputForm" modelAttribute="ccmCarControl"
		action="${ctx}/car/ccmCarControl/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<table>
			<tr>
				<td><label class="control-label">车牌号码：</label>
					<div class="controls">
						<form:input path="plateNumber" htmlEscape="false" maxlength="64"
							class="input-xlarge required" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div></td>
				<td><label class="control-label">布控类型：</label>
					<div class="controls">
						<form:radiobuttons path="controlType"
							items="${fns:getDictList('ccm_car_controller')}"
							itemLabel="label" itemValue="value" htmlEscape="false"
							class="required" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div></td>
			</tr>
			<tr>
				<td><label class="control-label">布控开始时间：</label>
					<div class="controls">
						<input name="startTime"  id="startTime" type="text" readonly="readonly"
							maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmCarControl.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
					</div></td>
				<td><label class="control-label">布控结束时间：</label>
					<div class="controls">
						<input name="endTime" id="endTime" type="text" readonly="readonly"
							maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmCarControl.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
					</div></td>
			</tr>
			<tr>
				<td><label class="control-label">车牌颜色：</label>
					<div class="controls">
						<form:radiobuttons path="plateColor"
							items="${fns:getDictList('ccm_plate_color')}" itemLabel="label"
							itemValue="value" htmlEscape="false" class="required" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div></td>
				<td><label class="control-label">布控原因：</label>
					<div class="controls">
						<form:radiobuttons path="controlReason"
							items="${fns:getDictList('ccm_controller_reason')}"
							itemLabel="label" itemValue="value" htmlEscape="false"
							class="required" />
						<span class="help-inline"><font color="red">*</font> </span>
					</div></td>
			</tr>
			<tr>
				<td><label class="control-label">联系人电话：</label>
					<div class="controls">
						<form:input path="contactNumber" htmlEscape="false" maxlength="11"
							class="input-xlarge phone" />
					</div></td>
				<td><label class="control-label">个人可见：</label>
					<div class="controls">
						<form:radiobuttons path="canSee"
							items="${fns:getDictList('yes_no')}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</div></td>
			</tr>
			<tr>
				<td><label class="control-label">报警弹框：</label>
					<div class="controls">
						<form:radiobuttons path="canDialogs"
							items="${fns:getDictList('yes_no')}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</div></td>
				<td><label class="control-label">报警声音：</label>
					<div class="controls">
						<form:radiobuttons path="canVoice"
							items="${fns:getDictList('yes_no')}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</div></td>
			</tr>

			<tr>
				<td colspan="2"><label class="control-label">布控描述：</label>
					<div class="controls">
						<form:textarea path="remarks" htmlEscape="false" rows="4"
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