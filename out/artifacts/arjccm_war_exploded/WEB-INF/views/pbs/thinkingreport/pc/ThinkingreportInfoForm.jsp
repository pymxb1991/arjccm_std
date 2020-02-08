<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>思想汇报信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
  $(document).ready(
      function() {
        //$("#name").focus();
        $("#inputForm").validate(
            {
              submitHandler : function(form) {
                loading('正在提交，请稍等...');
                form.submit();
              },
              errorContainer : "#messageBox",
              errorPlacement : function(error, element) {
                $("#messageBox").text("输入有误，请先更正。");
                if (element.is(":checkbox") || element.is(":radio")
                    || element.parent().is(".input-append")) {
                  error.appendTo(element.parent().parent());
                } else {
                  error.insertAfter(element);
                }
              }
            });
      });
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/thinkingreport/Pc/thinkRepValueList">我的思想汇报</a></li>
		<li><a href="${ctx}/thinkingreport/Pc/thinkRepForwardList">被转发的思想汇报</a></li>
		<li class="active"><a>思想汇报详细信息</a></li>
		<li><a href="${ctx}/thinkingreport/Pc/thinknewform">新的思想汇报</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsThinkingreport"
		action="${ctx}/thinkingreport/pbsThinkingreport/thinkRepSubmit"
		method="post" class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">思想汇报的标题：</label>
			<div class="controls">
				<form:input path="sTitle" htmlEscape="false" maxlength="200"
					class="input-xlarge " readonly="true" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>发布者：</label>
			<div class="controls">
				<sys:treeselect id="sReporteruser" name="sReporteruser"
					disabled="true" value="${pbsThinkingreport.sReporteruser.id}"
					labelName="sReporteruser" labelValue="${pbsThinkingreport.sReporteruser.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required"
					allowClear="true" notAllowSelectParent="true" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>发布者学员：</label>
			<div class="controls">
				<sys:treeselect id="sReportermem" name="sReportermem"
					disabled="true" value="${pbsThinkingreport.sReportermem.id}"
					labelName="sReportermem" labelValue="${pbsThinkingreport.sReportermem.SName}"
					title="用户" url="/sys/pbsOffice/treeData?type=4" cssClass=""
					allowClear="true" notAllowSelectParent="true" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>接收者学员：</label>
			<div class="controls">
				<sys:treeselect id="sAcceptermem" name="sAcceptermem"
					disabled="true" value="${pbsThinkingreport.sAcceptermem.id}"
					labelName="sAcceptermem" labelValue="${pbsThinkingreport.sAcceptermem.SName}"
					title="用户" url="/sys/pbsOffice/treeData?type=4" cssClass="required"
					allowClear="true" notAllowSelectParent="true" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">思想汇报内容：</label>
			<div class="controls">
				<form:textarea id="sContent" htmlEscape="false" path="sContent"
					rows="4" maxlength="200" class="input-xxlarge" />
				<sys:ckeditor replace="sContent"
					uploadPath="/thinkingreport/pbsThinkingreport" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">评价信息：</label>
			<div class="controls">
				<div class="panel-group " id="accordion" style="width: 80%;">
					<table id="contentTable"
						class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>评价人</th>
								<th>评价等级</th>
								<th>评价内容</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${fn:length(ThinkingreportoptValue)> 0}">
								<c:forEach items="${ThinkingreportoptValue}" var="value"
									varStatus="valuestat">
									<tr>
										<td>${value.sOperatemem.SName}</td>
										<td>${fns:getDictLabel(value.SLevel,'taskvaluetype','')}
										</td>
										<td>${value.SContent}</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${fn:length(ThinkingreportoptValue)== 0}">
								<tr>
									<td>请等待评价</td>
									<td>-</td>
									<td>-</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">转发信息：</label>
			<div class="controls">
				<div class="panel-group " id="accordion" style="width: 80%;">
					<table id="contentTable"
						class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>转发人</th>
								<th>接收人</th>
								<th>转发内容</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${fn:length(ThinkingreportoptForward)> 0}">
								<c:forEach items="${ThinkingreportoptForward}" var="Forward"
									varStatus="valuestat">
									<tr>
										<td>${Forward.sOperatemem.SName}</td>
										<td>${Forward.sTransmem.SName}</td>
										<td>${Forward.STranscontent}</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${fn:length(ThinkingreportoptForward)== 0}">
								<tr>
									<td>暂无转发</td>
									<td>-</td>
									<td>-</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button"
				onclick="valueThink()" value="评价" />&nbsp; <input id="btnSubmit"
				class="btn btn-primary" onclick="forwardThink()" type="button"
				value="转发" />&nbsp; <input id="btnCancel" class="btn" type="button"
				value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>

	<script type="text/javascript">
    function valueThink() {
      window.location.href = ctx
          + "/thinkingreport/Pc/thinkRepDealPage?id=${pbsThinkingreport.id}&type=0";
    }

    function forwardThink() {
      window.location.href = ctx
          + "/thinkingreport/Pc/thinkRepDealPage?id=${pbsThinkingreport.id}&type=1";
    }
  </script>
</body>
</html>