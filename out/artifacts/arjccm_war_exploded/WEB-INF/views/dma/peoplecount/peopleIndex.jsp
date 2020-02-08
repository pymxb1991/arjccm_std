<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>实有人口分析统计</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
	<link href="${ctxStatic}/bootstrap/2.3.1/css_${not empty cookie.theme.value ? cookie.theme.value : 'black'}/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<link href="${ctxStatic}/bootstrap/2.3.1/css_${not empty cookie.theme.value ? cookie.theme.value : 'black'}/custom.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.min.js"></script>
	<link rel="stylesheet"
		  href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome.min.css">
		  <!--[if lte IE 7]>
	<link rel="stylesheet" href="../bootstrap/2.3.1/awesome/font-awesome-ie7.css">
	<![endif]-->
	<!--[if lte IE 6]>
	<link rel="stylesheet" href="../bootstrap/bsie/css/bootstrap-ie6.min.css">
	<script src="../bootstrap/bsie/js/bootstrap-ie.min.js"></script>
	<![endif]-->
	<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
	<script src="${ctxStatic}/dma/peoplecount/js/dmaPeopleCount.js"></script>
    <link rel="stylesheet" href="${ctxStatic}/laydate/theme/default/laydate.css" media="all">
    <script src="${ctxStatic}/laydate/laydate.js"></script>
	<script src="${ctxStatic}/echarts/theme/${not empty cookie.theme.value ? cookie.theme.value : 'black'}.js"></script>
	<style>
		.common-pading{
			width:100%;
			height:750px;
			padding:5px;
		}
		.echarts{
			width:100%;
			height:100%;
		}
		.width33{
			width: 33%;
			height: 50%;
			float: left;
		}
		.width66{
			width: 66%;
			height: 50%;
			float: left;
		}
		.width49{
			width: 49%;
			height: 50%;
			float: left;
		}
		.width50{
			width: 50%;
			height: 50%;
			float: left;
		}
		.width99{
			width: 99%;
			height: 50%;
			float: left;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {

		});
	</script>
</head>
<body>
<div class="container-fluid" style="height: 100%; overflow: hidden" id="main">
	<div class="context" content="${ctx}"/>
	<div class="row-fluid height100">
		<div class="width33 shadow" >
			<%--<div class="top-header">人员性别分析统计</div>--%>
			<div class="common-pading">
				<div id="peopleSexCount" class="echarts" style="width: 100%;height: 100%;"></div>
			</div>
		</div>
		<div class="width66 shadow" >
			<%--<div class="top-header">人员出生时间统计</div>--%>
			<div class="common-pading">
				<div id="peopleBirthdayCount" class="echarts" style="width: 100%;height: 100%;"></div>
			</div>
		</div>
		<div class="width49 shadow" >
			<%--<div class="top-header">各民族人员统计</div>--%>
			<div class="common-pading">
				<div id="peopleNationCount" class="echarts" style="width: 100%;height: 100%;"></div>
			</div>
		</div>
		<div class="width50 shadow" >
			<%--<div class="top-header">人员年龄段统计</div>--%>
			<div class="common-pading">
				<div id="peopleAgeCount" class="echarts" style="width: 100%;height: 100%;"></div>
			</div>
		</div>
		<div class="width99 shadow" >
			<%--<div class="top-header">人员区域社区（街道）分布分析统计</div>--%>
			<div class="common-pading">
				<div id="peopleRegionCount" class="echarts" style="width: 100%;height: 100%;"></div>
			</div>
		</div>
	</div>
</div>
</body>
</html>