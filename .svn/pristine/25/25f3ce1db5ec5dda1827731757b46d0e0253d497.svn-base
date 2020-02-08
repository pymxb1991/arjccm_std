<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工作安排记录管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/task/js/pbsTaskrecForm.js"></script>
<script type="text/javascript">
function checkParam(){
		var endTime = $("#endTime").val();
		var currentTime = new Date();
		var endTimeEx = Date.parse(endTime);
		if(endTimeEx < currentTime.getTime()){
			alertx("截至时间不能小于当前系统时间");
			return false;
		}
 		$("#inputForm").submit();
}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/task/pbsTaskrec/">工作安排记录列表</a></li>
		<li class="active"><a
			href="${ctx}/task/pbsTaskrec/form?id=${pbsTaskrec.id}">工作安排记录<shiro:hasPermission
					name="task:pbsTaskrec:edit">${not empty pbsTaskrec.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="task:pbsTaskrec:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsTaskrec"
		action="${ctx}/task/pbsTaskrec/save" method="post"
		class="form-horizontal">
		<div class="hide sSuperiorid" topid="${pbsTaskrec.sSuperiorid.id}"></div>
		<div class="hide sType" topid="${pbsTaskrec.sType.id}"></div>
		<form:hidden path="id" />
		<sys:message content="${message}" />

		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>任务类型：</label>
			<div class="controls">
				<form:select path="sType" class="input-xlarge ">
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上级任务：</label>
			<div class="controls">
				<form:select path="sSuperiorid" class="input-xlarge ">
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>任务简述信息：</label>
			<div class="controls">
				<form:input path="sResume" htmlEscape="false" maxlength="500"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>执行任务人员的部门：</label>
			<div class="controls">
				<sys:treeselect id="sExecdepartment" name="sExecdepartment"
					value="${pbsTaskrec.sExecdepartment.id}" labelName="sExecdepartment"
					labelValue="${pbsTaskrec.sExecdepartment.name}" title="部门"
					url="/sys/office/treeData?type=2" cssClass="input-large required" allowClear="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>执行负责人员：</label>
			<div class="controls">
				<sys:treeselect id="sExecutor" name="sExecutor"
					value="${pbsTaskrec.sExecutor.id}" labelName="sExecutor"
					labelValue="${pbsTaskrec.sExecutor.SName}" title="人员"
					url="/sys/pbsOffice/treeData?type=4" cssClass="input-large required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>任务分配绑定人员：</label>
			<div class="controls">
				<sys:treeselect id="sBindmember" name="sBindmember"
					value="${pbsTaskrec.sBindmember.id}" labelName="sBindmember"
					labelValue="${pbsTaskrec.sBindmember.SName}" title="学员"
					url="/sys/pbsOffice/treeData?type=4" cssClass="input-large required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		
        <div class="control-group">
            <label class="control-label"><font color="red">*&nbsp;</font>截止日期：</label>
            <div class="controls">
				<input name="sDeadline" type="text" readonly="readonly" maxlength="20" id="endTime"
					class="input-xlarge Wdate required"
					value="<fmt:formatDate value="${pbsTaskrec.sDeadline}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
        </div>
        <div class="control-group">
            <label class="control-label"><font color="red">*&nbsp;</font>记录最终状态：</label>
            <c:if test="${not empty pbsTaskrec.id}">
	            <div class="controls">
	                <form:select path="sOptstatus" class="input-xlarge required"  disabled="true">
	                    <form:option value="" label="" />
	                    <form:options items="${fns:getDictList('taskoptstatus')}"
	                        itemLabel="label" itemValue="value" htmlEscape="false" />
	                </form:select>
	            </div>
            </c:if>
            <c:if test="${empty pbsTaskrec.id}">
	            <div class="controls">
	                <form:select path="sOptstatus" class="input-xlarge required">
	                    <form:option value="" label="" />
	                    <form:options items="${fns:getDictList('taskoptstatus')}"
	                        itemLabel="label" itemValue="value" htmlEscape="false" />
	                </form:select>
	            </div>
            </c:if>
        </div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>运行状态：</label>
			 <c:if test="${not empty pbsTaskrec.id}">
				<div class="controls">
					<form:select path="sStatus" class="input-xlarge required" disabled="true">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('taskstatus')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</div>
			</c:if>
			<c:if test="${empty pbsTaskrec.id}">
				<div class="controls">
					<form:select path="sStatus" class="input-xlarge required">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('taskstatus')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</div>
			</c:if>
		</div>
		<div class="control-group">
			<label class="control-label">任务内容：</label>
			<div class="controls">
				<form:textarea path="sContent" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="task:pbsTaskrec:edit">
				<input id="btnSubmit" class="btn btn-primary" type="button" onclick="checkParam()"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>