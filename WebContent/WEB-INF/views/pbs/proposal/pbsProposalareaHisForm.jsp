<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>建议分区历史信息管理</title>
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
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/proposal/pbsProposalareaHis/">建议分区历史信息列表</a></li>
		<li class="active"><a href="${ctx}/proposal/pbsProposalareaHis/form?id=${pbsProposalareaHis.id}">建议分区历史信息<shiro:hasPermission name="proposal:pbsProposalareaHis:edit">${not empty pbsProposalareaHis.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="proposal:pbsProposalareaHis:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pbsProposalareaHis" action="${ctx}/proposal/pbsProposalareaHis/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">所属支部编号：</label>
			<div class="controls">
				<sys:treeselect id="sDepartment" name="sDepartment" value="${pbsProposalareaHis.sDepartment}" labelName="" labelValue="${pbsProposalareaHis.}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标题名称：</label>
			<div class="controls">
				<form:input path="sName" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">建议负责人：</label>
			<div class="controls">
				<form:input path="sMastermem" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据版本：</label>
			<div class="controls">
				<form:input path="iVersion" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">展示方式：</label>
			<div class="controls">
				<form:select path="sShowtype" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('proposalshowtype')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公布标记：</label>
			<div class="controls">
				<form:input path="sPublish" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提交状态：</label>
			<div class="controls">
				<form:select path="sStat" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('thinkreppublishtype')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评价等级：</label>
			<div class="controls">
				<form:select path="sLevel" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('taskvaluetype')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述信息：</label>
			<div class="controls">
				<form:input path="sDescription" htmlEscape="false" maxlength="1000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="proposal:pbsProposalareaHis:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>