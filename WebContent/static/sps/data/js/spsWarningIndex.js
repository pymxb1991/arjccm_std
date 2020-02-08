/**区域查询
 * */
var PointData={};
$(function(){
	init();
	$("html").css("height","100%");
	$("body").css("height","100%");
	/* 工具栏 */
	$('.tag-panl-close').click(function() {
		$('.tag-panl').hide();
		$('.tag-panl-span').removeClass('active')
	})
	$('#DrawFlag').click(function() {
		$(this).toggleClass("active");
		LineBox()
	})
})
var map,Map;
var tableFirstTdMap = [];
var tableSecondTdMap = [];
function init(){
	// 地图默认数据设置
	var defaultPrams = {
		id : 'map',
		centerCoordinate : center,
		zoom : 15,
		maxZoom : 18,//最大就是18,再大就会提示没有图片
		minZoom : 1,//最小就是1,再小就会提示没有图片
		baseUrl : baseUrlT,
		zoomShowOrHide : false,
		overviewMap : false
	}
	Map = new ArjMap.Map(defaultPrams);
	Map.init();//加载地图
	map = Map.map;
	//Map.selectQueryInit();// 圈选查询初始化
}
var routeCoords=[],echartslayer;
var trackReplayLen;
function TableInit(data){
//	var Data=data.content
	var Data=data;
	trackReplayLen=Data.length;
	var tableData=[];
	Map.removeLayer('tralayer1');//轨迹回放
    Map.removeLayer('tralayer2');//轨迹回放
	
	if(trackReplayLen==0){
		HideDiv();
		$.jBox.tip("未查询到数据");
		dataTableInit(tableData)
		return;
	}
	if(trackReplayLen>0){
		var html='';
		routeCoords=[];
		for(var i=0;i<trackReplayLen;i++){
			tableData.push([Data[i].x,Data[i].y,Data[i].alarmDate])//第一列就是序号 之后依次是:x,y,h,s,timeOccurs
			routeCoords.push([Number(Data[i].x),Number(Data[i].y)])
		}
		dataTableInit(tableData)
//		Map.trackReplay(routeCoords);
		Map.trackReplayLine(routeCoords);
		ShowDiv();
		tableDataList();
		tableResultsShow();
		//页面下方table双击定位到地图设备
  		$('#contentTable tbody').on('click','tr',function(){
  			  var trTdX=Number($(this).children('td').eq(0).text());
  			  var trTdY=Number($(this).children('td').eq(1).text());
  			  Map.goTo([trTdX,trTdY]);//定位
  			 Map.startLocation([trTdX,trTdY]);//轨迹起始位置
  		});
	}
}
function dataTableInit(tableData){
	$('#TableCon').html('<table cellpadding="0" cellspacing="0" border="0" class="display table table-striped table-bordered table-condensed" id="contentTable"></table>');
    $('#contentTable').dataTable({
        "data": tableData,
        "searching" : false,
        "bLengthChange": false,
        "ordering":  false,// 排序
        "paging": false, // 禁止分页
        "scrollY": '156px',
        "scrollCollapse": true,
        "columns": [
            { "title": "经度" },
            { "title": "纬度" },
            { "title": "时间"}
        ]
    });
}
function add0(m){return m<10?'0'+m:m }
//当前时间
function timeChange(nowDateMS){
	var time = new Date(nowDateMS);
	var y = time.getFullYear();
	var m = time.getMonth()+1;
	var d = time.getDate();
	var h = time.getHours();
	var mm = time.getMinutes();
	var s = time.getSeconds();
	var now = y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
	return now;
}
//回放
function HisPlayBack(_this,id){
	//点击回放,获取到当前时间
	var nowDate = $(_this).parent().parent().children().eq(2).html();
	//将时间对象转换成时间戳对象
	var nowDateMS = new Date(nowDate).getTime();
	//当前时间两分钟前
	var beginDateTime = nowDateMS - 2*60*1000;
	//将时间戳对象转换成时间对象
	//开始时间
	var beginDate = timeChange(beginDateTime); 
	//当前时间两分钟后
	var endDateTime = nowDateMS + 2*60*1000;
	//将时间戳对象转换成时间对象
    var endDate = timeChange(endDateTime);//结束时间
	var CcmEarlyWarning={
		'idCard':id,
		'beginDate':beginDate,
		'endDate':endDate
	};
	$.post(ctx+"/warning/ccmEarlyWarning/getxylist",CcmEarlyWarning,function(data){
		TableInit(data);
	})
}

function  playBack(){
	var val = $('#speed').val();
	//Map.startAnimation()
	echartslayer.clear();
    echartsOption.series[1].effect.constantSpeed=val;
    echartsOption.series[1].effect.show=true;
    echartslayer.setChartOptions(echartsOption);
}
//获取table所有经、纬度
function tableDataList(){
	$('#contentTable tbody tr').each(function (){ 
		tableFirstTdMap.push($(this).children("td:first").text());
		tableSecondTdMap.push($(this).find("td:eq(1)").text());//获取table第二列数据
	}); 
}

//地图轨迹发生改变时table定位到相同行
function tableResultsShow(){
	var tableFirstTd = '117.6455278';
	var tableSecondTd = '39.0365361';
	for(var i = 0;i < tableFirstTdMap.length;i++){
		if(tableFirstTd == tableFirstTdMap[i]){
			if(tableSecondTd == tableSecondTdMap[i]){
				if(i > 0){
					$("table#contentTable tbody tr").eq(i-1).css('color','#ffffff')
				}
				$("table#contentTable tbody tr").eq(i).css('color','red')
				var shuliang = 149*6;
				$(".dataTables_scrollBody").animate({scrollTop:$("table#contentTable tbody tr").eq(i).offset().top - shuliang + "px"},1000);//1000是ms,也可以用slow代替
			}
		}
	}
}

function ShowDiv(){
	if(trackReplayLen == null || trackReplayLen === undefined || trackReplayLen == "" || trackReplayLen == 0){
		$.jBox.tip("没有数据","提示");
		return;
	} 
	$("#hideOrShowDiv").height(200);
	$("#showDiv").css({'bottom':-30});
	$("#hideDiv").css({'bottom':200});
	$("#playBack").css({'bottom':200});
}
function HideDiv(){
	$("#hideOrShowDiv").height(0);
	$("#hideDiv").css({'bottom':-30});
	$("#playBack").css({'bottom':-30});
	$("#showDiv").css({'bottom':0});
}