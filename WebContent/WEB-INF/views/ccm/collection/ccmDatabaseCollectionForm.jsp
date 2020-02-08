<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>收藏夹管理管理</title>
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
		<li><a href="${ctx}/collection/ccmDatabaseCollection/">收藏夹管理列表</a></li>
		<li class="active"><a href="${ctx}/collection/ccmDatabaseCollection/form?id=${ccmDatabaseCollection.id}">收藏夹管理<shiro:hasPermission name="collection:ccmDatabaseCollection:edit">${not empty ccmDatabaseCollection.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="collection:ccmDatabaseCollection:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ccmDatabaseCollection" action="${ctx}/collection/ccmDatabaseCollection/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">书籍信息UUID：</label>
			<div class="controls">
				<form:input path="bookId" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户信息UUID：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${ccmDatabaseCollection.user.id}" labelName="user.name" labelValue="${ccmDatabaseCollection.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="collection:ccmDatabaseCollection:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>