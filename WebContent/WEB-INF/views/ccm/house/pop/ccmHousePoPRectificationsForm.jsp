<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>人员标记</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
<script type="text/javascript">
	$(document).ready(
			function() {
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
				$("td").css({"padding":"8px"});
				$("td").css({"border":"0px dashed #CCCCCC"});
			});
</script>
	<link href="/arjccm/static/bootstrap/2.3.1/css_input/input_Custom.css" type="text/css" rel="stylesheet">
</head>
<body>
	<ul class="nav nav-tabs">
		<%-- <li><a href="${ctx}/report/ccmPeopleStat/statisticsPage?title=ccmPeopleStatPerson">人口统计</a></li>
		<li class=" "><a href="${ctx}/pop/ccmPeople/">实有人口列表</a></li>
		<li class="active"><a href="">人员标记</a></li> --%>
	</ul>

	<%-- <ul class="nav nav-pills">
		<li class=" spePop"><a href="${ctx}/pop/ccmPeople/specialform?id=${ccmPeople.id}">吸毒人员标记</a></li>
		<li><a href="${ctx}/house/ccmHouseRelease/specialform?id=${ccmPeople.id}">安置帮教人员标记</a></li>
		<li class="active"><a href="${ctx}/house/ccmHouseRectification/specialform?id=${ccmPeople.id}">社区矫正人员标记</a></li>
		<li><a href="${ctx}/house/ccmHousePsychogeny/specialform?id=${ccmPeople.id}">易肇事肇祸精神病人标记</a></li>
		<li><a href="${ctx}/house/ccmHouseAids/specialform?id=${ccmPeople.id}">艾滋病危险人员标记</a></li>
		<li><a href="${ctx}/house/ccmHousePetition/specialform?id=${ccmPeople.id}">重点上访人员标记</a></li>
		<li><a href="${ctx}/house/ccmHouseHeresy/specialform?id=${ccmPeople.id}">涉教人员标记</a></li>
		<li><a href="${ctx}/house/ccmHouseDangerous/specialform?id=${ccmPeople.id}">危险品从业人员标记</a></li>
		<li><a href="${ctx}/pop/ccmPopBehind/specialform?id=${ccmPeople.id}">留守人员标记</a></li>
		<li><a href="${ctx}/house/ccmHouseKym/specialform?id=${ccmPeople.id}">重点青少年标记</a></li>
	</ul> --%>

	<form:form id="inputForm" modelAttribute="ccmHouseRectification"
		action="${ctx}/house/ccmHouseRectification/save" method="post"
		class="form-horizontal">
		<sys:message content="${message}" />
		<form:hidden path="peopleId" value="${ccmPeople.id}" />
		<h4 class="tableName color-bg6">社区矫正人员信息</h4>
		<table border="0px" style="border-color: #CCCCCC; border: 0px solid #CCCCCC; padding: 10px; width: 100%;">
			<tr>
				<td style="width: 43%">
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>关注程度：</label>
						<div class="controls">
							<form:select path="atteType" class="input-xlarge required">
								<form:options items="${fns:getDictList('ccm_conc_exte')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>

						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>社区矫正人员编号：</label>
						<div class="controls">
							<form:input path="rectNum" htmlEscape="false" maxlength="16" class="input-xlarge required" />
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>矫正类別：</label>
						<div class="controls">
							<form:select path="rectType" class="input-xlarge required">
								<form:options items="${fns:getDictList('ccm_core_sort')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>案事件类別：</label>
						<div class="controls">
							<form:select path="caseType" class="input-xlarge required">
								<form:options items="${fns:getDictList('ccm_house_aids_cate')}"
									itemtd="td" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>具体罪名：</label>
						<div class="controls">
							<form:input path="charge" htmlEscape="false" maxlength="100" class="input-xlarge required" />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">原判刑期：</label>
						<div class="controls">
							<form:input path="origCharge" htmlEscape="false" maxlength="50" class="input-xlarge " />
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>接收方式：</label>
						<div class="controls">
							<form:select path="receiveMode" class="input-xlarge required">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('ccm_recv_way')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">原羁押场所：</label>
						<div class="controls">
							<form:input path="rectPlace" htmlEscape="false" maxlength="100" class="input-xlarge " />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">原判刑开始曰期：</label>
						<div class="controls">
							<input name="origBegin" type="text"
							readonly="readonly" maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmHouseRectification.origBegin}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">原判刑结束日期：</label>
						<div class="controls">
							<input name="origEnd" type="text"
							readonly="readonly" maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmHouseRectification.origEnd}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>矫正开始日期：</label>
						<div class="controls">
							<input name="rectBegin" type="text"
							readonly="readonly" maxlength="20" class="input-medium Wdate required"
							value="<fmt:formatDate value="${ccmHouseRectification.rectBegin}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>矫正结束日期：</label>
						<div class="controls">
							<input name="rectEnd" type="text"
							readonly="readonly" maxlength="20" class="input-medium Wdate required"
							value="<fmt:formatDate value="${ccmHouseRectification.rectEnd}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>是否有脱管：</label>
						<div class="controls">
							<form:radiobuttons path="detached"
							items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value"
							htmlEscape="false" class="required" />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">&ldquo;三涉&rdquo;情况：</label>
						<div class="controls">
							<form:checkboxes path="thrHoldList" items="${fns:getDictList('ccm_three_case')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">脱管原因：</label>
						<div class="controls">
							<form:input path="detaReason" htmlEscape="false" class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">&ldquo;四史&rdquo;情况：</label>
						<div class="controls">
							<form:checkboxes path="fourHisList" items="${fns:getDictList('ccm_four_case')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">检察监督脱管情况：</label>
						<div class="controls">
							<form:input path="detaSupe" htmlEscape="false" class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">矫正小组人员组成情况：</label>
						<div class="controls">
							<form:checkboxes path="correctedList" items="${fns:getDictList('ccm_jzxz_ryzc')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">脱管纠正情况：</label>
						<div class="controls">
							<form:input path="detaCorr" htmlEscape="false" class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">是否累惯犯：</label>
						<div class="controls">
							<form:radiobuttons path="recidivist"
							items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value"
							htmlEscape="false" class="" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>是否有漏管：</label>
						<div class="controls">
							<form:radiobuttons path="lackContr"
							items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value"
							htmlEscape="false" class="required" />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>是否建立矫正小组：</label>
						<div class="controls">
							<form:radiobuttons path="correcthas"
							items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value"
							htmlEscape="false" class="" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">漏管原因：</label>
						<div class="controls">
							<form:input path="lackContrRe" htmlEscape="false" class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>是否重新犯罪：</label>
						<div class="controls">
							<form:radiobuttons path="reoffend"
							items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value"
							htmlEscape="false" class="required" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">漏管纠正情况：</label>
						<div class="controls">
							<form:input path="lackContrCaseCorr" htmlEscape="false" class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">重新犯罪名称：</label>
						<div class="controls">
							<form:input path="reofCharge" htmlEscape="false" maxlength="100" class="input-xlarge " />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">检察监督漏管情况：</label>
						<div class="controls">
							<form:input path="lackContrCase" htmlEscape="false" class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">奖惩情况：</label>
						<div class="controls">
							<form:input path="rewandpun" htmlEscape="false" class="input-xlarge " />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">矫正解除（终止）类型：</label>
						<div class="controls">
							<form:select path="correctlift" class="input-xlarge ">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('ccm_core_rele')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">刑罚变更执行情况：</label>
						<div class="controls">
							<form:input path="penaChan" htmlEscape="false" class="input-xlarge " />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">备注信息：</label>
						<div class="controls">
							<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"></label>
						<div class="controls">
							
						</div>
					</div>
				</td>
			</tr>
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="house:ccmHouseRelease:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<!-- <input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" /> -->
		</div>

	</form:form>
</body>
</html>