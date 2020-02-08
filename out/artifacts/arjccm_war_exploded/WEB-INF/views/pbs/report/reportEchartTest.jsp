<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>统计信息</title>
<meta name="decorator" content="default" />
<link href="${ctxStatic}/ccm/pop/css/ccmPepInfo.css" rel="stylesheet" />
<script type="text/javascript"
	src="${ctxStatic}/echarts/echarts.common.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/echarts/walden.js"></script>
<script type="text/javascript" src="${ctxStatic}/echarts/roma.js"></script>
<script type="text/javascript" src="${ctxStatic}/echarts/essos.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/echarts/echarsCommonPbs.js"></script>
</head>
<body>
	<div class="context" content="${ctx}"></div>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/report/num">学员活动统计</a></li>
		<li class="active"><a >学员考试信息统计</a></li>
		<li><a href="${ctx}/report/struct">学员组织构成统计</a></li>
	</ul>
	<div class="row-fluid">
		<div id="ech1" class="span9"></div>
		<div id="echList1" class="span3">
			<div class="ToAuto">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>类型</th>
							<th title="本月考试数">本月考试成绩</th>
						</tr>
					</thead>
					<tbody class="body">
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div id="ech2" class="span9"></div>
		<div id="echList2" class="span3">
			<div class="ToAuto">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>类型</th>
							<th title="考试成绩">考试成绩</th>
						</tr>
					</thead>
					<tbody class="body">
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="${ctxStatic}/pbs/report/js/reportEchartTest.js"></script>
</body>
</html>