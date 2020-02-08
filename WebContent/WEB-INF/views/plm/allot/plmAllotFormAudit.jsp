<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>调拨单管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/common/zztable.css" type="text/css"
	rel="stylesheet">
	<!-- 表格试表单css -->
	<link href="${ctxStatic}/common/zzformtable.css" type="text/css"
	rel="stylesheet">
	<script type="text/javascript" src="${ctxStatic}/plm/act/supervise.js"></script>
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
	<style type="text/css">
	
	 #table .ntable td {
    	border: 0;
	}
	 
	.btn-select {
		color: #fff;
	    background-color: #3bb4f2 !important;
	    border-color: #3bb4f2 !important;
	}
	</style>
</head>
<body>
	<form:form  id="inputForm" modelAttribute="plmAllot" action="${ctx}/allot/plmAllot/saveAudit" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="plmAct.id"/>
		<form:hidden path="plmAct.title"/>
		<form:hidden path="plmAct.tableName"/>
		<form:hidden path="plmAct.tableId"/>
		<form:hidden path="plmAct.formUrl"/>	
		<form:hidden path="code"/>
		<form:hidden path="applyer.id"/>
		<form:hidden path="procInsId"/>
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<sys:message content="${message}"/>		
		<h2>资产内部调拨单</h2>
		<table id="tabletop" class="table">
			<tr>
				<td class="tabletop" colspan="2" width="33.33%">申请人:
				&nbsp;&nbsp; &nbsp;&nbsp;<span>${plmAllot.applyer.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td class="tabletop" colspan="2" width="33.33%">填写日期: 
				&nbsp;&nbsp; &nbsp;&nbsp;<span><fmt:formatDate value="${plmAllot.addDate}" pattern="yyyy-MM-dd"/></span>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td class="tabletop" colspan="2" width="33.33%">申请编号(系统生成):&nbsp;&nbsp; &nbsp;&nbsp;
				<span>${plmAllot.code}</span>&nbsp;&nbsp; &nbsp;&nbsp;</td>
			</tr>
		</table>
		<table id="table" class="table  table-condensed">
			<tr>
				<td class="trtop" colspan="1">标题</td>
				<td colspan="5">${plmAllot.title}</td>
			</tr>
			<tr>
				<td colspan="6">
					<table class="table  ntable table-condensed">
						<thead>
							<tr>
								<th>物品名称</th>
								<th>规格型号</th>
								<th>计量单位</th>
								<th>数量</th>
								<th>单价(元)</th>
								<th>价值(元)</th>
								<th>二维码</th>
								<th>备注</th>
							</tr>
						</thead>
						<tbody>
							
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td class="trtop" colspan="1">资产合计大写</td>
				<td colspan="2">${plmAllot.sumCap}</td>
				<td class="trtop" colspan="1">资产合计小写</td>
				<td colspan="2">${plmAllot.sumLow}</td>
			</tr>
			<tr>
				<td colspan="6">
					<table class="table ntable table-condensed" >
						<tr><td colspan="6"><h5>调出审核单位</h5></td></tr>
						<tr>
							<td class="trtop" style="width: 20%;" colspan="1">资产调出部门</td>
							<td colspan="2" style="width: 30%;">${plmAllot.outDep.name}</td>
							<td class="trtop" style="width: 20%;">负责人</td>
							<td colspan="2" style="width: 30%;">${plmAllot.outManager.name}</td>
						</tr>
						<tr>
							
							<td class="trtop" style="width: 20%;">经办人</td>
							<td colspan="2">${plmAllot.outOperator.name}</td>
							<td class="trtop">日期</td>
							<td colspan="2"><fmt:formatDate value="${plmAllot.outDate}" pattern="yyyy-MM-dd"/></td>
						</tr>
					</table>
				</td>
				</tr>
				<tr>
				<td colspan="6">
					<table class="table ntable table-condensed">
						<tr><td colspan="6"><h5>调入审核单位</h5></td></tr>
						<tr>
							<td class="trtop" style="width: 20%;" colspan="1">资产调入部门</td>
							<td colspan="2" style="width: 30%;">${plmAllot.inDep.name}</td>
							<td  class="trtop" style="width: 20%;">负责人</td>
							<td colspan="2" style="width: 30%;">${plmAllot.inManager.name}</td>
						</tr>
						<tr>
							
							<td  class="trtop" >经办人</td>
							<td colspan="2">${plmAllot.inOperator.name}</td>
							<td  class="trtop" >日期</td>
							<td colspan="2"><fmt:formatDate value="${plmAllot.outDate}" pattern="yyyy-MM-dd"/></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="trtop"  colspan="1">备注信息</td>
				<td colspan="5">${plmAllot.remarks}</td>
			</tr>
			<c:if test="${plmAllot.plmAct.isSup eq '0'}">
				<tr>
					<td class="trtop" colspan="1">是否督办</td>
					<td colspan="5">${fns:getDictLabel(plmAllot.plmAct.isSup, 'yes_no', '')}</td>
				</tr>	
			</c:if>
			<c:if test="${plmAllot.plmAct.isSup eq '1'}">
				<tr>
					<td class="trtop" colspan="1">是否督办</td>
					<td colspan="2">${fns:getDictLabel(plmAllot.plmAct.isSup, 'yes_no', '')}</td>
					<td class="trtop" colspan="1">督办人</td>
					<td colspan="2">${plmAllot.plmAct.supExe.name}</td>
				</tr>		
				<tr>
					<td class="trtop" colspan="1">督办明细</td>
					<td colspan="5">${plmAllot.plmAct.supDetail}</td>
				</tr>
			</c:if>		
			<act:histoicTable procInsId="${plmAllot.procInsId}"  colspan="5" titleColspan="1"/>
			<c:if test="${rejectedBtn}">
			<c:if test="${plmAllot.plmAct.isSup ne '1'}">
				<tr>
					<td class="trtop" colspan="1">是否添加督办</td>
					<td id="isSubTd" colspan="5">
						<form:radiobuttons path="plmAct.isSup" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
					</td>
					<td class="trtop isSup" colspan="1">督办人</td>
					<td class="isSup" colspan="2">
						<sys:treeselect id="supExe" name="plmAct.supExe.id" value="${plmAllot.plmAct.supExe.id}" labelName="plmAct.supExe.name" labelValue="${plmAllot.plmAct.supExe.name}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
					</td>
				</tr>		
				<tr class="isSup">
					<td class="trtop" colspan="1">督办明细</td>
					<td colspan="5">
						<form:textarea path="plmAct.supDetail" htmlEscape="false" rows="4" maxlength="256" class="input-xxlarge "/>
					</td>
				</tr>		
			</c:if>	</c:if>	
			<tr>
				<td class="trtop" colspan="1">您的意见</td>
				<td class="pingshen" colspan="5">
				   <form:textarea path="act.comment" htmlEscape="false" rows="5" maxlength="255" class="input-xxlarge "/>
				</td>				
			</tr>
		</table>
		<div class="form-actions">
			
			<input id="btnConmit" class="btn btn-primary" type="submit" value="同 意" onclick="$('#flag').val('yes')"/>&nbsp;
			<c:if test="${rejectedBtn}">
			<input id="btnCancel" class="btn btn-inverse" type="submit" value="驳 回" onclick="$('#flag').val('no')"/>&nbsp;
			</c:if>	
		
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>