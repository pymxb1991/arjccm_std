/**
 * Created by oHa on 2018/2/5.
 */
var windowsHeight, _fontSize = 14,
	_fontSize1 = 26,
	breakData = 8,
	legendTop = '30%',
	radiusData = [
		'40%', '65%'
	],
	lengthECharts = 30,
	mapHeigth = '90%',
	zoom = 14.75,
	centerCoordinate = [
		117.663920, 39.035650
	];
var color = ['#aa4644', '#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d',
	'#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'
];

$(function() {
	windowsHeight = $(window).width();
	if(windowsHeight > 1366) {
		_fontSize = 14;
		legendTop = '5%';
		radiusData = [90, 65];
		lengthECharts = 30;
		_fontSize1 = 26;
		breakData = 8;
	} else {
		_fontSize = 10;
		legendTop = '5%';
		radiusData = [60, 45];
		lengthECharts = 20;
		_fontSize1 = 14;
		breakData = 6;
	}
	var ctx = $("#ctx").attr("ctx");
	$.post(ctx + "/report/getMap", function(data) {
		// 学员性别、民族
		EchartsSex(data["type0"], data["type1"]);
		// 部门人数
		EchartsPeopleNumber(data["type2"]);
		// 改为学员类型构成
		EchartsOccupation(data["type3"]);
		// 支部考试TOP10
		EchartsExamination(data["type8"]);
		// 学员数量变化曲线
		EchartsNumber(data["type4"]);
		
		//学员组织类型
		EchartsoOrganization(data["type9"]);
		// 学员活动变化曲线
		//EchartsActivity(data["type12"],data["type13"],data["type14"]);
		EchartsActivity1(data["type12"]);
		EchartsActivity2(data["type13"]);
		EchartsActivity3(data["type14"]);

	});
})

// 学员性别、民族
function EchartsSex(data1, data2) {
	option = {
		//  tooltip : {
		//    trigger : 'item',
		//    formatter : "{a} <br/>{b}: {c} ({d}%)"
		//  },
		title: {
			text: '学员性别、民族构成',
			textStyle: {
				fontSize: 15
			}
		},
		legend: {
			orient: 'vertical',
			//x: 'right',
			right: 0,
			top: 0,
			data: ['男性', '女性', '汉族学员', '少数民族']
		},
		series: [{
			name: '学员男女构成',
			type: 'pie',
			selectedMode: 'single',
			radius: [0, '33%'],
			center: ['46%', '53%'],
			label: {
				normal: {
					show: true,
					formatter: '{c}人\n({d}%)',
					position: 'inner'
				}
			},
			labelLine: {
				normal: {
					show: true,
					length: 50,
					length2: 30
				}
			},
			color: color,
			data: data1
		}, {
			name: '学员民族构成',
			type: 'pie',
			radius: ['48%', '66%'],
			center: ['46%', '53%'],
			data: data2,
			label: {
				normal: {
					show: true,
					formatter: "{c}人\n({d}%)"
				}
			},
			labelLine: {
				normal: {
					show: true,
					smooth: 0.2,
					length: 5,
					length2: 8
				}

			},
		}]
	};
	var Barchart = echarts.init(document.getElementById('EchartsSex'));
	Barchart.setOption(option);
}


// 学员组织类型
function EchartsoOrganization(data) {
	// console.log(data)
	option = {
		color: color,
		title: {
			text: '学员组织类型',
			textStyle: {
				fontSize: 15
			}
		},
		legend: {
			orient: 'vertical',
			//x: 'right',
			right: 0,
			top: 0,
			data: ['工人', '两新学员', '知识分子']
		},
		tooltip: {
			trigger: 'item',
			formatter: "{a} <br/>{b} : {c} ({d}%)"
		},
		series: [{
			name: '学员组织类型',
			type: 'pie',
			radius: ['48%', '66%'],
			center: ['50%', '50%'],
			data: data,
			label: {
				normal: {
					formatter: "{b} : {c}\n({d}%)"
				},
				textStyle: {
					fontSize: 10 //文字的字体大小
				}
			},
			labelLine: {
				normal: {
					show: true,
					smooth: 0.2,
					length: 5,
					length2: 8
				}
			},
			itemStyle: {
				emphasis: {
					shadowBlur: 10,
					shadowOffsetX: 0,
					shadowColor: 'rgba(0, 0, 0, 0.5)'
				}
			}
		}]
	};
	var Barchart = echarts.init(document.getElementById('EchartsoOrganization'));
	Barchart.setOption(option);
}

