<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default" />
<title>建言献策</title>
<script>
  $(function() {
    var $tabSelectTree = $("#tabSelectTree");
    // 默认显示 当前为 公开建议
    $(".tabAll").hide();
    $(".sPublish").show();
		// 点击回复按钮
		$('#replyPage').click(
			function() {
				var sContent = prompt("请输入你的回复");
				// 获取当前 建议内容 
				if (sContent ) {
					var sProposalid = $("#replyPage").attr("areaid");
					$.post(ctx + "/proposal/pbsProposal/proposalReplyAction", {
						"sProposalid" : sProposalid,
						"sContent" : sContent,
						// 回复类型为 0
						"sOpttype" : "0",
					}, function(data) {
						if (data == "success" ) {
							alertx("回复成功");
							window.location.href = ctx + "/proposal/pbsProposalPC/proposalList";
						} else {
							alertx("回复失败");
						}
					});
				}else if(sContent == null || sContent ==""){
				  alertx("回复内容不能为空！");
				}
			});
		// 点击审核按钮
		$('#handlePage')
    	.click(
            function() {
              var handleFlag = "${handleFlag}";
              if (handleFlag == "true") {
                window.location.href = ctx
                    + "/proposal/pbsProposalPC/proposalHandlePage?id=${pbsProposal.id}";
              } else {
                alertx("您没有审核权限");
              }
      	});
  	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/proposal/pbsProposalPC/proposalList">建言信息列表</a></li>
		<li class="active"><a>建言详细信息</a></li>
	</ul>
	<form class="form-horizontal">
		<div class="control-group">
			<label class="control-label">建言状态</label>
			<div class="controls">
				<input type="text" class="input-xxlarge" value="${fns:getDictLabel(pbsProposal.SStat, 'proposalstattype', '')}" readonly="readonly"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">显示标题</label>
			<div class="controls">
				<input type="text" class="input-xxlarge" value="${pbsProposal.STitle}" readonly="readonly"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">建言内容</label>
			<div class="controls">
				<textarea class="input-xxlarge" rows="8" readonly="readonly">${pbsProposal.SContent}</textarea>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">建言部门</label>
			<div class="controls">
				<input type="text" class="input-xxlarge" value="${pbsProposalarea.sDepartment.name}" readonly="readonly"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核人</label>
			<div class="controls">
				<input type="text" class="input-xxlarge" value="${pbsProposalarea.sMastermem.SName}" readonly="readonly"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">回复信息</label>
			<div class="controls">
				<div class="panel-group " id="accordion" style="width: 80%;">
					<table id="contentTable"
						class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>回复人员</th>
								<th>回复时间</th>
								<th>回复内容</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.list}" var="proposalopt">
								<tr>
									<td>${proposalopt.sReportermem.SName}</td>
									<td><fmt:formatDate value="${proposalopt.createDate}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td>${proposalopt.SContent}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<c:if test="${pbsProposal.SStat ne 0}">
			<div class="form-actions">
				<button id='replyPage' type="button" areaid="${proposalId}" class="btn btn-primary">回复内容</button>
			</div>
		</c:if>
		<c:if test="${pbsProposal.SStat eq 0 }">
			<div class="form-actions">
				<button id='handlePage' type="button" class="btn btn-primary">审批意见</button>
			</div>
		</c:if>
	</form>
</body>
</html>