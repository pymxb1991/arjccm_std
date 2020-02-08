<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工作安排操作记录管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/task/js/pbsTaskoprecForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/task/pbsTaskoprec/">工作安排操作记录列表</a></li>
		<li class="active"><a
			href="${ctx}/task/pbsTaskoprec/form?id=${pbsTaskoprec.id}">工作安排操作记录<shiro:hasPermission
					name="task:pbsTaskoprec:edit">${not empty pbsTaskoprec.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="task:pbsTaskoprec:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsTaskoprec"
		action="${ctx}/task/pbsTaskoprec/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="hide sTaskid" topid="${pbsTaskoprec.sTaskid.id}"></div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>任务：</label>
			<div class="controls">
				<form:select path="sTaskid" class="input-xlarge required">
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>任务操作类型：</label>
			<div class="controls">
				<form:select path="sType" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('taskopttype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>执行任务人员的部门：</label>
			<div class="controls">
				<sys:treeselect id="sExecdepartment" name="sExecdepartment"
					value="${pbsTaskoprec.sExecdepartment.id}" labelName="sExecdepartment"
					labelValue="${pbsTaskoprec.sExecdepartment.name}" title="部门"
					url="/sys/office/treeData?type=2" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>执行负责人员：</label>
			<div class="controls">
				<sys:treeselect id="sExecutor" name="sExecutor"
					value="${pbsTaskoprec.sExecutor.id}" labelName="sExecutor"
					labelValue="${pbsTaskoprec.sExecutor.SName}" title="人员"
					url="/sys/office/treeData?type=3" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>操作人员：</label>
			<div class="controls">
				<sys:treeselect id="sOperator" name="sOperator"
					value="${pbsTaskoprec.sOperator.id}" labelName="sOperator"
					labelValue="${pbsTaskoprec.sOperator.name}" title="用户"
					url="/sys/office/treeData?type=3" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>操作学员：</label>
			<div class="controls">
				<sys:treeselect id="sBindmember" name="sBindmember"
					value="${pbsTaskoprec.sBindmember.id}" labelName=""
					labelValue="${pbsTaskoprec.sBindmember.SName}" title="学员"
					url="/sys/pbsOffice/treeData?type=4" cssClass="" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>任务最终操作状态：</label>
			<div class="controls">
				<form:select path="sOptstatus" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('taskoptstatus')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作内容描述：</label>
			<div class="controls">
				<form:textarea path="sContent" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="task:pbsTaskoprec:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>

