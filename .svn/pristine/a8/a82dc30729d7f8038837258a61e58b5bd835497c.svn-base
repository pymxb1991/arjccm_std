<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>微信消息管理</title>
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
		<li><a href="${ctx}/wechat/pbsWebchatsendmsg/">微信消息列表</a></li>
		<li class="active"><a
			href="${ctx}/wechat/pbsWebchatsendmsg/form?id=${pbsWebchatsendmsg.id}">微信消息<shiro:hasPermission
					name="wechat:pbsWebchatsendmsg:edit">${not empty pbsWebchatsendmsg.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="wechat:pbsWebchatsendmsg:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsWebchatsendmsg"
		action="${ctx}/wechat/pbsWebchatsendmsg/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="sMsgtype" value="0" />
		<form:hidden path="sStat" value="0" />
		<sys:message content="${message}" />
		<div class="control-group">
            <label class="control-label"><font color="red">*&nbsp;</font>学员：</label>
            <div class="controls">
                <sys:treeselect id="sCreatemem" name="sCreatemem"
                    value="${pbsWebchatsendmsg.sCreatemem.id}" labelName="sCreatemem"
                    labelValue="${pbsWebchatsendmsg.sCreatemem.SName}" title="学员"
                    url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
                    notAllowSelectParent="true" />
            </div>
        </div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>消息发送内容：</label>
			<div class="controls">
				<form:textarea path="sSenddata" htmlEscape="false" rows="4"
					maxlength="255" class="input-xlarge required" />
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">描述信息：</label>
			<div class="controls">
				<form:textarea path="sDescription" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
			</div>
		</div>
		<div class="form-actions">
		<c:if test="${pbsWebchatsendmsg.sStat ne '2'}">
			<shiro:hasPermission name="wechat:pbsWebchatsendmsg:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
		</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>