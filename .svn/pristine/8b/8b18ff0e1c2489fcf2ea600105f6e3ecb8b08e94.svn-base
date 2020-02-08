<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>微信信息上报管理</title>
	<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
	<meta name="decorator" content="default"/>
</head>
<body>
	<br />
	<form:form id="inputForm" modelAttribute="ccmWechatEvent" action="${ctx}/event/wechat/ccmWechatEvent/save" method="post" class="form-horizontal">
		<table class="table table-bordered table-hover">
			
			<tr>
				<td>
					<div>
						<label class="control-label">上报时间：</label>
						<div class="controls">
							<input name="reportTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmWechatEvent.reportTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">处理状态：</label>
						<div class="controls">
							<form:select path="status" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('ccm_event_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
				
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">上报信息：</label>
						<div class="controls">
							<form:textarea path="reportInfo" htmlEscape="false" rows="4" maxlength="256" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">附件：</label>
						<div class="controls">
							<c:forEach items="${ccmWechatEvent.eventAttachmentList}" var="eventAttachment">
								<a href="${eventAttachment.path}" 
								url="${eventAttachment.path}" target="_blank" class="">
								${eventAttachment.fileName}.${eventAttachment.fileType}</a><br>
							</c:forEach>
						</div>
					</div>
				</td>
				
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">联系方式：</label>
						<div class="controls">
							<form:input path="userTel" htmlEscape="false" maxlength="64" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">回复情况：</label>
						<div class="controls">
							${fns:getDictLabel(ccmWechatEvent.state, 'ccm_wechat_event_state', '')}
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">备注信息：</label>
						<div class="controls">
							<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
				</td>
			</tr>
		</table>

	</form:form>
</body>
</html>