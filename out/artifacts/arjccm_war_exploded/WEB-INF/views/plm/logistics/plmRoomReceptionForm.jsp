<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>接待室管理</title>
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
				"width" : "20%"
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/logistics/plmRoom/?category=02">接待室列表</a></li>
		<li class="active"><a href="${ctx}/logistics/plmRoom/form?id=${plmRoom.id}">接待室<shiro:hasPermission name="logistics:plmRoom:edit">${not empty plmRoom.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="logistics:plmRoom:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" style="margin: 30px 100px;" modelAttribute="plmRoom" action="${ctx}/logistics/plmRoom/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name="category" value="02"/>
		<sys:message content="${message}"/>		
		<h2 style="margin-bottom: 30px;">接待室信息表</h2>	
		<table id="table" class="table   table-condensed">
			<tr>
				<td class="trtop">接待室名称<font color="red">*</font></td>
				<td colspan="2"><form:input path="subject" htmlEscape="false" maxlength="64" class="input-xlarge required"/></td>
				<td class="trtop">座位数<font color="red">*</font></td>
				<td colspan="2"><form:input path="seat" htmlEscape="false" maxlength="6" class="input-xlarge required digits"/></td>
				<td class="trtop">接待室照片</td>
			</tr>
			<tr>
				<td class="trtop">接待室地址<font color="red">*</font></td>
				<td colspan="2"><form:input path="address" htmlEscape="false" maxlength="255" class="input-xlarge required"/></td>
				<td class="trtop">使用状态<font color="red">*</font></td>
				<td colspan="2">
					<form:select path="state" class="input-xlarge required">
						<form:options items="${fns:getDictList('plm_room_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</td>
				<td rowspan="3">
					<form:hidden id="picture" path="picture" htmlEscape="false" maxlength="256" class="input-xlarge"/>
					<sys:ckfinder input="picture" type="images" uploadPath="/car/plmCarRepair" selectMultiple="false"/>		
				</td>
			</tr>
			<tr>
				<td class="trtop">接待室介绍</td>
				<td colspan="5"><form:textarea path="memo" htmlEscape="false" rows="4" maxlength="1000" class="input-xxlarge "/></td>
			</tr>	
			<tr>
				<td class="trtop">备注信息</td>
				<td colspan="5"><form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/></td>
			</tr>
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="logistics:plmRoom:edit"><a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-ok"></i>保存</a>&nbsp;</shiro:hasPermission>
			<a id="btnCancel" class="btn" href="javascript:;" onclick="history.go(-1)" ><i class="icon-reply"></i>返回</a>
		</div>
	</form:form>
</body>
</html>