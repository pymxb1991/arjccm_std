<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节点操作后管理</title>
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
		<li class="active"><a href="${ctx}/flow/pbsFlowsetstat/">节点操作后列表</a></li>
		<shiro:hasPermission name="flow:pbsFlowsetstat:edit"><li><a href="${ctx}/flow/pbsFlowsetstat/form">节点操作后添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsFlowsetstat" action="${ctx}/flow/pbsFlowsetstat/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>定义流程：</label>
				<form:input path="sFlowid.sName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>节点：</label>
				<form:input path="sFlownodeid.sName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>操作类型：</label>
				<form:input path="sOpeatertype" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>定义流程</th>
				<th>节点</th>
				<th>操作类型</th>
				<th>更新时间</th>
				<shiro:hasPermission name="flow:pbsFlowsetstat:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsFlowsetstat">
			<tr>
				<td><a href="${ctx}/flow/pbsFlowsetstat/form?id=${pbsFlowsetstat.id}">
					${pbsFlowsetstat.sFlowid.SName}
				</a></td>
				<td>
					${pbsFlowsetstat.sFlownodeid.SName}
				</td>
				<td>
					${pbsFlowsetstat.SOpeatertype}
				</td>
				<td>
					<fmt:formatDate value="${pbsFlowsetstat.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="flow:pbsFlowsetstat:edit"><td>
    				<a href="${ctx}/flow/pbsFlowsetstat/form?id=${pbsFlowsetstat.id}"></a>
					<a href="${ctx}/flow/pbsFlowsetstat/delete?id=${pbsFlowsetstat.id}" onclick="return confirmx('确认要删除该节点操作后吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>