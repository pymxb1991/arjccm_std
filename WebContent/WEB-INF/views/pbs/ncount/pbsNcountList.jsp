<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>统计类别管理</title>
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
		<li class="active"><a href="${ctx}/ncount/pbsNcount/">统计类别列表</a></li>
		<%-- <shiro:hasPermission name="ncount:pbsNcount:edit"><li><a href="${ctx}/ncount/pbsNcount/form">统计类别添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsNcount" action="${ctx}/ncount/pbsNcount/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>统计类别：</label>
				<form:select path="sType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('stat_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>统计名称：</label>
				<form:input path="sName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>统计类别</th>
				<th>统计名称</th>
				<!-- <th>统计总数</th> -->
				<th>统计数据日期</th>
				<th>更新时间</th>
				<%-- <shiro:hasPermission name="ncount:pbsNcount:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsNcount">
			<tr>
				<td><a href="${ctx}/ncount/pbsNcount/form?id=${pbsNcount.id}">
					${fns:getDictLabel(pbsNcount.SType, 'stat_type', '')}
				</a></td>
				<td>
					${pbsNcount.SName}
				</td>
				<%-- <td>
					${pbsNcount.INumber}
				</td> --%>
				<td>
					<fmt:formatDate value="${pbsNcount.dtDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${pbsNcount.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%--目前不需要删除功能，暂时注释掉 2078-08-06 qxs --%>
				<%--<shiro:hasPermission name="ncount:pbsNcount:edit"><td>
    				<a href="${ctx}/ncount/pbsNcount/form?id=${pbsNcount.id}">修改</a>
					 <a href="${ctx}/ncount/pbsNcount/delete?id=${pbsNcount.id}" onclick="return confirmx('确认要删除该统计类别吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>