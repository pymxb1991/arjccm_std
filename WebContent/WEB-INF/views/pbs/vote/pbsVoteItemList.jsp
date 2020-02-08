<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>题目选项信息管理</title>
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
		<li class="active"><a href="${ctx}/vote/pbsVoteItem/">题目选项信息列表</a></li>
		<shiro:hasPermission name="vote:pbsVoteItem:edit">
			<li><a href="${ctx}/vote/pbsVoteItem/form">题目选项信息添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsVoteItem"
		action="${ctx}/vote/pbsVoteItem/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>题目名称：</label> <form:input path="sParentid.sName"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li><label>选项名称：</label> <form:input path="sName"
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
				<th>选项名称</th>
				<th>题目名称</th>
				<th>选择数量</th>
				<th>选项顺序</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="vote:pbsVoteItem:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsVoteItem">
				<tr>
					<td><a
						href="${ctx}/vote/pbsVoteItem/form?id=${pbsVoteItem.id}">
							${pbsVoteItem.SName} </a></td>
					<td>${pbsVoteItem.sParentid.SName}</td>
					<td><c:if test="${empty pbsVoteItem.ICnt}">0</c:if>${pbsVoteItem.ICnt}
					</td>
					<td>${fns:getDictLabel(pbsVoteItem.sSort, 'vote_item', '')}</td>
					<td><fmt:formatDate value="${pbsVoteItem.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td class="tp">${pbsVoteItem.remarks}</td>
					<shiro:hasPermission name="vote:pbsVoteItem:edit">
						<td><a
							href="${ctx}/vote/pbsVoteItem/form?id=${pbsVoteItem.id}" title = "修改"><i class="icon icon-pencil"></i></a> <a
							href="${ctx}/vote/pbsVoteItem/delete?id=${pbsVoteItem.id}"
							onclick="return confirmx('确认要删除该题目选项信息吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>