<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>重要会议管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
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
	<link href="/arjccm/static/bootstrap/2.3.1/css_input/input_Custom.css" type="text/css" rel="stylesheet">
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a style="width: 140px;text-align:center" href="${ctx}/know/ccmKnowKeyMeeting/">数据列表</a></li>
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/know/ccmKnowKeyMeeting/form?id=${ccmKnowKeyMeeting.id}">数据<shiro:hasPermission name="know:ccmKnowKeyMeeting:edit">${not empty ccmKnowKeyMeeting.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="know:ccmKnowKeyMeeting:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="ccmKnowKeyMeeting" action="${ctx}/know/ccmKnowKeyMeeting/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<table>
			<tr>
				<td><div class="control-group" style="padding-top: 8px">
					<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>标题：</label>
					<div class="controls">
						<form:input path="title" htmlEscape="false" maxlength="64" class="input-xlarge required"/>

					</div>
				</div></td>
				<td><div class="control-group">
					<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>开始时间：</label>
					<div class="controls">
						<input name="timeStart" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
							   value="<fmt:formatDate value="${ccmKnowKeyMeeting.timeStart}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
					</div>
				</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
					<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>会议时长(分钟)：</label>
					<div class="controls">
						<form:input path="timeLong" htmlEscape="false" maxlength="4" class="input-xlarge  digits required" type="number"/>
					</div>
				</div></td>
				<td><div class="control-group">
					<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>会议地点：</label>
					<div class="controls">
						<form:input path="address" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
					</div>
				</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
					<label class="control-label">发起部门：</label>
					<div class="controls">
						<sys:treeselect id="office" name="office.id" value="${ccmKnowKeyMeeting.office.id}" labelName="office.name" labelValue="${ccmKnowKeyMeeting.office.name}"
										title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="fasle"/>
					</div>
				</div></td>
				<td><div class="control-group">
					<label class="control-label">主持人：</label>
					<div class="controls">
						<form:input path="compere" htmlEscape="false" maxlength="256" class="input-xlarge "/>
					</div>
				</div></td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label"><span class="help-inline"><font color="red">*</font> </span>会议类型：</label>
						<div class="controls">
							<form:select path="type" class="input-xlarge ">
								<form:options items="${fns:getDictList('ccm_know_key_meeting_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>

						</div>
					</div></td>
				<td><div class="control-group">
					<label class="control-label">缺席人员：</label>
					<div class="controls">
						<form:textarea path="absentee" htmlEscape="false" rows="4" maxlength="256" class="input-xxlarge "/>
					</div>
				</div></td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">参加人员：</label>
						<div class="controls">
							<form:textarea path="attendee" htmlEscape="false" rows="4" maxlength="256" class="input-xxlarge "/>
						</div>
					</div>
					</td>
				<td><div class="control-group">
					<label class="control-label">会议决议：</label>
					<div class="controls">
						<form:textarea path="resolution" htmlEscape="false" rows="4" maxlength="256" class="input-xxlarge "/>
					</div>
				</div></td>
			</tr>
			<tr>
				<td><div class="control-group">
					<label class="control-label">会议督办：</label>
					<div class="controls">
						<form:textarea path="handle" htmlEscape="false" rows="4" maxlength="256" class="input-xxlarge "/>
					</div>
				</div></td>
				<td><div class="control-group">
					<label class="control-label">备注信息：</label>
					<div class="controls">
						<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
					</div>
				</div></td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label">附件：</label>
						<div class="controls">
							<form:hidden id="file" path="file" htmlEscape="false" maxlength="256" class="input-xlarge"/>
							<sys:ckfinder input="file" type="files" uploadPath="/know/ccmKnowKeyMeeting" selectMultiple="true"/>
						</div>
					</div>

				</td>
			</tr>
		</table>
		<div class="control-group">
			<label class="control-label">会议内容：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="30" maxlength="2000" class="input-xxlarge "/>
				<sys:ckeditor uploadPath="/know/ccmKnowKeyMeeting" replace="content" height="200"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="know:ccmKnowKeyMeeting:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>