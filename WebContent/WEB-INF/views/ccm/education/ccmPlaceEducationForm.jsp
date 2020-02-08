<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>文化教育场所管理</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
<link href="${ctxStatic}/form/css/formTable.css" rel="stylesheet" />
<script type="text/javascript">
	$(document).ready(
			function() {

				//关闭弹框事件
				$('#btnCancel').click(function() {
					parent.layer.close(parent.layerIndex);
				})
				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										$("#btnSubmit").attr("disabled", true);
					loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#btnSubmit").removeAttr('disabled');
					$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
								});
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
	<ul class="nav nav-tabs">
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="ccmPlaceEducation"
		action="${ctx}/education/ccmPlaceEducation/save/01" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<h4 class="tableNamefirst">基本信息</h4>
		<table>
			<tr>
				<td style="width: 50%;"><label class="control-label">场所名称：</label>
					<div class="controls">
						<form:input path="ccmBasePlace.placeName" htmlEscape="false"
							maxlength="255" class="input-xlarge required" />
						<span class="help-inline"><font color="red">*</font>
					</div></td>
				<td><label class="control-label">场所用途：</label>
					<div class="controls">
						<form:input path="ccmBasePlace.placeUse" htmlEscape="false"
							maxlength="255" class="input-xlarge " />
					</div></td>
			</tr>
			<tr>
				<td><label class="control-label">主管单位名称：</label>
					<div class="controls">
						<form:input path="ccmBasePlace.governingBodyName"
							htmlEscape="false" maxlength="255" class="input-xlarge " />
					</div></td>
				<td><label class="control-label">场所面积（平方米）：</label>
					<div class="controls">
						<form:input path="ccmBasePlace.placeArea" htmlEscape="false"
							maxlength="255" class="input-xlarge " type="number"/>
					</div></td>
			</tr>
			<tr>
				<td><label class="control-label">关联组织机构：</label>
					<div class="controls">
						<form:select path="ccmBasePlace.relevanceOrg" class="input-xlarge">
							<form:option value="" label="无关联" />
							<form:options items="${fns:getDictList('ccm_buss_cate')}"
								itemLabel="label" itemValue="value" htmlEscape="false"
								class="required" />
						</form:select>
						<span class="help-inline"><font color="red">*</font>
					</div></td>
				<td><label class="control-label">工作人员数量（人）：</label>
					<div class="controls">
						<form:input path="ccmBasePlace.workerNumber" htmlEscape="false"
							maxlength="255" class="input-xlarge " type="number"/>
					</div></td>
			</tr>
			<tr>
				<td><label class="control-label">负责人姓名：</label>
					<div class="controls">
						<form:input path="ccmBasePlace.leaderName" htmlEscape="false"
							maxlength="255" class="input-xlarge required" />
						<span class="help-inline"><font color="red">*</font>
					</div></td>
				<td><label class="control-label">负责人身份证号码：</label>
					<div class="controls">
						<form:input path="ccmBasePlace.leaderIdCard" htmlEscape="false"
							maxlength="18" class="input-xlarge ident1 card required" />
						<span class="help-inline"><font color="red">*</font>
					</div></td>
			</tr>
			<tr>
				<td><label class="control-label">负责人联系方式：</label>
					<div class="controls">
						<form:input path="ccmBasePlace.leaderContact" htmlEscape="false"
							maxlength="11" class="input-xlarge telephone0 phone required" />
						<span class="help-inline"><font color="red">*</font>
					</div></td>
				<td><label class="control-label">地址：</label>
					<div class="controls">
						<form:input path="ccmBasePlace.address" htmlEscape="false"
							maxlength="255" class="input-xlarge " />
					</div></td>
			</tr>
			<tr>
				<td><label class="control-label" style="display: none">场所类型：</label>
					<div class="controls" style="display: none">
						<form:input path="ccmBasePlace.placeType" style="display: none"
							htmlEscape="false" maxlength="255" class="input-xlarge " />
					</div> <label class="control-label"><span class="help-inline"><font color="red">*</font></span>所属网格：</label>
					<div class="controls">
						<sys:treeselect id="area"
							name="ccmBasePlace.administrativeDivision"
							value="${ccmPlaceEducation.ccmBasePlace.administrativeDivision}"
							labelName=""
							labelValue="${ccmPlaceEducation.ccmBasePlace.area.name}"
							title="网格" url="/sys/area/treeData" allowClear="true"
							notAllowSelectParent="true" cssClass=""/>
							<span class="help-inline"><font color="red" id="show1"></font></span>
					</div></td>
				<td><label class="control-label">成立时间：</label>
					<div class="controls">
						<input name="ccmBasePlace.createTime" type="text"
							readonly="readonly" maxlength="20"
							class="input-medium Wdate required"
							value="<fmt:formatDate value="${ccmPlaceEducation.ccmBasePlace.createTime}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						<span class="help-inline"><font color="red">*</font>
					</div></td>
			</tr>
			<tr>
				<td colspan="2"><label class="control-label">重点场所：</label>
					<div class="controls">
						<form:checkboxes path="keyPointList"
							items="${fns:getDictList('ccm_place_important')}"
							itemLabel="label" itemValue="value" htmlEscape="false"
							class="" />
					</div></td>
			</tr>
			<tr>
				<td colspan="2" rowspan="2"><label class="control-label">场所图片：</label>
					<div class="controls">
						<form:hidden id="images" path="ccmBasePlace.placePicture"
							htmlEscape="false" maxlength="255" class="input-xlarge" />
						<sys:ckfinder input="images" type="images" uploadPath="/photo"
							selectMultiple="false" maxWidth="240" maxHeight="360" />
					</div></td>
			</tr>
		</table>

		<h4 class="tableName">学校信息</h4>
		<table>
			<tr>
				<td><label class="control-label">学校标识码：</label>
					<div class="controls">
						<form:input path="schoolCode" htmlEscape="false" maxlength="255"
							class="input-xlarge required" />
						<span class="help-inline"><font color="red">*</font>
					</div></td>
				<td><label class="control-label">学校官网：</label>
					<div class="controls">
						<form:input path="schoolNet" htmlEscape="false" maxlength="255"
							class="input-xlarge required" />
						<span class="help-inline"><font color="red">*</font>
					</div></td>
			</tr>
			<tr>
				<td><label class="control-label">学校级别：</label>
					<div class="controls">
						<form:select path="schoolRank" class="input-xlarge required"
							items="${fns:getDictList('ccm_schol_type')}" itemLabel="label"
							itemValue="value" htmlEscape="false">
						</form:select>
						<span class="help-inline"><font color="red">*</font>
					</div></td>
				<td><label class="control-label">办学类型：</label>
					<div class="controls">
						<form:select path="schoolType" class="input-xlarge required"
							items="${fns:getDictList('ccm_school_property')}"
							itemLabel="label" itemValue="value" htmlEscape="false">
						</form:select>
						<span class="help-inline"><font color="red">*</font>
					</div></td>
			</tr>
			<tr>
				<td><label class="control-label">办公电话：</label>
					<div class="controls">
						<form:input path="schoolPhone" htmlEscape="false" maxlength="255"
							class="input-xlarge " />
					</div></td>
				<td><label class="control-label">传真号：</label>
					<div class="controls">
						<form:input path="faxNumber" htmlEscape="false" maxlength="11"
							class="input-xlarge " />
					</div></td>
			</tr>
			<tr>
				<td><label class="control-label">行政管理员名字：</label>
					<div class="controls">
						<form:input path="managerName" htmlEscape="false" maxlength="255"
							class="input-xlarge " />
					</div></td>
				<td><label class="control-label">行政管理员联系方式：</label>
					<div class="controls">
						<form:input path="managerPhoneNumber" htmlEscape="false"
							maxlength="255" class="input-xlarge required " />
						<span class="help-inline"><font color="red">*</font>
					</div></td>
			</tr>
			<tr>
				<td colspan="2"><label class="control-label">备注信息：</label>
					<div class="controls">
						<form:textarea path="remarks" htmlEscape="false" rows="2"
							maxlength="255" class="input-xxlarge" />
					</div></td>
			</tr>
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="education:ccmPlaceEducation:edit">
				<input id="btnSubmit" class="btn btn-primary" onclick="saveForm()" type="button"
					value="保 存" />
				<input id="btnCancel" class="btn btn-danger" type="button"
					value="关闭" />&nbsp;</shiro:hasPermission>
		</div>
	</form:form>
</body>
</html>