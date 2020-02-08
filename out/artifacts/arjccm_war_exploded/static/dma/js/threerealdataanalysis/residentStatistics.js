$(function() {
	var FontColor = "#999";
	var backgroundColor = "#fff";
	var color = ['#aa4644', '#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];
	var theme = getCookie('theme');
	if(theme == "black"){
		FontColor = "#fff";
		backgroundColor = "#0e2a4c";
	}
	if(theme=='gradient'){
		color = [ '#1F8BFA', '#E84442', '#FAB736', '#2CC189', '#F9A388', '#77E7F1', '#9E56E9', '#FF7453', '#16DDD3', '#FDB733'];
    }
	var context = $(".context").attr("content");
	// 初始化 Echarts组件
	var areaChart = echarts.init(document.getElementById('echArea'));
	var sexChart = echarts.init(document.getElementById('echSex'));
	var areaLineData = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
	var sexLineData = [1, 2, 3, 4];
	var areaTitle = "各区域人口类型";
	var sexTitle = "各区域人口性别";
	getAreaStatistics();
	getSexStatistics();
	function getAreaStatistics() {
		$.ajax({
			type: "POST",
			url: context + "/dma/threeRealDataAnalysis/residentStatisticsData",
			dataType: "json",
			cache: false,
			async: true,
			success: function (data) {
				var dataType = new Array();
				var dataSeries = new Array();
				var dataOption = new Array();
			    var dataA = data['dataType'];
			    for (var one in dataA) {
			    	dataType.push(dataA[one]['label']);
			    	dataSeries.push({
			    		name: dataA[one]['label'],
		                type: 'bar',
		                stack: 'one'
			    	});
			    }
			    for (var one in dataA) {
			    	dataSeries.push({
			    		name: dataA[one]['label'],
		                type: 'bar',
		                xAxisIndex: 2,
		                yAxisIndex: 2,
		                stack: 'right'
			    	});
			    }
			    var dataB = data['data'];
			    for (var i = 0; i < areaLineData.length; i++) {	
			    	var optionSeries = new Array();
			    	for (var one in dataA) {
			    		var value = dataA[one]['value'];
			    		optionSeries.push({
			    			data: dataB[areaLineData[i]][value]
			    		});
			    	}
			    	for (var one in dataA) {
			    		var value = dataA[one]['value'];
			    		optionSeries.push({
				    		data: dataB[areaLineData[i]][value]
				    	});
				    }
			    	dataOption.push({
						title: {
							text: areaTitle + areaLineData[i] + '月份数量变化统计',
						},
						series: optionSeries
			    	});
			    }
				var dataArea = data['dataArea'];
				showAreaStatistics(dataType,dataSeries,dataOption,dataArea);
			}
		});
	}
	function getSexStatistics() {
		$.ajax({
			type: "POST",
			url: context + "/dma/threeRealDataAnalysis/sexStatisticsData",
			dataType: "json",
			cache: false,
			async: true,
			success: function (data) {
				var dataType = new Array();
				var dataSeries = new Array();
				var dataOption = new Array();
			    var dataA = data['dataType'];
			    for (var one in dataA) {
			    	dataType.push(dataA[one]['label']);
			    	dataSeries.push({
			    		name: dataA[one]['label'],
		                type: 'bar'
			    	});
			    }
			    dataSeries.push({
                	name: '性别占比',
                    type: 'pie',
                    center: ['75%', '35%'],
                    radius: '28%',
                    tooltip : {
        		        trigger: 'item',
        		        formatter: "{a} <br/>{b} : {c} ({d}%)"
        		    },
                    z: 100
		    	});
			    var dataB = data['data'];
			    for (var i = 0; i < sexLineData.length; i++) {	
			    	var optionSeries = new Array();
			    	var pieData =  new Array();
			    	for (var one in dataA) {
			    		var value = dataA[one]['value'];
			    		var oneNum = dataB[sexLineData[i]][value];
			    		optionSeries.push({
			    			data: oneNum
			    		});
			    		var numCount = 0;
			    		for (var num in oneNum) {
			    			numCount += oneNum[num];
			    		}
			    		pieData.push({
			    			name:dataA[one]['label'],
			    			value:numCount
			    		});
			    	}
			    	optionSeries.push({
			    		data: pieData
			    	});
			    	dataOption.push({
						title: {
							text: sexTitle + '第' + sexLineData[i] + '季度数量变化统计',
						},
						series: optionSeries
			    	});
			    }
				var dataSex = data['dataArea'];
				showSexStatistics(dataType,dataSeries,dataOption,dataSex);
			}
		});
	}
	function showAreaStatistics(dataType,dataSeries,dataOption,dataArea){
		var option = {
		    baseOption: {
		        backgroundColor: backgroundColor,
		        timeline: {
		            show: true,
		            axisType: 'category',
		            tooltip: {
		                show: true,
		                formatter: function(params) {
		                    return params.name + '月份数据统计';
		                }
		            },
		            autoPlay: true,
		            currentIndex: 6,
		            playInterval: 1000,
		            label: {
		                normal: {
		                    show: true,
		                    interval: 'auto',
		                    formatter: '{value}月',
		                },
		            },
		            data: areaLineData,
		        },
		        title: {
		            textStyle: {
		                color: FontColor,
		                fontSize: 16,
		            }
		        },
		        legend: {
		            data: dataType,
		            top: 8,
		            right: '20%',
		            textStyle: {
		                color: FontColor,
		            },
		        },
		        tooltip: {
		            show: true,
		            trigger: 'axis',
		            axisPointer: {
		                type: 'shadow',
		            }
		        },
		        toolbox:{
		            right:20,
		            feature:{
		                saveAsImage: {},
		                restore: {},
		                dataView: {},
		                dataZoom: {},
		                magicType: {type:['line','bar']}
		            }
		        },
		        grid: [{
		            show: false,
		            left: "3%",
		            top: 60,
		            bottom: 60,
		            containLabel: true,
		            width:  '45%' ,
		        }, {
		            show: false,
		            left: '50.5%',
		            top: 80,
		            bottom: 60,
		            width:  '9%' ,
		        }, {
		            show: false,
		            right: "3%",
		            top: 60,
		            bottom: 60,
		            containLabel: true,
		            width:  '45%' ,
		        }],
		        xAxis: [{
	                type: 'value',
					triggerEvent: true,
	                inverse: true,
	                axisLine: {
	                    show: false
	                },
	                axisTick: {
	                    show: false
	                },
	                minInterval: 1,
			    	min: function(value) {
			    		if(value.max == 0){
			    			return 5;
			    		}
			    	    return value.min;
			    	},
	                position: 'top',
	                axisLabel: {
	                    show: true,
	                    textStyle: {
	                        color: '#B2B2B2',
	                        fontSize: 12
	                    }
	                },
	                splitLine: {
	                    show: true,
	                    lineStyle: {
	                        color: '#1F2022',
	                        width: 1,
	                        type: 'solid'
	                    }
	                }
		        }, 
		        {
		            gridIndex: 1,
		            show: false,
		        }, 
		        {
		            gridIndex: 2,
		            type: 'value',
		            axisLine: {
		                show: false,
		            },
		            axisTick: {
		                show: false,
		            },
		            minInterval: 1,
			    	min: function(value) {
			    		if(value.max == 0){
			    			return 5;
			    		}
			    	    return value.min;
			    	},
		            position: 'top',
		            axisLabel: {
		                show: true,
		                textStyle: {
		                    color: '#B2B2B2',
		                    fontSize: 12,
		                },
		            },
		            splitLine: {
		                show: true,
		                lineStyle: {
		                    color: '#1F2022',
		                    width: 1,
		                    type: 'solid',
		                }
		            }
		        }],
		        yAxis: [{
		            type: 'category',
		            inverse: true,
		            position: 'right',
		            axisLine: {
		                show: false
		            },
		            axisTick: {
		                show: false
		            },
		            axisLabel: {
		                show: false,
		                margin: 8,
		                textStyle: {
		                    color: '#fff',
		                    fontSize: 12,
		                },
	
		            },
		            data: dataArea
		        }, {
		            gridIndex: 1,
		            type: 'category',
		            inverse: true,
		            position: 'left',
		            axisLine: {
		                show: false
		            },
		            axisTick: {
		                show: false
		            },
		            axisLabel: {
		                show: true,
		                textStyle: {
		                    color: '#9D9EA0',
		                    fontSize: 12,
		                },
		            },
		            data: dataArea.map(function(value) {
		                return {
		                    value: value,
		                    textStyle: {
		                        align: 'center',
		                    }
		                }
		            })
		        }, {
		            gridIndex: 2,
		            type: 'category',
		            inverse: true,
		            position: 'left',
		            axisLine: {
		                show: false
		            },
		            axisTick: {
		                show: false
		            },
		            axisLabel: {
		                show: false,
		                textStyle: {
		                    color: '#9D9EA0',
		                    fontSize: 12,
		                },
	
		            },
		            data: dataArea
		        }],
		        series: dataSeries,
		    },
		    options: dataOption
		};
		areaChart.setOption(option);
	}
	function showSexStatistics(dataType,dataSeries,dataOption,dataSex){
		var option = {
		    baseOption: {
		        backgroundColor: backgroundColor,
		        timeline: {
		            show: true,
		            axisType: 'category',
		            tooltip: {
		                show: true,
		                formatter: function(params) {
		                    return '第' + params.name + '季度数据统计';
		                }
		            },
		            autoPlay: true,
		            currentIndex: 1,
		            playInterval: 1000,
		            label: {
		                normal: {
		                    show: true,
		                    interval: 'auto',
		                    formatter: '第{value}季度',
		                },
		            },
		            data: sexLineData,
		        },
		        title: {
		            textStyle: {
		                color: FontColor,
		                fontSize: 16,
		            }
		        },
		        tooltip: {
		        },
		        legend: {
		            data: dataType,
		            top: 8,
		            x: 'right',
		            textStyle: {
		                color: FontColor,
		            },
		        },
		        grid:{
		            top: 60,
		            bottom: 60,
		            tooltip: {
		                trigger: 'axis',
		                axisPointer: {
		                    type: 'shadow',
		                    label: {
		                        show: true,
		                        formatter: function (params) {
		                            return params.value.replace('\n', '');
		                        }
		                    }
		                }
		            }
		        },
		        xAxis: [{
	                type:'category',
	                axisLabel:{
	                	interval:0
	                },
	                data : dataSex,
	                splitLine: {
	                	show: false
	                }
	            }],
		        yAxis: [{
	                type: 'value',
	                minInterval: 1,
			    	min: function(value) {
			    		if(value.max == 0){
			    			return 5;
			    		}
			    	    return value.min;
			    	},
	                name: '人口'
	            }],
		        series: dataSeries,
		    },
		    options: dataOption
		};
		sexChart.setOption(option);
	}
	function getCookie(cname) {
		var name = cname + "=";
		var ca = document.cookie.split(';');
		for(var i=0; i<ca.length; i++) 
		{
			var c = ca[i].trim();
			if (c.indexOf(name)==0) {
				return c.substring(name.length,c.length);
			}
		}
		return "";
	}
});