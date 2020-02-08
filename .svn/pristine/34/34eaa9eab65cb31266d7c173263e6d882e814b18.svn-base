/**
 * V_1.0 Created by oHa on 2017/12/27. ^ V_1.01 Created by oHa on 2018/1/25.
 */
var context 
$(function() {

	 context = $(".context").attr("content");

	
	//仓库、物资、物资类型总数量统计
	$.getJSON(context + "/storage/plmStorage/countStorageList", function(
			data) {
		// 接收参数
		$.CountStorage("countStorage", data);
	});

	//各仓库物资数量
	$.getJSON(context + "/storage/plmEquipment/countEquipmentByStorageAjax", function(
			data) {
	// 接收参数 
		$.CountEquipmentByStorage("countEquipmentByStorage", data);
	});
		
	
	// 物资类别
	$.getJSON(context + "/storage/plmEquipment/ratioEquipmentByType", function(
			data) {
		// 接收参数
		$.RatioEquipmentByType("ratioEquipmentByType", $.ToConvertA(data[0]),$.ToConvertB(data[1]),data[2]);
	});
	
	

	// 物资使用情况
	$.getJSON(context + "/storage/plmEquipment/countEquipmentByStatsAjax", function(
			data) {
		// 接收参数
		$.CountEquipmentByStats("countEquipmentByStats", data);
	});
	
	
	/*// 物资预警（柱图）
	$.getJSON(context + "/storage/plmEquipment/warningEquipment", function(
			data) {
		// 接收参数
		$.WarningEquipment("warningEquipment", data);
	});*/
	// 物资预警（表格）
	$.getJSON(context + "/storage/plmEquipment/warningEquipmentTable", function(
			data) {
		// 接收参数
		$.WarningEquipmentTable("warningEquipment", data[0],data[1],data[2]);
	});
	
	//车辆数量
	$.getJSON(context + "/car/plmCar/countCar", function(
			data) {
		// 接收参数
		$.CountCar("countCar", data);
	});
	// 车辆类型
	$.getJSON(context + "/car/plmCar/numByVtypeAjax", function(
			data) {
		// 接收参数
		$.NumByVtype("numByVtype", $.ToConvertA(data),$.ToConvertLegendA(data));
	});
			
	// 用车趋势
	$.getJSON(context + "/car/plmCarUse/allNumByMonthAjax", function(
			data) {
		// 接收参数
		$.AllNumByMonth("allNumByMonth", data);
	});
	
	
	// 各部门用车次数
	$.getJSON(context + "/car/plmCarUse/useNumAllByOfficeAjax", function(
			data) {
		// 接收参数
		$.UseNumAllByOfficeAjax("useNumAllByOfficeAjax", data);
	});
	
	// 车辆使用状态
	$.getJSON(context + "/car/plmCar/countByStatusAjax", function(
			data) {
		
		/*// 接收参数
		$.CountByStatusAjax3("countByStatusAjax", data);*/
		
		$.CountByStatusAjax4(data);
	});
	
	// 流程申请统计（按状态）
	$.getJSON(context + "/act/plmAct/countActByStatus", function(
			data) {
				
		// 接收参数
		$.CountActByStatus("countActByStatus", $.ToConvertLegendA(data),$.ToConvertSeriesA(data));
	});
	
	// 流程趋势  以天为时间轴
	$.getJSON(context + "/act/plmAct/countActByDate", function(
			data) {
				
		// 接收参数
		$.CountActByDate("countActByDay", data);
	});
	/*
	// 各流程各状态数量
	$.getJSON(context + "/act/plmAct/countActByStatusType", function(
			data) {
				
		// 接收参数
		$.CountActByStatusType("countActByStatusType", data[0],data[1]);
	});*/
	// 各流程数量（图表统计）
	$.getJSON(context + "/act/plmAct/countActByType", function(
			data) {
				
		// 接收参数
		$.CountActByType("countActByType", data);
	});
	
	$(".echartsAlarm-tab li").on("click", function() {
		$(".echartsAlarm-tab li").removeClass("active");
		 $(this).addClass("active");
		 if($(this).attr("id")=="dli"){
			 $("#dtable").show();
			 $("#jtable").hide();
			 $("#ctable").hide();
			 
		 }else if($(this).attr("id")=="jli"){
			 $("#dtable").hide();
			 $("#jtable").show();
			 $("#ctable").hide();
		 }else if($(this).attr("id")=="cli"){
			 $("#dtable").hide();
			 $("#jtable").hide();
			 $("#ctable").show();
		 }
		
	});
	
	function checkTime(i)
	{
	if (i<10) 
	  {i="0" + i}
	  return i
	}
	setInterval(function() {
	   
	    var today=new Date()
	  

	    //获取当前年
	   var year = today.getFullYear();

	   //获取当前月
	   var month = today.getMonth() + 1;
	   //获取当前日
	   var date = today.getDate();
	   
	    var h=today.getHours()
	    var m=today.getMinutes()
	    var s=today.getSeconds()
	    // add a zero in front of numbers<10
	    m=checkTime(m)
	    s=checkTime(s)
	    var time = h+":"+m+":"+s
	    var date = year+"年<span>"+month+"</span>月<span>"+date+"</span>日";
	    $('.timespan').text(time);
	    $('.datespan').html(date);
	}, 1000);
	
	 
   
	
	
   
	
	$(window).resize(function(){
		window.location.reload()
	})
	
})

 
