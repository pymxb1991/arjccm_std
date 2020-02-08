<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>学员-组织关系管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/person/js/pbsDepartmentbindForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/person/pbsDepartmentbind/">学员表关系列表</a></li>
		<li class="active"><a
			href="${ctx}/person/pbsDepartmentbind/form?id=${pbsDepartmentbind.id}">学员表关系<shiro:hasPermission
					name="person:pbsDepartmentbind:edit">${not empty pbsDepartmentbind.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="person:pbsDepartmentbind:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsDepartmentbind"
		action="${ctx}/person/pbsDepartmentbind/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="sPartymemid"
			value="${pbsDepartmentbind.SPartymemid}" />
		<div class="hide sPost" topid="${pbsDepartmentbind.SPost.id}"></div>
		<div class="hide sPosttitle" topid="${pbsDepartmentbind.SPosttitle.id}"></div>
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">学员名称：</label>
			<div class="controls">
				<input value="${pbsDepartmentbind.partymem.SName}"
					htmlEscape="false" maxlength="50" class="input-xlarge" type="text"
					readonly="readonly" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>部门选择：</label>
			<div class="controls">
				<sys:treeselect id="sDepartmentid" name="sDepartmentid"
					value="${pbsDepartmentbind.SDepartmentid}" labelName="officename"
					labelValue="${pbsDepartmentbind.officename}" title="部门"
					url="/sys/office/treeData?type=2" cssClass="input-small required"
					allowClear="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">职务：</label>
			<div class="controls">
			<form:select path="sPost" class="input-xlarge ">
					<form:option value="" label="" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">头衔：</label>
			<div class="controls">
			<form:select path="sPosttitle" class="input-xlarge ">
					<form:option value="" label="" />
				</form:select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">任职日期：</label>
			<div class="controls">
				<input name="dtPosttime" type="text" readonly="readonly"
					maxlength="20" class="input-xlarge Wdate "
					value="<fmt:formatDate value="${pbsDepartmentbind.dtPosttime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
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
			<shiro:hasPermission name="person:pbsDepartmentbind:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>