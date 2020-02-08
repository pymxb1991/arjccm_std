<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>后勤物资管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.input-select{width: 117px;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function(){
				$('#searchForm').submit();
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<%-- <script type="text/javascript" src="${ctxStatic}/plm/storage/plmEquipmentType.js"></script> --%>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/logistics/plmEquipmentLogi/">后勤物资列表</a></li>
		<shiro:hasPermission name="logistics:plmEquipmentLogi:edit"><li><a href="${ctx}/logistics/plmEquipmentLogi/form">后勤物资添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="plmEquipment" action="${ctx}/logistics/plmEquipmentLogi/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>所在仓库：</label>
				<sys:treeselect id="storage" name="storage.id"
										value="${plmStorage.storage.id}" labelName="storage.name"
										labelValue="${plmStorage.storage.name}" title="仓库"
										url="/storage/plmStorage/treeData"
										cssClass="input-select"
										allowClear="true"  notAllowSelectParent="true"/>
			</li> --%>
			<li><label>物资名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>物资编号：</label>
				<form:input path="code" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>规格型号：</label>
				<form:input path="spec" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<%-- <li><label>物资类别：</label>
				<form:select path="typeId" class="input-medium" dictTyep="plm_equipment_type">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictListExt('1','plm_equipment_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>物资子类：</label>
				<form:select path="typeChild" class="input-medium" >
					<form:option value="" label=""/> 
					<form:options items="${fns:getDictList('plm_equipment_type_child')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li> --%>
			<li><label>使用人：</label>
				<sys:treeselect id="user" name="user.id" value="${plmEquipment.user.id}" labelName="user.name" labelValue="${plmEquipment.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-select" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>使用人部门：</label>
				<sys:treeselect id="userJob" name="userJob.id" value="${plmEquipment.userJob.id}" labelName="userJob.name" labelValue="${plmEquipment.userJob.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-select" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>状态：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('plm_equipment_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><a id="btnSubmit" class="btn btn-primary" href="javascript:;" ><i class="icon-search"></i> 查询</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>所在仓库</th>
				<th>物资名称</th>
				<th>物资编号</th>
				<th>规格型号</th>
				<th>物资类别</th>
				<th>物资子类</th>
				<th>使用人</th>
				<th>使用人部门</th>
				<th>状态</th>
				<shiro:hasPermission name="logistics:plmEquipmentLogi:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="plmEquipment">
			<tr>
				<td><a href="${ctx}/logistics/plmEquipmentLogi/form?id=${plmEquipment.id}">
					${plmEquipment.storage.name}
				</a></td>
				<td>
					${plmEquipment.name}
				</td>
				<td>
					${plmEquipment.code}
				</td>
				<td>
					${plmEquipment.spec}
				</td>
				<td>
					${fns:getDictLabel(plmEquipment.typeId, 'plm_equipment_type', '')}
				</td>
				<td>
					${fns:getDictLabel(plmEquipment.typeChild, 'plm_equipment_type_child', '')}
				</td>
				<td>
					${plmEquipment.user.name}
				</td>
				<td>
					${plmEquipment.userJob.name}
				</td>
				<td>
					${fns:getDictLabel(plmEquipment.type, 'plm_equipment_status', '')}
				</td>
				<shiro:hasPermission name="logistics:plmEquipmentLogi:edit"><td>
    				<a href="${ctx}/logistics/plmEquipmentLogi/form?id=${plmEquipment.id}">修改</a>
					<a href="${ctx}/logistics/plmEquipmentLogi/delete?id=${plmEquipment.id}" onclick="return confirmx('确认要删除该装备物资吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>