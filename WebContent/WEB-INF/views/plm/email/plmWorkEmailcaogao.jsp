<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>邮件发送成功</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
			<li class="active"><a>保存成功</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="plmWorkEmail" action="" class="form-horizontal">
		<sys:message content="${message}" />
		<h4>您的邮件已保存到草稿箱</h4>
		<p>此邮件已成功保存为草稿，并已保存到“草稿箱”文件夹。</p>
	</form:form>

</body>
</html> 

