<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>应急预案管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnMenu").hide();
	});
</script>
</head>
<body>
	<sys:message content="${message}" />
	<h4 style="padding: 10px;">预案
		事件分级:${fns:getDictLabel(ccmemergencyplan.eventScale, 'ccm_case_grad', '')}
		,事件类型: ${fns:getDictLabel(ccmemergencyplan.eventType, 'ccm_case_type', '')}</h4>
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>预案名称</th>
				<th>案（事）件分级</th>
				<th>案（事）件类型</th>
				<th>工作原则</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="ccmEmergencyPlan">
				<tr>
					<td><a href="${ctx}/event/ccmEmergencyPlan/solveForm?id=${ccmEmergencyPlan.id}">
							${ccmEmergencyPlan.name} </a></td>
					<td>${fns:getDictLabel(ccmEmergencyPlan.eventScale, 'ccm_case_grad', '')}
					</td>
					<td>${fns:getDictLabel(ccmEmergencyPlan.eventType, 'ccm_case_type', '')}
					</td>
					<td>${ccmEmergencyPlan.principle}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>