<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>事件处理详细信息</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
<script charset="utf-8" type="text/javascript">
	function saveForm(){
		//var flagTime = $("#flagTime").val();
		//if(flagTime.length!=0){
			//$("#inputForm").submit();
			//parent.$.jBox.tip('保存成功！ ');
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		//}else{
		//	$("#flag").html('<label for="" class="error">必填信息 *<label>');
		//}
		
		
	}
	function closeForm(){
		var index = parent.layer.getFrameIndex(window.name); 
		parent.layer.close(index);
		
	}
</script>
</head>
<body>
	<br />
	<form:form id="inputForm" modelAttribute="ccmEventCasedeal"
		action="${ctx}/event/ccmEventCasedeal/saveJump" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="objId" />
		<form:hidden path="objType" />
        <form:hidden path="checkDate" />
        <form:hidden path="checkUser" />
        <form:hidden path="checkOpinion" />
        <form:hidden path="checkScore" />
		<sys:message content="${message}" />
		
		<h4>任务安排：</h4>
		<br>
		<table border="1px" style="border-color: #CCCCCC; border: 1px solid #CCCCCC; padding: 10px; width: 100%;">
			<tr>
				<td style="width: 50%;">
					<label class="control-label">处理人员：</label>
					<div class="controls">
						${ccmEventCasedeal.handleUser.name}
					</div>
				</td>
				<td>
					<label class="control-label">处理人联系方式：</label>
					<div class="controls">
						${ccmEventCasedeal.handleUser.phone}
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<label class="control-label">处理截至时间：</label>
					<div class="controls">
						<fmt:formatDate value="${ccmEventCasedeal.handleDeadline}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</div>
				
				</td>
				<td>
					<label class="control-label">是否督办：</label>
					<div class="controls">
						${fns:getDictLabel(ccmEventCasedeal.isSupervise, 'yes_no', '')}
					</div>
				
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">附件上传：</label>
						<div class="controls">
							<form:hidden id="file" path="file" htmlEscape="false"
								maxlength="255" class="input-xlarge" />
							<sys:ckfinder input="file" type="images"
								uploadPath="/event/ccmEventCasedeal" selectMultiple="false" maxWidth="240"
								maxHeight="360" />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">事件反馈状态：</label>
						<div class="controls">
							<form:radiobuttons name="manageType" path="manageType" items="${fns:getDictList('manage_type')}"
								itemLabel="label" itemValue="value" htmlEscape="false" class="" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td colspan="2">
					<label class="control-label">事件说明及任务安排：</label>
					<div class="controls">
						<form:textarea path="remarks" style="width: 1180px;" htmlEscape="false" rows="8" maxlength="1000" class="input-xxlarge "/>
					</div>
				</td>
			</tr>
		</table>
		
		<br>
		<h4>处理信息：</h4>
		<br>
		<table class="table table-bordered table-hover" >
			<tr>
				<td>
						<label class="control-label">处理时间：</label>
						<div class="controls">
							<input name="handleDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required" id="flagTime"
								value="<fmt:formatDate value="${ccmEventCasedeal.handleDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							<span class="help-inline"><font color="red" id="flag">*</font> </span>
						</div>
				</td>
				<td>
						<label class="control-label">任务处理状态：</label>
						<div class="controls">
							<form:select path="handleStatus" class="input-xlarge " disabled="true">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('ccm_event_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
				</td>
			</tr>
			<tr>
				<td>
						<label class="control-label">处理措施：</label>
						<div class="controls">
							<form:textarea path="handleStep" htmlEscape="false" rows="6" maxlength="256" class="input-xlarge "/>
						</div>
				</td>
				<td>
						<label class="control-label">案事件反馈：</label>
						<div class="controls">
							<form:textarea path="handleFeedback" htmlEscape="false" rows="6" maxlength="256" class="input-xlarge "/>
						</div>
				</td>
			</tr>
			
			<!--  进行事件处理时，下述不显示
			<div class="control-group">
				<label class="control-label">考评日期：</label>
				<div class="controls">
					<input name="checkDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
						value="<fmt:formatDate value="${ccmEventCasedeal.checkDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">考评人员：</label>
				<div class="controls">
					<form:input path="checkUser" htmlEscape="false" maxlength="64" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">考评意见：</label>
				<div class="controls">
					<form:input path="checkOpinion" htmlEscape="false" maxlength="256" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">得分：</label>
				<div class="controls">
					<form:input path="checkScore" htmlEscape="false" maxlength="4" class="input-xlarge "/>
				</div>
			</div>
			-->
			
			
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="event:ccmEventCasedeal:edit">
				<input id="btnSubmit" class="btn btn-primary" onclick="saveForm()" type="button" value="关  闭" />&nbsp;</shiro:hasPermission>
		</div>
	</form:form>
</body>
</html>