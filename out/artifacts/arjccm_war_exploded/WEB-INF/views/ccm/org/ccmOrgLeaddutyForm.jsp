<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>综治领导责任制管理</title>
	<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
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
		<li><a href="${ctx}/org/ccmOrgLeadduty/">数据列表</a></li>
		<li class="active"><a href="${ctx}/org/ccmOrgLeadduty/form?id=${ccmOrgLeadduty.id}">数据<shiro:hasPermission name="org:ccmOrgLeadduty:edit">${not empty ccmOrgLeadduty.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="org:ccmOrgLeadduty:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ccmOrgLeadduty" action="${ctx}/org/ccmOrgLeadduty/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		
		<table border="1px"
			style="border-color: #CCCCCC; border: 1px solid #CCCCCC; padding: 10px;  width: 80%; ">
			
			<div class="control-group">
				<label class="control-label">被实施地区：</label>
				<div class="controls">
					<form:input path="implementedAdd" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				  	<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">被实施地区层级：</label>
				<div class="controls">
					<form:select path="implementedAddScale" class="input-xlarge ">
						<form:options items="${fns:getDictList('ccm_ply_rat')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				    </form:select>
				  	<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">实施主体名称：</label>
				<div class="controls">
					<form:input path="implementName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">实施主体层级：</label>
				<div class="controls">
					<form:select path="implementScale" class="input-xlarge ">
						<form:options items="${fns:getDictList('ccm_ply_rat')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			    	</form:select>
				  	<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">政策种类：</label>
				<div class="controls">
					<form:select path="policyType" class="input-xlarge ">
						<form:options items="${fns:getDictList('ccm_pol_var')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				    </form:select>
				  	<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">备注信息：</label>
				<div class="controls">
					<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="255" class="input-xxlarge " />
				</div>
			</div>
	     </table>	
		
		<div class="form-actions">
			<shiro:hasPermission name="org:ccmOrgLeadduty:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>