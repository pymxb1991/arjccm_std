<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>建议操作信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/proposal/js/pbsProposaloptForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/proposal/pbsProposalopt/">建议操作信息列表</a></li>
		<li class="active"><a
			href="${ctx}/proposal/pbsProposalopt/form?id=${pbsProposalopt.id}">建议操作信息<shiro:hasPermission
					name="proposal:pbsProposalopt:edit">${not empty pbsProposalopt.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="proposal:pbsProposalopt:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsProposalopt"
		action="${ctx}/proposal/pbsProposalopt/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="hide sAreatype" topid="${pbsProposalopt.sAreatype.id}"></div>
		<div class="hide sProposalid" topid="${pbsProposalopt.sProposalid.id}"></div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>建议类别：</label>
			<div class="controls">
				<form:select path="sAreatype" class="input-xlarge required">
					<form:option value="" label="" />
				</form:select>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">类别版本：</label>
			<div class="controls">
				<form:input path="sAreavesion" htmlEscape="false" maxlength="64"
					class="input-xlarge " />
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>建议标题：</label>
			<div class="controls">
				<form:select path="sProposalid" class="input-xlarge required">
					<form:option value="" label="" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>操作类型：</label>
			<div class="controls">
				<form:select path="sOpttype" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('proposalopttype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>审核结果：</label>
			<div class="controls">
				<form:select path="sOpresult" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('taskfinishtype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div> --%>

		<%-- <div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>展示方式：</label>
			<div class="controls">
				<form:select path="sShowtype" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('proposalshowtype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>操作者用户：</label>
			<div class="controls">
				<sys:treeselect id="sReporteruser" name="sReporteruser"
					value="${pbsProposalopt.sReporteruser.id}" labelName="sReporteruser"
					labelValue="${pbsProposalopt.sReporteruser.name}" title="用户"
					url="/sys/office/treeData?type=3" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>操作者学员：</label>
			<div class="controls">
				<sys:treeselect id="sReportermem" name="sReportermem"
					value="${pbsProposalopt.sReportermem.id}" labelName="sReportermem"
					labelValue="${pbsProposalopt.sReportermem.SName}" title="学员"
					url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>评价等级：</label>
			<div class="controls">
				<form:select path="sLevel" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('taskvaluetype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">描述信息：</label>
			<div class="controls">
				<form:input path="sDescription" htmlEscape="false" maxlength="1000"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作批注：</label>
			<div class="controls">
				<form:textarea path="sContent" htmlEscape="false" rows="4"
					class="input-xxlarge " />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="proposal:pbsProposalopt:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>