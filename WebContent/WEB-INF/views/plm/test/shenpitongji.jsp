<%@ page contentType="text/html;charset=UTF-8"%>
<html style="height: 100%"> 
   <head>
       <meta charset="utf-8">
       <style type="text/css">
       .common-pading .results div{
       
          text-align: center;
       }
       
       
       </style>
   </head>
   <body style="height: 90%; margin: 0">
     
   <div class="common-pading">
   <br><br><br>
						<div class="row-fluid results">
						
						
							<div class="span3">
								<b class="common-color" id="CameraTotal">50</b><br> <br>&nbsp;<span style='font-size:16px'>车辆使用申请</span>
							</div>
							<div class="span3">
								<b class="common-color" id="OnLineRate">164</b><br> <br>&nbsp;<span style='font-size:16px'>装备申请</span>
							</div>
							<div class="span3">
								<b class="common-color" id="OkRate1">64</b><br> <br>&nbsp;<span style='font-size:16px'>采购申请</span>
							</div>
							
							<div class="span3">
								<b class="common-color" id="OkRate4">112</b><br> <br>&nbsp;<span style='font-size:16px'>其他申请</span>
							</div>
						</div>
						
						<div style=" height: 60%;">
						<div id="ChuEcharts" class="echarts" ></div>
						</div>
					</div>
   
   
   
     <%--   <div id="container${porid}" class="container5" style="height:85%"></div> --%>
      
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