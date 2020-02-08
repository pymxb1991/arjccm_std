<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>在线考试</title>
<meta name="decorator" content="default" />

<link href="${ctxStatic}/jquery-icheck/skins/all.css?v=0.7.1"
	rel="stylesheet">
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
<script>
    var time = ${pbsExampaper.IExamtime};
    var maxtime = time*60;
    function CountDown() {
        if (maxtime >= 0) {
            minutes = Math.floor(maxtime / 60);
            seconds = Math.floor(maxtime % 60);
            msg = "距离结束还有" + minutes + "分" + seconds + "秒";
            document.all["timer"].innerHTML = msg;
            if (maxtime < 10 * 60){
            	document.all["timer"].style.color = "red";
            	msg = "距离结束还有" + minutes + "分" + seconds + "秒，到时间后将为您自动交卷";
                document.all["timer"].innerHTML = msg;
            }
            --maxtime;
        } else{
            clearInterval(timer);
            document.getElementById("")
        }
    }
    timer = setInterval("CountDown()", 1000); 
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/exam/testPC/onlineList">考试列表</a></li>
		<li class="active"><a>在线考试</a></li>
	</ul>
	<!-- <h4 class="tableNamefirst">人员基本信息</h4> -->
	<form:form id="inputForm" method="post" class="form-horizontal"
		onsubmit="javascript:return false;">
		<sys:message content="${message}" />
		<div class="control-group">
			<div style="margin-left:10%;margin-top:10px;margin-bottom:10px">
				<label style="font-size:16px;">考试试卷：${pbsExampaper.STitle}</label>
				<!-- <label style="font-size:16px;margin-left:10%;">在线考试人数：133人</label> -->
				<label style="font-size:16px;margin-left:10%;color:#1786F5" id="timer"></label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">选择代表部门：</label>
			<div class="controls">
				<select id="sDepartment" class="input-xlarge ">
					<option value="" label="" />
				</select>
			</div>
		</div>
		<div class="gztt" id="paperid" style="display: none;"
			attrid="${paperid}"></div>
		<c:forEach items="${itemList}" var="item" begin="0" varStatus="s">
			<div class="control-group">
				<label class="control-label exam">${item.ISort}.</label>
				<div class="controls exam">
					<label class="title"> ${item.sItem.SBody}</label>
					<ul class="list">
						<c:forEach items="${item.choiceList}" var="choice">
							<c:if test="${choice.SType ne '1'}">
								<li>
									<div class="iradio_flat-red " style="float: left;">
										<input type="radio" id="${choice.id}" name="${item.id}"
											value="${fns:getDictLabel(choice.SKey, 'vote_item', '')}">
									</div> <label class="item" for="${choice.id}">${fns:getDictLabel(choice.SKey, 'vote_item', '')}.${choice.SBody}</label>
								</li>
							</c:if>
							<c:if test="${choice.SType eq '1' }">
								<li>
									<div class="icheckbox_flat-red" style="float: left;">
										<input type="checkbox" id="${choice.id}" name="${item.id}"
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
			<input id="btnSubmit" class="btn btn-primary" type="button"
				sDepartment="${sDepartment}" value="交卷" /> <input id="btnCancel"
				class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>
	<script type="text/javascript"
		src="${ctxStatic}/pbs/exam/js/pbsExamOnlinePcForm.js"></script>
</body>
</html>