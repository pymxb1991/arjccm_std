<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>学员表关系管理</title>
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
		<li class="active"><a href="${ctx}/person/pbsPartymembind/">学员用户关系列表</a></li>
		<%-- <shiro:hasPermission name="person:pbsPartymembind:edit">
			<li><a href="${ctx}/person/pbsPartymembind/form">学员表关系添加</a></li>
		</shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsPartymembind"
		action="${ctx}/person/pbsPartymembind/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			  <li><label>用户名称：</label> <form:input path="username"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li><label>学员名称：</label> <form:input path="partymemname"
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
				<th>用户名称</th>
				<th>学员名称</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="person:pbsPartymembind:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsPartymembind">
				<tr>
					<td><a
						href="${ctx}/person/pbsPartymembind/form?id=${pbsPartymembind.id}">
							${pbsPartymembind.username}</a></td>
					<td>${pbsPartymembind.partymemname}</td>
					<td><fmt:formatDate value="${pbsPartymembind.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${pbsPartymembind.remarks}</td>
					<shiro:hasPermission name="person:pbsPartymembind:edit">
						<td><a
							href="${ctx}/person/pbsPartymembind/form?id=${pbsPartymembind.id}" title = "修改"><i class="icon icon-pencil"></i></a>
							<a
							href="${ctx}/person/pbsPartymembind/delete?id=${pbsPartymembind.id}"
							onclick="return confirmx('确认要删除该学员表关系吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>