<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>车辆管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/common/zztable.css" type="text/css"
	rel="stylesheet">
<!-- 表格试表单css -->
<link href="${ctxStatic}/common/zzformtable.css" type="text/css"
	rel="stylesheet">
<script type="text/javascript">
	$(document).ready(function() {
			jQuery.validator.methods.compareDate = function(value, element, param) {
				var startDate = $(param).val();//补全yyyy-MM-dd HH:mm:ss格式

				var date1 = new Date(Date.parse(startDate.replace(/\-/g,"/")));
				var date2 = new Date(Date.parse(value.replace(/\-/g,"/")));
				return date1 < date2;
			};
				//$("#name").focus();
				$('#btnSubmit').click(function(){
					$('#inputForm').submit();
				});
			});
</script>
<script type="text/javascript" src="${ctxStatic}/plm/car/plmCarForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/car/plmCar/">车辆列表</a></li>
		<li class="active"><a href="${ctx}/car/plmCar/form?id=${plmCar.id}">车辆<shiro:hasPermission name="car:plmCar:edit">${not empty plmCar.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="car:plmCar:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="plmCar" action="${ctx}/car/plmCar/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<h2 style="margin-bottom: 30px;">车辆信息</h2>
		
		
		<table id="table" class="table   table-condensed">
			<tr>
				<td class="trtop">车牌号<font color="red">*</font></td>
				<td colspan="2"><form:input path="vehicle" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
			</div></td>
				<td class="trtop">状态<font color="red">*</font></td>
				<td colspan="2"><form:select path="vehicleStatus" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('plm_car_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select></td>
				<td class="trtop">品牌<font color="red">*</font></td>
				<td colspan="2"><form:input path="brand" htmlEscape="false" maxlength="64" class="input-xlarge required"/></td>
				<td style="text-align: center;font-weight: 800" colspan="2">照片：</td>
			</tr>
			<tr>
				<td class="trtop">型号<font color="red">*</font></td>
				<td colspan="2"><form:input path="vmodel" htmlEscape="false" maxlength="64" class="input-xlarge required"/></td>
				<td class="trtop">车型<font color="red">*</font></td>
				<td colspan="2"><form:select path="vtype" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('plm_car_vtype')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select></td>
				<td class="trtop">发动机号<font color="red">*</font></td>
				<td colspan="2"><form:input path="engineNum" htmlEscape="false" maxlength="64" class="input-xlarge required"/></td>
				<td colspan="2" rowspan="6"><form:hidden id="photo" path="photo" htmlEscape="false" maxlength="256" class="input-xlarge" />
				<sys:ckfinder input="photo" type="images" uploadPath="/car/car" selectMultiple="false" /></td>
			</tr>
			<tr>
				<td class="trtop">出厂日期<font color="red">*</font></td>
				<td colspan="2"><input id="productDate" name="productDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${plmCar.productDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/></td>
				<td class="trtop">核载人数<font color="red">*</font></td>
				<td colspan="2"><form:input path="loadNum" htmlEscape="false" maxlength="3" class="input-xlarge digits required"/></td>
				<td class="trtop">载重量(kg)</td>
				<td colspan="2"><form:input path="loadCapacity" htmlEscape="false" maxlength="6" class="input-xlarge  digits"/></td>
			</tr>
			<tr>
				<td class="trtop">购车日期<font color="red">*</font></td>
				<td colspan="2"><input id="buyDate" name="buyDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${plmCar.buyDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/></td>
				<td class="trtop">年检日期<font color="red">*</font></td>
				<td colspan="2"><input name="annualDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${plmCar.annualDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/></td>
				<td class="trtop">所属部门</td>	
				<td colspan="2">
				<sys:treeselect id="office" name="office.id" value="${plmCar.office.id}" labelName="office.name" labelValue="${plmCar.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" notAllowSelectParent="false" isAll="true"/>
				</td>
			</tr>
			<tr>
			<td class="trtop">下次年检日期<font color="red">*</font></td>
				<td colspan="2"><input id="inspectionDate" name="inspectionDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${plmCar.inspectionDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/></td>
				
				<td class="trtop">交强险到期日期<font color="red">*</font></td>
				<td colspan="2"><input name="trafDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${plmCar.trafDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/></td>
				
				<td class="trtop">保险种类<font color="red">*</font></td>
				<td colspan="2"><form:select path="insuranceType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('plm_car_insurance_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select></td>
			</tr>
			<tr>
				<td class="trtop">商业险到期时间</td>
				<td colspan="2"><input name="commDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${plmCar.commDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/></td>
				<td class="trtop">行驶里程</td>
				<td colspan="2"><form:input path="mileage" htmlEscape="false" maxlength="8" class="input-xlarge  digits"/></td>
				<td class="trtop">上次保养里程</td>
				<td colspan="2"><form:input path="maintainMil" htmlEscape="false" maxlength="8" class="input-xlarge  digits"/></td>
			</tr>
			<tr>
				<td class="trtop">状况描述</td>
				<td colspan="2"><form:input path="stateDescription" htmlEscape="false" maxlength="1000" class="input-xlarge "/></td>
				<td class="trtop">车辆标志</td>
				<td colspan="2"><form:checkboxes path="carFlagList" items="${fns:getDictList('plm_car_flag')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/></td>
				<td class="trtop">车辆特征</td>
				<td colspan="2"><form:checkboxes path="carFeatureList" items="${fns:getDictList('plm_car_featrue')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/></td>
			</tr>
			<tr>
				<td class="trtop">备注</td>
				<td colspan="11"><form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/></td>
			</tr>
		</table>
		
		
		
		<div class="form-actions">
			<shiro:hasPermission name="car:plmCar:edit"><a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-ok"></i>保存</a>&nbsp;</shiro:hasPermission>
			<a id="btnCancel" class="btn" href="javascript:;" onclick="history.go(-1)" ><i class="icon-reply"></i>返回</a>
		</div>
	</form:form>
</body>
</html>