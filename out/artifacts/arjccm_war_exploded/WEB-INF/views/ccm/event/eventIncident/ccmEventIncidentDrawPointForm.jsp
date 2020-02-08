<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>地图标注</title>
	<meta name="decorator" content="default" />


	<link rel="stylesheet" href="${ctxStatic}/ol/ol.css" type="text/css">
	<link
			href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css"
			rel="stylesheet" type="text/css" />
	<!--
<link rel="stylesheet"
	href="${ctxStatic}/ccm/liveVideo/css/livedemo.css">
<link rel="stylesheet"
	href="${ctxStatic}/ccm/liveVideo/css/video-js.css">
<link rel="stylesheet"
	href="${ctxStatic}/modules/map/js/draw/css/p-ol3.min.css"
	type="text/css">-->
	<link rel="stylesheet"
		  href="${ctxStatic}/modules/map/js/draw/css/defaults.css"
		  type="text/css">
	<link rel="stylesheet" href="${ctxStatic}/modules/map/css/map.css"
		  type="text/css">
	<link rel="stylesheet" href="${ctxStatic}/modules/map/css/house.css"
		  type="text/css">
	<!-- <script src="${ctxStatic}/jquery/jquery-1.8.3.min.js"></script>
<script src="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.js"
	type="text/javascript"></script>-->
	<script src="${ctxStatic}/jquery-validation/1.11.1/lib/jquery.form.js" type="text/javascript"></script>
	<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css"
		  rel="stylesheet" />

	<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js"
			type="text/javascript"></script>
	<script src="${ctxStatic}/ol/ol.js"></script>

	<script src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.min.js"
			type="text/javascript"></script>
	<script
			src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.js"
			type="text/javascript"></script>
	<script src="${ctxStatic}/modules/map/js/draw/js/p-ol3.debug.js"></script>

	<link href="${ctxStatic}/modules/map/css/pop-info-animate.css"
		  rel="stylesheet" />
	<link href="${ctxStatic}/layer-v3.1.1/layer/theme/default/layer.css"
		  rel="stylesheet" />
	<link href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet">
	<link rel="stylesheet"
		  href="${ctxStatic}/modules/map/css/publicinstitutions.css">
	<script src="${ctxStatic}/layer-v3.1.1/layer/layer.js"></script>
	<link href="${ctxStatic}/layim/layui/css/layui.css" type="text/css" rel="stylesheet">

	<link href="${ctxStatic}/form/css/form.css" rel="stylesheet" />
	<link href="${ctxStatic}/form/css/formTable.css" rel="stylesheet" />
	<!--
<script src="${ctxStatic}/layim/layui/layui.js"></script>
<script src="${ctxStatic}/modules/map/js/mapconfig.js"></script>
<script src="${ctxStatic}/modules/map/js/commonMap.js"></script>
<script src="${ctxStatic}/common/common.js"></script>
<script src="${ctxStatic}/modules/map/js/draw/js/appIndex.js"></script>
<script src="${ctxStatic}/modules/map/js/QianXi.js"></script>
<script src="${ctxStatic}/ccm/sys/js/GISDataIndex.js"></script>
<script src="${ctxStatic}/ccm/sys/js/ccmElectronicFenceFormMap.js"></script>
 -->
	<script src="${ctxStatic}/ccm/sys/js/GISCommon.js"></script>
	<script src="${ctxStatic}/ol/ol.js"></script>
	<script src="${ctxStatic}/modules/map/js/commonMap.js"></script>
	<script src="${ctxStatic}/modules/map/js/mapconfig.js"></script>
	<script src="${ctxStatic}/modules/map/js/draw/js/app.js"></script>



	<style>
		#toolCenter {
			width: 250px;
		}
		.row-fluid .span3.tool-list{
			margin-left: 2px
		}
		.tool-gap {
			left: 6px;
		}

		#left.mapIndex {
			background: #fff !important;
		}

		.nav-icon {
			margin: auto;
			margin-right: 2px;
		}

		.nav-name {
			display: block;
			text-align: center;
			margin-top: -10px;
			color: #a0a0a1;
		}

		.ul-form label {
			margin-left: 22px;
		}

		.firstbtn {
			margin-left: 6px;
		}

		#mapKey {
			z-index: 1992;
			display: block;
			opacity: 1;
		}

		.mapView {
			position: absolute;
			z-index: 199202;
			top: 10px;
			left: 14px;
			width: 350px;
			height: 160px;
			border-radius: 8px;
			background-color: #fff;
			border: 1px solid #ccc;
		}

		.mapView p {
			margin: 0px;
			color: #FFF;
			background-color: #1491FF;
			text-align: center;
			line-height: 30px;
			font-weight: bold;
		}

		.mapView .mapView-p-head {
			position: absolute;
			width: 100%;
			background-color: #fff;
		}

		.mapView .mapView-p-head>.head_first {
			width: 300px;
		}

		.mapView .mapView-p-head>.head_second {
			position: absolute;
			z-index: 1;
			margin-top: -3px;
			width: 300px;
		}

		.form-control {
			outline: none !important;
			box-shadow: none !important;
		}

		.btn.green:not (.btn-outline ) {
			color: #fff;
			background-color: #32c5d2;
			border-color: #32c5d2;
		}

		.mapListTop {
			position: absolute;
			z-index: 199202;
			top: 180px;
			left: 14px;
			width: 340px;
			height: 30px;
			border: 1px solid #ccc;
			border-top-right-radius: 8px;
			border-top-left-radius: 8px;
			padding-top: 7px;
			padding-left: 10px;
			background-color: #fff;
			font-size: 12px;
			opacity: 0.9;
			overflow: hidden;
		}

		.map_main {
			position: absolute;
			z-index: 199202;
			left: 14px;
			top: 217px;
			width: 350px;
			height: calc(100% - 190px - 57px - 25px);
			border: 1px solid #ccc;
			background-color: #fff;
			opacity: 0.9;
			overflow: auto;
			border-bottom-right-radius: 8px;
			border-bottom-left-radius: 8px;
		}

		.map_list_data {
			min-height: 85px;
			border-bottom: 1px solid #e5e5e5;
			font-size: 12px;
			color: #8c8c8c;
			cursor: pointer;
		}

		.col-center {
			margin-right: 90px;
			margin-left: 30px !important;
		}

		.col-center .col-row {
			text-align: left;
			padding: 3px 3px;
			line-height: 1.2;
			min-height: 25px;
			color: #666667;
			box-sizing: border-box;
		}
		.col-left {
			float: left;
		}
		.col-right {
			float: right;
			width: 75px;
			margin: 10px 10px;
			text-align: right;
		}


		#showMapKey {
			position: absolute;
			z-index: 2;
			top: 10px;
			left: 14px;
			width: 40px;
			height: 32px;
			background-color: #fff;
			color: #fff;
			cursor: pointer;
			border-radius: 5px;
			line-height: 32px;
			text-align: center;
			box-sizing: border-box;
			box-shadow: 1px 2px 1px rgba(0, 0, 0, .15);
		}

		#showMapKey img {
			margin-top: 4px;
		}

		.layui-tab-title li {
			padding: 0 5px;
		}

		.layui-tab-title li {
			min-width: 60px;
		}

		.layui-tab {
			margin: 0px;
		}

		.layui-tab-content {
			padding: 0 10px;
		}

		.col-center .col-row .n-blue {
			color: #1972c1;
			text-decoration: none;
			outline: 0;
			cursor: pointer;
			font-weight: bold;
		}
		.map_list_data:hover {
			background-color: #efefef;
		}
		.mainBottom{
			position: absolute;
			z-index: 199301;
			left: 14px;
			bottom: 25px;
			width: 350px;
			height: 30px;
			border: 1px solid #ccc;
			border-bottom-right-radius: 8px;
			border-bottom-left-radius: 8px;
			background-color: #fff;
			font-size: 12px;
			opacity: 0.9;
			overflow: hidden;
		}
		.layui-laypage {
			margin: 0;

		}
	</style>
