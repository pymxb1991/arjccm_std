<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>政府项目采购申请管理</title>
	<meta name="decorator" content="default"/>
  
	<link href="${ctxStatic}/common/zztable.css" type="text/css" rel="stylesheet">
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
			$("#btn").on("click", function(){
					$("#inputForm").attr("action","${ctx}/act/plmAct/cancelApply");
					$("#inputForm").submit();
			});
		});
	
		/* pdf下载 */
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
		    form.attr("action",'${ctx}/purchase/plmPurchaseApply/printPdfIo');
            
		    var input1 = $('<input>');//将你请求的数据模仿成一个input表单
		    input1.attr('type', 'hidden');
		    input1.attr('name', 'id');//该输入框的name
		    input1.attr('value','${plmPurchaseApply.id}');//该输入框的值
		    
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
	      
	<form:form id="inputForm" modelAttribute="plmPurchaseApply" action="${ctx}/purchase/plmPurchaseApply/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="procInsId" />
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<sys:message content="${message}"/>		
		
		
		
		  <h2>政府项目采购申请单</h2>	  
		  <!--pdf下载   --> 
		  <div style="text-align: right; "> <a onclick="downloadFile()"><i class="icon-download"></i>下载</a></div>	  
		    <!--/pdf下载   -->	  	
	    <table  id="tabletop" class="table  ">
		<tr>
			<td class="tabletop" colspan="3" >    </td>
				
		</tr>
		<tr>
		   	<td class="tabletop" colspan="2" >申请人： <u> &nbsp;&nbsp; &nbsp;&nbsp;<span>${plmPurchaseApply.createBy.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;</u>  </td>			
			<td class="tabletop" colspan="2" > 采购项目部门： <u> &nbsp;&nbsp; &nbsp;&nbsp;<span>${plmPurchaseApply.office.name} </span>&nbsp;&nbsp;&nbsp;&nbsp;</u>      </td>
			<td  class="tabletop" colspan="2" >申请日期： <u> &nbsp;&nbsp; &nbsp;&nbsp;<span><fmt:formatDate value="${plmPurchaseApply.applyDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>&nbsp;&nbsp;&nbsp;&nbsp;</u>  </td>	
				<td class="tabletop" colspan="2" >项目编号： <u> &nbsp;&nbsp; &nbsp;&nbsp;<span>${plmPurchaseApply.applyId}</span>&nbsp;&nbsp;&nbsp;&nbsp;</u>  </td>			
		</tr>
		</table>
		<table id="table" class="table   table-condensed">
		<tr>
			<td class="trtop">经费项目名称</td>
			<td colspan="5" >${plmPurchaseApply.fundingName}</td>			
		</tr>
		<tr>
			<td class="trtop">经费卡号</td>
			<td colspan="2" >${plmPurchaseApply.fundingCard}</td>
			<td class="trtop">经费类型</td>
			<td colspan="2" ><c:forEach items="${fns:getDictList('purchase_funding_type')}" var="fundingType">
				<c:if test="${fundingType.value==plmPurchaseApply.fundingType }">
				${fundingType.label}
				</c:if>				
				</c:forEach></td>			
		</tr>
		
		<tr>
			<td class="trtop">采购项目名称</td>
			<td colspan="2" >${plmPurchaseApply.name}</td>		
			<td class="trtop">采购项目预算</td>
			<td colspan="2" >${plmPurchaseApply.purBudget}	万元</td>			
		</tr>
		<tr>
			<td rowspan="2" class="trtop tabletd1">采购项目负责人</td>
			<td class="tabletd2 trtop">姓名</td>
			<td class="tabletd3 ">${plmPurchaseApply.userPur.name}
					</td>
			<td rowspan="2" class="trtop tabletd2">项目技术负责人</td>
			<td class="tabletd5 trtop">姓名</td>
			<td class="tabletd6 ">${plmPurchaseApply.userTech.name} 				
				</td>
		</tr>
		<tr>		
			<td class=" trtop">联系电话</td>
			<td>${plmPurchaseApply.userPur.phone}</td>			
			<td class=" trtop">联系电话/手机/邮箱</td>
			<td><c:if test="${not empty plmPurchaseApply.userTech.phone}" var="e"> ${plmPurchaseApply.userTech.phone}</c:if>
				<c:if test="${!e&&not empty plmPurchaseApply.userTech.mobile}" var="f">${plmPurchaseApply.userTech.mobile}</c:if>
				<c:if test="${!e&&!f}" >${plmPurchaseApply.userTech.email}</c:if></td>
		</tr> 
		<tr>
			<td class="trtop">采购项目概括</td>
			<td class="pingshen" colspan="5"> ${plmPurchaseApply.describes}</td>
			
		</tr>
		<tr>
			<td class="trtop">附件</td>
			<td colspan="5" ><form:hidden id="files" path="files" htmlEscape="false" maxlength="256" class="input-xlarge"/> 
				<sys:ckfinder  input="files" type="files" uploadPath="/purchase/plmPurchaseApply" selectMultiple="true" readonly="true"/></td>
				
		</tr>
		<tr>
				<td class="trtop" colspan="1" style="width: 20%">是否督办</td>
				<td colspan="2" style="width: 30%">${fns:getDictLabel(plmPurchaseApply.plmAct.isSup, 'yes_no', '')}</td>
				<td class="trtop" colspan="1" style="width: 20%">督办人</td>
				<td colspan="2" style="width: 30%">${plmPurchaseApply.plmAct.supExe.name}</td>
			</tr>		
			<tr>
				<td class="trtop" colspan="1">督办明细</td>
				<td colspan="5">${plmPurchaseApply.plmAct.supDetail}</td>
			</tr>			
		<!-- 自定义标签   把流转信息用纸质表的方式显示   colspan:表格意见部分跨列数    WEB-INF/tags/act/histoicTable.tag   -->
		
			<act:histoicTable procInsId="${plmPurchaseApply.act.procInsId}" colspan="5" titleColspan="1"  />
	
	</table>
	
		<%-- <div class="control-group">
			<label class="control-label">附件：</label>
			<div class="controls">
				<form:hidden id="files" path="files" htmlEscape="false" maxlength="256" class="input-xlarge"/>
				<sys:ckfinder input="files" type="files" uploadPath="/purchase/plmPurchaseApply" selectMultiple="true"/>
			</div>
		</div> --%>
		
		<div class="form-actions">
			<c:if test="${cancelFlag == 1}">
				<a id="btn" class="btn" ><i class="icon-undo"></i>撤销</a>&nbsp;
			</c:if>
			<a id="btnCancel" class="btn" href="javascript:;" onclick="history.go(-1)" ><i class="icon-reply"></i>返回</a>
		</div>
		
		
	</form:form>
	   
</body>
</html>