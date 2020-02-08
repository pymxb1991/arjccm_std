<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>群防群治队伍管理</title>
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
<%--<span class="nav-position">当前位置 ：</span><span class="nav-menu"><%=session.getAttribute("activeMenuName")%>></span><span class="nav-menu2">综治组织</span>--%>
<ul class="back-list">
	<ul class="nav nav-tabs">
		<li class="active" style="width: 112px"><a class="nav-head" href="${ctx}/org/ccmOrgGropprevent/">数据列表</a></li>
		<%-- <shiro:hasPermission name="org:ccmOrgGropprevent:edit"><li><a href="${ctx}/org/ccmOrgGropprevent/form">数据添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmOrgGropprevent" action="${ctx}/org/ccmOrgGropprevent/" method="post" class="breadcrumb form-search clearfix">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form pull-left">
			<li class="first-line"><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="first-line"><label>出生开始日期：</label>
				<input name="beginBirthday" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${ccmOrgGropprevent.beginBirthday}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/></li>
			<li class="first-line"><label>出生结束日期：</label>
				<input name="endBirthday" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${ccmOrgGropprevent.endBirthday}" pattern="yyyy-MM-dd"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="first-line"><label>组织名称：</label>
				<form:select path="orgpreventId.id" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${ccmOrgOrgpreventList}" itemLabel="comName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="first-line"><label>性别：</label>
				<form:select path="sex" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="second-line"><label>学历：</label>
				<form:select path="education" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('sys_ccm_degree')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>

<%--			<li class="clearfix"></li>--%>
		</ul>

	<sys:message content="${message}"/>
	<div class="clearfix pull-right btn-box">

			<a onclick="parent.parent.LayerDialog('${ctx}/org/ccmOrgGropprevent/form', '添加', '1330px', '600px')"
			   class="btn btn-export" style="width: 49px;display:inline-block;float: right;"><i></i><span style="font-size: 12px">添加</span> </a>

		<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->

			<a href="javascript:;" id="btnSubmit" class="btn btn-primary" style="width: 49px;display:inline-block;float: right;">
				<i></i><span style="font-size: 12px">查询</span> </a>

	</div>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed table-gradient">
		<thead>
			<tr>
				<th>人员照片</th>
				<th>姓名</th>
				<th>组织名称</th>
				<th>性别</th>
				<th>职务</th>
				<th>出生日期</th>
				<th>学历</th>
				<th>手机号码</th>
				<shiro:hasPermission name="org:ccmOrgGropprevent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmOrgGropprevent">
			<tr>
				<td>
					<img src="${ccmOrgGropprevent.images}" style="height:50px;"/>
				</td>
				<td><a onclick="parent.parent.LayerDialog('${ctx}/org/ccmOrgGropprevent/form?id=${ccmOrgGropprevent.id}', '修改', '1330px', '600px')">
					${ccmOrgGropprevent.name}
				</a></td>
				<td>
					${ccmOrgGropprevent.orgpreventId.comName}
				</td>
				<td>
					${fns:getDictLabel(ccmOrgGropprevent.sex, 'sex', '')}
				</td>
				<td>
					${ccmOrgGropprevent.service}
				</td>
				<td>
					<fmt:formatDate value="${ccmOrgGropprevent.birthday}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${fns:getDictLabel(ccmOrgGropprevent.education, 'sys_ccm_degree', '')}
				</td>
				<td>
					${ccmOrgGropprevent.telephone}
				</td>
				<shiro:hasPermission name="org:ccmOrgGropprevent:edit"><td>
    				<a class="btnList" onclick="parent.parent.LayerDialog('${ctx}/org/ccmOrgGropprevent/form?id=${ccmOrgGropprevent.id}', '修改', '1330px', '600px')" title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/org/ccmOrgGropprevent/delete?id=${ccmOrgGropprevent.id}" onclick="return confirmx('确认要删除该人员信息吗？', this.href)"  title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" style="float: right; margin-top: 12px">${page}</div>
</body>
</html>