<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资料库录入管理管理</title>
	<meta name="decorator" content="default"/>
    <script src="${ctxStatic}/layer-v3.1.1/layer/layer.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			//关闭弹框事件
			$('#btnCancel').click(function() {
				parent.layer.close(parent.layerIndex);
			})
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
		<li class="active"><a href="${ctx}/book/ccmDatabaseBook/form?id=${ccmDatabaseBook.id}">书籍录入<shiro:hasPermission name="book:ccmDatabaseBook:edit">${not empty ccmDatabaseBook.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="book:ccmDatabaseBook:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ccmDatabaseBook" action="${ctx}/book/ccmDatabaseBook/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">书籍编号：</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="64" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
            </div>
		</div>
		<div class="control-group">
			<label class="control-label">书籍名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
            </div>
		</div>

		<div class="control-group">
			<label class="control-label">书籍显示排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
		</div>

		<%--<div class="control-group">
			<label class="control-label">书籍图片：</label>
			<div class="controls">
				<form:input path="imageurl" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>--%>

        <div class="control-group">
			<label class="control-label">书籍图片：</label>
			<div class="controls">
				<form:hidden id="file1" path="imageurl"  htmlEscape="false" maxlength="200" class="input-xxlarge"/>
				<sys:ckfinder input="file1" type="images" uploadPath="/book/ccmDatabaseBook" selectMultiple="false"/>
				<span class="help-inline"><font color="red">*</font> </span>
            </div>
		</div>

		<div class="control-group">
			<label class="control-label">描述信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="250" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="book:ccmDatabaseBook:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn btn-danger" type="button" value="关 闭"/>
			<%--<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
		</div>
	</form:form>
</body>
</html>