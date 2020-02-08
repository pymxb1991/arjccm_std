<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>违章记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#btnSubmit').click(function(){
				$('#searchForm').submit();
			});
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
		<li class="active"><a href="${ctx}/car/plmCarViolation/addFromUseList?carUseId=${plmCarViolation.carUseId}">违章记录列表</a></li>
		<c:if test="${not empty carUseId}">
		<shiro:hasPermission name="car:plmCarViolation:edit"><li><a href="${ctx}/car/plmCarViolation/addform?carUseId=${plmCarViolation.carUseId}">违章记录添加</a></li></shiro:hasPermission>
		</c:if>
	</ul>
	<form:form id="searchForm" modelAttribute="plmCarViolation" action="${ctx}/car/plmCarViolation/addFromUseList?carUseId=${plmCarViolation.carUseId}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>处理状态：</label>
				<form:select path="statue" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('plm_car_violation_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>

			<li><label>违章时间：</label>
				<input name="beginViolDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmCarViolation.beginViolDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endViolDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmCarViolation.endViolDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>违章类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('plm_car_violation_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>						
			<li class="btns"><a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-search"></i> 查询</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>违章车辆</th>
				<th>违章司机</th>
				<th>领用人</th>
				<th>处理状态</th>
				<th>违章时间</th>
				<th>违章类型</th>
				<th>罚款金额</th>
				<th>扣分</th>
				<c:if test="${not empty carUseId}">
				<shiro:hasPermission name="car:plmCarViolation:edit"><th>操作</th></shiro:hasPermission> 
				</c:if>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="plmCarViolation">
			<tr>
				<td><a href="${ctx}/car/plmCarViolation/addform?id=${plmCarViolation.id}">
					${plmCarViolation.car.vehicle}
				</a></td>
				<td>
					${plmCarViolation.driver.name}
				</td>
				<td>
					${plmCarViolation.use.name}
				</td>				
				<td>
					${fns:getDictLabel(plmCarViolation.statue, 'plm_car_violation_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${plmCarViolation.violDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(plmCarViolation.type, 'plm_car_violation_type', '')}
				</td>
				<td>
					${plmCarViolation.forfeit}
				</td>
				<td>
					${plmCarViolation.deduction}
				</td>
				<c:if test="${not empty carUseId}">
				<shiro:hasPermission name="car:plmCarViolation:edit"><td>
    				
					<a class="btnList" href="${ctx}/car/plmCarViolation/form?id=${plmCarViolation.id}&isCar=0"><i title="修改" class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/car/plmCarViolation/delete?id=${plmCarViolation.id}" onclick="return confirmx('确认要删除该违章记录吗？', this.href)"><i title="删除" class="icon-trash"></i></a>
				</td></shiro:hasPermission> 
				</c:if>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>