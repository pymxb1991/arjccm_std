/**
 * Created by oHa on 2017/12/28.
 */
var windowsHeight, _fontSize = 14, _fontSize1 = 26, _fontSize2 = 24,_fontSize3 = 20,breakData = 8,
legendTop = '30%', radiusData = ['40%', '65%'], lengthECharts = 30,mapHeigth = '90%',zoom=14.75,centerCoordinate= [ 117.663920, 39.035650 ],echarts3X="48%",echarts3y="40%", ItemGap=20;
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

  	//重点关怀人员
	$.getJSON(context + "/report/ccmPeopleAmount/getEmphasisCare", function(
			data) {
		// 接收参数
		PopEmphasisCare(data);
	});
	
	
	// 今日案事件 统计情况
	getEventTodayTotalFun()
	window.onload=function(){
		setInterval("getEventTodayTotalFun()", 300000); 
	}


	// 首页概况各种总数查询
	$.getJSON(context + "/org/ccmOrgCommonality/getOthersAll",
		function(data) {
			//地图
			map()
			//接收参数
			OthersAll(data);
		}
	);
	// 事项分类分析
	$.getJSON(context + "/service/ccmServiceOnline/getServiceType",
		function(data) {
			//事项分类分析
			EventTypeEchartsFun($.ToConvertA(data))
		}
	);

	//在线办事-处理进度
	$.getJSON(context + "/service/ccmServiceOnline/getServiceStatus",
		function(data) {
			//在线办事-处理总数
			ServiceStatusAllFun(data)
			//在线办事-处理进度
	        ChuliFun(data)
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
		echarts3X="48%";
		echarts3y="40%";
		zoom=14.7;
		centerCoordinate= [ 117.662920, 39.035650 ]
		lengthECharts = 30;
		_fontSize1 = 26;
		breakData = 8;
		mapHeigth = '90%'
	} else if(windowsHeight>=1600&&windowsHeight<1680){
		_fontSize = 12;
		radiusData = ['40%', '60%'];
		echarts3X="48%";
		echarts3y="35%";
		zoom=14.4;
		ItemGap=0;
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
		ItemGap=0;
		_fontSize2 = 18,
		_fontSize3 = 16,
		breakData = 8;
		mapHeigth = '90%'
	}else if(windowsHeight<1440) {
		_fontSize = 12;
		radiusData = ['40%', '58%'];
		zoom=14.1;
		centerCoordinate= [ 117.662920, 39.035650 ]
		lengthECharts = 30;
		_fontSize1 = 26;
		ItemGap=-10;
		_fontSize2 = 16,
		_fontSize3 = 14,
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

	//重点关怀人员
	function PopEmphasisCare(emphasisCare) {
		//console.log(numPopData);
		$("#liushou").html(emphasisCare[0]);//留守
		$("#qingshaonian").html(emphasisCare[1]);//重点青少年
		$("#laonian").html(emphasisCare[2]);//老年
		$("#shidu").html(emphasisCare[3]);//失独
		$("#chanji").html(emphasisCare[4]);//残疾
		$("#youfu").html(emphasisCare[5]);//优抚
		$("#lieshi").html(emphasisCare[6]);//烈士
		$("#dibao").html(emphasisCare[7]);//低保
		$("#quanbu").html(emphasisCare[8]);//总数
		
		
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
		$("#eachAll7").html(othersAll[5]+'(&nbsp;'+othersAll[6]+':'+othersAll[7]+':'+othersAll[8]+'&nbsp;)');//学校
		$("#eachAll8").html(othersAll[9]);//学生
		$("#eachAll9").html(othersAll[10]);//教职工
		$("#eachAll10").html(othersAll[11]);//教育经费
		$("#eachAll11").html(parseInt(othersAll[11]/othersAll[0]));//教育经费
		$("#eachAll12").html(othersAll[12]);//医院
		$("#eachAll13").html(othersAll[13]);//医生
		$("#eachAll14").html((othersAll[13]/othersAll[0]).toFixed(5));//人均医生
		$("#eachAll15").html(othersAll[14]);//床位
		$("#eachAll16").html((othersAll[14]/othersAll[0]).toFixed(5));//人均床位
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

	//事项分类
	function EventTypeEchartsFun(data){
		    var option = {
		    	    title : {
		    	        text: '事项分类分析',
		    	        x:'bottom',
		    	        textStyle : {
							color : '#fff',
							fontSize : _fontSize
						}
		    	    },
		    	    color:['#5cb0ea','#ffb289','#d87a82','#8d99b3','#e5ce0c','#97b553'],
		    	    tooltip : {
		    	        trigger: 'item',
		    	        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    	    },
		    	    calculable : true,
		    	    series : [
		    	        {
		    	            name:'事项分类分析',
		    	            type:'pie',
		    	            radius : [40,70],
		    	            center : ['50%', '48%'],
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
		   var Barchart = echarts.init(document.getElementById('EventTypeEcharts'));
		   // 传参
		   Barchart.setOption(option);
	}
	//在线办事-处理总数
	function ServiceStatusAllFun(data) {
		var sum = 0;
        for (var one in data) {
        	sum += data[one]["value"]*1;
        }
		$('#sumAll').html(sum);//处理总数
	}
	
	//在线办事-处理进度
	function ChuliFun(data) {
		var sum = 0;	//全部
		var unSum = 0;	//待审核
		var inSum = 0;	//已处理
        for (var one in data) {
        	if(data[one]["type"]=="待审核"){
        		unSum = Number(data[one]["value"]);
        	}
        	sum += Number(data[one]["value"]);
        }
        inSum = sum - unSum;
        if(sum==0){
        	sum = 1;
        }
        var dispose = inSum/sum;//处理率
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
	   var  options = [{
	        title: {
	            text: unSum,
	            /*x: '48%',
	            y: '40%',*/
	            x: echarts3X,
	            y: echarts3y,
	            textAlign: "center",
	            itemGap: ItemGap,
	            textStyle: {
	                fontWeight: 'bold',
	                fontSize: _fontSize2,
	                color: '#eea807'
	            },
	            subtextStyle: {
	               // fontWeight: 'bold',
	                fontSize: _fontSize3,
	                color: '#27a7f8'
	            },
	            subtext: '75%',
	        },
	        series: [{
	            name: ' ',
	            type: 'pie',
	            radius: radiusData,
	            center : ['50%', '50%'],
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
	            radius: radiusData,
	            center : ['50%', '50%'],
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
	    }
	    ];
	
	    //function setPercent(p) {
	
	    //    myChart.setOption(options, true);
	    //}
	
	    var charts = [];
	    for (var i = 0; i < options.length; ++i) {
	        var chart = echarts.init(containers[i]);
	
	        if (i > 0) {
	            options[i].series[0].silent = true;
	        }
	        var value = parseInt(dispose * 100),
	            value_ = 75 * value / 100;
	        options[i].title.subtext ='\n'+ value + "%";
	        options[i].series[0].data[0].value = value_;
	        options[i].series[0].data[1].value = 100 - value_;
	        chart.setOption(options[i]);
	        charts.push(chart);
	    }
	}