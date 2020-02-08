<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>编码方案管理</title>
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
		<li class="active"><a href="${ctx}/sys/sysCodes/">编码方案列表</a></li>
		<shiro:hasPermission name="sys:sysCodes:edit"><li><a href="${ctx}/sys/sysCodes/form">编码方案添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysCodes" action="${ctx}/sys/sysCodes/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>编码类型：</label>
				<form:select path="codeType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('code_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>首部编码一般固定</th>
				<th>次级编码YYYY年份</th>
				<th>三级编码MM月份</th>
				<th>四级编码DD日期</th>
				<th>存储下一个编号</th>
				<th>编码类型</th>
				<shiro:hasPermission name="sys:sysCodes:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysCodes">
			<tr>
				<td><a href="${ctx}/sys/sysCodes/form?id=${sysCodes.id}">
					${sysCodes.codeA}
				</a></td>
				<td>
					${sysCodes.codeB}
				</td>
				<td>
					${sysCodes.codeC}
				</td>
				<td>
					${sysCodes.codeD}
				</td>
				<td>
					${sysCodes.codeE}
				</td>
				<td>
					${fns:getDictLabel(sysCodes.codeType, 'code_type', '')}
				</td>
				<shiro:hasPermission name="sys:sysCodes:edit"><td>
    				<a href="${ctx}/sys/sysCodes/form?id=${sysCodes.id}">修改</a>
					<a href="${ctx}/sys/sysCodes/delete?id=${sysCodes.id}" onclick="return confirmx('确认要删除该编码方案吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>