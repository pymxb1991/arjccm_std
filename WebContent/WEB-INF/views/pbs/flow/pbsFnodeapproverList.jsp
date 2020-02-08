<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>流程审核人信息管理</title>
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
		<li class="active"><a href="${ctx}/flow/pbsFnodeapprover/">流程审核人信息列表</a></li>
		<shiro:hasPermission name="flow:pbsFnodeapprover:edit">
			<li><a href="${ctx}/flow/pbsFnodeapprover/form">流程审核人信息添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsFnodeapprover"
		action="${ctx}/flow/pbsFnodeapprover/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>所属流程：</label> <form:input path="sFlowid.sName"
					htmlEscape="false" maxlength="200" class="input-medium" /></li>
			<li><label>所属节点：</label> <form:input path="sFlownodeid.sName"
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
				<th>所属流程</th>
				<th>所属节点</th>
				<th>部门</th>
				<th>学员编号</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="flow:pbsFnodeapprover:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsFnodeapprover">
				<tr>
					<td><a
						href="${ctx}/flow/pbsFnodeapprover/form?id=${pbsFnodeapprover.id}">
							${pbsFnodeapprover.sFlowid.SName} </a></td>
					<td>${pbsFnodeapprover.sFlownodeid.SName}</td>
					<td>${pbsFnodeapprover.sDepartmentid.name}</td>
					<td>${pbsFnodeapprover.sApprover.SName}</td>
					<td><fmt:formatDate value="${pbsFnodeapprover.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${pbsFnodeapprover.remarks}</td>
					<shiro:hasPermission name="flow:pbsFnodeapprover:edit">
						<td><a
							href="${ctx}/flow/pbsFnodeapprover/form?id=${pbsFnodeapprover.id}" title = "修改"><i class="icon icon-pencil"></i></a>
							<a
							href="${ctx}/flow/pbsFnodeapprover/delete?id=${pbsFnodeapprover.id}"
							onclick="return confirmx('确认要删除该流程审核人信息吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>

