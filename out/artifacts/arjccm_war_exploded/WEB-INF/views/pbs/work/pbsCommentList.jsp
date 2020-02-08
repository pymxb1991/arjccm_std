<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>评论详细信息</title>
<meta name="decorator" content="default" />
<style>
	li {
		list-style: none;
	}
	
	a {
		text-decoration: none;
	}
	.comment li {
		padding-top: 10px;
		padding-left: 50px;
	}
	.user_card {
		float: left;
		width: 32px;
		height: 32px;
		border: 1px #ccc solid;
		padding: 1px;
	}
	
	.lzl_cnt {
		margin-left: 45px;
		zoom: 1;
		word-wrap: break-word;
		word-break: break-all;
	}
	
	.j_user_card {
		position: relative;
		zoom: 1;
	}
	
	.lzl_content_main {
		line-height: 20px;
		font-size: 14px;
		word-wrap: break-word;
		word-break: break-word;
	}
	
	.lzl_content_reply {
		width: 80%;
		text-align: right;
	}
	
	.lzl_s_r {
		cursor: pointer;
		color: #666;
		padding-left: 5px;
	}
</style>
<script type="text/javascript">
	$(function() {
		// 用户评论 隐藏
		$("#userComment").hide();
		// 评论转换按钮
		$("#tabTransform a").click(function() {
			// 分别
			if("allTab" == ($(this).attr("id"))) {
				$("#allComment").show();
				$("#userComment").hide();
			} else {
				$("#userComment").show();
				$("#allComment").hide();
			}
		});
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li>
			<a href="${ctx}/work/pbsMeeting/">活动信息列表</a>
		</li>
		<li>
			<a href="${ctx}/work/pbsComment/ActivityNewsInfo?id=${id}">详细信息</a>
		</li>
		<li class="active">
			<a>评论信息</a>
		</li>
	</ul>

	<div>
		<div id="tabTransform">
			<!-- <a href="#" id="allTab"><span>全部评论 &nbsp;&nbsp;</span></a>
			<a href="#" id="userTab"><span>我的评论</span></a> -->
		</div>
		<div class="comment" id="allComment">
			<ul>
				<c:forEach items="${commentList}" var="comment">
				<c:if test="${not empty comment.SContent}">
					<li>
						<a class="user_card">
							<img src="${comment.sBindmember.SPhoto}" onerror='this.src="${ctxStatic}/wechat/img/head.png"'/>
						</a>
						<div class="lzl_cnt">
							<a class="j_user_card ">${comment.sOperator.name}：</a>
							<span class="lzl_content_main">
								${comment.SContent}
							</span>
							<div class="lzl_content_reply">
								<span class="lzl_time"><fmt:formatDate value="${comment.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
								<!-- <a class="lzl_s_r">回复</a> -->
							</div>
						</div>
					</li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
<%-- <body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/work/pbsMeeting/">活动信息列表</a></li>
		<li><a href="${ctx}/work/pbsComment/ActivityNewsInfo?id=${id}">详细信息</a></li>
		<shiro:hasPermission name="work:pbsActivityrec:edit">
			<li class="active"><a href="">评论详细信息</a></li>
		</shiro:hasPermission>
	</ul>
	
	<div>
		<div id="tabTransform">
			<a href="#" id="allTab"><span>全部评论 &nbsp;&nbsp;</span></a> 
			<a href="#" id="userTab"><span>我的评论</span></a>
		</div>
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>评论人员</th>
					<th>评论时间</th>
					<th>评论内容</th>
				</tr>
			</thead>
			<tbody id="allComment">
				<c:forEach items="${commentList}" var="comment">
					<tr>
						<td>${comment.sOperator.name}</td>
						<td><fmt:formatDate value="${comment.createDate}" pattern="yyyy-MM-dd" /></td>
						<td>${comment.SContent}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tbody id="userComment">
				<c:forEach items="${userComList}" var="comment">
					<tr>
						<td>${comment.sOperator.name}</td>
						<td><fmt:formatDate value="${comment.createDate}" pattern="yyyy-MM-dd" /></td>
						<td>${comment.SContent}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body> --%>
</html>