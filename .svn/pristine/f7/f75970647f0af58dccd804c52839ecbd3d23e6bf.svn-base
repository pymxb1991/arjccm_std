<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>物资申请管理</title>
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
	<form:form id="searchForm" modelAttribute="plmEquApply"
		action="${ctx}/equapply/plmEquApply/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>日期：</label> <input name="applyDate" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${plmEquApply.applyDate}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</li>
			<li><label>申请原因：</label> <form:input path="applyBody"
					htmlEscape="false" class="input-medium" /></li>
			<div class="control-group" style="display: none">
				<label class="control-label">类型：</label>
				<div class="controls">
					<input type="text" name="type" value="1">
				</div>
			</div>
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
			class="table table-striped table-bordered table-condensed" style="table-layout: fixed;width:98%">
			<thead>
				<tr>
					<th>申请人</th>
					<th>日期</th>
					<th>申请原因</th>
					<th>备注</th>
					
						<th>操作</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="plmEquApply">
					<tr>
						<td ><a
							href="${ctx}/equapply/plmEquApply/form?id=${plmEquApply.id}">${plmEquApply.user.name} </a>
						</td>
						<td><fmt:formatDate value="${plmEquApply.applyDate}"
								pattern="yyyy-MM-dd" /></td>
						<td style="white-space: nowrap;text-overflow: ellipsis;overflow: hidden;">${plmEquApply.applyBody}</td>
						<td style="white-space: nowrap;text-overflow: ellipsis;overflow: hidden;">${plmEquApply.remarks}</td>
						<td>
								<c:if test="${ empty plmEquApply.procInsId}">
									<a class="btnList"
										href="${ctx}/equapply/plmEquApply/form?id=${plmEquApply.id}"
										title="修改"><i class="icon-pencil"></i></a>
									<a class="btnList"
										href="${ctx}/equapply/plmEquApply/delete?id=${plmEquApply.id}"
										onclick="return confirmx('确认要删除该物资申请吗？', this.href)"
										title="删除"><i class="icon-remove-sign"></i></a>
								</c:if>
							 
								<c:if test="${not empty plmEquApply.procInsId}">
									<a href="${ctx}/equapply/plmEquApply/form?id=${plmEquApply.id}"
										title="显示详情"><i class="icon-file"></i></a>
								</c:if>
							</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- 缩略图 -->
	<div id="prodInfo_small">
		<div class="row">
			<c:forEach items="${page.list}" var="plmEquApply">
				<div class="span4 spandiv">
					<div class="thumbnail">
						<a href="${ctx}/equapply/plmEquApply/form?id=${plmEquApply.id}">
							<h4 title="<%-- ${plmEquApply.user.name} --%>">申请人:<%-- ${plmEquApply.user.name} --%></h4>
						</a>
						<div class="caption row-fluid">
							
							<div class="spantext  " style="width: 84%; margin-left: 7%">
								<p title=""></p>
								<p title="${plmEquApply.applyBody}">申请原因:${plmEquApply.applyBody}</p>
								<p
									title="<fmt:formatDate value="${plmEquApply.applyDate}" pattern="yyyy-MM-dd"/>">
									日期:
									<fmt:formatDate value="${plmEquApply.applyDate}"
										pattern="yyyy-MM-dd" />
								</p>
							</div>
						</div>
						<div class="footbtn" style="text-align: right;">
							
								<c:if test="${ empty plmEquApply.procInsId}">
									<a href="${ctx}/equapply/plmEquApply/form?id=${plmEquApply.id}"
										title="修改"><i class="icon-pencil"></i> </a>
									<a class="btnList"
										href="${ctx}/equapply/plmEquApply/delete?id=${plmEquApply.id}"
										onclick="return confirmx('确认要删除该物资申请吗？', this.href)"
										title="删除"><i class="icon-remove-sign"></i></a>
								</c:if>
							
							
								<c:if test="${not empty plmEquApply.procInsId}">
									<a href="${ctx}/equapply/plmEquApply/form?id=${plmEquApply.id}"
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