$(function() {
	/* initCaiJi(); */
	initMaoDun();
	DisputesStatus();
	OfficeTeam();
	RealPopulation();
	RealHousing();
	findCountAddressLibrary();
	TwoNewOrganization();
	RoadProtectionLine();
	CampusPeriphery();
	findCountPeopleForType();
	housePrup();
	findKeyPlace();
	construction();
	streetRoadLane();
	findCountSchool();
	PeopleStatistics('0', '户籍');
	getnumPopFlowTable();
	$('#PeopleStatistics li').click(function() {
		$('#PeopleStatistics li').removeClass('active');
		$(this).addClass('active');
		var type = $(this).attr('type');
		var title = $(this).attr('title');
		PeopleStatistics(type, title);
	})
	$('#peopleKym li').click(function() {
		$('#peopleKym li').removeClass('active');
		$(this).addClass('active');
		var type = $(this).attr('type');
		var title = $(this).attr('title');
		if(type == "1"){
			$('#securityPeopleKym').show();
			$('#allPeopleKym').hide();
		}else if(type == "2"){
			$('#securityPeopleKym').hide();
			$('#allPeopleKym').show();
		}else{
			$('#securityPeopleKym').show();
			$('#allPeopleKym').hide();
		}
	})
})
var FontColor = '#647484';
if ($.cookie('theme') == undefined) {
	FontColor = '#647484';
} else if ($.cookie('theme') == 'gradient') {
	FontColor = '#647484';
} else if ($.cookie('theme') == 'black') {
	FontColor = '#fff';
}

// 建筑物
function construction() {

	$.getJSON(ctx + "/index/chart/findCountByconstruction", function(data) {
		// 接收参数
		$('#construction').html(data.count);
	});

}

// 街路巷
function streetRoadLane() {

	$.getJSON(ctx + "/index/chart/streetRoadLane", function(data) {
		// 接收参数
		$('#streetRoadLane').html(data.count);
	});

}

// 学校数量
function findCountSchool() {

	$.getJSON(ctx + "/index/chart/findCountSchool", function(data) {
		// 接收参数
		$('#schoolNumber').html(data.count);
		$('#findCountSchool').html(data.count);
	});

}

// 重点场所查询
function findKeyPlace() {
	$.getJSON(ctx + "/index/chart/findKeyPlace", function(data) {
		// 接收参数
		var data = data.data;
		var a = 0;
		var b = 0;
		var c = 0;
		var d = 0;
		var e = 0;
		var f = 0;
		var g = 0;
		for ( var i in data) {
			if (data[i].keyPoint.indexOf("d") != -1) {
				a++;
				$('#findKeyPlace1').html(a);
			}

			if (data[i].keyPoint.indexOf("b") != -1) {
				b++;
				$('#findKeyPlace2').html(b);
			}
			if (data[i].keyPoint.indexOf("a") != -1) {
				c++;
				$('#findKeyPlace3').html(c);
			}
			if (data[i].keyPoint.indexOf("c") != -1) {
				d++;
				$('#findKeyPlace4').html(d);
			}
			if (data[i].keyPoint.indexOf("g") != -1) {
				e++;
				$('#findKeyPlace5').html(e);
			}
			if (data[i].keyPoint.indexOf("e") != -1) {
				f++;
				$('#findKeyPlace6').html(f);
			}
			if (data[i].keyPoint.indexOf("f") != -1) {
				g++;
				$('#findKeyPlace7').html(g);
			}
		}

	});
}

function myChartPeople(datatitle, dataX, dataY, model, mainContent, rotate) {
	// 指定图表的配置项和数据
	var option = {

		color : [ '#219FFC', '#F8C73C' ],
		grid : {
			left : '3%',
			right : '4%',
			bottom : '3%',
			containLabel : true
		},
		tooltip : {
			trigger: 'axis'
		},
		legend : {
			data : datatitle,
			textStyle : {
				color : FontColor,
			}
		},
		xAxis : {
			axisLabel : {
				textStyle : {
					color : FontColor
				}
			},
			axisTick: {
                show: false
            },
			axisLine : {
				lineStyle : {
					color : FontColor,
				}
			},
			data : dataX,

		},
		yAxis : {
			axisLabel : {
				textStyle : {
					color : FontColor
				}
			},
			axisTick: {
                show: false
            },
			axisLine : {
				lineStyle : {
					color : FontColor,
				}
			},
		},
		series : dataY
	};
	model.setOption(option);
}

