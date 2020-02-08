<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>

<sys:treeselect id="areaGridId" name="areaGridId.id" value="${area.id}" labelName="areaGridId.name" labelValue="${area.name}"
title="区域" url="/tree/ccmTree/treeDataArea?type=7&areaid=${areaIds}"  cssClass="required" allowClear="true" notAllowSelectParent="true" cssStyle="width: 150px" />

