/**
 * Created by oHa on 2017/12/28.
 */
var windowsHeight, _fontSize = 14, _fontSize1 = 26, breakData = 8,
legendTop = '30%', radiusData = ['40%', '90%'],radiusDataCicle="65%", lengthECharts = 30,mapHeigth = '90%',zoom=14.75,centerCoordinate= [ 117.663920, 39.035650 ];
// 基础颜色表
var color = [ '#D87A82', '#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d',
		'#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f' ];
$(function() {
 
	//
	var context = $(".context").attr("content");
	
	
	// 各街道人口事件汇总
	$.getJSON(context + "/report/ccmPeopleAmount/findPeopleAndEventByArea", function(
			data) {
		// 接收参数
		var dataX = data["1"];
		var poepleDataY = data["2"];
		var eventDataY = data["3"];
		//AllEventAndPeopleFun(dataX, poepleDataY,eventDataY)
	});
	
	// 案事件 统计情况
	$.getJSON(context + "/report/ccmIncidentBegin/findSolveByMon", function(
			data) {
		var dataX = data["X轴"];
		var dataY1 = data["案事件数"];
		var dataY2 = data["处理率"];
		// 社区事务
		$.AlarmWeekInfo("AlarmInfoWeekEcharts", dataX, dataY1, dataY2);

	});
	// 事件区域分布
	$.getJSON(context + "/event/ccmEventIncident/getSafeDisData?eventType= ", function(
			data) {
		// 接收参数
	    SafeDisEchartsFun(data)
	});
	//治安重点场所
	$.getJSON(context + "/org/ccmOrgNpse/getSafePubData", function(
			data) {
			// 接收参数
			SafePubEchartsFun(data)
	});
	//事件性质
	$.getJSON(context + "/report/ccmIncidentBegin/getItemByProperty", function(
			data) {
		// 接收参数   
        SafeTypeEchartsFun($.ToConvertA(data))
	});
	// 案事件分级统计
	$.getJSON(context + "/report/ccmIncidentBegin/getItemByScale", function(
			data) {
			//事件分级
	      	EventTypeLevelEchartsFun(data)
	});
	// 案事件类型
	$.getJSON(context + "/report/ccmIncidentBegin/findSumByEventType",
			function(data) {
			// 填充数据  
			SafeLevelEchartsFun($.ToConvertA(data))
	});
	// 事件分类
	$.getJSON(context + "/report/ccmIncidentBegin/findEventFenLeiData",
			function(data) {
			// 填充数据   
			EventFenLeiEchartsFun($.ToConvertA(data))
	});
	// 事故发生趋势
	$.getJSON(context + "/event/ccmEventIncident/getSafeHappenData", function(
			data) {
		// 接收参数
	    SafeHappenEchartsFun(data)
	});
	// 矛盾纠纷化解
	$.getJSON(context + "/event/ccmEventAmbi/getAmbiData", function(
			data) {
		// 接收参数
		AmbiFun(data)
	});
	
	
	// 特殊人群分析
	$.getJSON(context + "/report/ccmPeopleAmount/getSpecialPopData", function(
			data) {
		// 接收参数
		NanDing(data)
	});
	
	// 首页概况各种总数查询
	$.getJSON(context + "/report/ccmReportOthers/getOthersAll",
		function(data) {
			// 接收参数
			EachAll(data);
		}
	);

	$(window).resize(function() {
		window.location.reload()
	})
	//
	$('#main').height($(window).height());
	$('.height100').height($('#main').height() - 80);
	windowsHeight = $(window).width();
	if (windowsHeight>=1680&&windowsHeight<1920) {
		_fontSize = 14;
		radiusData = ['40%', '90%'];
		zoom=14.7;
		centerCoordinate= [ 113.397004,34.546021 ]
		lengthECharts = 30;
		_fontSize1 = 26;
		breakData = 8;
		mapHeigth = '90%'
	} else if(windowsHeight>=1600&&windowsHeight<1680){
		_fontSize = 12;
		radiusData = ['40%', '90%'];
		zoom=14.4;
		centerCoordinate= [ 113.397004,34.546021 ]
		lengthECharts = 30;
		_fontSize1 = 26;
		breakData = 8;
		mapHeigth = '90%'
	}else if(windowsHeight>=1440&&windowsHeight<1660){
		_fontSize = 12;
		radiusData = ['40%', '88%'];
		zoom=14.4;
		centerCoordinate= [ 113.397004,34.546021 ]
		lengthECharts = 30;
		_fontSize1 = 26;
		breakData = 8;
		mapHeigth = '90%'
	}else if(windowsHeight<1440) {
		_fontSize = 12;
		radiusData = ['40%', '88%'];
		zoom=14.1;
		centerCoordinate= [ 113.397004,34.546021 ]
		lengthECharts = 30;
		_fontSize1 = 26;
		radiusDataCicle="50%";
		breakData = 8;
		mapHeigth = '90%'
	}

	//案事件处理
	$.getJSON("/arjccm/a/report/ccmIncidentBegin/getItemBySumToday",
		function(data) {
			//接收参数
			map();
			EventTotalFun(data);
		}
	);
	

	

	
	
	//实有人口分析
	function RightTypeEchartsFun(analyzePopData) {
		var color = [ '#4573a7', '#D87A82', '#89a54e', '#71588f', '#4298af',
			'#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc',
			'#92c3d4', '#ffdf5f' ];
		var option = {
				
		 color:color,
			grid : {
				left : '3%',
				right : '3%',
				bottom : '3%',
				top : '3%',
				containLabel : true
			},
			tooltip : {
				show : "true",
				trigger : 'axis',
				axisPointer : {
					type : 'shadow'
				},
				confine : true
			},
			yAxis : {
				 show : false,
				type : 'value',
				axisTick : {
					show : false
				},
				axisLine : {
					show : false,
					lineStyle : {
						color : '#fff',
					}
				},
				splitLine : {
					show : false,
					lineStyle : {
						color : '#aaa',
					}
				},
			},
			xAxis : [ {
				type : 'category',
				axisTick : {
					show : false
				},
				axisLine : {
					show : false,
					lineStyle : {
						color : '#fff',
					}
				},
				axisLabel : {
					// formatter: '{value} %',
					textStyle : {
						color : '#fff',
						fontSize : _fontSize
					}
				},
				data : [ '户籍人口', '境外人口', '流动人口', '常住人口' ]
			} ],
			series : [
					{
						name : '',
						type : 'bar',
//						itemStyle : {
//							normal : {
//								show : true,
//								color : new echarts.graphic.LinearGradient(0,
//										0, 0, 1, [ {
//											offset : 0,
//											color : '#00FFE6'
//										}, {
//											offset : 1,
//											color : '#007CC6'
//										} ]),
//								barBorderRadius : 0,
//								borderWidth : 0,
//								borderColor : '#333',
//							}
//						},
						label : {
							normal : {
								show : true,
								position : 'top',
								textStyle : {
									color : '#fff'
								}
							}
						},
						barWidth : '30%',
						data : analyzePopData
					// data: [195, 285, 355,123]
					}, ]
		};
		var myChart = echarts.init(document.getElementById("RightTypeEcharts"));
		myChart.setOption(option);
	}
	
	// 案事件处理
	function EventTotalFun(Data) {
		
		$('#HandlindToday').html(Data["0"]);//待处理
		$('#TotalToday').html(Data["1"]);//处理中
		$('#HandledToday').html(Data["2"]);//已处理
	}
	
	//矛盾纠纷化解
	function AmbiFun(data) {
		$("#huajie1").html(data[0]);//累计受理纠纷
		$("#huajie2").html(data[1]);//个体性事件
		$("#huajie3").html(data[2]);//一般群体性事件
		$("#huajie4").html(data[3]);//重大群体性事件
	}

	//首页概况各种总数查询
	function EachAll(data){
		$("#eachAll1").html(data[0]);//社区民警/辅警
		$("#eachAll2").html(data[1]);//公安警务站
		$("#eachAll3").html(data[2]);//网格员
		$("#eachAll4").html(data[3]);//视频监控
		$("#eachAll5").html(data[4]);//应急避难场所
		$("#eachAll6").html(data[5]);//应急物资
	}
	//各街道人口事件汇总
	function AllEventAndPeopleFun(dataX, poepleDataY,eventDataY){
        var option = {
        		tooltip: {
                    trigger: 'axis',
                    formatter: '{b}<br/>{a}: {c0}<br/>{a1}:{c1}',
    		        confine:true
                },
                
                legend: {
                    data: ['人口', '案事件'],
                    textStyle: {
                        color: '#fff',
                        fontSize: _fontSize
                    }
                },
                xAxis: [{
                    type: 'category',
                    // data: ydata,
                    data: dataX,
                    // data:ydata,
                    axisPointer: {
                        type: 'shadow'
                    },
                    axisLine: {
                        lineStyle: {
                            color: '#fff'
                        }
                    },
                    axisLabel: {
                        textStyle: {
                            color: '#fff',
                        },
                        interval:0,
    	                rotate:-30,
                    },
                }],
                yAxis: [{
                	
                        type: 'value',
                        name: '',
                        axisLabel: {
                            formatter: '{value} '
                        },
                        axisLine: {
                            lineStyle: {
                                color: '#fff'
                            }
                        },
                        splitLine: {
                            show: false
                        }
                    },
                {
                    type: 'value',
                    name: '',
                    axisLabel: {
                        formatter: '{value} '
                    },
                    axisLine: {
                        lineStyle: {
                            color: '#fff'
                        }
                    },
                    splitLine: {
                        show: false
                    }
                }],
                series: [{
                    name: '人口',
                    type: 'bar',
                    // data: jdata,
                    data: poepleDataY,
                    itemStyle: {
                        normal: {
                            color: "#89a54e",
                        },
                        textStyle: {
                            color: '#fff',
                            fontSize: _fontSize
                        }
                    },
                    
                },
                {
                    name: '案事件',
                    type: 'bar',
                    yAxisIndex: 1,
                    // data: hdata,
                    data: eventDataY,
                    itemStyle: {
                        normal: {
                            color: "#4573a7"
                        }
                    },
                    textStyle: {
                        color: '#fff',
                        fontSize: _fontSize
                    }
                }]
            };
		var myChart = echarts.init(document.getElementById("allEventAndPeople"));
		myChart.setOption(option);
	}




	// 地图
	function map() {
		var centerCoordinate= [ 117.663920, 39.035650 ];
		viewSituationFun(centerCoordinate,'14.75')
		// 地图默认数据设置
		var defaultPrams = {
			id : 'mapMask',
			centerCoordinate :centerCoordinateSituation,
			zoom : zoomPop,
			maxZoom : 18,
			minZoom : 9.8,
			baseUrl :baseUrlT,
			zoomShowOrHide : false,// 缩小放大
			overviewMap : false,
			selectPointerFlag:true
		// 鹰眼图
		}
	    var Map1 = new ArjMap.Map(defaultPrams);
		// 加载地图
		Map1.drawMapSituation();
		
		
		
		
		// 地图默认数据设置
		var defaultPrams = {
			id : 'map',
			centerCoordinate : centerCoordinateSituation,
			zoom : zoomPop,
			maxZoom : 18,
			minZoom : 9.8,
//			baseUrl : [{
//		        'type':'tiandi',
//				'id':'yingxiang',
//				'name' : '影像图 ',
//				'url' : 'http://t0.tianditu.com/DataServer?T=img_w&x={x}&y={y}&l={z}',
//				'isShow' : true,
//			}],
			zoomShowOrHide : false,// 缩小放大
			overviewMap : false,
			selectPointerFlag:true
		// 鹰眼图
		}
	    var Map = new ArjMap.Map(defaultPrams);
		// 加载地图
		Map.drawMapSituation();
	/*	var zuobiao=[ 117.655920, 39.038050 ];
		var zuobiao1=[ 117.653920, 39.035050 ];
		var zuobiao2=[ 117.663920, 39.042050 ];
		Map.postcomposeOlIndex('紧急',zuobiao,"123",'0')
		Map.postcomposeOlIndex('紧急',zuobiao1,"1234",'0')
		Map.postcomposeOlIndex('紧急',zuobiao2,"12345",'0')*/
		$.getJSON(context + "/sys/map/orgAreaMap?type=1", function(data) {
			Map.addJSON1([ {
				'type' : 'communitys',
				'data' : data,
				'isShow' : true
			} ])
	});
		
		
}
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
//饼图统计情况
	$.GetWorkSheets = function(model,data) {
		
        var nameArr = [],
        DataArr = [];
        
        var option = {
            tooltip: {
                trigger: 'item',
                formatter: "{b} :<br/> {c} ({d}%)",
		        confine:true
            },
            legend: {
                
                type: 'scroll',
                orient: 'vertical',
                left:'left',
                top:'middle',
                textStyle: {
                    color: '#fff',
                    fontSize: _fontSize,
                },
                data: data
            }, 
            series: [{
                name: '特殊人群类型',
                type: 'pie',
                radius : radiusData,
                center: ['70%', '50%'],
                color: color,
                label: {
                    normal: {
                        show: false
                    }
                },
                
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: data
            }]
        };
        var Barchart = echarts.init(document.getElementById(model));
        Barchart.setOption(option);

    }
	
	
	$.GeHouseState=function(model,Data){
		//数据
		  var data = {
			        name: "基础数据资源",
			        "children": [
			            {
			                "name": "总数",
			                "children": [
			                    {
			                        "name": "自住", //类型名称
			                        "size": Data[0].value, //总数
			                        "add": "11", //本月新增数
			                        "rate": "13%" //月增长率
			                    }, {
			                        "name": "出租",
			                        "size": Data[1].value,
			                        "add": "1",
			                        "rate": "13%"
			                    }, {
			                        "name": "空置",
			                        "size": Data[2].value,
			                        "add": "1",
			                        "rate": "13%"
			                    }],
			                "size": parseInt(Data[0].value)+parseInt(Data[0].value)+parseInt(Data[2].value),
			                "add": "11",
			                "rate": "13%"
			            }
			        ]
			    };

		//配色
		var getFillColor = function(nodeName, isLeaf) {
		    var fill = {};
		        if (isLeaf) {
		        	if(nodeName=="自住"){
		        		fill.fillColor = {
				                type: "radial",
				                x: 0.5,
				                y: 0.5,
				                r: 0.5,
				                colorStops: [{
				                    offset: 0,
				                    color: "#034e61" // 0% 处的颜色
				                }, {
				                    offset: 0.7,
				                    color: "#086b87" // 70% 处的颜色
				                }, {
				                    offset: 1,
				                    color: "#068ca3" // 100% 处的颜色
				                }],
				                globalCoord: false // 缺省为 false
				            };
				            fill.borderColor = "#03a1b3";
				            fill.borderWidth = 2;
				            fill.emphasisFillColor = {
				                type: "radial",
				                x: 0.5,
				                y: 0.5,
				                r: 0.5,
				                colorStops: [{
				                    offset: 0,
				                    color: "#0a7998" // 0% 处的颜色
				                }, {
				                    offset: 0.7,
				                    color: "#0b9ec8" //70% 处的颜色
				                }, {
				                    offset: 1,
				                    color: "#09c2f6" // 100% 处的颜色
				                }],
				                globalCoord: false // 缺省为 false
				            };
				            fill.emphasisBorderColor = "#09c2f6";
				            fill.emphasisborderWidth = 1;
		        	}else if(nodeName=="出租"){
		        		  fill.fillColor = {
		        	                type: "radial",
		        	                x: 0.5,
		        	                y: 0.5,
		        	                r: 0.5,
		        	                colorStops: [{
		        	                        offset: 0,
		        	                        color: "#4e0435" // 0% 处的颜色
		        	                    },
		        	                    /* {
		        	                                               offset: 0.5, color: "#730c50" // 100% 处的颜色
		        	                                           },*/
		        	                    {
		        	                        offset: 1,
		        	                        color: "#84085a" // 100% 处的颜色
		        	                    }
		        	                ],
		        	                globalCoord: false // 缺省为 false
		        	            };
		        	            fill.borderColor = "rgba(154, 14, 109, 1)";
		        	            fill.borderWidth = 2;
		        	            fill.emphasisFillColor = {
		        	                type: "radial",
		        	                x: 0.5,
		        	                y: 0.5,
		        	                r: 0.5,
		        	                colorStops: [{
		        	                    offset: 0,
		        	                    color: "#ae0876" // 0% 处的颜色
		        	                }, {
		        	                    offset: 0.5,
		        	                    color: "#cd0a8b" // 50% 处的颜色
		        	                }, {
		        	                    offset: 1,
		        	                    color: "#f505a4" // 100% 处的颜色
		        	                }],
		        	                globalCoord: false // 缺省为 false
		        	            };
		        	            fill.emphasisBorderColor = "#f505a4";
		        	            fill.emphasisborderWidth = 3;
		        	}else if(nodeName=="空置"){
		        		  fill.fillColor = {
		        	                type: "radial",
		        	                x: 0.5,
		        	                y: 0.5,
		        	                r: 0.5,
		        	                colorStops: [{
		        	                        offset: 0,
		        	                        color: "#626815" // 0% 处的颜色
		        	                    },
		        	                    /* {
		        	                                               offset: 0.7, color: "#7b8013" // 100% 处的颜色
		        	                                           },*/
		        	                    {
		        	                        offset: 1,
		        	                        color: "#a9aa0e" // 100% 处的颜色
		        	                    }
		        	                ],
		        	                globalCoord: false // 缺省为 false
		        	            };
		        	            fill.borderColor = "rgba(182, 184,30, 1)";
		        	            fill.borderWidth = 2;

		        	            fill.emphasisFillColor = {
		        	                type: "radial",
		        	                x: 0.5,
		        	                y: 0.5,
		        	                r: 0.5,
		        	                colorStops: [{
		        	                    offset: 0,
		        	                    color: "#a7ac0e" // 0% 处的颜色
		        	                }, {
		        	                    offset: 0.5,
		        	                    color: "#babc0c" // 50% 处的颜色
		        	                }, {
		        	                    offset: 1,
		        	                    color: "#f6f701" // 100% 处的颜色
		        	                }],
		        	                globalCoord: false // 缺省为 false
		        	            };
		        	            fill.emphasisBorderColor = "#f6f701";
		        	            fill.emphasisborderWidth = 2;
		        	}else{
		        		fill.fillColor = {
				                type: "radial",
				                x: 0.5,
				                y: 0.5,
				                r: 0.5,
				                colorStops: [{
				                    offset: 0,
				                    color: "#034e61" // 0% 处的颜色
				                }, {
				                    offset: 0.7,
				                    color: "#086b87" // 70% 处的颜色
				                }, {
				                    offset: 1,
				                    color: "#068ca3" // 100% 处的颜色
				                }],
				                globalCoord: false // 缺省为 false
				            };
				            fill.borderColor = "#03a1b3";
				            fill.borderWidth = 2;
				            fill.emphasisFillColor = {
				                type: "radial",
				                x: 0.5,
				                y: 0.5,
				                r: 0.5,
				                colorStops: [{
				                    offset: 0,
				                    color: "#0a7998" // 0% 处的颜色
				                }, {
				                    offset: 0.7,
				                    color: "#0b9ec8" //70% 处的颜色
				                }, {
				                    offset: 1,
				                    color: "#09c2f6" // 100% 处的颜色
				                }],
				                globalCoord: false // 缺省为 false
				            };
				            fill.emphasisBorderColor = "#09c2f6";
				            fill.emphasisborderWidth = 1;
		        	}
		            
		        } else {
		            fill.fillColor = "rgba(0, 252, 255, 0)";
		            fill.borderColor = "rgba(11, 72, 115, 0)";
		            fill.borderWidth = 1;
		            fill.emphasisFillColor = "rgba(0, 252, 255, 0)";
		            fill.emphasisBorderColor = "rgba(11, 72, 115, 0)";
		            fill.emphasisborderWidth = 3;
		        }
		
		    return fill;
		};

		//渲染函数
		var HouseTypeWidth=$('#ccmPopTenantHouseType').width();
		var HouseTypeHeight=$('#ccmPopTenantHouseType').height();
		var renderItem = function(params, api) {
		    var fillObj = getFillColor(api.value(2), api.value(9));
		    var textPosition = "inside";
		    if (!api.value(9)) {
		        if (api.value(1) == 1) {
		            if (api.value(6) > api.getWidth() / 2) {
		                textPosition = "right";
		            } else {
		                textPosition = "left";
		            }
		        } else {
		            textPosition = "top";
		        }
		    }
		    return {
		        type: "circle",
		        shape: {
		            cx: api.value(6),
		            cy: api.value(7),
		            r: api.value(8)
		        },
		        z2: api.value(1) * 2,
		        style: api.style({
		            stroke: fillObj.borderColor,
		            fill: fillObj.fillColor,
		            textPosition: textPosition,
		            lineWidth: fillObj.borderWidth
		                //text: nodeName,
		                // textFont: textFont,
		                // textDistance : itemLayout.r,
		        }),
		        styleEmphasis: api.style({
		            textPosition: textPosition,
		            stroke: fillObj.emphasisBorderColor,
		            fill: fillObj.emphasisFillColor,
		            lineWidth: fillObj.emphasisborderWidth
		        })
		    };
		};

		var root = d3.hierarchy(data)
		    .sum(function(d) {
		        return d.size;
		    })
		    .sort(function(a, b) {
		        return b.value - a.value;
		    });
		d3.pack().size([HouseTypeWidth - 2, HouseTypeHeight - 2]).padding(0.5)(root);
		var maxDepth = 0;
		var nodeAll = root.descendants();
		var nodes = nodeAll.filter(function(it) {
		    return it.parent;
		});

		//获取各圆相关数据
		var seriesData = nodes.map(function(node) {
		    maxDepth = Math.max(maxDepth, node.depth);
		    var color = "#ffffff";
		    node.isLeaf = !node.children || !node.children.length;
		    if (node.depth == 1) {
		        switch (node.data.name) {
		            case "家庭医生签约人数":
		                color = "rgba(218, 22, 158, 1)";
		                break;
		            case "基础信息数据量":
		                color = "rgba(156,156,14,1)";
		                break;
		            case "健康档案数量":
		                color = "#ffffff";
		                break;
		        }
		        return {
		            value: [
		                node.value,
		                node.depth,
		                node.data.name,
		                node.data.size,
		                node.data.add,
		                node.data.rate,
		                node.x,
		                node.y,
		                node.r,
		                node.isLeaf
		            ],
		            label: {
		                normal: {
		                    show: false,
		                    color: color,
		                    formatter: function(params) {
		                        return "{type|" + params.value[2] + "}\n{numAll|" + params.value[3] + "}\n{add|本月新增：" + params.value[4] + "}";
		                    },
		                    rich: {
		                        type: {
		                            fontSize: 14,
		                            color: color
		                        },
		                        numAll: {
		                            fontSize: 28,
		                            padding: [5, 0, 5, 0],
		                            color: color
		                        },
		                        add: {
		                            fontSize: 14,
		                            color: color
		                        }
		                    }
		                }
		            }
		        }
		    } else {
		        if (node.data.name == "家庭医生签约" || node.data.name == "慢性病人管理数") {
		            color = "rgba(222, 0, 155,1)";
		        }
		        return {
		            value: [
		                node.value,
		                node.depth,
		                node.data.name,
		                node.data.size,
		                node.data.add,
		                node.data.rate,
		                node.x,
		                node.y,
		                node.r,
		                node.isLeaf
		            ],
		            label: {
		                normal: {
		                    show: true,
		                    color: color,
		                    position: "inside",
		                    formatter: function(params) {
		                        var result = "";
		                        var nodeName = params.value[2];
		                        if (params.value[9]) {
		                            if (params.value[8] > 10) {
		                                var trunText = echarts.format.truncateText(nodeName, 2 * params.value[8], {
		                                    fontSize: 14,
		                                    fontFamily: "微软雅黑"
		                                }, '.');
		                                if (trunText.indexOf(".") > 0) {
		                                    var strindex1 = nodeName.indexOf("患者");
		                                    var strindex2 = nodeName.indexOf("管理数");
		                                    if (strindex1 > 0) {
		                                        result += "{type|" + nodeName.substring(0, strindex1) + "}";
		                                    } else {
		                                        result += "{type|" + nodeName.substring(0, strindex2) + "}";
		                                    }
		                                } else {
		                                    result += "{type|" + nodeName + "}";
		                                }
		                                if (params.value[8] > 45) {
		                                    result += "\n{num|" + params.value[3] + "}";
		                                }
		                            }
		                        } else {
		                            result += "{type|" + params.value[2] + "}";
		                        }
		                        return result;
		                    },
		                    rich: {
		                        type: {
		                            fontSize: 12,
		                            padding: [5, 0, 5, 0],
		                            color: color
		                        },
		                        num: {
		                            fontSize: 16,
		                            color: color
		                        }
		                    }
		                }
		            }
		        }
		    }
		});

		//echarts 配置
		var option = {
				   grid: {
				         left: '3%',  //如果离左边太远就用这个......
				         right: '3%',
				         bottom: '3%',
				         top: '0%',
				         containLabel: true
				     },
		    xAxis: {
		        axisLine: {
		            show: false
		        },
		        axisTick: {
		            show: false
		        },
		        axisLabel: {
		            show: false
		        },
		        splitLine: {
		            show: false
		        }
		    },
		    yAxis: {
		        axisLine: {
		            show: false
		        },
		        axisTick: {
		            show: false
		        },
		        axisLabel: {
		            show: false
		        },
		        splitLine: {
		            show: false
		        }
		    },
		    tooltip: {
		      //  backgroundColor: "rgba(50,50,50,0.95)",
		        formatter: function(params) {
		            var size = ("" + params.value[3]).replace(/\d{1,3}(?=(\d{3})+$)/g, "$&,");
		            var add = ("" + params.value[4]).replace(/\d{1,3}(?=(\d{3})+$)/g, "$&,");
		            var result = "<span>" + params.value[2] + "</span>:" +
		                "<span style = ''>" + size + "</span></br>" +
		                "<div>"
		            return result;
		        },
		        confine:true
		    },
		    series: {
		        type: "custom",
		        renderItem: renderItem,
		        data: seriesData,
		    	label : {
					normal : {
						textStyle : {
							color : '#fff',
							fontSize : _fontSize,
						}
					}
				},
		    }
		};
		   var Barchart = echarts.init(document.getElementById(model));
	        Barchart.setOption(option);
	}
	//
	function PopKeyEchartsFun(data) {
		var dataVal = [ {
			"name" : "自住",
			"value" : 100
		}, {
			"name" : "出租",
			"value" : 130
		}, {
			"name" : "空置",
			"value" : 80
		}];
		var option = {
			color : color,
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)",
				confine : true
			},
			legend : {
				//   top: '20%',
				left : 'left',
				top : 'middle',
				orient : 'vertical',
				textStyle : {
					color : '#fff',
					fontSize : _fontSize,
				},
				data : [ '自住', '出租', '空置']
			},
			//		  		    calculable : true,
			series : [ {
				name : '级别',
				type : 'pie',
				radius : [ '47%', '70%' ],
				center : [ '67%', '50%' ],
				roseType : 'radius',
				label : {
					normal : {
						show : false
					},
					emphasis : {
						show : true
					}
				},
				lableLine : {
					normal : {
						show : false
					},
					emphasis : {
						show : true
					}
				},
				data : dataVal
			} ]
		};
		// 初始化参数
		var myChart = echarts.init(document.getElementById(''));
		myChart.setOption(option, true);

	}
	//流动人员来源排名
	function PopFlowTop(){
		//亮色图片
		var uploadedDataURL1 = "/asset/get/s/data-1514970761653-ByzqAf9XG.png";
		var uploadedDataURL2 = "/asset/get/s/data-1514970765660-B1I50M5QM.png";

		var color = [ '#4573a7', '#D87A82', '#89a54e', '#71588f', '#4298af',
			'#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc',
			'#92c3d4', '#ffdf5f' ];

		var baifenbi = [ 0.444, 0.555, 0.777, 0.888, 0.922];
		var grayBar = [ 1, 1, 1, 1, 1];
		var paiming = [ 5, 4, 3, 2, 1];
		var zongjine = [ 30000000, 40000000, 50000000, 60000000, 70000000];
		var fenpeijine = [300000, 400000, 500000, 600000, 700000];
		var city = ['北京市',  '广州市', '厦门市', '杭州市', '石家庄'];
		var option = {
		    color: color, //进度条颜色
		     grid: {
		         left: '-5%',  //如果离左边太远就用这个......
		         right: '3%',
		         bottom: '3%',
		         top: '3%',
		         containLabel: true
		     },
		    xAxis: [{
		            show: false,
		        },
		        //由于下边X轴已经是百分比刻度了,所以需要在顶部加一个X轴,刻度是金额,也隐藏掉
		        {
		            show: false,
		        }
		    ],
		    yAxis: {
		        type: 'category',
		        axisLabel: {
		            show: false, //让Y轴数据不显示
		        },
		        itemStyle: {

		        },
		        axisTick: {
		            show: false, //隐藏Y轴刻度
		        },
		        axisLine: {
		            show: false, //隐藏Y轴线段
		        },
		        data: city,
		    },
		    series: [
		        //背景色--------------------我是分割线君------------------------------//
		        {
		            show: true,
		            type: 'bar',
		            barGap: '-100%',
		            barWidth: '15', //统计条宽度 
		            itemStyle: {
		                normal: {
		                    barBorderRadius: 15,
		                    color: 'rgba(102, 102, 102,0.5)'
		                },
		            },
		            z: 1,
		            data: grayBar,
		        },
		        //蓝条--------------------我是分割线君------------------------------//
		        {
		            show: true,
		            type: 'bar',
		            barGap: '-100%',
		            barWidth: '15', //统计条宽度
		            itemStyle: {
		                normal: {
		                    barBorderRadius: 20, //统计条弧度
		                },
		            },
		            max: 1,
		            label: {
		                normal: {
		                    show: true,


		                    //百分比格式
		                    formatter: function(data) {
		                        return (baifenbi[data.dataIndex] * 100).toFixed(1) + '%';
		                    },
		                }
		            },
		            labelLine: {
		                show: false,
		            },
		            z: 2,
		            data: baifenbi,
		        },
		        //数据条--------------------我是分割线君------------------------------//
		        {
		            show: true,
		            type: 'bar',
		            xAxisIndex: 1, //代表使用第二个X轴刻度!!!!!!!!!!!!!!!!!!!!!!!!
		            barGap: '-100%',
		            barWidth: '15', //统计条宽度
		            itemStyle: {
		                normal: {
		                    barBorderRadius: 5,
		                    color: 'rgba(22,203,115,0.05)'
		                },
		            },
		            label: {
		                normal: {
		                    show: true,
		                    //label 的position位置可以是top bottom left,right,也可以是固定值
		                    //在这里需要上下统一对齐,所以用固定值
		                    position: [0, '-100%'],
		                    rich: { //富文本
		                        black: { //自定义颜色
		                            color: '#fff',
		                        },
		                        start2: {
		                            backgroundColor: { //这里可以添加你想自定义的图片
		                                image: uploadedDataURL2,
		                            },
		                        },
		                        start1: {
		                            backgroundColor: {
		                                image: uploadedDataURL1,
		                            }
		                        },
		                        green: {
		                            color: '#70DDA7',
		                        },
		                        yellow: {
		                            color: '#FEC735',
		                        },
		                    },
		                    formatter: function(data) {
		                        //富文本固定格式{colorName|这里填你想要写的内容}
		                        return paiming[data.dataIndex] == 1 ? '{start1|}{yellow|第' + paiming[data.dataIndex] + '名:' + city[data.dataIndex] + '}' + '{black|        人数:}{yellow|' + zongjine[data.dataIndex] / 10000 + '}{black|,}' + '{}' : '{start2|}{black|第' + paiming[data.dataIndex] + '名:' + city[data.dataIndex] + '}' + '{black|        人数:}{yellow|' + zongjine[data.dataIndex] / 10000 + '}{black|}' ;
		                    },
		                }
		            },
		            data: zongjine
		        }

		    ]
		};
		var myChart = echarts.init(document.getElementById('PopFlowTop'));
		myChart.setOption(option, true);
	}
	//重点企业分布
	function KeyCompyDisEchartsFun(){
		var option = {
		        color:[ '#14C1C0', '#89a54e'],
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },

			    grid: {
		            top: '5%',
		            left: '1%',
		            right: '1%',
		            bottom: '5%',
		            containLabel: true
		        },
			    xAxis : [
			        {
			            type : 'category',
			            data : ['丽水社区','长征里社区','唐山李社区','新园里社区','贵阳里社区','米兰社区'],
			            axisPointer: {
			                type: 'shadow'
			            },
			            axisLine: {
			                lineStyle: {
			                    color: '#fff'
			                }
			            },
			            axisLabel: {
			                textStyle: {
			                    color: '#fff',
			                    fontSize: _fontSize
			                }
			            },
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            axisLabel: {
			                formatter: '{value} ',
			                textStyle: {
			                    color: '#fff',
			                    fontSize: _fontSize
			                }
			            },
			            axisLine: {
			                lineStyle: {
			                    color: '#fff'
			                }
			            },
			            splitLine: {
			                show: false
			            }
			        }
			    ],
			    series : [
			        {
			            name:'数量',
			            type:'bar',
			            barWidth : '30%',
			            data:[ 12, 10, 24, 2, 8, 10]
			        }
			    ]
			};

			var myChart = echarts.init(document.getElementById("KeyCompyDisEcharts"));
			myChart.setOption(option);

	}

 //事件区域分布
	function SafeDisEchartsFun(data){
    	var name = [];
    	var value = [];
    	for (var one in data) {
    		name.push(data[one]["type"]);
    		value.push(data[one]["value"]);
        }
		var option = {
		        color:[ '#14C1C0', '#89a54e'],
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },

			    grid: {
		            top: '5%',
		            left: '1%',
		            right: '1%',
		            bottom: '5%',
		            containLabel: true
		        },
			    xAxis : [
			        {
			            type : 'category',
			            data : name,
			            axisPointer: {
			                type: 'shadow'
			            },
			            axisLine: {
			                lineStyle: {
			                    color: '#fff'
			                }
			            },
			            axisLabel: {
			                textStyle: {
			                    color: '#fff',
			                    fontSize: _fontSize
			                }
			            },
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            axisLabel: {
			                formatter: '{value} ',
			                textStyle: {
			                    color: '#fff',
			                    fontSize: _fontSize
			                }
			            },
			            axisLine: {
			                lineStyle: {
			                    color: '#fff'
			                }
			            },
			            splitLine: {
			                show: false
			            }
			        }
			    ],
			    series : [
			        {
			            name:'数量',
			            type:'bar',
			            barWidth : '30%',
			            data:value
			        }
			    ]
			};

			var myChart = echarts.init(document.getElementById("SafeDisEcharts"));
			myChart.setOption(option);

	}
	//  安全生产防范检查
    function EventDisEchartsFun(){
    	var option = {
    	        tooltip: {
    	            trigger: 'axis',
    	            formatter: '{b}<br/>{a}: {c0}<br/>{a1}:{c1}%',
    		        confine:true
    	        },
     grid: {
        top: '5%',
        left: '1%',
        right: '1%',
        bottom: '5%',
        containLabel: true
    },
 
    xAxis: [{
        type: 'category',
        data: ['2017-06','2017-07','2017-08','2017-09','2017-10','2017-11','2017-12','2018-01','2018-02','2018-03','2018-04','2018-05'],
        axisLine: {
            lineStyle: {
                color: '#fff'
            }
        },
        axisLabel: {
            textStyle: {
                color: '#fff',
                fontSize: _fontSize
            }
        },
    }],
    yAxis: [
    {
        type: 'value',
        name: '',
        axisLabel: {
            formatter: '{value}'
        },
        axisLine: {
            lineStyle: {
                color: '#fff'
            }
        },
        splitLine: {
            show: false
        }
    }],
    series: [
    {
        name: '数量',
        type: 'line',
        // data: hdata,
        data: ['200','250','300','200','250','200','200','250','300','200','250','300'],
        itemStyle: {
            normal: {
                color: "#ffea00"
            }
        },
        textStyle: {
            color: '#fff',
            fontSize: _fontSize
        }
    }]
};
// 实例化对象
var Barchart = echarts.init(document.getElementById('EventDisEcharts'));
// 传参
Barchart.setOption(option);

	}
	
    
