<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>按指定派出所分类型统计警情总数</title>
<meta name="decorator" content="default" />
</head>
<body>
<div class="clearfix" style="width:100%;height:100%">
  <div class="" style="float:left;width:330px;height:100%; border-right: 1px solid #ccc;">
    <div id="officeStatAlarm" style="width:300px;height:300px"></div>
  </div>
   <div class="right-conent" style="float:left;height:100%">
     <div id="AlarmType" style="width:100%;height:100%"></div>
   </div>
</div>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script>
$('html,body').css({'height':'100%'});
$('.right-conent').width($(window).width()-350)
var officeStat=[];
<c:forEach items="${officeStat.data}" var="data">
officeStat.push({
	name:'${data.officeName}', value:'${data.num}',officeId:'${data.officeId}'
})
</c:forEach>
var alarmData=[],legendName=[];
<c:forEach items="${typeMap.data}" var="data">
alarmData.push({
	value:'${data.num}', name:'${data.name}',officeId:'${data.officeId}'
})
legendName.push("${data.name}");
</c:forEach>
// 基础颜色表
var FontColor='#fff';
if($.cookie('theme')==undefined){
	FontColor='#fff';
}else if($.cookie('theme')=='gradient'){
	FontColor='#000';
}else if($.cookie('theme')=='black'){
	FontColor='#fff';
}
var color = ['#feb155', '#5ec4ea', '#9c7bd4', '#ff6769', '#ffe26c', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];
var option = {
		  color:color,
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        data:legendName,
		        right: 15,
		        top:40,
		        itemGap: 20,
		        orient: 'vertical',
		        textStyle: {
		            color: FontColor
		        }
		    },
		    series : [
		        {
		            name: '警情数量',
		            type: 'pie',
		            radius : '60%',
		            center: ['50%', '50%'],
		            data:alarmData,
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
	   // 实例化 对象
	var Piechart = echarts.init(document.getElementById('AlarmType'));
	Piechart.on('click', function (params) {
	     var officeId=params.data.officeId
	     window.location.href="${ctx}/alarm/bphAlarmInfo/statCountByOffice?officeId="+officeId
	});
	// 填充数据
Piechart.setOption(option);

var TodayOption = {
		  color:color,
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    series : [
		        {
		            name: '各区域警情数量',
		            type: 'pie',
		            radius : '60%',
		            center: ['50%', '50%'],
		            data:officeStat,
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
	   // 实例化 对象
var TodayPiechart = echarts.init(document.getElementById('officeStatAlarm'));
TodayPiechart.on('click', function (params) {
   var typeCode=params.data.typeCode;
   window.location.href="${ctx}/alarm/bphAlarmInfo/statCountGroupOffice";
});
//填充数据
TodayPiechart.setOption(TodayOption);




</script>
</body>
</html>