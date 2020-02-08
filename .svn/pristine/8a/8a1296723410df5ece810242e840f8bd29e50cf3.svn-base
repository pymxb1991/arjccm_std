<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>网上论坛多级评论管理</title>
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
		<li class="active"><a href="${ctx}/cms/cmsBbsCommentMulti/">网上论坛多级评论列表</a></li>
		<shiro:hasPermission name="cms:cmsBbsCommentMulti:edit"><li><a href="${ctx}/cms/cmsBbsCommentMulti/form">网上论坛多级评论添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cmsBbsCommentMulti" action="${ctx}/cms/cmsBbsCommentMulti/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>comment_multi_content：</label>
				<form:input path="commentMultiContent" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>comment_multi_content</th>
				<shiro:hasPermission name="cms:cmsBbsCommentMulti:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cmsBbsCommentMulti">
			<tr>
				<td><a href="${ctx}/cms/cmsBbsCommentMulti/form?id=${cmsBbsCommentMulti.id}">
					${cmsBbsCommentMulti.commentMultiContent}
				</a></td>
				<shiro:hasPermission name="cms:cmsBbsCommentMulti:edit"><td>
    				<a href="${ctx}/cms/cmsBbsCommentMulti/form?id=${cmsBbsCommentMulti.id}">修改</a>
					<a href="${ctx}/cms/cmsBbsCommentMulti/delete?id=${cmsBbsCommentMulti.id}" onclick="return confirmx('确认要删除该网上论坛多级评论吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>