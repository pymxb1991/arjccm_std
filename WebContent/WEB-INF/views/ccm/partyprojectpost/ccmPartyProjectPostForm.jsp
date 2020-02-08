<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>认领功能管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			if("${ccmPartyProjectPost.type}" == "1"){
				$("#orgPartyText").text("党组织：")
				$("#type").val("1")
			}
			if("${ccmPartyProjectPost.type}" == "2"){
				$("#orgPartyText").text("党  员：")
				$("#type").val("2")
			}
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
		<%--<li><a href="${ctx}/partyprojectpost/ccmPartyProjectPost/">认领功能列表</a></li>--%>
		<%--<li class="active"><a href="${ctx}/partyprojectpost/ccmPartyProjectPost/form?id=${ccmPartyProjectPost.id}">认领功能<shiro:hasPermission name="partyprojectpost:ccmPartyProjectPost:edit">${not empty ccmPartyProjectPost.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="partyprojectpost:ccmPartyProjectPost:edit">查看</shiro:lacksPermission></a></li>--%>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="ccmPartyProjectPost" action="${ctx}/partyprojectpost/ccmPartyProjectPost/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<input id="type" name="type" type="hidden" />
		<input id="proPost" name="proPost" type="hidden" value="${ccmPartyProjectPost.proPost}" />
		<div class="control-group">
				<label class="control-label" id = "orgPartyText">党组织：</label>
			<div class="controls">
				<select data-placeholder="选择一个或多个党组织" style="width: 90%;" name="orgPartyList" class="chosen-select required" multiple tabindex="4">
					<option value=""></option>
					<c:forEach items="${list}" var="party">
						<option value="${party.id}">${party.name}</option>
					</c:forEach>
				</select>
<%--				<span class="help-inline"><font color="red">*</font> </span>--%>
<%--				<form:select path="orgParty" class="input-xlarge ">--%>
<%--					<form:option value="" label=""/>--%>
<%--					<form:options items="${list}" itemLabel="name" itemValue="id" htmlEscape="false"/>--%>
<%--				</form:select>--%>
			</div>
		</div>
		<div style="text-align:center">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" />
			<%--<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
		</div>
	</form:form>
</body>
</html>