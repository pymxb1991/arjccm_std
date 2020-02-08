<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>专家库管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmit").on("click" ,function(){
				$("#searchForm").submit();
			})
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/manage/riskSpecialist/">专家库列表</a></li>
		<shiro:hasPermission name="manage:riskSpecialist:edit"><li><a href="${ctx}/manage/riskSpecialist/form">专家库添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="riskSpecialist" action="${ctx}/manage/riskSpecialist/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>性别：</label>
				<form:select path="sex" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>成员类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('risk_specialist_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->
				<li class="btns">
			<a href="javascript:;" id="btnSubmit" class="btn btn-primary">
                <i class="icon-search"></i> 查询 </a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>性别</th>
				<th>出生年月</th>
				<th>成员类型</th>
				<th>研究领域</th>
				<shiro:hasPermission name="manage:riskSpecialist:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="riskSpecialist">
			<tr>
				<td><a href="${ctx}/manage/riskSpecialist/form?id=${riskSpecialist.id}">
					${riskSpecialist.name}
				</a></td>
				<td>
					${fns:getDictLabel(riskSpecialist.sex, 'sex', '')}
				</td>
				<td>
					<fmt:formatDate value="${riskSpecialist.birthday}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${fns:getDictLabel(riskSpecialist.type, 'risk_specialist_type', '')}
				</td>
				<td>
					${riskSpecialist.domain}
				</td>
				<shiro:hasPermission name="manage:riskSpecialist:edit"><td>
    				<a class="btnList" href="${ctx}/manage/riskSpecialist/form?id=${riskSpecialist.id}" title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/manage/riskSpecialist/delete?id=${riskSpecialist.id}" onclick="return confirmx('确认要删除该专家库吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>