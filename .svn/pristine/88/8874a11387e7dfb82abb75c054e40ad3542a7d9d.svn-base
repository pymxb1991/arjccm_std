<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>预处理事件管理</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
<link href="${ctxStatic}/form/css/formTable.css" rel="stylesheet" />
<style type="text/css">
</style>
<script type="text/javascript">
	$(document).ready(
			function() {

				//$("#name").focus();
				$("#EventDetailBtn").click(function() {
					$("#EventDetailTable").toggle();
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
	<link href="/arjccm/static/bootstrap/2.3.1/css_input/input_Custom.css" type="text/css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<form:form id="inputForm" modelAttribute="eventA"
			style="width:48%;float:left"
			action="${ctx}/preview/ccmEventIncidentPreview/check/delete?id=${eventA.id}"
			method="post" class="form-horizontal">
			<form:hidden path="id" />
			<sys:message content="${message}" />

			<div class=table-responsive">
				<table class="table">
					<tr>
						<td style=""><label class="control-label">事件名称：</label>
							<div class="controls">
								<form:input path="caseName" htmlEscape="false" maxlength="100"
									class="input-xlarge required" />

							</div></td>

					</tr>
					<tr>
						<td><label class="control-label">事件内容：</label>
							<div class="controls">
								<form:input path="caseCondition" htmlEscape="false"
									class="input-xlarge " />

							</div></td>

					</tr>
					<%-- 					<tr>
						<td><label class="control-label">所属网格：</label>
							<div class="controls">
								<sys:treeselect id="administrativeDivision"
									name="ccmBasePlace.administrativeDivision"
									value="${ccmHouseBuildmanage.area.id}"
									labelName="ccmBasePlace.administrativeDivision"
									labelValue="${ccmHouseBuildmanage.area.name}" title="网格"
									url="/tree/ccmTree/treeDataArea?type=7&areaid="
									allowClear="true" notAllowSelectParent="true" /></td>


					</tr> --%>
					<tr>
						<td style=""><label class="control-label">事发地点：</label>
							<div class="controls">
								<form:input path="happenPlace" htmlEscape="false"
									maxlength="100" class="input-xlarge required" />

							</div></td>


					</tr>

					<tr>
						<td><label class="control-label">上报人：</label>
							<div class="controls">
								<form:input path="reportPerson" htmlEscape="false"
									maxlength="15" class="input-xlarge required" />
							</div></td>
					</tr>
					<tr>
						<td><label class="control-label">上报人联系电话：</label>
							<div class="controls">
								<form:input path="reportPersonPhone" htmlEscape="false"
									maxlength="11" class="input-xlarge " />
							</div></td>

					</tr>
					<tr>
						<td><label class="control-label">事件时间：</label>
							<div class="controls">
								<input name="happenDate" type="text" readonly="readonly"
									maxlength="20" class="input-medium Wdate required"
									value="<fmt:formatDate value="${eventA.happenDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
							</div></td>
					</tr>
					<tr>
						<td style=""><label class="control-label">事件类别：</label>
							<div class="controls">
								<form:select path="eventKind" class="input-xlarge">
									<form:option value="" label="" />
									<form:options items="${fns:getDictList('ccm_event_sort')}"
										itemLabel="label" itemValue="value" htmlEscape="false" />
								</form:select>

							</div></td>
					</tr>
					<td style=""><label class="control-label">事件规模：</label>
						<div class="controls">
							<form:select path="caseScope" class="input-xlarge">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('ccm_event_scope')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>

						</div></td>

					</tr>
					</div>
					</td>
					</tr>
				</table>
			</div>
			<div class="form-actions">
				<shiro:hasPermission name="preview:ccmEventIncidentPreview:edit">
					<input id="btnSubmit" class="btn btn-primary" type="submit"
						value="移除" />&nbsp;
				</shiro:hasPermission>
			</div>
		</form:form>
		<form:form id="inputForm" modelAttribute="eventB"
			style="width:48%;float:left"
			action="${ctx}/preview/ccmEventIncidentPreview/check/delete?id=${eventB.id}"
			method="post" class="form-horizontal">
			<form:hidden path="id" />
			<sys:message content="${message}" />

			<div class=table-responsive">
				<table class="table">
					<tr>
						<td style=""><label class="control-label">事件名称：</label>
							<div class="controls">
								<form:input path="caseName" htmlEscape="false" maxlength="100"
									class="input-xlarge required" />

							</div></td>

					</tr>
					<tr>
						<td style=""><label class="control-label">事件内容：</label>
							<div class="controls">
								<form:input path="caseCondition" htmlEscape="false"
									maxlength="100" class="input-xlarge required" />

							</div></td>

					</tr>
					<%-- 					<tr>
						<td><label class="control-label">所属网格：</label>
							<div class="controls">
								<sys:treeselect id="administrativeDivision"
									name="ccmBasePlace.administrativeDivision"
									value="${ccmHouseBuildmanage.area.id}"
									labelName="ccmBasePlace.administrativeDivision"
									labelValue="${ccmHouseBuildmanage.area.name}" title="网格"
									url="/tree/ccmTree/treeDataArea?type=7&areaid="
									allowClear="true" notAllowSelectParent="true" /></td>


					</tr> --%>
					<tr>
						<td style=""><label class="control-label">事发地点：</label>
							<div class="controls">
								<form:input path="happenPlace" htmlEscape="false"
									maxlength="100" class="input-xlarge required" />

							</div></td>


					</tr>

					<tr>
						<td><label class="control-label">上报人：</label>
							<div class="controls">
								<form:input path="reportPerson" htmlEscape="false"
									maxlength="15" class="input-xlarge required" />
							</div></td>
					</tr>
					<tr>
						<td><label class="control-label">上报人联系电话：</label>
							<div class="controls">
								<form:input path="reportPersonPhone" htmlEscape="false"
									maxlength="11" class="input-xlarge " />
							</div></td>

					</tr>
					<tr>
						<td><label class="control-label">事件时间：</label>
							<div class="controls">
								<input name="happenDate" type="text" readonly="readonly"
									maxlength="20" class="input-medium Wdate required"
									value="<fmt:formatDate value="${eventB.happenDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
							</div></td>
					</tr>
					<tr>
						<td style=""><label class="control-label">事件类别：</label>
							<div class="controls">
								<form:select path="eventKind" class="input-xlarge">
									<form:option value="" label="" />
									<form:options items="${fns:getDictList('ccm_event_sort')}"
										itemLabel="label" itemValue="value" htmlEscape="false" />
								</form:select>

							</div></td>
					</tr>
					<td style=""><label class="control-label">事件规模：</label>
						<div class="controls">
							<form:select path="caseScope" class="input-xlarge">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('ccm_event_scope')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>

						</div></td>

					</tr>
					</div>
					</td>
					</tr>
				</table>
			</div>
			<div class="form-actions">
				<shiro:hasPermission name="preview:ccmEventIncidentPreview:edit">
					<input id="btnSubmit" class="btn btn-primary" type="submit"
						value="移除" />&nbsp;</shiro:hasPermission>
			</div>
		</form:form>
	</div>

</body>
</html>