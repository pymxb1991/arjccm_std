<%@ page contentType="text/html;charset=UTF-8"%>
<html style="height: 100%"> 
   <head>
       <meta charset="utf-8">
   </head>
   <body style="height: 90%; margin: 0">
       <div id="container${porid}" class="container5" style="height:85%"></div>
      
       <script type="text/javascript">
var dom = document.getElementById("container${porid}");
var myChart = echarts.init(dom);
var app = {};
option = null;


option = {
	    title: {
	        text: ''
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['出勤次数','违章、故障次数'],
	        textStyle:{
	            fontSize:15,
	            color:'#fff'
	        }
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    toolbox: {
	        feature: {
	            saveAsImage: {}
	        }
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: true,
	        data: ['刘华','张建军','李山','周大勇','王贺良','刘天明','赵宝强'],
	        axisLabel: {
                show: true,
                textStyle: {
                    color: '#fff'
                }
            }
	    },
	    yAxis: {
	        type: 'value',
	        axisLabel: {
                show: true,
                textStyle: {
                    color: '#fff'
                }
            }
	    },
	    series: [
	        {
	            name:'出勤次数',
	            type:'line',
	            stack: '总量',
	            data:[520, 432, 601,534, 380, 680, 600]
	        },
	        {
	            name:'违章、故障次数',
	            type:'line',
	            stack: '总量',
	            data:[110, 82, 71,84, 98, 129, 112]
	        }
	    ]
	};

;
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}
       </script>
   </body>
</html>