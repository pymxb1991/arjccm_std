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
.checkPoint {
	height: 300px;
}

#FullBody {
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
#toolCenter {
    width: 170px;
    }
</style>
<div id="FullBody">
	<div id="map" class="map" >
		<!--  工具栏-->
		<div id="tool">
			<div id="toolCenter">
				<div class="row-fluid tool-container">
					<div class="span4 tool-list tool-list-sign" onclick="Map.addPlanFlag()">
					<i class="tool-icon tool-sign"></i><span>标选</span> <b
						class="tool-gap"></b>
				</div>
				<div class="span4 tool-list tool-list-remove" onclick="Map.rePlanFlag()">
					<i class="tool-icon tool-remove"></i><span>删除</span>
					<b class="tool-gap"></b>
				</div>
				<div class="span4  tool-list" onclick="Map.fullScreen()" id="fullScreen">
					<i class="tool-icon tool-full"></i><span>全屏</span>
				</div>
				</div>
			</div>
		</div>
		<!--  工具栏-->
	</div>
</div>
<script type="text/javascript"
    src="${ctxStatic}/ccm/patrol/js/pointCheck.js"></script>
