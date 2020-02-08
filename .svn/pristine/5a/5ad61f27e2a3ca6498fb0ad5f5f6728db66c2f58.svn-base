<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>投票主题用户信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/vote/pbsVoteUser/">投票主题用户信息列表</a></li>
		<shiro:hasPermission name="vote:pbsVoteUser:edit"><li><a href="${ctx}/vote/pbsVoteUser/form">投票主题用户信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsVoteUser" action="${ctx}/vote/pbsVoteUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="vote:pbsVoteUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsVoteUser">
			<tr>
				<td><a href="${ctx}/vote/pbsVoteUser/form?id=${pbsVoteUser.id}">
					<fmt:formatDate value="${pbsVoteUser.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${pbsVoteUser.remarks}
				</td>
				<shiro:hasPermission name="vote:pbsVoteUser:edit"><td>
    				<a href="${ctx}/vote/pbsVoteUser/form?id=${pbsVoteUser.id}" title = "修改"><i class="icon icon-pencil"></i></a>
					<a href="${ctx}/vote/pbsVoteUser/delete?id=${pbsVoteUser.id}" onclick="return confirmx('确认要删除该投票主题用户信息吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>