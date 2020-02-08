<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>装备物资管理</title>
<meta name="decorator" content="default" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
<%--<img  src="${ctxStatic}/images/shouyedaohang.png"; class="nav-home">--%>
<%--<span class="nav-position">当前位置 ：</span><span class="nav-menu"><%=session.getAttribute("activeMenuName")%>></span><span class="nav-menu2">物资统计</span>--%>
<div class="back-list">
	<ul class="nav nav-tabs">
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/storage/plmEquipment/countByEquType">库存统计列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="plmEquipment"
		action="${ctx}/storage/plmEquipment/countByEquType" method="post"
		class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form pull-left">
			<li class="first-line"><label>物资类别：</label> <form:select path="typeId"
					class="input-medium" dictTyep="plm_equipment_type">
					<form:option value="" label="未选择" />
					<form:options items="${fns:getDictList('plm_equipment_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li class="first-line"><label>物资子类：</label> <form:select path="typeChild"
					class="input-medium">
					<form:option value="" label="未选择" />
					<form:options
						items="${fns:getDictList('plm_equipment_type_child')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
<%--			<li class="clearfix"></li>--%>
		</ul>
	<div class="clearfix pull-right btn-box">
		<a href="javascript:;" id="btnSubmit" style="width: 49px;
   /*margin-top: 25px;*/display:inline-block;float: right;" class="btn btn-primary">
				<%--<i class="icon-search"></i> --%><span style="font-size: 12px">查询</span> </a>
	</div>
	</form:form>
	<sys:message content="${message}" />
	<div style="color: red;text-align: right;margin-right: 10px">默认显示非耗材(如需查看耗材请选择类别为耗材进行查询)</div>
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed table-gradient">
			<thead>
				<tr>
					<th>物资型号</th>
					<th>物资类别</th>
					<th>物资子类</th>
					<th>总数量</th>
					<th>占用数量</th>
					<th>空闲数量</th>
					<th>维修保养数量</th>
					<th>使用中数量</th>
					<th>报废数量</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="plmEquipment">
					<tr>
						<td style="height: 50px">${fns:getDictLabel(plmEquipment.typeChild, 'plm_equipment_type_child', '')}(${plmEquipment.spec})</td>
						<td style="height: 50px">${fns:getDictLabel(plmEquipment.typeId, 'plm_equipment_type', '')}</td>
						<td style="height: 50px">${fns:getDictLabel(plmEquipment.typeChild, 'plm_equipment_type_child', '')}</td>
						<c:choose>
							<c:when test="${plmEquipment.typeId == '0'}">
								<td style="height: 50px">${plmEquipment.erialNumber}</td>
								<td>无</td>
								<td style="height: 50px">${plmEquipment.erialNumber - plmEquipment.useNumber}</td>
								<td>无</td>
								<td style="height: 50px">${plmEquipment.useNumber}</td>
								<td>无</td>
							</c:when>
							<c:otherwise>
								<td style="height: 50px">${plmEquipment.counts}</td>
								<td style="height: 50px">${plmEquipment.occupyCounts}</td>
								<td style="height: 50px">${plmEquipment.unoccupiedCounts}</td>
								<td style="height: 50px">${plmEquipment.repairCounts}</td>
								<td style="height: 50px">${plmEquipment.usingCounts}</td>
								<td style="height: 50px">${plmEquipment.scrapCounts}</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
</body>
</html>