<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>出库单管理</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/common/zztable.css" type="text/css"
	rel="stylesheet">
<!-- 表格试表单css -->
<link href="${ctxStatic}/common/zzformtable.css" type="text/css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.css">
<script src="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/plm/storage/ajaxMessageAlert.js"></script>
<script type="text/javascript" src="${ctxStatic}/plm/storage/storageValidate.js"></script>
<script type="text/javascript">
	$(function() {
		$("#inputForm").validate(
				{
					rules: {
						deliveryNumber: {
							maxlength : 64,
							order_num: $("#deliveryNumber").val()    //调用自定义验证
		                }
		            },
		            
		            messages: {
		            	deliveryNumber: {
		            		required: "必填信息",
		            		maxlength: "最大长度64"
		                }
		            },
					
					submitHandler : function(form) {
						$("#btnSubmit").attr("disabled", true);
					loading('正在提交，请稍等...');
						form.submit();
					},
					errorContainer : "#messageBox",
					errorPlacement : function(error, element) {
						$("#messageBox").text("输出有误，请先更正。");
						if (element.is(":checkbox") || element.is(":radio")
								|| element.parent().is(".input-append")) {
							error.appendTo(element.parent().parent());
						} else {
							error.insertAfter(element);
						}
					}
				});
		$("#equipmentCode").keyup(
				function(event) {
					if (event.keyCode == 13) {
						$.ajax({
							url : "${ctx}/storage/plmOut/getEquipmentById",
							type : "post",
							data : {
								"code" : $(this).val()
							},
							async : true,
							cache : false,
							timeout : 30000,
							success : function(data) {
								var content = jQuery.parseJSON(data);
								if (content === undefined || content == null
										|| content == "") {
									messageAlert("添加失败！", "error");
									return;
								}
								if (content.ret != 0) {
									messageAlert(content.message, "error");
									return;
								}
								messageAlert("添加成功！", "success");
								$("#addEqu").append(content.result);
							},
							error : function(jqXHR, textStatus, errorThrown) {
								messageAlert("添加失败！", "error");
								console.error("jqXHR:", jqXHR);
								console.error("textStatus:", textStatus);
								console.error("errorThrown:", errorThrown);
								top.$.jBox.closeTip();
							}
						});
						$("#equipmentCode").attr("value", "");
					}
				});
		$("#btnSubmit").on("click", function() {
			if($("#type").val() == 3) {
				$("#inputForm").submit();
			} else {
				if($("#detailInfos").children().length>0){
					$("#inputForm").submit();
				} else{
					messageAlert("请添加物资详情！", "error");
				}
			}
		});
		var idAndNum = "";
		var flag = true; //是否是添加
		$("#addEqu").on("click", "a", function() {
			if ($(this).attr("title") == "delete") {
				$(this).parent().parent().remove();
			}
			if ($(this).attr("title") == "num") {
				idAndNum = $(this).next().val();
				$(this).next().attr("id", idAndNum);
				$("#editNum").dialog("open");
			}
		});
		$("#editNum")
				.dialog(
						{
							autoOpen : false,
							closeOnEscape : false,
							height : 160,
							width : 315,
							modal : true,
							buttons : {
								"确定" : function() {
									$("#" + idAndNum).attr("value",
											idAndNum + "@@" + $("#num").val());
									$("#" + idAndNum).parent().prev().html(
											$("#num").val());
									$(this).dialog("close");
									if (!flag) {
										$.ajax({
													url : "${ctx}/storage/plmMinusandAddDetail/updateNum",
													type : "post",
													data : {
														"id" : idAndNum,
														"erialNumber" : $(
																"#num").val()
													},
													async : true,
													cache : false,
													timeout : 30000,
													success : function(data) {
														var content = jQuery
																.parseJSON(data);
														if (content === undefined
																|| content == null
																|| content == "") {
															messageAlert(
																	"修改失败！",
																	"error");
															return;
														}
														if (content.ret != 0) {
															messageAlert(
																	content.message,
																	"error");
															return;
														}
														messageAlert("修改成功！",
																"success");
													},
													error : function(jqXHR,
															textStatus,
															errorThrown) {
														messageAlert("修改失败！",
																"error");
														console.error("jqXHR:",
																jqXHR);
														console.error(
																"textStatus:",
																textStatus);
														console.error(
																"errorThrown:",
																errorThrown);
														top.$.jBox.closeTip();
													}
												});
									}
									$("#num").val(0);
								},
								"取消" : function() {
									$(this).dialog("close");
								}
							},
							close : function() {
								$(this).dialog("close");
							}
						});
		$("#detailInfo").dialog({
			autoOpen : false,
			closeOnEscape : false,
			height : 700,
			width : 1100,
			modal : true,
			close : function() {
				$(this).dialog("close");
				$("#incomingEntry")[0].click();
			}
		});
		$("a[title='showDetail']").on("click", function() {
			$("#detailInfo").attr("src", this);
			$("#detailInfo").dialog("open");
			$("#detailInfo").css({
				"width" : "98%"
			});
			return false;
		});
		$("a[title='deleteDetail']").on(
				"click",
				function() {
					var id = $(this).next().attr("id");
					$.ajax({
						url : "${ctx}/storage/plmOut/deleteDetail",
						type : "post",
						data : {
							"id" : id
						},
						async : true,
						cache : false,
						timeout : 30000,
						success : function(data) {
							var content = jQuery.parseJSON(data);
							if (content === undefined || content == null
									|| content == "") {
								messageAlert("删除失败！", "error");
								return;
							}
							if (content.ret != 0) {
								messageAlert(content.message, "error");
								return;
							}
							messageAlert("删除成功！", "success");
							$("#" + id).parent().parent().remove();
						},
						error : function(jqXHR, textStatus, errorThrown) {
							messageAlert("删除失败！", "error");
							console.error("jqXHR:", jqXHR);
							console.error("textStatus:", textStatus);
							console.error("errorThrown:", errorThrown);
							top.$.jBox.closeTip();
						}
					});
				});
		$("a[title='numDetail']").on("click", function() {
			var id = $(this).prev().attr("id");
			idAndNum = id;
			flag = false;
			$("#editNum").dialog("open");
		});
		$("#btnOut").on(
				"click",
				function() {
					$.ajax({
						url : "${ctx}/storage/plmOut/updateEquipmentUser",
						type : "post",
						data : {
							"id" : $("#id").val()
						},
						async : true,
						cache : false,
						timeout : 30000,
						success : function(data) {
							var content = jQuery.parseJSON(data);
							if (content === undefined || content == null
									|| content == "") {
								messageAlert("出库失败！", "error");
								return;
							}
							if (content.ret != 0) {
								messageAlert(content.message, "error");
								return;
							}
							messageAlert("出库成功！", "success");
							$("#incomingEntry")[0].click();
						},
						error : function(jqXHR, textStatus, errorThrown) {
							messageAlert("出库失败！", "error");
							console.error("jqXHR:", jqXHR);
							console.error("textStatus:", textStatus);
							console.error("errorThrown:", errorThrown);
							top.$.jBox.closeTip();
						}
					});
				});
		$("#refresh").on("click", function(){
			$.ajax({
				url : "${ctx}/storage/plmOut/refreshOccupyStatus",
				type : "post",
				data : null,
				async : true,
				cache : false,
				timeout : 30000,
				success : function(data) {
					var content = jQuery.parseJSON(data);
					if (content === undefined || content == null
							|| content == "") {
						messageAlert("刷新失败！", "error");
						return;
					}
					if (content.ret != 0) {
						messageAlert(content.message, "error");
						return;
					}
					messageAlert("刷新成功！", "success");
				},
				error : function(jqXHR, textStatus, errorThrown) {
					messageAlert("刷新失败！", "error");
					console.error("jqXHR:", jqXHR);
					console.error("textStatus:", textStatus);
					console.error("errorThrown:", errorThrown);
					top.$.jBox.closeTip();
				}
			});
		});
		 $("#outComplate").hide();
		if($("#detailInfos").children().length>0){
			$("#outComplate").show();
		}
		
		$("#outComplate").on("click", function(){
			$("#type").val("4");
			if($("#detailInfos").children().length>0){
				$("#inputForm").submit();
			} else{
				messageAlert("请添加物资详情！", "error");
				$("#type").val("3");
			}
		});
		$(".trtop").css({
			"width" : "15%"
		});
		$("#btnCancel").on("click",function(){
			window.location.href = "${ctx}/storage/plmOut/";
		});
	});
	
	function hideDeadlineDate(obj) {
		var val = $(obj).val();
		if (val != 3) {
			if ($("[name='deadLineDate']") !== undefined && $("[name='deadLineDate']") != null && $("[name='deadLineDate']") != "") {
				$("[name='deadLineDate']").removeClass("required");
				$("[name='deadLineDate']").attr("disabled","disabled");
				$("[name='deadLineDate']").parent().prev().children("span").remove();
			}
		} else {
			$("[name='deadLineDate']").removeClass("required");
			$("[name='deadLineDate']").addClass("required");
			$("[name='deadLineDate']").removeAttr("disabled");
			$("[name='deadLineDate']").parent().prev().append("<span class=\"help-inline\"><font color=\"red\">*</font> </span>")
		}
	}
