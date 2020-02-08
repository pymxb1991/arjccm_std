<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>物资申请管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/plm/storage/ajaxMessageAlert.js"></script>
<script type="text/javascript">
	$(document).ready(
					function() {
						$('#btnSubmit').click(function(){
							$('#searchForm').submit();
						});
						$("#btnSubmit3").on("click", function() {
						
					  		var chkItem=$("input[name='chkItem']:checked").parent();
							var name = chkItem.find(".name").val()
							var spec = chkItem.next().find(".spec").val();
							var number = Number(chkItem.next().next().find(".number").val());
							var counts = Number(chkItem.next().next().next().find(".counts").val());
							var date = chkItem.next().next().next().next().find(".Wdate").val();
							var re = /^[1-9]+[0-9]*]*$/; //判断正整数 /^[1-9]+[0-9]*]*$/
							var json = "{'name': '"+ name
									+ "','spec' : '"+ spec
									+ "','number' : '"+ number + "','validityDate':'"+date+"'}"
							/* $("#inputForm").append("");
							$("#name").val("");
							$("#spec").val(""); 
							$("#number").val("");*/
							if(date===undefined ){
								messageAlert("请选择申请物品", "error");
									return false;
							}
							else if(number===undefined || number=="" || number==null){
								 messageAlert("请填入申请数量", "error");
								return false;
							}else if (date===undefined || date=="" || date==null) {
								messageAlert("请选择有效期", "error");
								return false;
								}else if (!re.test(number)) {
									messageAlert("请输入正确数字", "error");
								return false;
								}else if(number>counts){
									messageAlert("库存剩余量不足", "error");
								return false;
								}else{
							//$(this).dialog("close");
							$("#detail" , parent.document).append(
											"<tr ><td colspan='3' style='padding:1%;'>"
													+ name
													+ "</td><td colspan='2'>"
													+ spec
													+ "</td><td colspan='1'>"
													+ number
													+ "</td><td colspan='2'>"+date+"</td><td colspan='1'><a name='addDetail' title='删除'>删除</a><input name='details' type='hidden' value=\"" + json + "\"></td></tr>");
								}
							parent.layer.closeAll();
					});
						
						
					});
	
	
	
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<br />
	<form:form id="searchForm" modelAttribute="plmEquipment" action="${ctx}/equapply/plmEquApply/findListBySpec" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>物资名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>规格型号：</label>
				<form:input path="spec" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-search"></i> 查询</a></li>
			<li class="btns"><a id="btnSubmit3" class="btn btn-primary" href="javascript:;"><i class="icon-ok-sign"></i>确认添加</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
		<table class="table table-condensed">
			<c:forEach items="${list}" var="plmEquipment">
			<tr>
				<td> <input name="chkItem" type="radio" value="${plmEquipment.name}" /><label class="control-label">物品：</label> <input class="name" id="name" style="width:120px;" value="${plmEquipment.name }"  readonly="readonly"/>
				</td>
				<td><label class="control-label">规格型号：</label> <input class="spec" id="spec" style="width:100px;"  value="${plmEquipment.spec }"  readonly="readonly"/>
				</td>
				<td><label class="control-label">申请数量<font
						color="red">*</font>：</label> <input
					id="number" style="width:60px;" class="digits number" /></td>
				<td><label class="control-label">库存剩余量：</label><input class="counts" id="counts" style="width:60px;"  value="${plmEquipment.counts }"  readonly="readonly"/></td>
				<td><label class="control-label">申请有效期限<font
						color="red">*</font>：</label><input type="text" style="width:100px;"
					readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" /></td>
			</tr>
			</c:forEach>
		</table>
</body>
</html>