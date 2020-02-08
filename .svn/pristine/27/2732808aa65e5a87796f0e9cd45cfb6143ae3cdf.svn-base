<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<title>学员档案</title>
<%@include file="/WEB-INF/views/include/pbshead.jsp"%>
</head>

<body>
	<div class="mui-content">
		<ul
			class="mui-table-view mui-table-view-striped mui-table-view-condensed">
			<li class="mui-table-view-cell"
				style="background: url(${ctxStatic}/wechat/img/bg.png) center no-repeat; background-size: 100% 100%;">
				<img class="mui-media-object mui-pull-left"
				src="${ctxStatic}/wechat/img/t1.jpg">
				<div class="mui-media-body color-white">
					${user.name}
					<p class='mui-ellipsis color-white'>${user.email}</p>
				</div>
			</li>
		</ul>
		<div class="mui-card">
			<ul class="mui-table-view">
				<li class="mui-table-view-cell mui-media">
					<div class="mui-media-body">
						党支部
						<p class='mui-ellipsis'>第一书记</p>
					</div>
				</li>
				<li class="mui-table-view-cell mui-media">
					<div class="mui-media-body">
						党内职务
						<p class='mui-ellipsis'>支部书记</p>
					</div>
				</li>
				<li class="mui-table-view-cell mui-media">
					<div class="mui-media-body">
						入党时间
						<p class='mui-ellipsis'>2013年7月1日</p>
					</div>
				</li>
				<li class="mui-table-view-cell mui-media">
					<div class="mui-media-body">
						联系方式
						<p class='mui-ellipsis'>${user.phone}</p>
					</div>
				</li>
			</ul>
		</div>
	</div>
</body>
<script>
	mui.init({
		swipeBack : true
	//启用右滑关闭功能
	});
</script>

</html>