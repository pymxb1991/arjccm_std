<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>活动通知管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/activity/js/pbsActinotificationsForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/activity/pbsActinotifications/">活动通知列表</a></li>
		<li class="active"><a
			href="${ctx}/activity/pbsActinotifications/form?id=${pbsActinotifications.id}">活动通知<shiro:hasPermission
					name="activity:pbsActinotifications:edit">${not empty pbsActinotifications.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="activity:pbsActinotifications:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsActinotifications"
		action="${ctx}/activity/pbsActinotifications/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="hide sTypeid"
			topid="${pbsActinotifications.sActivityid.id}"></div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>活动：</label>
			<div class="controls">
				<form:select path="sActivityid" class="input-xlarge required">
				
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>通知类型：</label>
			<div class="controls">
				<form:select path="sType" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('actinformtype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>通知内容：</label>
			<div class="controls">
				<form:input path="sContent" htmlEscape="false" class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>名称：</label>
			<div class="controls">
				<sys:treeselect id="sAcceptorid" name="sAcceptorid"
					value="${pbsActinotifications.sAcceptorid.id}" labelName="sAcceptorid"
					labelValue="${pbsActinotifications.sAcceptorid.SName}" title="用户"
					url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>读取状态：</label>
			<div class="controls">
				<form:select path="sStat" class="input-xlarge ">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('oa_notify_read')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">描述信息：</label>
			<div class="controls">
				<form:input path="sDescription" htmlEscape="false" maxlength="1000"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参考信息：</label>
			<div class="controls">
				<form:input path="sUrl" htmlEscape="false" maxlength="500"
					class="input-xlarge " />
			</div>
		</div> --%>

		<div class="form-actions">
			<shiro:hasPermission name="activity:pbsActinotifications:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>