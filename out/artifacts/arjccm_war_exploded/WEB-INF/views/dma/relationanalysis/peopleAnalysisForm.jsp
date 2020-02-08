<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>社会治安</title>
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

<%--	<script src="${ctxStatic}/dma/socialsecurity/js/dmaSocialSecurity.js"></script>--%>
	<style>
		.common-pading{
			width:100%;
			height:745px;
			padding:5px;
		}
		.echarts{
			width:100%;
			height:100%;
		}

	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			getPeopleRelationData();
		});
		function getPeopleRelationData() {
			$.ajax({
				type: "POST",
				url: ctx+"/relationAnalysis/dmaPeopleAnalysis/getPeopleRelationData",
				dataType: "json",
				data:{ident:"${ccmPeople.ident}",name:"${ccmPeople.name}",residencedetail:"${ccmPeople.residencedetail}"},
				cache: false,
				async: true,
				success: function (data) {
					showPeopleRelation(data);
				}
			});
		}
		function showPeopleRelation(data){
			var theme = $.cookie('theme');
			var textStyle = {
				color: '#333'
			};
			if(theme=="black"){
				textStyle = {color:'#FFF'};
			}
			var option = {
				// title : {
				// 	text: '人物关系：乔布斯',
				// 	subtext: '数据来自人立方',
				// 	x:'right',
				// 	y:'bottom'
				// },
				// tooltip : {
				// 	trigger: 'item',
				// 	formatter: '{a} : {b}'
				// },
				toolbox: {
					show : true,
					feature : {
						restore : {show: true},
						// magicType: {show: true, type: ['force', 'chord']},
						saveAsImage : {show: true}
					}
				},
				legend: {
					x: 'left',
					data:['事件','现住址','人际关系','车辆'],
					textStyle:textStyle
				},
				series : [
					{
						type:'force',
						name : "人物关系",
						ribbonType: false,
						categories : [
							{
								name: '本人'
							},
							{
								name: '事件'
							},
							{
								name: '现住址'
							},
							{
								name:'人际关系'
							},
							{
								name:'车辆'
							}
						],
						itemStyle: {
							normal: {
								label: {
									show: true,
									textStyle: textStyle
								},
								nodeStyle : {
									brushType : 'both',
									borderColor : 'rgba(255,215,0,0.4)',
									borderWidth : 1
								},
								linkStyle: {
									type: 'curve'
								}
							},
							emphasis: {
								label: {
									show: false
									// textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
								},
								nodeStyle : {
									//r: 30
								},
								linkStyle : {}
							}
						},
						useWorker: false,
						minRadius : 15,
						maxRadius : 25,
						gravity: 1.1,
						scaling: 1.1,
						roam: 'move',
						nodes:
								data["nodes"]
			// 				{category:0, name: '乔布斯', value : 10, label: '乔布斯\n（主要）'},
			// 				{category:1, name: '丽萨-乔布斯',value : 2},
			// 				{category:1, name: '保罗-乔布斯',value : 3},
			// 				{category:1, name: '克拉拉-乔布斯',value : 3},
			// 				{category:1, name: '劳伦-鲍威尔',value : 7},
			// 				{category:2, name: '史蒂夫-沃兹尼艾克',value : 5},
			// 				{category:2, name: '奥巴马',value : 8},
			// 				{category:2, name: '比尔-盖茨',value : 9},
			// 				{category:2, name: '乔纳森-艾夫',value : 4},
			// 				{category:2, name: '蒂姆-库克',value : 4},
			// 				{category:2, name: '龙-韦恩',value : 1},
						,
						links :
								data["links"]
							// {source : '丽萨-乔布斯', target : '乔布斯', weight : 1, name: '女儿'},
							// {source : '保罗-乔布斯', target : '乔布斯', weight : 2, name: '父亲'},
							// {source : '克拉拉-乔布斯', target : '乔布斯', weight : 1, name: '母亲'},
							// {source : '劳伦-鲍威尔', target : '乔布斯', weight : 2},
							// {source : '史蒂夫-沃兹尼艾克', target : '乔布斯', weight : 3, name: '合伙人'},
							// {source : '奥巴马', target : '乔布斯', weight : 1},
							// {source : '比尔-盖茨', target : '乔布斯', weight : 6, name: '竞争对手'},
							// {source : '乔纳森-艾夫', target : '乔布斯', weight : 1, name: '爱将'},
							// {source : '蒂姆-库克', target : '乔布斯', weight : 1},
							// {source : '龙-韦恩', target : '乔布斯', weight : 1},
							// {source : '克拉拉-乔布斯', target : '保罗-乔布斯', weight : 1},
							// {source : '奥巴马', target : '保罗-乔布斯', weight : 1},
							// {source : '奥巴马', target : '克拉拉-乔布斯', weight : 1},
							// {source : '奥巴马', target : '劳伦-鲍威尔', weight : 1},
							// {source : '奥巴马', target : '史蒂夫-沃兹尼艾克', weight : 1},
							// {source : '比尔-盖茨', target : '奥巴马', weight : 6},
							// {source : '比尔-盖茨', target : '克拉拉-乔布斯', weight : 1},
							// {source : '蒂姆-库克', target : '奥巴马', weight : 1}

					}
				]
			};
			var ecConfig = echarts.config;
			function focus(param) {
				var data = param.data;
				var links = option.series[0].links;
				var nodes = option.series[0].nodes;
				if (
						data.source !== undefined
						&& data.target !== undefined
				) { //点击的是边
					var sourceNode = nodes.filter(function (n) {return n.name == data.source})[0];
					var targetNode = nodes.filter(function (n) {return n.name == data.target})[0];
					console.log("选中了边 " + sourceNode.name + ' -> ' + targetNode.name + ' (' + data.weight + ')');
				} else { // 点击的是点
					console.log("选中了" + data.name + '(' + data.value + ')');
				}
			}


			var Barchart = echarts.init(document.getElementById('peopleRelation'));
			console.info("ecConfig",ecConfig);
			Barchart.on("click", focus)

			Barchart.on("force_layout_end", function () {
				console.log(Barchart.chart.force.getPosition());
			});
			Barchart.setOption(option);
		}
	</script>
</head>
<body>
<div class="container-fluid" style="height: 100%; overflow: hidden"
	 id="main">
	<div class="context" content="${ctx}"></div>

	<div class="row-fluid height100">
		<div class="shadow" >
<%--			<div class="top-header">重点区域统计</div>--%>
			<div class="common-pading">
				<div id="peopleRelation" class="echarts" style="width: 100%;height: 100%;"></div>
			</div>
		</div>
	</div>

</div>
</body>
</html>