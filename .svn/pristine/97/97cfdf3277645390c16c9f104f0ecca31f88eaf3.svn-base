<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>活动信息添加</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										$("#btnSubmit").attr("disabled", true);
					loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#btnSubmit").removeAttr('disabled');
					$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
								});
				 // 主题id
			    var topid = $(".sTypeid").attr("topid");
			    var changeFlag  = document.getElementById("selectChange").value;
			    // 选择主题
			    $("#sType").load(ctx + "/work/pbsMeeting/namelist?group="+changeFlag,
			    function() {
			        $("#sType").val(topid).select2();
			    });
			});
	
	
	function changeContent(){
		var changeFlag  = document.getElementById("selectChange").value;
		var activityTitle = document.getElementById("activityTitle");
		var activityType = document.getElementById("activityType");
		var activityPlace = document.getElementById("activityPlace");
		var activityPlaceDiv = document.getElementById("activityPlaceDiv");
		var activityMark = document.getElementById("activityMark"); 
		var partPerson = document.getElementById("partPerson"); 
		if(changeFlag == 0){
			activityTitle.innerHTML = "课程标题：";
			activityType.innerHTML = "课程类型：";
			activityPlace.innerHTML = "授课地点：";
			activityMark.innerHTML = "课程简介：";
			activityPlaceDiv.style.display = "block";
			var topid = $(".sTypeid").attr("topid");
		    // 选择主题
		    $("#sType").load(ctx + "/work/pbsMeeting/namelist?group="+changeFlag,
		    function() {
		        $("#sType").val(topid).select2();
		    }); 
		} else if(changeFlag == 1){
			activityTitle.innerHTML = "会议标题：";
			activityType.innerHTML = "会议类型：";
			activityPlace.innerHTML = "会议地点：";
			activityMark.innerHTML = "会议简介：";
			activityPlaceDiv.style.display = "block";
			var topid = $(".sTypeid").attr("topid");
		    // 选择主题
		    $("#sType").load(ctx + "/work/pbsMeeting/namelist?group="+changeFlag,
		    function() {
		        $("#sType").val(topid).select2();
		    });
		} else if (changeFlag == 2){
			activityTitle.innerHTML = "<font color='red'>*&nbsp;</font>评价标题：</label>";
			activityType.innerHTML = "评价类型：";
			activityMark.innerHTML = "评价简介： ";
			partPerson.innerHTML ="<font color='red'>*&nbsp;</font>被评价人员：</label>";
			activityPlaceDiv.style.display = "none";
			activityPlaceDiv.value="";
			var topid = $(".sTypeid").attr("topid");
		    var changeFlag  = document.getElementById("selectChange").value;
		    // 选择主题
		    $("#sType").load(ctx + "/work/pbsMeeting/namelist?group="+changeFlag,
		    function() {
		        $("#sType").val(topid).select2();
		    });
		} else {
			activityTitle.innerHTML = "<font color='red'>*&nbsp;</font>活动标题：</label>";
			activityType.innerHTML = "活动类型：";
			activityPlace.innerHTML = "<font color='red'>*&nbsp;</font>活动地点：</label>";
			activityMark.innerHTML = "活动简介：";
			activityPlaceDiv.style.display = "block";
			var topid = $(".sTypeid").attr("topid");
		    // 选择主题
		    $("#sType").load(ctx + "/work/pbsMeeting/namelist?group="+changeFlag,
		    function() {
		        $("#sType").val(topid).select2();
		    }); 
		}
	}
	function checkParam(){
        var sAttendants = $("#sAttendantsId").val();
        var html1 = '<label for="" class="error">必选字段<label>';
        if (sAttendants.length != 0) {
            $("#showPeople").html("");
        } else {
            $("#showPeople").html(html1);
            return false
        }
		var stat = $("#statSelect").val();
		//判断开始时间是否距离现在三天之前
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		var currentTime = new Date();
		var startTimeEx = Date.parse(startTime);
		var endTimeEx = Date.parse(endTime);
		var subTime = currentTime.getTime()-startTimeEx;
		var ThreeDay = 3*24*60*60*1000;
		if(stat == 2){
			if(subTime > ThreeDay){
				alertx("只能补录三天的数据");
				return;
			} 
			if(endTimeEx > currentTime.getTime()){
				alertx("补录结束时间不能大于当前时间");
				return;
			}
		}
		if(stat != 2){
			if(startTimeEx < currentTime.getTime()){
				alertx("开始时间不能小于当前系统时间");
				return;
			}
		}
		if(startTimeEx > endTimeEx){
			alertx("开始时间不能大于结束时间");
			return;
		}
		$("#inputForm").submit();
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/work/pbsMeeting/">活动信息列表</a></li>
		<shiro:hasPermission name="work:pbsActivityrec:edit">
			<li class="active"><a href="${ctx}/work/pbsMeeting/form">活动信息添加</a></li>
		</shiro:hasPermission>
	</ul>
	<br />
	<sys:message content="${message}" />
	<form:form id="inputForm" modelAttribute="pbsActivityrec"
		action="${ctx}/work/pbsMeeting/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>活动类别：</label>
			<div class="controls">
				<form:select path="sGroupType" class="input-xlarge required" onchange="changeContent()" id="selectChange">
					<form:options items="${fns:getDictList('actdefinitiontype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" id="activityTitle"><font color="red">*&nbsp;</font>党课标题：</label>
			<div class="controls">
				<form:input path="sTitle" htmlEscape="false" maxlength="30"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" id="activityType"><font color="red">*&nbsp;</font>党课类型：</label>
			<div class="controls">
				<form:select path="sType" class="input-xlarge">
					<form:option value="" label="" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" id="activityType"><font color="red">*&nbsp;</font>发布类型：</label>
			<div class="controls">
				<form:select path="sStat" class="input-xlarge required" id="statSelect">
					<form:options items="${fns:getDictList('activity_stat')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group" id="activityPlaceDiv">
			<label class="control-label" id="activityPlace"><font color="red">*&nbsp;</font>授课地点：</label>
			<div class="controls">
				<form:input path="sPlace" htmlEscape="false" maxlength="200"
					class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>开始时间：</label>
			<div class="controls">
				<input name="dtStarttime" type="text" readonly="readonly" maxlength="20" id="startTime"
					class="input-medium Wdate required"
					value="<fmt:formatDate value="${pbsActivityrec.dtStarttime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>结束时间：</label>
			<div class="controls">
				<input name="dtEndtime" type="text" readonly="readonly" maxlength="20" id="endTime"
					class="input-medium Wdate required"
					value="<fmt:formatDate value="${pbsActivityrec.dtEndtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" id="partPerson"><font color="red">*&nbsp;</font>参与人员：</label>
			<div class="controls">
				<sys:treeselect id="sAttendants" name="pbsActinotificationIds"
					value="${pbsActivityrec.pbsActinotificationIds}" isAll="true"
					labelName="sAttendants" labelValue="${pbsActivityrec.SAttendants}"
					title="用户" url="/sys/pbsOffice/treeData?type=4"
					cssClass="input-large required" notAllowSelectParent="true" checked="true" /><font color="red" id="showPeople"></font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" id="activityMark">党课简介：</label>
			<div class="controls">
				<form:textarea path="sContent" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge" />
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" onclick="checkParam()" class="btn btn-primary" type="button" value="发布提交" />
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>

