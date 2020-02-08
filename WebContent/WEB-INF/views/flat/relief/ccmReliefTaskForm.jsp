<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>备勤任务管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        var error = "<label for=\"\" class=\"error\" style=\"display: inline-block;\">必选字段</label>";
        $(document).ready(function () {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {
                    //判断必填项
                    var people = $(".people-num")
                    for (var i = 0; i < people.length; i++) {
                        if (!people[i].value) {
                            $(people[i].parentNode).append(error)
                            return "";
                        }

                    }
                    loading('正在提交，请稍等...');
                    form.submit();
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
            $("#startTime").click(function () {
			    WdatePicker({
			        el: "startTime", //点击对象id，一般可省略el
			        lang: 'auto', //语言选择，一般用auto
			        dateFmt: 'yyyy-MM-dd HH:mm:ss', //时间显示格式，年月日 时分秒，年月日就是yyyy-MM-dd
			        minDate: '%y-%M-%d', //最小日期
			        readOnly: true, //是否只读
			        isShowClear: true, //是否显示“清空”按钮
			        isShowOK: true, //是否显示“确定”按钮
			        isShowToday: true, //是否显示“今天”按钮
			        autoPickDate: true //为false时 点日期的时候不自动输入,而是要通过确定才能输入，为true时 即点击日期即可返回日期值，为null时(推荐使用) 如果有时间置为false 否则置为true
			    })
			});
			$("#endTime").click(function () {
			    WdatePicker({
			        el: "endTime", //点击对象id，一般可省略el
			        lang: 'auto', //语言选择，一般用auto
			        dateFmt: 'yyyy-MM-dd HH:mm:ss', //时间显示格式，年月日 时分秒，年月日就是yyyy-MM-dd
			        minDate: '#F{$dp.$D(\'startTime\')}',
			        readOnly: true, //是否只读
			        isShowClear: true, //是否显示“清空”按钮
			        isShowOK: true, //是否显示“确定”按钮
			        isShowToday: true, //是否显示“今天”按钮
			        autoPickDate: true //为false时 点日期的时候不自动输入,而是要通过确定才能输入，为true时 即点击日期即可返回日期值，为null时(推荐使用) 如果有时间置为false 否则置为true
			    })
			});
            //初始化值
            reliefDeptName = $('#reliefDeptName').val();
            var num = $('#reliefNumber').val();
            if ((num || "").indexOf(",") != -1) {
                addPeopleNum(num.split(","));
            } else {
                addPeopleNum([num])
            }

        });

        var reliefDeptName = "";

        //判断是否改变值
        function changeName() {
            var temp = $('#reliefDeptName').val();
            if (temp != reliefDeptName) {
                reliefDeptName = temp;
                addPeopleNum();
            } else {
                reliefDeptName = temp;

            }

        }

        function addPeopleNum(str) {
            // var tempStr="";

            //判断有没有传参

            if (!str) {
                var people = $(".people-num");
                str = [];
                for (var i = 0; i < people.length; i++) {
                    str[i] = people[i].value
                }
            }

            //添加人数
            var temp = "";
            if (reliefDeptName.indexOf(",") != -1) {
                var split = reliefDeptName.split(",");
                for (var i = 0; i < split.length; i++) {
                    temp += "<div class=\"input-prepend input-append\">\n" +
                        "<span class=\"add-on\">" + split[i] + ":</span>\n" +
                        "<input onchange='changeNumber()' class=\"span2 people-num \" value='" + (str[i] || '') + "' type=\"text\">\n" +
                        "<span class=\"add-on\">人数</span>\n" +
                        "</div>" +
                        "<span class=\"help-inline\"><font color=\"red\">*</font> </span><br/>"
                }

            } else if (reliefDeptName) {
                temp += "<div class=\"input-prepend input-append\">\n" +
                    "<span class=\"add-on\">" + reliefDeptName + ":</span>\n" +
                    "<input onchange='changeNumber()' class=\"span2 people-num \" value='" + (str[0] || "") + "' type=\"text\">\n" +
                    "<span class=\"add-on\">人数</span>\n" +
                    "</div><span class=\"help-inline\"><font color=\"red\">*</font> </span>"
            }
            $("#peopleNum").html(temp);


        }

        function changeNumber() {
            //设置提交input的值
            var people = $(".people-num");
            var str = [];

            for (var i = 0; i < people.length; i++) {
                str[i] = people[i].value
            }
            $("#reliefNumber").attr("value", str.join(","));
        }

    </script>

    <style>
        #peopleNum {
            margin-left: 5px;
        }
    </style>

