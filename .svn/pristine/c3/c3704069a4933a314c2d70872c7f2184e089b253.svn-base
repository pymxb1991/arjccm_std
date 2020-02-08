<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>事件处理管理</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
<script charset="utf-8" type="text/javascript"
    src="${ctxStatic}/ccm/validator/validatorBaseForm.js"></script>
<style type="text/css">
	td{padding: 8px;border: 1px dashed #CCCCCC}
</style>
</head>
<body>
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/event/ccmEventIncident/list">案事件列表</a></li>
        <li><a
            href="${ctx}/event/ccmEventIncident/form?id=${ccmEventCasedeal.eventIncidentId}">案事件<shiro:hasPermission
                    name="event:ccmEventIncident:edit">${not empty ccmEventCasedeal.eventIncidentId?'修改':'添加'}  
                </shiro:hasPermission> <shiro:lacksPermission name="event:ccmEventIncident:edit">查看</shiro:lacksPermission></a></li>
        <shiro:hasPermission name="event:ccmEventCasedeal:edit">
            <li class="active"><a
                href="${ctx}/event/ccmEventCasedeal/dealform?eventIncidentId=${ccmEventCasedeal.eventIncidentId}&id=${ccmEventCasedeal.id}">处理信息<shiro:hasPermission
                    name="event:ccmEventCasedeal:edit">${not empty ccmEventCasedeal.id?'修改':'添加'}  
                </shiro:hasPermission> 
            </a></li>
        </shiro:hasPermission>
    </ul>
    <br />
    <form:form id="inputForm" modelAttribute="ccmEventCasedeal"
        action="${ctx}/event/ccmEventCasedeal/saveCasedeal" method="post"
        class="form-horizontal">
        <form:hidden path="id" />
        <form:hidden path="eventIncidentId" />
        <form:hidden path="handleDate" />
        <form:hidden path="handleStep" />
        <form:hidden path="handleStatus" />
        <form:hidden path="handleFeedback" />
        <form:hidden path="checkDate" />
        <form:hidden path="checkUser" />
        <form:hidden path="checkOpinion" />
        <form:hidden path="checkScore" />
        <sys:message content="${message}" />
         <table border="1px" style="border-color: #CCCCCC; border: 1px solid #CCCCCC; padding: 10px; width: 100%;">
			<tr>
				<td>
				<label class="control-label">处理人员：</label>
				<div class="controls">
					<sys:treeselect id="handleUser" name="handleUser.id" value="${ccmEventCasedeal.handleUser.id}" labelName="handleUser.name" labelValue="${ccmEventCasedeal.handleUser.name}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
				</div>
				</td>
			</tr>
			<tr>
				<td>
				<label class="control-label">处理截至时间：</label>
				<div class="controls">
					<input name="handleDeadline" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
						value="<fmt:formatDate value="${ccmEventCasedeal.handleDeadline}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				</div>
				</td>
			</tr>
			<tr>
				<td>
				<label class="control-label">是否督办：</label>
				<div class="controls">
					<form:select path="isSupervise" class="input-xlarge ">
						<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</div>
				</td>
			</tr>
			<tr>
				<td>
				<label class="control-label">事件说明及任务安排444：</label>
				<div class="controls">
					<form:textarea path="remarks" htmlEscape="false" rows="8" maxlength="1000" class="input-xxlarge "/>
				</div>
				</td>
			</tr>
			
			
			<!--  进行事件分流处理时，下述不显示
			<div class="control-group">
				<label class="control-label">处理时间：</label>
				<div class="controls">
					<input name="handleDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
						value="<fmt:formatDate value="${ccmEventCasedeal.handleDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">处理措施：</label>
				<div class="controls">
					<form:textarea path="handleStep" htmlEscape="false" rows="8" maxlength="256" class="input-xxlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">处理状态：</label>
				<div class="controls">
					<form:select path="handleStatus" class="input-xlarge ">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('ccm_event_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">案事件反馈：</label>
				<div class="controls">
					<form:textarea path="handleFeedback" htmlEscape="false" rows="8" maxlength="256" class="input-xxlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">考评日期：</label>
				<div class="controls">
					<input name="checkDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
						value="<fmt:formatDate value="${ccmEventCasedeal.checkDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">考评人员：</label>
				<div class="controls">
					<form:input path="checkUser" htmlEscape="false" maxlength="64" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">考评意见：</label>
				<div class="controls">
					<form:input path="checkOpinion" htmlEscape="false" maxlength="256" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">得分：</label>
				<div class="controls">
					<form:input path="checkScore" htmlEscape="false" maxlength="4" class="input-xlarge "/>
				</div>
			</div>
			
			 -->
			
		</table>
			
        <div class="form-actions">
            <shiro:hasPermission name="event:ccmEventCasedeal:edit">
                <input id="btnSubmit" class="btn btn-primary" type="submit"
                    value="保 存" />&nbsp;</shiro:hasPermission>
            <input id="btnCancel" class="btn" type="button" value="返 回"
                onclick="history.go(-1)" />
        </div>
    </form:form>
</body>
</html>