/**
 * */

var map,Map;

var deviceId = "";
var deviceCode = "";

function init(){
	// 地图默认数据设置
	var defaultPrams = {
		id : 'map',
		centerCoordinate : centerCoordinate,
		zoom : zoomIndex,
		maxZoom : 18,//最大就是18,再大就会提示没有图片
		minZoom : 1,//最小就是1,再小就会提示没有图片
		baseUrl : baseUrlT,
		zoomShowOrHide : false,
		overviewMap : true
	}
	Map = new ArjMap.Map(defaultPrams);
	Map.init();//加载地图
	map = Map.map;
}
$(function() {
	$("#btnExport").click(
		function() {
			if(deviceId == null || deviceId == "" || deviceId === undefined){
				$.jBox.alert("请选择设备","警告");
				return;
			}
			var beginDate = $("#beginDate").val();
			var endDate = $("#endDate").val();
			if(beginDate == null || beginDate == "" || beginDate === undefined || endDate == null || endDate == "" || endDate === undefined){
				$.jBox.alert("请选择查询时间段","警告");
				return;
			}
			var beginDateNew = Date.parse(new Date(beginDate.replace(/-/g,"/")));
			var endDateNew = Date.parse(new Date(endDate.replace(/-/g,"/")));
			var time = endDateNew - beginDateNew;
			if(time > 24*60*60*1000){
				$.jBox.alert("时间差必须小于一天","警告");
				return;
			}
			top.$.jBox.confirm("确认要导出轨迹数据吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					var obj = {};
					obj.deviceId = deviceId;
					obj.devCode = deviceCode;
					obj.beginDate = beginDate;
					obj.endDate = endDate;
					var params = $.param(obj);
					document.location.href = ctx + "/data/spsTrajectoryPoint/export?" + params;
				}
			}, {
				buttonsFocus : 1
			});
			top.$('.jbox-body .jbox-icon').css('top', '55px');
		}
	);
});