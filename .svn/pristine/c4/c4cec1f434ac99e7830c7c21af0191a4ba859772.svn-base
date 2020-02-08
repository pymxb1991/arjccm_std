<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>城市部件管理</title>
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
<%--<span class="nav-position">当前位置 ：</span><span class="nav-menu"><%=session.getAttribute("activeMenuName")%>></span><span class="nav-menu2">社会治安</span>--%>
<div class="back-list">
	<ul class="nav nav-tabs">
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/citycomponents/ccmCityComponents/">数据列表</a></li>
		<shiro:hasPermission name="citycomponents:ccmCityComponents:edit"><li><a style="width: 140px;text-align:center" href="${ctx}/citycomponents/ccmCityComponents/form">数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmCityComponents" action="${ctx}/citycomponents/ccmCityComponents/" method="post" class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form pull-left">
			<li class="first-line"><label>部件类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('ccm_city_components_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="first-line"><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="first-line"><label>编号：</label>
				<form:input path="code" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="first-line"><label>设备所在区域：</label>
				<sys:treeselect id="area" name="area.id" value="${ccmCityComponents.area.id}" labelName="area.name" labelValue="${ccmCityComponents.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="first-line"><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('ccm_city_components_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
	<table id="contentTable" class="table table-striped table-bordered table-condensed table-gradient">
		<thead>
			<tr>
				<th>部件类型</th>
				<th>名称</th>
				<th>编号</th>
				<th>设备所在区域</th>
				<th>状态</th>
				<shiro:hasPermission name="citycomponents:ccmCityComponents:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmCityComponents">
			<tr>
				<td style="height: 50px"><a href="${ctx}/citycomponents/ccmCityComponents/form?id=${ccmCityComponents.id}">
					${fns:getDictLabel(ccmCityComponents.type, 'ccm_city_components_type', '')}
				</a></td>
				<td style="height: 50px">
					${ccmCityComponents.name}
				</td>
				<td style="height: 50px">
					${ccmCityComponents.code}
				</td>
				<td style="height: 50px">
					${ccmCityComponents.area.name}
				</td>
				<td style="height: 50px">
					${fns:getDictLabel(ccmCityComponents.status, 'ccm_city_components_status', '')}
				</td>
				<shiro:hasPermission name="citycomponents:ccmCityComponents:edit"><td style="height: 50px">
    				<a class="btnList" href="${ctx}/citycomponents/ccmCityComponents/form?id=${ccmCityComponents.id}" title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/citycomponents/ccmCityComponents/delete?id=${ccmCityComponents.id}" onclick="return confirmx('确认要删除该城市部件吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
</body>
</html>