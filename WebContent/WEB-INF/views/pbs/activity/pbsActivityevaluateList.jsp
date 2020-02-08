<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>活动评分管理</title>
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
		<li class="active"><a href="${ctx}/activity/pbsActivityevaluate/">活动评分列表</a></li>
		<shiro:hasPermission name="activity:pbsActivityevaluate:edit">
			<li><a href="${ctx}/activity/pbsActivityevaluate/form">活动评分添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsActivityevaluate"
		action="${ctx}/activity/pbsActivityevaluate/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>活动编号：</label> <form:input path="sActivityid.sTitle"
					htmlEscape="false" maxlength="200" class="input-medium" /></li>
			<li><label>参与人员：</label> <sys:treeselect id="sPartmember"
					name="sPartmember" value="${pbsActivityleave.sPartmember.id}"
					labelName="" labelValue="${pbsActivityleave.sPartmember.SName}"
					title="用户" url="/sys/pbsOffice/treeData?type=4" cssClass="input-small"
					allowClear="true" notAllowSelectParent="true" /></li>
			<li><label>评价分值：</label> <form:select path="sValue"
					class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('taskvaluetype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
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
				<th>活动编号</th>
				<th>参与人员</th>
				<th>评价分值</th>
				<th>更新时间</th>
				<!-- <th>备注信息</th> -->
				<shiro:hasPermission name="activity:pbsActivityevaluate:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsActivityevaluate">
				<tr>
					<td><a
						href="${ctx}/activity/pbsActivityevaluate/form?id=${pbsActivityevaluate.id}">
							${pbsActivityevaluate.sActivityid.STitle} </a></td>
					<td>${pbsActivityevaluate.sPartmember.SName}</td>
					<td>${fns:getDictLabel(pbsActivityevaluate.SValue, 'taskvaluetype', '')}
					</td>
					<td><fmt:formatDate value="${pbsActivityevaluate.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<%-- <td>${pbsActivityevaluate.remarks}</td> --%>
					<shiro:hasPermission name="activity:pbsActivityevaluate:edit">
						<td><a
							href="${ctx}/activity/pbsActivityevaluate/form?id=${pbsActivityevaluate.id}" title = "修改"><i class="icon icon-pencil"></i></a>
							<a
							href="${ctx}/activity/pbsActivityevaluate/delete?id=${pbsActivityevaluate.id}"
							onclick="return confirmx('确认要删除该活动评分吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>