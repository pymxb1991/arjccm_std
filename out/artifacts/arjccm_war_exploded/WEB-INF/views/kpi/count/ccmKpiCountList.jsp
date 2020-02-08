<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>绩效统计记录管理</title>
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
<style>
</style>
<body>
<%--	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/count/ccmKpiCount/">绩效统计记录列表</a></li>
		<shiro:hasPermission name="count:ccmKpiCount:edit"><li><a href="${ctx}/count/ccmKpiCount/form">绩效统计记录添加</a></li></shiro:hasPermission>
	</ul>--%>
	<form:form id="searchForm" modelAttribute="ccmKpiCount" action="${ctx}/count/ccmKpiCount/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
<%--		<ul class="ul-form">
			<li><label>用户名：</label>
				<form:input path="userName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>--%>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户名</th>
				<th>重点人员帮教次数</th>
				<th>新增重点人员数量</th>
				<th>新增的房东和租住人数量</th>
				<th>新增重点机构数量</th>
				<th>新增消防设施数量</th>
				<th>工作日志次数</th>
				<th>备勤次数</th>
				<th>备勤时长</th>
				<th>巡逻次数</th>
				<th>巡逻时长</th>
				<th>警卫任务次数</th>
				<th>总计次数</th>
				<%--<shiro:hasPermission name="count:ccmKpiCount:edit"><th>操作</th></shiro:hasPermission>--%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmKpiCount">
			<tr>
				<td><a href="${ctx}/count/ccmKpiCount/form?id=${ccmKpiCount.id}">
					${ccmKpiCount.userName}
				</a></td>
				<td>
					${ccmKpiCount.tailTimes}
				</td>
				<td>
					${ccmKpiCount.peopleNumber}
				</td>
				<td>
					${ccmKpiCount.tenantNumber}
				</td>
				<td>
					${ccmKpiCount.orgNumber}
				</td>
				<td>
					${ccmKpiCount.componentsNumber}
				</td>
				<td>
					${ccmKpiCount.reportsTimes}
				</td>
				<td>
					${ccmKpiCount.reliefTimes}
				</td>
				<td>
					${ccmKpiCount.reliefTime}
				</td>
				<td>
					${ccmKpiCount.patrolTimes}
				</td>
				<td>
					${ccmKpiCount.patrolTime}
				</td>
				<td>
					${ccmKpiCount.policeTaskTimes}
				</td>
				<td>
					哈哈嗝
				</td>
<%--				<shiro:hasPermission name="count:ccmKpiCount:edit"><td>
    				<a href="${ctx}/count/ccmKpiCount/form?id=${ccmKpiCount.id}">修改</a>
					<a href="${ctx}/count/ccmKpiCount/delete?id=${ccmKpiCount.id}" onclick="return confirmx('确认要删除该绩效统计记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>--%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>