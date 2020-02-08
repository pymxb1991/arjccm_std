<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会议申请管理</title>
	<meta name="decorator" content="default"/>
	<link type="text/css" href="${ctxStatic}/common/zztable.css" rel="stylesheet">
	<link type="text/css" href="${ctxStatic}/common/zzformtable.css" rel="stylesheet">

	<script type="text/javascript" src="${ctxStatic}/plm/room/plmRoomMeetingApplyForm.js"></script>
	<script type="text/javascript" src="${ctxStatic}/plm/act/supervise.js"></script> 
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function(){
				$('#inputForm').submit();
			});
			//隐藏上传文件的“上传”和“清除”按钮
			$("#files").next().next().hide();
			$("#files").next().next().next().hide();
		});
	</script>	
</head>
<body>
	<form:form id="inputForm" style="margin: 30px 100px;" modelAttribute="plmRoomApply" action="${ctx}/logistics/plmRoomMeetingApply/saveAudit" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="category"/>
		<form:hidden path="plmAct.id"/>
		<form:hidden path="plmAct.title"/>		
		<form:hidden path="act.taskId"/>
		<form:hidden path="act.taskName"/>
		<form:hidden path="act.taskDefKey"/>
		<form:hidden path="act.procInsId"/>
		<form:hidden path="act.procDefId"/>
		<form:hidden id="flag" path="act.flag"/>
		<sys:message content="${message}"/>		
		
		<h2>会议申请单</h2>	
	    <table id="tabletop" class="table">
			<tr>
				<td class="tabletop" colspan="2" >申请编号:<u>&nbsp;&nbsp; &nbsp;&nbsp;<span>${plmRoomApply.code}</span></u></td>
				<td class="tabletop" colspan="2">申请人:<u> &nbsp;&nbsp; &nbsp;&nbsp;<span>${plmRoomApply.initiator.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
				<td class="tabletop" colspan="2">所属部门:<u> &nbsp;&nbsp; &nbsp;&nbsp;<span>${plmRoomApply.initiator.office.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
				<td class="tabletop" colspan="2">申请日期： <u> &nbsp;&nbsp; &nbsp;&nbsp;<span><fmt:formatDate value="${plmRoomApply.createDate}" pattern="yyyy-MM-dd"/></span>&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
			</tr>
		</table>	
		<table id="table" class="table table-condensed">
			<tr>
				<td class="trtop" colspan="2" style="width: 20%">会议名称</td>
				<td colspan="2" style="width: 30%">${plmRoomApply.subject}</td>
				<td class="trtop" colspan="2" style="width: 20%">会议类型</td>
				<td colspan="2" style="width: 30%">${fns:getDictLabel(plmRoomApply.type, 'plm_room_apply_type', '')}</td>
			</tr>	
			<tr>
				<td class="trtop" colspan="2">会议地点</td>
				<td colspan="6">${plmRoomApply.room.subject}</td>
			</tr>				
			<tr>
				<td class="trtop" colspan="2" style="width: 20%">会议主持人</td>
				<td colspan="2" style="width: 30%">${plmRoomApply.presider.name}</td>
				<td class="trtop" colspan="2" style="width: 20%">会议记录人</td>
				<td colspan="2" style="width: 30%">${plmRoomApply.recorder.name}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="2" style="width: 20%">开始时间</td>
				<td colspan="2" style="width: 30%"><fmt:formatDate value="${plmRoomApply.startTime}" pattern="yyyy-MM-dd HH:mm"/></td>
				<td class="trtop" colspan="2" style="width: 20%">结束时间</td>
				<td colspan="2" style="width: 30%"><fmt:formatDate value="${plmRoomApply.endTime}" pattern="yyyy-MM-dd HH:mm"/></td>
			</tr>							
			<tr>
				<td class="trtop" colspan="2">详细内容</td>
				<td colspan="6">${plmRoomApply.details}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">附件</td>
				<td colspan="6">
					<form:hidden id="files" path="files" htmlEscape="false" maxlength="256" class="input-xlarge"/> 
					<sys:ckfinder  input="files" type="files" uploadPath="/contract/plmContractSign" selectMultiple="true" readonly="true"/>
				</td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">参会人员</td>
				<td colspan="6">
					<a onclick="LayerDialog('${ctx}/logistics/plmRoomAttendee/list?roomApply.id=${plmRoomApply.id}', '参会人员详情', '1000px', '700px')">
								共${plmRoomApply.number}人</a>
				</td>
			</tr>
			<c:if test="${plmRoomApply.plmAct.isSup eq '0'}">
				<tr>
					<td class="trtop" colspan="2">是否督办</td>
					<td colspan="6">${fns:getDictLabel(plmRoomApply.plmAct.isSup, 'yes_no', '')}</td>
				</tr>	
			</c:if>
			<c:if test="${plmRoomApply.plmAct.isSup eq '1'}">
				<tr>
					<td class="trtop" colspan="2">是否督办</td>
					<td colspan="2">${fns:getDictLabel(plmRoomApply.plmAct.isSup, 'yes_no', '')}</td>
					<td class="trtop" colspan="2">督办人</td>
					<td colspan="2">${plmRoomApply.plmAct.supExe.name}</td>
				</tr>		
				<tr>
					<td class="trtop" colspan="2">督办明细</td>
					<td colspan="6">${plmRoomApply.plmAct.supDetail}</td>
				</tr>
			</c:if>			
			<act:histoicTable procInsId="${plmRoomApply.procInsId}" colspan="6" titleColspan="2"/>
		<c:if test="${rejectedBtn}">
			<c:if test="${plmRoomApply.plmAct.isSup ne '1'}">
				<tr>
					<td class="trtop" colspan="2">是否添加督办</td>
					<td id="isSubTd" colspan="6">
						<form:radiobuttons path="plmAct.isSup" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
					</td>
					<td class="trtop isSup" colspan="2">督办人</td>
					<td class="isSup" colspan="2">
						<sys:treeselect id="supExe" name="plmAct.supExe.id" value="${plmRoomApply.plmAct.supExe.id}" labelName="plmAct.supExe.name" labelValue="${plmRoomApply.plmAct.supExe.name}"
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
			</c:if>
			<tr>
				<td class="trtop" colspan="2">您的意见<font color="red">*</font></td>
				<td colspan="6"><form:textarea path="act.comment" htmlEscape="false" rows="5" maxlength="255" class="input-xxlarge "/></td>
			</tr>	
		</table>						
			
		<div class="form-actions">
				<a id="btnSubmit" class="btn btn-primary" onclick="$('#flag').val('yes')"><i class="icon-ok-sign"></i>同 意</a>&nbsp;
				<c:if test="${rejectedBtn}">
				<a id="btnSubmit" class="btn btn-inverse" onclick="$('#flag').val('no')"><i class="icon-remove-sign"></i>驳 回</a>&nbsp;
			</c:if>
			<a id="btnCancel" class="btn" href="javascript:;" onclick="history.go(-1)" ><i class="icon-reply"></i>返回</a>
		</div>
	</form:form>
</body>
</html>