<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采购申报管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/common/zztable.css" type="text/css"
	rel="stylesheet">
	<script type="text/javascript" src="${ctxStatic}/plm/act/supervise.js"></script> 	
<!-- 表格试表单css -->
<link href="${ctxStatic}/common/zzformtable.css" type="text/css"
	rel="stylesheet">
	
	<script type="text/javascript">
	$(document).ready(function() {
		//$("#name").focus();
		$('#btnSubmit').click(function(){
			$('#inputForm').submit();
		});
		$("#inputForm").validate({
			submitHandler: function(form){
				loading('正在提交，请稍等...');
				form.submit();
			},
			errorContainer: "#messageBox",
			errorPlacement: function(error, element) {
				$("#messageBox").text("输入有误，请先更正。");
				if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
					error.appendTo(element.parent().parent());
				} else {
					error.insertAfter(element);
				}
			}
		});
	});
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		
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
		    form.attr("action",'${ctx}/purchase/plmPurchaseDeclare/printPdfIo');
            
		    var input1 = $('<input>');//将你请求的数据模仿成一个input表单
		    input1.attr('type', 'hidden');
		    input1.attr('name', 'id');//该输入框的name
		    input1.attr('value','${plmPurchaseDeclare.id}');//该输入框的值
		    
		    //将表单放置在页面(body)中
		    $("body").append(form);
		    form.append(input1);
		    
		    //表单提交
		    form.submit();
		    		  
		    form.remove();
		} 
	</script>
	<style type="text/css">
	#contentTable th{
	background-color: white;
	text-align: center;
	font-weight: 800;
	border-color:black;
	border-width: 1px 0px 1px 1px ;
	}
	#contentTable {
	border: 0px ;
	border-radius:0px;
	margin-bottom: 0px
	}
	#contentTable td{
	border-width: 1px 0px 0px 1px ;
	text-align: center;
	}
	
	</style>
