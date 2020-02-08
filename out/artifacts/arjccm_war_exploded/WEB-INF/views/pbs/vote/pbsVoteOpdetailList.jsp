<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>投票个人选项信息管理</title>
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
		<li class="active"><a href="${ctx}/vote/pbsVoteOpdetail/">投票/调查选项信息</a></li>
		<%-- <shiro:hasPermission name="vote:pbsVoteOpdetail:edit">
			<li><a href="${ctx}/vote/pbsVoteOpdetail/form">投票个人选项信息添加</a></li>
		</shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsVoteOpdetail"
		action="${ctx}/vote/pbsVoteOpdetail/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<%-- <li><label>所属选项：</label>
				<form:input path="sItem" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li> --%>
			<li><label>投票/调查题目：</label> <form:input path="sSubjectName"
                    htmlEscape="false" maxlength="50" class="input-medium" /></li>
			<li><label>投票/调查用户：</label> <form:input path="user.name"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li><label>用户访问IP：</label> <form:input path="sIp"
					htmlEscape="false" maxlength="50" class="input-medium" /></li>
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
				<th>投票/调查结果：</th>
			    <th>投票/调查题目</th>
				<th>投票/调查用户</th>
				<th>用户访问IP</th>
				<th>投票/调查时间</th>
				<!-- <th>备注信息</th> -->
				<%-- <shiro:hasPermission name="vote:pbsVoteOpdetail:edit">
					<th>操作</th>
				</shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsVoteOpdetail">
				<tr>
					<td>${pbsVoteOpdetail.sItemName}</td>
					<td>${pbsVoteOpdetail.sSubjectName}</td>
					<td>${pbsVoteOpdetail.user.name}</td>
					<td>${pbsVoteOpdetail.SIp}</td>
					<td><fmt:formatDate value="${pbsVoteOpdetail.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<%-- <td>${pbsVoteOpdetail.remarks}</td> --%>
					<%-- <shiro:hasPermission name="vote:pbsVoteOpdetail:edit">
						<td><a
							href="${ctx}/vote/pbsVoteOpdetail/form?id=${pbsVoteOpdetail.id}">修改</a>
							<a
							href="${ctx}/vote/pbsVoteOpdetail/delete?id=${pbsVoteOpdetail.id}"
							onclick="return confirmx('确认要删除该投票个人选项信息吗？', this.href)">删除</a></td>
					</shiro:hasPermission> --%>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>