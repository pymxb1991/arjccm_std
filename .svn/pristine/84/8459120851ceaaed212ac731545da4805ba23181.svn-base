<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>活动信息添加</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/work/pbsWork/">指派给我的工作列表</a></li>
		<li><a href="${ctx}/work/pbsWork/distributedList">我派发的工作列表</a></li>
		<shiro:hasPermission name="work:pbsActivityrec:edit">
			<li><a href="${ctx}/work/pbsWork/form">任务指派</a></li>
		</shiro:hasPermission>
		<li class="active"><a>评价信息</a></li>
	</ul>
	<br />
	<form:form  method="post" class="form-horizontal" modelAttribute="pbsTaskrec">
		<div class="control-group">
			<label class="control-label" id="activityTitle">任务标题</label>
			<div class="controls">
				<form:input path="sResume" htmlEscape="false" maxlength="200" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" id="activityTitle">任务类型</label>
			<div class="controls">
				<form:input path="sType.SName" htmlEscape="false" maxlength="200" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" id="activityTitle">任务内容</label>
			<div class="controls">
				<form:input path="SContent" htmlEscape="false" maxlength="200" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group" >
			<label class="control-label" id="activityTitle">是否完成</label>
			<div class="controls">
				<input value="${fns:getDictLabel(taskoprecs.SOptstatus, 'taskoptstatus', '')}" readonly="readonly"
 					type="text" maxlength="64" class="input-xlarge" style="width:205px"/> 
			</div>
		</div>
		<div class="control-group" >
			<label class="control-label" id="activityTitle">任务完成情况</label>
			<div class="controls">
				<input value="${taskoprecs.SContent}" readonly="readonly"
 					type="text" maxlength="64" class="input-xlarge" style="width:205px"/> 
			</div>
		</div>
		<div class="control-group" >
			<label class="control-label" id="activityTitle">评价分值</label>
			<div class="controls">
				<input value="${fns:getDictLabel(taskevaluate.SValue, 'taskvaluetype', '')}" readonly="readonly"
 					type="text" maxlength="64" class="input-xlarge" style="width:205px"/> 
			</div>
		</div>
		<div class="control-group" >
			<label class="control-label" id="activityTitle">记录状态</label>
			<div class="controls">
				<input value="${fns:getDictLabel(taskevaluate.SStatus, 'taskfinishtype', '')}" readonly="readonly"
 					type="text" maxlength="64" class="input-xlarge" style="width:205px"/> 
			</div>
		</div>
		<div class="control-group" >
			<label class="control-label" id="activityTitle">反馈结果</label>
			<div class="controls">
				<input value="${taskevaluate.SContent}" readonly="readonly"
 					type="text" maxlength="64" class="input-xlarge" style="width:205px"/> 
			</div>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>

