<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>运行工作流管理</title>
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
		<li class="active"><a href="${ctx}/flow/pbsFlowwork/">运行工作流列表</a></li>
		<shiro:hasPermission name="flow:pbsFlowwork:edit">
			<li><a href="${ctx}/flow/pbsFlowwork/form">运行工作流添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsFlowwork"
		action="${ctx}/flow/pbsFlowwork/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>任务名称：</label> <form:input path="sName"
					htmlEscape="false" maxlength="200" class="input-medium" /></li>
			<li><label>所属工作流模板：</label> <form:input path="sFlowid.sName"
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
				<th>任务名称</th>
				<th>所属工作流模板</th>
				<th>审核结果</th>
				<th>开始日期</th>
				<th>绑定的数据类型</th>
				<th>操作人员</th>
				<th>学员信息</th>
				<shiro:hasPermission name="flow:pbsFlowwork:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsFlowwork">
				<tr>
					<td><a
						href="${ctx}/flow/pbsFlowwork/form?id=${pbsFlowwork.id}">
							${pbsFlowwork.SName} </a></td>
					<td>${pbsFlowwork.sFlowid.SName}</td>
					<td>${fns:getDictLabel(pbsFlowwork.sBindstat, 'flowresult', '')}
					</td>
					<td><fmt:formatDate value="${pbsFlowwork.dtStartdate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${fns:getDictLabel(pbsFlowwork.SBinddata, 'flowbinddata', '')}
					</td>
					<td>${pbsFlowwork.sOperator.name}</td>
					<td>${pbsFlowwork.sBindmember.SName}</td>
					<shiro:hasPermission name="flow:pbsFlowwork:edit">
						<td><a
							href="${ctx}/flow/pbsFlowwork/form?id=${pbsFlowwork.id}" title = "修改"><i class="icon icon-pencil"></i></a> <a
							href="${ctx}/flow/pbsFlowwork/delete?id=${pbsFlowwork.id}"
							onclick="return confirmx('确认要删除该运行工作流吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>