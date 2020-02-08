<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>门户方案管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
				//$("#name").focus();
				$('#btnSubmit').click(function() {
					$('#inputForm').submit();
				});
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
<%--引入文本框外部样式--%>
<link href="/arjccm/static/bootstrap/2.3.1/css_input/input_Custom.css" type="text/css" rel="stylesheet">
</head>
<body>
	<form:form target="_parent" id="inputForm" cssStyle="padding-top: 10px"
		modelAttribute="plmPortalPlan" action="${ctx}/home/plmPortalPlan/save"
		method="post" class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group head_Space">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>方案名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="256"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">说明：</label>
			<div class="controls">
				<form:input path="introduce" htmlEscape="false" maxlength="256"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">系统类别：</label>
			<div class="controls">
				<form:select path="extend1" class="input-xlarge">
					<form:option value="" label="请选择" />
					<form:options items="${fns:getDictList('index_sys_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false"
						maxlength="2" class="input-medium" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>每行列数：</label>
			<div class="controls">

				<form:select path="type" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('plan_longItude')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="255" class="input-xlarge " cssStyle="width: 282px" />
			</div>
		</div>

		<div class="form-actions">
			<a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i
				class="icon-ok"></i>保存</a>
		</div>
	</form:form>
</body>
</html>