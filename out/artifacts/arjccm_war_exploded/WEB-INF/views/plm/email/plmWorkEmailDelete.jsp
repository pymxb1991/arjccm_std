<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>邮件发送成功</title>
<meta name="decorator" content="default" />
	
</head>
<body>
	<ul class="nav nav-tabs">
			<li class="active"><a>删除成功</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="plmWorkEmail" action="" class="form-horizontal">
		<sys:message content="${message}" />
		<h4>您的邮件已删除</h4>
		<p>此邮件删除成功，并已保存到“已删除”文件夹。</p>
	</form:form>
</body>
</html>