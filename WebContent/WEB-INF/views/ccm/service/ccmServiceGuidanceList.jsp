<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户指南管理</title>
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
<%--<img  src="${ctxStatic}/images/shouyedaohang.png"; class="nav-home">--%>
<%--<span class="nav-position">当前位置 ：</span><span class="nav-menu"><%=session.getAttribute("activeMenuName")%>></span><span class="nav-menu2">社区事务</span>--%>
<ul class="back-list">
	<ul class="nav nav-tabs">
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/service/ccmServiceGuidance/">数据列表</a></li>
		<shiro:hasPermission name="service:ccmServiceGuidance:edit"><li><a style="width: 140px;text-align:center" href="${ctx}/service/ccmServiceGuidance/form">数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmServiceGuidance" action="${ctx}/service/ccmServiceGuidance/" method="post" class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form pull-left">
			<li class="first-line"><label>事项分类：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('ccm_service_guidance_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="first-line"><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->
<%--			<li class="clearfix"></li>--%>
		</ul>
	<div class="clearfix pull-right btn-box">
		<a href="javascript:;" id="btnSubmit" style="width: 49px;/*margin-top: 25px;*/display:inline-block;float: right;" class="btn btn-primary">
				<%--<i class="icon-search"></i> --%><span style="font-size: 12px">查询</span> </a>
	</div>
	</form:form>
	<sys:message content="${message}"/>

	<table id="contentTable" class="table table-striped table-bordered table-condensed table-gradient">
		<thead>
			<tr>
				<th>事项分类</th>
				<th>标题</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="service:ccmServiceGuidance:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmServiceGuidance">
			<tr>
				<td style="height: 50px">
					${fns:getDictLabel(ccmServiceGuidance.type, 'ccm_service_guidance_type', '')}
				</td>
				<td style="height: 50px"><a href="${ctx}/service/ccmServiceGuidance/form?id=${ccmServiceGuidance.id}">
					${ccmServiceGuidance.title}
				</a></td>
				<td style="height: 50px">
					<fmt:formatDate value="${ccmServiceGuidance.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td class="tp" style="height: 50px">
					${ccmServiceGuidance.remarks}
				</td>
				<shiro:hasPermission name="service:ccmServiceGuidance:edit"><td style="height: 50px">
    				<a class="btnList" href="${ctx}/service/ccmServiceGuidance/form?id=${ccmServiceGuidance.id}" title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/service/ccmServiceGuidance/delete?id=${ccmServiceGuidance.id}" onclick="return confirmx('确认要删除该用户指南信息吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
</body>
</html>