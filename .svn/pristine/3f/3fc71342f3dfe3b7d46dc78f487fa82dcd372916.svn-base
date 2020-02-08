<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>过车信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
		<li class="active"><a href="${ctx}/carpass/ccmCarPass/">过车信息列表</a></li>
		<%-- <shiro:hasPermission name="carpass:ccmCarPass:edit"><li><a href="${ctx}/carpass/ccmCarPass/form">过车信息添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmCarPass" action="${ctx}/carpass/ccmCarPass/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>车牌号码：</label>
				<form:input path="number" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>所属卡口：</label>
<%--				<form:input path="bayonet" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
				<form:select path="bayonet" class="input-medium required">
					<form:option value="" label="全部" />
					<form:options items="${bayonetList}" itemLabel="bayonetName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>所属车道：</label>
<%--				<form:input path="lane" htmlEscape="false" maxlength="255" class="input-medium"/>--%>
				<form:select path="lane" class="input-medium required">
					<form:option value="" label="全部" />
					<form:options items="${laneList}" itemLabel="laneName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>车辆品牌：</label>
				<form:input path="brand" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>车辆类型：</label>
				<form:input path="type" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>过车时间：</label>
				<input id="passtime" name="passtime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${ccmCarPass.passtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>车牌</th>
				<th>车牌号码</th>
				<th>所属卡口</th>
				<th>所属车道</th>
				<th>车辆品牌</th>
				<th>车辆类型</th>
				<th>过车时间</th>
				<shiro:hasPermission name="carpass:ccmCarPass:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ccmCarPass">
			<tr>
				<td><a href="${ctx}/carpass/ccmCarPass/form?id=${ccmCarPass.id}">
					${ccmCarPass.plate}
				</a></td>
				<td>
					${ccmCarPass.number}
				</td>
				<td>
					${ccmCarPass.ccmCarBayonet.bayonetName}
				</td>
				<td>
					${ccmCarPass.ccmLane.laneName}
				</td>
				<td>
					${ccmCarPass.brand}
				</td>
				<td>
					${ccmCarPass.type}
				</td>
				<td>
					<fmt:formatDate value="${ccmCarPass.passtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="carpass:ccmCarPass:edit"><td>
					<a class="btnList" onclick="parent.parent.LayerDialog('${ctx}/carpass/ccmCarPass/form?id=${ccmCarPass.id}', '详情', '900px', '800px')" title="详情"><i class="icon-list-ul"></i></a>
					<%-- <a class="btnList" href="${ctx}/carpass/ccmCarPass/form?id=${ccmCarPass.id}" title="查看"><i class="icon-file"></i></a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>