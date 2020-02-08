<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>配置管理</title>
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
				$("td").css({
					"border-top" : "0px"
				});
				$("textarea").width("72%");
				var idVal = $("#id").val();
				if (idVal !== undefined && idVal != null && idVal != "") {
					$.getJSON("${ctx}/act/actUtConfig/getSelectList",{ tableId: $("#table").val() }, function(
							data) {
						$("[name='titleConfigs']").select2({
							placeholder: "请选择配置项...",
						    allowClear: true,
						    data: data
						 });
						/* var titleConfigs = document.getElementsByName("titleConfigs");
						for(var i=0; i < titleConfigs.length; i++) {
							titleConfigs[i].value = "";
						} */
					});
					var titleConfigs = document.getElementsByName("titleConfigs");
					var config = "${actUtConfig.titleConfig}";
					var configs = config.split("@@");
					if (configs != null && configs != "" && configs.length > 0) {
						for(var i=0; i < titleConfigs.length; i++) {
							titleConfigs[i].value = configs[i];
						}
					}
				}
				
			});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/act/actUtConfig/">配置列表</a></li>
		<li class="active"><a
			href="${ctx}/act/actUtConfig/form?id=${actUtConfig.id}">配置<shiro:hasPermission
					name="act:actUtConfig:edit">${not empty actUtConfig.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="act:actUtConfig:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="actUtConfig"
		action="${ctx}/act/actUtConfig/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="table-responesive">
			<table class="table">
				<tbody>
					<tr>
						<td>
							<div class="control-group">
								<label class="control-label">流程标题：</label>
								<div class="controls">
									<form:input path="title" htmlEscape="false" maxlength="512"
										class="input-xlarge required" />
									<span class="help-inline"><font color="red">*</font> </span>
								</div>
							</div>
						</td>
						<td>
							<div class="control-group">
								<label class="control-label">流程类别：</label>
								<div class="controls">
									<form:select path="processType" class="input-xlarge required">
										<form:option value="" label="" />
										<form:options items="${fns:getDictList('act_category')}"
											itemLabel="label" itemValue="value" htmlEscape="false"/>
									</form:select>
									<span class="help-inline"><font color="red">*</font> </span>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="control-group">
								<label class="control-label">流程定义id：</label>
								<div class="controls">
									<form:input path="processId" htmlEscape="false" maxlength="64"
										class="input-xlarge required" />
									<span class="help-inline"><font color="red">*</font> </span>
								</div>
							</div>
						</td>
						<td>
							<div class="control-group">
								<label class="control-label">流程名称：</label>
								<div class="controls">
									<form:input path="processName" htmlEscape="false"
										maxlength="256" class="input-xlarge required" />
									<span class="help-inline"><font color="red">*</font> </span>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="control-group">
								<label class="control-label">数据库表名：</label>
								<div class="controls">
									<form:select path="table" class="input-xlarge required">
										<form:options items="${tableList}" itemLabel="nameAndComments" itemValue="name" htmlEscape="false"/>
									</form:select>
									<span class="help-inline"><font color="red">*</font> </span>
								</div>
							</div>
						</td>
						<td>
							<div class="control-group">
								<label class="control-label">表单名：</label>
								<div class="controls">
									<form:input path="formKeyName" htmlEscape="false"
										maxlength="256" class="input-xlarge required" />
									<span class="help-inline"><font color="red">*</font> </span>
								</div>
							</div>
						</td>
					</tr>
					<c:if test="${not empty actUtConfig.id}">
					<tr>
						<td colspan="2">
							<label class="control-label">流程标题配置：</label>
								<div class="controls">
									<form:input type="hidden" path="titleConfigs" class="input-xlarge" style="margin-right: 20px;" />:
									<form:input type="hidden" path="titleConfigs" class="input-xlarge" style="margin-left: 20px;margin-right: 20px;"/>:
									<form:input type="hidden" path="titleConfigs" class="input-xlarge" style="margin-left: 20px;"/>
								</div>
						</td>
					</tr>
					</c:if>
					<tr style="display: none;">
						<td>
							<div class="control-group">
								<label class="control-label">扩展1：</label>
								<div class="controls">
									<form:input path="extend1" htmlEscape="false" maxlength="256"
										class="input-xlarge " />
								</div>
							</div>
						</td>
						<td>
							<div class="control-group">
								<label class="control-label">扩展2：</label>
								<div class="controls">
									<form:input path="extend2" htmlEscape="false" maxlength="256"
										class="input-xlarge " />
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="control-group">
								<label class="control-label">备注信息：</label>
								<div class="controls">
									<form:textarea path="remarks" htmlEscape="false" rows="4"
										maxlength="255" class="input-xxlarge " />
								</div>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="act:actUtConfig:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>