/**
 * Created by oHa on 2017/12/28.
 */
var windowsHeight, _fontSize = 14, _fontSize1 = 26, breakData = 8,
legendTop = '30%', radiusData = ['40%', '65%'], lengthECharts = 30,mapHeigth = '90%',zoom=14.75,centerCoordinate= [ 117.663920, 39.035650 ];
// 基础颜色表
var color = ['#4298af', '#89a54e', '#D87A82', '#4573a7', '#71588f', '#db843d',
		'#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f' ];
$(function() {
  //年月日
  $('#calendarYear').html(year)
  $('#calendarMonth').html(month)
  $('#calendarDay').html(day)
	//
	var context = $(".context").attr("content");
	
	//
	$('#main').height($(window).height());
	$('.height100').height($('#main').height() - 70);
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


	
	//非公有制经济组织类型统计
	$.getJSON(context + "/report/ccmPeopleAmount/getListCompType", function(
			data) {
		// 接收参数
		$.GetWorkSheets("ccmOrgNpseCompType", $.ToConvertA(data));
	});
	
	//重点类型统计
	$.getJSON(context + "/report/ccmPeopleAmount/getListCompImpoType", function(
			data) {
		// 接收参数
		$.GetWorkSheets("ccmOrgNpseCompImpoType", $.ToConvertA(data));
		//PopFlowTop();
		// 地图
		map();
		//事件流程
		AlarmFlowFun();
	});
	
	//全部房屋类型统计
	$.getJSON(context + "/report/ccmPeopleAmount/getListTypeAll", function(
			data) {
		//PopKeyEchartsFun()
		// 接收参数
		//$.GetWorkSheets("ccmPopTenantHouseType", $.ToConvertA(data));
		$.GeHouseState('ccmPopTenantHouseType',data)
	});
	
	// 实有人口统计情况
	$.getJSON(context + "/report/ccmPeopleAmount/getAnalyzePopData", function(
			data) {
	$("#registerPop").html(data[0]||0)
	$("#overseasPop").html(data[1]||0)
	$("#flowPop").html(data[2]||0)
	$("#noRegisterPop").html(data[3]||0)
	$("#permanentPop").html(data[4]||0)
		// 接收参数
		//RightTypeEchartsFun(data);
	});
	// 特殊人群分析
	$.getJSON(context + "/report/ccmPeopleAmount/getSpecialPopData", function(
			data) {
		// 接收参数
	  	NanDing(data)
	});
	// 各街道人口事件汇总
	/*$.getJSON(context + "/report/ccmPeopleAmount/findPeopleAndEventByArea", function(
			data) {
		// 接收参数
		var dataX = data["1"];
		var poepleDataY = data["2"];
		var eventDataY = data["3"];
	//2	AllEventAndPeopleFun(dataX, poepleDataY,eventDataY)
	});*/
	// 案事件 统计情况
	$.getJSON(context + "/report/ccmIncidentBegin/getItemBySum",
			function(data) {
				// 接收参数
			EventTotalFun(data);
			});
	// 今日案事件 统计情况
	getEventTodayTotalFun()
 window.onload=function(){
	 setInterval("getEventTodayTotalFun()", 300000); 
	}


	// 首页概况各种总数查询
	$.getJSON(context + "/report/ccmIncidentBegin/getEachAll",
		function(data) {
			// 接收参数
		EachAll(data);
		}
	);

	$(window).resize(function() {
		window.location.reload()
	})
	
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
	// 特殊人群分析
	function PopsNumEchartsFun(specialPopData) {
		var myChart = echarts.init(document.getElementById("PopsNumEcharts"));
		var color = [ '#4573a7', '#D87A82', '#89a54e', '#71588f', '#4298af',
				'#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc',
				'#92c3d4', '#ffdf5f' ];
		var data = [
		/*{
			value : specialPopData["value1"],
			name : '留守人员'
		}, */
		{
			value : specialPopData["value2"],
			name : '社区矫正人员'
		}, {
			value : specialPopData["value3"],
			name : '肇事肇祸等严重精神障碍患者'
		}, {
			value : specialPopData["value4"],
			name : '吸毒人员'
		}, {
			value : specialPopData["value5"],
			name : '安置帮教人员'
		}, {
			value : specialPopData["value6"],
			name : '艾滋病危险人员'
		}, {
			value : specialPopData["value11"],
			name : '重点上访人员'
		}, {
			value : specialPopData["value12"],
			name : '涉教人员'
		}, {
			value : specialPopData["value13"],
			name : '危险品从业人员'
		} ];
		var option = {
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)",
				confine : true
			},
			legend : {
				type : 'scroll',
				orient : 'vertical',
				//				top : legendTop,
				//				right : legendRight,
				left : 'left',
				top : 'middle',
				textStyle : {
					color : '#fff',
					fontSize : _fontSize,
				},
				data : [ '安置帮教人员','社区矫正人员', '肇事肇祸等严重精神障碍患者', '吸毒人员',  '艾滋病危险人员', '重点上访人员',
						'涉教人员', '危险品从业人员' ]
			},
			series : [ {
				name : '特殊人群占比',
				type : 'pie',
				radius : '70%',
				center : [ '70%', '50%' ],
				color : color,
				label : {
					normal : {
						show : false
					}
				},
				
				labelLine : {
					normal : {
						show : false
					}
				},
				data : data
			} ]
		};
		myChart.setOption(option, true);
	}
	// 待处理 已处理 总数
	function EventTotalFun(Data) {
		
		$('#Handlind').html(Data["1"]);//待处理
		$('#Handled').html(Data["0"]);//已处理
		$('#Total').html(Data["2"]);//总数
	}

	//首页概况各种总数查询
	function EachAll(Data){
		$("#eachAll1").html(Data["value1"]);//实有人口总数
		$("#eachAll2").html(Data["value2"]);//房屋总数
		$("#eachAll3").html(Data["value3"]);//楼栋总数
		$("#eachAll4").html(Data["value4"]);//网格总数
		$("#eachAll5").html(Data["value5"]);//网格人员
		$("#eachAll6").html(Data["value6"]);//重点企业
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
//				name : '易肇事肇祸精神病'
//			}, {
//				value : specialPopData["value4"],
//				name : '吸毒'
//			}, {
//				value : specialPopData["value5"],
//				name : '安置帮教'
//			}, {
//				value : specialPopData["value6"],
//				name : '艾滋病'
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
			    	  type: 'scroll',
		                orient: 'vertical',
		                left:'left',
		                top:'middle',
		                textStyle: {
		                    color: '#fff',
		                    fontSize: _fontSize,
		                },
			        "data": [
			        	"安置帮教",
			            "社区矫正",
			            "易肇事肇祸精神病",
			            "吸毒",
				        "艾滋病危险人员",
			        	"重点上访",
				        "涉教",
			            "危险品从业",
			        ],
			        "textStyle": {
			            "color": "#fff",
			            "fontSize": _fontSize,
			        }
			    },
			    "series": [{
			        "name": "",
			        "type": "pie",
			        radius : [ '40%', '100%' ],
			        "avoidLabelOverlap": false,
			        "startAngle": 90,
			         center : [ '48%', '50%' ],
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
			                "name": "社区矫正",
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
			                "name": "易肇事肇祸精神病",
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
			                "name": "吸毒",
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
			                "name": "安置帮教",
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
			                "name": "重点上访",
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
			                "name": "涉教",
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
			                "name": "危险品从业",
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

	//重点青少年统计


	// 地图
	function map() {
		 var centerCoordinate= [ 113.3895613562, 34.5213170808 ];
			viewSituationFun(centerCoordinate,'13.7');
		// 地图默认数据设置
		var defaultPrams = {
			id : 'mapMask',
			centerCoordinate : centerCoordinateSituation,
			zoom : zoom,
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
			zoom : zoom,
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
//		var zuobiao=[ 117.655920, 39.038050 ];
//		var zuobiao1=[ 117.653920, 39.035050 ];
//		var zuobiao2=[ 117.663920, 39.042050 ];
//		Map.postcomposeOlIndex('紧急',zuobiao,"123",'0')
//		Map.postcomposeOlIndex('紧急',zuobiao1,"1234",'0')
//		Map.postcomposeOlIndex('紧急',zuobiao2,"12345",'0')
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
                radius : [40,70],
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
	
	
	//事件流程
	function AlarmFlowFun(){
		var width=$(window).width();

        var symbolSize=30;
        var symbolSize1=30;
        var symbolSize2=30;
		var PC=[40.46607, 15.093863];
		var APP=[40.46607, 115.093863];
		var WeiXin=[40.46607, 215.093863];
		var FenPai=[180.65607, 115.093863];
		var ChuLi1=[520.0961, 15.093863];
		var ChuLi2=[520.0961, 115.093863];
		var ChuLi3=[520.0961, 215.093863];
		var KaoHe=[660.4361, 115.093863];
		var JieShu=[780.4361, 115.093863];
		var RenYuan1=[340.8361, 15.093863];
		var RenYuan2=[340.8361, 115.093863];
		var RenYuan3=[340.8361, 215.093863];
		if(width>=1680&&width<1920){
			  symbolSize=25;
			  symbolSize1=30;
		      symbolSize2=30;
			  PC=[40.46607, 15.093863];
			  APP=[40.46607, 100.093863];
			  WeiXin=[40.46607, 200.093863];
			  FenPai=[80.65607, 100.093863];
			  ChuLi1=[420.0961, 15.093863];
			  ChuLi2=[420.0961, 100.093863];
			  ChuLi3=[420.0961, 200.093863];
			  KaoHe=[560.4361, 100.093863];
			  JieShu=[680.4361, 100.093863];
			  RenYuan1=[240.8361, 15.093863];
			  RenYuan2=[240.8361, 100.093863];
			  RenYuan3=[240.8361, 200.093863];
		}else if(width>=1600&&width<1680){
			  symbolSize=20;
			  symbolSize1=30;
		      symbolSize2=25;
			  PC=[40.46607, 15.093863];
			  APP=[40.46607, 85.093863];
			  WeiXin=[40.46607, 155.093863];
			  FenPai=[140.65607, 85.093863];
			  ChuLi1=[390.0961, 15.093863];
			  ChuLi2=[390.0961, 85.093863];
			  ChuLi3=[390.0961, 155.093863];
			  KaoHe=[560.4361, 85.093863];
			  JieShu=[650.4361, 85.093863];
			  RenYuan1=[240.8361, 15.093863];
			  RenYuan2=[240.8361, 85.093863];
			  RenYuan3=[240.8361, 155.093863];
		}
		else if(width>=1440&&width<1600){
			 symbolSize=17;
			  symbolSize1=25;
		      symbolSize2=20;
			  PC=[40.46607, 15.093863];
			  APP=[40.46607, 75.093863];
			  WeiXin=[40.46607, 145.093863];
			  FenPai=[140.65607, 75.093863];
			  ChuLi1=[380.0961, 15.093863];
			  ChuLi2=[380.0961, 75.093863];
			  ChuLi3=[380.0961, 145.093863];
			  KaoHe=[490.4361, 75.093863];
			  JieShu=[580.4361, 75.093863];
			  RenYuan1=[270.8361, 15.093863];
			  RenYuan2=[270.8361, 75.093863];
			  RenYuan3=[270.8361, 145.093863];
		}else if(width<1440){
			 symbolSize=16;
			  symbolSize1=25;
		      symbolSize2=20;
			  PC=[40.46607, 15.093863];
			  APP=[40.46607, 65.093863];
			  WeiXin=[40.46607, 120.093863];
			  FenPai=[140.65607, 60.093863];
			  ChuLi1=[350.0961, 15.093863];
			  ChuLi2=[350.0961, 65.093863];
			  ChuLi3=[350.0961, 120.093863];
			  KaoHe=[450.4361, 65.093863];
			  JieShu=[550.4361, 65.093863];
			  RenYuan1=[250.8361, 15.093863];
			  RenYuan2=[250.8361, 65.093863];
			  RenYuan3=[250.8361, 120.093863];
		}
		
		
		    var queue =ctxStatic+"/common/index/images/flow/data-1516936989273-SJH71mdSM.png";
		    var table = ctxStatic+"/common/index/images/flow/data-1516338170066-HkGZ3e1rG.png";
		    var travltable = ctxStatic+"/common/index/images/flow/data-1516338063920-B1d5ixkrG.png";
		    var uploadedDataURL = ctxStatic+"/common/index/images/flow/data-1479697763933-ByhDrJlGx.json";
		    var unify = ctxStatic+"/common/index/images/flow/data-1516091863551-r1eJqEjEM.png";
		    var end = ctxStatic+"/common/index/images/flow/end.png";
		    var retail = ctxStatic+"/common/index/images/flow/data-1516091809749-SJcoYNs4z.png";
		    var household = ctxStatic+"/common/index/images/flow/data-1516091686336-BkRXF4oNz.png";
		    var travl = ctxStatic+"/common/index/images/flow/data-1516091446583-B1ySdVs4G.png";
		    var myChart = echarts.init(document.getElementById('AlarmFlow'));
		    $.get(uploadedDataURL, function (geoJson) {
		        myChart.hideLoading();
		        echarts.registerMap('wuhan', geoJson);
		        var allData = {
		            "citys": [
		                {
		                    "name": "PC采集",
		                    "value": PC,
		                    symbol: 'image://' + retail,
		                    "symbolSize": symbolSize,
		                    "itemStyle": {"normal": {"color": "#F58158"}}
		                },
		                {
		                    "name": "APP采集",
		                    "value": APP,
		                    symbol: 'image://' + travl,
		                    "symbolSize": symbolSize,
		                    "itemStyle": {"normal": {"color": "#F58158"}}
		                },
		                {
		                    "name": "微信采集",
		                    "value": WeiXin,
		                    symbol: 'image://' + household,
		                    "symbolSize": symbolSize,
		                    "itemStyle": {"normal": {"color": "#F58158"}}
		                },
		                {
		                    "name": "分派",
		                    "value": FenPai,
		                    symbol: 'image://' + travltable,
		                    "symbolSize": symbolSize,
		                    "itemStyle": {"normal": {"color": "#F58158"}}
		                },{
		                    "name": "处理",
		                    "value": ChuLi1,
		                    symbol: 'image://' + table,
		                    "symbolSize": symbolSize1,
		                    "itemStyle": {"normal": {"color": "#F58158"}}
		                },
		                {
		                    "name": "处理",
		                    "value": ChuLi2,
		                    symbol: 'image://' + table,
		                    "symbolSize": symbolSize1,
		                    "itemStyle": {"normal": {"color": "#F58158"}}
		                },
		                {
		                    "name": "处理",
		                    "value": ChuLi3,
		                    symbol: 'image://' + table,
		                    "symbolSize": symbolSize1,
		                    "itemStyle": {"normal": {"color": "#F58158"}}
		                },
		                
		                {
		                    "name": "考核",
		                    "value": KaoHe,
		                    symbol: 'image://' + unify,
		                    "symbolSize": symbolSize,
		                    "itemStyle": {"normal": {"color": "#F58158"}}
		                },
		                {
		                    "name": "结束",
		                    "value": JieShu,
		                    symbol: 'image://' + end,
		                    "symbolSize": symbolSize,
		                    "itemStyle": {"normal": {"color": "#F58158"}}
		                },
		                {
		                    "name": "人员",
		                    "value": RenYuan1,
		                    symbol: 'image://' + queue,
		                    "symbolSize": symbolSize2,
		                    "itemStyle": {"normal": {"color": "#F58158"}}
		                },
		                {
		                    "name": "人员",
		                    "value": RenYuan2,
		                    symbol: 'image://' + queue,
		                    "symbolSize": symbolSize2,
		                    "itemStyle": {"normal": {"color": "#F58158"}}
		                },
		                {
		                    "name": "人员",
		                    "value": RenYuan3,
		                    symbol: 'image://' + queue,
		                    "symbolSize": symbolSize2,
		                    "itemStyle": {"normal": {"color": "#F58158"}}
		                },
		            ],

		            'newCity': [],


		            "moveLines": [
		                {"fromName": "PC采集", "toName": "分派", "coords": [PC, FenPai]},
		                {"fromName": "APP采集", "toName": "分派", "coords": [APP, FenPai]},
		                {"fromName": "微信采集", "toName": "分派", "coords": [WeiXin, FenPai]},
		                {"fromName": "分派", "toName": "人员", "coords": [FenPai, RenYuan1]},
		                {"fromName": "分派", "toName": "人员", "coords": [FenPai, RenYuan2]},
		                {"fromName": "分派", "toName": "人员", "coords": [FenPai, RenYuan3]},
		                {"fromName": "人员", "toName": "处理", "coords": [RenYuan1, ChuLi1]},
		                {"fromName": "人员", "toName": "处理", "coords": [RenYuan2, ChuLi2]},
		                {"fromName": "人员", "toName": "处理", "coords": [RenYuan3, ChuLi3]},
		                {"fromName": "考核", "toName": "结束", "coords": [KaoHe, JieShu]},
		                {"fromName": "处理", "toName": "考核", "coords": [ChuLi1, KaoHe]},
		                {"fromName": "处理", "toName": "考核", "coords": [ChuLi2, KaoHe]},
		            ],

		            "newLines": [

		                {"fromName": "处理", "toName": "考核", "coords": [ChuLi3, KaoHe]},

		            ]
		        };

		      var   option = {
		           // backgroundColor: '#020933',
		            legend: {
		                show: true,
		                orient: 'vertical',
		                top: 'bottom',
		                left: 'right',
		                data: ['步骤', '流程'],
		                textStyle: {
		                    color: '#fff',
		                    fontSize : _fontSize
		                }
		            },
//		            tooltip: {
//		                trigger: 'item',
//		                show: true,
//		                alwaysShowContent: true,
//		                position: [185, 75],
//		                formatter: '{b}<br>MQ接收数据13278条<br>MQ取出数据13278条'
//		            },
		            geo: {
		                map: 'yincangditu',
		                label: {
		                    emphasis: {
		                        show: false
		                    }
		                },
		                roam: true,
		                itemStyle: {
		                    normal: {
		                        areaColor: '#323c48',
		                        borderColor: '#404a59'
		                    },
		                    emphasis: {
		                        areaColor: '#2a333d'
		                    }
		                }
		            },
		            series: [{
		                name: '步骤',
		                //type: 'effectScatter',
		                type: 'scatter',
		                coordinateSystem: 'geo',
		                zlevel: 2,
		                rippleEffect: {
		                    brushType: 'stroke',
		                    period: 7,
		                    scale: 26
		                },
		                label: {
		                    normal: {
		                        show: true,
		                        position: 'bottom',
		                        formatter: '{b}',
		                        color: 'white',
		                    },
		                    emphasis: {
		                        show: true,
		                        position: 'right',
		                        formatter: '{b}'
		                    }
		                },
		                symbolSize: 2,
		                showEffectOn: 'render',
		                itemStyle: {
		                    normal: {
		                        color: '#46bee9'
		                    }
		                },
		                data: allData.citys
		            }, {
		                name: '步骤',
		                type: 'effectScatter',
		                //type: 'scatter',
		                coordinateSystem: 'geo',
		                zlevel: 2,
		                rippleEffect: {
		                    brushType: 'stroke',
		                    period: 7,
		                    scale: 26
		                },
		                label: {
		                    normal: {
		                        show: true,
		                        position: 'top',
		                        formatter: '{b}',
		                        color: 'white',
		                    },
		                    emphasis: {
		                        show: true,
		                        position: 'right',
		                        formatter: '{b}'
		                    }
		                },
		                symbolSize: 2,
		                showEffectOn: 'render',
		                itemStyle: {
		                    normal: {
		                        color: '#46bee9'
		                    }
		                },
		                data: allData.newCity
		            },
		                {
		                    name: '流程',
		                    type: 'lines',
		                    coordinateSystem: 'geo',
		                    zlevel: 2,
		                    large: true,
		                    effect: {
		                        show: true,
		                        constantSpeed: 30,
		                        symbol: 'arrow',//ECharts 提供的标记类型包括 'circle', 'rect', 'roundRect', 'triangle', 'diamond', 'pin', 'arrow'
		                        symbolSize: 6,
		                        trailLength: 0,
		                    },

		                    lineStyle: {
		                        normal: {
		                            color: '#0fff17',
		                            /*
		                             color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
		                             offset: 0, color: '#58B3CC'
		                             }, {
		                             offset: 1, color: '#F58158'
		                             }], false),*/
		                            width: 2,
		                            opacity: 0.6,
		                            curveness: 0.1
		                        }
		                    },
		                    data: allData.moveLines
		                },
		                {
		                    name: '流程',
		                    type: 'lines',
		                    coordinateSystem: 'geo',
		                    zlevel: 2,
		                    large: true,
		                    effect: {
		                        show: true,
		                        constantSpeed: 30,
		                        symbol: 'arrow',//ECharts 提供的标记类型包括 'circle', 'rect', 'roundRect', 'triangle', 'diamond', 'pin', 'arrow'
		                        symbolSize: 6,
		                        trailLength: 0,
		                    },

		                    lineStyle: {
		                        normal: {
		                            color: 'rgb(255, 0, 0)',
		                            /*
		                             color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
		                             offset: 0, color: '#58B3CC'
		                             }, {
		                             offset: 1, color: '#F58158'
		                             }], false),*/
		                            width: 2,
		                            opacity: 1,
		                            curveness: 0.2
		                        }
		                    },
		                    data: allData.newLines
		                }
		            ]
		        };
		        myChart.setOption(option);
		    });

	}
	
	

	 function getEventTodayTotalFun(){
		$.getJSON( "/arjccm/a/report/ccmIncidentBegin/getItemBySumToday",
				function(data) {
					// 接收参数
					EventTodayTotalFun(data);
				}
			);
	}	
	//今日待处理 已处理 总数
	function EventTodayTotalFun(Data) {
		
		$('#HandlindToday').html(Data["0"]);//待处理
		$('#TotalToday').html(Data["1"]);//处理中
		$('#HandledToday').html(Data["2"]);//已处理
	}
