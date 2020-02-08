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
        $("#btnSubmit").click(function() {
          /* if(isEmpty($("#title").val())){
           alert("标题不能为空");
           return;
          }
          if(isEmpty($("#sAcceptermemName").val())){
           alert("请选择接收学员");
           return;
          }
          if(isEmpty($("#content").val())){
           alert("内容不能为空");
           return;
          }
          $("#inputForm").submit(); */
        });
      });
  function isEmpty(obj) {
    if (typeof obj == "undefined" || obj == null || obj == "") {
      return true;
    } else {
      return false;
    }
  }
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/thinkingreport/Pc/thinkRepValueList">我的思想汇报</a></li>
		<li><a href="${ctx}/thinkingreport/Pc/thinkRepForwardList">被转发的思想汇报</a></li>
		<li class="active"><a>新的思想汇报</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsThinkingreport"
		action="${ctx}/thinkingreport/Pc/thinkRepSubmit" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<form:hidden path="sStat" value="0" />
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>思想汇报的标题：</label>
			<div class="controls">
				<form:input path="sTitle" htmlEscape="false" maxlength="200"
					id="title" class="input-xlarge required" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>接收者学员：</label>
			<div class="controls">
				<sys:treeselect id="sAcceptermem" name="sAcceptermem"
					value="${pbsThinkingreport.sAcceptermem.id}" labelName="sAcceptermem"
					labelValue="${pbsThinkingreport.sAcceptermem.SName}" title="用户"
					url="/sys/pbsOffice/treeData?type=4" allowClear="true"
					notAllowSelectParent="true" cssClass="required" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>思想汇报内容：</label>
			<div class="controls">
				<%-- <form:textarea path="sContent" htmlEscape="false" rows="4"
					id="content" maxlength="255" class="input-xxlarge required" /> --%>
			 <form:textarea id="sContent" htmlEscape="false" path="sContent"
					rows="4" maxlength="280" class="input-xxlarge required" />
				<sys:ckeditor replace="sContent" uploadPath="/thinkingreport/pbsThinkingreport" />
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