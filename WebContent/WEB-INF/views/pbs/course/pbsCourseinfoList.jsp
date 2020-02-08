<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>课程信息管理</title>
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
		<li class="active"><a href="${ctx}/course/pbsCourseinfo/">课程信息列表</a></li>
		<shiro:hasPermission name="course:pbsCourseinfo:edit"><li><a href="${ctx}/course/pbsCourseinfo/form">课程信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsCourseinfoEx" action="${ctx}/course/pbsCourseinfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>科目id：</label>
				<form:input path="sParentid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li> --%>
			<li><label>课程类型：</label>
				<form:select path="sType" class="input-large ">
                    <form:option value="" label="全部" />
                    <form:options items="${fns:getDictList('course_type')}"
                        itemLabel="label" itemValue="value" htmlEscape="false" />
                </form:select>
			</li>
			<li><label>课程名称：</label>
				<form:input path="sName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>课程类型</th>
				<th>课程名称</th>
				<th>展示排序</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="course:pbsCourseinfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsCourseinfoEx">
			<tr>
				<td><a href="${ctx}/course/pbsCourseinfo/form?id=${pbsCourseinfoEx.id}">
					   ${fns:getDictLabel(pbsCourseinfoEx.SType, 'course_type', '')}
				</a></td>
				<td>
					${pbsCourseinfoEx.SName}
				</td>
				<td>
					${pbsCourseinfoEx.ISort}
				</td>
				<td>
					<fmt:formatDate value="${pbsCourseinfoEx.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${pbsCourseinfoEx.remarks}
				</td>
				<shiro:hasPermission name="course:pbsCourseinfo:edit"><td>
    				<a href="${ctx}/course/pbsCourseinfo/form?id=${pbsCourseinfoEx.id}" title = "修改"><i class="icon icon-pencil"></i>
					</a>
					<a href="${ctx}/course/pbsCourseinfo/delete?id=${pbsCourseinfoEx.id}" onclick="return confirmx('确认要删除该课程信息吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>