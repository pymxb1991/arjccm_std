<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>流程节点管理</title>
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
<style>
#demo11 {
	border: 10px solid #000;
	border-left-color: #f00;
	width: 0;
	height: 0;
}
</style>

</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/flow/pbsFlownode/">流程节点列表</a></li>
		<shiro:hasPermission name="flow:pbsFlownode:edit">
			<li><a href="${ctx}/flow/pbsFlownode/form">流程节点添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsFlownode"
		action="${ctx}/flow/pbsFlownode/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">

			<li><label>流程模板：</label> <form:input path="sFlowid.sName"
					htmlEscape="false" maxlength="200" class="input-medium" /></li>
			<li><label>节点类型：</label> <form:select path="sNodetype"
					class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('flownode_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
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

				<th>流程节点名称</th>
				<th>流程模板</th>
				<th>当前节点</th>
				<th>节点排序</th>
				<th>节点类型</th>
				<th>更新时间</th>
				<shiro:hasPermission name="flow:pbsFlownode:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsFlownode">
				<tr>
					<td><a
						href="${ctx}/flow/pbsFlownode/form?id=${pbsFlownode.id}">
							${pbsFlownode.SName}</a></td>
					<td>${pbsFlownode.sFlowid.SName}</td>
					<td><c:if test="${ !empty pbsFlownode.sPrevnodeid.SName }">${pbsFlownode.sPrevnodeid.SName}<i class="icon-circle-arrow-right icon-white"></i>
						</c:if> ${pbsFlownode.SName} <c:if
							test="${ !empty pbsFlownode.sNextnodeid.SName }">
							<i class="icon-circle-arrow-right icon-white"></i>${pbsFlownode.sNextnodeid.SName}</c:if>
					</td>
					<td>${pbsFlownode.sSort}</td>
					<td>${fns:getDictLabel(pbsFlownode.sNodetype, 'flownode_type', '')}
					</td>
					<td><fmt:formatDate value="${pbsFlownode.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<shiro:hasPermission name="flow:pbsFlownode:edit">
						<td><a
							href="${ctx}/flow/pbsFlownode/form?id=${pbsFlownode.id}" title = "修改"><i class="icon icon-pencil"></i></a> <a
							href="${ctx}/flow/pbsFlownode/delete?id=${pbsFlownode.id}"
							onclick="return confirmx('确认要删除该流程节点吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>