<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>客观题信息管理</title>
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
			href="${ctx}/question/pbsQuestionObjective/">客观题信息列表</a></li>
		<shiro:hasPermission name="question:pbsQuestionObjective:edit">
			<li><a href="${ctx}/question/pbsQuestionObjective/addform">客观题信息添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsQuestionObjective"
		action="${ctx}/question/pbsQuestionObjective/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<%-- <li><label>科目ID：</label>
				<form:select path="sParentid" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li> --%>
			<li><label>题目：</label> <form:input path="sBody"
					htmlEscape="false" maxlength="10" class="input-medium" /></li>
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
				<!-- <th>科目</th> -->
				<th>题目</th>
				<th>试题类型</th>
				<th>答案</th>
				<th>分值</th>
				<th>难度等级</th>
				<shiro:hasPermission name="question:pbsQuestionObjective:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsQuestionObjective">
				<tr>
					<%-- <td><a
						href="${ctx}/question/pbsQuestionObjective/checkform?id=${pbsQuestionObjective.id}">
							${pbsQuestionObjective.sParentid.SName} </a></td> --%>
					<td><a
						href="${ctx}/question/pbsQuestionObjective/checkform?id=${pbsQuestionObjective.id}">${pbsQuestionObjective.SBody}</a></td>
					<td>${fns:getDictLabel(pbsQuestionObjective.SType, 'objectivetype', '')}
					</td>
					<td>${pbsQuestionObjective.SAnswer}</td>
					<td>${pbsQuestionObjective.IValue}</td>
					<td>${pbsQuestionObjective.SLevel}</td>
					<shiro:hasPermission name="question:pbsQuestionObjective:edit">
						<td><a
							href="${ctx}/question/pbsQuestionObjective/checkform?id=${pbsQuestionObjective.id}" title = "修改"><i class="icon icon-pencil"></i></a>
							<a
							href="${ctx}/question/pbsQuestionObjective/delete?id=${pbsQuestionObjective.id}"
							onclick="return confirmx('确认要删除该客观题信息吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>