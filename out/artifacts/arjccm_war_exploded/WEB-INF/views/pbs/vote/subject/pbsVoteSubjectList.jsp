<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>题目管理</title>
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
		<li class="active"><a href="${ctx}/vote/pbsVoteSubject/">题目列表</a></li>
		<shiro:hasPermission name="vote:pbsVoteSubject:edit">
			<li><a href="${ctx}/vote/pbsVoteSubject/form">题目添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsVoteSubject"
		action="${ctx}/vote/pbsVoteSubject/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>题目名称：</label> <form:input path="sName"
					htmlEscape="false" maxlength="500" class="input-medium" /></li>
			<li><label>主题名称：</label> <form:input path="sParentid.sName"
					htmlEscape="false" maxlength="500" class="input-medium" /></li>
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
				<th>题目名称</th>
				<th>主题名称</th>
				<th>题目模式</th>
				<th>参与人数</th>
				<th>更新时间</th>
				<shiro:hasPermission name="vote:pbsVoteSubject:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsVoteSubject">
				<tr>
					<td><a
						href="${ctx}/vote/pbsVoteSubject/form?id=${pbsVoteSubject.id}">
							${pbsVoteSubject.SName} </a></td>
					<td>${pbsVoteSubject.sParentid.SName}</td>
					<td>${fns:getDictLabel(pbsVoteSubject.sMode, 'vote_type', '')}
					</td>
					<td>${pbsVoteSubject.sSum}</td>

					<td><fmt:formatDate value="${pbsVoteSubject.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<shiro:hasPermission name="vote:pbsVoteSubject:edit">
						<td><a
							href="${ctx}/vote/pbsVoteSubject/form?id=${pbsVoteSubject.id}" title = "修改"><i class="icon icon-pencil"></i></a>
							<a
							href="${ctx}/vote/pbsVoteSubject/delete?id=${pbsVoteSubject.id}"
							onclick="return confirmx('确认要删除该题目吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>