<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>活动签到管理</title>
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
		<li class="active"><a href="${ctx}/activity/pbsActivitysignin/">活动签到列表</a></li>
		<shiro:hasPermission name="activity:pbsActivitysignin:edit">
			<li><a href="${ctx}/activity/pbsActivitysignin/form">活动签到添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsActivitysignin"
		action="${ctx}/activity/pbsActivitysignin/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<%-- <li><label>活动：</label>
				<form:select path="sActivityid" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li> 
			 <li><label>活动类型：</label>
				<form:select path="sType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li> --%>
			<li><label>操作人员：</label> <sys:treeselect id="sOperator"
					name="sOperator" value="${pbsActivitysignin.sOperator.id}"
					labelName="" labelValue="${pbsActivitysignin.sOperator.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small"
					allowClear="true" notAllowSelectParent="true" /></li>
			<li><label>操作绑定人员：</label> <sys:treeselect id="sBindmember"
					name="sBindmember" value="${pbsActivitysignin.sBindmember.id}"
					labelName="" labelValue="${pbsActivitysignin.sBindmember.SName}"
					title="用户" url="/sys/pbsOffice/treeData?type=4" cssClass="input-small"
					allowClear="true" notAllowSelectParent="true" /></li>
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
				<th>活动类型</th>
                <th>签到人员</th>
				<th>操作人员</th>
				<th>操作绑定人员</th>
				<th>更新时间</th>
				<!-- <th>备注信息</th> -->
				<shiro:hasPermission name="activity:pbsActivitysignin:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsActivitysignin">
				<tr>
					<td><a
						href="${ctx}/activity/pbsActivitysignin/form?id=${pbsActivitysignin.id}">
							${pbsActivitysignin.sActivityid.STitle} </a></td>
					<td>${pbsActivitysignin.sType.SName}</td>
					<td>${pbsActivitysignin.sSignbindmember.SName}</td>
					<td>${pbsActivitysignin.sOperator.name}</td>
					<td>${pbsActivitysignin.sBindmember.SName}</td>
					<td><fmt:formatDate value="${pbsActivitysignin.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<%-- <td>${pbsActivitysignin.remarks}</td> --%>
					<shiro:hasPermission name="activity:pbsActivitysignin:edit">
						<td><a
							href="${ctx}/activity/pbsActivitysignin/form?id=${pbsActivitysignin.id}" title = "修改"><i class="icon icon-pencil"></i></a>
							<a
							href="${ctx}/activity/pbsActivitysignin/delete?id=${pbsActivitysignin.id}"
							onclick="return confirmx('确认要删除该活动签到吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>