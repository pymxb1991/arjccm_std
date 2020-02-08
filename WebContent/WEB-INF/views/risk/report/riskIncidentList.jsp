<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>风险事件管理</title>
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
		<li class="active"><a href="${ctx}/report/riskIncident/">风险事件列表</a></li>
		<shiro:hasPermission name="report:riskIncident:edit"><li><a href="${ctx}/report/riskIncident/form">风险事件添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="riskIncident" action="${ctx}/report/riskIncident/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>风险名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>风险类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('risk_risk_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>重要程度：</label>
				<form:select path="importance" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('ccm_conc_exte')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>紧急程度：</label>
				<form:select path="urgency" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('ccm_conc_exte')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->
				<li class="btns">
			<a href="javascript:;" id="btnSubmit" class="btn btn-primary">
                <i class="icon-search"></i> 查询 </a>
			</li>
			<li class="clearfix"></li>
			<li><label>处理状态：</label>
				<form:select path="disposeType" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('ccm_event_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>提出时间：</label>
				<input name="beginPutTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${riskIncident.beginPutTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endPutTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${riskIncident.endPutTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>风险名称</th>
				<th>来源</th>
				<th>风险类型</th>
				<th>重要程度</th>
				<th>紧急程度</th>
				<th>提出时间</th>
				<th>处理状态</th>
				<shiro:hasPermission name="report:riskIncident:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="riskIncident">
			<tr>
				<td><a href="${ctx}/report/riskIncident/form?id=${riskIncident.id}">
					${riskIncident.name}
				</a></td>
				<td>
					${riskIncident.source}
				</td>
				<td>
					${fns:getDictLabel(riskIncident.type, 'risk_risk_type', '')}
				</td>
				
				<c:if test="${riskIncident.importance eq '01'}">
					<td style='color:red'>${fns:getDictLabel(riskIncident.importance, 'ccm_conc_exte', '')}&nbsp; &nbsp; <img src="${ctxStatic}/images/atteType_red.png" /> </td>
				</c:if>
				<c:if test="${riskIncident.importance eq '02'}">
					<td style='color:orange'>${fns:getDictLabel(riskIncident.importance, 'ccm_conc_exte', '')}&nbsp; &nbsp; <img src="${ctxStatic}/images/atteType_orange.png" /> </td>
				</c:if>
				<c:if test="${riskIncident.importance eq '03'}">
					<td>${fns:getDictLabel(riskIncident.importance, 'ccm_conc_exte', '')}&nbsp; &nbsp; <img src="${ctxStatic}/images/atteType_green.png" /> </td>
				</c:if>
				<c:if test="${riskIncident.importance eq '' or empty riskIncident.importance}">
					<td>${fns:getDictLabel(riskIncident.importance, 'ccm_conc_exte', '')} </td>
				</c:if>
				
				<c:if test="${riskIncident.urgency eq '01'}">
					<td style='color:red'>${fns:getDictLabel(riskIncident.urgency, 'ccm_conc_exte', '')}&nbsp; &nbsp; <img src="${ctxStatic}/images/atteType_red.png" /> </td>
				</c:if>
				<c:if test="${riskIncident.urgency eq '02'}">
					<td style='color:orange'>${fns:getDictLabel(riskIncident.urgency, 'ccm_conc_exte', '')}&nbsp; &nbsp; <img src="${ctxStatic}/images/atteType_orange.png" /> </td>
				</c:if>
				<c:if test="${riskIncident.urgency eq '03'}">
					<td>${fns:getDictLabel(riskIncident.urgency, 'ccm_conc_exte', '')}&nbsp; &nbsp; <img src="${ctxStatic}/images/atteType_green.png" /> </td>
				</c:if>
				<c:if test="${riskIncident.urgency eq '' or empty riskIncident.urgency}">
					<td>${fns:getDictLabel(riskIncident.urgency, 'ccm_conc_exte', '')} </td>
				</c:if>
				
				<td>
					<fmt:formatDate value="${riskIncident.putTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${fns:getDictLabel(riskIncident.disposeType, 'ccm_event_status', '')}
				</td>
				<td><shiro:hasPermission name="report:riskIncident:edit">
    				<a class="btnList" href="${ctx}/report/riskIncident/form?id=${riskIncident.id}" title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/report/riskIncident/delete?id=${riskIncident.id}" onclick="return confirmx('确认要删除该风险事件管理吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</shiro:hasPermission>
				<shiro:hasPermission name="log:ccmLogTail:edit">
					<a class="btnList" onclick="parent.LayerDialog('${ctx}/log/ccmLogTail/list?relevance_id=${riskIncident.id}&relevance_table=risk_incident', '记录信息', '800px', '660px')" 
								  title="记录信息"><i class="icon-print" style="color: cornflowerblue;"></i></a>
					<a class="btnList" onclick="parent.LayerDialog('${ctx}/log/ccmLogTail/formProPermanent?relevance_id=${riskIncident.id}&relevance_table=risk_incident', '添加记录', '800px', '660px')"
								  title="添加记录"><i class="icon-plus"></i></a>
				</shiro:hasPermission></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>