// 学员类型构成
function EchartsOccupation(data) {
	// console.log(data)
	option = {
		color: color,
		title: {
			text: '学员类型构成',
			textStyle: {
				fontSize: 15
			}
		},
		legend: {
			orient: 'vertical',
			//x: 'right',
			right: 0,
			top: 0,
			data: ['正式学员', '预备学员', '积极分子']
		},
		tooltip: {
			trigger: 'item',
			formatter: "{a} <br/>{b} : {c} ({d}%)"
		},
		series: [{
			name: '学员类型构成',
			type: 'pie',
			radius: '70%',
			center: ['50%', '50%'],
			data: data,
			label: {
				normal: {
					formatter: "{b} : {c}\n({d}%)"
				},
				textStyle: {
					fontSize: 10 //文字的字体大小
				}
			},
			labelLine: {
				normal: {
					show: true,
					smooth: 0.2,
					length: 5,
					length2: 8
				}
			},
			itemStyle: {
				emphasis: {
					shadowBlur: 10,
					shadowOffsetX: 0,
					shadowColor: 'rgba(0, 0, 0, 0.5)'
				}
			}
		}]
	};
	var Barchart = echarts.init(document.getElementById('EchartsOccupation'));
	Barchart.setOption(option);
}

// 支部考试
function EchartsExamination(data) {
	// console.log("-----------");
	// console.log(data)
	var names = new Array();
	var values = new Array();
	// 循环添加 数据信息
	for(var i = 0; i < data.length; i++) {
		// 目前不要月份，需要部门显示在一行，暂时注掉
		/*
		 * if (i % 2 == 1) { names.push("\n" + data[i]["name"]) } else {
		 */
		names.push(data[i]["name"])
		/* } */
		values.push(data[i]["value"]);
	}
	// var dataY = values
	option = {
		color: color,
		title: {
			text: '各支部考试成绩',
			textStyle: {
				fontSize: 15
			},
		},
		//  tooltip : {
		//    trigger : 'axis',
		//    formatter : "平均成绩: {c} "
		//  },

		grid: {
			top: '12%',
			left: '3%',
			right: '6%',
			bottom: '3%',
			containLabel: true
		},
		yAxis: [{
			'type': 'category',
			'axisLabel': {
				'interval': 0
			},
			'data': names,
			axisPointer: {
				type: 'shadow'
			},
			splitLine: {
				show: false
			}
		}],
		xAxis: [{
			type: 'value',
			min: 0,
			max: 100,
			axisLabel: {
				formatter: '{value}'
			},
			splitLine: {
				show: false
			}
		}],
		series: [{
			name: '平均成绩',
			type: 'bar',
			data: values,
			barWidth: '40%',
			label: {
				normal: {
					show: true,
					formatter: '{c}分',
					position: 'insideRight',
					color: '#fff'
				}
			},
		}]
	};
	var Barchart = echarts.init(document.getElementById('EchartsExamination'));
	Barchart.setOption(option);
}

// 学员数量变化曲线
function EchartsNumber(data) {
	var names = new Array();
	var values = new Array();
	// 循环添加 数据信息
	for(var i = 0; i < data.length; i++) {
		names.push(data[i]["name"])
		values.push(data[i]["value"]);
	}
	option = {
		color: color,
		title: {
			text: '学员数量变化',
			textStyle: {
				fontSize: 15
			},
		},
		tooltip: {
			trigger: 'axis',
			formatter: "学员数量: {c} "
		},

		grid: {
			left: '10%',
			right: '4%',
		},
		xAxis: [{
			'type': 'category',
			'axisLabel': {
				'interval': 0
			},
			'axisLine': {
				show: true
			},
			'data': names,
			axisPointer: {
				type: 'shadow'
			},
			splitLine: {
				show: false
			}
		}],
		yAxis: [{
			type: 'value',
			name: '人数',
			min: 0,
			//        max : 100,
			axisLabel: {
				formatter: '{value}'
			},
			splitLine: {
				show: false
			}
		}],
		series: [{
			name: '学员数量变化',
			type: 'line',
			data: values,
			barWidth: '40%',
			label: {
				normal: {
					show: true,
					position: 'top'
				}
			},
		}]
	};
	var Barchart = echarts.init(document.getElementById('EchartsNumber'));
	Barchart.setOption(option);
}

// 学员活动变化曲线
function EchartsActivity1(data) {
	var names = new Array();
	var values = new Array();
	var names1 = new Array();
	var values1 = new Array();
	var names2 = new Array();
	var values2 = new Array();
	// 循环添加 数据信息
	for(var i = 0; i < data.length; i++) {
		names.push(data[i]["name"])
		values.push(data[i]["value"]);
	}
	//	for(var i = 0; i < data1.length; i++) {
	//		names1.push(data1[i]["name"])
	//		values1.push(data1[i]["value"]);
	//	}
	//	for(var i = 0; i < data2.length; i++) {
	//		names2.push(data2[i]["name"])
	//		values2.push(data2[i]["value"]);
	//	}
	option = {
		color: color,
		tooltip: {
			trigger: 'item',
			formatter: "月份：{b}<br/>次数：{c} "
		},
		//		legend: {
		//			data: ['党课']
		//		},
		grid: {
			left: '10%',
			right: '3%',
			//      top : '20%',
			//      bottom : '15%',
		},
		title: {
			text: '党课活动变化曲线',
			textStyle: {
				fontSize: 15
			}
		},
		xAxis: {
			type: 'category',
			'axisLabel': {
				'interval': 0
			},
			data: names
		},
		yAxis: {
			name: '次数',
			type: 'value'
		},
		series: [{
			name: '党课',
			type: 'line',
			stack: '总量',
			areaStyle: {
				normal: {}
			},
			label: {
				normal: {
					show: true,
					position: 'top'
				}
			},
			data: values
		}]
	};
	var Barchart = echarts.init(document.getElementById('EchartsActivity1'));
	Barchart.setOption(option);
}