</head>
<body>
<div class="layui-fluid" style="border-color: red;">
	<div class="layui-row">

		<div class="layui-col-sm12" style="height: 890px;">
			<!-- 地图布局 -->
			<div id="tool">
				<div id="toolCenter">
					<div class="row-fluid tool-container">
						<div class="span3 tool-list" onclick="HasChildren()" style="width:19%">
							<i class="tool-icon tool-flag" ></i><span>标注</span>
							<b class="tool-gap"></b>
						</div>
						<div id="editTool" class="span3  tool-list"  onclick="Map.editGraphical(true)" style="width:19%">
							<i class="tool-icon tool-edit"></i><span>编辑</span>
							<b class="tool-gap"></b>
						</div>
						<div class="span3  tool-list" onclick="Map.removeGraphical()" style="width:19%">
							<i class="tool-icon tool-remove"></i><span>删除</span> <b
								class="tool-gap"></b>
						</div>
						<div class="span3  tool-list" onclick="Map.switchMap()" id="switchMap" style="width:19%">
							<i class="tool-icon tool-map"></i><span>切换</span>
							<b class="tool-gap"></b>
						</div>
						<div class="span3  tool-list" onclick="Map.saveGraphical()" style="width:19%">
							<i class="tool-icon tool-save"></i><span>保存</span>
						</div>
					</div>
				</div>
				<div class="tag-panl" id="TagPanl">
					<div class="row-fluid tag-panl-header">
						<div class="span10">
							<span style="margin-left: 5px">标注</span>
						</div>
						<div class="span2 tag-panl-close">
							<i class="icon-remove"></i>
						</div>
					</div>
					<div class="row-fluid tag-panl-center">
						<div class="span4">
								<span class="tag-panl-point tag-panl-span" title="点标注" onclick="drawMark('Point')">
								   <i class="tool-icon tool-point"></i>
								</span>

						</div>
						<div class="span4">
								<span  class="tag-panl-line tag-panl-span" title="线标注" onclick="drawMark('LineString')">
								<i class="tool-icon tool-line"></i>
								</span>

						</div>
						<div class="span4">
								<span  class="tag-panl-polygon tag-panl-span" title="面标注" onclick="drawMark('Polygon')">
								  <i class="tool-icon tool-polygon"></i>
								</span>

						</div>
					</div>
				</div>
			</div>
			<!--  工具栏-->
			<div class="ctxStatic" attr="${ctxStatic}" style="display: none"></div>
			<div id="map" class="map" tabindex="0">
				<div id="layerControl" class="layerControl" style="display: none">
					<div class="title">
						<label>图层列表</label>
					</div>
					<ul id="layerTree" class="layerTree"></ul>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="${ctxStatic}/ccm/event/js/ccmDrawPointForm.js"></script>
<script src="${ctxStatic}/common/jsplit.js" type="text/javascript"></script>
</body>
</html>