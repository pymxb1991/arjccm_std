<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>详细信息</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(function() {
		// 点击 接收 按钮
		$('#acceptSubmit').click(function() {
			// 提交申请
			$.post(ctx + "/task/pbsTaskrec/receiveTask", {
				"id": $(this).attr("taskid")
			}, function(data) {
				if (data == "success") {
					alert("接收成功");
					 window.location.href = ctx + "/work/pbsWork/";
				} else {
					alert("接收失败");
				}
			});
		});
		
		// 判断为空
		function isEmpty(obj) {
			if (typeof obj == "undefined" || obj == null || obj == "") {
				return true;
			} else {
				return false;
			}
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
	<form:form  method="post" class="form-horizontal" modelAttribute="task">
		<div class="control-group">
			<label class="control-label">任务标题</label>
			<div class="controls">
				<form:input path="sResume" htmlEscape="false" maxlength="200" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务类型</label>
			<div class="controls">
				<form:input path="sType.SName" htmlEscape="false" maxlength="200" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务内容</label>
			<div class="controls">
				<form:input path="SContent" htmlEscape="false" maxlength="200" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" id="activityTitle">截止时间</label>
			<div class="controls">
				<input name="sDeadline" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
 					style="width:205px" value="<fmt:formatDate value="${task.sDeadline}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" id="activityTitle">接收时间</label>
			<div class="controls">
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
 					style="width:205px" value="<fmt:formatDate value="${taskoprecs.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			</div>
		</div>
		<div class="control-group" >
			<label class="control-label" id="activityTitle">任务状态</label>
			<div class="controls">
				<input value="${fns:getDictLabel( task.sOptstatus, 'taskoptstatus', '')}" readonly="readonly"
 					type="text" htmlEscape="false" maxlength="64"
  					class="input-xlarge" style="width:205px"/> 
			</div>
		</div>
	</form:form>
	<div class="form-horizontal">
		<div class="form-actions">
			<c:if test="${task.sOptstatus eq 'CREATE'}">
				<input id="acceptSubmit" class="btn btn-primary" type="button" taskid="${task.id}" value="接受处理"/>
			</c:if>
			<c:if test="${task.sOptstatus eq 'ACCEPT'}">
				<a href="${ctx}/work/pbsWork/givemefeedback?id=${task.id}">
					<button id="dealSubmit" class="btn btn-primary" taskid="${task.id}">任务处理</button>
				</a>
			</c:if>
			<c:if test="${task.SStatus eq '99' and task.sOptstatus ne 'CANECL'}">
		        <a href="${ctx}/work/pbsWork/taskValueInfo?id=${task.id}">
					<button class="btn btn-primary">查看评价</button>
				</a>
		    </c:if>
		</div>
	</div>
</body>
</html>

