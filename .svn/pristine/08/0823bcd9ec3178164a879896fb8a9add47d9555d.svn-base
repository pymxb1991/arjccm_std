<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>反馈信息</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(function() {
		var $taskform =$("#taskform");
		// 点击 接收 按钮
		$('#applySubmit').click(function() {
			
			var sContent =$taskform.find("#sContent").val();
			var sOptstatus =$taskform.find("#sOptstatus").val();
			// 提交申请
			$.post(ctx + "/work/pbsWork/applyFinish", {
				"sTaskid" : $(this).attr("taskid"),
				"sContent":sContent,
				"sOptstatus":sOptstatus
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
		<li class="active"><a>反馈信息</a></li>
	</ul>
	<br />
	<div id="taskform" class="form-horizontal">
		<div class="control-group">
			<label class="control-label">任务标题</label>
			<div class="controls">
				<input value="${task.SResume}" maxlength="200" class="input-xlarge" type="text" readonly="readonly"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务类型</label>
			<div class="controls">
				<input value="${task.sType.SName}"  maxlength="200" class="input-xlarge" type="text" readonly="readonly"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务处理</label>
			<div class="controls">
				 <select id="sOptstatus" class="input-xlarge">
					<option value="SFEEDBACK">完成任务</option>
					<option value="FFEEDBACK">未完成任务</option>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" id="activityTitle">处理描述</label>
			<div class="controls">
				<textarea id="sContent" rows="5" placeholder="请输入内容,1-5000字" class="input-xlarge"></textarea>
			</div>
		</div>
		<div class="form-actions">
			<button type="button" id="applySubmit" class="btn btn-primary" taskid="${task.id}" >提交反馈</button>
		</div>
	</div>
</body>
</html>

