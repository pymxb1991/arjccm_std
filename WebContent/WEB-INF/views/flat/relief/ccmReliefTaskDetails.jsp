<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>备勤任务管理</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<form:form id="inputForm" modelAttribute="ccmReliefTask" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <br/>
    <div class="control-group">
        <label class="control-label">备勤任务：</label>
        <div class="controls">
            <form:input path="taskName" disabled="true" htmlEscape="false" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">备勤等级：</label>
        <div class="controls">
            <form:select path="reliefLevel" disabled="true" class="input-xlarge required">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('ccm_relief_level')}" itemLabel="label"
                              itemValue="value" htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">备勤类别：</label>
        <div class="controls">
            <form:select path="reliefType" disabled="true" class="input-xlarge required">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('ccm_relief_type')}" itemLabel="label"
                              itemValue="value" htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">开始时间：</label>
        <div class="controls">
            <input name="patrolTime" disabled="disabled" type="text" readonly="readonly" maxlength="20"
                   class="input-medium Wdate "
                   value="<fmt:formatDate value="${ccmReliefTask.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">结束时间：</label>
        <div class="controls">
            <input name="endTime" disabled="disabled" type="text" readonly="readonly" maxlength="20"
                   class="input-medium Wdate required"
                   value="<fmt:formatDate value="${ccmReliefTask.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">参与单位：</label>
        <div class="controls">
            <sys:treeselect id="reliefDept" disabled="disabled" name="reliefDept" value="${ccmReliefTask.reliefDept}"
                            labelName="reliefDeptName" labelValue="${ccmReliefTask.reliefDeptName}"
                            title="部门" url="/sys/office/treeData?type=2" checked="true" cssClass="" allowClear="true"
                            notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">每个单位人数：</label>
        <div class="controls">
            <form:input path="reliefNumber" disabled="true" htmlEscape="false" maxlength="1024" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">巡逻路线：</label>
        <div class="controls">
            <form:input path="roadLine" disabled="true" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">巡逻辖区：</label>
        <div class="controls">
            <sys:treeselect id="area" disabled="disabled" name="area.id" value="${ccmReliefTask.area.id}"
                            labelName="area.name" labelValue="${ccmReliefTask.area.name}"
                            title="区域" url="/sys/area/treeData" cssClass="" allowClear="true"
                            notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">任务状态：</label>
        <div class="controls">
            <form:select path="reviewStatus" disabled="true" class="input-xlarge ">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('ccm_patrol_missions_status')}" itemLabel="label"
                              itemValue="value" htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">描述信息：</label>
        <div class="controls">
            <form:textarea path="remarks" disabled="true" htmlEscape="false" rows="4" maxlength="255"
                           class="input-xxlarge "/>
        </div>
    </div>
    <div class="form-actions">
            <%--parent.document.getElementsByClassName("layui-layer-close"))--%>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="parent.layer.close(parent.layer.index)"/>
    </div>
</form:form>
<HR SIZE=3>
</body>
</html>