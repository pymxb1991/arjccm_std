<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>志愿者管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			//关闭弹框事件
			$('#btnCancel').click(function() {
				parent.layer.close(parent.layerIndex);
			})
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
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ccmPartyVolunteer" action="${ctx}/partyvoluteer/ccmPartyVolunteer/save" method="post" class="form-horizontal">
	<div>
			<form:hidden path="id"/>
			<sys:message content="${message}"/>
		<table id="PartyPersonDetailTable" border="1px" style="border-color: #CCCCCC; border: 1px solid #CCCCCC; padding: 10px; width: 100%;">
			<tr>
				<td style="padding: 8px;border: 1px dashed #CCCCCC" >
					<div>
						<label class="control-label">名称：</label>
						<div class="controls">
							<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td style="padding: 8px;border: 1px dashed #CCCCCC" >
					<div>
						<label class="control-label">性别：</label>
						<div class="controls">
							<form:select path="sex" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
			</tr><tr>
				<td style="padding: 8px;border: 1px dashed #CCCCCC" >
					<div>
						<label class="control-label">选择社区：</label>
						<div class="controls">
							<sys:treeselect id="community" name="community.id" value="${ccmPartyVolunteer.community.id}" labelName="community.name" labelValue="${ccmPartyVolunteer.community.name}"
											title="区域" url="/tree/ccmTree/treeDataArea?type=6" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
							<span class="help-inline"><font color="red">*</font> </span>

						</div>

					</div>
				</td>
				<td style="padding: 8px;border: 1px dashed #CCCCCC" >
					<div>
						<label class="control-label">所属队伍：</label>
						<div class="controls">
							<form:select path="beloneTeam" class="input-xlarge ">
								<form:option value="" label="请选择"/>
								<form:options items="${list}" itemLabel="name" itemValue="id" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
			</tr><tr>
				<td style="padding: 8px;border: 1px dashed #CCCCCC" >
					<div>
						<label class="control-label">学历：</label>
						<div class="controls">
							<form:select path="educationalBackground" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('education_background')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>

					</div>
				</td>
				<td style="padding: 8px;border: 1px dashed #CCCCCC" >
					<div>
						<label class="control-label">年龄：</label>
						<div class="controls">
							<form:input path="age" htmlEscape="false" maxlength="10" class="input-xlarge "/>
						</div>
					</div>
				</td>
			</tr><tr>
				<td style="padding: 8px;border: 1px dashed #CCCCCC" >
					<div>
						<label class="control-label">民族：</label>
						<div class="controls">
							<form:select path="nation" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('sys_volk')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>

					</div>
				</td>
				<td style="padding: 8px;border: 1px dashed #CCCCCC" >
					<div>
						<label class="control-label">健康状况：</label>
						<div class="controls">
							<form:select path="healthCondition" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('ccm_phy_cdt')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
			</tr><tr>
				<td style="padding: 8px;border: 1px dashed #CCCCCC" colspan="2">
					<div>
						<label class="control-label">现任职务：</label>
						<div class="controls">
							<form:input path="presentAssignment" htmlEscape="false" maxlength="100" class="input-xlarge "/>
						</div>

					</div>
				</td>
			</tr><tr>
				<td style="padding: 8px;border: 1px dashed #CCCCCC" colspan="2">
					<div>
						<label class="control-label">服务意向：</label>
						<div class="controls">
							<form:textarea path="serviceIntention" htmlEscape="false" rows="4" class="input-xxlarge " style="width: 560px"/>
						</div>
					</div>
				</td>
			</tr><tr>
				<td style="padding: 8px;border: 1px dashed #CCCCCC" colspan="2">
					<div>
						<label class="control-label">简介：</label>
						<div class="controls">
							<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " style="width: 560px"/>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
		<div class="form-actions">
			<shiro:hasPermission name="partyvoluteer:ccmPartyVolunteer:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn btn-danger" type="button" value="关 闭"/>
		</div>
	</form:form>
</body>
</html>