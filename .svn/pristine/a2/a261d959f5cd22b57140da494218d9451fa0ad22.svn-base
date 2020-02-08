<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>巡逻点位管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/ccm/patrol/js/ccmPatrolPointForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/patrol/ccmPatrolPoint/">数据列表</a></li>
		<li class="active"><a
			href="${ctx}/patrol/ccmPatrolPoint/form?id=${ccmPatrolPoint.id}">数据<shiro:hasPermission
					name="patrol:ccmPatrolPoint:edit">${not empty ccmPatrolPoint.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="patrol:ccmPatrolPoint:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="ccmPatrolPoint"
		action="${ctx}/patrol/ccmPatrolPoint/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group hide">
			<label class="control-label">坐标：</label>
			<div class="controls">
				<form:input path="areaPoint" htmlEscape="false" maxlength="64"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">属性：</label>
			<div class="controls">
				<form:input path="property" htmlEscape="false" maxlength="11"
					class="input-xlarge " />
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> --%>

		<div class="control-group addPoint"></div>
		<div class="form-actions">
			<shiro:hasPermission name="patrol:ccmPatrolPoint:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>