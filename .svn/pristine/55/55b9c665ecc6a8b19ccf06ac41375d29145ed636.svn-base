<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>题目管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/vote/js/pbsVoteSubjectForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<c:if test="${pbsVoteSubject.sParentid.SBelongfunc eq  '1' }">
			<li><a href="${ctx}/vote/pbsVoteTopic/listsurvey">调查主题信息列表
			</a></li>
			<li><a
				href="${ctx}/vote/pbsVoteTopic/formsurvey?id=${pbsVoteSubject.sParentid.id}">调查主题信息<shiro:hasPermission
						name="vote:pbsVoteTopic:edit">${not empty pbsVoteSubject.sParentid.id?'修改':'添加'}</shiro:hasPermission>
					<shiro:lacksPermission name="vote:pbsVoteTopic:edit">查看</shiro:lacksPermission></a></li>
		</c:if>
		<c:if test="${pbsVoteSubject.sParentid.SBelongfunc ne  '1' }">
			<li><a href="${ctx}/vote/pbsVoteTopic/">投票主题信息列表 </a></li>
			<li><a
				href="${ctx}/vote/pbsVoteTopic/form?id=${pbsVoteSubject.sParentid.id}">投票主题信息<shiro:hasPermission
						name="vote:pbsVoteTopic:edit">${not empty pbsVoteSubject.sParentid.id?'修改':'添加'}</shiro:hasPermission>
					<shiro:lacksPermission name="vote:pbsVoteTopic:edit">查看</shiro:lacksPermission></a></li>
		</c:if>
		<li class="active"><a >题目信息<shiro:hasPermission
					name="vote:pbsVoteSubject:edit">${not empty pbsVoteSubject.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="vote:pbsVoteSubject:edit">查看</shiro:lacksPermission></a></li>
		<c:if test="${! empty pbsVoteSubject.id}">
			<li><a
				href="${ctx}/vote/pbsVoteItem/addformtopic?sParentid=${pbsVoteSubject.id}&topid.sBelongfunc=${pbsVoteSubject.sParentid.SBelongfunc}&topid.id=${pbsVoteSubject.sParentid.id}">选项信息添加</a></li>
		</c:if>
	</ul>
	<br />
	<div class="hide topid" topid="${pbsVoteSubject.sParentid.id}"></div>
	<form:form id="inputForm" modelAttribute="pbsVoteSubject"
		action="${ctx}/vote/pbsVoteSubject/addsubjectsave" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>选择主题：</label>
			<div class="controls">
				<form:select path="sParentid" class="input-xlarge required">
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>题目名称：</label>
			<div class="controls">
				<form:input path="sName" htmlEscape="false" maxlength="500"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>题目模式：</label>
			<div class="controls">
				<form:select path="sMode" class="input-xlarge required">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('vote_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>顺序：</label>
			<div class="controls">
				<form:input path="sSort" htmlEscape="false" maxlength="50"
					class="input-xlarge required" />
			</div>
		</div>
		<c:if test="${fn:length(itemList)> 0}">
			<div class="panel-group control-group" id="accordion">
				<table id="contentTable"
					class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>选项</th>
							<th>题目</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${itemList}" var="item">
							<tr>
								<td><a
									href="${ctx}/vote/pbsVoteItem/addformtopic?id=${item.id}&topid.sBelongfunc=${pbsVoteSubject.sParentid.SBelongfunc}&topid.id=${pbsVoteSubject.sParentid.id}"">
										${fns:getDictLabel(item.sSort, 'vote_item', '')}</a></td>
								<td><a
									href="${ctx}/vote/pbsVoteItem/addformtopic?id=${item.id}&topid.sBelongfunc=${pbsVoteSubject.sParentid.SBelongfunc}&topid.id=${pbsVoteSubject.sParentid.id}"">${item.SName}</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>
		<div class="form-actions">
			<shiro:hasPermission name="vote:pbsVoteSubject:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>