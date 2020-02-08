<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>综合试卷信息管理</title>
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
		<li class="active"><a href="${ctx}/exam/pbsExampaper/?sWay=2">综合试卷列表</a></li>
		<shiro:hasPermission name="exam:pbsExampaper:edit">
			<li><a href="${ctx}/exam/pbsExampaper/formSynthesize">综合试卷添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsExampaper"
		action="${ctx}/exam/pbsExampaper/?sWay=2" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>试卷名称：</label> <form:input path="sName"
					htmlEscape="false" maxlength="200" class="input-medium" /></li>
			<li><label>展示标题：</label> <form:input path="sTitle"
					htmlEscape="false" maxlength="200" class="input-medium" /></li>
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
				<th>展示标题</th>
				<th>科目编号</th>
				<th>是否发布</th>
				<th>考试开始时间</th>
				<th>考试时间(分)</th>
				<th>更新时间</th>
				<shiro:hasPermission name="exam:pbsExampaper:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsExampaper">
				<tr>
					<td><a
						href="${ctx}/exam/pbsExampaper/paperDetail?id=${pbsExampaper.id}">
							${pbsExampaper.SName} </a></td>
					<td>${pbsExampaper.STitle}</td>
					<td>${pbsExampaper.sProject.SName}</td>
					<td>${fns:getDictLabel(pbsExampaper.sStat, 'yes_no', '否')}
					</td>
					<td><fmt:formatDate value="${pbsExampaper.dtStart}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${pbsExampaper.IExamtime}</td>

					<td><fmt:formatDate value="${pbsExampaper.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<shiro:hasPermission name="exam:pbsExampaper:edit">
						<td><a
							href="${ctx}/exam/pbsExampaper/formSynthesize?id=${pbsExampaper.id}" title = "修改"><i class="icon icon-pencil"></i></a>
							<a href="${ctx}/exam/pbsExampaper/delete?id=${pbsExampaper.id}"
							onclick="return confirmx('确认要删除该试卷信息吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a> <a
							href="${ctx}/exam/pbsExampaper/handleform?id=${pbsExampaper.id}&sStat=1&sWay=2"
							onclick="return confirmx('确认要发布该试卷信息吗？', this.href)" title = "发布"><i class="icon-fankui"></i></a> <a
							href="${ctx}/exam/pbsExampaper/handleform?id=${pbsExampaper.id}&sStat=0&sWay=2"
							onclick="return confirmx('确认要撤销该试卷信息吗？', this.href)" title = "撤销"><i class="icon-reply"></i></a> <a
							href="${ctx}/exam/pbsExampaper/paperDetail?id=${pbsExampaper.id}&sWay=2" title = "查看"><i class="icon-eye-open"></i></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>