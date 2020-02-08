/**
 * V_1.0 Created by oHa on 2017/12/27. ^ V_1.01 Created by oHa on 2018/1/25.
 */

$(function() {

	var context = $(".context").attr("content");

	// 案事件性质统计情况 1
	$.getJSON(context + "/report/ccmIncidentBegin/getItemByProperty", function(
			data) {
		// 接收参数
		$.AlarmNatureFun("AlarmNatureEcharts", $.ToConvertA(data));
	});

	// 案事件分级统计情况 2
	 
	$.getJSON(context + "/report/ccmIncidentBegin/getItemByScale", function(
			data) {
		//$.AlarmFun("echartsAlarm", $.ToConvertA(data));
		$.AlarmFun1("echartsAlarm", $.ToConvertA(data.result),data.name)
	// 添加左侧 数据
		//$.AlarmFunTable("echartsAlarm-value", $.ToConvertA(data));
	});

	GetAlarmHtml();
	
	function GetAlarmHtml() {
		var type = '';
		$('ul.echartsAlarm-tab>li').click(function() {
			$(this).addClass('active').siblings().removeClass("active");
			type = '0' + $(this).index();
			if (type == 00) {
				type = '';
			}
			getItemByScaleTableFun(type);
		})
		getItemByScaleTableFun(type);
	}
	// 案事件分区域、分级统计情况
	function getItemByScaleTableFun(type) {
		$.getJSON(context + "/report/ccmIncidentBegin/getItemByScaleTable", {
			type : type || '',
		}, function(data) {
			$.updateSearchTable("#alarmTbody", data)
		});
	}

	// 案事件 统计情况
	$.getJSON(context + "/report/ccmIncidentBegin/getItemBySum",
			function(data) {
				$("#CameraTotal").html(data["2"]);
				$("#OnLineRate").html(data["0"]);
				$("#OkRate").html(data["1"]);
			});

	// 案事件 统计情况
	$.getJSON(context + "/report/ccmIncidentBegin/getItemBySum",
			function(data) {
				$("#CameraTotal").html(data["2"]);
				$("#OnLineRate").html(data["1"]);
				$("#OkRate").html(data["0"]);
			});
//近七天案事件处理数
	$.getJSON(context + "/report/ccmIncidentBegin/findSumByEventWeek", function(
			data) {
		$.ChuEchartsFun("ChuEcharts", $.ToConvertA(data))
	
	});
	// 案事件 统计情况
	$.getJSON(context + "/report/ccmIncidentBegin/findSolveByArea", function(
			data) {
		var dataX = data["1"];
		var dataY = data["2"];
		// 社区事务
		$.AffairFun("AlarmInfoEcharts", dataX, dataY);

	});

	// 案事件 统计情况
	$.getJSON(context + "/report/ccmIncidentBegin/findSolveByMon", function(
			data) {
		var dataX = data["X轴"];
		var dataY1 = data["案事件数"];
		var dataY2 = data["处理率"];
		// 社区事务
		$.AlarmWeekInfo("AlarmInfoWeekEcharts", dataX, dataY1, dataY2);
		//事件流程
		$.AlarmFlowFun();
	});

	// 案事件 统计情况
	$.getJSON(context + "/report/ccmIncidentBegin/findSumByCondition",
			function(data) {
				// 填充数据
				$.updateConditionTable('AlarmUL', data);
			});

	// 案事件 统计情况
	$.getJSON(context + "/report/ccmIncidentBegin/findSumByEventType",
			function(data) {
				// 填充数据
				$.GetWorkSheets("echarts3-1", $.ToConvertA(data));
			});
	// $(window).resize(function(){
	// 	window.location.reload()
	// })
})
