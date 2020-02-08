<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>活动评论信息管理</title>
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
        // 主题id
        var topid = $(".sActivityid").attr("topid");
        // 选择主题
        $("#sActivityid").load(ctx + "/activity/pbsActivityrec/namelist",
            function() {
              $("#sActivityid").val(topid).select2();
            });
      });
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/activity/pbsActivityecomment/">活动评论信息列表</a></li>
		<li class="active"><a
			href="${ctx}/activity/pbsActivityecomment/form?id=${pbsActivityecomment.id}">活动评论信息<shiro:hasPermission
					name="activity:pbsActivityecomment:edit">${not empty pbsActivityecomment.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="activity:pbsActivityecomment:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsActivityecomment"
		action="${ctx}/activity/pbsActivityecomment/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="hide sActivityid"
			topid="${pbsActivityecomment.sActivityid.id}"></div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>活动编号：</label>
			<div class="controls">
				<form:select path="sActivityid" class="input-xlarge required">
				</form:select>
			</div>
		</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>评论内容信息：</label>
			<div class="controls">
				<form:input path="sContent" htmlEscape="false" maxlength="2000"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>操作人员：</label>
			<div class="controls">
				<sys:treeselect id="sOperator" name="sOperator"
					value="${pbsActivityecomment.sOperator.id}" labelName="sOperator"
					labelValue="${pbsActivityecomment.sOperator.name}" title="用户"
					url="/sys/office/treeData?type=3" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>评论用户：</label>
			<div class="controls">
				<sys:treeselect id="sBindmember" name="sBindmember"
					value="${pbsActivityecomment.sBindmember.id}" labelName="sBindmember"
					labelValue="${pbsActivityecomment.sBindmember.SName}" title="用户"
					url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">记录状态：</label>
			<div class="controls">
				<form:input path="sStatus" htmlEscape="false" maxlength="100"
					class="input-xlarge " />
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="activity:pbsActivityecomment:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>