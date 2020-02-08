/**
 * Created by oHa on 2017/12/28.
 */
var windowsHeight, _fontSize = 14, _fontSize1 = 26, breakData = 8,
legendTop = '30%', radiusData = ['40%', '65%'], lengthECharts = 30,mapHeigth = '90%',zoom=14.75,centerCoordinate= [ 117.663920, 39.035650 ];
// 基础颜色表
var color = [ '#D87A82', '#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d',
		'#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f' ];
$(function() {
  //年月日
  $('#calendarYear').html(year)
  $('#calendarMonth').html(month)
  $('#calendarDay').html(day)
	//
	var context = $(".context").attr("content");

  
  
	
	// 经济数据
	$.getJSON(context + "/know/ccmEconomicsMonth/getData",
		function(data) {
			//接收参数
			$("#num1").html(data[0]);//GDP
			$("#num2").html(data[1]);//人均可支配收入	
		}
	);
	// 税收统计
	$.getJSON(context + "/know/ccmEconomicsMonth/getShuiShouData",
			function(data) {
				//接收参数
			  	ShuiShouFun(data)	
			}
		);
	// 经济运行数据
	$.getJSON(context + "/know/ccmEconomicsYear/getData",
		function(data) {
			//接收参数
		  	JingJiYunXIngFun(data)	
		}
	);
	// 首页概况各种总数查询
	$.getJSON(context + "/org/ccmOrgCommonality/getOthersAll",
		function(data) {
			//地图
			map()
			//接收参数
			OthersAll(data);
		}
	);
	
	
	$(window).resize(function() {
		window.location.reload()
	})
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



	
	

	//重点青少年统计


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
			centerCoordinate :centerCoordinateSituation,
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

	
	//首页概况各种总数查询
	function OthersAll(othersAll) {
		//console.log(numPopData);
		$("#eachAll1").html(othersAll[0]);//实有人口
		$("#eachAll2").html(othersAll[1]);//社区民警/辅警
		$("#eachAll3").html(othersAll[2]);//养老机构
		$("#eachAll4").html(othersAll[3]);//公安警务站
		$("#eachAll5").html(othersAll[4]);//医院卫生机构
		$("#eachAll6").html(othersAll[5]);//学校教育机构
		
		
	}
	// EchartType 转换成 Echart所需要的类型
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

	
	
})
	
	

		//税收统计
		function ShuiShouFun(data){
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
		
		   var 	option = {
		
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
	   
	   
	   //经济运行数据
	   function JingJiYunXIngFun(data){
		   	var name = [];
			var datas = [];
			for (var one in data) {
				var Time = new Date(data[one]["years"].time);
				var years = Time.getFullYear();
				name.push(years+"年");
				datas.push([Number(data[one]["goal"]),Number(data[one]["industrial"]),Number(data[one]["invest"]),
					Number(data[one]["imports"]),Number(data[one]["retail"]),Number(data[one]["production"])]);
	        }

		   var 	option = {
				   tooltip: {
		                trigger: 'item',
		                formatter: "{a} <br/>{b}: {c}",
				        confine:true
		            },
		       legend: {
		           data:[name[1],name[0]],
		           textStyle: {
	                   color: '#fff',
	                   fontSize: _fontSize
	               }
		       },
		       grid: {
		           left: '3%',
		           right: '4%',
		           bottom: '3%',
		           top:'5%',
		           containLabel: true
		       },
		       color:['#58c7fc','#008ff8'],
		       xAxis : [
		           {
		               type : 'category',
		               data : ['任务目标','工业总产值','固定资产投资','招商引资','商业零售','生产总值'],
		               axisLine: {
		                   lineStyle: {
		                       color: '#fff'
		                   }
		               },
		               axisLabel: {
		                   textStyle: {
		                       color: '#fff',
		                       fontSize: _fontSize
		                   },
		                   interval:0,//横轴信息全部显示  
	                         //rotate:-30,//-30度角倾斜显示  
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
		               name:name[1],
		               type:'bar',
		               data:datas[1],
		               barWidth: '20%',
		           },
		           {
		               name:name[0],
		               type:'bar',
		               data:datas[0],
		               barWidth: '20%',
		           }
		       ]
		   };

		   // 实例化对象
	       var Barchart = echarts.init(document.getElementById('JingJiYunXIng'));
	       // 传参
	       Barchart.setOption(option);
	   }