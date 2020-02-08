<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>思想汇报操作管理</title>
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
			href="${ctx}/thinkingreport/pbsThinkingreportopt/">思想汇报操作列表</a></li>
		<shiro:hasPermission name="thinkingreport:pbsThinkingreportopt:edit">
			<li><a href="${ctx}/thinkingreport/pbsThinkingreportopt/form">思想汇报操作添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsThinkingreportopt"
		action="${ctx}/thinkingreport/pbsThinkingreportopt/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>思想汇报的标题：</label> <form:input path="sReportid.sTitle"
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
				<th>思想汇报操作类型</th>
				<th>转发发送的学员</th>
				<th>评价/转发的学员</th>
				<th>评价信息</th>
				<th>更新时间</th>
				<shiro:hasPermission name="thinkingreport:pbsThinkingreportopt:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsThinkingreportopt">
				<tr>
				<td><a href="${ctx}/thinkingreport/pbsThinkingreportopt/form?id=${pbsThinkingreportopt.id}">
                    ${pbsThinkingreportopt.sReportid.STitle}
                </a></td>
                 <td>
                    ${fns:getDictLabel(pbsThinkingreportopt.SType, 'thinkopttype', '')}
                </td>
                <td>
                    ${pbsThinkingreportopt.sTransmem.SName}
                </td>
                <td>
                    ${pbsThinkingreportopt.sOperatemem.SName}
                </td>
                  <td>
                  ${fns:getDictLabel(pbsThinkingreportopt.SLevel, 'taskvaluetype', '')}
                </td>
					<td><a
						href="${ctx}/thinkingreport/pbsThinkingreportopt/form?id=${pbsThinkingreportopt.id}">
							<fmt:formatDate value="${pbsThinkingreportopt.updateDate}"
								pattern="yyyy-MM-dd HH:mm:ss" />
					</a></td>
					<shiro:hasPermission
						name="thinkingreport:pbsThinkingreportopt:edit">
						<td><a
							href="${ctx}/thinkingreport/pbsThinkingreportopt/form?id=${pbsThinkingreportopt.id}" title = "修改"><i class="icon icon-pencil"></i></a>
							<a
							href="${ctx}/thinkingreport/pbsThinkingreportopt/delete?id=${pbsThinkingreportopt.id}"
							onclick="return confirmx('确认要删除该思想汇报操作吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>