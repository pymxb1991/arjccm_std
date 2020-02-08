<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>支部通讯录</title>
<meta name="decorator" content="default"/>
<script src="${ctxStatic}/statis/js/jquery-2.2.4.min.js" type="text/javascript"></script>
</head>
<body style="backgroud-color:#bdbdbd">
	<ul class="nav nav-tabs">
		<li class="active"><a>支部通信录</a></li>
	</ul>
	<table align="center" id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>学员头像</th>
				<th>学员名称</th>
				<th>联系电话</th>
				<th>工作岗位</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pbsPartymems}" var="pbsPartymem">
				<tr>
					<td style="text-align:center;width:60px">
						<img onerror='this.src="${ctxStatic}/wechat/img/head.png"' src="${pbsPartymem.SPhoto}" style="width:40px;height:40px"> 
					</td>
					<td>${pbsPartymem.SName}</td>
					<td>
					<img src="${ctxStatic}/statis/img/shouji.png" style="width:13px"/>
					${pbsPartymem.SMobilephone}
					</td>
					<td>${pbsPartymem.SPost}</td>
				</tr> 
			</c:forEach>
		</tbody>
	</table>
</body>
</html>