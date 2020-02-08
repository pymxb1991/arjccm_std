<%@ page contentType="text/html;charset=UTF-8"%>
<html style="height: 100%"> 
   <head>
       <meta charset="utf-8">
   </head>
   <body style="height: 90%; margin: 0">
       <div id="container1${porid}" class="container6" style="height:85% ;width: 50%; float: left;"></div>
       <div id="container2${porid}" class="container6" style="height:85% ;width: 50%;float: right;"></div>
       <script type="text/javascript">
var dom = document.getElementById("container1${porid}");
var myChart = echarts.init(dom);

option = null;

 var dom2 = document.getElementById("container2${porid}");
var myChart2 = echarts.init(dom2);

option2 = null; 


//基础颜色表
var color = [ '#79F0C9','#9CDCDE','#F5D883','#ED817C', '#B0DAB4',  '#2ec7c9','#3398DB','#b6a2de','#5ab1ef','#ffb980','#d87a80',
    '#8d98b3','#97b552','#95706d','#dc69aa',
    '#07a2a4','#9a7fd1','#588dd5','#f5994e','#c05050',
    '#59678c','#c9ab00','#7eb00a','#6f5553','#c14089'];
    
function getCookie(cname)
{
  var name = cname + "=";
  var ca = document.cookie.split(';');
  for(var i=0; i<ca.length; i++) 
  {
    var c = ca[i].trim();
    if (c.indexOf(name)==0) return c.substring(name.length,c.length);
  }
  return "";
}

    
var color1;
if(getCookie("theme")=="black"||!getCookie("theme")){
	$('.container-fluid').css("background-color",getCookie("theme"));
	color1="#fff"
}else if(getCookie("theme")=="gradient"){
	    	$('.container-fluid').css("background-color",getCookie("theme"));
	    	color1="#000"}

option = {
		color : color,
	    title : {
	    	
	    		textStyle: {
	                fontWeight: 400,
	                fontSize: 16,
	                color1
	            },
	            text: '各接待室使用次数',
		        x:'center'
		       
	        
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	    	orient: 'horizontal',
	        y: 'bottom',
	        data:$.ToConvertLegendA(${jsondata1}),
	        textStyle:{
	            fontSize:10,
	            color1
	        }
	    },
	    series : [
	        {
	            name: '接待室使用次数',
	            type: 'pie',
	            radius: ['45%', '65%'],
	            avoidLabelOverlap: false,
	            center: ['50%', '40%'],
	           
	            data:$.ToConvertA(${jsondata1}),
	            label: {
	                normal: {
	                    show: false,
	                    position: 'center'
	                },
	                emphasis: {
	                    show: true,
	                    textStyle: {
	                        fontSize: '16',
	                        fontWeight: 'bold'
	                    }
	                }
	            },
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            }
	        }
	       
	    ]
	};
	
option2 = {
		color : color,
	    title : {
	    	textStyle: {
                fontWeight: 400,
                fontSize: 16,
                color1
            },
            text: '各会议室使用次数',
	        x:'center',
	       
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	    	orient: 'horizontal',
	        y: 'bottom',
	        data:$.ToConvertLegendA(${jsondata2}),
	        textStyle:{
	            fontSize:10,
	            color1
	        }
	    },
	    series : [	       
	        {
	            name: '会议室使用次数',
	            type: 'pie',
	            radius: ['45%', '65%'],
	            avoidLabelOverlap: false,
	            center: ['50%', '40%'],
	        
	            data:$.ToConvertA(${jsondata2}),
	            label: {
	                normal: {
	                    show: false,
	                    position: 'center'
	                },
	                emphasis: {
	                    show: true,
	                    textStyle: {
	                        fontSize: '16',
	                        fontWeight: 'bold'
	                    }
	                }
	            },
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            }
	        }
	    ]
	};
if (option && typeof option === "object") {
    myChart.setOption(option, true);
} 
if (option2 && typeof option2 === "object") {
    myChart2.setOption(option2, true);
}
       </script>
   </body>
</html>