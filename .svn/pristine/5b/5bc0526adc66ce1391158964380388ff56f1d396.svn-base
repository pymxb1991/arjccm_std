<%@ page contentType="text/html;charset=UTF-8"%>
<html style="height: 100%">
<head>
<meta charset="utf-8">
</head>
<body style="height: 90%; margin: 0">
	<div id="container${porid}" class="container6" style="height: 85%"></div>

	<script type="text/javascript">
		var dom = document.getElementById("container${porid}");
		var myChart = echarts.init(dom);
		var app = {};
		option = null;

		var labelOption = {};
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
			tooltip : {
				trigger : 'axis',
				axisPointer : {
					type : 'shadow'
				}
			},
			legend : {
				data : [ '费用', '用车次数', '违章次数', '事故次数' ],
				textStyle: {
		        	color1,
		        	fontSize:10
		        }
			},
			calculable : true,
			xAxis : [ {
				type : 'category',
				axisTick : {
					show : false
				},
				data : $.ToConvertLegendA(${jsondataUseAndVio}), 
				axisLabel: {
	                formatter: '{value} 月',
	                textStyle: {
		                color1,
		               fontSize:12
		            }
	            }
			} ],
			yAxis : [ 
				{
					type : 'value',
					name :'次数',
					axisLabel: {
				        textStyle: {
				        	color1,
				        		fontSize:12
				        }
			        },
			        nameTextStyle: {
			        	color1
					}
					
				},
				{
					type : 'value',
					name :'费用',
					axisLabel: {
				        textStyle: {
				        	color1
				        }
			        },
					nameTextStyle: {
			        	color1
					}
				}
			],
			series : [ {
				name : '费用',
				type : 'bar',
				barGap : 0,
				label : labelOption,
				yAxisIndex: 1,
				data : $.ToConvertSeriesC(${jsondataDamagedAndDis}),
				
			}, {
				name : '用车次数',
				type : 'bar',
				label : labelOption,
				data : $.ToConvertSeriesA(${jsondataUseAndVio}),
			}, {
				name : '违章次数',
				type : 'bar',
				label : labelOption,
				data : $.ToConvertSeriesC(${jsondataUseAndVio}),
			}, {
				name : '事故次数',
				type : 'bar',
				label : labelOption,
				data : $.ToConvertSeriesA(${jsondataDamagedAndDis}),
			} ]
		};

		if (option && typeof option === "object") {
			myChart.setOption(option, true);
		}
	</script>
</body>
</html>