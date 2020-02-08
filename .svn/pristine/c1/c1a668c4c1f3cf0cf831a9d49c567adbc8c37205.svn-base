<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>调解组织管理</title>
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
		<li class="active"><a href="${ctx}/organization/ccmEventOrganization/">调解组织列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmEventOrganization" action="${ctx}/organization/ccmEventOrganization/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		 	<%-- <li><label>调解组织名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li> --%>
			<%-- <li><label>所属社区：</label>
				<sys:treeselect id="area" name="area.id" value="${ccmEventOrganization.area.id}" labelName="area.name" labelValue="${ccmEventOrganization.area.name}"
					title="区域" url="/tree/ccmTree/treeDataArea?type=6" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>   --%>
			<li class="btns">
				<a onclick="parent.parent.LayerDialog('${ctx}/organization/ccmEventOrganization/form', '添加', '700px', '700px')"
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
			    <th>组织名称</th>
			    <th>所属区域</th>
				<th>电话</th>
				<th>地址</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="organization:ccmEventOrganization:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmEventOrganization">
			<tr>
				<td>${ccmEventOrganization.orgName}</td>
				<td>${ccmEventOrganization.area.name}</td>
				<td>${ccmEventOrganization.orgPhone}</td>
				<td>${ccmEventOrganization.orgAdd}</td>
				<td><a onclick="parent.parent.LayerDialog('${ctx}/organization/ccmEventOrganization/form?id=${ccmEventOrganization.id}', '修改', '700px', '700px')">
					<fmt:formatDate value="${ccmEventOrganization.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td class="tp">
					${ccmEventOrganization.remarks}
				</td>
				<shiro:hasPermission name="organization:ccmEventOrganization:edit"><td>
					<a class="btnList" onclick="parent.parent.LayerDialog('${ctx}/organization/ccmEventOrganization/form?id=${ccmEventOrganization.id}', '修改', '700px', '700px')" title="修改">
						<i class="icon-pencil"></i>
					</a>
					<a class="btnList" href="${ctx}/organization/ccmEventOrganization/delete?id=${ccmEventOrganization.id}" onclick="return confirmx('确认要删除该调解组织吗？', this.href)" title="删除">
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