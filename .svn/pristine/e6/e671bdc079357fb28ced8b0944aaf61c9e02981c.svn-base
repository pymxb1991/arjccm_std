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
	<form:form id="searchForm" modelAttribute="pbsDepartmentbind"
		action="${ctx}/pbsDepartmentbind/Pc" method="post"
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
				<th>学员头像</th>
				<th>学员名称</th>
				<th>组织机构</th>
				<th>担任职务</th>
				<!-- <th>工作岗位</th> -->
				<th>联系电话</th>
				<th>更新时间</th>
				<th>任职开始日期</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsDepartmentbind">
				<tr>
					<td style="width:60px;text-align:center">
						<img onerror="this.src='${ctxStatic}/images/headPC.png'" src="${pbsDepartmentbind.partymem.SPhoto}" style="width:40px;height:40px"/>
					</td>
					<td>${pbsDepartmentbind.partymemname}</td>
					<td>${pbsDepartmentbind.officename}</td>
					<td>${pbsDepartmentbind.SPost.SName}</td>
					<%-- <td>${pbsDepartmentbind.partymem.SPost}</td> --%>
					<td><img src="${ctxStatic}/statis/img/shouji.png" style="width:15px"/>
						${pbsDepartmentbind.partymem.SMobilephone}
					</td>
					<td><fmt:formatDate value="${pbsDepartmentbind.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${pbsDepartmentbind.dtPosttime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>