<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>请假申请管理</title>
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
	<form:form   id="inputForm" modelAttribute="plmApplicationForLeave" action="${ctx}/travel/plmApplicationForLeave/saveAudit" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="plmAct.id"/>
		<form:hidden path="plmAct.title"/>	
		<form:hidden path="procInsId"/>
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<sys:message content="${message}"/>		
		<h2>请假申请单</h2>
		<table id="tabletop" class="table">
			<tr>
				<td class="tabletop" colspan="2" width="33.33%">申请人:&nbsp;&nbsp; &nbsp;&nbsp;<span>${plmApplicationForLeave.applyer.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td class="tabletop" colspan="2" width="33.33%">所属部门: &nbsp;&nbsp; &nbsp;&nbsp;<span>${plmApplicationForLeave.department.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td class="tabletop" colspan="2" width="33.33%">申请编号(系统生成):&nbsp;&nbsp; &nbsp;&nbsp;<span>${plmApplicationForLeave.code}</span>&nbsp;&nbsp; &nbsp;&nbsp;</td>
			</tr>
		</table>
		<table id="table" class="table table-condensed">
			<tr>
				<td class="trtop" colspan="1">主题</td>
				<td colspan="5">${plmApplicationForLeave.title}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="1">开始时间</td>
				<td colspan="2"><fmt:formatDate value="${plmApplicationForLeave.fromDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td class="trtop" colspan="1">结束日期</td>
				<td colspan="2"><fmt:formatDate value="${plmApplicationForLeave.toDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>
				<td class="trtop" colspan="1">请假类型</td>
				<td colspan="2">${fns:getDictLabel(plmApplicationForLeave.applyType, 'leave_type', '')}</td>
				<td class="trtop" colspan="1">请假时长(h)</td>
				<td colspan="2">${plmApplicationForLeave.days}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="1">请假原因</td>
				<td colspan="5">${plmApplicationForLeave.cause}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="1">备注信息</td>
				<td colspan="5">${plmApplicationForLeave.remarks}</td>
			</tr>
			<c:if test="${plmApplicationForLeave.plmAct.isSup eq '0'}">
				<tr>
					<td class="trtop" colspan="1">是否督办</td>
					<td colspan="5">${fns:getDictLabel(plmApplicationForLeave.plmAct.isSup, 'yes_no', '')}</td>
				</tr>	
			</c:if>
			<c:if test="${plmApplicationForLeave.plmAct.isSup eq '1'}">
				<tr>
					<td class="trtop" colspan="1">是否督办</td>
					<td colspan="2">${fns:getDictLabel(plmApplicationForLeave.plmAct.isSup, 'yes_no', '')}</td>
					<td class="trtop" colspan="1">督办人</td>
					<td colspan="2">${plmApplicationForLeave.plmAct.supExe.name}</td>
				</tr>		
				<tr>
					<td class="trtop" colspan="1">督办明细</td>
					<td colspan="5">${plmApplicationForLeave.plmAct.supDetail}</td>
				</tr>
			</c:if>		
			<act:histoicTable procInsId="${plmApplicationForLeave.procInsId}"  colspan="5" titleColspan="1"/>
			<c:if test="${rejectedBtn}">
			<c:if test="${plmApplicationForLeave.plmAct.isSup ne '1'}">
				<tr>
					<td class="trtop" colspan="1">是否添加督办</td>
					<td id="isSubTd" colspan="5">
						<form:radiobuttons path="plmAct.isSup" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
					</td>
					<td class="trtop isSup" colspan="1">督办人</td>
					<td class="isSup" colspan="2">
						<sys:treeselect id="supExe" name="plmAct.supExe.id" value="${plmApplicationForLeave.plmAct.supExe.id}" labelName="plmAct.supExe.name" labelValue="${plmApplicationForLeave.plmAct.supExe.name}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true" isAll="true"/>
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
			<tr>
				<td class="trtop" colspan="1">附件</td>
				<td colspan="5">
					<form:hidden id="file" path="file" htmlEscape="false" maxlength="4000" class="input-xlarge"/>
					<sys:ckfinder input="file" type="files" uploadPath="/travel/plmApplicationForLeave" selectMultiple="false" readonly="true"/>
				</td>
			</tr>
		</table>
		<div class="form-actions">
			
			<a id=btnSubmit class="btn btn-primary" onclick="$('#flag').val('yes')"><i class="icon-ok-sign"></i>同 意</a>&nbsp;
			<c:if test="${rejectedBtn}">
			<a id="btnCancel" class="btn btn-inverse" onclick="$('#flag').val('no')"><i class="icon-remove-sign"></i>驳 回</a>&nbsp;
			</c:if>	
			<a id="btnCancel" class="btn" href="javascript:;" onclick="history.go(-1)" ><i class="icon-reply"></i>返回</a>
		</div>
	</form:form>
</body>
</html>