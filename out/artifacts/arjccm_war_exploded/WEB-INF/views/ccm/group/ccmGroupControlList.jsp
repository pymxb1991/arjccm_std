<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>自治群管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmit").on("click" ,function(){
        		$("#searchForm").submit();
        	})
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
		<li class="active"><a href="${ctx}/group/ccmGroupControl/">自治群列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmGroupControl" action="${ctx}/group/ccmGroupControl/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>成员数：</label>
				<form:input path="number" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>需要审批：</label>
				<form:radiobuttons path="isapprove" items="${fns:getDictList('group_isapprove')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>群性质：</label>
				<form:select path="property" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('group_property')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>创建时间：</label>
				<input name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${ccmGroupControl.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns">
				<a onclick="parent.parent.LayerDialog('${ctx}/group/ccmGroupControl/form', '添加', '700px', '550px')"
					class="btn btn-success"><i class="icon-plus"></i> 添加</a>
			</li>
			<li class="btns">
				<a href="javascript:;" id="btnSubmit" class="btn btn-primary"> 
					<i class="icon-search"></i> 查询
				</a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>社区</th>
				<th>名称</th>
				<th>成员数</th>
				<th>需要审批</th>
				<th>群性质</th>
				<th>创建时间</th>
				<shiro:hasPermission name="group:ccmGroupControl:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmGroupControl">
			<tr>
				<td><a onclick="parent.parent.LayerDialog('${ctx}/group/ccmGroupControl/form?id=${ccmGroupControl.id}', '修改', '700px', '550px')">
					${ccmGroupControl.areaName}
				</a></td>
				<td>
					${ccmGroupControl.name}
				</td>
				<td>
					${ccmGroupControl.number}
				</td>
				<td>
					${fns:getDictLabel(ccmGroupControl.isapprove, 'group_isapprove', '')}
				</td>
				<td>
					${fns:getDictLabel(ccmGroupControl.property, 'group_property', '')}
				</td>
				<td>
					<fmt:formatDate value="${ccmGroupControl.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="group:ccmGroupControl:edit"><td>
					<a class="btnList" onclick="parent.parent.LayerDialog('${ctx}/group/ccmGroupControl/form?id=${ccmGroupControl.id}', '修改', '700px', '550px')" title="修改">
						<i class="icon-pencil"></i>
					</a>
					<a href="${ctx}/group/ccmGroupControl/delete?id=${ccmGroupControl.id}" onclick="return confirmx('确认要删除该自治群吗？', this.href)" title="删除" class="btnList">
						<i class="icon-remove-sign"></i>
					</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>