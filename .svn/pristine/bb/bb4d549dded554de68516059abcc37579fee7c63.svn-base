<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>入库信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function(){
				$('#searchForm').submit();
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<script type="text/javascript" src="${ctxStatic}/plm/storage/plmEquipmentType.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/storage/plmMinusandAddDetail/countEquipmentByType">资产模板列表</a></li>
		<shiro:hasPermission name="storage:plmMinusandAddDetail:edit"><li><a href="${ctx}/storage/plmMinusandAddDetail/form">物资信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="plmEquipment" action="${ctx}/storage/plmMinusandAddDetail/countEquipmentByType" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>物资名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>规格型号：</label>
				<form:input path="spec" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>物资类别：</label>
				<form:select path="typeId" class="input-medium" dictTyep="plm_equipment_type">
					<form:option value="" label="未选择"/>
					<form:options items="${fns:getDictList('plm_equipment_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>物资子类：</label>
				<form:select path="typeChild" class="input-medium">
					<form:option value="" label="未选择"/>
					<form:options items="${fns:getDictList('plm_equipment_type_child')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-search"></i> 查询</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>物资名称</th>
				<th>规格型号</th>
				<th>物资类别</th>
				<th>物资子类</th>
				<th>总数量</th>
				<shiro:hasPermission name="storage:plmIncomingEntry:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${equipmentList}" var="plmEquipment" varStatus="status">
			<tr>
				<td>
					${plmEquipment.name}
					<input type="hidden" id="name_${status.count}" value="${plmEquipment.name}">
				</td>
				<td>
					${plmEquipment.spec}
					<input type="hidden" id="spec_${status.count}" value="${plmEquipment.spec}">
				</td>
				<td>
					${fns:getDictLabel(plmEquipment.typeId, 'plm_equipment_type', '未知')}
					<input type="hidden" id="typeId_${status.count}" value="${plmEquipment.typeId}">
				</td>
				<td>
					${fns:getDictLabel(plmEquipment.typeChild, 'plm_equipment_type_child', '未知')}
					<input type="hidden" id="typeChild_${status.count}" value="${plmEquipment.typeChild}">
				</td>
				<td>
					<c:choose>
						<c:when test="${plmEquipment.typeId == '0'}">${plmEquipment.erialNumber}</c:when>
						<c:otherwise>
							${plmEquipment.counts}
						</c:otherwise>
					</c:choose>
				</td>
				<shiro:hasPermission name="storage:plmIncomingEntry:edit"><td>
					<c:choose>
						<c:when test="${plmEquipment.typeId == '0'}">
							<a href="${ctx}/storage/plmMinusandAddDetail/getEquipmentDemo?id=${plmEquipment.id}"><i title="使用模板" class="icon-file"></i></a>
						</c:when>
						<c:otherwise>
							<a href="${ctx}/storage/plmMinusandAddDetail/getEquipmentDemo?name=${plmEquipment.name}&spec=${plmEquipment.spec}&typeId=${plmEquipment.typeId}&typeChild=${plmEquipment.typeChild}"><i title="使用模板" class="icon-file"></i></a>
						</c:otherwise>
					</c:choose>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>