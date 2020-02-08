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

        // 任务id
        var sReportid = $(".sReportid").attr("topid");
        // 选择主题
        $("#sReportid").load(
            ctx + "/thinkingreport/pbsThinkingreport/namelist", function() {
              $("#sReportid").val(sReportid).select2();
            });
      });
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/thinkingreport/Pc/thinkRepValueList">我的思想汇报</a></li>
		<li><a href="${ctx}/thinkingreport/Pc/thinkRepForwardList">被转发的思想汇报</a></li>
		<li><a
			href="${ctx}/thinkingreport/Pc/thinkRepInfo?id=${pbsThinkingreport.id}">思想汇报详细信息</a></li>
		<li class="active"><a>评价思想汇报</a></li>
		<li><a href="${ctx}/thinkingreport/Pc/thinknewform">新的思想汇报</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsThinkingreportopt"
		action="${ctx}/thinkingreport/Pc/thinkRepAction" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="sType" value="0" />
		<%-- <form:hidden path="sReportid.id" value="${pbsThinkingreport.id}" /> --%>
		<sys:message content="${message}" />
		<div class="hide sReportid" topid="${pbsThinkingreport.id}"></div>
		<div class="control-group">
			<label class="control-label">思想汇报编号：</label>
			<div class="controls">
				<form:input maxlength="200" path="sReportid" class="input-xlarge"
					readonly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">思想汇报标题：</label>
			<div class="controls">
				<form:input path="sReportid" class="input-xlarge " readonly="true"
					value="${pbsThinkingreport.getSTitle()}" type="text" />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>评价等级：</label>
			<div class="controls">
				<form:select path="sLevel" class="input-xlarge required">
					<form:option value="" label="未选择" />
					<form:options items="${fns:getDictList('taskvaluetype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>评价/评语信息：</label>
			<div class="controls">
				<form:textarea id="sContent" htmlEscape="false" path="sContent"
					rows="4" maxlength="200" class="input-xxlarge required" />
<%-- 				<sys:ckeditor replace="sContent"  --%>
<%-- 					uploadPath="/thinkingreport/pbsThinkingreport" /> --%>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="保 存" />&nbsp; <input id="btnCancel" class="btn"
				type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>

</body>
</html>