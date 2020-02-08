<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>消息提醒信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
  $(document).ready(
      function() {
        //$("#name").focus();
        $("#inputForm").validate(
            {
              submitHandler : function(form) {
                loading('正在提交，请稍等...');
                form.submit();
              },
              errorContainer : "#messageBox",
              errorPlacement : function(error, element) {
                $("#messageBox").text("输入有误，请先更正。");
                if (element.is(":checkbox") || element.is(":radio")
                    || element.parent().is(".input-append")) {
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
		<li><a href="${ctx}/sys/pbsRemindMsg/">消息提醒信息列表</a></li>
		<li class="active"><a
			href="${ctx}/sys/pbsRemindMsg/form?id=${pbsRemindMsg.id}">消息提醒信息<shiro:hasPermission
					name="sys:pbsRemindMsg:edit">${not empty pbsRemindMsg.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="sys:pbsRemindMsg:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsRemindMsg"
		action="${ctx}/sys/pbsRemindMsg/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>模块功能：</label>
			<div class="controls">
				<form:select path="sFuncionid" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('notice_fun')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>对应的数据主键ID：</label>
			<div class="controls">
				<form:input path="sDataid" htmlEscape="false" maxlength="64"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>通知内容：</label>
			<div class="controls">
				<form:input path="sContent" htmlEscape="false" maxlength="1500"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>学员：</label>
			<div class="controls">
				<sys:treeselect id="sAcceptorid" name="sAcceptorid"
					value="${pbsRemindMsg.sAcceptorid.id}" labelName="sAcceptorid"
					labelValue="${pbsRemindMsg.sAcceptorid.SName}" title="学员"
					url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>读取状态：</label>
			<div class="controls">
				<form:select path="sStat" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('yes_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>发送者：</label>
			<div class="controls">
				<sys:treeselect id="sSenderid" name="sSenderid"
					value="${pbsRemindMsg.sSenderid.id}" labelName="sSenderid"
					labelValue="${pbsRemindMsg.sSenderid.SName}" title="学员"
					url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
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
			<shiro:hasPermission name="sys:pbsRemindMsg:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>