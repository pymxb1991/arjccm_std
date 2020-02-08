<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>特殊车辆服务管理管理</title>
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
			$("td").css({"padding":"8px"});
			$("td").css({"border":"1px dashed #CCCCCC"});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/citycomponents/ccmCityCar/">数据列表</a></li>
		<li class="active"><a href="${ctx}/citycomponents/ccmCityCar/form?id=${ccmCityCar.id}">数据<shiro:hasPermission name="citycomponents:ccmCityCar:edit">${not empty ccmCityCar.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="citycomponents:ccmCityCar:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ccmCityCar" action="${ctx}/citycomponents/ccmCityCar/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<table border="1px"
			style="border-color: #CCCCCC; border: 1px solid #CCCCCC; padding: 10px; width: 100%;">
			
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">车牌号码：</label>
						<div class="controls">
							<form:input path="number" htmlEscape="false" maxlength="64" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">车身颜色：</label>
						<div class="controls">
							<form:select path="colorCar" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('color_car')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">车辆类型：</label>
						<div class="controls">
							<form:select path="type" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('ccm_city_car_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">车牌颜色：</label>
						<div class="controls">
							<form:select path="colorCarPlate" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('color_car_plate')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
			</tr>	
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">特种运输类型：</label>
						<div class="controls">
							<form:select path="transportType" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('ccm_city_car_transport_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">车辆所属企业：</label>
						<div class="controls">
							<form:input path="carCompany" htmlEscape="false" maxlength="64" class="input-xlarge "/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">企业负责人：</label>
						<div class="controls">
							<form:input path="principle" htmlEscape="false" maxlength="64" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">企业负责人联系方式：</label>
						<div class="controls">
							<form:input path="principleTel" htmlEscape="false" maxlength="18" class="input-xlarge "/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">车主姓名：</label>
						<div class="controls">
							<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">车主性别：</label>
						<div class="controls">
							<form:radiobuttons path="sex" items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">车主驾驶证号码：</label>
						<div class="controls">
							<form:input path="drivingLicence" htmlEscape="false" maxlength="18" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">车主身份证号码：</label>
						<div class="controls">
							<form:input path="ident" htmlEscape="false" maxlength="18" class="input-xlarge "/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">联系电话：</label>
						<div class="controls">
							<form:input path="tel" htmlEscape="false" maxlength="18" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td rowspan="8">
					<div class="control-group">
						<label class="control-label">车辆照片：</label>
						<div class="controls">
							<form:hidden id="images" path="images" htmlEscape="false" maxlength="255" class="input-xlarge"/>
							<sys:ckfinder input="images" type="images" uploadPath="/citycomponents/特殊车辆管理" selectMultiple="false" maxWidth="240" maxHeight="360"/>	
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
			</tr>
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="citycomponents:ccmCityCar:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>