<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工作安排操作记录管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/task/pbsTaskoprec/">工作安排操作记录列表</a></li>
		<%-- <shiro:hasPermission name="task:pbsTaskoprec:edit">
			<li><a href="${ctx}/task/pbsTaskoprec/form">工作安排操作记录添加</a></li>
		</shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsTaskoprec"
		action="${ctx}/task/pbsTaskoprec/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>任务名称：</label> <form:input path="sTaskid.sResume"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li>
				<label>任务操作类型：</label>
				<form:select path="sType" class="input-medium">
					<form:option value=""  label="全部"></form:option>
					<form:options items="${fns:getDictList('taskopttype')}" itemLabel="label" itemValue="value" htmlEscape="false"></form:options>
				</form:select>
			</li>
			<%-- <li><label>执行任务人员的部门编号：</label>
				<sys:treeselect id="sExecdepartment" name="sExecdepartment" value="${pbsTaskoprec.sExecdepartment}" labelName="" labelValue="${pbsTaskoprec.}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li> 
			<li><label>执行负责人员：</label>
				<sys:treeselect id="sExecutor" name="sExecutor" value="${pbsTaskoprec.sExecutor}" labelName="" labelValue="${pbsTaskoprec.}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>--%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>任务操作类型</th>
				<th>任务名称</th>
				<th>发布人部门</th>
				<th>发布人</th>
				<th>执行负责人员</th>
				<th>更新时间</th>
				<shiro:hasPermission name="task:pbsTaskoprec:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsTaskoprec">
				<tr>
					<td><a
						href="${ctx}/task/pbsTaskoprec/form?id=${pbsTaskoprec.id}">
							${fns:getDictLabel(pbsTaskoprec.SType, 'taskopttype', '')} </a></td>
					<td>${pbsTaskoprec.sTaskid.SResume}</td>
					<td>${pbsTaskoprec.sExecdepartment.name}</td>
					<td>${pbsTaskoprec.sExecutor.SName}</td>
					<td>${pbsTaskoprec.sBindmember.SName}</td>
					<td><fmt:formatDate value="${pbsTaskoprec.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<shiro:hasPermission name="task:pbsTaskoprec:edit">
						<td><a
							href="${ctx}/task/pbsTaskoprec/form?id=${pbsTaskoprec.id}" title = "修改"><i class="icon icon-pencil"></i></a>
							<a href="${ctx}/task/pbsTaskoprec/delete?id=${pbsTaskoprec.id}"
							onclick="return confirmx('确认要删除该工作安排操作记录吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>