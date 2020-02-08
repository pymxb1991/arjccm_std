<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>用户绑定设备管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$('#btnSubmit').click(function(){
			$('#inputForm').submit();
		});
		$("#inputForm").validate({
			submitHandler: function(form){
				loading('正在提交，请稍等...');
				form.submit();
			},
			errorContainer: "#messageBox",
			errorPlacement: function(error, element) {
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
<%--引入文本框外部样式--%>
<link href="/arjccm/static/bootstrap/2.3.1/css_input/input_Custom.css" type="text/css" rel="stylesheet">
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/userBindingDevice/userBindingDevice/finduserBindingDeviceList">数据列表</a></li>
		<li class="active"><a href="${ctx}/userBindingDevice/userBindingDevice/form?id=${user.id}">${not empty user.id?'修改':'绑定'}</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="userBindingDevice" action="${ctx}/userBindingDevice/userBindingDevice/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="userId" />
		<sys:message content="${message}" />
		<div class="control-group head_Space">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>警务通设备编号:</label>
			<div class="controls">
				<form:input path="policePhoneCode" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>执法记录仪设备编号:</label>
			<div class="controls">
				<form:input path="actionRecoderCode" htmlEscape="false" maxlength="50" class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>对讲设备编号:</label>
			<div class="controls">
				<form:input path="interPhoneCode" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">定位优先级:</label>
			<form:radiobuttons path="defualtDevice" items="${fns:getDictList('DEFUALT_DEVICE')}" itemLabel="label" itemValue="value" htmlEscape="false" style="margin-left:20px;" />
		</div>
		<div class="form-actions">
			<!-- <input id="btnSubmit" class="btn btn-primary" type="submit" value="保存" /> -->
			<a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-ok"></i>保存</a>
			&nbsp;
			<!-- <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" /> -->
			<a id="btnCancel" class="btn btn-back" href="javascript:;"  onclick="history.go(-1)"><i class="icon-reply"></i>返回</a>
		</div>
	</form:form>
</body>
</html>