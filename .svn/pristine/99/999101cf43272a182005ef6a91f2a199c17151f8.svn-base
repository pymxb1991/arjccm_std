<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工作安排反馈信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/task/js/pbsTaskevaluateForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/task/pbsTaskevaluate/">工作安排反馈信息列表</a></li>
		<li class="active"><a
			href="${ctx}/task/pbsTaskevaluate/form?id=${pbsTaskevaluate.id}">工作安排反馈信息<shiro:hasPermission
					name="task:pbsTaskevaluate:edit">${not empty pbsTaskevaluate.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="task:pbsTaskevaluate:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsTaskevaluate"
		action="${ctx}/task/pbsTaskevaluate/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="hide sTaskid" topid="${pbsTaskevaluate.sTaskid.id}"></div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>任务：</label>
			<div class="controls">
				<form:select path="sTaskid" class="input-xlarge required">
				</form:select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>任务的执行人：</label>
			<div class="controls">
				<sys:treeselect id="sExecmember" name="sExecmember"
					value="${pbsTaskevaluate.sExecmember.id}" labelName="sExecmember"
					labelValue="${pbsTaskevaluate.sExecmember.SName}" title="学员"
					url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>评价分值：</label>
			<div class="controls">
				<form:select path="sValue" class="input-xlarge required">
                    <form:option value="" label="" />
                    <form:options items="${fns:getDictList('taskvaluetype')}"
                        itemLabel="label" itemValue="value" htmlEscape="false" />
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>操作人员：</label>
			<div class="controls">
				<sys:treeselect id="sOperator" name="sOperator"
					value="${pbsTaskevaluate.sOperator.id}" labelName="sOperator"
					labelValue="${pbsTaskevaluate.sOperator.name}" title="用户"
					url="/sys/office/treeData?type=3" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>操作学员：</label>
			<div class="controls">
				<sys:treeselect id="sBindmember" name="sBindmember"
					value="${pbsTaskevaluate.sBindmember.id}" labelName="sBindmember"
					labelValue="${pbsTaskevaluate.sBindmember.SName}" title="学员"
					url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>记录状态：</label>
			<div class="controls"> 
				<form:select path="sStatus" class="input-xlarge required">
                    <form:option value="" label="" />
                    <form:options items="${fns:getDictList('taskfinishtype')}"
                        itemLabel="label" itemValue="value" htmlEscape="false" />
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评价描述信息：</label>
			<div class="controls">
				<form:textarea path="sContent" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="task:pbsTaskevaluate:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>