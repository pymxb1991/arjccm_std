<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>思想汇报操作管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/thinkingreport/js/pbsThinkingreportoptForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/thinkingreport/pbsThinkingreportopt/">思想汇报操作列表</a></li>
		<li class="active"><a
			href="${ctx}/thinkingreport/pbsThinkingreportopt/form?id=${pbsThinkingreportopt.id}">思想汇报操作<shiro:hasPermission
					name="thinkingreport:pbsThinkingreportopt:edit">${not empty pbsThinkingreportopt.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission
					name="thinkingreport:pbsThinkingreportopt:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsThinkingreportopt"
		action="${ctx}/thinkingreport/pbsThinkingreportopt/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="hide sReportid"
			topid="${pbsThinkingreportopt.sReportid.id}"></div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>思想汇报编号：</label>
			<div class="controls">
				<form:select path="sReportid" class="input-xlarge required">
					<form:option value="" label="" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>操作类型：</label>
			<div class="controls">
				<form:select path="sType" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('thinkopttype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>转发发送的学员：</label>
			<div class="controls">
				<sys:treeselect id="sTransmem" name="sTransmem"
					value="${pbsThinkingreportopt.sTransmem.id}" labelName="sTransmem"
					labelValue="${pbsThinkingreportopt.sTransmem.SName}" title="用户"
					url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>评价/转发的学员：</label>
			<div class="controls">
				<sys:treeselect id="sOperatemem" name="sOperatemem"
					value="${pbsThinkingreportopt.sOperatemem.id}" labelName="sOperatemem"
					labelValue="${pbsThinkingreportopt.sOperatemem.SName}" title="用户"
					url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>评价/转发的登录用户：</label>
			<div class="controls">
				<sys:treeselect id="sOperateuser" name="sOperateuser"
					value="${pbsThinkingreportopt.sOperateuser.id}" labelName="sOperateuser"
					labelValue="${pbsThinkingreportopt.sOperateuser.name}" title="用户"
					url="/sys/office/treeData?type=3" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>评价等级：</label>
			<div class="controls">
				<form:select path="sLevel" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('taskvaluetype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">评价/评语信息：</label>
			<div class="controls">
				<form:textarea path="sContent" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">描述信息：</label>
			<div class="controls">
				<form:textarea path="sDescription" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">转发附加信息：</label>
			<div class="controls">
				<form:textarea path="sTranscontent" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
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
			<shiro:hasPermission name="thinkingreport:pbsThinkingreportopt:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>