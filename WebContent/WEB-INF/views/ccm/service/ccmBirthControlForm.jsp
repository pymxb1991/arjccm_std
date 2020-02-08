<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>计生管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/service/ccmBirthControl/">数据列表</a></li>
		<li class="active"><a href="${ctx}/service/ccmBirthControl/form?id=${ccmBirthControl.id}">数据<shiro:hasPermission name="service:ccmBirthControl:edit">${not empty ccmBirthControl.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="service:ccmBirthControl:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ccmBirthControl" action="${ctx}/service/ccmBirthControl/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red" id="show1">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">日期：</label>
			<div class="controls">
				<input name="relDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${ccmBirthControl.relDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red" >*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:select path="type" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ccm_birth_control_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red" >*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容：</label>
			<div class="controls">
				<%-- <form:textarea path="content" htmlEscape="false" rows="4" class="input-xxlarge "/> --%>
				<form:textarea path="content" htmlEscape="false" rows="30" maxlength="280"  class="input-xxlarge "/>
				<sys:ckeditor uploadPath="/service/ccmBirthControl" replace="content" height="200"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">附件一：</label>
			<div class="controls">
				<%-- <form:input path="file" htmlEscape="false" maxlength="128" class="input-xlarge "/> --%>
				<form:hidden id="file" path="file"  htmlEscape="false" maxlength="255" class="input-xxlarge"/>
				<sys:ckfinder input="file" type="files" uploadPath="/service/ccmBirthControl" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">附件二：</label>
			<div class="controls">
				<%-- <form:input path="file" htmlEscape="false" maxlength="128" class="input-xlarge "/> --%>
				<form:hidden id="file2" path="file2"  htmlEscape="false" maxlength="255" class="input-xxlarge"/>
				<sys:ckfinder input="file2" type="files" uploadPath="/service/ccmBirthControl" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="service:ccmBirthControl:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>