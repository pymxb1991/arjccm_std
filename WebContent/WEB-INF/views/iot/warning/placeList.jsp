<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>重点人员信息感知</title>
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
		<li class="active"><a href="${ctx}/warning/ccmEarlyWarning/placeList">场所预警</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmEarlyWarning" action="${ctx}/warning/ccmEarlyWarning/placeList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>身份证：</label>
				<form:input path="idCard" htmlEscape="false" maxlength="2" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>身份证</th>
				<th>特殊类型</th>
				<th>预警设备</th>
				<th>位置</th>
				<th>状态</th>
				<th>预警时间</th>
				<th>照片</th>
				<shiro:hasPermission name="warning:ccmEarlyWarning:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmEarlyWarning">
			<tr>
				<td>
					${ccmEarlyWarning.name}
				</td>
				<td>
					${ccmEarlyWarning.idCard}
				</td>
				<td>
					${ccmEarlyWarning.specialType}
				</td>
				<td>
					${ccmEarlyWarning.alarmFacility}
				</td>
				<td>
					${ccmEarlyWarning.address}
				</td>
				<td>
					${ccmEarlyWarning.type}
				</td>
				<td>
					<fmt:formatDate value="${ccmEarlyWarning.alarmDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${ccmEarlyWarning.img}
				</td>
				<shiro:hasPermission name="warning:ccmEarlyWarning:edit"><td>
    				<%-- <a href="${ctx}/warning/ccmEarlyWarning/form?id=${ccmEarlyWarning.id}">修改</a> --%>
					<a href="${ctx}/warning/ccmEarlyWarning/delete?id=${ccmEarlyWarning.id}" onclick="return confirmx('确认要删除该人员记录吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>