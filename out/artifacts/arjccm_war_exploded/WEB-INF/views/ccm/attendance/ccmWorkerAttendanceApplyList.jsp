<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>加班请假申请管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
<%--<img  src="${ctxStatic}/images/shouyedaohang.png"; class="nav-home">--%>
<%--<span class="nav-position">当前位置 ：</span><span class="nav-menu"><%=session.getAttribute("activeMenuName")%>></span><span class="nav-menu2">事件管理</span>--%>
<ul class="back-list">
	<ul class="nav nav-tabs">
		<li class="active"><a class="nav-head" href="${ctx}/attendanceapply/ccmWorkerAttendanceApply?type=${type}"><c:if test="${type == 3}">加班</c:if><c:if test="${type == 2}">请假</c:if>申请列表</a></li>
		<%--<shiro:hasPermission name="attendanceapply:ccmWorkerAttendanceApply:edit"><li><a href="${ctx}/attendanceapply/ccmWorkerAttendanceApply/form">加班请假申请添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmWorkerAttendanceApply" action="${ctx}/attendanceapply/ccmWorkerAttendanceApply?type=${type}" method="post" class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form pull-left">
			<%--<li><label>外出类型：</label>
				<form:input path="gooutType" htmlEscape="false" maxlength="2" class="input-medium"/>
			</li>--%>
			<c:if test="${type == 2}">
				<li class="first-line"><label>请假类型：</label>
					<form:select path="leaveType" class="input-medium">
						<form:option value="" label="全部"/>
						<form:options items="${fns:getDictList('ccm_worker_attendance_leave_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</li>
			</c:if>
			<c:if test="${type == 3}">
				<li class="first-line"><label>加班类型：</label>
					<form:select path="workingtimeType" class="input-medium">
						<form:option value="" label="全部"/>
						<form:options items="${fns:getDictList('ccm_worker_attendance_workingtime_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</li>
			</c:if>
			<li class="first-line"><label>开始日期：</label>
				<input name="attendanceBegin" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmWorkerAttendanceApply.attendanceBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="first-line"><label>结束日期：</label>
				<input name="attendanceEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmWorkerAttendanceApply.attendanceEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
		<%--	<li><label>事由：</label>
				<form:input path="cause" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>--%>
			<li class="first-line"><label>申请状态：</label>
				<form:select path="applyType" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('worker_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>

<%--			<li class="clearfix"></li>--%>
		</ul>
	<sys:message content="${message}"/>
	<div class="clearfix pull-right btn-box">
		<input id="btnSubmit" class="btn btn-primary" style="width: 75px;display:inline-block;float: right;" type="submit" value="查询"/>
	</div>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed table-gradient">
		<thead>
			<tr>
				<th>申请人</th>
				<th>部门</th>
				<c:if test="${type == 2}">
					<th>请假类型</th>
				</c:if>
				<c:if test="${type == 3}">
					<th>加班类型</th>
				</c:if>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>天数</th>
				<th>地点</th>
				<th>事由</th>
				<th>申请状态</th>
				<shiro:hasPermission name="attendanceapply:ccmWorkerAttendanceApply:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmWorkerAttendanceApply">
			<tr>
				<td style="height: 50px"><a href="${ctx}/attendanceapply/ccmWorkerAttendanceApply/form?id=${ccmWorkerAttendanceApply.id}">
					${ccmWorkerAttendanceApply.createByname}
				</a></td>
				<td style="height: 50px">
					${ccmWorkerAttendanceApply.createBy.office.name}
				</td>
				<td style="height: 50px">
					<c:if test="${type == 2}">
						${fns:getDictLabel(ccmWorkerAttendanceApply.leaveType, 'ccm_worker_attendance_leave_type', '')}
					</c:if>
					<c:if test="${type == 3}">
						${fns:getDictLabel(ccmWorkerAttendanceApply.workingtimeType, 'ccm_worker_attendance_workingtime_type', '')}
					</c:if>
				</td>
				<td style="height: 50px">
					<fmt:formatDate value="${ccmWorkerAttendanceApply.attendanceBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td style="height: 50px">
					<fmt:formatDate value="${ccmWorkerAttendanceApply.attendanceEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td style="height: 50px">
					${ccmWorkerAttendanceApply.days}
				</td>
				<td style="height: 50px">
					${ccmWorkerAttendanceApply.address}
				</td>
				<td style="height: 50px">
					${ccmWorkerAttendanceApply.cause}
				</td>
				<td style="height: 50px">
					${fns:getDictLabel(ccmWorkerAttendanceApply.applyType, 'worker_type', '')}
				</td>
				<shiro:hasPermission name="attendanceapply:ccmWorkerAttendanceApply:edit"><td style="height: 50px">
    				<a href="${ctx}/attendanceapply/ccmWorkerAttendanceApply/form?id=${ccmWorkerAttendanceApply.id}&type=${type}" title="审核"><i class="icon-pencil"></i></a>
					<%--<a href="${ctx}/attendanceapply/ccmWorkerAttendanceApply/delete?id=${ccmWorkerAttendanceApply.id}" onclick="return confirmx('确认要删除该加班请假申请吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
</body>
</html>