<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工作节点记录管理</title>
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
		<li class="active"><a href="${ctx}/flow/pbsFlowworknode/">工作节点记录列表</a></li>
		<shiro:hasPermission name="flow:pbsFlowworknode:edit">
			<li><a href="${ctx}/flow/pbsFlowworknode/form">工作节点记录添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsFlowworknode"
		action="${ctx}/flow/pbsFlowworknode/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>任务节点：</label> <form:input path="sName"
					htmlEscape="false" maxlength="200" class="input-medium" /></li>
			<li><label>所属工作模板：</label> <form:input path="sFlowid.sName"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li><label>所属节点：</label> <form:input path="sNodeid.sName"
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
				<th>任务节点</th>
				<th>所属工作模板</th>
				<th>所属节点</th>
				<th>是否完成</th>
				<th>操作人员</th>
				<th>学员信息</th>
				<th>更新时间</th>
				<shiro:hasPermission name="flow:pbsFlowworknode:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsFlowworknode">
				<tr>
					<td><a
						href="${ctx}/flow/pbsFlowworknode/form?id=${pbsFlowworknode.id}">
							${pbsFlowworknode.SName} </a></td>
					<td>${pbsFlowworknode.sFlowid.SName}</td>
					<td>${pbsFlowworknode.sNodeid.SName}</td>
					<td>${fns:getDictLabel(pbsFlowworknode.SSetstatval, 'yes_no', '')}</td>
					<td>${pbsFlowworknode.sOperator.name}</td>
					<td>${pbsFlowworknode.sBindmember.SName}</td>
					<td><fmt:formatDate value="${pbsFlowworknode.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<shiro:hasPermission name="flow:pbsFlowworknode:edit">
						<td>
						<c:if test="${pbsFlowworknode.SSetstatval eq '0'}">
						<a href="${ctx}/flow/pbsFlowworknode/passhandle?id=${pbsFlowworknode.id}"
                            onclick="return confirmx('确认要审核通过该节点吗？', this.href)" title = "通过"><i class="icon-check"></i></a>
                        <a href="${ctx}/flow/pbsFlowworknode/refusehandle?id=${pbsFlowworknode.id}"
                            onclick="return confirmx('确认要审核驳回该节点吗？', this.href)" title = "驳回"><i class="icon-remove-circle"></i></a>
                            </c:if>
						<a href="${ctx}/flow/pbsFlowworknode/form?id=${pbsFlowworknode.id}" title = "修改"><i class="icon icon-pencil"></i></a>
						<a href="${ctx}/flow/pbsFlowworknode/delete?id=${pbsFlowworknode.id}"
							onclick="return confirmx('确认要删除该工作节点记录吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a> 
						
						</td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>