//  事故发生趋势
    function SafeHappenEchartsFun(data){
    	var name = [];
    	var value = [];
    	for (var one in data) {
    		name.push(data[one]["type"]);
    		value.push(data[one]["value"]);
        }
    	
    	var option = {
    	        tooltip: {
    	            trigger: 'axis',
    	            formatter: '{b}<br/>{a}: {c0}<br/>',
    		        confine:true
    	        },
			     grid: {
			        top: '5%',
			        left: '1%',
			        right: '1%',
			        bottom: '15%',
			        containLabel: true
			    },
			 
			    xAxis: [{
			        type: 'category',
			        data: name,
			        axisLine: {
			            lineStyle: {
			                color: '#fff'
			            }
			        },
			        axisLabel: {
			            textStyle: {
			                color: '#fff',
			                fontSize: _fontSize
			            }
			        },
			    }],
			    yAxis: [
			    {
			        type: 'value',
			        name: '',
			        axisLabel: {
			            formatter: '{value}'
			        },
			        axisLine: {
			            lineStyle: {
			                color: '#fff'
			            }
			        },
			        splitLine: {
			            show: false
			        }
			    }],
			    series: [
			    {
			        name: '数量',
			        type: 'line',
			        // data: hdata,
			        data: value,
			        itemStyle: {
			            normal: {
			                color: "#ffea00"
			            }
			        },
			        textStyle: {
			            color: '#fff',
			            fontSize: _fontSize
			        }
			    }]
			};
			// 实例化对象
			var Barchart = echarts.init(document.getElementById('SafeHappenEcharts'));
			// 传参
			Barchart.setOption(option);

	}
	
    //事件性质
   function SafeTypeEchartsFun(data){
	    var option = {
	    	    color:['#5cb0ea','#ffb289','#d87a82','#8d99b3','#e5ce0c','#97b553'],
	    	    tooltip: {
	                trigger: 'item',
	                formatter: "{b} :<br/> {c} ({d}%)",
			        confine:true
	            },
	    	    calculable : true,
	    	    series : [
	    	        {
	    	            name:'事件性质',
	    	            type:'pie',
	                    radius : [30, 55],
	    	            center : ['50%', '50%'],
	    	            roseType : 'radius',
	    	            label: {
	    	                normal: {
	    	                    show: false
	    	                },
	    	                emphasis: {
	    	                    show: false
	    	                }
	    	            },
	    	            lableLine: {
	    	                normal: {
	    	                    show: false
	    	                },
	    	                emphasis: {
	    	                    show: false
	    	                }
	    	            },
	    	            data:data
	    	        },
	   
	    	    ]
	    	};

	  // 实例化对象
	   var Barchart = echarts.init(document.getElementById('SafeTypeEcharts'));
	   // 传参
	   Barchart.setOption(option);
   }
   
   
   //事故级别
   function SafeLevelEchartsFun(data){
	        var option = {
	            tooltip: {
	                trigger: 'item',
	                formatter: "{b} :<br/> {c} ({d}%)",
			        confine:true
	            },
	            series: [{
	                name: '事故级别',
	                type: 'pie',
	                radius : ['60%','85%'],
	                center: ['50%', '50%'],
	                color: ['#30c6cd','#b6a0e1','#5ab0ee','#ffb174','#df7680'],
	                label: {
	                    normal: {
	                        show: false
	                    }
	                },
	                
	                labelLine: {
	                    normal: {
	                        show: false
	                    }
	                },
	                data:data
	            }]
	        };
	        var Barchart = echarts.init(document.getElementById('SafeLevelEcharts'));
	        Barchart.setOption(option);

   }
   //事件分类
   function EventFenLeiEchartsFun(data){

	        
	        var option = {
	            tooltip: {
	                trigger: 'item',
	                formatter: "{b} :<br/> {c} ({d}%)",
			        confine:true
	            },
	            series: [{
	                name: '事件分类',
	                type: 'pie',
	                radius:"85%",
	                center: ['50%', '45%'],
	                color: ['#30c6cd','#b6a0e1','#5ab0ee','#ffb174','#df7680'],
	                label: {
	                    normal: {
	                        show: false
	                    }
	                },
	                
	                labelLine: {
	                    normal: {
	                        show: false
	                    }
	                },
	                data:data
	            }]
	        };
	        var Barchart = echarts.init(document.getElementById('EventFenLeiEcharts'));
	        Barchart.setOption(option);

   }
   //危化企业
   function WeiHuaEchartsFun(){
	   var option = {
			    title: {
			        text: '27%',
			        x: 'center',
			        y: 'center',
			        textStyle: {
			            fontWeight: 'normal',
			            color: '#517f93',
			            fontSize: '20'
			        }
			    },
			    color: ['#cbcec7'], 
			    series: [{
			        name: 'Line 1',
			        type: 'pie',
			        clockWise: true,
			        radius: ['54%', '70%'],
			        itemStyle: {
			            normal: {
			                label: {
			                    show: false
			                },
			                labelLine: {
			                    show: false
			                }
			            }
			        },
			        hoverAnimation: false, 
			        data: [{
			            value: 27,
			            name: '01',
			            itemStyle: {
			                normal: {
			                    color: { // 完成的圆环的颜色
			                        colorStops: [{
			                            offset: 0,
			                            color: '#d87a81' // 0% 处的颜色
			                        }, {
			                            offset: 1,
			                            color: '#d87a81' // 100% 处的颜色
			                        }]
			                    },
			                    label: {
			                        show: false
			                    },
			                    labelLine: {
			                        show: false
			                    }
			                } 
			            }
			        }, {
			            name: '02',
			            value: 73
			        }]
			    }]
			}

	   var Barchart = echarts.init(document.getElementById('WeiHuaEcharts'));
       Barchart.setOption(option);
   }
   // 近一周案事件报警情况
   $.AlarmWeekInfo = function(model,dataX,dataY1,dataY2) {
       var option = {
           tooltip: {
               trigger: 'axis',
               formatter: '{b}<br/>{a}: {c0}<br/>{a1}:{c1}%',
		        confine:true
           },
           grid: {
               top: '18%',
               left: '4%',
               right: '4%',
               bottom: '3%',
               containLabel: true
           },
           legend: {
               data: ['案事件数', '处理率'],
               textStyle: {
                   color: '#fff',
                   fontSize: _fontSize
               }
           },
           xAxis: [{
               type: 'category',
               // data: ydata,
               data: dataX,
               // data:ydata,
               axisPointer: {
                   type: 'shadow'
               },
               axisLine: {
                   lineStyle: {
                       color: '#fff'
                   }
               },
               axisLabel: {
                   textStyle: {
                       color: '#fff',
                       fontSize: _fontSize
                   }
               },
           }],
           yAxis: [{
               type: 'value',
               name: '',
               min: 0,
               axisLabel: {
                   formatter: '{value} ',
                   textStyle: {
                       color: '#fff',
                       fontSize: _fontSize
                   }
               },
               axisLine: {
                   lineStyle: {
                       color: '#fff'
                   }
               },
               splitLine: {
                   show: false
               }
           },
           {
               type: 'value',
               name: '',
               axisLabel: {
                   formatter: '{value} %'
               },
               axisLine: {
                   lineStyle: {
                       color: '#fff'
                   }
               },
               splitLine: {
                   show: false
               }
           }],
           series: [{
               name: '案事件数',
               type: 'bar',
               // data: jdata,
               data: dataY1,
               itemStyle: {
                   normal: {
                       color: "#14c1c0",
                   },
                   textStyle: {
                       color: '#fff',
                       fontSize: _fontSize
                   }
               },
               barWidth: 13,
               boundaryGap: true
           },
           {
               name: '处理率',
               type: 'line',
               yAxisIndex: 1,
               // data: hdata,
               data: dataY2,
               itemStyle: {
                   normal: {
                       color: "#ffea00"
                   }
               },
               textStyle: {
                   color: '#fff',
                   fontSize: _fontSize
               }
           }]
       };
       // 实例化对象
       var Barchart = echarts.init(document.getElementById(model));
       // 传参
       Barchart.setOption(option);
   }
   function NanDing(specialPopData){
		
		
		
		
//		var data = [
//			/*{
//				value : specialPopData["value1"],
//				name : '留守人员'
//			}, */
//			{
//				value : specialPopData["value2"],
//				name : '社区矫正'
//			}, {
//				value : specialPopData["value3"],
//				name : '肇事肇祸等严重精神障碍患者'
//			}, {
//				value : specialPopData["value4"],
//				name : '吸毒'
//			}, {
//				value : specialPopData["value5"],
//				name : '安置帮教'
//			}, {
//				value : specialPopData["value6"],
//				name : '艾滋病危险人员'
//			}, {
//				value : specialPopData["value11"],
//				name : '重点上访'
//			}, {
//				value : specialPopData["value12"],
//				name : '涉教'
//			}, {
//				value : specialPopData["value13"],
//				name : '危险品从业'
//			} ];
	
		var option = {
               color:color,
			    calculable: true,
			    "tooltip": {
			        "trigger": "item",
			        "formatter": "{b}:{c}"
			    },
			    "calculable": true,
			    "legend": {
			    	 // type: 'scroll',
			    	wight:"200px",
			    	padding: [5, 10],
			    	   height:"100px",
		                orient: 'vertical',
		                left:'left',
		                top:'middle',
		                textStyle: {
		                    color: '#fff',
		                    fontSize: _fontSize,
		                },
			        "data": [
			        	 "安置帮教人员",
			        	 "社区矫正人员",
			        	 "肇事肇祸等严重精神障碍患者",
			            "吸毒人员",
			            "艾滋病危险人员",
			        	"重点上访人员",
				        "涉教人员",
			            "危险品从业人员",
			        ],
			        "textStyle": {
			            "color": "#fff",
			            "fontSize": _fontSize,
			        }
			    },
			    "series": [{
			        "name": "",
			        "type": "pie",
			        "radius" : [ 70,160 ],
			        "avoidLabelOverlap": false,
			        "startAngle": 180,
			        "center" : [ '65%', '80%' ],
			        "roseType": "area",
			        "selectedMode": "single",
			        "label": {
			            "normal": {
			                "show": false,
			                "formatter": "{c}"
			            },
			            "emphasis": {
			                "show": true
			            }
			        },
			        "labelLine": {
			            "normal": {
			                "show": false,
			                "smooth": false,
			                "length": 20,
			                "length2": 10
			            },
			            "emphasis": {
			                "show": false
			            }
			        },
			        "data": [{
			                "value": specialPopData["value2"],
			                "name": "社区矫正人员",
			                "itemStyle": {
			                    "normal": {
			                        "label": {
			                            "show": false
			                        },
			                        "labelLine": {
			                            "show": false
			                        }
			                    }
			                }
			            },
			            {
			                "value": specialPopData["value3"],
			                "name": "肇事肇祸等严重精神障碍患者",
			                "itemStyle": {
			                    "normal": {
			                        "label": {
			                            "show": false
			                        },
			                        "labelLine": {
			                            "show": false
			                        }
			                    }
			                }
			            },
			            {
			                "value": specialPopData["value4"],
			                "name": "吸毒人员",
			                "itemStyle": {
			                    "normal": {
			                        "label": {
			                            "show": false
			                        },
			                        "labelLine": {
			                            "show": false
			                        }
			                    }
			                }
			            },
			            {
			                "value": specialPopData["value5"],
			                "name": "安置帮教人员",
			                "itemStyle": {
			                    "normal": {
			                        "label": {
			                            "show": false
			                        },
			                        "labelLine": {
			                            "show": false
			                        }
			                    }
			                }
			            },
			            {
			                "value": specialPopData["value6"],
			                "name": "艾滋病危险人员",
			                "itemStyle": {
			                    "normal": {
			                        "label": {
			                            "show": false
			                        },
			                        "labelLine": {
			                            "show": false
			                        }
			                    }
			                }
			            },
			            {
			                "value": specialPopData["value11"],
			                "name": "重点上访人员",
			                "itemStyle": {
			                    "normal": {
			                        "label": {
			                            "show": false
			                        },
			                        "labelLine": {
			                            "show": false
			                        }
			                    }
			                }
			            },
			            {
			                "value": specialPopData["value12"],
			                "name": "涉教人员",
			                "itemStyle": {
			                    "normal": {
			                        "label": {
			                            "show": false
			                        },
			                        "labelLine": {
			                            "show": false
			                        }
			                    }
			                }
			            },
			            {
			                "value": specialPopData["value13"],
			                "name": "危险品从业人员",
			                "itemStyle": {
			                    "normal": {
			                        "label": {
			                            "show": false
			                        },
			                        "labelLine": {
			                            "show": false
			                        }
			                    }
			                }
			            },
			            {
			                "value": 0,
			                "name": "",
			                "itemStyle": {
			                    "normal": {
			                        "label": {
			                            "show": false
			                        },
			                        "labelLine": {
			                            "show": false
			                        }
			                    }
			                }
			            },
			            {
			                "value": 0,
			                "name": "",
			                "itemStyle": {
			                    "normal": {
			                        "label": {
			                            "show": false
			                        },
			                        "labelLine": {
			                            "show": false
			                        }
			                    }
			                }
			            },
			            {
			                "value": 0,
			                "name": "",
			                "itemStyle": {
			                    "normal": {
			                        "label": {
			                            "show": false
			                        },
			                        "labelLine": {
			                            "show": false
			                        }
			                    }
			                }
			            },
			            {
			                "value": 0,
			                "name": "",
			                "itemStyle": {
			                    "normal": {
			                        "label": {
			                            "show": false
			                        },
			                        "labelLine": {
			                            "show": false
			                        }
			                    }
			                }
			            },
			            {
			                "value": 0,
			                "name": "",
			                "itemStyle": {
			                    "normal": {
			                        "label": {
			                            "show": false
			                        },
			                        "labelLine": {
			                            "show": false
			                        }
			                    }
			                }
			            },
			            {
			                "value": 0,
			                "name": "",
			                "itemStyle": {
			                    "normal": {
			                        "label": {
			                            "show": false
			                        },
			                        "labelLine": {
			                            "show": false
			                        }
			                    }
			                }
			            },
			            {
			                "value": 0,
			                "name": "",
			                "itemStyle": {
			                    "normal": {
			                        "label": {
			                            "show": false
			                        },
			                        "labelLine": {
			                            "show": false
			                        }
			                    }
			                }
			            },
			            {
			                "value": 0,
			                "name": "",
			                "itemStyle": {
			                    "normal": {
			                        "label": {
			                            "show": false
			                        },
			                        "labelLine": {
			                            "show": false
			                        }
			                    }
			                }
			            }
			        ]
			    }]
			};
		var myChart = echarts.init(document.getElementById("PopsNumEcharts"));
		myChart.setOption(option);
	}
   //治安重点场所
   function SafePubEchartsFun(data){
   	   var type = [];
	   var ajaxData = new Array();
       for (var one in data) {
           ajaxData.push({
               "name": data[one]["type"],
               "value": Number(data[one]["value"])
           });
           type.push(data[one]["type"]);
       }
	        
	        var option = {
	            tooltip: {
	                trigger: 'item',
	                formatter: "{b} :<br/> {c}",
			        confine:true
	            },
	            legend: {
	                data: type,
	                textStyle: {
	                    color: '#fff',
	                    fontSize: '12px'
	                },
	                orient:"vertical",
	                itemGap:2,
	                left:'center',
	                top:'bottom',
	            },
	            series: [{
	                name: '治安重点场所',
	                type: 'pie',
	                radius:radiusDataCicle,
	                center: ['50%', '35%'],
	                color: color,
	                label: {
	                    normal: {
	                        show: false
	                    }
	                },
	                
	                labelLine: {
	                    normal: {
	                        show: false
	                    }
	                },
	                data:ajaxData
	            }]
	        };
	        var Barchart = echarts.init(document.getElementById('SafePubEcharts'));
	        Barchart.setOption(option);

   }
   //事件分级
   function EventTypeLevelEchartsFun(data){
   	   var type = [];
	   var value = [];
	   var sum = 0;
       for (var one in data) {
           type.push(data[one]["type"]);
           value.push(data[one]["value"]);
           sum += Number(data[one]["value"]);
       } 
       
	   var dataStyle = { 
			    normal: {
			        label: {show:false},
			        labelLine: {show:false},
			        shadowBlur: 40,
			        shadowColor: 'rgba(40, 40, 40, 0.5)',
			    }
			};
			var placeHolderStyle = {
			    normal : {
			    	 color: 'rgba(57,61,80,0.3)',
			        label: {show:false},
			        labelLine: {show:false}
			    },
			    emphasis : {
			    	 color: 'rgba(57,61,80,0.3)',
			    }
			};
		var 	option = {
			    color: ['#5ab0ee','#b6a0e1','#30c6cd','#ffb174','#df7680'],
			    tooltip: {
	                trigger: 'item',
	                formatter: "{b} :<br/> {c} ({d}%)",
			        confine:true
	            },
			    series : [
			        {
			            name:'Line 1',
			            type:'pie',
			            clockWise:false,
			            radius : ["70%","85%"],
			            itemStyle : dataStyle,
			            hoverAnimation: false,
			       
			            data:[
			                {
			                    value:value[3],
			                    name:type[3]
			                },
			                {
			                    value:sum - Number(value[3]),
			                    name:'others',
			                    tooltip: {
			                        show: false
			                    },
			                    itemStyle : placeHolderStyle
			                }
			         
			            ]
			        }, 
			         {
			            name:'Line 2',
			            type:'pie',
			            clockWise:false,
			            radius : ["55%","70%"],
			            itemStyle : dataStyle,
			            hoverAnimation: false,
			           
			            data:[
			                {
			                    value:value[2], 
			                    name:type[2]
			                },
			                {
			                    value:sum - Number(value[2]),
			                    name:'others',
			                    tooltip: {
			                        show: false
			                    },
			                    itemStyle : placeHolderStyle
			                }
			            ]
			        },
			        {
			            name:'Line 3',
			            type:'pie',
			            clockWise:false,
			            hoverAnimation: false,
			            radius : ["40%","55%"],
			            itemStyle : dataStyle,
			           
			            data:[
			                {
			                    value:value[1], 
			                    name:type[1]
			                },
			                {
			                    value:sum - Number(value[1]),
			                    name:'others',
			                    tooltip: {
			                        show: false
			                    },
			                    itemStyle : placeHolderStyle
			                }
			            ]
			        },
			        {
			            name:'Line 4',
			            type:'pie',
			            clockWise:false,
			            hoverAnimation: false,
			            radius : ["25%","40%"],
			            itemStyle : dataStyle,
			           
			            data:[
			                {
			                    value:value[0], 
			                    name:type[0]
			                },
			                {
			                    value:sum - Number(value[0]),
			                    name:'others',
			                    tooltip: {
			                        show: false
			                    },
			                    itemStyle : placeHolderStyle
			                }
			            ]
			        }
			    ]
			};
	   var Barchart = echarts.init(document.getElementById('EventTypeLevelEcharts'));
       Barchart.setOption(option);
   }
	