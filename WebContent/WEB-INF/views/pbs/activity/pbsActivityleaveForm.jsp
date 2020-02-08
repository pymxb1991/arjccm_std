<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>活动请假管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/activity/js/pbsActivityleaveForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/activity/pbsActivityleave/">活动请假列表</a></li>
		<li class="active"><a
			href="${ctx}/activity/pbsActivityleave/form?id=${pbsActivityleave.id}">活动请假<shiro:hasPermission
					name="activity:pbsActivityleave:edit">${not empty pbsActivityleave.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="activity:pbsActivityleave:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsActivityleave"
		action="${ctx}/activity/pbsActivityleave/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="hide sActivityid" topid="${pbsActivityleave.sActivityid.id}"></div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>活动：</label>
			<div class="controls">
			<form:select path="sActivityid" class="input-xlarge required">
                </form:select>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">请假类型：</label>
			<div class="controls">
				<form:select path="sType" class="input-xlarge ">
				</form:select>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>操作人员：</label>
			<div class="controls">
				<sys:treeselect id="sOperator" name="sOperator"
					value="${pbsActivityleave.sOperator.id}" labelName="sOperator"
					labelValue="${pbsActivityleave.sOperator.name}" title="用户"
					url="/sys/office/treeData?type=3" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>学员：</label>
			<div class="controls">
				<sys:treeselect id="sBindmember" name="sBindmember"
					value="${pbsActivityleave.sBindmember.id}" labelName="sBindmember"
					labelValue="${pbsActivityleave.sBindmember.SName}" title="用户"
					url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">请假事由：</label>
			<div class="controls">
				<form:textarea path="sAllpyreason" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="activity:pbsActivityleave:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>