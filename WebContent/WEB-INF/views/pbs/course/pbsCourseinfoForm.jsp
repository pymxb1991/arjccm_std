  <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>课程信息管理</title>
<meta name="decorator" content="default" />
<script src="${ctxStatic}/pbs/course/pbsCourseinfoForm.js"
	type="text/javascript"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/course/pbsCourseinfo/">课程信息列表</a></li>
		<li class="active"><a
			href="${ctx}/course/pbsCourseinfo/form?id=${pbsCourseinfo.id}">课程信息<shiro:hasPermission
					name="course:pbsCourseinfo:edit">${not empty pbsCourseinfo.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="course:pbsCourseinfo:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsCourseinfo"
		action="${ctx}/course/pbsCourseinfo/save" method="post"
		class="form-horizontal">
		<div class="hide sParentid" parentid="${pbsCourseinfo.SParentid}"></div>
		<form:hidden path="id" />
		<sys:message content="${message}" />
		
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>课程类型：</label>
			<div class="controls">
				<%-- <form:input path="sType" htmlEscape="false" maxlength="10"
					class="input-xlarge required" /> --%>
				<form:select path="sType" class="input-xlarge required">
                    <form:option value="" label="" />
                    <form:options items="${fns:getDictList('course_type')}"
                        itemLabel="label" itemValue="value" htmlEscape="false" />
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>课程名称：</label>
			<div class="controls">
				<form:input path="sName" htmlEscape="false" maxlength="50"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">展示排序：</label>
			<div class="controls">
				<form:input path="iSort" htmlEscape="false" maxlength="10"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">展示的缩略图：</label>
			<div class="controls">
			<form:hidden id="sIconurl" path="sIconurl" htmlEscape="false"
                    maxlength="1000" class="input-xlarge" />
                <sys:ckfinder input="sIconurl" type="images"
                    uploadPath="/course/pbsCourseinfo/images"   />
			</div>
		</div>
		<div class="control-group">
            <label class="control-label"><font color="red">*&nbsp;</font>选择科目：</label>
            <div class="controls">
                <form:select path="sParentid" class="input-xlarge required">
                </form:select>
            </div>
        </div>
		<div class="control-group">
			<label class="control-label">文件路径：</label>
			<div class="controls">
				<form:hidden id="sFileurl" path="sFileurl" htmlEscape="false"
					maxlength="1000" class="input-xlarge" />
				<sys:ckfinder input="sFileurl" type="files"
					uploadPath="/course/pbsCourseinfo"  />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">课程内容：</label>
			<div class="controls">
				<form:textarea path="sContent" htmlEscape="false" rows="4"
                    maxlength="255" class="input-xxlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4"
					maxlength="255" class="input-xxlarge " />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="course:pbsCourseinfo:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>