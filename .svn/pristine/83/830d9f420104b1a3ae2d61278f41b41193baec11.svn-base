<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>活动评分管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/activity/js/pbsActivityevaluateForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/activity/pbsActivityevaluate/">活动评分列表</a></li>
		<li class="active"><a
			href="${ctx}/activity/pbsActivityevaluate/form?id=${pbsActivityevaluate.id}">活动评分<shiro:hasPermission
					name="activity:pbsActivityevaluate:edit">${not empty pbsActivityevaluate.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="activity:pbsActivityevaluate:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsActivityevaluate"
		action="${ctx}/activity/pbsActivityevaluate/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="hide sActivityid"
			topid="${pbsActivityevaluate.sActivityid.id}"></div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>活动编号：</label>
			<div class="controls">
				<form:select path="sActivityid" class="input-xlarge required">
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>被评价人员：</label>
			<div class="controls">
				<sys:treeselect id="sPartmember" name="sPartmember"
					value="${pbsActivityevaluate.sPartmember.id}" labelName=""
					labelValue="${pbsActivityevaluate.sPartmember.SName}" title="用户"
					url="/sys/pbsOffice/treeData?type=4" cssClass="" allowClear="true"
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
					value="${pbsActivityevaluate.sOperator.id}" labelName=""
					labelValue="${pbsActivityevaluate.sOperator.name}" title="用户"
					url="/sys/office/treeData?type=3" cssClass="" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>操作学员：</label>
			<div class="controls">
				<sys:treeselect id="sBindmember" name="sBindmember"
					value="${pbsActivityevaluate.sBindmember.id}" labelName=""
					labelValue="${pbsActivityevaluate.sBindmember.SName}" title="学员"
					url="/sys/pbsOffice/treeData?type=4" cssClass="" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">记录状态：</label>
			<div class="controls">
				<form:input path="sStatus" htmlEscape="false" maxlength="100"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
            <label class="control-label">评价描述信息：</label>
            <div class="controls">
                <form:input path="sContent" htmlEscape="false" maxlength="2000"
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
			<shiro:hasPermission name="activity:pbsActivityevaluate:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>