<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>题目管理</title>
<meta name="decorator" content="default" />
<link rel="stylesheet" href="${ctxStatic}/common/vote.css"
	media="screen" type="text/css" />
<link rel="stylesheet"
	href="${ctxStatic}/jquery-icheck/skins/all.css?v=0.7.1">
<script type="text/javascript"
	src="${ctxStatic}/jquery-icheck/js/jquery.icheck.js"></script>
<style>
.list li {
	list-style: none;
	margin-top: 10px;
}

.staticlist {
	margin-top: 30px;
	margin-left: 10%;
	margin-right: 10%;
}

.btn {
	margin-left: 10px;
}

.list input {
	position: absolute;
	top: -20%;
	left: -20%;
	display: block;
	width: 140%;
	height: 140%;
	margin: 0px;
	padding: 0px;
	background: rgb(255, 255, 255);
	border: 0px;
	opacity: 0;
}

.list {
	margin-left: 2px;
}

label.title {
	line-height: 27px;
}

label.item {
	margin-left: 15px;
}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/vote/pbsVotePc/investigationlist">问卷列表</a></li>
		<li class="active"><a href="#">调查问卷</a></li>
	</ul>
	<br />
	<form:form id="inputForm" method="post" class="form-horizontal"
		onsubmit="javascript:return false;">
		<sys:message content="${message}" />
		<div class="userCheck" attrflag="${userCheck}" style="display: none"></div>
		<div id="begin_2" topid="${pbsVoteTopic.id}" style="display: none"></div>
		
		<div class="control-group">
			<label class="control-label">调查问卷：</label>
			<div class="controls">
				<label class="title">${pbsVoteTopic.SName}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">问卷内容：</label>
			<div class="controls">
				<label class="title">${pbsVoteTopic.SBody}</label>
			</div>
		</div>
		<c:forEach items="${pbsVoteSubjectList}" var="Subject"
			varStatus="SubCount">
			<div class="control-group">
				<label class="control-label">${SubCount.index+1}.</label>
				<div class="controls">
					<label class="title"> ${Subject.SName}</label>
					<ul class="list" subjectid="${Subject.id}" attrid="">
						<c:if test='${Subject.sMode eq "1" }'>
							<c:forEach items="${Subject.pbsVoteItemList}" var="item">
								<li>
									<div class="iradio_flat-red " style="float: left;">
										<input tabindex="${item.ICnt}" type="radio" id="${item.id}"
											<c:if test='${item.voteFlag eq true }'> checked </c:if>
											name="${item.sParentid.id}">
									</div> <label class="item" for="${item.id}">${item.SName}</label>
								</li>
							</c:forEach>
						</c:if>
						<c:if test='${Subject.sMode eq "2" }'>
							<c:forEach items="${Subject.pbsVoteItemList}" var="item">
								<li>
									<div class="icheckbox_flat-red" style="float: left;">
										<input tabindex="1" type="checkbox" id="${item.id}"
											<c:if test='${item.voteFlag eq true }'> checked </c:if>>
									</div> <label for="${item.id}" class="item">${item.SName}</label>
								</li>
							</c:forEach>
						</c:if>
						<input type="hidden" attrid="${Subject.id}" class="answer"
                            result="">
					</ul>
				</div>
 
		<c:if test="${userCheck eq true}">
			<div class="control-group">
				<label class="control-label">统计项:</label>
				<div class="staticlist" subjectid="${Subject.id}">
					<c:forEach items="${Subject.pbsVoteItemList}" var="item">
						<div class="skillbar clearfix " data-percent="${item.iCntCent}%">
							<div class="skillbar-title" style="background: #d35400;">
								<span>${item.SName}</span>
							</div>
							<div class="skillbar-bar" style="background: #e67e22;"></div>
							<div class="skill-bar-percent">${item.iCntCent}%</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</c:if>
		</c:forEach>
		<div class="deadlineflag" attr="${deadlineflag}" style="display: none;"></div>
		<div class="form-actions">
			<c:if test="${(userCheck eq false) and ( deadlineflag eq false) }">
				<input id="btnSubmit" class="btn btn-primary" type="button"
					value="保 存" />
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
	<script type="text/javascript"
		src="${ctxStatic}/pbs/vote/js/pbsInvestigationForm.js"></script>
</body>
</html>