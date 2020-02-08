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
	        data: ['全部数量', '空闲数量'],
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
	        data: ${jsonY},
	        axisLabel: {
                show: true,
                textStyle: {
                    color: '#fff'
                }
            }
	    },
	    series: [{
	            name: '全部数量',
	            type: 'bar',
	            stack: '总量',
	            data: ${jsondata2},
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
	            name: '空闲数量',
	            type: 'bar',
	           
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
	            data:${jsondata3},
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