<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
		<li class="active"><a href="${ctx}/act/actUtConfig/">配置列表</a></li>
		<shiro:hasPermission name="act:actUtConfig:edit"><li><a href="${ctx}/act/actUtConfig/form">配置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="actUtConfig" action="${ctx}/act/actUtConfig/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>流程标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="512" class="input-medium"/>
			</li>
			<li><label>流程类别：</label>
				<form:select path="processType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('act_category')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>流程名称：</label>
				<form:input path="processName" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li><label>表单名：</label>
				<form:input path="formKeyName" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>流程标题</th>
				<th>流程类别</th>
				<th>流程定义id</th>
				<th>流程名称</th>
				<th>数据库表名</th>
				<th>表单名</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="act:actUtConfig:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="actUtConfig">
			<tr>
				<td><a href="${ctx}/act/actUtConfig/form?id=${actUtConfig.id}">
					${actUtConfig.title}
				</a></td>
				<td>
					${fns:getDictLabel(actUtConfig.processType, 'act_category', '')}
				</td>
				<td>
					${actUtConfig.processId}
				</td>
				<td>
					${actUtConfig.processName}
				</td>
				<td>
					${actUtConfig.table}
				</td>
				<td>
					${actUtConfig.formKeyName}
				</td>
				<td>
					<fmt:formatDate value="${actUtConfig.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${actUtConfig.remarks}
				</td>
				<shiro:hasPermission name="act:actUtConfig:edit"><td>
    				<a href="${ctx}/act/actUtConfig/form?id=${actUtConfig.id}">修改</a>
					<a href="${ctx}/act/actUtConfig/delete?id=${actUtConfig.id}" onclick="return confirmx('确认要删除该配置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>