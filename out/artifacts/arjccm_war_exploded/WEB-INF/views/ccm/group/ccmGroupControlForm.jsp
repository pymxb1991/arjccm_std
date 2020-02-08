<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>自治群管理</title>
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
		<%-- <li><a href="${ctx}/group/ccmGroupControl/">自治群列表</a></li>
		<li class="active"><a href="${ctx}/group/ccmGroupControl/form?id=${ccmGroupControl.id}">自治群<shiro:hasPermission name="group:ccmGroupControl:edit">${not empty ccmGroupControl.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="group:ccmGroupControl:edit">查看</shiro:lacksPermission></a></li> --%>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ccmGroupControl" action="${ctx}/group/ccmGroupControl/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
<%-- 		<div class="control-group">
			<label class="control-label">社区：</label>
			<div class="controls">
				<form:input path="community" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">归属区域:</label>
			<div class="controls">
                <sys:treeselect id="area" name="community" value="${ccmGroupControl.community}" labelName="areaName" labelValue="${ccmGroupControl.areaName}"
					title="区域" url="/sys/area/treeData" allowClear="true" cssClass="required"/>
					<span class="help-inline"><font color="red" id="show1">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">群主：</label>
			<div class="controls">
				<form:input path="master" htmlEscape="false" maxlength="255" class="input-xlarge "/>
				<%-- <form:select path="master" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select> --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">成员数：</label>
			<div class="controls">
				<form:input path="number" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">需要审批：</label>
			<div class="controls">
				<form:radiobuttons path="isapprove" items="${fns:getDictList('group_isapprove')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">群性质：</label>
			<div class="controls">
				<form:select path="property" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('group_property')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">简介：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="group:ccmGroupControl:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn btn-danger" type="button" value="关 闭"/>
		</div>
	</form:form>
</body>
</html>