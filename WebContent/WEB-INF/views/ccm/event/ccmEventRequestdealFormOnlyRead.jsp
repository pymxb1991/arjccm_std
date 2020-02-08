<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>请求处理管理</title>
<meta name="decorator" content="default" />
<script charset="utf-8" type="text/javascript" language="javascript"
	src="${ctxStatic}/ccm/validator/validatorBaseForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/event/ccmEventRequest/">请求登记列表</a></li>
		<shiro:hasPermission name="event:ccmEventRequest:edit">
			<li><a
				href="${ctx}/event/ccmEventRequest/form?id=${ccmEventRequestdeal.eventRequestId}">请求登记修改</a></li>
		</shiro:hasPermission>
		<!--请求处理编辑权限（请求登记附属信息） -->
		<shiro:hasPermission name="event:ccmEventRequestdeal:edit">
			<li class="active"><a
				href="${ctx}/event/ccmEventRequestdeal/readform?eventRequestId=${ccmEventRequestdeal.eventRequestId}">请求处理<shiro:hasPermission
					name="event:ccmEventRequestdeal:edit">${not empty ccmEventRequestdeal.id?'修改':'添加'}</shiro:hasPermission>
			</a></li>
		</shiro:hasPermission>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="ccmEventRequestdeal"
		action="${ctx}/event/ccmEventRequestdeal/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<form:hidden path="eventRequestId" />
		  <table class="table table-bordered table-hover">
            <tr>
                <td class="pad">处理单位：</td>
                <td class="pad"><form:input path="dealUnit" htmlEscape="false"
                        maxlength="200" class="input-xlarge "  readonly="true"/></td>
                <td class="pad">请求负责人：</td>
                <td class="pad"><form:input path="eventPrincipal"  readonly="true"
                        htmlEscape="false" maxlength="20" class="input-xlarge " />
                	<span class="help-inline"><font color="red">*</font> </span>
            </tr>
            <tr>
                <td class="pad">个人电话：</td>
                <td class="pad"><form:input path="telPerson" htmlEscape="false"
                        maxlength="30" class="input-xlarge "  readonly="true"/></td>
                <td class="pad">单位电话：</td>
                <td class="pad"><form:input path="telCom" htmlEscape="false"
                        maxlength="30" class="input-xlarge "  readonly="true" />
            </tr>
            <tr>
                <td class="pad">请求说明：</td>
                <td class="pad" colspan="3"><form:textarea path="remarks"
                        htmlEscape="false" rows="4" maxlength="2000" readonly="true"
                        class="input-xxlarge " /></td>
            </tr>
        </table>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>