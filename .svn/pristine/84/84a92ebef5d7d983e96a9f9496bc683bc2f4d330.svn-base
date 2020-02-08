<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>通讯录管理</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/common/zztable.css" type="text/css"
	rel="stylesheet">
<!-- 表格试表单css -->
<link href="${ctxStatic}/common/zzformtable.css" type="text/css"
	rel="stylesheet">
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						//$("#name").focus();
						$('#btnSubmit').click(function(){
							$('#inputForm').submit();
						});
						$("#inputForm")
								.validate(
										{
											submitHandler : function(form) {
														$("#btnSubmit").attr("disabled", true);
					loading('正在提交，请稍等...');
														form.submit();
											},
											errorContainer : "#messageBox",
											errorPlacement : function(error,
													element) {
												$("#messageBox").text(
														"输入有误，请先更正。");
												if (element.is(":checkbox")
														|| element.is(":radio")
														|| element
																.parent()
																.is(
																		".input-append")) {
													error.appendTo(element
															.parent().parent());
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
		<li><a
			href="${ctx}/addressbook/plmEmployeePersonalAddress/?type=1">个人通讯录列表</a></li>
		<li class="active"><a
			href="${ctx}/addressbook/plmEmployeePersonalAddress/form?id=${plmEmployee.id}">个人通讯录<shiro:hasPermission
					name="addressbook:plmEmployeePersonalAddress:edit">${not empty plmEmployee.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="addressbook:plmEmployee:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="plmEmployee"
		action="${ctx}/addressbook/plmEmployeePersonalAddress/save"
		method="post" class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<h2>人员信息</h2>
		<table id="table" class="table   table-condensed"
			style="table-layout: fixed;">
			<tr>
				<td class="trtop">姓名<span class="help-inline"><font
						color="red">*</font> </span>
				</td>
				<td colspan="2"><form:input path="name" htmlEscape="false"
						maxlength="64" class="input-xlarge required" /></td>
				<td class="trtop">英文姓名</td>
				<td colspan="2"><form:input path="ename" htmlEscape="false"
						maxlength="64" class="input-xlarge " /></td>
				<td style="text-align: center; font-weight: 800" class="trtop">照片</td>
			</tr>
			<tr>
				<td class="trtop">性别</td>
				<td colspan="2"><form:radiobuttons path="sex"
						items="${fns:getDictList('sex')}" itemLabel="label"
						itemValue="value" htmlEscape="false" class="required" /></td>
				<td class="trtop">部门<span class="help-inline"><font
						color="red">*</font> </span>
				</td>
				<td colspan="2"><sys:treeselect id="dePid" name="dePid.id"
						value="${plmEmployee.dePid.id}" labelName="dePid.name"
						labelValue="${plmEmployee.dePid.name}" title="部门"
						url="/sys/office/treeData?type=2" cssClass="required"
						allowClear="true" notAllowSelectParent="true" isAll="true" /></td>
				<td class="trtop" rowspan="6"><input id="imgul" name="imgul"
					type="hidden" value="${plmEmployee.imgul}" /> <sys:ckfinder
						input="imgul" type="thumb" uploadPath="/addressbook/plmEmployee"
						selectMultiple="false" maxWidth="170" maxHeight="200"/></td>
			</tr>
			<tr>
				<td class="trtop">职务<font color="red">*</font>
				</td>
				<td colspan="2"><form:input path="duty" htmlEscape="false"
						maxlength="64" class="input-xlarge required" /></td>
				<td class="trtop">就职状态<span class="help-inline"><font
						color="red">*</font> </span>
				</td>
				<td colspan="2"><form:select path="state"
						class="input-xlarge required">
						<form:option value="" label="请选择" />
						<form:options items="${fns:getDictList('plm_state')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select></td>
			</tr>
			<tr>
				<td class="trtop">联系电话<font color="red">*</font>
				</td>
				<td colspan="2"><form:input  path="phoneone"
						htmlEscape="false" maxlength="14" class="input-xlarge required phone" /></td>
				<td class="trtop">备用联系电话</td>
				<td colspan="2"><form:input  path="phonetwo"
						htmlEscape="false" maxlength="14" class="input-xlarge phone" /></td>
			</tr>
			<tr>
				<td class="trtop">工作人员编号<font color="red">*</font>
				</td>
				<td colspan="2"><form:input path="accounts" htmlEscape="false"
						maxlength="64" class="input-xlarge required" /></td>
				<td class="trtop">电子邮箱</td>
				<td colspan="2"><form:input path="email" htmlEscape="false"
						maxlength="64" class="input-xlarge email" /></td>
			</tr>
			<tr>
				<td class="trtop">现住址<font color="red">*</font>
				</td>
				<td colspan="2"><form:input path="nowPlace" htmlEscape="false"
						maxlength="128" class="input-xlarge required" /></td>
				<td class="trtop">家庭住址</td>
				<td colspan="2"><form:input path="familyPlace"
						htmlEscape="false" maxlength="64" class="input-xlarge " /></td>
			</tr>
			<tr>
				<td class="trtop">紧急联系人<font color="red">*</font>
				</td>
				<td colspan="2"><form:input path="exigenceMan"
						htmlEscape="false" maxlength="64" class="input-xlarge required" /></td>
				<td class="trtop">紧急联系人电话<font color="red">*</font>
				</td>
				<td colspan="2"><form:input path="exigencePhone"
						htmlEscape="false" maxlength="14" class="input-xlarge required phone" /></td>
			</tr>
			<tr>
				<td class="trtop">所属分组</td>
				<td colspan="6"><sys:treeselect id="groups" name="groups.id"
						value="${plmEmployee.groups.id}" labelName="groups.name"
						labelValue="${plmEmployee.groups.name}" title="通讯录分组"
						url="/addressbook/plmEmployeeGroups/treeData"
						cssClass="input-select" allowClear="true"
						notAllowSelectParent="true" /></td>
			</tr>
			<tr>
				<td class="trtop">备注</td>
				<td colspan="6"><form:textarea path="remarks"
						htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " /></td>
			</tr>
			<input style="display: none" name="type" type="text"
				readonly="readonly" value="1" pattern="" />
		</table>
		<div class="form-actions">
			<shiro:hasPermission
				name="addressbook:plmEmployeePersonalAddress:edit">
				<!-- <input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" /> -->
				<a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-ok"></i>保存</a>	
					&nbsp;</shiro:hasPermission>
			<a id="btnCancel" class="btn" href="javascript:;" onclick="history.go(-1)" ><i class="icon-reply"></i>返回</a>
		</div>
	</form:form>
</body>
</html>