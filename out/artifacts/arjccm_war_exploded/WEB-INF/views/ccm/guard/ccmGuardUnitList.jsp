<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>警卫单位管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			let href=window.location.search.split("sid=")[1];
			if(href){
				$("#sid").attr("value",href);
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
		<li><a href="${ctx}/guard/ccmGuardUnit/securityList">警卫任务</a></li>
		<li class="active"><a href="${ctx}/guard/ccmGuardUnit/">警卫单位列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmGuardUnit" action="${ctx}/guard/ccmGuardUnit/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>警卫：</label>
				<sys:treeselect id="user" name="user.id" value="${ccmGuardUnit.user.id}" labelName="user.name" labelValue="${ccmGuardUnit.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<input  style="display: none;" type="text" name="sid" id="sid" />
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>警卫任务</th>
				<th>警卫</th>
				<!-- 
				<th>车辆</th>
				<th>车载设备</th>-->
				<th>警卫路线</th>
				<th>时间</th>
				<th>状态</th>
				<th>更新时间</th>
				<th>描述信息</th>
				<shiro:hasPermission name="guard:ccmGuardUnit:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmGuardUnit">
			<tr>
				<td><a href="${ctx}/guard/ccmGuardUnit/form?id=${ccmGuardUnit.id}">
					${ccmGuardUnit.security.title}
				</a></td>
				<td>
					${ccmGuardUnit.user.name}
				</td>
				<!-- 
				<td>
					${ccmGuardUnit.patrolVehicles}
				</td>
				<td>
					${ccmGuardUnit.vehicleEquipment}
				</td>
				-->
				<td>
					${ccmGuardUnit.guardLine}
				</td>
				<td>
					<fmt:formatDate value="${ccmGuardUnit.departureTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(ccmGuardUnit.status, 'ccm_patrol_missions_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${ccmGuardUnit.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${ccmGuardUnit.remarks}
				</td>
				<shiro:hasPermission name="guard:ccmGuardUnit:edit"><td>
    				<a href="${ctx}/guard/ccmGuardUnit/form?id=${ccmGuardUnit.id}">修改</a>
					<a href="${ctx}/guard/ccmGuardUnit/delete?id=${ccmGuardUnit.id}" onclick="return confirmx('确认要删除该警卫单位吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>