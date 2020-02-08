<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html style="height:100%;width:100%;">
<head>
	<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
	<script src="${ctxStatic}/jquery/jquery.cookie.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctxStatic}/echarts/echarts-4.2.1/echarts.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {


			//仓库、物资、物资类型总数量统计
			$.getJSON(ctx + "/storage/plmStorage/countStorageList", function(data) {
				var content = data[1]
				$.getJSON(ctx + "/storage/plmEquipment/ratioEquipmentByType", function(data) {
					var ajaxDataA = new Array();
					for (var one in data[0]) {
						ajaxDataA.push({
							"name": data[0][one]["type"],
							"value": Number(data[0][one]["value"])
						});
					}
					var ajaxDataB = new Array();
					for (var one in data[1]) {
						ajaxDataB.push({
							"name": data[1][one]["typeO"],
							"value": Number(data[1][one]["value"])
						});
					}
					// 接收参数
					RatioEquipmentByType("ratioEquipmentByType", ajaxDataA,ajaxDataB,data[2],content);
				});
			});
		    var color1;
		    if($.cookie("theme")=="black"||!$.cookie("theme")){
				color1="#fff"
		    }else if($.cookie("theme")=="gradient"){
    	    	color1="#000"
    	    }else{
    	    	color1="#000"
    	    }

			function RatioEquipmentByType(model, dataValue1,dataValue2,dataLegend,count) {
				var option = { 
					color: ['#79F0C9','#9CDCDE','#F5D883','#ED817C', '#B0DAB4', '#2ec7c9',
						'#3398DB','#b6a2de','#5ab1ef','#ffb980','#d87a80','#8d98b3',
				        '#97b552','#95706d','#dc69aa','#07a2a4','#9a7fd1','#588dd5',
				        '#f5994e','#c05050','#59678c','#c9ab00','#7eb00a','#6f5553','#c14089'],
					title : {
						text: '{title|物资总数量：}{value|' + count + '}{unit|件}',
			            textStyle: {
			                rich: {
			                    title: {
			                        fontFamily: 'Microsoft YaHei',
			                        fontWeight: 400,
			                        fontSize: 12,
			                        lineHeight: 39,
			                        color: color1
			                    },
			                    value: {
			                        fontFamily: 'DIN Alternate',
			                        fontWeight: 'bold',
			                        fontSize: 30,
			                        lineHeight: 39,
			                        padding: [0, 5, 0, 5],
			                        color: "#F9CB42"
			                    },
			                    unit: {
			                        fontFamily: 'Microsoft YaHei',
			                        fontWeight: 400,
			                        fontSize: 14,
			                        lineHeight: 39,
			                        color: "#406CA9"
			                    }
			                }
			            },
			            x: 'left' 
					},
					tooltip: {
						trigger: 'item',
						formatter: "{a} <br/>{b}: {c} ({d}%)"
					},
					series: [{
						name:'物资类型',
						type:'pie',
						selectedMode: 'single',
						radius: [0, '25%'],
						center: ['40%', '50%'],
						label: {
							normal: {
								position: 'inner'
							}
						},
						labelLine: {
							normal: {
								show: false
							}
						},
						data:dataValue1
					},{
						name:'物资子类型',
						type:'pie',
						radius: ['30%', '50%'],
						center: ['40%', '50%'],
						data:dataValue2
					}]
				};
				// 实例化 对象
				var Barchart = echarts.init(document.getElementById("ratioEquipmentByType"));
				// 填充数据
				Barchart.setOption(option);
			}
		});
	</script>
</head>
<body> 
<div id="ratioEquipmentByType" style="width:100%;height:100%"></div>
</body>
</html>