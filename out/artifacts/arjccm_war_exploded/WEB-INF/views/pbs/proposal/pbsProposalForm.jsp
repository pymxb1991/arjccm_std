<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>建议信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/proposal/js/pbsProposalForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/proposal/pbsProposal/">建议信息列表</a></li>
		<li class="active"><a
			href="${ctx}/proposal/pbsProposal/form?id=${pbsProposal.id}">建议信息<shiro:hasPermission
					name="proposal:pbsProposal:edit">${not empty pbsProposal.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="proposal:pbsProposal:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsProposal"
		action="${ctx}/proposal/pbsProposal/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<div class="hide sAreaid" topid="${pbsProposal.sAreaid.id}"></div>
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>建议汇报的标题：</label>
			<div class="controls">
				<form:input path="sTitle" htmlEscape="false" maxlength="200"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>建议类别：</label>
			<div class="controls">
				<form:select path="sAreaid" class="input-xlarge required">
					<form:option value="" label="" />
				</form:select>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">建议类别版本：</label>
			<div class="controls">
				<form:input path="sAreaversion" htmlEscape="false" maxlength="10"
					class="input-xlarge " />
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
		<%-- 	<div class="control-group">
			<label class="control-label">回复人数：</label>
			<div class="controls">
				<form:input path="iCnt" htmlEscape="false" maxlength="11"
					class="input-xlarge " />
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>发布者：</label>
			<div class="controls">
			<sys:treeselect id="sReporteruser" name="sReporteruser"
                    value="${pbsProposal.sReporteruser.id}" labelName="sReporteruser"
                    labelValue="${pbsProposal.sReporteruser.name}" title="用户"
                    url="/sys/office/treeData?type=3" cssClass="required" allowClear="true"
                    notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>发布者学员信息：</label>
			<div class="controls">
			 <sys:treeselect id="sReportermem" name="sReportermem"
                    value="${pbsProposal.sReportermem.id}" labelName="sReportermem"
                    labelValue="${pbsProposal.sReportermem.SName}" title="用户"
                    url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
                    notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>接收者学员信息：</label>
			<div class="controls">
			 <sys:treeselect id="sAcceptermem" name="sAcceptermem"
                    value="${pbsProposal.sAcceptermem.id}" labelName="sAcceptermem"
                    labelValue="${pbsProposal.sAcceptermem.SName}" title="学员"
                    url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
                    notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>公布标记：</label>
			<div class="controls">
				 <form:select path="sPublish" class="input-xlarge required">
                    <form:option value="" label="" />
                    <form:options items="${fns:getDictList('yes_no')}"
                        itemLabel="label" itemValue="value" htmlEscape="false" />
                </form:select>
			</div>
		</div>  
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>提交状态：</label>
			<div class="controls">
				<form:select path="sStat" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('proposalstattype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>被评价标记：</label>
			<div class="controls">
				<form:select path="sLevel" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('taskvaluetype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
            <label class="control-label">建议汇报内容：</label>
            <div class="controls">
                <form:textarea path="sContent" htmlEscape="false" rows="4"
                    maxlength="255" class="input-xxlarge " />
            </div>
        </div>
		<div class="control-group">
			<label class="control-label">描述信息：</label>
			<div class="controls">
				<form:textarea path="sDescription" htmlEscape="false" rows="4"
					  class="input-xxlarge " />
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="proposal:pbsProposal:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>