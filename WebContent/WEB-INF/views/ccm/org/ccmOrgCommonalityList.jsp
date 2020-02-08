<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公共机构管理管理</title>
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
		<li class="active" style="width: 140px"><a class="nav-head" href="${ctx}/org/ccmOrgCommonality/">数据列表</a></li>
		<shiro:hasPermission name="org:ccmOrgCommonality:edit"><li><a style="width: 140px;text-align:center" href="${ctx}/org/ccmOrgCommonality/form">数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmOrgCommonality" action="${ctx}/org/ccmOrgCommonality/" method="post" class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form pull-left">
			<li class="first-line"><label>机构名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="first-line"><label>机构编号：</label>
				<form:input path="orgCode" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="first-line"><label>机构类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('ccm_org_commonality_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="first-line"><label>所属区域：</label>
				<sys:treeselect id="area" name="area.id" value="${ccmOrgCommonality.area.id}" labelName="area.name" labelValue="${ccmOrgCommonality.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="false"/>
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
				<th>机构名称</th>
				<th>机构编号</th>
				<th>机构类型</th>
				<th>所属区域</th>
				<th>负责人姓名</th>
				<th>负责人电话</th>
				<th>机构联系电话</th>
				<shiro:hasPermission name="org:ccmOrgCommonality:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmOrgCommonality">
			<tr>
				<td style="height: 50px"><a href="${ctx}/org/ccmOrgCommonality/form?id=${ccmOrgCommonality.id}">
					${ccmOrgCommonality.name}
				</a></td>
				<td style="height: 50px">
					${ccmOrgCommonality.orgCode}
				</td>
				<td style="height: 50px">
					${fns:getDictLabel(ccmOrgCommonality.type, 'ccm_org_commonality_type', '')}
				</td>
				<td style="height: 50px">
					${ccmOrgCommonality.area.name}
				</td>
				<td style="height: 50px">
					${ccmOrgCommonality.principalName}
				</td>
				<td style="height: 50px">
					${ccmOrgCommonality.principalTel}
				</td>
				<td style="height: 50px">
					${ccmOrgCommonality.orgTel}
				</td>
				<shiro:hasPermission name="org:ccmOrgCommonality:edit"><td style="height: 50px">
    				<a class="btnList" href="${ctx}/org/ccmOrgCommonality/form?id=${ccmOrgCommonality.id}"  title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/org/ccmOrgCommonality/delete?id=${ccmOrgCommonality.id}" onclick="return confirmx('确认要删除该公共机构管理吗？', this.href)"  title="删除"><i class="icon-remove-sign"></i></a>
				<%--<c:if test="${ccmOrgCommonality.type == 10 ||  ccmOrgCommonality.type == 11}" >--%>
					<a class="btnList" onclick="parent.LayerDialog('${ctx}/org/ccmOrgDevice/form?id=${ccmOrgCommonality.id}', '视频绑定', '1120px', '350px')"
					   title="视频绑定"				class="btn btn-success"><i class="icon-align-left"></i></a>
					<a class="btnList" onclick="parent.LayerDialog('${ctx}/org/ccmOrgDevice/list?id=${ccmOrgCommonality.id}', '视频列表', '1120px', '350px')"
					   title="视频列表"				class="btn btn-success"><i class="icon-align-justify"></i></a>
				<%--</c:if>--%>
				</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
</body>
</html>