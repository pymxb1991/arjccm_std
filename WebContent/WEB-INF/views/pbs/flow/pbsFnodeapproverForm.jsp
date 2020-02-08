<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>流程审核人信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/flow/js/FlowNodeItemList.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/flow/pbsFnodeapprover/">流程审核人信息列表</a></li>
		<li class="active"><a
			href="${ctx}/flow/pbsFnodeapprover/form?id=${pbsFnodeapprover.id}">流程审核人信息<shiro:hasPermission
					name="flow:pbsFnodeapprover:edit">${not empty pbsFnodeapprover.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="flow:pbsFnodeapprover:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsFnodeapprover"
		action="${ctx}/flow/pbsFnodeapprover/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="hide sFlowid" topid="${pbsFnodeapprover.sFlowid.id}"></div>
        <div class="hide sFlownodeid" topid="${pbsFnodeapprover.sFlownodeid.id}"></div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>所属流程：</label>
			<div class="controls">
				    <form:select path="sFlowid" class="input-xlarge required">
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>所属节点：</label>
			<div class="controls">
				<form:select path="sFlownodeid" class="input-xlarge ">
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>部门：</label>
			<div class="controls ">
				<sys:treeselect id="sDepartmentid" name="sDepartmentid"
					value="${pbsFnodeapprover.sDepartmentid.id}" labelName=""
					labelValue="${pbsFnodeapprover.sDepartmentid.name}" title="部门"
					url="/sys/office/treeData?type=2" cssClass="input-large required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>学员编号：</label>
			<div class="controls required">
				<sys:treeselect id="sApprover" name="sApprover"
					value="${pbsFnodeapprover.sApprover.id}" labelName="mastername"
					labelValue="${pbsFnodeapprover.sApprover.SName}" title="学员"
					url="/sys/pbsOffice/treeData?type=4" cssClass="input-large required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">流程节点的描述信息：</label>
			<div class="controls">
				<form:input path="sDescription" htmlEscape="false" maxlength="1000"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="flow:pbsFnodeapprover:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>