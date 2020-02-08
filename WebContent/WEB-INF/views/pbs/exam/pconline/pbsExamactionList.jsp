<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>考试信息管理</title>
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
		<li class="active"><a>考试信息列表</a></li>
		<li><a href="${ctx}/exam/testPC/officeSelfList">考试信息添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsExamaction"
		action="${ctx}/exam/testPC/selftestList" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>试卷编号：</label> <form:input path="sExampaper.sName"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
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
				<th>试卷编号</th>
				<th>答题者编号</th>
				<th>开始答题时间</th>
				<th>考试成绩</th>
				<th>更新时间</th>
				<shiro:hasPermission name="exam:pbsExamaction:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsExamaction">
				<tr>
					<td><a
						href="${ctx}/exam/testPC/examcheck?id=${pbsExamaction.id}">
							${pbsExamaction.sExampaper.SName} </a></td>
					<td>${pbsExamaction.sExaminee.SName}</td>
					<td><fmt:formatDate value="${pbsExamaction.dtStart}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${pbsExamaction.IReport}</td>
					<td><fmt:formatDate value="${pbsExamaction.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<shiro:hasPermission name="exam:pbsExamaction:edit">
						<td><a
							href="${ctx}/exam/testPC/examcheck?id=${pbsExamaction.id}">查看</a>
						</td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>