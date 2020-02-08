<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出差申请表单管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/common/zztable.css" type="text/css" rel="stylesheet">
	<!-- 表格试表单css -->
	<link href="${ctxStatic}/common/zzformtable.css" type="text/css"
	rel="stylesheet">
	<script type="text/javascript">
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
	    form.attr("action",'${ctx}/travel/plmTravelManage/printPdfIo');
        
	    var input1 = $('<input>');//将你请求的数据模仿成一个input表单
	    input1.attr('type', 'hidden');
	    input1.attr('name', 'id');//该输入框的name
	    input1.attr('value','${plmTravelManage.id}');//该输入框的值
	    
	    //将表单放置在页面(body)中
	    $("body").append(form);
	    form.append(input1);
	    
	    //表单提交
	    form.submit();
	    		  
	    form.remove();
	} 		
	$(document).ready(function() {
		$("#btn").on("click", function(){
				$("#inputForm").attr("action","${ctx}/act/plmAct/cancelApply");
				$("#inputForm").submit();
		});
	});
	</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="plmTravelManage" action="" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="procInsId"/>
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<h2>出差申请单</h2>
		<div style="text-align: right; ">       <a onclick="downloadFile()"><i class="icon-download"></i>下载</a></div>
		<table id="tabletop" class="table">
			<tr>
				<td class="tabletop" colspan="2">申请编号:<u>&nbsp;&nbsp; &nbsp;&nbsp;<span>${plmTravelManage.code}</span>&nbsp;&nbsp; &nbsp;&nbsp;</u></td>
				<td class="tabletop" colspan="2">申请人:<u> &nbsp;&nbsp; &nbsp;&nbsp;<span>${plmTravelManage.fldApplicant.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
				<td class="tabletop" colspan="2">所属部门:<u> &nbsp;&nbsp; &nbsp;&nbsp;<span>${plmTravelManage.fldDept.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
				<td class="tabletop" colspan="2">申请日期： <u> &nbsp;&nbsp; &nbsp;&nbsp;<span><fmt:formatDate value="${plmTravelManage.fldDt}" pattern="yyyy-MM-dd HH:mm:ss"/></span>&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
			</tr>
		</table>
		<table id="table" class="table table-condensed">
			<tr>
				<td class="trtop" colspan="2">主题</td>
				<td colspan="6">${plmTravelManage.fldSubject}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">目的地</td>
				<td colspan="2">${plmTravelManage.fldDest}</td>
				<td class="trtop" colspan="2">交通工具</td>
				<td colspan="2">${plmTravelManage.fldTransport}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">出发时间</td>
				<td colspan="2"><fmt:formatDate value="${plmTravelManage.fldBdt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td class="trtop" colspan="2">返回时间</td>
				<td colspan="2"><fmt:formatDate value="${plmTravelManage.fldEdt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">出差天数(天)</td>
				<td colspan="2">${plmTravelManage.fldDays}</td>
				<td class="trtop" colspan="2">借款总额(元)</td>
				<td colspan="2">${plmTravelManage.fldLoan}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">出差路线</td>
				<td colspan="6">${plmTravelManage.fldRoute}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">出差事由</td>
				<td colspan="6">${plmTravelManage.fldReason}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">备注信息</td>
				<td colspan="6">${plmTravelManage.remarks}</td>
			</tr>
			<act:histoicTable procInsId="${plmTravelManage.procInsId}" colspan="6" titleColspan="2"/>
			<tr>
				<td class="trtop" colspan="2" style="width: 20%">是否督办</td>
				<td colspan="2" style="width: 30%">${fns:getDictLabel(plmTravelManage.plmAct.isSup, 'yes_no', '')}</td>
				<td class="trtop" colspan="2" style="width: 20%">督办人</td>
				<td colspan="2" style="width: 30%">${plmTravelManage.plmAct.supExe.name}</td>
			</tr>		
			<tr>
				<td class="trtop" colspan="2">督办明细</td>
				<td colspan="6">${plmTravelManage.plmAct.supDetail}</td>
			</tr>			
			<tr>
				<td class="trtop" colspan="2">附件</td>
				<td colspan="6">
					<form:hidden id="fldAccessory" path="fldAccessory" htmlEscape="false" maxlength="256" class="input-xlarge"/>
					<sys:ckfinder input="fldAccessory" type="files" uploadPath="/travel/plmTravelManage" selectMultiple="false" readonly="true"/>
				</td>
			</tr>
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