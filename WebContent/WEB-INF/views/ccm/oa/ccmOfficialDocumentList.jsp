<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公文管理</title>
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
		<li class="active"><a href="${ctx}/oa/ccmOfficialDocument/">数据列表</a></li>
		<shiro:hasPermission name="oa:ccmOfficialDocument:edit"><li><a href="${ctx}/oa/ccmOfficialDocument/form">数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmOfficialDocument" action="${ctx}/oa/ccmOfficialDocument/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>分类：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('ccm_official_document_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>公文主题：</label>
				<form:input path="subject" htmlEscape="false" maxlength="256" class="input-medium"/>
			</li>
			<li><label>内容：</label>
				<form:input path="content" htmlEscape="false" maxlength="1000" class="input-medium"/>
			</li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->
			<li class="btns">
			<a href="javascript:;" id="btnSubmit" class="btn btn-primary">
                <i class="icon-search"></i> 查询 </a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>分类</th>
				<th>公文主题</th>
				<th>起草人姓名</th>
				<th>更新时间</th>
				<shiro:hasPermission name="oa:ccmOfficialDocument:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmOfficialDocument">
			<tr>
				<td><a href="${ctx}/oa/ccmOfficialDocument/form?id=${ccmOfficialDocument.id}">
					${fns:getDictLabel(ccmOfficialDocument.type, 'ccm_official_document_type', '')}
				</a></td>
				<td>
					${ccmOfficialDocument.subject}
				</td>
				<td>
					${ccmOfficialDocument.drafter}
				</td>
				<td>
					<fmt:formatDate value="${ccmOfficialDocument.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="oa:ccmOfficialDocument:edit"><td>
    				<a class="btnList" href="${ctx}/oa/ccmOfficialDocument/form?id=${ccmOfficialDocument.id}" title="查看"><i class="icon-file"></i></a>
					<a class="btnList" href="${ctx}/oa/ccmOfficialDocument/delete?id=${ccmOfficialDocument.id}" onclick="return confirmx('确认要删除该公文吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>