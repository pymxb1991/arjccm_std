<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>学员登记信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/person/pbsPartymemreg/">学员登记信息列表</a></li>
		<%-- <shiro:hasPermission name="person:pbsPartymemreg:edit">
			<li><a href="${ctx}/person/pbsPartymemreg/form">学员登记信息添加</a></li>
		</shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="pbsPartymemreg"
		action="${ctx}/person/pbsPartymemreg/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>身份号码：</label> <form:input path="sIdnum"
					htmlEscape="false" maxlength="30" class="input-medium" /></li>
			<li><label>证件类别：</label> <form:select path="sIdtype"
					class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('sys_idtype')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li><label>姓名：</label> <form:input path="sName"
					htmlEscape="false" maxlength="200" class="input-medium" /></li>
			<li class="clearfix"></li>
			<li><label>登记的用户：</label> <form:input path="username"
					htmlEscape="false" maxlength="64" class="input-medium" /></li>
			<li><label>审核状态：</label> <form:select path="sRegstat"
					class="input-medium">
					<form:option value="" label="全部" />
					<form:options items="${fns:getDictList('memreg_type')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select></li>
			<li><label>出生日期：</label> <input name="beginDtBirth" type="text"
				readonly="readonly" maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${pbsPartymemreg.beginDtBirth}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});" />
				- <input name="endDtBirth" type="text" readonly="readonly"
				maxlength="20" class="input-medium Wdate"
				value="<fmt:formatDate value="${pbsPartymemreg.endDtBirth}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});" />
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>登记的用户头像</th>
				<th>登记的用户</th>
				<th>姓名</th>
				<th>学员证件类别</th>
				<th>学员身份号码</th>
				<th>审核状态</th>
				<th>出生日期</th>
				<th>更新时间</th>
				<shiro:hasPermission name="person:pbsPartymemreg:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="pbsPartymemreg">
				<tr>
					<td style="width:60px;text-align:center">
						<img onerror="this.src='${ctxStatic}/images/headPC.png'" src="${pbsPartymemreg.userPhoto}" style="width:40px;height:40px"/>
					</td>
					<td><a
						href="${ctx}/person/pbsPartymemreg/form?id=${pbsPartymemreg.id}">${pbsPartymemreg.username}</a></td>
					<td>${pbsPartymemreg.SName}</td>
					<td>${fns:getDictLabel(pbsPartymemreg.SIdtype, 'sys_idtype', '')}
					</td>
					<td>${pbsPartymemreg.SIdnum}</td>

					<td>${fns:getDictLabel(pbsPartymemreg.SRegstat, 'memreg_type', '')}
					</td>
					<td><fmt:formatDate value="${pbsPartymemreg.dtBirth}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${pbsPartymemreg.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<shiro:hasPermission name="person:pbsPartymemreg:edit">
						<td><a
							href="${ctx}/person/pbsPartymemreg/form?id=${pbsPartymemreg.id}"  title = "修改"><i class="icon icon-pencil"></i></a>
							<a
							href="${ctx}/person/pbsPartymemreg/delete?id=${pbsPartymemreg.id}"
							onclick="return confirmx('确认要删除该学员登记信息吗？', this.href)"  title = "删除"><i class="icon icon-trash"></i></a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>