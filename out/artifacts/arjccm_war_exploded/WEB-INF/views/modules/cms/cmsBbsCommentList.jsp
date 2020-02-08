<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>网上论坛一级评论管理</title>
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
		<li class="active"><a href="${ctx}/cms/cmsBbsComment/">网上论坛一级评论列表</a></li>
		<shiro:hasPermission name="cms:cmsBbsComment:edit"><li><a href="${ctx}/cms/cmsBbsComment/form">网上论坛一级评论添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cmsBbsComment" action="${ctx}/cms/cmsBbsComment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>评论内容：</label>
				<form:input path="comContent" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>评论内容</th>
				<shiro:hasPermission name="cms:cmsBbsComment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cmsBbsComment">
			<tr>
				<td><a href="${ctx}/cms/cmsBbsComment/form?id=${cmsBbsComment.id}">
					${cmsBbsComment.comContent}
				</a></td>
				<shiro:hasPermission name="cms:cmsBbsComment:edit"><td>
    				<a href="${ctx}/cms/cmsBbsComment/form?id=${cmsBbsComment.id}">修改</a>
					<a href="${ctx}/cms/cmsBbsComment/delete?id=${cmsBbsComment.id}" onclick="return confirmx('确认要删除该网上论坛一级评论吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>