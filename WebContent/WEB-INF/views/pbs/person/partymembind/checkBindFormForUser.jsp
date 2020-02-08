<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>学员-组织关系管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/person/js/pbsPartymembindForm.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/person/pbsPartymembind/">学员表关系列表</a></li>
		<li class="active"><a
			href="${ctx}/person/pbsPartymembind/form?id=${pbsPartymembind.id}">学员表关系<shiro:hasPermission
					name="person:pbsPartymembind:edit">${not empty pbsPartymembind.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="person:pbsPartymembind:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsPartymembind"
        action="${ctx}/person/pbsPartymembind/save" method="post"
        class="form-horizontal">
        <form:hidden path="id" />
        <form:hidden path="sSource" value="sys_user" />
        <form:hidden path="sType" value="User" />
        <sys:message content="${message}" />
        <div class="control-group">
            <label class="control-label">学员名称：</label>
            <div class="controls">
                <input value="${pbsPartymembind.partymem.SName}"
                    htmlEscape="false" maxlength="50" class="input-xlarge" type="text"
                    readonly="readonly" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label"><font color="red">*&nbsp;</font>用户选择：</label>
            <div class="controls">
                    <sys:treeselect id="sPrimarykey01" name="sPrimarykey01"
                    value="${pbsPartymembind.SPrimarykey01}" labelName="username"
                    labelValue="${pbsPartymembind.username}" title="用户"
                    url="/sys/office/treeData?type=3" cssClass="input-small required"
                    allowClear="true" />
                </li>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">备注：</label>
            <div class="controls">
                <form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
            </div>
        </div>
        <div class="form-actions">
            <shiro:hasPermission name="person:pbsPartymembind:edit">
                <input id="btnSubmit" class="btn btn-primary" type="submit"
                    value="保 存" />&nbsp;</shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回"
                onclick="history.go(-1)" />
        </div>
    </form:form>
</body>
</html>