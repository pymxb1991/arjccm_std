<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>app版本管理</title>
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
		<li class="active"><a href="${ctx}/appversion/appVersion/">app版本列表</a></li>
		<shiro:hasPermission name="appversion:appVersion:edit"><li><a href="${ctx}/appversion/appVersion/form">app版本添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="appVersion" action="${ctx}/appversion/appVersion/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>版本code：</label>
				<form:input path="versionCode" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>版本号：</label>
				<form:input path="version" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>版本code</th>
				<th>版本号</th>
				<th>下载地址</th>
				<th>更新时间</th>
				<th>描述信息</th>
				<shiro:hasPermission name="appversion:appVersion:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="appVersion">
			<tr>
				<td><a href="${ctx}/appversion/appVersion/form?id=${appVersion.id}">
					${appVersion.versionCode}
				</a></td>
				<td>
					${appVersion.version}
				</td>
				<td>
					<a download="${appVersion.download.split("\\|")[1]}" href="${appVersion.download.split("\\|")[1]}">
							文件下载
					</a>
				</td>
				<td>
					<fmt:formatDate value="${appVersion.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${appVersion.remarks}
				</td>
				<shiro:hasPermission name="appversion:appVersion:edit"><td>
    				<a href="${ctx}/appversion/appVersion/form?id=${appVersion.id}">修改</a>
					<a href="${ctx}/appversion/appVersion/delete?id=${appVersion.id}" onclick="return confirmx('确认要删除该app版本吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>