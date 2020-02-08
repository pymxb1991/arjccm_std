<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>运行工作流管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/flow/js/pbsFlowworkForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/flow/pbsFlowwork/">运行工作流列表</a></li>
		<li class="active"><a
			href="${ctx}/flow/pbsFlowwork/form?id=${pbsFlowwork.id}">运行工作流<shiro:hasPermission
					name="flow:pbsFlowwork:edit">${not empty pbsFlowwork.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="flow:pbsFlowwork:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsFlowwork"
		action="${ctx}/flow/pbsFlowwork/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="hide sFlowid" topid="${pbsFlowwork.sFlowid.id}"></div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>工作流名称：</label>
			<div class="controls">
				<form:input path="sName" htmlEscape="false" maxlength="200"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>所属工作流模板：</label>
			<div class="controls">
				<form:select path="sFlowid" class="input-xlarge required">
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作流程页面URL：</label>
			<div class="controls">
				<form:input path="sFlowurl" htmlEscape="false" maxlength="1000"
					class="input-xlarge" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>开始日期：</label>
			<div class="controls">
				<input name="dtStartdate" type="text" readonly="readonly"
					maxlength="20" class="input-xlarge Wdate required"
					value="<fmt:formatDate value="${pbsFlowwork.dtStartdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">结束日期：</label>
			<div class="controls">
				<input name="dtEnddate" type="text" readonly="readonly"
					maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pbsFlowwork.dtEnddate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>操作人员：</label>
			<div class="controls">
				<sys:treeselect id="sOperator" name="sOperator"
					value="${pbsFlowwork.sOperator.id}" labelName="sOperator"
					labelValue="${pbsFlowwork.sOperator.name}" title="用户"
					url="/sys/office/treeData?type=3" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>学员信息：</label>
			<div class="controls">
				<sys:treeselect id="sBindmember" name="sBindmember"
					value="${pbsFlowwork.sBindmember.id}" labelName="sBindmember"
					labelValue="${pbsFlowwork.sBindmember.SName}" title="用户"
					url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">绑定的数据类型：</label>
			<div class="controls">
				<form:input path="sBinddata" htmlEscape="false" maxlength="200"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">绑定的主键：</label>
			<div class="controls">
				<form:input path="sBindkey" htmlEscape="false" maxlength="200"
					class="input-xlarge " />
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">流程节点的描述信息：</label>
			<div class="controls">
				<form:textarea path="sDescription" htmlEscape="false" rows="4"
					maxlength="1000" class="input-xxlarge " />
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="flow:pbsFlowwork:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>