<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>流程定义信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/flow/js/FlowTypeList.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/flow/pbsFlowdefinition/">流程定义信息列表</a></li>
		<li class="active"><a
			href="${ctx}/flow/pbsFlowdefinition/form?id=${pbsFlowdefinition.id}">流程定义信息<shiro:hasPermission
					name="flow:pbsFlowdefinition:edit">${not empty pbsFlowdefinition.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="flow:pbsFlowdefinition:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsFlowdefinition"
		action="${ctx}/flow/pbsFlowdefinition/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="hide sTypeid" topid="${pbsFlowdefinition.sTypeid.id}"></div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>流程定义名称：</label>
			<div class="controls">
				<form:input path="sName" htmlEscape="false" maxlength="200"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>流程类别编号：</label>
			<div class="controls">
				<form:select path="sTypeid" class="input-xlarge required">
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>启用状态：</label>
			<div class="controls">
				<form:select path="sStartstat" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('yes_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">锁定状态：</label>
			<div class="controls">
				<form:select path="sLockstat" class="input-xlarge ">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('yes_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">撤销状态：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:select path="sRevoke" class="input-xlarge "> --%>
<%-- 					<form:option value="" label="" /> --%>
<%-- 					<form:options items="${fns:getDictList('yes_no')}" --%>
<%-- 						itemLabel="label" itemValue="value" htmlEscape="false" /> --%>
<%-- 				</form:select> --%>
<!-- 			</div> -->
<!-- 		</div> -->
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>设计编号：</label>
			<div class="controls">
				<form:select path="sTypecode" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('flowCodeType')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述信息：</label>
			<div class="controls">
				<form:textarea path="sDescription" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="flow:pbsFlowdefinition:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>