// 判断是否为空
function IsNull(variable1) {
	if (variable1 !== null && variable1 != undefined && variable1 !== '') {
		return variable1;
	} else {
		return "0";
	}
}

/**
 * @Param titleS
 * @Param ajaxDataYALLS
 */
function getSeriesDate(titleS, ajaxDataYALLS) {
	// 返回数据
	var seriesDate1 = new Array();
	// for 循环生成数据
	for (var titleNumer = 0; titleNumer < titleS.length; titleNumer++) {
		seriesDate1.push({
			"name" : titleS[titleNumer],
			"type" : 'bar',
			"barWidth" : '20%',
			"data" : ajaxDataYALLS[titleNumer]
		})
	}
	// 返回数据
	return seriesDate1;
}

/**
 * @Param data
 *            返回数据
 * @Param titleS
 *            本月户籍新增人数
 * @Param XYtype
 *            type_value
 * 
 */
function getyAxisDate(data, titleS, XYtype) {
	// 表1数据-本月户籍新增人数 group area
	var ajaxDataYALL1 = new Array();
	for (var number = 0; number < titleS.length; number++) {
		var dataA = data[titleS[number]];
		var ajaxDataY1 = new Array();
		for ( var one in dataA) {
			var item = dataA[one];
			ajaxDataY1.push(item["value"]);
		}
		ajaxDataYALL1.push(ajaxDataY1);
	}
	return ajaxDataYALL1;
}

/**
 * @Param data
 *            数据
 * @Param titleS
 *            标题
 */
function getxAxisDate(data, titleS) {
	// 表1数据-本月户籍新增人数 group area
	var ajaxDataY1 = new Array();
	var dataA = data[titleS[0]];
	for ( var one in dataA) {
		var item = dataA[one];
		ajaxDataY1.push(item["type"]);
	}
	return ajaxDataY1;
}

