<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>选择题选项管理</title>
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
		<li class="active"><a href="${ctx}/question/pbsChoiceItem/">选择题选项列表</a></li>
		<shiro:hasPermission name="question:pbsChoiceItem:edit">
			<li><a href="${ctx}/question/pbsChoiceItem/form">选择题选项添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsChoiceItem"
		action="${ctx}/question/pbsChoiceItem/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>选择题编号：</label> <form:input path="sParentid.sBody"
					htmlEscape="false" maxlength="10" class="input-medium" /></li>
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
				<th>选项内容</th>
				<th>选择题编号</th>
				<th>选择题类型</th>
				<th>选择题键值</th>
				<th>更新时间</th>
				<shiro:hasPermission name="question:pbsChoiceItem:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsChoiceItem">
				<tr>
					<td><a
						href="${ctx}/question/pbsChoiceItem/form?id=${pbsChoiceItem.id}">${pbsChoiceItem.SBody}</a>
					</td>
					<td>${pbsChoiceItem.sParentid.SBody}</td>
					<td>${fns:getDictLabel(pbsChoiceItem.SType, 'objectivetype', '')}
					</td>
					<td>${fns:getDictLabel(pbsChoiceItem.SKey, 'vote_item', '')}</td>
					<td><fmt:formatDate value="${pbsChoiceItem.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<shiro:hasPermission name="question:pbsChoiceItem:edit">
						<td><a
							href="${ctx}/question/pbsChoiceItem/form?id=${pbsChoiceItem.id}" title = "修改"><i class="icon icon-pencil"></i></a>
							<a
							href="${ctx}/question/pbsChoiceItem/delete?id=${pbsChoiceItem.id}"
							onclick="return confirmx('确认要删除该选择题选项吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>