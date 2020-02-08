<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>重点人员签到记录管理</title>
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
		<li><a href="${ctx}/log/ccmLogImpPopSign/">重点人员签到记录列表</a></li>
		<li class="active"><a href="${ctx}/log/ccmLogImpPopSign/form?id=${ccmLogImpPopSign.id}">重点人员签到记录<shiro:hasPermission name="log:ccmLogImpPopSign:edit">${not empty ccmLogImpPopSign.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="log:ccmLogImpPopSign:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ccmLogImpPopSign" action="${ctx}/log/ccmLogImpPopSign/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group" >
			<label class="control-label">人员姓名：</label>
			<div class="controls">
				<input type="text" value="${ccmLogImpPopSign.peopleId.name}" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group" >
			<label class="control-label">关联表：</label>
			<div class="controls">
				<input type="text" value="${ccmLogImpPopSign.relevanceTable}" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group" >
			<label class="control-label">类型：</label>
			<div class="controls">
				<input type="text" value="${fns:getDictLabel(ccmLogImpPopSign.type, 'ccm_imp_people_sign_type', '')}" maxlength="100" class="input-xlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group" >
			<label class="control-label">签到有效状态：</label>
			<div class="controls">
				<form:select path="effectiveStatus" class="input-xlarge ">
					<form:options items="${fns:getDictList('sign_effective_status')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group" >
			<label class="control-label">签到异常状态：</label>
			<div class="controls">
				<input type="text" value="${fns:getDictLabel(ccmLogImpPopSign.errorStatus, 'sign_error_status', '')}" maxlength="100" class="input-xlarge" readonly="true"/>
			</div>
		</div>
		<img style="width: auto; height: 360px;position: absolute;top: 70px;left: 30%;" src="${ccmLogImpPopSign.pic}" onerror="this.src='${ctxStatic}/images/userinfobig.jpg'">
		<img style="width: auto; height: 360px;position: absolute;top: 70px;left: 70%;" src="${ccmLogImpPopSign.peopleId.images}" onerror="this.src='${ctxStatic}/images/userinfobig.jpg'">
		<div class="control-group">
			<label class="control-label">内容：</label>
			<div class="controls">
				<input type="text" value="${ccmLogImpPopSign.content}" htmlEscape="false" maxlength="255" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">时间：</label>
			<div class="controls">
				<input type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${ccmLogImpPopSign.tailTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					 readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="log:ccmLogImpPopSign:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>