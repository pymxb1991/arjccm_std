<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>维修(保养)申请管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/common/zztable.css" type="text/css" rel="stylesheet">
	<link type="text/css" href="${ctxStatic}/common/zzformtable.css" rel="stylesheet">
	<script type="text/javascript" src="${ctxStatic}/plm/car/plmCarUseForm.js"></script> 
	<script type="text/javascript" src="${ctxStatic}/plm/act/supervise.js"></script> 
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
	<form:form id="inputForm" style="margin: 30px 100px;" modelAttribute="plmCarApplyRepair" action="${ctx}/car/apply/plmCarApplyRepair/saveAudit" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="plmAct.id"/>
		<form:hidden path="plmAct.title"/>
		<form:hidden path="act.taskId"/>
		<form:hidden path="act.taskName"/>
		<form:hidden path="act.taskDefKey"/>
		<form:hidden path="act.procInsId"/>
		<form:hidden path="act.procDefId"/>
		<form:hidden id="flag" path="act.flag"/>
		<sys:message content="${message}"/>		
		<h2>维修(保养)申请单</h2>	
	    <table id="tabletop" class="table">
			<tr>
				<td class="tabletop" colspan="2" >申请编号:<u>&nbsp;&nbsp; &nbsp;&nbsp;<span>${plmCarApplyRepair.title}</span></u></td>
				<td class="tabletop" colspan="2">申请人:<u> &nbsp;&nbsp; &nbsp;&nbsp;<span>${plmCarApplyRepair.user.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
				<td class="tabletop" colspan="2">所属部门:<u> &nbsp;&nbsp; &nbsp;&nbsp;<span>${plmCarApplyRepair.user.office.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
				<td class="tabletop" colspan="2">申请日期： <u> &nbsp;&nbsp; &nbsp;&nbsp;<span><fmt:formatDate value="${plmCarApplyRepair.createDate}" pattern="yyyy-MM-dd"/></span>&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
			</tr>
		</table>	
		<table id="table" class="table table-condensed">
			<tr>
				<td class="trtop" colspan="2" style="width: 20%">车牌号</td>
				<td colspan="2" style="width: 30%">${plmCarApplyRepair.car.vehicle}</td>
				<td class="trtop" colspan="2" style="width: 20%">维保单位</td>
				<td colspan="2" style="width: 30%">${plmCarApplyRepair.repairCom.name}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">品牌</td>
				<td colspan="2">${plmCarApplyRepair.car.brand}</td>
				<td class="trtop" colspan="2">联系人</td>
				<td colspan="2">${plmCarApplyRepair.repairCom.leader}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">型号</td>
				<td colspan="2">${plmCarApplyRepair.car.vmodel}</td>
				<td class="trtop" colspan="2">联系电话</td>
				<td colspan="2">${plmCarApplyRepair.repairCom.phone}</td>
			</tr>		
			<tr>
				<td class="trtop" colspan="2">计划维修(保养)日期</td>
				<td colspan="6"><fmt:formatDate value="${plmCarApplyRepair.repairDate}" pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">维修(保养)原因</td>
				<td colspan="6">${plmCarApplyRepair.remarks}</td>
			</tr>
			<c:if test="${plmCarApplyRepair.plmAct.isSup eq '0'}">
				<tr>
					<td class="trtop" colspan="2">是否督办</td>
					<td colspan="6">${fns:getDictLabel(plmCarApplyRepair.plmAct.isSup, 'yes_no', '')}</td>
				</tr>	
			</c:if>
			<c:if test="${plmCarApplyRepair.plmAct.isSup eq '1'}">
				<tr>
					<td class="trtop" colspan="2">是否督办</td>
					<td colspan="2">${fns:getDictLabel(plmCarApplyRepair.plmAct.isSup, 'yes_no', '')}</td>
					<td class="trtop" colspan="2">督办人</td>
					<td colspan="2">${plmCarApplyRepair.plmAct.supExe.name}</td>
				</tr>		
				<tr>
					<td class="trtop" colspan="2">督办明细</td>
					<td colspan="6">${plmCarApplyRepair.plmAct.supDetail}</td>
				</tr>
			</c:if>			
			<act:histoicTable procInsId="${plmCarApplyRepair.procInsId}" colspan="6" titleColspan="2"/>
		
			<c:if test="${plmCarApplyRepair.plmAct.isSup ne '1'}">
				<tr>
					<td class="trtop" colspan="2">是否添加督办</td>
					<td id="isSubTd" colspan="6">
						<form:radiobuttons path="plmAct.isSup" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
					</td>
					<td class="trtop isSup" colspan="2">督办人</td>
					<td class="isSup" colspan="2">
						<sys:treeselect id="supExe" name="plmAct.supExe.id" value="${plmCarApplyRepair.plmAct.supExe.id}" labelName="plmAct.supExe.name" labelValue="${plmCarApplyRepair.plmAct.supExe.name}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true" isAll="true"/>
					</td>
				</tr>		
				<tr class="isSup">
					<td class="trtop" colspan="2">督办明细</td>
					<td colspan="6">
						<form:textarea path="plmAct.supDetail" htmlEscape="false" rows="4" maxlength="256" class="input-xxlarge "/>
					</td>
				</tr>		
			</c:if>	
			<tr>
				<td class="trtop" colspan="2">您的意见<font color="red">*</font></td>
				<td colspan="6"><form:textarea path="act.comment" htmlEscape="false" rows="5" maxlength="255" class="input-xxlarge "/></td>
			</tr>	
		</table>							
		<div class="form-actions">
			<a id="btnSubmit" class="btn btn-primary" onclick="$('#flag').val('yes')"><i class="icon-ok-sign"></i>同 意</a>&nbsp;
			<a id="btnSubmit" class="btn btn-inverse" onclick="$('#flag').val('no')"><i class="icon-remove-sign"></i>驳 回</a>&nbsp;
			<a id="btnCancel" class="btn" href="javascript:;" onclick="history.go(-1)" ><i class="icon-reply"></i>返回</a>
		</div>
	</form:form>
</body>
</html>