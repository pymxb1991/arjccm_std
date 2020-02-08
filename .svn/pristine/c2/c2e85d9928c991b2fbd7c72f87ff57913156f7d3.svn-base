<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>进入节点条件信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/flow/js/FlowNodeItemList.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/flow/pbsFlowentercond/">进入节点条件信息列表</a></li>
		<li class="active"><a
			href="${ctx}/flow/pbsFlowentercond/form?id=${pbsFlowentercond.id}">进入节点条件信息<shiro:hasPermission
					name="flow:pbsFlowentercond:edit">${not empty pbsFlowentercond.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="flow:pbsFlowentercond:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsFlowentercond"
		action="${ctx}/flow/pbsFlowentercond/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="hide sFlowid" topid="${pbsFlowentercond.sFlowid.id}"></div>
        <div class="hide sFlownodeid" topid="${pbsFlowentercond.sFlownodeid.id}"></div>
		<div class="control-group">
			<label class="control-label">所属流程：</label>
			<div class="controls">
				<form:select path="sFlowid" class="input-xlarge ">
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属工节点：</label>
			<div class="controls">
				<form:select path="sFlownodeid" class="input-xlarge ">
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态类型：</label>
			<div class="controls">
				<form:input path="sCondtype" htmlEscape="false" maxlength="1000"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">顺序编号：</label>
			<div class="controls">
				<form:input path="sOrder" htmlEscape="false" maxlength="64"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作对象名称：</label>
			<div class="controls">
				<form:input path="sCollection" htmlEscape="false" maxlength="100"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作对象属性名：</label>
			<div class="controls">
				<form:input path="sProperty" htmlEscape="false" maxlength="100"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">条件符号：</label>
			<div class="controls">
				<form:input path="sCondsign" htmlEscape="false" maxlength="10"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">条件判断值：</label>
			<div class="controls">
				<form:input path="sCondval" htmlEscape="false" maxlength="100"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">与其他条件的连接关系：</label>
			<div class="controls">
				<form:input path="sConnection" htmlEscape="false" maxlength="20"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作内容记录：</label>
			<div class="controls">
				<form:input path="sOpearatecontent" htmlEscape="false"
					maxlength="1000" class="input-xlarge " />
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
			<shiro:hasPermission name="flow:pbsFlowentercond:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>