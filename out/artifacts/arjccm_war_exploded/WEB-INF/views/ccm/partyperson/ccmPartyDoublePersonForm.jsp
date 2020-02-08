<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>党员信息管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			//关闭弹框事件
			$('#btnCancel').click(function() {
				parent.layer.close(parent.layerIndex);
			})
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
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ccmPartyPerson" action="${ctx}/partyperson/ccmPartyPerson/save" method="post" class="form-horizontal">
		<div>
			<form:hidden path="id"/>
			<sys:message content="${message}"/>
			<input id="type" name="type" type="hidden" value="2" />
			<table id="PartyPersonDetailTable" border="1px" style="border-color: #CCCCCC; border: 1px solid #CCCCCC; padding: 10px; width: 100%;">
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">党员姓名：</label>
							<div class="controls">
								<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
								<span class="help-inline"><font color="red">*</font> </span>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC"  >
						<div>
							<label class="control-label">人员状态：</label>
							<div class="controls">
								<form:input path="status" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
								<span class="help-inline"><font color="red">*</font> </span>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<%--<label class="control-label">社区（单位）：</label>--%>
							<%--<div class="controls">--%>
								<%--<sys:treeselect id="community" name="community" value="${ccmPartyPerson.community}" labelName="" labelValue="${ccmPartyPerson.community}"
												title="区域" url="/sys/area/treeData" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
								<span class="help-inline"><font color="red">*</font> </span>--%>
									<label class="control-label">选择社区：</label>
									<div class="controls">
										<sys:treeselect id="community" name="community"
														value="${ccmPartyPerson.community.id}" labelName="community.name"
														labelValue="${ccmPartyPerson.community.name}" title="区域"
														url="/tree/ccmTree/treeDataArea?type=6" cssClass=""
														allowClear="true" notAllowSelectParent="true" />
										<span class="help-inline"><font color="red">*</font> </span>
									</div>
							<%--</div>--%>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<%--<label class="control-label">所属组织：</label>--%>
							<%--<div class="controls">--%>
								<%--<sys:treeselect id="beloneOrg" name="beloneOrg" value="${ccmPartyPerson.beloneOrg}" labelName="" labelValue="${ccmPartyPerson.beloneOrg}"--%>
												<%--title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>--%>
							<%--</div>--%>
								<label class="control-label">所属组织：</label>
								<div class="controls">
										<%--<sys:treeselect id="beloneOrg" name="beloneOrg" value="${ccmPartyPerson.beloneOrg}" labelName="" labelValue="${ccmPartyPerson.beloneOrg}"--%>
										<%--title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>--%>
									<form:select path="beloneOrg" class="input-xlarge ">
										<form:option value="" label=""/>
										<form:options items="${list}" itemLabel="name" itemValue="id" htmlEscape="false"/>
									</form:select>
								</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">党员身份：</label>
							<div class="controls">
								<form:input path="partMembership" htmlEscape="false" maxlength="100" class="input-xlarge "/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<%--<label class="control-label">上级党组织：</label>
							<div class="controls">
								<sys:treeselect id="superPartOrg" name="superPartOrg" value="${ccmPartyPerson.superPartOrg}" labelName="" labelValue="${ccmPartyPerson.superPartOrg}"
												title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
							</div>--%>
								<label class="control-label">上级党组织：</label>
								<div class="controls">
										<%--<sys:treeselect id="superPartOrg" name="superPartOrg" value="${ccmPartyPerson.superPartOrg}" labelName="" labelValue="${ccmPartyPerson.superPartOrg}"--%>
										<%--title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>--%>
									<form:select path="superPartOrg" class="input-xlarge ">
										<form:option value="" label=""/>
										<form:options items="${list}" itemLabel="name" itemValue="id" htmlEscape="false"/>
									</form:select>
								</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">入党时间：</label>
							<div class="controls">
								<input name="joinPartTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
									   value="<fmt:formatDate value="${ccmPartyPerson.joinPartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
								<span class="help-inline"><font color="red">*</font> </span>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">所属支部：</label>
							<div class="controls">
								<form:input path="beloneBranch" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
								<span class="help-inline"><font color="red">*</font> </span>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">现任党内职务：</label>
							<div class="controls">
								<form:input path="innerPartPosition" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
								<span class="help-inline"><font color="red">*</font> </span>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">是否是组长或楼道长：</label>
							<div class="controls">
								<form:radiobuttons path="chargemanFlag" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">是否是老党员：</label>
							<div class="controls">
								<form:radiobuttons path="oldPartFlag" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">是否是困难党员：</label>
							<div class="controls">
								<form:radiobuttons path="difficultPartFlag" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" colspan="2" >
						<div>
							<label class="control-label">困难情况：</label>
							<div class="controls">
								<form:textarea path="difficultInfo" htmlEscape="false" rows="4" maxlength="500" class="input-xxlarge " style="width: 950px"/>
							</div>
						</div>
					</td>

				</tr><tr>

				<td style="padding: 8px;border: 1px dashed #CCCCCC" colspan="2" >
					<div>
						<label class="control-label">补助情况：</label>
						<div class="controls">
							<form:textarea path="subsidyInfo" htmlEscape="false" rows="4" maxlength="500" class="input-xxlarge " style="width: 950px"/>
						</div>
					</div>
				</td>
			</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">是否流动党员：</label>
							<div class="controls">
								<form:radiobuttons path="flowPartFlag" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">流出地：</label>
							<div class="controls">
								<form:input path="flowOutAdd" htmlEscape="false" maxlength="100" class="input-xlarge "/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">流入地：</label>
							<div class="controls">
								<form:input path="flowInAdd" htmlEscape="false" maxlength="100" class="input-xlarge "/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">现工作单位及职务：</label>
							<div class="controls">
								<form:input path="nowWorkAndPosition" htmlEscape="false" maxlength="100" class="input-xlarge "/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">现挂靠党组织名称：</label>
							<div class="controls">
								<form:input path="nowBeAttachPart" htmlEscape="false" maxlength="100" class="input-xlarge "/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">报道时间：</label>
							<div class="controls">
								<input name="reportingTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									   value="<fmt:formatDate value="${ccmPartyPerson.reportingTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">是否担任所领办：</label>
							<div class="controls">
								<form:radiobuttons path="actAsLeaderFlag" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">结对情况：</label>
							<div class="controls">
								<form:input path="conditionInfo" htmlEscape="false" maxlength="200" class="input-xlarge "/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">领办项目：</label>
							<div class="controls">
								<form:input path="leaderProject" htmlEscape="false" maxlength="100" class="input-xlarge "/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">隶属单位级别：</label>
							<div class="controls">
								<form:input path="deptLevel" htmlEscape="false" maxlength="20" class="input-xlarge "/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">入学时间：</label>
							<div class="controls">
								<input name="admissionTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									   value="<fmt:formatDate value="${ccmPartyPerson.admissionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">毕业时间：</label>
							<div class="controls">
								<input name="graduationTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									   value="<fmt:formatDate value="${ccmPartyPerson.graduationTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">家庭出身：</label>
							<div class="controls">
								<form:select path="familyOrigin" class="input-xlarge ">
									<form:option value="" label=""/>
									<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">民主党派：</label>
							<div class="controls">
								<form:input path="democraticParty" htmlEscape="false" maxlength="100" class="input-xlarge "/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">个人身份：</label>
							<div class="controls">
								<form:select path="individualStatus" class="input-xlarge ">
									<form:option value="" label=""/>
									<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">农民工：</label>
							<div class="controls">
								<form:radiobuttons path="migrantWorkerFlag" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">一线情况：</label>
							<div class="controls">
								<form:select path="firstLineSituation" class="input-xlarge ">
									<form:option value="" label=""/>
									<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">新阶层：</label>
							<div class="controls">
								<form:select path="newOrder" class="input-xlarge ">
									<form:option value="" label=""/>
									<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">学历：</label>
							<div class="controls">
								<form:select path="educationalBackground" class="input-xlarge ">
									<form:option value="" label=""/>
									<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">学位：</label>
							<div class="controls">
								<form:select path="academicDegree" class="input-xlarge ">
									<form:option value="" label=""/>
									<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">专业：</label>
							<div class="controls">
								<form:select path="profession" class="input-xlarge ">
									<form:option value="" label=""/>
									<form:options items="${fns:getDictList('ccm_pro_spe')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">毕业院校：</label>
							<div class="controls">
								<form:input path="graduateInstitutions" htmlEscape="false" maxlength="100" class="input-xlarge "/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">技术职务：</label>
							<div class="controls">
								<form:select path="technicalPosition" class="input-xlarge ">
									<form:option value="" label=""/>
									<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">职务级别：</label>
							<div class="controls">
								<form:select path="positionLevel" class="input-xlarge ">
									<form:option value="" label=""/>
									<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">联系情况：</label>
							<div class="controls">
								<form:select path="contactInfo" class="input-xlarge ">
									<form:option value="" label=""/>
									<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">加入其它党团日期：</label>
							<div class="controls">
								<input name="joinOtherPartTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									   value="<fmt:formatDate value="${ccmPartyPerson.joinOtherPartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">退休时间：</label>
							<div class="controls">
								<input name="retirementTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									   value="<fmt:formatDate value="${ccmPartyPerson.retirementTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">离岗时间：</label>
							<div class="controls">
								<input name="departureTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									   value="<fmt:formatDate value="${ccmPartyPerson.departureTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">所在党小组：</label>
							<div class="controls">
								<form:input path="belonePartGroup" htmlEscape="false" maxlength="100" class="input-xlarge "/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">入党介绍人：</label>
							<div class="controls">
								<form:input path="joinPartReference" htmlEscape="false" maxlength="64" class="input-xlarge "/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">转正时间：</label>
							<div class="controls">
								<input name="correctionTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									   value="<fmt:formatDate value="${ccmPartyPerson.correctionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>

						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">发展类型：</label>
							<div class="controls">
								<form:input path="developType" htmlEscape="false" maxlength="64" class="input-xlarge "/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">党费缴纳基数：</label>
							<div class="controls">
								<form:input path="basePartFees" htmlEscape="false" maxlength="64" class="input-xlarge "/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">是否27年7月前入团8月后转入党：</label>
							<div class="controls">
								<form:input path="julyJoinPartFlag" htmlEscape="false" maxlength="1" class="input-xlarge "/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">工作开始日期：</label>
							<div class="controls">
								<input name="workStartTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									   value="<fmt:formatDate value="${ccmPartyPerson.workStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">工作截止日期：</label>
							<div class="controls">
								<input name="workEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									   value="<fmt:formatDate value="${ccmPartyPerson.workEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>

							<label class="control-label">工作时间：</label>
							<div class="controls">
								<input name="workTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									   value="<fmt:formatDate value="${ccmPartyPerson.workTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">党内职务名称：</label>
							<div class="controls">
								<form:input path="partPositionCaption" htmlEscape="false" maxlength="100" class="input-xlarge "/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">任职组织编码：</label>
							<div class="controls">
								<form:input path="officeOrgCoding" htmlEscape="false" maxlength="64" class="input-xlarge "/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">任职所在组织：</label>
							<div class="controls">
								<form:input path="officeOrgWhich" htmlEscape="false" maxlength="255" class="input-xlarge "/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">是否班子成员：</label>
							<div class="controls">
								<form:radiobuttons path="classMemberFlag" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">任职方式：</label>
							<div class="controls">
								<form:input path="modeOfService" htmlEscape="false" maxlength="100" class="input-xlarge "/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">任职时间：</label>
							<div class="controls">
								<input name="serviceTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									   value="<fmt:formatDate value="${ccmPartyPerson.serviceTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">任职年限：</label>
							<div class="controls">
								<input name="serviceYears" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									   value="<fmt:formatDate value="${ccmPartyPerson.serviceYears}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>

						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">是否兼任村委会社区居委会委员：</label>
							<div class="controls">
								<form:radiobuttons path="villaCommMembFlag" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">是否兼任村委会社区居委会主任：</label>
							<div class="controls">
								<form:radiobuttons path="direCommMembFlag" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">班子职务排序：</label>
							<div class="controls">
								<form:input path="rankingOfTeamPosts" htmlEscape="false" maxlength="10" class="input-xlarge "/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">行政职务名称：</label>
							<div class="controls">
								<form:input path="adminPostionName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">任职单位：</label>
							<div class="controls">
								<form:input path="servingUint" htmlEscape="false" maxlength="100" class="input-xlarge "/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">个人职务排序：</label>
							<div class="controls">
								<form:input path="personalJobRanking" htmlEscape="false" maxlength="10" class="input-xlarge "/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">行政任职时间：</label>
							<div class="controls">
								<input name="adminServiceTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									   value="<fmt:formatDate value="${ccmPartyPerson.adminServiceTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">行政离任时间：</label>
							<div class="controls">
								<input name="adminDepartTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									   value="<fmt:formatDate value="${ccmPartyPerson.adminDepartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">教育类别：</label>
							<div class="controls">
								<form:input path="educationCategory" htmlEscape="false" maxlength="64" class="input-xlarge "/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">学位授予日期：</label>
							<div class="controls">
								<input name="degreeAwardTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									   value="<fmt:formatDate value="${ccmPartyPerson.degreeAwardTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">聘任起始日期：</label>
							<div class="controls">
								<input name="engageStartTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									   value="<fmt:formatDate value="${ccmPartyPerson.engageStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>

							<label class="control-label">聘任终止日期：</label>
							<div class="controls">
								<input name="engageEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									   value="<fmt:formatDate value="${ccmPartyPerson.engageEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">专业技术职务：</label>
							<div class="controls">
								<form:input path="profTechPosition" htmlEscape="false" maxlength="100" class="input-xlarge "/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">专业技术资格：</label>
							<div class="controls">
								<form:input path="profTechTitle" htmlEscape="false" maxlength="100" class="input-xlarge "/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">获得资格日期：</label>
							<div class="controls">
								<input name="eligibilityTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									   value="<fmt:formatDate value="${ccmPartyPerson.eligibilityTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>

							<label class="control-label">党员是否联系村务、厂务：</label>
							<div class="controls">
								<form:radiobuttons path="partRelaVillaFlag" items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">联系点名称：</label>
							<div class="controls">
								<form:input path="relationName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">党员是否联系农户：</label>
							<div class="controls">
								<form:radiobuttons path="partRelaFarmerFlag" items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">农户数量：</label>
							<div class="controls">
								<form:input path="farmerNum" htmlEscape="false" maxlength="10" class="input-xlarge "/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>

						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">是否&rdquo;十百千万&ldquo;驻村工作组干部：</label>
							<div class="controls">
								<form:radiobuttons path="villaWorkGroupFlag" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">驻村名称：</label>
							<div class="controls">
								<form:input path="residentVillageName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">增加时间：</label>
							<div class="controls">
								<input name="addTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
									   value="<fmt:formatDate value="${ccmPartyPerson.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">党员增加：</label>
							<div class="controls">
								<form:input path="partAdd" htmlEscape="false" maxlength="100" class="input-xlarge "/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">单位属性：</label>
							<div class="controls">
								<form:input path="deptAttr" htmlEscape="false" maxlength="64" class="input-xlarge "/>
							</div>
						</div>
					</td>
					<td style="padding: 8px;border: 1px dashed #CCCCCC" >
						<div>
							<label class="control-label">所属行业：</label>
							<div class="controls">
								<form:input path="deptIndustry" htmlEscape="false" maxlength="255" class="input-xlarge "/>
							</div>
						</div>
					</td>
				</tr><tr>
				<td style="padding: 8px;border: 1px dashed #CCCCCC"  colspan="2">
					<div>
						<label class="control-label">备注：</label>
						<div class="controls">
							<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " style="width:950px"/>
						</div>
					</div>
				</td>
			</tr>
			</table>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="partyperson:ccmPartyPerson:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn btn-danger" type="button" value="关 闭"/>
		</div>
	</form:form>
</body>
</html>