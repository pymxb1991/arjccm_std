<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>建议分区管理</title>
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
		<li><a href="${ctx}/proposal/pbsProposalarea/">建议分区列表</a></li>
		<li class="active"><a
			href="${ctx}/proposal/pbsProposalarea/form?id=${pbsProposalarea.id}">建议分区<shiro:hasPermission
					name="proposal:pbsProposalarea:edit">${not empty pbsProposalarea.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="proposal:pbsProposalarea:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsProposalarea"
		action="${ctx}/proposal/pbsProposalarea/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>所属支部：</label>
			<div class="controls">
				<sys:treeselect id="sDepartment" name="sDepartment"
					value="${pbsProposalarea.sDepartment.id}" labelName="sDepartment"
					labelValue="${pbsProposalarea.sDepartment.name}" title="部门"
					url="/sys/office/treeData?type=2" cssClass="required" allowClear="true"
					 />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>标题名称：</label>
			<div class="controls">
				<form:input path="sName" htmlEscape="false" maxlength="200"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>建议负责人：</label>
			<div class="controls">
			
				<sys:treeselect id="sMastermem" name="sMastermem"
					value="${pbsProposalarea.sMastermem.id}" labelName="sMastermem"
					labelValue="${pbsProposalarea.sMastermem.SName}" title="学员"
					url="/sys/pbsOffice/treeData?type=4" cssClass="required" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">数据版本：</label>
			<div class="controls">
				<form:input path="iVersion" htmlEscape="false" maxlength="10"
					class="input-xlarge " />
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>展示方式：</label>
			<div class="controls">
				<form:select path="sShowtype" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('proposalshowtype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label">公布标记：</label>
			<div class="controls">
				<form:input path="sPublish" htmlEscape="false" maxlength="20"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提交状态：</label>
			<div class="controls">
				<form:select path="sStat" class="input-xlarge ">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('thinkreppublishtype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评价等级：</label>
			<div class="controls">
				<form:select path="sLevel" class="input-xlarge ">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('taskvaluetype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">描述信息：</label>
			<div class="controls">
				<form:input path="sDescription" htmlEscape="false" maxlength="1000"
					class="input-xlarge " />
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
			<shiro:hasPermission name="proposal:pbsProposalarea:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>