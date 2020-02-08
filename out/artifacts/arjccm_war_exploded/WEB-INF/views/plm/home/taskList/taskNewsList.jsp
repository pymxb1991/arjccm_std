<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


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
	padding-bottom: 10px;
	/* color: black; */
}

h4{color: #08c!important; 
   font-weight: 800;
   font-size:115%;
   margin-bottom: 12px;
}
</style>
<script type="text/javascript">

</script>
	
	<div id ="cmsContainer">
	<h4 style="margin-top: 5px">我的消息<span style="color: red;">【${handleCheckCount}】</span> <span style="float: right;">
	<a href="${ctx}/news/message/list">更多>></a></span></h4>
	<table id="contentTable"  style="table-layout: fixed;">
		<c:forEach var="message" items="${list}" end="${line-1}">
			<tr>
				<td ><a href="${ctx}/news/message/form?id=${message.id}">
					【${message.createByName}】
				</a>
					<span title="${message.createByName}于<fmt:formatDate value="${message.updateDate}" pattern="yyyy-MM-dd HH:mm"/>给您发送了一条消息，${message.content}！">
					于<fmt:formatDate value="${message.updateDate}" pattern="yyyy-MM-dd HH:mm"/>
					给您发送了一条消息，${message.content}！</span>
					</td>
			</tr>
		</c:forEach>
	</table>
</div>