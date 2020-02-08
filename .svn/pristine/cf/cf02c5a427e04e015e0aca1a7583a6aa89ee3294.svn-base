<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>专业信息管理</title>
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
		<li class="active"><a href="${ctx}/question/pbsQuestionMajor/">专业信息列表</a></li>
		<shiro:hasPermission name="question:pbsQuestionMajor:edit"><li><a href="${ctx}/question/pbsQuestionMajor/form">专业信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsQuestionMajor" action="${ctx}/question/pbsQuestionMajor/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>专业名称：</label>
				<form:input path="sName" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>专业信息描述：</label>
				<form:input path="sDesc" htmlEscape="false" maxlength="2000" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>专业名称</th>
				<th>专业信息描述</th>
				<th>更新时间</th>
				<!-- <th>备注信息</th> -->
				<shiro:hasPermission name="question:pbsQuestionMajor:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsQuestionMajor">
			<tr>
				<td><a href="${ctx}/question/pbsQuestionMajor/form?id=${pbsQuestionMajor.id}">
					${pbsQuestionMajor.SName}
				</a></td>
				<td>
					${pbsQuestionMajor.SDesc}
				</td>
				<td>
					<fmt:formatDate value="${pbsQuestionMajor.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <td>
					${pbsQuestionMajor.remarks}
				</td> --%>
				<shiro:hasPermission name="question:pbsQuestionMajor:edit"><td>
    				<a href="${ctx}/question/pbsQuestionMajor/form?id=${pbsQuestionMajor.id}" title = "修改"><i class="icon icon-pencil"></i></a>
					<a href="${ctx}/question/pbsQuestionMajor/delete?id=${pbsQuestionMajor.id}" onclick="return confirmx('确认要删除该专业信息吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>