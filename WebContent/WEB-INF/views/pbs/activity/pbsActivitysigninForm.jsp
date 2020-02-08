<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>活动签到管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/activity/js/pbsActivitysigninForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/activity/pbsActivitysignin/">活动签到列表</a></li>
		<li class="active"><a
			href="${ctx}/activity/pbsActivitysignin/form?id=${pbsActivitysignin.id}">活动签到<shiro:hasPermission
					name="activity:pbsActivitysignin:edit">${not empty pbsActivitysignin.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="activity:pbsActivitysignin:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsActivitysignin"
		action="${ctx}/activity/pbsActivitysignin/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="hide sActivityid"
			topid="${pbsActivitysignin.sActivityid.id}"></div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>活动：</label>
			<div class="controls">
				<form:select path="sActivityid" class="input-xlarge required">
				</form:select>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">活动类型：</label>
			<div class="controls">
				<form:select path="sType" class="input-xlarge ">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('')}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">是否报名人员：</label>
			<div class="controls">
				<form:select path="sRegflag" class="input-xlarge">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('yes_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>签到绑定人员：</label>
			<div class="controls">
				<sys:treeselect id="sSignbindmember" name="sSignbindmember"
					value="${pbsActivitysignin.sSignbindmember.id}" labelName="sSignbindmember"
					labelValue="${pbsActivitysignin.sSignbindmember.SName}" title="学员"
					url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">携带家属人数：</label>
			<div class="controls">
				<form:input path="sFamilyflow" htmlEscape="false" maxlength="20"
					class="input-xlarge" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>操作人员：</label>
			<div class="controls">
				<sys:treeselect id="sOperator" name="sOperator"
					value="${pbsActivitysignin.sOperator.id}" labelName="sOperator"
					labelValue="${pbsActivitysignin.sOperator.name}" title="用户"
					url="/sys/office/treeData?type=3" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>操作绑定人员：</label>
			<div class="controls">
				<sys:treeselect id="sBindmember" name="sBindmember"
					value="${pbsActivitysignin.sBindmember.id}" labelName="sBindmember"
					labelValue="${pbsActivitysignin.sBindmember.SName}" title="学员"
					url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="activity:pbsActivitysignin:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>