<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>学员登记信息管理</title>
<meta name="decorator" content="default" />
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
			});
</script>
<style type="text/css">
#pbsPartymemreg .control-group {
	border-bottom: 0px;
	padding-bottom: 1px;
}

#pbsPartymemreg  .control-group {
	margin-bottom: 1px;
}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/person/pbsPartymemreg/">学员登记信息列表</a></li>
		<li class="active"><a
			href="${ctx}/person/pbsPartymemreg/form?id=${pbsPartymemreg.id}">学员登记信息<shiro:hasPermission
					name="person:pbsPartymemreg:edit">${not empty pbsPartymemreg.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="person:pbsPartymemreg:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsPartymemreg"
		action="${ctx}/person/pbsPartymemreg/updatstat" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="pbspartymemid" value="${pbsPartymem.id}" />
		<sys:message content="${message}" />
		
		<table class=" table table-bordered" id="pbsPartymemreg">
			<tr>
				<td style="text-align: center;">
					<h4>登记信息</h4>
				</td>
				<td style="text-align: center;">
					<h4>学员信息</h4>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label"><font color="red">*&nbsp;</font>证件类别：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${fns:getDictLabels(pbsPartymemreg.SIdtype, 'sys_idtype', '')}" />
						</div>
					</div>
				</td>

				<td>
					<div class="control-group">
						<label class="control-label">证件类别：</label>
						<div class="controls">
							<input class="input-xlarge" readonly="readonly" type="text"
								value="${fns:getDictLabels(pbsPartymem.SIdtype, 'sys_idtype', '')}" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label"><font color="red">*&nbsp;</font>身份号码：</label>
						<div class="controls">
							<input class="input-xlarge" readonly="readonly" type="text"
								value="${pbsPartymemreg.SIdnum}" /> 
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">身份号码：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${pbsPartymem.SIdnum}" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">姓名：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${pbsPartymemreg.SName}" />

						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">姓名：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${pbsPartymem.SName}" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">性别：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${fns:getDictLabels(pbsPartymemreg.ISex, 'sex', '')}" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">性别：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${fns:getDictLabels(pbsPartymem.ISex, 'sex', '')}" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">民族信息：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${fns:getDictLabels(pbsPartymemreg.INation, 'sys_volk', '')}" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">民族信息：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${fns:getDictLabels(pbsPartymem.INation, 'sys_volk', '')}" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">出生日期：</label>
						<div class="controls">
							<input type="text" readonly="readonly"
								value="<fmt:formatDate value="${pbsPartymemreg.dtBirth}" pattern="yyyy-MM-dd"/>" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">出生日期：</label>
						<div class="controls">
							<input type="text" readonly="readonly"
								value="<fmt:formatDate value="${pbsPartymem.dtBirth}" pattern="yyyy-MM-dd"/>" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">学历信息：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${fns:getDictLabels(pbsPartymemreg.SEducation, 'sys_degree', '')}" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">学历信息：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${fns:getDictLabels(pbsPartymem.SEducation, 'sys_degree', '')}" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">学员类别：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${fns:getDictLabels(pbsPartymemreg.IType, 'sys_mebcategory', '')}" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">学员类别：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${fns:getDictLabels(pbsPartymem.IType, 'sys_mebcategory', '')}" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">所在党支部全称：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${pbsPartymemreg.SPartybranch}" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">所在党支部全称：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${pbsPartymem.SPartybranch}" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">入党日期：</label>
						<div class="controls">
							<input type="text" readonly="readonly"
								value="<fmt:formatDate value="${pbsPartymemreg.dtAdmission}" pattern="yyyy-MM-dd"/>" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">入党日期：</label>
						<div class="controls">
							<input type="text" readonly="readonly"
								value="<fmt:formatDate value="${pbsPartymem.dtAdmission}" pattern="yyyy-MM-dd"/>" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">转正：</label>
						<div class="controls">
							<input type="text" readonly="readonly"
								value="<fmt:formatDate value="${pbsPartymemreg.dtCorrection}" pattern="yyyy-MM-dd"/>" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">转正：</label>
						<div class="controls">
							<input type="text" readonly="readonly"
								value="<fmt:formatDate value="${pbsPartymem.dtCorrection}" pattern="yyyy-MM-dd"/>" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">工作岗位：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${pbsPartymemreg.SPost}" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">工作岗位：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${pbsPartymem.SPost}" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">联系电话-移动电话：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${pbsPartymemreg.SMobilephone}" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">联系电话-移动电话：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${pbsPartymem.SMobilephone}" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">联系电话-固定电话：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${pbsPartymemreg.SFixedphone}" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">联系电话-固定电话：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${pbsPartymem.SFixedphone}" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">家庭住址：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${pbsPartymemreg.SHomeaddr}" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">家庭住址：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${pbsPartymem.SHomeaddr}" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">党籍状态：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${fns:getDictLabels(pbsPartymemreg.IStat, 'sys_mebtype', '')}" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">党籍状态：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${fns:getDictLabels(pbsPartymem.IStat, 'sys_mebtype', '')}" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">是否为失联学员：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${fns:getDictLabels(pbsPartymemreg.IOutcontact, 'yes_no', '')}" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">是否为失联学员：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${fns:getDictLabels(pbsPartymem.IOutcontact, 'yes_no', '')}" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">失联日期：</label>
						<div class="controls">
							<input type="text" readonly="readonly"
								value="<fmt:formatDate value="${pbsPartymemreg.dtOutcontact}" 
								pattern="yyyy-MM-dd"/>" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">失联日期：</label>
						<div class="controls">
							<input type="text" readonly="readonly"
								value="<fmt:formatDate value="${pbsPartymem.dtOutcontact}"
								 pattern="yyyy-MM-dd"/>" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">是否为流动学员：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${fns:getDictLabels(pbsPartymemreg.IFloat, 'yes_no', '')}" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">是否为流动学员：</label>
						<div class="controls">
							<input class="input-xlarge " readonly="readonly" type="text"
								value="${fns:getDictLabels(pbsPartymem.IFloat, 'yes_no', '')}" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">外出流向省：</label>
						<div class="controls">
							<input type="text" readonly="readonly"
								value="${pbsPartymemreg.SFloattopro}" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">外出流向省：</label>
						<div class="controls">
							<input type="text" readonly="readonly"
								value="${pbsPartymem.SFloattopro}" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">外出流向市：</label>
						<div class="controls">
							<input type="text" readonly="readonly"
								value="${pbsPartymemreg.SFloattocity}" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">外出流向市：</label>
						<div class="controls">
							<input type="text" readonly="readonly"
								value="${pbsPartymem.SFloattocity}" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">外出流向县：</label>
						<div class="controls">
							<input type="text" readonly="readonly"
								value="${pbsPartymemreg.SFloattocounty}" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label">外出流向县：</label>
						<div class="controls">
							<input type="text" readonly="readonly"
								value="${pbsPartymem.SFloattocounty}" />
						</div>
					</div>
				</td>
			</tr>
		</table>
		<div class="control-group">
			<label class="control-label">审核状态：</label>
			<div class="controls">
				<c:if test="${pbsPartymemreg.SRegstat eq '0'}">
					<form:select path="sRegstat" class="input-xlarge">
						<form:options items="${fns:getDictList('memreg_type')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if>
				<c:if test="${pbsPartymemreg.SRegstat ne '0'}">
					<input type="text" readonly="readonly"
						value="${fns:getDictLabels(pbsPartymemreg.SRegstat, 'memreg_type', '')}" />
				</c:if>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="person:pbsPartymemreg:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>