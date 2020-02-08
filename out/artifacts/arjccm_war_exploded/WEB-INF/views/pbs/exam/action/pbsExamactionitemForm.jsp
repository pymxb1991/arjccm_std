<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>考试题目信息管理</title>
<meta name="decorator" content="default" />
<script src="${ctxStatic}/pbs/exam/js/pbsExamactionitemForm.js"
	type="text/javascript"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/exam/pbsExamactionitem/">考试题目信息列表</a></li>
		<li class="active"><a
			href="${ctx}/exam/pbsExamactionitem/form?id=${pbsExamactionitem.id}">考试题目信息<shiro:hasPermission
					name="exam:pbsExamactionitem:edit">${not empty pbsExamactionitem.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="exam:pbsExamactionitem:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsExamactionitem"
		action="${ctx}/exam/pbsExamactionitem/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="hide sExampaper"
			topid="${pbsExamactionitem.sExampaper.id}"></div>
		<div class="hide sItem"
			topid="${pbsExamactionitem.sItem.id}"></div>
			<div class="hide sActionid"
			topid="${pbsExamactionitem.sActionid.id}"></div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>试卷编号：</label>
			<div class="controls">
				<form:select path="sExampaper" class="input-xlarge required">
					<form:option value="" label="" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">考题编号：</label>
			<div class="controls">
				<form:select path="sItem" class="input-xlarge ">
					<form:option value="" label="" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">考试结果编号：</label>
			<div class="controls">
				<form:select path="sActionid" class="input-xlarge ">
					<form:option value="" label="" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>答题者编号：</label>
			<div class="controls">
				<sys:treeselect id="sExaminee" name="sExaminee"
					value="${pbsExamactionitem.sExaminee.id}" labelName="sExaminee"
					labelValue="${pbsExamactionitem.sExaminee.SName}" title="用户"
					url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">答案结果：</label>
			<div class="controls">
				<form:input path="sDoanswer" htmlEscape="false" maxlength="64"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>答案对错：</label>
			<div class="controls">
				<form:select path="sJudge" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('yes_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>	
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">给与分数：</label>
			<div class="controls">
				<form:input path="iScore" htmlEscape="false" maxlength="11"
					class="input-xlarge  digits" />
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
			<shiro:hasPermission name="exam:pbsExamactionitem:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>