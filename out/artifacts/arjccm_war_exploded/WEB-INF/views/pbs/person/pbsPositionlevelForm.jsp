<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>职位信息管理</title>
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
		<li><a href="${ctx}/person/pbsPositionlevel/">职位信息列表</a></li>
		<li class="active"><a href="${ctx}/person/pbsPositionlevel/form?id=${pbsPositionlevel.id}">职位信息<shiro:hasPermission name="person:pbsPositionlevel:edit">${not empty pbsPositionlevel.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="person:pbsPositionlevel:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pbsPositionlevel" action="${ctx}/person/pbsPositionlevel/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>职位/头衔：</label>
			<div class="controls">
				<form:select path="sType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('positionlevel')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>对象名称：</label>
			<div class="controls">
				<form:input path="sName" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>对象编码：</label>
			<div class="controls">
				<form:input path="sWorkcode" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>对象等级：</label>
			<div class="controls">
				<form:input path="sLevel" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
			</div>
		</div>
	<%-- 	<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="person:pbsPositionlevel:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>