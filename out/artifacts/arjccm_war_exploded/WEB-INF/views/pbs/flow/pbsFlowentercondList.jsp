<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>进入节点条件信息管理</title>
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
		<li class="active"><a href="${ctx}/flow/pbsFlowentercond/">进入节点条件信息列表</a></li>
		<shiro:hasPermission name="flow:pbsFlowentercond:edit"><li><a href="${ctx}/flow/pbsFlowentercond/form">进入节点条件信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsFlowentercond" action="${ctx}/flow/pbsFlowentercond/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>流程模板：</label>
				<form:input path="sFlowid" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>流程节点：</label>
				<form:input path="sFlownodeid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			 
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>所属流程</th>
				<th>所属节点</th>
				<th>状态类型</th>
				<th>更新时间</th>
				<shiro:hasPermission name="flow:pbsFlowentercond:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsFlowentercond">
			<tr>
				<td><a href="${ctx}/flow/pbsFlowentercond/form?id=${pbsFlowentercond.id}">
					${pbsFlowentercond.sFlowid.SName}
				</a></td>
				<td>
					${pbsFlowentercond.sFlownodeid.SName}
				</td>
				<td>
					${pbsFlowentercond.SCondtype}
				</td>
				<td>
					<fmt:formatDate value="${pbsFlowentercond.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="flow:pbsFlowentercond:edit"><td>
    				<a href="${ctx}/flow/pbsFlowentercond/form?id=${pbsFlowentercond.id}" title = "修改"><i class="icon icon-pencil"></i></a>
					<a href="${ctx}/flow/pbsFlowentercond/delete?id=${pbsFlowentercond.id}" onclick="return confirmx('确认要删除该进入节点条件信息吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>