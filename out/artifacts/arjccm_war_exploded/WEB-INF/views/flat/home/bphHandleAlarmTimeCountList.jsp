<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>处警时间统计</title>
<meta name="decorator" content="default" />
</head>
<body>
<div id="alarmTime" style="width:100%;height:100%"></div>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script>
$('html,body').css({'height':'100%'});
function getBeforeDate(n){//n为参数，当前为0，前一天为-1，后一天为1
    var date = new Date() ;
    var year,month,day ;
    date.setDate(date.getDate()+n);
    year = date.getFullYear();
    month = date.getMonth()+1;
    day = date.getDate() ;
    s = year + '-' + ( month < 10 ? ( '0' + month ) : month ) + '-' + ( day < 10 ? ( '0' + day ) : day) ;
    return s ;
  }
  //获取当前七天日期
 var dateTime=[];
  for(var i=0;i<7;i++){
	  dateTime.unshift(getBeforeDate(-i))
  }
var data=${jsonData};
var len=data.length;
var lenData=[];
var seriesData=[];
if(len>0){
	for(var j=0;j<len;j++){
		lenData.push(data[j].officeName);
		seriesData.push( {
	        name: data[j].officeName,
	        type: 'line',
	        data: [data[j].VALUE6, data[j].VALUE5, data[j].VALUE4, data[j].VALUE3, data[j].VALUE2, data[j].VALUE1, data[j].VALUE0],
	    })
	}
}
var FontColor='#fff';
if($.cookie('theme')==undefined){
	FontColor='#fff';
}else if($.cookie('theme')=='gradient'){
	FontColor='#000';
}else if($.cookie('theme')=='black'){
	FontColor='#fff';
}
var myChart = echarts.init(document.getElementById('alarmTime'));
var options = {
	    grid: [
	    { x: '5%', y: '10%', width: '75%', height: '70%' },
	    ],
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
	        boundaryGap: false,
	        data: dateTime
	    }
	    ],
	    yAxis: [   
	    {
	        type: 'value',
	        name: '',        

	    }
	    ],
	    series: seriesData
	};
	myChart.setOption(options);

</script>
</body>
</html>