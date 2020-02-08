<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>试卷题目管理</title>
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
		<li class="active"><a href="${ctx}/exam/pbsExampaperitem/">试卷题目列表</a></li>
		<shiro:hasPermission name="exam:pbsExampaperitem:edit"><li><a href="${ctx}/exam/pbsExampaperitem/form">试卷题目添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsExampaperitem" action="${ctx}/exam/pbsExampaperitem/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>试卷名称：</label>
				<form:input path="sExampaper.sName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>考题内容</th>
				<th>试卷名称</th>
				<th>考题顺序</th>
				<th>更新时间</th>
				<shiro:hasPermission name="exam:pbsExampaperitem:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsExampaperitem">
			<tr>
				<td><a href="${ctx}/exam/pbsExampaperitem/form?id=${pbsExampaperitem.id}">
					${pbsExampaperitem.sItem.SBody}
				</a></td>
				<td>
					${pbsExampaperitem.sExampaper.SName}
				</td>
				<td>
					${pbsExampaperitem.ISort}
				</td>
				<td>
					<fmt:formatDate value="${pbsExampaperitem.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="exam:pbsExampaperitem:edit"><td>
    				<a href="${ctx}/exam/pbsExampaperitem/form?id=${pbsExampaperitem.id}" title = "修改"><i class="icon icon-pencil"></i></a>
					<a href="${ctx}/exam/pbsExampaperitem/delete?id=${pbsExampaperitem.id}" onclick="return confirmx('确认要删除该试卷题目吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>