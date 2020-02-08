<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>调拨单管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/common/zztable.css" type="text/css"
	rel="stylesheet">
	<!-- 表格试表单css -->
	<%-- <link href="${ctxStatic}/common/zzformtable.css" type="text/css"
	rel="stylesheet"> --%>
	<link href="${ctxStatic}/form/css/formTable.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctxStatic}/plm/act/supervise.js"></script>
	<link rel="stylesheet" href="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.css">
	<script src="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/plm/storage/ajaxMessageAlert.js"></script>
	<script type="text/javascript" src="${ctxStatic}/plm/storage/storageValidate.js"></script>
	<style type="text/css">
	
	 #table .ntable td {
    	border: 0;
	}
	.btn-select {
		color: #fff;
	    background-color: #3bb4f2 !important;
	    border-color: #3bb4f2 !important;
	}
	</style>
	<script type="text/javascript">
	// 自定义Map对象
	  function Map() {

	    this.keys = new Array();
	    this.data = new Object();

	    this.set = function(key, value) {
	        if (this.data[key] == null) {
	            if (this.keys.indexOf(key) == -1) {
	                this.keys.push(key);
	            }
	        }
	        this.data[key] = value;
	    }

	    this.get = function(key) {
	        return this.data[key];
	    }
	}
		var selectRemarkMap = new Map();
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				rules: {
	                sumLow: {
	                	required: true,
						maxlength : 32,
						amount: $("#sumLow").val()    //调用自定义验证
	                }
	            },
	            
	            messages: {
	                invoice: {
	                	required: "必填信息",
	            		maxlength: "最大长度32"
	                }
	            },
				
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
			var detailInfo = $("#selectRemarks").val();
			if(detailInfo !== undefined && detailInfo != null && detailInfo != "") {
				var infos = detailInfo.split("@");
				for (var i = 0; i < infos.length; i++) {
					var details = infos[i].split(":");
					selectRemarkMap.set(details[0],details[1]);
				}
			}
			$("#btnCancel").on("click", function(){
				
				/* confirmx("确定要撤销申请吗？",function(){ */
					$('#flag').val('no');
					$("#inputForm").attr("action","${ctx}/allot/plmAllot/apply");
					$("#inputForm").submit();
				/* }); */
			});
			$("#btnSubmit").on("click", function(){
				$("#selectRemarks").val(mapToJson(selectRemarkMap));
				$("#inputForm").attr("action","${ctx}/allot/plmAllot/save");
				$("#inputForm").submit();
			});
			$("#btnApply").on("click", function(){
				confirmx("提交申请后无法修改申请信息",function(){
					$('#flag').val('yes');
					$("#selectRemarks").val(mapToJson(selectRemarkMap));
					$("#inputForm").attr("action","${ctx}/allot/plmAllot/apply");
					$("#inputForm").submit();
				});
			});
			
			$( "#equSelect" ).dialog({
			    autoOpen: false,
			    closeOnEscape: false,
			    height: 400,
			    width: 850,
			    modal: true,
			    buttons : {
			    	"确定" : function() {
			    		var selectItems = [];
						var itemNodes = $("[name='checkItem']");
						for(var i = 0; i < itemNodes.length; i++) {
							if (itemNodes[i].checked == true) {
								selectItems.push(itemNodes[i].value);
							}
						}
						for(var i = 0; i < selectItems.length; i++) {
							selectRemarkMap.set(selectItems[i],"");
							var htmlVal = $("#"+selectItems[i]).val();
							$("#equipment").append(htmlVal);
						}
						$( this ).dialog( "close" );
						$("#selectBody>tr").remove();
						var dec=$(".detailSum");
						var detailSum=0;
						for (var i = 0; i < dec.length; i++) {				
							detailSum+=Number(dec.eq(i).html());	
						}
						if(detailSum>0){
							$("#sumLow").val(detailSum)
							var cap = changeMoneyToChinese(detailSum);
							$("#sumCap").attr("value",cap);
						}
						
			    	},
			    	"取消" : function() {
			    		$( this ).dialog( "close" );
			    		$("#selectBody>tr").remove();
			    	}
			    },
			    close: function() {
			    	$( this ).dialog( "close" );
			    	$("#selectBody>tr").remove();
			    }
			 });
			$("#btnAdd").on("click", function(){
				var outDepId = $("#outDepId").val();
				if (outDepId === undefined || outDepId == null || outDepId == "") {
					messageAlert("请先选择调出部门！", "error");
					return;
				}
				$.ajax({
					url : "${ctx}/allot/plmAllot/equipmentSelect",
					type : "post",
					data : {"userJob.id":$("#outDepId").val()},
					async : true,
					cache : false,
					timeout : 30000,
					success : function(data) {
						if (data.ret != 0) {				
							messageAlert("显示失败", "error");
							return;
						}
						var html = data.result;
						$("#selectBody").append(html);
						$("#equSelect").dialog( "open" );
						$("#equSelect").css({"width":"98%"});
					},
					error : function(jqXHR, textStatus, errorThrown) {
						messageAlert("显示失败！", "error");
						console.error("jqXHR:", jqXHR);
						console.error("textStatus:", textStatus);
						console.error("errorThrown:", errorThrown);
						
					}
				});
			});
			$("#checkAll").on("click", function(){
				var itemNodes = $("[name='checkItem']");
				if ($(this)[0].checked == true) {
					for(var i = 0; i < itemNodes.length; i++) {
						itemNodes[i].checked = true;
					}
				} else {
					for(var i = 0; i < itemNodes.length; i++) {
						itemNodes[i].checked = false;
					}
				}
			});
			
				  
		});
		function removeSelect(obj) {
			var a = $(obj).parent().prev().children().attr("id");
			selectRemarkMap.delete(a);
			$(obj).parent().parent().remove();
		}
		

		function updateRemark(obj) {
			selectRemarkMap.set($(obj).attr("id"),$(obj).attr("value"));
		}
		
		function mapToJson(map) {
			var json = "";
			var objArray = [];
			
			 var len = map.keys.length;
		        for(var i = 0;i < len;i++){
		            var k = map.keys[i];		            
		            var tmp = k + ":" + map.data[k];
					objArray.push(tmp);
		        }
			/* for (var [key, value] of map) {
				var tmp = key + ":" + value;
				objArray.push(tmp);
			} */
			for(var i = 0; i < objArray.length; i++){
				if (i != 0) {
					json += "@";
				}
				json += objArray[i];
			}
			return json;
		}
		
		function changeMoneyToChinese(money) {
			var cnNums = new Array("零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"); //汉字的数字
			var cnIntRadice = new Array("", "拾", "佰", "仟"); //基本单位
			var cnIntUnits = new Array("", "万", "亿", "兆"); //对应整数部分扩展单位
			var cnDecUnits = new Array("角", "分", "毫", "厘"); //对应小数部分单位
			var cnInteger = "整"; //整数金额时后面跟的字符
			var cnIntLast = "圆"; //整型完以后的单位
			var maxNum = 999999999999999.9999; //最大处理的数字

			var IntegerNum; //金额整数部分
			var DecimalNum; //金额小数部分
			var ChineseStr = ""; //输出的中文金额字符串
			var parts; //分离金额后用的数组，预定义
			if(money == "") {
				return "";
			}
			money = parseFloat(money);
			if(money >= maxNum) {
				$.alert('超出最大处理数字');
				return "";
			}
			if(money == 0) {
				ChineseStr = cnNums[0] + cnIntLast + cnInteger;
				//ChineseStr = cnNums[0]+cnIntLast
				return ChineseStr;
			}
			money = money.toString(); //转换为字符串
			if(money.indexOf(".") == -1) {
				IntegerNum = money;
				DecimalNum = '';
			} else {
				parts = money.split(".");
				IntegerNum = parts[0];
				DecimalNum = parts[1].substr(0, 4);
			}
			if(parseInt(IntegerNum, 10) > 0) { //获取整型部分转换
				zeroCount = 0;
				IntLen = IntegerNum.length;
				for(i = 0; i < IntLen; i++) {
					n = IntegerNum.substr(i, 1);
					p = IntLen - i - 1;
					q = p / 4;
					m = p % 4;
					if(n == "0") {
						zeroCount++;
					} else {
						if(zeroCount > 0) {
							ChineseStr += cnNums[0];
						}
						zeroCount = 0; //归零
						ChineseStr += cnNums[parseInt(n)] + cnIntRadice[m];
					}
					if(m == 0 && zeroCount < 4) {
						ChineseStr += cnIntUnits[q];
					}
				}
				ChineseStr += cnIntLast;
				//整型部分处理完毕
			}
			if(DecimalNum != '') { //小数部分
				decLen = DecimalNum.length;
				for(i = 0; i < decLen; i++) {
					n = DecimalNum.substr(i, 1);
					if(n != '0') {
						ChineseStr += cnNums[Number(n)] + cnDecUnits[i];
					}
				}
			}
			if(ChineseStr == '') {
				ChineseStr += cnNums[0] + cnIntLast + cnInteger;
				//ChineseStr += cnNums[0]+cnIntLast;
			} else if(DecimalNum == '') {
				ChineseStr += cnInteger;
			}
			return ChineseStr;
		}
	</script>
