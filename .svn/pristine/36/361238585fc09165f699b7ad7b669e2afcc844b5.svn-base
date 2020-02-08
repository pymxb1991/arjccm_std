<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>借款申请管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/common/zztable.css" type="text/css"
	rel="stylesheet">
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
	    form.attr("action",'${ctx}/travel/plmBorrowMoney/printPdfIo');
        
	    var input1 = $('<input>');//将你请求的数据模仿成一个input表单
	    input1.attr('type', 'hidden');
	    input1.attr('name', 'id');//该输入框的name
	    input1.attr('value','${plmBorrowMoney.id}');//该输入框的值
	    
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
	<form:form id="inputForm" modelAttribute="plmBorrowMoney" action="${ctx}/travel/plmBorrowMoney/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="procInsId"/>
		<sys:message content="${message}"/>		
		<h2>借款申请单</h2>
		<div style="text-align: right; ">       <a onclick="downloadFile()"><i class="icon-download"></i>下载</a></div>
		<table id="tabletop" class="table">
			<tr>
				<td class="tabletop" colspan="2" width="33.33%">申请人:&nbsp;&nbsp; &nbsp;&nbsp;<span>${plmBorrowMoney.applyer.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td class="tabletop" colspan="2" width="33.33%">所属部门: &nbsp;&nbsp; &nbsp;&nbsp;<span>${plmBorrowMoney.department.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td class="tabletop" colspan="2" width="33.33%">申请编号(系统生成):&nbsp;&nbsp; &nbsp;&nbsp;<span>${plmBorrowMoney.code}</span>&nbsp;&nbsp; &nbsp;&nbsp;</td>
			</tr>
		</table>
		<table id="table" class="table table-condensed">
			<tr>
				<td class="trtop" colspan="1">主题</td>
				<td colspan="5">${plmBorrowMoney.title}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="1">借款时间</td>
				<td colspan="1"><fmt:formatDate value="${plmBorrowMoney.borrowDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td class="trtop" colspan="1">截止日期</td>
				<td colspan="1"><fmt:formatDate value="${plmBorrowMoney.deadlineDate}" pattern="yyyy-MM-dd"/></td>
				<td class="trtop" colspan="1">借款总额</td>
				<td colspan="1">${plmBorrowMoney.sum}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="1">借款原因</td>
				<td colspan="5">${plmBorrowMoney.cause}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="1">借款用途</td>
				<td colspan="5">${plmBorrowMoney.purpose}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="1">账号</td>
				<td colspan="2">${plmBorrowMoney.accountCode}</td>
				<td class="trtop" colspan="1">账户名</td>
				<td colspan="2">${plmBorrowMoney.accountName}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="1">备注信息</td>
				<td colspan="5">${plmBorrowMoney.remarks}</td>
			</tr>
			<c:if test="${not empty plmBorrowMoney.procInsId}">
				<act:histoicTable procInsId="${plmBorrowMoney.procInsId}"  colspan="6" titleColspan="1"/>
			</c:if>
			
			<tr>
				<td class="trtop" colspan="1">附件</td>
				<td colspan="5">
					<form:hidden id="file" path="file" htmlEscape="false" maxlength="4000" class="input-xlarge"/>
					<sys:ckfinder input="file" type="files" uploadPath="/travel/plmBorrowMoney" selectMultiple="false" readonly="true"/>
				</td>
			</tr>
			<tr>
				<td class="trtop" colspan="1" style="width: 20%">是否督办</td>
				<td colspan="2" style="width: 30%">${fns:getDictLabel(plmBorrowMoney.plmAct.isSup, 'yes_no', '')}</td>
				<td class="trtop" colspan="1" style="width: 20%">督办人</td>
				<td colspan="2" style="width: 30%">${plmBorrowMoney.plmAct.supExe.name}</td>
			</tr>		
			<tr>
				<td class="trtop" colspan="1">督办明细</td>
				<td colspan="5">${plmBorrowMoney.plmAct.supDetail}</td>
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