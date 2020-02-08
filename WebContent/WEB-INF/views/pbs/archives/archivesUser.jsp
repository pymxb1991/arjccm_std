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
		<div class="mui-content-padded">
			<div class="mui-input-row mui-search">
				<input type="search" class="mui-input-clear"
					style="margin-bottom: 0;" placeholder="搜索名字/拼音/手机号码/党内职位/党支部">
			</div>
		</div>
		<ul
			class="mui-table-view mui-table-view-striped mui-table-view-condensed">
			<li class="mui-table-view-cell"><a>首页 > 党委</a></li>
		</ul>
		<div class="mui-card">
			<ul class="mui-table-view">
				<c:forEach items="${usrList}" var="pbspersoninfo">
					<li class="mui-table-view-cell mui-media"><a
						href="${ctxFront}/user/userInfo?id=${pbspersoninfo.id}"> <img
							class="mui-media-object mui-pull-left"
							src="${ctxStatic}/wechat/img/t1.jpg">
							<div class="mui-media-body">
								${pbspersoninfo.SName}
								<p class='mui-ellipsis'>
								 ${pbspersoninfo.SPost}
								</p>
							</div>
					</a></li>
				</c:forEach>

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


