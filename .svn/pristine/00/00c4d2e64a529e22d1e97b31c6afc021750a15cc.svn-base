<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>民政工作管理管理</title>
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
	<li><a style="width: 140px;text-align:center" href="${ctx}/service/ccmServiceCivil/">数据列表</a></li>
	<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/service/ccmServiceCivil/form?id=${ccmServiceCivil.id}">数据<shiro:hasPermission name="service:ccmServiceCivil:edit">${not empty ccmServiceCivil.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="service:ccmServiceCivil:edit">查看</shiro:lacksPermission></a></li>
</ul>
<form:form id="inputForm" modelAttribute="ccmServiceCivil" action="${ctx}/service/ccmServiceCivil/save" method="post" class="form-horizontal">
	<form:hidden path="id"/>
	<sys:message content="${message}"/>
	<table>
		<tr>
			<td>
				<div class="control-group" style="padding-top: 8px">
					<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>工作类型：</label>
					<div class="controls">
						<form:select path="type" class="input-xlarge required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('ccm_service_civil_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>

					</div>
				</div>
			</td>
			<td>
				<div class="control-group">
					<label class="control-label">时间：</label>
					<div class="controls">
						<input name="times" type="text" readonly="readonly"  maxlength="20" class="input-medium Wdate required"
							   value="<fmt:formatDate value="${ccmServiceCivil.times}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							   onclick="WdatePicker({maxDate: '%y-%M-%d',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>

						<span id="tshi" class="help-inline" style="display: none"><font color="red">日期不能大于当前日期</font> </span>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div class="control-group">
					<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>受理人：</label>
					<div class="controls">
						<form:input path="receiver" htmlEscape="false" maxlength="64" class="input-xlarge required"/>

					</div>
				</div>
			</td>
			<td>
				<div class="control-group">
					<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>被受理人：</label>
					<div class="controls">
						<form:input path="gods" htmlEscape="false" maxlength="64" class="input-xlarge required"/>

					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div class="control-group">
					<label class="control-label">服务场所：</label>
					<div class="controls">
						<form:textarea path="adds" htmlEscape="false" rows="2" maxlength="256" class="input-xxlarge "/>
					</div>
				</div>
			</td>
			<td>
				<div class="control-group">
					<label class="control-label">工作内容：</label>
					<div class="controls">
						<form:textarea path="details" htmlEscape="false" rows="12" maxlength="1024" class="input-xxlarge "/>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div class="control-group">
					<label class="control-label">备注信息：</label>
					<div class="controls">
						<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
					</div>
				</div>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>
				<div class="control-group">
					<label class="control-label">附件：</label>
					<div class="controls">
						<form:hidden id="file" path="file" htmlEscape="false" maxlength="256" class="input-xlarge"/>
						<sys:ckfinder input="file" type="files" uploadPath="/service/ccmServiceCivil" selectMultiple="true"/>
					</div>
				</div>
			</td>
			<td></td>
		</tr>
	</table>








	<div class="form-actions">
		<shiro:hasPermission name="service:ccmServiceCivil:edit"><input id="btnSubmit" class="btn btn-primary"  type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
</form:form>
</body>
</html>