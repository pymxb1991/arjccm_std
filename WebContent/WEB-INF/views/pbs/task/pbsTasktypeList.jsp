<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工作安排类型定义管理</title>
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
		<li class="active"><a href="${ctx}/task/pbsTasktype/">工作安排类型定义列表</a></li>
		<shiro:hasPermission name="task:pbsTasktype:edit"><li><a href="${ctx}/task/pbsTasktype/form">工作安排类型定义添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsTasktype" action="${ctx}/task/pbsTasktype/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>任务类型定义名称：</label>
				<form:input path="sName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>自定义分组标识：</label>
				<form:input path="sGroup" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>任务类型定义名称</th>
				<th>自定义分组标识</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="task:pbsTasktype:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsTasktype">
			<tr>
				<td><a href="${ctx}/task/pbsTasktype/form?id=${pbsTasktype.id}">
					${pbsTasktype.SName}
				</a></td>
				<td>
					${pbsTasktype.SGroup}
				</td>
				<td>
					<fmt:formatDate value="${pbsTasktype.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${pbsTasktype.remarks}
				</td>
				<shiro:hasPermission name="task:pbsTasktype:edit"><td>
    				<a href="${ctx}/task/pbsTasktype/form?id=${pbsTasktype.id}" title = "修改"><i class="icon icon-pencil"></i></a>
					<a href="${ctx}/task/pbsTasktype/delete?id=${pbsTasktype.id}" onclick="return confirmx('确认要删除该工作安排类型定义吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>