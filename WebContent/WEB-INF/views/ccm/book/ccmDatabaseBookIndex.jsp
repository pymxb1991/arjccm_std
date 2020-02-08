<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>书籍管理</title>
    <meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/treetable.jsp" %>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#treeTable").treeTable({expandLevel : 3}).show();
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/book/ccmDatabaseBook/index">标题信息</a></li>
    <shiro:hasPermission name="sys:menu:edit"><li><a href="${ctx}/book/ccmDatabaseBook/">书籍列表</a></li></shiro:hasPermission>
</ul>
<sys:message content="${message}"/>
<form id="listForm" method="post">
    <table id="treeTable" class="table table-striped table-bordered table-condensed hide">
        <thead><tr><th>名称</th><th style="text-align:center;">排序</th><shiro:hasPermission name="book:ccmDatabaseBook:edit"><th>操作</th></shiro:hasPermission></tr></thead>
        <tbody><c:forEach items="${list}" var="menu">
            <tr id="${menu.id}" pId="${menu.parent.id ne '1'?menu.parent.id:'0'}">
                <td nowrap><a onclick="parent.LayerDialog('${ctx}/book/ccmDatabaseBook/add?id=${menu.id}', '修改', '1200px', '900px')">${menu.name}</a></td>
                <td style="text-align:center;">
                    <shiro:hasPermission name="book:ccmDatabaseBook:edit">
                        <input type="hidden" name="ids" value="${menu.id}"/>
                        <input name="sorts" type="text" value="${menu.sort}" style="width:50px;margin:0;padding:0;text-align:center;">
                    </shiro:hasPermission><shiro:lacksPermission name="book:ccmDatabaseBook:edit">
                    ${menu.sort}
                </shiro:lacksPermission>
                </td>
                <shiro:hasPermission name="book:ccmDatabaseBook:edit"><td nowrap>
                    <a class="btnList"
                            onclick="parent.LayerDialog('${ctx}/book/ccmDatabaseBook/add?id=${menu.id}', '修改', '1200px', '900px')"><i class="icon-pencil"></i></a>
                    <%--<a class="btnList" href="${ctx}/book/ccmDatabaseBook/add?id=${menu.id}" title="修改"><i class="icon-pencil"></i></a>--%>
                    <a class="btnList" href="${ctx}/book/ccmDatabaseBook/delete?id=${menu.id}" onclick="return confirmx('要删除该菜单及所有子菜单项吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
                    <a class="btnList"
                            onclick="parent.LayerDialog('${ctx}/book/ccmDatabaseBook/add?parent.id=${menu.id}', '添加下级菜单', '800px', '400px')"><i class="icon-plus"></i></a>
                    <%--<a class="btnList" href="${ctx}/book/ccmDatabaseBook/add?parent.id=${menu.id}" title="添加下级菜单"><i class="icon-plus"></i></a>--%>
                </td></shiro:hasPermission>
            </tr>
        </c:forEach></tbody>
    </table>
</form>
</body>
</html>