<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>居民用户管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/layer-v3.1.1/layer/layer.js" type="text/javascript"></script>
	<script src="${ctxStatic}/ccm/event/js/ccmEventIncident.js" type="text/javascript"></script>
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
		<li class="active"><a href="${ctx}/cms/ccmFontUser/">居民用户管理列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmFontUser" action="${ctx}/cms/ccmFontUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">			
			<li><label>身份证号：</label>
				<form:input path="no" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>审核状态：</label> <form:select path="loginFlag" class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('font_user_loginFlag')}"
						itemLabel="label" itemValue="value" htmlEscape="false"  />
				</form:select></li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->
			<li class="btns">
			<a href="javascript:;" id="btnSubmit" class="btn btn-primary">
                <i class="icon-search"></i> 查询 </a>
			</li>
			<li class="btns">
			<a onclick="parent.parent.LayerDialog('${ctx}/cms/ccmFontUser/form', '添加', '700px', '750px')"
				class="btn btn-success">
				<i class="icon-plus"></i> 添加</a> 
			</li>
			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>登录名</th>
				<th>身份证号</th>
				<th>姓名</th>
				<th>审核状态</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="cms:ccmFontUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmFontUser">
			<tr>
				<td><a onclick="parent.parent.LayerDialog('${ctx}/cms/ccmFontUser/form?id=${ccmFontUser.id}', '修改', '700px', '750px')">
					${ccmFontUser.loginName}
				</a></td>
				<td>
					${ccmFontUser.no}
				</td>
				<td>
					${ccmFontUser.name}
				</td>
				<td>
					${fns:getDictLabel(ccmFontUser.loginFlag,'font_user_loginFlag','')}	
					
				</td>
				<td>
					<fmt:formatDate value="${ccmFontUser.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td class="tp">
					${ccmFontUser.remarks}
				</td>
				<shiro:hasPermission name="cms:ccmFontUser:edit"><td>
    				<a onclick="parent.parent.LayerDialog('${ctx}/cms/ccmFontUser/form?id=${ccmFontUser.id}', '修改', '700px', '750px')" title="修改"><i class="icon-pencil"></i></a>
					<%-- <a href="${ctx}/cms/ccmFontUser/delete?id=${ccmFontUser.id}" onclick="return confirmx('确认要删除该居民用户管理吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>