// 人口统计情况
function PeopleStatistics(type, title) {

	title2 = [ "新增" + title + "人数", "" + title + "总人数" ];
	var myChart2 = echarts.init(document.getElementById('CaiJi'));
	$.getJSON(ctx + "/report/ccmPeopleStat/personStat",
		{
			title : title,
			type : type
		}, function(data) {

			// 图表2 数据
			var ajaxDataX2 = getxAxisDate(data, title2), ajaxDataYALL2 = getyAxisDate(
					data, title2);
			// 组装图表的Y轴数据
			var seriesDate2 = getSeriesDate(title2, ajaxDataYALL2);
			// 生成图表
			myChartPeople(title2, ajaxDataX2, seriesDate2,
					myChart2, "" + title + "人数", 0);

		});
}
// 出租房屋
function housePrup() {
	$.getJSON(ctx + '/index/chart/findCountByRentPur', function(data) {
		var data = data.data;
		for ( var i in data) {
			if (data[i].rentPur == '01') {
				$('#HousePrup01').html(data[i].count)
			} else if (data[i].rentPur == '02') {
				$('#HousePrup02').html(data[i].count)
			} else if (data[i].rentPur == '03') {
				$('#HousePrup03').html(data[i].count)
			} else if (data[i].rentPur == '04') {
				$('#HousePrup04').html(data[i].count)
			} else if (data[i].rentPur == '05') {
				$('#HousePrup05').html(data[i].count)
			} else if (data[i].rentPur == '06') {
				$('#HousePrup06').html(data[i].count)
			} else if (data[i].rentPur == '99') {
				$('#HousePrup99').html(data[i].count)
			}
		}
	})
}
// 重点人员
function findCountPeopleForType() {
	$.getJSON(ctx + '/index/chart/findCountPeopleForType', function(data) {
		var data = data.data;
		for ( var i in data) {
			if (data[i].name == 'dangerous') {
				$('#dangerous').html(data[i].count)
			} else if (data[i].name == 'illegal') {
				$('#illegal').html(data[i].count)
			} else if (data[i].name == 'dispute') {
				$('#dispute').html(data[i].count)
			} else if (data[i].name == 'heresy') {
				$('#heresy').html(data[i].count)
			} else if (data[i].name == 'drugs') {
				$('#drugs').html(data[i].count)
			} else if (data[i].name == 'escape') {
				$('#escape').html(data[i].count)
			} else if (data[i].name == 'security') {
				$('#security').html(data[i].count)
			} else if (data[i].name == 'aids') {
				$('#aids').html(data[i].count)
			} else if (data[i].name == 'petition') {
				$('#petition').html(data[i].count)
			} else if (data[i].name == 'psychogeny') {
				$('#psychogeny').html(data[i].count)
			} else if (data[i].name == 'rectification') {
				$('#rectification').html(data[i].count)
			} else if (data[i].name == 'release') {
				$('#release').html(data[i].count)
			} else if (data[i].name == 'serious') {
				$('#serious').html(data[i].count)
			}
		}
	})
}
function getnumPopFlowTable() {
	$.getJSON(ctx + "/report/ccmPeopleAmount/getnumPopFlowTable",
			function(data) {
				// 接收参数
				if(data[0]&&data[0]["value1"]){
					$('#PopKey01').html(data[0]["value1"]);
				}
				if(data[1]&&data[1]["value1"]){
					$('#PopKey02').html(data[1]["value1"]);
				}
				if(data[2]&&data[2]["value1"]){
					$('#PopKey03').html(data[2]["value1"]);
				}
				if(data[3]&&data[3]["value1"]){
					$('#PopKey04').html(data[3]["value1"]);
				}
				if(data[4]&&data[4]["value1"]){
					$('#PopKey05').html(data[4]["value1"]);
				}
				if(data[5]&&data[5]["value1"]){
					$('#PopKey99').html(data[5]["value1"]);
				}
			});
}
// 校园周边
function CampusPeriphery() {

	$.getJSON(ctx + '/index/chart/findCountSchoolPeople', function(data) {
		var data = data.data[0].count;
		$('#findCountSchoolPeople').html(data);
	})
}
// 实有人口
function RealPopulation() {
	$.getJSON(ctx + '/index/chart/findCountByType', function(data) {
		var data = data.data;
		for ( var i in data) {
			if (data[i].type == '10') {
				$('#CountByType10').html(data[i].count)
			} else if (data[i].type == '20') {
				$('#CountByType20').html(data[i].count)
			} else if (data[i].type == '30') {
				$('#CountByType30').html(data[i].count)
			} else if (data[i].type == '40') {
				$('#CountByType40').html(data[i].count)
			}
		}
	})
	$.getJSON(ctx + '/index/chart/findCountByUniformlogo', function(data) {
		var data = data.data;
		for ( var i in data) {
			if (data[i].uniformlogo == '01') {
				$('#uniformlogo01').html(data[i].count)
			} else if (data[i].uniformlogo == '02') {
				$('#uniformlogo02').html(data[i].count)
			}
		}
	})
}
// 实有房屋
function RealHousing() {
	$.getJSON(ctx + '/index/chart/findCountByHouseType', function(data) {
		var data = data.data;
		for ( var i in data) {
			if (data[i].houseType == '01') {
				$('#houseType01').html(data[i].count)
			} else if (data[i].houseType == '02') {
				$('#houseType02').html(data[i].count)
			} else if (data[i].houseType == '03') {
				$('#houseType03').html(data[i].count)
			} else if (data[i].houseType == '99') {
				$('#houseType99').html(data[i].count)
			}
		}
	})
}
// 地址库
function findCountAddressLibrary() {
	$.getJSON(ctx + '/index/chart/findCountAddressLibrary', function(data) {
		var data = data.data;
		for ( var i in data) {
			if (data[i].uniformlogo == '01') {
				$('#uniformlogo01').html(data[i].count)
			} else if (data[i].uniformlogo == '02') {
				$('#uniformlogo02').html(data[i].count)
			}
		}
	})
}
// 两新组织
function TwoNewOrganization() {
	$.getJSON(ctx + '/index/chart/findCountByCompType', function(data) {
		var data = data.data;
		for ( var i in data) {
			if (data[i].compType == '01') {
				$('#compType01').html(data[i].count)
			} else if (data[i].compType == '02') {
				$('#compType02').html(data[i].count)
			} else if (data[i].compType == '03') {
				$('#compType03').html(data[i].count)
			} else if (data[i].compType == '04') {
				$('#compType04').html(data[i].count)
			} else if (data[i].compType == '05') {
				$('#compType99').html(data[i].count)
			}
		}
	})
}
// 护路护线
function RoadProtectionLine() {
	$.getJSON(ctx + '/index/chart/findCountLine', function(data) {
		var data = data.data[0].count;
		$('#findCountLine').html(data);
	});
	$.getJSON(ctx + '/index/chart/findCountLineProtect', function(data) {
		var data = data.data[0].count;
		$('#findCountLineProtect').html(data);
	})
}
function initCaiJi() {
	/*
	 * var option = { color: ['#ff8948', '#82c9f9', '#dd80d7'], tooltip: {
	 * trigger: 'axis', axisPointer: { type: 'shadow' } }, legend: { data:
	 * ['实有人口', '实有房屋', '实有单位'] }, grid: { left: '3%', right: '4%', bottom:
	 * '3%', containLabel: true }, calculable: true, xAxis: [ { type:
	 * 'category', axisTick: {show: false}, data: ['一月', '二月', '三月', '四月', '五月',
	 * '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'] } ],
	 * 
	 * yAxis: [ { type: 'value' } ], series: [ { name: '实有人口', type: 'bar',
	 * barGap: 0, data: [320, 332, 301, 334, 390,320, 332, 301, 334, 390, 334,
	 * 390] }, { name: '实有房屋', type: 'bar', data: [220, 182, 191, 234, 290,220,
	 * 182, 191, 234, 290, 234, 290] }, { name: '实有单位', type: 'bar', data: [150,
	 * 232, 201, 154, 190,150, 232, 201, 154, 190,154, 190] } ] };
	 */
	var option = {
		color : [ '#4573a7', '#89a54e' ],
		/*
		 * title: { text: '人口变化情况', x: 'center', y: 'top', textStyle: {
		 * fontWeight: 'normal', color: '#fff', fontSize: '14' } },
		 */
		tooltip : {
			trigger : 'axis',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		/*
		 * legend: { data:['迁入','迁出'], textStyle: { color: '#fff', fontSize:
		 * _fontSize }, itemGap: 100 },
		 */
		grid : {
			left : '3%',
			right : '4%',
			bottom : '3%',
			containLabel : true
		},
		xAxis : [ {
			type : 'category',
			data : [ '2018-2', '2018-3', '2018-4', '2018-5', '2018-6',
					'2018-7', '2018-8' ],

		} ],
		yAxis : [ {
			type : 'value',

		} ],
		series : [

		{
			name : '迁入',
			type : 'bar',
			stack : '广告',
			barWidth : '30%',
			data : [ 420, 332, 401, 334, 490, 530, 410 ]
		}, {
			name : '迁出',
			type : 'bar',
			stack : '广告',
			barWidth : '30%',
			data : [ 220, 182, 191, 134, 190, 230, 210 ]
		} ]
	};
	// 初始化参数
	var myChart = echarts.init(document.getElementById('CaiJi'));
	myChart.setOption(option, true);
}
function initMaoDun() {
	$.getJSON(ctx + '/index/chart/solveEvent', function(data) {
		var dataArr = [];
		var xAxisArr = [];
		var seriesArr = [];
		var seriesArrAll = [];
		for ( var i in data) {
			xAxisArr.push(data[i].type);
			seriesArr.push(data[i].value);
			seriesArrAll.push(data[i].value1)
		}
		var option = {
			color : [ '#219FFC', '#FF5E28' ],
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '事件总数', '事件处置' ],
				textStyle : {
					color : FontColor,
				}
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : {
				type : 'category',
				boundaryGap : false,
				data : xAxisArr,
				axisLabel : {
					textStyle : {
						color : FontColor
					}
				},
				axisLine : {
					lineStyle : {
						color : FontColor,
					}
				},
			},
			yAxis : {
				type : 'value',
				axisLabel : {
					textStyle : {
						color : FontColor
					}
				},
				axisLine : {
					lineStyle : {
						color : FontColor,
					}
				},
			},
			series : [ {
				name : '事件处置',
				type : 'line',
				symbol: 'circle',
	            symbolSize: 10,
	            itemStyle: {
	                normal: {
	                	color: '#219FFC',
	                    lineStyle: {
	                        color: "#219FFC",
	                        width: 2
	                    },
	                    areaStyle: {
	                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
	                            offset: 0,
	                            color: 'rgba(33, 159, 252, 1)'
	                        }, {
	                            offset: 1,
	                            color: 'rgba(33, 159, 252, 0)'
	                        }])
	                    },
	                    shadowColor: '#219FFC',
	                    shadowBlur: 20,
	                }
	            },
				data : seriesArr
			}, {
				name : '事件总数',
				type : 'line',
				symbol: 'circle',
	            symbolSize: 10,
	            itemStyle: {
	                normal: {
	                	color: '#FF5E28',
	                    lineStyle: {
	                        color: "#FF5E28",
	                        width: 2
	                    },
	                    areaStyle: {
	                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
	                            offset: 0,
	                            color: 'rgba(255, 94, 40, 1)'
	                        }, {
	                            offset: 1,
	                            color: 'rgba(255, 94, 40, 0)'
	                        }])
	                    },
	                    shadowColor: '#FF5E28',
	                    shadowBlur: 20,
	                }
	            },
				data : seriesArrAll
			} ]
		};

		// 初始化参数
		var myChart = echarts.init(document.getElementById('MaoDun'));
		myChart.setOption(option, true);
	})

}
// 矛盾纠纷排查
function DisputesStatus() {
	$.getJSON(ctx + '/index/chart/byStatus', function(data) {
		var numData = data.data;
		var DisputesStatus0 = 0, DisputesStatus1 = 0, DisputesStatus2 = 0, DisputesStatus3 = 0, DisputesStatus4 = 0;
		for ( var i in numData) {
			if (numData[i].status == '01') {
				DisputesStatus0 = numData[i].count;
				$('#DisputesStatus0').html(DisputesStatus0);
			} else if (numData[i].status == '02') {
				DisputesStatus1 = numData[i].count;
				$('#DisputesStatus1').html(DisputesStatus1);
			}
			// else if (numData[i].status == '05') {
			// 	DisputesStatus2 = numData[i].count;
			// 	$('#DisputesStatus2').html(DisputesStatus2);
			// } else if (numData[i].status == '03') {
			// 	DisputesStatus3 = numData[i].count;
			// 	$('#DisputesStatus3').html(DisputesStatus3);
			// } else if (numData[i].status == '04') {
			// 	DisputesStatus4 = numData[i].count;
			// 	$('#DisputesStatus4').html(DisputesStatus4);
			// }
		}
		DisputesStatusAll = Number(DisputesStatus0) + Number(DisputesStatus1);
				// + Number(DisputesStatus2)+ Number(DisputesStatus3)+ Number(DisputesStatus4);
		$('#DisputesStatusAll').html(DisputesStatusAll);
	})
}
// 综治机构
function OfficeTeam() {
	$.getJSON(ctx + '/index/chart/findOfficeCount', function(data) {
		$('#OfficeCount').html(data.data[0].count);
	})
	$.getJSON(ctx + '/index/chart/findTeamCount', function(data) {
		$('#TeamCoun').html(data.data[0].count);
	})
}
