var Map,currPage=1,pageSize =10; //每页出现数量;
var pTypeObj={},placeTypeObj={},placeImportantObj={};
$(function(){
	//加载地图
	initMap();
    layui.use('element', function(){
		  var element = layui.element; //Tab的切换功能，
		  element.on('tab(allTab)', function(data){ });
		});
    $('#ClearSubmit').click(function(){
    	$('.input-medium').val('');
    	$('#primaryPersonName').val('');
    	$('#primaryPersonId').val('');
    	HideDiv();
    })
})

function initMap(){
	// 地图默认数据设置
	var defaultPrams = {
		id : 'map',
		centerCoordinate : centerCoordinate,
		zoom : zoomIndex,
		maxZoom : maxZoom,
		minZoom : minZoom,
		baseUrl : baseUrlT,
		zoomShowOrHide : false,// 缩小放大
		overviewMap : false,
		selectPointerFlag : true
	// 鹰眼图
	}
	Map = new ArjMap.Map(defaultPrams);
	// 加载地图
	Map.init();
	map=Map.map;
}
//历史轨迹
function HisTrackFun(_this) {
	var id = $(_this).attr('id')
	var _deviceId = $(_this).attr('deviceId')
    $('#detailsDialog').hide();
	$.getJSON(ctx+'/sys/map/deviceMobileTrace?deviceId=' + _deviceId + '',
			function(data) {
		if (data.returnFlag) {
			TableInit(data);
			$('#popup-closer').click();
		} else {
			top.$.jBox.tip('暂无轨迹信息');
			HideDiv();
		}
  })
}
//历史轨迹查询
function HisTrackFun1() {
	 $('#detailsDialog').hide();
   var beginCreateDate=$('#beginCreateDate').val();
   var endCreateDate=$('#endCreateDate').val();
   var uesrId=$('#primaryPersonId').val();
   if(uesrId==""){
	   top.$.jBox.tip("请选择人员");
	   return;
   }
   if(beginCreateDate==""){
	   top.$.jBox.tip("请选择开始时间");
	   return;
   }
   if(endCreateDate==""){
	   top.$.jBox.tip("请选择结束时间");
	   return;
   }
	$.getJSON(ctx+'/sys/map/deviceMobileTrace?beginCurDate=' + beginCreateDate + '&endCurDate='+endCreateDate+'&user.id='+uesrId,
			function(data) {
				if (data.returnFlag) {
					TableInit(data)
				} else {
					top.$.jBox.tip('暂无轨迹信息');
					HideDiv();
				}

			})

}
var routeCoords = [];
var trackReplayLen;
function TableInit(data) {
	var Data = data.result
	trackReplayLen = Data.length;
	var tableData = [];
	Map.removeLayer('tralayer1');//轨迹回放
    Map.removeLayer('tralayer2');//轨迹回放

	if (trackReplayLen == 0) {
		HideDiv();
		top.$.jBox.tip("暂无轨迹信息");
		dataTableInit(tableData)
		return;
	}
	if (trackReplayLen > 0) {
		var html = '';
		routeCoords=[];
		for (var i = 0; i < trackReplayLen; i++) {
			var areaPoint = Data[i].areaPoint;
			var value = [];
			value[0] = Number(areaPoint.split(',')[0]);
			value[1] = Number(areaPoint.split(',')[1]);
			tableData.push([ Data[i].user.name, value[0],value[1],Data[i].updateDate ])
			routeCoords.push(value)
		}
		dataTableInit(tableData)
		//轨迹回放
		Map.map.getView().setZoom(18);
        Map.trackReplayLine(routeCoords);
		
		ShowDiv();
		//页面下方table双击定位到地图设备
  		$('#contentTable tbody').on('dblclick','tr',function(){
  			  var trTdX=Number($(this).children('td').eq(1).text());
  			  var trTdY=Number($(this).children('td').eq(2).text());
  			Map.map.getView().setZoom(18);
  			  Map.goTo([trTdX,trTdY]);//定位
  			 Map.startLocation([trTdX,trTdY]);//轨迹起始位置
  		});
	}
}
function dataTableInit(tableData) {
	$('#TableCon').html('<table cellpadding="0" cellspacing="0" border="0" class="display table table-striped table-bordered table-condensed" id="contentTable"></table>');
	$('#contentTable').dataTable({
		"data" : tableData,
		"searching" : false,
		"bLengthChange" : false,
		"ordering" : false,// 排序
		"paging" : false, // 禁止分页
		"scrollY" : '156px',
		"scrollCollapse" : true,
		"columns" : [{
			"title" : "人员"
		}, {
			"title" : "经度"
		}, {
			"title" : "纬度"
		}, {
			"title" : "时间"
		} ]
	});
}

function ShowDiv() {
	$("#hideOrShowDiv").height(200);
	$("#showDiv").css({
		'bottom' : -30
	});
	$("#hideDiv").css({
		'bottom' : 200
	});
	$("#playBack").css({
		'bottom' : 200
	});
}
function HideDiv() {
	$("#hideOrShowDiv").height(0);
	$("#hideDiv").css({
		'bottom' : -30
	});
	$("#playBack").css({
		'bottom' : -30
	});
	$("#showDiv").css({
		'bottom' : 0
	});
	tableData = [];
	Map.removeLayer('tralayer1');//轨迹回放
    Map.removeLayer('tralayer2');//轨迹回放
	dataTableInit(tableData);
}
function selectRealTime(){
	if($('#realTime').is(':checked')){
		GetPopLocation()
		PopLocationTime = setInterval(function() // 开启循环：每秒出现一次提示框
		{
			GetPopLocation()
		}, 15000);
		
	}else{
		Map.clearOverlays();
		Map.removeLayer('PopLocation');
		clearInterval(PopLocationTime);
	}
}
function GetPopLocation() {
	Map.removeLayer('PopLocation');
	  if(Map.selectClick){
			Map.selectClick.getFeatures().clear();//清除选中
	  }
	$.getJSON('' + ctx + '/sys/map/deviceMobileMap',
			function(data) {
				Map.addJSON1([ {
					'type' : 'PopLocation',
					'data' : data,
					'isShow' : true 
				} ]);
			})

	Map.layersIsShow('PopLocation', true);
}