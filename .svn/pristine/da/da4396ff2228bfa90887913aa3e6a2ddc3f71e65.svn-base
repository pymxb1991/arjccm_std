$(function(){
	//浮动工具栏
	var isDrag = false;  //声明拖动的默认状态是：否
	$('#floatingLayer').draggable({ containment: "parent" });
	var floatingLayerFlag=true;
	$('#toolMenu').draggable({
		containment: "parent" ,
		stop: function(e) {
		    isDrag = true;
		}
	});
	$("#toolbar div span").click(function () {
	    if(!isDrag){
		    $("#toolbar div").removeClass('conversion-color');
		    $(this).parent('div').addClass('conversion-color');
	    }
	});
	$("#floatingLayer").click(function () {
		if(!isDrag){
			var offset = $("#floatingLayer").offset();
	        $("#toolMenu").css("top", offset.top - 300);
	        $("#toolMenu").css("left", offset.left-300);
	        $(this).parent().children('#toolMenu').toggleClass('active');
	        $(this).hide();
		}
		isDrag = false;
    });
    $("#map .return-map span").click(function () {
    	if(!isDrag){
    		var _this = $(this).parents('.wrapper');
            _this.children('.left-side').show();
            _this.children('.right-side').show();
            $(this).parents('.map').removeClass('none-map');
            $(this).parents('.toolbar').children('.all-map').show();
            $(this).parents('.toolbar').children('.all-map').addClass('conversion-color');
            $(this).parent('.return-map').hide();
    	}
    	isDrag = false;
    });
    
    $('.box .hd span').click(function () {
        var _this = $(this).parent().parent();
        _this.children('.bd').toggle();
        $(this).toggleClass('toggle-up');
        $(this).toggleClass('toggle');
    });
    $("#range span").click(function () {
    	if(!isDrag){
    		var _this = $(this).parents('#toolMenu').children('.inner-menu').children('.range-query');
	        $(this).parents('#toolMenu').children('.inner-menu').children('.toolbars,.plottingbar').removeClass('active');
	        _this.toggleClass("active");
	        $(this).toggleClass("triangle-down");
	        $(this).toggleClass("triangle-up");
    	}
    	isDrag = false;
    });
    $("#plotting span").click(function () {
    	if(!isDrag){
	        var _this = $(this).parents('#toolMenu').children('.inner-menu').children('.plottingbar');
	        $(this).parents('#toolMenu').children('.inner-menu').children('.toolbars,.range-query').removeClass('active');
	        _this.toggleClass("active");
	        $(this).toggleClass("triangle-down");
	        $(this).toggleClass("triangle-up");
    	}
    	isDrag = false;
    });
   
    $(".Tools span").click(function () {
    	if(!isDrag){
	        var _this = $(this).parents('#toolMenu').children('.inner-menu').children('.toolbars');
	        $(this).parents('#toolMenu').children('.inner-menu').children('.plottingbar,.range-query').removeClass('active');
	        _this.toggleClass("active");
	        $(this).toggleClass("triangle-down");
	        $(this).toggleClass("triangle-up");
    	}
    	isDrag = false;
    });
    $("#turn-off").click(function (e) {
    	if(!isDrag){
	        $(this).parents('#toolMenu').removeClass('active');
	        $(this).parent().children('div').removeClass('conversion-color');
	        $(this).parents('#toolMenu').children().children().removeClass('active');
	        $("#floatingLayer").show();
    	}
    	isDrag = false;
    });
    $("#map .all-map span").click(function () {
    	if(!isDrag){
	        var _this = $(this).parents('.wrapper');
	        _this.children('.left-side').hide();
	        _this.children('.right-side').hide();
	        $(this).parents('.map').addClass('none-map');
	        $(this).parents('.toolbar').children('.return-map').show();
	        $(this).parents('.toolbar').children('.return-map').addClass('conversion-color');
	        $(this).parent('.all-map').hide();
    	}
    	isDrag = false;
    });
})

//指定标绘类型，开始绘制。
function plotDrawInit(){
	// 初始化标绘绘制工具，添加绘制结束事件响应
	plotDraw = new P.PlotDraw(map);
	plotDraw.on(P.Event.PlotDrawEvent.DRAW_END, onDrawEnd, false, this);

    // 设置标绘符号显示的默认样式
    var stroke = new ol.style.Stroke({
        color: '#FF0000',
        width: 2
    });
    var fill = new ol.style.Fill({color: 'rgba(0,255,0,0.4)'});
    var image = new ol.style.Circle({fill: fill, stroke: stroke, radius: 8});
    drawStyle = new ol.style.Style({image: image, fill:fill, stroke:stroke});

    // 绘制好的标绘符号，添加到FeatureOverlay显示。
    drawOverlay = new ol.layer.Vector({
        source: new ol.source.Vector()
    });
    drawOverlay.setStyle(drawStyle);
    drawOverlay.setMap(map);
    
    //测距初始化
    Map.measureMapInit();
	// 框选查询初始化
	Map.selectQueryInit();
}

function activate(type){
	Map.map.getOverlays().clear();
    plotDraw.activate(type);
}
function onDrawEnd(event){
	var feature = event.feature;
	drawOverlay.getSource().addFeature(feature);
}
function clearAllGraphic(){
	Map.measureMapClear();//清楚测绘
    drawOverlay.getSource().clear();//清除标绘
	Map.removeLayer('videos');//清除范围图层
	Map.removeLayer('jingyuan');
	Map.removeLayer('jingche');
	Map.drawVector.getSource().clear();//清除圈选查询
    Map.removeLayer('riceDrawVector');//清除范围图层
	//Map.removeLayer('alarm');//清除
	$('#policeResult').html('');//清除警力结果
	$('#carResult').html('');//清除警车结果
	$('#videoResult').html('');//清除视频结果	
}
//圈选查询
function boxSelectionDevice(data){
	var param={
		'id':'',
		'x':data.centerX,
		'y':data.centerY,
		'radius':data.radius,
		"type": "1,1,1"
	};
	nearSearchQuery(param);
}