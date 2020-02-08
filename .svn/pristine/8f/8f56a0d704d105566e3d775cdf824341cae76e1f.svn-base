<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>消息提醒信息管理</title>
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
		<li class="active"><a href="${ctx}/sys/pbsRemindMsg/">消息提醒信息列表</a></li>
		<shiro:hasPermission name="sys:pbsRemindMsg:edit"><li><a href="${ctx}/sys/pbsRemindMsg/form">消息提醒信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsRemindMsg" action="${ctx}/sys/pbsRemindMsg/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>模块功能：</label>
				<form:select path="sFuncionid" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('notice_fun')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>读取状态：</label>
				<form:select path="sStat" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>模块功能</th>
				<th>读取状态</th>
				<th>更新时间</th>
				<shiro:hasPermission name="sys:pbsRemindMsg:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsRemindMsg">
			<tr>
				<td><a href="${ctx}/sys/pbsRemindMsg/form?id=${pbsRemindMsg.id}">
					${fns:getDictLabel(pbsRemindMsg.SFuncionid, 'notice_fun', '')}
				</a></td>
				<td>
					${fns:getDictLabel(pbsRemindMsg.SStat, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${pbsRemindMsg.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="sys:pbsRemindMsg:edit"><td>
    				<a href="${ctx}/sys/pbsRemindMsg/form?id=${pbsRemindMsg.id}">修改</a>
					<a href="${ctx}/sys/pbsRemindMsg/delete?id=${pbsRemindMsg.id}" onclick="return confirmx('确认要删除该消息提醒信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
