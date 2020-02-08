<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>抓拍机管理管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
	<script type="text/javascript">
		$(document).ready(function() {
			
			//关闭弹框事件
			$('#btnCancel').click(function() {
				parent.layer.close(parent.layerIndex);
			})
			
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
			$("td").css({"padding":"8px"});
			$("td").css({"border":"1px dashed #CCCCCC"});
		});
	</script>
</head>
<body>
	<%-- <ul class="nav nav-tabs">
		<li><a href="${ctx}/grabber/ccmGrabberManage/">抓拍机管理列表</a></li>
		<li class="active"><a href="${ctx}/grabber/ccmGrabberManage/form?id=${ccmGrabberManage.id}">抓拍机管理<shiro:hasPermission name="grabber:ccmGrabberManage:edit">${not empty ccmGrabberManage.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="grabber:ccmGrabberManage:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/> --%>
	<form:form id="inputForm" modelAttribute="ccmGrabberManage" action="${ctx}/grabber/ccmGrabberManage/save" method="post" class="form-horizontal" style="margin-top:10px;">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table border="1px" style="border-color: #CCCCCC; border: 1px solid #CCCCCC; padding: 10px; width: 100%;">
			<tr>
				<td>
					<div class="control-group" style="border:none;margin-top:10px;">
						<label class="control-label">抓拍机系数：</label>
						<div class="controls">
							<form:input path="grabberCoefficient" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group" style="border:none;margin-top:10px;">
						<label class="control-label">抓拍机偏移量：</label>
						<div class="controls">
							<form:input path="grabberOffset" htmlEscape="false" maxlength="64" class="input-xlarge required number"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="control-group" style="border:none;margin-top:10px;">
						<label class="control-label">正常值范围：</label>
						<div class="controls">
							<form:input path="valMax" htmlEscape="false" maxlength="64" class="input-xlarge required number"/>&nbsp;-&nbsp;<form:input path="valMin" htmlEscape="false" maxlength="64" class="input-xlarge required number"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="control-group" style="border:none;margin-top:10px;">
						<label class="control-label">超过范围报警状态：</label>
						<div class="controls">
							<form:checkboxes path="reportState" items="${fns:getDictList('ccm_grabber_report_state')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
						</div>
					</div>
				</td>
			</tr>
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="grabber:ccmGrabberManage:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn btn-danger" type="button" value="关 闭"/>
		</div>
	</form:form>
</body>
</html>