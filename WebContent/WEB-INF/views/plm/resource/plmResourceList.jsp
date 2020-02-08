<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>资源共享管理</title>
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
		<li class="active"><a href="${ctx}/resource/plmResource/?type=01">资源共享列表</a></li>
		<shiro:hasPermission name="resource:plmResource:edit">
			<li><a href="${ctx}/resource/plmResource/form">资源共享添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="plmResource"
		action="${ctx}/resource/plmResource/?type=01" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>标题：</label> <form:input path="name"
					htmlEscape="false" maxlength="128" class="input-medium" /></li>
			<shiro:hasPermission name="resource:plmResource:view">
			<li class="btns">
			<a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-search"></i> 查询</a>
				</li>
			<li class="clearfix"></li>
			</shiro:hasPermission>
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
					<th>创建人</th>
					<th>创建时间</th>
					<th>备注信息</th>
					<shiro:hasPermission name="resource:plmResource:edit">
						<th>操作</th>
					</shiro:hasPermission>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="plmResource">
					<tr>
						<td><a
							href="${ctx}/resource/plmResource/form?id=${plmResource.id}">
								${plmResource.name} </a></td>
						<td>${plmResource.user.name}</td>
						<td><fmt:formatDate value="${plmResource.createDate}"
								pattern="yyyy-MM-dd " /></td>
						<td>${plmResource.remarks}</td>
						<td>
						<shiro:hasPermission name="resource:plmResource:edit">
								<a class="btnList" href="${ctx}/resource/plmResource/form?id=${plmResource.id}"><i title="修改" class="icon-pencil"></i></a>
								<a class="btnList"
									href="${ctx}/resource/plmResource/delete?id=${plmResource.id}"
									onclick="return confirmx('确认要删除该资源共享吗？', this.href)"><i title="删除" class="icon-trash"></i></a>
							</shiro:hasPermission></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- 缩略图 -->
	<div id="prodInfo_small">
		<div class="row">
			<c:forEach items="${page.list}" var="plmResource">
				<div class="span4 spandiv">
					<div class="thumbnail">
						<a href="${ctx}/resource/plmResource/form?id=${plmResource.id}">
							<h4 title="${plmResource.name}">标题：${plmResource.name}</h4>
						</a>
						<div class="caption row-fluid">
							<div class=" spanimg" style="width: 30%">
								<img src="${plmResource.img}"
									onerror='this.src="${ctxStatic}/common/list/images/timg.jpg"'
									alt="通用的占位符缩略图">
							</div>
							<div class="spantext  " style="width: 63%; margin-left: 7%">
								<p title="${plmResource.user.name }">提交人：${plmResource.user.name }</p>
								<p
									title="<fmt:formatDate value="${plmResource.updateDate}"
								pattern="yyyy-MM-dd " />">
									提交时间：
									<fmt:formatDate value="${plmResource.updateDate}"
										pattern="yyyy-MM-dd " />
								</p>
								<p title="${plmResource.remarks}">备注：${plmResource.remarks}</p>
							</div>
						</div>
						<div class="footbtn" style="text-align: right;">
							<shiro:hasPermission name="resource:plmResource:edit">
								<a class="btnList"
									href="${ctx}/resource/plmResource/form?id=${plmResource.id}"
									title="修改"><i class="icon-pencil"></i></a>
								<a class="btnList"
									href="${ctx}/resource/plmResource/delete?id=${plmResource.id}"
									onclick="return confirmx('确认要删除该资源共享吗？', this.href)" title="删除"><i
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