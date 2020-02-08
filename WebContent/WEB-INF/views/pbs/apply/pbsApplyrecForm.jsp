<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>申请记录管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/apply/js/pbsApplyrecForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/apply/pbsApplyrec/">申请记录列表</a></li>
		<li class="active"><a
			href="${ctx}/apply/pbsApplyrec/form?id=${pbsApplyrec.id}">申请记录<shiro:hasPermission
					name="apply:pbsApplyrec:edit">${not empty pbsApplyrec.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="apply:pbsApplyrec:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsApplyrec"
		action="${ctx}/apply/pbsApplyrec/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="hide sType" topid="${pbsApplyrec.sType.id}"></div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>申请类型：</label>
			<div class="controls">
				<form:select path="sType" class="input-xlarge required">
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>申请简述信息：</label>
			<div class="controls">
				<form:input path="sResume" htmlEscape="false" maxlength="500"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>申请的部门编号：</label>
			<div class="controls">
				<sys:treeselect id="sPartment" name="sPartment"
					value="${pbsApplyrec.sPartment.id}" labelName="sPartment"
					labelValue="${pbsApplyrec.sPartment.name}" title="部门"
					url="/sys/office/treeData?type=2" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>申请绑定人员：</label>
			<div class="controls">
			  <sys:treeselect id="sBindmember" name="sBindmember"
					value="${pbsApplyrec.sBindmember.id}" labelName="sBindmember"
					labelValue="${pbsApplyrec.sBindmember.SName}" title="学员"
					url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
<%-- 				<form:input path="sBindmember.sName" htmlEscape="false" --%>
<%-- 					maxlength="64" class="input-xlarge required" /> --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>记录状态：</label>
			<div class="controls">
				<form:select path="sStatus" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('flowresult')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件：</label>
			<div class="controls">
				<form:hidden path="sFileurl" id="sFileurl"/>
				<sys:ckfinder input="sFileurl" type="files" uploadPath="/apply/pbsApplyrec" selectMultiple="true"></sys:ckfinder>
			</div>
		</div>
		<div class="control-group">
            <label class="control-label">申请内容：</label>
            <div class="controls">
                <form:textarea path="sContent" htmlEscape="false" rows="4"
                    maxlength="2000" class="input-xxlarge " />
            </div>
        </div>
		<div class="form-actions">
			<shiro:hasPermission name="apply:pbsApplyrec:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>