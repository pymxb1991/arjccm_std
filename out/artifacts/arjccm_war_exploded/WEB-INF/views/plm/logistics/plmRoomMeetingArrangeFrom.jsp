<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会议室</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/common/zztable.css" type="text/css"
	rel="stylesheet">
	<!-- 表格试表单css -->
	<link href="${ctxStatic}/common/zzformtable.css" type="text/css" rel="stylesheet">	
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$('#btnSubmit').click(function(){
				$('#inputForm').submit();
			});
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
			$(".trtop").css({
				"width" : "20%"
			});
		});
	</script>
</head>
<body>
	<form:form id="inputForm" style="margin: 30px 100px;" modelAttribute="plmRoomApplyResource" action="${ctx}/logistics/plmRoomMeetingApplyResource/saveroomresource" method="post" class="form-horizontal">
		<form:hidden path="meetingId" value="${meetingId}"/> 
		<sys:message content="${message}"/>	
		<h3 style="margin-bottom: 30px;">会议安排附件</h2>	
		
		<div class="control-group">
			<label class="control-label">附件类型：</label>
			<div class="controls">
				<form:select path="resourceName" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('plm_room_resource_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select> 
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">附件：</label>
			<div class="controls">
				<form:hidden id="resourceAddress" path="resourceAddress" htmlEscape="false"
					maxlength="1000" class="input-xlarge" />
				<sys:ckfinder input="resourceAddress" type="files"
					uploadPath="/logistics/plmRoomMeetingApplyResource" selectMultiple="true" />
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="logistics:plmRoom:edit"><a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-ok"></i>保存</a>&nbsp;</shiro:hasPermission>
		</div>
	</form:form>
</body>
</html>