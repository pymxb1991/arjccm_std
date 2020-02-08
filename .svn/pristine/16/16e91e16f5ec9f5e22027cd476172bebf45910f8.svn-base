<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动信息管理</title>
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
		<li class="active"><a href="${ctx}/activity/pbsActivityrec/">活动信息列表</a></li>
		<%-- <shiro:hasPermission name="activity:pbsActivityrec:edit"><li><a href="${ctx}/activity/pbsActivityrec/form">活动信息添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsActivityrec" action="${ctx}/activity/pbsActivityrec/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>活动标题：</label>
				<form:input path="sTitle" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<%-- <li><label>活动类型：</label>
				<form:select path="sType" class="input-medium">
					<form:option value="" label=""/>
				</form:select>
			</li> --%>
			<li><label>活动负责人：</label>
				<form:input path="sMastermem.sName" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>参加人员姓名列表：</label>
				<form:input path="sAttendants" htmlEscape="false" maxlength="1000" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>活动标题</th>
				<th>活动类型</th>
				<th>活动负责人</th>
				<th>参加人员姓名</th>
				<th>活动开始时间</th>
				<th>活动结束时间</th>
				<th>活动地点</th>
				<th>更新时间</th>
				<shiro:hasPermission name="activity:pbsActivityrec:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsActivityrec">
			<tr>
				<td><a href="${ctx}/activity/pbsActivityrec/form?id=${pbsActivityrec.id}">
					${pbsActivityrec.STitle}
				</a></td>
				<td>
					${pbsActivityrec.sType.SName}
				</td>
				<td>
					${pbsActivityrec.SMastermem.SName}
				</td>
				<td>
					${pbsActivityrec.SAttendants}
				</td>
				<td>
					<fmt:formatDate value="${pbsActivityrec.dtStarttime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${pbsActivityrec.dtEndtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${pbsActivityrec.SPlace}
				</td>
				<td>
					<fmt:formatDate value="${pbsActivityrec.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="activity:pbsActivityrec:edit"><td>
    				<a href="${ctx}/activity/pbsActivityrec/form?id=${pbsActivityrec.id}" title = "修改"><i class="icon icon-pencil"></i></a>
					<a href="${ctx}/activity/pbsActivityrec/delete?id=${pbsActivityrec.id}" onclick="return confirmx('确认要删除该活动信息吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>