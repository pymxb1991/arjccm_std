<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<style type="text/css">
table {
	border: 0px;
	width: 98% border:0px;
	margin-top: 0px;
}

td {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
th{
	/* color:010101; */
	font-size:16px;
	font-family: Helvetica, Georgia, Arial, sans-serif, 宋体;
    text-align:left;
        font-weight: inherit;
}
</style>
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
	<sys:message content="${message}"/>
	<table id="contentTable"  style="table-layout: fixed;">
			<tr>
				<th>姓名</th>
				<th>所在部门</th>
				<th>联系电话</th>
			</tr>
		<c:forEach items="${page.list}" end="${line-1}" var="plmEmployee">
			<tr>
				<td><a href="${ctx}/addressbook/plmEmployee/one?id=${plmEmployee.id}">
					${plmEmployee.name}
				</a></td>
				<td>
					${plmEmployee.dePid.name }
				</td>
				<td>
					${plmEmployee.phoneone}
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>