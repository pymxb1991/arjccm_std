<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>活动通知管理</title>
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
		<li class="active"><a
			href="${ctx}/activity/pbsActinotifications/">活动通知列表</a></li>
		<%-- <shiro:hasPermission name="activity:pbsActinotifications:edit">
			<li><a href="${ctx}/activity/pbsActinotifications/form">活动通知添加</a></li>
		</shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsActinotifications"
		action="${ctx}/activity/pbsActinotifications/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li>
				<label>活动：</label>
				<form:input path="sActivityid.sTitle" htmlEscape="false" maxlength="200" />
			</li>
			<li><label>通知类型：</label> <form:select path="sType"
					class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('actinformtype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<%-- 	<li><label>名称：</label>
				<sys:treeselect id="sAcceptorid" name="sAcceptorid" value="${pbsActinotifications.sAcceptorid.id}" labelName="" labelValue="${pbsActinotifications.sAcceptorid.SName}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li> --%>
			<li><label>读取状态：</label> <form:select path="sStat"
					class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('oa_notify_read')}"
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
				<th>活动</th>
				<th>通知类型</th>
				<th>名称</th>
				<th>读取状态</th>
				<th>更新时间</th>
				<!-- <th>备注信息</th> -->
				<shiro:hasPermission name="activity:pbsActinotifications:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsActinotifications">
				<tr>
					<td><a
						href="${ctx}/activity/pbsActinotifications/form?id=${pbsActinotifications.id}">
							${pbsActinotifications.sActivityid.STitle} </a></td>
					<td>${fns:getDictLabel(pbsActinotifications.sType, 'actinformtype', '')}
					</td>
					<td>${pbsActinotifications.sAcceptorid.SName}</td>
					<td>${fns:getDictLabel(pbsActinotifications.SStat, 'oa_notify_read', '')}
					</td>
					<td><fmt:formatDate value="${pbsActinotifications.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<%-- <td>${pbsActinotifications.remarks}</td> --%>
					<shiro:hasPermission name="activity:pbsActinotifications:edit">
						<td><a
							href="${ctx}/activity/pbsActinotifications/form?id=${pbsActinotifications.id}" title = "修改"><i class="icon icon-pencil"></i></a>
							<a
							href="${ctx}/activity/pbsActinotifications/delete?id=${pbsActinotifications.id}"
							onclick="return confirmx('确认要删除该活动通知吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>

