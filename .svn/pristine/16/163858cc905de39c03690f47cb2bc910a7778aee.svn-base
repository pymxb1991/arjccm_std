/**
 * V 1.0 2019-9-3 11:13:45 社会治安图
 */
var theme = $.cookie("theme");
var textStyle = {
	color: '#333'
};
$(document).ready(function () {
	if(theme=="black"){
		textStyle = {color:'#FFF'};
	}
	getImportAreaData();

	getRegisteredTypeData();

	getHomicideData();
});


function getImportAreaData() {

	$.ajax({
		type: "POST",
		url: ctx+"/place/ccmBasePlace/getImportByKeyPoint",
		dataType: "json",
		cache: false,
		async: true,
		success: function (data) {
			showImportArea(data);
		}
	});
}

function showImportArea(data){
	var option = {
		title : {
			text: '重点区域统计',
			// subtext: '重点类型',
			x:'center',
			textStyle : textStyle
		},
		tooltip : {
			trigger: 'item',
			formatter: "{a} <br/>{b} : {c} ({d}%)",
			hideDelay:500
		},
		legend: {
			orient : 'vertical',
			x : 'left',
			data:data["name"],
			textStyle:textStyle
		},
		toolbox: {
			show : true,
			feature : {
				dataView : {show: true, readOnly: false},
				restore : {show: true},
				saveAsImage : {show: true}
			}
		},
		calculable : true,
		series : [
			{
				name:'重点区域统计',
				type:'pie',
				radius : ['40%', '70%'],
				itemStyle : {
					normal : {
						label : {
							show : false
						},
						labelLine : {
							show : false
						}
					},
					emphasis : {
						label : {
							show : true,
							position : 'center',
							textStyle : {
								fontSize : '18',
								fontWeight : 'bold'
							}
						}
					}
				},
				data:data["value"]
			}
		]
	};

	var Barchart = echarts.init(document.getElementById('importAreaEcharts'));
	Barchart.setOption(option);
}




function getRegisteredTypeData() {

	$.ajax({
		type: "POST",
		url: ctx+"/org/ccmOrgNpse/getOrgNpseByRegiType",
		dataType: "json",
		cache: false,
		async: true,
		success: function (data) {
			showRegisteredType(data);
		}
	});
}

function showRegisteredType(data){
	var option = {
		title : {
			text: '寄递安全登记注册类型统计',
			// subtext: '纯属虚构',
			x:'center',
			textStyle : textStyle
		},
		tooltip : {
			trigger: 'item',
			formatter: '{b} : {c} ({d}%)'
		},
		legend: {
			orient : 'vertical',
			x : 'left',
			data:data["name"],
			textStyle:textStyle
		},
		series : [
			{
				name:'登记注册类型',
				type:'pie',
				radius : '55%',
				center: ['50%', '60%'],
				data:data["value"]
			}
		]
	};

	var Barchart = echarts.init(document.getElementById('registeredTypeEcharts'));
	Barchart.setOption(option);
}




function getHomicideData() {

	$.ajax({
		type: "POST",
		url: ctx+"/event/ccmEventIncident/getHomicideByArea",
		dataType: "json",
		cache: false,
		async: true,
		success: function (data) {
			showHomicide(data);
		}
	});
}

function showHomicide(data){
	var option = {
		title : {
			text: '命案区域统计',
			x:'center',
			textStyle : textStyle
		},
		tooltip : {
			trigger: 'axis',
			formatter: '{b}:{c}'
		},
		xAxis : [
			{
				type : 'category',
				data :  data["type"],
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
				name:'所属区域',
				type:'bar',
				data:data["value"],
				markPoint : {
					data : [

					]
				},
				itemStyle:{
					normal: {
						color: function(params) {
							// build a color map as your need.
							var colorList = [
								'#F0805A','#E87C25','#27727B','#FE8463','#9BCA63','#FCCE10'
							];
							return colorList[params.dataIndex]
						},
						label: {
							show: true,
							position: 'top',
							formatter: '{c}'
						}
					}
				}
			}
		]
	};

	var Barchart = echarts.init(document.getElementById('homicideEcharts'));
	Barchart.setOption(option);
}