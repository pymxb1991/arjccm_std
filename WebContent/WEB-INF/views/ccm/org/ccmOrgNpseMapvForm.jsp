<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>机构组织管理</title>
<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
<meta name="decorator" content="technology" />
<!-- 鱼骨图 -->
<link rel="stylesheet" href="${ctxStatic}/ccm/event/css/fishBonePop.css" />
<script type="text/javascript"
	src="${ctxStatic}/ccm/event/js/fishBonePop.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/ccm/event/js/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				//关闭弹框事件
				$('#btnCancel').click(function() {
					parent.layer.close(parent.layerIndex);
				})
				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
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
				$("td").css({
					"padding" : "8px"
				});
				$("td").css({
					"border" : "1px dashed #CCCCCC"
				});
				//跟踪信息
				var jsonString = '${documentList}';
				data = JSON.parse(jsonString);
				$(".fishBone1").fishBone(data, '${ctx}', 'deal');
				$(".fishBone2").fishBone(data, '${ctx}', 'read');
				var s = $("#sel").val();
				if (s == "01") {
					$(".selectHidden").show();
				} else {
					$(".selectHidden").hide();
				}
			});
	function sels() {
		var s = $("#sel").val();
		if (s == "01") {
			$(".selectHidden").show();
		} else {
			$(".selectHidden").hide();
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
</script>
</head>
<body>
	<%-- <ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/org/ccmOrgNpse/form?id=${ccmOrgNpse.id}">机构组织<shiro:hasPermission
					name="org:ccmOrgNpse:edit">${not empty ccmOrgNpse.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="org:ccmOrgNpse:edit">查看</shiro:lacksPermission></a></li>
		<c:if test="${not empty ccmOrgNpse.id}">
			<li><a href="${ctx}/log/ccmLogTail/formPro?relevance_id=${ccmOrgNpse.id}&relevance_table=ccm_org_npse">跟踪信息<shiro:hasPermission name="log:ccmLogTail:edit">${not empty ccmLogTail.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="log:ccmLogTail:edit">查看</shiro:lacksPermission></a></li>
		</c:if>
	</ul> --%>
	<br />
	<form:form id="inputForm" modelAttribute="ccmOrgNpse"
		action="${ctx}/org/ccmOrgNpse/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />

		<table border="1px"
			style="border-color: #CCCCCC; border: 1px solid #CCCCCC; padding: 10px; width: 100%;">

			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font></span>所属网格：</label>
						<div class="controls">
							<sys:treeselect disabled="disabled" id="area" name="area.id"
								value="${ccmOrgNpse.area.id}" labelName="area.name"
								labelValue="${ccmOrgNpse.area.name}" title="区域"
								url="/tree/ccmTree/treeDataArea?type=7&areaid="
								allowClear="true" notAllowSelectParent="true" cssClass=""/>
							<span class="help-inline"><font color="red" id="show1"></font></span>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">企业名称：</label>
						<div class="controls">
							<form:input readonly="true" path="compName" htmlEscape="false" maxlength="100"
								class="input-xlarge required" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">工商执照注册号：</label>
						<div class="controls">
							<form:input readonly="true" path="compId" htmlEscape="false" maxlength="64"
								class="input-xlarge required" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">企业类别：</label>
						<div class="controls">
							<form:select disabled="true" path="compType" class="input-xlarge required">
								<form:options items="${fns:getDictList('ccm_buss_cate')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">登记注册类型：</label>
						<div class="controls">
							<form:select disabled="true" path="regiType" class="input-xlarge ">
								<form:options items="${fns:getDictList('sys_ccm_regi_type')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">企业地址：</label>
						<div class="controls">
							<form:input readonly="true" path="compAdd" htmlEscape="false" maxlength="200"
								class="input-xlarge required" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">面积（平方米）：</label>
						<div class="controls">
							<form:input readonly="true" path="compArea" htmlEscape="false"
								class="input-xlarge required number" />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">所属行业：</label>
						<div class="controls">
							<sys:treeselect disabled="disabled" id="industry" name="industry"
								value="${ccmOrgNpse.industry}" labelName="dicts.label"
								labelValue="${ccmOrgNpse.industry}" title="行业"
								url="/sys/sysDicts/treeData?type=ccm_profession"
								extId="${sysDicts.id}" cssClass="" allowClear="true"
								dicts="true" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">企业联系方式：</label>
						<div class="controls">
							<form:input readonly="true" path="compTl" htmlEscape="false" maxlength="50"
								class="input-xlarge required phone" />
							<span class="help-inline"><font color="red">*</font> </span>

						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">企业员工数：</label>
						<div class="controls">
							<form:input readonly="true" path="companyNum" htmlEscape="false" maxlength="6"
								class="input-xlarge digits required" type="number" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">注册资金：</label>
						<div class="controls">
							<form:input readonly="true" path="registeredFund" htmlEscape="false"
								class="input-xlarge number" />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">登记注册日期：</label>
						<div class="controls">
							<input name="registerDate" type="text" readonly="readonly"
								maxlength="20" class="input-medium Wdate "
								value="<fmt:formatDate value="${ccmOrgNpse.registerDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">法定代表人证件类型：</label>
						<div class="controls">
							<form:select disabled="true" path="legalReprCode" class="input-xlarge "
								items="${fns:getDictList('sys_ccm_org_papers')}"
								itemLabel="label" itemValue="value" htmlEscape="false">
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">法定代表人证件号码：</label>
						<div class="controls">
							<form:input readonly="true" path="legalReprId" htmlEscape="false" maxlength="50"
								class="input-xlarge ident0 card required" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">法定代表人姓名：</label>
						<div class="controls">
							<form:input readonly="true" path="legalReprName" htmlEscape="false"
								maxlength="80" class="input-xlarge required" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">法定代表人联系方式：</label>
						<div class="controls">
							<form:input readonly="true" path="legalReprTl" htmlEscape="false" maxlength="50"
								class="input-xlarge required phone" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">治保负责人姓名：</label>
						<div class="controls">
							<form:input readonly="true" path="secuName" htmlEscape="false" maxlength="50"
								class="input-xlarge required" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">治保负责人联系方式：</label>
						<div class="controls">
							<form:input readonly="true" path="secuPhone" htmlEscape="false" maxlength="50"
								class="input-xlarge required phone" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">单位负责人姓名：</label>
						<div class="controls">
							<form:input readonly="true" path="entePrinName" htmlEscape="false" maxlength="50"
								class="input-xlarge required" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>

				<td>
					<div>
						<label class="control-label">单位负责人联系方式：</label>
						<div class="controls">
							<form:input readonly="true" path="entePrincipalTl" htmlEscape="false"
								maxlength="50" class="input-xlarge phone " />
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div>
						<label class="control-label">控股情况：</label>
						<div class="controls">
							<form:select disabled="true" path="holdCase" class="input-xlarge ">
								<form:options items="${fns:getDictList('ccm_hold_cond')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">是否具备建立中共党组织条件：</label>
						<div class="controls">
							<form:radiobuttons disabled="disabled" path="estaOrgaCond"
								items="${fns:getDictList('yes_no')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class="required" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>

			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">中共党员数量：</label>
						<div class="controls">
							<form:input readonly="true" path="partyMem" htmlEscape="false" maxlength="6"
								class="input-xlarge digits required" type="number" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">是否有中共党组织：</label>
						<div class="controls">
							<form:radiobuttons disabled="disabled" path="estaOrga"
								items="${fns:getDictList('yes_no')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class="required" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">工会会员数量：</label>
						<div class="controls">
							<form:input readonly="true" path="laborUnionNum" htmlEscape="false" maxlength="6"
								class="input-xlarge digits required" type="number" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">是否有工会：</label>
						<div class="controls">
							<form:radiobuttons disabled="disabled" path="laborUnion"
								items="${fns:getDictList('yes_no')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class="required" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">共青团团员数量：</label>
						<div class="controls">
							<form:input readonly="true" path="youthLeagOrgaNum" htmlEscape="false"
								maxlength="6" class="input-xlarge digits required" type="number" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">是否有共青团组织：</label>
						<div class="controls">
							<form:radiobuttons disabled="disabled" path="youthLeagOrga"
								items="${fns:getDictList('yes_no')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class="required" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">妇女数量：</label>
						<div class="controls">
							<form:input readonly="true" path="womenNum" htmlEscape="false" maxlength="6"
								class="input-xlarge digits required" type="number" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">是否有妇联组织：</label>
						<div class="controls">
							<form:radiobuttons disabled="disabled" path="womenOrg"
								items="${fns:getDictList('yes_no')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class="required" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>


			<tr>
				<td>
					<div>
						<label class="control-label">安全隐患类型：</label>
						<div class="controls">
							<form:select disabled="true" path="safeHazaType" class="input-xlarge ">
								<form:options items="${fns:getDictList('ccm_pori_type')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">是否危化企业：</label>
						<div class="controls">
							<form:radiobuttons disabled="disabled" path="dangComp"
								items="${fns:getDictList('yes_no')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class="required" />
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>

			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">隐患情况：</label>
						<div class="controls">
							<form:textarea disabled="true" path="dangerCase" htmlEscape="false" rows="4"
								maxlength="200" class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">安全整改情况：</label>
						<div class="controls">
							<form:textarea disabled="true" path="reformCase" htmlEscape="false" rows="4"
								maxlength="255" class="input-xlarge " />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">关注程度：</label>
						<div class="controls">
							<form:select disabled="true" path="concExte" class="input-xlarge ">
								<form:options items="${fns:getDictList('ccm_conc_exte')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">风险级别：</label>
						<div class="controls">
							<form:select disabled="true" path="riskRank" class="input-xlarge ">
								<form:options items="${fns:getDictList('ccm_npse_risk_rank')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>

			</tr>
			<tr>
				<td colspan="2">
					<div>
						<label class="control-label">重点企业类型：</label>
						<div class="controls">
							<form:select disabled="true" path="compImpoType" class="input-xlarge required"
								id="sel" onchange="sels()">
								<form:options items="${fns:getDictList('comp_impo_type')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
				</td>
			</tr>


			<tr class="selectHidden">
				<td>
					<div>
						<label class="control-label">服务品牌：</label>
						<div class="controls">
							<form:input readonly="true" path="servBrand" htmlEscape="false" maxlength="50"
								class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">监控摄像机数量：</label>
						<div class="controls">
							<form:input readonly="true" path="survCameNum" htmlEscape="false" maxlength="5"
								class="input-xlarge digits" type="number" />
						</div>
					</div>
				</td>
			</tr>
			<tr class="selectHidden">
				<td>
					<div>
						<label class="control-label">X光机数量：</label>
						<div class="controls">
							<form:input readonly="true" path="xRayNum" htmlEscape="false" maxlength="4"
								class="input-xlarge digits" type="number" />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">是否落实100%X光机安检：</label>
						<div class="controls">
							<form:radiobuttons disabled="disabled" path="xRayChec"
								items="${fns:getDictList('yes_no')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class="" />
						</div>
					</div>
				</td>
			</tr>
			<tr class="selectHidden">
				<td>
					<div>
						<label class="control-label">是否落实100%先验视后封箱：</label>
						<div class="controls">
							<form:radiobuttons disabled="disabled" path="checPack"
								items="${fns:getDictList('yes_no')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class="" />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">是否落实100%寄递实名制：</label>
						<div class="controls">
							<form:radiobuttons disabled="disabled" path="realName"
								items="${fns:getDictList('yes_no')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class="" />
						</div>
					</div>
				</td>
			</tr>
			<tr class="selectHidden">
				<td colspan="2">
					<div>
						<label class="control-label">经营范围：</label>
						<div class="controls">
							<form:checkboxes path="manageScopeList"
								items="${fns:getDictList('ccm_busi_scope')}" itemLabel="label"
								itemValue="value" htmlEscape="false" class="" />
						</div>
					</div>
				</td>
			</tr>

			<%-- <tr>
				<td>
					<div>
						<label class="control-label">中心点：</label>
						<div class="controls">
							<form:input path="areaPoint" htmlEscape="false" maxlength="40"
								class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">区域图：</label>
						<div class="controls">
							<form:input path="areaMap" htmlEscape="false" maxlength="2000"
								class="input-xlarge " />
						</div>
					</div>
				</td>
			</tr> --%>
			<%-- <tr>
				<td>
					<div>
						<label class="control-label">图标：</label>
						<div class="controls">
							<sys:iconselect id="icon" name="icon" value="${ccmOrgNpse.icon}" />
						</div>
					</div>
				</td>
				<td></td>
			</tr> --%>
			<tr>
				<td>
					<div>
						<label class="control-label">备注信息：</label>
						<div class="controls">
							<form:textarea disabled="true" path="remarks" htmlEscape="false" rows="4"
								maxlength="255" class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">图片：</label>
						<div class="controls">
							<form:hidden id="images" path="images" htmlEscape="false"
								maxlength="255" class="input-xlarge" />
							<sys:ckfinder input="images" type="images"
								uploadPath="/org/FeiGongYouZhiZuZhi" selectMultiple="false"
								maxWidth="240" maxHeight="360" />
						</div>
					</div>
				</td>
			</tr>

		</table>

		<%-- 	<table border="1px" style="border-color: #CCCCCC; border: 1px solid #CCCCCC; padding: 10px; width: 100%;">
		       <%@include file="/WEB-INF/views/include/ccmlog.jsp" %>
		
		</table> --%>
	</form:form>
	<br>
	<c:if test="${documentNumber > 0}">
		<shiro:hasPermission name="log:ccmLogTail:edit">
			<h4>&nbsp;跟踪信息：</h4>
			<br>
			<div class="fishBone1"></div>
		</shiro:hasPermission>
		<shiro:lacksPermission name="log:ccmLogTail:edit">
			<h4>&nbsp;查看跟踪信息：</h4>
			<br>
			<div class="fishBone2"></div>
		</shiro:lacksPermission>
	</c:if>
	<script type="text/javascript">
		$(function(){
			partyMemChange($('input[type=radio][name=estaOrga]:checked').val());
			laborUnionNumChange($('input[type=radio][name=laborUnion]:checked').val());
			youthLeagOrgaNumChange($('input[type=radio][name=youthLeagOrga]:checked').val());
			womenNumChange($('input[type=radio][name=womenOrg]:checked').val());
			$('input[type=radio][name=estaOrga]').change(estaOrgaChange);
			$('input[type=radio][name=laborUnion]').change(laborUnionChange);
			$('input[type=radio][name=youthLeagOrga]').change(youthLeagOrgaChange);
			$('input[type=radio][name=womenOrg]').change(womenOrgChange);
			function estaOrgaChange(){
				partyMemChange(this.value);
			}
			function partyMemChange(value){
				if(value == '0'){
					$('#partyMem').attr('readonly','readonly');
					$('#partyMem').attr('class','input-xlarge digits');
				}else{
					$("#partyMem").removeAttr("readonly");
					$('#partyMem').attr('class','input-xlarge digits required');
				}
			}
			function laborUnionChange(){
				laborUnionNumChange(this.value);
			}
			function laborUnionNumChange(value){
				if(value == '0'){
					$('#laborUnionNum').attr('readonly','readonly'); 
					$('#laborUnionNum').attr('class','input-xlarge digits'); 
				}else{
					$("#laborUnionNum").removeAttr("readonly");
					$('#laborUnionNum').attr('class','input-xlarge digits required'); 
				}
			}
			function youthLeagOrgaChange(){
				youthLeagOrgaNumChange(this.value);
			}
			function youthLeagOrgaNumChange(value){
				if(value == '0'){
					$('#youthLeagOrgaNum').attr('readonly','readonly'); 
					$('#youthLeagOrgaNum').attr('class','input-xlarge digits'); 
				}else{
					$("#youthLeagOrgaNum").removeAttr("readonly");
					$('#youthLeagOrgaNum').attr('class','input-xlarge digits required'); 
				}
			}
			function womenOrgChange(){
				womenNumChange(this.value);
			}
			function womenNumChange(value){
				if(value == '0'){
					$('#womenNum').attr('readonly','readonly'); 
					$('#womenNum').attr('class','input-xlarge digits'); 
				}else{
					$("#womenNum").removeAttr("readonly");
					$('#womenNum').attr('class','input-xlarge digits required'); 
				}
			}
		})
	</script>
</body>
</html>