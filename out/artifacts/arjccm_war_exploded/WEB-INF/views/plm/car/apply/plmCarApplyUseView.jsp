<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用车申请管理</title>
	<meta name="decorator" content="default"/>
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
			    form.attr("action",'${ctx}/car/apply/plmCarApplyUse/printPdfIo');
	            
			    var input1 = $('<input>');//将你请求的数据模仿成一个input表单
			    input1.attr('type', 'hidden');
			    input1.attr('name', 'id');//该输入框的name
			    input1.attr('value','${plmCarApplyUse.id}');//该输入框的值
			    
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
	<form:form id="inputForm" style="margin: 30px 100px;" modelAttribute="plmCarApplyUse" action="${ctx}/car/apply/plmCarApplyUse/save" method="post" class="form-horizontal">
		<sys:message content="${message}"/>	
		<form:hidden path="procInsId"/>
		<h2>用车申请单</h2>	
		<div style="text-align: right; ">       <a onclick="downloadFile()">下载</a></div>	  	  	
	    <table id="tabletop" class="table">
			<tr>
				<td class="tabletop" colspan="2" >申请编号:<u>&nbsp;&nbsp; &nbsp;&nbsp;<span>${plmCarApplyUse.title}</span></u></td>
				<td class="tabletop" colspan="2">申请人:<u> &nbsp;&nbsp; &nbsp;&nbsp;<span>${plmCarApplyUse.user.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
				<td class="tabletop" colspan="2">所属部门:<u> &nbsp;&nbsp; &nbsp;&nbsp;<span>${plmCarApplyUse.user.office.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
				<td class="tabletop" colspan="2">申请日期： <u> &nbsp;&nbsp; &nbsp;&nbsp;<span><fmt:formatDate value="${plmCarApplyUse.createDate}" pattern="yyyy-MM-dd"/></span>&nbsp;&nbsp;&nbsp;&nbsp;</u></td>
			</tr>
		</table>	
		<table id="table" class="table table-condensed">
			<tr>
				<td class="trtop" colspan="2">用车事请事由</td>
				<td colspan="6">${plmCarApplyUse.remarks}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">车辆需求描述</td>
				<td colspan="6">${plmCarApplyUse.carDesc}</td>
			</tr>			
			<tr>
				<td class="trtop" colspan="2" style="width: 20%">预选车辆</td>
				<td colspan="2" style="width: 30%">${plmCarApplyUse.car.vehicle}</td>
				<td class="trtop" colspan="2" style="width: 20%">预选司机</td>
				<td colspan="2" style="width: 30%">${plmCarApplyUse.driver.name}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">出车时间</td>
				<td colspan="2"><fmt:formatDate value="${plmCarApplyUse.outDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td class="trtop" colspan="2">归还时间</td>
				<td colspan="2"><fmt:formatDate value="${plmCarApplyUse.inDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">乘车人数</td>
				<td colspan="6">${plmCarApplyUse.num}</td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">是否督办</td>
				<td colspan="2">${fns:getDictLabel(plmCarApplyUse.plmAct.isSup, 'yes_no', '')}</td>
				<td class="trtop" colspan="2">督办人</td>
				<td colspan="2">${plmCarApplyUse.plmAct.supExe.name}</td>
			</tr>		
			<tr>
				<td class="trtop" colspan="2">督办明细</td>
				<td colspan="6">${plmCarApplyUse.plmAct.supDetail}</td>
			</tr>
			<act:histoicTable procInsId="${plmCarApplyUse.procInsId}" colspan="6" titleColspan="2"/>			
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