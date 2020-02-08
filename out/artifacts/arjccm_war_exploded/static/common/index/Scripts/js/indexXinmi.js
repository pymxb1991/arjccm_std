/**
 * Created by oHa on 2017/12/28.
 */
var windowsHeight, _fontSize = 14, _fontSize1 = 26, breakData = 8;
legendTop = '30%', radiusData = [ 90, 65 ], lengthECharts = 30,
		mapHeigth = '100%';
// 基础颜色表
var color = [ '#aa4644', '#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d',
		'#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f' ];
var centerCoordinateData=[]
$(function() {
centerCoordinateData=centerCoordinate;

	var sdatab1={
			"尖山派出所": {
				 "实有人口":"11020", "常住人口":"10698", "重点人员":"21", "辖区面积":"57平方公里" , "行政村":"12个" , "村民组":"87个" , "风景区":"3个" 
			},
			"米村派出所": { 
				"实有人口":"38132", "常住人口":"37117",  "重点人员":"60","辖区面积":"63平方公里" , "行政村":"21个" , "村民组":"183个" , "特种行业":"3个" 
					},
			"袁庄派出所": { 
				"实有人口":"26110",   "常住人口":"25324",  "重点人员":"106","辖区面积":"60平方公里" , "行政村":"20个" , "涉爆单位":"14家" , "消防单位":"11家" 
					},
			"白寨派出所": { 
				"实有人口":"71000","常住人口":"69699", "重点人员":"88", "辖区面积":"97平方公里" , "行政村":"23个" , "重点单位":"53家" 
					},
			"新密城区": { 
				"实有人口":"140354",  "常住人口":"152506",  "重点人员":"108", "社区民警":"5" , "包村辅警":"5" 
					},
					
			"牛店派出所": { 
				"实有人口":"51024",  "常住人口":"50453", "重点人员":"97", "辖区面积":"83.5平方公里" , "行政村":"23个" , "消防单位":"13家" , "金融单位":"6家" 
					},
			"城关派出所": { 
				"实有人口":"30940",   "常住人口":"30243",  "重点人员":"40", "辖区面积":"25平方公里" , "村民居委会":"10个" , "县衙居委会":"10个" , "学校":"14所" 
					},
			"岳村派出所": { 
				"实有人口":"43527",  "常住人口":"42839",  "重点人员":"70", "辖区面积":"66平方公里" , "行政村":"22个"  , "自然组":"208个" , "涉爆单位":"2家" 
					},
			"来集派出所": { 
				"实有人口":"60308", "常住人口":"59220",  "重点人员":"77", "医疗机构":"147家" , "宗教场所":"6家"  , "金融机构":"4家" , "公共场所":"4家" 
					},
			"平陌派出所": { 
				"实有人口":"42351", "常住人口":"41865",  "重点人员":"58", "医疗机构":"21家" , "特种行业":"7家" ,"宗教场所":"6家" , "风景区":"2家" 
					},	
		"超化派出所": { 
			"实有人口":"52033",  "常住人口":"78230",  "重点人员":"148", "辖区面积":"78平方公里" , "村民组":"28个", "消防单位":"10家" , "加油站":"3家"   
				},			
		"刘寨派出所": { 
			"实有人口":"52033",  "常住人口":"49947",  "重点人员":"86", "行政村":"20个" , "学校":"12所" , "金融单位":"4家", "加油站":"6家"
				},	
				
		"曲梁派出所": { 
			"实有人口":"65299",  "常住人口":"81870",  "重点人员":"104", "辖区面积":"102平方公里" , "行政村":"29个" , "公共场所":"186家" , "消防单位":"16家"  
				},
		"大隗派出所": { 
			"实有人口":"65299",  "常住人口":"63170",  "重点人员":"88", "辖区面积":"58.8平方公里" , "行政村":"25个", "重点部位":"25个" , "重点单位":"196家" 
				},	
		"苟堂派出所": { 
			"实有人口":"59757",   "常住人口":"59071",  "重点人员":"82", "行政村":"24个" , "村民组":"313个"  , "新农村社区":"4个" , "特种行业":"19家" 
				},			
		"青屏街派出所": { 
			"实有人口":"74054", "常住人口":"64924",  "重点人员":"50", "辖区面积":"10.9平方公里" , "居委会":"16个"  , "各类场所":"349家", "重点单位":"66家"  
				},				
	}
	$(".paichusuosuoyou>div").hover(function() {
		var index = $(".paichusuosuoyou>div").index(this);
		var element=$(".paichusuosuoyou>div").eq(index);
		var offset = $(this).offset();
		console.log(index)
		
		
		
		var name=$(this).attr('attr-name');
		var data=sdatab1[name]; 
		var len=data.length;
		var html='';
		html+='<table>'; 
		for(var i in data){
			html+='<tr class="dialog4-center-tr"> ';              
			html+='<td  align="left" class="dialog4-center-name">'+i+'：</td>';                    
			html+='<td  align="left"><span >'+data[i]+'</span></td>';                    
			html+=' </tr>';  
		}
		
	            
//		html+='<tr class="dialog4-center-tr">';                
//		html+='<td  align="left" class="dialog4-center-name">常住人口：</td>';                   
//		html+='  <td  align="left"><span>'+$(this).attr('attr-changzhu')+'</span>人</td>';                 
//		html+='</tr>';               
//		html+='<tr class="dialog4-center-tr"> ';              
//		html+=' <td  align="left" class="dialog4-center-name">重点人员：</td>';                   
//		html+='<td  align="left"><span>'+$(this).attr('attr-zhongdian')+'</span>人</td>';                   
//		html+='</tr>';           
		html+=' </table>';        
	   $('.dialog4-header-name').html(name)
       $('.dialog4-center').html(html)
       $('.dialog4').css({"left":offset.left+90,"top":offset.top-100});
		$('.dialog4').show();
	}, function() {
	  $('.dialog4').hide();
	});

	
	
	
	 //年月日
	  $('#calendarYear').html(year)
	  $('#calendarMonth').html(month)
	  $('#calendarDay').html(day)
	  //时分秒
	   $('#calendarHH').html(hh)
		  $('#calendarMM').html(mm)
		  $('#calendarSS').html(ss)
	setInterval(function() {
		var now = new Date();
		hh = now.getHours(); //时  
		mm = now.getMinutes(); //分  
		ss = now.getSeconds(); //秒  
		if (hh < 10){
			hh = "0"+hh;
		}
		if (mm < 10)
			mm = '0'+mm;

		if (ss < 10){
			ss = '0'+ss;
		}
		 $('#calendarHH').html(hh)
		  $('#calendarMM').html(mm)
		  $('#calendarSS').html(ss)
}, 1000);
	
	
	
	//
	var context = $(".context").attr("content");
	// 实有人口统计情况
//	$.getJSON(context + "/report/ccmPeopleAmount/getAnalyzePopData", function(
//			data) {
//		// 接收参数
//		RightTypeEchartsFun(data);
//	});
//	// 特殊人群分析
//	$.getJSON(context + "/report/ccmPeopleAmount/getSpecialPopData", function(
//			data) {
//		// 接收参数
//		PopsNumEchartsFun(data);
//	});
//	// 实有人口总数、新增CameraTotal/OnLineRate
//	$.getJSON(context + "/report/ccmPeopleAmount/getnumPopData",
//			function(data) {
//				// 接收参数
//				CameraTotalFun(data);
//			});
//	//  重点青少年分析
//	$.getJSON(context + "/report/ccmPeopleAmount/getnumPopFlowTable", function(
//			data) {
//		// 接收参数
//		PopFlowTableFun(data);
//		PopKeyEchartsFun(data);
//
//	});
	//本月人口信息PopFollowPop
//	$.getJSON(context + "/report/ccmPeopleAmount/getnumPopFollowPop", function(
//			data) {
//		// 接收参数
//		
//		//重点人员
//		fault()//PopFollowPopFun(data);
//	});
	//重点青少年统计
//	$.getJSON(context + "/report/ccmPeopleAmount/getnumPopFollowEcharts",
//			function(data) {
//				// 接收参数
//				PopFollowEchartsFun(data);
//			});
//窗口变化刷新
//	$(window).resize(function() {
//		window.location.reload()
//	})
	
	

	
	//
	$('#main').height($(window).height());
	$('.height100').height($('#main').height() - 105);
	windowsHeight = $(window).width();
	if (windowsHeight > 1600) {
		_fontSize = 14;
		legendTop = '20%';
		legendRight = '8%';
		radiusData = [ 90, 65 ];
		lengthECharts = 30;
		_fontSize1 = 26;
		breakData = 8;
		mapHeigth = '100%'
	} else {
		_fontSize = 10;
		legendTop = '15%';
		legendRight = '0%';
		radiusData = [ 60, 45 ];
		lengthECharts = 20;
		_fontSize1 = 14;
		breakData = 6;
		mapHeigth = '100%'
	}
	// 产权类型//实有人口分析
	//	RightTypeEchartsFun();
	// 特殊人群
	//	PopsNumEchartsFun();


	 jQuery(".slideTxtBox").slide({
	        endFun: function (data) {
	        	if(data=='1'){
	        		 mapShequ();
	        		 var Data={
	     					"X轴":['03-30','04-06','04-13','04-20','04-27','05-04','05-11'],
	     					"事件数":['45','38','35','40','24','37','34'],
	     					"处理率":['88','95','92','97','90','94','90'],
	     			}
	        		mapAlarmWeekInfo('ccmOrgNpseCompImpoTypeEvent',Data)
	        		NanDingPlace()

	        		//人口情况
	        		$.getJSON(context + "/report/ccmPeopleStat/getPopInOut", function(
	        				data) {
	        			// 接收参数
	        			RenKouQingKuan(data);
	        		});
	        	}else if(data=='2'){
	        		  FangwuNumEchartsFun();
	        		  var Data={
		     					"X轴":['03-30','04-06','04-13','04-20','04-27','05-04','05-11'],
		     					"事件数":['17','8','14','8','6','13','14'],
		     					"处理率":['99','94','91','94','92','91','94'],
		     			}
	        		  mapAlarmWeekInfo('ccmOrgNpseCompImpoTypeEvent1',Data);
	        		  mapLoudongFun()
	        		  fangwuyinhuanleixingFun()
		          }else if(data=='3'){
	        	 // map();
	        	  bukongFun();
	        	  yujingxinxiFun();
	          }
	        }
	    });
	
	 //最近七周map-2
	 function mapAlarmWeekInfo(_id,Data){
		
			
			var dataX = Data["X轴"];
			var dataY1 = Data["事件数"];
			var dataY2 = Data["处理率"];
			// 社区事务
			$.AlarmWeekInfo(_id, dataX, dataY1, dataY2);
	 }
	
	// 房屋关注程度
	//PopFollowEchartsFun();
	// 地图
	//map();
	//map1();
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
				data : [ '户籍人口', '境外人口', '流动人口', '未落户人口' ]
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
		var color = [ '#4573a7', '#aa4644', '#89a54e', '#71588f', '#4298af',
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
			name : '易肇事精神病人'
		}, {
			value : specialPopData["value4"],
			name : '吸毒人员'
		}, {
			value : specialPopData["value5"],
			name : '刑释解教人员'
		}, {
			value : specialPopData["value6"],
			name : '艾滋病人'
		}, {
			value : specialPopData["value11"],
			name : '上访人员'
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
				data : [ '社区矫正人员', '易肇事精神病人', '吸毒人员', '刑释解教人员', '艾滋病人', '上访人员',
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
		$("#PopFlowTable01").html(numPopFlowTable[0]["value1"]);
		$("#PopFlowTable02").html(numPopFlowTable[0]["value2"]);
		$("#PopFlowTable03").html(numPopFlowTable[1]["value1"]);
		$("#PopFlowTable04").html(numPopFlowTable[1]["value2"]);
		$("#PopFlowTable05").html(numPopFlowTable[2]["value1"]);
		$("#PopFlowTable06").html(numPopFlowTable[2]["value2"]);
		$("#PopFlowTable07").html(numPopFlowTable[3]["value1"]);
		$("#PopFlowTable08").html(numPopFlowTable[3]["value2"]);
		$("#PopFlowTable09").html(numPopFlowTable[4]["value1"]);
		$("#PopFlowTable10").html(numPopFlowTable[4]["value2"]);
		$("#PopFlowTable11").html(numPopFlowTable[5]["value1"]);
		$("#PopFlowTable12").html(numPopFlowTable[5]["value2"]);
	}

	//重点人员统计
	function PopKeyEchartsFun(data) {
		var dataVal = [ {
			"name" : "闲散青少年",
			"value" : data[0]["value1"]
		}, {
			"name" : "不良行为青少年",
			"value" : data[1]["value1"]
		}, {
			"name" : "流浪乞讨未成年",
			"value" : data[2]["value1"]
		}, {
			"name" : "服刑人员未成年子女",
			"value" : data[3]["value1"]
		}, {
			"name" : "农村留守儿童",
			"value" : data[4]["value1"]
		}, {
			"name" : "其他",
			"value" : data[5]["value1"]
		}, ];
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
		$("#numPopFollowPop211").html(numPopFollowPop[1]["value"]);//未落户
		$("#numPopFollowPop212").html(numPopFollowPop[1]["value11"]);//上访
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
		$("#numPopFollowPop111").html(numPopFollowPop[0]["value"]);//未落户
		$("#numPopFollowPop112").html(numPopFollowPop[0]["value11"]);//上访
		$("#numPopFollowPop113").html(numPopFollowPop[0]["value12"]);//涉教人员
		$("#numPopFollowPop114").html(numPopFollowPop[0]["value13"]);//危险品从业人员
		$("#numPopFollowPop115").html(numPopFollowPop[0]["value14"]);//特殊关怀人员
		$("#numPopFollowPop116").html(numPopFollowPop[0]["value15"]);//老年人
		$("#numPopFollowPop117").html(numPopFollowPop[0]["value16"]);//党员
	}



	//重点青少年统计
	function PopFollowEchartsFun(numPopFollowEcharts) {
		var dataName = new Array();
		for (var i = 0; i < numPopFollowEcharts.length; i++) {
			dataName[i] = numPopFollowEcharts[i].type;
		}
		var option = {
			tooltip : {
				show : "true",
				trigger : 'axis',
				axisPointer : {
					type : 'shadow'
				},
				confine : true
			},
			grid : {
				left : '3%',
				right : '3%',
				bottom : '5%',
				top : '15%',
				containLabel : true
			},
			xAxis : [ {
				type : 'category',
				data : dataName,
				//data : [ numPopFollowEcharts[0]["type"], 'X2社区', 'X3社区', 'X4社区', 'X5社区', 'X6社区' ],
				axisPointer : {
					type : 'shadow'
				},
				axisLine : {
					lineStyle : {
						color : '#fff'
					}
				},
				axisLabel : {
					// formatter: '{value} %',
					textStyle : {
						color : '#fff',
						fontSize : _fontSize
					}
				},
			} ],
			yAxis : [ {
				type : 'value',
				min : 0,
				axisLabel : {
					formatter : '{value} ',
					textStyle : {
						color : '#fff',
						fontSize : _fontSize
					}
				},
				axisLine : {
					lineStyle : {
						color : '#fff'
					}
				},
				splitLine : {
					show : false
				}
			// max: 250,
			// interval: 50,
			} ],
			series : [ {
				name : '',
				type : 'line',
				data : numPopFollowEcharts,
				barWidth : 23,
				itemStyle : {
					normal : {
						color : '#89a54e'
					}
				}
			}, ]
		};
		var myChart = echarts.init(document.getElementById('PopFollowEcharts'));
		myChart.setOption(option);
	}

	// 地图
	function map() {
		// 地图默认数据设置
//		var defaultPrams = {
//			id : 'map',
//			centerCoordinate : [ 117.655920, 39.032550 ],
//			zoom : 14.4,
//			maxZoom : 18,
//			minZoom : 9.8,
//			baseUrl : [],
//			
////			urlArr : [ {
////				'name' : '银川地图 ',
////				'url' : 'http://192.168.1.22:6080/arcgis/rest/services/YinChuan/MapServer',
////				'isShow' : true
////			} ],
//			zoomShowOrHide : false,// 缩小放大
//			overviewMap : false,
//			selectPointerFlag:true
//		// 鹰眼图
//		}
//	    var Map = new ArjMap.Map(defaultPrams);
//		// 加载地图
//		Map.init();
//		
//		
//		$.getJSON(context + "/sys/map/orgAreaMap?type=1", function(data) {
//			Map.addJSON1([ {
//				'type' : 'communitys',
//				'data' : data,
//				'isShow' : true
//			} ])
//	});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		var ValArr = [];
//		var Data = data.features;
//		var len = Data.length;
//		if (len > 0) {
//			for (var i = 0; i < len; i++) {
//				ValArr.push({
//					name : Data[i].properties.name,
//					value : Math.floor(Math.random() * 100 + 20),
//				})
//			}
//		}
//		console.log(ValArr)
//		var chart = echarts.init(document.getElementById('map'));
//		echarts.registerMap('滨海', data);
//		var option = {
//			geo : {
//				map : '滨海'
//			},
//			tooltip : { // 显示悬浮窗口
//				trigger : 'item',
//				textStyle : {
//					color : '#fff',
//					fontSize : _fontSize,
//				},
//				confine : true,
//			},
//			series : [ {
//				type : 'map',
//				name : '人数',
//				map : '滨海', // 自定义扩展图表类型
//				layoutSize : mapHeigth,
//				//aspectScale : 1,
//				itemStyle : {
//					normal : {
//						label : {
//							show : true,
//							textStyle : {
//								color : "#fff",
//								fontSize : _fontSize,
//							}
//						}
//					}
//				},
//
//				data : ValArr,
//			} ],
//			visualMap : {
//				min : 1,
//				max : 100,
//				text : [ 'High', 'Low' ],
//				realtime : false,
//				calculable : false,
//				show : false,
//				inRange : {
//					color : [ '#e0ffff', '#006edd' ]
//				},
//			}
//		}
//
	var chart = echarts.init(document.getElementById('map'));
	var option = {
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
				name : '重点人员',
				layoutCenter : [ '50%', '55%' ],
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
					name : '百花巷社区',
					value : 60
				}, {
					name : '气象街社区',
					value : 60
				}, {
					name : '农业路社区',
					value : 0
				}, {
					name : '嵩阳路社区',
					value : 60
				}, {
					name : '青屏大街社区',
					value : 0
				}, {
					name : '长乐路社区',
					value : 40
				}, {
					name : '北密新路社区',
					value : 60
				}, {
					name : '开阳路社区',
					value : 0
				}, {
					name : '北文峰路社区',
					value : 400
				}, {
					name : '幸福街社区',
					value : 0
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
				max : 100,
				text : [ '高', '低' ],
				realtime : false,
				calculable : false,
				inRange : {
					color : ['#89a54e', '#DEE035', '#EE8819','#D11A1C']
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
		};
	chart.setOption(option)
	}
	
	/*自定义*/
	
	/*自定义*/
	
	
	
	function map1(){
		 // --- 地图 ---
        var myChart2 = echarts.init(document.getElementById('map'));
        var geoCoordMap = {
        	    '上海': [121.4648, 31.2891],
        	    '东莞': [113.8953, 22.901],
        	    '东营': [118.7073, 37.5513],
        	    '中山': [113.4229, 22.478],
        	    '临汾': [111.4783, 36.1615],
        	    '临沂': [118.3118, 35.2936],
        	    '丹东': [124.541, 40.4242],
        	    '丽水': [119.5642, 28.1854],
        	    '乌鲁木齐': [87.9236, 43.5883],
        	    '佛山': [112.8955, 23.1097],
        	    '保定': [115.0488, 39.0948],
        	    '兰州': [103.5901, 36.3043],
        	    '包头': [110.3467, 41.4899],
        	    '北京': [116.4551, 40.2539],
        	    '北海': [109.314, 21.6211],
        	    '南京': [118.8062, 31.9208],
        	    '南宁': [108.479, 23.1152],
        	    '南昌': [116.0046, 28.6633],
        	    '南通': [121.1023, 32.1625],
        	    '厦门': [118.1689, 24.6478],
        	    '台州': [121.1353, 28.6688],
        	    '合肥': [117.29, 32.0581],
        	    '呼和浩特': [111.4124, 40.4901],
        	    '咸阳': [108.4131, 34.8706],
        	    '哈尔滨': [127.9688, 45.368],
        	    '唐山': [118.4766, 39.6826],
        	    '嘉兴': [120.9155, 30.6354],
        	    '大同': [113.7854, 39.8035],
        	    '大连': [122.2229, 39.4409],
        	    '天津': [117.4219, 39.4189],
        	    '太原': [112.3352, 37.9413],
        	    '威海': [121.9482, 37.1393],
        	    '宁波': [121.5967, 29.6466],
        	    '宝鸡': [107.1826, 34.3433],
        	    '宿迁': [118.5535, 33.7775],
        	    '常州': [119.4543, 31.5582],
        	    '广州': [113.5107, 23.2196],
        	    '廊坊': [116.521, 39.0509],
        	    '延安': [109.1052, 36.4252],
        	    '张家口': [115.1477, 40.8527],
        	    '徐州': [117.5208, 34.3268],
        	    '德州': [116.6858, 37.2107],
        	    '惠州': [114.6204, 23.1647],
        	    '成都': [103.9526, 30.7617],
        	    '扬州': [119.4653, 32.8162],
        	    '承德': [117.5757, 41.4075],
        	    '拉萨': [91.1865, 30.1465],
        	    '无锡': [120.3442, 31.5527],
        	    '日照': [119.2786, 35.5023],
        	    '昆明': [102.9199, 25.4663],
        	    '杭州': [119.5313, 29.8773],
        	    '枣庄': [117.323, 34.8926],
        	    '柳州': [109.3799, 24.9774],
        	    '株洲': [113.5327, 27.0319],
        	    '武汉': [114.3896, 30.6628],
        	    '汕头': [117.1692, 23.3405],
        	    '江门': [112.6318, 22.1484],
        	    '沈阳': [123.1238, 42.1216],
        	    '沧州': [116.8286, 38.2104],
        	    '河源': [114.917, 23.9722],
        	    '泉州': [118.3228, 25.1147],
        	    '泰安': [117.0264, 36.0516],
        	    '泰州': [120.0586, 32.5525],
        	    '济南': [117.1582, 36.8701],
        	    '济宁': [116.8286, 35.3375],
        	    '海口': [110.3893, 19.8516],
        	    '淄博': [118.0371, 36.6064],
        	    '淮安': [118.927, 33.4039],
        	    '深圳': [114.5435, 22.5439],
        	    '清远': [112.9175, 24.3292],
        	    '温州': [120.498, 27.8119],
        	    '渭南': [109.7864, 35.0299],
        	    '湖州': [119.8608, 30.7782],
        	    '湘潭': [112.5439, 27.7075],
        	    '滨州': [117.8174, 37.4963],
        	    '潍坊': [119.0918, 36.524],
        	    '烟台': [120.7397, 37.5128],
        	    '玉溪': [101.9312, 23.8898],
        	    '珠海': [113.7305, 22.1155],
        	    '盐城': [120.2234, 33.5577],
        	    '盘锦': [121.9482, 41.0449],
        	    '石家庄': [114.4995, 38.1006],
        	    '福州': [119.4543, 25.9222],
        	    '秦皇岛': [119.2126, 40.0232],
        	    '绍兴': [120.564, 29.7565],
        	    '聊城': [115.9167, 36.4032],
        	    '肇庆': [112.1265, 23.5822],
        	    '舟山': [122.2559, 30.2234],
        	    '苏州': [120.6519, 31.3989],
        	    '莱芜': [117.6526, 36.2714],
        	    '菏泽': [115.6201, 35.2057],
        	    '营口': [122.4316, 40.4297],
        	    '葫芦岛': [120.1575, 40.578],
        	    '衡水': [115.8838, 37.7161],
        	    '衢州': [118.6853, 28.8666],
        	    '西宁': [101.4038, 36.8207],
        	    '西安': [109.1162, 34.2004],
        	    '贵阳': [106.6992, 26.7682],
        	    '连云港': [119.1248, 34.552],
        	    '邢台': [114.8071, 37.2821],
        	    '邯郸': [114.4775, 36.535],
        	    '新密': [113.4668, 34.6234],
        	    '鄂尔多斯': [108.9734, 39.2487],
        	    '重庆': [107.7539, 30.1904],
        	    '金华': [120.0037, 29.1028],
        	    '铜川': [109.0393, 35.1947],
        	    '银川': [106.3586, 38.1775],
        	    '镇江': [119.4763, 31.9702],
        	    '长春': [125.8154, 44.2584],
        	    '长沙': [113.0823, 28.2568],
        	    '长治': [112.8625, 36.4746],
        	    '阳泉': [113.4778, 38.0951],
        	    '青岛': [120.4651, 36.3373],
        	    '韶关': [113.7964, 24.7028]
        	};
        	var BJData = [
        	    [{
        	        name: '广州',
        	        value: 70
        	    }, {
        	        name: '新密'
        	    }],
        	    [{
        	        name: '哈尔滨',
        	        value: 30
        	    }, {
        	        name: '新密'
        	    }],
        	    [{
        	        name: '青岛',
        	        value: 50
        	    }, {
        	        name: '新密'
        	    }],
        	    [{
        	        name: '南昌',
        	        value: 20
        	    }, {
        	        name: '新密'
        	    }],
        	    [{
        	        name: '银川',
        	        value: 10
        	    }, {
        	        name: '新密'
        	    }],
        	    [{
        	        name: '拉萨',
        	        value: 80
        	    }, {
        	        name: '新密'
        	    }],
        	    [{
        	        name: '贵阳',
        	        value: 55
        	    }, {
        	        name: '新密'
        	    }],
        	    [{
        	        name: '乌鲁木齐',
        	        value: 90
        	    }, {
        	        name: '新密'
        	    }]
        	];
        	var convertData = function(data) {
        	    var res = [];
        	    for (var i = 0; i < data.length; i++) {
        	        var dataItem = data[i];
        	        var fromCoord = geoCoordMap[dataItem[0].name];
        	        var toCoord = geoCoordMap[dataItem[1].name];
        	        if (fromCoord && toCoord) {
        	            res.push([{
        	                coord: fromCoord,
        	                value: dataItem[0].value
        	            }, {
        	                coord: toCoord,
        	            }]);
        	        }
        	    }
        	    return res;
        	};

        	var color = ['#a6c84c', '#ffa022', '#46bee9'];
        	var series = [];
        	[
        	    ['新密', BJData]
        	].forEach(function(item, i) {
        	    series.push(

        	        {
        	            type: 'lines',
        	            zlevel: 2,
        	            effect: {
        	                show: true,
        	                period: 4,
        	                trailLength: 0.02,
        	                symbol: 'arrow',
        	                symbolSize: 5,
        	            },
        	            lineStyle: {
        	                normal: {
        	                    width: 1,
        	                    opacity: 0.6,
        	                    curveness: 0.2
        	                }
        	            },

        	            data: convertData(item[1])
        	        }, {
        	            type: 'effectScatter',
        	            coordinateSystem: 'geo',
        	            zlevel: 2,
        	            rippleEffect: {
        	                period: 4,
        	                brushType: 'stroke',
        	                scale: 4
        	            },
        	            label: {
        	                normal: {
        	                    show: true,
        	                    position: 'right',
        	                    offset: [5, 0],
        	                    formatter: '{b}'
        	                },
        	                emphasis: {
        	                    show: true
        	                }
        	            },
        	            symbol: 'circle',
        	            symbolSize: function(val) {
        	                return 4 + val[2] / 10;
        	            },
        	            itemStyle: {
        	                normal: {
        	                    show: false,
        	                    color: '#f00'
        	                }
        	            },
        	            data: item[1].map(function(dataItem) {
        	                return {
        	                    name: dataItem[0].name,
        	                    value: geoCoordMap[dataItem[0].name].concat([dataItem[0].value])
        	                };
        	            }),
        	        },
        	        //被攻击点
        	        {
        	            type: 'scatter',
        	            coordinateSystem: 'geo',
        	            zlevel: 2,
        	            rippleEffect: {
        	                period: 4,
        	                brushType: 'stroke',
        	                scale: 4
        	            },
        	            label: {
        	                normal: {
        	                    show: true,
        	                    position: 'right',
        	                    //			                offset:[5, 0],

        	                    color: '#00ffff',
        	                    formatter: '{b}',
        	                    textStyle: {
        	                        color: "#00ffff"
        	                    }
        	                },
        	                emphasis: {
        	                    show: true
        	                }
        	            },
        	            symbol: 'pin',
        	            symbolSize: 30,
        	            itemStyle: {
        	                normal: {
        	                    show: true,
        	                    color: '#9966cc'
        	                }
        	            },
        	            data: [{
        	                name: item[0],
        	                value: geoCoordMap[item[0]].concat([100]),
        	            }],
        	        }
        	    );
        	});

        	option = {
//        	    backgroundColor: '#404a59',

        	    visualMap: {
        	        min: 0,
        	        max: 100,
        	        calculable: true,
        	        color: ['#ff3333', 'orange', 'yellow', 'lime', 'aqua'],
        	        textStyle: {
        	            color: '#fff'
        	        }
        	    },
        	    geo: {
        	        map: 'china',
        	        label: {
        	            emphasis: {
        	                show: false
        	            }
        	        },
        	        roam: true,
        	        layoutCenter: ['50%', '53%'],
        	        layoutSize: "108%",
//        	        itemStyle: {
//        	            normal: {
//        	                color: 'rgba(51, 69, 89, .5)',
//        	                borderColor: 'rgba(100,149,237,1)'
//        	            },
//        	            emphasis: {
//        	                color: 'rgba(37, 43, 61, .5)'
//        	            }
//        	        }
        	        itemStyle: {
                        normal: {
                            areaColor: 'transparent',
                            borderColor: '#3fdaff',
                            borderWidth: 2,
                            shadowColor: 'rgba(63, 218, 255, 0.5)',
                            shadowBlur: 30
                        },
                        emphasis: {
                            areaColor: '#2B91B7',
                        }
                    }
        	    },

        	    series: series
        	};
        	myChart2.setOption(option);
	}
	
	
	//重点人员
	function fault() {



	    var bgColor = '#0078ff';
	    var containers = getClassNames('echarts3-2', 'div');

	    function getClassNames(classStr, tagName) {
	        if (document.getElementsByClassName) {
	            return document.getElementsByClassName(classStr)
	        } else {
	            var nodes = document.getElementsByTagName(tagName),
					ret = [];
	            for (i = 0; i < nodes.length; i++) {
	                if (hasClass(nodes[i], classStr)) {
	                    ret.push(nodes[i])
	                }
	            }
	            return ret;
	        }
	    }
	    function hasClass(tagStr, classStr) {
	        var arr = tagStr.className.split(/\s+/); //这个正则表达式是因为class可以有多个,判断是否包含  
	        for (var i = 0; i < arr.length; i++) {
	            if (arr[i] == classStr) {
	                return true;
	            }
	        }
	        return false;
	    }
	    options = [{
	        title: {
	          //  text: data[0].Count,
	            text: '36',
	            x: '48%',
	            y: '35%',
	            textAlign: "center",
	            itemGap: 20,
	            textStyle: {
	                fontWeight: 'bold',
	                fontSize: 24,
	                color: '#eea807'
	            },
	            subtextStyle: {
	                fontWeight: 'bold',
	                fontSize: 20,
	                color: '#eea807'
	            },
	            subtext: '5%',
	        },
	        series: [{
	            name: ' ',
	            type: 'pie',
	            radius: ['75%', '100%'],
	            startAngle: 225,
	            color: [new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
	                offset: 0,
	                color: '#00a2ff'
	            }, {
	                offset: 1,
	                color: '#70ffac'
	            }]), "transparent"],
	            hoverAnimation: false,
	            legendHoverLink: false,
	            itemStyle: {
	                normal: {
	                    borderColor: "transparent",
	                    borderWidth: "10"
	                },
	                emphasis: {
	                    borderColor: "transparent",
	                    borderWidth: "10"
	                }
	            },
	            z: 10,
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data: [{
	                value: 70
	            }, {
	                value: 25
	            }]
	        },
	        {
	            name: '',
	            type: 'pie',
	            radius: ['75%', '100%'],
	            startAngle: 225,
	            color: ["#153664", "transparent"],
	            hoverAnimation: false,
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data: [{
	                value: 75
	            }, {
	                value: 25
	            }]
	        }

	        ]
	    },
	    {
	        title: {
	           // text: data[1].Count,
	            text: '30',
	            x: '48%',
	            y: '35%',
	            textAlign: "center",
	            itemGap: 20,
	            textStyle: {
	                fontWeight: 'bold',
	                fontSize: 24,
	                color: '#eea807'
	            },
	            subtextStyle: {
	                fontWeight: 'bold',
	                fontSize: 20,
	                color: '#eea807'
	            },
	            subtext: '5%',
	        },
	        series: [{
	            name: ' ',
	            type: 'pie',
	            radius: ['75%', '100%'],
	            startAngle: 225,
	            color: [new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
	                offset: 0,
	                color: '#00a2ff'
	            }, {
	                offset: 1,
	                color: '#70ffac'
	            }]), "transparent"],
	            hoverAnimation: false,
	            legendHoverLink: false,
	            itemStyle: {
	                normal: {
	                    borderColor: "transparent",
	                    borderWidth: "10"
	                },
	                emphasis: {
	                    borderColor: "transparent",
	                    borderWidth: "10"
	                }
	            },
	            z: 10,
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data: [{
	                value: 70
	            }, {
	                value: 25
	            }]
	        },
	        {
	            name: '',
	            type: 'pie',
	            radius: ['75%', '100%'],
	            startAngle: 225,
	            color: ["#153664", "transparent"],
	            hoverAnimation: false,
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data: [{
	                value: 75
	            }, {
	                value: 25
	            }]
	        }

	        ]
	    }, {
	        title: {
	            //text: data[2].Count,
	            text: '26',
	            x: '48%',
	            y: '35%',
	            textAlign: "center",
	            itemGap: 20,
	            textStyle: {
	                fontWeight: 'bold',
	                fontSize: 24,
	                color: '#eea807'
	            },
	            subtextStyle: {
	                fontWeight: 'bold',
	                fontSize: 20,
	                color: '#eea807'
	            },
	            subtext: '4%',
	        },
	        series: [{
	            name: ' ',
	            type: 'pie',
	            radius: ['75%', '100%'],
	            startAngle: 225,
	            color: [new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
	                offset: 0,
	                color: '#00a2ff'
	            }, {
	                offset: 1,
	                color: '#70ffac'
	            }]), "transparent"],
	            hoverAnimation: false,
	            legendHoverLink: false,
	            itemStyle: {
	                normal: {
	                    borderColor: "transparent",
	                    borderWidth: "10"
	                },
	                emphasis: {
	                    borderColor: "transparent",
	                    borderWidth: "10"
	                }
	            },
	            z: 10,
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data: [{
	                value: 90
	            }, {
	                value: 25
	            }]
	        }, {
	            name: '',
	            type: 'pie',
	            radius: ['75%', '100%'],
	            startAngle: 225,
	            color: ["#153664", "transparent"],
	            hoverAnimation: false,
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data: [{
	                value: 75
	            }, {
	                value: 25
	            }]
	        }

	        ]
	    },
	    {
	        title: {
	           // text: data[3].Count,
	            text: '10',
	            x: '48%',
	            y: '35%',
	            textAlign: "center",
	            itemGap: 20,
	            textStyle: {
	                fontWeight: 'bold',
	                fontSize: 24,
	                color: '#eea807'
	            },
	            subtextStyle: {
	                fontWeight: 'bold',
	                fontSize: 20,
	                color: '#eea807'
	            },
	            subtext: '3%',
	        },
	        series: [{
	            name: ' ',
	            type: 'pie',
	            radius: ['75%', '100%'],
	            startAngle: 225,
	            color: [new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
	                offset: 0,
	                color: '#00a2ff'
	            }, {
	                offset: 1,
	                color: '#70ffac'
	            }]), "transparent"],
	            hoverAnimation: false,
	            legendHoverLink: false,
	            itemStyle: {
	                normal: {
	                    borderColor: "transparent",
	                    borderWidth: "10"
	                },
	                emphasis: {
	                    borderColor: "transparent",
	                    borderWidth: "10"
	                }
	            },
	            z: 10,
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data: [{
	                value: 100
	            }, {
	                value: 25
	            }]
	        }, {
	            name: '',
	            type: 'pie',
	            radius: ['75%', '100%'],
	            startAngle: 225,
	            color: ["#153664", "transparent"],
	            hoverAnimation: false,
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data: [{
	                value: 75
	            }, {
	                value: 25
	            }]
	        }

	        ]
	    },
	    ];


	    var charts = [];
	    for (var i = 0; i < options.length; ++i) {

	        var chart = echarts.init(containers[i]);

	        if (i > 0) {
	            options[i].series[0].silent = true;
	        }
	        var value = parseInt(Math.random() * 100) + 1,
	            value_ = 75 * value / 100;
	    /*    var value = data[i].Rate,
	            value_ = 75 * value / 100;*/
	        options[i].title.subtext = value + "%";
	        options[i].series[0].data[0].value = value_;
	        options[i].series[0].data[1].value = 100 - value_;
	        chart.setOption(options[i]);
	        charts.push(chart);
	    }
	}

	
	
	//******************************************新加
