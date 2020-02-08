<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>思想汇报信息管理</title>
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
		<li><a href="${ctx}/thinkingreport/pbsThinkingreport/">思想汇报信息列表</a></li>
		<li class="active"><a
			href="${ctx}/thinkingreport/pbsThinkingreport/form?id=${pbsThinkingreport.id}">思想汇报信息<shiro:hasPermission
					name="thinkingreport:pbsThinkingreport:edit">${not empty pbsThinkingreport.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="thinkingreport:pbsThinkingreport:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsThinkingreport"
		action="${ctx}/thinkingreport/pbsThinkingreport/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>思想汇报的标题：</label>
			<div class="controls">
				<form:input path="sTitle" htmlEscape="false" maxlength="200"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>发布者：</label>
			<div class="controls">
				<sys:treeselect id="sReporteruser" name="sReporteruser"
					value="${pbsThinkingreport.sReporteruser.id}" labelName="sReporteruser"
					labelValue="${pbsThinkingreport.sReporteruser.name}" title="用户"
					url="/sys/office/treeData?type=3" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>发布者学员：</label>
			<div class="controls">
				<sys:treeselect id="sReportermem" name="sReportermem"
					value="${pbsThinkingreport.sReportermem.id}" labelName="sReportermem"
					labelValue="${pbsThinkingreport.sReportermem.SName}" title="用户"
					url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>接收者学员：</label>
			<div class="controls">
				<sys:treeselect id="sAcceptermem" name="sAcceptermem"
					value="${pbsThinkingreport.sAcceptermem.id}" labelName="sAcceptermem"
					labelValue="${pbsThinkingreport.sAcceptermem.SName}" title="用户"
					url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">公布标记：</label>
			<div class="controls">
				<form:select path="sPublish" class="input-xlarge ">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('')}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>状态：</label>
			<div class="controls">
				<form:select path="sStat" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('thinkreppublishtype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			  <label class="control-label"><font color="red">*&nbsp;</font>被评价等级：</label>
			<div class="controls">
				<form:select path="sLevel" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('taskvaluetype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">思想汇报内容：</label>
			<div class="controls">
				    <form:textarea id="sContent" htmlEscape="true" path="sContent"
                    rows="4" maxlength="200" class="input-xxlarge" />
                <sys:ckeditor replace="sContent"
                    uploadPath="/thinkingreport/pbsThinkingreport" />
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">描述信息：</label>
			<div class="controls">
				<form:textarea path="sDescription" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="thinkingreport:pbsThinkingreport:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>