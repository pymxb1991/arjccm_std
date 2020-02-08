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
	    legend: {
	        data: ['可用数量', '故障数量'],
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
	        data: ['执法记录仪','作战靴','八件套','电击棒','对讲机','警棍','强光手电','水壶','防爆盾牌','警服','抓捕器','装备包','手铐'],
	        axisLabel: {
                show: true,
                textStyle: {
                    color: '#fff'
                }
            }
	    },
	    series: [
	        {
	            name: '可用数量',
	            type: 'bar',
	            stack: '总量',
	            data: [50, 40,45,80, 120, 150, 220, 160, 80, 95,110, 80, 185],
	            label: {
	                normal: {
	                    show: true,
	                    position: 'insideRight'
	                }
	            },
	            itemStyle:{
                    normal:{
                        color:'#4ad2ff'
                    }
                }
	            
	        },
	        {
	            name: '故障数量',
	            type: 'bar',
	            stack: '总量',
	            label: {
	                normal: {
	                    show: true,
	                    position: 'insideRight'
	                }
	            },
	            itemStyle:{
                    normal:{
                        color:'#7091C4'
                    }
                },
	            data: [5, 4,5,6, 10, 6,15, 5, 3, 6,8, 10, 20],
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