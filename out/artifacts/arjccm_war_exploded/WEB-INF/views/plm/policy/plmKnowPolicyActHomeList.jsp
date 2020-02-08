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
}
</style>
<script type="text/javascript">
$("#plmKnowPolicyAct").on("click",function(){
	$.ajax({
		url:'${ctx}/policy/plmKnowPolicyAct/view',
		type:'post',
		data:{"height":"${porheigh}" ,"width":"${porwidth}","content":"${porcontent}","divId":"${divId}"},
		dataType:'html',
		error: function(){ $("#${divId}").html("链接不正确")},
		success:function(data){
			$("#${divId}").html(data);
			
		}
	 });
});	
$("#plmKnowPolicy").on("click",function(){
	$.ajax({
		url:'${ctx}/policy/plmKnowPolicy/view',
		type:'post',
		data:{"height":"${porheigh}" ,"width":"${porwidth}","content":"${porcontent}","divId":"${divId}"},
		dataType:'html',
		error: function(){ $("#${divId}").html("链接不正确")},
		success:function(data){
			$("#knowPolicy").html(data);
			$("#plmKnowPolicy").attr("class","active");
			$("#plmKnowPolicyAct").attr("class","unactive");
		}
	 });
});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li id="plmKnowPolicyAct" class="active"><a>规章制度</a></li>
		<li id="plmKnowPolicy" class="unactive"><a >法律法规</a></li>
	</ul>
	<sys:message content="${message}" />
	<div id ="knowPolicy">
	<table id="contentTable"  style="table-layout: fixed;">
			<c:forEach  var="plmKnowPolicy" items="${list}" end="${line-1}">
				<tr>
					<td width="40%">标题：<a
						href="${ctx}/policy/plmKnowPolicyAct/one?id=${plmKnowPolicy.id}">
							${plmKnowPolicy.name} </a></td>
					<td width="28%">发文字号：${plmKnowPolicy.lssNo}</td>
					<td width="32%">发布时间：<fmt:formatDate value="${plmKnowPolicy.updateDate}"
							pattern="yyyy年MM月dd日" /></td>
				</tr>
			</c:forEach>
	</table>
</div>