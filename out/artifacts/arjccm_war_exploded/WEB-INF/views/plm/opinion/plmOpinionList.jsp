<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>建议意见箱管理</title>
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
	<form:form id="searchForm" modelAttribute="plmOpinion"
		action="${ctx}/opinion/plmOpinion/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>标题名称：</label> <form:input path="name"
					htmlEscape="false" maxlength="128" class="input-medium" /></li>
			<li><label>意见类型：</label> <form:select path="type"
					class="input-medium">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('plm_opinion_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li class="btns"><a id="btnSubmit" class="btn btn-primary" href="javascript:;"  onclick="return page();"><i class="icon-search"></i> 查询</a></li>
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
					<th>标题名称</th>
					<th>主题名称</th>
					<th>意见类型</th>
					<th>提交时间</th>
					<th>备注</th>
					
						<th>操作</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="plmOpinion">
					<tr>
						<td><a
							href="${ctx}/opinion/plmOpinion/form?id=${plmOpinion.id}">
								${plmOpinion.name} </a></td>
						<td>${plmOpinion.themeName}</td>
						<td>${fns:getDictLabel(plmOpinion.type, 'plm_opinion_type', '')}
						</td>
						<td><fmt:formatDate value="${plmOpinion.createDate}"
								pattern="yyyy-MM-dd " /></td>
						<td>${plmOpinion.remarks}</td>
						<td>
							<c:if test="${ empty plmOpinion.procInsId}">
									<a class="btnList"
										href="${ctx}/opinion/plmOpinion/form?id=${plmOpinion.id}"
										title="修改"><i class="icon-pencil"></i></a>
									<a class="btnList"
										href="${ctx}/opinion/plmOpinion/delete?id=${plmOpinion.id}"
										onclick="return confirmx('确认要删除该建议意见吗？', this.href)"
										title="删除"><i class="icon-remove-sign"></i></a>
								</c:if>
						
						<c:if test="${not empty plmOpinion.procInsId}">
							<a
								href="${ctx}/opinion/plmOpinion/form?id=${plmOpinion.id}"
								title="显示详情"><i class="icon-file"></i></a>
						</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
	<!-- 缩略图 -->
	<div id="prodInfo_small">
		<div class="row">
			<c:forEach items="${page.list}" var="plmOpinion">
				<div class="span4 spandiv">
					<div class="thumbnail">
						<a href="${ctx}/opinion/plmOpinion/form?id=${plmOpinion.id}">
							<h4 title="${plmOpinion.name}">标题名称：${plmOpinion.name}</h4>
						</a>
						<div class="caption row-fluid">
							<div class=" spanimg" style="width: 30%">
								<img src="${plmOpinion.imgUrl}"
									onerror='this.src="${ctxStatic}/common/list/images/timg.jpg"'
									alt="通用的占位符缩略图">
							</div>
							<div class="spantext  " style="width: 63%; margin-left: 7%">
								<p title="${plmOpinion.themeName}">主题名称：${plmOpinion.themeName}</p>
								<p
									title="${fns:getDictLabel(plmOpinion.type, 'plm_opinion_type', '')}">意见类型：${fns:getDictLabel(plmOpinion.type, 'plm_opinion_type', '')}</p>
								<p
									title="<fmt:formatDate value="${plmOpinion.createDate}" pattern="yyyy-MM-dd "/>">
									提交时间：
									<fmt:formatDate value="${plmOpinion.createDate}"
										pattern="yyyy-MM-dd " />
								</p>
							</div>
						</div>
						<div class="footbtn" style="text-align: right;">
							
								<c:if test="${ empty plmOpinion.procInsId}">
									<a class="btnList"
										href="${ctx}/opinion/plmOpinion/form?id=${plmOpinion.id}"
										title="修改"><i class="icon-pencil"></i></a>
									<a class="btnList"
										href="${ctx}/opinion/plmOpinion/delete?id=${plmOpinion.id}"
										onclick="return confirmx('确认要删除该建议意见吗？', this.href)"
										title="删除"><i class="icon-remove-sign"></i></a>
								</c:if>
							
							<c:if test="${not empty plmOpinion.procInsId}">
								<a href="${ctx}/opinion/plmOpinion/form?id=${plmOpinion.id}"
									title="显示详情"><i class="icon-file"></i></a>
							</c:if>
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