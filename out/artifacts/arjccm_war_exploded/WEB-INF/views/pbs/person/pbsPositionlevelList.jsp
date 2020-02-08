<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>职位信息管理</title>
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
		<li class="active"><a href="${ctx}/person/pbsPositionlevel/">职位信息列表</a></li>
		<shiro:hasPermission name="person:pbsPositionlevel:edit"><li><a href="${ctx}/person/pbsPositionlevel/form">职位信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsPositionlevel" action="${ctx}/person/pbsPositionlevel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>职位/头衔：</label>
				<form:select path="sType" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('positionlevel')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>对象名称：</label>
				<form:input path="sName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>对象编码：</label>
				<form:input path="sWorkcode" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>对象等级：</label>
				<form:input path="sLevel" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>对象名称</th>
				<th>职位/头衔</th>
				<th>对象编码</th>
				<th>对象等级</th>
				<th>更新时间</th>
				<!-- <th>备注信息</th> -->
				<shiro:hasPermission name="person:pbsPositionlevel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsPositionlevel">
			<tr>
				<td><a href="${ctx}/person/pbsPositionlevel/form?id=${pbsPositionlevel.id}">
					${pbsPositionlevel.SName}
				</a></td>
				<td>
					${fns:getDictLabel(pbsPositionlevel.SType, 'positionlevel', '')}
				</td>
				<td>
					${pbsPositionlevel.SWorkcode}
				</td>
				<td>
					${pbsPositionlevel.SLevel}
				</td>
				<td>
					<fmt:formatDate value="${pbsPositionlevel.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%-- <td>
					${pbsPositionlevel.remarks}
				</td> --%>
				<shiro:hasPermission name="person:pbsPositionlevel:edit"><td>
    				<a href="${ctx}/person/pbsPositionlevel/form?id=${pbsPositionlevel.id}" title = "修改"><i class="icon icon-pencil"></i></a>
					<a href="${ctx}/person/pbsPositionlevel/delete?id=${pbsPositionlevel.id}" onclick="return confirmx('确认要删除该职位信息吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>