<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>申请记录管理</title>
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
		<li class="active"><a href="${ctx}/apply/ApplyPc/">申请记录列表</a></li>
			<li><a href="${ctx}/apply/ApplyPc/applyNew">申请记录添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsApplyrec"
		action="${ctx}/apply/ApplyPc/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>申请简述信息：</label> <form:input path="sResume"
					htmlEscape="false" maxlength="500" class="input-medium" /></li>
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
				<th>申请简述信息</th>
				<th>申请类型</th>
				<th>记录状态</th>
				<th>更新时间</th>
				<shiro:hasPermission name="apply:pbsApplyrec:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsApplyrec">
				<tr>
					<td><a
						href="${ctx}/apply/ApplyPc/applyInfo?id=${pbsApplyrec.id}">
							${pbsApplyrec.SResume} </a></td>
					<td>${pbsApplyrec.sType.SName}</td>
					<td>${fns:getDictLabel(pbsApplyrec.SStatus, 'flowresult', '')}
					</td>
					<td><fmt:formatDate value="${pbsApplyrec.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<shiro:hasPermission name="apply:pbsApplyrec:edit">
						<td><a
							href="${ctx}/apply/ApplyPc/applyInfo?id=${pbsApplyrec.id}"
							title="查看"><i class="icon-eye-open"></i></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>