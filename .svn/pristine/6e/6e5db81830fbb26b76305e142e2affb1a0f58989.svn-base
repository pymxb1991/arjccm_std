/**
 * Created by oHa on 2017/12/28.
 */
var windowsHeight, _fontSize = 14, _fontSize1 = 26, breakData = 8,
legendTop = '30%', radiusData = ['40%', '65%'], lengthECharts = 30,mapHeigth = '90%',zoom=14.75,centerCoordinate= [ 117.663920, 39.035650 ];
// 基础颜色表
var color = [ '#D87A82', '#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d',
		'#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f' ];
$(function() {
 $('.indexclose').click(function(){
	 $("#index1").hide();
	 $("#index2").hide();
 })
	//
	var context = $(".context").attr("content");
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
	var windowsHeight, _fontSize = 14,

	_fontSize1 = 26,
	breakData = 8;
	legendTop = '30%',
	radiusData = [90, 65],
	lengthECharts = 30;
 
	/* var	context = $(".context").attr("content"); */
	var FontColor="#999",backgroundColor="#fff";
	var theme=$.cookie('theme'); 
	if(theme=="black"){
		FontColor="#fff";
		backgroundColor="#0e2a4c";
	}
	
	// 实有人口统计情况
	$.getJSON(context + "/report/ccmPeopleAmount/getAnalyzePopData", function(
			data) {
			$("#registerPop").html(data[0]||0)
			$("#overseasPop").html(data[1]||0)
			$("#flowPop").html(data[2]||0)
			$("#noRegisterPop").html(data[3]||0)
			$("#permanentPop").html(data[4]||0)
			var sum = "0";
			var html = '<div class="pop-name">人口总数:</div>';
			sum = (Number(data[0])+Number(data[1])+Number(data[2])+Number(data[3]))+"";
			for(var j=0;j<7-sum.length;j++){
				html += '<span class="pop-num"></span>';
			}
			for(var i=0;i<sum.length;i++){
				html += '<span class="pop-num">'+sum.charAt(i)+'</span>';
			}
			$("#popAll").html(html)
	});
	
	
	//  重点青少年分析
/*	$.getJSON(context + "/report/ccmPeopleAmount/getnumPopFlowTable", function(
			data) {
		// 接收参数
		
		PopKeyEchartsFun(data);
	
	});*/
	// 特殊人群分析
/*	$.getJSON(context + "/report/ccmPeopleAmount/getSpecialPopData", function(
			data) {
		// 接收参数
		PopsNumEchartsFun(data);
	});*/
	//特殊人群类型及变化趋势
	$.getJSON(context + "/report/ccmPeopleAmount/findPopTrendByArea",
				function(data) {
					// 填充数据
					$.popEcharts("popTrendArea", data);
					 map();
					//事件流程
					$.AlarmFlowFun();
				
	});
	$.getJSON(context + "/report/ccmPeopleAmount/getCountpersonAndEvent",
		function(data) {
			// 填充数据
			RenKouQingKuan(data);
		});
	//各街道人口数据对比（重点人群与特殊人群关系）
/*	$.getJSON(context + "/report/ccmPeopleAmount/getStreetPopData", function(
			data) {
		// 接收参数
		 StreetPopEchartsFun('StreetPopEcharts',data)
		
	});*/
	
	$('#StreetPopEcharts').click(function(){
		$('#index1').show();
		$('#index2').hide();
		$.getJSON(context + "/report/ccmPeopleAmount/getStreetPopData", function(
				data) {
			// 接收参数
			 StreetPopEchartsFunbck('StreetPopEchartsbck',data)
		});
	})
	//城市部件状态
	$.getJSON(context + "/citycomponents/ccmCityComponents/getCityType", function(
			data) {
		   //公用设施
        ChengShiBuJianFun(data[0])
		// SafeLevelEchartsFun('ChengShiBuJian','公用设施',data[0])
        ChengShiBuJianFun
	       //道路交通
        DaoLuJiaoTongFun(data[1])
		// SafeLevelEchartsFun('DaoLuJiaoTong','道路交通',data[1])
	       //市容环境
        ShiRongHuangjingFun(data[2])
        // SafeLevelEchartsFun('ShiRongHuangjing','市容环境',data[2])
	      
	});
	// 城市部件状态-大屏-滨海新区社会网格化管理信息平台
    $.getJSON(context + "/citycomponents/ccmCityComponents/getCityTypeAll",
			function(data) {
			// 填充数据  
    		CityBuEchartsFun($.ToConvertA(data))
	});
    // 城市部件数据一览-大屏-滨海新区社会网格化管理信息平台
    $.getJSON(context + "/citycomponents/ccmCityComponents/getCityData",
			function(data) {
			// 填充数据  
    	$("#city1").html(data[0])
		$("#city2").html(data[1])
		$("#city3").html(data[2])
	});
    //案事件处理
	$.getJSON("/arjccm/a/report/ccmIncidentBegin/getItemBySumToday",
		function(data) {
			//接收参数
			EventTotalFun(data);
		}
	);
	//案事件处理比例-大屏-滨海新区社会网格化管理信息平台
	$.getJSON("/arjccm/a/report/ccmIncidentBegin/findSumByMon",
		function(data) {
			//接收参数
		   EventEcharts1Fun(data)
		}
	);

	// 事件区域分布
	$.getJSON(context + "/event/ccmEventIncident/getSafeDisData?eventType= ", function(
			data) {
		// 接收参数
	    SafeDisEchartsFun(data)
	});
	// 事故发生趋势
	$.getJSON(context + "/event/ccmEventIncident/getSafeHappenData", function(
			data) {
		// 接收参数
	    SafeHappenEchartsFun(data)
	});

	//事件性质
/*	$.getJSON(context + "/report/ccmIncidentBegin/getItemByProperty", function(
			data) {
		// 接收参数   
        SafeTypeEchartsFun('SafeTypeEcharts','事件性质',$.ToConvertA(data))
	});*/
	// 案事件分级统计
	/*$.getJSON(context + "/report/ccmIncidentBegin/getItemByScale", function(
			data) {
			//事件分级
	      	EventTypeLevelEchartsFun('EventTypeLevelEcharts','事件分级',data)
	});*/
	// 案事件类型
   /* $.getJSON(context + "/report/ccmIncidentBegin/findSumByEventType",
			function(data) {
			// 填充数据  
			SafeLevelEchartsFun('SafeLevelEcharts','事件类型',$.ToConvertA(data))
	});*/
	// 事件分类
	/*$.getJSON(context + "/report/ccmIncidentBegin/findEventFenLeiData",
			function(data) {
			// 填充数据   
			EventFenLeiEchartsFun($.ToConvertA(data))
	});*/
 	//近一年重点人员帮扶/事件发生趋势图
    $.getJSON(context + "/report/ccmIncidentBegin/findEventYearMap",function(
    		data) {
				// 填充数据
    	$.GetChangeYearSheets("findEventYearMap", data);
	});
	// 税收统计
/*	$.getJSON(context + "/know/ccmEconomicsMonth/getShuiShouData",
		function(data) {
			//接收参数
		  	ShuiShouFun(data)	
		}
	);*/
	//企业数量-大屏-滨海新区社会网格化管理信息平台
	$.getJSON(context + "/org/ccmOrgNpse/getqiyeNumData",
		function(data) {
			//接收参数
			$("#qiyeNum").html(data);//企业数量
		}
	);
	//治安重点场所
	$.getJSON(context + "/org/ccmOrgNpse/getSafePubData", function(
			data) {
			// 接收参数
		 	var gao = "0";
		 	var da = "0";
		 	var yi = "0";
		 	var di = "0";
		 	for (var one in data) {
		 		if("高风险单位"==(data[one]["type"])){
		 			gao = data[one]["value"];
		 		}
		 		if("较大风险单位"==(data[one]["type"])){
		 			da = data[one]["value"];
		 		}
		 		if("一般风险单位"==(data[one]["type"])){
		 			yi = data[one]["value"];
		 		}
		 		if("低风险单位"==(data[one]["type"])){
		 			di = data[one]["value"];
		 		}
		 	}
		 	$("#gao").html(gao);//高风险单位
		 	$("#da").html(da);//企业数量
		 	$("#yi").html(yi);//一般风险单位
		 	$("#di").html(di);//低风险单位
	});
	//风险单位数据分析-按街道-大屏-滨海新区社会网格化管理信息平台
	$.getJSON(context + "/org/ccmOrgNpse/getwhatRiskRankData",
			function(data) {
				//接收参数
				whatEchartsFun(data)
	});

	$('#whatEcharts').click(function(){
		$("#index1").hide()
		$("#index2").show()
		$.getJSON(context + "/org/ccmOrgNpse/getwhatRiskRankData",
				function(data) {
					//接收参数
					whatEchartsFunbak(data)
		});
	})
	
	 // 组织队伍建设数据-大屏-滨海新区社会网格化管理信息平台
    $.getJSON(context + "/org/ccmOrgOrgprevent/getOrgpreventData",
			function(data) {
			// 填充数据  
    	$("#jiedao").html(data[0])//街道
		$("#wangge").html(data[1])//网格
		$("#shequ").html(data[2])//社区
		$("#wanggeyuan").html(data[3])//网格员
		$("#zuzi").html(data[4])//群防群治组织
		$("#duiwu").html(data[5])//群防群治队伍人数
		$("#zhongxin").html(data[6])//综治中心
	});

	//群防群治组织结构-大屏-滨海新区社会网格化管理信息平台
    $.getJSON(context + "/org/ccmOrgOrgprevent/findOrgpreventComTypeType",
			function(data) {
			 //群防群治组织结构
			OrgEchartsFun($.ToConvertA(data))
	});
    //群防群治队伍构成-性别-大屏-滨海新区社会网格化管理信息平台
    $.getJSON(context + "/org/ccmOrgGropprevent/findGroppreventSexType",
		function(data) {
			//接收参数
			RanksEchartsFun(data)
		}
	);
    //街道信息-大屏-滨海新区社会网格化管理信息平台
    $.getJSON(context + "/report/ccmReportOthers/findStringType",
		function(data) {
			//接收参数
    	 var len=data.length;
    	 if(len>0){
    		 var html='';
    		 for(var i=0;i<len;i++){
    			 html+='<option value="'+data[i].name+'">'+data[i].name+'</option>';
    		 }
    		 $('#treeType optgroup').html(html);
    		 
    		 
    		 
    		 treeTypeInit(data)
    		 $("#treeType").change(function () { 
    			 treeTypeInit(data)
    		 })
    		// console.log(data)
    	 }
		}
	);
	
    
   
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

	

//地圖
  var Map;
  function map(){
	// 地图默认数据设置
		var defaultPrams = {
			id : 'StatIndexMap',
			centerCoordinate :centerCoordinateSituation,
			zoom : 16.5,
			maxZoom : 18,
			minZoom : 9.8,		
			baseUrl: baseUrlT,
			zoomShowOrHide : false,// 缩小放大
			overviewMap : false,
			selectPointerFlag:true
		// 鹰眼图
		}
		Map = new ArjMap.Map(defaultPrams);
		// 加载地图
		Map.init();
  }
  
  function treeTypeInit(data){
  	var len=data.length;
  	 var value=$('#treeType option:selected').val();
		 for(var  j=0;j<len;j++){
			 if(data[j].name==value){
				 $('#mapDetails-SheQu').html(data[j].shequNum);
				 $('#mapDetails-LouDong').html(data[j].loudongNum);
				 $('#mapDetails-DanYuan').html(data[j].danyuanNum);
				 $('#mapDetails-FangWu').html(data[j].fangwuNum);
				 $('#mapDetails-XueXiao').html(data[j].xuexiaoNum);
				 $('#mapDetails-RenKou').html(data[j].renkouNum);
				 $('#mapDetails-ZhongDian').html(data[j].zhongdianchangsuoNum);
			Map.clearOverlays();	 
			 var shequDataValue=data[j].shequData;
				 var shequDataLen=shequDataValue.length;
				 for(var k=0;k<shequDataLen;k++){
					 
				  var name=shequDataValue[k].name;
					var  Point =shequDataValue[k].point.split(',');
					var  id =shequDataValue[k].id
					if(Point=="0"){
						continue
					}
					PointFun(name,Point,id,'ban1.png')
				 }
				 var xuexiaoDataValue=data[j].xuexiaoData;
				 var xuexiaoDataLen=xuexiaoDataValue.length;
				 for(var m=0;m<xuexiaoDataLen;m++){
						var  Point =xuexiaoDataValue[m].point.split(',')
						 var name=xuexiaoDataValue[m].name;
						 var id=xuexiaoDataValue[m].id;
						if(Point=="0"){
							continue
						}
						PointFun(name,Point,id,'schools.png')
					 }
				 
				 var zhongdianchangsuoDataValue=data[j].zhongdianchangsuoData;
				 var zhongdianchangsuoDataLen=zhongdianchangsuoDataValue.length;
				 for(var n=0;n<zhongdianchangsuoDataLen;n++){
						var  Point =zhongdianchangsuoDataValue[n].point.split(',')
						 var name=zhongdianchangsuoDataValue[n].name;
						 var id=zhongdianchangsuoDataValue[n].id;
						if(Point=="0"){
							continue
						}
						PointFun(name,Point,id,'security1.png')
					 }
				
			 }
		 }
  	
  }
  function PointFun(name, coordinate,overlayId,icon){
  	//console.log(Point)
  	Map.addOverlayLabel4(name, coordinate,overlayId,icon,'')
  	
  }

	// 地图
	function map1() {
		// 分辨率 卫星地图 
	    var resolutions = [  
	        0.00243656806990755,  
	        0.00121828403495378,  
	        0.000609142017476888,  
	        0.000304571008738444,  
	        0.000152285504369222,  
	        7.6142752184611E-05,  
	        3.80713760923055E-05,  
	        1.90356880461528E-05,  
	        9.51784402307638E-06,  
	        4.75892201153819E-06,  
	        2.37946100576909E-06,  
	        1.18973050288455E-06  
	    ]; 
		
	    var resolutions1=[  
	        1.40625,  
	        0.703125,  
	        0.3515625,  
	        0.17578125,  
	        0.087890625,  
	        0.0439453125,  
	        0.02197265625,  
	        0.010986328125,  
	        0.0054931640625,  
	        0.00274658203125,  
	        0.001373291015625,  
	        0.0006866455078125,  
	        0.00034332275390625,  
	        0.000171661376953125,  
	        8.58306884765625E-05,  
	        4.29153442382813E-05,  
	        2.14576721191406E-05,  
	        1.07288360595703E-05,  
	        5.36441802978516E-06,  
	        2.68220901489258E-06  
	    ];  
		
		
		// 地图默认数据设置
		var defaultPrams = {
			id : 'mapMask',
			centerCoordinate : centerCoordinate,
			zoom : zoom,
			maxZoom : 18,
			minZoom : 9.8,
			baseUrl : [{
				'type':'xianchang',
				'id':'yingxiang',
				'name' : '影像图 ',
				'url' : 'http://59.193.204.40/51b153b49e2542550cd9cfc43d84201408f44ac0/Tile/ArcGISRest/BHRHDOMCGCS2000.gis/tile/{z}/{y}/{x}',
				'isShow' : true,
				'resolutions':resolutions,
				'tileSize':'512',
				'origin':[-400.0, 400.0],
			}],
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
			centerCoordinate : centerCoordinate,
			zoom : zoom,
			maxZoom : 18,
			minZoom : 14,
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
		Map.postcomposeOlIndex('紧急',zuobiao,"123")
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
//事件流程
$.AlarmFlowFun=function(){
	var width=$(window).width();
    var symbolSize=20;
    var symbolSize1=20
    var symbolSize2=20;
	var PC=[40.46607, 10.093863];
	var APP=[40.46607, 70.093863];
	var WeiXin=[40.46607, 135.093863];
	var FenPai=[125.65607, 70.093863];
	var ChuLi1=[320.0961, 10.093863];
	var ChuLi2=[320.0961, 70.093863];
	var ChuLi3=[320.0961, 135.093863];
	var KaoHe=[420.4361, 70.093863];
	var JieShu=[515.4361, 70.093863];
	var RenYuan1=[220.8361, 10.093863];
	var RenYuan2=[220.8361, 70.093863];
	var RenYuan3=[220.8361, 135.093863];
	if(width>=1680&&width<1920){
		  symbolSize=20;
		  symbolSize1=20;
	      symbolSize2=20;
		  PC=[40.46607, 15.093863];
		  APP=[40.46607, 100.093863];
		  WeiXin=[40.46607, 200.093863];
		  FenPai=[80.65607, 100.093863];
		  ChuLi1=[420.0961, 15.093863];
		  ChuLi2=[420.0961, 100.093863];
		  ChuLi3=[420.0961, 200.093863];
		  KaoHe=[570.4361, 100.093863];
		  JieShu=[690.4361, 100.093863];
		  RenYuan1=[240.8361, 15.093863];
		  RenYuan2=[240.8361, 100.093863];
		  RenYuan3=[240.8361, 200.093863];
	}else if(width>=1600&&width<1680){
		 symbolSize=20;
		  symbolSize1=20;
	      symbolSize2=20;
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
		 symbolSize=20;
		  symbolSize1=20;
	      symbolSize2=20;
		  PC=[40.46607, 15.093863];
		  APP=[40.46607, 75.093863];
		  WeiXin=[40.46607, 145.093863];
		  FenPai=[140.65607, 75.093863];
		  ChuLi1=[380.0961, 15.093863];
		  ChuLi2=[380.0961, 75.093863];
		  ChuLi3=[380.0961, 145.093863];
		  KaoHe=[500.4361, 75.093863];
		  JieShu=[590.4361, 75.093863];
		  RenYuan1=[270.8361, 15.093863];
		  RenYuan2=[270.8361, 75.093863];
		  RenYuan3=[270.8361, 145.093863];
	}else if(width<1440){
		  symbolSize=20;
		  symbolSize1=20;
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
//	            tooltip: {
//	                trigger: 'item',
//	                show: true,
//	                alwaysShowContent: true,
//	                position: [185, 75],
//	                formatter: '{b}<br>MQ接收数据13278条<br>MQ取出数据13278条'
//	            },
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
	//税收统计
	function ShuiShouFun(data){
	   	//
	   	var years2;
	   	for (var one in data) {
			var Time = new Date(data[one]["months"].time);
			years2 = Time.getFullYear();
	    }
	   	var years1;
	   	var years3;
	   	for (var one in data) {
			var Time = new Date(data[one]["months"].time);
			years3 = Time.getFullYear();
			if(years2 != years3){
				years1 = years3;
			}
	    }
	   	var name = [];
	   	name.push(years1+"年");
	   	name.push(years2+"年");
	   	
	   	//
		var datas1 = [0,0,0,0,0,0,0,0,0,0,0,0];
		var datas2 = [0,0,0,0,0,0,0,0,0,0,0,0];
		var m =["01","02","03","04","05","06","07","08","09","10","11","12"]
		for (var x in m) {
			for (var one in data) {
				var Time = new Date(data[one]["months"].time);
				var years = Time.getFullYear();
				if(years==years1){
					var month = Time.getMonth() + 1;
					if (month < 10){
						month = "0"+month;
					}
					if(m[x] == month){
						datas1[x] = Number(data[one]["revenue"]);
					}
					
				}
				if(years==years2){
					var month = Time.getMonth() + 1;
					if (month < 10){
						month = "0"+month;
					}
					if(m[x] == month){
						datas2[x] = Number(data[one]["revenue"]);
					}
					
				}
		    }
		}
		
		//累计纳税
		var qunian = 0;
		for (var qu in datas1) {
			qunian += datas1[qu];
		}
		var jinnian = 0;
		for (var jin in datas2) {
			jinnian += datas2[jin];
		}
		var bi = 0.0;
		var tongbi = "+0.0%";
		bi = ((jinnian*1.0-qunian*1.0)*100/qunian*1.0).toFixed(1);
		if(bi>=0){
			tongbi = "+"+bi+"%";
		}else{
			tongbi = +bi+"%";
		}
		$("#leiji").html(jinnian+"");//累计纳税
		$("#tongbi").html(tongbi+"");//同比
		
		
		
		var option = {
			
		       legend: {
		           data:[name[0],name[1]],
		           textStyle: {
		               color: '#fff',
		               fontSize: _fontSize
		           }
		       },
		       tooltip: {
	                trigger: 'item',
	                formatter: "{a} <br/>{b}: {c}",
			        confine:true
	            },
		       grid: {
		           left: '3%',
		           right: '4%',
		           bottom: '3%',
		           top:'14%',
		           containLabel: true
		       },
		       color:['#58c7fc','#1BF79C'],
		       xAxis : [
		           {
		               type : 'category',
		               data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
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
		           {  show:false, 
		               type : 'value'
		           }
		       ],
		       series : [
		           {
		               name:name[0],
		               type:'bar',
		               data:datas1
		           },
		           {
		               name:name[1],
		               type:'bar',
		               data:datas2
		           }
		       ]
		   };
	
	   // 实例化对象
	   var Barchart = echarts.init(document.getElementById('AlarmInfoWeekEcharts'));
	   // 传参
	   Barchart.setOption(option);
	}
	
	
	// 案事件处理
	function EventTotalFun(Data) {
		
		$('#HandlindToday').html(Data["0"]);//待处理
		$('#TotalToday').html(Data["1"]);//处理中
		$('#HandledToday').html(Data["2"]);//已处理
	}

	//事件区域分布
	function SafeDisEchartsFun(data){
    	//
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
			    title: {
			        text: '事件区域分布',
			        x: 'center',
			        y: 'top',
			        textStyle: {
			            fontWeight: 'normal',
			            color: '#fff',
			            fontSize: '14'
			        }
			    },
			    grid: {
		            top: '20%',
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

    	        title: {
       			        text: '事故发生趋势',
       			        x: 'center',
       			        y: 'top',
       			        textStyle: {
       			            fontWeight: 'normal',
       			            color: '#fff',
       			            fontSize: '14'
       			        }
       			    },
			     grid: {
			        top: '20%',
			        left: '1%',
			        right: '1%',
			        bottom: '5%',
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
   function SafeTypeEchartsFun(id,name,data){
	   
	    var option = {
	    	    color:['#5cb0ea','#ffb289','#d87a82','#8d99b3','#e5ce0c','#97b553'],
	    	    tooltip: {
	                trigger: 'item',
	                formatter: "{b} :<br/> {c} ({d}%)",
			        confine:true
	            },

	            title: {
       			        text: name,
       			        x: 'center',
       			        y: 'bottom',
       			        textStyle: {
       			            fontWeight: 'normal',
       			            color: '#fff',
       			            fontSize: '14'
       			        }
       			    },
	    	    calculable : true,
	    	    series : [
	    	        {
	    	            name:name,
	    	            type:'pie',
	    	            radius : ['30%', '70%'],
	    	            center : ['50%', '45%'],
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
	   var Barchart = echarts.init(document.getElementById(id));
	   // 传参
	   Barchart.setOption(option);
   }
   
   
   //事故级别
   function SafeLevelEchartsFun(id,name,data){
	        var option = {
	            tooltip: {
	                trigger: 'item',
	                formatter: "{b} :<br/> {c} ({d}%)",
			        confine:true
	            },
	            title: {
			        text: name,
			        x: 'center',
			        y: 'bottom',
			        textStyle: {
			            fontWeight: 'normal',
			            color: '#fff',
			            fontSize: '14'
			        }
			    },
	            series: [{
	                name: '事件类型',
	                type: 'pie',
	                radius : ['50%','70%'],
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
	                data:[]
	            }]
	        };
	        var Barchart = echarts.init(document.getElementById(id));
	        Barchart.setOption(option);

   }
 //折线图  近一年事件发生/重点人员帮扶趋势图
   var FontColor="#fff",backgroundColor="#fff";
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
				   title: {
				        text: '近一年重点人员帮扶/事件发生趋势图',
				        x: 'center',
				        y: 'top',
				        textStyle: {
				            fontWeight: 'normal',
				            color: '#fff',
				            fontSize: '14'
				        }
				    },
				    tooltip: {
				        trigger: 'axis',
				        confine:true
				    },
				    color:color,
			   grid: {
				        left: '1%',
				        right: '2%',
				        bottom: '3%',
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
	 
   //事件分类
   function EventFenLeiEchartsFun(data){

	        
	        var option = {
	            tooltip: {
	                trigger: 'item',
	                formatter: "{b} :<br/> {c} ({d}%)",
			        confine:true
	            },
	            title: {
			        text: '事件分类',
			        x: 'center',
			        y: 'bottom',
			        textStyle: {
			            fontWeight: 'normal',
			            color: '#fff',
			            fontSize: '14'
			        }
			    },
	            series: [{
	                name: '事件分类',
	                type: 'pie',
	                radius:"70%",
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
   //事件分级
   function EventTypeLevelEchartsFun(id,name,data){
	   //
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
			    color: ['#32c1c3', '#b4a4df','#6eaee3'],
			    tooltip: {
	                trigger: 'item',
	                formatter: "{b} :<br/> {c} ({d}%)",
			        confine:true
	            },
	            title: {
	    	        text: name,
	    	        x: 'center',
	    	        y: 'bottom',
	    	        textStyle: {
	    	            fontWeight: 'normal',
	    	            color: '#fff',
	    	            fontSize: '14'
	    	        }
	    	    },
			    series : [
			        {
			            name:'Line 1',
			            type:'pie',
			            clockWise:false,
			            radius : ["60%","70%"],
			            center : ['50%', '45%'],
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
			            radius : ["50%","60%"],
			            center : ['50%', '45%'],
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
			            radius : ["40%","50%"],
			            center : ['50%', '45%'],
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
			            radius : ["30%","40%"],
			            center : ['50%', '45%'],
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
	   var Barchart = echarts.init(document.getElementById(id));
       Barchart.setOption(option);
   }
	
   
   //事件数据汇总
   function EventEcharts1Fun(data){
	   //
	   var quan = JSON.parse(data["quan"]);
	   var w = JSON.parse(data["01"]);
	   var c = JSON.parse(data["02"]);
	   var y = JSON.parse(data["03"]);
	   var NameTotal = [];
	   var Daichuli= [];
	   var Chulizhong=[];
	   var Yichuli=[];
	   for (var one in quan) {
		   NameTotal.push(quan[one]["type"]);
		   Daichuli.push(0);
		   Chulizhong.push(0);
		   Yichuli.push(0);
	   }
	  
	   for(var x in NameTotal){
		   for (var one in w) {
				if(NameTotal[x]==w[one]["type"]){
					Daichuli[x] = Number(w[one]["value"]);
				}
		   }
		   for (var one in c) {
				if(NameTotal[x]==c[one]["type"]){
					Chulizhong[x] = Number(c[one]["value"]);
				}
		   }
		   for (var one in y) {
				if(NameTotal[x]==y[one]["type"]){
					Yichuli[x] = Number(y[one]["value"]);
				}
		   }
	   }

	    var option = {
	/*        legend: {
	            data: ['库存', '报废', '借用','使用率'],
	            textStyle: {
	        color: '#fff',
	        fontSize: _fontSize
	    }
	        },*/
	        tooltip: {
	            show: "true",
	            trigger: 'axis',
	            axisPointer: {
	                type: 'shadow'
	            }
	        },
	        grid: {
	            left: '1%',
	            right: '1%',
	            bottom: '5%',
	            top: '15%',
	            containLabel: true
	        },
	        xAxis: [
	            {
	                type: 'category',
	                data: NameTotal,
	                axisPointer: {
	                    type: 'shadow'
	                },
	                axisLine: {
	                    lineStyle: {
	                        color: '#fff'
	                    }
	                },
	                axisLabel: {
	                    // formatter: '{value} %',
	                    textStyle: {
	                        color: '#fff',
	                        fontSize: _fontSize
	                    }
	                },
	            }
	        ],
	        yAxis: [
	            {
	                type: 'value',
	                min: 0,
	                name: '',
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
	                    show: true
	                },
	                axisLine: {show: false},
	                // max: 250,
	                // interval: 50,
	            }
	        ],
	        series: [
	        	 {
	                name: '已处理',
	                type: 'bar',
	                stack: '数量',
	                data: Yichuli,
	                barWidth: '40%',
	                itemStyle: {
	                    normal: {
	                        color: '#4573a7'
	                    }
	                }
	            },
	            {
	                name: '处理中',
	                type: 'bar',
	                stack: '数量',
	                data: Chulizhong,
	                barWidth: '40%',
	                itemStyle: {
	                    normal: {
	                        color: '#db843d'
	                    }
	                }
	            },
	           {
	                name: '待处理',
	                type: 'bar',
	                stack: '数量',
	                data: Daichuli,
	                barWidth: '40%',
	                itemStyle: {
	                    normal: {
	                        color: '#D87A82'
	                    }
	                }
	            },
	            
	        ]
	    };
	   var Barchart = echarts.init(document.getElementById('EventEcharts1'));
       Barchart.setOption(option);
   }
   function whatEchartsFun(data){
	   //
	   var quan = JSON.parse(data["quan"]);
	   var gao02 = JSON.parse(data["02"]);
	   var da03 = JSON.parse(data["03"]);
	   var yi04 = JSON.parse(data["04"]);
	   var di05 = JSON.parse(data["05"]);
	   	var NameTotal=[]
	    var gao=[];
	    var da= [];
	    var yi= [];
	    var di= [];
	   for (var one in quan) {
		   NameTotal.push(quan[one]["type"]);
		   gao.push(0);
		   da.push(0);
		   yi.push(0);
		   di.push(0);
	   }
	  
	   for(var x in NameTotal){
		   for (var one in gao02) {
				if(NameTotal[x]==gao02[one]["type"]){
					gao[x] = Number(gao02[one]["value"]);
				}
		   }
		   for (var one in da03) {
				if(NameTotal[x]==da03[one]["type"]){
					da[x] = Number(da03[one]["value"]);
				}
		   }
		   for (var one in yi04) {
				if(NameTotal[x]==yi04[one]["type"]){
					yi[x] = Number(yi04[one]["value"]);
				}
		   }
		   for (var one in di05) {
				if(NameTotal[x]==di05[one]["type"]){
					di[x] = Number(di05[one]["value"]);
				}
		   }
	   }
	   
//	   
//	   	var NameTotal=['杭州道街', '泰达大街']
//	    var gao=[90, 118];
//	    var da= [90, 118];
//	    var yi= [90, 118];
//	    var di= [90, 118];


	    var option = {
	/*        legend: {
	            data: ['库存', '报废', '借用','使用率'],
	            textStyle: {
	        color: '#fff',
	        fontSize: _fontSize
	    }
	        },*/
	        tooltip: {
	            show: "true",
	            trigger: 'axis',
	            axisPointer: {
	                type: 'shadow'
	            }
	        },
	        grid: {
	            left: '-3%',
	            right: '1%',
	            bottom: '2%',
	            top: '5%',
	            containLabel: true
	        },
	        xAxis: [
	            {
	                type: 'category',
	                data: NameTotal,
	                axisLine : {
						show : true,
						lineStyle : {
							color : '#fff',
						}
					},
					splitLine : {
						show : true,
						lineStyle : {
							color : '#fff',
						}
					}, 
	            }
	        ],
	        yAxis: [
	            {
	            	splitLine: {
			              show: false
			          },
			          axisTick: {
			              show: false
			          },
			          axisLine: {
			              show: false
			          },
			          axisLabel: {
			        	  show: false,
			        	  textStyle : {
								color : 'transparent',
							}
			          }
	            }
	        ],
	        series: [
	        	 {
	                name: '低风险单位',
	                type: 'bar',
	                stack: '数量',
	                data: di,
	                barWidth: '50%',
	                label: {
		                normal: {
		                    show: false,
		                    position: 'inside'
		                }
		            },
	                itemStyle: {
	                    normal: {
	                        color: '#30C6CD'
	                    }
	                }
	            },{
	                name: '一般风险单位',
	                type: 'bar',
	                stack: '数量',
	                data: yi,
	                barWidth: '50%',
	                label: {
		                normal: {
		                    show: false,
		                    position: 'inside'
		                }
		            },
	                itemStyle: {
	                    normal: {
	                        color: '#4573a7'
	                    }
	                }
	            },{
	                name: '较大风险单位',
	                type: 'bar',
	                stack: '数量',
	                data: da,
	                barWidth: '50%',
	                label: {
		                normal: {
		                    show: false,
		                    position: 'inside'
		                }
		            },
	                itemStyle: {
	                    normal: {
	                        color: '#db843d'
	                    }
	                }
	            },
	     
	            {
	                name: '高风险单位',
	                type: 'bar',
	                stack: '数量',
	                data: gao,
	                label: {
		                normal: {
		                    show: false,
		                    position: 'inside'
		                }
		            },
	                barWidth: '50%',
	                itemStyle: {
	                    normal: {
	                        color: '#D87A82'
	                    }
	                }
	            },
	            
	        ]
	    };
	   var Barchart = echarts.init(document.getElementById('whatEcharts'));
       Barchart.setOption(option);
   }
   
   function whatEchartsFunbak(data){
	   //
	   var quan = JSON.parse(data["quan"]);
	   var gao02 = JSON.parse(data["02"]);
	   var da03 = JSON.parse(data["03"]);
	   var yi04 = JSON.parse(data["04"]);
	   var di05 = JSON.parse(data["05"]);
	   	var NameTotal=[]
	    var gao=[];
	    var da= [];
	    var yi= [];
	    var di= [];
	   for (var one in quan) {
		   NameTotal.push(quan[one]["type"]);
		   gao.push(0);
		   da.push(0);
		   yi.push(0);
		   di.push(0);
	   }
	  
	   for(var x in NameTotal){
		   for (var one in gao02) {
				if(NameTotal[x]==gao02[one]["type"]){
					gao[x] = Number(gao02[one]["value"]);
				}
		   }
		   for (var one in da03) {
				if(NameTotal[x]==da03[one]["type"]){
					da[x] = Number(da03[one]["value"]);
				}
		   }
		   for (var one in yi04) {
				if(NameTotal[x]==yi04[one]["type"]){
					yi[x] = Number(yi04[one]["value"]);
				}
		   }
		   for (var one in di05) {
				if(NameTotal[x]==di05[one]["type"]){
					di[x] = Number(di05[one]["value"]);
				}
		   }
	   }
	   
//	   
//	   	var NameTotal=['杭州道街', '泰达大街']
//	    var gao=[90, 118];
//	    var da= [90, 118];
//	    var yi= [90, 118];
//	    var di= [90, 118];


	    var option = {
	/*        legend: {
	            data: ['库存', '报废', '借用','使用率'],
	            textStyle: {
	        color: '#fff',
	        fontSize: _fontSize
	    }
	        },*/
	        tooltip: {
	            show: "true",
	            trigger: 'axis',
	            axisPointer: {
	                type: 'shadow'
	            }
	        },
	        grid: {
	            left: '3%',
	            right: '3%',
	            bottom: '18%',
	            top: '5%',
	            containLabel: true
	        },
	        xAxis: [
	            {
	                type: 'category',
	                data: NameTotal,
	                axisLine : {
						show : true,
						lineStyle : {
							color : '#fff',
						}
					},
					splitLine : {
						show : true,
						lineStyle : {
							color : '#fff',
						}
					}, 
					  axisLabel: {
		                	interval: 0,
		                	rotate:45,//倾斜度 -90 至 90 默认为0
		               		  // formatter: '{value} %',
		                    textStyle: {
		                        color: '#fff',
		                        fontSize: _fontSize
		                    }
		                },
	            }
	        ],
	        yAxis: [
	            {
	            	splitLine: {
			              show: false
			          },
			          axisTick: {
			              show: false
			          },
			          axisLine: {
			              show: false
			          },
			          axisLabel: {
			        	  show: false,
			        	  textStyle : {
								color : 'transparent',
							}
			          }
	            }
	        ],
	        series: [
	        	 {
	                name: '低风险单位',
	                type: 'bar',
	                stack: '数量',
	                data: di,
	                barWidth: '50%',
	                label: {
		                normal: {
		                    show: true,
		                    position: 'inside'
		                }
		            },
	                itemStyle: {
	                    normal: {
	                        color: '#30C6CD'
	                    }
	                }
	            },{
	                name: '一般风险单位',
	                type: 'bar',
	                stack: '数量',
	                data: yi,
	                barWidth: '50%',
	                label: {
		                normal: {
		                    show: true,
		                    position: 'inside'
		                }
		            },
	                itemStyle: {
	                    normal: {
	                        color: '#4573a7'
	                    }
	                }
	            },{
	                name: '较大风险单位',
	                type: 'bar',
	                stack: '数量',
	                data: da,
	                barWidth: '50%',
	                label: {
		                normal: {
		                    show: true,
		                    position: 'inside'
		                }
		            },
	                itemStyle: {
	                    normal: {
	                        color: '#db843d'
	                    }
	                }
	            },
	     
	            {
	                name: '高风险单位',
	                type: 'bar',
	                stack: '数量',
	                data: gao,
	                label: {
		                normal: {
		                    show: true,
		                    position: 'inside'
		                }
		            },
	                barWidth: '50%',
	                itemStyle: {
	                    normal: {
	                        color: '#D87A82'
	                    }
	                }
	            },
	            
	        ]
	    };
	   var Barchart = echarts.init(document.getElementById('whatEchartsbak'));
       Barchart.setOption(option);
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
				type : 'scroll',
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
				radius : [ '50%', '70%' ],
				center : [ '80%', '50%' ],
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
				data : [ '肇事肇祸等严重精神障碍患者','安置帮教人员','社区矫正人员',  '吸毒人员',  '艾滋病危险人员', '重点上访人员',
						'涉教人员', '危险品从业人员' ]
			},
			series : [ {
				name : '特殊人群占比',
				type : 'pie',
				radius : '70%',
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
	//特殊人群类型及变化趋势
	   $.popEcharts=function(model,data){
		   var type=[];
		   var value1=[];
		   var value2=[];
		   var value3=[];
		   var value4=[];
		   var value5=[];
		   var value6=[];
		   var value7=[];
		   var value8=[];
		   for(var i=0;i<data.length;i++){ 
			   type.push(data[i]['type']);
			   value1.push(data[i]['value1']);
			   value2.push(data[i]['value2']);
			   value3.push(data[i]['value3']);
			   value4.push(data[i]['value4']);
			   value5.push(data[i]['value5']);
			   value6.push(data[i]['value11']);
			   value7.push(data[i]['value12']);
			   value8.push(data[i]['value13']);
			}
		   
		   //console.log(data["type"])
		   option = {
				   title: {
				        text: '特殊人群数量及变化趋势',
				        x: 'center',
				        y: 'top',
				        textStyle: {
				            fontWeight: 'normal',
				            color: '#fff',
				            fontSize: '14'
				        }
				    },
			/*	      legend: {
			                left:'center',
			                top:'top',
			                textStyle: {
			                    color: '#fff',
			                    fontSize: '10'
			                },
			                data: ['安置帮教人员','社区矫正人员','肇事肇祸等严重精神障碍患者','吸毒人员','艾滋病危险人员','重点上访人员','涉教人员','危险品从业人员']
			            }, */
				    tooltip: {
				        trigger: 'axis',
				        confine:true
				    },
				    color:color,
			   grid: {
				        left: '2%',
				        right: '5%',
				        bottom: '3%',
				        top:'5%',
				        containLabel: true
				    },
				    xAxis: {
				        type: 'category',
				        boundaryGap: false,
				        //data: ['周一','周二','周三','周四','周五','周六','周日'],\
				        data: type,
				        axisLine: {
		                    lineStyle: {
		                        color: '#fff'
		                    }
		                },
				    },
				    yAxis: {
				        type: 'value',
				        axisLine: {
		                    lineStyle: {
		                        color: '#fff'
		                    }
		                },
		                splitLine: {
		                    show: false
		                }
				    },
				    series: [
				    	{
				            name:'安置帮教人员',
				            type:'line',
				            data:value4,
				            smooth: true
				        },{
				            name:'社区矫正人员',
				            type:'line',
				            //data:[120, 132, 101, 134, 90, 230, 210]
				            data:value1,
				            smooth: true
				        },
				        {
				            name:'肇事肇祸等严重精神障碍患者',
				            type:'line',
				            data:value2,
				            smooth: true
				        },
				        {
				            name:'吸毒人员',
				            type:'line',
				            data:value3,
				            smooth: true
				        },
				        
				        {
				            name:'艾滋病危险人员',
				            type:'line',
				            data:value5,
				            smooth: true
				        },
				        {
				            name:'重点上访人员',
				            type:'line',
				            data:value6,
				            smooth: true
				        },
				        {
				            name:'涉教人员',
				            type:'line',
				            data:value7,
				            smooth: true
				        },
				        {
				            name:'危险品从业人员',
				            type:'line',
				            data:value8,
				            smooth: true
				        }
				    ]
				};

		    var Barchart = echarts.init(document.getElementById('popEcharts'));
	        Barchart.setOption(option);
		}
	   
	//各街道人口数据对比（重点人群与特殊关系
	function StreetPopEchartsFun(id,data){
		var name = [];
		var z = [];
		var t = [];
        for (var one in data) {
        	name.push(data[one]["type"]);
        	z.push(Number(data[one]["value1"]));
        	t.push(Number(data[one]["value2"]));
        	
        }
        
        
//	   	var NameTotal=['杭州道', '塘沽', '杭州道', '塘沽','杭州道', '塘沽', '杭州道', '塘沽']
//	    var KuCunTotal=[138, 143, 150,147,138, 143, 150,147];
//	    var JieYongTotal=[30, 25, 36, 30,30, 25, 36, 30];
    	var NameTotal=name;
	    var KuCunTotal=z;
	    var JieYongTotal=t;


		    var option = {

		    	    title: {
				        text: '各街道人口数据对比（重点人群与特殊人群关系）',
				        x: 'center',
				        y: 'top',
				        textStyle: {
				            fontWeight: 'normal',
				            color: '#fff',
				            fontSize: '14'
				        }
				    },
		        tooltip: {
		            show: "true",
		            trigger: 'axis',
		            axisPointer: {
		                type: 'shadow'
		            }
		        },
		        grid: {
		            left: '1%',
		            right: '1%',
		            bottom: '3%',
		            top: '20%',
		            containLabel: true
		        },
		        xAxis: [
		            {
		                type: 'category',
		                data: NameTotal,
		                axisPointer: {
		                    type: 'shadow'
		                },
		                axisLine: {
		                    lineStyle: {
		                        color: '#fff'
		                    }
		                },
		                axisLabel: {
		                    // formatter: '{value} %',
		                    textStyle: {
		                        color: '#fff',
		                        fontSize: _fontSize
		                    }
		                },
		            }
		        ],
		        yAxis: [
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
		        series: [
		            {
		                name: '重点人群',
		                type: 'bar',
		                data: KuCunTotal,
		                barWidth: '20%',
		                itemStyle: {
		                    normal: {
		                        color: '#14C1C0'
		                    }
		                }
		            },
		            {
		                name: '特殊人群',
		                type: 'bar',
		                data: JieYongTotal,
		                barWidth: '20%',
		                itemStyle: {
		                    normal: {
		                        color: '#14C1C0'
		                    }
		                }
		            }
		            
		        ]
		    };
		// 初始化参数
		var myChart = echarts.init(document.getElementById(id));
		myChart.setOption(option, true);
	}
	//各街道人口数据对比（重点人群与特殊关系
	function StreetPopEchartsFunbck(id,data){
		var name = [];
		var z = [];
		var t = [];
        for (var one in data) {
        	name.push(data[one]["type"]);
        	z.push(Number(data[one]["value1"]));
        	t.push(Number(data[one]["value2"]));
        	
        }
        
        
//	   	var NameTotal=['杭州道', '塘沽', '杭州道', '塘沽','杭州道', '塘沽', '杭州道', '塘沽']
//	    var KuCunTotal=[138, 143, 150,147,138, 143, 150,147];
//	    var JieYongTotal=[30, 25, 36, 30,30, 25, 36, 30];
    	var NameTotal=name;
	    var KuCunTotal=z;
	    var JieYongTotal=t;


		    var option = {

		    	    title: {
				        text: '各街道人口数据对比（重点人群与特殊人群关系）',
				        x: 'center',
				        y: 'top',
				        textStyle: {
				            fontWeight: 'normal',
				            color: '#fff',
				            fontSize: '14'
				        }
				    },
		        tooltip: {
		            show: "true",
		            trigger: 'axis',
		            axisPointer: {
		                type: 'shadow'
		            }
		        },
		        grid: {
		            left: '1%',
		            right: '1%',
		            bottom: '18%',
		            top: '20%',
		            containLabel: true
		        },
		        xAxis: [
		            {
		                type: 'category',
		                data: NameTotal,
		                axisPointer: {
		                    type: 'shadow'
		                },
		                axisLine: {
		                    lineStyle: {
		                        color: '#fff'
		                    }
		                },
		                axisLabel: {
		                	interval: 0,
		                	rotate:45,//倾斜度 -90 至 90 默认为0
		               		  // formatter: '{value} %',
		                    textStyle: {
		                        color: '#fff',
		                        fontSize: _fontSize
		                    }
		                },
		            }
		        ],
		        yAxis: [
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
		        series: [
		            {
		                name: '重点人群',
		                type: 'bar',
		                data: KuCunTotal,
		                barWidth: '20%',
		                itemStyle: {
		                    normal: {
		                        color: '#14C1C0'
		                    }
		                }
		            },
		            {
		                name: '特殊人群',
		                type: 'bar',
		                data: JieYongTotal,
		                barWidth: '20%',
		                itemStyle: {
		                    normal: {
		                        color: '#14C1C0'
		                    }
		                }
		            }
		            
		        ]
		    };
		// 初始化参数
		var myChart = echarts.init(document.getElementById(id));
		myChart.setOption(option, true);
	}
	//群防群治组织结构
	function OrgEchartsFun(data){
	      var option = {
		            tooltip: {
		                trigger: 'item',
		                formatter: "{b} :<br/> {c} ({d}%)",
				        confine:true
		            },
		            title: {
				        text: '群防群治组织结构',
				        x: 'center',
				        y: 'bottom',
				        textStyle: {
				            fontWeight: 'normal',
				            color: FontColor,
				            fontSize: '14'
				        }
				    },
		            series: [{
		                name: '结构型',
		                type: 'pie',
		                
		                radius : ['50%','80%'],
		                center: ['50%', '40%'],
		                color:color,
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
		// 初始化参数
		var myChart = echarts.init(document.getElementById('OrgEcharts'));
		myChart.setOption(option, true);
	}
	
	//群防群治队伍构成
	function RanksEchartsFun(data){
		var sum = Number(data[0])+Number(data[1]);
		var bi = 0.0;
		var tongbi = "+0.0%";
		var bili = "0%";
		if(sum!=0){
			bi = (Number(data[0])*1.0*100/sum*1.0).toFixed(1);
			bili = +bi+"%";
		}

		var option = {
			    tooltip: {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
		    title: {
			        text: bili,
			        x: 'center',
			        y: '60',
			        textStyle: {
			            fontWeight: 'normal',
			            color: '#fff',
			            fontSize: '14'
			        }
			    },
			    color:['#24aeb3'],
			    series: [{
			        name: '性别比例',
			        type: 'pie',
			        radius: ['50%', '80%'],
			        center: ['50%', '40%'],
			        label: {
			            normal: {
			                position: 'center'
			            }
			        },
			        data: [{
			            value: data[0],
			            name: '女',
			          
			        }, {
			            value: data[1],
			            name: '男',
						//
			            itemStyle: {
			                normal: {
			                    color: '#16618d',
								label:{
									show:false
								}
			                },
			                emphasis: {
			                    color: '#16618d',
								label:{
									show:false
								}
			                }
			            },
			            hoverAnimation: true
			        }]
			    }]
			};
		// 初始化参数
		var myChart = echarts.init(document.getElementById('RanksEcharts'));
		myChart.setOption(option, true);
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
						/*	dataLabels: {
									enabled: true,
									format: '{point.name}'
							},*/
							dataLabels: {
								enabled: true,//是否直接呈现数据 也就是外围显示数据与否
								distance: -10,//通过设置这个属性，将每个小饼图的显示名称和每个饼图重叠
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
			credits:{
			     enabled: false // 禁用版权信息
			},
			
			colors:['#4ebba1','#9764c7','#db993a'],
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
						/*	dataLabels: {
									enabled: true,
									format: '{point.name}'
							},*/
							dataLabels: {
								enabled: true,//是否直接呈现数据 也就是外围显示数据与否
								distance: -10,//通过设置这个属性，将每个小饼图的显示名称和每个饼图重叠
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
						/*	dataLabels: {
									enabled: true,
									format: '{point.name}'
							},*/
							dataLabels: {
								enabled: true,//是否直接呈现数据 也就是外围显示数据与否
								distance: -10,//通过设置这个属性，将每个小饼图的显示名称和每个饼图重叠
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
	
	function CityBuEchartsFun(data){
		 var option = {
		            tooltip: {
		                trigger: 'item',
		                formatter: "{b} :<br/> {c} ({d}%)",
				        confine:true
		            },
		            title: {
				        text: '城市部件状态',
				        x: 'center',
				        y: 'bottom',
				        textStyle: {
				            fontWeight: 'normal',
				            color: '#fff',
				            fontSize: '14'
				        }
				    },
		            series: [{
		                name: '结构型',
		                type: 'pie',
		                
		                radius : '70%',
		                center: ['50%', '45%'],
		                color:color,
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
		// 初始化参数
		var myChart = echarts.init(document.getElementById('CityBuEcharts'));
		myChart.setOption(option, true);
	}
	function RenKouQingKuan(data){
		var option = {
	        color:[ '#4573a7', '#89a54e'],
	        title: {
		        text: '人口变化情况',
		        x: 'center',
		        y: 'top',
		        textStyle: {
		            fontWeight: 'normal',
		            color: '#fff',
		            fontSize: '14'
		        }
		    },
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		 /*   legend: {
		        data:['迁入','迁出'],
		        textStyle: {
	                color: '#fff',
	                fontSize: _fontSize
	            },
	            itemGap: 100
		    },*/
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
		            // data : ['2018-2','2018-3','2018-4','2018-5','2018-6','2018-7','2018-8'],
					data:data["name"],
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
		            // data:[420, 332, 401, 334, 490, 530, 410]
					data:data["value"]
		        },
		        {
		            name:'迁出',
		            type:'bar',
		            stack: '广告',
		            barWidth : '30%',
		            // data:[220, 182, 191, 134, 190, 230, 210]
					data:data["value1"]
		        }
		    ]
		};

		var myChart = echarts.init(document.getElementById("renyuanqingkuang"));
		myChart.setOption(option);
	}