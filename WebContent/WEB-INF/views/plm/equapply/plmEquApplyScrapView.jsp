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
								}

						);

				$("td").css({
					"padding" : "1%"
				});
				$("#btn").on("click", function(){
						$("#inputForm").attr("action","${ctx}/act/plmAct/cancelApply");
						$("#inputForm").submit();
				});
			});
	//根据html模版 pdf下载       url表示请求路径,进入后台处理,后台返回一个文件流		
	function downloadFile(){

	    //定义一个form表单,通过form表单来发送请求
	    var form=$("<form>");

	    //设置表单状态为不显示
	    form.attr("style","display:none");

	    //method属性设置请求类型为get
	    form.attr("method","get");

	    //action属性设置请求路径,(如有需要,可直接在路径后面跟参数)
	    //例如:htpp://127.0.0.1/test?id=123
	    form.attr("action",'${ctx}/equapply/plmEquApplyScrap/printPdfIo');
        
	    var input1 = $('<input>');//将你请求的数据模仿成一个input表单
	    input1.attr('type', 'hidden');
	    input1.attr('name', 'id');//该输入框的name
	    input1.attr('value','${plmEquApply.id}');//该输入框的值
	    
	    //将表单放置在页面(body)中
	    $("body").append(form);
	    form.append(input1);
	    
	    //表单提交
	    form.submit();
	    		  
	    form.remove();
	} 
</script>
</head>
<body>
	<br />
	<form:form id="inputForm" modelAttribute="plmEquApply"
		action="${ctx}/equapply/plmEquApplyScrap/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="procInsId" />
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<sys:message content="${message}" />
		<h2>装备报废申请单</h2>
		<div style="text-align: right; ">       <a onclick="downloadFile()"><i class="icon-download"></i>下载</a></div>
		<table id="tabletop" class="table">
			<tr>
				<td class="tabletop" colspan="2">申请人：<u>&nbsp;&nbsp;
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
		<table id="table" class="table   table-condensed">
			<tr>
				<td class="trtop">物资编号</td>
				<td class="trtop">物资名称</td>
				<td class="trtop">规格型号</td>
				<td class="trtop">物资类别</td>
				<td class="trtop">物资子类</td>
				<td class="trtop">生产日期</td>
			</tr>
			<c:forEach items="${plmEquEquipmentList}" var="plmEquEquipmentList">
			<tr>
				<td class="trtop">${plmEquEquipmentList.code}</td>
				<td class="trtop">${plmEquEquipmentList.name}</td>
				<td class="trtop">${plmEquEquipmentList.spec}</td>
				<td class="trtop">${fns:getDictLabel(plmEquEquipmentList.typeId, 'plm_equipment_type', '')}
				</td>
				<td class="trtop">${fns:getDictLabel(plmEquEquipmentList.typeChild, 'plm_equipment_type_child', '')}
				</td>
				<td class="trtop"><fmt:formatDate
						value="${plmEquEquipmentList.productionDate}"
						pattern="yyyy-MM-dd" /></td>
			</tr>
			</c:forEach>
			<tr>
				<td class="trtop">申请原因<span class="help-inline"><font
						color="red">*</font> </span>
				</td>
				<td colspan="5"><form:textarea path="applyBody"
						htmlEscape="false" rows="4" class="input-xxlarge "
						cssStyle="width:76.296%" /></td>
			</tr>
			<tr>
				<td class="trtop">备注</td>
				<td colspan="5"><form:textarea path="remarks"
						htmlEscape="false" rows="6" maxlength="255" class="input-xxlarge "
						cssStyle="width:76.296%" /></td>
			</tr>
			<tr>
				<td class="trtop">是否督办</td>
				<td colspan="2">${fns:getDictLabel(plmEquApply.plmAct.isSup, 'yes_no', '')}</td>
				<td class="trtop">督办人</td>
				<td colspan="2">${plmEquApply.plmAct.supExe.name}</td>
			</tr>
			<tr>
				<td class="trtop">督办明细</td>
				<td colspan="5">${plmEquApply.plmAct.supDetail}</td>
			</tr>
			<act:histoicTable procInsId="${plmEquApply.procInsId}" colspan="5"
				titleColspan="1" />
		</table>
		<div class="form-actions">
			<c:if test="${cancelFlag == 1}">
				<a id="btn" class="btn" ><i class="icon-undo"></i>撤销</a>&nbsp;
			</c:if>
			<a id="btnCancel" class="btn" href="javascript:;" onclick="history.go(-1)" ><i class="icon-reply"></i>返回</a>
		</div>
	</form:form>
</body>
</html>