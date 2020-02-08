<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>题目选项信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript"
    src="${ctxStatic}/pbs/vote/js/pbsVoteItemForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/vote/pbsVoteTopic/listsurvey"> <c:if
					test="${pbsVoteItem.topid.SBelongfunc eq  '1' }">调查</c:if><c:if test="${pbsVoteItem.topid.SBelongfunc ne  '1' }">投票</c:if>主题信息列表
		</a></li>
		<c:if test="${pbsVoteItem.topid.SBelongfunc eq  '1' }">
		<li><a
			href="${ctx}/vote/pbsVoteTopic/formsurvey?id=${pbsVoteItem.topid.id}">调查主题信息<shiro:hasPermission
					name="vote:pbsVoteTopic:edit">${not empty pbsVoteItem.topid.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="vote:pbsVoteTopic:edit">查看</shiro:lacksPermission></a></li>
		</c:if>
		<c:if test="${pbsVoteItem.topid.SBelongfunc ne  '1' }">
		<li><a
			href="${ctx}/vote/pbsVoteTopic/form?id=${pbsVoteItem.topid.id}">投票主题信息<shiro:hasPermission
					name="vote:pbsVoteTopic:edit">${not empty pbsVoteItem.topid.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="vote:pbsVoteTopic:edit">查看</shiro:lacksPermission></a></li>
		</c:if>
		
		<li><a
			href="${ctx}/vote/pbsVoteSubject/addsubjectform?id=${pbsVoteItem.sParentid.id}">题目信息<shiro:hasPermission
					name="vote:pbsVoteSubject:edit">${not empty pbsVoteItem.sParentid.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="vote:pbsVoteSubject:edit">查看</shiro:lacksPermission></a></li>
		<li class="active"><a >题目选项信息<shiro:hasPermission name="vote:pbsVoteItem:edit">${not empty pbsVoteItem.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="vote:pbsVoteItem:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<div class="hide topid" topid="${pbsVoteItem.sParentid.id}"></div>
	<form:form id="inputForm" modelAttribute="pbsVoteItem" action="${ctx}/vote/pbsVoteItem/addsavetopic" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>选择题目：</label>
			<div class="controls">
				    <form:select path="sParentid" class="input-xlarge required">
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>选项名称：</label>
			<div class="controls">
				<form:input path="sName" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>选项键值：</label>
			<div class="controls">
				<form:select path="sSort" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('vote_item')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="vote:pbsVoteItem:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>