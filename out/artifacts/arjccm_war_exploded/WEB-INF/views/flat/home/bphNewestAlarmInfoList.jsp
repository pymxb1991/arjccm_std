<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html style="height:100%;width:100%;">
<head>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body> 
	<table id="contentTable" class="table  table-condensed">
		<thead>
			<tr>
				<th style="width: 65%;">案情描述</th>
				<th>报警时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="bphAlarmInfo">
			<tr>
				<td><span style="width:270px; height:18px; display:block;overflow: hidden;text-overflow:ellipsis;word-break:keep-all;white-space:nowrap;" title="${bphAlarmInfo.content}">${bphAlarmInfo.content}</span></td>
				<td><fmt:formatDate value="${bphAlarmInfo.alarmTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>