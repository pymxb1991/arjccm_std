<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>维修申请管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/common/zztable.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctxStatic}/plm/car/plmCarUseForm.js"></script> 
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
			    form.attr("action",'${ctx}/car/apply/plmCarApplyRepair/printPdfIo');
	            
			    var input1 = $('<input>');//将你请求的数据模仿成一个input表单
			    input1.attr('type', 'hidden');
			    input1.attr('name', 'id');//该输入框的name
			    input1.attr('value','${plmCarApplyRepair.id}');//该输入框的值
			    
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
	<form:form id="inputForm" style="margin: 30px 100px;" modelAttribute="plmCarApplyRepair" action="${ctx}/car/apply/plmCarApplyRepair/save" method="post" class="form-horizontal">
		<form:hidden path="procInsId"/>
		<sys:message content="${message}"/>	
		<h2>维修(保养)申请单</h2>	
		<div style="text-align: right; ">       <a onclick="downloadFile()"><i class="icon-download"></i>下载</a></div>	  	  	
	    <table id="tabletop" class="table">
			<tr>
				<td class="tabletop" colspan="2" >申请编号:<u>&nbsp;&nbsp; &nbsp;&nbsp;<span>${plmCarApplyRepair.title}</span></u></td>
				<td class="tabletop" colspan="2">申请人:<u> &nbsp;&nbsp; &nbsp;&nbsp;<span>${plmCarApplyRepair.user.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
				<td class="tabletop" colspan="2">所属部门:<u> &nbsp;&nbsp; &nbsp;&nbsp;<span>${plmCarApplyRepair.user.office.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
				<td class="tabletop" colspan="2">申请日期： <u> &nbsp;&nbsp; &nbsp;&nbsp;<span><fmt:formatDate value="${plmCarApplyRepair.createDate}" pattern="yyyy-MM-dd"/></span>&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
			</tr>
		</table>	
		<table id="table" class="table table-condensed">
			<tr>
				<td class="trtop" colspan="2" style="width: 20%">车牌号</td>
				<td colspan="2" style="width: 30%">${plmCarApplyRepair.car.vehicle}</td>
				<td class="trtop" colspan="2" style="width: 20%">维保单位</td>
				<td colspan="2" style="width: 30%">${plmCarApplyRepair.repairCom.name}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">品牌</td>
				<td colspan="2">${plmCarApplyRepair.car.brand}</td>
				<td class="trtop" colspan="2">联系人</td>
				<td colspan="2">${plmCarApplyRepair.repairCom.leader}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">型号</td>
				<td colspan="2">${plmCarApplyRepair.car.vmodel}</td>
				<td class="trtop" colspan="2">联系电话</td>
				<td colspan="2">${plmCarApplyRepair.repairCom.phone}</td>
			</tr>		
			<tr>
				<td class="trtop" colspan="2">计划维修(保养)日期</td>
				<td colspan="6"><fmt:formatDate value="${plmCarApplyRepair.repairDate}" pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">维修(保养)原因</td>
				<td colspan="6">${plmCarApplyRepair.remarks}</td>
			</tr>	
			<tr>
				<td class="trtop" colspan="2">是否督办</td>
				<td colspan="2">${fns:getDictLabel(plmCarApplyRepair.plmAct.isSup, 'yes_no', '')}</td>
				<td class="trtop" colspan="2">督办人</td>
				<td colspan="2">${plmCarApplyRepair.plmAct.supExe.name}</td>
			</tr>		
			<tr>
				<td class="trtop" colspan="2">督办明细</td>
				<td colspan="6">${plmCarApplyRepair.plmAct.supDetail}</td>
			</tr>			
			<act:histoicTable procInsId="${plmCarApplyRepair.procInsId}" colspan="6" titleColspan="2"/>
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