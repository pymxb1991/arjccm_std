<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>内部邮件回复管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					$("#btnSubmit").attr("disabled", true);
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#btnSubmit").removeAttr('disabled');
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<%-- <ul class="nav nav-tabs">
		<li><a href="${ctx}/email/plmWorkEmail/self">收件箱</a></li>
		<li><a href="${ctx}/email/plmWorkEmail/view?id=${plmWorkEmail.id}">邮件<shiro:hasPermission
						name="email:plmWorkEmail:edit">${not empty plmWorkEmail.id?'查看':'添加'}</shiro:hasPermission></a></li>
		<li class="active"><a href="${ctx}/email/plmWorkEmailReply/form?id=${plmWorkEmailReply.id}">添加回复</a></li>
	</ul><br/> --%>
	<form:form id="inputForm" modelAttribute="plmWorkEmailReply" action="${ctx}/email/plmWorkEmailReply/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="workEmailId"/>
		<sys:message content="${message}"/>		
		<!-- <div class="control-group">
			<label class="control-label">回复内容：</label>
			<div class="controls"> -->
			${plmWorkEmailReply.remarks}
				<%-- <form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/> --%>
			<!-- </div>
		</div> -->
		<!-- <div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div> -->
	</form:form>
</body>
</html>