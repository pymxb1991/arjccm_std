<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>申请记录管理</title>
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
		<li class="active"><a href="${ctx}/apply/pbsApplyrec/">申请记录列表</a></li>
		<shiro:hasPermission name="apply:pbsApplyrec:edit"><li><a href="${ctx}/apply/pbsApplyrec/form">申请记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsApplyrec" action="${ctx}/apply/pbsApplyrec/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>申请简述信息：</label>
				<form:input path="sResume" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>记录状态：</label>
				<form:select path="sStatus" class="input-large">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('flowresult')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
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
				<th>申请简述信息</th>
				<th>申请类型</th>
				<th>记录状态</th>
				<th>发起者</th>
				<th>更新时间</th>
				<shiro:hasPermission name="apply:pbsApplyrec:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pbsApplyrec">
			<tr>
				<td><a href="${ctx}/apply/pbsApplyrec/form?id=${pbsApplyrec.id}">
					${pbsApplyrec.SResume}
				</a></td>
				<td>
					${pbsApplyrec.sType.SName}
				</td>
				<td>
					${fns:getDictLabel(pbsApplyrec.SStatus, 'flowresult', '')}
				</td>
				<td>
                    ${pbsApplyrec.sBindmember.SName}
                </td>
				<td>
					<fmt:formatDate value="${pbsApplyrec.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="apply:pbsApplyrec:edit"><td>
    				<a href="${ctx}/apply/pbsApplyrec/form?id=${pbsApplyrec.id}" title = "修改"><i class="icon icon-pencil"></i></a>
					<a href="${ctx}/apply/pbsApplyrec/delete?id=${pbsApplyrec.id}" onclick="return confirmx('确认要删除该申请记录吗？', this.href)" title = "删除"><i class="icon icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>