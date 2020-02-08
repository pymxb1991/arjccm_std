<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>跟踪信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnSubmit").on("click" ,function(){
			$("#searchForm").submit();
		})
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
<%-- 		<li class="active"><a href="${ctx}/log/ccmLogTail/">跟踪信息列表</a></li> --%>
		<!-- 
		<shiro:hasPermission name="log:ccmLogTail:edit">
			<li><a href="${ctx}/log/ccmLogTail/form">跟踪信息添加</a></li>
		</shiro:hasPermission>
		-->
	</ul>
	<form:form id="searchForm" modelAttribute="ccmLogTail"
		action="${ctx}/log/ccmLogTail/list" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<form:hidden path="relevanceId" />
		<form:hidden path="relevanceTable" />
		<%-- <ul class="ul-form">
			<li><label>跟踪事项：</label> <form:input path="tailCase"
					htmlEscape="false" maxlength="100" class="input-medium" /></li>
			<li><label>跟踪对象：</label> <form:select path="relevanceTable"
					class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('ccm_log_tail_table')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li><label>处理人员：</label> <form:input path="tailPerson"
					htmlEscape="false" maxlength="100" class="input-medium" /></li>
			<li class="clearfix"></li>

			<li><label>类型：</label> <form:select path="type"
					class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('ccm_log_tail_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li><label>处理人联系方式：</label> <form:input path="more1"
					htmlEscape="false" maxlength="100" class="input-medium" /></li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li> -->
				<li class="btns">
			<a href="javascript:;" id="btnSubmit" class="btn btn-primary">
                <i class="icon-search"></i> 查询 </a>
			</li>
			<li class="clearfix"></li>
		</ul>  --%>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<!-- <th>跟踪对象</th> -->
				<th>类型</th>
				<th>跟踪事项</th>
				<th>处理人员</th>
				<th>时间</th>
				<th>处理人联系方式</th>
				<shiro:hasPermission name="log:ccmLogTail:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="ccmLogTail">
				<tr>
					<%-- <td><a href="${ctx}/log/ccmLogTail/formPro?id=${ccmLogTail.id}">
							${fns:getDictLabel(ccmLogTail.relevanceTable, 'ccm_log_tail_table', '')}
					</a></td> --%>
					<td style="height: 50px">${fns:getDictLabel(ccmLogTail.type, 'ccm_log_tail_type', '')}
					</td>
					<td style="height: 50px">${ccmLogTail.tailCase}</td>
					<td style="height: 50px">${ccmLogTail.tailPerson}</td>
					<td style="height: 50px"><fmt:formatDate value="${ccmLogTail.tailTime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td style="height: 50px">${ccmLogTail.more1}</td>
					<shiro:hasPermission name="log:ccmLogTail:edit">
						<td><a class="btnList"
							href="${ctx}/log/ccmLogTail/formPro?id=${ccmLogTail.id}&relevance_id=${ccmPeople.id}&relevance_table=ccm_people"  title="修改"><i class="icon-pencil"></i></a> <a
							class="btnList"
							href="${ctx}/log/ccmLogTail/delete?id=${ccmLogTail.id}&relevance_id=${ccmPeople.id}&relevance_table=ccm_people"
							onclick="return confirmx('确认要删除该跟踪信息吗？', this.href)"  title="删除"><i class="icon-remove-sign"></i></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination"style="float: right; margin-top: 12px">${page}</div>
</body>
</html>