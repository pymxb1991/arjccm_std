<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>活动定义管理</title>
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
		<li class="active"><a href="${ctx}/activity/pbsActivitytype/">活动定义列表</a></li>
		<shiro:hasPermission name="activity:pbsActivitytype:edit">
			<li><a href="${ctx}/activity/pbsActivitytype/form">活动定义添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsActivitytype"
		action="${ctx}/activity/pbsActivitytype/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>活动名称：</label> <form:input path="sName"
					htmlEscape="false" maxlength="200" class="input-medium" /></li>
			<li><label>自定义分组标识：</label> <form:select path="sGroup"
					class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('actdefinitiontype')}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
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
				<th>活动名称</th>
				<!-- <th>工作流</th> -->
				<th>自定义分组标识</th>
				<th>更新时间</th>
				<!-- <th>备注信息</th> -->
				<shiro:hasPermission name="activity:pbsActivitytype:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsActivitytype">
				<tr>
					<td><a
						href="${ctx}/activity/pbsActivitytype/form?id=${pbsActivitytype.id}">
							${pbsActivitytype.SName} </a></td>
					<%--<td>
					${fns:getDictLabel(pbsActivitytype.SFlowdefinitionid, '', '')}
				</td> --%>
					<td>${fns:getDictLabel(pbsActivitytype.SGroup, 'actdefinitiontype', '')}</td>
					<td><fmt:formatDate value="${pbsActivitytype.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<%-- <td>${pbsActivitytype.remarks}</td> --%>
					<shiro:hasPermission name="activity:pbsActivitytype:edit">
						<td><a
							href="${ctx}/activity/pbsActivitytype/form?id=${pbsActivitytype.id}" title = "修改"><i class="icon icon-pencil"></i></a>
							<a
							href="${ctx}/activity/pbsActivitytype/delete?id=${pbsActivitytype.id}"
							onclick="return confirmx('确认要删除该活动定义吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>