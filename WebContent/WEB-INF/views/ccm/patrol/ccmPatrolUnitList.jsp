<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>巡逻单位管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

			let href=window.location.search.split("sid=")[1];
			if(href){
				$("#sid").attr("value",href)
				console.log(href)
			}
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
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/patrol/ccmPatrolMissions/arrangement">巡逻任务安排</a></li>
		<li class="active"><a href="${ctx}/patrol/ccmPatrolUnit/">巡逻单位任务列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmPatrolUnit" action="${ctx}/patrol/ccmPatrolUnit/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input type="hidden" name="sid" id="sid" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>巡逻民警：</label>
				<sys:treeselect id="user" name="user" value="${ccmPatrolUnit.user}" labelName="userName" labelValue="${ccmPatrolUnit.userName}"
								title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>

			<%--<li><label>巡逻任务：</label>--%>
				<%--<form:select path="missions.id" class="input-medium">--%>
					<%--<form:option value="" label=""/>--%>
					<%--<form:options items="${ccmPatrolUnit}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
				<%--</form:select>--%>
			<%--</li>--%>
			<li><label>时间：</label>
				<input name="departureTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${ccmPatrolUnit.departureTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ccm_patrol_missions_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
			<th>巡逻民警</th>
			<th>巡逻任务</th>
			<th>巡逻车辆</th>
			<th>车载设备</th>
			<th>单兵装备</th>
			<th>时间</th>
			<th>状态</th>
			<th>更新时间</th>
			<th>描述信息</th>
			<shiro:hasPermission name="patrol:ccmPatrolUnit:edit"><th>操作</th></shiro:hasPermission>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmPatrolUnit">
			<tr>
				<td><a href="${ctx}/patrol/ccmPatrolUnit/form2?id=${ccmPatrolUnit.id}">
						${ccmPatrolUnit.user.name}
				</a></td>
				<td>
						${ccmPatrolUnit.missions.patrolContent}
				</td>
				<td>
						${ccmPatrolUnit.patrolVehicles}
				</td>
				<td>
						${ccmPatrolUnit.vehicleEquipment}
				</td>
				<td>
						${ccmPatrolUnit.individualEquipment}
				</td>
				<td>
					<fmt:formatDate value="${ccmPatrolUnit.departureTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${fns:getDictLabel(ccmPatrolUnit.status, 'ccm_patrol_missions_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${ccmPatrolUnit.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${ccmPatrolUnit.remarks}
				</td>
				<shiro:hasPermission name="patrol:ccmPatrolUnit:edit"><td>
					<a href="${ctx}/patrol/ccmPatrolUnit/form2?id=${ccmPatrolUnit.id}">修改</a>
					<a href="${ctx}/patrol/ccmPatrolUnit/delete?id=${ccmPatrolUnit.id}" onclick="return confirmx('确认要删除该巡逻单位吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>