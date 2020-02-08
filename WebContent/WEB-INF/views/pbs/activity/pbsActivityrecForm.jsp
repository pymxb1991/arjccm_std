<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>活动信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript"
	src="${ctxStatic}/pbs/activity/js/pbsActivityrecForm.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/activity/pbsActivityrec/">活动信息列表</a></li>
		<li class="active"><a
			href="${ctx}/activity/pbsActivityrec/form?id=${pbsActivityrec.id}">活动信息<shiro:hasPermission
					name="activity:pbsActivityrec:edit">${not empty pbsActivityrec.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="activity:pbsActivityrec:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="pbsActivityrec"
		action="${ctx}/activity/pbsActivityrec/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="hide sTypeid" topid="${pbsActivityrec.sType.id}"></div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>活动标题：</label>
			<div class="controls">
				<form:input path="sTitle" htmlEscape="false" maxlength="200"
					class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>活动类型：</label>
			<div class="controls">
				<form:select path="sType" class="input-xlarge required">
					<form:option value="" label="" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" id="activityType"><font color="red">*&nbsp;</font>发布类型：</label>
			<div class="controls">
				<form:select path="sStat" class="input-xlarge required" id="statSelect">
					<form:options items="${fns:getDictList('activity_stat')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>活动负责人：</label>
			<div class="controls">
				<sys:treeselect id="sMastermem" name="sMastermem"
					value="${pbsActivityrec.SMastermem.id}" labelName="sMastermem"
					labelValue="${pbsActivityrec.SMastermem.SName}" title="用户"
					url="/sys/pbsOffice/treeData?type=4" cssClass="input-large required"
					notAllowSelectParent="true" />
			</div>
		</div>
		<%-- 	<div class="control-group">
			<label class="control-label">参加活动组织名称列表：</label>
			<div class="controls">
				<sys:treeselect id="sAttendorgs" isAll="true"
					name="pbsActinotificationIds"
					value=" ${pbsActivityrec.pbsActinotificationIds}"
					labelName="sAttendorgs" labelValue="${pbsActivityrec.SAttendorgs}"
					title="用户" url="/sys/office/treeData?type=2"
					cssClass="input-large required" checked="true" />
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>参加人员姓名列表：</label>
			<div class="controls">
				<sys:treeselect id="sAttendants" name="pbsActinotificationIds"
					value="${pbsActivityrec.pbsActinotificationIds}" isAll="true"
					labelName="sAttendants" labelValue="${pbsActivityrec.SAttendants}"
					title="用户" url="/sys/pbsOffice/treeData?type=4"
					cssClass="input-large required" notAllowSelectParent="true"
					checked="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>活动开始时间：</label>
			<div class="controls">
				<input name="dtStarttime" type="text" readonly="readonly"
					maxlength="20" class="input-xlarge Wdate required"
					value="<fmt:formatDate value="${pbsActivityrec.dtStarttime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>活动结束时间：</label>
			<div class="controls">
				<input name="dtEndtime" type="text" readonly="readonly"
					maxlength="20" class="input-xlarge Wdate required"
					value="<fmt:formatDate value="${pbsActivityrec.dtEndtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font color="red">*&nbsp;</font>活动地点：</label>
			<div class="controls">
				<form:input path="sPlace" htmlEscape="false" maxlength="500"
					class="input-xlarge required" />
			</div>
		</div>
		<%--<div class="control-group">
			<label class="control-label">审核状态：</label>
			<div class="controls">
				<form:input path="sStat" htmlEscape="false" maxlength="10"
					class="input-xlarge " />
			</div> 
		</div>--%>

		<div class="control-group">
			<label class="control-label">参考信息：</label>
			<div class="controls">
				<form:input path="sUrl" htmlEscape="false" maxlength="500"
					class="input-xlarge" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评分标记：</label>
			<div class="controls">
				<form:select path="sFlag" class="input-xlarge">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('del_flag')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述信息：</label>
			<div class="controls">
				<form:input path="sDescription" htmlEscape="false" maxlength="1000"
					class="input-xlarge " />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">活动内容：</label>
			<div class="controls">
				<form:textarea path="sContent" htmlEscape="false" rows="4"
					class="input-xxlarge " />
			</div>
		</div>

		<%-- <div class="control-group">
            <label class="control-label">报名人数：</label>
            <div class="controls">
                <form:input path="sEnrolment" htmlEscape="false" maxlength="10"
                    class="input-xlarge " />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">登记人数：</label>
            <div class="controls">
                <form:input path="sRegistnum" htmlEscape="false" maxlength="10"
                    class="input-xlarge " />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">实到人数：</label>
            <div class="controls">
                <form:input path="sActualnum" htmlEscape="false" maxlength="10"
                    class="input-xlarge " />
            </div>
        </div> --%>
		<div class="control-group">
			<label class="control-label">附件1：</label>
			<div class="controls">
				<form:hidden id="sFile01" path="sFile01" htmlEscape="false"
					maxlength="500" class="input-xlarge" />
				<sys:ckfinder input="sFile01" type="files"
					uploadPath="/activity/pbsActivityrec" selectMultiple="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">附件2：</label>
			<div class="controls">
				<form:hidden id="sFile02" path="sFile02" htmlEscape="false"
					maxlength="500" class="input-xlarge" />
				<sys:ckfinder input="sFile02" type="files"
					uploadPath="/activity/pbsActivityrec" selectMultiple="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">附件3：</label>
			<div class="controls">
				<form:hidden id="sFile03" path="sFile03" htmlEscape="false"
					maxlength="500" class="input-xlarge" />
				<sys:ckfinder input="sFile03" type="files"
					uploadPath="/activity/pbsActivityrec" selectMultiple="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">附件4：</label>
			<div class="controls">
				<form:hidden id="sFile04" path="sFile04" htmlEscape="false"
					maxlength="500" class="input-xlarge" />
				<sys:ckfinder input="sFile04" type="files"
					uploadPath="/activity/pbsActivityrec" selectMultiple="true" />
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="activity:pbsActivityrec:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>