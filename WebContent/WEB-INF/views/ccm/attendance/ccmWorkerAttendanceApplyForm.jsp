<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>加班请假申请管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					$("#btnSubmit").attr("disabled", true);
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#btnSubmit").removeAttr('disabled');
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
	<link href="/arjccm/static/bootstrap/2.3.1/css_input/input_Custom.css" type="text/css" rel="stylesheet">
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a style="width: 140px;text-align:center" href="${ctx}/attendanceapply/ccmWorkerAttendanceApply?type=${type}"><c:if test="${type==2}">请假</c:if><c:if test="${type==3}">加班</c:if>审核列表</a></li>
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/attendanceapply/ccmWorkerAttendanceApply/form?id=${ccmWorkerAttendanceApply.id}"><c:if test="${type==2}">请假</c:if><c:if test="${type==3}">加班</c:if>审核<shiro:hasPermission name="attendanceapply:ccmWorkerAttendanceApply:edit">${not empty ccmWorkerAttendanceApply.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="attendanceapply:ccmWorkerAttendanceApply:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="ccmWorkerAttendanceApply" action="${ctx}/attendanceapply/ccmWorkerAttendanceApply/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<c:if test="${type==2}">
			<div class="control-group" style="padding-top: 8px">
				<label class="control-label">请假类型：</label>
				<div class="controls">
					<form:select path="leaveType" class="input-medium" disabled="true">
						<form:option value="" label="全部"/>
						<form:options items="${fns:getDictList('ccm_worker_attendance_leave_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</div>
			</div>
		</c:if>
		<c:if test="${type==3}">
		<div class="control-group" style="padding-top: 8px">
			<label class="control-label">加班类型：</label>
			<div class="controls">
				<form:select path="workingtimeType" class="input-medium" disabled="true">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('ccm_worker_attendance_workingtime_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">开始时间：</label>
			<div class="controls">
				<input name="attendanceBegin" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${ccmWorkerAttendanceApply.attendanceBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					/>  <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束时间：</label>
			<div class="controls">
				<input name="attendanceEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${ccmWorkerAttendanceApply.attendanceEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					/>  <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">天数：</label>
			<div class="controls">
				<form:input path="days" htmlEscape="false" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地点：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">事由：</label>
			<div class="controls">
				<form:input path="cause" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核状态：</label>
			<div class="controls">
                <c:if test="${ccmWorkerAttendanceApply.applyType == 2}">
                    <form:select path="applyType" class="input-medium" disabled="true">
                        <form:options items="${fns:getDictList('worker_type')}" itemLabel="label" itemValue="value" htmlEscape="false" />
                    </form:select>
                </c:if>
                <c:if test="${ccmWorkerAttendanceApply.applyType != 2}">
                    <form:select path="applyType" class="input-medium">
                        <form:options items="${fns:getDictList('worker_type')}" itemLabel="label" itemValue="value" htmlEscape="false" />
                    </form:select>
                </c:if>
			</div>
			<%--<div class="controls">
				${fns:getDictLabel(ccmWorkerAttendanceApply.applyType, 'worker_type', '')}
			&lt;%&ndash;	<form:input path="applyType" htmlEscape="false" maxlength="2" class="input-xlarge "/>&ndash;%&gt;
			</div>--%>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="attendanceapply:ccmWorkerAttendanceApply:edit">
                <c:if test="${ccmWorkerAttendanceApply.applyType != 2}">
                    <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
                </c:if>
            </shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>