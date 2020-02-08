<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用车费用记录管理</title>
	<meta name="decorator" content="default"/>
	<link type="text/css" href="${ctxStatic}/common/zztable.css" rel="stylesheet">
	<link type="text/css" href="${ctxStatic}/common/zzformtable.css" rel="stylesheet">	
	<script type="text/javascript" src="${ctxStatic}/plm/car/plmCarUseForm.js"></script> 
 	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function(){
				$('#inputForm').submit();
			});
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
		<li><a href="${ctx}/car/plmCarUseSpend/">用车费用记录列表</a></li>
		<li class="active"><a href="${ctx}/car/plmCarUseSpend/form?id=${plmCarUseSpend.id}">用车费用记录<shiro:hasPermission name="car:plmCarUseSpend:edit">${not empty plmCarUseSpend.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="car:plmCarUseSpend:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" style="margin: 30px 200px;" modelAttribute="plmCarUseSpend" action="${ctx}/car/plmCarUseSpend/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="carUseId"/>
		
		<sys:message content="${message}"/>		
		<h2 style="margin-bottom: 20px">用车费用记录单</h2>	
		<table id="table" class="table table-condensed">
			<tr>
				<td class="trtop" colspan="2" style="width: 20%">发生时间<font color="red">*</font></td>
				<td colspan="2" style="width: 30%">
					<fmt:formatDate value="${plmCarUseSpend.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td class="trtop" colspan="2" style="width: 20%">车辆<font color="red">*</font></td>
				<td colspan="2" style="width: 30%">${plmCarUseSpend.car.vehicle}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="2" style="width: 20%">司机<font color="red">*</font></td>
				<td colspan="2" style="width: 30%">
				          <c:if test="${not empty plmCarUseSpend.driver.id}" var="e">			
					       ${plmCarUseSpend.driver.name}
				         </c:if>
					     <c:if test="${!e}">
						     领用人自驾				     	
						 </c:if>			
				</td>
				<td class="trtop" colspan="2" style="width: 20%">领用人<font color="red">*</font></td>
				<td colspan="2" style="width: 30%">${plmCarUseSpend.use.name}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="2" style="width: 20%">费用类型<font color="red">*</font></td>
				<td colspan="6" style="width: 30%">${fns:getDictLabel(plmCarUseSpend.type, 'plm_car_use_type', '')}</td>
			</tr>	
			<c:if test="${plmCarUseSpend.type eq '01'}">
				<tr>
					<td class="trtop" colspan="2" style="width: 20%">是否损坏</td>
					<td colspan="2" style="width: 30%">
						<form:radiobuttons path="isDamaged" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
					</td>
					<td class="trtop" colspan="2" style="width: 20%">行驶里程(km)</td>
					<td colspan="2" style="width: 30%">
						<form:input path="mildage" htmlEscape="false" class="input-xlarge znumber lrunlv decimal number"/>
					</td>
				</tr>
				<tr>
					<td class="trtop" colspan="2" style="width: 20%">加油费(元)</td>
					<td colspan="2" style="width: 30%">
						<form:input id="oilFee" path="oilFee" htmlEscape="false" class="input-xlarge amount number"/>
					</td>
					<td class="trtop" colspan="2" style="width: 20%">过路费(元)</td>
					<td colspan="2" style="width: 30%">
						<form:input id="tollFee" path="tollFee" htmlEscape="false" class="input-xlarge amount number"/>
					</td>
				</tr>
				<tr>
					<td class="trtop" colspan="2" style="width: 20%">停车费(元)</td>
					<td colspan="2" style="width: 30%">
						<form:input id="parkingFee" path="parkingFee" htmlEscape="false" class="input-xlarge amount number"/>
					</td>
					<td class="trtop" colspan="2" style="width: 20%">其他费用(元)</td>
					<td colspan="2" style="width: 30%">
						<form:input id="otherFee" path="otherFee" htmlEscape="false" class="input-xlarge amount number"/>
					</td>
				</tr>	
				<tr>
					<td class="trtop" colspan="2" style="width: 20%">总费用(元)<font color="red">*</font></td>
					<td colspan="6" style="width: 30%">
						<form:input id="totaFee" path="totaFee" htmlEscape="false" class="input-xlarge required amount number" placeholder="点击自动求和"/>
					</td>
				</tr>																		
				<tr>
					<td class="trtop" colspan="2" style="width: 20%">用车详情</td>
					<td colspan="6" style="width: 30%">
						<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
					</td>
				</tr>
			</c:if>
			<c:if test="${plmCarUseSpend.type eq '02'}">
				<tr>
					<td class="trtop" colspan="2" style="width: 20%">维保单位</td>
					<td colspan="2" style="width: 30%">
						<form:input type="hidden" id="repairComIds" path="repairCom.id" class="input-xlarge " />
					</td>
					<td class="trtop" colspan="2" style="width: 20%">行驶里程(km)</td>
					<td colspan="2" style="width: 30%">
						<form:input path="mildage" htmlEscape="false" class="input-xlarge lrunlv decimal znumber "/>
					</td>
				</tr>			
				<tr>
					<td class="trtop" colspan="2" style="width: 20%">维保费用(元)</td>
					<td colspan="2" style="width: 30%">
						<form:input id="repairFee" path="repairFee" htmlEscape="false" class="input-xlarge amount number"/>
					</td>
					<td class="trtop" colspan="2" style="width: 20%">其他费用(元)</td>
					<td colspan="2" style="width: 30%">
						<form:input id="otherFee" path="otherFee" htmlEscape="false" class="input-xlarge amount number"/>
					</td>
				</tr>			
				<tr>
					<td class="trtop" colspan="2" style="width: 20%">总费用(元)<font color="red">*</font></td>
					<td colspan="6" style="width: 30%">
						<form:input id="totaFee" path="totaFee" htmlEscape="false" class="input-xlarge amount number required" placeholder="点击自动求和"/>
					</td>
				</tr>																		
				<tr>
					<td class="trtop" colspan="2" style="width: 20%">维保详情</td>
					<td colspan="6" style="width: 30%">
						<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
					</td>
				</tr>			
			</c:if>
		<%-- 	<c:if test="${plmCarUseSpend.type eq '03'}">	
				<tr>
					<td class="trtop" colspan="2" style="width: 20%">维保单位</td>
					<td colspan="2" style="width: 30%">
						<form:input type="hidden" id="repairComIds" path="repairCom.id" class="input-xlarge " />
					</td>
					<td class="trtop" colspan="2" style="width: 20%">行驶里程(km)</td>
					<td colspan="2" style="width: 30%">
						<form:input path="mildage" htmlEscape="false" class="input-xlarge  number"/>
					</td>
				</tr>			
				<tr>
					<td class="trtop" colspan="2" style="width: 20%">保养费用(元)</td>
					<td colspan="2" style="width: 30%">
						<form:input id="repairFee" path="repairFee" htmlEscape="false" class="input-xlarge  number"/>
					</td>
					<td class="trtop" colspan="2" style="width: 20%">其他费用(元)</td>
					<td colspan="2" style="width: 30%">
						<form:input id="otherFee" path="otherFee" htmlEscape="false" class="input-xlarge  number"/>
					</td>
				</tr>			
				<tr>
					<td class="trtop" colspan="2" style="width: 20%">总费用(元)<font color="red">*</font></td>
					<td colspan="6" style="width: 30%">
						<form:input id="totaFee" path="totaFee" htmlEscape="false" class="input-xlarge required number" placeholder="点击自动求和"/>
					</td>
				</tr>																		
				<tr>
					<td class="trtop" colspan="2" style="width: 20%">保养详情</td>
					<td colspan="6" style="width: 30%">
						<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
					</td>
				</tr>							
			</c:if>					
			
			 --%>
					
		</table>		
		
		
		
		
		<%-- <div class="control-group">
			<label class="control-label">是否损坏：</label>
			<div class="controls">
				<form:radiobuttons path="isDamaged" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">维保单位：</label>
			<div class="controls">
				<form:input type="hidden" id="repairComIds" path="repairCom.id" class="input-xlarge " readonly="true"/>

			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">行驶里程(km)：</label>
			<div class="controls">
				<form:input path="mildage" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">加油费(元)：</label>
			<div class="controls">
				<form:input path="oilFee" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">过路费(元)：</label>
			<div class="controls">
				<form:input path="tollFee" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">停车费(元)：</label>
			<div class="controls">
				<form:input path="parkingFee" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保/修费用(元)：</label>
			<div class="controls">
				<form:input path="repairFee" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">其他费用(元)：</label>
			<div class="controls">
				<form:input path="otherFee" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总费用(元)：</label>
			<div class="controls">
				<form:input path="totaFee" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保/修/损详情：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="car:plmCarUseSpend:edit"><a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-ok"></i>保存</a>&nbsp;</shiro:hasPermission> 
			<a id="btnCancel" class="btn" href="javascript:;" onclick="history.go(-1)" ><i class="icon-reply"></i>返回</a>
		</div>
	</form:form>
</body>
</html>