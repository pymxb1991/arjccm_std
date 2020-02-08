<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>领用记录管理</title>
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
		<%-- <li ><a href="${ctx}/car/plmCar/">车辆列表</a></li> --%>
		<li class="active"><a href="${ctx}/car/plmCarUse/carList?car.id=${plmCar.id}">领用记录列表</a></li>
		<shiro:hasPermission name="car:plmCarUse:edit">
			<c:if test="${plmCar.vehicleStatus eq '01'}">
				<li><a href="${ctx}/car/plmCarUse/carForm?car.id=${plmCar.id}">领用记录添加</a></li>
			</c:if>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="plmCarUse" action="${ctx}/car/plmCarUse/carList?car.id=${plmCar.id}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>领用事由：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('plm_car_use_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>		
			<li><label>领出人：</label>
				<sys:treeselect id="use" name="use.id" value="${plmCarUse.use.id}" labelName="use.name" labelValue="${plmCarUse.use.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true" isAll="true"/>
			</li>
			<li><label>使用时间：</label>
				<input name="beginOutDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${plmCarUse.beginOutDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li class="btns"><a id="btnSubmit" class="btn btn-primary" href="javascript:;"><i class="icon-search"></i> 查询</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>流程类型</th>
				<th>领出日期</th>
				<th>领出状态</th>				
				<th>领出人</th>
				<th>归还日期</th>
				<th>归还人</th>
				<%-- <shiro:hasPermission name="car:plmCarUse:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="plmCarUse">
			<tr>
				<td>
					${fns:getDictLabel(plmCarUse.type, 'plm_car_use_type', '')}
				</td>			
				<td><a href="${ctx}/car/plmCarUse/carForm?id=${plmCarUse.id}">
					<fmt:formatDate value="${plmCarUse.outDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${fns:getDictLabel(plmCarUse.gbFlag, 'plm_car_use_gbFlag', '')}
				</td>
				<td>
					${plmCarUse.use.name}
				</td>
				<td>
					<fmt:formatDate value="${plmCarUse.inDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${plmCarUse.gbuse.name}
				</td>

				<%-- <shiro:hasPermission name="car:plmCarUse:edit"><td>
    				<a href="${ctx}/car/plmCarUse/form?id=${plmCarUse.id}">修改</a>
					<a href="${ctx}/car/plmCarUse/delete?id=${plmCarUse.id}" onclick="return confirmx('确认要删除该领用记录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>  --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>