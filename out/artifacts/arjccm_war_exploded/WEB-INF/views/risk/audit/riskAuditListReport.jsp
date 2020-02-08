<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>重大事项上报管理</title>
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
		<li class="active"><a href="${ctx}/audit/riskAudit/listReport">重大事项上报列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="riskReport" action="${ctx}/audit/riskAudit/listReport" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>所属重大事项：</label>
				<sys:treeselect id="riskEventGreat" name="riskEventGreat.id" value="${riskReport.riskEventGreat.id}" labelName="riskEventGreat.name" labelValue="${riskReport.riskEventGreat.name}"
					title="重大事项" url="/report/riskIncident/treeData" cssClass="" allowClear="true" notAllowSelectParent="true" cssStyle="width: 150px"/>
			</li>
			<li><label>是否入库：</label>
				<form:select path="isReserve" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>生成时间：</label>
				<input name="beginUpdateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${riskReport.beginUpdateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endUpdateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${riskReport.endUpdateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
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
				<th>报告名称</th>
				<th>所属重大事项</th>
				<th>是否入库</th>
				<th>生成时间</th>
				<th>查阅状态</th>
				<th>未通过状态</th>
				<th>已通过状态</th>
				<shiro:hasPermission name="report:riskReport:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="riskReport">
			<tr>
				<td><a href="${ctx}/audit/riskAudit/formReport?id=${riskReport.id}&readNum=${riskReport.readNum}">
					${riskReport.fileName}
				</a></td>
				<td>
					${riskReport.riskEventGreat.name}
				</td>
				<td>
					${fns:getDictLabel(riskReport.isReserve, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${riskReport.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${not empty riskReport.readNum}">
						${riskReport.readNum} / ${riskReport.readNum + riskReport.unReadNum}
					</c:if>
				</td>
				<td>
					<c:if test="${not empty riskReport.noauditNum}">
						${riskReport.noauditNum} / ${riskReport.unauditNum + riskReport.noauditNum + riskReport.auditNum}
					</c:if>
				</td>
				<td>
					<c:if test="${not empty riskReport.auditNum}">
						${riskReport.auditNum} / ${riskReport.unauditNum + riskReport.noauditNum + riskReport.auditNum}
					</c:if>
				</td>
				<shiro:hasPermission name="audit:riskAudit:edit"><td>
    				<a class="btnList" href="${ctx}/audit/riskAudit/formReport?id=${riskReport.id}&readNum=${riskReport.readNum}" title="上报"><i class="icon-file"></i></a>
					<a class="btnList" href="${ctx}/audit/riskAudit/deleteReport?id=${riskReport.id}" onclick="return confirmx('确认要删除该重大事项的上报吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>