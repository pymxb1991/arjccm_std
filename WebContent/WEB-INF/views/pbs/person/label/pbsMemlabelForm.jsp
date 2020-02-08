<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>人物标签管理</title>
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
		<li><a href="${ctx}/person/pbsMemlabel/">人物标签列表</a></li>
		<li class="active"><a
			href="${ctx}/person/pbsMemlabel/form?id=${pbsMemlabel.id}">人物标签<shiro:hasPermission
					name="person:pbsMemlabel:edit">${not empty pbsMemlabel.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="person:pbsMemlabel:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsMemlabel"
		action="${ctx}/person/pbsMemlabel/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="sType" value="0" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>学员：</label>
			<div class="controls">
				<sys:treeselect id="sMemberid" name="sMemberid"
					value="${pbsMemlabel.sMemberid.id}" labelName=""
					labelValue="${pbsMemlabel.sMemberid.SName}" title="用户"
					url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>标签列表信息：</label>
			<div class="controls">
				<%-- ${pbsLabelinfos} --%>
				<form:checkboxes path="posidList" items="${pbsLabelinfos}"
					itemLabel="SName" itemValue="SType" htmlEscape="false" class="required" />
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
			<shiro:hasPermission name="person:pbsMemlabel:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>