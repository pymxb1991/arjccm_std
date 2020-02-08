<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>邮箱表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function(){
				$('#searchForm').submit();
			});
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
		<li class="active"><a href="${ctx}/email/plmEmailBox/">邮箱表列表</a></li>
		<shiro:hasPermission name="email:plmEmailBox:edit"><li><a href="${ctx}/email/plmEmailBox/form">邮箱表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="plmEmailBox" action="${ctx}/email/plmEmailBox/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>主题：</label>
				<form:input path="title" htmlEscape="false" class="input-medium"/>
			</li>
			<li class="btns"><a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-search"></i> 查询</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>主题</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="email:plmEmailBox:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="plmEmailBox">
			<tr>
				<td><a href="${ctx}/email/plmEmailBox/form?id=${plmEmailBox.id}">
					${plmEmailBox.title}
				</a></td>
				<td>
					<fmt:formatDate value="${plmEmailBox.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${plmEmailBox.remarks}
				</td>
				<shiro:hasPermission name="email:plmEmailBox:edit"><td>
    				<a href="${ctx}/email/plmEmailBox/form?id=${plmEmailBox.id}">修改</a>
					<a href="${ctx}/email/plmEmailBox/delete?id=${plmEmailBox.id}" onclick="return confirmx('确认要删除该邮箱表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>