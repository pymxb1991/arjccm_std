<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>考试信息管理</title>
<meta name="decorator" content="default" />
<script src="${ctxStatic}/pbs/exam/js/pbsExamactionForm.js"
	type="text/javascript"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/exam/pbsExamaction/">考试信息列表</a></li>
		<li class="active"><a
			href="${ctx}/exam/pbsExamaction/form?id=${pbsExamaction.id}">考试信息<shiro:hasPermission
					name="exam:pbsExamaction:edit">${not empty pbsExamaction.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="exam:pbsExamaction:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsExamaction"
		action="${ctx}/exam/pbsExamaction/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="hide sExampaper"
			topid="${pbsExamaction.sExampaper.id}"></div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>试卷编号：</label>
			<div class="controls">
			<form:select path="sExampaper" class="input-xlarge required">
					<form:option value="" label="" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>答题者编号：</label>
			<div class="controls">
			<sys:treeselect id="sExaminee" name="sExaminee"
					value="${pbsExamaction.sExaminee.id}" labelName="required"
					labelValue="${pbsExamaction.sExaminee.SName}" title="学员"
					url="/sys/pbsOffice/treeData?type=4" cssClass="" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开始答题时间：</label>
			<div class="controls">
				<input name="dtStart" type="text" readonly="readonly" maxlength="20"
					class="input-xlarge Wdate "
					value="<fmt:formatDate value="${pbsExamaction.dtStart}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提交时间：</label>
			<div class="controls">
				<input name="dtSubmit" type="text" readonly="readonly"
					maxlength="20" class="input-xlarge Wdate "
					value="<fmt:formatDate value="${pbsExamaction.dtSubmit}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">考试成绩：</label>
			<div class="controls">
				<form:input path="iReport" htmlEscape="false" maxlength="11"
					class="input-xlarge  digits" />
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
			<shiro:hasPermission name="exam:pbsExamaction:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>