<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>申请记录管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/apply/js/pbsApplyNewForm.js"></script>

<script type="text/javascript">
  $(function() {
    var flowtype = "${pbsApplyrec.flowtype}";
    $("#flowtype").val(flowtype).select2();
    var sPartment=  "${pbsApplyrec.sPartment.id}";
    $("#sPartment").val(sPartment).select2();
  });
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/apply/ApplyPc/">申请记录列表</a></li>
		<li class="active"><a>申请记录<shiro:hasPermission
					name="apply:pbsApplyrec:edit">${not empty pbsApplyrec.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="apply:pbsApplyrec:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsApplyrec"
		action="${ctx}/apply/ApplyPc/apply" method="post"
		class="form-horizontal">
		<div class="hide sPartment" topid="${pbsApplyrec.sPartment.id}"></div>
		<form:hidden path="id" />
		<sys:message content="${message}" />

		<div class="control-group">
			<label class="control-label">申请类型：</label>
			<div class="controls">
				<form:select path="flowtype" class="input-xlarge ">
					<option value="JoinOfc">转入</option>
					<option value="DragOfc">转出</option>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请的部门：</label>
			<div class="controls">
				<form:select path="sPartment" class="input-xlarge required">
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>申请简述信息：</label>
			<div class="controls">
				<form:input path="sResume" htmlEscape="false" maxlength="500"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件：</label>
			<div class="controls">
				<form:hidden path="sFileurl" id="sFileurl"/>
				<sys:ckfinder input="sFileurl" type="files" uploadPath="/apply/applyPc" selectMultiple="true"></sys:ckfinder> 
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请内容：</label>
			<div class="controls">
				<form:textarea path="sContent" htmlEscape="false" rows="4"
					maxlength="2000" class="input-xxlarge " />
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="保 存" />&nbsp; <input id="btnCancel" class="btn"
				type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>