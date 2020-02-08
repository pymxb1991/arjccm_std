<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>场所布控管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//关闭弹框事件
			$('#btnCancel').click(function() {
				parent.layer.close(parent.layerIndex);
			})
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
			
			var flag = "${ccmPlaceControl.id}"
			if(flag!=''){
				$('.hide1').show();
				$('.form-actions').hide();
				$('.help-inline').hide();
				$('.input-xlarge').attr("readonly","readonly");
				$('.displayedbuttons').attr("disabled","disabled");
				$('.input-medium').attr("disabled","disabled");
			}
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/place/ccmPlaceControl/form?id=${ccmPlaceControl.id}">场所布控<shiro:hasPermission name="place:ccmPlaceControl:edit">${not empty ccmPlaceControl.id?'详情':'添加'}</shiro:hasPermission><shiro:lacksPermission name="place:ccmPlaceControl:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ccmPlaceControl" action="${ctx}/place/ccmPlaceControl/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">场所名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">布控类型：</label>
			<div class="controls">
				<form:select path="controlType" class="input-xlarge required ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ccm_control_level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">布控时间：</label>
			<div class="controls">
				<input name="controltime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${ccmPlaceControl.controltime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">布控等级：</label>
			<div class="controls">
				<form:select path="grade" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ccm_control_level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">布控原因：</label>
			<div class="controls">
				<form:input path="reason" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">布控范围：</label>
			<div class="controls">
<%--				<form:input path="controlRange" htmlEscape="false" maxlength="64" class="input-xlarge "/>--%>
				<form:select path="controlRange" class="input-xlarge required">
					<form:option value="" label="请选择" />
					<form:options items="${fenceList}"
								  itemLabel="fenceName" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">布控设备：</label>
			<div class="controls">
				<form:input path="controlBy" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="place:ccmPlaceControl:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn btn-danger" type="button"
				   value="关闭" />
		</div>
	</form:form>
</body>
</html>