<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<title>政府项目采购申请管理</title>
<meta name="decorator" content="default" />

<!-- 列表缩略图切换 -->
<!--自适应  -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${ctxStatic}/bootstrap/2.3.1/css_flat/bootstrap-responsive.css" rel="stylesheet">
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
</style>
</head>
<body>
	<form:form id="searchForm" modelAttribute="plmPurchaseApply"
		action="${ctx}/purchase/plmPurchaseApply/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<%-- <li><label>采购部门：</label> <sys:treeselect id="office"
					name="office" value="${plmPurchaseApply.office.id}"
					labelName="office.name"
					labelValue="${plmPurchaseApply.office.name}" title="部门"
					url="/sys/office/treeData?type=2" cssClass="input-small"
					allowClear="true" notAllowSelectParent="true" /></li> --%>
			<li><label>项目编号</label> <form:input path="applyId"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>

			<%-- <li><label>申请日期：</label>
				<input name="beginApplyDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmPurchaseApply.beginApplyDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endApplyDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmPurchaseApply.endApplyDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li> --%>
			<%-- <li><label>经费项目名称：</label>
				<form:input path="fundingName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li> --%>
			<li><label>经费类型：</label> <form:select path="fundingType"
					class="input-medium">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('purchase_funding_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li><label>采购项目名称：</label> <form:input path="name"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
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
					<th>项目编号</th>
					<th>采购项目名称</th>

					<th>申请日期</th>
					<th>经费项目名称</th>
					<th>经费类型</th>
					<th>采购部门</th>
					<th>状态</th>
					<th>更新时间</th>
					<th>备注信息</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="plmPurchaseApply">
					<tr>
						<td><a
							href="${ctx}/purchase/plmPurchaseApply/form?id=${plmPurchaseApply.id}">
								${plmPurchaseApply.applyId} </a></td>
						<td>${plmPurchaseApply.name}</td>
						<td><fmt:formatDate value="${plmPurchaseApply.applyDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>${plmPurchaseApply.fundingName}</td>
						<td>${fns:getDictLabel(plmPurchaseApply.fundingType, 'purchase_funding_type', '')}
						</td>
						<td>${plmPurchaseApply.office.name}</td>
						<td>${fns:getDictLabel(plmPurchaseApply.plmAct.status, 'plm_act_status', '')}</td>
						<td><fmt:formatDate value="${plmPurchaseApply.updateDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>${plmPurchaseApply.remarks}</td>
						<td><c:if test="${not empty plmPurchaseApply.procInsId}"
								var="e">
								
									<a class="btnList"
										href="${ctx}/purchase/plmPurchaseApply/form?id=${plmPurchaseApply.id}"
										title="显示详情"><i class="icon-file"></i></a>
							</c:if> <c:if test="${!e}">
									<a class="btnList"
										href="${ctx}/purchase/plmPurchaseApply/form?id=${plmPurchaseApply.id}"
										title="修改"><i class="icon-pencil"></i></a>
									<a class="btnList"
										href="${ctx}/purchase/plmPurchaseApply/delete?id=${plmPurchaseApply.id}"
										onclick="return confirmx('确认要删除该申请吗？', this.href)" title="删除"><i
										class="icon-trash"></i></a>
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- 缩略图 -->
	<div id="prodInfo_small">
		<div class="row">
			<c:forEach items="${page.list}" var="plmPurchaseApply">
				<div class="span4 spandiv">
					<div class="thumbnail">
						<a
							href="${ctx}/purchase/plmPurchaseApply/form?id=${plmPurchaseApply.id}">
							<h4 title="${plmPurchaseApply.name}">采购项目名称：${plmPurchaseApply.name}</h4>
						</a>
						<div class="caption row-fluid">
						   <%--  <div class=" spanimg" style="width: 30%"> 
							    <img src="${ctxStatic}/common/index/images/index-bg.gif" onerror='this.src="${ctxStatic}/common/list/images/timg.jpg"' alt="通用的占位符缩略图"/> 
							</div>   --%>
							<div class="spantext  " style="width: 95%; margin-left:5%">
							    <p title="${plmPurchaseApply.applyId}">项目编号：${plmPurchaseApply.applyId}</p>
								<p title="${plmPurchaseApply.office.name}">采购部门：${plmPurchaseApply.office.name}</p>
								<p
									title="<fmt:formatDate value="${plmPurchaseApply.applyDate}" pattern='yyyy-MM-dd'/>">
									申请日期：
									<fmt:formatDate value="${plmPurchaseApply.applyDate}"
										pattern="yyyy-MM-dd " />
								</p>
							</div>
						</div>
						<div class="footbtn" style="text-align: right;">
							<c:if test="${not empty plmPurchaseApply.procInsId}" var="e">
									<a
										href="${ctx}/purchase/plmPurchaseApply/form?id=${plmPurchaseApply.id}"
										title="显示详情"><i class="icon-file"></i></a>
							</c:if>
							<c:if test="${!e}">
									<a
										href="${ctx}/purchase/plmPurchaseApply/form?id=${plmPurchaseApply.id}"
										title="提交申请"><i class="icon-pencil"></i></a>
									<a class="btnList"
										href="${ctx}/purchase/plmPurchaseApply/delete?id=${plmPurchaseApply.id}"
										onclick="return confirmx('确认要删除该申请吗？', this.href)" title="删除"><i
										class="icon-trash"></i></a>
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