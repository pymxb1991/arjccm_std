<%@ page contentType="text/html;charset=UTF-8"%>
<html style="height: 100%"> 
   <head>
       <meta charset="utf-8">
   </head>
   <body style="height: 90%; margin: 0">
       <div id="container${porid}" class="container6" style="height:85%"></div>
      
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
	     title: {
	       /*  text: '使用次数',
	        x: 'center',
	        y: 'center',
	        textStyle: {
	            fontWeight: 'normal',
	            fontSize: 16,
	            color:'#fff'
	        } */
	    },
	    tooltip: {
	        trigger: 'item',
	        formatter: "{a} <br/>{b}: {c} ({d}%)"
	    },
	    
	    legend: {
	         orient: 'horizontal',
	        y: 'bottom',
	        data:$.ToConvertLegendA(${jsondataUse}),
	        textStyle:{
	            fontSize:10,
	            color1
	        }
	    },
	    series: [
	        {
	            name:'使用次数',
	            type:'pie',
	              radius: ['20%', '40%'],
	            center: ['85%', '40%'],
	          
	            avoidLabelOverlap: true,
	             label: {
	            normal: {
	                
	                position: 'inner',
	                // formatter: '{d}%',
	                formatter: function(param) {
	                    if (!param.percent) return ''
	                    var f = Math.round(param.percent * 10) / 10;
	                    var s = f.toString();
	                    var rs = s.indexOf('.');
	                    if (rs < 0) {
	                        rs = s.length;
	                        s += '.';
	                    }
	                    while (s.length <= rs + 1) {
	                        s += '0';
	                    } if(s<5){ return '';}
	                    return s + '%';
	                },
	                textStyle: {
	                    color1,
	                    fontSize: 10
	                }
	            },
	            emphasis: {
	                    show: true,
	                    textStyle: {
	                        fontSize: '15',
	                        fontWeight: 'bold'
	                    }
	                }
	        },
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data:$.ToConvertA(${jsondataUse}),
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        },
	         {
	            name:'总里程',
	            type:'pie',
	            radius: ['20%', '40%'],
	            center: ['50%', '40%'],
	          
	            avoidLabelOverlap: true,
	             label: {
	            normal: {
	                
	                position: 'inner',
	                // formatter: '{d}%',
	                formatter: function(param) {
	                    if (!param.percent) return ''
	                    var f = Math.round(param.percent * 10) / 10;
	                    var s = f.toString();
	                    var rs = s.indexOf('.');
	                    if (rs < 0) {
	                        rs = s.length;
	                        s += '.';
	                    }
	                    while (s.length <= rs + 1) {
	                        s += '0';
	                    } if(s<5){ return '';}
	                    return s + '%';
	                },
	                textStyle: {
	                    color1,
	                    fontSize: 10
	                }
	            },
	            emphasis: {
	                    show: true,
	                    textStyle: {
	                        fontSize: '15',
	                        fontWeight: 'bold'
	                    }
	                }
	        },
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data:$.ToConvertA(${jsondataDistance}),
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        },
	         {
	            name:'总费用',
	            type:'pie',
	           radius: ['20%', '40%'],
	            center: ['15%', '40%'],
	          
	            avoidLabelOverlap: true,
	             label: {
	            normal: {
	                
	                position: 'inner',
	                // formatter: '{d}%',
	                formatter: function(param) {
	                    if (!param.percent) return ''
	                    var f = Math.round(param.percent * 10) / 10;
	                    var s = f.toString();
	                    var rs = s.indexOf('.');
	                    if (rs < 0) {
	                        rs = s.length;
	                        s += '.';
	                    }
	                    while (s.length <= rs + 1) {
	                        s += '0';
	                    }
	                    if(s<5){ return '';}
	                    return s + '%';
	                },
	                textStyle: {
	                    color1,
	                    fontSize: 10
	                }
	            },
	            emphasis: {
	                    show: true,
	                    textStyle: {
	                        fontSize: '15',
	                        fontWeight: 'bold'
	                    }
	                }
	        },
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data:$.ToConvertA(${jsondataSpend}),
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};

	
if (option && typeof option === "object") {
    myChart.setOption(option, true);
} 
       </script>
   </body>
</html>