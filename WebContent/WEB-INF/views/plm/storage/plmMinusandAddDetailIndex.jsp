<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>出库单管理</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/common/zztable.css" type="text/css"
	rel="stylesheet">
<!-- 表格试表单css -->
<link href="${ctxStatic}/common/zzformtable.css" type="text/css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.css">
<script src="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/plm/storage/ajaxMessageAlert.js"></script>
<script type="text/javascript" src="${ctxStatic}/plm/storage/storageValidate.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/storage/plmMinusandAddDetail/index">物品使用详情列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="detailList" action="${ctx}/storage/plmMinusandAddDetail/index" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
	<br />
			<table  id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>物资编号</th>
						<th>物资名称</th>
						<th>规格型号</th>
						<th>物资类别</th>
						<th>物资子类</th>
						<th>生产日期</th>
						<th>物资数量</th>
						<th>计量单位</th>
						<th>归还状态</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${detailList}" var="plmMinusandAddDetail">
						<tr>
							<td>${plmMinusandAddDetail.equipmentCode}</td>
							<td>${plmMinusandAddDetail.name}</td>
							<td>${plmMinusandAddDetail.spec}</td>
							<td>${fns:getDictLabel(plmMinusandAddDetail.typeId, 'plm_equipment_type', '未知')}</td>
							<td>${fns:getDictLabel(plmMinusandAddDetail.typeChild, 'plm_equipment_type_child', '未知')}</td>
							<td><fmt:formatDate value="${plmMinusandAddDetail.productionDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${plmMinusandAddDetail.erialNumber}</td>
							<td>${plmMinusandAddDetail.unit}</td>
							<td>${fns:getDictLabel(plmMinusandAddDetail.giveBack, 'plm_giveBack', '')}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</form:form>
			<div class="pagination">${page}</div>
</body>
</html>