</script>
	<%--引入文本框外部样式--%>
	<link href="/arjccm/static/bootstrap/2.3.1/css_input/input_Custom.css" type="text/css" rel="stylesheet">
	<style>
		td{
			border: 1px solid black !important;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a style="width: 140px;text-align:center" href="${ctx}/storage/plmOut/">出库单列表</a></li>
		<li class="active" style="width: 140px"><a class="nav-head" id="incomingEntry"
			href="${ctx}/storage/plmOut/form?id=${plmIncomingEntry.id}">
				 <c:choose>
					<c:when test="${flag == '5'}">出库单查看</c:when>
					<c:otherwise>
						<shiro:hasPermission name="storage:plmIncomingEntry:edit">${not empty plmIncomingEntry.id?'出库单修改':'出库单添加'}</shiro:hasPermission>
						<shiro:lacksPermission name="storage:plmIncomingEntry:edit">出库单查看</shiro:lacksPermission>
					</c:otherwise>
				</c:choose>
		</a></li>
	</ul>

	<form:form id="inputForm" modelAttribute="plmout"
		action="${ctx}/storage/plmOut/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="type" />
		<sys:message content="${message}" />
		<h2>出库单</h2>
		<table id="table" class="table table-condensed first_table">
			<tr>
				<td class="trtop" colspan="6">出库信息</td>
			</tr>
			<tr>
				<td class="trtop">申请人</td>
				<td colspan="2">
					<c:choose>
						<c:when test="${flag == '3'}">
							<sys:treeselect id="user" name="user.id" value="${plmout.user.id}" labelName="user.name" labelValue="${plmout.user.name}"
								title="用户" url="/sys/office/treeData?type=3" cssClass="required" allowClear="true" notAllowSelectParent="true" isAll="true"/>
						</c:when>
						<c:otherwise>
							<form:hidden path="user.id"/>
							<label>${plmout.user.name}</label>
						</c:otherwise>
					</c:choose>
					
				</td>
				<td class="trtop">申请部门</td>
				<td colspan="2">
					<c:choose>
						<c:when test="${flag == '3'}">
							<sys:treeselect id="userJob" name="userJob.id" value="${plmout.userJob.id}" labelName="userJob.name" labelValue="${plmout.userJob.name}"
								title="部门" url="/sys/office/treeData?type=2" cssClass="required" allowClear="true" notAllowSelectParent="true" isAll="true"/>
						</c:when>
						<c:otherwise>
							<form:hidden path="userJob.id"/>
							<label>${plmout.userJob.name}</label>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="trtop">出库单号(系统自动生成)<span class="help-inline"></span></td>
				<td colspan="${flag == '3'?'1':'2'}">
					<label>${plmout.incomingCode}</label>
				</td>
				<td class="trtop"><font color="red">*</font> </span>出库日期<span class="help-inline"></td>
				<td colspan="${flag == '3'?'1':'2'}">
					<c:choose>
						<c:when test="${flag != '3'}">
							<fmt:formatDate value="${plmout.incomingDate}"
								pattern="yyyy-MM-dd" />
						</c:when>
						<c:otherwise>
							<input name="incomingDate" type="text" readonly="readonly"
								maxlength="20" class="input-medium Wdate required"
								value="<fmt:formatDate value="${plmout.incomingDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
						</c:otherwise>
					</c:choose>
				</td>
				<c:if test="${flag == '3'}">
				<td class="trtop"><span class="help-inline"><font color="red">*</font> </span>物品使用有效期：</td>
				<td>
						<input name="deadLineDate" type="text" readonly="readonly"
							maxlength="20" class="input-medium Wdate required"
							value="<fmt:formatDate value="${plmout.deadLineDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
				</td>
				</c:if>
			</tr>
			<tr>
				<td class="trtop"><span class="help-inline"><font color="red">*</font> </span>出库类别</td>
				<td>
					<c:choose>
						<c:when test="${flag != '3'}">
						${fns:getDictLabel(plmout.incomingType, 'plm_out_type', '未知')}
					</c:when>
						<c:otherwise>
							<form:select path="incomingType"
								class="input-xlarge required">
								<form:option value="" label="未选择" />
								<form:options items="${fns:getDictList('plm_out_type')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="trtop"><span class="help-inline"><font color="red">*</font> </span>出库状态</td>
				<td>
					${fns:getDictLabel(plmout.type, 'plm_out_status', '未知')}
				</td>
				<td class="trtop">送货单号</td>
				<td>
					<c:choose>
						<c:when test="${flag != '3'}">
						${plmout.deliveryNumber}
					</c:when>
						<c:otherwise>
							<form:input path="deliveryNumber" htmlEscape="false"
								maxlength="64" class="input-xlarge " />
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="trtop">申请原因</td>
				<td colspan="5">
					<c:choose>
						<c:when test="${flag != '3'}">
							<p>${plmout.cause}</p>
						</c:when>
						<c:otherwise>
							<form:textarea path="cause" htmlEscape="false" rows="4"
								maxlength="255" class="input-xxlarge " />
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td class="trtop">备注</td>
				<td colspan="5">
					<c:choose>
						<c:when test="${flag != '3'}">
							<p>${plmout.remarks}</p>
						</c:when>
						<c:otherwise>
							<form:textarea path="remarks" htmlEscape="false" rows="4"
								maxlength="255" class="input-xxlarge " />
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
		<c:if test="${not empty plmout.id}">
			<h2 style="margin-top: 25px;">出库详细列表</h2>
			<table id="table" class="table table-condensed">
				<thead>
					<tr>
						<td>物资编号</td>
						<td>物资名称</td>
						<td>规格型号</td>
						<td>物资类别</td>
						<td>物资子类</td>
						<td>生产日期</td>
						<td>物资数量</td>
						<td>计量单位</td>
						<td>有效期</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody id="detailInfos">
					<c:forEach items="${detailList}" var="plmMinusandAddDetail">
						<tr>
							<td>${plmMinusandAddDetail.equipmentCode}</td>
							<td>${plmMinusandAddDetail.name}</td>
							<td>${plmMinusandAddDetail.spec}</td>
							<td>${fns:getDictLabel(plmMinusandAddDetail.typeId, 'plm_equipment_type', '未知')}</td>
							<td>${fns:getDictLabel(plmMinusandAddDetail.typeChild, 'plm_equipment_type_child', '未知')}</td>
							<td><fmt:formatDate
									value="${plmMinusandAddDetail.productionDate}"
									pattern="yyyy-MM-dd" /></td>
							<td>${plmMinusandAddDetail.erialNumber}</td>
							<td>${plmMinusandAddDetail.unit}</td>
							<td>
							<fmt:formatDate value="${plmMinusandAddDetail.deadlineDate}" pattern="yyyy-MM-dd"/>
							</td>
							<td>
								<a title="showDetail" href="${ctx}/storage/plmOut/detailForm?id=${plmMinusandAddDetail.id}">查看</a>&nbsp;
								<c:if test="${flag == '3'}">
									<a title="deleteDetail">删除</a>&nbsp;
									<input id="${plmMinusandAddDetail.id}" type="hidden" value="${plmMinusandAddDetail.id}"/>
									<c:if test="${plmMinusandAddDetail.typeId == '0'}">
										<a title="numDetail">耗材数量</a>
									</c:if>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<c:if test="${flag == '3'}">
			<h2 style="margin-top: 25px;">物资添加列表</h2>
			<div>
				<label class="control-group">物资编号：</label>
				<input type="text" id="equipmentCode" class="input-xlarge"
					style="margin-left: 25px;" placeholder="请扫码或输入具体物资编号..." />
				<input type="button" id="refresh" class="btn" style="margin-left: 25px;" value="刷新占用">
			</div>
			<table id="table" class="table table-condensed">
				<thead>
					<tr>
						<td>物资编号</td>
						<td>物资名称</td>
						<td>规格型号</td>
						<td>物资类别</td>
						<td>物资子类</td>
						<td>生产日期</td>
						<td>物资数量</td>
						<td>计量单位</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody id="addEqu">
				</tbody>
			</table>
		</c:if>
		<div class="form-actions">
			<shiro:hasPermission name="storage:plmIncomingEntry:edit">
				<c:if test="${flag == '3'}">
					<a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i></i>保存</a>&nbsp;
					<a id="outComplate" class="btn btn-primary" href="javascript:;" style="width: 60px!important;"><i class="icon-ok-sign"></i>完成添加</a>
				</c:if>
				<c:if test="${flag == '4'}">
					<a id="btnOut" class="btn btn-primary" href="javascript:;"><i class="icon-ok"></i>确认出库</a>&nbsp;</c:if>
			</shiro:hasPermission>
			<a id="btnCancel" class="btn" href="javascript:;" onclick="history.go(-1)" ><i ></i>返回</a>
		</div>
	</form:form>
	<div id="editNum">
		<input id="num" type="text" class="input-xlarge"
			placeholder="请输入耗材数量..." />
	</div>
	<iframe id="detailInfo" src=""></iframe>
</body>
</html>