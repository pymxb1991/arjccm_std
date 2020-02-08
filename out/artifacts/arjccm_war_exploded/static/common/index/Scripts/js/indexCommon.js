/**
 * Created by oHa on 2017/12/27.
 */

$(function() {

    // 基础颜色表
    var color = ['#D87A82', '#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];
    // 基础参数
    var windowsHeight, _fontSize = 14,

    _fontSize1 = 26,
    breakData = 8;
    legendTop = '30%',
    radiusData = [90, 65],
    lengthECharts = 30;

    // 
    $('#main').height($(window).height());

    // 
    $('.height100').height($('#main').height() - 75);

    windowsHeight = $(window).width();

    if (windowsHeight > 1600) {
        _fontSize = 14;
        legendTop = '15%';
        radiusData = [90, 65];
        lengthECharts = 20;
        _fontSize1 = 26;
        breakData = 8;
        legendRight="8%"
    } else {
        _fontSize = 10;
        legendTop = '15%';
        radiusData = [60, 45];
        lengthECharts = 5;
        _fontSize1 = 14;
        breakData = 6;
        legendRight="5%"
    }

    /**
	 * 案事件性质统计情况
	 */

    $.AlarmNatureFun = function(model, dataService) {
        var option = {
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)",
		        confine:true
            },
            color: color,
            grid: {
                left: '3%',
                right: '3%',
                bottom: '3%',
                top: '0%',
                containLabel: true
            },
            series: [{
                name: '',
                type: 'pie',
                radius: ['35%', '55%'],
                label: {
                    normal: {
                        formatter: '{b|{b}：}{c}  ',
                        borderRadius: 4,
                        rich: {
                            a: {
                                color: '#999',
                                lineHeight: 22,
                                align: 'center'
                            },
                            b: {
                                fontSize: _fontSize,
                                lineHeight: 18
                            },
                            per: {
                                color: '#eee',
                                backgroundColor: '#334455',
                                padding: [2, 4],
                                borderRadius: 2
                            }
                        }
                    }
                },
                data: dataService
            }]
        };
        // 实例化 对象
        var Barchart = echarts.init(document.getElementById(model));
        // 填充数据
        Barchart.setOption(option);
    }

    /**
	 * 案事件报警一览
	 */
    $.AlarmFun = function(model, originalData) {
        var colorList = ['#D87A82', '#cc7b33', '#89a54e', '#3ed666'];
        // 总和
        var originalDataTotle = 0;
        for (var i in originalData) {
            originalDataTotle += originalData[i]["value"];
        }
      var originalData1=[];
        for (var i in originalData) {
            originalData1.push({
                value:originalData[i]["value"],
                name:(originalDataTotle==0?0:(originalData[i]["value"]/originalDataTotle)*100).toFixed(1) + '%'
            });
        }

        var total = {
            name: '',
            value: originalDataTotle
        }
        // Echart-util
        echarts.util.each(originalData,
        function(item, index) {
            item.itemStyle = {
                normal: {
                    color: colorList[index]
                }
            };
        });

        // 配置项
        var option = {
            title: [{
                text: '',
                left: '49%',
                top: '46%',
                textAlign: 'center',
                textBaseline: 'middle',
                textStyle: {
                    fontWeight: 'bold',
                    fontSize: 26,
                    color: '#fff'
                }
            },
            {
                text: total.value,
                left: '52%',
                top: '43%',
                center:'center',
                textAlign: 'center',
                textBaseline: 'middle',
                textStyle: {
                    fontWeight: 'bold',
                    fontSize: 26,
                    // color: '#eea807'
                    color: '#fff'
                }
            }],
            series: [{
                hoverAnimation: false,
                // 设置饼图默认的展开样式
                radius: ['45%', '60%'],
                center: ['55%', '45%'],
                name: 'pie',
                type: 'pie',
                selectedMode: 'single',
                selectedOffset: 16,
                // 选中是扇区偏移量
                clockwise: true,
                startAngle: 90,
                label: {
                    normal: {
                        textStyle: {
                            fontWeight: 'normal',
                            fontSize: _fontSize,
                            color: '#fff'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        lineStyle: {
                            color: '#fff',
                        },
                        length: lengthECharts
                    }
                },
                data: originalData1
            }]
        };
        // 初始化参数
        var myChart = echarts.init(document.getElementById(model));
        myChart.setOption(option, true);



    }
    //案事件一览1
    $.AlarmFun1=function(model, originalData){
    	  var originalData1=[];
//    	 for (var i in originalData) {
//             originalData1.push({
//                 value:originalData[i]["value"],
//                 name:(originalDataTotle==0?0:(originalData[i]["value"]/originalDataTotle)*100).toFixed(1) + '%'
//             });
//         }
    	var option = {
    		  color:color,
    		    tooltip : {
    		        trigger: 'item',
    		        formatter: "{a} <br/>{b} : {c} ({d}%)",
    		        confine:true
    		    },
    		    legend: {
    		    	 //   top: '20%',
    	                left: 'left',
    	                top:'middle',
    	                orient: 'vertical',
    	                textStyle: {
    	                    color: '#fff',
    	                    fontSize: _fontSize,
    	                },
    		        data:['重特大','重大','较大','一般']
    		    },
//    		    calculable : true,
    		    series : [
    		        {
    		            name:'级别',
    		            type:'pie',
    		            radius : ['45%', '70%'],
    		            center : ['57%', '50%'],
    		            roseType : 'radius',
    		            label: {
    		                normal: {
    		                    show: false
    		                },
    		                emphasis: {
    		                    show: true
    		                }
    		            },
    		            lableLine: {
    		                normal: {
    		                    show: false
    		                },
    		                emphasis: {
    		                    show: true
    		                }
    		            },
    		            data:originalData
    		        }
    		    ]
    		};
        // 初始化参数
        var myChart = echarts.init(document.getElementById(model));
        myChart.setOption(option, true);
    	
    }
    //近七天处理案事件数量
    $.ChuEchartsFun=function(model,data){
    	var len=data.length;
    	var name=[],coord=[];
    	if(len>0){
    		
    		for(var i=0;i<len;i++){
    			name.push(data[i].name)
    			coord.push({coord:[i,data[i].value]})
    		}
    	}
        var myEcharts34 = echarts.init(document.getElementById(model));
        var option = {
//            title: [
//                {
//                    text: '一周内日处理案事件数量',
//                    x: 'center',
//                    y: '79%',
//                    textStyle: {
//                        color: '#fff',
//                        fontSize: 14,
//                        fontFamily: "微软雅黑"
//                    }
//                }
//            ],
        		// grid: {
                //     left: '3%',
//                     right: '3%',
//                     containLabel: true
                // },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: name,
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                },
                axisLabel:{
                    margin:20
                },
            },
            yAxis: {
                show: false,
                type: 'value',
                axisLabel: {
                    formatter: '{value}'
                }
            },
            series: [
                {
                    name: '最高气温',
                    type: 'line',
                    data: data,
                    markPoint: {
                        data: coord,
                        itemStyle: {
                            normal: {
                                color: '#eb9041'
                            },
                            x: "50%",
                            y: '30%'
                        },
                        symbol: 'circle',
                        symbolSize: 30,

                    },
                    itemStyle: {
                        normal: {
                            lineStyle: {
                                color: '#eb9041'
                            }
                        }
                    },
                },
            ]
        };
        myEcharts34.setOption(option);

    	
    }
    // 案事件按区域统计
    $.AffairFun = function(model,ajaxDataX,ajaxDataY) {
        var option = {
            tooltip: {
                show: "true",
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                },
                formatter: "{b} :<br/> {c}%",
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
                min: 0,
                axisLabel: {
                    formatter: '{value} %',
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
                barWidth: 23,
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

    // 社会稳定风险评估情况
    $.GetWorkSheets = function(model,data) {
        var nameArr = [],
        DataArr = [];
        
        var option = {
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)",
		        confine:true
            },
            legend: {
                // type: 'scroll',
                orient: 'vertical',
//                top: legendTop,
//                right: legendRight,
                left:'right',
                top:'middle',
                textStyle: {
                    color: '#fff',
                    fontSize: _fontSize,
                },
                data: data
                // data: nameArr
            },
            series: [{
                name: '案事件类型',
                type: 'pie',
                radius: '75%',
                center: ['35%', '50%'],
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
        Barchart.on('click',
        function(params) {
        });

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

    // 更新 案事件分级中文内容
    $.AlarmFunTable = function(model, originalData) {
        // echartsAlarm-value
        // 拼接 table内容
        var tableContent = "";
        // 获取 按月类型值
        for (var i in originalData) {
            // 获取每一个值
            tableContent += "<li> <span class=\"alarm-name" + (Number(i) + 1) + " alarm-name\">" + originalData[i]["name"] + "</span><span class=\"alarm-value\" " + "id=\"level1\">" + originalData[i]["value"] + "</span></li>"
        }
        // 添加内容 到页面
        $("." + model).html(tableContent);
    }
    
    
    $.updateSearchTable = function(model, originalData) {
        // echartsAlarm-value
        // 拼接 table内容
        var tableContent = "";
        // 获取 按月类型值
        for (var i in originalData) {
            // 获取每一个值
            tableContent += "<tr><td>"+i+"</td><td>"+originalData[i]["value1"]+"</td><td>"+originalData[i]["value2"]+"</td> <td>"+originalData[i]["value3"]+"</td> <td>"+originalData[i]["value4"]+"</td></tr>"
        }
        // 添加内容 到页面
        $ (model).html(tableContent);
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
			var datedate=originalData[i]["value1"]
			datedate=datedate.substring(0,16)
			
            var clock="";
        	var clock1="";
        	var year,month,day,hh,mm,ss;
			var  DataValue=originalData[i]["value1"].replace( new RegExp(/-/gm),"/");
        	var eventTime = new Date(DataValue);
	
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
            		"<b>【"+datedate+"】</b>【"+originalData[i]["typeO"]+"】"+originalData[i]["value"]+
            		"</a></li>"
        }
       
        // 添加内容 到页面
        $ ("#"+model).html(tableContent);
        $("div.list_lh").myScroll({
            speed: 40, //数值越大，速度越慢
            rowHeight: 28.38 //li的高度
        });
    }
   
    $('#alarmTable').height($('.height33').height()-58)
    
    
    
  //事件流程
    $.AlarmFlowFun=function(){
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
    	var KaoHe=[700.4361, 115.093863];
    	var JieShu=[840.4361, 115.093863];
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
    		  KaoHe=[570.4361, 100.093863];
    		  JieShu=[690.4361, 100.093863];
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
    		  KaoHe=[500.4361, 75.093863];
    		  JieShu=[590.4361, 75.093863];
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
//    	            tooltip: {
//    	                trigger: 'item',
//    	                show: true,
//    	                alwaysShowContent: true,
//    	                position: [185, 75],
//    	                formatter: '{b}<br>MQ接收数据13278条<br>MQ取出数据13278条'
//    	            },
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
});






