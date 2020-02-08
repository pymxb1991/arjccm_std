<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>巡逻人员管理</title>
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
		<li class="active"><a href="${ctx}/patrol/ccmPatrolUser/">数据列表</a></li>
		<shiro:hasPermission name="patrol:ccmPatrolUser:edit"><li><a href="${ctx}/patrol/ccmPatrolUser/form">数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmPatrolUser" action="${ctx}/patrol/ccmPatrolUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
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
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="patrol:ccmPatrolUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmPatrolUser">
			<tr>
				<td><a href="${ctx}/patrol/ccmPatrolUser/form?id=${ccmPatrolUser.id}">
					<fmt:formatDate value="${ccmPatrolUser.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${ccmPatrolUser.remarks}
				</td>
				<shiro:hasPermission name="patrol:ccmPatrolUser:edit"><td>
    				<a class="btnList" href="${ctx}/patrol/ccmPatrolUser/form?id=${ccmPatrolUser.id}" title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/patrol/ccmPatrolUser/delete?id=${ccmPatrolUser.id}" onclick="return confirmx('确认要删除该巡逻人员吗？', this.href)" title="删除"><i class="icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>