<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>地图</title>
<script type="text/javascript">
	var ctx = '${ctx}', ctxStatic = '${ctxStatic}';
</script>
<link rel="stylesheet" href="${ctxStatic}/modules/map/css/map.css" type="text/css">
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/ol/ol.js"></script>
<script src="${ctxStatic}/modules/map/js/commonMap.js"></script>
<script src="${ctxStatic}/modules/map/js/mapconfig.js"></script>
<script src="${ctxStatic}/ccm/event/js/mapAlarmLog.js"></script>
<!--  
<script src="${ctxStatic}/modules/map/js/draw/js/app.js"></script>-->

</head>
<body onload="init()">
	<input type="hidden" value="${ccmAlarmLog.objId}" id="objId">
	<input type="hidden" id="createDate" value="<fmt:formatDate value="${ccmAlarmLog.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
	<div id="content" class="row-fluid">
		<div id="map" class="map" tabindex="0">
			<div id="layerControl" class="layerControl" style="display: none">
				<ul id="layerTree" class="layerTree"></ul>
			</div>
		</div>
	</div>
	<!--  工具栏-->
	<div id="tool" class="row-fluid" style="width:200px;right:150px;">
		<div class="span4  tool-list" onclick="Map.switchMap()" id="switchMap" style="padding:0px 8px ;background: #FAFAFA;border-radius: 5px;box-shadow: 3px 3px 2px rgba(0, 0, 0, .25);">
			<i class="tool-icon tool-map"></i><span>切换</span>
		</div>
		<div class="span6  tool-list" onclick="" >
			<button id="start-animation" style="margin-left:10px;margin-top:3px"> 开始</button>
		</div>
	</div>
	
	
</body>
</html>