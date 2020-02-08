<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height:100%;width:100%;">
<head>
<title>通知公告</title>
<%--<meta name="decorator" content="default"/>--%>
<script type="text/javascript">
	$(document).ready(function() {
		
	});
</script>
</head>
<body>
	<table id="contentTable" class="table table-condensed">
		<div style="height: 18px;padding-top: 9px;padding-bottom: 9px;">
			<c:forEach items="${list}" var="oaNotifys">
				<b>今日通知公告</b><span style="color: #1bad83;font-size: 25px;">${oaNotifys.value0}</span><b>条</b>
				<span style="padding-left:10px;"><b>待查看</b><span style="color: #d70054;font-size: 25px;">${oaNotifys.value1}</span><b>条</b></span>
			</c:forEach>
		</div>
		<thead>
			<tr>
				<th style="width: 17%;text-align: center;">处理状态</th>
				<th style="width: 20%;text-align: center;">发件人</th>
				<th style="width: 29%;text-align: center;">内容</th>
				<th style="text-align: center;">发送时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${oaNotifyList}" var="oaNotify">
			<tr>
				<c:choose>
					<c:when test="${oaNotify.readFlag == 0}">
						<td><span style="color: #d70054;">${fns:getDictLabel(oaNotify.readFlag,'handle_status_type','')}</span></td>
					</c:when>
					<c:otherwise>
						<td><span style="color: #1bad83;">${fns:getDictLabel(oaNotify.readFlag,'handle_status_type','')}</span></td>
					</c:otherwise>
				</c:choose>
				<td><span style="width:80px; height:18px; display:block;overflow: hidden;text-overflow:ellipsis;word-break:keep-all;white-space:nowrap;" title="${oaNotify.sender}">${oaNotify.sender}</span></td>
				<td><span style="width:115px; height:18px; display:block;overflow: hidden;text-overflow:ellipsis;word-break:keep-all;white-space:nowrap;" title="${oaNotify.content}">${oaNotify.content}</span></td>
				<td><fmt:formatDate value="${oaNotify.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>