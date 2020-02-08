<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height:100%;width:100%;">
<head>
<title>今日统计</title>
<%--<meta name="decorator" content="default"/>--%>
<script type="text/javascript">
	$(document).ready(function() {
		
	});
</script>
</head>
<body>
	<table id="contentTable" class="table table-condensed">
		<thead>
			<tr>
				<th style="width: 35%;">单位</th>
				<th>未处理</th>
				<th>已派发</th>
				<th>处理中</th>
				<th>已完成</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="bphAlarmInfo">
			<tr>
				<td>${bphAlarmInfo.oName}</td>
				<td style="text-align: center;">${bphAlarmInfo.value0}</td>
				<td style="text-align: center;">${bphAlarmInfo.value1}</td>
				<td style="text-align: center;">${bphAlarmInfo.value2}</td>
				<td style="text-align: center;">${bphAlarmInfo.value3}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>