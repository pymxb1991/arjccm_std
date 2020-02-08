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
	var lastMonthData=data.lastMonthNum;
	var thisMonthData=data.thisMonthNum;
	var xData=data.dateNum;

	var lenData=['上月','本月'];

	// 基础颜色表
	var color = ['#feb155', '#5ec4ea', '#9c7bd4', '#ff6769', '#ffe26c', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];

	var option = {
        color:color,
        grid: [{
            x: '7%',
            y: '10%',
            width: '90%',
            height: '80%',
            top: '50px'
        }],
        tooltip : {
            trigger: 'axis',
        },
        legend: {
            data:['上月','本月'],
            textStyle: {
                color: FontColor
            }
        },
        toolbox: {
            show : true,
            feature : {
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
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
                data : xData
            }
        ],
        yAxis : [
            {
                type: 'value',
                minInterval: 1,
                axisLabel: {
                    textStyle: {
                        color: FontColor
                    }
                },
                axisLine: {
                    lineStyle: {
                        color: FontColor,
                    }
                },
                name: ''
            }
],
        series : [
            {
                name:'上月',
                type:'bar',
                data:lastMonthData

            },
            {
                name:'本月',
                type:'bar',
                data:thisMonthData
            }
        ]
    };

    var Barchart = echarts.init(document.getElementById('sameDayContrastStat'));
    Barchart.setOption(option);
}
</script>
</body>
</html>