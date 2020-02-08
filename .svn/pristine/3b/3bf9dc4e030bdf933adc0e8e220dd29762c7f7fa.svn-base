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
<script type="text/javascript"
	src="${ctxStatic}/echarts/echarsCommonPbs.js"></script>
</head>
<body>
	<div class="context" content="${ctx}"></div>
	<ul class="nav nav-tabs">
		<li  class="active"><a>学员活动统计</a></li>
		<li><a href="${ctx}/report/count">学员考试信息统计</a></li>
		<li><a  href="${ctx}/report/struct">学员组织构成统计</a></li>
	</ul>
	<div class="row-fluid">
		<div id="ech1" class="span9"></div>
		<div id="echList1" class="span3">
			<div class="ToAuto" style="overflow:hidden">
				<table class="table table-striped" >
					<thead>
						<tr>
							<th>月份</th>
							<th title="活动次数">活动次数</th>
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
							<th>月份</th>
							<th title="学员数量">学员数量</th>
						</tr>
					</thead>
					<tbody class="body">
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="${ctxStatic}/pbs/report/js/reportEchartsNum.js"></script>
</body>
</html>