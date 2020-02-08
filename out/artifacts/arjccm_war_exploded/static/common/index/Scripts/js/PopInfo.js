/**
 * Created by oHa on 2017/12/28.
 */
var windowsHeight, _fontSize = 14, _fontSize1 = 26, breakData = 8;
legendTop = '30%', radiusData = [ 90, 65 ], lengthECharts = 30,
		mapHeigth = '90%';
// 基础颜色表
var color = [ '#D87A82', '#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d',
		'#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f' ];
$(function() {

	//
	var context = $(".context").attr("content");
	// 实有人口统计情况
	$.getJSON(context + "/report/ccmPeopleAmount/getAnalyzePopNewData", function(
			data) {
		// 接收参数
		RightTypeEchartsFun(data);
	});
	// 特殊人群分析
	$.getJSON(context + "/report/ccmPeopleAmount/getSpecialPopData", function(
			data) {
		// 接收参数
		PopsNumEchartsFun(data);
	});
	// 实有人口总数、新增CameraTotal/OnLineRate
	$.getJSON(context + "/report/ccmPeopleAmount/getnumPopData",
			function(data) {
				// 接收参数
				CameraTotalFun(data);
			});
	//  重点青少年分析
	$.getJSON(context + "/report/ccmPeopleAmount/getnumPopFlowTable", function(
			data) {
		// 接收参数
		PopFlowTableFun(data);
		PopKeyEchartsFun(data);

	});
	//本月人口信息PopFollowPop
	$.getJSON(context + "/report/ccmPeopleAmount/getnumPopFollowPop", function(
			data) {
		// 接收参数
		PopFollowPopFun(data);
	});
	//重点青少年统计
	$.getJSON(context + "/report/ccmPeopleAmount/getnumPopFollowEcharts",
			function(data) {
				// 接收参数
				PopFollowEchartsFun(data);
			});
	$(window).resize(function() {
		window.location.reload()
	})
	//
	$('#main').height($(window).height());
	$('.height100').height($('#main').height() - 86);
	windowsHeight = $(window).width();
	if (windowsHeight > 1600) {
		_fontSize = 14;
		legendTop = '20%';
		legendRight = '8%';
		radiusData = [ 90, 65 ];
		lengthECharts = 30;
		_fontSize1 = 26;
		breakData = 8;
		mapHeigth = '90%'
	} else {
		_fontSize = 10;
		legendTop = '15%';
		legendRight = '0%';
		radiusData = [ 60, 45 ];
		lengthECharts = 20;
		_fontSize1 = 14;
		breakData = 6;
		mapHeigth = '90%'
	}
	// 产权类型//实有人口分析
	//	RightTypeEchartsFun();
	// 特殊人群
	//	PopsNumEchartsFun();




	// 房屋关注程度
	//PopFollowEchartsFun();
	// 地图
	map();
	//实有人口分析
	function RightTypeEchartsFun(analyzePopData) {
		var option = {
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
				type : 'value',
				axisTick : {
					show : false
				},
				axisLine : {
					show : true,
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
					show : true,
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
						itemStyle : {
							normal : {
								show : true,
								color : new echarts.graphic.LinearGradient(0,
										0, 0, 1, [ {
											offset : 0,
											color : '#00FFE6'
										}, {
											offset : 1,
											color : '#007CC6'
										} ]),
								barBorderRadius : 0,
								borderWidth : 0,
								borderColor : '#333',
							}
						},
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
				left : 'right',
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
				radius : '75%',
				center : [ '35%', '50%' ],
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
	// 实有人口总数、新增CameraTotal/OnLineRate
	function CameraTotalFun(numPopData) {
		//console.log(numPopData);
		$("#CameraTotal").html(numPopData[0]);
		$("#OnLineRate").html(numPopData[1]);
	}
	//  重点青少年分析
	function PopFlowTableFun(numPopFlowTable) {
		//console.log(numPopData);
		if(numPopFlowTable[0]&&numPopFlowTable[0]["value1"]){
			$("#PopFlowTable01").html(numPopFlowTable[0]["value1"]);
		}
		if(numPopFlowTable[0]&&numPopFlowTable[0]["value2"]){
			$("#PopFlowTable02").html(numPopFlowTable[0]["value2"]);
		}
		if(numPopFlowTable[1]&&numPopFlowTable[1]["value1"]){
			$("#PopFlowTable03").html(numPopFlowTable[1]["value1"]);
		}
		if(numPopFlowTable[1]&&numPopFlowTable[1]["value2"]){
			$("#PopFlowTable04").html(numPopFlowTable[1]["value2"]);
		}
		if(numPopFlowTable[2]&&numPopFlowTable[2]["value1"]){
			$("#PopFlowTable05").html(numPopFlowTable[2]["value1"]);
		}
		if(numPopFlowTable[2]&&numPopFlowTable[2]["value2"]){
			$("#PopFlowTable06").html(numPopFlowTable[2]["value2"]);
		}
		if(numPopFlowTable[3]&&numPopFlowTable[3]["value1"]){
			$("#PopFlowTable07").html(numPopFlowTable[3]["value1"]);
		}
		if(numPopFlowTable[3]&&numPopFlowTable[3]["value2"]){
			$("#PopFlowTable08").html(numPopFlowTable[3]["value2"]);
		}
		if(numPopFlowTable[4]&&numPopFlowTable[4]["value1"]){
			$("#PopFlowTable09").html(numPopFlowTable[4]["value1"]);
		}
		if(numPopFlowTable[4]&&numPopFlowTable[4]["value2"]){
			$("#PopFlowTable10").html(numPopFlowTable[4]["value2"]);
		}
		if(numPopFlowTable[5]&&numPopFlowTable[5]["value1"]){
			$("#PopFlowTable11").html(numPopFlowTable[5]["value1"]);
		}
		if(numPopFlowTable[5]&&numPopFlowTable[5]["value2"]){
			$("#PopFlowTable12").html(numPopFlowTable[5]["value2"]);
		}
	}

	//重点人员统计
	function PopKeyEchartsFun(data) {
		var dataVal = []
		for (var one in data) {
			var type = data[one]["type"];
			if(type=="01"){
				dataVal.push({
					"name" : "闲散青少年",
					"value" : data[one]["value1"]
				})
			}
			if(type=="02"){
				dataVal.push({
					"name" : "不良行为青少年",
					"value" : data[one]["value1"]
				})
			}
			if(type=="03"){
				dataVal.push({
					"name" : "流浪乞讨未成年",
					"value" : data[one]["value1"]
				})
			}
			if(type=="04"){
				dataVal.push({
					"name" : "服刑人员未成年子女",
					"value" : data[one]["value1"]
				})
			}
			if(type=="05"){
				dataVal.push({
					"name" : "农村留守儿童",
					"value" : data[one]["value1"]
				})
			}
			if(type=="99"){
				dataVal.push({
					"name" : "其他",
					"value" : data[one]["value1"]
				})
			}
	    }
		
		
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
				data : [ '闲散青少年', '不良行为青少年', '流浪乞讨未成年', '服刑人员未成年子女', '农村留守儿童',
						'其他' ]
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
						show : false
					}
				},
				lableLine : {
					normal : {
						show : false
					},
					emphasis : {
						show : false
					}
				},
				data : dataVal
			} ]
		};
		// 初始化参数
		var myChart = echarts.init(document.getElementById('PopKeyEcharts'));
		myChart.setOption(option, true);

	}

	//本月人口信息PopFollowPop
	function PopFollowPopFun(numPopFollowPop) {
		//console.log(numPopData);
		$("#numPopFollowPop201").html(numPopFollowPop[1]["value1"]);
		$("#numPopFollowPop202").html(numPopFollowPop[1]["value2"]);
		$("#numPopFollowPop203").html(numPopFollowPop[1]["value3"]);
		$("#numPopFollowPop204").html(numPopFollowPop[1]["value4"]);
		$("#numPopFollowPop205").html(numPopFollowPop[1]["value5"]);
		$("#numPopFollowPop206").html(numPopFollowPop[1]["value6"]);
		$("#numPopFollowPop207").html(numPopFollowPop[1]["value7"]);
		$("#numPopFollowPop208").html(numPopFollowPop[1]["value8"]);
		$("#numPopFollowPop209").html(numPopFollowPop[1]["value9"]);
		$("#numPopFollowPop210").html(numPopFollowPop[1]["value10"]);
		$("#numPopFollowPop211").html(numPopFollowPop[1]["value17"]);//常住
		$("#numPopFollowPop212").html(numPopFollowPop[1]["value11"]);//重点上访
		$("#numPopFollowPop213").html(numPopFollowPop[1]["value12"]);//涉教人员
		$("#numPopFollowPop214").html(numPopFollowPop[1]["value13"]);//危险品从业人员
		$("#numPopFollowPop215").html(numPopFollowPop[1]["value14"]);//特殊关怀人员
		$("#numPopFollowPop216").html(numPopFollowPop[1]["value15"]);//老年人
		$("#numPopFollowPop217").html(numPopFollowPop[1]["value16"]);//党员
		//
		$("#numPopFollowPop101").html(numPopFollowPop[0]["value1"]);
		$("#numPopFollowPop102").html(numPopFollowPop[0]["value2"]);
		$("#numPopFollowPop103").html(numPopFollowPop[0]["value3"]);
		$("#numPopFollowPop104").html(numPopFollowPop[0]["value4"]);
		$("#numPopFollowPop105").html(numPopFollowPop[0]["value5"]);
		$("#numPopFollowPop106").html(numPopFollowPop[0]["value6"]);
		$("#numPopFollowPop107").html(numPopFollowPop[0]["value7"]);
		$("#numPopFollowPop108").html(numPopFollowPop[0]["value8"]);
		$("#numPopFollowPop109").html(numPopFollowPop[0]["value9"]);
		$("#numPopFollowPop110").html(numPopFollowPop[0]["value10"]);
		$("#numPopFollowPop111").html(numPopFollowPop[0]["value17"]);//常住
		$("#numPopFollowPop112").html(numPopFollowPop[0]["value11"]);//重点上访
		$("#numPopFollowPop113").html(numPopFollowPop[0]["value12"]);//涉教人员
		$("#numPopFollowPop114").html(numPopFollowPop[0]["value13"]);//危险品从业人员
		$("#numPopFollowPop115").html(numPopFollowPop[0]["value14"]);//特殊关怀人员
		$("#numPopFollowPop116").html(numPopFollowPop[0]["value15"]);//老年人
		$("#numPopFollowPop117").html(numPopFollowPop[0]["value16"]);//党员
	}



	//重点青少年统计
	function PopFollowEchartsFun(numPopFollowEcharts) {
		var dataName = new Array();
		var value = new Array();
		for (var i = 0; i < numPopFollowEcharts.length; i++) {
			dataName[i] = numPopFollowEcharts[i].type;
			value[i] = numPopFollowEcharts[i].value;
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
			            data : dataName,
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
		var myChart = echarts.init(document.getElementById('PopFollowEcharts'));
		myChart.setOption(option);
	}

	// 地图
	function map() {
        var centerCoordinate= [ 117.663920, 39.035650 ];
        viewSituationFun(centerCoordinate,'14.75')
		// 地图默认数据设置
		var defaultPrams = {
			id : 'map',
			centerCoordinate :centerCoordinateSituation,
		    //zoom : zoomPop,
			zoom : 12,
			maxZoom : 18,
			minZoom : 9.8,
			baseUrl : baseUrlT,
			
//			urlArr : [ {
//				'name' : '银川地图 ',
//				'url' : 'http://192.168.1.22:6080/arcgis/rest/services/YinChuan/MapServer',
//				'isShow' : true
//			} ],
			zoomShowOrHide : false,// 缩小放大
			overviewMap : false,
			selectPointerFlag:true
		// 鹰眼图
		}
	    var Map = new ArjMap.Map(defaultPrams);
		// 加载地图
		Map.init();
		
		
		$.getJSON(context + "/sys/map/orgAreaMap?type=1", function(data) {
			Map.addJSON1([ {
				'type' : 'communitys',
				'data' : data,
				'isShow' : true
			} ])
	});
		
		
		
		
		
		
		
		
		
		
		
		
		
/*
		var ValArr = [];
		var Data = data.features;
		var len = Data.length;
		if (len > 0) {
			for (var i = 0; i < len; i++) {
				ValArr.push({
					name : Data[i].properties.name,
					value : Math.floor(Math.random() * 100 + 20),
				})
			}
		}
		console.log(ValArr)
		var chart = echarts.init(document.getElementById('map'));
		echarts.registerMap('滨海', data);
		var option = {
			geo : {
				map : '滨海'
			},
			tooltip : { // 显示悬浮窗口
				trigger : 'item',
				textStyle : {
					color : '#fff',
					fontSize : _fontSize,
				},
				confine : true,
			},
			series : [ {
				type : 'map',
				name : '人数',
				map : '滨海', // 自定义扩展图表类型
				layoutSize : mapHeigth,
				//aspectScale : 1,
				itemStyle : {
					normal : {
						label : {
							show : true,
							textStyle : {
								color : "#fff",
								fontSize : _fontSize,
							}
						}
					}
				},

				data : ValArr,
			} ],
			visualMap : {
				min : 1,
				max : 100,
				text : [ 'High', 'Low' ],
				realtime : false,
				calculable : false,
				show : false,
				inRange : {
					color : [ '#e0ffff', '#006edd' ]
				},
			}
		}

		chart.setOption(option)
		/!*var option = {
			// title: {
			// text: '总数： 3000\n\n在线率：80%\n\n完好率：78%',
			// right: '10%',
			// top:'5%',
			// textStyle: {
			// color:'#fff'
			// },
			// backgroundColor: "rgba(128, 128, 128, 0.5)",//标题背景色，默认透明，颜色可以使用
			// RGB 表示，比如 'rgb(128, 128, 128)' ，如果想要加上 alpha 通道，可以使用 RGBA，比如
			// 'rgba(128, 128, 128, 0.5)'，也可以使用十六进制格式，比如 '#ccc'
			// borderColor: "red",//标题的边框颜色，颜色格式支持同backgroundColor
			// borderWidth: 2,//标题边框线宽，默认为0，可自行设置
			// shadowBlur: 10,//图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX,
			// shadowOffsetY 一起设置图形的阴影效果。
			// shadowColor: "black",
			// },
			tooltip : { // 显示悬浮窗口
				trigger : 'item',
				textStyle : {
					color : '#fff',
					fontSize : _fontSize,
				},
		        confine:true,
				formatter : function(params) {
					// 定义一个res变量来保存最终返回的字符结果,并且先把地区名称放到里面
					var res = params.name + '<br />';
					// 定义一个变量来保存series数据系列
					var myseries = option.series;
					// 循环遍历series数据系列
					for (var i = 0; i < myseries.length; i++) {
						// 在内部继续循环series[i],从data中判断：当地区名称等于params.name的时候就将当前数据和名称添加到res中供显示
						for (var k = 0; k < myseries[i].data.length; k++) {
							// console.log(myseries[i].data[k].name);
							// 如果data数据中的name和地区名称一样
							if (myseries[i].data[k].name == params.name) {
								// 将series数据系列每一项中的name和数据系列中当前地区的数据添加到res中
								if (myseries[i].name == '在线率'
										|| myseries[i].name == '完好率') {
									res += myseries[i].name + ':'
											+ myseries[i].data[k].value
											+ '%<br />';
								} else {
									res += myseries[i].name + ':'
											+ myseries[i].data[k].value
											+ '<br />';

								}
							}
						}
					}
					// 返回res
					// console.log(res);
					return res;
				},
			},
			series : [ {
				type : 'map',
				map : '铜仁市',
				name : '总数',
				layoutCenter : [ '50%', '45%' ],
				layoutSize : mapHeigth,
				aspectScale : 1,
				itemStyle : {
					normal : {
						label : {
							show : true,
							textStyle : {
								color : "#fff",
								fontSize : _fontSize,
							}
						}
					}
				},
				data : [ {
					name : '第一网格',
					value : 500
				}, {
					name : '第二网格',
					value : 600
				}, {
					name : '第三网格',
					value : 600
				}, {
					name : '第四网格',
					value : 400
				}, {
					name : '第五网格',
					value : 500
				}, {
					name : '第六网格',
					value : 550
				}, {
					name : '第七网格',
					value : 700
				}, {
					name : '第八网格',
					value : 800
				}, {
					name : '第九网格',
					value : 400
				}, {
					name : '第十网格',
					value : 300
				}, ]
			}, {
				type : 'map',
				map : '铜仁市',
				name : '在线率',
				layoutSize : '100%',
				layoutCenter : [ '50%', '50%' ],
				aspectScale : 1,
				label : {
					normal : {
						show : true,
					}
				},
				itemStyle : {
					normal : {
						label : {
							show : true,
							textStyle : {
								color : "#fff",
								fontSize : _fontSize,
							}
						}
					}
				},
				data : [ {
					name : '碧江区',
					value : 80
				}, {
					name : '万山区',
					value : 82
				}, {
					name : '江口县',
					value : 83
				}, {
					name : '玉屏县',
					value : 80
				}, {
					name : '石阡县',
					value : 75
				}, {
					name : '思南县',
					value : 78
				}, {
					name : '德江县',
					value : 73
				}, {
					name : '印江县',
					value : 82
				}, {
					name : '沿河县',
					value : 80
				}, {
					name : '松桃县',
					value : 75
				}, ]

			}, {
				type : 'map',
				map : '铜仁市',
				name : '完好率',
				layoutSize : '100%',
				layoutCenter : [ '50%', '50%' ],
				aspectScale : 1,
				itemStyle : {
					normal : {
						label : {
							show : true,
							textStyle : {
								color : "#fff",
								fontSize : _fontSize,
							}
						}
					}
				},
				data : [ {
					name : '碧江区',
					value : 70
				}, {
					name : '万山区',
					value : 72
				}, {
					name : '江口县',
					value : 75
				}, {
					name : '玉屏县',
					value : 76
				}, {
					name : '石阡县',
					value : 78
				}, {
					name : '思南县',
					value : 78
				}, {
					name : '德江县',
					value : 76
				}, {
					name : '印江县',
					value : 50
				}, {
					name : '沿河县',
					value : 42
				}, {
					name : '松桃县',
					value : 40
				}, ]

			} ],
			visualMap : {
				min : 10,
				max : 1000,
				text : [ '高', '低' ],
				realtime : false,
				calculable : false,
				inRange : {
					color : [ '#e0ffff', '#006edd' ]
				},
				show : false,
				hoverLink : false,

				seriesIndex : 0,
				textStyle : {
					color : '#fff',
					fontSize : _fontSize
				},
				formatter : function(value, a, b) {

					return value; // 范围标签显示内容。
				}
			},
		};*!/*/

	}
})
