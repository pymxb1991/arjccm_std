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
		<script type="text/javascript" src="${ctxStatic}/plm/car/plmCarUseForm.js"></script> 
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/car/plmCarUse/">领用记录列表</a></li>
		<%-- <shiro:hasPermission name="car:plmCarUse:edit"><li><a href="${ctx}/car/plmCarUse/form">领用记录添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="plmCarUse" action="${ctx}/car/plmCarUse/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>车辆：</label>
				<form:input id="carIdsAll" path="car.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>领出人：</label>
				<sys:treeselect id="use" name="use.id" value="${plmCarUse.use.id}" labelName="use.name" labelValue="${plmCarUse.use.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>领用事由：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('plm_car_use_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>车辆</th>
				<th>领出人</th>
				<th>领出日期</th>
				<th>领用事由</th>
				<th>归还人</th>
				<th>归还日期</th>
				<shiro:hasPermission name="car:plmCarUse:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="plmCarUse">
			<tr>
				<td><a href="${ctx}/car/plmCarUse/form?id=${plmCarUse.id}">
					${plmCarUse.car.vehicle}
				</a></td>
				<td>
					${plmCarUse.use.name}
				</td>
				<td>
					<fmt:formatDate value="${plmCarUse.outDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(plmCarUse.type, 'plm_car_use_type', '')}
				</td>
				<td>
					${plmCarUse.gbuse.name}
				</td>
				<td>
					<fmt:formatDate value="${plmCarUse.inDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<shiro:hasPermission name="car:plmCarViolation:edit">
						<a onclick="parent.LayerDialog('${ctx}/car/plmCarViolation/addFromUseList?carUseId=${plmCarUse.id}', '【${plmCarUse.car.vehicle}】本次领用违章记录', '1000px', '700px')">
								 <i title="追加违章" class="icon-fast-forward"></i></a>
					</shiro:hasPermission>					
					<shiro:hasPermission name="car:plmCarUse:edit">
	    				<a class="btnList" href="${ctx}/car/plmCarUse/form?id=${plmCarUse.id}"><i title="修改" class="icon-pencil"></i></a>
						<a class="btnList" href="${ctx}/car/plmCarUse/delete?id=${plmCarUse.id}" onclick="return confirmx('确认要删除该领用记录吗？', this.href)"><i title="删除" class="icon-trash"></i> </a>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>