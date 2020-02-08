<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>课程操作信息管理</title>
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
		<li class="active"><a href="${ctx}/course/pbsCourseoperate/">课程操作信息列表</a></li>
		<shiro:hasPermission name="course:pbsCourseoperate:edit">
			<li><a href="${ctx}/course/pbsCourseoperate/form">课程操作信息添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsCourseoperate"
		action="${ctx}/course/pbsCourseoperate/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>课程信息：</label> <form:input path="coursename"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li><label>课程类型：</label> <form:select path="sType"
					class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('course_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li><label>访问类型：</label> <form:select path="sOptype"
					class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('course_opt_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<%-- <li><label>操作标记：</label> <form:select path="sOpflag"
					class="input-medium">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('yes_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li><label>操作结果：</label> <form:select path="sOpresult"
					class="input-medium">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('yes_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li> --%>
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
				<th>课程信息</th>
				<th>课程类型</th>
				<th>访问类型</th>
				<th>操作学员</th>
				<th>观看时长(s)</th> 
				<th>更新时间</th>
				<shiro:hasPermission name="course:pbsCourseoperate:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsCourseoperate">
				<tr>
					<td><a
						href="${ctx}/course/pbsCourseoperate/form?id=${pbsCourseoperate.id}">
							${pbsCourseoperate.coursename} </a></td>
					<td>${fns:getDictLabel(pbsCourseoperate.SType, 'course_type', '')}
					</td>
					<td>${fns:getDictLabel(pbsCourseoperate.SOptype, 'course_opt_type', '')}
					</td>
					<td>${pbsCourseoperate.sBindmember.SName }</td>
					 <td>${pbsCourseoperate.ITime} </td>
					<td><fmt:formatDate value="${pbsCourseoperate.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<shiro:hasPermission name="course:pbsCourseoperate:edit">
						<td><a
							href="${ctx}/course/pbsCourseoperate/form?id=${pbsCourseoperate.id}" title = "修改"><i class="icon icon-pencil"></i>
							</a>
							<a
							href="${ctx}/course/pbsCourseoperate/delete?id=${pbsCourseoperate.id}"
							onclick="return confirmx('确认要删除该课程操作信息吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>