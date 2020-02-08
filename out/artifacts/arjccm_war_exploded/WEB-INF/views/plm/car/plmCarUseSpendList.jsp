<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用车费用记录管理</title>
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
	<script type="text/javascript" src="${ctxStatic}/plm/car/plmCarUseForm.js"></script> 	
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/car/plmCarUseSpend/">用车费用记录列表</a></li>
		<%-- <shiro:hasPermission name="car:plmCarUseSpend:edit"><li><a href="${ctx}/car/plmCarUseSpend/form">用车费用记录添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="plmCarUseSpend" action="${ctx}/car/plmCarUseSpend/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>车辆：</label>
				<form:input id="carIdsAll" path="car.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>领用人：</label>
				<sys:treeselect id="use" name="use.id" value="${plmCarUseSpend.use.id}" labelName="use.name" labelValue="${plmCarUseSpend.use.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>费用类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label="未选择"/>
					<form:options items="${fns:getDictList('plm_car_use_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>是否损坏：</label>
				<form:radiobuttons path="isDamaged" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-search"></i> 查询</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>车辆</th>
				<th>领用人</th>
				<th>费用类型</th>
				<th>是否损坏</th>
				<th>总费用(元)</th>
				<th>发生时间</th>
				<shiro:hasPermission name="car:plmCarUseSpend:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="plmCarUseSpend">
			<tr>
				<td><a href="${ctx}/car/plmCarUseSpend/form?id=${plmCarUseSpend.id}">
					${plmCarUseSpend.car.vehicle}
				</a></td>
				<td>
					${plmCarUseSpend.use.name}
				</td>
				<td>
					${fns:getDictLabel(plmCarUseSpend.type, 'plm_car_use_type', '')}
				</td>
				<td>
					${fns:getDictLabel(plmCarUseSpend.isDamaged, 'yes_no', '')}
				</td>
				<td>
					${plmCarUseSpend.totaFee}
				</td>
				<td>
					<fmt:formatDate value="${plmCarUseSpend.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="car:plmCarUseSpend:edit"><td>
    				<a class="btnList" href="${ctx}/car/plmCarUseSpend/form?id=${plmCarUseSpend.id}"><i title="修改" class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/car/plmCarUseSpend/delete?id=${plmCarUseSpend.id}" onclick="return confirmx('确认要删除该用车费用记录吗？', this.href)"><i title="删除" class="icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>