//	 $("div.list_lh").myScroll({
//         speed: 40, //数值越大，速度越慢
//         rowHeight: 28.38 //li的高度
//     });
	var cont=0
	setInterval(function(){
		$('.wrap').removeClass('active1');
		$('.wrap').removeClass('active2');
		$('.wrap').removeClass('active3');
		$('.wrap').removeClass('active4');
		cont++
		$('.wrap').addClass('active'+cont+'');
		if(cont==4){
			cont=0;
		}
		
	},1000)
	
	function bukongFun(){
	    var myChart = echarts.init(document.getElementById('bukong'));
	    var data = [
	        [0, 1, '一'],
	        [1, 0, '二'],
	        [2, -1, '三'],
	        [3, 0, '四'],
	        [4, 0, '五']
	    ]
	    var data1=['群组','列控','撤控','续控','审批']
	  var  option = {
	        xAxis: {
	            show: false,
	            type: 'value',
	            min: 0,
	            max: 6
	        },
	        yAxis: {
	            show: false,
	            type: 'value',
	            min: 0,
	            max: 4
	        },
	        grid: {
	            bottom: 0,
	            top: 25,
	            left: 0,
	            right: 0
	        },
	        series: [{
	            type: 'custom',
	            name: 'custom',
	            itemStyle: {
	                normal: {
	                    color: new echarts.graphic.LinearGradient(0,0,0,1, [
	                        {offset: 0, color: '#63E4FB'},
	                        {offset: 1, color: '#028BBB'}
	                    ]),
	                    shadowBlur: 20,
	                    shadowOffsetX: -15,
	                    shadowOffsetY: 15,
	                    shadowColor: 'rgba(0,0,0,0.8)'
	                },
	                emphasis: {
	                    color: new echarts.graphic.LinearGradient(0.5,0,0.5,1, [
	                        {offset: 0, color: '#63E4FBCC'},
	                        {offset: 1, color: '#028BBBCC'}
	                    ])
	                }
	            },
	            renderItem: function(params, api) {
	                var value = api.value(0);
	                var diff = api.value(1);
	                var name = api.value(2);
	                var size = (100 - 30) / (15 - 0) * value + 50;
	                var coord = api.coord([params.dataIndexInside + 1, (params.dataIndexInside) % 3 + 1]);
	                var x = coord[0];
	                var y = coord[1];
	                var d = (size / 2) * Math.cos(45 / 180);
	                var points = {
	                    left: [x - d, y],
	                    right: [x + d, y],
	                    top: [x, y - d],
	                    bottom: [x, y + d]
	                }
	                var valueLength = String(value).length;
	                var valueWidth = 12 * valueLength;
	                var unitWidth = 12;
	                var iconWidth = diff ? 5 : 0;
	                var labelWidth = valueWidth + unitWidth + iconWidth;
	                var labelHeight = 18
	                return {
	                    type: 'group',
	                    children: [
	                        {
	                            type: 'polygon',
	                            shape: {
	                                points: [points.left, points.top, points.right, points.bottom, points.left]
	                            },
	                            style: api.style(),
	                            styleEmphasis: api.styleEmphasis()
	                        },
	                        {
	                            type: 'group',
	                            children: [
	                                {
	                                    type: 'text',
	                                    style: {
	                                        text: data1[value],
	                                        x: x - labelWidth / 2-5,
	                                        y: y - labelHeight / 2,
	                                        fill: '#fff',
	                                        font: 'normal 18px "Microsoft YaHei", sans-serif'
	                                    }
	                                },
	                                {
	                                    type: 'text',
	                                    style: {
	                                        text: '',
	                                        x: x - labelWidth / 2 + valueWidth,
	                                        y: y - 5,
	                                        fill: '#fff',
	                                    }
	                                },
	                                {
	                                    type: 'text',
	                                    style: {
	                                        text: '' ,
	                                        x: x - labelWidth / 2 + valueWidth + unitWidth,
	                                        y: y - 5,
	                                        fill: '#fff',
	                                    }
	                                }
	                            ]
	                        },
	                        {
	                            type: 'text',
	                            style: {
	                                text: name,
	                                x: x - (name.length * 12 / 2),
	                                y: y - d - 18,
	                                fill: '#8492A6',
	                                font: 'normal 12px "Microsoft YaHei", sans-serif'
	                            }
	                        }
	                    ]
	                }
	            },
	            data: data,
	            animationDuration: 1500,
	            animationEasing: 'sinusoidalInOut',
	            animationDelay: function(idx) {
	                return idx * 300;
	            }
	        }]
	    };
	    myChart.setOption(option);
	}
	
	
	
function yujingxinxiFun(){
	var myChart = echarts.init(document.getElementById('yujingxinxi'));
	myChart.showLoading();


	var data2 = [{
	    name: "信息",
	    label: {
	        normal: {
	            backgroundColor: '#725bb8'
	        }
	    },
	    children: [{
	        name: "信息处置",
	        children: [{
	            name: "一级"
	        },{
	            name: "二级"
	        }]
	    }, {
	        name: "信息统计",
	        children: [{
	            name: "非正常上访"
	        }, {
	            name: "涉军群体等"
	        }]
	    }, {
	        name: "任务统计",
	        children: [{
	            name: "待处理"
	        },{
	            name: "已处理"
	        }]
	    }]
	}];

	myChart.hideLoading();

	myChart.setOption(option = {
	    tooltip: {
	        trigger: 'item',
	        formatter: '{b}'
	    },
	    series: [
	        {
	            type: 'tree',
	            name: '信息上报',
	            data: data2,
	            top: '0%',
	            bottom:'0%',
	            right: '30%',
	            symbolSize: 1,
	            initialTreeDepth: 30,
	            label: {
	                normal: {
	                    position: 'center',
	                    verticalAlign: 'middle',
	                    align: 'left',
	                    backgroundColor: '#7049f0',
	                    color: '#fff',
	                    padding: 3,
	                    formatter: [
	                        '{box|{b}}'
	                    ].join('\n'),
	                    rich: {
	                        box: {
	                            height: 14,
	                            color: '#fff',
	                            padding: [3, 3],
	                            align: 'center'
	                        }
	                    }
	                }
	            },
	            leaves: {
	                label: {
	                    normal: {
	                        position: 'center',
	                        verticalAlign: 'middle',
	                        align: 'left',
	                        backgroundColor: '#c44eff',
	                        formatter: [
	                            '{box|{b}}'
	                        ].join('\n'),
	                        rich: {
	                            box: {
	                                height: 14,
	                                color: '#fff',
	                                padding: [0, 3],
	                                align: 'center'
	                            }
	                        }
	                    }
	                }
	            },
	            expandAndCollapse: true,
	            animationDuration: 550,
	            animationDurationUpdate: 750
	        }
	    ]
	});

	myChart.setOption(option);
}





//数据集控

