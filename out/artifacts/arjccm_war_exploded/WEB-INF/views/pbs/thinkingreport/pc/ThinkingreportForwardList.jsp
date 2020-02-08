<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>思想汇报信息管理</title>
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
		<li><a href="${ctx}/thinkingreport/Pc/thinkRepValueList">我的思想汇报</a></li>
		<li class="active"><a
			href="${ctx}/thinkingreport/Pc/thinkRepForwardList">被转发的思想汇报</a></li>
		<li><a href="${ctx}/thinkingreport/Pc/thinknewform">新的思想汇报</a></li>

	</ul>
	<form:form id="searchForm" modelAttribute="pbsThinkingreport"
		action="${ctx}/thinkingreport/Pc/thinkRepForwardList" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>思想汇报的标题：</label> <form:input path="sTitle"
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
				<th>思想汇报的标题</th>
				<th>发布者</th>
				<th>发布者学员</th>
				<th>接收者学员</th>
				<th>状态</th>
				<th>更新时间</th>
				<shiro:hasPermission name="thinkingreport:pbsThinkingreport:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsThinkingreport">
				<tr>
					<td><a
						href="${ctx}/thinkingreport/Pc/thinkRepInfo?id=${pbsThinkingreport.id}">
							${pbsThinkingreport.STitle} </a></td>
					<td>${pbsThinkingreport.sReporteruser.name}</td>
					<td>${pbsThinkingreport.sReportermem.SName}</td>
					<td>${pbsThinkingreport.sAcceptermem.SName}</td>
					<td>${fns:getDictLabel(pbsThinkingreport.SStat, 'thinkreppublishtype', '')}
					</td>
					<td><fmt:formatDate value="${pbsThinkingreport.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><a
						href="${ctx}/thinkingreport/Pc/thinkRepInfo?id=${pbsThinkingreport.id}"
						title="修改"><i class="icon icon-pencil"></i></a> </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>