<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>重大事项数据分析</title>
	<meta name="decorator" content="default"/>
	<style>
	.common-pading{
	  width:100%;
	  height:200px;
	  padding:5px;
	}
	.echarts{
	  width:100%;
	  height:100%;
	}
	
	
	</style>
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
		<li class="active"><a href="${ctx}/patrol/ccmPatrolMissions/summaryGraph">统计数据</a></li>
		<li><a href="${ctx}/patrol/ccmPatrolMissions/">数据列表</a></li>
		<li ><a href="${ctx}/patrol/ccmPatrolMissions/form?id=${ccmPatrolMissions.id}">巡逻任务<shiro:hasPermission name="patrol:ccmPatrolMissions:edit">${not empty ccmPatrolMissions.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="patrol:ccmPatrolMissions:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	
	<sys:message content="${message}"/>
	    <br>
	    <div class="row-fluid">
			
					<h4>巡逻单位任务完成情况</h4>
					<div class="common-pading">
						<div id="PopFlowTable" class="echarts" ></div>
					</div>

			</div>
		      
			
		    
	    </div>
	    <br>
	     <div class="row-fluid">
	     	  
			<div class="span12" ><h4>巡逻单位任务完成情况</h4>
					<div style="height:571px;overflow: auto">
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
					<thead>
						<tr id="th">
							<th>单位名称</th>
						</tr>
					</thead>
					<tbody id="td">
						<tr >
							<td>
							</td>

						</tr>
					</tbody>
				</table>
				</div>
			</div> 
	    </div>
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
<script src="${ctxStatic}/jquery/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.js"></script>
<script>
	var color;

	var theme=$.cookie('theme');
	if(theme=='gradient'){
		color = [ '#25B8FE', '#2BE0D5', '#B180E4', '#FD93B6', '#53D5FF', '#F8C73C', '#1F8BFA', '#F77136',//循环一遍
			'#25B8FE', '#2BE0D5', '#B180E4', '#FD93B6', '#53D5FF', '#F8C73C', '#1F8BFA', '#F77136' ];
	}
	else {

		color = ['#D87A82', '#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d',
			'#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];
	}
	//基础参数
	var windowsHeight, _fontSize = 14,

	_fontSize1 = 26,
	breakData = 8;
	legendTop = '30%',
	radiusData = [90, 65],
	lengthECharts = 30;
	var context = $(".context").attr("content");

	var FontColor="#999",backgroundColor="#fff";
	var theme=$.cookie('theme');
	if(theme=="black"){
		FontColor="#fff";
		backgroundColor="#0e2a4c";
	}
	$(function(){
	    windowsHeight= $(window).width();
		PopFlowTableFun();
		function PopFlowTableFun() {
			var option = {
				tooltip: {
					trigger: 'axis',
					axisPointer: { // 坐标轴指示器，坐标轴触发有效
						type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
					}
				},
				legend: {
					x: 'center',
					y: 10,
					data: ['幺铺镇', '西航办事处', '宋旗镇'],
					textStyle: {
						fontWeight: 'normal', //标题颜色
						color: '#000'
					},
				},
				grid: {
					borderWidth: 0,
					left: '3%',
					right: '4%',
					bottom: '5%',
					containLabel: true
				},
				xAxis: {
					type: 'category',
					data: ['未启动', '进行中', '已完成'],
					//min:0,
					//max: 250000,
					textStyle: {
						color: '#000'
					},
					axisLine: {
						lineStyle: {
							color: '#000',
							width: 2
						}
					},
					axisLabel: {
						show: true,
						textStyle: {
							color: '#000'
						}
					},
					splitLine: {
						show: true
					}
				},
				yAxis: {
					type: 'value',
					name: '个数',
					splitLine: {
						show: false
					},
					axisLine: {
						lineStyle: {
							color: '#000',
							width: 2
						}
					},
					axisLabel: {
						show: true,
						textStyle: {
							color: '#000'
						}
					}
				},
				series: [],
				opts:{
					width:null
				}

			};
			// {
			// 	name: "qwe",
			// 			type: 'bar',
			// 		itemStyle: {
			// 	normal: {
			// 		color: color[2]
			// 	}
			// },
			// 	data: [1,1,1]
			// }
			$.post("summaryGraphData",{},function (data) {
				var mapUnit = data["mapUnit"];
				var title = data["key"];
				var type = data["typeArr"];
				option.legend.data = title;
				option.xAxis.data = type;
				for (var i = 0; i < title.length; i++) {
					option.series[i] = {
						name: title[i],
						type: 'bar',
						itemStyle: {
							normal: {
								color: color[i]
							}
						},
						data: mapUnit[title[i]]
					}
				}
				var myChart = echarts.init(document.getElementById("PopFlowTable"));
				myChart.setOption(option, true);

				var oneHtml="<th>单位名称</th>";
				for (var i = 0; i <type.length ; i++) {
					oneHtml+="<th>"+type[i]+"</th>";
				}
				$("#th").html(oneHtml);
				var twoHtml=""
				for (var i = 0; i <title.length ; i++) {
					twoHtml+="<tr ><td>"+title[i]+"</td>"
					for (var i1 = 0; i1 <mapUnit[title[i]].length ; i1++) {
						twoHtml+="<td>"+mapUnit[title[i]][i1]+"</td>"
					}
					twoHtml+="</tr >"
				}
				$("#td").html(twoHtml);
			})




		}
	 })
	 
		
</script>
</body>
</html>