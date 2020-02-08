<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>通讯录管理</title>
<meta name="decorator" content="default" />
<!-- 列表缩略图切换 -->
<!--自适应  -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="${ctxStatic}/bootstrap/2.3.1/css_flat/bootstrap-responsive.css"
	rel="stylesheet">
<link rel="stylesheet" href="${ctxStatic}/common/list/list.css">
<script type="text/javascript" src="${ctxStatic}/common/list/list.js"></script>
<!-- /列表缩略图切换 -->
<style type="text/css">
.input-select {
	width: 117px;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {
		$('#btnSubmit').click(function(){
			$('#searchForm').submit();
		});
	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/addressbook/plmEmployeePersonalAddress/?type=1">个人通讯录列表</a></li>
		<shiro:hasPermission
			name="addressbook:plmEmployeePersonalAddress:edit">
			<li><a href="${ctx}/addressbook/plmEmployeePersonalAddress/form">个人通讯录添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="plmEmployee"
		action="${ctx}/addressbook/plmEmployeePersonalAddress/list?type=1" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>人员姓名：</label> <form:input path="name"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li><label>就职状态：</label> <form:select path="state"
						class="input-xlarge required">
						<form:option value=""/>
						<form:options items="${fns:getDictList('plm_state')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select></li>
			<li><label>职务：</label> <form:input path="duty"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li class="btns"><a id="btnSubmit" class="btn btn-primary"><i class="icon-search"></i>查询</a></li></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<!-- 列表缩略图切换按钮 -->
	<div id="switchbtn">
		<a class="thumbnailbtn"><i class="icon-th "></i></a>&nbsp; <a
			class="listbtn"> <i class="icon-th-list "></i></a>
	</div>
	<!--/列表缩略图切换按钮 -->
	<div id="prodInfo_List">
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>人员姓名</th>
					<th>所在部门</th>
					<th>就职状态</th>
					<th>职务</th>
					<th>联系电话</th>
					<th>备用联系电话</th>
					<th>备注</th>
					<shiro:hasPermission
						name="addressbook:plmEmployeePersonalAddress:edit">
						<th>操作</th>
					</shiro:hasPermission>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="plmEmployee">
					<tr>
						<td><a
							href="${ctx}/addressbook/plmEmployeePersonalAddress/form?id=${plmEmployee.id}">
								${plmEmployee.name} </a></td>
						<td>${plmEmployee.dePid.name }</td>
						<td>${fns:getDictLabel(plmEmployee.state,'plm_state','')}</td>
						<td>${plmEmployee.duty}</td>
						<td>${plmEmployee.phoneone}</td>
						<td>${plmEmployee.phonetwo}</td>
						<td>${plmEmployee.remarks}</td>
						<shiro:hasPermission
							name="addressbook:plmEmployeePersonalAddress:edit">
							<td><a class="btnList"
								href="${ctx}/addressbook/plmEmployeePersonalAddress/form?id=${plmEmployee.id}"
								title="修改"><i class="icon-pencil"></i></a> <a class="btnList"
								href="${ctx}/addressbook/plmEmployeePersonalAddress/delete?id=${plmEmployee.id}"
								onclick="return confirmx('确认要删除该通讯录吗？', this.href)" title="删除"><i
								class="icon-trash"></i></a></td>
						</shiro:hasPermission>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- 缩略图 -->
	<div id="prodInfo_small">
		<div class="row">
			<c:forEach items="${page.list}" var="plmEmployee">
				<div class="span4 spandiv">
					<div class="thumbnail">
						<a
							href="${ctx}/addressbook/plmEmployeePersonalAddress/form?id=${plmEmployee.id}">
							<h4 title="${plmEmployee.name} ">姓名：${plmEmployee.name}</h4>
						</a>
						<div class="caption row-fluid">
							<div class=" spanimg" style="width: 30%">
								<img src="${plmEmployee.imgul}"
									onerror='this.src="${ctxStatic}/common/list/images/timg.jpg"'
									alt="无图片显示">
							</div>
							<div class="spantext  " style="width: 63%; margin-left: 7%">
								<p title="${plmEmployee.duty}">职务：${plmEmployee.duty}</p>
								<p title="${plmEmployee.dePid.name }">所在部门：${plmEmployee.dePid.name }</p>
								<p title="${plmEmployee.phoneone}">联系电话：${plmEmployee.phoneone}</p>
							</div>
						</div>
						<div class="footbtn" style="text-align: right;">
							<a class="btnList"
								href="${ctx}/addressbook/plmEmployeePersonalAddress/form?id=${plmEmployee.id}"
								title="修改"><i class="icon-pencil"></i></a> <a class="btnList"
								href="${ctx}/addressbook/plmEmployeePersonalAddress/delete?id=${plmEmployee.id}"
								onclick="return confirmx('确认要删除该通讯录吗？', this.href)" title="删除"><i
								class="icon-trash"></i></a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<!-- /缩略图 -->
	<div class="pagination">${page}</div>
</body>
</html>