//shujujikongFun()
function shujujikongFun(){
	var myChart = echarts.init(document.getElementById('shujujikong'));
    var uploadedDataURL = "/arjccm/static/common/index/images/flow/data-1479697763933-ByhDrJlGx.json";
    var table = "/arjccm/static/common/index/images/pop/flow/data-1516333073341-SyYMOkJSM.png";
    var householdtable = "/arjccm/static/common/index/images/pop/flow/data-1516333049746-S1fbuJyBG.png";
    var retailtable = "/arjccm/static/common/index/images/pop/flow/data-1516332870727-ryJUw1kBz.png";
    var travltable = "/arjccm/static/common/index/images/pop/flow/data-1516332865580-Bk5Swk1rG.png";
    var unify = "/arjccm/static/common/index/images/pop/flow/data-1516091863551-r1eJqEjEM.png";
    var retail = "/arjccm/static/common/index/images/pop/flow/renkou.png";
    var household = "/arjccm/static/common/index/images/pop/flow/shijian.png";
    var finance = "/arjccm/static/common/index/images/pop/flow/data-1516091523271-rJiFdNj4f.png";
    var travl = "/arjccm/static/common/index/images/pop/flow/fangwu.png";
    var zhianp="/arjccm/static/common/index/images/pop/flow/zhian.png";
    var gaodianp="/arjccm/static/common/index/images/pop/flow/gaodian.png";
    var daolup="/arjccm/static/common/index/images/pop/flow/daolu.png";
    
    var sanwei3dp="/arjccm/static/common/index/images/pop/flow/3dditu.png";
    var sanwei2dp=	"/arjccm/static/common/index/images/pop/flow/2dditu.png";
    
    var cheliangp=	"/arjccm/static/common/index/images/pop/flow/cheliang.png";
    var zhifanyip="/arjccm/static/common/index/images/pop/flow/zhifayi.png";
    
    var chezhanp="/arjccm/static/common/index/images/pop/flow/chezhan.png";;
    var shangchangp="/arjccm/static/common/index/images/pop/flow/shangchang.png";;
    var shequp="/arjccm/static/common/index/images/pop/flow/shequpng.png";
    var renliankakoup="/arjccm/static/common/index/images/pop/flow/renliankakou.png";
    
    
    var zhiankakoup="/arjccm/static/common/index/images/pop/flow/zhiankakou.png";;
    var jiaotongp="/arjccm/static/common/index/images/pop/flow/jiaotong.png";;
    var shoufeizhanp="/arjccm/static/common/index/images/pop/flow/shoufeizhan.png";
    var zhianjianchazhanp="/arjccm/static/common/index/images/pop/flow/zhianjianchazhan.png";
    
    
    var dingweixinxip=	"/arjccm/static/common/index/images/pop/flow/2dditu.png";
    
    var dianziweilanp="/arjccm/static/common/index/images/pop/flow/dianziweilan.png";
    var jianyup="/arjccm/static/common/index/images/pop/flow/jianyu.png";
    var jichangp="/arjccm/static/common/index/images/pop/flow/jichang.png";
    
    var wifitanzhenp="/arjccm/static/common/index/images/pop/flow/wifi.png";
    var yiliaop="/arjccm/static/common/index/images/pop/flow/yiliao.png";
    var jinrongp="/arjccm/static/common/index/images/pop/flow/jinrong.png";
    $.get(uploadedDataURL, function (geoJson) {
        myChart.hideLoading();
        echarts.registerMap('wuhan', geoJson);
        var renkou =[80.46607, 100.093863];
        var fangwu =[80.46607, 180.093863];
        var wanggehua=[300.65607,215.093863,4];
        var shijian=[80.46607, 260.093863];
        var zhuanwang1=[500.8361,375.093863];
        var pingtai=[900.4361,375.093863,4];
        var shipin=[300.65607,430.093863];
        var sanwei=[300.65607,640.093863];
        var ditu=[300.65607,535.093863];
        var dingweixinxi=[232.65607,810.093863];
        
        
        var zhian=[80.46607, 350.093863];
        var gaodian=[80.46607, 430.093863];
        var daolu=[80.46607, 510.093863];
        
        
        var sanwei2d=[80.46607, 600.093863];
        var sanwei3d=[80.46607, 680.093863];
        
        var cheliang=[80.46607, 770.093863];
        var zhifanyi=[80.46607, 850.093863];
        
        
        var zhuanwang2=[1300.8361,375.093863];
        
        var chezhan=[1800.46607, 100.093863];
        var shangchang=[1800.46607, 180.093863];
        var shequ=[1800.46607, 260.093863];
        var renliankakou=[1500.65607,215.093863,4];
        
      
        
        
        var zhiankakou=[1500.65607,430.093863];
        var jiaotong=[1800.46607, 350.093863];
        var shoufeizhan=[1800.46607, 510.093863];
        var zhianjianchazhan=[1800.46607, 430.093863];
        
        var dianziweilan=[1500.65607,535.093863];
        var jianyu=[1800.46607, 600.093863];
        var jichang=[1800.46607, 680.093863];
        
        var wifitanzhen= [1500.65607,810.093863];
        var jinrong=[1800.46607, 770.093863];
        var yiliao=[1800.46607, 850.093863];
        
        var allData = {
        		"citys":[
        		{"name":"人口","value":renkou,symbol: 'image://' + retail,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"房屋","value":fangwu,symbol: 'image://' + travl,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"事件","value":shijian,symbol: 'image://' + household,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"网格化","value":wanggehua,symbol: 'image://' + retailtable,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"视频监控","value":shipin,symbol: 'image://' + travltable,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"地图","value":sanwei,symbol: 'image://' + householdtable,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"专网","value":zhuanwang1,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"平台","value":pingtai,symbol: 'image://' + unify,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"治安监控","value":zhian,symbol: 'image://' + zhianp,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"高点监控","value":gaodian,symbol: 'image://' + gaodianp,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"道路监控","value":daolu,symbol: 'image://' + daolup,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"三维地图","value":sanwei3d,symbol: 'image://' + daolup,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"二维地图","value":sanwei2d,symbol: 'image://' + daolup,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},

        		{"name":"车辆","value":cheliang,symbol: 'image://' + cheliangp,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"执法仪","value":zhifanyi,symbol: 'image://' + zhifanyip,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"定位","value":dingweixinxi,symbol: 'image://' + dingweixinxip,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},

        		{"name":"专网","value":zhuanwang2,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"车站","value":chezhan,symbol: 'image://' + chezhanp,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"商场","value":shangchang,symbol: 'image://' + shangchangp,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"社区","value":shequ,symbol: 'image://' + shequp,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"人脸卡口","value":renliankakou,symbol: 'image://' + renliankakoup,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},

        		
        		
        		
        		
        		{"name":"交通","value":jiaotong,symbol: 'image://' + jiaotongp,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"收费站","value":shoufeizhan,symbol: 'image://' + shoufeizhanp,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"治安检查站","value":zhianjianchazhan,symbol: 'image://' + zhianjianchazhanp,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"治安卡口","value":zhiankakou,symbol: 'image://' + zhiankakoup,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		
        		
        		{"name":"监狱","value":jianyu,symbol: 'image://' + jianyup,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"机场","value":jichang,symbol: 'image://' + jichangp,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"电子围栏","value":dianziweilan,symbol: 'image://' + dianziweilanp,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		
        		
        		
        		{"name":"医疗","value":yiliaop,symbol: 'image://' + yiliaop,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"金融","value":jinrong,symbol: 'image://' + jinrongp,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		{"name":"WIFI探针","value":wifitanzhen,symbol: 'image://' + wifitanzhenp,"symbolSize":2,"itemStyle":{"normal":{"color":"#F58158"}}},
        		
        		],


        		"moveLines":[
        		{"fromName":"人口","toName":"网格化","coords":[renkou,wanggehua]},
        		{"fromName":"房屋","toName":"网格化","coords":[fangwu,wanggehua]},
        		{"fromName":"事件","toName":"网格化","coords":[shijian,wanggehua]},
        		{"fromName":"网格化","toName":"专网","coords":[wanggehua,zhuanwang1]},
        		{"fromName":"专网","toName":"网格化","coords":[zhuanwang1,wanggehua]},
        		{"fromName":"视频监控","toName":"专网","coords":[shipin,zhuanwang1]},
        		{"fromName":"专网","toName":"视频监控","coords":[zhuanwang1,shipin]},
        		{"fromName":"地图","toName":"专网","coords":[sanwei,zhuanwang1]},
        		{"fromName":"专网","toName":"地图","coords":[zhuanwang1,sanwei]},
        		{"fromName":"专网","toName":"平台","coords":[zhuanwang1,pingtai]},
        		
        		{"fromName":"治安监控","toName":"视频监控","coords":[zhian,shipin]},
        		{"fromName":"高点监控","toName":"视频监控","coords":[gaodian,shipin]},
        		{"fromName":"道路监控","toName":"视频监控","coords":[daolu,shipin]},
        		
        		
        		{"fromName":"二维地图","toName":"地图","coords":[sanwei2d,sanwei]},
        		{"fromName":"三维地图","toName":"地图","coords":[sanwei3d,sanwei]},
        		
        		{"fromName":"车辆","toName":"定位信息","coords":[cheliang,dingweixinxi]},
        		{"fromName":"执法仪","toName":"定位信息","coords":[zhifanyi,dingweixinxi]},
        		{"fromName":"定位信息","toName":"专网","coords":[dingweixinxi,zhuanwang1]},

        		
        		
        		{"fromName":"车站","toName":"人脸卡口","coords":[chezhan,renliankakou]},
        		{"fromName":"商场","toName":"人脸卡口","coords":[shangchang,renliankakou]},
        		{"fromName":"社区","toName":"人脸卡口","coords":[shequ,renliankakou]},
        		{"fromName":"人脸卡口","toName":"专网","coords":[renliankakou,zhuanwang2]},
        		{"fromName":"专网","toName":"人脸卡口","coords":[zhuanwang2,renliankakou]},
        		{"fromName":"专网","toName":"平台","coords":[zhuanwang2,pingtai]},
        	
        		
        		{"fromName":"交通","toName":"治安卡口","coords":[jiaotong,zhiankakou]},
        		{"fromName":"收费站","toName":"治安卡口","coords":[shoufeizhan,zhiankakou]},
        		{"fromName":"治安检查站","toName":"治安卡口","coords":[zhianjianchazhan,zhiankakou]},
        		{"fromName":"治安卡口","toName":"专网","coords":[zhiankakou,zhuanwang2]},
        		{"fromName":"专网","toName":"治安卡口","coords":[zhuanwang2,zhiankakou]},

        		
        	
        		{"fromName":"监狱","toName":"电子围栏","coords":[jianyu,dianziweilan]},
        		{"fromName":"机场","toName":"电子围栏","coords":[jichang,dianziweilan]},
        		{"fromName":"电子围栏","toName":"专网","coords":[dianziweilan,zhuanwang2]},
        		{"fromName":"专网","toName":"电子围栏","coords":[zhuanwang2,dianziweilan]},
        		
        		
        		{"fromName":"医疗","toName":"WIFI探针","coords":[yiliao,wifitanzhen]},
        		{"fromName":"金融","toName":"WIFI探针","coords":[jinrong,wifitanzhen]},
        		{"fromName":"WIFI探针","toName":"专网","coords":[wifitanzhen,zhuanwang2]},
        		{"fromName":"专网","toName":"WIFI探针","coords":[zhuanwang2,wifitanzhen]},
        		
        		
        		]
        		};
        		  
        		option = {
        		   // backgroundColor: '#030832',
        		    title: {
        		        text: '数据集控与交换',
        		        left: 'center',
        		        textStyle: {
        		            color: '#fff'
        		        }
        		    },
        		    legend: {
        		        show: true,
        		        orient: 'vertical',
        		        top: 'bottom',
        		        left: 'right',
        		        data: ['地点', '线路'],
        		        textStyle: {
        		            color: '#fff'
        		        }
        		    },
        		    tooltip:{
        		        formatter:'{b}'
        		    },
        		    geo: {
        		        map: '北京市',
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
        		        name: '地点',
        		        type: 'effectScatter',
        		        coordinateSystem: 'geo',
        		        zlevel: 2,
        		        rippleEffect: {
        		            brushType: 'stroke',
        		            period:7,
        		            scale:26
        		        },
        		        label: {
        		            normal:{
        		              show:true,
        		              position:'top',
        		              formatter:'{b}'
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
        		        name: '线路',
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
        		                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
        		                        offset: 0, color: '#58B3CC'
        		                    }, {
        		                        offset: 1, color: '#F58158'
        		                    }], false),
        		                width: 1,
        		                opacity: 0.6,
        		                curveness: 0.2
        		            }
        		        },
        		        data: allData.moveLines
        		    }]
        		};
        		myChart.setOption(option);
        
    })

	//eventHuanbi
    function eventHuanbiFun(){
    	var myChart = echarts.init(document.getElementById('eventHuanbi'));
       
    	myChart.setOption(option);
    }
    
    
}


// 案事件 统计情况
$.getJSON("/arjccm/a/report/ccmIncidentBegin/findSolveByMon", function(
		data) {
	var Data={
			"X轴":['03-30','04-06','04-13','04-20','04-27','05-04','05-11'],
			"事件数":['380','248','300','480','200','340','267'],
			"处理率":['88','95','92','97','90','94','90'],
	}
	
	console.log(data)
	var dataX = Data["X轴"];
	var dataY1 = Data["事件数"];
	var dataY2 = Data["处理率"];
	// 社区事务
	$.AlarmWeekInfo("AlarmInfoWeekEcharts", dataX, dataY1, dataY2);

});
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
            left: '1%',
            right: '1%',
            bottom: '5%',
            containLabel: true
        },
        legend: {
            data: ['事件数', '处理率'],
            textStyle: {
                color: '#fff',
                fontSize: _fontSize
            },
            itemGap: 100
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
            interval: 25,
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
            name: '事件数',
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
            barWidth: 15,
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

//myFunction() ;
//function myFunction(){  
//    setTimeout(function(){
//    	 $('.radar').show();
//    	 setTimeout(function(){
//        	 $('.radar').hide()
//        	 $('.xinmimap').css('background','url(/arjccm/static/common/index/images/pop/xinmimap.png)  center no-repeat')
//        	 $('.xinmimap').css('background-size','100% 100%');
//        	 $('.rhombs').show()
//
//    	 },4000); 
//},500);  
//}  
$('.laiji').click(function(){
//	
//	 $('.xinmimap').css('background','url(/arjccm/static/common/index/images/pop/shequli4.png)  center no-repeat')
//     $('.xinmimap').css('background-size','100% 100%');
//	 $('.paichusuosuoyou').hide();
//	 $('.shequ1').show();
//	 $('.loudong1').hide();
//	 
//	 $('.map-calss1').html('60308')
//	 $('.map-calss2').html('58008')
//	 $('.map-calss3').html('5001')
//	 $('.map-calss4').html('77')
//	  $('.map-calss5').html('19292')
//	 $('.map-calss6').html('4')
//	 $('.map-calss6').html('16')
	//$('.arrow1 .next').click()
	 
})
$('.xinmichengqu').dblclick(function(){
	$('.arrow1 .next').click()
})

$('.shequ1').click(function(){
	 $('.xinmimap').css('background','url(/arjccm/static/common/index/images/pop/fangwuli3.png)  center no-repeat')
     $('.xinmimap').css('background-size','100% 100%');
	 $('.shequ1').hide();
	 $('.loudong1').show();
})
$('.xiangzhenli').click(function(){
	 $('.xinmimap').css('background','url(/arjccm/static/common/index/images/pop/xinmimap.png)  center no-repeat')
     $('.xinmimap').css('background-size','100% 100%');
	 $('.paichusuosuoyou').show();
	 $('.shequ1').hide();
	 $('.loudong1').hide();
})

$('.shequli').click(function(){
	 $('.xinmimap').css('background','url(/arjccm/static/common/index/images/pop/shequli4.png)  center no-repeat')
     $('.xinmimap').css('background-size','100% 100%');
})

$('.loudongli').click(function(){
	 $('.xinmimap').css('background','url(/arjccm/static/common/index/images/pop/fangwuli3.png)  center no-repeat')
     $('.xinmimap').css('background-size','100% 100%');
})
//shequtongjigFun()

function shequtongjigFun(){
    var myChart = echarts.init(document.getElementById('shequtongji'));
    var data = [
        [0, 1, '实有人口'],
        [1, 0, '常住人口'],
        [2, -1,'流动人员'],
        [3, 0, '重点人员'],
        [4, 0, '警务人员']
    ]
    var data1=['901867','759249 ','140012','1420','577']
    var data2=[18,18,18,5,5]
  var  option = {
        xAxis: {
            show: false,
            type: 'value',
            min: 0,
            max: 6
        },
        yAxis: {
            show: false,
            type: 'value',
            min: 0,
            max: 4
        },
        grid: {
            bottom: 0,
            top: 25,
            left: 0,
            right: 0
        },
        series: [{
            type: 'custom',
            name: 'custom',
            itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0,0,0,1, [
                        {offset: 0, color: '#63E4FB'},
                        {offset: 1, color: '#028BBB'}
                    ]),
                    shadowBlur: 20,
                    shadowOffsetX: -15,
                    shadowOffsetY: 15,
                    shadowColor: 'rgba(0,0,0,0.8)'
                },
                emphasis: {
                    color: new echarts.graphic.LinearGradient(0.5,0,0.5,1, [
                        {offset: 0, color: '#63E4FBCC'},
                        {offset: 1, color: '#028BBBCC'}
                    ])
                }
            },
            renderItem: function(params, api) {
                var value = api.value(0);
                var diff = api.value(1);
                var name = api.value(2);
                var size = (100 - 30) / (15 - 0) * value + 100;
                var coord = api.coord([params.dataIndexInside + 1, (params.dataIndexInside) % 3 + 1]);
                var x = coord[0];
                var y = coord[1];
                var d = (size / 2) * Math.cos(45 / 180);
                var points = {
                    left: [x - d, y],
                    right: [x + d, y],
                    top: [x, y - d],
                    bottom: [x, y + d]
                }
                var valueLength = String(value).length;
                var valueWidth = 12 * valueLength;
                var unitWidth = 12;
                var iconWidth = diff ? 5 : 0;
                var labelWidth = valueWidth + unitWidth + iconWidth;
                var labelHeight = 18
                return {
                    type: 'group',
                    children: [
                        {
                            type: 'polygon',
                            shape: {
                                points: [points.left, points.top, points.right, points.bottom, points.left]
                            },
                            style: api.style(),
                            styleEmphasis: api.styleEmphasis()
                        },
                        {
                            type: 'group',
                            children: [
                                {
                                    type: 'text',
                                    style: {
                                        text: data1[value],
                                        x: x - labelWidth / 2-data2[value],
                                        y: y - labelHeight / 2,
                                        fill: '#fff',
                                        font: 'normal 18px "Microsoft YaHei", sans-serif'
                                    }
                                },
                                {
                                    type: 'text',
                                    style: {
                                        text: '',
                                        x: x - labelWidth / 2 + valueWidth,
                                        y: y - 5,
                                        fill: '#fff',
                                    }
                                },
                                {
                                    type: 'text',
                                    style: {
                                        text: '' ,
                                        x: x - labelWidth / 2 + valueWidth + unitWidth,
                                        y: y - 5,
                                        fill: '#fff',
                                    }
                                }
                            ]
                        },
                        {
                            type: 'text',
                            style: {
                                text: name,
                                x: x - (name.length * 12 / 2),
                                y: y - d - 18,
                                fill: '#8492A6',
                                font: 'normal 12px "Microsoft YaHei", sans-serif'
                            }
                        }
                    ]
                }
            },
            data: data,
            animationDuration: 1500,
            animationEasing: 'sinusoidalInOut',
            animationDelay: function(idx) {
                return idx * 300;
            }
        }]
    };
    myChart.setOption(option);
}


$("#main").on("dblclick", "#map",function(){
	$('.arrow1 .next').click()
})
//楼栋弹框
$("#main").on("dblclick", "#maploudong",
    function (e) {
        //var id = $(this).attr('featureId') || "";
        //var pilesNum = $(this).attr('pilesNum'); // 层数
        //var elemNum = $(this).attr('elemNum'); // 单元数
        var buildName = '小区'; // 楼栋名称
        var houseId = null;
        var doorNum = null;
        
        //var id='01c24f6e637942a48557af0000000020';
        var id='19718';
        
        var pilesNum='18';
        var elemNum=2
        
        
        
        // 加载信息
        $('#buildBtn').trigger("click");
        $('#myModal').css('top',"10%")
        $("#build-details").load(ctx + "/house/ccmHouseBuildmanage/Maplist", {
            "id": id,
            "pilesNum": pilesNum,
            "buildName": buildName,
            "elemNum": elemNum,
            "type":127
        }, function() {
			$("#house-details,#pop-details").empty();
			 $("#build-details").show();
				
				//上下滚动
			var mainContainer = $('.house-center'), scrollToContainer = mainContainer
						.find('.ElemNum:last').height();//
				if (mainContainer.scrollTop() + mainContainer.height() == mainContainer[0].scrollHeight
						|| mainContainer[0].scrollHeight == 0) {
					$('.page-down').addClass('active')
				} else {
					$('.page-down').removeClass('active');
				}
				if (mainContainer.scrollTop() == 0) {
					$('.page-top').addClass('active')
				} else {
					$('.page-top').removeClass('active')
				}

		});
    });
// 房屋事件
$("#myModal ").on("click", ".houseclick",
    function (e) {
        // 取消原本事件
        e.preventDefault();
        var houseId = $(this).attr('houseId') || "";
        var buildName = $(this).attr('buildName'); // 楼栋名称
        var elemNum = $(this).attr('elemNum'); // 单元数
        var pilesNum = $(this).attr('pilesNum'); // 层数
        var doorNum = $(this).attr('doorNum'); // 门牌号
        var popId = null;
        
        
        // 判断是否为空 ，为空则不进行出发
        if (houseId == "" || houseId == undefined || houseId == null) {
            top.$.jBox.tip('暂无数据');
            return false;
        } else {
            // 页面加载
            $("#house-details").load(ctx + "/pop/ccmPopTenant/getHouseMapPopList", {
                    "id": houseId,
                    "pilesNum": pilesNum,
                    "buildName": buildName,
                    "elemNum": elemNum,
                },
                function () {
                    // 隐藏 原本数据
                    $("#build-details").hide();
                    var html = '';
                    html += '<button class="jbox-button closeHouse " style="padding: 0px 10px 0px 10px;">返回</button>';
                    html += '<button class="jbox-button" style="padding: 0px 10px 0px 10px;" 	data-dismiss="modal" aria-hidden="true">关闭</button>';
                    $('#modal-footer').html(html);
                });
        }
    });

// 人员信息弹框
$("#myModal").on("click", ".popclick",
    function (e) {
        e.preventDefault();
        var popId = $(this).attr('popId');
        var buildName = $(this).attr('buildName'); // 楼栋名称
        var elemNum = $(this).attr('elemNum'); // 单元数
        var pilesNum = $(this).attr('pilesNum'); // 层数
        var doorNum = $(this).attr('doorNum'); // 门牌号
        $("#pop-details").load(ctx + "/pop/ccmPeople/getMapPopForm", {
                "id": popId,
                "pilesNum": pilesNum,
                "buildName": buildName,
                "elemNum": elemNum,
            },
            function () {
                $("#house-details").hide();
            });
        var html = '';
        html += '<button class="jbox-button closePop" style="padding: 0px 10px 0px 10px;">返回</button>';
        html += '<button class="jbox-button" style="padding: 0px 10px 0px 10px;" 	data-dismiss="modal" aria-hidden="true">关闭</button>';
        $('#modal-footer').html(html)
    });

// 定义房屋返回事件
$("#myModal").on("click", ".closeHouse",
    function (e) {
        e.preventDefault();
        // 点击返回事件
        var html = '';
        html += '<button class="jbox-button" style="padding: 0px 10px 0px 10px;" 	data-dismiss="modal" aria-hidden="true">关闭</button>';
        $('#modal-footer').html(html);
        $("#build-details").show();
        $("#house-details").empty();
    });

// 定义人员返回事件
$("#myModal").on("click", ".closePop",
    function (e) {
        e.preventDefault();
        // 点击返回事件
        var html = '';
        html += '<button class="jbox-button closeHouse " style="padding: 0px 10px 0px 10px;">返回</button>';
        html += '<button class="jbox-button" style="padding: 0px 10px 0px 10px;" 	data-dismiss="modal" aria-hidden="true">关闭</button>';
        $('#modal-footer').html(html); 
        $("#house-details").show();
        $("#pop-details").empty();
    });

// 定义页面向左跳转事件
$("#myModal").on("click", ".page-left",
    function (e) {
        //  当前的 页码数 
        var Num = $(".houseView .house-center.NumView").attr("Num");
        // 当前 数据为 0
        if (Num <= 0) {
        	top.$.jBox.tip('已无数据');
            return;
        }

        $(".houseView .house-center .Elem" + Number(Num - 4)).removeClass("hide");
        $(".houseView .house-center .Elem" + Num).addClass("hide");
        $(".houseView .house-center.NumView").attr("Num", Number(Num) - 4);
    });

// 定义页面向右跳转事件
$("#myModal").on("click", ".page-right",
    function (e) {
        // 当前的 页码数 
        var Num = $(".houseView .house-center.NumView").attr("Num");
        var MaxNum = $(".houseView .house-center.NumView").attr("maxnum");
        // 当前 数据为 0
        if (Num >= MaxNum) {
            //alert("已无数据");
            top.$.jBox.tip('已无数据');
            return;
        }

        $(".houseView .house-center .Elem" + Number(Num + 4)).removeClass("hide");
        $(".houseView .house-center .Elem" + Num).addClass("hide");
        $(".houseView .house-center.NumView").attr("Num", Number(Num) + 4);
    });

