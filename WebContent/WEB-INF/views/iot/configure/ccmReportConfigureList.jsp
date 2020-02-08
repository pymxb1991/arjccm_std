<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
	<title>报警配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
<script src="${ctxStatic}/ccm/event/js/ccmEventIncident.js"
	type="text/javascript"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/configure/ccmReportConfigure/">报警配置列表</a></li>
		<%-- <shiro:hasPermission name="configure:ccmReportConfigure:edit"><li><a href="${ctx}/configure/ccmReportConfigure/form">报警配置添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmReportConfigure" action="${ctx}/configure/ccmReportConfigure/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		<%--	<li><label>报警类型：</label>
				<form:select path="reportType" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('ccm_configure_report_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>--%>
			<shiro:hasPermission name="grabber:ccmGrabberManage:edit">
				<li class="btns">
					<a onclick="parent.LayerDialog('${ctx}/configure/ccmReportConfigure/form', '添加', '1100px', '700px')" class="btn btn-success">
						<i class="icon-plus"></i>添加
					</a>
				</li>
			</shiro:hasPermission>
		<%--	<li class="btns">
				<a href="javascript:;" id="btnSubmit" class="btn btn-primary">
					<i class="icon-search"></i>查询
				</a>
			</li>--%>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center;vertical-align:middle;">报警类型</th>
<%--				<th style="text-align:center;vertical-align:middle;">头部展示类型</th>
				<th style="text-align:center;vertical-align:middle;">头部展示未处理类型</th>
				<th style="text-align:center;vertical-align:middle;">头部展示图片</th>
				<th style="text-align:center;vertical-align:middle;">图标</th>--%>
				<th style="text-align:center;vertical-align:middle;">报警音乐</th>
				<th style="text-align:center;vertical-align:middle;">地图展示图片</th>
				<shiro:hasPermission name="configure:ccmReportConfigure:edit"><th style="text-align:center;vertical-align:middle;"	>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmReportConfigure">
			<tr>
				<td style="text-align:center;vertical-align:middle;">
					${fns:getDictLabel(ccmReportConfigure.reportType, 'ccm_configure_report_type', '')}
				</td>
<%--				<td style="text-align:center;vertical-align:middle;">
					${fns:getDictLabel(ccmReportConfigure.topRtype, 'ccm_configure_top_rtype', '')}
				</td>
				<td style="text-align:center;vertical-align:middle;">
					${fns:getDictLabel(ccmReportConfigure.topRstype, 'ccm_configure_top_rstype', '')}
				</td>
				<td width="100px">
					<img src="${ccmReportConfigure.topRimages}" style="height:50px;"/>
				</td>
				<td width="100px">
					<img src="${ccmReportConfigure.icon}" style="height:50px;"/>
				</td>--%>
                <td style="text-align:center;vertical-align:middle;">
                    ${ccmReportConfigure.remarks}
                    </td>
				<td width="100px">
					<img src="${ccmReportConfigure.reportAimages}" style="height:50px;"/>
				</td>

				<shiro:hasPermission name="configure:ccmReportConfigure:edit"><td style="text-align:center;vertical-align:middle;">
					<a class="btnList" onclick="parent.LayerDialog('${ctx}/configure/ccmReportConfigure/detail?id=${ccmReportConfigure.id}', '详情', '1100px', '700px')" title="详情"><i class="icon-comment-alt"></i></a>
    				<a class="btnList" onclick="parent.LayerDialog('${ctx}/configure/ccmReportConfigure/form?id=${ccmReportConfigure.id}', '编辑', '1100px', '700px')" title="编辑"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/configure/ccmReportConfigure/delete?id=${ccmReportConfigure.id}" onclick="return confirmx('确认要删除该报警配置吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
</body>
</html>