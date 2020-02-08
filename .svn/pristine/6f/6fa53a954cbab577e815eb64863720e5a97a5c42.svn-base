<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>实有居民组合统计</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
	<link href="${ctxStatic}/bootstrap/2.3.1/css_${not empty cookie.theme.value ? cookie.theme.value : 'black'}/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<link href="${ctxStatic}/bootstrap/2.3.1/css_${not empty cookie.theme.value ? cookie.theme.value : 'black'}/custom.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome.min.css">
	<!--[if lte IE 7]>
	<link rel="stylesheet" href="../bootstrap/2.3.1/awesome/font-awesome-ie7.css">
	<![endif]-->
	<!--[if lte IE 6]>
	<link rel="stylesheet" href="../bootstrap/bsie/css/bootstrap-ie6.min.css">
	<script src="../bootstrap/bsie/js/bootstrap-ie.min.js"></script>
	<![endif]-->
	<script type="text/javascript" src="${ctxStatic}/echarts/echarts-4.2.1/echarts.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/dma/js/threerealdataanalysis/residentStatistics.js"></script>
	<script type="text/javascript">
		$(document).ready(function () {
			$('#mianEcharts').height($(window).height()*0.98);
		});
	</script>
	<style>
		.common-pading{
			width:100%;
			height:500px;
			padding:0.25%;
		}
		.borderEcharts {
			border-radius:5px ;
			box-shadow: 1px 2px 5px #ccc;
			margin: 0.25% 0.5%;
    		background-color: none;
		}
		.echarts{
			width:99.5%;
			height:100%;
		}
		.widthAll{
			width: 99.5%;
			float: left;
		}
		#mianEcharts{
			overflow: scroll;
			padding-left: 5px;
			padding-right: 5px;
			overflow-x: hidden;
		}
	</style>
</head>
<body>
	<div id="mianEcharts">
		<div class="context" content="${ctx}"></div>
		<div class="widthAll borderEcharts">
			<div class="common-pading">
				<div id="echArea" class="echarts"></div>
			</div>
		</div>
		<div class="widthAll borderEcharts">
			<div class="common-pading">
				<div id="echSex" class="echarts"></div>
			</div>
		</div>
	</div>
</body>
</html>