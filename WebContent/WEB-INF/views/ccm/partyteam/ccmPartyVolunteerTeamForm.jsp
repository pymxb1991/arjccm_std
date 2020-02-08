<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>队伍管理管理</title>
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
		<%--<li class="active"><a href="${ctx}/partyteam/ccmPartyVolunteerTeam/form?id=${ccmPartyVolunteerTeam.id}">队伍管理<shiro:hasPermission name="partyteam:ccmPartyVolunteerTeam:edit">${not empty ccmPartyVolunteerTeam.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="partyteam:ccmPartyVolunteerTeam:edit">查看</shiro:lacksPermission></a></li>--%>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ccmPartyVolunteerTeam" action="${ctx}/partyteam/ccmPartyVolunteerTeam/save" method="post" class="form-horizontal">
	<div>
			<form:hidden path="id"/>
			<sys:message content="${message}"/>
		<table id="PartyPersonDetailTable" border="1px" style="border-color: #CCCCCC; border: 1px solid #CCCCCC; padding: 10px; width: 100%;">
			<tr>
				<td style="padding: 8px;border: 1px dashed #CCCCCC" colspan="2">
					<div>
						<label class="control-label">名称：</label>
						<div class="controls">
							<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required" style="width: 83%;"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td style="padding: 8px;border: 1px dashed #CCCCCC" >
					<div>
						<label class="control-label">选择社区：</label>
						<div class="controls">
							<sys:treeselect id="community" name="community.id" value="${ccmPartyVolunteerTeam.community.id}" labelName="community.name" labelValue="${ccmPartyVolunteerTeam.community.name}"
											title="区域" url="/sys/area/treeData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>

					</div>
				</td><td style="padding: 8px;border: 1px dashed #CCCCCC" >
					<div>
						<label class="control-label">上级队伍：</label>
						<div class="controls">
							<form:select path="superTeam" class="input-xlarge ">
								<form:option value="" label="请选择"/>
								<form:options items="${list}" itemLabel="name" itemValue="id" htmlEscape="false"/>
							</form:select>

						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td style="padding: 8px;border: 1px dashed #CCCCCC" >
					<div>
						<label class="control-label">成立日期：</label>
						<div class="controls">
							<input name="foundTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
								   value="<fmt:formatDate value="${ccmPartyVolunteerTeam.foundTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>

					</div>
				</td><td style="padding: 8px;border: 1px dashed #CCCCCC" >
					<div>
						<label class="control-label">负责人：</label>
						<div class="controls">
							<form:input path="user.id" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td style="padding: 8px;border: 1px dashed #CCCCCC" >
					<div>

						<label class="control-label">联系电话：</label>
						<div class="controls">
							<form:input path="telphone" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>

					</div>
				</td>	<td style="padding: 8px;border: 1px dashed #CCCCCC" >
					<div>
						<label class="control-label">通讯地址：</label>
						<div class="controls">
							<form:input path="address" htmlEscape="false" maxlength="200" class="input-xlarge required" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td style="padding: 8px;border: 1px dashed #CCCCCC" colspan="2">
					<div>
						<label class="control-label">服务事项：</label>
						<div class="controls">
							<form:textarea path="serverMatters" htmlEscape="false" rows="4" class="input-xxlarge required" style="width: 888px;"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td style="padding: 8px;border: 1px dashed #CCCCCC" colspan="2">
					<div>
						<label class="control-label">服务事迹：</label>
						<div class="controls">
							<form:textarea path="serverDeeds" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " style="width: 888px;"/>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
		<div class="form-actions">
			<shiro:hasPermission name="partyteam:ccmPartyVolunteerTeam:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn btn-danger" type="button" value="关 闭"/>
		</div>
	</form:form>
</body>
</html>