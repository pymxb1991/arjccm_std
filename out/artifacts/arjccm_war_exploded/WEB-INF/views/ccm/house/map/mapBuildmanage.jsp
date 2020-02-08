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
<link rel="stylesheet" href="${ctxStatic}/ol/ol.css" type="text/css">
<link rel="stylesheet" href="${ctxStatic}/modules/map/css/map.css"
	type="text/css">
<link rel="stylesheet" href="${ctxStatic}/modules/map/css/house.css"
	type="text/css">
<link href="${ctxStatic}/ccm/house/map/css/ccmMapTree.css"
	rel="stylesheet" type="text/css" />
<link
	href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="${ctxStatic}/modules/map/js/draw/css/defaults.css"
	type="text/css">
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css"
	rel="stylesheet" />
<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js"
	type="text/javascript"></script>
<script
	src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.js"
	type="text/javascript"></script>
<script src="${ctxStatic}/ccm/sys/js/GISCommon.js"></script>
<script src="${ctxStatic}/ol/ol.js"></script>
<script src="${ctxStatic}/modules/map/js/commonMap.js"></script>
<script src="${ctxStatic}/modules/map/js/mapconfig.js"></script>
<script src="${ctxStatic}/modules/map/js/draw/js/app.js"></script>
<script src="${ctxStatic}/jscolor.js"></script>
<style>
#toolCenter {
    width: 350px;
}
.row-fluid .span3.tool-list{
margin-left: 2px}
.tool-gap {
    left: 6px;
}
.ztree{
overflow: auto}
#toolCenter {
	width: 350px;
}

.tab-item {
	display: none
}

.tab-item.active {
	display: block;
}

.tab-title li {
	cursor: pointer;
	height: 13%;
	text-align: center;
	overflow: hidden;
}

.tab-title li.active {
	background: #fff;
}

#contentLeft {
	background: #f0f3f4 !important;
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

.tab-title li.active .nav-name, .tab-title li.active .nav-icon {
	color: #44a5ff;
}

.input-medium.Wdate, .input-medium {
	width: 158px;
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
	height: 100px;
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
    top: 120px;
    width: 350px;
    height: calc(100% - 64px - 57px - 25px);
    border: 1px solid #ccc;
    background-color: #fff;
    opacity: 0.9;
    overflow: auto;
    border-radius: 8px;
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
#primaryPersonName{
height:30px;
width:113px;
}
.input-append {
   margin-bottom: 0px;
}
.map-lend{
    border-radius: 8px;
    background-color: #fff;
    border: 1px solid #ccc;
    height: 146px;
    position: absolute;
    bottom: 19px;
    left: 380px;
    z-index: 199202;
}
.map-lend-head{
  text-align: center;
  line-height: 22px;
  border-bottom: 1px solid #ccc;
}
.fourColorsLegend{
     width: 100%;
    line-height: 26px;
}
.color-bg {
    width: 30px;
    height: 20px;
    float: left;
    margin-top: 2px;
    margin-right: 5px;
}
.color-bg1{
  background: #ffc143;
}
.color-bg2 {
    background: #ffc858;
}
.color-bg3 {
    background: #ffdd95;
}
.color-bg4 {
    background: #ffe3aa;
}
.datalist{
 height:100%;
}
#treeType{
width: 158px!important;
 margin-left: 0px;
}
#secuPlace{
 width: 113px!important;
 width: 113px; 
 height: 30px; 
 margin-left: 6px;
 margin-bottom: 0;
}
.ol-custom-overviewmap{
	bottom: auto;
	left: auto;
	right: 0;
	top: 60px;
}
</style>
<%--<script src="${ctxStatic}/ccm/house/js/mapBuildmanage.js"></script> --%>

