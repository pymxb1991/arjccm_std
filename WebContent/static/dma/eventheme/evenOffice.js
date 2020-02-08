
var  areaName = [];
var  areaData = [];
$(document).ready(function () {
	countEventAreaData();
});
function countEventAreaData() {

	$.ajax({
		type: "POST",
		url: ctx+"/eventType/dmaEventTypeCount/countOfficeEvent",
		dataType: "json",
		cache: false,
		async: true,
		success: function (data) {
			showEventArea(data);
		}
	});
}
function showEventArea(data){
	// 指定图表的配置项和数据
	option = {
		title : {
			text: '各单位工作量统计',
			subtext: '单位-处理类型'
		},
		tooltip : {
			trigger: 'axis'
		},
		legend: {
			data:data["eventAreaTypeData"]
		},
		toolbox: {
			show : true,
			feature : {
				mark : {show: true},
				dataView : {show: true, readOnly: false},
				magicType : {show: true, type: ['line', 'bar']},
				restore : {show: true},
				saveAsImage : {show: true}
			}
		},
		calculable : true,
		xAxis : [
			{
				type : 'category',
				data : data["eventAreaNameData"]
			}
		],
		yAxis : [
			{
				type : 'value'
			}
		],
		series : data["eventAreaData"]
	};
	var Barchart = echarts.init(document.getElementById('eventAreaEcharts'));
	Barchart.setOption(option);
}