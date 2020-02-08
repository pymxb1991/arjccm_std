<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>街路巷管理</title>
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
<%--<span class="nav-position">当前位置 ：</span><span class="nav-menu"><%=session.getAttribute("activeMenuName")%>></span><span class="nav-menu2">地址库管理</span>--%>
<div class="back-list">
	<div class="context" content="${ctx}"></div>
	<ul class="nav nav-tabs">
		<li class="active" style="width: 112px"><a class="nav-head" href="${ctx}/pop/ccmRoadAddress/">数据列表</a></li>
		<shiro:hasPermission name="pop:ccmRoadAddress:edit"><li style="width: 112px"><a style="text-align: center" href="${ctx}/pop/ccmRoadAddress/form">数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmRoadAddress" action="${ctx}/pop/ccmRoadAddress/" method="post" class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form pull-left">
			<li class="first-line"><label class="title-text">街路巷名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="first-line"><label class="title-text">地址：</label>
				<form:input path="address" htmlEscape="false" maxlength="512" class="input-medium"/>
			</li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->

<%--			<li class="clearfix"></li>--%>
		</ul>
	<div class="clearfix pull-right btn-box">
		<a href="javascript:;" id="btnSubmit" class="btn btn-primary" style="width: 49px;display:inline-block;float: right;">
			<i></i><span style="font-size: 12px">查询</span>  </a>
	</div>
	</form:form>
	<sys:message content="${message}"/>
	<table  style="table-layout:fixed;width:1659px" id="contentTable" class="table table-striped table-bordered table-condensed table-gradient">
		<thead>
			<tr>
				<th>街路巷名称</th>
				<th>地址</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="pop:ccmRoadAddress:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmRoadAddress">
			<tr>
				<td style="height: 50px"><a href="${ctx}/pop/ccmRoadAddress/form?id=${ccmRoadAddress.id}">
					${ccmRoadAddress.name}
				</a></td>
				<td style="height: 50px">
					${ccmRoadAddress.address}
				</td>
				<td style="height: 50px">
					<fmt:formatDate value="${ccmRoadAddress.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td class=""style="height: 50px">
					${ccmRoadAddress.remarks}
				</td>
				<shiro:hasPermission name="pop:ccmRoadAddress:edit"><td style="height: 50px">
    				<a class="btnList" href="${ctx}/pop/ccmRoadAddress/form?id=${ccmRoadAddress.id}"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/pop/ccmRoadAddress/delete?id=${ccmRoadAddress.id}" onclick="return confirmx('确认要删除该街路巷吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
	</div>
</body>
</html>