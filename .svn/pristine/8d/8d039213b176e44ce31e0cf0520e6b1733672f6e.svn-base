<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>申请记录管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/apply/js/pbsApplyNewForm.js"></script>

</head>

<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/flow/dealPc">我提交的申请</a></li>
		<li class="active"><a>新的申请</a></li>
		<li><a href="${ctx}/flow/dealPc/dealtList">待我处理</a></li>
		<li><a href="${ctx}/flow/dealPc/dealtFinishList">我已处理</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsApplyrec"
		action="${ctx}/flow/dealPc/apply" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<div class="hide sPartment" topid="${pbsApplyrec.sPartment.id}"></div>
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
				<sys:ckfinder input="sFileurl" type="files" uploadPath="/flow/dealPc" selectMultiple="true"></sys:ckfinder> 
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
	<script type="text/javascript">
    $(function() {
      var flowtype = "${pbsApplyrec.flowtype}";
      $("#flowtype").val(flowtype).select2();
      var sPartment = "${pbsApplyrec.sPartment.id}";
      $("#sPartment").val(sPartment).select2();
    });
  </script>
</body>
</html>