// 学员活动变化曲线
function EchartsActivity2(data) {
	var color = ['#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d',
	'#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'
];
	var names = new Array();
	var values = new Array();
	var names1 = new Array();
	var values1 = new Array();
	var names2 = new Array();
	var values2 = new Array();
	// 循环添加 数据信息
	for(var i = 0; i < data.length; i++) {
		names.push(data[i]["name"])
		values.push(data[i]["value"]);
	}
	//	for(var i = 0; i < data1.length; i++) {
	//		names1.push(data1[i]["name"])
	//		values1.push(data1[i]["value"]);
	//	}
	//	for(var i = 0; i < data2.length; i++) {
	//		names2.push(data2[i]["name"])
	//		values2.push(data2[i]["value"]);
	//	}
	option = {
		color: color,
		tooltip: {
			trigger: 'item',
			formatter: "月份：{b}<br/>次数：{c} "
		},
		//		legend: {
		//			data: ['评论']
		//		},
		grid: {
			left: '10%',
			right: '3%',
			//      top : '20%',
			//      bottom : '15%',
		},
		title: {
			text: '评论活动变化曲线',
			textStyle: {
				fontSize: 15
			}
		},
		xAxis: {
			type: 'category',
			'axisLabel': {
				'interval': 0
			},
			data: names
		},
		yAxis: {
			name: '次数',
			type: 'value'
		},
		series: [{
			name: '评论',
			type: 'line',
			stack: '总量',
			areaStyle: {
				normal: {}
			},
			label: {
				normal: {
					show: true,
					position: 'top'
				}
			},
			data: values
		}]
	};
	var Barchart = echarts.init(document.getElementById('EchartsActivity2'));
	Barchart.setOption(option);
}

// 学员活动变化曲线
function EchartsActivity3(data) {
	var color = ['#89a54e', '#71588f', '#4298af', '#db843d',
	'#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'
];
	var names = new Array();
	var values = new Array();
	var names1 = new Array();
	var values1 = new Array();
	var names2 = new Array();
	var values2 = new Array();
	// 循环添加 数据信息
	for(var i = 0; i < data.length; i++) {
		names.push(data[i]["name"])
		values.push(data[i]["value"]);
	}
	//	for(var i = 0; i < data1.length; i++) {
	//		names1.push(data1[i]["name"])
	//		values1.push(data1[i]["value"]);
	//	}
	//	for(var i = 0; i < data2.length; i++) {
	//		names2.push(data2[i]["name"])
	//		values2.push(data2[i]["value"]);
	//	}
	option = {
		color: color,
		tooltip: {
			trigger: 'item',
			formatter: "月份：{b}<br/>次数：{c} "
		},
		//		legend: {
		//			data: ['会议']
		//		},
		grid: {
			left: '10%',
			right: '3%',
			//      top : '20%',
			//      bottom : '15%',
		},
		title: {
			text: '会议活动变化曲线',
			textStyle: {
				fontSize: 15
			}
		},
		xAxis: {
			type: 'category',
			'axisLabel': {
				'interval': 0
			},
			data: names
		},
		yAxis: {
			name: '次数',
			type: 'value'
		},
		series: [{
			name: '会议',
			type: 'line',
			stack: '总量',
			areaStyle: {
				normal: {}
			},
			label: {
				normal: {
					show: true,
					position: 'top'
				}
			},
			data: values
		}]
	};
	var Barchart = echarts.init(document.getElementById('EchartsActivity3'));
	Barchart.setOption(option);
}

// 部门人数
function EchartsPeopleNumber(data) {
	var names = new Array();
	var values = new Array();
	// 循环添加 数据信息
	for(var i = 0; i < data.length; i++) {
		names.push(data[i]["name"])
		values.push(data[i]["value"]);
	}
	option = {
		color: color,
		title: {
			text: '部门人数统计',
			textStyle: {
				fontSize: 15
			}
		},
		//  tooltip : {
		//    trigger : 'axis',
		//    formatter : "人数: {c} ",
		//    axisPointer : {
		//      type : 'shadow'
		//    }
		//  },
		grid: {
			top: '12%',
			left: '3%',
			right: '6%',
			bottom: '3%',
			containLabel: true
		},
		xAxis: {
			type: 'value',
			boundaryGap: [0, 0.01]
		},
		yAxis: {
			type: 'category',
			data: names
		},
		series: [{
			type: 'bar',
			data: values,
			label: {
				normal: {
					show: true,
					formatter: '{c}人',
					position: 'insideRight',
					color: '#fff'
				}
			},
		}]
	};
	var Barchart = echarts.init(document.getElementById('EchartsPeopleNumber'));
	Barchart.setOption(option);
}