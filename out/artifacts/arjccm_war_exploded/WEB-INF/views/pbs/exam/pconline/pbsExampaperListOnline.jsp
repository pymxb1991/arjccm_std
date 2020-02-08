<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>在线试卷信息管理</title>
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
		<li class="active"><a>在线试卷列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsExampaper"
		action="${ctx}/exam/testPC/onlineList" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>试卷名称：</label> <form:input path="sName"
					htmlEscape="false" maxlength="200" class="input-medium" /></li>
			<%-- <li><label>展示标题：</label> <form:input path="sTitle"
					htmlEscape="false" maxlength="200" class="input-medium" /></li> --%>
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
				<th>试卷名称</th>
				<th>科目编号</th>
				<th>是否参加</th>
				<th>考试分数</th>
				<th>考试开始时间</th>
				<th>考试时间(分)</th>

				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsExampaper">
				<tr>
					<td><c:if test='${pbsExampaper.iReport eq -1}'>
							<a href="${ctx}/exam/testPC/onlinetest?id=${pbsExampaper.id}">
								${pbsExampaper.SName} </a></c:if>
					    <c:if test='${pbsExampaper.iReport ne -1}'>
					        <a href="${ctx}/exam/testPC/onlinecheck?id=${pbsExampaper.examActionId}">
                                ${pbsExampaper.SName} </a></c:if>
					    </td>
					<td>${pbsExampaper.sProject.SName}</td>
					<td>${fns:getDictLabel(pbsExampaper.writeFlag,'yes_no',"")}</td>
					<td><c:if test='${pbsExampaper.iReport eq -1}'>尚未参加</c:if> <c:if
							test='${pbsExampaper.iReport ne -1}'>${pbsExampaper.iReport}</c:if>
					</td>
					<td><fmt:formatDate value="${pbsExampaper.dtStart}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${pbsExampaper.IExamtime}</td>
					<td><c:if test='${pbsExampaper.iReport eq -1}'>
							<a href="${ctx}/exam/testPC/onlinetest?id=${pbsExampaper.id}">开始考试</a>
						</c:if> <c:if test='${pbsExampaper.iReport ne -1}'>
							<a
								href="${ctx}/exam/testPC/onlinecheck?id=${pbsExampaper.examActionId}">查看试卷</a>
						</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>