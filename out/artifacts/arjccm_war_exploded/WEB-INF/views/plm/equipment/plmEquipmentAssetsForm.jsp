<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>装备物资管理</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/common/zztable.css" type="text/css"
	rel="stylesheet">
<!-- 表格试表单css -->
<link href="${ctxStatic}/common/zzformtable.css" type="text/css"
	rel="stylesheet">
<script type="text/javascript">
	$(document).ready(
			function() {
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
				$("td").css({
					"padding" : "1%"
				});
				
			});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/equipment/plmEquipmentAssets/">装备列表</a></li>
		<li class="active"><a
			href="${ctx}/equipment/plmEquipmentAssets/form?id=${plmEquipment.id}">装备信息</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="plmEquipment" style="margin: 0px 260px;"
		action="${ctx}/equipment/plmEquipmentAssets/" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<h2>装备信息</h2>
		<table  id="table" class="table   table-condensed"
			style="table-layout: fixed;">
			<tr>
				<td class="trtop">物资名称</td>
				<td colspan="2">${plmEquipment.name }</td>
				<td class="trtop">物资编号：</td>
				<td colspan="2">${plmEquipment.code}</td>
				<td  style="text-align: center; ">图片</td>
			</tr>
			<tr>
				<td class="trtop">规格型号：</td>
				<td colspan="2">${plmEquipment.spec}</td>
				<td class="trtop">物资类别：</td>
				<td colspan="2">${fns:getDictLabel(plmEquipment.typeChild, 'plm_equipment_type_child', '')}</td>
				<td rowspan="5" style="text-align: center;"><form:hidden id="imgUrl" path="imgUrl"
						htmlEscape="false" maxlength="256" class="input-xlarge" /> <sys:ckfinder
						input="imgUrl" type="images" uploadPath="/storage/plmEquipment"
						selectMultiple="true" /></td>
			</tr>
			<tr>
				<td class="trtop">物资数量：</td>
				<td colspan="2">${plmEquipment.erialNumber}</td>
				<td class="trtop">计量单位：</td>
				<td colspan="2">${plmEquipment.unit}</td>
			</tr>
			<tr>
				<td class="trtop">生产批次：</td>
				<td colspan="2">${plmEquipment.productionBatch}</td>
				<td class="trtop">生产日期：</td>
				<td colspan="2"><fmt:formatDate value="${plmEquipment.productionDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>
				<td class="trtop">质保期限：</td>
				<td colspan="2"><fmt:formatDate value="${plmEquipment.guaranteePeriod}" pattern="yyyy-MM-dd "/></td>
				<td class="trtop">保质期：</td>
				<td colspan="2">${plmEquipment.expirationDate}</td>
			</tr>
			<tr>
				<td class="trtop">使用年限：</td>
				<td colspan="2"><fmt:formatDate value="${plmEquipment.durableYears}" pattern="yyyy-MM-dd "/></td>
				<td class="trtop">状态：</td>
				<td colspan="2">${fns:getDictLabel(plmEquipment.type,'plm_equipment_status','')}</td>
			</tr>
			<tr>
				<td class="trtop">备注：</td>
				<td colspan="6"><form:textarea path="remarks"
						htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " /></td>
			</tr>
		</table>

		<div class="form-actions">
			<a id="btnCancel" class="btn" href="javascript:;" onclick="history.go(-1)" ><i class="icon-reply"></i>返回</a>
		</div>
	</form:form>
</body>
</html>