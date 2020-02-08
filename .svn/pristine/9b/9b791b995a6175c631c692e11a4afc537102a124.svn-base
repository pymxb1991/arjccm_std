<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>党干部管理</title>
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
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/pbsDepartmentetc/">党干部管理列表</a></li>
		<li class="active"><a
			href="${ctx}/sys/pbsDepartmentetc/form?id=${pbsDepartmentetc.id}">党干部管理<shiro:hasPermission
					name="sys:pbsDepartmentetc:edit">${not empty pbsDepartmentetc.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="sys:pbsDepartmentetc:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsDepartmentetc"
		action="${ctx}/sys/pbsDepartmentetc/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>部门：</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id"
					value="${pbsDepartmentetc.office.id}" labelName="office.name"
					labelValue="${pbsDepartmentetc.office.name}" title="部门"
					url="/sys/office/treeData?type=2" cssClass="required"
					allowClear="true" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>负责人：</label>
			<div class="controls">
				<sys:treeselect id="sMasterid" name="sMasterid"
					value="${pbsDepartmentetc.SMasterid}" labelName="mastername"
					labelValue="${pbsDepartmentetc.mastername}" title="学员"
					url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">兼职党干部管理：</label>
			<div class="controls">
				
					<sys:treeselect id="sSpare01" name="sSpare01"
					value="${pbsDepartmentetc.SSpare01}" labelName="masternames"
					labelValue="${pbsDepartmentetc.masternames}" title="学员"
					url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
					notAllowSelectParent="true" checked="true"/>
					
					<%-- <sys:treeselect id="sSpare01" name="sSpare01"
					value="${pbsDepartmentetc.SSpare01}" isAll="true"
					labelName="mastername"
					labelValue="${pbsDepartmentetc.masternames}" title="用户"
					url="/sys/pbsOffice/treeData?type=4" 
					cssClass="input-xxlarge" notAllowSelectParent="true"
					checked="true" /> --%>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">部门图片：</label>
			<div class="controls">
				<form:hidden id="nameImage" path="sImgurl" htmlEscape="false"
					maxlength="255" class="input-xlarge" />
				<sys:ckfinder input="nameImage" type="images"
					uploadPath="/sys/pbsDepartmentetc" selectMultiple="false"
					maxWidth="100" maxHeight="100" />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:pbsDepartmentetc:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>