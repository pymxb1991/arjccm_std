<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>警卫管理</title>
	<meta name="decorator" content="default"/>

</head>
<body>

	<div class="control-group">
		<label >警卫任务</label>
	</div>
	<form:form id="inputForm" modelAttribute="ccmPatrolSecurity" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">巡逻民警：</label>--%>
			<%--<div class="controls">--%>
				<%--<sys:treeselect id="user" name="user.id" disabled="true" value="${ccmPatrolSecurity.user.id}" labelName="user.name" labelValue="${ccmPatrolSecurity.user.name}"--%>
					<%--title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="control-group">
			<label class="control-label">任务标题：</label>
			<div class="controls">
				<form:textarea path="title" disabled="true" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">警卫时间：</label>
			<div class="controls">
				<input name="securityTime" type="text" disabled="disabled" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${ccmPatrolSecurity.securityTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束时间：</label>
			<div class="controls">
				<input name="endTime" type="text" disabled="disabled" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					   value="<fmt:formatDate value="${ccmPatrolSecurity.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参与单位：</label>
			<div class="controls">
				<sys:treeselect id="office" name="office" disabled="disabled" value="${ccmPatrolSecurity.office}" labelName="officeName" labelValue="${ccmPatrolSecurity.officeName}"
					title="部门" url="/sys/office/treeData?type=2" checked="true" cssClass="input-xxlarge  required" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单位人数：</label>
			<div class="controls">
				<form:input path="numberUnits" disabled="true" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">警卫线路：</label>
			<div class="controls">
				<form:input path="guardLine" disabled="true" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">集合时间：</label>
			<div class="controls">
				<input name="collectionTime" disabled="true" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate  required"
					value="<fmt:formatDate value="${ccmPatrolSecurity.collectionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">集合地点：</label>
			<div class="controls">
				<form:input path="collectionPlace" disabled="true" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:select path="status" disabled="true" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ccm_patrol_missions_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核状态：</label>
			<div class="controls">
				<form:select path="auditingStatus" disabled="true" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('auditing_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述信息：</label>
			<div class="controls">
				<form:textarea path="remarks" disabled="true" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
	</form:form>
</body>
</html>