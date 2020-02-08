<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html class="feedback">
<head>
<title>审批处理</title>
<meta name="decorator" content="default"/>
<script>
	$(function() {
	  $('#acceptSubmit').click(function() {
	    var proposalid = $(this).attr("proposalid");
	    var sOpresult = $("#sOpresult").val();
	    var sContent = $("#sContent").val();
	    $.post(ctx + "/proposal/pbsProposal/proposalHandleAction", {
	      "sProposalid" : proposalid,
	      "sContent" : sContent,
	      "sOpresult" :sOpresult,
	      // 审核类型为 1
	      "sOpttype":"1",
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
		<li class="active"><a>意见审批</a></li>
	</ul>
	<form class="form-horizontal">
		<div class="control-group">
			<label class="control-label">审批内容</label>
			<div class="controls">
				<input type="text" placeholder="请输入审批内容概述" value="${proposal.STitle}" readonly="readonly" class="input-xlarge">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作类型</label>
			<div class="controls">
				<select id="sOpresult"  class="input-xlarge">
					<option value="1">同意</option>
					<option value="2">驳回</option>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审批意见</label>
			<div class="controls">
				<textarea id="sContent" rows="8" placeholder="请输入内容,1-5000字" class="input-xlarge"></textarea>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<button type="button" id="acceptSubmit"proposalid="${proposal.id}" class="btn btn-primary">提交</button>
			</div>
		</div>
	</form>
</body>
</html>