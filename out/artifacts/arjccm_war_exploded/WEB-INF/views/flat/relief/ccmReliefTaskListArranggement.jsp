<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>备勤任务安排</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });

        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }

        function LayerDialog(src, title, height, width) {
            parent.layer.open({
                type : 2,
                title : title,
                area : [ height, width ],
                fixed : true, //固定
                maxmin : true,
                //btn: ['确定', '关闭'], //可以无限个按钮
                content : src,
                end:function() {
                }
            });
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/relief/ccmReliefTask/arrangement">备勤任务安排</a></li>
    <shiro:hasPermission name="relief:ccmReliefTask:edit">
        <li><a href="${ctx}/unit/ccmReliefUnit/">备勤单位任务列表</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="ccmReliefTask" action="${ctx}/relief/ccmReliefTask/arrangement" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>任务名称：</label>
            <form:input path="taskName" htmlEscape="false" maxlength="64" class="input-medium"/>
        </li>
        <li><label>开始时间：</label>
            <input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${ccmReliefTask.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </li>
        <li><label>结束时间：</label>
            <input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="<fmt:formatDate value="${ccmReliefTask.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>任务名称</th>
        <th>备勤等级</th>
        <th>备勤类别</th>
        <th>开始时间</th>
        <th>结束时间</th>
        <th>备勤参与部门</th>
        <th>每个单位人数</th>
        <th>任务状态</th>
        <th>备勤辖区</th>
        <shiro:hasPermission name="relief:ccmReliefTask:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="ccmReliefTask">
        <tr>
            <td>
                    ${ccmReliefTask.taskName}
            </td>
            <td>
                    ${fns:getDictLabel(ccmReliefTask.reliefLevel, 'ccm_relief_level', '')}
            </td>
            <td>
                    ${fns:getDictLabel(ccmReliefTask.reliefType, 'ccm_relief_type', '')}
            </td>
            <td>
                <fmt:formatDate value="${ccmReliefTask.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                <fmt:formatDate value="${ccmReliefTask.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                    ${ccmReliefTask.reliefDeptName}
            </td>
            <td>
                    ${ccmReliefTask.reliefNumber}
            </td>
            <td>
                    ${fns:getDictLabel(ccmReliefTask.reviewStatus, 'ccm_patrol_missions_status', '')}
            </td>
            <td>
                    ${ccmReliefTask.area.name}
            </td>
            <shiro:hasPermission name="relief:ccmReliefTask:edit">
                <td>
                    <a onclick="LayerDialog('${ctx}/relief/ccmReliefTask/details?id=${ccmReliefTask.id}', '查看', '1000px', '570px')">查看</a>
                    <a onclick="LayerDialog('${ctx}/unit/ccmReliefUnit/form?id=${ccmReliefTask.id}', '安排备勤任务', '1200px', '900px')">安排备勤任务</a>
                    <a href="${ctx}/unit/ccmReliefUnit?missionsId=${ccmReliefTask.id}" >查看安排</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>