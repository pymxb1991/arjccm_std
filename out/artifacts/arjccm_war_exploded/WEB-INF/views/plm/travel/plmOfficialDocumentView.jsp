<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公文管理</title>
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
	    form.attr("action",'${ctx}/travel/plmOfficialDocument/printPdfIo');
        
	    var input1 = $('<input>');//将你请求的数据模仿成一个input表单
	    input1.attr('type', 'hidden');
	    input1.attr('name', 'id');//该输入框的name
	    input1.attr('value','${plmOfficialDocument.id}');//该输入框的值
	    
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
	<form:form id="inputForm"  modelAttribute="plmOfficialDocument" action="" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="procInsId"/>
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<sys:message content="${message}"/>		
		<h2>公文收发申请单</h2>
		<div style="text-align: right; ">       <a onclick="downloadFile()"><i class="icon-download"></i>下载</a></div>
		<table id="tabletop" class="table">
			<tr>
				<td class="tabletop" colspan="3">申请编号:<u>&nbsp;&nbsp; &nbsp;&nbsp;<span>${plmOfficialDocument.code}</span>&nbsp;&nbsp; &nbsp;&nbsp;</u></td>
				<td class="tabletop" colspan="2">密级:<u> &nbsp;&nbsp; &nbsp;&nbsp;<span>${fns:getDictLabel(plmOfficialDocument.confidentiality, 'confident_type', '未选择')}</span>&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
				<td class="tabletop" colspan="3">申请日期： <u> &nbsp;&nbsp; &nbsp;&nbsp;<span><fmt:formatDate value="${plmOfficialDocument.date}" pattern="yyyy-MM-dd"/></span>&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
			</tr>
		</table>
		<table id="table" class="table table-condensed">
			<tr>
				<td class="trtop" colspan="2">标题</td>
				<td colspan="6">${plmOfficialDocument.title}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">文案类型</td>
				<td colspan="2" style="width: 25%">${fns:getDictLabel(plmOfficialDocument.documentType, 'official_doc_type', '未选择')}</td>
				<td class="trtop" colspan="2" style="width: 25%">保密期限</td>
				<td colspan="2">${fns:getDictLabel(plmOfficialDocument.deadline, 'deadline_type', '未选择')}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">紧急程度</td>
				<td colspan="2">${fns:getDictLabel(plmOfficialDocument.urgent, 'urgent_type', '未选择')}</td>
				<td class="trtop" colspan="2">发送单位</td>
				<td colspan="2">${plmOfficialDocument.fromDept.name}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">联合行文单位</td>
				<td colspan="2">${plmOfficialDocument.associatedDept.name}</td>
				<td class="trtop" colspan="2">接收单位</td>
				<td colspan="2">${plmOfficialDocument.toDept.name}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">备注信息</td>
				<td colspan="6">${plmOfficialDocument.remarks}</td>
			</tr>
			<act:histoicTable procInsId="${plmOfficialDocument.procInsId}" colspan="6" titleColspan="2"/>
			<tr>
				<td class="trtop" colspan="2" style="width: 20%">是否督办</td>
				<td colspan="2" style="width: 30%">${fns:getDictLabel(plmOfficialDocument.plmAct.isSup, 'yes_no', '')}</td>
				<td class="trtop" colspan="2" style="width: 20%">督办人</td>
				<td colspan="2" style="width: 30%">${plmOfficialDocument.plmAct.supExe.name}</td>
			</tr>		
			<tr>
				<td class="trtop" colspan="2">督办明细</td>
				<td colspan="6">${plmOfficialDocument.plmAct.supDetail}</td>
			</tr>			
			<tr>
				<td class="trtop" colspan="2">附件</td>
				<td colspan="6">
					<form:hidden id="file" path="file" htmlEscape="false" maxlength="4000" class="input-xlarge"/>
					<sys:ckfinder input="file" type="files" uploadPath="/travel/plmOfficialDocument" selectMultiple="false" readonly="true"/>
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