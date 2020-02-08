<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>微信信息上报管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
	<link rel="stylesheet" href="${ctxStatic}/ccm/event/css/fishBone.css" />
	<script type="text/javascript" src="${ctxStatic}/ccm/event/js/fishBone.js"></script>
	<script type="text/javascript" src="${ctxStatic}/ccm/event/js/jquery.SuperSlide.2.1.1.js"></script>
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
			var jsonString = '${CcmEventCasedealList}';
			data = JSON.parse(jsonString);  
			//创建事件历史
			$(".fishBone1").fishBone(data, '${ctx}','deal');
			$(".fishBone2").fishBone(data, '${ctx}','read');
		});
	</script>
	<style type="text/css">
		td{padding: 8px;border: 1px dashed #CCCCCC}
	</style>
</head>
<body>
	<form:form id="inputForm" modelAttribute="ccmWechatEvent" action="${ctx}/event/wechat/ccmWechatEvent/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<%-- <div class="control-group ">
			<label class="control-label ">微信用户：</label>
			<div class="controls">
				<form:input path="wechatUser" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div> --%>
		<table border="1px" style="border-color: #CCCCCC; border: 1px solid #CCCCCC; padding: 10px; width: 100%;">
			<tr>
				<td>
					<label class="control-label">上报时间：</label>
					<div class="controls">
						<input name="reportTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmWechatEvent.reportTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<label class="control-label">上报信息：</label>
					<div class="controls">
						<form:textarea path="reportInfo" htmlEscape="false" rows="4" maxlength="256" class="input-xxlarge "/>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<label class="control-label">回复情况：</label>
					<div class="controls">
						${fns:getDictLabel(ccmWechatEvent.state, 'ccm_wechat_event_state', '')}
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<label class="control-label">联系方式：</label>
					<div class="controls">
						<form:input path="userTel" htmlEscape="false" maxlength="64" class="input-xlarge phone"/>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<label class="control-label">附件：</label>
					<div class="controls">
						<%--<c:forEach items="${ccmWechatEvent.eventAttachmentList}" var="eventAttachment">
							<a href="${eventAttachment.path}" 
							url="${eventAttachment.path}" target="_blank" class="">
							${eventAttachment.fileName}.${eventAttachment.fileType}</a><br>
						</c:forEach>--%>
						<form:hidden id="file" path="file"  htmlEscape="false" maxlength="255" class="input-xxlarge"/>
						<sys:ckfinder input="file" type="files" uploadPath="/event/wechat/ccmWechatEvent" selectMultiple="false"/>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<label class="control-label">处理状态：</label>
					<div class="controls">
						<form:select path="status" class="input-xlarge ">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('ccm_event_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<label class="control-label">备注信息：</label>
					<div class="controls">
						<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
					</div>
				</td>
			</tr>
		</table>
		
		<div class="form-actions">
			<shiro:hasPermission name="event:wechat:ccmWechatEvent:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
<%-- 			<a href="${ctx}/event/wechat/ccmWechatEvent/?repage"> --%>
<!-- 			<input id="btnCancel" class="btn" type="button" value="返 回" /> -->
<!-- 			<a/> -->
		</div>
	</form:form><br/>
	<c:if test="${CasedealListNumber > 0}">
		<shiro:hasPermission name="event:ccmEventCasedeal:edit">
			<h4>&nbsp;修改处理信息：</h4>
			<div class="fishBone1" ></div>
		</shiro:hasPermission>
		<shiro:lacksPermission name="event:ccmEventCasedeal:edit">
			<h4>&nbsp;查看处理信息：</h4>
			<div class="fishBone2" ></div>
		</shiro:lacksPermission> 
	</c:if>
</body>
</html>