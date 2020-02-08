<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工作安排记录管理</title>
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
		<li class="active"><a href="${ctx}/task/pbsTaskrec/">工作安排记录列表</a></li>
		<shiro:hasPermission name="task:pbsTaskrec:edit">
			<li><a href="${ctx}/task/pbsTaskrec/form">工作安排记录添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsTaskrec"
		action="${ctx}/task/pbsTaskrec/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>上级任务：</label> <form:input path="sSuperiorid.sResume"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li><label>任务简述信息：</label> <form:input path="sResume"
					htmlEscape="false" maxlength="500" class="input-medium" /></li>
			<li><label>发布人员：</label> <form:input path="sExecutor.sName"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
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
				<th>任务简述信息</th>
				<th>任务类型</th>
				<th>上级任务</th>
				<th>发布人部门</th>
				<th>发布人员</th>
				<th>任务接收学员</th>
				<th>更新时间</th>
				<!-- <th>备注信息</th> -->
				<shiro:hasPermission name="task:pbsTaskrec:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsTaskrec">
				<tr>
					<td><a href="${ctx}/task/pbsTaskrec/form?id=${pbsTaskrec.id}">
						${pbsTaskrec.SResume}	 </a></td>
					<td>${pbsTaskrec.sType.SName}</td>
					<td>${pbsTaskrec.sSuperiorid.SResume}</td>
					<td>${pbsTaskrec.sExecdepartment.name}</td>
					<td>${pbsTaskrec.sExecutor.SName}</td>
					<td>${pbsTaskrec.sBindmember.SName}</td>
					<td><fmt:formatDate value="${pbsTaskrec.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<%-- <td>${pbsTaskrec.remarks}</td> --%>
					<shiro:hasPermission name="task:pbsTaskrec:edit">
						<td><a href="${ctx}/task/pbsTaskrec/form?id=${pbsTaskrec.id}" title = "修改"><i class="icon icon-pencil"></i></a>
							<a href="${ctx}/task/pbsTaskrec/delete?id=${pbsTaskrec.id}"
							onclick="return confirmx('确认要删除该工作安排记录吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>