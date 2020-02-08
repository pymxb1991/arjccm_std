<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!-- <html>
<head> -->
 	<script type="text/javascript">
		$("#homeAll").on("click",function(){
			$.ajax({
				url:'${ctx}/act/plmAct/actforHome',
				type:'post',
				data:{"height":"${porheigh}" ,"width":"${porwidth}","content":"${porcontent}","divId":"${divId}"},
				dataType:'html',
				error: function(){ $("#${divId}").html("链接不正确")},
				success:function(data){
					$("#${divId}").html(data);
					
				}
			 });
		});	
		$("#homeTodo").on("click",function(){
			$.ajax({
				url:'${ctx}/act/taskSelf/actforHomeTodo/',
				type:'post',
				data:{"height":"${porheigh}" ,"width":"${porwidth}","content":"${porcontent}","divId":"${divId}"},
				dataType:'html',
				error: function(){ $("#${divId}").html("链接不正确")},
				success:function(data){
					$("#actContainer").html(data);
					$("#homeTodo").attr("class","active");
					$("#homeAll").attr("class","unactive");
					$("#homeFinish").attr("class","unactive");
					$("#homeSup").attr("class","unactive");
				}
			 });
		});
		$("#homeFinish").on("click",function(){
			$.ajax({
				url:'${ctx}/act/taskSelf/actforHomeFinish',
				type:'post',
				data:{"height":"${porheigh}" ,"width":"${porwidth}","content":"${porcontent}","divId":"${divId}"},
				dataType:'html',
				error: function(){ $("#${divId}").html("链接不正确")},
				success:function(data){
					$("#actContainer").html(data);
					$("#homeTodo").attr("class","unactive");
					$("#homeAll").attr("class","unactive");
					$("#homeFinish").attr("class","active");
					$("#homeSup").attr("class","unactive");
				}
			 });
		});
		$("#homeSup").on("click",function(){
			$.ajax({
				url:'${ctx}/act/plmAct/actforHomeSup',
				type:'post',
				data:{"height":"${porheigh}" ,"width":"${porwidth}","content":"${porcontent}","divId":"${divId}"},
				dataType:'html',
				error: function(){ $("#${divId}").html("链接不正确")},
				success:function(data){
					$("#actContainer").html(data);
					$("#homeTodo").attr("class","unactive");
					$("#homeAll").attr("class","unactive");
					$("#homeFinish").attr("class","unactive");
					$("#homeSup").attr("class","active");
				}
			 });
		});
	</script> 

<!-- </head>
<body> -->
 	
	<ul class="nav nav-tabs">
		<li id="homeAll" class="active"><a>所有任务[${allNum}]</a></li>
		<li id="homeTodo" class="unactive"><a >待办任务[${tNum}]</a></li>
		<li id="homeFinish" class="unactive"><a>已办任务[${fNum}]</a></li>
		<li id="homeSup" class="unactive"><a>我的督办[${supNum}]</a></li>
	</ul>
	<sys:message content="${message}"/>
	<div id ="actContainer">
	<table id="contentTable" class="table table-striped table-bordered table-condensed" style="table-layout: fixed;">
		<thead>
			<tr>
				<th style="width: 40%">标题</th>
				<th>状态</th>
				<th style="width: 30%">类型</th>
				<th>是否督办</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="plmAct" end="${line-1}">
			<tr>
				<td><a href="${ctx}/act/plmAct/form?id=${plmAct.id}">
					${plmAct.title}
				</a></td>
				<td>
					${fns:getDictLabel(plmAct.status, 'plm_act_status', '')}
				</td>
				<td>
					${fns:getDictLabel(plmAct.type, 'act_type', '')}
				</td>
				<td>
					${fns:getDictLabel(plmAct.isSup, 'yes_no', '')}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<%-- <div class="pagination">${page}</div> --%>
	</div>
<!--  </body>
</html>  -->