<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>本月事件发生前十——社区/街道/近一年重点人员帮扶/事件发生趋势图</title>
	<style>
	.common-pading{
	  width:100%;
	  height:200px;
	  padding:5px;
	}
	.echarts{
	  width:100%;
	  height:100%;
	}
	.event-name{
	color:#fff;
	}
	
	</style>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<link href="${ctxStatic}/bootstrap/2.3.1/css_${not empty cookie.theme.value ? cookie.theme.value : 'black'}/custom.css" type="text/css" rel="stylesheet" />
</head>
<body style="overflow: hidden">
	<div class="context" content="${ctx}"></div>
	<sys:message content="${message}"/>
	    <br>
	    <div class="row-fluid">
		      <div class="span12" ><h4 class="event-name">本月事件发生TOP10</h4>
		         <div class="common-pading">
					<div id="findEventMonthMap" class="echarts" ></div>
				 </div>
		      </div>
		    
	    </div>
	    <br>
	    <div class="row-fluid">
		      <div class="span12" ><h4 class="event-name">近一年重点人员帮扶/事件发生趋势图</h4>
		         <div class="common-pading">
					<div id="findEventYearMap" class="echarts" ></div>
				 </div>
		      </div>
		    
	    </div>
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
<script src="${ctxStatic}/jquery/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script>
	var color = ['#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f','#aa4644'];
	//基础参数
	var windowsHeight, _fontSize = 14,

	_fontSize1 = 26,
	breakData = 8;
	legendTop = '30%',
	radiusData = [90, 65],
	lengthECharts = 30;
	var context = $(".context").attr("content");
	var FontColor="#999",backgroundColor="#fff";
	var theme=$.cookie('theme');
	if(theme=="black"){
		FontColor="#fff";
		backgroundColor="#0e2a4c";
	}
	$(function(){
	    windowsHeight= $(window).width();

	    if (windowsHeight >= 1600) {
	    	
	        _fontSize = 14;
	        legendTop = '15%';
	        radiusData = [90, 65];
	        lengthECharts = 20;
	        _fontSize1 = 26;
	        breakData = 8;
	        legendRight="8%"
	    }else {
	    	
	        _fontSize = 12;
	        legendTop = '15%';
	        radiusData = [60, 45];
	        lengthECharts = 5;
	        _fontSize1 = 12;
	        breakData = 6;
	        legendRight="5%"
	    }

	    
	    //本月事件发生前十
	    $.getJSON(context + "/report/ccmIncidentBegin/findEventMonthMap", function(
				data) {
			var dataX = data["1"];
			var dataY = data["2"];
			// 社区事务
			$.GetChangeMonthSheets("findEventMonthMap", dataX, dataY);

		});
	  	//近一年重点人员帮扶/事件发生趋势图
	    $.getJSON(context + "/report/ccmIncidentBegin/findEventYearMap",function(
	    		data) {
					// 填充数据
	    	$.GetChangeYearSheets("findEventYearMap", data);
		});
		
	})	
	
	
	
	// 饼图pingJson况
	$.ToConvertA = function(object) {
        var ajaxData = new Array();
        for (var one in object) {
            ajaxData.push({
                "name": object[one]["type"],
                "value": Number(object[one]["value"])
            });
        }
        return ajaxData;
    } 
	
	//柱状图本月事件发生前十
	$.GetChangeMonthSheets= function(model,ajaxDataX,ajaxDataY) {
        var option = {
                tooltip: {
                    show: "true",
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    },
                    formatter: "{b} :<br/> {c}件",
    		        confine:true
                },
                grid: {
                    left: '3%',
                    right: '3%',
                    bottom: '5%',
                    top: '15%',
                    containLabel: true
                },
                xAxis: [{
                    type: 'category',
                    data: ajaxDataX,
                    axisPointer: {
                        type: 'shadow'
                    },
                    axisLine: {
                        lineStyle: {
                            color: FontColor
                        }
                    },
                    axisLabel: {
                        textStyle: {
                            color: FontColor,
                            fontSize: _fontSize
                        }
                    },
                }],
                yAxis: [{
                    type: 'value',
                    min: 0,
                    axisLabel: {
                        formatter: '{value} ',
                        textStyle: {
                            color: FontColor,
                            fontSize: _fontSize
                        }
                    },
                    axisLine: {
                        lineStyle: {
                            color: FontColor
                        }
                    },
                    splitLine: {
                        show: false
                    }
                }],
                series: [{
                    name: '',
                    type: 'bar',
                    data: ajaxDataY,
                    barWidth: 23,
                    itemStyle: {
                        normal: {
                            color: '#89a54e'
                        }
                    }
                },
                ]
            };
            var Barchart = echarts.init(document.getElementById(model));
            Barchart.setOption(option);
        }
	
	//折线图  近一年事件发生/重点人员帮扶趋势图
	$.GetChangeYearSheets=function(model,data){
		   var type=[];
		   var value1=[];
		   var value2=[];
		   for(var i=0;i<data.length;i++){ 
			   type.push(data[i]['type']);
			   value1.push(data[i]['value1']);
			   value2.push(data[i]['value2']);
			}
		   
		   //console.log(data["type"])
		   option = {

				    tooltip: {
				        trigger: 'axis',
				        confine:true
				    },
				    color:color,
			   grid: {
				        left: '3%',
				        right: '3%',
				        bottom: '5%',
				        top:'3%',
				        containLabel: true
				    },
				    xAxis: {
				        type: 'category',
				        boundaryGap: false,
				        //data: ['周一','周二','周三','周四','周五','周六','周日'],\
				        data: type,
				        axisLine: {
		                    lineStyle: {
		                        color: FontColor
		                    }
		                },
				    },
				    yAxis: {
				        type: 'value',
				        axisLine: {
		                    lineStyle: {
		                        color: FontColor
		                    }
		                },
		                splitLine: {
		                    show: false
		                }
				    },
				    series: [
				        {
				            name:'重点人员事件发生次数',
				            type:'line',
				            //data:[120, 132, 101, 134, 90, 230, 210]
				            data:value1,
				            smooth: true
				        },
				        {
				            name:'重点人员帮扶次数',
				            type:'line',
				            data:value2,
				            smooth: true
				        }
				        
				    ]
				};

		    var Barchart = echarts.init(document.getElementById(model));
	        Barchart.setOption(option);
		}
	 
		
</script>
</body>
</html>