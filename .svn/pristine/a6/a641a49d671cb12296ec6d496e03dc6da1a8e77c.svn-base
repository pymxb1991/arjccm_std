var  beginHappenDate = '' ;
var  endHappenDate ='';
var  areaName = [];
var  areaData = [];
$(document).ready(function () {
	countEventAreaData();
	// countEventData();

    //执行一个laydate实例
    laydate.render({
        elem: '#beginHappenDate' //指定元素
       // ,range: true // 开启左右面板范围选择
		,trigger: 'click' //采用click弹出
		,done: function(value, date, endDate){
			//console.log("beginHappenDate",value);//得到日期生成的值，如：2017-08-18
			beginHappenDate = value;
			// console.log(beginHappenDate);//得到日期生成的值，如：2017-08-18
			// console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
			// console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
		}
    });
    laydate.render({
        elem: '#endHappenDate' //指定元素
       // ,range: true // 开启左右面板范围选择
		,trigger: 'click' //采用click弹出
		,done: function(value, date, endDate){
			//console.log("beginHappenDate",value); //得到日期生成的值，如：2017-08-18
			endHappenDate = value;
			// console.log(endHappenDate); //得到日期生成的值，如：2017-08-18
			// console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
			// console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
		}
    });

});
function countEventAreaData() {

	$.ajax({
		type: "POST",
		url: ctx+"/eventType/dmaEventTypeCount/countAreaEventByReportType?beginHappenDate="+ beginHappenDate +"&endHappenDate=" + endHappenDate,
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
			text: '事件分区域统计',
			subtext: '分区域-分类型'
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
function queryEventInfo(){

	var begin = new Date(beginHappenDate);
	var end = new Date(endHappenDate);
	if(begin.getTime() > end.getTime()){
		messageAlert("开始时间大于结束时间！", "error");
		return false;
	}
	countEventAreaData(beginHappenDate,endHappenDate);
}
