<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>巡逻点位结果管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/ccm/patrol/js/ccmPatrolRespointForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/patrol/ccmPatrolRespoint/">数据列表</a></li>
		<li class="active"><a
			href="${ctx}/patrol/ccmPatrolRespoint/form?id=${ccmPatrolRespoint.id}">数据<shiro:hasPermission
					name="patrol:ccmPatrolRespoint:edit">${not empty ccmPatrolRespoint.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="patrol:ccmPatrolRespoint:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="ccmPatrolRespoint"
		action="${ctx}/patrol/ccmPatrolRespoint/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<!-- ccmccmPatrolIDs  -->
		<div class="ccmccmPatrolIDs" style="display: none;"
		pointId="${ccmPatrolRespoint.pointId.id}"
        resultId="${ccmPatrolRespoint.resultId.id}"></div>
        
        <div class="control-group">
            <label class="control-label">巡检结果编号：</label>
            <div class="controls">
                <form:select id="resultId" path="resultId.id" class="input-xlarge ">
                    <form:option value="" label="加载中..." />
                </form:select>
            </div>
        </div>
        
		<div class="control-group">
			<label class="control-label">巡检点：</label>
			<div class="controls">
				<form:select id="pointId" path="pointId.id" class="input-xlarge ">
					<form:option value="" label="加载中..." />
				</form:select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">巡检结果：</label>
			<div class="controls">
				<form:input path="result" htmlEscape="false" maxlength="64"
                    class="input-xlarge " />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="patrol:ccmPatrolRespoint:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>