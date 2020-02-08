<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>统计首页字典管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$('#btnSubmit').click(function(){
				$('#inputForm').submit();
			});
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
		<li><a href="${ctx}/statistics/plmStatisticsDict/">统计首页字典列表</a></li>
		<li class="active"><a href="${ctx}/statistics/plmStatisticsDict/form?id=${plmStatisticsDict.id}">统计首页字典<shiro:hasPermission name="statistics:plmStatisticsDict:edit">${not empty plmStatisticsDict.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="statistics:plmStatisticsDict:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="plmStatisticsDict" action="${ctx}/statistics/plmStatisticsDict/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="256" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容链接：</label>
			<div class="controls">
					<form:input path="content" htmlEscape="false" maxlength="256" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据行数：</label>
			<div class="controls">
				<form:input path="line" htmlEscape="false" maxlength="2" value="5" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		 <div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:input path="type" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型名称：</label>
			<div class="controls">
				<form:input path="typeName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<%-- 
		<div class="control-group">
			<label class="control-label">扩展1：</label>
			<div class="controls">
				<form:input path="extend1" htmlEscape="false" maxlength="256" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">扩展2：</label>
			<div class="controls">
				<form:input path="extend2" htmlEscape="false" maxlength="256" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="statistics:plmStatisticsDict:edit"><a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-ok"></i>保存</a>&nbsp;</shiro:hasPermission>
			<a id="btnCancel" class="btn" href="javascript:;" onclick="history.go(-1)" ><i class="icon-reply"></i>返回</a>
		</div>
	</form:form>
</body>
</html>