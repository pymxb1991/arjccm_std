<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>城市部件管理</title>
	<meta name="decorator" content="technology"/>
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
		
		
		function saveForm() {
			var area = $("#areaId").val();
			var html1 = '<label for="" class="error">必选字段 <label>';
			//alert(officeId.length);
			if (area.length != 0) {
				$("#show1").html("");
			} else {
				$("#show1").html(html1);
			}

			if (area.length != 0) {
				$("#inputForm").submit();
			}

		}
	</script>
</head>
<body>
	<br/>
	<form:form modelAttribute="ccmCityComponents" action="${ctx}/citycomponents/ccmCityComponents/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		
		<table border="1px" style="border-color: #CCCCCC; border: 1px solid #CCCCCC; padding: 10px; width: 100%;">
			<tr>
				<td>
					<div>
						<label class="control-label">部件类型：</label>
						<div class="controls">
							<form:select disabled="true" path="type" class="input-xlarge required">
								<form:options items="${fns:getDictList('ccm_city_components_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">名称：</label>
						<div class="controls">
							<form:input path="name" readonly="true" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div>
						<label class="control-label">城市部件图片：</label>
						<div class="controls">
							<form:hidden id="images" path="imagePath" htmlEscape="false" maxlength="255" class="input-xlarge"/>
							<sys:ckfinder input="images" readonly="true" type="images" uploadPath="/photo/ChengShiBuJian" selectMultiple="false" maxWidth="160" maxHeight="240"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">编号：</label>
						<div class="controls">
							<form:input path="code" readonly="true" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">主管部门代码：</label>
						<div class="controls">
							<form:input path="competentDepartmentCode" readonly="true" htmlEscape="false" maxlength="6" class="input-xlarge number"/>
						</div>
					</div>
				</td>
			</tr>
						
			<tr>
				<td>
					<div>
						<label class="control-label">主管部门名称：</label>
						<div class="controls">
							<form:input path="competentDepartmentName" readonly="true" htmlEscape="false" maxlength="100" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">权属部门代码：</label>
						<div class="controls">
							<form:input path="ownershipDepartmentCode" readonly="true" htmlEscape="false" maxlength="6" class="input-xlarge number"/>
						</div>
					</div>
				</td>
			</tr>
						
			<tr>
				<td>
					<div>
						<label class="control-label">权属部门名称：</label>
						<div class="controls">
							<form:input path="ownershipDepartmentName" readonly="true" htmlEscape="false" maxlength="100" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">养护部门代码：</label>
						<div class="controls">
							<form:input path="maintainDepartmentCode" htmlEscape="false" readonly="true" maxlength="6" class="input-xlarge number"/>
						</div>
					</div>
				</td>
			</tr>
						
			<tr>
				<td>
					<div>
						<label class="control-label">养护部门名称：</label>
						<div class="controls">
							<form:input path="maintainDepartmentName" readonly="true" htmlEscape="false" maxlength="100" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">养护部门电话：</label>
						<div class="controls">
							<form:input path="maintainDepartmentTel" readonly="true" htmlEscape="false" maxlength="64" class="input-xlarge phone"/>
						</div>
					</div>
				</td>
			</tr>
						
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font></span>设备所在区域：</label>
						<div class="controls">
							<sys:treeselect disabled="disabled" id="area" name="area.id" value="${ccmCityComponents.area.id}" labelName="area.name" labelValue="${ccmCityComponents.area.name}"
								title="区域" url="/sys/area/treeData" cssClass="" allowClear="true" notAllowSelectParent="true"/>
							<span class="help-inline"><font color="red" id="show1"></font></span>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">详细地点：</label>
						<div class="controls">
							<form:input path="address" readonly="true" htmlEscape="false" maxlength="100" class="input-xlarge "/>
						</div>
					</div>
				</td>
			</tr>
						
			<tr>
				
				<td>
					<div>
						<label class="control-label">空间形态：</label>
						<div class="controls">
							<form:select disabled="true" path="spatialForm" class="input-xlarge ">
								<form:option value="" label=""/>
								<form:options items="${fns:getDictList('ccm_city_components_spatial')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">状态：</label>
						<div class="controls">
							<form:select path="status" disabled="true" class="input-xlarge required">
								<form:options items="${fns:getDictList('ccm_city_components_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
						
			<tr>
				<td>
					<div>
						<label class="control-label">坐标（面）：</label>
						<div class="controls">
							<form:input path="areaMap" readonly="true" htmlEscape="false" maxlength="2000" class="input-xlarge " disabled="true"/>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">坐标（点）：</label>
						<div class="controls">
							<form:input path="areaPoint" readonly="true" htmlEscape="false" maxlength="40" class="input-xlarge " disabled="true"/>
						</div>
					</div>
				</td>
			</tr>
						
			<tr>
				<td>
					<div>
						<label class="control-label">备注信息：</label>
						<div class="controls">
							<form:textarea path="remarks" readonly="true" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge "/>
						</div>
					</div>
				</td>
				<td>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>