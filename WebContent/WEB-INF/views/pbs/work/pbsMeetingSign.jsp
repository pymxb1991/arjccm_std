<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>参与人详情</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/work/pbsMeeting/">活动信息列表</a></li>
		<shiro:hasPermission name="work:pbsActivityrec:edit">
			<li class="active"><a>参与人查看</a></li>
		</shiro:hasPermission>
	</ul>
	<br />
	<form  action="" method="post" class="form-horizontal">
		<c:if test="${type ne '2'}">
			<%-- <div class="control-group">
				<label class="control-label" id="activityTitle">总人数：</label>
				<div class="controls">
					<input type="text" readonly="readonly" value="${fn:length(signinIdList)+fn:length(LeavesList)+fn:length(NeedList)}" class="input-xlarge">
				</div>
			</div> --%>
			<div class="control-group">
				<label class="control-label" id="activityTitle">参与人数：</label>
				<div class="controls">
					<input type="text" readonly="readonly" value="${fn:length(signinIdList)}" class="input-xlarge">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" id="activityTitle">参与人员：</label>
				<div class="controls">
					<textarea readonly="readonly" rows="5" class="input-xlarge"><c:forEach items="${signinIdList}" var="signin">${signin.SName}; &nbsp;</c:forEach>
					</textarea>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" id="activityTitle">请假人数：</label>
				<div class="controls">
					<input type="text" readonly="readonly" value="${fn:length(LeavesList)}" class="input-xlarge">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" id="activityTitle">请假人员：</label>
				<div class="controls">
					<textarea readonly="readonly" rows="5" class="input-xlarge"><c:forEach items="${LeavesList}" var="leave">${leave.SName}; &nbsp;</c:forEach>
					</textarea>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" id="activityTitle">未回复人数：</label>
				<div class="controls">
					<input type="text" readonly="readonly" value="${fn:length(NeedList)}" class="input-xlarge">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" id="activityTitle">未回复人员：</label>
				<div class="controls">
					<textarea readonly="readonly" rows="5" class="input-xlarge"><c:forEach items="${NeedList}" var="need">${need.SName}; &nbsp;</c:forEach>
					</textarea>
				</div>
			</div>
			<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回"
			onclick="history.go(-1)" />
		</div>
		</c:if>
		<c:if test="${type eq '2'}">
			<table  class="table table-striped table-bordered table-condensed" style="width:30%">
				<thead>
					<tr>
						<th style="text-align:center">总人数</th>
						<th>被评价者</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${NeedList}" var="need" begin="0" end="0"> 
					<tr>
						<td style="text-align:center" rowspan="${fn:length(signinIdList)+fn:length(LeavesList)+fn:length(NeedList)}">${fn:length(signinIdList)+fn:length(LeavesList)+fn:length(NeedList)}</td>
						<td>${need.SName} &nbsp;</td>
					</tr> 
				</c:forEach>
				<c:forEach items="${NeedList}" var="need" begin="1"> 
					<tr>
						<td>${need.SName} &nbsp;</td>
					</tr> 
				</c:forEach>
				</tbody>
			</table>
			<div>
				<input id="btnCancel" class="btn" type="button" value="返 回"
			onclick="history.go(-1)" />
			</div>
		</c:if>
	</form>
</body>
</html>

