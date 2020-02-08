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
		<li class="active"></li>
	</ul>
	<%-- <ul class="nav nav-pills">
		<li class=" spePop"><a href="${ctx}/pop/ccmPeople/specialform?id=${ccmPeople.id}">吸毒人员标记</a></li>
		<li class="active"><a href="${ctx}/house/ccmHouseRelease/specialform?id=${ccmPeople.id}">安置帮教人员标记</a></li>
		<li><a href="${ctx}/house/ccmHouseRectification/specialform?id=${ccmPeople.id}">社区矫正人员标记</a></li>
		<li><a href="${ctx}/house/ccmHousePsychogeny/specialform?id=${ccmPeople.id}">易肇事肇祸精神病人标记</a></li>
		<li><a href="${ctx}/house/ccmHouseAids/specialform?id=${ccmPeople.id}">艾滋病危险人员标记</a></li>
		<li><a href="${ctx}/house/ccmHousePetition/specialform?id=${ccmPeople.id}">重点上访人员标记</a></li>
		<li><a href="${ctx}/house/ccmHouseHeresy/specialform?id=${ccmPeople.id}">涉教人员标记</a></li>
		<li><a href="${ctx}/house/ccmHouseDangerous/specialform?id=${ccmPeople.id}">危险品从业人员标记</a></li>
		<li><a href="${ctx}/pop/ccmPopBehind/specialform?id=${ccmPeople.id}">留守人员标记</a></li>
		<li><a href="${ctx}/house/ccmHouseKym/specialform?id=${ccmPeople.id}">重点青少年标记</a></li>
	</ul> --%>

	<form:form id="inputForm" modelAttribute="ccmHouseRelease"
		action="${ctx}/house/ccmHouseRelease/save" method="post"
		class="form-horizontal">
		<form:hidden path="peopleId" value="${ccmPeople.id}" />
		<sys:message content="${message}" />
		<h4 class="tableName color-bg6">安置帮教人员信息</h4>
		<table border="0px" style="border-color: #CCCCCC; border: 0px solid #CCCCCC; padding: 10px; width: 100%;">
			<tr>
				<td>
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
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>原罪名：</label>
						<div class="controls">
							<form:select path="origCha" class="input-xlarge required">
								<form:options items="${fns:getDictList('ccm_chag_type')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>

						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">原判刑期：</label>
						<div class="controls">
							<form:input path="sentence" htmlEscape="false" maxlength="50" class="input-xlarge number" />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">服刑场所：</label>
						<div class="controls">
							<form:input path="servinGplace" htmlEscape="false" maxlength="100" class="input-xlarge " />
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>释放日期：</label>
						<div class="controls">
							<input name="releDate" type="text"
							readonly="readonly" maxlength="20" class="input-medium Wdate required"
							value="<fmt:formatDate value="${ccmHouseRelease.releDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />

						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>危险性评估类型：</label>
						<div class="controls">
							<form:select path="risk" class="input-xlarge required">
								<form:options items="${fns:getDictList('ccm_risk_type')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>

						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div>
						<label class="control-label">衔接日期：</label>
						<div class="controls">
							<input name="joinDate" type="text"
							readonly="readonly" maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmHouseRelease.joinDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">衔接情况：</label>
						<div class="controls">
							<form:select path="joinCond" class="input-xlarge ">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('ccm_gose_case')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">安置日期：</label>
						<div class="controls">
							<input name="placeDate" type="text"
							readonly="readonly" maxlength="20" class="input-medium Wdate "
							value="<fmt:formatDate value="${ccmHouseRelease.placeDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">安置情况：</label>
						<div class="controls">
							<form:select path="placement" class="input-xlarge ">
								<form:option value="" label="" />
								<form:options items="${fns:getDictList('ccm_plam_case')}"
									itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">未安置原因：</label>
						<div class="controls">
							<form:input path="notPlace" htmlEscape="false" maxlength="1024" class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>是否累犯：</label>
						<div class="controls">
							<form:radiobuttons path="recidivism"
							items="${fns:getDictList('yes_no')}" itemLabel="label"
							itemValue="value" htmlEscape="false" class="required" />

						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div>
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>是否重新犯罪：</label>
						<div class="controls">
							<form:radiobuttons path="reoffend"
							items="${fns:getDictList('yes_no')}" itemLabel="label"
							itemValue="value" htmlEscape="false" class="required" />

						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">重新犯罪罪名：</label>
						<div class="controls">
							<form:input path="reofCharge" htmlEscape="false" maxlength="100" class="input-xlarge " />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label class="control-label">帮教情况：</label>
						<div class="controls">
							<form:textarea path="helpCase" htmlEscape="false" rows="4" maxlength="1024" class="input-xlarge " />
						</div>
					</div>
				</td>
				<td>
					<div>
						<label class="control-label">备注信息：</label>
						<div class="controls">
							<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xlarge " />
						</div>
					</div>
				</td>
			</tr>
		</table>

		<div class="form-actions">
			<shiro:hasPermission name="house:ccmHouseRectification:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<!-- <input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" /> -->
			<input id="btnCancel" class="btn btn-danger" type="button" value="关 闭" />
		</div>
	</form:form>
</body>
</html>