<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>帮助信息管理</title>
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
		<li class="active"><a href="${ctx}/manage/riskHelp/">帮助信息列表</a></li>
		<shiro:hasPermission name="manage:riskHelp:edit"><li><a href="${ctx}/manage/riskHelp/form">帮助信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="riskHelp" action="${ctx}/manage/riskHelp/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>信息类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('risk_help_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>关键词：</label>
				<form:input path="antistop" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>重要程度：</label>
				<form:select path="importance" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('ccm_conc_exte')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->
				<li class="btns">
			<a href="javascript:;" id="btnSubmit" class="btn btn-primary">
                <i class="icon-search"></i> 查询 </a>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>信息类型</th>
				<th>关键词</th>
				<th>重要程度</th>
				<th>备注信息</th>
				<shiro:hasPermission name="manage:riskHelp:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="riskHelp">
			<tr>
				<td><a href="${ctx}/manage/riskHelp/form?id=${riskHelp.id}">
					${riskHelp.name}
				</a></td>
				<td>
					${fns:getDictLabel(riskHelp.type, 'risk_help_type', '')}
				</td>
				<td>
					${riskHelp.antistop}
				</td>
				<c:if test="${riskHelp.importance eq '01'}">
					<td style='color:red'>${fns:getDictLabel(riskHelp.importance, 'ccm_conc_exte', '')}&nbsp; &nbsp; <img src="${ctxStatic}/images/atteType_red.png" /> </td>
				</c:if>
				<c:if test="${riskHelp.importance eq '02'}">
					<td style='color:orange'>${fns:getDictLabel(riskHelp.importance, 'ccm_conc_exte', '')}&nbsp; &nbsp; <img src="${ctxStatic}/images/atteType_orange.png" /> </td>
				</c:if>
				<c:if test="${riskHelp.importance eq '03'}">
					<td>${fns:getDictLabel(riskHelp.importance, 'ccm_conc_exte', '')}&nbsp; &nbsp; <img src="${ctxStatic}/images/atteType_green.png" /> </td>
				</c:if>
				<c:if test="${riskHelp.importance eq '' or empty riskHelp.importance}">
					<td>${fns:getDictLabel(riskHelp.importance, 'ccm_conc_exte', '')} </td>
				</c:if>   
				<td class="tp">
					${riskHelp.remarks}
				</td>
				<shiro:hasPermission name="manage:riskHelp:edit"><td>
    				<a class="btnList" href="${ctx}/manage/riskHelp/form?id=${riskHelp.id}" title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/manage/riskHelp/delete?id=${riskHelp.id}" onclick="return confirmx('确认要删除该帮助信息吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>