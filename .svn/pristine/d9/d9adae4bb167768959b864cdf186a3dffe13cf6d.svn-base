<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>流程定义信息管理</title>
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
		<li class="active"><a href="${ctx}/flow/pbsFlowdefinition/">流程定义信息列表</a></li>
		<shiro:hasPermission name="flow:pbsFlowdefinition:edit"><li><a href="${ctx}/flow/pbsFlowdefinition/form">流程定义信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsFlowdefinition" action="${ctx}/flow/pbsFlowdefinition/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>流程定义名称：</label>
				<form:input path="sName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<%-- <li><label>流程类别编号：</label>
				<form:input path="sTypeid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li> --%>
			<li><label>启用状态：</label>
				<form:select path="sStartstat" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>流程定义名称</th>
				<th>流程类别编号</th>
				<th>启用状态</th>
				<th>更新时间</th>
				<shiro:hasPermission name="flow:pbsFlowdefinition:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsFlowdefinition">
			<tr>
				<td><a href="${ctx}/flow/pbsFlowdefinition/checkform?id=${pbsFlowdefinition.id}">
					${pbsFlowdefinition.SName}
				</a></td>
				<td>
					${pbsFlowdefinition.sTypeid.SName}
				</td>
				<td>
					${fns:getDictLabel(pbsFlowdefinition.SStartstat, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${pbsFlowdefinition.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="flow:pbsFlowdefinition:edit"><td>
    				<a href="${ctx}/flow/pbsFlowdefinition/checkform?id=${pbsFlowdefinition.id}" title = "修改"><i class="icon icon-pencil"></i></a>
					<a href="${ctx}/flow/pbsFlowdefinition/delete?id=${pbsFlowdefinition.id}" onclick="return confirmx('确认要删除该流程定义信息吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>