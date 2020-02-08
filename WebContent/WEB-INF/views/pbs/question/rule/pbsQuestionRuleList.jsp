<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评分规则定义管理</title>
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
		<li class="active"><a href="${ctx}/question/pbsQuestionRule/">评分规则定义列表</a></li>
		<shiro:hasPermission name="question:pbsQuestionRule:edit"><li><a href="${ctx}/question/pbsQuestionRule/form">评分规则定义添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsQuestionRule" action="${ctx}/question/pbsQuestionRule/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>评分规则名称：</label>
				<form:input path="sName" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>难度级别赋值：</label>
				<form:input path="iVal" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>评分规则名称</th>
				<th>难度级别赋值</th>
				<th>更新时间</th>
				<shiro:hasPermission name="question:pbsQuestionRule:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsQuestionRule">
			<tr>
				<td><a href="${ctx}/question/pbsQuestionRule/form?id=${pbsQuestionRule.id}">
					${pbsQuestionRule.SName}
				</a></td>
				<td>
					${pbsQuestionRule.IVal}
				</td>
				<td>
					<fmt:formatDate value="${pbsQuestionRule.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="question:pbsQuestionRule:edit"><td>
    				<a href="${ctx}/question/pbsQuestionRule/form?id=${pbsQuestionRule.id}" title = "修改"><i class="icon icon-pencil"></i></a>
					<a href="${ctx}/question/pbsQuestionRule/delete?id=${pbsQuestionRule.id}" onclick="return confirmx('确认要删除该评分规则定义吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>