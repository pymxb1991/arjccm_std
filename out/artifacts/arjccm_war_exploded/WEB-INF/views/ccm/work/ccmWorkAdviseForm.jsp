<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>意见建议管理</title>
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
	<link href="/arjccm/static/bootstrap/2.3.1/css_input/input_Custom.css" type="text/css" rel="stylesheet">
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a style="width: 140px;text-align:center" href="${ctx}/work/ccmWorkAdvise/">数据列表</a></li>
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/work/ccmWorkAdvise/form?id=${ccmWorkAdvise.id}">数据<shiro:hasPermission name="work:ccmWorkAdvise:edit">${not empty ccmWorkAdvise.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="work:ccmWorkAdvise:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="ccmWorkAdvise" action="${ctx}/work/ccmWorkAdvise/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<c:if test="${not empty ccmWorkAdvise.id}">
			<div class="control-group" style="padding-top: 8px">
				<label class="control-label" ><span class="help-inline"><font color="red">*</font> </span>时间：</label>
				<div class="controls">
					<input name="datas" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
						value="<fmt:formatDate value="${ccmWorkAdvise.datas}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						/>

				</div>
			</div>
			<div class="control-group">
				<label class="control-label">内容：</label>
				<div class="controls">
					<form:textarea path="details" htmlEscape="false" rows="6" maxlength="1000" class="input-xxlarge " readonly="true"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">备注信息：</label>
				<div class="controls">
					<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " readonly="true"/>
				</div>
			</div>
		</c:if>
		<c:if test="${empty ccmWorkAdvise.id}">
			<div class="control-group">
				<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>时间：</label>
				<div class="controls">
					<input name="datas" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
						value="<fmt:formatDate value="${ccmWorkAdvise.datas}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>

				</div>
			</div>
			<div class="control-group">
				<label class="control-label">内容：</label>
				<div class="controls">
					<form:textarea path="details" htmlEscape="false" rows="6" maxlength="1000" class="input-xxlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">备注信息：</label>
				<div class="controls">
					<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
				</div>
			</div>
		</c:if>
		<c:if test="${userSelfId != '1' and not empty ccmWorkAdvise.id}">
			<div class="control-group">
			</div>
			<div class="control-group">
				<label class="control-label">回复：</label>
				<div class="controls">
					<form:textarea path="reply" htmlEscape="false" rows="6" maxlength="1000" class="input-xxlarge " readonly="true"/>
				</div>
			</div>
		</c:if>
		<c:if test="${userSelfId eq '1' and not empty ccmWorkAdvise.id}">
			<div class="control-group">
			</div>
			<div class="control-group">
				<label class="control-label">回复：</label>
				<div class="controls">
					<form:textarea path="reply" htmlEscape="false" rows="6" maxlength="1000" class="input-xxlarge "/>
				</div>
			</div>
		</c:if>
		<div class="form-actions">
			<shiro:hasPermission name="work:ccmWorkAdvise:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>