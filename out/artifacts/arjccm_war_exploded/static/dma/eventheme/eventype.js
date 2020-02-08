var  beginHappenDate = '' ;
var  endHappenDate ='';
$(document).ready(function () {
	countEventPreviewData();
	countEventData();

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
function getCookie(cname)
{
  var name = cname + "=";
  var ca = document.cookie.split(';');
  for(var i=0; i<ca.length; i++) 
  {
    var c = ca[i].trim();
    if (c.indexOf(name)==0) return c.substring(name.length,c.length);
  }
  return "";
}
var windowsHeight, _fontSize = 14,

_fontSize1 = 26,
breakData = 8;
legendTop = '30%',
radiusData = [90, 65],
lengthECharts = 30;
//var context = "/arjccm/"
/* var	context = $(".context").attr("content"); */
/*var FontColor="#999",backgroundColor="#fff";
var theme=getCookie('theme'); 
if(theme=="black"){
	FontColor="#fff";
	backgroundColor="#0e2a4c";
}
var color;
if(theme=='gradient'){
 color = [ '#1F8BFA', '#E84442', '#FAB736', '#2CC189', '#F9A388', '#77E7F1', '#9E56E9', '#FF7453', '#16DDD3', '#FDB733'];
}
else{
 color = ['#4573a7', '#aa4644', '#89a54e', '#db843d','#4298af', '#93a9d0', '#b9ce96', '#d09392','#a99bbc', '#92c3d4', '#ffdf5f', '#71588f'];
} */
function countEventPreviewData() {

	$.ajax({
		type: "POST",
		url: ctx+"/eventType/dmaEventTypeCount/countEventPreviewByReportType?beginHappenDate="+ beginHappenDate +"&endHappenDate=" + endHappenDate,
		dataType: "json",
		cache: false,
		async: true,
		success: function (data) {
			showEventPreview(data);
		}
	});
}
function showEventPreview(data){
	// 指定图表的配置项和数据
	var option = {
		title : {
			text: '事件报警量统计',
			subtext: '预处理事件',
			x:'center'
		},
		tooltip : {
			trigger: 'item',
			formatter: "{a} <br/>{b} : {c} ({d}%)"
		},
		legend: {
			orient: 'vertical',
			left: 'left',
			data:['APP录入','短信录入','热线录入','网站录入','机顶盒录入'],
			/*textStyle:{color: '#1F8BFA'}*/
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
				name: '访问来源',
				type: 'pie',
				radius : '55%',
				center: ['50%', '60%'],
				
				data:data,
				itemStyle: {
					emphasis: {
						shadowBlur: 10,
						shadowOffsetX: 0,
						shadowColor: 'rgba(0, 0, 0, 0.5)'
					}
				}
			}
		]
	};

	var Barchart = echarts.init(document.getElementById('eventPreviewEcharts'),'theme');
	Barchart.setOption(option);
}
function countEventData() {
	$.ajax({
		type: "POST",
		url: ctx+"/eventType/dmaEventTypeCount/countEventByReportType?beginHappenDate="+ beginHappenDate +"&endHappenDate=" + endHappenDate,
		dataType: "json",
		cache: false,
		async: true,
		success: function (data) {
			showEvent(data);
		}
	});
}
function showEvent(data){
	var option = {
		title : {
			text: '事件实际发生量统计',
			subtext: '已发生事件',
			x:'center'
		},
		tooltip : {
			trigger: 'item2',
			formatter: "{a} <br/>{b} : {c} ({d}%)"
		},
		legend: {
			orient: 'vertical',
			left: 'left',
			data:['手动录入','APP录入','短信录入','热线录入','网站录入','机顶盒录入']
		},
		series : [
			{
				name:'访问来源',
				type:'pie',
				radius : '55%',
				center: ['50%', '60%'],
			
				data:data,
				itemStyle: {
					emphasis: {
						shadowBlur: 10,
						shadowOffsetX: 0,
						shadowColor: 'rgba(0, 0, 0, 0.5)'
					}
				}
			}
		]
	};
	var Barchart = echarts.init(document.getElementById('eventEcharts'),'theme');
	Barchart.setOption(option);
}
function queryEventInfo(){

	var begin = new Date(beginHappenDate);
	var end = new Date(endHappenDate);
	if(begin.getTime() > end.getTime()){
		messageAlert("开始时间大于结束时间！", "error");
		return false;
	}
	countEventPreviewData(beginHappenDate,endHappenDate);
	countEventData(beginHappenDate,endHappenDate);
}
