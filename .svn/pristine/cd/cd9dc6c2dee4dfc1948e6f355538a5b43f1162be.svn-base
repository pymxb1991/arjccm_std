<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>附件管理</title>
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
		<li class="active"><a href="${ctx}/files/plmPublicFiles/">附件列表</a></li>
		<shiro:hasPermission name="files:plmPublicFiles:edit"><li><a href="${ctx}/files/plmPublicFiles/form">附件添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="plmPublicFiles" action="${ctx}/files/plmPublicFiles/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-search"></i> 查询</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="plmknom:plmPublicFiles:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="plmPublicFiles">
			<tr>
				<td><a href="${ctx}/files/plmPublicFiles/form?id=${plmPublicFiles.id}">
					<fmt:formatDate value="${plmPublicFiles.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${plmPublicFiles.remarks}
				</td>
				<shiro:hasPermission name="files:plmPublicFiles:edit"><td>
    				<a href="${ctx}/files/plmPublicFiles/form?id=${plmPublicFiles.id}">修改</a>
					<a href="${ctx}/files/plmPublicFiles/delete?id=${plmPublicFiles.id}" onclick="return confirmx('确认要删除该附件吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>