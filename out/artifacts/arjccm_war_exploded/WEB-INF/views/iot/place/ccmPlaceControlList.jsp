<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>场所布控管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			/* $("#btnSubmit").on("click" ,function(){
				$("#searchForm").submit();
			}) */
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<script src="${ctxStatic}/ccm/event/js/ccmEventIncident.js"	type="text/javascript"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/place/ccmPlaceControl/">场所布控列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmPlaceControl" action="${ctx}/place/ccmPlaceControl/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>场所名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>布控类型：</label>
				<form:select path="controlType" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('ccm_control_level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><a
				onclick="parent.LayerDialog('${ctx}/place/ccmPlaceControl/form', '添加', '750px', '500px')"
				class="btn btn-success"><i class="icon-plus"></i> 添加</a></li>
 			<li class="btns"><a href="javascript:;" id="btnSubmit"
								class="btn btn-primary"> <i class="icon-search"></i> 查询</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>场所名称</th>
				<th>布控原因</th>
				<th>创建时间</th>
				<shiro:hasPermission name="place:ccmPlaceControl:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmPlaceControl">
			<tr>
				<td>
					${ccmPlaceControl.name}
				</td>
				<td>
					${ccmPlaceControl.reason}
				</td>
				<td>
					<fmt:formatDate value="${ccmPlaceControl.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="place:ccmPlaceControl:edit"><td>
					<a class="btnList" onclick="parent.parent.LayerDialog('${ctx}/place/ccmPlaceControl/form?id=${ccmPlaceControl.id}&hide1=true', '详情', '750px', '500px')" title="详情"><i class="icon-list-alt"></i></a>
					<a class="btnList" onclick="parent.parent.LayerDialog('${ctx}/warning/ccmEarlyWarning/placeList', '预警列表', '1200px', '500px')" title="预警列表"><i class="icon-list-ul"></i></a>
					<a href="${ctx}/place/ccmPlaceControl/delete?id=${ccmPlaceControl.id}" onclick="return confirmx('确认要删除该场所布控吗？', this.href)" title="删除" style="margin-left: 5px;"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>