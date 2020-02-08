<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学员部门关系管理</title>
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
		<li><a href="${ctx}/person/pbsDepartmentbind/">学员部门关系列表</a></li>
		<li class="active"><a href="${ctx}/person/pbsDepartmentbind/form?id=${pbsDepartmentbind.id}">学员部门关系<shiro:hasPermission name="person:pbsDepartmentbind:edit">${not empty pbsDepartmentbind.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="person:pbsDepartmentbind:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pbsDepartmentbind" action="${ctx}/person/pbsDepartmentbind/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">党支部id：</label>
			<div class="controls">
				<form:input path="sDepartmentid" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学员的主键信息：</label>
			<div class="controls">
				<form:input path="sPartymemid" htmlEscape="false" maxlength="30" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">担任职务：</label>
			<div class="controls">
				<form:input path="sPost" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
	<%-- 	<div class="control-group">
			<label class="control-label">备用字段：</label>
			<div class="controls">
				<form:input path="sSpare01" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备用字段：</label>
			<div class="controls">
				<form:input path="sSpare02" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备用字段：</label>
			<div class="controls">
				<form:input path="sSpare03" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备用字段：</label>
			<div class="controls">
				<form:input path="sSpare04" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">任职开始日期：</label>
			<div class="controls">
				<input name="dtPosttime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pbsDepartmentbind.dtPosttime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">头衔：</label>
			<div class="controls">
				<form:input path="sPosttitle" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="person:pbsDepartmentbind:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>