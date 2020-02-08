<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>微信回复管理</title>
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
		<li ><a href="${ctx}/event/wechat/ccmWechatEventReply/list?event.id=${ccmWechatEventReply.event.id}&type=chatEvent">微信回复列表</a></li>
		<shiro:hasPermission name="event:wechat:ccmWechatEventReply:edit"><li class="active"><a href="${ctx}/event/wechat/ccmWechatEventReply/form?id=${ccmWechatEventReply.id}">微信回复修改</a></li></shiro:hasPermission>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ccmWechatEventReply" action="${ctx}/event/wechat/ccmWechatEventReply/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="event.id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">上报详情：</label>
			<div class="controls">
				${ccmWechatEventReply.event.reportInfo }
			</div>
		</div> 
		<div class="control-group">
			<label class="control-label">回复时间：</label>
			<div class="controls">
				<input name="replyTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${ccmWechatEventReply.replyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">回复人员：</label>
			<div class="controls">
				${ccmWechatEventReply.createBy.name}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">回复信息：</label>
			<div class="controls">
				<form:textarea path="replyInfo" htmlEscape="false" rows="4" maxlength="256" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">消息回复状态：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ccm_wechat_event_reply_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="event:wechat:ccmWechatEventReply:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>