<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>请求处理管理</title>
<meta name="decorator" content="default" />
<script charset="utf-8" type="text/javascript"
	src="${ctxStatic}/ccm/validator/validatorBaseForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/event/ccmEventRequestdeal/">请求处理列表</a></li>
		<li class="active"><a
			href="${ctx}/event/ccmEventRequestdeal/form?id=${ccmEventRequestdeal.id}">请求处理<shiro:hasPermission
					name="event:ccmEventRequestdeal:edit">${not empty ccmEventRequestdeal.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="event:ccmEventRequestdeal:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="ccmEventRequestdeal"
		action="${ctx}/event/ccmEventRequestdeal/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="eventRequestId" />
		<sys:message content="${message}" />
		<table class="table table-bordered table-hover" >
		  
			<div class="control-group">
				<label class="control-label">请求名称：</label>
				<div class="controls">
					<form:input path="caseName" htmlEscape="false" maxlength="64"
                    class="input-xlarge "  readonly="true"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">处理单位：</label>
				<div class="controls">
					<form:input path="dealUnit" htmlEscape="false" maxlength="200"
                    class="input-xlarge " />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">请求负责人：</label>
				<div class="controls">
					<form:input path="eventPrincipal" htmlEscape="false" maxlength="20"
                    class="input-xlarge required" />
                    <span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">个人电话：</label>
				<div class="controls">
					<form:input path="telPerson" htmlEscape="false" maxlength="30"
                    class="input-xlarge " />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">单位电话：</label>
				<div class="controls">
					<form:input path="telCom" htmlEscape="false" maxlength="30"
                    class="input-xlarge " />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">请求说明：</label>
				<div class="controls">
					<form:textarea path="remarks" htmlEscape="false" rows="4"
                    maxlength="2000" class="input-xxlarge " />
				</div>
			</div>
        </table>
        
		<div class="form-actions">
			<shiro:hasPermission name="event:ccmEventRequestdeal:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>