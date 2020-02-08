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
	        text: '',
	        textStyle:{
	            fontSize:15,
	            color:'#fff'
	        }
	        
	    },
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'shadow'
	        }
	    },	    
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis: {
	        type: 'value',
	        boundaryGap: [0, 0.01],
	        axisLabel: {
                show: true,
                textStyle: {
                    color: '#fff'
                }
            }
	    },
	    yAxis: {
	        type: 'category',
	        data: ['执法记录仪','对讲机','警棍','防爆盾牌','警服'],
	        axisLabel: {
                show: true,
                textStyle: {
                    color: '#fff'
                }
            }
	    },
	    series: [
	        {
	            name: '库存预警',
	            type: 'bar',
	            data: [20, 50,45,30, 20]
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