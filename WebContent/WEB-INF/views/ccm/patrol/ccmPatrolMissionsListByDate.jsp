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
				<th>更新时间</th>
				<th>描述信息</th>
				<shiro:hasPermission name="patrol:ccmPatrolMissions:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="ccmPatrolMissions">
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
					<fmt:formatDate value="${ccmPatrolMissions.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${ccmPatrolMissions.remarks}
				</td>
				<shiro:hasPermission name="patrol:ccmPatrolMissions:edit"><td>
    				<a href="${ctx}/patrol/ccmPatrolUnit/form?id=${ccmPatrolMissions.id}">任务安排</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>