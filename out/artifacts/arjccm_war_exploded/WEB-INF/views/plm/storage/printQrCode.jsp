<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title></title>
</head>
<body>
<div align="center" style="padding-top: 10px;">
	<table>
		<tbody>
		<c:forEach items="${callbackList}" var="qrCode" varStatus="status">
			<c:if test="${status.count%3 == 1}"><tr></c:if>
				<td style="padding: 6px;">
					<table>
						<tr>
							<td>名称:${qrCode.name}</td>
							<td rowspan="3"><img src="data:image/jpeg;base64,${qrCode.qrCode}"></td>
						</tr>
						<tr>
							<td>编号:${qrCode.code}</td>
						</tr>
						<tr>
							<td>型号:${qrCode.spec }</td>
						</tr>
					</table>
				</td>
			<c:if test="${status.count%3 == 0}"></tr></c:if>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<script type="text/javascript">
		$(function(){
			$("#btnMenu").css({"display":"none"});
			window.print();
		})
	</script>
</body>
</html>