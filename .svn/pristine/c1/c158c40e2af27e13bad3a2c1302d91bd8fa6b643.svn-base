<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>思想汇报信息管理</title>
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
		<li class="active"><a href="${ctx}/thinkingreport/pbsThinkingreport/">思想汇报信息列表</a></li>
		<shiro:hasPermission name="thinkingreport:pbsThinkingreport:edit"><li><a href="${ctx}/thinkingreport/pbsThinkingreport/form">思想汇报信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsThinkingreport" action="${ctx}/thinkingreport/pbsThinkingreport/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>思想汇报的标题：</label>
				<form:input path="sTitle" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>思想汇报的标题</th>
				<th>发布者</th>
				<th>发布者学员</th>
				<th>接收者学员</th>
				<th>状态</th>
				<th>创建时间</th>
				<shiro:hasPermission name="thinkingreport:pbsThinkingreport:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsThinkingreport">
			<tr>
				<td><a href="${ctx}/thinkingreport/pbsThinkingreport/form?id=${pbsThinkingreport.id}">
					${pbsThinkingreport.STitle}
				</a></td>
				<td>
					${pbsThinkingreport.sReporteruser.name}
				</td>
				<td>
					${pbsThinkingreport.sReportermem.SName}
				</td>
				<td>
					${pbsThinkingreport.sAcceptermem.SName}
				</td>
				<td>
					${fns:getDictLabel(pbsThinkingreport.SStat, 'thinkreppublishtype', '')}
				</td>
				<td>
					<fmt:formatDate value="${pbsThinkingreport.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="thinkingreport:pbsThinkingreport:edit"><td>
    				<a href="${ctx}/thinkingreport/pbsThinkingreport/form?id=${pbsThinkingreport.id}" title = "修改"><i class="icon icon-pencil"></i></a>
					<a href="${ctx}/thinkingreport/pbsThinkingreport/delete?id=${pbsThinkingreport.id}" onclick="return confirmx('确认要删除该思想汇报信息吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>