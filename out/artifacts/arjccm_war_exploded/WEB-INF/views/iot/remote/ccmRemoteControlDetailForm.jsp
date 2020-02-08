<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>远程控制管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			//关闭弹框事件
			$('#btnCancel').click(function() {
				parent.layer.close(parent.layerIndex);
			})
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
</head>
<body>
	<%--注释掉原有的页头 --%>	
	<%-- <ul class="nav nav-tabs">
		<li><a href="${ctx}/remote/ccmRemoteControl/">远程控制列表</a></li>
		<li class="active"><a href="${ctx}/remote/ccmRemoteControl/form?id=${ccmRemoteControl.id}">远程控制<shiro:hasPermission name="remote:ccmRemoteControl:edit">${not empty ccmRemoteControl.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="remote:ccmRemoteControl:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/> --%>
	<form:form id="inputForm" modelAttribute="ccmRemoteControl" action="${ctx}/remote/ccmRemoteControl/save" method="post" class="form-horizontal" style="margin-top:20px;">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table border="1px" style="border-color: #CCCCCC; border: 1px solid #CCCCCC; padding: 10px; width: 100%;">
			<tr>
				<td>
					<div class="control-group" style="border:none;margin-top:15px;">
						<label class="control-label">名称：</label>
						<div class="controls">
							<form:input path="equipmentName" htmlEscape="false" maxlength="64" class="input-xlarge " readonly="true"/>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group" style="border:none;margin-top:15px;">
						<label class="control-label">编号：</label>
						<div class="controls">
							<form:input path="equipmentNum" htmlEscape="false" maxlength="64" class="input-xlarge " readonly="true"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group" style="border:none;margin-top:15px;">
						<label class="control-label">定位频率：</label>
						<div class="controls">
							<form:input path="equipmentFrequency" htmlEscape="false" maxlength="64" class="input-xlarge " readonly="true"/>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group" style="border:none;margin-top:15px;">
						<label class="control-label">是否关机：</label>
						<div class="controls">
							<form:radiobuttons path="equipmentState" items="${fns:getDictList('ccm_remote_equipment_state')}" itemLabel="label" itemValue="value" htmlEscape="false" class="" disabled="true"/>
						</div>
					</div>
				</td>
			</tr>
		</table>
		<div class="form-actions">
			<input id="btnCancel" class="btn btn-danger" type="button" value="关 闭"/>
		</div>
	</form:form>
</body>
</html>