//拖拽
$("#PanZoomBar").draggable({
	 axis:"y",
	 containment:"#ControlPanZoomBar",
	 start: function(e1) {
		 
		 high_top1 = parseInt(document.getElementById("PanZoomBar").style.top);
		 console.log(high_top1)
	 },
	 stop: function(e2) {
		 high_top2= parseInt(document.getElementById("PanZoomBar").style.top);
			// 此处的_this.maxZoom为地图最大的放大级数，250代替滑块150的为滑块轴的高度，16位滑块的高度单位为px,对计算出来的小数向上取整
		// var realzoom = Number(_this.maxZoom)- Number(_this.maxZoom) * high_top2 / (303 - 16);
		    // 设置view zoom值
		 console.log(high_top2)
		 if(high_top2>80&&high_top2<=100){
			 $('.xinmimap').css('background','url(/arjccm/static/common/index/images/pop/shequli4.png)  center no-repeat')
		     $('.xinmimap').css('background-size','100% 100%');
			 $('.paichusuosuoyou').hide();
			 $('.shequ1').show();
			 $('.loudong1').hide();
		 }else if(high_top2<=80){
			 $('.xinmimap').css('background','url(/arjccm/static/common/index/images/pop/fangwuli3.png)  center no-repeat')
		     $('.xinmimap').css('background-size','100% 100%');
			 
			 
			 $('.map-calss1').html('60308')
			 $('.map-calss2').html('58008')
			 $('.map-calss3').html('5001')
			 $('.map-calss4').html('77')
			  $('.map-calss5').html('19292')
			 $('.map-calss6').html('4')
			 $('.map-calss6').html('16')
		 }else if(high_top2>100){
			 $('.xinmimap').css('background','url(/arjccm/static/common/index/images/pop/xinmimap.png)  center no-repeat')
		     $('.xinmimap').css('background-size','100% 100%');
			 $('.paichusuosuoyou').show();
			 $('.shequ1').hide();
			 $('.loudong1').hide();
			 
			 
			 $('.map-calss1').html('901867')
			 $('.map-calss2').html('759249')
			 $('.map-calss3').html('140012')
			 $('.map-calss4').html('1420')
			 $('.map-calss5').html('192924')
			 $('.map-calss6').html('60')
			 $('.map-calss6').html('290')
		 }
	 }
});


})
function mapShequ(){
	
	$('#mapMask').html('')
	$('#map').html('')
	   var center= [ 113.36391845605729,34.549508895818356 ];
	        console.log(center)
		viewSituationFun(center,'13.7');
	// 地图默认数据设置
	var defaultPrams = {
		id : 'mapMask',//113.3595613562, 34.5373170808
		centerCoordinate : center,
		zoom : 13.7,
		maxZoom : 19,
		minZoom : 9.8,		
		baseUrl: baseUrlT,
		zoomShowOrHide : false,// 缩小放大
		overviewMap : false,
		selectPointerFlag:false
	// 鹰眼图
	}
    var Map1 = new ArjMap.Map(defaultPrams);
	// 加载地图
	Map1.drawMapSituation();
	
	
	
	
	// 地图默认数据设置
	var defaultPrams = {
		id : 'map',//113.3595613562, 34.5373170808
		centerCoordinate : centerCoordinateData,
		zoom : 13.7,
		maxZoom : 19,
		minZoom : 9.8,		
//		baseUrl : [{
//	        'type':'tiandi',
//			'id':'yingxiang',
//			'name' : '影像图 ',
//			'url' : 'http://t0.tianditu.com/DataServer?T=img_w&x={x}&y={y}&l={z}',
//			'isShow' : true,
//		}],
		zoomShowOrHide : false,// 缩小放大
		overviewMap : false,
		selectPointerFlag:false
	// 鹰眼图
	}
    var Map = new ArjMap.Map(defaultPrams);
	// 加载地图
	Map.drawMapSituation();
//	var zuobiao=[ 117.655920, 39.038050 ];
//	var zuobiao1=[ 117.653920, 39.035050 ];
//	var zuobiao2=[ 117.663920, 39.042050 ];
//	Map.postcomposeOlIndex('紧急',zuobiao,"123")
//	Map.postcomposeOlIndex('紧急',zuobiao1,"1234")
//	Map.postcomposeOlIndex('紧急',zuobiao2,"12345")
	$.getJSON( "/arjccm/a/sys/map/orgAreaMap?type=1", function(data) {
		Map.addJSON1([ {
			'type' : 'communitys',
			'data' : data,
			'isShow' : true
		} ])
});
	
	
}



//重点场所
function NanDingPlace(specialPopData){
	
	
	
	
//	var data = [
//		/*{
//			value : specialPopData["value1"],
//			name : '留守人员'
//		}, */
//		{
//			value : specialPopData["value2"],
//			name : '社区矫正'
//		}, {
//			value : specialPopData["value3"],
//			name : '易肇事精神病'
//		}, {
//			value : specialPopData["value4"],
//			name : '吸毒'
//		}, {
//			value : specialPopData["value5"],
//			name : '刑释解教'
//		}, {
//			value : specialPopData["value6"],
//			name : '艾滋病'
//		}, {
//			value : specialPopData["value11"],
//			name : '上访'
//		}, {
//			value : specialPopData["value12"],
//			name : '涉教'
//		}, {
//			value : specialPopData["value13"],
//			name : '危险品从业'
//		} ];

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
		        "data": [ "学校","医院","加油站","娱乐场所" ,"特种行业","涉危涉爆单位"],
		      /*  "textStyle": {
		            "color": "#fff",
		            "fontSize": _fontSize,
		        }*/
		    },
		    "series": [{
		        "name": "",
		        "type": "pie",
		         "radius" : [ '40%', '100%' ],
		        "avoidLabelOverlap": false,
		        "startAngle": 180,
		        "center" : [ '70%', '65%' ],
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
		                "value":45,
		                "name": "学校",
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
		                "value": 16,
		                "name": "医院",
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
		                "value": 23,
		                "name": "加油站",
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
		                "value": 33,
		                "name": "娱乐场所",
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
		                "value": 13,
		                "name": "特种行业",
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
		                "value": 29,
		                "name": "涉危涉爆单位",
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
		          
		        
		        ]
		    }]
		};
	var myChart = echarts.init(document.getElementById("PubNumEcharts"));
	myChart.setOption(option);
}


function RenKouQingKuan(data){
	/*var xData = function(){
	    var data = [];
	    for(var i=1;i<15;i++){
	     data.push(i+"号店");   
	    }
	    return data;
	}();

	option = {
	    "title": {
	        "text": "人流环比图", 
	        "subtext": "昨天 vs 前天", 
	        "x": "center"
	    }, 
	    "tooltip": {
	        "trigger": "axis", 
	        "axisPointer": {
	            "type": "shadow"
	        },
	    }, 
	    "grid": {
	        "borderWidth": 0, 
	        "y2": 120
	    }, 
	    "legend": {
	        "x": "right", 
	        "data": [ ]
	    }, 
	    "toolbox": {
	        "show": true, 
	        "feature": {
	            "restore": { }, 
	            "saveAsImage": { }
	        }
	    }, 
	    "calculable": true, 
	    "xAxis": [
	        {
	            "type": "category", 
	            "splitLine": {
	                "show": false
	            }, 
	            "axisTick": {
	                "show": false
	            }, 
	            "splitArea": {
	                "show": false
	            }, 
	            "axisLabel": {
	                "interval": 0, 
	                "rotate": 45, 
	                "show": true, 
	                "splitNumber": 15, 
	                "textStyle": {
	                    "fontFamily": "微软雅黑", 
	                    "fontSize": 12
	                }
	            }, 
	            "data": xData,
	        }
	    ], 
	    "yAxis": [
	        {
	            "type": "value", 
	            "splitLine": {
	                "show": false
	            }, 
	            "axisLine": {
	                "show": true
	            }, 
	            "axisTick": {
	                "show": false
	            }, 
	            "splitArea": {
	                "show": false
	            }
	        }
	    ], 
	    "dataZoom": [
	        {
	            "show": true, 
	            "height": 30, 
	            "xAxisIndex": [
	                0
	            ], 
	            bottom:40,
	            "start": 0, 
	            "end": 80
	        }, 
	        {
	            "type": "inside", 
	            "show": true, 
	            "height": 15, 
	            "xAxisIndex": [
	                0
	            ], 
	            "start": 1, 
	            "end": 35
	        }
	    ], 
	    "series": [
	        {
	            "name": "昨日", 
	            "type": "bar", 
	            "stack": "总量", 
	            "barMaxWidth": 50, 
	            "barGap": "10%", 
	            "itemStyle": {
	                "normal": {
	                    "barBorderRadius": 0, 
	                    "color": "rgba(60,169,196,0.5)", 
	                    "label": {
	                        "show": true, 
	                        "textStyle": {
	                            "color": "rgba(0,0,0,1)"
	                        }, 
	                        "position": "insideTop",
	                        formatter : function(p) {
		                                                return p.value > 0 ? (p.value ): '';
		                                            }
	                    }
	                }
	            }, 
	            "data": [
	                3709, 
	                2417, 
	                755, 
	                2610, 
	                1719, 
	                433, 
	                2544, 
	                4285, 
	                3372, 
	                2484, 
	                4078, 
	                1355, 
	                5208, 
	                1723
	            ], 
	        }, 
	        {
	            "name": "人流减少", 
	            "type": "bar", 
	            "stack": "总量", 
	            "itemStyle": {
	                "normal": {
	                    "color": "rgba(51,204,112,1)", 
	                    "barBorderRadius": 0, 
	                    "label": {
	                        "show": true, 
	                        "position": "top",
	                        formatter : function(p) {
		                                                return p.value > 0 ? ('▼'
		                                                        + p.value + '')
		                                                        : '';
		                                            }
	                    }
	                }
	            }, 
	            "data": [
	                386, 
	                0, 
	                0, 
	                122, 
	                261, 
	                171, 
	                0, 
	                40, 
	                246, 
	                0, 
	                815, 
	                275, 
	                570, 
	                159,
	            ]
	        }, 
	        {
	            "name": "人流增长", 
	            "type": "bar", 
	            "stack": "总量", 
	            "itemStyle": {
	                "normal": {
	                    "color": "rgba(193,35,43,1)", 
	                    "barBorderRadius": 0, 
	                    "label": {
	                        "show": true, 
	                        "position": "top",
	                        formatter : function(p) {
		                                                return p.value > 0 ? ('▲'
		                                                        + p.value + '')
		                                                        : '';
		                                            }
	                    }
	                }
	            }, 
	            "data": [
	                0, 
	                376, 
	                1727, 
	                0, 
	                0, 
	                0, 
	                220, 
	                0, 
	                0, 
	                1951, 
	                0, 
	                0, 
	                0, 
	                0
	            ]
	        }
	    ]
	}*/
	var type = [];
	var value1 = [];
	var value2 = [];
	for (var one in data) {
		type.push(data[one]["type"]);
		value1.push(Number(data[one]["value1"]));
		value2.push(Number(data[one]["value2"]));
    }
	
	var option = {
        color:[ '#4573a7', '#89a54e'],
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    legend: {
	        data:['迁入','迁出'],
	        textStyle: {
                color: '#fff',
                fontSize: _fontSize
            },
            itemGap: 100
	    },
	    grid: {
            top: '18%',
            left: '1%',
            right: '1%',
            bottom: '5%',
            containLabel: true
        },
	    xAxis : [
	        {
	            type : 'category',
	            //data : ['03-30','04-06','04-13','04-20','04-27','05-04','05-11'],
	            data : type,
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
	            name:'迁入',
	            type:'bar',
	            stack: '广告',
	            barWidth : '30%',
	            //data:[420, 332, 401, 334, 490, 530, 410]
	            data:value1
	        },
	        {
	            name:'迁出',
	            type:'bar',
	            stack: '广告',
	            barWidth : '30%',
	            //data:[220, 182, 191, 134, 190, 230, 210]
	            data:value2
	        }
	    ]
	};

	var myChart = echarts.init(document.getElementById("renyuanqingkuang"));
	myChart.setOption(option);
}


