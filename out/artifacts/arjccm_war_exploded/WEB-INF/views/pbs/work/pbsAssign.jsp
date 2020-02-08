<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>任务指派</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(function() {
		// 任务id
		$("#sPartment").load(ctx + "/sys/pbsDepartmentetc/namelist?type=2",
			function() {
			  $("#sPartment").val("#sPartment").select2();
			  var selectString = $(this).val();
	    	  //  查询 学员
			  $("#sMember").load(ctx + "/person/pbsPartymem/findPbsListByOffice?officeid="+selectString,function(){
				  $("#sMember").val("#sMember").select2();
			  });
			});
		// 修改 部门
		// $("#sPartment").change(function() {
		// 	$("#sMember").load(ctx + "/person/pbsPartymem/findPbsListByOffice", {
		// 		"officeid" : $(this).val()
		// 	}, function() {
		// 		$("#sMember").val("#sMember").select2();
		// 	});
		// });
		
		// 任务类型
		$("#sType").load(ctx + "/task/pbsTasktype/namelist",function(){
			$("#sType").val("#sType").select2();
		});
		// 上级任务
		$("#sSuperiorid").load(ctx + "/task/pbsTaskrec/namelist?getType=0");
		
		// 点击
		$('#applySubmit').click(function() {
			var $form = $("#Applyform");
			var sMember = $form.find("#sMember").val();
			var sPartment = $form.find("#sPartment").val();
			var sContent = $form.find("#sContent").val();
			var sSuperiorid = $form.find("#sSuperiorid").val();
			var sType = $form.find("#sType").val();
			var sResume = $form.find("#sResume").val();
			var sDeadline = $form.find("#sDeadline").val();
			// 如果为空
			if (isEmpty(sResume)) {
				alert("请填写标题");
				return;
			}
			if(isEmpty(sContent)){
				alert("请输入任务内容");
				return;
			}
			if(isEmpty(sDeadline)){
				alert("请选择截至时间");
				return;
			}
			var currentTime = new Date();
			var deadLine = Date.parse(sDeadline);
			if(deadLine < currentTime.getTime()){
				alert("截止时间要大于当前时间");
				return;
			}
			// 提交申请
			$.post(ctx + "/work/pbsWork/addapply", {
				"sBindmember":sMember,
				"sExecdepartment":sPartment,
				"sContent":sContent,
				"sSuperiorid":sSuperiorid,
				"sType":sType,
				"sResume":sResume,
				"sDeadline":sDeadline
			}, function(data) {
				if (data == "success") {
					alert("提交成功");
					window.location.href = ctx + "/work/pbsWork/distributedList";
				} else {
					alert("提交失败");
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
		<li><a  href="${ctx}/work/pbsWork/">指派给我的工作列表</a></li>
		<li><a href="${ctx}/work/pbsWork/distributedList">我派发的工作列表</a></li>
		<shiro:hasPermission name="work:pbsActivityrec:edit">
			<li class="active"><a>任务指派</a></li>
		</shiro:hasPermission>
	</ul>
	<br />
	<div id="Applyform" class="form-horizontal">
		<div class="control-group">
			<label class="control-label "><font color="red">*&nbsp;</font>任务标题</label>
			<div class="controls">
				<input type="text" maxlength="64" class="input-xlarge required" id="sResume" placeholder="请输入任务标题"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>任务类型</label>
			<div class="controls">
				<select id="sType" class="input-xlarge required">
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上级任务</label>
			<div class="controls">
				<select id="sSuperiorid" class="input-xlarge">
					<option value="">请选择</option>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>任务内容</label>
			<div class="controls">
				<textarea id="sContent" rows="4" placeholder="请输入内容,1-5000字" class="input-xlarge "></textarea>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" id="activityTitle"><font color="red">*&nbsp;</font>截止时间</label>
			<div class="controls">
				<input name="sDeadline" id="sDeadline" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
 					style="width:270px" value="<fmt:formatDate value="${task.sDeadline}" pattern="yyyy-MM-dd HH:mm:ss"/>"
 					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<!-- <div class="control-group">
			<label class="control-label">指派部门</label>
			<div class="controls">
				<select id="sPartment" name="select" class="input-xlarge ">
				</select>
			</div>
		</div> -->
		<div id="sPartment" >
		<div class="control-group">
			<label class="control-label "><font color="red">*&nbsp;</font>指派部门</label>
			<div class="controls">
				<input type="text" maxlength="64" class="input-xlarge required" />
			</div>
		</div>
			<div id="sMember" >
		<div class="control-group">
			<label class="control-label "><font color="red">*&nbsp;</font>指派人员</label>
			<div class="controls">
				<input type="text" maxlength="64" class="input-xlarge required" />
			</div>
		</div>
		<!-- <div class="control-group">
			<label class="control-label">指派人员</label>
			<div class="controls">
				<select id="sMember" name="select" class="input-xlarge ">
				</select>
			</div>
		</div> -->
		<div class="form-actions">
			<input id="applySubmit" class="btn btn-primary" type="button" value="发布提交" />
		</div>
	</div>
</body>
</html>

