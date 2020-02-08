<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>巡逻任务管理</title>
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
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/patrol/ccmPatrolMissions/summaryGraph">统计数据</a></li>
		<li class="active"><a href="${ctx}/patrol/ccmPatrolMissions/">数据列表</a></li>
		<shiro:hasPermission name="patrol:ccmPatrolMissions:edit"><li><a href="${ctx}/patrol/ccmPatrolMissions/form">巡逻任务添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmPatrolMissions" action="${ctx}/patrol/ccmPatrolMissions/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>巡逻时间：</label>
				<input name="patrolTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmPatrolMissions.patrolTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>结束时间：</label>
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${ccmPatrolMissions.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>参与单位：</label>
				<sys:treeselect id="office" name="office" value="${ccmPatrolMissions.office}" labelName="officeName" labelValue="${ccmPatrolMissions.officeName}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" checked="true" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>巡逻辖区：</label>
				<sys:treeselect id="area" name="area.id" value="${ccmPatrolMissions.area.id}" labelName="area.name" labelValue="${ccmPatrolMissions.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>巡逻任务</th>
				<th>巡逻时间</th>
				<th>结束时间</th>
				<th>参与单位</th>
				<th>每个单位人数</th>
				<th>巡逻路线</th>
				<th>巡逻辖区</th>
				<th>状态</th>
				<th>审核状态</th>
				<th>更新时间</th>
				<th>描述信息</th>
				<shiro:hasPermission name="patrol:ccmPatrolMissions:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmPatrolMissions">
			<tr>
				<td><a href="${ctx}/patrol/ccmPatrolMissions/form?id=${ccmPatrolMissions.id}">
					${ccmPatrolMissions.patrolContent}
				</a></td>
				<td>
					<fmt:formatDate value="${ccmPatrolMissions.patrolTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${ccmPatrolMissions.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${ccmPatrolMissions.officeName}
				</td>
				<td>
					${ccmPatrolMissions.number}
				</td>
				<td>
					${ccmPatrolMissions.patrolRoutes}
				</td>
				<td>
					${ccmPatrolMissions.area.name}
				</td>
				<td>
					${fns:getDictLabel(ccmPatrolMissions.status, 'ccm_patrol_missions_status', '')}
				</td>
				<td>
					${fns:getDictLabel(ccmPatrolMissions.auditingStatus, 'auditing_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${ccmPatrolMissions.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${ccmPatrolMissions.remarks}
				</td>
				<shiro:hasPermission name="patrol:ccmPatrolMissions:edit"><td>
    				<a href="${ctx}/patrol/ccmPatrolMissions/form?id=${ccmPatrolMissions.id}">修改</a>
					<a href="${ctx}/patrol/ccmPatrolMissions/delete?id=${ccmPatrolMissions.id}" onclick="return confirmx('确认要删除该巡逻任务吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>