</head>
<body onload="">
	<div id="content" class="row-fluid" style="height:100%;width:100%">
		 
		<div id="right">
 <div id="showMapKey" style="width: 0px; height: 0px; display: none;">
			<img src="${ctxStatic}/images/GIS_list.png">
		</div>
       <div id="mapKey">
			<div class="mapView">
					<p id="mapView-p">地图标注<span style="float: right; font-size: 20px; padding-right: 10px; color: #FFF; cursor: pointer;">×</span></p>
					<div class="mapView-p-head">
						<div class="layui-tab-content">
							<ul class="ul-form"  style="margin-top:10px;">
		                     <select id="treeType" name="treeType" placeholder="请选择类型 " class="input-medium" style="margin-bottom:0">
								<option value="" class="select-option-bg">全部信息</option>
								<%--<option value="build">楼栋</option>--%>
								<option value="commonality">公共机构</option>
								<option value="npse">重点及风险单位</option>
								<option value="school">学校</option>
								<option value="vccmorg">综治机构</option>
								<option value="citycomponents">城市部件</option>
								<option value="land">土地</option>
								<option value="camera">视频监控</option>
								<option value="appEfence">app电子围栏</option>
								<option value="basePlace">场所</option>
						    </select>
						    <div class="input-append">
						    <input id="secuPlace" name="secuPlace" class="input-medium"
								type="text" value="" maxlength="100" >
							<a id="areaButton" href="javascript:" class="btn" style="border-radius: 0 14px 14px 0;display: inline-block;width: 24px!important;">&nbsp;<i
								class="icon-search"></i>
							</a>
                     </div>

								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="map_main">
			<div style="padding:8px;height:97%;">
			<div style="height:100%;" id="IncidentList">
			    <ul id="assetTree" class="ztree" style="margin-top: 10px;"></ul>
			</div>
			</div>
			</div>
		</div>
			<!-- 中间内容 -->
			<!--  工具栏-->
			<div id="tool">
				<div id="toolCenter">
					<div class="row-fluid tool-container">
						<div class="span3 tool-list" onclick="HasChildren()" style="width:16%">
							<i class="tool-icon tool-flag" ></i><span>标注</span>
							<b class="tool-gap"></b>
						</div>
						<div class="span3  tool-list" id="editTool"  onclick="Map.editGraphical(true)" style="width:16%">
							<i class="tool-icon tool-edit"></i><span>编辑</span>
							<b class="tool-gap"></b>
						</div>
						<div class="span3  tool-list" onclick="Map.removeGraphical()" style="width:16%">
							<i class="tool-icon tool-remove"></i><span>删除</span> <b
								class="tool-gap"></b>
						</div>
						<div class="span3 tool-list" onclick="paint()" style="width:16%">
							<i class="tool-icon tool-flag" ></i><span>着色</span>
							<b class="tool-gap"></b>
						</div>
						<div class="span3  tool-list" onclick="Map.switchMap()" id="switchMap" style="width:16%">
							<i class="tool-icon tool-map"></i><span>切换</span>
							<b class="tool-gap"></b>
						</div>
						<div class="span3  tool-list" onclick="Map.saveGraphical()" style="width:16%">
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
				
				<div class="tag-panl-paint" id="TagPanlPaint">
					<div class="row-fluid tag-panl-header">
						<div class="span10">
							<span style="margin-left: 5px">着色</span>
						</div>
						<div class="span2 tag-panl-paint-close">
							<i class="icon-remove"></i>
						</div>
					</div>
					<div class="row-fluid tag-panl-center">
						<div>
							颜色: <input id="a_color" name="a_color"  class="jscolor" value="ab2567">
						</div>
						<div>
							透明度：<input id="a_opacity" name="a_opacity" type="text" value="0.6" maxlength="3" >
						</div>
						<div class="span4  tool-list" onclick="Map.previewArea($('#a_color').val(), $('#a_opacity').val())" style="width:46%">
							<i class="tool-icon tool-save"></i><span>预览</span>
						</div>
						<div class="span4  tool-list" onclick="Map.saveAreaColor($('#a_color').val(), $('#a_opacity').val())" style="width:46%">
							<i class="tool-icon tool-save"></i><span>保存</span>
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

	<script src="${ctxStatic}/ccm/house/map/js/ccmMapTree.js"
		type="text/javascript"></script>
		<script src="${ctxStatic}/common/jsplit.js" type="text/javascript"></script>
	<script type="text/javascript">
		/* 布局 */
		var leftWidth = 280; // 左侧窗口大小
		var tabTitleHeight = 33; // 页签的高度
		var htmlObj = $("html"), mainObj = $("#main");
		var headerObj = $("#header"), footerObj = $("#footer");
		var frameObj = $("#left, #right, #right iframe");

		function wSize() {
			var minHeight = 500, minWidth = 980;
			var strs = getWindowSize().toString().split(",");
			htmlObj.css({
				"overflow-x" : strs[1] < minWidth ? "auto" : "hidden",
				"overflow-y" : strs[0] < minHeight ? "auto" : "hidden"
			});
			mainObj.css("width", strs[1] < minWidth ? minWidth - 10 : "auto");
			//frameObj.height((strs[0] < minHeight ? minHeight : strs[0]) - headerObj.height() - footerObj.height() - (strs[1] < minWidth ? 42 : 28));
			frameObj.height((strs[0] < minHeight ? minHeight : strs[0])
					- headerObj.height() - footerObj.height()
					- (strs[1] < minWidth ? 30 : 0));
			$(".jericho_tab iframe").height(
					$("#right").height() - tabTitleHeight); 
			wSizeWidth();
			$(".ztree").height($('.map_main').height() - 50);
		}
		function wSizeWidth() {
			$("#right").width("100%");
		}


		$(function(){
			init();
		})
</script>
<!-- 左侧导航栏双击隐藏显示 -->
<script src="${ctxStatic}/common/wsizeDb.js" type="text/javascript"></script>
</body>
</html>