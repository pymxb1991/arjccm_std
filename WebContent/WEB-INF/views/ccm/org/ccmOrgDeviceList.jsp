<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构设置绑定管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
	<link href="${ctxStatic}/form/css/formTable.css" rel="stylesheet" />
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			if("${ccmDevice.deviceList}"!=null&&"${ccmDevice.deviceList}"!=""){
debugger
				var deviceList = "${deviceIdList}".split(",");
				$("#deviceSelect").val(deviceList).select2();
			}
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
</head>
<body>
<ul class="nav nav-tabs">
	<li><a href="${ctx}/org/ccmOrgDevice/">视频列表</a></li>
</ul>
<form:form id="inputForm" modelAttribute="ccmOrgDevice" action="${ctx}/org/ccmOrgDevice/save" method="post" class="form-horizontal">
	<form:hidden path="orgId"/>
	<sys:message content="${message}"/>
	<div class="control-group" style="padding-top: 8px">
		<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>监控设备：</label>
		<div class="controls">
			<select data-placeholder="选择一个或多个监控设备" style="width: 90%;" name="deviceSelect" id="deviceSelect" class="chosen-select required" multiple tabindex="4">
				<option value=""></option>
				<c:forEach items="${ccmDevice.deviceList}" var="video">
					<option value="${video.id}">${video.name}</option>
				</c:forEach>
			</select>

		</div>
	</div>

	<div class="form-actions">
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
<%--		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
	</div>
</form:form>
</body>
</html>