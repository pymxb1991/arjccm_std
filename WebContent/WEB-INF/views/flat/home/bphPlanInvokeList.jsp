<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height:100%;width:100%;">
<head>
<title>预案调用</title>
<meta name="decorator" content="default"/>
<script type="text/javascript">
	$(document).ready(function() {
		
	});
</script>
</head>
<body>
	<table id="contentTable" class="table table-condensed">
		<thead>
			<tr>
				<th>预案</th>
				<th>次数</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${planList}" var="bphPlanInfo">
			<tr>
				<td>${bphPlanInfo.name}</td>
				<td>${bphPlanInfo.count}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>   
</body>
</html>