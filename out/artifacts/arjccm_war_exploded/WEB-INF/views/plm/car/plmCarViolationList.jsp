<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>违章记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript"
	src="${ctxStatic}/plm/storage/ajaxMessageAlert.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#btnSubmit").on("click", function() {
			    var begin = new Date(Date.parse($("[name='beginViolDate']").val()));
			    var end = new Date(Date.parse($("[name='endViolDate']").val()));
			    if(begin.getTime() > end.getTime()){
			    	messageAlert("开始时间大于结束时间！", "error");
			    	return false;
			    }
			    $("#searchForm").submit();
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
		<li class="active"><a href="${ctx}/car/plmCarViolation/">违章记录列表</a></li>
		<%-- <shiro:hasPermission name="car:plmCarViolation:edit"><li><a href="${ctx}/car/plmCarViolation/form">违章记录添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="plmCarViolation" action="${ctx}/car/plmCarViolation/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>违章车辆：</label>
				<form:input type="hide" id="carIdsAll" path="car.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<%-- <li><label>违章司机：</label>
				<form:input type="hide" id="driverIds" path="driver.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li> --%>
			<li><label>处理状态：</label>
				<form:select path="statue" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('plm_car_violation_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>违章类型：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('plm_car_violation_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="clearfix"></li>			
			<li><label>违章时间：</label>
				<input name="beginViolDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmCarViolation.beginViolDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endViolDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmCarViolation.endViolDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
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
				<th>领用申请人</th>
				<th>处理状态</th>
				<th>违章时间</th>
				<th>违章类型</th>
				<th>罚款金额</th>
				<th>扣分</th>
				<shiro:hasPermission name="car:plmCarViolation:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="plmCarViolation">
			<tr>
				<td><a href="${ctx}/car/plmCarViolation/form?id=${plmCarViolation.id}">
					${plmCarViolation.car.vehicle}
				</a></td>
				<td>
				<c:if test="${not empty plmCarViolation.driver.id}" var="e">			
					${plmCarViolation.driver.name}
				</c:if>
				<c:if test="${!e}">
				     领用人自驾				     	
				</c:if>					
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
				<shiro:hasPermission name="car:plmCarViolation:edit"><td>
    				<a class="btnList" href="${ctx}/car/plmCarViolation/form?id=${plmCarViolation.id}&isCar=1"><i title="修改" class="icon-pencil"></i></a>
					<a class="btnList" href="${ctx}/car/plmCarViolation/delete?id=${plmCarViolation.id}" onclick="return confirmx('确认要删除该违章记录吗？', this.href)"><i title="删除" class="icon-trash"></i></a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>