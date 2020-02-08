<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>两新组织信息统计</title>
	<meta name="decorator" content="default"/>
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
	<script src="${ctxStatic}/common/index/Scripts/js/echarts2.2.7/echarts-all.js"></script>
	<script src="${ctxStatic}/dma/twoneworg/js/dmaTwoNewOrg.js"></script>
	<script src="${ctxStatic}/echarts/theme/${not empty cookie.theme.value ? cookie.theme.value : 'black'}.js"></script>
	<style>
		.common-pading{
			width:100%;
			height:750px;
			padding-top:50px;
		}
		.echarts{
			width:100%;
			height:100%;
		}


	</style>
	<script type="text/javascript">
		$(document).ready(function() {

		});
	</script>
</head>
<body>
<div class="container-fluid" style="height: 100%; overflow: hidden"
	 id="main">
	<div class="context" content="${ctx}"></div>

		<div class="row-fluid height100">
			<div class="height33 shadow" >
<%--				<div class="top-header">两新组织信息统计</div>--%>
				<div class="common-pading">
					<div id="orgEcharts" class="echarts" style="width: 100%;height: 100%;"></div>
				</div>
			</div>
		</div>

</div>
</body>
</html>