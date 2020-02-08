<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>入库物资详细信息列表</title>
<meta name="decorator" content="default" />
<link rel="stylesheet" href="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.css">
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
<script src="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#btnPrint").on("click",function(){
			window.open("${ctx}/storage/plmMinusandAddDetail/print","qr_print","width=750px,height=800px");
		})
	});
</script>
</head>
<body>
<div id="listForm">
	<ul class="nav nav-tabs">
		<li class="active"><a>入库物资详细信息列表</a></li>
	</ul>
	<br />
	<input class="btn btn-primary" type="button" id="btnPrint" value="打印所有二维码"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>物资名称</th>
				<th>物资编号</th>
				<th>所在仓库</th>
				<th>规格型号</th>
				<th>物资类别</th>
				<th>物资类别2</th>
				<th>二维码</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${callbackList}" var="plmEquipment" varStatus="status">
			<tr>
				<td style="display: none;"><input name="id" type="hidden" value="${plmEquipment.id}"/></td>
				<td>
					${plmEquipment.name}
				</td>
				<td>
					${plmEquipment.code}
				</td>
				<td>
					${plmEquipment.storage.name}
				</td>
				<td>
					${plmEquipment.spec}
				</td>
				<td>
					${fns:getDictLabel(plmEquipment.typeId, 'plm_equipment_type', '')}
				</td>
				<td>
					${fns:getDictLabel(plmEquipment.typeChild, 'plm_equipment_type_child', '')}
				</td>
				<td>
					<img src="data:image/jpeg;base64,${plmEquipment.qrCode}">
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>