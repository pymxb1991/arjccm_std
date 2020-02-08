<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="technology" />
<title>事件管理</title>
<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctxStatic}/ccm/event/css/fishBone.css" />
<script type="text/javascript" src="${ctxStatic}/ccm/event/js/fishBone.js"></script>
<script type="text/javascript" src="${ctxStatic}/ccm/event/js/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				//$("#name").focus();
				$("#EventDetailBtn").click(function() {
					$("#EventDetailTable").toggle();
				});
				$("#inputForm").validate({
					submitHandler : function(form) {
						$("#btnSubmit").attr("disabled", true);
					loading('正在提交，请稍等...');
						form.submit();
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
				$("td").css({"padding":"8px"});
				$("td").css({"border":"1px dashed #CCCCCC"});
				
				var jsonString = '${CcmEventCasedealList}';
				data = JSON.parse(jsonString);  
				//创建事件历史
				$(".fishBone1").fishBone(data, '${ctx}','deal');
				$(".fishBone2").fishBone(data, '${ctx}','read');
			});


	function selectChange() {
        var temp = $("#test1 option:selected").val();

        if (temp != '01') {
            $("#styleType").removeClass("input-xlarge ident0 card")
            $("#styleType").addClass('input-xlarge')
        } else {
            $("#styleType").removeClass('input-xlarge')
            $("#styleType").addClass('input-xlarge ident0 card')
        }

    }

	function saveForm() {
		var area = $("#areaId").val();
		var html1 = '<label for="" class="error">必选字段 <label>';
		//alert(officeId.length);
		if (area.length != 0) {
			$("#show1").html("");
		} else {
			$("#show1").html(html1);
		}

		if (area.length != 0) {
			$("#inputForm").submit();
		}

	}
	function ThisLayerDialog(src, title, height, width){
		parent.drawForm=parent.layer.open({
			type: 2,
			title: title,
			area: [height, width],
			fixed: true, //固定
			maxmin: false,
			/*   btn: ['关闭'], //可以无限个按钮 */
			content: src,
			zIndex:'1992',
			cancel:function(){
				//防止取消和删除效果一样
				window.isCancel = true;
			},
			end:function(){
				if(!window.isCancel){
					$("#areaPoint")[0].value = parent.areaPoint;
					parent.areaPoint = null;
					$("#areaMap")[0].value = parent.areaMap;
					parent.areaMap = null;
				}
				window.isCancel = null;
			}
		});
	}
</script>
</head>
<body>
	<br />



	<form:form id="inputForm" modelAttribute="ccmEventIncident"
		action="${ctx}/event/ccmEventIncident/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="otherId" />
		<sys:message content="${message}" />
		<table border="1px" style="border-color: #CCCCCC; border: 1px solid #CCCCCC; padding: 10px; width: 100%;">
			<tr>
				<td colspan="2" style="padding: 8px;border: 1px dashed #CCCCCC">
					<div>
						<label class="control-label">事件名称：</label>
						<div class="controls">
							<form:input readonly="true" path="caseName" htmlEscape="false"
								maxlength="100" class="input-xlarge required" />
					  		<span class="help-inline"><font color="red">*</font></span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="padding: 8px;border: 1px dashed #CCCCCC">
					<div>
						<label class="control-label">事件情况：</label>
						<div class="controls">
							<form:textarea disabled="true" path="caseCondition"
						htmlEscape="false" rows="2" maxlength="4000" class="input-xxlarge " />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div>
						<label class="control-label">事件图标：</label>
						<div class="controls">
							<form:hidden id="file1" path="file1"  htmlEscape="false" maxlength="200" class="input-xxlarge"/>
				    		<sys:ckfinder input="file1" type="images" uploadPath="/event/ccmEventIncident" selectMultiple="false"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td style="padding: 8px;border: 1px dashed #CCCCCC">
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font></span>发案地：</label>
						<div class="controls">
							<sys:treeselect id="area" name="area.id"
							value="${ccmEventIncident.area.id}" labelName="area.name"
							labelValue="${ccmEventIncident.area.name}" title="区域"
							url="/sys/area/treeData" cssClass="" allowClear="true"
							notAllowSelectParent="true" cssStyle="width: 220.22px" />
							<span class="help-inline"><font color="red" id="show1"></font></span>
						</div>
					</div>
				</td>
				<td style="padding: 8px;border: 1px dashed #CCCCCC">
					<div>
						<label class="control-label">发生日期：</label>
						<div class="controls">
							<input name="happenDate" type="text"
								readonly="readonly" maxlength="20" class="input-medium Wdate required"
								value="<fmt:formatDate value="${ccmEventIncident.happenDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
					  		<span class="help-inline"><font color="red">*</font></span> 
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td style="padding: 8px;border: 1px dashed #CCCCCC">
					<div>
						<label class="control-label">发案地代码：</label>
						<div class="controls">
							<form:input readonly="true" path="address" htmlEscape="false" maxlength="6" class="input-xlarge"/>
						</div>
					</div>
				</td>
				<td style="padding: 8px;border: 1px dashed #CCCCCC">
					<div>
						<label class="control-label">发生地详址：</label>
						<div class="controls">
							<form:input readonly="true" path="happenPlace" htmlEscape="false" maxlength="200" class="input-xlarge required" />
							<span class="help-inline"><font color="red">*</font></span> 
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td style="padding: 8px;border: 1px dashed #CCCCCC">
					<div>
						<label class="control-label">事件模块分类：</label>
						<div class="controls">
							<form:select disabled="true" path="eventKind" class="input-xlarge required" style="width:284px">
								<form:options items="${fns:getDictList('ccm_event_inci_kind')}"
								 	itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select><span class="help-inline"><font color="red">*</font></span>
						</div>
					</div>
				</td>
				<td style="padding: 8px;border: 1px dashed #CCCCCC">
					<div>
						<label class="control-label">事件编号：</label>
						<div class="controls">
							<form:input readonly="true" path="number" htmlEscape="false"
						maxlength="100" class="input-xlarge " />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td style="padding: 8px;border: 1px dashed #CCCCCC">
					<div>
						<label class="control-label">事件类别：</label>
						<div class="controls">
							<form:select disabled="true" path="eventSort" class="input-xlarge required" style="width:284px">
								<form:options items="${fns:getDictList('ccm_event_sort')}"
											  itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select><span class="help-inline"><font color="red">*</font></span>
						</div>
					</div>
				</td>
				<td style="padding: 8px;border: 1px dashed #CCCCCC">
					<div>
						<label class="control-label">事件规模：</label>
						<div class="controls">
							<form:select disabled="true" path="caseScope" class="input-xlarge required" style="width:284px">
								<form:options items="${fns:getDictList('ccm_event_scope')}"
											  itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td style="padding: 8px;border: 1px dashed #CCCCCC">
					<div>
						<label class="control-label">区域图：</label>
						<div class="controls">
							<form:input path="areaMap" readonly="true" htmlEscape="false"
						maxlength="2000" class="input-xlarge " />
						</div>
					</div>
				</td>
 				<td style="padding: 8px;border: 1px dashed #CCCCCC">
					<div>
						<label class="control-label">中心点：</label>
						<div class="controls">
							<form:input path="areaPoint" readonly="true" htmlEscape="false"
						maxlength="40" class="input-xlarge " />
<%--							<input id="draw" class="btn btn-primary" onclick="drawPoint()" type="button"--%>
<%--								   value="标 注" />--%>
							<a onclick="ThisLayerDialog('${ctx}/event/ccmEventIncident/drawForm?areaMap='+$('#areaMap').val()+'&areaPoint='+$('#areaPoint').val(), '标注', '1100px', '700px');"
									class="btn btn-primary">标 注</a>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td style="padding: 8px;border: 1px dashed #CCCCCC">
					<div>
						<label class="control-label">事件分级：</label>
						<div class="controls">
							<form:radiobuttons disabled="true" path="eventScale"
							items="${fns:getDictList('ccm_case_grad')}" itemLabel="label"
							itemValue="value" htmlEscape="false" class="required" /><span class="help-inline"><font color="red">*</font></span>
						</div>
					</div>
				</td>
				<td style="padding: 8px;border: 1px dashed #CCCCCC">
					<div>
						<label class="control-label">是否破案：</label>
						<div class="controls">
							<form:radiobuttons disabled="true" path="typeSolve"
							items="${fns:getDictList('yes_no')}" itemLabel="label"
							itemValue="value" htmlEscape="false" class="" />
						</div>
					</div>
				</td>
			</tr>
		</table>
		<br />
		<div>
			<h4 class="tableNamefirst" id="EventDetailBtn"
				style="cursor: pointer;">
				高级<i class=" icon-share-alt"></i>
			</h4>
			<table id="EventDetailTable" border="1px" style="display: none;border-color: #CCCCCC; border: 1px solid #CCCCCC; padding: 10px; width: 100%;">
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC">
						<div>
							<label class="control-label">事件性质：</label>
							<div class="controls">
								<form:select disabled="true" path="property" class="input-xlarge required" style="width:284px">
									<form:options items="${fns:getDictList('bph_alarm_info_typecode')}"
										itemLabel="label" itemValue="value" htmlEscape="false" />
								</form:select><span class="help-inline"><font color="red">*</font></span>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC">
						<div>
							<label class="control-label">事件类型：</label>
							<div class="controls">
								<form:select disabled="true" path="eventType" class="input-xlarge required" style="width:284px">
									<form:options items="${fns:getDictList('ccm_case_type')}" 
										itemLabel="label" itemValue="value" htmlEscape="false" />
								</form:select><span class="help-inline"><font color="red">*</font></span>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC">
						<div>
							<label class="control-label">主犯（嫌疑人）姓名：</label>
							<div class="controls">
								<form:input readonly="true" path="culName" htmlEscape="false"
							maxlength="80" class="input-xlarge " />
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC">
						<div>
							<label class="control-label">是否历史遗留：</label>
							<div class="controls">
								<form:radiobuttons disabled="true" path="historyLegacy"
								items="${fns:getDictList('yes_no')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class="" />
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC">
						<div>
							<label class="control-label">是否置顶：</label>
							<div class="controls">
								<form:radiobuttons disabled="true" path="stick"
								items="${fns:getDictList('yes_no')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class="" />
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC">
						<div>
							<label class="control-label">是否加急：</label>
							<div class="controls">
								<form:radiobuttons disabled="true" path="urgent" items="${fns:getDictList('yes_no')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class="" />
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC">
						<div>
							<label class="control-label">主犯（嫌疑人）证件类型：</label>
							<div class="controls">
								<form:select disabled="true" id="test1" path="culPapercode" onchange="selectChange()" class="input-xlarge " items="${fns:getDictList('legal_person_certificate_type')}" itemLabel="label" itemValue="value" htmlEscape="false"  style="width:284px">
								</form:select>
							</div>
						</div>
					</td>

					<td style="padding: 8px;border: 1px dashed #CCCCCC">
						<div>
							<label class="control-label">主犯（嫌疑人）证件号码：</label>
							<div class="controls">
								<form:input readonly="true" path="culPaperid" id="styleType"
							htmlEscape="false" maxlength="50" class="input-xlarge ident0 card"  />
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC">
						<div>
							<label class="control-label">作案人数：</label>
							<div class="controls">
								<form:input readonly="true" path="numCrime" htmlEscape="false"
							maxlength="6" class="input-xlarge  digits" type="number" />
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC">
						<div>
							<label class="control-label">在逃人数：</label>
							<div class="controls">
								<form:input readonly="true" path="numFlee" htmlEscape="false"
							maxlength="6" class="input-xlarge  digits" type="number" />
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC">
						<div>
							<label class="control-label">抓捕人数：</label>
							<div class="controls">
								<form:input readonly="true" path="numArrest" htmlEscape="false"
								maxlength="6" class="input-xlarge  digits" type="number" />
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC">
						<div>
							<label class="control-label">侦查终结日期：</label>
							<div class="controls">
								<input name="caseOverDay" type="text"
								readonly="readonly" maxlength="20" class="input-medium Wdate "
								value="<fmt:formatDate value="${ccmEventIncident.caseOverDay}" pattern="yyyy-MM-dd HH:mm:ss"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC">
						<div>
							<label class="control-label">事件上报人评价：</label>
							<div class="controls">
								<form:textarea disabled="true" path="caseSolve"
							htmlEscape="false" rows="4" maxlength="4000" class="input-xlarge " />
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC">
						<div>
							<label class="control-label">处理状态：</label>
							<div class="controls">
								<form:select disabled="true" path="status" class="input-xlarge "  style="width:284px">
									<c:if test="${empty ccmEventIncident.id}"><form:option value="01" label="未处理"/></c:if>
									<c:if test="${not empty ccmEventIncident.id}">
									<form:options items="${fns:getDictList('ccm_event_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
									</c:if>
								</form:select>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC">
						<div>
							<label class="control-label">附件二：</label>
							<div class="controls">
								<form:hidden id="file2" path="file2" htmlEscape="false" maxlength="24" class="input-xlarge"/>
							    <sys:ckfinder input="file2" type="files" uploadPath="/event/ccmEventIncident" selectMultiple="true"/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC">
						<div>
							<label class="control-label">附件三：</label>
							<div class="controls">
								<form:hidden id="file3" path="file3" htmlEscape="false" maxlength="24" class="input-xlarge"/>
					   			<sys:ckfinder input="file3" type="files" uploadPath="/event/ccmEventIncident" selectMultiple="true"/>
							</div>
						</div>
					</td>
				</tr>	
				<tr>
					<td colspan="2"><label class="control-label">备注信息：</label>
						<div class="controls">
							<form:textarea disabled="true" path="remarks" htmlEscape="false" rows="4"
								maxlength="255" class="input-xxlarge " />
						</div>
					</td>
				</tr>  
			</table>
		</div>

	</form:form>
<br/>


<script>
    var cardType = $.attr("test1");
</script>

</body>

</html>