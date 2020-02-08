<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>建言献策</title>
<meta name="decorator" content="default" />

<script>
$(function() {
	  $("#sAreaid").load(ctx + "/proposal/pbsProposalarea/namelist",
		function() {
         $("#sAreaid").val(sAreaid).select2();
      	});
	  var $form = $("#applysubmit");
	  // 转发
	  $('#acceptSubmit').click(function() {
	    // 必须接收人
	    var sAreaid = $form.find("#sAreaid").val();
	    var sContent = $form.find("#sContent").val();
	    var sShowtype = $form.find("#sShowtype").val();
	    var sTitle =$form.find("#sTitle").val()
	    if(sTitle == '' || sTitle == null){
	    	alertx("建言标题不能为空");
	    	return;
	    }
	    // 提交申请
	    $.post(ctx + "/proposal/pbsProposalPC/proposalSubmit", {
	      "sAreaid" : sAreaid,
	      "sTitle" : sTitle,
	      "sShowtype" : sShowtype,
	      "sContent" : sContent,
	    }, function(data) {
	      if (data == "success") {
			alertx("提交成功");
	    	window.location.href = ctx + "/proposal/pbsProposalPC/proposalList";
	      } else {
	       	alertx("提交失败");
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
		<li><a href="${ctx}/proposal/pbsProposalPC/proposalList">建言信息列表</a></li>
		<li class="active"><a>提交建言</a></li>
	</ul>
	<form class="form-horizontal" id="applysubmit"  action="" method="post">
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>建言类型</label>
			<div class="controls">
				<select id="sAreaid" name="select" class="input-xlarge required"></select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" id="activityTitle"><font color="red">*&nbsp;</font>建言标题</label>
			<div class="controls">
				<input class="input-xlarge required" id="sTitle" type="text" placeholder="请输入标题"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label" id="activityType"><font color="red">*&nbsp;</font>公布类型</label>
			<div class="controls">
				<select id="sShowtype" name="select" class="input-xlarge required">
					<c:forEach items="${fns:getDictList('proposalshowtype')}"
						var="dict">
						<option value="${dict.value}">${dict.label}</option>
					</c:forEach>
				</select>
			</div>
		</div> --%>
		<div class="control-group" id="activityPlaceDiv">
			<label class="control-label" id="activityPlace">建言内容</label>
			<div class="controls">
				<textarea class="input-xlarge" id="sContent" rows="5" placeholder="请输入内容,1-5000字"></textarea>
			</div>
		</div>
		<div class="form-actions">
			<button type="button" id="acceptSubmit" class="btn btn-primary">提交</button>
		</div>
	</form>
</body>
</html>