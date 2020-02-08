/**
 * 实有人口统计
 */
$(function() {
	// project-Path
	var context = $(".context").attr("content");

	// 初始化 Echarts组件
	var myChart = echarts.init(document.getElementById('ech'), 'walden');
	var myChart1 = echarts.init(document.getElementById('ech1'), 'walden');
	var myChart2 = echarts.init(document.getElementById('ech2'), 'walden');

	// 初始化页面方法
	showEchart();

	// 点击查询按钮 触发 页面展示方法
	$("#btnSubmit.ccmPepInfo").click(function() {
		showEchart();
	});
	// 单表视图数据填充
	function myChart1F(dataX, dataY, typeShape, myChart1) {
		// 指定图表的配置项和数据
		var option = {
			// 表格位置偏移量
			grid : {
				right : "3%",
				left : "5%"
			},
			title : {
				text : '实有人口新增人数'
			},
			tooltip : {},
			legend : {
				data : [ '每月新增人数' ]
			},
			xAxis : {
				data : dataX
			},
			yAxis : {},
			series : [ {
				name : '每月新增人数',
				// type : 'bar',
				type : typeShape,
				data : dataY,
				barWidth : "20%",
				animationDelay : function(idx) {
					return idx * 20 + 100;
				}
			} ],
			// 展现动画方式
			animationEasing : 'elasticOut',
			animationDelayUpdate : function(idx) {
				return idx * 5;
			}
		};
		myChart1.setOption(option);

	}
	// 多表视图数据填充
	function myChart2F(dataX, dataY, myChart2) {
		// 指定图表的配置项和数据
		var option = {
			grid : {
				right : "3%",
				left : "5%"
			},
			title : {
				text : '实有人口新增人数'
			},
			tooltip : {},
			legend : {
				data : [ '户籍', '流动', '境外' ]
			},
			xAxis : {
				data : dataX
			},
			yAxis : {},
			series : [ {
				name : '户籍',
				type : 'bar',
				data : dataY[0]

			}, {
				name : '流动',
				type : 'bar',
				data : dataY[1]

			}, {
				name : '境外',
				type : 'bar',
				data : dataY[2]

			} ]
		};
		myChart2.setOption(option);
	}

	// 展示 页面的方法
	function showEchart() {
		// X轴标题 Y轴数据 单表数据
		var ajaxDataX = new Array();
		var ajaxDataY = new Array();
		var ajaxDataXL = new Array();
		var ajaxDataYL = new Array();

		// 多表数据
		var ajaxDataXALL = new Array();
		var ajaxDataYALL = new Array();

		// GetJson 获取数据
		$.getJSON(context + "/pop/ccmPepInfo/popEchA", {
			"type" : $("#type").val()
		}, function(data) {
			/**
			 * 单表数据
			 */
			var dataA = data["按月"];
			for ( var one in dataA) {
				var item = dataA[one];
				ajaxDataX.push(item["type"]);
				ajaxDataY.push(item["value"]);
			}
			var dataA = data["按日"];
			for ( var one in dataA) {
				var item = dataA[one];
				ajaxDataXL.push(item["type"]);
				ajaxDataYL.push(item["value"]);
			}
			/**
			 * 多表数据
			 */
			// 户籍
			var dataA = data["户籍"];
			var ajaxDataY1 = new Array();
			for ( var one in dataA) {
				var item = dataA[one];
				ajaxDataXALL.push(item["type"]);
				ajaxDataY1.push(item["value"]);
			}
			ajaxDataYALL.push(ajaxDataY1);
			// 流动
			var ajaxDataY2 = new Array();
			var dataA = data["流动"];
			for ( var one in dataA) {
				var item = dataA[one];
				ajaxDataY2.push(item["value"]);
			}
			ajaxDataYALL.push(ajaxDataY2);
			// 境外
			var ajaxDataY3 = new Array();
			var dataA = data["境外"];
			for ( var one in dataA) {
				var item = dataA[one];
				ajaxDataY3.push(item["value"]);
			}
			ajaxDataYALL.push(ajaxDataY3);
			// 生成 单表 柱状图
			myChart1F(ajaxDataX, ajaxDataY, "bar", myChart1);
			// 生成 单表 曲线图
			myChart1F(ajaxDataXL, ajaxDataYL, "line", myChart2);
			// 生成 多表 柱状图
			myChart2F(ajaxDataXALL, ajaxDataYALL, myChart);
			// 生成 表单
			TableList("echList1", data, "按月");
			TableList("echList2", data, "按日");
			// 生成 总表表单
			TableListAll(ajaxDataXALL, ajaxDataYALL)
		});
	}

	// 补充Table数据-单表
	function TableList(echList, data, type) {
		// 拼接 table内容
		var tableContent = "";
		// 获取 按月类型值
		var dataA = data[type];
		for ( var one in dataA) {
			// 获取每一个值
			var item = dataA[one];
			tableContent += "<tr><td>" + item["type"] + "</td><td>"
					+ item["value"] + "</td></tr>";
		}
		// 添加内容 到页面
		$("#" + echList + " .body").html(tableContent);

	}
	// 补充Table数据-多表
	function TableListAll(data1, data2) {
		// 拼接 table内容
		var tableContent = "";
		// 获取 按月类型值
		for ( var one in data1) {
			// 获取每一个值
			tableContent += "<tr><td>" + data1[one] + "</td><td>"
					+ IsNull(data2[0][one]) + "</td><td>"
					+ IsNull(data2[1][one]) + "</td><td>"
					+ IsNull(data2[2][one]) + "</td></tr>";
		}
		// 添加内容 到页面
		$("#echListAll .body").html(tableContent);

		$('.table.table-striped').bootstrapTable({
			height:250
		});
	}
	// 判断是否为空
	function IsNull(variable1) {
		if (variable1 !== null && variable1 != undefined && variable1 !== '') {
			return variable1;
		} else {
			return "0";
		}
	}
});

function page(n, s) {
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#searchForm").submit();
	return false;
}

function show() {
	var s = $("#che").prop('checked');
	if (s) {
		$("#show").css("display", "block");
	} else {
		$("#show").css("display", "none");
	}
}
