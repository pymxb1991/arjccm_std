<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>物资申请管理</title>
<meta name="decorator" content="default" />
<link rel="stylesheet"
	href="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.css">
<link type="text/css" href="${ctxStatic}/common/zztable.css"
	rel="stylesheet">
<%-- <link type="text/css" href="${ctxStatic}/common/zzformtable.css"
	rel="stylesheet"> --%>
	<link href="${ctxStatic}/form/css/formTable.css" rel="stylesheet" />
<script type="text/javascript" src="${ctxStatic}/plm/act/supervise.js"></script>
<script src="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/plm/storage/ajaxMessageAlert.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
						$("#inputForm").validate({
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
						$("#ascertain").on("click", function() {
							var codeList = $(".codeId");
							var equipmentCode = $("#equipmentCode").val();
							for(var i=0;i<codeList.length;i++){
								if(equipmentCode==codeList.eq(i).html()){
									messageAlert("物品已添加，请不要重复添加同一个物品！","error");
									$("#equipmentCode").attr("value", "");//清空输入框
									return;
								}
							}
							$.ajax({
							url : "${ctx}/equapply/plmEquApplyMaintenance/getEquipmentById",
							type : "post",
							data : {"code" : $("#equipmentCode").val()},
							async : true,
							cache : false,
							timeout : 30000,
							success : function(data) {
								var content = jQuery.parseJSON(data);
								if (content === undefined|| content == null|| content == "") {
									messageAlert("添加失败！","error");
									return;
								}
								if (content.ret != 0) {
									messageAlert(content.message,"error");
									return;
								}
								messageAlert( "添加成功！", "success");
								$("#addEqu").append(content.result);
								$("#equipmentCode").attr("value","");
							},
							error : function(
									jqXHR,
									textStatus,
									errorThrown) {
								messageAlert("添加失败！","error");
								console.error("jqXHR:",jqXHR);
								console.error("textStatus:",textStatus);
								console.error("errorThrown:",errorThrown);
								top.$.jBox.closeTip();
							}
						});
							$("#equipmentCode").attr("value", "");//清空输入框
						});
						$("a[title='deleteDetail']").on(
								"click",
								function() {
									var id = $(this).next().attr("id");
									$.ajax({
										url : "${ctx}/equapply/plmEquApplyMaintenance/deleteDetail?ids=1",
										type : "post",
										data : {
											"id" : id
										},
										async : true,
										cache : false,
										timeout : 30000,
										success : function(data) {
											var content = jQuery.parseJSON(data);
											if (content === undefined || content == null
													|| content == "") {
												messageAlert("删除失败！", "error");
												return;
											}
											 if (content.ret != 0) {
												messageAlert(content.message, "error");
												return;
											} 
											messageAlert("删除成功！", "success");
											$("#" + id).parent().parent().remove();
										},
										error : function(jqXHR, textStatus, errorThrown) {
											messageAlert("删除失败！", "error");
											console.error("jqXHR:", jqXHR);
											console.error("textStatus:", textStatus);
											console.error("errorThrown:", errorThrown);
											top.$.jBox.closeTip();
										}
									});
								});
						$("#btnSubmit").on("click",function() {
											if($("#addEqu").children().length<=0){
												alert("请正确添加所申请物品的信息");
												return false;
											}else{
											$('#flag').val('yes');
											$("#inputForm")
													.attr("action","${ctx}/equapply/plmEquApplyMaintenance/save?type=3");
											$("#inputForm").submit();
											}
										});
						$("#btnSubmit2").on("click",function() {
											$('#flag').val('no');
											$("#inputForm").attr("action","${ctx}/equapply/plmEquApplyMaintenance/save?type=3");
											$("#inputForm").submit();
										});
						$("#btnSubmit3").on("click",function() {
											if($("#addEqu").children().length<=0){
												alert("请正确添加所申请物品的信息");
												return false;
											}else{
											$("#inputForm").attr("action","${ctx}/equapply/plmEquApplyMaintenance/notCommit?type=3");
											$("#inputForm").submit();
											}
										});
						var idAndNum = "";
						var flag = true; //是否是添加
						$("#addEqu").on("click", "a", function() {
							if ($(this).attr("title") == "delete") {
								$(this).parent().parent().remove();
							}
							if ($(this).attr("title") == "num") {
								idAndNum = $(this).next().val();
								$(this).next().attr("id", idAndNum);
								$("#editNum").dialog("open");
							}
						});
					});
