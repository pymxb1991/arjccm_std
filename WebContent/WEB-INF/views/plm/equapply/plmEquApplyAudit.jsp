<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>物资申请管理</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/common/zztable.css" type="text/css"
	rel="stylesheet">
<!-- 表格试表单css -->
<link href="${ctxStatic}/common/zzformtable.css" type="text/css"
	rel="stylesheet">
<script type="text/javascript" src="${ctxStatic}/plm/act/supervise.js"></script>
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
	$("td").css({
		"padding" : "1%"
	});
</script>
</head>
<body>
	<br />
	<form:form id="inputForm" modelAttribute="plmEquApply"
		action="${ctx}/equapply/plmEquApply/saveAudit" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="type" />
		<form:hidden path="plmAct.id" />
		<form:hidden path="plmAct.title" />
		<form:hidden path="procInsId" />
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<sys:message content="${message}" />
		<h2>装备领用申请单</h2>
		<table id="tabletop" class="table">
			<tr>
				<td class="tabletop" colspan="1"></td>
				<td class="tabletop" colspan="3">申请人：<u>&nbsp;&nbsp;
						&nbsp;&nbsp;<span>${plmEquApply.user.name}</span>
				</u></td>
				<td class="tabletop" colspan="2">申请人所在部门：<u>&nbsp;&nbsp;
						&nbsp;&nbsp;<span>${plmEquApply.user.office.name}</span>
				</u></td>
				<td class="tabletop" colspan="2">申请日期：<u>&nbsp;&nbsp;
						&nbsp;&nbsp;<span><fmt:formatDate
								value="${plmEquApply.applyDate}" pattern="yyyy-MM-dd" /></span>
				</u></td>
			</tr>
		</table>
		<table id="table" class="table   table-condensed"
			style="table-layout: fixed;">
			<tr>
				<td class="trtop" colspan="3">物品</td>
				<td class="trtop" colspan="3">规格型号</td>
				<td class="trtop" colspan="2">申请数量</td>
			</tr>
			<c:forEach items="${applyDetails}" var="plmEquApplyDetail">
				<tr>
					<td class="trtop" colspan="3">${plmEquApplyDetail.name}</td>
					<td class="trtop" colspan="3">${plmEquApplyDetail.spec}</td>
					<td class="trtop" colspan="2">${plmEquApplyDetail.number}</td>

				</tr>
			</c:forEach>
			<tr>
				<td class="trtop" colspan="2">申请原因<span class="help-inline"><font
						color="red">*</font> </span>
				</td>
				<td colspan="6"><form:textarea path="applyBody"
						htmlEscape="false" rows="4" class="input-xxlarge "
						cssStyle="width:76.296%" /></td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">备注</td>
				<td colspan="6"><form:textarea path="remarks"
						htmlEscape="false" rows="6" maxlength="255" class="input-xxlarge "
						cssStyle="width:76.296%" /></td>
			</tr>
			<c:if test="${plmEquApply.plmAct.isSup eq '0'}">
				<tr>
					<td class="trtop" colspan="2">是否督办</td>
					<td colspan="6">${fns:getDictLabel(plmEquApply.plmAct.isSup, 'yes_no', '')}</td>
				</tr>
			</c:if>
			<c:if test="${plmEquApply.plmAct.isSup eq '1'}">
				<tr>
					<td class="trtop" colspan="2">是否督办</td>
					<td colspan="2">${fns:getDictLabel(plmEquApply.plmAct.isSup, 'yes_no', '')}</td>
					<td class="trtop" colspan="2">督办人</td>
					<td colspan="2">${plmEquApply.plmAct.supExe.name}</td>
				</tr>
				<tr>
					<td class="trtop" colspan="2">督办明细</td>
					<td colspan="6">${plmEquApply.plmAct.supDetail}</td>
				</tr>
			</c:if>
			<act:histoicTable procInsId="${plmEquApply.procInsId}" colspan="6"
				titleColspan="2" />

			<c:if test="${plmEquApply.plmAct.isSup ne '1'}">
				<tr>
					<td class="trtop" colspan="2">是否添加督办</td>
					<td id="isSubTd" colspan="6"><form:radiobuttons
							path="plmAct.isSup" items="${fns:getDictList('yes_no')}"
							itemLabel="label" itemValue="value" htmlEscape="false" class="" />
					</td>
					<td class="trtop isSup" colspan="2">督办人</td>
					<td class="isSup" colspan="2"><sys:treeselect id="supExe"
							name="plmAct.supExe.id" value="${plmEquApply.plmAct.supExe.id}"
							labelName="plmAct.supExe.name"
							labelValue="${plmEquApply.plmAct.supExe.name}" title="用户"
							url="/sys/office/treeData?type=3" cssClass="" allowClear="true"
							notAllowSelectParent="true" isAll="true" /></td>
				</tr>
				<tr class="isSup">
					<td class="trtop" colspan="2">督办明细</td>
					<td colspan="6"><form:textarea path="plmAct.supDetail"
							htmlEscape="false" rows="4" maxlength="256"
							class="input-xxlarge " /></td>
				</tr>
			</c:if>
			<tr>
				<td class="trtop" colspan="2">您的意见</td>
				<td colspan="6"><form:textarea path="act.comment"
						htmlEscape="false" rows="5" maxlength="255" class="input-xxlarge " /></td>
			</tr>
		</table>
		<input name='details' type='hidden' value="">
		<div class="form-actions">
				<a id="btnSubmit" class="btn btn-primary" onclick="$('#flag').val('yes')"><i class="icon-ok-sign"></i>同 意</a>&nbsp;
				<a id="btnSubmit" class="btn btn-inverse" onclick="$('#flag').val('no')"><i class="icon-remove-sign"></i>驳 回</a>&nbsp;
				<a id="btnCancel" class="btn" href="javascript:;" onclick="history.go(-1)" ><i class="icon-reply"></i>返回</a>
		</div>
	</form:form>
</body>
</html>