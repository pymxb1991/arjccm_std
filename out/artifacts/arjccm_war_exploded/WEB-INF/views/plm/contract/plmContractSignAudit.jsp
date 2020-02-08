<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采购合同会签管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/common/zztable.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctxStatic}/plm/act/supervise.js"></script> 	
	<link href="${ctxStatic}/common/zzformtable.css" type="text/css"
	rel="stylesheet">
	<script type="text/javascript">
	
	
		$(document).ready(function() {
			$('#btnSubmit').click(function(){
				$('#inputForm').submit();
			});
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
	<form:form id="inputForm" modelAttribute="plmContractSign" action="${ctx}/contract/plmContractSign/saveAudit" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="plmAct.id"/>
		<form:hidden path="plmAct.title"/>		
		<form:hidden path="procInsId" />
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<sys:message content="${message}"/>		
		
			<h2>合同会签表</h2>	  	  	  	
	    <table  id="tabletop" class="table  ">
		
		<tr>
			 <td class="tabletop" colspan="2" >申请人： <u> &nbsp;&nbsp; &nbsp;&nbsp;<span>${plmContractSign.createBy.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;</u>  </td>			
			<td class="tabletop" colspan="2" > 采购项目部门： <u> &nbsp;&nbsp; &nbsp;&nbsp;<span>${plmContractSign.depart.name} </span>&nbsp;&nbsp;&nbsp;&nbsp;</u>      </td>
			<td  class="tabletop" colspan="2" >申请日期： <u> &nbsp;&nbsp; &nbsp;&nbsp;<span><fmt:formatDate value="${plmContractSign.applyDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>&nbsp;&nbsp;&nbsp;&nbsp;</u>  </td>	
			<td class="tabletop" colspan="2" >项目编号： <u> &nbsp;&nbsp; &nbsp;&nbsp;<span>${plmContractSign.applyId}</span>&nbsp;&nbsp;&nbsp;&nbsp;</u>       </td>					
		</tr>
		</table>
		<table id="table" class="table   table-condensed">
		
		<tr>
			<td class="trtop">合同名称</td>
			<td >${plmContractSign.contractName}</td>
			<td class="trtop">合同编号</td>
			<td >${plmContractSign.contractId}</td>
			<td class="trtop">合同类型</td>
			<td ><c:forEach items="${fns:getDictList('contract_contract_type')}" var="contractType">
				<c:if test="${contractType.value==plmContractSign.contractType }">
				${contractType.label}
				</c:if>				
				</c:forEach></td>
			
		</tr>
		<tr>
			<td class="trtop">项目负责人</td>
			<td >${plmContractSign.user.name}</td>
			<td class="trtop">是否标准合同</td>
			<td ><c:forEach items="${fns:getDictList('contract_is_standard')}" var="isStandard">
				<c:if test="${isStandard.value==plmContractSign.isStandard }">
				${isStandard.label}
				</c:if>				
				</c:forEach></td>
			<td class="trtop">合同提供方</td>
			<td ><c:forEach items="${fns:getDictList('contract_provider')}" var="provider">
				<c:if test="${provider.value==plmContractSign.provider }">
				${provider.label}
				</c:if>				
				</c:forEach></td>
			
		</tr>
		<tr>
			<td class="trtop">合同金额</td>
			<td >${plmContractSign.contractMoney}万元</td>
			<td class="trtop">是否在预算内</td>
			<td colspan="3"><c:forEach items="${fns:getDictList('contract_isBudget')}" var="isBudget">
				<c:if test="${isBudget.value==plmContractSign.isBudget }">
				${isBudget.label}
				</c:if>				
				</c:forEach></td>
			
			
		</tr>
		
		<tr>
			<td class="trtop">合同概要</td>
			<td colspan="5" >${plmContractSign.describes}</td>
				
		</tr>
		<tr>
			<td class="trtop">附件（采购清单等）</td>
			<td colspan="5" ><form:hidden id="files" path="files" htmlEscape="false" maxlength="256" class="input-xlarge"/> 
				<sys:ckfinder  input="files" type="files" uploadPath="/contract/plmContractSign" selectMultiple="true" readonly="true"/></td>
				
		</tr>
		<c:if test="${plmContractSign.plmAct.isSup eq '0'}">
				<tr>
					<td class="trtop" colspan="1">是否督办</td>
					<td colspan="5">${fns:getDictLabel(plmContractSign.plmAct.isSup, 'yes_no', '')}</td>
				</tr>	
			</c:if>
			<c:if test="${plmContractSign.plmAct.isSup eq '1'}">
				<tr>
					<td class="trtop" colspan="1">是否督办</td>
					<td colspan="2">${fns:getDictLabel(plmContractSign.plmAct.isSup, 'yes_no', '')}</td>
					<td class="trtop" colspan="1">督办人</td>
					<td colspan="2">${plmContractSign.plmAct.supExe.name}</td>
				</tr>		
				<tr>
					<td class="trtop" colspan="2">督办明细</td>
					<td colspan="5">${plmContractSign.plmAct.supDetail}</td>
				</tr>
			</c:if>			
			<act:histoicTable procInsId="${plmContractSign.procInsId}" colspan="5" titleColspan="1"/>
		    <c:if test="${rejectedBtn}">
			<c:if test="${plmContractSign.plmAct.isSup ne '1'}">
				<tr>
					<td class="trtop" colspan="1">是否添加督办</td>
					<td id="isSubTd" colspan="2">
						<form:radiobuttons path="plmAct.isSup" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
					</td>
					<td class="trtop isSup" colspan="1">督办人</td>
					<td class="isSup" colspan="2">
						<sys:treeselect id="supExe" name="plmAct.supExe.id" value="${plmContractSign.plmAct.supExe.id}" labelName="plmAct.supExe.name" labelValue="${plmContractSign.plmAct.supExe.name}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true" isAll="true"/>
					</td>
				</tr>		
				<tr class="isSup">
					<td class="trtop" colspan="1">督办明细</td>
					<td colspan="5">
						<form:textarea path="plmAct.supDetail" htmlEscape="false" rows="4" maxlength="256" class="input-xxlarge "/>
					</td>
				</tr>		
			</c:if>	
		    </c:if>	
		<tr>
				<td class="trtop">您的意见</td>
				<td class="pingshen" colspan="5">
				   <form:textarea path="act.comment" htmlEscape="false" rows="5" maxlength="255" class="input-xxlarge "/>
				</td>				
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