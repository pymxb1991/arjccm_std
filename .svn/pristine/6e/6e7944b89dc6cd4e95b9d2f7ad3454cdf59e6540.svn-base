<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>司机管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/common/zztable.css" type="text/css"
	rel="stylesheet">
	<!-- 表格试表单css -->
	<link href="${ctxStatic}/common/zzformtable.css" type="text/css"
	rel="stylesheet">
	<script type="text/javascript">
		$(document).ready(function() {
			$(".trtop").css({
				"width" : "12.5%"
			});
			$("td[colspan='2']").css({
				"width" : "22.5%"
			});
			$('#btnSubmit').click(function(){
				$('#inputForm').submit();
			});
		});
	</script>
	<script type="text/javascript" src="${ctxStatic}/plm/car/plmCarDriverForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/car/plmCarDriver/">司机列表</a></li>
		<li class="active"><a href="${ctx}/car/plmCarDriver/form?id=${plmCarDriver.id}">司机<shiro:hasPermission name="car:plmCarDriver:edit">${not empty plmCarDriver.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="car:plmCarDriver:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" style="margin: 30px 200px;" modelAttribute="plmCarDriver" action="${ctx}/car/plmCarDriver/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<h2 style="margin-bottom: 30px;">驾驶员信息表</h2>
		<table id="table" class="table   table-condensed">
			<tr>
				<td class="trtop">姓名<font color="red">*</font></td>
				<td colspan="2"><form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge required"/></td>
				<td class="trtop">性别<font color="red">*</font></td>
				<td colspan="2"><form:radiobuttons path="sex" items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" /></td>
				<td class="trtop">人员照片</td>
				<td style="text-align: center;font-weight: 800">驾驶证照片</td>
			</tr>
			<tr>
				<td class="trtop">出生日期</td>
				<td colspan="2"><input name="birthday" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${plmCarDriver.birthday}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/></td>
				<td class="trtop">状态<font color="red">*</font></td>
				<td colspan="2">
					<form:select path="status" class="input-xlarge required">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('plm_car_driver_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</td>
				<td rowspan="5">
					<form:hidden id="photo" path="photo" htmlEscape="false" maxlength="256" class="input-xlarge" />
					<sys:ckfinder input="photo" type="images" uploadPath="/storage/plmCarDriver" selectMultiple="false" />				
				</td>
				<td rowspan="5">
					<form:hidden id="licensePic" path="licensePic" htmlEscape="false" maxlength="256" class="input-xlarge" />
					<sys:ckfinder input="licensePic" type="images" uploadPath="/car/plmCarDriver" selectMultiple="false" />
				</td>
			</tr>
			<tr>
				<td class="trtop">电话</td>
				<td colspan="2"><form:input path="tel" htmlEscape="false" maxlength="12" class="input-xlarge simplePhone"/></td>
				<td class="trtop">手机<font color="red">*</font></td>
				<td colspan="2"><form:input path="phone" htmlEscape="false" maxlength="11" class="input-xlarge required mobile"/></td>
			</tr>
			 <tr>
				<td class="trtop">准驾车型<font color="red">*</font></td>
				<td colspan="2">
					<form:select path="didType" class="input-xlarge required">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('plm_car_driver_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</td>
				<td class="trtop">驾驶证号<font color="red">*</font></td>
				<td colspan="2"><form:input path="didNum" htmlEscape="false" maxlength="20" class="input-xlarge number"/></td>
			</tr>
			<tr>
				<%-- <td class="trtop">交强险到期日期<font color="red">*</font></td>
				<td colspan="2">
					<input name="trafDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${plmCar.trafDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/></td> --%>
				<td class="trtop">领证日期</td>
				<td colspan="2">
					<input name="dexpDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${plmCarDriver.dexpDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				</td>
				<td class="trtop">驾驶证有效期<font color="red">*</font></td>
				<td colspan="2"><input name="didAvldate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${plmCarDriver.didAvldate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/></td>
			</tr>
			<tr>
				<td class="trtop">年审日期</td>
				<td colspan="5"><input name="didValdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${plmCarDriver.didValdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/></td>
			</tr>			
			<tr>
				<td class="trtop">备注</td>
				<td colspan="7"><form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/></td>
			</tr>
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="car:plmCarDriver:edit"><a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-ok"></i>保存</a>&nbsp;</shiro:hasPermission>
			<a id="btnCancel" class="btn" href="javascript:;" onclick="history.go(-1)" ><i class="icon-reply"></i>返回</a>
		</div>
	</form:form>
</body>
</html>