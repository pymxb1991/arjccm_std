<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>维保单位管理</title>
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
<style type="text/css">
img {
	width: 130px;
	max-width: 150px;
}

p {
	margin: 0 27px 10px;
}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/car/plmCarRepair/">维保单位列表</a></li>
		<shiro:hasPermission name="car:plmCarRepair:edit">
			<li><a href="${ctx}/car/plmCarRepair/form">维保单位添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="plmCarRepair"
		action="${ctx}/car/plmCarRepair/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>单位名称：</label> <form:input path="name"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li><label>单位编号：</label> <form:input path="num"
					htmlEscape="false" maxlength="20" class="input-medium" /></li>
			<li><label>单位地址：</label> <form:input path="addr"
					htmlEscape="false" maxlength="100" class="input-medium" /></li>
			<li class="btns"><a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-search"></i> 查询</a></li>
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
					<th>单位名称</th>
					<th>单位编号</th>
					<th>单位地址</th>
					<th>单位电话</th>
					<th>负责人</th>
					<th>负责人手机</th>
					<th>更新时间</th>
					<shiro:hasPermission name="car:plmCarRepair:edit">
						<th>操作</th>
					</shiro:hasPermission>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="plmCarRepair">
					<tr>
						<td><a
							href="${ctx}/car/plmCarRepair/form?id=${plmCarRepair.id}">
								${plmCarRepair.name} </a></td>
						<td>${plmCarRepair.num}</td>
						<td>${plmCarRepair.addr}</td>
						<td>${plmCarRepair.tel}</td>
						<td>${plmCarRepair.leader}</td>
						<td>${plmCarRepair.phone}</td>
						<td><fmt:formatDate value="${plmCarRepair.updateDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><shiro:hasPermission name="car:plmCarRepair:edit">
								<a class="btnList"
									href="${ctx}/car/plmCarRepair/form?id=${plmCarRepair.id}"
									title="修改"><i class="icon-pencil"></i></a>
								<a class="btnList"
									href="${ctx}/car/plmCarRepair/delete?id=${plmCarRepair.id}"
									onclick="return confirmx('确认要删除该维保单位吗？', this.href)" title="删除"><i
									class="icon-trash"></i></a>
							</shiro:hasPermission></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- 缩略图 -->
	<div id="prodInfo_small">
		<div class="row">
			<c:forEach items="${page.list}" var="plmCarRepair">
				<div class="span4 spandiv">
					<div class="thumbnail">
						<a href="${ctx}/car/plmCarRepair/form?id=${plmCarRepair.id}">
							<h4 title="${plmCarRepair.name}">单位名称：${plmCarRepair.name}</h4>
						</a>
						<div class="caption row-fluid">
							<div class=" spanimg" style="width: 30%">
								<img src="${plmCarRepair	.photo}"
									onerror='this.src="${ctxStatic}/common/list/images/timg.jpg"'
									alt="通用的占位符缩略图" />
							</div>
							<div class="spantext  " style="width: 63%; margin-left: 7%">
								<p title="${plmCarRepair.leader}">负责人:${plmCarRepair.leader}</p>
								<p title="${plmCarRepair.tel}">单位电话:${plmCarRepair.tel}</p>
								<p title="${plmCarRepair.addr}">地址:${plmCarRepair.addr}</p>
							</div>
						</div>
						<div class="footbtn" style="text-align: right;">
							<shiro:hasPermission name="car:plmCarRepair:edit">
								<a class="btnList"
									href="${ctx}/car/plmCarRepair/form?id=${plmCarRepair.id}"
									title="修改"><i class="icon-pencil"></i></a>
								<a class="btnList"
									href="${ctx}/car/plmCarRepair/delete?id=${plmCarRepair.id}"
									onclick="return confirmx('确认要删除该维保单位吗？', this.href)" title="删除"><i
									class="icon-trash"></i></a>
							</shiro:hasPermission>
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