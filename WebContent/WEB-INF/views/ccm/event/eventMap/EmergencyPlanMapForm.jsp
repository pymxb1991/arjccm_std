<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>应急预案查看</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
			    $("#btnMenu").hide();				 
			    }
			);
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<%-- <li><a href="${ctx}/event/ccmEmergencyPlan/">应急预案列表</a></li> --%>
		<li class="active"><a href="##">应急预案查看</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="ccmEmergencyPlan"
		action="${ctx}/event/ccmEmergencyPlan/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">预案名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">案（事）件分级：</label>
			<div class="controls">
				<form:select path="eventScale" class="input-xlarge ">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('ccm_case_grad')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">案（事）件类型：</label>
			<div class="controls">
				<form:select path="eventType" class="input-xlarge ">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('ccm_case_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">应急领导小组：</label>
			<div class="controls">
				<form:textarea path="leader" htmlEscape="false" rows="4"
					maxlength="1000" class="input-xxlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作原则：</label>
			<div class="controls">
				<form:textarea path="principle" htmlEscape="false" rows="6"
					maxlength="1000" class="input-xxlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">应急处理措施：</label>
			<div class="controls">
				<form:textarea path="step" htmlEscape="false" rows="10"
					maxlength="1000" class="input-xxlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注说明：</label>
			<div class="controls">
				<form:textarea path="description" htmlEscape="false" rows="4"
					maxlength="1000" class="input-xxlarge " />
			</div>
		</div>
		<div class="control-group">
            <label class="control-label">附件信息：</label>
            <div class="controls">
                <form:hidden id="images" path="images" htmlEscape="false" maxlength="255" class="input-xlarge"/>
                <sys:ckfinder input="images" type="files" uploadPath="/event/ccmEmergencyPlan" readonly="true"/>
            </div>
        </div>
		
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>