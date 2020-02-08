<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ page import="java.util.*"%>
<c:set var="ctxpath" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
  var ctxpath = "${bathpath}${ctxpath}";
</script>
<html>
<head>
<title>聊天室</title>
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"
	type="text/javascript"></script>
<script>
  window.onload = function() {
    var linkList = window.parent.document.getElementsByTagName("link");//获取父窗口link标签对象列表
    var head = document.getElementsByTagName("head").item(0);
    //外联样式
    for (var i = 0; i < linkList.length; i++) {
      var l = document.createElement("link");
      l.rel = 'stylesheet'
      l.type = 'text/css';
      l.href = linkList[i].href;
      head.appendChild(l);
    }
  }
</script>

<style>
body {
	margin-top: 5px;
}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/chat/pbsChatroom/">聊天室列表</a></li>
		<shiro:hasPermission name="chat:pbsChatroom:edit">
			<li class="active"><a>聊天房间</a></li>
		</shiro:hasPermission>
	</ul>
	<div class="container container-fluid" style="margin-top: 30px">
		<div id="userId" class="hide" userid="${parmember.id}"
			username="${parmember.SName}"></div>
		<div id="roomid" class="hide" roomid="${roomid}"></div>
		<div class="row ">
			<div style="width: 25%; margin-left: -15%; float: left">
				<div class="panel panel-primary">
					<div class="panel-heading" style="height: 20px">
						<h3 class="panel-title"
							style="font-size: 16px; font-family: normal; color: white; margin-top: -8px">当前登录用户</h3>
					</div>
					<div class="panel-body">
						<div class="list-group">
							<a href="#" class="list-group-item">你好，${parmember.SName}</a> <a
								href="${ctx}/chat/pbsChatroom/" class="list-group-item">退出</a>
						</div>
					</div>
				</div>
				<div class="panel panel-primary" id="online">
					<div class="panel-heading" style="height: 20px">
						<h3 class="panel-title"
							style="font-size: 16px; font-family: normal; color: white; margin-top: -8px">当前房间</h3>
					</div>
					<div class="panel-body" style="height: 334px;">
						<div class="list-group" id="rooms"></div>
					</div>
				</div>
				<!-- 目前需求不需要暂时注释掉 -->
				<!-- <div class="panel panel-primary">
				  <div class="panel-heading">
				    <h3 class="panel-title">群发系统广播</h3>
				  </div>
				  <div class="panel-body">
				    <input type="text" class="form-control"  id="msg" /><br>
				    <button id="broadcast" type="button" class="btn btn-primary">发送</button>
				  </div>
				</div> -->
			</div>
			<div style="width: 60%; float: left; margin-left: 5%">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title" id="talktitle"></h3>
					</div>
					<div class="panel-body" >
						<div class="well" id="log-container"
							style="height: 400px; overflow-y: scroll"></div>
						<input type="text" id="myinfo" class="form-control"
							style="width: 100%; height: 30px; marign-top: -10px; margin-bottom: 40px" />
						<br>
						<button id="send" type="button" class="btn btn-primary"
							sendtype="" style="margin-top: -30px; float: right">发送</button>
					</div>
				</div>
			</div>
			<div style="float: right; margin-right: -6%; width: 25%">
				<div class="panel panel-primary" id="online">
					<div class="panel-heading" style="height: 20px">
						<h3 class="panel-title"
							style="font-size: 16px; font-family: normal; color: white; margin-top: -8px">当前在线用户</h3>
					</div>
					<div class="panel-body" style="height: 511px;">
						<div class="list-group" id="users"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctxStatic}/pbs/exchange/chatroom.js"
		type="text/javascript"></script>
</body>
</html>
