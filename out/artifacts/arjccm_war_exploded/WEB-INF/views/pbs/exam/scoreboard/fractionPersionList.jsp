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
		<li class="active"><a href="${ctx}/fraction/Pc/persionlist">个人分数排行</a></li>
		<li><a href="${ctx}/fraction/Pc/officelist">部门分数排行</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsExamaction"
		action="${ctx}/fraction/Pc/persionlist" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>当前学员：</label> <input class="input-medium" type="text"
				value="${user.SName}" readonly="readonly" /></li>
			<li>
			<li><label>分数：</label> <input class="input-medium" type="text"
				value="<c:if test='${! empty pbsExamactionCur.IReport}'>${pbsExamactionCur.IReport}</c:if><c:if test='${ empty pbsExamactionCur.IReport}'>您未参加此次考试。 </c:if>"
				readonly="readonly" /></li>
			<li>
			<li><label>答题者：</label> <form:input path="sExaminee.sName"
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
				<th>名次</th>
				<th>头像</th>
				<th>试卷名称</th>
				<th>部门</th>
				<th>答题者</th>
				<th>考试成绩</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsExamaction" varStatus="examRanking">
				<tr>
					<td>${examRanking.index+1}</td>
					<td><img style="max-height: 50px;"
						class="mui-media-object mui-pull-left"
						onerror='this.src="${ctxStatic}/wechat/img/head.png"'
						src="${pbsExamaction.sExaminee.SPhoto}"></td>
					<td>${pbsExamaction.sExampaper.SName}</td>
					<td>${pbsExamaction.sDepartment.name}</td>
					<td>${pbsExamaction.sExaminee.SName}</td>

					<td>${pbsExamaction.IReport}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>