<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>活动请假管理</title>
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
		<li class="active"><a href="${ctx}/activity/pbsActivityleave/">活动请假列表</a></li>
		<shiro:hasPermission name="activity:pbsActivityleave:edit">
			<li><a href="${ctx}/activity/pbsActivityleave/form">活动请假添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsActivityleave"
		action="${ctx}/activity/pbsActivityleave/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<%-- <li><label>活动：</label> <form:input path="sActivityid"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li><label>请假类型：</label> <form:select path="sType"
					class="input-medium">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('')}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select></li> --%>
			<li><label>报名操作人员：</label> <sys:treeselect id="sOperator"
					name="sOperator" value="${pbsActivityleave.sOperator.id}"
					labelName="" labelValue="${pbsActivityleave.sOperator.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small"
					allowClear="true" notAllowSelectParent="true" /></li>
			<li><label>报名学员：</label> <sys:treeselect id="sBindmember"
					name="sBindmember" value="${pbsActivityleave.sBindmember.id}"
					labelName="" labelValue="${pbsActivityleave.sBindmember.SName}"
					title="用户" url="/sys/pbsOffice/treeData?type=4" cssClass="input-small"
					allowClear="true" notAllowSelectParent="true" /></li>
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
				<th>活动</th>
				<th>操作人员</th>
				<th>学员</th>
				<th>请假事由</th>
				<th>更新时间</th>
				<shiro:hasPermission name="activity:pbsActivityleave:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsActivityleave">
				<tr>
					<td><a
						href="${ctx}/activity/pbsActivityleave/form?id=${pbsActivityleave.id}">
							${pbsActivityleave.sActivityid.STitle} </a></td>
					<td>${pbsActivityleave.sOperator.name}</td>
					<td>${pbsActivityleave.sBindmember.SName}</td>
					<td>${pbsActivityleave.SAllpyreason}</td>
					<td><fmt:formatDate value="${pbsActivityleave.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<shiro:hasPermission name="activity:pbsActivityleave:edit">
						<td><a
							href="${ctx}/activity/pbsActivityleave/form?id=${pbsActivityleave.id}" title = "修改"><i class="icon icon-pencil"></i></a>
							<a
							href="${ctx}/activity/pbsActivityleave/delete?id=${pbsActivityleave.id}"
							onclick="return confirmx('确认要删除该活动请假吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>