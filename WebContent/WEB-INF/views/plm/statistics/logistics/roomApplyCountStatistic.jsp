

<%@ page contentType="text/html;charset=UTF-8"%>
<html style="height: 100%"> 
   <head>
       <meta charset="utf-8">
   </head>
   <body style="height: 100%; margin: 0">
       <div id="container${porid}" class="container3" style="height: 85%"></div>
      
       <script type="text/javascript">
       
       var dom = document.getElementById("container${porid}");
       var myChart = echarts.init(dom);
       var app = {};
       option = null;
    // 基础颜色表
       var color = [ '#79F0C9','#9CDCDE','#F5D883','#ED817C', '#B0DAB4',  '#2ec7c9','#3398DB','#b6a2de','#5ab1ef','#ffb980','#d87a80',
           '#8d98b3','#97b552','#95706d','#dc69aa',
           '#07a2a4','#9a7fd1','#588dd5','#f5994e','#c05050',
           '#59678c','#c9ab00','#7eb00a','#6f5553','#c14089'];

       option = {
       		color : color,
    title: {
        text: '',
        textStyle:{
            fontSize:10,
            color:'#fff'
        }
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
    	
        
        data:['接待次数','会议次数'],
        textStyle:{
            fontSize:15,
            color:'#fff'
        }
    },
    toolbox: {
        feature: {
            saveAsImage: {}
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : ${jsonmonth},
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#fff'
                }
            }
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#fff'
                }
            }
        }
    ],
    series : [
    	 {
             name:'接待次数',
             type:'line',
           
             data:${jsondata2}
         },
        {
            name:'会议次数',
            type:'line',
           
            data:${jsondata3}
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