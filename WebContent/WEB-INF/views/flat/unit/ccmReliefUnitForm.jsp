<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>备勤单位管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                    window.parent.document.getElementById("mainFrame").setAttribute("src","${ctx}/unit/ccmReliefUnit/");
                    parent.layer.close(parent.layer.index);
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
            $("#iframeDetails").attr("src", "${ctx}/relief/ccmReliefTask/details2" + location.search);
            if ("${isCcmReliefUnit}" == "yes") {
                $("#form").hide();
                $('input,select').attr('disabled', true);
                $('#btnCancel1').removeAttr("disabled")
                $('textarea').attr('disabled', "disabled");
                $("#isCcmReliefUnit").show();
            }
        });
    </script>
</head>
<body>
<br/>
<iframe id="iframeDetails" style="width: 1200px;height: 480px;border: 0px;" src=""></iframe>

<div class="control-group">
    <label>备勤安排</label>
</div>
<div id="isCcmReliefUnit" style="display: none;" class="control-group">
    <label class="error">已有安排请去，单位列表修改</label>
</div>

<form:form id="inputForm" modelAttribute="ccmReliefUnit" action="${ctx}/unit/ccmReliefUnit/save" method="post"
           class="form-horizontal">
    <div id="form">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>

    <div class="control-group">
        <label class="control-label">备勤人员：</label>
        <div class="controls">
            <sys:treeselect id="user" name="user" value="${ccmReliefUnit.user}"
                            disabled="${isCcmReliefUnit=='yes' ? 'disabled' : ''}" labelName="userName"
                            labelValue="${ccmReliefUnit.userName}"
                            title="用户" url="/unit/ccmReliefUnit/getTreeData?id=${id}" cssClass="required"
                            allowClear="true" checked="true" notAllowSelectParent="true"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>

    <form:hidden path="missionsId" />

    <div class="control-group">
        <label class="control-label">备勤车辆：</label>
        <div class="controls">
            <form:input path="patrolVehicles" htmlEscape="false" maxlength="128" class="input-xlarge "/>
        </div>
    </div>
    <%--<div class="control-group">--%>
        <%--<label class="control-label">接受时间：</label>--%>
        <%--<div class="controls">--%>
            <%--<input name="departureTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "--%>
                   <%--value="<fmt:formatDate value="${ccmReliefUnit.departureTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
                   <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
        <%--<label class="control-label">任务状态：</label>--%>
        <%--<div class="controls">--%>
            <%--<form:select path="status" class="input-xlarge required">--%>
                <%--<form:option value="" label=""/>--%>
                <%--<form:options items="${fns:getDictList('ccm_patrol_missions_status')}" itemLabel="label"--%>
                              <%--itemValue="value" htmlEscape="false"/>--%>
            <%--</form:select>--%>
        <%--</div>--%>
    <%--</div>--%>
    <div class="control-group">
        <label class="control-label">描述信息：</label>
        <div class="controls">
            <form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="unit:ccmReliefUnit:edit"><input id="btnSubmit" class="btn btn-primary" type="submit"
                                                                   value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="parent.layer.close(parent.layer.index)"/>
    </div>
    </div>
</form:form>
</body>
</html>