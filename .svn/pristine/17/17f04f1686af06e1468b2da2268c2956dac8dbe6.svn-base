<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动评论信息管理</title>
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
		<li class="active"><a href="${ctx}/activity/pbsActivityecomment/">活动评论信息列表</a></li>
		<shiro:hasPermission name="activity:pbsActivityecomment:edit"><li><a href="${ctx}/activity/pbsActivityecomment/form">活动评论信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsActivityecomment" action="${ctx}/activity/pbsActivityecomment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>活动编号：</label>
				<form:input path="sActivityid.sTitle" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>评论内容信息：</label>
				<form:input path="sContent" htmlEscape="false" maxlength="2000" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>活动编号</th>
				<th>评论内容信息</th>
				<th>评论用户</th>
				<th>更新时间</th>
				<shiro:hasPermission name="activity:pbsActivityecomment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsActivityecomment">
			<tr>
				<td><a href="${ctx}/activity/pbsActivityecomment/form?id=${pbsActivityecomment.id}">
					${pbsActivityecomment.sActivityid.STitle}
				</a></td>
				<td>
					${pbsActivityecomment.SContent}
				</td>
				<td>
                    ${pbsActivityecomment.sBindmember.SName}
                </td>
				<td>
					<fmt:formatDate value="${pbsActivityecomment.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="activity:pbsActivityecomment:edit"><td>
    				<a href="${ctx}/activity/pbsActivityecomment/form?id=${pbsActivityecomment.id}" title = "修改"><i class="icon icon-pencil"></i></a>
					<a href="${ctx}/activity/pbsActivityecomment/delete?id=${pbsActivityecomment.id}" onclick="return confirmx('确认要删除该活动评论信息吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>