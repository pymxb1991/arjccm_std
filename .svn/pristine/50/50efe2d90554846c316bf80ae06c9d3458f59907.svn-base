<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>科目信息管理</title>
<meta name="decorator" content="default" />
<script src="${ctxStatic}/pbs/common/js/Form.js" type="text/javascript"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/question/pbsQuestionProject/">科目信息列表</a></li>
		<li class="active"><a
			href="${ctx}/question/pbsQuestionProject/form?id=${pbsQuestionProject.id}">科目信息<shiro:hasPermission
					name="question:pbsQuestionProject:edit">${not empty pbsQuestionProject.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="question:pbsQuestionProject:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsQuestionProject"
		action="${ctx}/question/pbsQuestionProject/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="sParentid" value="000001" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>科目名称：</label>
			<div class="controls">
				<form:input path="sName" htmlEscape="false" maxlength="500"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>科目描述：</label>
			<div class="controls">
				<form:textarea path="sDesc" htmlEscape="false" rows="4"
					maxlength="1000" class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="1000" class="input-xlarge" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图片显示：</label>
			<div class="controls">
				<form:hidden id="sUrl" path="sUrl" htmlEscape="false"
					maxlength="500" class="input-xlarge" />
				<sys:ckfinder input="sUrl" type="files"
					uploadPath="/question/pbsQuestionProject" selectMultiple="true" />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="question:pbsQuestionProject:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>