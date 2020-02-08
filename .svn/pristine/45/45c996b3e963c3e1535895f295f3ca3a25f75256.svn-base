<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>投票主题信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/vote/js/pbsVoteTopicForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/vote/pbsVoteTopic/">投票主题信息列表</a></li>
		<li class="active"><a
			href="${ctx}/vote/pbsVoteTopic/form?id=${pbsVoteTopic.id}">投票主题信息<shiro:hasPermission
					name="vote:pbsVoteTopic:edit">${not empty pbsVoteTopic.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="vote:pbsVoteTopic:edit">查看</shiro:lacksPermission></a></li>
		<c:if test="${not empty pbsVoteTopic.id}">
			<li class=""><a
				href="${ctx}/vote/pbsVoteSubject/addsubjectform?sParentid.id=${pbsVoteTopic.id}&sParentid.SBelongfunc=0">投票题目信息添加</a></li>
		</c:if>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsVoteTopic"
		action="${ctx}/vote/pbsVoteTopic/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="sBelongfunc" value="0" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>标题名称：</label>
			<div class="controls">
				<form:input path="sName" htmlEscape="false" maxlength="200"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>内容：</label>
			<div class="controls">
				<form:textarea path="sBody" htmlEscape="false" rows="4"
                    maxlength="255" class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>投票开始时间：</label>
			<div class="controls">
				<input name="dtStart" type="text" readonly="readonly" maxlength="20"
					class="input-xlarge Wdate required"
					value="<fmt:formatDate value="${pbsVoteTopic.dtStart}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>投票结束时间：</label>
			<div class="controls">
				<input name="dtEnd" type="text" readonly="readonly" maxlength="20"
					class="input-xlarge Wdate required"
					value="<fmt:formatDate value="${pbsVoteTopic.dtEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否关闭：</label>
			<div class="controls">
				<form:select path="sClose" class="input-xlarge ">
					<form:options items="${fns:getDictList('yes_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开启自动关闭：</label>
			<div class="controls">
				<form:select path="sIsautoclose" class="input-xlarge ">
					<form:options items="${fns:getDictList('yes_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
 
 <c:if test="${fn:length(subList)> 0}">
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
					<c:forEach items="${subList}" var="item" varStatus ="subitem">
						<tr>
							<td>
							<a href="${ctx}/vote/pbsVoteSubject/addsubjectform?id=${item.id}">
										${subitem.index+1}</a></td>
							<td><a href="${ctx}/vote/pbsVoteSubject/addsubjectform?id=${item.id}">${item.SName}</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
			</c:if>
		<div class="form-actions">
			<shiro:hasPermission name="vote:pbsVoteTopic:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>