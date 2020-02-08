<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>警情数量对比图</title>
</head>
<body>
<div id="sameDayContrastStat" style="width:100%;height:100%"></div>
<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script>
$('html,body').css({'height':'100%'});
$.getJSON('${ctx}/alarm/bphAlarmInfo/sameDayContrastStat',function(data){
	initEchartsData(data);
})
var FontColor='#fff';
if($.cookie('theme')==undefined){
	FontColor='#fff';
}else if($.cookie('theme')=='gradient'){
	FontColor='#000';
}else if($.cookie('theme')=='black'){
	FontColor='#fff';
}
function initEchartsData(data){
	var lastMonthData=data.lastMonth;
	var thisMonthData=data.thisMonth;
	var lastMonthDataLen=lastMonthData.length;
	var thisMonthDataLen=thisMonthData.length;
	
	var len=data.length;
	var lenData=['上月','本月'];
	var xData=[];
	var seriesData=[];
	var seriesthisMonthData=[];
	var serieslastMonthData=[];
	 
	if(thisMonthDataLen>0){
		for(var i=0;i<thisMonthDataLen;i++){
			seriesthisMonthData.push(thisMonthData[i].num);
			if(lastMonthData[i]!=undefined){
				serieslastMonthData.push(lastMonthData[i].num);
			}else{
				serieslastMonthData.push(0);
			}
			var dateTime=thisMonthData[i].dateTime;
			dateTime=dateTime.substring(0,10);
			xData.push(dateTime);
		}
		seriesData.push({
			name:'上月',
			type: 'bar',
			data:serieslastMonthData
		});
		seriesData.push({
			name:'本月',
			type: 'bar',
			data:seriesthisMonthData
		});
	}
	// 基础颜色表
	var color = ['#feb155', '#5ec4ea', '#9c7bd4', '#ff6769', '#ffe26c', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];
	var myChart = echarts.init(document.getElementById('sameDayContrastStat'));
	var options = {
		color:color,
		grid: [{ 
			x: '7%', 
			y: '10%', 
			width: '80%', 
			height: '70%' 
		}],
		tooltip: {
			trigger: 'axis'//item  axis  
		},
		legend: {
			data:lenData,
			right: 15,
			top:50,
			itemGap: 25,
			orient: 'vertical',
			textStyle: {
				color: FontColor
			}
		},
		toolbox: {
			show: true,
            right: '10%',
			feature: {
				mark: { 
					show: true 
				},
				dataView: { 
					show: false, 
					readOnly: false 
				},
				magicType: { 
					show: true,
					type: ['line', 'bar'] 
				},
			}
		},
		calculable: true,
		xAxis: [{
			type: 'category',
			boundaryGap: true,
			axisLabel: {
                textStyle: {
                    color: FontColor
                }
            },
	        axisLine:{
            	lineStyle:{
                	color:FontColor,
            	}
            },
			data: xData
		}],
		yAxis: [{
			type: 'value',
			minInterval: 1,
			min: function(value) {
				if(value.min < 5){
					return 5;
				}else{					
			    	return value.min;
				}
			},
			axisLabel: {
                textStyle: {
                    color: FontColor
                }
            },
	        axisLine:{
            	lineStyle:{
                	color:FontColor,
            	}
            },
			name: '',        
		}],
		series: seriesData
	};
	myChart.setOption(options);
}
</script>
</body>
</html>