<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>业务申请单主表管理</title>
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
</head>
<body>
	<ul class="nav nav-tabs">
		<li ><a href="${ctx}/act/plmAct/mySupTodolist?status=02">未完成任务</a></li>
		<li class="active"><a href="${ctx}/act/plmAct/mySupFinishlist">已结束任务</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="plmAct" action="${ctx}/act/plmAct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictListExt('01,02','plm_act_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('act_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>发起人：</label>
				<sys:treeselect id="supIni" name="supIni.id" value="${plmAct.supIni.id}" labelName="supIni.name" labelValue="${plmAct.supIni.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>状态</th>
				<th>类型</th>
				<th>发起人</th>
				<th>更新时间</th>
				<shiro:hasPermission name="act:plmAct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="plmAct">
			<tr>
				<td><a href="${ctx}/act/plmAct/form?id=${plmAct.id}">
					${plmAct.title}
				</a></td>
				<td>
					${fns:getDictLabel(plmAct.status, 'plm_act_status', '')}
				</td>
				<td>
					${fns:getDictLabel(plmAct.type, 'act_type', '')}
				</td>
				<td>
					${plmAct.supIni.name}
				</td>
				<td>
					<fmt:formatDate value="${plmAct.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="act:plmAct:edit"><td>
    				<a href="${ctx}/act/plmAct/form?id=${plmAct.id}"title="显示详情"><i class="icon-file"></i></a>
					<%-- <a href="${ctx}/act/plmAct/delete?id=${plmAct.id}" onclick="return confirmx('确认要删除该业务申请单主表吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>