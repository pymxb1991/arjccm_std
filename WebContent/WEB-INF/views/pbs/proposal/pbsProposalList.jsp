<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>建议信息管理</title>
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
		<li class="active"><a href="${ctx}/proposal/pbsProposal/">建议信息列表</a></li>
		<shiro:hasPermission name="proposal:pbsProposal:edit"><li><a href="${ctx}/proposal/pbsProposal/form">建议信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsProposal" action="${ctx}/proposal/pbsProposal/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>建议标题：</label>
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
				<th>建议类别</th>
				<th>建议标题</th>
				<!-- <th>展示方式</th> -->
				<th>回复人数</th>
				<th>发布者</th>
				<th>更新时间</th>
				<!-- <th>备注信息</th> -->
				<shiro:hasPermission name="proposal:pbsProposal:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsProposal">
			<tr>
				<td><a href="${ctx}/proposal/pbsProposal/form?id=${pbsProposal.id}">
					${pbsProposal.sAreaid.SName}
				</a></td>
				<td>
					${pbsProposal.STitle}
				</td>
				<%-- <td>
					${fns:getDictLabel(pbsProposal.SShowtype, 'proposalshowtype', '')}
				</td> --%>
				<td>
					${pbsProposal.ICnt}
				</td>
				<td>
					${pbsProposal.sReporteruser.name}
				</td>
				<td>
					<fmt:formatDate value="${pbsProposal.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <td>
					${pbsProposal.remarks}
				</td> --%>
				<shiro:hasPermission name="proposal:pbsProposal:edit"><td>
    				<a href="${ctx}/proposal/pbsProposal/form?id=${pbsProposal.id}" title = "修改"><i class="icon icon-pencil"></i></a>
					<a href="${ctx}/proposal/pbsProposal/delete?id=${pbsProposal.id}" onclick="return confirmx('确认要删除该建议信息吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>