</head>
<body>
	
	<form:form   id="inputForm" modelAttribute="plmPurchaseDeclare" action="${ctx}/purchase/plmPurchaseDeclare/saveAudit" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="plmAct.id"/>
		<form:hidden path="plmAct.title"/>		
		<form:hidden path="procInsId" />
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<sys:message content="${message}"/>		
      <h2>采购申报申请单</h2>
        
		<table id="tabletop" class="table">
			<tr>
				<td class="tabletop" colspan="2" width="25%">申请人 </span>:&nbsp;&nbsp; &nbsp;&nbsp;<span>${plmPurchaseDeclare.createBy.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td class="tabletop" colspan="2" width="25%">所属部门 </span>: &nbsp;&nbsp; &nbsp;&nbsp;<span>${plmPurchaseDeclare.depart.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td class="tabletop" colspan="2">申请日期：				    
					<fmt:formatDate value="${plmPurchaseDeclare.applyDate}" pattern="yyyy-MM-dd HH:mm:ss" />			 
					</td>
				<td class="tabletop" colspan="2" width="25%">申请编号(系统生成):&nbsp;&nbsp; &nbsp;&nbsp;<span>${plmPurchaseDeclare.applyId}</span>&nbsp;&nbsp; &nbsp;&nbsp;</td>
			</tr>
		</table>
		<table id="table" class="table table-condensed">
			
			<!-- <tr>
			<td class="trtop"  colspan="8">采购物品详细</td>
			
			</tr> -->
			<tr>					
			<td colspan="8" style="padding: 0px; ">
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th style="border-left: 0px; ">名称<font color="red">*</font></th>
								<th>型号</th>
								<th>数量<font color="red">*</font></th>
								<th>申报金额(元)<font color="red">*</font></th>
								<th>核实金额(元)</th>
								<th>存放地点</th>
								<th>使用人</th>
								<!-- <th>附件</th> -->
								
								
							</tr>
						</thead>
						<tbody id="plmPurchaseDeclareDetailList">
						</tbody>
						
					</table>
					
					
					<script type="text/template" id="plmPurchaseDeclareDetailTpl">//<!--
						<tr id="plmPurchaseDeclareDetailList{{idx}}">						
							<td style="border-left: 0px; ">
								{{row.name}}
							</td>
							<td>
                              {{row.spec}}
							</td>
							<td>
								{{row.number}}
							</td>
							<td>
								{{row.declareMoney}}
							</td>
							<td>
								{{row.verifyMoney}}
							</td>
							<td>
								{{row.place}}
							</td>
							<td>
								{{row.user.name}}
							</td>													
							
						</tr>//-->
					</script>
					<script type="text/javascript">
						var plmPurchaseDeclareDetailRowIdx = 0, plmPurchaseDeclareDetailTpl = $("#plmPurchaseDeclareDetailTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(plmPurchaseDeclare.plmPurchaseDeclareDetailList)};
							for (var i=0; i<data.length; i++){
								addRow('#plmPurchaseDeclareDetailList', plmPurchaseDeclareDetailRowIdx, plmPurchaseDeclareDetailTpl, data[i]);
								plmPurchaseDeclareDetailRowIdx = plmPurchaseDeclareDetailRowIdx + 1;
							}
						});
					</script>
			
			</td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">申报合计(元)<span class="help-inline"></span></td>
				<td colspan="2">${plmPurchaseDeclare.declareTotal} </td>
				<td class="trtop" colspan="2">核实合计(元)<span class="help-inline"></span></td>
				<td colspan="2"> ${plmPurchaseDeclare.verifyTotal}   </td>
			</tr>
			<tr>
				<td class="trtop" colspan="2">采购原由</td>
				<td class="pingshen" colspan="6">
				            ${plmPurchaseDeclare.describes}                 
				</td>				
			</tr>
			<tr>
				<td class="trtop" colspan="2">备注</td>
				<td class="pingshen" colspan="6">
				   ${plmPurchaseDeclare.remarks}                
				</td>				
			</tr>
			<tr>
				<td class="trtop" colspan="2">附件（采购清单等）</td>
				<td colspan="6"><form:hidden id="files" path="files"
						htmlEscape="false" maxlength="256" class="input-xlarge" /> <sys:ckfinder
						input="files" type="files" uploadPath="/purchase/plmPurchaseDeclare"
						selectMultiple="true"  readonly="true"/>  </td>
                   
			</tr>
			
			<c:if test="${plmPurchaseDeclare.plmAct.isSup eq '0'}">
				<tr>
					<td class="trtop" colspan="2">是否督办</td>
					<td colspan="6">${fns:getDictLabel(plmPurchaseDeclare.plmAct.isSup, 'yes_no', '')}</td>
				</tr>	
			</c:if>
			<c:if test="${plmPurchaseDeclare.plmAct.isSup eq '1'}">
				<tr>
					<td class="trtop" colspan="2">是否督办</td>
					<td colspan="2">${fns:getDictLabel(plmPurchaseDeclare.plmAct.isSup, 'yes_no', '')}</td>
					<td class="trtop" colspan="2">督办人</td>
					<td colspan="2">${plmPurchaseDeclare.plmAct.supExe.name}</td>
				</tr>		
				<tr>
					<td class="trtop" colspan="2">督办明细</td>
					<td colspan="6">${plmPurchaseDeclare.plmAct.supDetail}</td>
				</tr>
			</c:if>	
				<!-- 自定义标签   把流转信息用纸质表的方式显示   colspan:表格意见部分跨列数    WEB-INF/tags/act/histoicTable.tag   -->		
			<act:histoicTable procInsId="${plmPurchaseDeclare.procInsId}" colspan="6" titleColspan="2"/>
		   	<c:if test="${rejectedBtn}">
			<c:if test="${plmPurchaseDeclare.plmAct.isSup ne '1'}">
				<tr>
					<td class="trtop" colspan="2">是否添加督办</td>
					<td id="isSubTd" colspan="2">
						<form:radiobuttons path="plmAct.isSup" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
					</td>
					<td class="trtop isSup" colspan="2">督办人</td>
					<td class="isSup" colspan="2">
						<sys:treeselect id="supExe" name="plmAct.supExe.id" value="${plmPurchaseDeclare.plmAct.supExe.id}" labelName="plmAct.supExe.name" labelValue="${plmPurchaseDeclare.plmAct.supExe.name}"
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
				<td class="trtop" colspan="2">您的意见</td>
				<td class="pingshen" colspan="6">
				   <form:textarea path="act.comment" htmlEscape="false" rows="5" maxlength="255" class="input-xxlarge "/>
                   
				</td>				
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