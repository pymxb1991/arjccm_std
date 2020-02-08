<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>试卷题目管理</title>
<meta name="decorator" content="default" />
<script src="${ctxStatic}/pbs/exam/js/pbsExampaperitemForm.js"
	type="text/javascript"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/exam/pbsExampaperitem/">试卷题目列表</a></li>
		<li class="active"><a
			href="${ctx}/exam/pbsExampaperitem/form?id=${pbsExampaperitem.id}">试卷题目<shiro:hasPermission
					name="exam:pbsExampaperitem:edit">${not empty pbsExampaperitem.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="exam:pbsExampaperitem:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsExampaperitem"
		action="${ctx}/exam/pbsExampaperitem/addsave" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="hide sExampaper" topid="${pbsExampaperitem.sExampaper.id}"></div>
		<div class="hide sItem" topid="${pbsExampaperitem.sItem.id}"></div>
		<div class="hide sBelongtype" topid="${pbsExampaperitem.sItem.SType}"></div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>试卷编号：</label>
			<div class="controls">
				<form:select path="sExampaper" class="input-xlarge required">
					<form:option value="" label="" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>考题编号：</label>
			<div class="controls">
				<form:select path="sItem" class="input-xlarge required">
					<form:option value="" label="" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属试题类型区域：</label>
			<div class="controls">
				<form:select path="sBelongtype" class="input-xlarge ">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('exambelongtype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
            <label class="control-label">题目序号：</label>
            <div class="controls">
                <form:input path="iSort" htmlEscape="false" maxlength="11"
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
			<shiro:hasPermission name="exam:pbsExampaperitem:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>

