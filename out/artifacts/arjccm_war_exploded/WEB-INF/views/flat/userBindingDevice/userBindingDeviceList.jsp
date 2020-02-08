<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>用户绑定设备管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$('#btnSubmit').click(function(){
			$('#searchForm').submit();
		});
	});
	function page(n, s) {
		if (n) $("#pageNo").val(n);
		if (s) $("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/userBindingDevice/userBindingDevice/finduserBindingDeviceList");
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
<%--<img  src="${ctxStatic}/images/shouyedaohang.png"; class="nav-home">--%>
<%--<span class="nav-position">当前位置 ：</span><span class="nav-menu"><%=session.getAttribute("activeMenuName")%>></span><span class="nav-menu2">设备管理</span>--%>
<div class="back-list">
	<ul class="nav nav-tabs">
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/userBindingDevice/userBindingDevice/finduserBindingDeviceList">数据列表</a></li>
		<li style="display:none;"><a href="${ctx}/userBindingDevice/userBindingDevice/form">绑定</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="userBindingDevice" action="${ctx}/userBindingDevice/userBindingDevice/finduserBindingDeviceList" method="post" class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();" />
		<ul class="ul-form pull-left">
 			<li class="first-line"><label>归属机构：</label> <sys:treeselect id="companyId" name="company.id" value="${userBindingDevice.company.id}" labelName="company.name" labelValue="${userBindingDevice.company.name}" title="机构" url="/sys/office/treeData?type=1" cssClass="input-small" allowClear="true" /></li>
			<li class="first-line"><label>登录名：</label> <form:input path="loginName" htmlEscape="false" maxlength="50" class="input-medium" /></li>
			<li class="first-line"><label>归属部门：</label> <sys:treeselect id="officeId" name="office.id" value="${userBindingDevice.office.id}" labelName="office.name" labelValue="${userBindingDevice.office.name}" title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true" /></li>
			<li class="first-line"><label>姓&nbsp;&nbsp;&nbsp;名：</label> <form:input path="name" htmlEscape="false" maxlength="50" class="input-medium" /></li>

<%--			<li class="clearfix"></li>--%>
		</ul>
	<div class="clearfix pull-right btn-box">

			<a href="javascript:;" id="btnSubmit" style="width: 49px;
   /*margin-top: 25px;*/display:inline-block;float: right" class="btn btn-primary">
				<%--<i class="icon-search"></i> --%><span style="font-size: 12px">查询</span> </a>

	</div>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed table-gradient">
		<thead>
			<tr>
				<th>归属机构</th>
				<th>归属部门</th>
				<th class="sort-column name">姓名</th>
				<th>电话</th>
				<th>手机</th>
				<th>登录名</th>
				<th>警务通</th>
				<th>执法记录仪</th>
				<th>对讲</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="userBindingDevice">
				<tr>
					<td style="height: 50px;">${userBindingDevice.company.name}</td>
					<td style="height: 50px;">${userBindingDevice.office.name}</td>
					<td style="height: 50px;"><a href="${ctx}/userBindingDevice/userBindingDevice/form?id=${userBindingDevice.id}&userId=${userBindingDevice.userId}">${userBindingDevice.name}</a></td>
					<td style="height: 50px;">${userBindingDevice.phone}</td>
					<td style="height: 50px;">${userBindingDevice.mobile}</td>
					<td style="height: 50px;">${userBindingDevice.loginName}</td>
					<c:choose>
						<c:when test="${userBindingDevice.defualtDevice == 0}">
							<td style="height: 50px;"><span style="color:red;">${userBindingDevice.policePhoneCode}</span></td>
						</c:when>
						<c:otherwise>
							<td style="height: 50px;">${userBindingDevice.policePhoneCode}</td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${userBindingDevice.defualtDevice == 1}">
							<td style="height: 50px;"><span style="color:red;">${userBindingDevice.actionRecoderCode}</span></td>
						</c:when>
						<c:otherwise>
							<td style="height: 50px;">${userBindingDevice.actionRecoderCode}</td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${userBindingDevice.defualtDevice == 2}">
							<td style="height: 50px;"><span style="color:red;">${userBindingDevice.interPhoneCode}</span></td>
						</c:when>
						<c:otherwise>
							<td style="height: 50px;">${userBindingDevice.interPhoneCode}</td>
						</c:otherwise>
					</c:choose>
					<td style="height: 50px;"><a class="btnList" href="${ctx}/userBindingDevice/userBindingDevice/form?id=${userBindingDevice.id}&userId=${userBindingDevice.userId}" title="修改"><i class="icon-pencil"></i></a>
						<a class="btnList" href="${ctx}/userBindingDevice/userBindingDevice/delete?id=${userBindingDevice.id}" onclick="return confirmx('确认要删除该用户绑定设备数据吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
</body>
</html>