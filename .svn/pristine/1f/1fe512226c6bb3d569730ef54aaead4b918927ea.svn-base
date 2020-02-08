<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>风险事件数据分析</title>
	<meta name="decorator" content="default"/>
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
	
	
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmit").on("click" ,function(){
				$("#searchForm").submit();
			})
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/report/riskIncident/map">风险事件数据分析</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="riskIncident" action="${ctx}/report/riskIncident/map" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>提出时间：</label>
				<input name="beginPutTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${riskIncident.beginPutTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endPutTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${riskIncident.endPutTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li> -->
				<li class="btns">
			<a href="javascript:;" id="btnSubmit" class="btn btn-primary">
                <i class="icon-search"></i> 查询 </a>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	    <br>
	    <div class="row-fluid">
			<div class="span6" >
				<h4>风险事件重要程度统计</h4>
				<div class="common-pading common-pading1" style="height:350px">
					<div id="riskIncidentImportance" class="echarts" style="height: 240px; margin-top: 50px;" ></div>
				</div>
			</div>
		    <div class="span6" >
				<h4>风险事件紧急程度统计</h4>
				<div class="common-pading common-pading1" style="height:350px">
					<div id="riskIncidentUrgency" class="echarts"  style="height: 240px; margin-top: 50px;"></div>
				</div>
			</div>
		  
		    
	    </div>
	    <br>
	    <div class="row-fluid">
		  <div class="span6" >
				<h4>风险事件处理状态统计</h4>
				<div class="common-pading common-pading1" style="height:350px">
					<div id="riskIncidentDisposeType" class="echarts"  style="height: 240px; margin-top: 50px;"></div>
				</div>
			</div> 
		    <div class="span6" >
				<h4>近十二个月风险事件趋势图</h4>
				<div class="common-pading common-pading1" style="height:350px">
					<div id="riskIncidentTrend" class="echarts" style="height: 240px; margin-top: 50px;"></div>
				</div>
			</div>  
	    </div>
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
<script src="${ctxStatic}/jquery/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script>
	var color = ['#aa4644', '#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];
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
	   
		var riskIncidentImportance = [{"type":"天津","value":"123"},
			{"type":"上海","value":"23"},
			{"type":"北京","value":"111"},
			{"type":"河北","value":"43"},
			{"type":"山东","value":"98"}]
		var listImportance = ${listImportance}
		var listUrgency = ${listUrgency}
		var listDisposeType = ${listDisposeType}
		var listTrend = ${listTrend}
		$.GetWorkSheets("riskIncidentImportance", $.ToConvertA(listImportance));
		$.GetWork2Sheets("riskIncidentUrgency", $.ToConvertA(listUrgency));
		$.GetWork3Sheets("riskIncidentDisposeType", $.ToConvertA(listDisposeType));
		$.GetChangeSheets("riskIncidentTrend", listTrend);
		
		
		
		
		
		
	})	
	
	
	
	// 饼图pingJson况
	$.ToConvertA = function(object) {
        var ajaxData = new Array();
        for (var one in object) {
            ajaxData.push({
                "name": object[one]["type"],
                "value": Number(object[one]["value"]),
                label: {
                    normal: {
                        color: FontColor,
                        fontSize: 10,
                        formatter: '{b}：{c}\n\n比例：{d}%'
                    }
                },
                labelLine: {
                    normal: {
                        length: 20
                    }
                }
            });
        }
        return ajaxData;
    } 
	
	
	//饼图统计情况
	$.GetWorkSheets = function(model,data) {
		
        var nameArr = [],
        DataArr = [];
        
        var option = {
	    	    color: ['#C23531', '#2F4554','#4573A7'],
	    	    backgroundColor: backgroundColor,
        	    tooltip: {
        	        trigger: 'item',
        	        formatter: "{a} <br/>{b}: {c} ({d}%)"
        	    },
        	    series: [
        	        {
        	            name:'重要程度',
        	            type: 'pie',
        	            radius : ['40%', '70%'],
        	            center: ['50%', '50%'],
        	            hoverAnimation: false,
        	            silent: true,
        	            clockwise: false,
        	            data: [{
        	                value: 0,
        	                name: '',
        	                label: {
        	                    normal: {
        	                        show: true,
        	                        position: 'center',
        	                        formatter: '风险事件',
        	                        color: FontColor,
        	                        fontSize: 10,
        	                        fontWeight: 'bold'
        	                    }
        	                },
        	                itemStyle: {
        	                    normal: {
        	                        color: '#ccc',
        	                        shadowBlur: 15,
        	                        shadowColor: '#129BED',
        	                        show: true
        	                    }
        	                }
        	            }]
        	        }, {
        	            type: 'pie',
        	            radius : ['40%', '70%'],
        	            center: ['50%', '50%'],
        	            hoverAnimation: false,
        	            silent: true,
        	            data: [{
        	                value: 0,
        	                name: '大',
        	                label: {
        	                    normal: {
        	                        show: true,
        	                        position: 'center',
        	                        formatter: '\n\n\n重要程度比例',
        	                        color: FontColor,
        	                        fontSize: 10,
        	                        fontWeight: 'bold'
        	                    }
        	                },
        	                itemStyle: {
        	                    normal: {
        	                        color: '#ccc',
        	                        shadowBlur: 30,
        	                        shadowColor: '#129BED',
        	                    }
        	                }
        	            }]
        	        }, {
        	        	 type: 'pie',
         	            radius : ['40%', '70%'],
         	            center: ['50%', '50%'],
         	            hoverAnimation: false,
         	            silent: true,
         	           data:data
        	        }
        	         
        	    ]
        	};
        var Barchart = echarts.init(document.getElementById(model));
        Barchart.setOption(option);

    }
	
	
	$.GetWork2Sheets = function(model,data) {
		
        var nameArr = [],
        DataArr = [];
        
        var option = {
	    	    color: ['#C23531', '#2F4554','#4573A7'],
	    	    backgroundColor: backgroundColor,
        	    tooltip: {
        	        trigger: 'item',
        	        formatter: "{a} <br/>{b}: {c} ({d}%)"
        	    },
        	    series: [
        	        {
        	            name:'紧急程度',
        	            type: 'pie',
        	            radius : ['40%', '70%'],
        	            center: ['50%', '50%'],
        	            hoverAnimation: false,
        	            silent: true,
        	            clockwise: false,
        	            data: [{
        	                value: 0,
        	                name: '',
        	                label: {
        	                    normal: {
        	                        show: true,
        	                        position: 'center',
        	                        formatter: '风险事件',
        	                        color: FontColor,
        	                        fontSize: 10,
        	                        fontWeight: 'bold'
        	                    }
        	                },
        	                itemStyle: {
        	                    normal: {
        	                        color: '#ccc',
        	                        shadowBlur: 15,
        	                        shadowColor: '#129BED',
        	                        show: true
        	                    }
        	                }
        	            }]
        	        }, {
        	            type: 'pie',
        	            radius : ['40%', '70%'],
        	            center: ['50%', '50%'],
        	            hoverAnimation: false,
        	            silent: true,
        	            data: [{
        	                value: 0,
        	                name: '大',
        	                label: {
        	                    normal: {
        	                        show: true,
        	                        position: 'center',
        	                        formatter: '\n\n\n紧急程度比例',
        	                        color: FontColor,
        	                        fontSize: 10,
        	                        fontWeight: 'bold'
        	                    }
        	                },
        	                itemStyle: {
        	                    normal: {
        	                        color: '#ccc',
        	                        shadowBlur: 30,
        	                        shadowColor: '#129BED',
        	                    }
        	                }
        	            }]
        	        }, {
        	        	 type: 'pie',
         	            radius : ['40%', '70%'],
         	            center: ['50%', '50%'],
         	            hoverAnimation: false,
         	            silent: true,
         	           data:data
        	        }
        	    ]
        	};
        var Barchart = echarts.init(document.getElementById(model));
        Barchart.setOption(option);

    }
    
    
	$.GetWork3Sheets = function(model,data) {
		
	    var nameArr = [],
	    DataArr = [];
	    
	    var option = {
	    	    color: ['#C23531', '#2F4554','#4573A7'],
	    	    backgroundColor: backgroundColor,
	    	    tooltip: {
	    	        trigger: 'item',
	    	        formatter: "{a} <br/>{b}: {c} ({d}%)"
	    	    },
	    	    series: [
	    	        {
	    	            name:'处理状态',
	    	            type: 'pie',
        	            radius : ['40%', '70%'],
        	            center: ['50%', '50%'],
        	            hoverAnimation: false,
        	            silent: true,
        	            clockwise: false,
        	            data: [{
        	                value: 0,
        	                name: '',
        	                label: {
        	                    normal: {
        	                        show: true,
        	                        position: 'center',
        	                        formatter: '风险事件',
        	                        color: FontColor,
        	                        fontSize: 10,
        	                        fontWeight: 'bold'
        	                    }
        	                },
        	                itemStyle: {
        	                    normal: {
        	                        color: '#ccc',
        	                        shadowBlur: 15,
        	                        shadowColor: '#129BED',
        	                        show: true
        	                    }
        	                }
        	            }]
        	        }, {
        	            type: 'pie',
        	            radius : ['40%', '70%'],
        	            center: ['50%', '50%'],
        	            hoverAnimation: false,
        	            silent: true,
        	            data: [{
        	                value: 0,
        	                name: '大',
        	                label: {
        	                    normal: {
        	                        show: true,
        	                        position: 'center',
        	                        formatter: '\n\n\n处理状态比例',
        	                        color: FontColor,
        	                        fontSize: 10,
        	                        fontWeight: 'bold'
        	                    }
        	                },
        	                itemStyle: {
        	                    normal: {
        	                        color: '#ccc',
        	                        shadowBlur: 30,
        	                        shadowColor: '#129BED',
        	                    }
        	                }
        	            }]
        	        }, {
        	        	 type: 'pie',
         	            radius : ['40%', '70%'],
         	            center: ['50%', '50%'],
         	            hoverAnimation: false,
         	            silent: true,
         	           data:data
        	        }
	    	    ]
	    	};
	    var Barchart = echarts.init(document.getElementById(model));
	    Barchart.setOption(option);
	
	}
	
	//折线图统计情况
	$.GetChangeSheets =function(model,data){
		   var type=[];
		   var value=[];
		   for(var i=0;i<data.length;i++){ 
			   type.push(data[i]['type']);
			   value.push(data[i]['value']);
			}

		 var  option = {
				    backgroundColor: backgroundColor,
				    title: {
				        text: '近12个月风险事件上报次数',
				        show: false,
				        textStyle: {
				            fontWeight: 'normal',
				            fontSize: 16,
				            color: FontColor
				        },
				        left: '6%'
				    },
				    tooltip: {
				        trigger: 'axis',
				        axisPointer: {
				            lineStyle: {
				                color: FontColor
				            }
				        }
				    },
				    legend: {
				        icon: 'rect',
				        itemWidth: 14,
				        itemHeight: 5,
				        itemGap: 13,
				        data: ['近12个月风险事件上报次数'],
				        right: 'center',
				        textStyle: {
				            fontSize: 12,
				            color: FontColor
				        }
				    },
				    grid: {
				        left: '3%',
				        right: '4%',
				        bottom: '3%',
				        containLabel: true
				    },
				    xAxis: [{
				        type: 'category',
				        boundaryGap: false,
				        axisLine: {
				            lineStyle: {
				                color: '#ccc'
				            }
				        },
				        axisLabel: {
				            margin: 10,
				            textStyle: {
				                fontSize: 14,
				                color: FontColor
				            }
				        },
				        data: type
				    }],
				    yAxis: [{
				        type: 'value',
				        name: '',
				        axisTick: {
				            show: false
				        },
				        axisLine: {
				            lineStyle: {
				                color: '#fff'
				            }
				        },
				        axisLabel: {
				            margin: 10,
				            textStyle: {
				                fontSize: 14,
				                color: FontColor
				            }
				        },
				        splitLine: {
				            lineStyle: {
				                type: 'solid',
				                color: '#ccc'
				            }
				        }
				    }],
				    series: [{
				        name: '近12个月风险事件上报次数',
				        type: 'line',
				        smooth: true,
				        symbol: 'circle',
				        symbolSize: 5,
				        showSymbol: false,
				        lineStyle: {
				            normal: {
				                width: 1
				            }
				        },
				        areaStyle: {
				            normal: {
				                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
				                    offset: 0,
				                    color: 'rgba(0, 136, 212, 0.2)'
				                }, {
				                    offset: 1,
				                    color: 'rgba(0, 136, 212, 0)'
				                }], false),
				                shadowColor: 'rgba(0, 0, 0, 0.1)',
				                shadowBlur: 10
				            }
				        },
				        itemStyle: {
				            normal: {
				                color: 'rgb(0,136,212)',
				                borderColor: 'rgba(0,136,212,0.2)',
				                borderWidth: 12

				            }
				        },
				        data: value
				    }, ]
				}

		    var Barchart = echarts.init(document.getElementById(model));
	        Barchart.setOption(option);
	   }
	 
		
</script>
</body>
</html>