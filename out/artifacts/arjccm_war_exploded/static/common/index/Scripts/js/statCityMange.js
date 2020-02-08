/**
 * Created by oHa on 2017/12/28.
 */
var windowsHeight, _fontSize = 14, _fontSize1 = 26, breakData = 8,
legendTop = '30%', radiusData = ['40%', '65%'], lengthECharts = 30,mapHeigth = '90%',zoom=14.75,centerCoordinate= [ 117.663920, 39.035650 ];
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
	
	//全部房屋类型统计
	$.getJSON(context + "/report/ccmPeopleAmount/getListTypeAll", function(
			data) {
		// 接收参数
		$.GeHouseState('ccmPopTenantHouseType',data)
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
	
	// 案事件 统计情况
	$.getJSON(context + "/report/ccmIncidentBegin/findSumByCondition",
			function(data) {
				// 填充数据
				$.updateConditionTable('AlarmUL', data);
			});

	
	
	// 首页概况各种总数查询
	$.getJSON(context + "/report/ccmIncidentBegin/getEachAll2",
		function(data) {
			// 接收参数
			EachAll(data);
		}
	);

	// 实有人口统计情况
	$.getJSON(context + "/report/ccmPeopleAmount/getAnalyzePopData", function(
			data) {
			$("#registerPop").html(data[0]||0)
			$("#overseasPop").html(data[1]||0)
			$("#flowPop").html(data[2]||0)
			$("#noRegisterPop").html(data[3]||0)
			$("#permanentPop").html(data[4]||0)
	});
	
	//类型统计情况
	$.getJSON(context + "/report/ccmIncidentBegin/findSumByEventType2", function(
			data) {
			$("#event1").html(data[0]);//累计受理事件
			$("#event2").html(data[1]);//安全事故
			$("#event3").html(data[2]);//群体性事件
			$("#event4").html(data[3]);//食品安全事故
			$("#event5").html(data[4]);//有关刑事案件
			$("#event6").html(data[5]);//其他
	});
	//行政处罚
	$.getJSON(context + "/know/ccmKnowPunish/getType", function(
			data) {
		  //行政处罚
		XingZhengChuFaFun(data)
	});
	//城市部件状态
	$.getJSON(context + "/citycomponents/ccmCityComponents/getCityType", function(
			data) {
		   //公用设施
	       ChengShiBuJianFun(data[0])
	       //道路交通
	       DaoLuJiaoTongFun(data[1])
	       //市容环境
	       ShiRongHuangjingFun(data[2])
	      
	});
	
	
	$(window).resize(function() {
		window.location.reload()
	})
	//
	$('#main').height($(window).height());
	$('.height100').height($('#main').height() - 80);
	windowsHeight = $(window).width();
	if (windowsHeight>=1680&&windowsHeight<1920) {
		_fontSize = 14;
		radiusData = ['40%', '60%'];
		zoom=14.7;
		centerCoordinate= [ 117.662920, 39.035650 ]
		lengthECharts = 30;
		_fontSize1 = 26;
		breakData = 8;
		mapHeigth = '90%'
	} else if(windowsHeight>=1600&&windowsHeight<1680){
		_fontSize = 12;
		radiusData = ['40%', '60%'];
		zoom=14.4;
		centerCoordinate= [ 117.662920, 39.035650 ]
		lengthECharts = 30;
		_fontSize1 = 26;
		breakData = 8;
		mapHeigth = '90%'
	}else if(windowsHeight>=1440&&windowsHeight<1660){
		_fontSize = 12;
		radiusData = ['40%', '58%'];
		zoom=14.4;
		centerCoordinate= [ 117.662920, 39.035650 ]
		lengthECharts = 30;
		_fontSize1 = 26;
		breakData = 8;
		mapHeigth = '90%'
	}else if(windowsHeight<1440) {
		_fontSize = 12;
		radiusData = ['40%', '58%'];
		zoom=14.1;
		centerCoordinate= [ 117.662920, 39.035650 ]
		lengthECharts = 30;
		_fontSize1 = 26;
		breakData = 8;
		mapHeigth = '90%'
	}

	
	//重点类型统计
	$.getJSON(context + "/report/ccmPeopleAmount/getListCompImpoType", function(
			data) {
		// 接收参数
		// 地图
		map();
	});
	

	
	
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

	//首页概况各种总数查询
	function EachAll(Data){
		$("#eachAll1").html(Data["value1"]);//实有人口总数
		$("#eachAll2").html(Data["value2"]);//楼栋总数
		$("#eachAll3").html(Data["value3"]);//房屋总数
		$("#eachAll4").html(Data["value4"]);//网格总数
		$("#eachAll5").html(Data["value5"]);//网格人员
		$("#eachAll6").html(Data["value6"]);//城市部件
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
			centerCoordinate :  centerCoordinateSituation,
			zoom : zoomPop,
			maxZoom : 18,
			minZoom : 9.8,
			baseUrl : baseUrlT,
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
		/*var zuobiao=[ 117.655920, 39.038050 ];
		var zuobiao1=[ 117.653920, 39.035050 ];
		var zuobiao2=[ 117.663920, 39.042050 ];*/
		/*Map.postcomposeOlIndex('紧急',zuobiao,"123")
		Map.postcomposeOlIndex('紧急',zuobiao1,"1234")
		Map.postcomposeOlIndex('紧急',zuobiao2,"12345")*/
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
                formatter: "{b} :<br/> {c}套 ({d}%)",
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
 
   
  
   $.updateConditionTable = function(model, originalData) {
       // echartsAlarm-value
       // 拼接 table内容
       var tableContent = "";
       // 获取 按月类型值
       for (var i in originalData) {
           // 获取每一个值
           var alarm='alarm1';
           if(originalData[i]["type"]=='特别重大'){
               alarm='alarm1';
           }else if(originalData[i]["type"]=='重大'){
               alarm='alarm2';
           } else if(originalData[i]["type"]=='较大'){
               alarm='alarm3';
           }else if(originalData[i]["type"]=='一般'){
               alarm='alarm4';
           }
           //格式化时间
           var clock="";
       	var clock1="";
       	var year,month,day,hh,mm,ss;
       	var eventTime = new Date(originalData[i]["value1"]);
       	year = eventTime.getFullYear(); //年  
       	month = eventTime.getMonth() + 1; //月  
       	day = eventTime.getDate(); //日  
       	hh = eventTime.getHours(); //时  
       	mm = eventTime.getMinutes(); //分  
       	ss = eventTime.getSeconds(); //秒  
       	
       	if (month < 10){
       		month = "0"+month;
       	}
       	if (day < 10){
       		day="0"+day;
       	}
       	if (hh < 10){
       		hh = "0"+hh;
       	}
       	if (mm < 10)
       		mm = '0'+mm;

       	if (ss < 10){
       		ss = '0'+ss;
       	}
       	var id = originalData[i]["value2"];
       	clock = month+"-"+day+" "+hh+":"+mm+":"+ss;
       	clock1= month+"月"+day+"日 "+hh+"时"+mm+"分"+ss+"秒";
           tableContent += "<li class='"+alarm+"'>" +
           		"<a onclick=\"cli(\'"+id+"\')\" title='案事件详情' style='text-decoration:none'>" +
           		"<b>【"+clock+"】</b>【"+originalData[i]["typeO"]+"】"+originalData[i]["value"]+
           		"</a></li>"
       }
      
       // 添加内容 到页面
       $ ("#"+model).html(tableContent);
       $("div.list_lh").myScroll({
           speed: 40, //数值越大，速度越慢
           rowHeight:36.88  //li的高度
       });
   }
   
   //行政处罚
   function XingZhengChuFaFun(data){
		$('#XingZhengChuFa').highcharts({
			chart: {
				   backgroundColor: 'transparent',
					type: 'column',
					margin: 35,
					options3d: {
							enabled: true,
							alpha: 0,
							beta: 15,
							depth: 110
					}
			},
			credits:{
			     enabled: false // 禁用版权信息
			},
			legend:{
				enabled: false 
			},
			title: {
			    text: null
			},
/*			tooltip: {
				headerFormat: '<b>{point.key}</b><br>',
				pointFormat: '<span style="color:{series.color}">\u25CF</span> {series.name}: {point.y} / {point.stackTotal}'
		 },*/
			xAxis:{
				   title:{
					   text: null
				   },
					categories: [
						'一季度',
						'二季度',
						'三季度',
						'四季度',
				],
				crosshair: true,
				 labels: {  
	                    formatter: function () {  
	                        return this.value  
	                    },  
	                    style: {  
	                        color: '#fff',  
	                        fontSize:'14px',  
	                    }  
	                },          
				},
				yAxis:{
				   title:{
					   text: null
				   },
				   labels: {  
	                    formatter: function () {  
	                        return this.value  
	                    },  
	                    style: {  
	                        color: '#fff',  
	                        fontSize:'14px',  
	                    }  
	                },   
				},
			plotOptions: {
					column: {
							depth: 40,
							stacking: true,
							grouping: true,
							groupZPadding: 10
					}
			},
			series: [{name: '警告',
						data: data[0],
						stack: 0//"警告"
						
					}, {name: '罚款',
						data: data[1],
						stack: 1//"罚款"
					}, {name: '没收',
						data: data[2],
						stack: 2//"没收"
					}, {name: '行政拘留',
						data: data[3],
						stack: 3//"行政拘留"
					}, {name: '吊销许可证',
						data: data[4],
						stack: 4//"吊销许可证"
					}, {name: '责令停产停业',
						data: data[5],
						stack: 5//"责令停产停业"
				    }, {name: '其它',
						data: data[6],
						stack: 6//"其它"
				    }]
	});  
   }
   //公用设施
	function ChengShiBuJianFun(data){
		var sum = 0;
		for (var one in data) {
			sum += Number(data[one]["value"]);
        }
		//datas = [['损毁', 10.0],['丢失', 20.0],['完好', 70.0],];
		var datas = [];
		for (var one in data) {
			datas.push([data[one]["type"],Number((Number(data[one]["value"])*100/sum).toFixed(1))]);
        }
		
		
		var chart = Highcharts.chart('ChengShiBuJian', {
			chart: {
				  backgroundColor: 'transparent',
					type: 'pie',
					options3d: {
							enabled: true,
							alpha: 45,
							beta: 0
					}
			},
			credits:{
			     enabled: false // 禁用版权信息
			},
			colors:['#9bc342','#4ebba1','#4899c9'],
			title: {
					text: '公用设施',
					  style: {
			                color: '#fff',      //字体颜色
			                "fontSize": "14px",   //字体大小
			            }
			},
		
			tooltip: {
					pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			},
			plotOptions: {
					pie: {
							allowPointSelect: true,
							cursor: 'pointer',
							depth: 35,
							size:"100%",
						/*	dataLabels: {
									enabled: true,
									format: '{point.name}'
							},*/
							dataLabels: {
								enabled: true,//是否直接呈现数据 也就是外围显示数据与否
								distance: -20,//通过设置这个属性，将每个小饼图的显示名称和每个饼图重叠
								  style: {
						                'color': '#fff',      //字体颜色
						                "fontSize": "14px",   //字体大小
						            },
								formatter: function(index) {
								return '<span style="color:#fff;">' + this.point.y +'%'+ '</span>';
								},
								
								},
			
					}
			},
			series: [{
					type: 'pie',
					name: '公用设施',
					data: datas
			}]
	});
	}
	//道路交通
	function DaoLuJiaoTongFun(data){
		var sum = 0;
		for (var one in data) {
			sum += Number(data[one]["value"]);
        }
		//datas = [['损毁', 20.0],['丢失', 20.0],['完好', 60.0],];
		var datas = [];
		for (var one in data) {
			datas.push([data[one]["type"],Number((Number(data[one]["value"])*100/sum).toFixed(1))]);
        }
		
		var chart = Highcharts.chart('DaoLuJiaoTong', {
			chart: {
				  backgroundColor: 'transparent',
					type: 'pie',
					options3d: {
							enabled: true,
							alpha: 45,
							beta: 0
					}
			},
			colors:['#4ebba1','#9764c7','#db993a'],
			credits:{
			     enabled: false // 禁用版权信息
			},
			title: {
					text: '道路交通',
					  style: {
			                color: '#fff',      //字体颜色
			                "fontSize": "14px",   //字体大小
			            }
			},
		
			tooltip: {
					pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			},
			plotOptions: {
					pie: {
							allowPointSelect: true,
							cursor: 'pointer',
							depth: 35,
							size:"100%",
						/*	dataLabels: {
									enabled: true,
									format: '{point.name}'
							},*/
							dataLabels: {
								enabled: true,//是否直接呈现数据 也就是外围显示数据与否
								distance: -20,//通过设置这个属性，将每个小饼图的显示名称和每个饼图重叠
								  style: {
						                'color': '#fff',      //字体颜色
						                "fontSize": "14px",   //字体大小
						            },
								formatter: function(index) {
								return '<span style="color:#fff;">' + this.point.y +'%'+ '</span>';
								},
								
								},
			
					}
			},
			series: [{
					type: 'pie',
					name: '道路交通',
					data: datas
			}]
	});
	}
	
	//市容环境--市容环境
	function ShiRongHuangjingFun(data){
		var sum = 0;
		for (var one in data) {
			sum += Number(data[one]["value"]);
        }
		//datas = [['损毁', 30.0],['丢失', 20.0],['完好', 50.0],];
		var datas = [];
		for (var one in data) {
			datas.push([data[one]["type"],Number((Number(data[one]["value"])*100/sum).toFixed(1))]);
        }
		
		var chart = Highcharts.chart('ShiRongHuangjing', {
			chart: {
				  backgroundColor: 'transparent',
					type: 'pie',
					options3d: {
							enabled: true,
							alpha: 45,
							beta: 0
					}
			},
			colors:['#db993a','#c34e70','#9764c7'],
			credits:{
			     enabled: false // 禁用版权信息
			},
			title: {
					text: '市容环境',
					  style: {
			                color: '#fff',      //字体颜色
			                "fontSize": "14px",   //字体大小
			            }
			},
		
			tooltip: {
					pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			},
			plotOptions: {
					pie: {
							allowPointSelect: true,
							cursor: 'pointer',
							depth: 35,
							size:"100%",
						/*	dataLabels: {
									enabled: true,
									format: '{point.name}'
							},*/
							dataLabels: {
								enabled: true,//是否直接呈现数据 也就是外围显示数据与否
								distance: -50,//通过设置这个属性，将每个小饼图的显示名称和每个饼图重叠
								  style: {
						                'color': '#fff',      //字体颜色
						                "fontSize": "14px",   //字体大小
						            },
								formatter: function(index) {
								return '<span style="color:#fff;">' + this.point.y +'%'+ '</span>';
								},
								
								},
			
					}
			},
			series: [{
					type: 'pie',
					name: '市容环境',
					data: datas
			}]
	});
	}
	
	