</script>
</head>
<body>
	<br />
	<form:form target="_parent" id="inputForm" modelAttribute="plmEquApply"
		action="${ctx}/equapply/plmEquApplyMaintenance/save?type=3"
		method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="type" />
		<form:hidden path="plmAct.id" />
		<form:hidden path="plmAct.title" />
		<form:hidden path="plmAct.tableName" />
		<form:hidden path="plmAct.tableId" />
		<form:hidden path="plmAct.formUrl" />
		<form:hidden path="procInsId" />
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<sys:message content="${message}" />
		<h2>维护保养申请单</h2>
		
		<table id="tabletop" class="table  ">
			<tr>
				<td class="tabletop" colspan="3">申请人：<c:if
						test="${empty plmEquApply.user.id}">
						<sys:treeselect id="user" name="user.id"
							value="${fns:getUser().id}" labelName="user.name"
							labelValue="${fns:getUser().name}" title="用户"
							url="/sys/office/treeData?type=3" cssClass="" allowClear="true"
							notAllowSelectParent="true" />
					</c:if> <c:if test="${not empty plmEquApply.user.id}">
					${plmEquApply.user.name }
				</c:if></td>
				<td class="tabletop" colspan="3">申请人所在部门：<c:if
						test="${empty plmEquApply.user.id}">
				${fns:getUser().office.name}
				</c:if> <c:if test="${not empty plmEquApply.user.id}">
				${plmEquApply.user.office.name}
				</c:if></td>
				<td class="tabletop" colspan="3">申请日期：<input type="text"
					name="applyDate" id="today" readonly="readonly"  /> <script
						type="text/javascript">
						function today() {
							var today = new Date();
							var h = today.getFullYear();
							var m = today.getMonth() + 1;
							var d = today.getDate();
							return h + "-" + m + "-" + d;
						}
						document.getElementById("today").value = today();
					</script></td>
			</tr>
		</table>
		<table id="table" class="table   table-condensed"
			>
			<tr>
				<td class="trtop">申请原因<span class="help-inline"><font
						color="red">*</font> </span>
				</td>
				<td colspan="8"><form:textarea path="applyBody"
						htmlEscape="false" rows="6" class="input-xxlarge required"
						cssStyle="width:76.296%" /></td>
			</tr>
			<tr>
				<td class="trtop">备注</td>
				<td colspan="8"><form:textarea path="remarks"
						htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "
						cssStyle="width:76.296%" /></td>
			</tr>
			<tr>
				<td class="trtop" colspan="9">物资编号 <input type="text"
					id="equipmentCode" class="input-xlarge" style="margin-left: 25px;"
					placeholder="请输入具体物资编号..." />&nbsp;
					<input id="ascertain" 
					style="color: #fff; background-color: #19a7f0; width: 54px; height: 30px; background-repeat: no-repeat;"
					class="btn btn-inverse" type="button" 	value="确定" />
					</td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">物资编号</td>
				<td class="trtop">物资名称</td>
				<td class="trtop">规格型号</td>
				<td class="trtop">物资类别</td>
				<td class="trtop">物资子类</td>
				<td class="trtop">生产日期</td>
				<td class="trtop">操作</td>
			</tr>
			<tbody id="addEqu">	
			<c:if test="${not empty plmEquApply.id}">
			<c:forEach items="${plmEquEquipmentList}" var="plmEquEquipmentList">
			<tr>
				<td class="trtop"  colspan="2">${plmEquEquipmentList.code}</td>
				<td class="trtop">${plmEquEquipmentList.name}</td>
				<td class="trtop">${plmEquEquipmentList.spec}</td>
				<td class="trtop">${fns:getDictLabel(plmEquEquipmentList.typeId, 'plm_equipment_type', '')}
				</td>
				<td class="trtop">${fns:getDictLabel(plmEquEquipmentList.typeChild, 'plm_equipment_type_child', '')}
				</td>
				<td class="trtop"><fmt:formatDate value="${plmEquipmentList.plmEquEquipmentList}" pattern="yyyy-MM-dd" /></td>
				<td  class="trtop" colspan="1"><a title="deleteDetail">删除</a>&nbsp;
						<input id="${plmEquEquipmentList.id}" type="hidden" value="${plmEquEquipmentList.id}"/></td>
					</tr>
				</c:forEach>
			</c:if>
			</tbody>		
			<tr>
				<td class="trtop" colspan="2" style="width: 20%">是否督办</td>
				<td id="isSubTd" colspan="8"><form:radiobuttons
						path="plmAct.isSup" items="${fns:getDictList('yes_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" class="" />
				</td>
				<td class="trtop isSup" colspan="2" style="width: 20%">督办人</td>
				<td class="isSup" colspan="2" style="width: 30%"><sys:treeselect
						id="supExe" name="plmAct.supExe.id"
						value="${plmEquApply.plmAct.supExe.id}"
						labelName="plmAct.supExe.name"
						labelValue="${plmEquApply.plmAct.supExe.name}" title="用户"
						url="/sys/office/treeData?type=3" cssClass="" allowClear="true"
						notAllowSelectParent="true"  isAll="true"/></td>
			</tr>
			<tr class="isSup">
				<td class="trtop" colspan="2">督办明细</td>
				<td colspan="8"><form:textarea path="plmAct.supDetail"
						htmlEscape="false" rows="4" maxlength="256" class="input-xxlarge " />
				</td>
			</tr>
			<act:histoicTable procInsId="${plmEquApply.procInsId}" colspan="7"
				titleColspan="2" />
			<c:if test="${not empty plmEquApply.procInsId}">
				<tr>
					<td class="trtop" colspan="2">修改备注</td>
					<td colspan="8"><form:textarea path="act.comment"
							htmlEscape="false" rows="5" maxlength="255"
							class="input-xxlarge " /></td>
				</tr>
			</c:if>
		</table>
		<div class="form-actions">
				<a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-print"></i>提交申请</a>&nbsp;
				<c:if test="${not empty plmEquApply.procInsId}">
					<a id="btnSubmit2" class="btn btn-primary" href="javascript:;"><i class="icon-minus-sign"></i>销毁申请</a>&nbsp;
					</c:if>
					<c:if test="${ empty plmEquApply.procInsId}">
				<a id="btnSubmit3" class="btn btn-primary" href="javascript:;"><i class="icon-ok"></i>保存</a>&nbsp;
					</c:if>
			<c:if test="${not empty plmEquApply.id}">
			<a id="btnCancel" class="btn" href="javascript:;" onclick="history.go(-1)" ><i class="icon-reply"></i>返回</a>
			</c:if>
			<c:if test="${empty plmEquApply.id}">
			<a id="btnCancelf" class="btn btn-primary" href="javascript:;" onclick="parent.layer.closeAll();" ><i class="icon-remove-circle"></i>关闭</a>
			</c:if>
		</div>
	</form:form>
</body>
</html>