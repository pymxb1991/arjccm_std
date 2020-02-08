<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>客观题信息管理</title>
<meta name="decorator" content="default" />
<script src="${ctxStatic}/pbs/question/pbsQuestionObjectiveForm.js" type="text/javascript"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/question/pbsQuestionObjective/">客观题信息列表</a></li>
		<li class="active"><a
			href="${ctx}/question/pbsQuestionObjective/addform?id=${pbsQuestionObjective.id}">客观题信息<shiro:hasPermission
					name="question:pbsQuestionObjective:edit">${not empty pbsQuestionObjective.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="question:pbsQuestionObjective:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsQuestionObjective"
		action="${ctx}/question/pbsQuestionObjective/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<div class="hide sParentid" topid="${pbsQuestionObjective.sParentid.id}"></div>
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>科目：</label>
			<div class="controls">
				<form:select path="sParentid" class="input-xlarge required">
					<form:option value="" label="" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>试题类型：</label>
			<div class="controls">
			<form:select path="sType" class="input-xlarge ">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('objectivetype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>试题内容：</label>
			<div class="controls">
				<form:textarea path="sBody" htmlEscape="false" rows="4"
					maxlength="255" class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">选项个数：</label>
			<div class="controls">
				<form:input path="iChoicecnt" htmlEscape="false" maxlength="11"
					class="input-xlarge  digits" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参考答案：</label>
			<div class="controls">
				<form:input path="sAnswer" htmlEscape="false" maxlength="200"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分值：</label>
			<div class="controls">
				<form:input path="iValue" htmlEscape="false" maxlength="11"
					class="input-xlarge  digits" />
			</div>
		</div>
	<%-- 	<div class="control-group">
			<label class="control-label">难度等级：</label>
			<div class="controls">
				<form:input path="sLevel" htmlEscape="false" maxlength="64"
					class="input-xlarge " />
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>难度等级：</label>
			<div class="controls">
			<form:select path="sLevel" class="input-xlarge ">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('objectivelevel')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="question:pbsQuestionObjective:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>