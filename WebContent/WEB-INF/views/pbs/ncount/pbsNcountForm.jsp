<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>统计类别管理</title>
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
		<li><a href="${ctx}/ncount/pbsNcount/">统计类别列表</a></li>
		<li class="active"><a
			href="${ctx}/ncount/pbsNcount/form?id=${pbsNcount.id}">统计类别<shiro:hasPermission
					name="ncount:pbsNcount:edit">${not empty pbsNcount.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="ncount:pbsNcount:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsNcount"
		action="${ctx}/ncount/pbsNcount/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<form:hidden path="sSubitem" value="0" />
		<div class="control-group">
			<label class="control-label">统计类别：</label>
			<div class="controls">
				<form:select path="sType" class="input-xlarge ">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('stat_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">统计名称：</label>
			<div class="controls">
				<form:input path="sName" htmlEscape="false" maxlength="50"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">统计总数：</label>
			<div class="controls">
				<form:input path="iNumber" htmlEscape="false" maxlength="11" readonly="true"
					class="input-xlarge  digits" />
			</div>
		</div>
		<%-- 	<div class="control-group">
			<label class="control-label">是否有子统计项目：</label>
			<div class="controls">
				<form:select path="sSubitem" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">统计数据日期：</label>
			<div class="controls">
				<input name="dtDate" type="text" readonly="readonly" maxlength="20"
					class="input-xlarge Wdate "
					value="<fmt:formatDate value="${pbsNcount.dtDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述信息：</label>
			<div class="controls">
				<form:textarea path="sDesc" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="ncount:pbsNcount:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>