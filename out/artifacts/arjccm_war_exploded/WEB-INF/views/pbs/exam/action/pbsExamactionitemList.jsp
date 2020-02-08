<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考试题目信息管理</title>
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
		<li class="active"><a href="${ctx}/exam/pbsExamactionitem/">考试题目信息列表</a></li>
		<shiro:hasPermission name="exam:pbsExamactionitem:edit"><li><a href="${ctx}/exam/pbsExamactionitem/form">考试题目信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsExamactionitem" action="${ctx}/exam/pbsExamactionitem/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>试卷编号：</label>
				 <form:input path="sExampaper.sName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>考题编号：</label>
			 <form:input path="sItem.sBody" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>试卷编号</th>
				<th>考题编号</th>
				<th>答题者</th>
				<th>用户答案</th>
				<th>正确与否</th>
				<th>更新时间</th>
				<shiro:hasPermission name="exam:pbsExamactionitem:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsExamactionitem">
			<tr>
				<td><a href="${ctx}/exam/pbsExamactionitem/form?id=${pbsExamactionitem.id}">
					${pbsExamactionitem.sExampaper.SName}
				</a></td>
				<td>
					${pbsExamactionitem.sItem.SBody}
				</td>
				<td>
					${pbsExamactionitem.sExaminee.SName}
				</td>
				<td>
					${pbsExamactionitem.sDoanswer}
				</td>
				<td>
					${fns:getDictLabel(pbsExamactionitem.SJudge, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${pbsExamactionitem.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="exam:pbsExamactionitem:edit"><td>
    				<a href="${ctx}/exam/pbsExamactionitem/form?id=${pbsExamactionitem.id}" title = "修改"><i class="icon icon-pencil"></i></a>
					<a href="${ctx}/exam/pbsExamactionitem/delete?id=${pbsExamactionitem.id}" onclick="return confirmx('确认要删除该考试题目信息吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>