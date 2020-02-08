<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>考试结果</title>
<meta name="decorator" content="default" />
<link rel="stylesheet" href="${ctxStatic}/common/vote.css"
	media="screen" type="text/css" />
<link href="${ctxStatic}/jquery-icheck/skins/all.css?v=0.7.1"
	rel="stylesheet">
<script type="text/javascript"
	src="${ctxStatic}/pbs/vote/js/pbsVoteSubjectForm.js"></script>
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

.form-horizontal .control-label.exam {
	width: 100px;
}

.form-horizontal .controls.exam {
	margin-left: 130px;
}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/exam/testPC/onlineList">考试列表</a></li>
		<li class="active"><a>在线考试</a></li>
	</ul>
	<br />
	<!-- <h4 class="tableNamefirst">人员基本信息</h4> -->
	<form:form id="inputForm" method="post" class="form-horizontal"
		onsubmit="javascript:return false;">
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">考试试卷：</label>
			<div class="controls">
				<label class="title">学习自测 （分数:${pbsExamaction.IReport}）</label>
			</div>
		</div>
		<div class="userCheck" attrflag="true" style="display: none"></div>
		<div class="gztt" id="notice" style="display: none;"
			attrid="${pbsExamactionCur.id}"></div>
		<c:forEach items="${pbsExampaperitems}" var="item" begin="0"
			varStatus="s">
			<div class="control-group">
				<label class="control-label exam">${item.ISort}.</label>
				<div class="controls exam">
					<label class="title"> ${item.sItem.SBody}<br> 正确答案:
						${item.sItem.SAnswer}
					</label>
					<ul class="list">
						<c:forEach items="${item.choiceList}" var="choice">
							<c:if test="${choice.SType  ne  '1'}">
								<li>
									<div class="iradio_flat-red " style="float: left;">
										<input type="radio" id="${choice.id}" name="${item.id}"
											<c:if test="${fn:contains(item.pbsExamactionitem.sDoanswer,fns:getDictLabel(choice.SKey, 'vote_item', ''))}">checked="true"</c:if>
											value="${fns:getDictLabel(choice.SKey, 'vote_item', '')}">
									</div> <label class="item" for="${choice.id}">${fns:getDictLabel(choice.SKey, 'vote_item', '')}.${choice.SBody}</label>
								</li>
							</c:if>
							<c:if test="${choice.SType  eq  '1' }">
								<li>
									<div class="icheckbox_flat-red" style="float: left;">
										<input type="checkbox" id="${choice.id}" name="${item.id}"
											<c:if test="${fn:contains(item.pbsExamactionitem.sDoanswer,fns:getDictLabel(choice.SKey, 'vote_item', ''))}">checked="true"</c:if>
											value="${fns:getDictLabel(choice.SKey, 'vote_item', '')}">
									</div> <label for="${choice.id}" class="item">${choice.SBody}</label>
								</li>
							</c:if>
						</c:forEach>
						<input type="hidden" attrid="${item.sItem.id}" class="answer"
							result="">
					</ul>
				</div>
			</div>

		</c:forEach>
		<div class="form-actions">
			<%-- 	<input id="btnSubmit" class="btn btn-primary" type="button"
				sDepartment="${sDepartment}" value="保 存" /> --%>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
	<script type="text/javascript"
		src="${ctxStatic}/pbs/exam/js/pbsExamSelfPcForm.js">
    
  </script>
</body>
</html>