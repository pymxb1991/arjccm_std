<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>重大事项报备管理</title>
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
		<li class="active"><a href="${ctx}/report/riskEventGreat/">重大事项报备列表</a></li>
		<shiro:hasPermission name="report:riskEventGreat:edit"><li><a href="${ctx}/report/riskEventGreat/form">重大事项报备添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="riskEventGreat" action="${ctx}/report/riskEventGreat/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>事项名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>报告单位：</label>
				<form:input path="department" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>责任人：</label>
				<form:input path="respName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>报告时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${riskEventGreat.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${riskEventGreat.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>事项名称</th>
				<th>报告单位</th>
				<th>责任人</th>
				<th>报告时间</th>
				<th>是否入库</th>
				<shiro:hasPermission name="report:riskEventGreat:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="riskEventGreat">
			<tr>
				<td><a href="${ctx}/report/riskEventGreat/form?id=${riskEventGreat.id}">
					${riskEventGreat.name}
				</a></td>
				<td>
					${riskEventGreat.department}
				</td>
				<td>
					${riskEventGreat.respName}
				</td>
				<td>
					<fmt:formatDate value="${riskEventGreat.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(riskEventGreat.isReserve, 'yes_no', '')}
				</td>
				<shiro:hasPermission name="report:riskEventGreat:edit"><td>
    				<a class="btnList" href="${ctx}/report/riskEventGreat/form?id=${riskEventGreat.id}" title="修改"><i class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/report/riskEventGreat/delete?id=${riskEventGreat.id}" onclick="return confirmx('确认要删除该重大事项报备吗？', this.href)" title="删除"><i class="icon-remove-sign"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>