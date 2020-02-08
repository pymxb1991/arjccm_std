<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>个人门户管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$('#btnSubmit').click(function(){
			$('#inputForm').submit();
		});
		$("#inputForm").validate({
			submitHandler : function(form) {
				form.submit();
			},
			errorContainer : "#messageBox",
			errorPlacement : function(error, element) {
				$("#messageBox").text("输入有误，请先更正。");
				if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
					error.appendTo(element.parent().parent());
				} else {
					error.insertAfter(element);
				}
			}
		});
	});
	function selectsubmit() {
		var portalDictId = $("#portalDictId").val();
		$.ajax({
			url : '${ctx}/home/plmHome/selectContent',
			data : {"portalDictId" : portalDictId},
			type : 'post',
			dataType : 'json',
			error : function() {
				alert("错误")
			},
			success : function(data) {
				$("input[name='connect']").attr("value", data.connect);
				$("input[name='title']").attr("value", data.title);
			}
		});
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
	</ul>
	<br />
	<form:form id="inputForm" target="_parent" modelAttribute="plmPortalDetail" action="${ctx}/home/plmHome/saveFan" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="parent" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">内容：</label>
			<div class="controls">
				<form:select path="portalDictId" class="input-xlarge required" onchange="selectsubmit()">
					<form:options items="${plmPortalDictList}" itemLabel="title" itemValue="id" htmlEscape="false" />
				</form:select>
				<span class="help-inline">
					<font color="red">*</font>
				</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">布局标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" class="input-xlarge " />
				<span class="help-inline"> 标题可以为空；标题为空时，窗口将没有头部</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">更多链接：</label>
			<div class="controls">
				<form:input path="connect" htmlEscape="false" class="input-xlarge " />
			</div>
		</div>
		<div class="form-actions">
			<a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-ok"></i>保存</a>
			&nbsp;
		</div>
	</form:form>
</body>
</html>