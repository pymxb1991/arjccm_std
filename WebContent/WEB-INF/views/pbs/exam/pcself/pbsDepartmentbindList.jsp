<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>学员部门关系管理</title>
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
		<li><a href="${ctx}/exam/testPC/selftestList">自测列表</a></li>
		<li class="active"><a href="${ctx}/exam/testPC/officeSelfList">学习自测</a></li>
		<li><a href="${ctx}/exam/testPC/errorExam">自测错题库</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsDepartmentbind"
		action="${ctx}/exam/testPC/officeSelfList" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>党支部名称：</label> <form:input path="officename"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li><label>学员名称：</label> <form:input path="partymemname"
					htmlEscape="false" maxlength="30" class="input-medium" /></li>
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
				<th>党支部名称</th>
				<th>学员名称</th>
				<th>担任职务</th>
				<th>任职开始日期</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsDepartmentbind">
				<tr>
					<td><a
						href="${ctx}/exam/testPC/examtest?sDepartment=${pbsDepartmentbind.SDepartmentid}">
							${pbsDepartmentbind.officename} </a></td>
					<td>${pbsDepartmentbind.partymemname}</td>
					<td>${pbsDepartmentbind.SPost.SName}</td>
					<td><fmt:formatDate value="${pbsDepartmentbind.dtPosttime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><a
						href="${ctx}/exam/testPC/examtest?sDepartment=${pbsDepartmentbind.SDepartmentid}">开始测试</a>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>