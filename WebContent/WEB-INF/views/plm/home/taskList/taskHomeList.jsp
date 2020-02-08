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
	<h4 style="margin-top: 5px">在线办事审核<span style="color: red;">【${handleCheckCount}】</span> <span style="float: right;">
	<a href="${ctx}/service/ccmServiceOnline/examinelist">更多>></a></span></h4>
	<table id="contentTable"  style="table-layout: fixed;">
		<c:forEach var="ccmServiceOnline" items="${handleCheckList}" end="${line-1}">
			<tr>
				<td ><a href="${ctx}/service/ccmServiceOnline/form?id=${ccmServiceOnline.id}&etype=2">
					【${fns:getDictLabel(ccmServiceOnline.type, 'ccm_service_online_type', '')}】
				</a>
				</a>
					<span title="您辖区实有人口${ccmServiceOnline.applicant}（${ccmServiceOnline.ident}）于<fmt:formatDate value="${ccmServiceOnline.createDate}" pattern="yyyy-MM-dd HH:mm"/>提交在线办事申请，请即时审核和反馈 ">
					您辖区实有人口<span style="color: red;">${ccmServiceOnline.applicant}</span>（${ccmServiceOnline.ident}）于<fmt:formatDate value="${ccmServiceOnline.createDate}" pattern="yyyy-MM-dd HH:mm"/>
					提交在线办事申请，请即时审核和反馈。 </span>
					</td>
				<%-- <td width="28%">发布者:${task.user.name}</td>
				<td width="32%">发布时间: <fmt:formatDate
						value="${task.updateDate}" type="both" pattern="yyyy年MM月dd日	 " /></td> --%>
			</tr>
		</c:forEach>
	</table>
	<h4>我的通告<span style="color: red;">【${annunciateCount}】</span> <span style="float: right;">
<a href="${ctx}/oa/oaNotify/self">更多>></a></span></h4>
	<table id="contentTable"  style="table-layout: fixed;">
		<c:forEach var="oaNotify" items="${annunciateList}" end="${line-1}">
			<tr>
				<td ><a href="${ctx}/oa/oaNotify/${requestScope.oaNotify.self?'view':'form'}?id=${oaNotify.id}">						
						【${fns:abbr(oaNotify.title,50)}】
				</a>
					<span title="<fmt:formatDate value="${oaNotify.updateDate}" pattern="yyyy年MM月dd日   HH时mm分"/>您有一条公告，请即时查阅 "><fmt:formatDate value="${oaNotify.updateDate}" pattern="yyyy年MM月dd日   HH时mm分"/>您有一条<span style="color: red;">${fns:getDictLabel(oaNotify.type, 'oa_notify_type', '')}</span>公告，请即时查阅。 </span>
					</td>
				<%-- <td width="28%">发布者:${task.user.name}</td>
				<td width="32%">发布时间: <fmt:formatDate
						value="${task.updateDate}" type="both" pattern="yyyy年MM月dd日	 " /></td> --%>
			</tr>
		</c:forEach>
	</table>
</div>