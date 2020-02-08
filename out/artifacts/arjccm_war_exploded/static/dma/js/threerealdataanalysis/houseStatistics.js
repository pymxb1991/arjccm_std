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
	var housePropertyChart = echarts.init(document.getElementById('echHouseProperty'));
	var housePrupChart = echarts.init(document.getElementById('echHousePrup'));
	var houseYearChart = echarts.init(document.getElementById('echHouseYear'));
	var houseAreaChart = echarts.init(document.getElementById('echHouseArea'));
	var houseStructureChart = echarts.init(document.getElementById('echHouseStructure'));
	getHousePropertyStatistics();
	getHousePrupStatistics();
	getHouseYearStatistics();
	getHouseAreaStatistics();
	getHouseStructureStatistics();
	function getHousePropertyStatistics() {
		$.ajax({
			type: "POST",
			url: context + "/dma/threeRealDataAnalysis/housePropertyStatisticsData",
			dataType: "json",
			cache: false,
			async: true,
			success: function (data) {
				var ajaxData = new Array();
		        for (var one in data) {
		            ajaxData.push({
		                "name": data[one]["type"],
		                "value": Number(data[one]["value"])
		            });
		        }
				showHousePropertyStatistics(ajaxData);
			}
		});
	}
	function getHousePrupStatistics() {
		$.ajax({
			type: "POST",
			url: context + "/dma/threeRealDataAnalysis/housePrupStatisticsData",
			dataType: "json",
			cache: false,
			async: true,
			success: function (data) {
				var ajaxData = new Array();
		        for (var one in data) {
		            ajaxData.push({
		                "name": data[one]["type"],
		                "value": Number(data[one]["value"])
		            });
		        }
				showHousePrupStatistics(ajaxData);
			}
		});
	}
	function getHouseYearStatistics() {
		$.ajax({
			type: "POST",
			url: context + "/dma/threeRealDataAnalysis/houseYearStatisticsData",
			dataType: "json",
			cache: false,
			async: true,
			success: function (data) {
				var xData = new Array();
				var yData = new Array();
		        for (var one in data) {
		        	xData.push(Number(data[one]["value"]));
		        	yData.push(data[one]["type"]);
		        }
				showHouseYearStatistics(xData,yData);
			}
		});
	}
	function getHouseAreaStatistics() {
		$.ajax({
			type: "POST",
			url: context + "/dma/threeRealDataAnalysis/houseAreaStatisticsData",
			dataType: "json",
			cache: false,
			async: true,
			success: function (data) {
				var ajaxData = new Array();
		        for (var one in data) {
		            ajaxData.push({
		                "name": data[one]["type"],
		                "value": Number(data[one]["value"])
		            });
		        }
		        showHouseAreaStatistics(ajaxData);
			}
		});
	}
	function getHouseStructureStatistics() {
		$.ajax({
			type: "POST",
			url: context + "/dma/threeRealDataAnalysis/houseStructureStatisticsData",
			dataType: "json",
			cache: false,
			async: true,
			success: function (data) {
				var xData = new Array();
				var yData = new Array();
		        for (var one in data) {
		        	xData.push(Number(data[one]["value"]));
		        	yData.push(data[one]["type"]);
		        }
				showHouseStructureStatistics(xData,yData);
			}
		});
	}
	function showHousePropertyStatistics(data){
		var option = {
		    title : {
		        text: '房屋产权类别分布统计',
		        x:'left'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'right',
		        data: data
		    },
		    series : [
		        {
		            name: '产权类别',
		            type: 'pie',
		            radius : '55%',
		            center: ['40%', '50%'],
		            data: data,
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
		housePropertyChart.setOption(option);
	}
	function showHousePrupStatistics(data){
		var option = {
		    title : {
		        text: '房屋建筑用途分布统计',
		        x:'left'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'right',
		        data: data
		    },
		    series : [
		        {
		            name: '建筑用途',
		            type: 'pie',
		            radius: ['35%', '60%'],
		            data: data,
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
		housePrupChart.setOption(option);
	}
	function showHouseYearStatistics(xData,yData){
		var option = {
		    title : {
		        text: '房屋建成年限统计',
		        x:'left'
		    },
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    xAxis:  {
		        type: 'value',
		        minInterval: 1,
		    	min: function(value) {
		    		if(value.max == 0){
		    			return 5;
		    		}
		    	    return value.min;
		    	}
		    },
		    yAxis: {
		        type: 'category',
		        data: yData
		    },
		    series : [
		        {
		            name: '房屋数量',
		            type: 'bar',
		            stack: '建筑用途',
		            itemStyle:{
	                    normal: {
	                        label: {
	                            show: true,
	                            position: 'right',
	                            formatter: '{c}'
	                        }
	                    }
	                },
		            data: xData
		        }
		    ]
		};
		houseYearChart.setOption(option);
	}
	function showHouseAreaStatistics(data){
		var option = {
		    title : {
		        text: '房屋区域分布统计',
		        x:'left'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        type: 'scroll',
		        orient: 'vertical',
		        right: 10,
		        top: 20,
		        bottom: 20,
		        data: data
		    },
		    series : [
		        {
		            name: '区域分布',
		            type: 'pie',
		            radius : '55%',
		            center: ['40%', '50%'],
		            data: data,
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
		houseAreaChart.setOption(option);
	}
	function showHouseStructureStatistics(xData,yData){
		var option = {
		    title : {
		        text: '房屋结构类型统计',
		        x:'left'
		    },
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    xAxis:  {
		    	type: 'category',
		        data: yData
		    },
		    yAxis: {
		    	minInterval: 1,
		    	min: function(value) {
		    		if(value.max == 0){
		    			return 5;
		    		}
		    	    return value.min;
		    	},
		    	type: 'value'	        
		    },
		    series : [
		        {
		            name: '房屋数量',
		            type: 'bar',
		            stack: '结构类型',
		            itemStyle:{
	                    normal: {
	                        color: function(params) {
	                            var colorList = color;
	                            return colorList[params.dataIndex]
	                        },
	                        label: {
	                            show: true,
	                            position: 'top',
	                            formatter: '{c}'
	                        }
	                    }
	                },
		            data: xData
		        }
		    ]
		};
		houseStructureChart.setOption(option);
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