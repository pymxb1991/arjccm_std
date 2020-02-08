<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>活动定义管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
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
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/activity/pbsActivitytype/">活动定义列表</a></li>
		<li class="active"><a
			href="${ctx}/activity/pbsActivitytype/form?id=${pbsActivitytype.id}">活动定义<shiro:hasPermission
					name="activity:pbsActivitytype:edit">${not empty pbsActivitytype.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="activity:pbsActivitytype:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsActivitytype"
		action="${ctx}/activity/pbsActivitytype/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>活动名称：</label>
			<div class="controls">
				<form:input path="sName" htmlEscape="false" maxlength="200"
					class="input-xlarge required" />
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">工作流定义：</label>
			<div class="controls">
				<form:select path="sFlowdefinitionid" class="input-xlarge ">
					<form:option value="" label="" />
				</form:select>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>自定义分组标识：</label>
			<div class="controls">
				<form:select path="sGroup" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('actdefinitiontype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务类型定义描述信息：</label>
			<div class="controls">
				<form:textarea path="sDescription" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="activity:pbsActivitytype:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>