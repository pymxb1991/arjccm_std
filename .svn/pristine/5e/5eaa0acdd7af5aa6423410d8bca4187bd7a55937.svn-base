<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<title>档案</title>
<%@include file="/WEB-INF/views/include/pbshead.jsp"%>
</head>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<title>学员档案</title>
</head>
<body>
	<div class="mui-content-padded" style="margin: 50px 10px 10px 10px;">
		<div class="mui-input-row mui-search">
			<input type="search" class="mui-input-clear"
				style="margin-bottom: 0;" placeholder="搜索名字/拼音/手机号码/党内职位/党支部">
		</div>
	</div>
	<div class="mui-table-view-cell" style="background: #fff;">首页</div>
	<div class="mui-content">
		<div id="pullrefresh" class="mui-content mui-scroll-wrapper">
			<div class="mui-scroll">
				<ul class="mui-table-view mui-table-view-chevron">
					<c:forEach items="${ofcList}" var="office">
						<li class="mui-table-view-cell">
							<!--<img class="mui-media-object mui-pull-left" src="../images/shuijiao.jpg">-->
							<a class="mui-navigate-right"
							href="${ctxFront}/user/list?office.id=${office.id}">${office.name}(
								${fns:getDictLabel(office.type,'sys_area_type',"")}) </a>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</body>


</html>