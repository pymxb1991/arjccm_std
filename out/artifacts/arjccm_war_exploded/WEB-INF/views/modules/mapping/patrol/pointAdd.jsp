<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<link rel="stylesheet" href="${ctxStatic}/ol/ol.css" type="text/css">
<link rel="stylesheet" href="${ctxStatic}/modules/map/css/map.css"
	type="text/css">
<script src="${ctxStatic}/ol/ol.js"></script>
<script src="${ctxStatic}/modules/map/js/mapconfig.js"></script>
<script src="${ctxStatic}/modules/map/js/commonMap.js"></script>
<script src="${ctxStatic}/modules/map/js/mapconfig.js"></script>
<style>
.addPoint{
height:300px;
}
#FullBody{
  width: 100%;
	height: 100%;
}
#map {
	width: 100%;
	height: 100%;
	position: relative;
}

.map {
	overflow: auto;
}


</style>
<div id="FullBody">
<div id="map" class="map">
	<!--  工具栏-->
	<div id="tool">
		<div id="toolCenter">
			<div class="row-fluid tool-container">
				<div class="span3 tool-list" onclick="drawMark('Point')">
					<i class="tool-icon tool-flag"></i><span>标点</span> <b
						class="tool-gap"></b>
				</div>
			<!-- 	<div class="span3  tool-list" onclick="Map.editGraphical(true)">
					<i class="tool-icon tool-edit"></i><span>编辑</span> <b
						class="tool-gap"></b>
				</div> -->
				<div class="span3  tool-list" onclick="Map.removeGraphicalPlan()">
					<i class="tool-icon tool-remove"></i><span>删除</span>
					<b class="tool-gap"></b>
				</div>
				<div class="span3  tool-list" onclick="Map.fullScreen()" id="fullScreen">
					<i class="tool-icon tool-full"></i><span>全屏</span>
				</div>
			</div>
		</div>
	</div>
	<!--  工具栏-->
</div>
</div>
<script>
	var Map, map;
	$(function() {
		
		
		// 地图默认数据设置
		var defaultPrams = {
			id : 'map',
			centerCoordinate :centerCoordinate,
			zoom : zoomIndex,
			maxZoom : 20,
			minZoom : 2,
			baseUrl :baseUrlT,
			zoomShowOrHide : false,// 缩小放大
			overviewMap : false
		// 鹰眼图
		};
		Map = new ArjMap.Map(defaultPrams);
		// 加载地图
		Map.init();
		map = Map.map; 
		var areaPoint=$('#areaPoint').val();
		if(areaPoint!=""){
			Map.addGraphicalPlan("Point",areaPoint.split(','))
		}
		//Map.geoStrDraw  点坐标
	})
	function drawMark(type) {
		Map.drawMark(type);
	}
</script>