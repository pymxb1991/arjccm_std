<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>维保单位管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/common/zztable.css" type="text/css"
	rel="stylesheet">
	<!-- 表格试表单css -->
	<link href="${ctxStatic}/common/zzformtable.css" type="text/css" rel="stylesheet">
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$('#btnSubmit').click(function(){
				$('#inputForm').submit();
			});
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
			$(".trtop").css({
				"width" : "12.5%"
			});
			$("td[colspan='2']").css({
				"width" : "22.5%"
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/car/plmCarRepair/">维保单位列表</a></li>
		<li class="active"><a href="${ctx}/car/plmCarRepair/form?id=${plmCarRepair.id}">维保单位<shiro:hasPermission name="car:plmCarRepair:edit">${not empty plmCarRepair.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="car:plmCarRepair:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" style="margin: 30px 200px;" modelAttribute="plmCarRepair" action="${ctx}/car/plmCarRepair/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<h2 style="margin-bottom: 30px;">维保单位信息表</h2>
		<table id="table" class="table   table-condensed">
			<tr>
				<td class="trtop">单位名称<font color="red">*</font></td>
				<td colspan="2"><form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge required"/></td>
				<td class="trtop">单位编号</td>
				<td colspan="2"><form:input path="num" htmlEscape="false" maxlength="20" class="input-xlarge "/></td>
				<td class="trtop">单位照片 	</td>
			</tr>
			<tr>
				<td class="trtop">单位地址<font color="red">*</font></td>
				<td colspan="5"><form:input path="addr" htmlEscape="false" maxlength="100" class="input-xlarge required"/></td>
				<td rowspan="6">
					<form:hidden id="photo" path="photo" htmlEscape="false" maxlength="256" class="input-xlarge"/>
					<sys:ckfinder input="photo" type="images" uploadPath="/car/plmCarRepair" selectMultiple="false"/>		
				</td>
			</tr>
			<tr>
				<td class="trtop">单位电话</td>
				<td colspan="5"><form:input path="tel" htmlEscape="false" maxlength="20" class="input-xlarge simplePhone"/></td>
			</tr>
			<tr>
				<td class="trtop">负责人<font color="red">*</font></td>
				<td colspan="2">
					<form:input path="leader" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				</td>
				<td class="trtop">负责人手机<font color="red">*</font></td>
				<td colspan="2"><form:input path="phone" htmlEscape="false" maxlength="11" class="input-xlarge required mobile"/></td>
			</tr>
			<tr>
				<td class="trtop">合同</td>
				<td colspan="5">
					<form:hidden id="files" path="files" htmlEscape="false" maxlength="1000" class="input-xlarge"/>
					<sys:ckfinder input="files" type="files" uploadPath="/car/plmCarRepair" selectMultiple="true"/></td>
			</tr>			
			<tr>
				<td class="trtop">备注信息</td>
				<td colspan="5"><form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/></td>
			</tr>		
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="car:plmCarRepair:edit"><a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-ok"></i>保存</a>&nbsp;</shiro:hasPermission>
			<a id="btnCancel" class="btn" href="javascript:;" onclick="history.go(-1)" ><i class="icon-reply"></i>返回</a>
		</div>
	</form:form>
</body>
</html>