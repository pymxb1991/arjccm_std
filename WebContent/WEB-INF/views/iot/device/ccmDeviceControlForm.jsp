<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>布控管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
	<link href="${ctxStatic}/form/css/formTable.css" rel="stylesheet" />
	<style>
		.hide1{
			display: none
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			//关闭弹框事件
			$('#btnCancel').click(function() {
				parent.layer.close(parent.layerIndex);
			})

			//获取url中的参数
			function getUrlParam(name) {
				var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
				var r = window.location.search.substr(1).match(reg);  //匹配目标参数
				if (r != null) return unescape(r[2]); return null; //返回参数值
			}
			var hide1=getUrlParam('hide1');

			if(hide1!=null){
				if(hide1=="true"){
					$('.hide1').show();
					$('.form-actions').hide();
					$('.help-inline').hide();
					$('.input-xlarge').attr("readonly","readonly");
					$('.displayedbuttons').attr("disabled","disabled");
					$('.input-medium').attr("disabled","disabled");
				}
			}else{
				$('.hide1').show();
			}

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
<%--	<ul class="nav nav-tabs">--%>
<%--		<li><a href="${ctx}/device/ccmDeviceControl/">探针布控列表</a></li>--%>
<%--		<li class="active"><a href="${ctx}/device/ccmDeviceControl/form?id=${ccmDeviceControl.id}">探针布控<shiro:hasPermission name="device:ccmDeviceControl:edit">${not empty ccmDeviceControl.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="device:ccmDeviceControl:edit">查看</shiro:lacksPermission></a></li>--%>
<%--	</ul><br/>--%>
	<form:form id="inputForm" modelAttribute="ccmDeviceControl" action="${ctx}/device/ccmDeviceControl/save" method="post" class="form-horizontal hide1">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
<%--        <div class="control-group">--%>
<%--            <label class="control-label">布控设备（1为WIFI，2为RFID）：</label>--%>
<%--            <div class="controls">--%>
                <form:hidden path="controlBy" value="${ccmDeviceControl.controlBy}"/>
<%--            </div>--%>
<%--        </div>--%>
	<table>
		<tr>
		<td><div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div></td>
		<td><div class="control-group">
			<label class="control-label">性别：</label>
			<div class="controls">
				<form:radiobuttons path="sex" items="${fns:getDictList('sex')}"
								   itemLabel="label" itemValue="value" htmlEscape="false" class="input-xlarge displayedbuttons required"/>
<%--				<form:input path="sex" htmlEscape="false" maxlength="2" class="input-xlarge required"/>--%>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div></td>
		</tr>
		<tr>
			<td><div class="control-group">
				<label class="control-label">证件号码：</label>
				<div class="controls">
					<form:input path="idCard" htmlEscape="false" maxlength="18" class="input-xlarge required ident0 card"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div></td>
			<td><div class="control-group">
				<label class="control-label">年龄：</label>
				<div class="controls">
					<form:input path="age" htmlEscape="false" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/" class="input-xlarge required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div></td>
		</tr>
		<tr>
		<td><div class="control-group">
			<label class="control-label">人员类型：</label>
			<div class="controls">
				<form:input path="peopleType" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div></td>
		<td><div class="control-group">
			<label class="control-label">布控类型：</label>
			<div class="controls">
				<form:select path="controlType" class="input-xlarge">
					<form:option value="" label="请选择" />
					<form:options items="${fns:getDictList('ccm_people_controller')}"
								  itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
<%--				<form:input path="controlType" htmlEscape="false" maxlength="20" class="input-xlarge "/>--%>
			</div>
		</div></td>
		</tr>
		<tr>
		<td><div class="control-group">
			<label class="control-label">布控开始时间：</label>
			<div class="controls">
				<input name="startDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${ccmDeviceControl.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div></td>
		<td><div class="control-group">
			<label class="control-label">布控结束时间：</label>
			<div class="controls">
				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${ccmDeviceControl.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div></td>
		</tr>
		<tr>
		<td><div class="control-group">
			<label class="control-label">布控等级：</label>
			<div class="controls">
				<form:select path="grade" class="input-xlarge">
					<form:option value="" label="请选择" />
					<form:options items="${fns:getDictList('ccm_control_level')}"
								  itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
<%--				<form:input path="grade" htmlEscape="false" maxlength="2" class="input-xlarge "/>--%>
			</div>
		</div></td>
		<td><div class="control-group">
			<label class="control-label">布控范围：</label>
			<div class="controls">
<%--				<form:input path="controlRange" htmlEscape="false" maxlength="64" class="input-xlarge required"/>--%>
				<form:select path="controlRange" class="input-xlarge required">
					<form:option value="" label="请选择" />
					<form:options items="${fenceList}"
								  itemLabel="fenceName" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div></td>
		</tr>
		<c:if test="${ccmDeviceControl.controlBy==3}"> 
		<tr>
			<td>
				<div class="control-group">
				<label class="control-label">设备编号：</label>
				<div class="controls">
					<form:input path="deviceCode" htmlEscape="false" maxlength="64" class="input-xlarge "/>
				</div>
				</div>
			</td>
		</tr>
		</c:if>
		<tr>
		<td colspan="2"><div class="control-group">
			<label class="control-label">布控原因：</label>
			<div class="controls">
				<form:textarea path="reason" htmlEscape="false" rows="4"
							   maxlength="255" class="input-xlarge " />
			</div>
		</div></td>
		</tr>
<%--		<tr>--%>
<%--		<td><div class="control-group">--%>
<%--			<label class="control-label">备注信息：</label>--%>
<%--			<div class="controls">--%>
<%--				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>--%>
<%--			</div>--%>
<%--		</div></td>--%>
<%--		<td><div class="control-group">--%>
<%--			<label class="control-label">是否为重点人员：</label>--%>
<%--			<div class="controls">--%>
<%--				<form:input path="peopleEmphasis" htmlEscape="false" maxlength="2" class="input-xlarge "/>--%>
<%--			</div>--%>
<%--		</div></td>--%>
<%--		</tr>--%>
	</table>
<%--		<div class="control-group">--%>
<%--			<label class="control-label">预留字段1：</label>--%>
<%--			<div class="controls">--%>
<%--				<form:input path="reserved1" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
<%--			</div>--%>
<%--		</div>--%>
<%--		<div class="control-group">--%>
<%--			<label class="control-label">预留字段2：</label>--%>
<%--			<div class="controls">--%>
<%--				<form:input path="reserved2" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
<%--			</div>--%>
<%--		</div>--%>
		<div class="form-actions">
			<shiro:hasPermission name="device:ccmDeviceControl:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn btn-danger" type="button"
				   value="关闭" />
		</div>
	</form:form>
</body>
</html>