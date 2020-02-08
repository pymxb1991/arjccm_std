<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>建议分区历史信息管理</title>
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
		<li class="active"><a href="${ctx}/proposal/pbsProposalareaHis/">建议分区历史信息列表</a></li>
		<shiro:hasPermission name="proposal:pbsProposalareaHis:edit"><li><a href="${ctx}/proposal/pbsProposalareaHis/form">建议分区历史信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsProposalareaHis" action="${ctx}/proposal/pbsProposalareaHis/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>所属支部编号：</label>
				<sys:treeselect id="sDepartment" name="sDepartment" value="" labelName="" labelValue=""
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>标题名称：</label>
				<form:input path="sName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>所属支部编号</th>
				<th>标题名称</th>
				<th>建议负责人</th>
				<th>数据版本</th>
				<th>展示方式</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="proposal:pbsProposalareaHis:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsProposalareaHis">
			<tr>
				<td><a href="${ctx}/proposal/pbsProposalareaHis/form?id=${pbsProposalareaHis.id}">
					${pbsProposalareaHis.sDepartment}
				</a></td>
				<td>
					${pbsProposalareaHis.sName}
				</td>
				<td>
					${pbsProposalareaHis.sMastermem}
				</td>
				<td>
					${pbsProposalareaHis.iVersion}
				</td>
				<td>
					${fns:getDictLabel(pbsProposalareaHis.sShowtype, 'proposalshowtype', '')}
				</td>
				<td>
					<fmt:formatDate value="${pbsProposalareaHis.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${pbsProposalareaHis.remarks}
				</td>
				<shiro:hasPermission name="proposal:pbsProposalareaHis:edit"><td>
    				<a href="${ctx}/proposal/pbsProposalareaHis/form?id=${pbsProposalareaHis.id}" title = "修改"><i class="icon icon-pencil"></i></a>
					<a href="${ctx}/proposal/pbsProposalareaHis/delete?id=${pbsProposalareaHis.id}" onclick="return confirmx('确认要删除该建议分区历史信息吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>