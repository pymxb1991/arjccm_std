<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>节点操作后管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/flow/js/FlowNodeItemList.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/flow/pbsFlowsetstat/">节点操作后列表</a></li>
		<li class="active"><a
			href="${ctx}/flow/pbsFlowsetstat/form?id=${pbsFlowsetstat.id}">节点操作后<shiro:hasPermission
					name="flow:pbsFlowsetstat:edit">${not empty pbsFlowsetstat.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="flow:pbsFlowsetstat:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsFlowsetstat"
		action="${ctx}/flow/pbsFlowsetstat/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="hide sFlowid" topid="${pbsFlowsetstat.sFlowid.id}"></div>
        <div class="hide sFlownodeid" topid="${pbsFlowsetstat.sFlownodeid.id}"></div>
		<div class="control-group">
            <label class="control-label">所属流程：</label>
            <div class="controls">
                <form:select path="sFlowid" class="input-xlarge ">
                </form:select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">所属流程节点：</label>
            <div class="controls">
                <form:select path="sFlownodeid" class="input-xlarge ">
                </form:select>
            </div>
        </div>
		<div class="control-group">
			<label class="control-label">操作类型：</label>
			<div class="controls">
				<form:input path="sOpeatertype" htmlEscape="false" maxlength="64"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作名称：</label>
			<div class="controls">
				<form:input path="sName" htmlEscape="false" maxlength="100"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作对象：</label>
			<div class="controls">
				<form:input path="sCollection" htmlEscape="false" maxlength="100"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作对象属性名称：</label>
			<div class="controls">
				<form:input path="sProperty" htmlEscape="false" maxlength="100"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作设置值：</label>
			<div class="controls">
				<form:input path="sSetval" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">流程节点的描述信息：</label>
			<div class="controls">
				<form:input path="sDescription" htmlEscape="false" maxlength="1000"
					class="input-xlarge " />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="flow:pbsFlowsetstat:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>