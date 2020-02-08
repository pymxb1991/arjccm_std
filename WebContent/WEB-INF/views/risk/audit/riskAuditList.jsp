<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>重大事项审核管理</title>
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
		<li class="active"><a href="${ctx}/audit/riskAudit/">重大事项审核列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="riskAudit" action="${ctx}/audit/riskAudit/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>所属报告：</label>
				<sys:treeselect id="riskReport" name="riskReport.id" value="${riskAudit.riskReport.id}" labelName="riskReport.fileName" labelValue="${riskAudit.riskReport.fileName}"
					title="所属报告" url="/report/riskReport/treeData" cssClass="" allowClear="true" notAllowSelectParent="true" cssStyle="width: 150px"/>
			</li>
			<li><label>阅读状态：</label>
				<form:select path="readFlag" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('oa_notify_read')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>审核结果：</label>
				<form:select path="result" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('ccm_service_online_handle')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>报告名称</th>
				<th>所属重大事项</th>
				<th>审核人</th>
				<th>阅读状态</th>
				<th>审核时间</th>
				<th>审核结果</th>
				<shiro:hasPermission name="audit:riskAudit:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="riskAudit">
			<tr>
				<td><a href="${ctx}/audit/riskAudit/form?id=${riskAudit.id}">
					${riskAudit.riskReport.fileName}
				</a></td>
				<td>
					${riskAudit.riskReport.riskEventGreat.name}
				</td>
				<td>
					${riskAudit.user.name}
				</td>
				<c:if test="${riskAudit.readFlag eq '0'}">
					<td>${fns:getDictLabel(riskAudit.readFlag, 'oa_notify_read', '')}
				</c:if>
				<c:if test="${riskAudit.readFlag eq '1'}">
					<td style="color:green;">${fns:getDictLabel(riskAudit.readFlag, 'oa_notify_read', '')}
				</c:if>
				</td>
				<td>
					<fmt:formatDate value="${riskAudit.readTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<c:if test="${riskAudit.result eq '01'}">
					<td>${fns:getDictLabel(riskAudit.result, 'ccm_service_online_handle', '')}
				</c:if>		
				<c:if test="${riskAudit.result eq '02'}">
					<td style="color:red;">${fns:getDictLabel(riskAudit.result, 'ccm_service_online_handle', '')}
				</c:if>	
				<c:if test="${riskAudit.result eq '03'}">
					<td style="color:green;">${fns:getDictLabel(riskAudit.result, 'ccm_service_online_handle', '')}
				</c:if>	
				</td>
				<shiro:hasPermission name="audit:riskAudit:edit"><td>
    				<a class="btnList" href="${ctx}/audit/riskAudit/form?id=${riskAudit.id}" title="审核"><i class="icon-pencil"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>