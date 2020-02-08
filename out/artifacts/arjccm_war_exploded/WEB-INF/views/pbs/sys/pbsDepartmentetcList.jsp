<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>党干部管理</title>
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
		<li class="active"><a href="${ctx}/sys/pbsDepartmentetc/">党干部管理列表</a></li>
		<shiro:hasPermission name="sys:pbsDepartmentetc:edit"><li><a href="${ctx}/sys/pbsDepartmentetc/form">党干部管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsDepartmentetc" action="${ctx}/sys/pbsDepartmentetc/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>部门：</label>
				<sys:treeselect id="office" name="office.id" value="${pbsDepartmentetc.office.id}" labelName="office.name" labelValue="${pbsDepartmentetc.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true"/>
			</li>
			<li><label>负责人：</label>
				<sys:treeselect id="sMasterid" name="sMasterid" value="${pbsDepartmentetc.SMasterid}" labelName="" labelValue="${pbsDepartmentetc.SMasterid}"
					title="用户" url="/sys/pbsOffice/treeData?type=4" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>部门</th>
				<th>负责人</th>
				<th>兼职当干部管理人员</th>
				<th>更新时间</th>
				<!-- <th>备注信息</th> -->
				<shiro:hasPermission name="sys:pbsDepartmentetc:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsDepartmentetc">
			<tr>
				<td><a href="${ctx}/sys/pbsDepartmentetc/form?id=${pbsDepartmentetc.id}">
					${pbsDepartmentetc.office.name}
				</a></td>
				<td>
					${pbsDepartmentetc.mastername}
				</td>
				<td>
					${pbsDepartmentetc.masternames}
				</td>
				<td>
					<fmt:formatDate value="${pbsDepartmentetc.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <td>
					${pbsDepartmentetc.remarks}
				</td> --%>
				<shiro:hasPermission name="sys:pbsDepartmentetc:edit"><td>
    				<a href="${ctx}/sys/pbsDepartmentetc/form?id=${pbsDepartmentetc.id}" title = "修改"><i class="icon icon-pencil"></i></a>
					<a href="${ctx}/sys/pbsDepartmentetc/delete?id=${pbsDepartmentetc.id}" onclick="return confirmx('确认要删除该学员部门扩展信息吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>