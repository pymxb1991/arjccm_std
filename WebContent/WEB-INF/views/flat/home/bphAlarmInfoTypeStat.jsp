<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>警情趋势分析图</title>
</head>
<body>
<div id="alarmTime" style="width:100%;height:100%"></div>
<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script>
$('html,body').css({'height':'100%'});
var FontColor='#fff';
if($.cookie('theme')==undefined){
    FontColor='#000';
}else if($.cookie('theme')=='gradient'){
    FontColor='#000';
}else if($.cookie('theme')=='black'){
    FontColor='#fff';
}
$.getJSON('${ctx}/alarm/bphAlarmInfo/statCountByAlarmType',function(data){
	init(data);
})
function getBeforeDate(n){//n为参数，当前为0，前一天为-1，后一天为1
    var date = new Date() ;
    var year,month,day ;
    date.setDate(date.getDate()+n);
    year = date.getFullYear(); //当前年份
    month = date.getMonth()+1; //当前月份
    day = date.getDate() ; //几号
   // s = year + '-' + ( month < 10 ? ( '0' + month ) : month ) + '-' + ( day < 10 ? ( '0' + day ) : day) ;
    s = ( month < 10 ? ( '0' + month ) : month ) + '-' + ( day < 10 ? ( '0' + day ) : day) ;
    return s ;
  }
function init(data){

var Data=data.data;
var typeData=data.typeData;
var len=Data.length;
var dateData=[];
var seriesData=[];
if(len>0){
    for(var i=0;i<len;i++){
        dateData.unshift(getBeforeDate(-(Number(i)+1)))
        seriesData.push( {
            name: Data[i].name,
            type: 'line',
            data: Data[i].nums.split(','),
        })
    }
} else{
    var len= typeData.length;
    if(len>0){
        for(var i=0;i<len;i++){
            dateData.unshift(getBeforeDate(-(Number(i)+1)))
            seriesData.push( {
                name: typeData[i],
                type: 'line',
                data:0,
            })
        }
    }
}


// 基础颜色表
var color = ['#feb155', '#5ec4ea', '#9c7bd4', '#ff6769', '#ffe26c', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];
var myChart = echarts.init(document.getElementById('alarmTime'));
var options = {
		color:color,
	    grid: [{
	    	left:"5%",                      //组件离容器左侧的距离,百分比字符串或整型数字
	        top:'20%',
	    	width: '85%',
	    	height: '70%' },
	    ],
	    tooltip: {
	        trigger: 'axis'//item  axis
	    },
	    legend: {
/*	        type: 'scroll',
	        orient: 'vertical',
	        x: 'left', // 'right' | 'left' | {number},
	        y: 'top', // 'center'*/
	        textStyle: {
	            color: FontColor
	        },
	        data:typeData
	    },
	    toolbox: {
	        show: true,
            right: '5%',
            top:'10%',
/*	        x:'right',
	        y:'top',*/
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
            }
	    }
	    ],
	    series: seriesData
	};
	myChart.setOption(options);
}
</script>
</body>
</html>