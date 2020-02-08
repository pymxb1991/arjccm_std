<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>详细信息</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(function() {
	    // 获取 taskid
	    var taskid = $(".feedbackoprecsFlag").attr("taskid");
	    //  获取 是否反馈了
	    var taskValueFlage = $(".feedbackoprecsFlag").attr("flag");
	    // 是否存在
	    var existFlag = $(".feedbackoprecsFlag").attr("SStatus");
	    
	    // 判断 当前的 状态
	    if(taskValueFlage == "true"){
	    	if(existFlag =='99'){
	    		return;
	    	}
	    // 添加  评价与 撤销按钮
	    document.getElementById("confirmBtn").addEventListener('click',
	    function() {
	    	if(confirm("确定关闭任务？")){
	    		$.post(ctx + "/task/pbsTaskrec/cancelTask?id="+taskid,function(){
	        		window.location.href = ctx + "/work/pbsWork/distributedList";
	        	});
	    	}
	    });
	    document.getElementById("reviewBtn").addEventListener('click',
	    	    function() {
			    	$.post(ctx + "/task/pbsTaskrec/cancelTask?id="+taskid,function(){
		        		window.location.href = ctx + "/work/pbsWork/distributedfeedback?id=" + taskid;
		        	});
	    	    });
	    } else if( taskValueFlage == "false"){
	    	if(existFlag =='99'){
	    		return;
	    	}
	    // cancelBtn
	    document.getElementById("cancelBtn").addEventListener('click',
	    function() {
	    	if(confirm("确定撤销任务？")){
	    		 $.post(ctx + "/work/pbsWork/cancelTask?id="+taskid, function() {
	 	            window.location.href = ctx + "/work/pbsWork/distributedList";
	 	        });
	    	}
	    });
	  }
	    
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/work/pbsWork/">指派给我的工作列表</a></li>
		<li><a href="${ctx}/work/pbsWork/distributedList">我派发的工作列表</a></li>
		<shiro:hasPermission name="work:pbsActivityrec:edit">
			<li><a href="${ctx}/work/pbsWork/form">任务指派</a></li>
		</shiro:hasPermission>
		<li class="active"><a>详细信息</a></li>
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
		<div class="control-group">
			<label class="control-label" id="activityTitle">截止时间</label>
			<div class="controls">
				<input name="sDeadline" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:205px"
 					value="<fmt:formatDate value="${pbsTaskrec.sDeadline}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" id="activityTitle">接收时间</label>
			<div class="controls">
				<c:if test="${empty acceptoprecs.createDate }">
				<input value="暂未接受" readonly="readonly" type="text"  maxlength="64" class="input-xlarge" style="width:205px"/> 
				</c:if> 
				<c:if test="${not empty acceptoprecs.createDate }">
					<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:205px"
 					value="<fmt:formatDate value="${acceptoprecs.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
				</c:if> 
			</div>
		</div>
		<div class="control-group" >
			<label class="control-label" id="activityTitle">任务状态</label>
			<div class="controls">
				<input value="${fns:getDictLabel( pbsTaskrec.sOptstatus, 'taskoptstatus', '')}" readonly="readonly"
 					type="text"  maxlength="64"
  					class="input-xlarge" style="width:205px"/> 
			</div>
		</div>
		<div class="control-group" >
			<label class="control-label" id="activityTitle">接收人部门</label>
			<div class="controls">
				<input value="${pbsTaskrec.sExecdepartment.name}" readonly="readonly"
 					type="text"  maxlength="64"
  					class="input-xlarge" style="width:205px"/> 
			</div>
		</div>
		<div class="control-group" >
			<label class="control-label" id="activityTitle">接收人员</label>
			<div class="controls">
				<input value="${pbsTaskrec.sBindmember.SName}" readonly="readonly"
 					type="text"  maxlength="64"
  					class="input-xlarge" style="width:205px"/> 
			</div>
		</div>
		<div class="control-group" >
			<label class="control-label" id="activityTitle">当前状态</label>
			<div class="controls">
				<input value="${fns:getDictLabel(pbsTaskrec.sOptstatus, 'taskoptstatus', '')}" readonly="readonly"
 					type="text"  maxlength="64"
  					class="input-xlarge" style="width:205px"/> 
			</div>
		</div>
	</form:form>
	<div class="form-horizontal">
		<div class="form-actions">
			<c:if test="${pbsTaskrec.SStatus ne '99'}">
				<nav class="mui-bar mui-bar-tab">
					<div class="feedbackoprecsFlag" taskid="${pbsTaskrec.id}"
						SStatus="${pbsTaskrec.SStatus}" style="display: none"
						flag="${feedbackoprecs}"></div>
					<c:if test="${feedbackoprecs eq true}">
						<input id="reviewBtn" class="btn btn-primary" type="button" value="评价任务"/>&nbsp;
						<input id="confirmBtn" class="btn" type="button" value="关闭任务"/>
					</c:if>
					<c:if test="${feedbackoprecs ne  true}">
							<input id="cancelBtn" class="btn btn btn-primary" type="button" value="撤销处理"/>
					</c:if>
				</nav>
			</c:if>
			<c:if test="${pbsTaskrec.SStatus eq '99' and pbsTaskrec.sOptstatus ne 'CANECL'}">
				<a href="${ctx}/work/pbsWork/taskValueInfo?id=${pbsTaskrec.id}">
					<input class="btn btn-primary" type="button" value="查看评价"/>
				</a>
			</c:if>
		</div>
	</div>	
</body>
</html>