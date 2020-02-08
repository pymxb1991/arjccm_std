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
//基础颜色表
var color = [ '#79F0C9','#9CDCDE','#F5D883','#ED817C', '#B0DAB4',  '#2ec7c9','#3398DB','#b6a2de','#5ab1ef','#ffb980','#d87a80',
    '#8d98b3','#97b552','#95706d','#dc69aa',
    '#07a2a4','#9a7fd1','#588dd5','#f5994e','#c05050',
    '#59678c','#c9ab00','#7eb00a','#6f5553','#c14089'];

option = {
		color : color,
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
	        data: ['占用', '闲置','维修保养','使用中','报废'],
	        textStyle:{
	            fontSize:10,
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
	            name: '占用',
	            type: 'bar',
	            stack: '总量',
	            data: ${jsondata0},
	            label: {
	                normal: {
	                   /*  show: true,
	                    position: 'insideRight' */
	                }
	            },
	            itemStyle:{
                    normal:{
                        color:'#4ad2ff'
                    }
                }
	            
	        },
	        {
	            name: '闲置',
	            type: 'bar',
	            stack: '总量',
	            label: {
	                normal: {
	                    /* show: true,
	                    position: 'insideRight' */
	                }
	            },
	            itemStyle:{
                    normal:{
                        color:'#7091C4'
                    }
                },
	            data:${jsondata1},
	        },{
	            name: '维修保养',
	            type: 'bar',
	            stack: '总量',
	            label: {
	                normal: {
	                    /* show: true,
	                    position: 'insideRight' */
	                }
	            },
	            
	            data:${jsondata2},
	        },{
	            name: '使用中',
	            type: 'bar',
	            stack: '总量',
	            label: {
	                normal: {
	                   /*  show: true,
	                    position: 'insideRight' */
	                }
	            },
	            
	            data:${jsondata3},
	        },{
	            name: '报废',
	            type: 'bar',
	            stack: '总量',
	            label: {
	                normal: {
	                   /*  show: true,
	                    position: 'insideRight' */
	                }
	            },
	            
	            data:${jsondata4}
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