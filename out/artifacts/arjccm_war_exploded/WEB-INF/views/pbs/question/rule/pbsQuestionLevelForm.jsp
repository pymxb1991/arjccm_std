<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>考试难度级别管理</title>
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
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/question/pbsQuestionLevel/">考试难度级别列表</a></li>
		<li class="active"><a href="${ctx}/question/pbsQuestionLevel/form?id=${pbsQuestionLevel.id}">考试难度级别<shiro:hasPermission name="question:pbsQuestionLevel:edit">${not empty pbsQuestionLevel.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="question:pbsQuestionLevel:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pbsQuestionLevel" action="${ctx}/question/pbsQuestionLevel/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>考试难度级别名称：</label>
			<div class="controls">
				<form:input path="sName" htmlEscape="false" maxlength="500" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">难度级别赋值：</label>
			<div class="controls">
				<form:input path="iVal" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">展示的图片：</label>
			<div class="controls">
				<form:hidden id="sUrl" path="sUrl" htmlEscape="false" maxlength="500" class="input-xlarge"/>
				<sys:ckfinder input="sUrl" type="files" uploadPath="/question/pbsQuestionLevel" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="question:pbsQuestionLevel:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>