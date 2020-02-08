<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工作安排反馈信息管理</title>
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
		<li class="active"><a href="${ctx}/task/pbsTaskevaluate/">工作安排反馈信息列表</a></li>
		<shiro:hasPermission name="task:pbsTaskevaluate:edit"><li><a href="${ctx}/task/pbsTaskevaluate/form">工作安排反馈信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsTaskevaluate" action="${ctx}/task/pbsTaskevaluate/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>任务：</label>
				<form:input path="sTaskid.sResume" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>任务的负责人：</label>
				<form:input path="sExecmember.sName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li>
 			<label>评价分值：</label>
				<form:select path="sValue" class="input-xlarge required" style="width:195px">
                    <form:option value="" label="" />
                    <form:options items="${fns:getDictList('taskvaluetype')}"
                        itemLabel="label" itemValue="value" htmlEscape="false" />
                </form:select>
<%-- 				<form:input path="sValue" htmlEscape="false" maxlength="255" class="input-medium"/> --%>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>任务</th>
				<th>任务的负责人</th>
				<th>评价分值</th>
				<th>更新时间</th>
				<shiro:hasPermission name="task:pbsTaskevaluate:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsTaskevaluate">
			<tr>
				<td><a href="${ctx}/task/pbsTaskevaluate/form?id=${pbsTaskevaluate.id}">
					${pbsTaskevaluate.sTaskid.SResume}
				</a></td>
				<td>
					${pbsTaskevaluate.sExecmember.SName}
				</td>
				<td>
					${fns:getDictLabel(pbsTaskevaluate.SValue, 'taskvaluetype', '')}
				</td>
				<td>
					<fmt:formatDate value="${pbsTaskevaluate.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="task:pbsTaskevaluate:edit"><td>
    				<a href="${ctx}/task/pbsTaskevaluate/form?id=${pbsTaskevaluate.id}" title = "修改"><i class="icon icon-pencil"></i></a>
					<a href="${ctx}/task/pbsTaskevaluate/delete?id=${pbsTaskevaluate.id}" onclick="return confirmx('确认要删除该工作安排反馈信息吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>