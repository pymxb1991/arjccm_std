<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>选择题选项管理</title>
<meta name="decorator" content="default" />
<script src="${ctxStatic}/pbs/question/pbsChoiceItemForm.js" type="text/javascript"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/question/pbsChoiceItem/">选择题选项列表</a></li>
			<li class=""><a
			href="${ctx}/question/pbsQuestionObjective/checkform?id=${pbsChoiceItem.sParentid.id}">客观题信息<shiro:hasPermission
					name="question:pbsQuestionObjective:edit">${not empty pbsChoiceItem.sParentid.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="question:pbsQuestionObjective:edit">查看</shiro:lacksPermission></a></li>
		<li class="active"><a
			href="${ctx}/question/pbsChoiceItem/form?id=${pbsChoiceItem.id}">选择题选项<shiro:hasPermission
					name="question:pbsChoiceItem:edit">${not empty pbsChoiceItem.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="question:pbsChoiceItem:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsChoiceItem"
		action="${ctx}/question/pbsChoiceItem/Checksave" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="hide sParentid"
			topid="${pbsChoiceItem.sParentid.id}"></div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>试题类型：</label>
			<div class="controls">
				<form:select path="sType" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('objectivetype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>选择题编号：</label>
			<div class="controls">
			<form:select path="sParentid" class="input-xlarge required">
					<form:option value="" label="" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>选项键值：</label>
			<div class="controls">
				<form:select path="sKey" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('vote_item')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>选项内容：</label>
			<div class="controls">
				<form:textarea path="sBody" htmlEscape="false" rows="4"
					maxlength="255" class="input-xlarge required" />
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">选项展示排序：</label>
			<div class="controls">
				<form:input path="iSort" htmlEscape="false" maxlength="11"
					class="input-xlarge " />
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="question:pbsChoiceItem:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>