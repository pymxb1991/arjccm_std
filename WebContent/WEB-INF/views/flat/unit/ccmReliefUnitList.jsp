<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>备勤单位管理</title>
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
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/relief/ccmReliefTask/arrangement">备勤任务安排</a></li>
    <shiro:hasPermission name="relief:ccmReliefTask:edit">
        <li class="active"><a href="${ctx}/unit/ccmReliefUnit/">备勤单位任务列表</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="ccmReliefUnit" action="${ctx}/unit/ccmReliefUnit/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>备勤人员：</label>
            <sys:treeselect id="user" name="user.id" value="${ccmReliefUnit.user.id}" labelName="user.name"
                            labelValue="${ccmReliefUnit.user.name}"
                            title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true"
                            notAllowSelectParent="true"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>备勤人员</th>
        <th>备勤任务</th>
        <th>备勤车辆</th>
        <th>接受时间</th>
        <th>任务状态</th>
        <th>描述信息</th>
        <shiro:hasPermission name="unit:ccmReliefUnit:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="ccmReliefUnit">
        <tr>
            <td><a href="${ctx}/unit/ccmReliefUnit/form2?id=${ccmReliefUnit.id}">
                    ${ccmReliefUnit.userName}
            </a></td>
            <td>
                    ${ccmReliefUnit.missionsId}
            </td>
            <td>
                    ${ccmReliefUnit.patrolVehicles}
            </td>
            <td>
                <fmt:formatDate value="${ccmReliefUnit.departureTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                    ${fns:getDictLabel(ccmReliefUnit.status, 'ccm_patrol_missions_status', '')}
            </td>
            <td>
                    ${ccmReliefUnit.remarks}
            </td>
            <shiro:hasPermission name="unit:ccmReliefUnit:edit">
                <td>
                    <a href="${ctx}/unit/ccmReliefUnit/form2?id=${ccmReliefUnit.id}">修改</a>
                    <a href="${ctx}/unit/ccmReliefUnit/delete?id=${ccmReliefUnit.id}"
                       onclick="return confirmx('确认要删除该备勤单位吗？', this.href)">删除</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>