<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>参会人员管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<table id="contentTable" class="table table-striped table-bordered table-condensed" style="width 100%">
		<thead>
			<tr>
				<th>姓名</th>
				<th>编号</th>
				<th>部门</th>
				<th>联系方式</th>
			</tr>
		</thead>
			<tbody>
				<c:forEach items="${page.list}" var="roomAttendee">
					<tr>
						<td>${roomAttendee.user.name}</td>
						<td>${roomAttendee.user.no}</td>
						<td>${roomAttendee.user.office.name}</td>
						<td>${roomAttendee.user.phone}</td>
					</tr>
				</c:forEach>
			</tbody>
	</table>		
		
		
		
	<div class="pagination">${page}</div>
</body>
</html>