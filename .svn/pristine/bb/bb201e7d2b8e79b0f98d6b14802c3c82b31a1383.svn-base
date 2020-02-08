<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<html>
<head>
	<title>居民用户管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/layer-v3.1.1/layer/layer.js" type="text/javascript"></script>
	<script src="${ctxStatic}/ccm/event/js/ccmEventIncident.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			//关闭弹框事件
			$('#btnCancel').click(function() {
				parent.layer.close(parent.layerIndex);
			})
			$("#inputForm").validate({
				rules: {
					
				},
				messages: {
					
					confirmNewPassword: {equalTo: "输入与上面相同的密码"}
				},
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
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ccmFontUser" action="${ctx}/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="loginName"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">登录名：</label>
			<div class="controls">
				<form:input path="loginName" htmlEscape="false" maxlength="100" class="input-xlarge required userName" disabled="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<c:choose>
    		<c:when test="${ccmFontUser.updateStatus eq '1'}">
				<div class="control-group">
					<label class="control-label">密码:</label>
					<div class="controls">
						<input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3" class="input-xlarge ${empty ccmFontUser.id?'required':''}"/>
						<c:if test="${empty ccmFontUser.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
						<c:if test="${not empty ccmFontUser.id}"><span class="help-inline">若不修改密码，请留空。</span></c:if>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">确认密码:</label>
					<div class="controls">
						<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" equalTo="#newPassword" class="input-xlarge"/>
						<c:if test="${empty cmFontUser.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
					</div>
				</div>
				<form:hidden path="no"/>
				<form:hidden path="isNoVisable"/>
				<form:hidden path="areaComId.id"/>
				<form:hidden path="name"/>
				<form:hidden path="isNameVisable"/>
				<form:hidden path="email"/>
				<form:hidden path="phone"/>
				<form:hidden path="mobile"/>
				<form:hidden path="isMobileVisable"/>
				<form:hidden path="photo"/>
				<form:hidden path="loginFlag"/>
				<form:hidden path="remarks"/>
			</c:when>
     		<c:otherwise>
     			<form:hidden path="password"/>
				<div class="control-group">
					<label class="control-label">身份证号：</label>
					<div class="controls">
						<form:input path="no" htmlEscape="false" maxlength="100" class="input-xlarge "/>
					</div>
					<div class="controls" style="padding-top:10px;">
						<form:radiobuttons path="isNoVisable" items="${fns:getDictList('yes_no_visible')}" 
							itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label">归属区域:</label>
					<div class="controls">
					             <sys:treeselectWebsite id="area" name="areaComId.id" value="${ccmFontUser.areaComId.id}" labelName="areaComId.name" labelValue="${ccmFontUser.areaComId.name}"
					  title="区域" url="/tag/treeData" cssClass=""/>
					  <span class="help-inline"><font color="red" id="show1">*</font></span>
					</div>
		  	 	</div>
				<div class="control-group">
					<label class="control-label">姓名：</label>
					<div class="controls">
						<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
						<span class="help-inline"><font color="red">*</font> </span>
					</div>
					<div class="controls" style="padding-top:10px;">
						<form:radiobuttons path="isNameVisable" items="${fns:getDictList('yes_no_visible')}" 
							itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">邮箱：</label>
					<div class="controls">
						<form:input path="email" htmlEscape="false" maxlength="200" class="input-xlarge email"/>
					</div>
					<div class="controls" style="padding-top:10px;">
						<form:radiobuttons path="isEmailVisable" items="${fns:getDictList('yes_no_visible')}" 
							itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">电话：</label>
					<div class="controls">
						<form:input path="phone" htmlEscape="false" maxlength="200" class="input-xlarge "/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">手机：</label>
					<div class="controls">
						<form:input path="mobile" htmlEscape="false" maxlength="200" class="input-xlarge phone"/>
					</div>
					<div class="controls" style="padding-top:10px;">
						<form:radiobuttons path="isMobileVisable" items="${fns:getDictList('yes_no_visible')}" 
							itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">用户头像：</label>
					<div class="controls">
						<form:hidden id="photo" path="photo" htmlEscape="false" maxlength="1000" class="input-xlarge" />
						<sys:ckfinder input="photo" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100" />
					</div> 
				</div>
				<div class="control-group">
					<label class="control-label">审核状态：</label>
					<div class="controls">
					<form:select path="loginFlag" class="input-xlarge">
							<form:option value="" label="请选择"/>
							<form:options items="${fns:getDictList('font_user_loginFlag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
						
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">备注信息：</label>
					<div class="controls">
						<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge "/>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn btn-danger" type="button" value="关 闭"/>
		</div>
	</form:form>
</body>
</html>