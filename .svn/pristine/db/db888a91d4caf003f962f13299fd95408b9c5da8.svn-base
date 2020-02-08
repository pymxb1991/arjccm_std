<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>趋势研判分析</title>
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
			getTrendData();
		});
		function getTrendData() {
			$.ajax({
				type: "POST",
				url: ctx+"/trendResearch/dmaTrendResearch/getCountpersonAndEvent",
				dataType: "json",
				<%--data:{ident:"${ccmPeople.ident}",name:"${ccmPeople.name}",residencedetail:"${ccmPeople.residencedetail}"},--%>
				cache: false,
				async: true,
				success: function (data) {
					showTrend(data);
				}
			});
		}
		function showTrend(data) {
			var theme = $.cookie('theme');
			var textStyle = {
				color: '#333'
			};
			if (theme == "black") {
				textStyle = {color: '#FFF'};
			}
			var option = {
				title:{
					text: '趋势研判分析'
				},
				tooltip : {
					trigger: 'axis'
				},
				legend: {
					data:['人员流入','人员流出','事件发生率'],
					textStyle:textStyle
				},
				toolbox: {
					show : true,
					feature : {
						// mark : {show: true},
						dataView : {show: true, readOnly: false},
						magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
						restore : {show: true},
						saveAsImage : {show: true}
					}
				},
				// calculable : true,
				xAxis : [
					{
						type : 'category',
						boundaryGap : false,
						// data : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
						data:data["name"],
						axisLabel : {
							textStyle : textStyle
						}
					}
				],
				yAxis : [
					{
						type : 'value',
						axisLabel : {
							textStyle : textStyle
						}
					}
				],
				series : [
					{
						name:'人员流入',
						type:'line',
						stack: '总量',
						// data:[120, 132, 101, 134, 90, 230, 210,120, 132, 101, 134, 90, 230]
						data:data["value"]
					},
					{
						name:'人员流出',
						type:'line',
						stack: '总量',
						// data:[220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234, 290]
						data:data["value1"]
					},
					{
						name:'事件发生率',
						type:'line',
						stack: '总量',
						// data:[150, 232, 201, 154, 190, 330, 410,150, 232, 201, 154, 190, 330]
						data:data["value2"]
					}
				]
			};
			var Barchart = echarts.init(document.getElementById('trendEcharts'));
			Barchart.setOption(option);
		}

	</script>
</head>
<body>
<div class="container-fluid" style="height: 100%; overflow: hidden"
	 id="main">
	<div class="context" content="${ctx}"></div>

		<div class="row-fluid height100">
			<div class="height33 shadow" >
<%--				<div class="top-header">趋势研判分析</div>--%>
				<div class="common-pading">
					<div id="trendEcharts" class="echarts" style="width: 100%;height: 100%;"></div>
				</div>
			</div>
		</div>

</div>
</body>
</html>