<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>流程节点管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/flow/js/FlowNodeList.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/flow/pbsFlownode/">流程节点列表</a></li>
		<li class="active"><a
			href="${ctx}/flow/pbsFlownode/form?id=${pbsFlownode.id}">流程节点<shiro:hasPermission
					name="flow:pbsFlownode:edit">${not empty pbsFlownode.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="flow:pbsFlownode:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsFlownode"
		action="${ctx}/flow/pbsFlownode/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="hide sFlowid" topid="${pbsFlownode.sFlowid.id}"></div>
		<div class="hide sPrevnodeid" topid="${pbsFlownode.sPrevnodeid.id}"></div>
		<div class="hide sNextnodeid" topid="${pbsFlownode.sNextnodeid.id}"></div>

		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>流程节点定义名称：</label>
			<div class="controls">
				<form:input path="sName" htmlEscape="false" maxlength="200"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>流程模板：</label>
			<div class="controls">
				<form:select path="sFlowid" class="input-xlarge required">
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作节点的页面URL：</label>
			<div class="controls">
				<form:input path="sFlowurl" htmlEscape="false" maxlength="1000"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上一个节点的编号：</label>
			<div class="controls">
				<form:select path="sPrevnodeid" class="input-xlarge ">
				</form:select>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">下一个节点的编号：</label>
			<div class="controls">
				<form:select path="sNextnodeid" class="input-xlarge ">
				</form:select>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">是否人工操作：</label>
			<div class="controls">
				<form:select path="sManual" class="input-xlarge ">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('yes_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
            <label class="control-label"><font color="red">*&nbsp;</font>节点顺序：</label>
            <div class="controls">
                <form:input path="sSort" htmlEscape="false" maxlength="1000"
                    class="input-xlarge required" />
            </div>
        </div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>节点类型：</label>
			<div class="controls">
				<form:select path="sNodetype" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('flownode_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
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
			<shiro:hasPermission name="flow:pbsFlownode:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>