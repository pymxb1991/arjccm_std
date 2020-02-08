\<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>菜单管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            //$("#name").focus();
            //关闭弹框事件
            $('#btnCancel').click(function() {
                parent.layer.close(parent.layerIndex);
            })

            $("#name").focus();
            $("#inputForm").validate({
                submitHandler: function(form){
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <%--<li><a href="${ctx}/book/ccmDatabaseBook/index">菜单列表</a></li>--%>
    <%--<li class="active"><a href="${ctx}/book/ccmDatabaseBook/add?id=${menu.id}&parent.id=${menu.parent.id}">菜单<shiro:hasPermission name="book:ccmDatabaseBook:edit">
        ${not empty menu.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:menu:edit">查看</shiro:lacksPermission></a></li>--%>
    <c:choose>
        <c:when test="${not empty menu.id}">
            <li class="active"><a href="${ctx}/book/ccmDatabaseBook/add?id=${menu.id}&parent.id=${menu.parent.id}">文章编辑</a></li></ul><br/>
            <form:form id="inputForm" modelAttribute="ccmDatabaseBook" action="${ctx}/book/ccmDatabaseBook/save" method="post" class="form-horizontal">
                <form:hidden path="id"/>
                <sys:message content="${message}"/>
                <div class="control-group">
                    <label class="control-label">文章标题:</label>
                    <div class="controls">
                        <form:input path="name" htmlEscape="false" maxlength="50" class="required input-xlarge"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">排序:</label>
                    <div class="controls">
                        <form:input path="sort" htmlEscape="false" maxlength="50" class="required digits input-small"/>
                        <span class="help-inline">排列顺序，升序。</span>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label">文章内容:</label>
                    <div class="controls">
                        <form:textarea path="content" htmlEscape="false" rows="3" maxlength="200" class="input-xxlarge"/>
                        <sys:ckeditor replace="content" uploadPath="/ccmDatabaseBook/email" />
                    </div>
                </div>
                <div class="form-actions">
                    <shiro:hasPermission name="book:ccmDatabaseBook:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
                    <input id="btnCancel" class="btn btn-danger" type="button" value="关 闭"/>
                    <%--<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
                </div>
            </form:form>
        </c:when>
        <c:otherwise>
            <li class="active"><a href="${ctx}/book/ccmDatabaseBook/add?id=${menu.id}&parent.id=${menu.parent.id}">菜单添加</a></li></ul><br/>
            <form:form id="inputForm" modelAttribute="ccmDatabaseBook" action="${ctx}/book/ccmDatabaseBook/saveAdd" method="post" class="form-horizontal">
                <form:hidden path="id"/>
                <sys:message content="${message}"/>
                <div class="control-group">
                    <label class="control-label">上级菜单:</label>
                    <div class="controls">
                        <sys:treeselect id="menu" name="parent.id" value="${menu.parent.id}" labelName="parent.name" labelValue="${menu.parent.name}"
                                        title="菜单" url="/book/ccmDatabaseBook/treeData" extId="${menu.id}" cssClass="required"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">目录名称:</label>
                    <div class="controls">
                        <form:input path="name" htmlEscape="false" maxlength="50" class="required input-xlarge"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">排序:</label>
                    <div class="controls">
                        <form:input path="sort" htmlEscape="false" maxlength="50" class="required digits input-small"/>
                        <span class="help-inline">排列顺序，升序。</span>
                    </div>
                </div>

                <%--<div class="control-group">
                    <label class="control-label">备注:</label>
                    <div class="controls">
                        <form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xxlarge"/>
                    </div>
                </div>--%>
                <div class="form-actions">
                    <shiro:hasPermission name="book:ccmDatabaseBook:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
                    <input id="btnCancel" class="btn btn-danger" type="button" value="关 闭"/>
                </div>
            </form:form>
        </c:otherwise>
    </c:choose>
</ul><br/>

</body>
</html>