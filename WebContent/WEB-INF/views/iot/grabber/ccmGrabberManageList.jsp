<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>抓拍机管理管理</title>
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
		<li class="active"><a href="${ctx}/grabber/ccmGrabberManage/">抓拍机管理列表</a></li>
		<%-- <shiro:hasPermission name="grabber:ccmGrabberManage:edit"><li><a href="${ctx}/grabber/ccmGrabberManage/form">抓拍机管理添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmGrabberManage" 
		action="${ctx}/grabber/ccmGrabberManage/" method="post" 
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="grabberName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<shiro:hasPermission name="grabber:ccmGrabberManage:edit">
				<li class="btns">
					<a onclick="parent.LayerDialog('${ctx}/grabber/ccmGrabberManage/form', '添加', '1100px', '700px')" class="btn btn-success">
						<i class="icon-plus"></i>添加
					</a>
				</li>
				<li class="btns">
					<a href="${ctx}/grabber/ccmGrabberManage/synchro" class="btn btn-warning">
						<i class="icon-refresh"></i>同步
					</a>
				</li>
			</shiro:hasPermission>
			<li class="btns">
				<a href="javascript:;" id="btnSubmit" class="btn btn-primary">
					<i class="icon-search"></i>查询
				</a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center;vertical-align:middle;">名称</th>
				<th style="text-align:center;vertical-align:middle;">组织资源编号</th>
				<th style="text-align:center;vertical-align:middle;">IP</th>
				<th style="text-align:center;vertical-align:middle;">端口号</th>
				<th style="text-align:center;vertical-align:middle;">状态</th>
				<th style="text-align:center;vertical-align:middle;">更新时间</th>
				<shiro:hasPermission name="grabber:ccmGrabberManage:edit"><th style="text-align:center;vertical-align:middle;">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmGrabberManage">
			<tr>
				<td style="text-align:center;vertical-align:middle;">
					${ccmGrabberManage.grabberName}
				</a></td>
				<td style="text-align:center;vertical-align:middle;">
					${ccmGrabberManage.resourcesNum}
				</td>
				<td style="text-align:center;vertical-align:middle;">
					${ccmGrabberManage.grabberIp}
				</td>
				<td style="text-align:center;vertical-align:middle;">
					${ccmGrabberManage.grabberPort}
				</td>
				<td style="text-align:center;vertical-align:middle;">
					${fns:getDictLabel(ccmGrabberManage.synchroState, 'ccm_grabber_synchro_state', '')}
				</td>
				<td style="text-align:center;vertical-align:middle;">
					<fmt:formatDate value="${ccmGrabberManage.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="grabber:ccmGrabberManage:edit"><td style="text-align:center;vertical-align:middle;">
					<a class="btnList" onclick="parent.LayerDialog('${ctx}/grabber/ccmGrabberManage/detail?id=${ccmGrabberManage.id}', '详情', '1100px', '700px')" title="详情"><i class="icon-comment-alt"></i></a>
    				<a class="btnList" onclick="parent.LayerDialog('${ctx}/grabber/ccmGrabberManage/form?id=${ccmGrabberManage.id}', '编辑', '1100px', '700px')" title="编辑"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/grabber/ccmGrabberManage/delete?id=${ccmGrabberManage.id}" onclick="return confirmx('确认要删除该抓拍机管理吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
					<a class="btnList" onclick="parent.LayerDialog('${ctx}/grabber/ccmGrabberManage/rule?id=${ccmGrabberManage.id}', '规则配置', '1100px', '400px')" title="规则配置"><i class="icon-reorder"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>