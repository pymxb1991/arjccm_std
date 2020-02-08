/**
 * V 1.0 2019-9-3 11:13:45 两新组织图
 */
var theme = $.cookie("theme");
var textStyle = {
	color: '#333'
};
$(document).ready(function () {
	if(theme=="black"){
		textStyle = {color:'#FFF'};
	}
	getData();

});

function getData() {

	$.ajax({
		type: "POST",
		url: ctx+"/partybuild/ccmPartyOrganiz/getOrgByBussCate",
		dataType: "json",
		cache: false,
		async: true,
		success: function (data) {
			showInfo(data);
		}
	});
}

function showInfo(data){
	var option = {
		title : {
			text: '两新组织信息统计'
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
				name:'企业类型',
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

	var Barchart = echarts.init(document.getElementById('orgEcharts'));
	Barchart.setOption(option);
}