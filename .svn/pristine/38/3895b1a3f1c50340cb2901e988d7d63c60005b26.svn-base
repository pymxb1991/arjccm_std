<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>数据统计</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/ccm/pop/css/ccmPepInfo.css" rel="stylesheet" />
<script type="text/javascript"
	src="${ctxStatic}/echarts/echarts.common.min.js"></script>
<script type="text/javascript" 
	src="${ctxStatic}/echarts/walden.js"></script>

</head>
<body>
	<div class="context" content="${ctx}"></div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pop/ccmPepInfo/form">数据统计</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="ccmPeople"
		action="${ctx}/pop/ccmPepInfo/form" method="post"
		class="breadcrumb form-search">
		<input id="btnSubmit" class="btn btn-primary ccmPepInfo" type="button"
			value="查询" style="float: right; margin-right: 3%" />
		<table>
			<tr>
				<td style="width: 330px">人口类型： <form:select path="type"
						class="input-medium">
						<form:option value="" label="全部数据" />
						<form:options items="${fns:getDictList('sys_ccm_people')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				<td />
			<tr />
		</table>
	</form:form>
	<div class="row-fluid">
		<div id="ech" class="span9"></div>
		<div id="echListAll" class="span3">
			<table class="table table-striped "  >
				<thead>
					<tr>
						<th>类型</th>
						<th>户籍</th>
						<th>流动</th>
						<th>境外</th>
					</tr>
				</thead>
				<tbody class="body">
				</tbody>
			</table>
		</div>
	</div>
	<div class="row-fluid">
		<div id="ech1" class="span9"></div>
		<div id="echList1" class="span3">
			<table class="table table-striped" >
				<thead>
					<tr>
						<th>类型</th>
						<th>总数</th>
					</tr>
				</thead>
				<tbody class="body">

				</tbody>
			</table> 
		</div>
	</div>
	<div class="row-fluid">
		<div id="ech2" class="span9"></div>
		<div id="echList2" class="span3">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>类型</th>
						<th>总数</th>
					</tr>
				</thead>
				<tbody class="body">
				</tbody>
			</table> 
		</div>
	</div>
	<script type="text/javascript"
		src="${ctxStatic}/ccm/pop/js/ccmPepInfo.js"></script> 
</body>
</html>
