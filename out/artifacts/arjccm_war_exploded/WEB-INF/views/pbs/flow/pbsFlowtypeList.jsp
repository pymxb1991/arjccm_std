<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>流程类型管理</title>
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
		<li class="active"><a href="${ctx}/flow/pbsFlowtype/">流程类型列表</a></li>
		<shiro:hasPermission name="flow:pbsFlowtype:edit"><li><a href="${ctx}/flow/pbsFlowtype/form">流程类型添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsFlowtype" action="${ctx}/flow/pbsFlowtype/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="sName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>描述</th>
				<th>更新时间</th>
				<shiro:hasPermission name="flow:pbsFlowtype:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsFlowtype">
			<tr>
				<td><a href="${ctx}/flow/pbsFlowtype/form?id=${pbsFlowtype.id}">
					${pbsFlowtype.SName}
				</a></td>
				<td>
					${pbsFlowtype.SDescription}
				</td>
				<td>
					<fmt:formatDate value="${pbsFlowtype.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="flow:pbsFlowtype:edit"><td>
    				<a href="${ctx}/flow/pbsFlowtype/form?id=${pbsFlowtype.id}" title = "修改"><i class="icon icon-pencil"></i></a>
					<a href="${ctx}/flow/pbsFlowtype/delete?id=${pbsFlowtype.id}" onclick="return confirmx('确认要删除该流程类型吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>