</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/relief/ccmReliefTask/summaryGraph">统计数据</a></li>
    <li><a href="${ctx}/relief/ccmReliefTask/list">备勤任务列表</a></li>
    <li class="active"><a href="${ctx}/relief/ccmReliefTask/form?id=${ccmReliefTask.id}">备勤任务<shiro:hasPermission
            name="relief:ccmReliefTask:edit">${not empty ccmReliefTask.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="relief:ccmReliefTask:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" onmouseover="changeName()" modelAttribute="ccmReliefTask"
           action="${ctx}/relief/ccmReliefTask/save" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">任务名称：</label>
        <div class="controls">
            <form:input path="taskName" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">备勤等级：</label>
        <div class="controls">
            <form:select path="reliefLevel" class="input-xlarge required">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('ccm_relief_level')}" itemLabel="label"
                              itemValue="value" htmlEscape="false"/>
            </form:select>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">备勤类别：</label>
        <div class="controls">
            <form:select path="reliefType" class="input-xlarge required">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('ccm_relief_type')}" itemLabel="label"
                              itemValue="value" htmlEscape="false"/>
            </form:select>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">开始时间：</label>
        <div class="controls">
            <input name="startTime" id="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
                   value="<fmt:formatDate value="${ccmReliefTask.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">结束时间：</label>
        <div class="controls">
            <input name="endTime" id="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
                   value="<fmt:formatDate value="${ccmReliefTask.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">集结地点：</label>
        <div class="controls">
            <form:input path="massAddress" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">备勤参与单位：</label>
        <div class="controls">
            <sys:treeselect id="reliefDept" name="reliefDept" value="${ccmReliefTask.reliefDept}"
                            labelName="reliefDeptName"
                            labelValue="${ccmReliefTask.reliefDeptName}"
                            title="部门" url="/sys/office/treeData?type=2" checked="true"
                            cssClass="input-xlarge required" allowClear="true" notAllowSelectParent="true"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>

    <div style="line-height: 40px;" class="control-group">
        <label class="control-label">每个单位人数：</label>
        <div class="controls">
            <form:input cssStyle="display: none" value="${ccmReliefTask.reliefNumber}" path="reliefNumber" onclick=""
                        htmlEscape="false" maxlength="1024" class="input-xlarge "/>
            <div id="peopleNum">
            </div>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">任务状态：</label>
        <div class="controls">
            <form:select path="reviewStatus" class="input-xlarge required">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('ccm_patrol_missions_status')}" itemLabel="label"
                              itemValue="value" htmlEscape="false"/>
            </form:select>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">审核状态：</label>
        <div class="controls">
            <form:select path="auditingStatus" class="input-xlarge required">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('auditing_status')}" itemLabel="label"
                              itemValue="value" htmlEscape="false"/>
            </form:select>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">备勤路线：</label>
        <div class="controls">
            <form:input path="roadLine" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">备勤辖区：</label>
        <div class="controls">
            <sys:treeselect id="area" name="area.id" value="${ccmReliefTask.area.id}" labelName="area.name"
                            labelValue="${ccmReliefTask.area.name}"
                            title="区域" url="/sys/area/treeData" cssClass="" allowClear="true"
                            notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">备注信息：</label>
        <div class="controls">
            <form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="relief:ccmReliefTask:edit"><input id="btnSubmit" class="btn btn-primary"
                                                                     type="submit"
                                                                     value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>