//房屋状态统计
function FangwuNumEchartsFun(){
	//数据
	  var data = {
		        name: "基础数据资源",
		        "children": [
		            {
		                "name": "总数",
		                "children": [
		                    {
		                        "name": "自住", //类型名称
		                        "size": 1324 , //总数
		                        "add": "11", //本月新增数
		                        "rate": "13%" //月增长率
		                    }, {
		                        "name": "出租",
		                        "size": 192,
		                        "add": "1",
		                        "rate": "13%"
		                    }, {
		                        "name": "空置",
		                        "size": 28,
		                        "add": "1",
		                        "rate": "13%"
		                    }],
		                "size": parseInt(1324)+parseInt(192)+parseInt(28),
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
	   var Barchart = echarts.init(document.getElementById('ccmPopTenantHouseType'));
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

	var color = [ '#4573a7', '#aa4644', '#89a54e', '#71588f', '#4298af',
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
function fangwuyinhuanleixingFun(){
	var option = {
	        color:[ '#4573a7', '#89a54e'],
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
//		    legend: {
//		        data:['迁入','迁出'],
//		        textStyle: {
//	                color: '#fff',
//	                fontSize: _fontSize
//	            },
//	            itemGap: 100
//		    },
		    grid: {
	            top: '18%',
	            left: '1%',
	            right: '1%',
	            bottom: '5%',
	            containLabel: true
	        },
		    xAxis : [
		        {
		            type : 'category',
		            data : ['火灾隐患','治安隐患','矛盾纠纷隐患','疾病传染隐患','危化物品隐患','其他'],
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

		var myChart = echarts.init(document.getElementById("fangwuyinhuanleixing"));
		myChart.setOption(option);
}

function mapLoudongFun(){
	$('#mapMaskloudong').html('')
	$('#maploudong').html('')
         var center=[113.3616891503334,34.53495812416077];
	viewSituationFun(center,'18');
	// 地图默认数据设置
	var defaultPrams = {
		id : 'mapMaskloudong',//113.3595613562, 34.5373170808 
		centerCoordinate : center,
		zoom : 15,
		maxZoom : 19,
		minZoom : 9.8,		
		baseUrl: baseUrlT,
		zoomShowOrHide : false,// 缩小放大
		overviewMap : false,
		selectPointerFlag:false
	// 鹰眼图
	}
    var Map1 = new ArjMap.Map(defaultPrams);
	// 加载地图
	Map1.drawMapSituationLoudong();
	
	
	
	
	// 地图默认数据设置
	var defaultPrams = {
		id : 'maploudong',//113.3595613562, 34.5373170808 
		centerCoordinate : center,
		zoom : 15,
		maxZoom : 19,
		minZoom : 9.8,		
//		baseUrl : [{
//	        'type':'tiandi',
//			'id':'yingxiang',
//			'name' : '影像图 ',
//			'url' : 'http://t0.tianditu.com/DataServer?T=img_w&x={x}&y={y}&l={z}',
//			'isShow' : true,
//		}],
		zoomShowOrHide : false,// 缩小放大
		overviewMap : false,
		selectPointerFlag:false
	// 鹰眼图
	}
    var Map = new ArjMap.Map(defaultPrams);
	// 加载地图
	Map.drawMapSituationLoudong();
	$.getJSON( "/arjccm/a/sys/map/buildmanageMap", function(data) {
		
		Map.addJSON1([ {
			'type' : 'build',
			'data' : data,
			'isShow' : true
		} ])
});
	
	
}

var sdatab={
		"尖山派出所": {
			 "实有人口":"11020", "实有房屋":"2755" , "实有单位":"1101", "常住人口":"10698", "流动人员":"322" , "重点人员":"21", "社区民警":"5" , "包村辅警":"7" 
		},
		"米村派出所": { 
			"实有人口":"38132", "实有房屋":"9533" , "实有单位":"1022", "常住人口":"37117", "流动人员":"1015" , "重点人员":"60", "社区民警":"3" , "包村辅警":"20" 
				},
		"袁庄派出所": { 
			"实有人口":"26110", "实有房屋":"6527" , "实有单位":"1213", "常住人口":"25324", "流动人员":"786" , "重点人员":"106", "社区民警":"12" , "包村辅警":"6" 
				},
		"白寨派出所": { 
			"实有人口":"71000", "实有房屋":"17750" , "实有单位":"2014", "常住人口":"69699", "流动人员":"1301" , "重点人员":"88", "社区民警":"9" , "包村辅警":"14" 
				},
		"新密城区": { 
			"实有人口":"140354", "实有房屋":"35088" , "实有单位":"4105", "常住人口":"152506", "流动人员":"7848" , "重点人员":"108", "社区民警":"5" , "包村辅警":"5" 
				},
				
		"牛店派出所": { 
			"实有人口":"51024", "实有房屋":"12756" , "实有单位":"1030", "常住人口":"50453", "流动人员":"571" , "重点人员":"97", "社区民警":"4" , "包村辅警":"6" 
				},
		"城关派出所": { 
			"实有人口":"30940", "实有房屋":"8698" , "实有单位":"1305", "常住人口":"30243", "流动人员":"697" , "重点人员":"40", "社区民警":"4" , "包村辅警":"5" 
				},
		"岳村派出所": { 
			"实有人口":"43527", "实有房屋":"10821" , "实有单位":"1024", "常住人口":"42839", "流动人员":"688" , "重点人员":"70", "社区民警":"8" , "包村辅警":"8" 
				},
		"来集派出所": { 
			"实有人口":"60308", "实有房屋":"15077" , "实有单位":"1204", "常住人口":"59220", "流动人员":"1088" , "重点人员":"77", "社区民警":"3" , "包村辅警":"9" 
				},
		"平陌派出所": { 
			"实有人口":"42351", "实有房屋":"12600" , "实有单位":"9791", "常住人口":"41865", "流动人员":"486" , "重点人员":"58", "社区民警":"14" , "包村辅警":"12" 
				},	
	"超化派出所": { 
		"实有人口":"52033", "实有房屋":"15593" , "实有单位":"1501", "常住人口":"78230", "流动人员":"1537" , "重点人员":"148", "社区民警":"3" , "包村辅警":"14" 
			},			
	"刘寨派出所": { 
		"实有人口":"52033", "实有房屋":"10406" , "实有单位":"1560", "常住人口":"49947", "流动人员":"2086" , "重点人员":"86", "社区民警":"4" , "包村辅警":"16" 
			},	
			
	"曲梁派出所": { 
		"实有人口":"65299", "实有房屋":"21123" , "实有单位":"389", "常住人口":"81870", "流动人员":"4254" , "重点人员":"104", "社区民警":"19" , "包村辅警":"40" 
			},
	"大隗派出所": { 
		"实有人口":"65299", "实有房屋":"13059" , "实有单位":"1958", "常住人口":"63170", "流动人员":"2129" , "重点人员":"88", "社区民警":"14" , "包村辅警":"14" 
			},	
	"苟堂派出所": { 
		"实有人口":"59757", "实有房屋":"14939" , "实有单位":"1792", "常住人口":"59071", "流动人员":"686" , "重点人员":"82", "社区民警":"11" , "包村辅警":"13" 
			},			
	"青屏街派出所": { 
		"实有人口":"74054", "实有房屋":"18669" , "实有单位":"2809", "常住人口":"64924", "流动人员":"5130" , "重点人员":"50", "社区民警":"4" , "包村辅警":"10" 
			},				
}

var sgongzuozhanb={
		"曲梁派出所":{
			"产业集聚区警务室":{	
				"实有人口":"33324", "实有房屋":"5056" , "实有单位":"68", "常住人口":"23124", "流动人员":"10200" , "重点人员":"30", "社区民警":"1" , "包村辅警":"4",
				"工作站":{
					"五虎庙村":{
						"实有人口":"3812", "实有房屋":"905" , "实有单位":"4", "常住人口":"3623", "流动人员":"189" , "重点人员":"6", "社区民警":"1" , "包村辅警":"1",
					},
					"马庄村":{
						"实有人口":"1982", "实有房屋":"115" , "实有单位":"115", "常住人口":"1852", "流动人员":"152" , "重点人员":"1", "社区民警":"0" , "包村辅警":"1",
					},
					"岗牛村":{
						"实有人口":"4194", "实有房屋":"903" , "实有单位":"1", "常住人口":"4064", "流动人员":"1455" , "重点人员":"0", "社区民警":"0" , "包村辅警":"1",
					},
					"高洼村":{
						"实有人口":"2258", "实有房屋":"4" , "实有单位":"521", "常住人口":"2346", "流动人员":"339" , "重点人员":"3", "社区民警":"0" , "包村辅警":"1",
					},
					"蒋坡村":{
						"实有人口":"3830", "实有房屋":"939" , "实有单位":"115", "常住人口":"3758", "流动人员":"560" , "重点人员":"5", "社区民警":"0" , "包村辅警":"1",
					},
					"朱寨村":{
						"实有人口":"3125", "实有房屋":"756" , "实有单位":"1", "常住人口":"3025", "流动人员":"236" , "重点人员":"4", "社区民警":"0" , "包村辅警":"1",
					},
					"坡刘村":{
						"实有人口":"2264", "实有房屋":"503" , "实有单位":"2", "常住人口":"2012", "流动人员":"852" , "重点人员":"3", "社区民警":"0" , "包村辅警":"1",
					},
					"李庄村":{
						"实有人口":"3680", "实有房屋":"920" , "实有单位":"2", "常住人口":"3680", "流动人员":"852" , "重点人员":"5", "社区民警":"0" , "包村辅警":"1",
					},
				}
			},
			"窦沟警务室":{	
				"实有人口":"16712", "实有房屋":"3869" , "实有单位":"14", "常住人口":"16212", "流动人员":"500" , "重点人员":"16", "社区民警":"1" , "包村辅警":"2",
				"工作站":{
					"窦沟村":{
						"实有人口":"4256", "实有房屋":"892" , "实有单位":"5", "常住人口":"3582", "流动人员":"85" , "重点人员":"10", "社区民警":"1" , "包村辅警":"0",
					},
					"冯家村":{
						"实有人口":"1623", "实有房屋":"286" , "实有单位":"1", "常住人口":"985", "流动人员":"638" , "重点人员":"1", "社区民警":"0" , "包村辅警":"1",
					},
					"东岗村":{
						"实有人口":"1752", "实有房屋":"384" , "实有单位":"1", "常住人口":"1563", "流动人员":"189" , "重点人员":"0", "社区民警":"0" , "包村辅警":"1",
					},
					"河西村":{
						"实有人口":"3302", "实有房屋":"522" , "实有单位":"1", "常住人口":"2541", "流动人员":"10" , "重点人员":"0", "社区民警":"0" , "包村辅警":"1",
					},
					"张湾村":{
						"实有人口":"3252", "实有房屋":"566" , "实有单位":"2", "常住人口":"2563", "流动人员":"12" , "重点人员":"0", "社区民警":"0" , "包村辅警":"1",
					},
					"曲梁村":{
						"实有人口":"3255", "实有房屋":"656" , "实有单位":"3", "常住人口":"2358", "流动人员":"35" , "重点人员":"1", "社区民警":"0" , "包村辅警":"1",
					},
					"牛角湾村":{
						"实有人口":"3112", "实有房屋":"566" , "实有单位":"1", "常住人口":"2620", "流动人员":"50" , "重点人员":"4", "社区民警":"0" , "包村辅警":"1",
					},
				
				}
			},
			"庙朱警务室":{	
				"实有人口":"25797", "实有房屋":"5646" , "实有单位":"24", "常住人口":"25141", "流动人员":"656" , "重点人员":"29", "社区民警":"1" , "包村辅警":"2",
				"工作站":{
					"庙朱村":{
						"实有人口":"3284", "实有房屋":"697" , "实有单位":"2", "常住人口":"3092", "流动人员":"108" , "重点人员":"3", "社区民警":"0" , "包村辅警":"1",
					},
					"沟刘村":{
						"实有人口":"2623", "实有房屋":"603" , "实有单位":"1", "常住人口":"2858", "流动人员":"50" , "重点人员":"4", "社区民警":"0" , "包村辅警":"1",
					},
					"沃郑村":{
						"实有人口":"3522", "实有房屋":"568" , "实有单位":"1", "常住人口":"2525", "流动人员":"50" , "重点人员":"4", "社区民警":"0" , "包村辅警":"1",
					},
					"柿园村":{
						"实有人口":"3651", "实有房屋":"618" , "实有单位":"1", "常住人口":"2378", "流动人员":"56" , "重点人员":"3", "社区民警":"0" , "包村辅警":"1",
					},
					"牛集村":{
						"实有人口":"3056", "实有房屋":"687" , "实有单位":"2", "常住人口":"2565", "流动人员":"56" , "重点人员":"4", "社区民警":"0" , "包村辅警":"1",
					},
					"全庄村":{
						"实有人口":"4893", "实有房屋":"1127" , "实有单位":"2", "常住人口":"4523", "流动人员":"101" , "重点人员":"5", "社区民警":"0" , "包村辅警":"1",
					},
					"下牛村":{
						"实有人口":"5706", "实有房屋":"1346" , "实有单位":"16", "常住人口":"4930", "流动人员":"235" , "重点人员":"6", "社区民警":"0" , "包村辅警":"1",
					},
				
				}
			},
			"尚庄警务室":{	
				"实有人口":"16553", "实有房屋":"6552" , "实有单位":"7", "常住人口":"16365", "流动人员":"188" , "重点人员":"29", "社区民警":"11" , "包村辅警":"13",
				"工作站":{
					"尚庄村":{
						"实有人口":"2358", "实有房屋":"1080" , "实有单位":"17", "常住人口":"1894", "流动人员":"38" , "重点人员":"0", "社区民警":"1" , "包村辅警":"1",
					},
					"杨庄村":{
						"实有人口":"2775", "实有房屋":"1351" , "实有单位":"115", "常住人口":"2083", "流动人员":"32" , "重点人员":"1", "社区民警":"0" , "包村辅警":"1",
					},
					"大樊庄村":{
						"实有人口":"5058", "实有房屋":"1152" , "实有单位":"13", "常住人口":"3885", "流动人员":"35" , "重点人员":"10", "社区民警":"0" , "包村辅警":"1",
					},
					"草岗村":{
						"实有人口":"3568", "实有房屋":"907" , "实有单位":"8", "常住人口":"2368", "流动人员":"15" , "重点人员":"5", "社区民警":"0" , "包村辅警":"1",
					},
					"田庄村":{
						"实有人口":"1846", "实有房屋":"472" , "实有单位":"3", "常住人口":"1254", "流动人员":"25" , "重点人员":"1", "社区民警":"0" , "包村辅警":"1",
					},
					"黄台村":{
						"实有人口":"2852", "实有房屋":"850" , "实有单位":"8", "常住人口":"1574", "流动人员":"230" , "重点人员":"7", "社区民警":"0" , "包村辅警":"1",
					},
					"周庄村":{
						"实有人口":"3887", "实有房屋":"741" , "实有单位":"1", "常住人口":"3250", "流动人员":"20" , "重点人员":"5", "社区民警":"0" , "包村辅警":"1",
					},
				
				}
			},
      }	,
      "平陌派出所":{
			"葛沟警务室":{	
				"实有人口":"13663", "实有房屋":"3095" , "实有单位":"11", "常住人口":"13660", "流动人员":"3" , "重点人员":"17", "社区民警":"1" , "包村辅警":"4",
				"工作站":{
					"葛沟村":{
						"实有人口":"2125", "实有房屋":"905" , "实有单位":"4", "常住人口":"2123", "流动人员":"189" , "重点人员":"2", "社区民警":"1" , "包村辅警":"0",
					},
					"龙泉村":{
						"实有人口":"1604", "实有房屋":"375" , "实有单位":"0", "常住人口":"1604", "流动人员":"0" , "重点人员":"3", "社区民警":"0" , "包村辅警":"1",
					},
					"杨台村":{
						"实有人口":"3172", "实有房屋":"741" , "实有单位":"1", "常住人口":"3172", "流动人员":"0" , "重点人员":"4", "社区民警":"0" , "包村辅警":"1",
					},
					"禹寨村":{
						"实有人口":"1579", "实有房屋":"324" , "实有单位":"0", "常住人口":"1579", "流动人员":"0" , "重点人员":"2", "社区民警":"0" , "包村辅警":"1",
					},
					"簸箕掌村":{
						"实有人口":"1895", "实有房屋":"448" , "实有单位":"1", "常住人口":"1894", "流动人员":"1" , "重点人员":"4", "社区民警":"0" , "包村辅警":"1",
					},
					"刘沟村":{
						"实有人口":"1160", "实有房屋":"337" , "实有单位":"1", "常住人口":"1160", "流动人员":"0" , "重点人员":"1", "社区民警":"0" , "包村辅警":"1",
					},
					"白龙庙村":{
						"实有人口":"1611", "实有房屋":"385" , "实有单位":"1", "常住人口":"1610", "流动人员":"1" , "重点人员":"1", "社区民警":"0" , "包村辅警":"1",
					},
				}
			},
			"平陌警务室":{	
				"实有人口":"16778", "实有房屋":"3859" , "实有单位":"30", "常住人口":"16766", "流动人员":"12" , "重点人员":"19", "社区民警":"1" , "包村辅警":"2",
				"工作站":{
					"平陌村":{
						"实有人口":"3386", "实有房屋":"819" , "实有单位":"6", "常住人口":"3379", "流动人员":"7" , "重点人员":"3", "社区民警":"1" , "包村辅警":"0",
					},
					"香山村":{
						"实有人口":"1535", "实有房屋":"372" , "实有单位":"1", "常住人口":"1535", "流动人员":"0" , "重点人员":"2", "社区民警":"0" , "包村辅警":"1",
					},
					"马门村":{
						"实有人口":"1962", "实有房屋":"462" , "实有单位":"1", "常住人口":"1962", "流动人员":"0" , "重点人员":"2", "社区民警":"0" , "包村辅警":"1",
					},
					"崔沟村":{
						"实有人口":"2815", "实有房屋":"611" , "实有单位":"1", "常住人口":"2814", "流动人员":"1" , "重点人员":"5", "社区民警":"0" , "包村辅警":"1",
					},
					"扎子沟村":{
						"实有人口":"1598", "实有房屋":"361" , "实有单位":"2", "常住人口":"1597", "流动人员":"1" , "重点人员":"1", "社区民警":"0" , "包村辅警":"1",
					},
					"界河村":{
						"实有人口":"3360", "实有房屋":"733" , "实有单位":"7", "常住人口":"3358", "流动人员":"2" , "重点人员":"5", "社区民警":"0" , "包村辅警":"1",
					},
					"杨里沟村":{
						"实有人口":"2122", "实有房屋":"501" , "实有单位":"1", "常住人口":"2121", "流动人员":"1" , "重点人员":"1", "社区民警":"0" , "包村辅警":"1",
					},
				
				}
			},
			"虎岭警务室":{	
				"实有人口":"25797", "实有房屋":"5646" , "实有单位":"24", "常住人口":"25141", "流动人员":"656" , "重点人员":"22", "社区民警":"1" , "包村辅警":"2",
				"工作站":{
					"虎岭村":{
						"实有人口":"1479", "实有房屋":"349" , "实有单位":"7", "常住人口":"1479", "流动人员":"0" , "重点人员":"2", "社区民警":"0" , "包村辅警":"1",
					},
					"龙岗村":{
						"实有人口":"1479", "实有房屋":"349" , "实有单位":"2", "常住人口":"1479", "流动人员":"0" , "重点人员":"1", "社区民警":"0" , "包村辅警":"1",
					},
					"大坡村":{
						"实有人口":"2740", "实有房屋":"639" , "实有单位":"5", "常住人口":"2739", "流动人员":"1" , "重点人员":"7", "社区民警":"0" , "包村辅警":"1",
					},
					"牛岭村":{
						"实有人口":"2913", "实有房屋":"696" , "实有单位":"3", "常住人口":"2910", "流动人员":"3" , "重点人员":"7", "社区民警":"0" , "包村辅警":"1",
					},
					"耿堂村":{
						"实有人口":"1414", "实有房屋":"356" , "实有单位":"2", "常住人口":"1413", "流动人员":"1" , "重点人员":"3", "社区民警":"0" , "包村辅警":"1",
					},
					"刘门村":{
						"实有人口":"1885", "实有房屋":"448" , "实有单位":"2", "常住人口":"1884", "流动人员":"1" , "重点人员":"2", "社区民警":"0" , "包村辅警":"1",
					},
					
				}
			},
			
      },
      "城关派出所":{
			"甘寨警务室":{	
				"实有人口":"18367", "实有房屋":"4181" , "实有单位":"363", "常住人口":"18171", "流动人员":"196" , "重点人员":"27", "社区民警":"1" , "包村辅警":"4",
				"工作站":{
					"甘寨村":{
						"实有人口":"5673", "实有房屋":"1141" , "实有单位":"99", "常住人口":"5621", "流动人员":"52" , "重点人员":"5", "社区民警":"1" , "包村辅警":"0",
					},
					"高沟村":{
						"实有人口":"4240", "实有房屋":"976" , "实有单位":"89", "常住人口":"4200", "流动人员":"40" , "重点人员":"12", "社区民警":"0" , "包村辅警":"1",
					},
					"楚沟村":{
						"实有人口":"2355", "实有房屋":"550" , "实有单位":"59", "常住人口":"2350", "流动人员":"5" , "重点人员":"3", "社区民警":"0" , "包村辅警":"1",
					},
					"湾子河村":{
						"实有人口":"3849", "实有房屋":"865" , "实有单位":"58", "常住人口":"3810", "流动人员":"39" , "重点人员":"3", "社区民警":"0" , "包村辅警":"1",
					},
					"东瓦店村":{
						"实有人口":"2413", "实有房屋":"649" , "实有单位":"58", "常住人口":"2390", "流动人员":"33" , "重点人员":"4", "社区民警":"0" , "包村辅警":"1",
					},
				}
			},
			"东街警务室":{	
				"实有人口":"12573", "实有房屋":"2933" , "实有单位":"822", "常住人口":"12573", "流动人员":"88" , "重点人员":"23", "社区民警":"1" , "包村辅警":"2",
				"工作站":{
					"东街村":{
						"实有人口":"2396", "实有房屋":"629" , "实有单位":"118", "常住人口":"2370", "流动人员":"26" , "重点人员":"5", "社区民警":"1" , "包村辅警":"0",
					},
					"西街村":{
						"实有人口":"2422", "实有房屋":"653" , "实有单位":"131", "常住人口":"2400", "流动人员":"22" , "重点人员":"4", "社区民警":"0" , "包村辅警":"1",
					},
					"南街村":{
						"实有人口":"2352", "实有房屋":"510" , "实有单位":"145", "常住人口":"2350", "流动人员":"2" , "重点人员":"7", "社区民警":"0" , "包村辅警":"1",
					},
					"西瓦店村":{
						"实有人口":"3269", "实有房屋":"640" , "实有单位":"243", "常住人口":"3240", "流动人员":"29" , "重点人员":"5", "社区民警":"0" , "包村辅警":"1",
					},
					"翟沟村":{
						"实有人口":"2222", "实有房屋":"501" , "实有单位":"185", "常住人口":"2213", "流动人员":"9" , "重点人员":"1", "社区民警":"0" , "包村辅警":"1",
					},
					
				}
			},
      },
      "青屏街派出所":{
			"北密新路警务室":{	
				"实有人口":"34848", "实有房屋":"8610" , "实有单位":"1179", "常住人口":"30435", "流动人员":"413" , "重点人员":"14", "社区民警":"1" , "包村辅警":"4",
				"工作站":{
					"北密新路":{
						"实有人口":"6466", "实有房屋":"1616" , "实有单位":"198", "常住人口":"5479", "流动人员":"987" , "重点人员":"7", "社区民警":"1" , "包村辅警":"0",
					},
					"开阳":{
						"实有人口":"8952", "实有房屋":"2238" , "实有单位":"371", "常住人口":"7752", "流动人员":"1200" , "重点人员":"3", "社区民警":"0" , "包村辅警":"1",
					},
					"周楼":{
						"实有人口":"16300", "实有房屋":"4075" , "实有单位":"477", "常住人口":"14602", "流动人员":"1698" , "重点人员":"4", "社区民警":"0" , "包村辅警":"1",
					},
					"气象街":{
						"实有人口":"3130", "实有房屋":"689" , "实有单位":"133", "常住人口":"2602", "流动人员":"528" , "重点人员":"0", "社区民警":"0" , "包村辅警":"1",
					},
				}
			},
			"长乐路警务室":{	
				"实有人口":"20533", "实有房屋":"5375" , "实有单位":"470", "常住人口":"14621", "流动人员":"5921" , "重点人员":"14", "社区民警":"1" , "包村辅警":"2",
				"工作站":{
					"长乐路":{
						"实有人口":"2630", "实有房屋":"657" , "实有单位":"9", "常住人口":"2104", "流动人员":"526" , "重点人员":"4", "社区民警":"1" , "包村辅警":"0",
					},
					"嵩阳路":{
						"实有人口":"8403", "实有房屋":"2100" , "实有单位":"332", "常住人口":"6003", "流动人员":"2400" , "重点人员":"6", "社区民警":"0" , "包村辅警":"1",
					},
					"于家岗":{
						"实有人口":"6500", "实有房屋":"1625" , "实有单位":"292", "常住人口":"4500", "流动人员":"2000" , "重点人员":"1", "社区民警":"0" , "包村辅警":"1",
					},
					"广场社区":{
						"实有人口":"3000", "实有房屋":"996" , "实有单位":"107", "常住人口":"2014", "流动人员":"986" , "重点人员":"3", "社区民警":"0" , "包村辅警":"1",
					}
				}
			},
			"梁沟警务室":{	
				"实有人口":"16906", "实有房屋":"4337" , "实有单位":"761", "常住人口":"13684", "流动人员":"3222" , "重点人员":"14", "社区民警":"1" , "包村辅警":"2",
				"工作站":{
					"梁沟":{
						"实有人口":"3500", "实有房屋":"988" , "实有单位":"66", "常住人口":"3300", "流动人员":"200" , "重点人员":"3", "社区民警":"0" , "包村辅警":"1",
					},
					"方寨":{
						"实有人口":"4320", "实有房屋":"1080" , "实有单位":"234", "常住人口":"2820", "流动人员":"1500" , "重点人员":"3", "社区民警":"0" , "包村辅警":"1",
					},
					"百花巷":{
						"实有人口":"5436", "实有房屋":"1359" , "实有单位":"231", "常住人口":"4212", "流动人员":"1220" , "重点人员":"2", "社区民警":"0" , "包村辅警":"1",
					},
					"青屏大街":{
						"实有人口":"3650", "实有房屋":"910" , "实有单位":"230", "常住人口":"3548", "流动人员":"102" , "重点人员":"6", "社区民警":"0" , "包村辅警":"1",
					}
					
				}
			},
			"青峰路警务室":{	
				"实有人口":"12832", "实有房屋":"3356" , "实有单位":"474", "常住人口":"12012", "流动人员":"820" , "重点人员":"13", "社区民警":"1" , "包村辅警":"2",
				"工作站":{
					"青峰路":{
						"实有人口":"8832", "实有房屋":"2208" , "实有单位":"282", "常住人口":"8204", "流动人员":"628" , "重点人员":"13", "社区民警":"0" , "包村辅警":"1",
					},
					"北文峰":{
						"实有人口":"4000", "实有房屋":"1148" , "实有单位":"192", "常住人口":"3808", "流动人员":"192" , "重点人员":"0", "社区民警":"0" , "包村辅警":"1",
					},

				}
			},
			"幸福街警务室":{	
				"实有人口":"9468", "实有房屋":"2366" , "实有单位":"395", "常住人口":"8793", "流动人员":"675" , "重点人员":"9", "社区民警":"1" , "包村辅警":"2",
				"工作站":{
					"幸福街":{
						"实有人口":"5230", "实有房屋":"1307" , "实有单位":"127", "常住人口":"4730", "流动人员":"500" , "重点人员":"4", "社区民警":"0" , "包村辅警":"1",
					},
					"农业路":{
						"实有人口":"4238", "实有房屋":"1059" , "实有单位":"268", "常住人口":"4063", "流动人员":"175" , "重点人员":"5", "社区民警":"0" , "包村辅警":"1",
					},
				}
			},
			
    },
}

function sxinxibFun(name){
	var data=sdatab[name];
	$('#table-info1').html(name);
	$('#shiyourenkou1').html(data['实有人口']);
	$('#shiyoufangwu1').html(data['实有房屋']);
	$('#shiyoudanwei1').html(data['实有单位']);
	$('#cahngzhurenkou1').html(data['常住人口']);
	$('#liudongrenkou1').html(data['流动人员']);
	$('#zhongdianrenkou1').html(data['重点人员']);
	$('#shequminjing1').html(data['社区民警']);
	$('#baocunfujing1').html(data['包村辅警']);
}

var sjingeuhsibdata={
		"尖山派出所":[
			{"姓名":"姜军凯 ","性别":"男","职务":'民警',"电话":"13643711111","警务室":"钟沟警务室"},
			{"姓名":"杨中敏 ","性别":"男","职务":'民警',"电话":"17603710983","警务室":"神仙洞警务室"},
		],
		"米村派出所":[
			{"姓名":"陈昌鸿 ","性别":"男","职务":'民警',"电话":"17603710983","警务室":"于湾警务室"},
			{"姓名":"杨 磊  ", "性别":"男","职务":'副所长',"电话":"17603710983","警务室":"杨岗警务室"},
			{"姓名":"樊绍敏","性别":"男","职务":'民警',"电话":"18703860215","警务室":"范村警务室"},
			],
		"袁庄派出所":[
			{"姓名":"孙银海 ","性别":"男","职务":'民警',"电话":"17603710983","警务室":"靳沟警务室"},
			{"姓名":"袁祖新", "性别":"男","职务":'民警',"电话":"17603710836","警务室":"姚山警务室"},
			{"姓名":"钱建国","性别":"男","职务":'民警',"电话":"18703860215","警务室":"石贯峪警务室"},
			],
		"白寨派出所":[
			{"姓名":"陈学领 ","性别":"男","职务":'民警',"电话":"17603711505","警务室":"韦沟警务室"},
			{"姓名":"刘勇智", "性别":"男","职务":'民警',"电话":"17603710738","警务室":"刘堂警务室"},
			{"姓名":"程跃伟  ","性别":"男","职务":'民警',"电话":"1760371897","警务室":"白寨警务室"},
			{"姓名":"孙浩","性别":"男","职务":'民警',"电话":"17603711537","警务室":"黑峪沟警务室"},
			],
		"牛店派出所":[
			{"姓名":"张少东 ","性别":"男","职务":'民警',"电话":"13703986219","警务室":"李湾警务室"},
			{"姓名":"朱文力", "性别":"男","职务":'民警',"电话":"13592420440","警务室":"高村警务室"},
			{"姓名":"杨君生  ","性别":"男","职务":'民警',"电话":"13700851496","警务室":"牛店警务室"},
			{"姓名":"慎卫平","性别":"男","职务":'民警',"电话":"17603711701","警务室":"张湾东宏社区警务室"},
			],	
		"来集派出所":[
			{"姓名":"崔建涛 ","性别":"男","职务":'民警',"电话":"13803714466","警务室":"宋楼警务室 "},
			{"姓名":"慎东灿", "性别":"男","职务":'民警',"电话":"17603710692","警务室":"苏寨警务室 "},
			{"姓名":"马钊  ","性别":"男","职务":'民警',"电话":"17603711805","警务室":"王堂警务室"},
			],	
		"平陌派出所":[
			{"姓名":"刘志强","性别":"男","职务":'民警',"电话":"17603710680","警务室":"葛沟警务室 "},
			{"姓名":"聂利军", "性别":"男","职务":'民警',"电话":"17603711032","警务室":"平陌警务室 "},
			{"姓名":"赵新鹏","性别":"男","职务":'民警',"电话":"17603712003","警务室":"虎岭警务室"},
			],
		"超化派出所":[
			{"姓名":"张治国","性别":"男","职务":'民警',"电话":"13838514022","警务室":"樊寨警务室 "},
			{"姓名":"郑书军", "性别":"男","职务":'民警',"电话":"17603710739","警务室":"黄固寺警务室 "},
			{"姓名":"段政委","性别":"男","职务":'民警',"电话":"15838220838","警务室":"河西警务室"},
			],
		"曲梁派出所":[
			{"姓名":"于瑜","性别":"女","职务":'民警',"电话":"15038071555","警务室":"产业集聚区警务室 "},
			{"姓名":"冯俊清", "性别":"男","职务":'民警',"电话":"17603711806","警务室":"窦沟警务室 "},
			{"姓名":"丁建设","性别":"男","职务":'民警',"电话":"17603711786","警务室":"庙朱警务室"},
			{"姓名":"杨建东","性别":"男","职务":'民警',"电话":"17603710950","警务室":"尚庄警务室"},
			],	
		"苟堂派出所":[
			{"姓名":" 张金利","性别":"男","职务":'民警',"电话":"17603711563","警务室":"小刘寨 "},
			{"姓名":"杨中现", "性别":"男","职务":'民警',"电话":"13783549567","警务室":"关口 "},
			{"姓名":" 黄瑞杰","性别":"男","职务":'民警',"电话":"15937176320","警务室":"九龙咀"},
			{"姓名":"张荣海","性别":"男","职务":'民警',"电话":"17603711023","警务室":"养老湾"},
			],	
			
		"大隗派出所":[
			{"姓名":"裴保生","性别":"男","职务":'民警',"电话":"13700851489","警务室":"大隗警务室  "},
			{"姓名":"米建勋", "性别":"男","职务":'民警',"电话":"13937127555","警务室":"桃园警务室 "},
			{"姓名":"龙红杰","性别":"男","职务":'民警',"电话":"17603711215","警务室":"纸房警务室"},
			{"姓名":"段大伟","性别":"男","职务":'民警',"电话":"13700875983","警务室":"陈庄警务室"},
			],	
		"西街派出所":[
			{"姓名":"桑长青","性别":"男","职务":'民警',"电话":"13014636111","警务室":"东风电厂警务室  "},
			{"姓名":"王威", "性别":"男","职务":'民警',"电话":"17603711207","警务室":"中心警务室 "},
			{"姓名":"朱春涛","性别":"男","职务":'民警',"电话":"13700875815","警务室":"超化矿警务室"},
			],
		"新华路派出所":[
			{"姓名":"刘志远","性别":"男","职务":'民警',"电话":"17603711871","警务室":"赵坡警务室   "},
			{"姓名":"王东红", "性别":"女","职务":'民警',"电话":"13838198568","警务室":"民康路警务室  "},
			{"姓名":"王晓红","性别":"女","职务":'民警',"电话":"13838590725","警务室":"五里店警务室  "},
			],
		"刘寨派出所":[
			{"姓名":"杨晓东","性别":"男","职务":'民警',"电话":"15981827788","警务室":"刘沃警务室 "},
			{"姓名":"张峰杰", "性别":"女","职务":'民警',"电话":"17603711030","警务室":"刘寨警务室 "},
			{"姓名":"马石聚","性别":"女","职务":'民警',"电话":"13608674311","警务室":"皇帝宫警务室 "},
			{"姓名":"杨现廷","性别":"女","职务":'民警',"电话":"18339906123","警务室":"八里岔警务室 "},
			],
		"城关派出所":[
			{"姓名":"冯继凯","性别":"男","职务":'民警',"电话":"17603711960","警务室":"甘寨警务室"},
			{"姓名":"宋明玉", "性别":"女","职务":'民警',"电话":"17603711723","警务室":"东街警务室 "},
			],
		"青屏街派出所":[
			{"姓名":"王玉虎","性别":"男","职务":'民警',"电话":"17603711728","警务室":"幸福街警务室"},
			{"姓名":"宋明玉", "性别":"女","职务":'民警',"电话":"17603711723","警务室":"长乐路警务室 "},
			{"姓名":"江敏", "性别":"女","职务":'民警',"电话":"18838268518","警务室":"青峰路警务室 "},
			{"姓名":"王云兰", "性别":"男","职务":'民警',"电话":"17503711759","警务室":"梁沟警务室 "},
			{"姓名":"李格", "性别":"男","职务":'民警',"电话":"17603711738","警务室":"北密新路警务室 "},
			],
};


var sshequminjingbdata= {
	    "尖山派出所": [
	    	
	        { "姓名":"王新东","性别":"男","职务":"民警","电话":"13603828599","警务室":"钟沟警务室","警务工作站":"国公岭"},
	        { "姓名":"姜晓东","性别":"男","职务":"民警","电话":"13903852777","警务室":"钟沟警务室","警务工作站":"下寺沟"},
	        { "姓名":"姜军凯","性别":"男","职务":"民警","电话":"13643711111","警务室":"钟沟警务室","警务工作站":"钟沟"},
	        { "姓名":"刘新建","性别":"男","职务":"民警","电话":"17603710978","警务室":"钟沟警务室","警务工作站":"巩密关"},
	        { "姓名":"杨中敏","性别":"男","职务":"民警","电话":"17603710983","警务室":"神仙洞警务室","警务工作站":"神仙洞"},
	        { "姓名":"焦泽豪","性别":"男","职务":"辅警","电话":"18703860215","警务室":"钟沟警务室","警务工作站":"牛心石"},
	        { "姓名":"王晓磊","性别":"男","职务":"辅警","电话":"15617805811","警务室":"钟沟警务室","警务工作站":"尖山"},
	        { "姓名":"王亚鹏","性别":"男","职务":"辅警","电话":"18339906383","警务室":"钟沟警务室","警务工作站":"田种湾"},
	        { "姓名":"余鹏飞","性别":"男","职务":"辅警","电话":"13203866199","警务室":"神仙洞警务室","警务工作站":"丁沟"},
	        { "姓名":"郭子豪","性别":"男","职务":"辅警","电话":"15515593567","警务室":"神仙洞警务室","警务工作站":"沙古堆"},
	        { "姓名":"楚卓琳","性别":"男","职务":"辅警","电话":"13213219291","警务室":"神仙洞警务室","警务工作站":"楼院"},
	        { "姓名":"刘伟涛","性别":"男","职务":"辅警","电话":"15003820388","警务室":"神仙洞警务室","警务工作站":"五虎沟"},
	    ],
	    "米村派出所": [
	        { "姓名":"杨磊","性别":"男","职务":"民警","电话":"17603711671","警务室":"钟沟警务室","警务工作站":"杨岗村"},
	        { "姓名":"徐云朋","性别":"男","职务":"辅警","电话":"15093423097","警务室":"于湾警务室","警务工作站":"米村村"},
	        { "姓名":"方江涛","性别":"男","职务":"辅警","电话":"18530076520","警务室":"于湾警务室","警务工作站":"下王村"},
	        { "姓名":"张志文","性别":"男","职务":"辅警","电话":"18695800697","警务室":"于湾警务室","警务工作站":"于湾村"},
	        { "姓名":"吴培昊","性别":"男","职务":"辅警","电话":"18638205535","警务室":"于湾警务室","警务工作站":"宋村村"},
	        { "姓名":"谢文成","性别":"男","职务":"辅警","电话":"18738141637","警务室":"于湾警务室","警务工作站":"孟庄村"},
	        { "姓名":"魏博雅","性别":"女","职务":"辅警","电话":"15138944229","警务室":"于湾警务室","警务工作站":"方山村"},
	        { "姓名":"张臣晨","性别":"女","职务":"辅警","电话":"18625516137","警务室":"于湾警务室","警务工作站":"慎窑村"},
	        { "姓名":"桑长娥","性别":"女","职务":"辅警","电话":"18137275787","警务室":"杨岗警务室","警务工作站":"马寨村"},
	        { "姓名":"吕鹏程","性别":"男","职务":"辅警","电话":"18239988234","警务室":"杨岗警务室","警务工作站":"柿树湾村"},
	        { "姓名":"郑东浩","性别":"男","职务":"辅警","电话":"15838133562","警务室":"杨岗警务室","警务工作站":"茶庵村"},
	        { "姓名":"张 雨","性别":"女","职务":"辅警","电话":"15136211005","警务室":"杨岗警务室","警务工作站":"金井沟村"},
	        { "姓名":"楚超领 ","性别":"男","职务":"辅警","电话":"13253635520","警务室":"杨岗警务室","警务工作站":"杨寨沟村"},
	        { "姓名":"王俊杰","性别":"男","职务":"辅警","电话":"15515830222","警务室":"杨岗警务室","警务工作站":"温庄村"},
	        { "姓名":"朱晓丽","性别":"女","职务":"辅警","电话":"15003836287","警务室":"范村警务室","警务工作站":"白槐村"},
	        { "姓名":"高畅蔚","性别":"女","职务":"辅警","电话":"13526837211","警务室":"范村警务室","警务工作站":"范村村"},
	        { "姓名":"张伟超","性别":"男","职务":"辅警","电话":"13015512521","警务室":"范村警务室","警务工作站":"蔓菁峪村"},
	        { "姓名":"徐英涛","性别":"男","职务":"辅警","电话":"15290887193","警务室":"范村警务室","警务工作站":"朱家庵村"},
	        { "姓名":"韩静静","性别":"女","职务":"辅警","电话":"17803850797","警务室":"范村警务室","警务工作站":"月寨村"},
	        { "姓名":"赵耀辉","性别":"男","职务":"辅警","电话":"15890666993","警务室":"范村警务室","警务工作站":"贾寨村"},
	        { "姓名":"米广丽","性别":"女","职务":"辅警","电话":"15981959188","警务室":"范村警务室","警务工作站":"拐峪村"},

	    ],
	    "袁庄派出所": [
	        { "姓名":"孙银海","性别":"男","职务":"民警","电话":"17603711905","警务室":"靳沟警务室","警务工作站":"靳沟村"},
	        { "姓名":"周俊广","性别":"男","职务":"民警","电话":"17603711915","警务室":"靳沟警务室","警务工作站":"小台沟村"},
	        { "姓名":"张 勇","性别":"男","职务":"民警","电话":"15937155398","警务室":"靳沟警务室","警务工作站":"青河村"},
	        { "姓名":"刘群安","性别":"男","职务":"民警","电话":"17603710625","警务室":"靳沟警务室","警务工作站":"郭庄村"},
	        { "姓名":"刘冠军","性别":"男","职务":"民警","电话":"13938285396","警务室":"靳沟警务室","警务工作站":"山顶村"},
	        { "姓名":"袁祖新","性别":"男","职务":"民警","电话":"17603710836","警务室":"姚山警务室","警务工作站":"姚山村"},
	        { "姓名":"邵二云","性别":"女","职务":"民警","电话":"13603991518","警务室":"姚山警务室","警务工作站":"袁庄村"},
	        { "姓名":"韩超锋","性别":"男","职务":"民警","电话":"17603711912","警务室":"姚山警务室","警务工作站":"姜沟村"},
	        { "姓名":"郑洪军","性别":"男","职务":"民警","电话":"17603710695","警务室":"姚山警务室","警务工作站":"吴家庄村"},
	        { "姓名":"钱建国","性别":"男","职务":"民警","电话":"17603711309","警务室":"石贯峪警务室","警务工作站":"石贯峪村"},
	        { "姓名":"钱建国","性别":"男","职务":"民警","电话":"17603711309","警务室":"石贯峪警务室","警务工作站":"张华岭村"},
	        { "姓名":"李明慧","性别":"女","职务":"民警","电话":"13838199993","警务室":"石贯峪警务室","警务工作站":"拐沟村"},
	        { "姓名":"孙万青","性别":"男","职务":"民警","电话":"13598874513","警务室":"石贯峪警务室","警务工作站":"陈垴村"},

	        
	        { "姓名":"吕忠洲","性别":"男","职务":"辅警","电话":"18638637663","警务室":"靳沟警务室","警务工作站":"郑冲村"},
	        { "姓名":"张展","性别":"男","职务":"辅警","电话":"15938732520","警务室":"靳沟警务室","警务工作站":"乱石坡村"},
	        { "姓名":"孙  帅","性别":"男","职务":"辅警","电话":"17803850563","警务室":"姚山警务室","警务工作站":"张家门村"},
	        { "姓名":"孙  帅","性别":"男","职务":"辅警","电话":"17803850563","警务室":"姚山警务室","警务工作站":"槐阴寺村"},
	        { "姓名":"姜景帅","性别":"男","职务":"辅警","电话":"18239995958","警务室":"石贯峪警务室","警务工作站":"龙泉寺村"},
	        { "姓名":"王泽震","性别":"男","职务":"辅警","电话":"17737718521","警务室":"石贯峪警务室","警务工作站":"方沟村"},
	        { "姓名":"孟小刚","性别":"男","职务":"辅警","电话":"15136116888","警务室":"石贯峪警务室","警务工作站":"井沟村"}, 
	    ],
	    "白寨派出所": [
	        { "姓名":"陈学领","性别":"男","职务":"民警","电话":"17603711505","警务室":"韦沟警务室","警务工作站":"韦沟村"},
	        { "姓名":"刘勇智","性别":"男","职务":"民警","电话":"17603710738","警务室":"刘堂警务室","警务工作站":"刘堂村"},
	        { "姓名":"孙自锦","性别":"男","职务":"民警","电话":"15517573526","警务室":"刘堂警务室","警务工作站":"三岔口村"},
	        { "姓名":"陈明磊","性别":"男","职务":"民警","电话":"13938242375","警务室":"刘堂警务室","警务工作站":"西腰村"},
	        { "姓名":"申进英","性别":"男","职务":"民警","电话":"17603710197","警务室":"刘堂警务室","警务工作站":"柳沟村"},
	        { "姓名":"程跃伟","性别":"男","职务":"民警","电话":"17603711897","警务室":"白寨警务室 ","警务工作站":"白寨村"},
	        { "姓名":"吕松涛","性别":"男","职务":"民警","电话":"13613812608","警务室":"白寨警务室","警务工作站":"东岗村"},
	        { "姓名":"孙浩","性别":"男","职务":"民警","电话":"17603711537","警务室":"黑峪沟警务室","警务工作站":"黑峪沟村"},
	        { "姓名":"王占军","性别":"男","职务":"民警","电话":"15838258601","警务室":"黑峪沟警务室","警务工作站":"牌坊沟村"},

	        
	        { "姓名":"王建新","性别":"男","职务":"辅警","电话":"17803850916","警务室":"韦沟警务室","警务工作站":"堂沟村"},
	        { "姓名":"芦丹丹","性别":"女","职务":"辅警","电话":"18538085920","警务室":"韦沟警务室","警务工作站":"史沟村"},
	        { "姓名":"庞鹤鹤","性别":"女","职务":"辅警","电话":"13938418928","警务室":"韦沟警务室","警务工作站":"周家寨村"},
	        { "姓名":"慕晓峰","性别":"男","职务":"辅警","电话":"15003999900","警务室":"韦沟警务室","警务工作站":"光武陈村"},
	        { "姓名":"周红臻","性别":"男","职务":"辅警","电话":"13623817774","警务室":"刘堂警务室","警务工作站":"高庙村"},
	        { "姓名":"周世磊","性别":"男","职务":"辅警","电话":"15238077700","警务室":"刘堂警务室","警务工作站":"皇帝岭村"},
	        { "姓名":"张国豪","性别":"男","职务":"辅警","电话":"13838300919","警务室":"白寨警务室 ","警务工作站":"良水寨村"},
	        { "姓名":"王浩","性别":"男","职务":"辅警","电话":"13703930323","警务室":"白寨警务室","警务工作站":"王寨河村"},
	        { "姓名":"刘小倩","性别":"女","职务":"辅警","电话":"13523501651","警务室":"白寨警务室","警务工作站":"翟沟村"},
	        { "姓名":"屈  浩","性别":"男","职务":"辅警","电话":"15290834260","警务室":"白寨警务室","警务工作站":"杨树岗村"},
	        { "姓名":"李珂","性别":"男","职务":"辅警","电话":"18595840595","警务室":"白寨警务室","警务工作站":"油坊庄村"},
	        { "姓名":"王泊喃","性别":"男","职务":"辅警","电话":"15036062963","警务室":"黑峪沟警务室","警务工作站":"山白村"},
	        { "姓名":"刘朝辉","性别":"男","职务":"辅警","电话":"18790269333","警务室":"黑峪沟警务室","警务工作站":"寨沟村"},
	        { "姓名":"吴凯华","性别":"男","职务":"辅警","电话":"15837106895","警务室":"黑峪沟警务室","警务工作站":"张楼沟村"},
	    ],
	    "牛店派出所": [
	        { "姓名":"张少东","性别":"男","职务":"民警","电话":"13703986219","警务室":"李湾警务室","警务工作站":"李湾村"},
	        { "姓名":"朱文力","性别":"男","职务":"民警","电话":"13592420440","警务室":"高村警务室","警务工作站":"石匠窑村"},
	        { "姓名":"朱文力","性别":"男","职务":"民警","电话":"13592420440","警务室":"高村警务室","警务工作站":"高村村"},

	        { "姓名":"杨君生","性别":"男","职务":"民警","电话":"13700851496","警务室":"牛店警务室","警务工作站":"牛店村"},
	        { "姓名":"慎卫平","性别":"男","职务":"民警","电话":"17603711701","警务室":"张湾东宏社区警务室","警务工作站":"张湾村"},
	        { "姓名":"慎卫平","性别":"男","职务":"民警","电话":"17603711701","警务室":"张湾东宏社区警务室","警务工作站":"打虎亭村"},

	        
	        { "姓名":"郑航","性别":"男","职务":"辅警","电话":"15890620456","警务室":"","警务工作站":"北召村"},
	        { "姓名":"郑航","性别":"男","职务":"辅警","电话":"15890620456","警务室":"","警务工作站":"花家店村"},
	        { "姓名":"郑航","性别":"男","职务":"辅警","电话":"15890620456","警务室":"","警务工作站":"助泉寺村"},
	        { "姓名":"高建伟","性别":"男","职务":"辅警","电话":"13523409707","警务室":"李湾警务室","警务工作站":"闫沟村"},
	        { "姓名":"高建伟","性别":"男","职务":"辅警","电话":"13523409707","警务室":"李湾警务室","警务工作站":"三岔口村"},
	        { "姓名":"高建伟","性别":"男","职务":"辅警","电话":"13523409707","警务室":"李湾警务室","警务工作站":"土门村"},
	        { "姓名":"刘海洋","性别":"男","职务":"辅警","电话":"17638575094","警务室":"高村警务室","警务工作站":"宝泉村"},
	        { "姓名":"刘海洋","性别":"男","职务":"辅警","电话":"17638575094","警务室":"高村警务室","警务工作站":"武村村"},
	        { "姓名":"刘海洋","性别":"男","职务":"辅警","电话":"17638575094","警务室":"高村警务室","警务工作站":"南龙村"},
	        { "姓名":"柴继伟","性别":"男","职务":"辅警","电话":"13592687345","警务室":"牛店警务室","警务工作站":"月台村、古角村"},
	        { "姓名":"朱世豪","性别":"男","职务":"辅警","电话":"18538205968","警务室":"牛店警务室","警务工作站":"寨脖村、小寨村"},
	        { "姓名":"王亚磊","性别":"男","职务":"辅警","电话":"13838039371","警务室":"张湾东宏社区警务室","警务工作站":"园林场村"},
	        { "姓名":"王亚磊","性别":"男","职务":"辅警","电话":"13838039371","警务室":"张湾东宏社区警务室","警务工作站":"谭村湾村"},
	        { "姓名":"王亚磊","性别":"男","职务":"辅警","电话":"13838039371","警务室":"张湾东宏社区警务室","警务工作站":"张坡村"},

	    ],
	    "来集派出所": [
	        { "姓名":"崔建涛","性别":"男","职务":"民警","电话":"13803714466","警务室":"宋楼警务室 ","警务工作站":"赵沟村"},
	        { "姓名":"慎东灿","性别":"男","职务":"民警","电话":"17603710692","警务室":"苏寨警务室","警务工作站":"苏寨村"},
	        { "姓名":"马钊","性别":"男","职务":"民警","电话":"17603711805","警务室":"王堂警务室","警务工作站":"王堂村"},
	        
	        
	        { "姓名":"李薪水","性别":"男","职务":"辅警","电话":"15238030759","警务室":"宋楼警务室 ","警务工作站":"西于沟村"},
	        { "姓名":"李薪水","性别":"男","职务":"辅警","电话":"15238030759","警务室":"宋楼警务室 ","警务工作站":"东于沟村"},
	        { "姓名":"韩龙辉","性别":"男","职务":"辅警","电话":"18239953181","警务室":"宋楼警务室 ","警务工作站":"黄寨村"},
	        { "姓名":"韩龙辉","性别":"男","职务":"辅警","电话":"18239953181","警务室":"宋楼警务室 ","警务工作站":"宋楼村"},

	        { "姓名":"陈博文","性别":"男","职务":"辅警","电话":"18838951398","警务室":"宋楼警务室 ","警务工作站":"李堂村"},
	        { "姓名":"陈博文","性别":"男","职务":"辅警","电话":"18838951398","警务室":"宋楼警务室 ","警务工作站":"岳岗村"},

	        { "姓名":"陈璐灏","性别":"男","职务":"辅警","电话":"15638216523","警务室":"苏寨警务室  ","警务工作站":"韩家门村"}, 
	        { "姓名":"陈璐灏","性别":"男","职务":"辅警","电话":"15638216523","警务室":"苏寨警务室  ","警务工作站":"来集村"}, 

	        { "姓名":"来梦佳","性别":"女","职务":"辅警","电话":"15290816008","警务室":"苏寨警务室 ","警务工作站":"陈沟村"},
	        { "姓名":"来梦佳","性别":"女","职务":"辅警","电话":"15290816008","警务室":"苏寨警务室 ","警务工作站":"巩楼村"},

	        { "姓名":"朱元龙","性别":"男","职务":"辅警","电话":"15838028296","警务室":"苏寨警务室 ","警务工作站":"杨家门村"}, 
	        { "姓名":"朱元龙","性别":"男","职务":"辅警","电话":"15838028296","警务室":"苏寨警务室 ","警务工作站":"郭岗村"}, 

	        { "姓名":"赵营","性别":"女","职务":"辅警","电话":"16603830058","警务室":"王堂警务室 ","警务工作站":"王家窝村"},
	        { "姓名":"赵营","性别":"女","职务":"辅警","电话":"16603830058","警务室":"王堂警务室 ","警务工作站":"翟坡村"},

	        { "姓名":"侯吉林","性别":"男","职务":"辅警","电话":"18737182912","警务室":"王堂警务室 ","警务工作站":"桧树亭村"},
	        { "姓名":"侯吉林","性别":"男","职务":"辅警","电话":"18737182912","警务室":"王堂警务室 ","警务工作站":"马武寨村"},

	        { "姓名":"任庚辉","性别":"男","职务":"辅警","电话":"13683839655","警务室":"王堂警务室 ","警务工作站":"裴沟村"}, 
	        { "姓名":"任庚辉","性别":"男","职务":"辅警","电话":"13683839655","警务室":"王堂警务室 ","警务工作站":"马沟村"},    

	    ],
	    "平陌派出所": [
	        { "姓名":"魏书杰","性别":"男","职务":"民警","电话":"17603711709","警务室":"葛沟警务室 ","警务工作站":"葛沟村"},
	        { "姓名":"魏书杰","性别":"男","职务":"民警","电话":"17603711709","警务室":"葛沟警务室 ","警务工作站":"龙泉村"},

	        { "姓名":"曹志远","性别":"男","职务":"民警","电话":"17603711883","警务室":"葛沟警务室","警务工作站":"杨台村"},
	        { "姓名":"曹志远","性别":"男","职务":"民警","电话":"17603711883","警务室":"葛沟警务室","警务工作站":"禹寨村"},

	        { "姓名":"孙晓锋","性别":"男","职务":"民警","电话":"17603711710","警务室":"葛沟警务室","警务工作站":"簸箕掌村"},
	        { "姓名":"聂利军","性别":"男","职务":"民警","电话":"17603711032","警务室":"葛沟警务室 ","警务工作站":"刘沟村"},
	        { "姓名":"聂利军","性别":"男","职务":"民警","电话":"17603711032","警务室":"葛沟警务室 ","警务工作站":"白龙庙村"},

	        { "姓名":"张满长","性别":"男","职务":"民警","电话":"17603711707","警务室":"平陌警务室","警务工作站":"平陌村"},
	        { "姓名":"张满长","性别":"男","职务":"民警","电话":"17603711707","警务室":"平陌警务室","警务工作站":"香山村"},

	        { "姓名":"樊晓东","性别":"男","职务":"民警","电话":"13503990779","警务室":"平陌警务室","警务工作站":"马门村"},
	        { "姓名":"樊晓东","性别":"男","职务":"民警","电话":"13503990779","警务室":"平陌警务室","警务工作站":"崔沟村"},

	        { "姓名":"刘磊","性别":"男","职务":"民警","电话":"17603710153","警务室":"平陌警务室 ","警务工作站":"扎子沟"},
	        { "姓名":"杜新跃","性别":"男","职务":"民警","电话":"17603711708","警务室":"平陌警务室","警务工作站":"界河村"},	
	        { "姓名":"杜新跃","性别":"男","职务":"民警","电话":"17603711708","警务室":"平陌警务室","警务工作站":"杨里沟村"},
	        { "姓名":"赵新鹏","性别":"男","职务":"民警","电话":"17603712003","警务室":"虎岭警务室","警务工作站":"龙岗村"},
	        { "姓名":"赵新鹏","性别":"男","职务":"民警","电话":"17603712003","警务室":"虎岭警务室","警务工作站":"虎岭村"},

	        { "姓名":"梁  猛","性别":"男","职务":"民警","电话":"17603711916","警务室":"虎岭警务室","警务工作站":"牛岭村"},
	        { "姓名":"梁  猛","性别":"男","职务":"民警","电话":"17603711916","警务室":"虎岭警务室","警务工作站":"大坡村"},

	        { "姓名":"刘志强","性别":"男","职务":"民警","电话":"17603710680","警务室":"虎岭警务室","警务工作站":"耿堂村"},
	        { "姓名":"张  楚","性别":"男","职务":"民警","电话":"17603711607","警务室":"虎岭警务室","警务工作站":"刘门村"},
	        
	        
	        
	        { "姓名":"李亚东","性别":"男","职务":"辅警","电话":"15890006223","警务室":"葛沟警务室","警务工作站":"葛沟村"},
	        { "姓名":"李亚东","性别":"男","职务":"辅警","电话":"15890006223","警务室":"葛沟警务室","警务工作站":"龙泉村"},

	        { "姓名":"魏志峰","性别":"女","职务":"辅警","电话":"18037782932","警务室":"葛沟警务室","警务工作站":"杨台村"},
	        { "姓名":"魏志峰","性别":"女","职务":"辅警","电话":"18037782932","警务室":"葛沟警务室","警务工作站":"禹寨村"},

	        { "姓名":"白晓帆","性别":"女","职务":"辅警","电话":"15803853013","警务室":"葛沟警务室","警务工作站":"簸箕掌村"},
	        { "姓名":"杨冠军","性别":"男","职务":"辅警","电话":"15238606123","警务室":"葛沟警务室","警务工作站":"白龙庙村"},
	        { "姓名":"杨冠军","性别":"男","职务":"辅警","电话":"15238606123","警务室":"葛沟警务室","警务工作站":"刘沟村"},

	        { "姓名":"刘晓楠","性别":"男","职务":"辅警","电话":"13603847111","警务室":"葛沟警务室","警务工作站":"崔沟村"},
	        { "姓名":"刘晓楠","性别":"男","职务":"辅警","电话":"13603847111","警务室":"葛沟警务室","警务工作站":"马门村"},

	        { "姓名":"冯晓娜","性别":"女","职务":"辅警","电话":"17803850775","警务室":"平陌警务室 ","警务工作站":"扎子沟村"},
	        { "姓名":"张洪兵","性别":"男","职务":"辅警","电话":"17803850769","警务室":"平陌警务室 ","警务工作站":"界河村"},
	        { "姓名":"张洪兵","性别":"男","职务":"辅警","电话":"17803850769","警务室":"平陌警务室 ","警务工作站":"杨里沟村"},

	        { "姓名":"燕浩远","性别":"男","职务":"辅警","电话":"18638003883","警务室":"虎岭警务室 ","警务工作站":"虎岭村"},
	        { "姓名":"燕浩远","性别":"男","职务":"辅警","电话":"18638003883","警务室":"虎岭警务室 ","警务工作站":"龙岗村"},

	        { "姓名":"冯书静","性别":"女","职务":"辅警","电话":"15036008831","警务室":"虎岭警务室","警务工作站":"大坡村"},
	        { "姓名":"赵飞飞","性别":"女","职务":"辅警","电话":"15036008831","警务室":"虎岭警务室 ","警务工作站":"牛岭村"},
	        { "姓名":"冯浩洋 ","性别":"男","职务":"辅警","电话":"15538020221","警务室":"虎岭警务室 ","警务工作站":"耿堂村"},
	        { "姓名":"冯浩洋 ","性别":"男","职务":"辅警","电话":"15538020221","警务室":"虎岭警务室 ","警务工作站":"刘门村"},


	    ] ,
	    "超化派出所": [
	        { "姓名":"张治国","性别":"男","职务":"民警","电话":"13838514022","警务室":"樊寨警务室 ","警务工作站":"樊寨村"},
	        { "姓名":"张治国","性别":"男","职务":"民警","电话":"13838514022","警务室":"樊寨警务室 ","警务工作站":"王岗村"},

	        { "姓名":"郑书军","性别":"男","职务":"民警","电话":"17603710739","警务室":"樊寨警务室","警务工作站":"栗林村"},
	        { "姓名":"段政委","性别":"男","职务":"民警","电话":"15838220838","警务室":"河西警务室","警务工作站":"湖地村"},
	        { "姓名":"段政委","性别":"男","职务":"民警","电话":"15838220838","警务室":"河西警务室","警务工作站":"、龙潭村"},
	        { "姓名":"段政委","性别":"男","职务":"民警","电话":"15838220838","警务室":"河西警务室","警务工作站":"东店村"},
	        { "姓名":"段政委","性别":"男","职务":"民警","电话":"15838220838","警务室":"河西警务室","警务工作站":"河西村"},


	        { "姓名":"张留浩","性别":"男","职务":"辅警","电话":"18336312515","警务室":"樊寨警务室 ","警务工作站":"王村村"},
	        { "姓名":"张留浩","性别":"男","职务":"辅警","电话":"18336312515","警务室":"樊寨警务室 ","警务工作站":"超化村"},
	        { "姓名":"赵菁伟","性别":"男","职务":"辅警","电话":"15903603190","警务室":"樊寨警务室 ","警务工作站":"郑家庄村"},
	        { "姓名":"梁慧彬","性别":"男","职务":"辅警","电话":"13503858285","警务室":"樊寨警务室 ","警务工作站":"圣帝庙村"},
	        { "姓名":"孙航","性别":"男","职务":"辅警","电话":"15515921123","警务室":"樊寨警务室 ","警务工作站":"杏树岗村"},
	        { "姓名":"周凡博","性别":"男","职务":"辅警","电话":"13783533261","警务室":"樊寨警务室 ","警务工作站":"新庄村"},
	        { "姓名":"钱路丹","性别":"女","职务":"辅警","电话":"13838066220","警务室":"黄固寺警务室 ","警务工作站":"李坡村"},
	        { "姓名":"钱路丹","性别":"女","职务":"辅警","电话":"13838066220","警务室":"黄固寺警务室 ","警务工作站":"申沟村"},

	        { "姓名":"杨浩东","性别":"男","职务":"辅警","电话":"15290865858","警务室":"黄固寺警务室 ","警务工作站":"周岗村"},
	        { "姓名":"高龙飞","性别":"男","职务":"辅警","电话":"18530924360","警务室":"黄固寺警务室 ","警务工作站":"油坊沟村"},
	        { "姓名":"高龙飞","性别":"男","职务":"辅警","电话":"18530924360","警务室":"黄固寺警务室 ","警务工作站":"黄固寺村"},

	        
	        { "姓名":"郑龙源","性别":"男","职务":"辅警","电话":"18537174849","警务室":"黄固寺警务室 ","警务工作站":"崔庄村"},
	        { "姓名":"包金源","性别":"男","职务":"辅警","电话":"15936228689","警务室":"黄固寺警务室 ","警务工作站":"楚岭村"},
	        { "姓名":"徐亚辉","性别":"男","职务":"辅警","电话":"13523500423","警务室":"河西警务室 ","警务工作站":"莪沟村"},
	        { "姓名":"张韶元","性别":"男","职务":"辅警","电话":"13598082898","警务室":"河西警务室 ","警务工作站":"任沟村"},
	        { "姓名":"陈浩鹏","性别":"男","职务":"辅警","电话":"15038309133","警务室":"河西警务室","警务工作站":"草庙村"},
	        { "姓名":"冯浩阳","性别":"男","职务":"辅警","电话":"18037109888","警务室":"河西警务室","警务工作站":"黄路山村"},
	    ],
	    "曲梁派出所": [
	        { "姓名":"于瑜","性别":"女","职务":"民警","电话":"15038071555","警务室":"产业集聚区警务室 ","警务工作站":"五虎庙村"},
	        { "姓名":"冯俊清","性别":"男","职务":"民警","电话":"17603711806","警务室":"窦沟警务室","警务工作站":"窦沟村"},
	        { "姓名":"马亚龙","性别":"男","职务":"民警","电话":"13027613596","警务室":"庙朱警务室","警务工作站":"沟刘村"},
	        { "姓名":"马亚龙","性别":"男","职务":"民警","电话":"13027613596","警务室":"庙朱警务室","警务工作站":"沃郑村"},
	        { "姓名":"马亚龙","性别":"男","职务":"民警","电话":"13027613596","警务室":"庙朱警务室","警务工作站":"柿园村"},

	        { "姓名":"杨建东","性别":"男","职务":"民警","电话":"17603710950","警务室":"尚庄警务室 ","警务工作站":"尚庄村"},
	        { "姓名":"史明慧","性别":"女","职务":"辅警","电话":"17803850731","警务室":"产业集聚区警务室","警务工作站":"马庄村"},
	        { "姓名":"宫嘉展","性别":"男","职务":"辅警","电话":"13938287369","警务室":"产业集聚区警务室","警务工作站":"岗牛村"},
	        { "姓名":"宫嘉展","性别":"男","职务":"辅警","电话":"13938287369","警务室":"产业集聚区警务室","警务工作站":"高洼村"},

	        { "姓名":"郑凯南","性别":"男","职务":"辅警","电话":"18538010602","警务室":"产业集聚区警务室","警务工作站":"蒋坡村"},
	        { "姓名":"郑凯南","性别":"男","职务":"辅警","电话":"18538010602","警务室":"产业集聚区警务室","警务工作站":"朱寨村"},

	        { "姓名":"陈相见","性别":"男","职务":"辅警","电话":"13673356689","警务室":"产业集聚区警务室","警务工作站":"李庄村"},
	        { "姓名":"陈相见","性别":"男","职务":"辅警","电话":"13673356689","警务室":"产业集聚区警务室","警务工作站":"坡刘村"},

	        { "姓名":"王明玉","性别":"男","职务":"辅警","电话":"15036145509","警务室":"窦沟警务室 ","警务工作站":"冯家村"},
	        { "姓名":"王明玉","性别":"男","职务":"辅警","电话":"15036145509","警务室":"窦沟警务室 ","警务工作站":"河西村"},
	        { "姓名":"王明玉","性别":"男","职务":"辅警","电话":"15036145509","警务室":"窦沟警务室 ","警务工作站":"东岗村"},

	        { "姓名":"周江涛","性别":"男","职务":"辅警","电话":"18738108345","警务室":"窦沟警务室","警务工作站":"牛角湾村"},
	        { "姓名":"周江涛","性别":"男","职务":"辅警","电话":"18738108345","警务室":"窦沟警务室","警务工作站":"曲梁村"},

	        { "姓名":"周江涛","性别":"男","职务":"辅警","电话":"18738108345","警务室":"窦沟警务室","警务工作站":"张湾村"},

	        { "姓名":"丁建设","性别":"男","职务":"辅警","电话":"17603711786","警务室":"庙朱警务室 ","警务工作站":"庙朱村"},
	        { "姓名":"郑崤青","性别":"男","职务":"辅警","电话":"13783521333","警务室":"庙朱警务室","警务工作站":"牛集村"},
	        { "姓名":"郑崤青","性别":"男","职务":"辅警","电话":"13783521333","警务室":"庙朱警务室","警务工作站":"下牛村"},
	        { "姓名":"郑崤青","性别":"男","职务":"辅警","电话":"13783521333","警务室":"庙朱警务室","警务工作站":"全庄村"},

	        { "姓名":"王永帅","性别":"男","职务":"辅警","电话":"13140021072","警务室":"尚庄警务室","警务工作站":"杨庄村"},
	        { "姓名":"王永帅","性别":"男","职务":"辅警","电话":"13140021072","警务室":"尚庄警务室","警务工作站":"大樊庄村"},
	        { "姓名":"王永帅","性别":"男","职务":"辅警","电话":"13140021072","警务室":"尚庄警务室","警务工作站":"草岗村"},

	        { "姓名":"闫凯松","性别":"男","职务":"辅警","电话":"17788118550","警务室":"尚庄警务室","警务工作站":"田庄村"},
	        { "姓名":"闫凯松","性别":"男","职务":"辅警","电话":"17788118550","警务室":"尚庄警务室","警务工作站":"周庄村"},
	        { "姓名":"闫凯松","性别":"男","职务":"辅警","电话":"17788118550","警务室":"尚庄警务室","警务工作站":"黄台村"},

	       
	    ],
	    "苟堂派出所": [
	        { "姓名":"李书乾","性别":"男","职务":"民警","电话":"17603711317","警务室":"小刘寨 ","警务工作站":"苟堂村"},
	        { "姓名":"牛建岭","性别":"男","职务":"民警","电话":"17603711568","警务室":"小刘寨","警务工作站":"樊沟村"},
	        { "姓名":"薛凤娟","性别":"男","职务":"民警","电话":"17603711328","警务室":"小刘寨","警务工作站":"大么岭村"},
	        { "姓名":"宋战伟","性别":"男","职务":"民警","电话":"17603710723","警务室":"小刘寨","警务工作站":"靳寨村"},
	        { "姓名":"张金利","性别":"男","职务":"民警","电话":"13903853529","警务室":"关口 ","警务工作站":"张寨村"},
	        { "姓名":"杨新朝","性别":"男","职务":"民警","电话":"17603711963","警务室":"关口","警务工作站":"平山庵村"},
	        { "姓名":"黄瑞杰","性别":"男","职务":"民警","电话":"15937176320","警务室":"九龙咀","警务工作站":"劝门村"},
	        { "姓名":"周新伟","性别":"男","职务":"民警","电话":"13838355288","警务室":"九龙咀 ","警务工作站":"付寨村"},
	        { "姓名":"张战亭","性别":"男","职务":"民警","电话":"13608673219","警务室":"养老湾 ","警务工作站":"孙家庄村"},
	        { "姓名":"杨忠现","性别":"男","职务":"民警","电话":"13783549567","警务室":"养老湾","警务工作站":"槐树岭村"},
	        { "姓名":"张荣海","性别":"男","职务":"民警","电话":"17603711023","警务室":"养老湾","警务工作站":"付家门村"},



	        { "姓名":"侯昊杰","性别":"男","职务":"辅警","电话":"15238383083","警务室":"小刘寨","警务工作站":"小刘寨村"},
	        { "姓名":"苏明磊","性别":"男","职务":"辅警","电话":"17503826986","警务室":"关口","警务工作站":"关口村"},
	        { "姓名":"乔双磊","性别":"男","职务":"辅警","电话":"15093114040","警务室":"关口","警务工作站":"石桥村"},
	        { "姓名":"白浩杰","性别":"男","职务":"辅警","电话":"15036027683","警务室":"关口","警务工作站":"张门村"},
	        { "姓名":"王靖淦","性别":"男","职务":"辅警","电话":"15003804956","警务室":"九龙咀","警务工作站":"九龙咀村"},
	        { "姓名":"陈留洋","性别":"男","职务":"辅警","电话":"15093209629","警务室":"九龙咀","警务工作站":"栗树岗村"},
	        { "姓名":"郭明选","性别":"男","职务":"辅警","电话":"13673623623","警务室":"九龙咀","警务工作站":"石庙村"},
	        { "姓名":"李  想","性别":"男","职务":"辅警","电话":"15238338003","警务室":"养老湾","警务工作站":"养老湾村"},
	        { "姓名":"张  坡","性别":"男","职务":"辅警","电话":"15038158767","警务室":"养老湾","警务工作站":"玉皇庙村"},
	        { "姓名":"朱远浩","性别":"男","职务":"辅警","电话":"15638268378","警务室":"养老湾","警务工作站":"申门村"},
	        { "姓名":"樊语露","性别":"男","职务":"辅警","电话":"15137151671","警务室":"养老湾","警务工作站":"方沟村"},
	        { "姓名":"郑丽佳","性别":"男","职务":"辅警","电话":"15093456350","警务室":"养老湾","警务工作站":"范堂沟村"},
	        { "姓名":"王跃华","性别":"男","职务":"辅警","电话":"15837194345","警务室":"养老湾","警务工作站":"土门村"},
	    ],
	    "大隗派出所": [
	        { "姓名":"裴保生","性别":"男","职务":"民警","电话":"13700851489","警务室":"大隗警务室","警务工作站":"观寨村"},
	        { "姓名":"米建勋","性别":"男","职务":"民警","电话":"13937127555","警务室":"桃园警务室","警务工作站":"刘湾村"},
	        { "姓名":"杨  伟","性别":"男","职务":"民警","电话":"15903999123","警务室":"桃园警务室","警务工作站":"黄湾寨"},
	        { "姓名":"龙红杰","性别":"男","职务":"民警","电话":"17603711215","警务室":"纸房警务室","警务工作站":"纸坊村"},

	        { "姓名":"段大伟","性别":"男","职务":"民警","电话":"17603711329","警务室":"陈庄警务室","警务工作站":"陈庄村"},
	        { "姓名":"王彦峰","性别":"男","职务":"民警","电话":"13700854161","警务室":"陈庄警务室","警务工作站":"王沟村"},




	        { "姓名":"余明祥","性别":"男","职务":"辅警","电话":"15093335090","警务室":"大隗警务室","警务工作站":"大隗村"},
	        { "姓名":"刘少波","性别":"男","职务":"辅警","电话":"15136296721","警务室":"大隗警务室","警务工作站":"五里堡村"},
	        { "姓名":"董桢桢","性别":"女","职务":"辅警","电话":"17803850869","警务室":"大隗警务室","警务工作站":"河屯村"},
	        { "姓名":"李宗奇","性别":"男","职务":"辅警","电话":"15136289521","警务室":"大隗警务室","警务工作站":"进化村"},
	        { "姓名":"赵来发","性别":"男","职务":"辅警","电话":"18838182185","警务室":"大隗警务室","警务工作站":"振北村"},
	        { "姓名":"杨占坡","性别":"男","职务":"辅警","电话":"18538713522","警务室":"桃园警务室","警务工作站":"桃园村"},
	        { "姓名":"刘晓月","性别":"女","职务":"辅警","电话":"18337168371","警务室":"桃园警务室","警务工作站":"龙王庙村"},
	        { "姓名":"刘遂卿","性别":"男","职务":"辅警","电话":"13592496559","警务室":"桃园警务室","警务工作站":"孙沟村"},
	        { "姓名":"姜柯帅","性别":"男","职务":"辅警","电话":"18239945123","警务室":"桃园警务室","警务工作站":"和合村"},
	        { "姓名":"李鹏磊","性别":"男","职务":"辅警","电话":"15093242829","警务室":"桃园警务室","警务工作站":"香坊庄村"},
	        { "姓名":"黄铭浩","性别":"男","职务":"辅警","电话":"15136470105","警务室":"纸房警务室","警务工作站":"大路沟村"},
	        { "姓名":"靳根浩","性别":"男","职务":"辅警","电话":"13290905596","警务室":"纸房警务室","警务工作站":"窑沟村"},
	        { "姓名":"陈晓洋","性别":"男","职务":"辅警","电话":"13140111201","警务室":"纸房警务室","警务工作站":"大庙村"},
	        { "姓名":"吕松杰","性别":"男","职务":"辅警","电话":"13333763835","警务室":"纸房警务室","警务工作站":"大坡寨村"},
	        { "姓名":"樊晓娟","性别":"男","职务":"辅警","电话":"15037141008","警务室":"纸房警务室","警务工作站":"铁匠沟村"},
	        { "姓名":"王  浩","性别":"男","职务":"辅警","电话":"15093301771","警务室":"陈庄警务室","警务工作站":"双楼村"},
	        { "姓名":"任宏伟","性别":"男","职务":"辅警","电话":"13673602123","警务室":"陈庄警务室","警务工作站":"侯庄村"},
	        { "姓名":"姜广帅","性别":"男","职务":"辅警","电话":"13783699353","警务室":"陈庄警务室","警务工作站":"张庄村"},
	        { "姓名":"梁银磊","性别":"男","职务":"辅警","电话":"15617590877","警务室":"陈庄警务室","警务工作站":"老其沟村"},
	        
	        
	    ],
	    "西街派出所": [
	        { "姓名":"桑长青 ","性别":"男","职务":"民警","电话":"13014636111","警务室":"东风电厂警务室","警务工作站":"芦沟矿社区"},
	        { "姓名":"桑长青 ","性别":"男","职务":"民警","电话":"13014636111","警务室":"东风电厂警务室","警务工作站":"东风电厂社区"},

	        { "姓名":"冯发聚","性别":"男","职务":"民警","电话":"17603711852","警务室":"中心警务室","警务工作站":"大平矿社区"},
	        { "姓名":"王威","性别":"男","职务":"民警","电话":"17603711207","警务室":"中心警务室","警务工作站":"大楼社区"},
	        { "姓名":"王威","性别":"男","职务":"民警","电话":"17603711207","警务室":"中心警务室","警务工作站":"、裴沟矿社区"},

	        { "姓名":"冯发聚","性别":"男","职务":"民警","电话":"17603711852","警务室":"中心警务室","警务工作站":"机电总厂社区"},
	        { "姓名":"朱春涛","性别":"男","职务":"民警","电话":"13700875815","警务室":"超化矿警务室","警务工作站":"道北社区"},
	        { "姓名":"朱春涛","性别":"男","职务":"民警","电话":"13700875815","警务室":"超化矿警务室","警务工作站":"超化矿社区"},


	        { "姓名":"王文龙","性别":"男","职务":"辅警","电话":"13783658123","警务室":"东风电厂警务室","警务工作站":"龙潭社区"},
	        { "姓名":"王文龙","性别":"男","职务":"辅警","电话":"13783658123","警务室":"东风电厂警务室","警务工作站":"建井二处社区"},

	        { "姓名":"于红伟","性别":"男","职务":"辅警","电话":"18837147889","警务室":"东风电厂警务室","警务工作站":"东小区社区"},
	        { "姓名":"于红伟","性别":"男","职务":"辅警","电话":"18837147889","警务室":"东风电厂警务室","警务工作站":"技校社区"},

	        { "姓名":"陈德山","性别":"男","职务":"辅警","电话":"13592578739","警务室":"中心警务室","警务工作站":"茅岗社区"},
	        { "姓名":"王英豪","性别":"男","职务":"辅警","电话":"13523097355","警务室":"中心警务室","警务工作站":"王沟矿社区"},
	        { "姓名":"王科兴","性别":"男","职务":"辅警","电话":"15937168280","警务室":"超化矿警务室","警务工作站":"道南社区"},
	        { "姓名":"王科兴","性别":"男","职务":"辅警","电话":"15937168280","警务室":"超化矿警务室","警务工作站":"米村矿社区"},

	        { "姓名":"王启来","性别":"男","职务":"辅警","电话":"13523081679","警务室":"超化矿警务室","警务工作站":"王庄矿社区"},
	        { "姓名":"王启来","性别":"男","职务":"辅警","电话":"13523081679","警务室":"超化矿警务室","警务工作站":"告成矿社区"},

	       
	    ],
	    "新华路派出所": [
	        { "姓名":"刘志远","性别":"男","职务":"民警","电话":"17603711871","警务室":"赵坡警务室 ","警务工作站":"赵坡村警务工作站"},
	        { "姓名":"付二勋","性别":"男","职务":"民警","电话":"13938419888","警务室":"赵坡警务室 ","警务工作站":"和平街社区警务工作站"},
	        { "姓名":"王东红 ","性别":"女","职务":"民警","电话":"13838198568","警务室":"民康路警务室 ","警务工作站":"文化街社区警务工作站"},
	        { "姓名":"王晓红","性别":"女","职务":"民警","电话":"13838590725","警务室":"五里店警务室  ","警务工作站":"五里店村警务工作站"},
	        { "姓名":"赵光辉","性别":"男","职务":"民警","电话":"118530077888","警务室":"五里店警务室  ","警务工作站":"惠沟村警务工作站"},
	        { "姓名":"李跃宗","性别":"男","职务":"辅警","电话":"17803850571","警务室":"赵坡警务室 ","警务工作站":"人民路社区警务工作站"},
	        { "姓名":"侯玉鑫","性别":"男","职务":"辅警","电话":"15838016158","警务室":"赵坡警务室 ","警务工作站":"新惠街社区警务工作站"},
	        { "姓名":"刘亚东","性别":"男","职务":"辅警","电话":"15136262199","警务室":"赵坡警务室 ","警务工作站":"和平街社区警务工作站"},
	        { "姓名":"牛旭倩","性别":"女","职务":"辅警","电话":"18837117500","警务室":"民康路警务室 ","警务工作站":"民康路社区警务工作站"},
	        { "姓名":"郭浩明","性别":"男","职务":"辅警","电话":"15515992052","警务室":"民康路警务室 ","警务工作站":"文化街社区警务工作站"},
	        { "姓名":"钱雅晴","性别":"女","职务":"辅警","电话":"13938230626","警务室":"民康路警务室 ","警务工作站":"文化街社区警务工作站"},
	        { "姓名":"张继博","性别":"男","职务":"辅警","电话":"18625535519","警务室":"民康路警务室 ","警务工作站":"石庙沟社区警务工作站"},
	        { "姓名":"杨宁","性别":"女","职务":"辅警","电话":"15038178123","警务室":"民康路警务室 ","警务工作站":"商运街社区警务工作站"},
	        { "姓名":"张留源","性别":"男","职务":"辅警","电话":"15736735397","警务室":"五里店警务室","警务工作站":"五里店村警务工作站"},
	        { "姓名":"屈冬珂","性别":"男","职务":"辅警","电话":"15038376132","警务室":"五里店警务室","警务工作站":"嵩山路社区警务工作站"},
	        { "姓名":"李伟鹏","性别":"男","职务":"辅警","电话":"15838126211","警务室":"五里店警务室","警务工作站":"杨寨村警务工作站"},

	    ],
	    "刘寨派出所": [
	        { "姓名":"杨晓东","性别":"男","职务":"民警","电话":"15981827788","警务室":"刘沃警务室 ","警务工作站":"刘沃村"},
	        { "姓名":"张锋杰","性别":"男","职务":"民警","电话":"17603711030","警务室":"刘寨警务室","警务工作站":"园林村"},
	        { "姓名":"马石聚","性别":"男","职务":"民警","电话":"13608674311","警务室":"皇帝宫警务室 ","警务工作站":"小李寨村"},
	        { "姓名":"杨现廷","性别":"男","职务":"民警","电话":"18339906123","警务室":"八里岔警务室 ","警务工作站":"王咀村"},
	       
	        { "姓名":"张兴旺","性别":"男","职务":"辅警","电话":"18339963779","警务室":"刘沃警务室 ","警务工作站":"观音堂村"},
	        { "姓名":"刘天乐","性别":"男","职务":"辅警","电话":"13333829318","警务室":"刘沃警务室","警务工作站":"赵贵岗村"},
	        { "姓名":"张畦园","性别":"男","职务":"辅警","电话":"15890676590","警务室":"刘沃警务室","警务工作站":"李岗村"},
	        { "姓名":"宋炎鑫","性别":"男","职务":"辅警","电话":"13838594168","警务室":"刘沃警务室","警务工作站":"水竹园村"},
	        { "姓名":"邓增辉","性别":"男","职务":"辅警","电话":"17729744033","警务室":"刘寨警务室","警务工作站":"刘寨村"},
	        { "姓名":"薛建超","性别":"男","职务":"辅警","电话":"13592466863","警务室":"刘寨警务室","警务工作站":"宋寨村"},
	        { "姓名":"李英英","性别":"男","职务":"辅警","电话":"15038362251","警务室":"刘寨警务室","警务工作站":"新寨村"},
	        { "姓名":"张勇豪","性别":"男","职务":"辅警","电话":"13333814708","警务室":"刘寨警务室","警务工作站":"赵沟村"},
	        { "姓名":"郑义凡","性别":"男","职务":"辅警","电话":"15890019606","警务室":"皇帝宫警务室","警务工作站":"崔岗村"},
	        { "姓名":"张志豪","性别":"男","职务":"辅警","电话":"15837179255","警务室":"皇帝宫警务室","警务工作站":"西马庄村"},
	        { "姓名":"崔世坤","性别":"男","职务":"辅警","电话":"18437150657","警务室":"皇帝宫警务室","警务工作站":"老寨村"},
	        { "姓名":"岳豪杰","性别":"男","职务":"辅警","电话":"15903670100","警务室":"皇帝宫警务室","警务工作站":"吕楼村"},
	        { "姓名":"牛  童","性别":"男","职务":"辅警","电话":"13592414445","警务室":"八里岔警务室 ","警务工作站":"八里岔村"},
	        { "姓名":"马文博","性别":"男","职务":"辅警","电话":"15803882585","警务室":"八里岔警务室","警务工作站":"东马庄村"},
	        { "姓名":"张亚欣","性别":"男","职务":"辅警","电话":"18339255930","警务室":"八里岔警务室","警务工作站":"张庄村"},
	        { "姓名":"刘伟光","性别":"男","职务":"辅警","电话":"15238016826","警务室":"八里岔警务室","警务工作站":"王沟村"},
	    ],
	    "城关派出所": [
	        { "姓名":"冯继凯","性别":"男","职务":"民警","电话":"17603711960","警务室":"甘寨警务室","警务工作站":"甘寨村"},
	        { "姓名":"冯继凯 ","性别":"男","职务":"民警","电话":"17603711960","警务室":"甘寨警务室","警务工作站":"湾子河村"},
	        { "姓名":"冯继凯 ","性别":"男","职务":"民警","电话":"17603711960","警务室":"东街警务室","警务工作站":"西街村"},
	        { "姓名":"宋明玉","性别":"女","职务":"民警","电话":"17603711723","警务室":"东街警务室","警务工作站":"东街村"},
	        { "姓名":"宋明玉","性别":"女","职务":"民警","电话":"17603711723","警务室":"东街警务室","警务工作站":"南街村"},
	        { "姓名":"郭宝玉","性别":"男","职务":"民警","电话":"17603711205","警务室":"甘寨警务室","警务工作站":"楚沟村"},
	        { "姓名":"郭宝玉","性别":"男","职务":"民警","电话":"17603711205","警务室":"甘寨警务室","警务工作站":"东瓦店村"},
	        { "姓名":"郭宝玉","性别":"男","职务":"民警","电话":"17603711205","警务室":"东街警务室","警务工作站":"西瓦店村"},
	        { "姓名":"周玉芳","性别":"女","职务":"民警","电话":"15238063789","警务室":"东街警务室","警务工作站":"翟沟村"},
	        { "姓名":"周玉芳","性别":"女","职务":"民警","电话":"15238063789","警务室":"甘寨警务室","警务工作站":"高沟村"},

	        { "姓名":"郭晓航","性别":"男","职务":"辅警","电话":"17803850885","警务室":"东街警务室","警务工作站":"翟沟村"},
	        { "姓名":"郭晓航","性别":"男","职务":"辅警","电话":"17803850885","警务室":"甘寨警务室","警务工作站":"高沟村"},
	        { "姓名":"杨牧原","性别":"男","职务":"辅警","电话":"17803850889","警务室":"东街警务室","警务工作站":"西瓦店村"},
	        { "姓名":"杨牧原","性别":"男","职务":"辅警","电话":"17803850889","警务室":"甘寨警务室","警务工作站":"东瓦店村"},
	        { "姓名":"汪岩","性别":"男","职务":"辅警","电话":"15537141112","警务室":"东街警务室","警务工作站":"东街村"},
	        { "姓名":"汪岩","性别":"男","职务":"辅警","电话":"15537141112","警务室":"东街警务室","警务工作站":"南街村"},
	        { "姓名":"郭石磊","性别":"男","职务":"辅警","电话":"13333826265","警务室":"东街警务室 ","警务工作站":"西街村"},
	        { "姓名":"郭石磊","性别":"男","职务":"辅警","电话":"13333826265","警务室":"甘寨警务室 ","警务工作站":"湾子河村"},
	        { "姓名":"尚志龙","性别":"男","职务":"辅警","电话":"13592684777","警务室":"甘寨警务室 ","警务工作站":"甘寨村"},
	        { "姓名":"尚志龙","性别":"男","职务":"辅警","电话":"13592684777","警务室":"甘寨警务室 ","警务工作站":"楚沟村"},
	        
	    ],
	    "青屏街派出所": [
	        { "姓名":"王玉虎","性别":"男","职务":"民警","电话":"17603711728","警务室":"幸福街警务室","警务工作站":"幸福街"},
	        { "姓名":"王玉虎","性别":"男","职务":"民警","电话":"17603711728","警务室":"幸福街警务室","警务工作站":"农业路"},
	        { "姓名":"张文君","性别":"男","职务":"民警","电话":"13674919298","警务室":"长乐路警务室","警务工作站":"嵩阳路"},
	        { "姓名":"张文君","性别":"男","职务":"民警","电话":"13674919298","警务室":"长乐路警务室","警务工作站":"广场社区"},
	        { "姓名":"宋明玉","性别":"女","职务":"民警","电话":"17603711723","警务室":"长乐路警务室","警务工作站":"长乐路"},
	        { "姓名":"宋明玉","性别":"女","职务":"民警","电话":"17603711723","警务室":"长乐路警务室","警务工作站":"于家岗"},

	        { "姓名":"姜敏","性别":"女","职务":"民警","电话":"18838268518","警务室":"青峰路警务室","警务工作站":"青峰路"},
	        { "姓名":"张亚楠","性别":"女","职务":"民警","电话":"13509856056","警务室":"青峰路警务室","警务工作站":"北文峰路"},
	        { "姓名":"王云兰 ","性别":"女","职务":"民警","电话":"17603711759","警务室":"梁沟警务室","警务工作站":"梁沟"},
	        { "姓名":"王云兰 ","性别":"女","职务":"民警","电话":"17603711759","警务室":"梁沟警务室","警务工作站":"百花巷"},
	        { "姓名":"王云兰 ","性别":"女","职务":"民警","电话":"17603711759","警务室":"梁沟警务室","警务工作站":"青屏大街"},
	        { "姓名":"王云兰 ","性别":"女","职务":"民警","电话":"17603711759","警务室":"梁沟警务室","警务工作站":"方寨"},


	        { "姓名":"李格 ","性别":"男","职务":"民警","电话":"17603711738","警务室":"北密新路警务室","警务工作站":"北密新路"},
	        { "姓名":"李格 ","性别":"男","职务":"民警","电话":"17603711738","警务室":"北密新路警务室","警务工作站":"开阳"},
	        { "姓名":"李格 ","性别":"男","职务":"民警","电话":"17603711738","警务室":"北密新路警务室","警务工作站":"气象街"},
	        { "姓名":"马双","性别":"女","职务":"民警","电话":"13603986786","警务室":"北密新路警务室","警务工作站":"周楼"},

	        
	        
	        { "姓名":"楚凯歌","性别":"男","职务":"辅警","电话":"15937155908","警务室":"幸福街警务室","警务工作站":"幸福街"},
	        { "姓名":"姜佳恒","性别":"男","职务":"辅警","电话":"18738735597","警务室":"幸福街警务室","警务工作站":"农业路"},
	        { "姓名":"张会桥","性别":"男","职务":"辅警","电话":"15093337726","警务室":"长乐路警务室","警务工作站":"嵩阳路"},
	        { "姓名":"吕一凡","性别":"男","职务":"辅警","电话":"15093057722","警务室":"长乐路警务室","警务工作站":"广场社区"},
	        { "姓名":"王庆豪","性别":"男","职务":"辅警","电话":"15981852234","警务室":"长乐路警务室","警务工作站":"长乐路"},
	        { "姓名":"杨晓东","性别":"男","职务":"辅警","电话":"15515991957","警务室":"长乐路警务室","警务工作站":"于家岗"},
	        { "姓名":"朱振涛","性别":"男","职务":"辅警","电话":"13673992608","警务室":"青峰路警务室","警务工作站":"青峰路"},
	        { "姓名":"韩建东","性别":"男","职务":"辅警","电话":"18539436116","警务室":"青峰路警务室","警务工作站":"青峰路"},
	        { "姓名":"丁志鹏","性别":"男","职务":"辅警","电话":"15803817833","警务室":"青峰路警务室","警务工作站":"北文峰路"},
	        { "姓名":"屈银刚","性别":"男","职务":"辅警","电话":"15890009001","警务室":"青峰路警务室","警务工作站":"北文峰路"},
	        { "姓名":"李旭东","性别":"男","职务":"辅警","电话":"18437188688","警务室":"青峰路警务室","警务工作站":"北文峰路"},
	        { "姓名":"苏玉龙","性别":"男","职务":"辅警","电话":"15136205957","警务室":"梁沟警务室","警务工作站":"梁沟"},
	        { "姓名":"孙起隆","性别":"男","职务":"辅警","电话":"15136205957","警务室":"梁沟警务室","警务工作站":"百花巷"},
	        { "姓名":"楚铭","性别":"男","职务":"辅警","电话":"18538167006","警务室":"梁沟警务室","警务工作站":"青屏大街"},
	        { "姓名":"楚铭","性别":"男","职务":"辅警","电话":"18538167006","警务室":"梁沟警务室","警务工作站":"方寨"},
	        { "姓名":"杨东浩","性别":"男","职务":"辅警","电话":"13523061314","警务室":"北密新路警务室","警务工作站":"北密新路"},
	        { "姓名":"孟一丹","性别":"女","职务":"辅警","电话":"15290869958","警务室":"北密新路警务室","警务工作站":"北密新路"},
	        { "姓名":"李宏鹏","性别":"男","职务":"辅警","电话":"13838049444","警务室":"北密新路警务室","警务工作站":"周楼"},
	        { "姓名":"郑可","性别":"女","职务":"辅警","电话":"18336340963","警务室":"北密新路警务室","警务工作站":"周楼"},
	        { "姓名":"刘星君","性别":"男","职务":"辅警","电话":"13523580573","警务室":"北密新路警务室","警务工作站":"开阳"},
	        { "姓名":"张建华","性别":"男","职务":"辅警","电话":"13938212312","警务室":"北密新路警务室","警务工作站":"开阳"},
	        { "姓名":"高国强","性别":"男","职务":"辅警","电话":"1","警务室":"北密新路警务室","警务工作站":"气象街"},

	    ],
	}
//社区民警
function sshequminjingbFun(){
	var name=$('#table-info1').html();
	//var data=sshequminjingbdata[name]
	var data=sjingeuhsibdata[name];
	var len=data.length;
	
	var html='<div>';
//	html+='<table class="map-table" style="margin-top:10px;">';
//	html+='<tbody><tr>';
//
//	html+='	<td><div class="map-calss1 common-color map-calss" style="color: #fff" id="eachAll11">&nbsp;<span id="shiyourenkou1">901867</span></div></td>';	
//
//	html+='	<td><div class="map-calss6 common-color map-calss" style="color: #fff" id="eachAll41">&nbsp;<span id="shiyoufangwu1">192924</span></div></td>';	
//
//	html+='	<td><div class="map-calss3 common-color map-calss" style="color: #fff" id="eachAll41">&nbsp;<span id="shiyoudanwei1">2924</span></div></td>';	
//
//	html+='	<td><div class="map-calss2 common-color map-calss" style="color: #fff" id="eachAll21">&nbsp;<span id="cahngzhurenkou1">759249</span></div></td>';
//	html+='</tr>';
//	
//	html+='<tr>';
//	html+='<td align="center" class="table-info-name">实有人口</td>';	
//	html+='	<td align="center" class="table-info-name">实有房屋</td>';
//	html+='<td align="center" class="table-info-name">实有单位</td>';
//	html+='	<td align="center" class="table-info-name">常住人口</td>';
//	html+='</tr>';
//	
//	html+='<tr>';
//
//	html+='<td><div class="map-calss3 common-color map-calss" style="color: #fff" id="eachAll31">&nbsp;<span id="liudongrenkou1">140012</span></div></td>';	
//	
//	html+='<td><div class="map-calss4 common-color map-calss" style="color: #fff" id="eachAll41">&nbsp;<span id="zhongdianrenkou1">1420</div></td>';
//
//	html+='<td onclick="sshequminjingbFun(\'民警\')"><div class="map-calss5 common-color map-calss" style="color: #fff" id="eachAll51">&nbsp;<span id="shequminjing1">60</span></div></td>';
//	
//	html+='<td><div class="map-calss7 common-color map-calss" style="color: #fff" id="eachAll61">&nbsp;<span id="baocunfujing1">290</span></div></td>';
//	
//	html+='<tr>';
//	html+='<td align="center" class="table-info-name">流动人口</td>';	
//	html+='<td align="center" class="table-info-name">重点人口</td>';
//	html+='<td align="center" class="table-info-name">社区民警</td>';
//	html+='<td align="center" class="table-info-name">包村辅警</td>';
//	html+='</tr>';
//	html+='</tbody></table>';


	
	html += '<table style="margin-top:20px;box-shadow: 1px 1px 3px #436dc0;" class="ta-bg">';
	html += '<tr>';
	html += '<td>姓名</td>';
	html += '<td>性别</td>';
	html += '<td>职务</td>';
	html += '<td>电话</td>';
	html += '<td>警务室</td>';
	//html += '<td>警务工作站</td>';
	html += '</tr>';
	for(var i=0;i<len;i++){

	
			html += '<tr>';
			for(var k in data[i]){   
				html += '<td  style="color:#eea807;cursor: pointer;" attrname="'+data[i]['警务室']+'" onclick="sjingeuhsibdataFun(this)">' + data[i][k] + '</td>';
			}
			html += '</tr>'
		
	}
	
	
	 html += '</table>';
	 html +='</div>'
	 layer.open({
		  type: 1,
		  title: false,
		  closeBtn: 0,
		  area: ['900px', 'auto'],
		  shadeClose: true,
		  skin: 'sclassb',
		  content: html
		});
}
function sjingeuhsibdataFun(_this){
	var name=$.trim($('#table-info1').html());
	var data=sshequminjingbdata[name];
	
	var jingwushiname=$.trim($(_this).attr('attrname'))
	var len=data.length;
	
	 var datashisha=sgongzuozhanb[name][jingwushiname];
	
	
	
	var html='<div>';
	html+='<table class="map-table" style="margin-top:10px;">';
	html+='<tbody><tr>';

	html+='	<td><div class="map-calss1 common-color map-calss" style="color: #fff" id="eachAll11">&nbsp;<span id="" class="sshiyourenkoub">901867</span></div></td>';	

	html+='	<td><div class="map-calss6 common-color map-calss" style="color: #fff" id="eachAll41">&nbsp;<span id=""  class="sshiyoufangwub">192924</span></div></td>';	

	html+='	<td><div class="map-calss3 common-color map-calss" style="color: #fff" id="eachAll41">&nbsp;<span id=""  class="sshiyoudanweib">2924</span></div></td>';	

	html+='	<td><div class="map-calss2 common-color map-calss" style="color: #fff" id="eachAll21">&nbsp;<span id=""  class="scahngzhurenkoub">759249</span></div></td>';
	html+='</tr>';
	
	html+='<tr>';
	html+='<td align="center" class="table-info-name">实有人口</td>';	
	html+='	<td align="center" class="table-info-name">实有房屋</td>';
	html+='<td align="center" class="table-info-name">实有单位</td>';
	html+='	<td align="center" class="table-info-name">常住人口</td>';
	html+='</tr>';
	
	html+='<tr>';

	html+='<td><div class="map-calss3 common-color map-calss" style="color: #fff" id="eachAll31">&nbsp;<span id=""  class="sliudongrenkoub">140012</span></div></td>';	
	
	html+='<td><div class="map-calss4 common-color map-calss" style="color: #fff" id="eachAll41">&nbsp;<span id=""  class="szhongdianrenkoub">1420</div></td>';

	html+='<td><div class="map-calss5 common-color map-calss" style="color: #fff" id="">&nbsp;<span id=""  class="sshequminjingb">60</span></div></td>';
	
	html+='<td><div class="map-calss7 common-color map-calss" style="color: #fff" id="eachAll61">&nbsp;<span id=""  class="sbaocunminjingb">290</span></div></td>';
	
	html+='<tr>';
	html+='<td align="center" class="table-info-name">流动人口</td>';	
	html+='<td align="center" class="table-info-name">重点人口</td>';
	html+='<td align="center" class="table-info-name">社区民警</td>';
	html+='<td align="center" class="table-info-name">包村辅警</td>';
	html+='</tr>';
	html+='</tbody></table>';


	
	html += '<table style="margin-top:20px;box-shadow: 1px 1px 3px #436dc0;"  class="ta-bg">';
	html += '<tr>';
	html += '<td>姓名</td>';
	html += '<td>性别</td>';
	html += '<td>职务</td>';
	html += '<td>电话</td>';
	html += '<td>警务室</td>';
	html += '<td>警务工作站</td>';
	html += '</tr>';
	for(var i=0;i<len;i++){
		if($.trim(data[i]['警务室'])==$.trim(jingwushiname)){
			html += '<tr >';
			for(var k in data[i]){ 
				
					html += '<td  style="color:#eea807;cursor: pointer;" attrname="'+data[i]['警务室']+'"   attrgongzuozhanname="'+data[i]['警务工作站']+'"  onclick="sshequxin(this)">' + data[i][k] + '</td>';
				}
			html += '</tr>'
			}
		
	
	}
		
	 html += '</table>';
	 html += '</div>';
	 
	 
	 
	 layer.open({
		  type: 1,
		  title: false,
		  closeBtn: 0,
		  area: ['900px', 'auto'],
		  shadeClose: true,
		  skin: 'sclassb',
		  content: html
		});
	
	 $('.sshiyourenkoub').html(datashisha['实有人口']);
	 $('.sshiyoufangwub').html(datashisha['实有房屋']);
	 $('.sshiyoudanweib').html(datashisha['实有单位']);
	 $('.scahngzhurenkoub').html(datashisha['常住人口']);
	 $('.sliudongrenkoub').html(datashisha['流动人员']);
	 $('.szhongdianrenkoub').html(datashisha['重点人员']);
	 $('.sshequminjingb').html(datashisha['社区民警']);
	 $('.sbaocunminjingb').html(datashisha['包村辅警']);
	
}
function sshequxin(_this){
	var name=$.trim($('#table-info1').html());
	var attrname=$.trim($(_this).attr('attrname'));
	var attrgongzuozhanname=$.trim($(_this).attr('attrgongzuozhanname'));
	var datashisha=sgongzuozhanb[name][attrname]['工作站'][attrgongzuozhanname];
	 $('.sshiyourenkoub').html(datashisha['实有人口']);
	 $('.sshiyoufangwub').html(datashisha['实有房屋']);
	 $('.sshiyoudanweib').html(datashisha['实有单位']);
	 $('.scahngzhurenkoub').html(datashisha['常住人口']);
	 $('.sliudongrenkoub').html(datashisha['流动人员']);
	 $('.szhongdianrenkoub').html(datashisha['重点人员']);
	 $('.sshequminjingb').html(datashisha['社区民警']);
	 $('.sbaocunminjingb').html(datashisha['包村辅警']);
}
