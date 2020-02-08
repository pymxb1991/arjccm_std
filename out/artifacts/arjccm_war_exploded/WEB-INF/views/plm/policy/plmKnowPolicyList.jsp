<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>政策法规管理</title>
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
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/policy/plmKnowPolicy/?types=1">规章制度管理列表</a></li>
		<shiro:hasPermission name="policy:plmKnowPolicy:edit">
			<li><a href="${ctx}/policy/plmKnowPolicy/form">规章制度添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="plmKnowPolicy"
		action="${ctx}/policy/plmKnowPolicy/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>标题：</label> <form:input path="name"
					htmlEscape="false" maxlength="512" class="input-medium" /></li>
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
					<th>标题</th>
					<th>发文字号</th>
					<th>发布部门</th>
					<th>发布时间</th>
					<th>备注信息</th>
					<shiro:hasPermission name="policy:plmKnowPolicy:edit">
						<th>操作</th>
					</shiro:hasPermission>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="plmKnowPolicy">
					<tr>
						<td><a
							href="${ctx}/policy/plmKnowPolicy/one?id=${plmKnowPolicy.id}">
								${plmKnowPolicy.name} </a></td>
						<td>${plmKnowPolicy.lssNo}</td>
						<td>${plmKnowPolicy.relDept}</td>
						<td><fmt:formatDate value="${plmKnowPolicy.relDate}"
								pattern="yyyy-MM-dd" /></td>
						<td>${plmKnowPolicy.remarks}</td>
						<shiro:hasPermission name="policy:plmKnowPolicy:edit">
							<td><a class="btnList"
									href="${ctx}/policy/plmKnowPolicy/form?id=${plmKnowPolicy.id}" title="修改"><i class="icon-pencil"></i></a>
									<a class="btnList"
									href="${ctx}/policy/plmKnowPolicy/delete?id=${plmKnowPolicy.id}"
									onclick="return confirmx('确认要删除该政策法规吗？', this.href)" title="删除"><i
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
			<c:forEach items="${page.list}" var="plmKnowPolicy">
				<div class="span4 spandiv">
					<div class="thumbnail">
						<a
							href="${ctx}/policy/plmKnowPolicyAct/one?id=${plmKnowPolicy.id}"><h4
								title="${plmKnowPolicy.name}">标题:${plmKnowPolicy.name}</h4> </a>
						<div class="caption row-fluid">
							<%-- <div class=" spanimg" style="width: 30%">
								<img src="${ctxStatic}/common/index/images/index-bg.gif"
									onerror='this.src="${ctxStatic}/common/list/images/timg.jpg"'
									alt="通用的占位符缩略图" />
							</div> --%>
							<div class="spantext  " style="width: 88%; margin-left: 6%">
								<p title="${plmKnowPolicy.relDept}">发布部门:${plmKnowPolicy.relDept}</p>
								<p title="${plmKnowPolicy.lssNo}">发文字号:${plmKnowPolicy.lssNo}</p>
								<p
									title="<fmt:formatDate value="${plmKnowPolicy.updateDate}"
							pattern="yyyy-MM-dd" />">
									发布时间：
									<fmt:formatDate value="${plmKnowPolicy.updateDate}"
										pattern="yyyy-MM-dd" />
								</p>
							</div>
						</div>
						<div class="footbtn" style="text-align: right;">
							<shiro:hasPermission name="policy:plmKnowPolicy:edit">
								<a class="btnList"
									href="${ctx}/policy/plmKnowPolicy/form?id=${plmKnowPolicy.id}" title="修改"><i class="icon-pencil"></i></a>
									<a class="btnList"
									href="${ctx}/policy/plmKnowPolicy/delete?id=${plmKnowPolicy.id}"
									onclick="return confirmx('确认要删除该政策法规吗？', this.href)" title="删除"><i
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