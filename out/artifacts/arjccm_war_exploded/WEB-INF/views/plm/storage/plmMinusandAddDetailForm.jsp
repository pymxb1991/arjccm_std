<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>入库信息管理</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/common/zztable.css" type="text/css"
	rel="stylesheet">
<!-- 表格试表单css -->
<%-- <link href="${ctxStatic}/common/zzformtable.css" type="text/css"
	rel="stylesheet"> --%>
<link href="${ctxStatic}/form/css/formTable.css" rel="stylesheet" />
<script type="text/javascript"
	src="${ctxStatic}/plm/storage/ajaxMessageAlert.js"></script>
	<script type="text/javascript" src="${ctxStatic}/plm/storage/plmEquipmentType.js"></script>
<script type="text/javascript" src="${ctxStatic}/plm/storage/storageValidate.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				//$("#name").focus();
				$('#btnSubmit').click(function(){
					$('#inputForm').submit();
				});
				$("#inputForm")
						.validate(
								{
									
									rules: {
										price: {
											required: true,
											maxlength : 64,
											amount: $("#price").val()    //调用自定义验证
						                },
						                unit: {
						                	required: true,
											maxlength : 8,
											chinese: $("#unit").val()    //调用自定义验证
						                },
						                spec: {
											maxlength : 64,
											special_str: $("#spec").val()    //调用自定义验证
						                },
						                productionBatch: {
											maxlength : 20,
											order_num: $("#productionBatch").val()    //调用自定义验证
						                }
						            },
						            
						            messages: {
						            	price: {
						            		maxlength: "最大长度64"
						                },
						                unit: {
						                	required: "必填信息",
						            		maxlength: "最大长度8"
						                },
						                spec: {
						            		maxlength: "最大长度64"
						                },
						                productionBatch: {
						            		maxlength: "最大长度20"
						                }
						            },
						            
									submitHandler : function(form) {
										$("#btnSubmit").attr("disabled", true);
					loading('正在提交，请稍等...');
										sub();
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
								});
				function sub() {
					$.ajax({
						url : "${ctx}/storage/plmMinusandAddDetail/save",
						type : "post",
						data : $("#inputForm").serialize(),
						async : true,
						cache : false,
						timeout : 30000,
						success : function(data) {
							var content = jQuery.parseJSON(data);
							if (content === undefined || content == null
									|| content == "") {
								messageAlert("添加失败！", "error");
								top.$.jBox.closeTip();
								return;
							}
							if (content.ret != 0) {
								top.$.jBox.closeTip();
								messageAlert(content.message, "error");
								return;
							}
							top.$.jBox.closeTip();
							messageAlert("添加成功！如有需要可继续添加", "success");
						},
						error : function(jqXHR, textStatus, errorThrown) {
							messageAlert("添加失败！", "error");
							console.error("jqXHR:", jqXHR);
							console.error("textStatus:", textStatus);
							console.error("errorThrown:", errorThrown);
							top.$.jBox.closeTip();
						}
					});
				}
				var parentsrc=""
				 parentsrc=$(window.parent.document).find("#addDetailInfo").attr("src"); 
				
				
				
				if(parentsrc==null||parentsrc==""){
                 parentsrc=$(window.parent.document).find("#id").val(); 				
				
				}
				var parent =parentsrc.substr(parentsrc.lastIndexOf("=")+1);
				$("#parent").val(parent)
			});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<c:if test="${empty plmMinusandAddDetail.id}">
			<li><a
				href="${ctx}/storage/plmMinusandAddDetail/countEquipmentByType">资产模板列表</a></li>
		</c:if>
		<c:if test="${plmMinusandAddDetail.flag == '0'}">
		<li class="active"><a>物资信息<shiro:hasPermission
					name="storage:plmMinusandAddDetail:edit">${not empty plmMinusandAddDetail.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="storage:plmMinusandAddDetail:edit">查看</shiro:lacksPermission></a></li></c:if>
		<c:if test="${plmMinusandAddDetail.flag != '0'}"><li class="active"><a>物资信息查看</a></li></c:if>
	</ul>
	<br />
	<c:if test="${plmMinusandAddDetail.flag == '0'}">
	<form:form id="inputForm" modelAttribute="plmMinusandAddDetail"
		action="" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="parent"  />
		<form:hidden path="equId"  />
		<sys:message content="${message}" />
		<div id="message"></div>
		<div style="color: red;text-align: right;">耗材只允许修改数量</div>
		<table id="table" class="table   table-condensed"
			style="table-layout: fixed;">
			<c:if test="${plmMinusandAddDetail.typeId != '0'}">
			<tr>
				<td class="trtop">物资名称<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td colspan="2"><form:input path="name" htmlEscape="false"
						maxlength="64" class="input-xlarge required" /></td>
				<td class="trtop">所属仓库</td>
				<td colspan="2"><sys:treeselect id="storage" name="storage.id"
						value="${plmMinusandAddDetail.storage.id}" labelName="storage.name"
						labelValue="${plmMinusandAddDetail.storage.name}" title="仓库"
						url="/storage/plmStorage/treeData"
						allowClear="true" notAllowSelectParent="true" /></td>
			</tr>
			<tr>
				<td class="trtop">规格型号</td>
				<td colspan="2"><form:input path="spec" htmlEscape="false"
						maxlength="64" class="input-xlarge " /></td>
				<td rowspan="4" class="trtop">图片</td>
					<td rowspan="4" colspan="2"><form:hidden id="imgUrl" path="imgUrl"
						htmlEscape="false" maxlength="256" class="input-xlarge" /> <sys:ckfinder
						input="imgUrl" type="images" uploadPath="/storage/plmMinusandAddDetail"
						selectMultiple="false" /></td>
			</tr> 
			<tr>
				<td class="trtop">物资类别<span class="help-inline"><font
						color="red">*</font> </span>
				</td>
				<td colspan="2"><form:select path="typeId"
						class="input-xlarge required" dictTyep="plm_equipment_type">
						<form:option value="" label="" />
						<form:options items="${fns:getDictList('plm_equipment_type')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select></td>
			</tr>
			 <tr>
			 	<td class="trtop">物资子类<span class="help-inline"><font
						color="red">*</font> </span></td>
				<td colspan="2"><form:select path="typeChild"
						class="input-xlarge required">
						<form:option value="" label="" />
						<form:options
							items="${fns:getDictList('plm_equipment_type_child')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</td>
			</tr>
			<tr>
				<td class="trtop">分类</td>
				<td colspan="2">
					<form:select path="category"
						class="input-xlarge required">
						<form:option value="" label="" />
						<form:options
							items="${fns:getDictList('equ_category')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</td>
			</tr>
			<tr>
				<td class="trtop">生产日期</td>
				<td colspan="2"><input name="productionDate" type="text"
					readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${plmMinusandAddDetail.productionDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" /></td>
				<td class="trtop">生产批次</td>
				<td colspan="2"><form:input path="productionBatch"
						htmlEscape="false" maxlength="20" class="input-xlarge " />
				</td>
			</tr>
			<tr>
			<td class="trtop">质保期限</td>
				<td colspan="2"><input name="guaranteePeriod" type="text"
					readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${plmMinusandAddDetail.guaranteePeriod}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" /></td>
				<td class="trtop">使用年限</td>
				<td colspan="2"><input name="durableYears" type="text"
					readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${plmMinusandAddDetail.durableYears}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" /></td>
			</tr>
			<tr>
				<td class="trtop">保质期(月)</td>
				<td colspan="2"><form:input path="expirationDate" htmlEscape="false"
						maxlength="9" class="input-xlarge  digits" /></td>
				<td class="trtop">物资单价<font
							color="red">*</font></td>
				<td colspan="2" ><form:input path="price" htmlEscape="false"
						maxlength="64" class="input-xlarge required" /></td>
			</tr>
				<tr>
					<td class="trtop">计量单位<span class="help-inline"><font
							color="red">*</font> </span>
					</td>
					<td colspan="2"><form:input path="unit" htmlEscape="false"
							maxlength="64" class="input-xlarge " /></td>
						<td class="trtop">物资数量<span class="help-inline"><font
							color="red">*</font> </span>
					</td>
					<td colspan="2"><form:input path="erialNumber"
							htmlEscape="false" maxlength="64"
							class="input-xlarge required digits" />
					</td>
				</tr>
				</c:if>
				<c:if test="${plmMinusandAddDetail.typeId == '0'}">
					<tr>
				<td class="trtop">物资名称<span class="help-inline"> </span>
				</td>
				<td colspan="2">${plmMinusandAddDetail.name}<form:hidden path="name"/></td>
				<td class="trtop">所属仓库</td>
				<td colspan="2">${plmMinusandAddDetail.storage.name}<form:hidden path="storage.id"/> </td>
			</tr>
			<tr>
				<td class="trtop">规格型号</td>
				<td colspan="2">${plmMinusandAddDetail.spec}<form:hidden path="spec"/></td>
				<td rowspan="4" class="trtop">图片</td>
					<td rowspan="4" colspan="2"><form:hidden id="imgUrl" path="imgUrl"
						htmlEscape="false" maxlength="256" class="input-xlarge" /> <sys:ckfinder
						input="imgUrl" type="images" uploadPath="/storage/plmMinusandAddDetail"
						selectMultiple="false" readonly="true"/></td>
			</tr> 
			<tr>
				<td class="trtop">物资类别<span class="help-inline"> </span>
				</td>
				<td colspan="2">${fns:getDictLabel(plmMinusandAddDetail.typeId, 'plm_equipment_type', '未知')}<form:hidden path="typeId"/></td>
			</tr>
			 <tr>
			 	<td class="trtop">物资子类</td>
				<td colspan="2">${fns:getDictLabel(plmMinusandAddDetail.typeChild, 'plm_equipment_type_child', '未知')}<form:hidden path="typeChild"/>
				</td>
			</tr>
			<tr>
				<td class="trtop">分类</td>
				<td colspan="2">
					${fns:getDictLabel(plmMinusandAddDetail.category, 'equ_category', '未知')}
				</td>
			</tr>
			<tr>
				<td class="trtop">生产日期</td>
				<td colspan="2"><fmt:formatDate value="${plmMinusandAddDetail.productionDate}" pattern="yyyy-MM-dd"/>
					<input name="productionDate" type="hidden" value="<fmt:formatDate value="${plmMinusandAddDetail.productionDate}" pattern="yyyy-MM-dd"/>"/>
				</td>
				<td class="trtop">生产批次</td>
				<td colspan="2">${plmMinusandAddDetail.productionBatch}<form:hidden path="productionBatch"/>
				</td>
			</tr>
			<tr>
			<td class="trtop">质保期限</td>
				<td colspan="2"><fmt:formatDate value="${plmMinusandAddDetail.guaranteePeriod}" pattern="yyyy-MM-dd"/>
					<input name="guaranteePeriod" type="hidden" value="<fmt:formatDate value="${plmMinusandAddDetail.guaranteePeriod}" pattern="yyyy-MM-dd"/>"/>
				</td>
				<td class="trtop">使用年限</td>
				<td colspan="2"><fmt:formatDate value="${plmMinusandAddDetail.durableYears}" pattern="yyyy-MM-dd"/>
					<input name="durableYears" type="hidden" value="<fmt:formatDate value="${plmMinusandAddDetail.durableYears}" pattern="yyyy-MM-dd"/>"/>
				</td>
			</tr>
			<tr>
				<td class="trtop">保质期(月)</td>
				<td colspan="2">${plmMinusandAddDetail.expirationDate}<form:hidden path="expirationDate"/></td>
				<td class="trtop">物资价格</td>
				<td colspan="2" >${plmMinusandAddDetail.price}<form:hidden path="price"/></td>
			</tr>
				<tr>
					<td class="trtop">计量单位<span class="help-inline"></span>
					</td>
					<td colspan="2">${plmMinusandAddDetail.unit}<form:hidden path="unit"/></td>
						<td class="trtop">物资数量（耗材）<span class="help-inline"> </span>
					</td>
					<td colspan="2"><form:input path="erialNumber"
							htmlEscape="false" maxlength="64"
							class="input-xlarge required digits" />
					</td>
				</tr>
				</c:if>
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="storage:plmMinusandAddDetail:edit">
				<a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-ok"></i>保存</a>&nbsp;</shiro:hasPermission>
			<!-- <input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" /> -->
		</div>
	</form:form>
	</c:if>
	<c:if test="${plmMinusandAddDetail.flag != '0'}">
		<form:form id="inputForm" modelAttribute="plmMinusandAddDetail"
		action="" class="form-horizontal">
		<table id="table" class="table   table-condensed"
			style="table-layout: fixed;">
			<tr>
				<td class="trtop">物资名称<span class="help-inline"> </span>
				</td>
				<td colspan="2">${plmMinusandAddDetail.name}</td>
				<td class="trtop">所属仓库</td>
				<td colspan="2">${plmMinusandAddDetail.storage.name}</td>
			</tr>
			<tr>
				<td class="trtop">规格型号</td>
				<td colspan="2">${plmMinusandAddDetail.spec}</td>
				<td rowspan="4" class="trtop">图片</td>
					<td rowspan="4" colspan="2"><form:hidden id="imgUrl" path="imgUrl"
						htmlEscape="false" maxlength="256" class="input-xlarge" /> <sys:ckfinder
						input="imgUrl" type="images" uploadPath="/storage/plmMinusandAddDetail"
						selectMultiple="false" readonly="true"/>
						</td>
			</tr> 
			<tr>
				<td class="trtop">物资类别<span class="help-inline"> </span>
				</td>
				<td colspan="2">${fns:getDictLabel(plmMinusandAddDetail.typeId, 'plm_equipment_type', '未知')}</td>
			</tr>
			 <tr>
			 	<td class="trtop">物资子类</td>
				<td colspan="2">${fns:getDictLabel(plmMinusandAddDetail.typeChild, 'plm_equipment_type_child', '未知')}
				</td>
			</tr>
			<tr>
				<td class="trtop">分类</td>
				<td colspan="2">
					${fns:getDictLabel(plmMinusandAddDetail.category, 'equ_category', '未知')}
				</td>
			</tr>
			<tr>
				<td class="trtop">生产日期</td>
				<td colspan="2"><fmt:formatDate value="${plmMinusandAddDetail.productionDate}" pattern="yyyy-MM-dd"/></td>
				<td class="trtop">生产批次</td>
				<td colspan="2"><form:input path="productionBatch"
						htmlEscape="false" maxlength="20" class="input-xlarge " />
				</td>
			</tr>
			<tr>
			<td class="trtop">质保期限</td>
				<td colspan="2"><fmt:formatDate value="${plmMinusandAddDetail.guaranteePeriod}" pattern="yyyy-MM-dd"/></td>
				<td class="trtop">使用年限</td>
				<td colspan="2"><fmt:formatDate value="${plmMinusandAddDetail.durableYears}" pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>
				<td class="trtop">保质期(月)</td>
				<td colspan="2">${plmMinusandAddDetail.expirationDate}</td>
				<td class="trtop">物资价格</td>
				<td colspan="2">${plmMinusandAddDetail.price}</td>
			</tr>
			<c:if test="${plmMinusandAddDetail.typeId == '0'}">
				<tr>
					<td class="trtop">计量单位<span class="help-inline"> </span>
					</td>
					<td colspan="2">${plmMinusandAddDetail.unit}</td>
						<td class="trtop">物资数量（耗材）<span class="help-inline"> </span>
					</td>
					<td colspan="2">${plmMinusandAddDetail.erialNumber}
					</td>
				</tr>
			</c:if>
			<c:if test="${plmMinusandAddDetail.typeId != '0'}">
				<tr>
					<td class="trtop">计量单位<span class="help-inline"> </span>
					</td>
					<td colspan="2">${plmMinusandAddDetail.unit}</td>
						<td class="trtop">物资数量<span class="help-inline"> </span>
					</td>
					<td colspan="2">${plmMinusandAddDetail.erialNumber}
					</td>
				</tr>
			</c:if>
		</table>
		</form:form>
	</c:if>
</body>
</html>