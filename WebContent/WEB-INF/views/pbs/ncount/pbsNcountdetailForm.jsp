<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>统计明细信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/ncount/js/pbsNcountdetailForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ncount/pbsNcountdetail/">统计明细信息列表</a></li>
		<li class="active"><a
			href="${ctx}/ncount/pbsNcountdetail/form?id=${pbsNcountdetail.id}">统计明细信息<shiro:hasPermission
					name="ncount:pbsNcountdetail:edit">${not empty pbsNcountdetail.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="ncount:pbsNcountdetail:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<div class="hide sParentid"
            topid="${pbsNcountdetail.sParentid.id}"></div>
	<form:form id="inputForm" modelAttribute="pbsNcountdetail"
		action="${ctx}/ncount/pbsNcountdetail/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="sSubitem" value="0" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">统计名目：</label>
			<div class="controls">
				<form:select path="sParentid" class="input-xlarge ">
					<form:option value="" label="" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">统记名称：</label>
			<div class="controls">
				<form:input path="sName" htmlEscape="false" maxlength="100"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">统计总数：</label>
			<div class="controls">
				<form:input path="iNumber" htmlEscape="false" maxlength="50"
					class="input-xlarge " />
			</div>
		</div>
		<%-- <div class="control-group">
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
					value="<fmt:formatDate value="${pbsNcountdetail.dtDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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

		<div class="form-actions">
			<shiro:hasPermission name="ncount:pbsNcountdetail:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>