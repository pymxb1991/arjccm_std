/**
 * Created by oHa on 2017/12/28.
 */
var windowsHeight, _fontSize = 14, _fontSize1 = 26, breakData = 8;
legendTop = '30%', radiusData = [ 90, 65 ], lengthECharts = 30,
		mapHeigth = '90%';
// 基础颜色表
var color = [ '#D87A82', '#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d',
		'#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f' ];
$(function() {

	//
	var context = $(".context").attr("content");

	$(window).resize(function() {
		window.location.reload()
	})
	//
	$('#main').height($(window).height());
	$('.height100').height($('#main').height() - 80);
	windowsHeight = $(window).width();
	if (windowsHeight > 1600) {
		_fontSize = 14;
		legendTop = '20%';
		legendRight = '8%';
		radiusData = [ 90, 65 ];
		lengthECharts = 30;
		_fontSize1 = 26;
		breakData = 8;
		mapHeigth = '90%'
	} else {
		_fontSize = 10;
		legendTop = '15%';
		legendRight = '0%';
		radiusData = [ 60, 45 ];
		lengthECharts = 20;
		_fontSize1 = 14;
		breakData = 6;
		mapHeigth = '90%'
	}

	// 地图
	map();
	// 地图
	function map() {
		// 地图默认数据设置
		var defaultPrams = {
			id : 'map',
			centerCoordinate : centerCoordinate,
			zoom : '12.7',
			maxZoom : 20,
			minZoom : 9.8,
			baseUrl : baseUrlT,
			zoomShowOrHide : false,// 缩小放大
			overviewMap : false,
			selectPointerFlag : true
		// 鹰眼图
		}
		Map = new ArjMap.Map(defaultPrams);
		// 加载地图
		Map.init();
		
		$.getJSON(context + "/sys/map/orgAreaMap?type=1", function(data) {
			Map.addJSON1([ {
				'type' : 'communitys',
				'data' : data,
				'isShow' : true
			} ])
	});
		
		
		
		
	}
})
