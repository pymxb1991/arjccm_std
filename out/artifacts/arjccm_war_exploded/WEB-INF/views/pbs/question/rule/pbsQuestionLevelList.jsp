<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考试难度级别管理</title>
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
		<li class="active"><a href="${ctx}/question/pbsQuestionLevel/">考试难度级别列表</a></li>
		<shiro:hasPermission name="question:pbsQuestionLevel:edit"><li><a href="${ctx}/question/pbsQuestionLevel/form">考试难度级别添加</a></li></shiro:hasPermission>
	</ul>
	<%-- <form:form id="searchForm" modelAttribute="pbsQuestionLevel" action="${ctx}/question/pbsQuestionLevel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>考试难度级别名称：</label>
				<form:input path="sName" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form> --%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed" style="margin-top:15px">
		<thead>
			<tr>
				<th>考试难度级别名称</th>
				<th>难度级别赋值</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="question:pbsQuestionLevel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsQuestionLevel">
			<tr>
				<td><a href="${ctx}/question/pbsQuestionLevel/form?id=${pbsQuestionLevel.id}">
					${pbsQuestionLevel.SName}
				</a></td>
				<td>
					${pbsQuestionLevel.IVal}
				</td>
				<td>
					<fmt:formatDate value="${pbsQuestionLevel.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${pbsQuestionLevel.remarks}
				</td>
				<shiro:hasPermission name="question:pbsQuestionLevel:edit"><td>
    				<a href="${ctx}/question/pbsQuestionLevel/form?id=${pbsQuestionLevel.id}" title = "修改"><i class="icon icon-pencil"></i></a>
					<a href="${ctx}/question/pbsQuestionLevel/delete?id=${pbsQuestionLevel.id}" onclick="return confirmx('确认要删除该考试难度级别吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>