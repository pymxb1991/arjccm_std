/**
 * Created by oHa on 2017/12/27.
 */

$(function() {

    // 基础颜色表
    var color = ['#aa4644', '#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];
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
	var FontColor="#999",backgroundColor="#fff";
	var theme=$.cookie('theme');
	if(theme=="black"){
		FontColor="#fff";
		backgroundColor="#0e2a4c";
	}
    windowsHeight = $(window).width();

    if (windowsHeight > 1366) {
        _fontSize = 14;
        legendTop = '15%';
        radiusData = [90, 65];
        lengthECharts = 20;
        _fontSize1 = 26;
        breakData = 8;
        legendRight="4%"
    } else {
        _fontSize = 12;
        legendTop = '15%';
        radiusData = [60, 45];
        lengthECharts = 5;
        _fontSize1 = 14;
        breakData = 6;
        legendRight="2%"
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
                top: '3%',
                containLabel: true
            },
            series: [{
                name: '',
                labelLine:{    
                    normal:{    
                       length:5
                    }    
                }, 
                type: 'pie',
                radius: ['25%', '35%'],
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
        var colorList = ['#aa4644', '#cc7b33', '#89a54e', '#3ed666'];
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
                left: '29%',
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
                left: '52.5%',
                top: '43%',
                center:'center',
                textAlign: 'center',
                textBaseline: 'middle',
                textStyle: {
                    fontWeight: 'bold',
                    fontSize: 18,
                    // color: '#eea807'
                    color: FontColor
                }
            }],
            series: [{
                hoverAnimation: false,
                // 设置饼图默认的展开样式
                radius: ['25%', '40%'],
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
                            color: FontColor
                        }
                    }
                },
                labelLine: {
                    normal: {
                        lineStyle: {
                            color: FontColor,
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
    $.AlarmFun1=function(model, originalData, name){
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
    	                    color: FontColor,
    	                    fontSize: _fontSize,
    	                },
    		        data:name
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
                        color: FontColor
                    }
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
                        color: FontColor
                    }
                },
                axisLabel: {
                    textStyle: {
                        color: FontColor,
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
                        color: FontColor,
                        fontSize: _fontSize
                    }
                },
                axisLine: {
                    lineStyle: {
                        color: FontColor
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
                    color: FontColor,
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
                        color: FontColor
                    }
                },
                axisLabel: {
                    textStyle: {
                        color: FontColor,
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
                        color: FontColor,
                        fontSize: _fontSize
                    }
                },
                axisLine: {
                    lineStyle: {
                        color: FontColor
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
                        color: FontColor
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
                        color: FontColor,
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
                    color: FontColor,
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
                top: legendTop,
                right: legendRight,
                textStyle: {
                    color: FontColor,
                    fontSize: _fontSize,
                },
                data: data
                // data: nameArr
            },
            series: [{
                name: '案事件类型',
                type: 'pie',
                radius: '60%',
                center: ['30%', '35%'],
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
            if(originalData[i]["type"]=='重特大'){
                alarm='alarm1';
            }else if(originalData[i]["type"]=='重大'){
                alarm='alarm2';
            } else if(originalData[i]["type"]=='较大'){
                alarm='alarm3';
            }else if(originalData[i]["type"]=='一般'){
                alarm='alarm4';
            }
            tableContent += "<li class='"+alarm+"'><b>【"+originalData[i]["type"]+"】</b>【"+originalData[i]["typeO"]+"】"+originalData[i]["value"]+"</li>"
        }
        // 添加内容 到页面
        $ ("#"+model).html(tableContent);
        $("div.list_lh").myScroll({
            speed: 40, //数值越大，速度越慢
            rowHeight: 28.38 //li的高度
        });
    }


    $('#alarmTable').height($('.alarm-height').height()-$('.alarm-row-fluid1').height()-$('.alarm-row-fluid2').height()-20)
});