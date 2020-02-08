<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>社工考勤登记管理</title>
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
<%--<span class="nav-position">当前位置 ：</span><span class="nav-menu"><%=session.getAttribute("activeMenuName")%>></span><span class="nav-menu2">网格员管理</span>--%>
<ul class="back-list">
	<ul class="nav nav-tabs">
		<li class="active" style="width: 112px"><a class="nav-head" href="${ctx}/attendance/ccmWorkerAttendance/gooutlist">数据列表</a></li>
		<%-- <shiro:hasPermission name="attendance:ccmWorkerAttendance:edit"><li><a href="${ctx}/attendance/ccmWorkerAttendance/gooutform">外出登记添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmWorkerAttendance" action="${ctx}/attendance/ccmWorkerAttendance/gooutlist" method="post" class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form pull-left">
			<li class="first-line"><label>类型：</label>
				<form:select path="gooutType" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('ccm_worker_attendance_goout_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="first-line"><label>申请人：</label>
				<form:input path="createByname" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="first-line"><label>事由：</label>
				<form:input path="cause" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="first-line"><label>开始日期：</label>
				<input name="attendanceBegin" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmWorkerAttendance.attendanceBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="first-line"><label>结束日期：</label>
				<input name="attendanceEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmWorkerAttendance.attendanceEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="second-line"><label>天数：</label>
				<form:input path="days" htmlEscape="false" class="input-medium"/>
			</li>

<%--			<li class="clearfix"></li>--%>
		</ul>
		<div class="clearfix pull-right btn-box">
			<shiro:hasPermission name="attendance:ccmWorkerAttendance:edit">
				<input id="btnSubmit" class="btn btn-export" style="width: 75px;display:inline-block;float: right;" type="button" value="添加" onclick="parent.LayerDialog('${ctx}/attendance/ccmWorkerAttendance/gooutform','添加','1100px','700px')"/>

			</shiro:hasPermission>
			<input id="btnSubmit" class="btn btn-primary" style="width: 75px;display:inline-block;float: right;margin-right: 15px" type="submit" value="查询"/>
		</div>
	<sys:message content="${message}"/>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed table-gradient">
		<thead>
			<tr>
				<th>申请人</th>
				<th>部门</th>
				<th>事由</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>天数</th>
				<th>类型</th>
				<th>备注信息</th>
				<shiro:hasPermission name="attendance:ccmWorkerAttendance:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmWorkerAttendance">
			<tr>
				<td style="height: 50px">
					${ccmWorkerAttendance.createByname}
				</td>
				<td style="height: 50px">
					${ccmWorkerAttendance.createBy.office.name}
				</td>
				<td style="height: 50px">
					${ccmWorkerAttendance.cause}
				</td>
				<td style="height: 50px">
					<fmt:formatDate value="${ccmWorkerAttendance.attendanceBegin}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td style="height: 50px">
					<fmt:formatDate value="${ccmWorkerAttendance.attendanceEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td style="height: 50px">
					${ccmWorkerAttendance.days}
				</td>
				<td style="height: 50px">
					${fns:getDictLabel(ccmWorkerAttendance.gooutType, 'ccm_worker_attendance_goout_type', '')}
			   </td>
				<td class="" style="height: 50px">
					${ccmWorkerAttendance.remarks}
				</td>
				<shiro:hasPermission name="attendance:ccmWorkerAttendance:edit"><td style="height: 50px">
    				<a class="btnList" href="${ctx}/attendance/ccmWorkerAttendance/gooutform?id=${ccmWorkerAttendance.id}" title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/attendance/ccmWorkerAttendance/delete?id=${ccmWorkerAttendance.id}&deleteType=1" onclick="return confirmx('确认要删除该社工考勤登记吗？', this.href) title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
</ul>
</body>
</html>