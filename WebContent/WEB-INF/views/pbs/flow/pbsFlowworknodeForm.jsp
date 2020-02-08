<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工作节点记录管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/flow/js/pbsFlowworknodeForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/flow/pbsFlowworknode/">工作节点记录列表</a></li>
		<li class="active"><a
			href="${ctx}/flow/pbsFlowworknode/form?id=${pbsFlowworknode.id}">工作节点记录<shiro:hasPermission
					name="flow:pbsFlowworknode:edit">${not empty pbsFlowworknode.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="flow:pbsFlowworknode:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsFlowworknode"
		action="${ctx}/flow/pbsFlowworknode/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="hide sFlowid" sFlowid="${pbsFlowworknode.sFlowid.id}"></div>
		<div class="hide sNodeid" sNodeid="${pbsFlowworknode.sNodeid.id}"></div>
		<div class="hide sFlowworkid"
			sFlowworkid="${pbsFlowworknode.sFlowworkid.id}"></div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>任务节点：</label>
			<div class="controls">
				<form:input path="sName" htmlEscape="false" maxlength="200"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>所属工作模板：</label>
			<div class="controls">
				<form:select path="sFlowid" class="input-xlarge required">
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>所属节点：</label>
			<div class="controls">
				<form:select path="sNodeid" class="input-xlarge required">
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>所属任务：</label>
			<div class="controls">
				<form:select path="sFlowworkid" class="input-xlarge required">
				</form:select>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">业务表：</label>
			<div class="controls">
				<form:input path="sBinddata" htmlEscape="false" maxlength="200"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">业务主键：</label>
			<div class="controls">
				<form:input path="sBindkey" htmlEscape="false" maxlength="2000"
					class="input-xlarge " />
			</div>
		</div> 
		<div class="control-group">
			<label class="control-label">操作人员：</label>
			<div class="controls">
				<sys:treeselect id="sOperator" name="sOperator"
					value="${pbsFlowworknode.sOperator.id}" labelName=""
					labelValue="${pbsFlowworknode.sOperator.name}" title="用户"
					url="/sys/office/treeData?type=3" cssClass="" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>学员信息：</label>
			<div class="controls">
				<sys:treeselect id="sBindmember" name="sBindmember"
					value="${pbsFlowworknode.sBindmember.id}" labelName="sBindmember"
					labelValue="${pbsFlowworknode.sBindmember.SName}" title="学员"
					url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>执行的状态设置：</label>
			<div class="controls">
				<form:select path="sSetstatval" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('worknodestats')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">节点备注：</label>
			<div class="controls">
				<form:textarea path="sActionremark" htmlEscape="false" rows="4"
					maxlength="1000" class="input-xxlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">记录描述：</label>
			<div class="controls">
				<form:textarea path="sDescription" htmlEscape="false" rows="4"
					maxlength="1000" class="input-xxlarge " />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="flow:pbsFlowworknode:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>