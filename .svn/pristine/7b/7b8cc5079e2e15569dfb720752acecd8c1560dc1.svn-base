<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>门户明细管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						//$("#name").focus();
						$('#btnSubmit').click(function() {
							$('#inputForm').submit();
						});
						$("#inputForm")
								.validate(
										{
											submitHandler : function(form) {
												//判断 该位置是否有窗口存在 longItude(输入的行位置)latItude(输入的列位置)ylongItude(编辑时原本的行位置)ylatItude(编辑时原本的列位置)
												$
														.ajax({
															url : '${ctx}/home/plmPortalDetail/itudeValidate',
															data : {
																"longItude" : $(
																		"#longItude")
																		.val(),
																"latItude" : $(
																		"#latItude")
																		.val(),
																"ylongItude" : "${plmPortalDetail.longItude}",
																"ylatItude" : "${plmPortalDetail.latItude}"
															},
															type : 'post',
															dataType : 'text',
															error : function() {
																alert("错误")
															},
															success : function(
																	data) {
																if (data == "1") {
																	form
																			.submit();
																} else {
																	$(
																			".itudeError")
																			.show();
																}
															}
														});
											},
											errorContainer : "#messageBox",
											errorPlacement : function(error,
													element) {
												$("#messageBox").text(
														"输入有误，请先更正");
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

	//下拉事件,内容变动时,会自动匹配相对应的标题和更多链接
	function selectsubmit() {
		var portalDictId = $("#portalDictId").val();
		$.ajax({
			url : '${ctx}/home/plmHome/selectContent',
			data : {
				"portalDictId" : portalDictId
			},
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
<%--引入文本框外部样式--%>
<link href="/arjccm/static/bootstrap/2.3.1/css_input/input_Custom.css" type="text/css" rel="stylesheet">
</head>
<body>
	<form:form target="_parent" id="inputForm" cssStyle="padding-top: 10px;"
		modelAttribute="plmPortalDetail"
		action="${ctx}/home/plmPortalDetail/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="parent.id" />
		<form:hidden path="type" />
		<form:hidden path="hight" value="270" />
		<sys:message content="${message}" />
		<div class="control-group head_Space">
			<label class="control-label"><span class="help-inline"> <font color="red">*</font></span>内容：</label>
			<div class="controls">
				<form:select path="portalDictId" class="input-xlarge required"
					onchange="selectsubmit()">
					<form:option value="" label="" />
					<form:options items="${plmPortalDictList}" itemLabel="title"
						itemValue="id" htmlEscape="false" />
				</form:select>
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
				<span class="help-inline"> 更多链接可根据内容自动匹配，可自行修改，可为空</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="help-inline"> <font color="red">*</font></span>行位置：</label>
			<div class="controls">
				<form:select path="longItude" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('home_longItude')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<%-- <form:input path="longItude" htmlEscape="false" maxlength="4" class="input-xlarge required number"/> --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="help-inline"> <font color="red">*</font>列位置：</span></label>
			<div class="controls">
				<form:select path="latItude" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${home_latItudelist}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select>
				<%-- <form:input path="latItude" htmlEscape="false" maxlength="4" class="input-xlarge required"/> --%>
				</span> <br> <label style="display: none;" class="error itudeError">该位置已存在其他窗口</label>
			</div>
		</div>
		<div class="form-actions">
			<a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i
				class="icon-ok"></i>保存</a> &nbsp;
		</div>
	</form:form>
</body>
</html>