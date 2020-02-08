/**
 * */

var map,Map, plotDraw, plotEdit, drawOverlay, drawStyle;

var center = [ 117.648920, 39.034450 ];

function init(){
//滨海新区  centerCoordinate : [ 117.648920, 39.034450 ],
//津南区  centerCoordinate : [ 117.29900836944581,39.03772830963135],
	// 地图默认数据设置
	var defaultPrams = {
		id : 'map',
		centerCoordinate : centerCoordinate,
		zoom : zoomApp,
		maxZoom : 20,
		minZoom : 2,
		baseUrl :baseUrlT,
		zoomShowOrHide : false,// 缩小放大
		overviewMap : true
	// 鹰眼图
	}
    　Map = new ArjMap.Map(defaultPrams);
	// 加载地图
	Map.init();

    map=Map.map;
    
	//************************************************************热力图************************************************//
	
//    $.getJSON('' + ctx + '/sys/map/buildHeatMap', 
//			function(heatmapData) {
//   
//    	Map.heatMap(heatmapData);
//
//	})
//
//	var zNodes1 = [ {
//		id : "heatmap",
//		pId : 0,
//		name : "热力图",
//		open : true,
//		checked : true
//	}];
//	
//	
//	// ztree
//	var setting1 = {
//		check : {
//			enable : true
//		},
//		data : {
//			simpleData : {
//				enable : true
//			}
//		},
//	callback : {
//		onCheck : getCheckedNodes
//	} 
//	};
//
//	$.fn.zTree.init($("#heatmapTree"), setting1, zNodes1);
//	
//	
//
//	function getCheckedNodes() {
//		var checked = "";
//		var zTree = $.fn.zTree.getZTreeObj("heatmapTree");
//		var checkedNodes = zTree.getCheckedNodes(true);
//		if (checkedNodes.length != 0) {
//			 Map.layersIsShow('heatMap',true);
//			
//		}else{
//			 Map.layersIsShow('heatMap',false);
//		}
//	}
	
	
	/*工具栏*/
    $('.tag-panl-span').click(function(){
    	$(this).addClass('active').parent().siblings().children('.tag-panl-span').removeClass('active');
    })
    $('.tag-panl-close').click(function(){
    	$('#TagPanl').hide();
    })
    
    $('.tag-panl-paint-close').click(function(){
    	$(this).addClass('active').parent().siblings().children('.tag-panl-paint-close').removeClass('active');
    })
    $('.tag-panl-paint-close').click(function(){
    	$('#TagPanlPaint').hide();
    })
    /*工具栏*/
    
    
    /*轨迹回放*/
     var routeCoords = [[117.63434886932373, 39.02674198150635],[117.64155864715576, 39.02519702911377],[117.6436185836792, 39.031291007995605],[117.64919757843018, 39.029831886291504],[117.64722347259521, 39.024338722229004],[117.64430522918701, 39.01712894439697],[117.65160083770752, 39.015069007873535],[117.65400409698486, 39.02236461639404],[117.66035556793213, 39.02116298675537],[117.66318798065186, 39.021592140197754],[117.66456127166748, 39.022536277770996],[117.6659345626831, 39.02519702911377],[117.66602039337158, 39.026570320129395],[117.66602039337158, 39.028801918029785],[117.66610622406006, 39.031548500061035], [117.66619205474854, 39.041075706481934],[117.66747951507568, 39.04433727264404],[117.66747951507568, 39.04433727264404],[117.68576145172119, 39.03858661651611]];

   // Map.trackReplay('start-animation',5,routeCoords);
    /*轨迹回放*/
}

//标注绘制
function drawMark(type){
	Map.drawMark(type)
}

//工具栏
function HasChildren(){
	$('#TagPanl').show()
}
//工具栏--着色
function paint(){
	$('#TagPanlPaint').show()
}




