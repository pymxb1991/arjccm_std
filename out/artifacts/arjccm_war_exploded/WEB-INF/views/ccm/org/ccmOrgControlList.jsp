<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>自治组织管理</title>
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
		<li class="active"><a href="${ctx}/org/ccmOrgControl/">自治组织列表</a></li>
		<shiro:hasPermission name="org:ccmOrgControl:edit"><li><a href="${ctx}/org/ccmOrgControl/form">自治组织添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmOrgControl" action="${ctx}/org/ccmOrgControl/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>所属社区</th>
				<th>名称</th>
				<th>负责人</th>
				<th>联系电话</th>
				<th>通讯地址</th>
				<th>成立日期</th>
				<shiro:hasPermission name="org:ccmOrgControl:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmOrgControl">
			<tr>
				<td><a href="${ctx}/org/ccmOrgControl/form?id=${ccmOrgControl.id}">
					${ccmOrgControl.area.name}
				</a></td>
				<td>
					${ccmOrgControl.name}
				</td>
				<td>
					${ccmOrgControl.principal}
				</td>
				<td>
					${ccmOrgControl.phone}
				</td>
				<td>
					${ccmOrgControl.teladdress}
				</td>
				<td>
					<fmt:formatDate value="${ccmOrgControl.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="org:ccmOrgControl:edit"><td>
				<a class="btnList"
							onclick="parent.LayerDialog('${ctx}/org/ccmOrgControl/form?id=${ccmOrgControl.id}', '编辑', '1100px', '700px')"
							title="修改"><i class="icon-pencil"></i></a> <a class="btnList"
							href="${ctx}/org/ccmOrgControl/delete?id=${ccmOrgControl.id}"
							onclick="return confirmx('确认要删除该车辆布控记录吗？', this.href)" title="删除"><i
								class="icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>