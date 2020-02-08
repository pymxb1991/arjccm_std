<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>警情趋势分析图</title>
<meta name="decorator" content="default" />
</head>
<body>
<div class="clearfix" style="width:100%;height:100%">
  <div class="" style="float:left;width:330px;height:100%; border-right: 1px solid #ccc;">
    <div id="AlarmPercent" style="width:300px;height:300px"></div>
  </div>
   <div class="right-conent" style="float:left;height:100%">
     <div id="AlarmCount" style="width:100%;height:90%"></div>
   </div>
</div>

<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script>
$('html,body').css({'height':'100%'});
$('.right-conent').width($(window).width()-350)
var alarmData=[];
<c:forEach items="${typeMap.data}" var="data">
alarmData.push({
	value:'${data.num}', name:'${data.name}',officeId:'${data.officeId}'
})
</c:forEach>
var countData=[],dateData=[],nameArr=[],seriesData=[];

<c:forEach items="${data.dateArray}" var="date">
dateData.push('${date}')
</c:forEach>
<c:forEach items="${data.data}" var="data">
nameArr.push('${data.name}')
seriesData.push({
	data:${data.numList},
	name:'${data.name}',
	type:'line',
})
</c:forEach>

// 基础颜色表
var color = ['#feb155', '#5ec4ea', '#9c7bd4', '#ff6769', '#ffe26c', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];
var PiechartOption = {
	  color:color,
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    series : [
	        {
	            name: '警情类型占比',
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
var Piechart = echarts.init(document.getElementById('AlarmPercent'));
Piechart.on('click', function (params) {
     var officeId=params.data.officeId
     window.location.href="${ctx}/alarm/bphAlarmInfo/statPercentByOffice?officeId="+officeId
});
// 填充数据
Piechart.setOption(PiechartOption);

var FontColor='#fff';
if($.cookie('theme')==undefined){
	FontColor='#fff';
}else if($.cookie('theme')=='gradient'){
	FontColor='#000';
}else if($.cookie('theme')=='black'){
	FontColor='#fff';
}

var CountChart = echarts.init(document.getElementById('AlarmCount'));
var CountOption = {
		color:color,
	    grid: [
	    { x: '7%', y: '10%', width: '80%', height: '80%' },
	    ],
	    tooltip: {
	        trigger: 'axis'//item  axis  
	    },
	    legend: {
	        data:nameArr,
	        right: 15,
	        top:40,
	        itemGap: 20,
	        orient: 'vertical',
       	   textStyle: {
  	            color: FontColor
  	        }
	    },
	    toolbox: {
	        show: true,
	        feature: {
	            mark: { show: true },
	            dataView: { show: false, readOnly: false },
	            magicType: { show: true, type: ['line', 'bar'] },
	        }
	    },
	    calculable: true,
	    xAxis: [
	    {
	        type: 'category',
	        boundaryGap: true,
	        data: dateData,
	        axisLabel: {
                textStyle: {
                    color: FontColor
                }
            },
	       axisLine:{  
            lineStyle:{  
                color:FontColor,  
            }  
        } 
	    }
	    ],
	    yAxis: [   
	    {
	        type: 'value',
	        name: '',        
	        axisLabel: {
                textStyle: {
                    color: FontColor
                }
            },
	       axisLine:{  
            lineStyle:{  
                color:FontColor,  
            }  
        } 
	    }
	    ],
	    series: seriesData
};
CountChart.setOption(CountOption);
</script>
</body>
</html>