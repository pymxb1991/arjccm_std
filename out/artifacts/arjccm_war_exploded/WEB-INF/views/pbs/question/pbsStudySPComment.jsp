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
				<a href="${ctx}/question/pbsTestOnlinePC/coursePage">党建视频列表</a>
			</li>
			<li>
				<a href="${ctx}/question/pbsTestOnlinePC/courseInfo?id=${studyId}">视频播放</a>
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
								<img src="${comment.SUserid.photo}" />
							</a>
							<div class="lzl_cnt">
								<a class="j_user_card ">${comment.username}：</a>
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
					<%-- <li>
						<a class="user_card">
							<img src="https://gss0.bdstatic.com/6LZ1dD3d1sgCo2Kml5_Y_D3/sys/portrait/item/6985e5a4a9e7bd9ae58dab242a" />
						</a>
						<div class="lzl_cnt">
							<a class="j_user_card ">${comment.username}：</a>
							<span class="lzl_content_main">
								这里是评论内容
							</span>
							<div class="lzl_content_reply">
								<span class="lzl_time">2018-9-2 16:40</span>
								<a class="lzl_s_r">回复</a>
							</div>
						</div>
					</li>
					<li>
						<a class="user_card">
							<img src="https://gss0.bdstatic.com/6LZ1dD3d1sgCo2Kml5_Y_D3/sys/portrait/item/6985e5a4a9e7bd9ae58dab242a" />
						</a>
						<div class="lzl_cnt">
							<a class="j_user_card ">张三：</a>
							<span class="lzl_content_main">
								这里是评论内容
							</span>
							<div class="lzl_content_reply">
								<span class="lzl_time">2018-9-2 16:40</span>
								<a class="lzl_s_r">回复</a>
							</div>
						</div>
					</li> --%>
				</ul>
			</div>
			<!--<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>评论人员</th>
						<th>评论时间</th>
						<th>评论内容</th>
					</tr>
				</thead>
				<tbody id="allComment">
					<c:forEach items="${commentList}" var="comment">
						<c:if test="${not empty comment.SContent}">
							<tr>
								<td>${comment.username}</td>
								<td>
									<fmt:formatDate value="${comment.createDate}" pattern="yyyy-MM-dd" />
								</td>
								<td>${comment.SContent}</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
				<tbody id="userComment">
					<c:forEach items="${userComList}" var="comment">
						<c:if test="${not empty comment.SContent}">
							<tr>
								<td>${comment.username}</td>
								<td>
									<fmt:formatDate value="${comment.createDate}" pattern="yyyy-MM-dd" />
								</td>
								<td>${comment.SContent}</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>-->
		</div>
	</body>

</html>