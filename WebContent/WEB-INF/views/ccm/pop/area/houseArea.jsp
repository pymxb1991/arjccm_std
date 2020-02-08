<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>

<sys:treeselect id="roomId" name="roomId.id" value="${ccmPopTenant.id}" labelName="roomId.houseBuild"
                labelValue="${ccmPopTenant.houseBuild}"
                title="房屋" url="/tree/ccmTree/treeDataArea?type=9&areaid=${netIds}" cssClass=""
                allowClear="true" notAllowSelectParent="true" cssStyle="width: 150px"/>