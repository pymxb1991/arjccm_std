
$(document).ready(function () {
	getPeopleSexCount();
	getPeopleBirthdayCount();
	getPeopleNationCount();
	getPeopleAgeCount();
});

/*function getCookie(cname)
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
var context = "/arjccm/a"
 var	context = $(".context").attr("content"); 
var FontColor="#999",backgroundColor="#fff";
var theme=getCookie('theme'); 
if(theme=="black"){
	FontColor="#fff";
	backgroundColor="#0e2a4c";
}
var color;
if(theme=='gradient'){
 color = [ '#1F8BFA', '#E84442', '#FAB736', '#2CC189', '#F9A388', '#77E7F1', '#9E56E9', '#FF7453', '#16DDD3', '#FDB733'];
}
else{
 color = ['#4573a7', '#aa4644', '#89a54e', '#db843d','#4298af', '#93a9d0', '#b9ce96', '#d09392','#a99bbc', '#92c3d4', '#ffdf5f', '#71588f'];
} */

function getPeopleSexCount() {

	$.ajax({
		type: "POST",
		url: ctx+"/comprehensiveTopic/damPeopleCount/peopleSexCount",
		dataType: "json",
		cache: false,
		async: true,
		success: function (data) {
			showPeopleSexCount(data);
		}
	});
}


function showPeopleSexCount(data){
	var option = {
		title: {
			text: '人员性别分析统计',
			padding: [
				30,  // 上
				10, // 右
				5,  // 下
				20, // 左
			],
			textStyle: {
				fontSize: 15,
				
			}
		},
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
				top: '15%',
				right: '4%',
		        /*left: 'left',*/
		        data: data["name"]
		    },
		    series : [
		        {
		            name: '',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '50%'],
		            
		            data: data["value"]
		        }
		    ]
	};
	
	var Barchart = echarts.init(document.getElementById('peopleSexCount'),'theme');
	Barchart.setOption(option);
}

function getPeopleBirthdayCount() {

	$.ajax({
		type: "POST",
		url: ctx+"/comprehensiveTopic/damPeopleCount/peopleBirthdayCount",
		dataType: "json",
		cache: false,
		async: true,
		success: function (data) {
			showPeopleBirthdayCount(data);
		}
	});
}

function showPeopleBirthdayCount(data){
	var option = {
			
		title: {
			text: '人员出生时间统计',
			padding: [
				30,  // 上
				10, // 右
				5,  // 下
				100, // 左
			],
			textStyle: {
				fontSize: 15,
				
			}
		},
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    legend: {
				top: '8%',
				right: '40%',
		        data: data["legend"]
		    },
		    grid: {
			    top: '15%',
		        left: '10%',
		        right: '4%',
		        bottom: '10%',
		        containLabel: true
		    },
		    xAxis: {
		        type: 'value',
		        boundaryGap: [0, 0.01]
		    },
		    yAxis: {
		        type: 'category',
		        data: data["yAxis"]
		    },
		    series: data["value"]
		};
	var Barchart = echarts.init(document.getElementById('peopleBirthdayCount'),'theme');
	Barchart.setOption(option);
}

function getPeopleNationCount() {

	$.ajax({
		type: "POST",
		url: ctx+"/comprehensiveTopic/damPeopleCount/peopleNationCount",
		dataType: "json",
		cache: false,
		async: true,
		success: function (data) {
			showPeopleNationCount(data);
		}
	});
}

function showPeopleNationCount(data){
	var option = {
			
		title: {
			text: '各民族人员统计',
			padding: [
				100,  // 上
				10, // 右
				5,  // 下
				20, // 左
			],
			textStyle: {
				fontSize: 15,
			
			}
		},
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: []
		    },
		    series : [
		        {
		            name: '',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            
		            data: data["value"]
		        }
		    ]
	};
	
	var Barchart = echarts.init(document.getElementById('peopleNationCount'),'theme');
	Barchart.setOption(option);
}



function getPeopleAgeCount() {

	$.ajax({
		type: "POST",
		url: ctx+"/comprehensiveTopic/damPeopleCount/peopleAgeCount",
		dataType: "json",
		cache: false,
		async: true,
		success: function (data) {
			showPeopleAgeCount(data);
		}
	});
}

function showPeopleAgeCount(data){
	var all = 100;// 总量
	var option = {
			
		title: {
			text: '人员年龄段统计',
			padding: [
				100,  // 上
				10, // 右
				5,  // 下
				20, // 左
			],
			textStyle: {
				fontSize: 15,
				
			}
		},
		tooltip: {
			trigger: 'item',
			formatter: "{a} <br/>{b} : {c} ({d}%)"
		},
		legend: {
			orient: 'vertical',
			top: '20%',
			right: '15%',
			data: data["name"]
		},
		series: [{
			name: '该年龄段人数：',
			type: 'pie',
			startAngle: -180,
			radius: ['0%', '55%'],
			center: ['50%', '60%'],
			
			data: data["value"]
		}]
	};

	var Barchart = echarts.init(document.getElementById('peopleAgeCount'),'theme');
	Barchart.setOption(option);
	Barchart.on('click',
		function (params) {
	});
}
/**
 * 人员区域社区(街道)分布分析统计
 */
$.getJSON(ctx + "/comprehensiveTopic/damPeopleCount/peopleRegionCount", function(
	data) {
	// 接收参数
	$.peopleRegionCountFun("peopleRegionCount", data);
});

$.peopleRegionCountFun = function (model, jsondata) {



	option = {
		
		title: {
			text: '人员区域社区(街道)分布分析统计',
			textStyle: {
				fontSize: 15,
			
			}
		},
		/*color: ['#71588f'],*/
		tooltip: {
			trigger: 'axis',
			axisPointer: {            // 坐标轴指示器，坐标轴触发有效
				type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		grid: {
			top: "100",
			left: '3%',
			right: '50',
			bottom: '60',
			containLabel: true
		},
		xAxis: [{
			type: 'category',
			data: $.ToConvertLegendA(jsondata),
			axisLine: {
				lineStyle: {
					
					
				}
			},
			axisTick: {
				alignWithLabel: true
			},

			axisLabel: {
				show: true,
				interval: 0, //设置为 1，表示『隔一个标签显示一个标签』
				rotate: -35,
				textStyle: {
					fontSize: 14,
					
				},
			}

		}
		],
		yAxis: [{
			type: 'value',
			splitLine: {show: false},//去除网格线
			axisLine: {
				lineStyle: {
					
				}
			},
			axisLabel: {
				show:true,
				textStyle :
					{
					
					}
			}
		}
		],
		series : [{
			name: '人数',
			type: 'bar',
			barWidth: '40%',
			data: jsondata,
			itemStyle: {
				normal: {
					/*color: '#FCCE10',*/
					},
					/*label: {
						show: true,
						position: 'top',
						formatter: '{c}'
					}*/

			},
		}
		]
	};

	// 初始化参数
	var myChart = echarts.init(document.getElementById(model),'theme');
	myChart.setOption(option, true);
}

// EchartType 转换成 Echart legend  所需要的类型
$.ToConvertLegendA = function(object) {
	var ajaxData = new Array();
	for (var one in object) {
		ajaxData.push(
			object[one]["type"]
		);
	}
	return ajaxData;
}