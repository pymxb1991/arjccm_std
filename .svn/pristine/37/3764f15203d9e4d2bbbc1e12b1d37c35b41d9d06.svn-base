<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>统计明细信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
  $(document).ready(function() {

  });
  function page(n, s) {
    $("#pageNo").val(n);
    $("#pageSize").val(s);
    $("#searchForm").submit();
    return false;
  }
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ncount/pbsNcountdetail/">统计明细信息列表</a></li>
		<%--目前不需要添加功能，暂时注释掉 2018-08-06 qxs--%>
		<%-- <shiro:hasPermission name="ncount:pbsNcountdetail:edit">
			<li><a href="${ctx}/ncount/pbsNcountdetail/form">统计明细信息添加</a></li>
		</shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsNcountdetail"
		action="${ctx}/ncount/pbsNcountdetail/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>统计类别：</label>
				<form:select path="sParentid.sType" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('stat_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>统计名称：</label> <form:input path="sName"
					htmlEscape="false" maxlength="100" class="input-medium" /></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>统计名称</th>
				<th>统计类别</th>
				<th>统计总数</th>
				<th>统计数据日期</th>
				<th>更新时间</th>
				<!-- <th>备注信息</th> -->
				<%--目前不需要操作功能，暂时注释掉  2018-08-06 qxs--%>
				<%-- <shiro:hasPermission name="ncount:pbsNcountdetail:edit">
					<th>操作</th>
				</shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsNcountdetail">
				<tr>
					<td>${pbsNcountdetail.SName}</td>
					<%--目前不需要操作功能，暂时注释掉  2018-08-11 qxs--%>
					<%-- <td><a
						href="${ctx}/ncount/pbsNcountdetail/form?id=${pbsNcountdetail.id}">
							${pbsNcountdetail.SName} </a></td> --%>
					<td>${pbsNcountdetail.sParentid.SName}</td>

					<td>${pbsNcountdetail.INumber}</td>
					<td><fmt:formatDate value="${pbsNcountdetail.dtDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${pbsNcountdetail.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<%-- <td>${pbsNcountdetail.remarks}</td> --%>
					<%--目前不需要操作功能，暂时注释掉  2018-08-06 qxs--%>
					<%-- <shiro:hasPermission name="ncount:pbsNcountdetail:edit">
						<td><a
							href="${ctx}/ncount/pbsNcountdetail/form?id=${pbsNcountdetail.id}">修改</a>
							<a
							href="${ctx}/ncount/pbsNcountdetail/delete?id=${pbsNcountdetail.id}"
							onclick="return confirmx('确认要删除该统计明细信息吗？', this.href)">删除</a></td> 
					</shiro:hasPermission>--%>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>