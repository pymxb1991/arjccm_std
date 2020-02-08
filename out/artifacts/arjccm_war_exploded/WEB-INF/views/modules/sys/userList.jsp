<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>用户管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnSubmit").on("click" ,function(){
			$("#searchForm").submit();
		})
		$("#btnExport").click(
					function() {

						top.$.jBox.confirm("确认要导出用户数据吗？", "系统提示", function(v, h, f) {
							if (v == "ok") {
								$("#searchForm").attr("action", "${ctx}/sys/sysUser/export");
								$("#searchForm").submit();
								// 还原查询action
								$("#searchForm").attr("action","${ctx}/sys/user/list");
							}
						}, {
							buttonsFocus : 1
						});
						top.$('.jbox-body .jbox-icon').css('top', '55px');
					});
		$("#btnImport").click(function() {
			$.jBox($("#importBox").html(), {
				title : "导入数据",
				buttons : {
					"关闭" : true
				},
				bottomText : "导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"
			});
		});
	});
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/sys/user/list");
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
<%--<img  src="${ctxStatic}/images/shouyedaohang.png"; class="nav-home">--%>
<%--<span class="nav-position">当前位置 ：</span><span class="nav-menu"><%=session.getAttribute("activeMenuName")%>></span><span class="nav-menu2">用户管理</span>--%>
<div class="back-list">
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/sys/sysUser/import" method="post"
			enctype="multipart/form-data" class="form-search"
			style="padding-left: 20px; text-align: center;"
			onsubmit="loading('正在导入，请稍等...');">
			<br /> <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br /> <br />
			<input id="btnImportTemplate"
				   class="btn btn-primary"  type="button" value="模板下载 " onclick="location.href='${ctxStatic}/template/excel/userTemplate.xlsx'"/>
			<input id="btnImportSubmit"
				class="btn btn-primary" type="submit" value="   导    入   " />
<%--			<a href="${ctx}/sys/user/import/template">下载模板</a>--%>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/sys/user/list">用户列表</a></li>
		<%-- 	<li><a href="${ctx}/sys/userAuthstr/list?loginFlag=0">待审核用户列表</a></li> --%>
		<shiro:hasPermission name="sys:user:edit">
			<li><a style="width: 140px;text-align:center" href="${ctx}/sys/user/form">用户添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="user"
		action="${ctx}/sys/user/list" method="post"
		class="breadcrumb form-search clearfix" >
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}"
			callback="page();" />
		<ul class="ul-form  pull-left ">
			<li class="first-line"><label>归属机构：</label> <sys:treeselect id="company"
					name="company.id" value="${user.company.id}"
					labelName="company.name" labelValue="${user.company.name}"
					title="机构" url="/sys/office/treeData?type=1" cssClass="input-small"
					allowClear="true" /></li>
			<li class="first-line"><label>登录名：</label> <form:input path="loginName"
					htmlEscape="false" maxlength="50" class="input-medium" /></li>
			<li class="first-line"><label>归属部门：</label> <sys:treeselect id="office"
					name="office.id" value="${user.office.id}" labelName="office.name"
					labelValue="${user.office.name}" title="部门"
					url="/sys/office/treeData?type=2" cssClass="input-small"
					allowClear="true" notAllowSelectParent="" /></li>
			<li class="first-line"><label>姓&nbsp;&nbsp;&nbsp;名：</label> <form:input path="name"
					htmlEscape="false" maxlength="50" class="input-medium" /></li>

<%--			<li class="clearfix"></li>--%>
		</ul>

	<sys:message content="${message}" />
	<div  class="clearfix pull-right btn-box">

			<!-- <input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" onclick="return page();" /> -->

			<shiro:hasPermission
					name="sys:user:edit">
				<!-- <input id="btnExport" class="btn btn-primary" type="button"
				value="导出" />
				<input id="btnImport" class="btn btn-primary" type="button"
				value="导入" /> -->
				<a href="javascript:;" id="btnImport"  style="width: 49px;display:inline-block;float: right;" class="btn  btn-export ">
					<i ></i> <span style="font-size: 12px">导入</span>
				</a>
				<a href="javascript:;" id="btnExport" class="btn btn-export" style="width: 49px;display:inline-block;float: right;">
					<i></i> <span style="font-size: 12px">导出</span>
				</a>
			</shiro:hasPermission>
		<a href="javascript:;" id="btnSubmit" class="btn btn-primary" style="width: 49px;display:inline-block;float: right;">
			<i></i><span style="font-size: 12px">查询</span>  </a>
	</div>
	</form:form>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed table-gradient">
		<thead>
			<tr>
				<th>归属机构</th>
				<th>归属部门</th>
				<th class="sort-column login_name">登录名</th>
				<th class="sort-column name">姓名</th>
				<th>电话</th>
				<th>手机</th>
				<%--<th>角色</th> --%>
				<shiro:hasPermission name="sys:user:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="user">
				<tr>
					<td style="height: 50px">${user.company.name}</td>
					<td style="height: 50px">${user.office.name}</td>
					<td style="height: 50px"><a href="${ctx}/sys/user/form?id=${user.id}">${user.loginName}</a></td>
					<td style="height: 50px">${user.name}</td>
					<td style="height: 50px">${user.phone}</td>
					<td style="height: 50px">${user.mobile}</td>
					<%--
				<td>${user.roleNames}</td> --%>
					<shiro:hasPermission name="sys:user:edit">
						<td style="height: 50px"><a class="btnList"
							href="${ctx}/sys/user/form?id=${user.id}" title="修改"><i
								class="icon-pencil"></i></a> <a class="btnList"
							href="${ctx}/sys/user/delete?id=${user.id}"
							onclick="return confirmx('确认要删除该用户吗？', this.href)" title="删除"><i
								class="icon-trash"></i></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
</body>
</html>