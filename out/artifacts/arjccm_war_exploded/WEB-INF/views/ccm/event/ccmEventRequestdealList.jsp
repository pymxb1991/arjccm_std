<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>请求处理管理</title>
<meta name="decorator" content="default" />
<script charset="utf-8" type="text/javascript"
	src="${ctxStatic}/ccm/validator/validatorBaseList.js"></script>
		 <script>
	$(document).ready(function() {
		$("#btnSubmit").on("click" ,function(){
			$("#searchForm").submit();
		})
	});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/event/ccmEventRequestdeal/">请求处理列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmEventRequestdeal"
		action="${ctx}/event/ccmEventRequestdeal/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>请求名称：</label> <form:input path="caseName"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li><label>处理单位：</label> <form:input path="dealUnit"
					htmlEscape="false" maxlength="200" class="input-medium" /></li>
			<li><label>请求负责人：</label> <form:input path="eventPrincipal"
					htmlEscape="false" maxlength="20" class="input-medium" /></li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li> -->
				<li class="btns">
			<a href="javascript:;" id="btnSubmit" class="btn btn-primary">
                <i class="icon-search"></i> 查询 </a>
			</li>
			<li class="clearfix"></li>
			<li><label>个人电话：</label> <form:input path="telPerson"
					htmlEscape="false" maxlength="30" class="input-medium" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>请求名称</th>
				<th>处理单位</th>
				<th>请求负责人</th>
				<th>个人电话</th>
				<th>受理人</th>
				<th>处理日期</th>
				<th>请求说明</th>
				<shiro:hasPermission name="event:ccmEventRequestdeal:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="ccmEventRequestdeal">
				<tr>
					<td><a href="${ctx}/event/ccmEventRequestdeal/form?id=${ccmEventRequestdeal.id}">
							${ccmEventRequestdeal.caseName} </a></td>
					<td>${ccmEventRequestdeal.dealUnit}</td>
					<td>${ccmEventRequestdeal.eventPrincipal}</td>
					<td>${ccmEventRequestdeal.telPerson}</td>
					<td>${ccmEventRequestdeal.createName}</td>
					<td><fmt:formatDate value="${ccmEventRequestdeal.createDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${ccmEventRequestdeal.remarks}</td>
					<shiro:hasPermission name="event:ccmEventRequestdeal:edit">
						<td><a class="btnList"
							href="${ctx}/event/ccmEventRequestdeal/form?id=${ccmEventRequestdeal.id}" title="处理"><i class="icon-pencil"></i></a>
							<a class="btnList"
							href="${ctx}/event/ccmEventRequestdeal/delete?id=${ccmEventRequestdeal.id}"
							onclick="return confirmx('确认要删除该请求处理吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>