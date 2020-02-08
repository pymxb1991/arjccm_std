<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>街路巷管理</title>
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
	<link href="/arjccm/static/bootstrap/2.3.1/css_input/input_Custom.css" type="text/css" rel="stylesheet">
</head>
<body>
	<ul class="nav nav-tabs hide1">
		<li><a style="width: 140px;text-align:center" href="${ctx}/pop/ccmRoadAddress/">数据列表</a></li>
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/pop/ccmRoadAddress/form?id=${ccmRoadAddress.id}">数据<shiro:hasPermission name="pop:ccmRoadAddress:edit">${not empty ccmRoadAddress.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pop:ccmRoadAddress:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="ccmRoadAddress" action="${ctx}/pop/ccmRoadAddress/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group"style="padding-top: 8px">
			<label class="control-label"><span class="help-inline"><font color="red" >*</font> </span> 街路巷名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge required" />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red" >*</font> </span>地址：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="512" class="input-xlarge required"/>

			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">区域图：</label>
			<div class="controls">
				<form:input path="areaMap" htmlEscape="false" maxlength="2000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中心点：</label>
			<div class="controls">
				<form:input path="areaPoint" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">图片：</label>
			<div class="controls">
				<form:hidden id="image" path="image" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="image" type="images" uploadPath="/pop/ccmRoadAddress" selectMultiple="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pop:ccmRoadAddress:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>