</head>
<body>
	<form:form target="_parent" id="inputForm" modelAttribute="plmAllot" action="${ctx}/allot/plmAllot/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="plmAct.id"/>
		<form:hidden path="plmAct.title"/>
		<form:hidden path="plmAct.tableName"/>
		<form:hidden path="plmAct.tableId"/>
		<form:hidden path="plmAct.formUrl"/>	
		<input name="addDate" type="hidden" value="<fmt:formatDate value="${plmAllot.addDate}" pattern="yyyy-MM-dd"/>"/>
		<form:hidden path="code"/>
		<form:hidden path="applyer.id"/>
		<form:hidden path="procInsId"/>
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden path="selectRemarks"/>
		<form:hidden id="flag" path="act.flag" />
		<sys:message content="${message}"/>		
		<h2>资产内部调拨单</h2>
		<table id="tabletop" class="table">
			<tr>
				<td class="tabletop" colspan="2" width="33.33%">申请人<span class="help-inline"><font color="red">*</font> </span>:
				&nbsp;&nbsp; &nbsp;&nbsp;<span>${plmAllot.applyer.name}</span>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td class="tabletop" colspan="2" width="33.33%">填写日期<span class="help-inline"><font color="red">*</font> </span>: 
				&nbsp;&nbsp; &nbsp;&nbsp;<span><fmt:formatDate value="${plmAllot.addDate}" pattern="yyyy-MM-dd"/></span>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td class="tabletop" colspan="2" width="33.33%">申请编号(系统生成):&nbsp;&nbsp; &nbsp;&nbsp;
				<span>${plmAllot.code}</span>&nbsp;&nbsp; &nbsp;&nbsp;</td>
			</tr>
		</table>
		<table id="table" class="table table-condensed">
			<tr>
				<td class="trtop" colspan="1">标题<span class="help-inline"><font color="red">*</font></span></td>
				<td colspan="5"><form:input path="title" htmlEscape="false" maxlength="255" class="input-xlarge required"/></td>
			</tr>
			<tr>
				<td colspan="6">
					<table class="table ntable table-condensed">
						<tr><td colspan="6"><h5>调出审核单位</h5></tr>
						<tr>
							<td colspan="1">资产调出部门<font color="red">*</font>：</td>
							<td colspan="2"><sys:treeselect id="outDep" name="outDep.id" value="${plmAllot.outDep.id}" labelName="outDep.name" labelValue="${plmAllot.outDep.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="required" allowClear="true" notAllowSelectParent="true" isAll="true"/></td>
						
							<td>负责人<font color="red">*</font>：</td>
							<td><sys:treeselect id="outManager" name="outManager.id" value="${plmAllot.outManager.id}" labelName="outManager.name" labelValue="${plmAllot.outManager.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required" allowClear="true" notAllowSelectParent="true" isAll="true"/></td>
						</tr>
							<tr>
							<td>经办人：</td>
							<td colspan="2"><sys:treeselect id="outOperator" name="outOperator.id" value="${plmAllot.outOperator.id}" labelName="outOperator.name" labelValue="${plmAllot.outOperator.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true" isAll="true"/></td>
							<td>日期：</td>
							<td colspan="2"><input name="outDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${plmAllot.outDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/></td>
						</tr>
					</table>
				</td>
				</tr>
				<tr>
				<td colspan="6">
					<table class="table ntable table-condensed">
						<tr><td colspan="6"><h5>调入审核单位</h5></tr>
						<tr>
							<td colspan="1">资产调入部门<font color="red">*</font>：</td>
							<td colspan="2"><sys:treeselect id="inDep" name="inDep.id" value="${plmAllot.inDep.id}" labelName="inDep.name" labelValue="${plmAllot.inDep.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="required" allowClear="true" notAllowSelectParent="true" isAll="true"/></td>
							<td>负责人<font color="red">*</font>：</td>
							<td colspan="2"><sys:treeselect id="inManager" name="inManager.id" value="${plmAllot.inManager.id}" labelName="inManager.name" labelValue="${plmAllot.inManager.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required" allowClear="true" notAllowSelectParent="true" isAll="true"/></td>
							</tr>
							<tr>
							<td>经办人：</td>
							<td colspan="2"><sys:treeselect id="inOperator" name="inOperator.id" value="${plmAllot.inOperator.id}" labelName="inOperator.name" labelValue="${plmAllot.inOperator.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true" isAll="true"/></td>
							<td>日期：</td>
							<td colspan="2"><input name="inDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${plmAllot.outDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<div><input id="btnAdd" type="button" value="添加项" class="btn btn-primary"/><label>(请先选择调出部门)</label></div>
					<div>
					<table class="table ntable table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>物品名称</th>
								<th>规格型号</th>
								<th>计量单位</th>
								<th>数量</th>
								<th>单价(元)</th>
								<th>价值(元)</th>
								<th>二维码</th>
								<th>备注</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="equipment">
							<c:forEach items="${detailList}" var="plmAllotDetail">
								<tr>
									<td>${plmAllotDetail.name}</td>
									<td>${plmAllotDetail.spec}</td>
									<td>${plmAllotDetail.unit}</td>
									<td>${plmAllotDetail.erialNumber}</td>
									<td>${plmAllotDetail.price}</td>
									<td class="detailSum">${plmAllotDetail.sum}</td>
									<td><img src="data:image/jpeg;base64,${plmAllotDetail.qrCode}"/></td>
									<td><input id="${plmAllotDetail.equCode}" type="text" value="${plmAllotDetail.remarks}" onchange="updateRemark(this);"/></td>
									<td><a href="javascript:void(0);" onclick="removeSelect(this);"><i title="删除" class='icon-trash'></i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
				</td>
			</tr>
			<tr>
				<td class="trtop" colspan="1">资产合计大写<span class="help-inline"><font color="red">*</font> </span></td>
				<td colspan="2"><form:input path="sumCap" htmlEscape="false" maxlength="128" class="input-xlarge required" readonly="true"/></td>
				<td class="trtop" colspan="1">资产合计小写<span class="help-inline"><font color="red">*</font> </span></td>
				<td colspan="2"><form:input path="sumLow" htmlEscape="false" readonly="true" class="input-xlarge required number"/></td>
			</tr>
			
			<tr>
				<td class="trtop" colspan="1">备注信息</td>
				<td colspan="5"><form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/></td>
			</tr>
			<tr>
				<td class="trtop" colspan="1" style="width: 20%">是否督办</td>
				<td id="isSubTd" colspan="6">
					<form:radiobuttons path="plmAct.isSup" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
				</td>
				<td class="trtop isSup" colspan="1" style="width: 20%">督办人</td>
				<td class="isSup" colspan="2" style="width: 30%">
					<sys:treeselect id="supExe" name="plmAct.supExe.id" value="${plmAllot.plmAct.supExe.id}" labelName="plmAct.supExe.name" labelValue="${plmAllot.plmAct.supExe.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
				</td>
			</tr>		
			<tr class="isSup">
				<td class="trtop" colspan="1">督办明细</td>
				<td colspan="5">
					<form:textarea path="plmAct.supDetail" htmlEscape="false" rows="4" maxlength="256" class="input-xxlarge "/>
				</td>
			</tr>			
			<c:if test="${not empty plmAllot.procInsId}">
				<act:histoicTable procInsId="${plmAllot.procInsId}"  colspan="6" titleColspan="1"/>
			</c:if>
			<c:if test="${not empty plmAllot.procInsId}">
				<tr>
					<td class="trtop" colspan="1">修改备注</td>
					<td colspan="5">
					   <form:textarea path="act.comment" htmlEscape="false" rows="5" maxlength="255" class="input-xxlarge "/>
					</td>				
				</tr>			
			</c:if>
		</table>
		<div class="form-actions">
			
			<input id="btnApply" class="btn btn-primary" type="button" value="提交申请"/>&nbsp;
			<c:if test="${ empty plmAllot.procInsId}">
			<input id="btnSubmit" class="btn " type="button" value="保 存"/>&nbsp;
			</c:if>
			<c:if test="${not empty plmAllot.procInsId}">
				<input id="btnCancel" class="btn" type="button" value="作废"/>&nbsp;
			</c:if>
		
			<c:if test="${not empty plmAllot.id}">
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
			</c:if>
			<c:if test="${empty plmAllot.id}">
			<input id="btnCancel" class="btn " type="button" value="关闭"
				onclick="parent.layer.closeAll();" />
			</c:if>
		</div>
	</form:form>
	<div id="equSelect">
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr><th><input id="checkAll" name="checkAll" type="checkbox"/></th>
					<th>物资名称</th>
					<th>物资编号</th>
					<th>规格型号</th>
					<th>物资类别</th>
					<th>物资子类</th>
					<th>使用人</th>
				</tr>
			</thead>
			<tbody id="selectBody">
			</tbody>
		</table>
	</div>
</body>
</html>