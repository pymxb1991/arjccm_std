<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>地图</title>
<script type="text/javascript">
	var ctx = '${ctx}', ctxStatic = '${ctxStatic}';
</script>
<link rel="stylesheet" href="${ctxStatic}/ol/ol.css" type="text/css">
<link
	href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="${ctxStatic}/ccm/liveVideo/css/livedemo.css">
<link rel="stylesheet"
	href="${ctxStatic}/ccm/liveVideo/css/video-js.css">
<link rel="stylesheet"
	href="${ctxStatic}/modules/map/js/draw/css/p-ol3.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${ctxStatic}/modules/map/js/draw/css/defaults.css"
	type="text/css">
<link rel="stylesheet" href="${ctxStatic}/modules/map/css/map.css"
	type="text/css">
<link rel="stylesheet" href="${ctxStatic}/modules/map/css/house.css"
	type="text/css">
<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" href="${ctxStatic}/DataTables/css/jquery.dataTables.css">
<script src="${ctxStatic}/DataTables/js/jquery.dataTables.js" type="text/javascript"></script>
<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css"
	rel="stylesheet" />

<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js"
	type="text/javascript"></script>
<script src="${ctxStatic}/ol/ol.js"></script>

<%-- <script src="${ctxStatic}/ccm/liveVideo/js/video.min.js"></script>
<script src="${ctxStatic}/ccm/liveVideo/js/videojs5.flvjs.js"></script>
<script src="${ctxStatic}/ccm/liveVideo/js/videojs-contrib-hls.js"></script>
<script src="${ctxStatic}/ccm/liveVideo/js/videojs-ie8.min.js"></script>
<script src="${ctxStatic}/ccm/liveVideo/js/livedemo.js"></script>
<script type="text/javascript">
	videojs.options.flash.swf = "${ctxStatic}/ccm/liveVideo/js/video-js.swf";
</script> --%>

<script src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.min.js"
	type="text/javascript"></script>
<script
	src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.js"
	type="text/javascript"></script>
<script src="${ctxStatic}/modules/map/js/draw/js/p-ol3.debug.js"></script>
<script src="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.js"
	type="text/javascript"></script>
<link href="${ctxStatic}/modules/map/css/pop-info-animate.css"
	rel="stylesheet" />
<link href="${ctxStatic}/layer-v3.1.1/layer/theme/default/layer.css"
	rel="stylesheet" />
	<link href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet">
<link rel="stylesheet"
	href="${ctxStatic}/modules/map/css/publicinstitutions.css">
<script src="${ctxStatic}/layer-v3.1.1/layer/layer.js"></script>
<link href="${ctxStatic}/layim/layui/css/layui.css" type="text/css" rel="stylesheet">
<link href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet">
<script src="${ctxStatic}/layim/layui/layui.js"></script>
<script src="${ctxStatic}/modules/map/js/mapconfig.js"></script>
<script src="${ctxStatic}/modules/map/js/commonMap.js"></script>
<script src="${ctxStatic}/common/common.js"></script>
<script src="${ctxStatic}/modules/map/js/draw/js/appIndex.js"></script>
<script src="${ctxStatic}/modules/map/js/QianXi.js"></script>
<script src="${ctxStatic}/ccm/sys/js/APPTerminalIndex.js"></script>
<script src="${ctxStatic}/ccm/sys/js/APPTerminal.js"></script>

<style>
#toolCenter {
	width: 250px;
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
width: 113px;
}
.input-append {
   margin-bottom: 0px;
}
</style>

</head>
<body  style="overflow: hidden;" onload="init()">
    <div id="content" class="row-fluid">
     
     <div  id="right" style="overflow: hidden;">   
         <div id="showMapKey" style="width: 0px; height: 0px; display: none;">
			<img src="${ctxStatic}/images/GIS_list.png">
		</div>
       <div id="mapKey">
			<div class="mapView">
				<p id="mapView-p">APP终端<span style="float: right;font-size: 20px;padding-right: 10px;color: #FFF;cursor: pointer;">×</span></p>
				<div class="mapView-p-head">
				  <div class="layui-tab layui-tab-brief" lay-filter="allTab">
  <ul class="layui-tab-title" id="GIStab" >
    <li class="layui-this" type="event"><i class="nav-icon iconfont  icon-guijiguanli"></i>实时位置</li>
    <li  type="build"><i class="nav-icon iconfont  icon-dizhiku"></i>历史位置</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
      	<div class="checkbox checkbox-success radio-div tab-content-bg" style="display: inline-block;padding-top: 5px;padding-bottom: 5px;margin-left: 20px; margin-top: 10px;">
					<input type="checkbox" value="16" class="type-input" id="realTime" onchange="selectRealTime()"/>
					<label	for="realTime" style="padding:0;width:80px;" ><i class="nav-icon iconfont  icon-guijiguanli"></i>实时位置</label>
		</div>
    </div>
    <div class="layui-tab-item">
		<ul class="ul-form">
		    <li>
		     <input name="beginCreateDate" type="text" placeholder="请选择开始时间" 
									  id="beginCreateDate" maxlength="20"
									class="input-medium Wdate" value=""
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endCreateDate\')||\'%y-%M\'}'});" style="height: 30px; margin-top: 10px;"/>
			 <input name="endCreateDate" type="text"  placeholder="请选择结束时间" 
									  id="endCreateDate" maxlength="20" 
									class="input-medium Wdate" value=""
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginCreateDate\')}',maxDate:'%y-%M'});" style="height: 30px; margin-top: 10px;"/>						
		    </li>
		    <li>
		    <sys:treeselect id="primaryPerson" name="primaryPerson.id" 
									value="${office.primaryPerson.id}"
									labelName="office.primaryPerson.name"
									labelValue="${office.primaryPerson.name}" title="用户"
									url="/sys/office/treeData?type=3" allowClear="true"
									notAllowSelectParent="true" />
		  <a href="javascript:;" id="btnSubmitHis" onclick="HisTrackFun1()"  class="btn btn-primary firstbtn">
                <i class="icon-search"></i> 查询 </a>
                <a href="javascript:;" id="ClearSubmit" style="background: #f50b4b !important;" class="btn btn-primary" >
                <i class="icon-remove"></i> 清空 </a>
		    </li>
		</ul>
    </div>

  </div>
</div> 
				</div>
			</div>
		</div>

	<div id="FullBody" style="position: relative;">
	<div id="popup" class="ol-popup">
			<div class="">
				<a href="#" id="popup-closer" class="ol-popup-closer  icon-remove "
					title="关闭" onmouseover="$(this).addClass('jbox-close-hover');"
					onmouseout="$(this).removeClass('jbox-close-hover');"
					style="position: absolute; display: block; cursor: pointer; top: 4px; right: 11px; width: 15px; height: 15px;color:#666"></a>
				<div class="jbox-title-panel" style="height: 25px;">
					<div class="jbox-title">信息</div>
				</div>
				<div class="jbox-state">
					<div id="popup-content" style="padding: 8px 15px;"></div>
				</div>
			</div>
		</div>
		<!-- 展开按钮 -->
				<div
					style="position: absolute; bottom: 0px; right: 3px; transition: bottom 1s;"
					id="showDiv">
					<input onclick="ShowDiv()"
						style="background-color: #19a7f0 !important; display: none;"
						class="btn btn-primary" type="button" value="展开" />
				</div>
				<!--历史轨迹 底部grid列表数据 -->
				<div id="hideOrShowDiv" style="position: absolute;width:100%; bottom: 0px; right: 3px; height: 0px; transition: height 1s; z-index: 1992">
					<!-- 收起按钮 -->
					<div
						style="position: absolute; right: 0px; bottom: -30px; transition: bottom 1s;"
						id="hideDiv">
						<input onclick="HideDiv()"
							style="background-color: #3bb4f2 !important; border: 0px;"
							class="btn btn-primary" type="button" value="关闭" />
					</div>
					<!-- 倍速回放 -->
					<div
						style="position: absolute; left: 3px; bottom: -30px; transition: bottom 1s;"
						id="playBack">
						<input id="speed" name="" class="input-medium" type="number"
							min="1" max="1000" value="30"
							style="width: 55px; margin: 0; margin-right: 5px;"> <input
							id="start"
							style="background-color: #3bb4f2 !important; border: 0px;"
							class="btn btn-primary" type="button" value="开始" /> <input
							id="stop"
							style="background-color: #3bb4f2 !important; border: 0px;"
							class="btn btn-primary" type="button" value="停止" /> <input
							id="tempstop"
							style="background-color: #3bb4f2 !important; border: 0px;"
							class="btn btn-primary" type="button" value="暂停" /> <input
							id="next"
							style="background-color: #3bb4f2 !important; border: 0px;"
							class="btn btn-primary" type="button" value="前进" /> <input
							id="prev"
							style="background-color: #3bb4f2 !important; border: 0px;"
							class="btn btn-primary" type="button" value="后退" />
					</div>
					<!-- grid列表 -->
					<div id="TableCon" style="width: 100%;"></div>
				</div>
		<!--  工具栏-->
		<div id="tool">
			<div id="toolCenter">
				<div class="row-fluid tool-container">
					<div class="span3 tool-list" id="DrawFlag"  style="width: 27%;">
						<i class="tool-icon tool-draw"></i><span>标绘</span> <i
							class="tool-icon tool-arrow-up"></i> <b class="tool-gap"></b>
					</div>
				    <div class="span2  tool-list" onclick="Map.selectQuery('Polygon')"  style="width: 23%;"
						id="switchMap">
						<i class="tool-icon tool-select"></i><span>框选</span> <b
							class="tool-gap"></b>
					</div>
					<div class="span2  tool-list" onclick="Map.switchMap()"  style="width: 23%;"
						id="switchMap">
						<i class="tool-icon tool-map"></i><span>切换</span> <b
							class="tool-gap"></b>
					</div>
					<div class="span2 tool-list" onclick="Map.fullScreen()"  style="width: 20%;"
						id="fullScreen">
						<i class="tool-icon tool-full"></i><span>全屏</span>
					</div>
				</div>
			</div>
			<div class="detail-box" id="toolDetailBox">
				<ul id="boxul" class="boxinfo">
					<li class="clearfix tool-list" onclick="PointBox()"><i
						class="tool-icon tool-point"></i><span>点标</span></li>
					<li class="clearfix tool-list" onclick="LineBox()"><i
						class="tool-icon tool-line"></i><span>线标</span></li>
					<li class="clearfix tool-list" onclick="PolygonBox()"><i
						class="tool-icon tool-polygon"></i><span>面标</span></li>
					<li class="clearfix tool-list" onclick="ArrowBox()"><i
						class="tool-icon tool-arrow"></i><span>箭头</span></li>
					<li class="clearfix tool-list" onclick="TextBox()"><i
						class="tool-icon tool-text"></i><span>文字</span></li>

				</ul>
			</div>
			<!-- 点 -->
			<div class="tag-panl" id="PointBox">
				<div class="row-fluid tag-panl-header">
					<div class="span10">
						<span style="margin-left: 5px">标绘</span>
					</div>
					<div class="span2 tag-panl-close">
						<i class="icon-remove"></i>
					</div>
				</div>
				<div class="row-fluid tag-panl-center">
					<div class="span4"></div>
					<div class="span4">
						<span class="tag-panl-polygon tag-panl-span" title="点标绘"
							onclick="activate(P.PlotTypes.MARKER)"> <i
							class="tool-icon  tool-point"></i>
						</span>

					</div>
					<div class="span4"></div>
				</div>
			</div>
			<!-- 线 -->
			<div class="tag-panl" id="LineBox">
				<div class="row-fluid tag-panl-header">
					<div class="span10">
						<span style="margin-left: 5px">标绘</span>
					</div>
					<div class="span2 tag-panl-close">
						<i class="icon-remove"></i>
					</div>
				</div>
				<div class="row-fluid tag-panl-center">
					<div class="span3">
						<span class="tag-panl-polygon tag-panl-span" title="弧线标绘"
							onclick="activate(P.PlotTypes.ARC)"> <i class="tool-icon ">弧</i>
						</span>

					</div>
					<div class="span3">
						<span class="tag-panl-polygon tag-panl-span" title="曲线标绘"
							onclick="activate(P.PlotTypes.CURVE)"> <i
							class="tool-icon">曲</i>
						</span>
					</div>
					<div class="span3">
						<span class="tag-panl-polygon tag-panl-span" title="折线标绘"
							onclick="activate(P.PlotTypes.POLYLINE)"> <i
							class="tool-icon">折</i>
						</span>
					</div>
					<div class="span3">
						<span class="tag-panl-polygon tag-panl-span" title="自由线标绘"
							style="margin-left: -3px;"
							onclick="activate(P.PlotTypes.FREEHAND_LINE)"> <i
							class="tool-icon">自</i>
						</span>
					</div>
				</div>
			</div>
			<!-- 面 -->
			<div class="tag-panl" id="PolygonBox">
				<div class="row-fluid tag-panl-header">
					<div class="span10">
						<span style="margin-left: 5px">标绘</span>
					</div>
					<div class="span2 tag-panl-close">
						<i class="icon-remove"></i>
					</div>
				</div>
				<div class="row-fluid tag-panl-center">
					<div class="span4">
						<span class="tag-panl-polygon tag-panl-span" title="圆标绘"
							onclick="activate(P.PlotTypes.CIRCLE)"> <i
							class="tool-icon ">圆</i>
						</span>

					</div>
					<div class="span4">
						<span class="tag-panl-polygon tag-panl-span" title="椭圆标绘"
							onclick="activate(P.PlotTypes.ELLIPSE)"> <i
							class="tool-icon">椭</i>
						</span>
					</div>
					<div class="span4">
						<span class="tag-panl-polygon tag-panl-span" title="弓形标绘"
							onclick="activate(P.PlotTypes.LUNE)"> <i class="tool-icon">弓</i>
						</span>
					</div>
				</div>
				<div class="row-fluid tag-panl-center">
					<div class="span4">
						<span class="tag-panl-polygon tag-panl-span" title="扇形标绘"
							style="margin-left: -3px;" onclick="activate(P.PlotTypes.SECTOR)">
							<i class="tool-icon">扇</i>
						</span>
					</div>
					<div class="span4">
						<span class="tag-panl-polygon tag-panl-span" title="曲线面标绘"
							onclick="activate(P.PlotTypes.CLOSED_CURVE)"> <i
							class="tool-icon ">曲</i>
						</span>

					</div>
					<div class="span4">
						<span class="tag-panl-polygon tag-panl-span" title="多边形标绘"
							onclick="activate(P.PlotTypes.POLYGON)"> <i
							class="tool-icon">多</i>
						</span>
					</div>
				</div>
				<div class="row-fluid tag-panl-center">

					<div class="span4">
						<span class="tag-panl-polygon tag-panl-span" title="矩形标绘"
							onclick="activate(P.PlotTypes.RECTANGLE)"> <i
							class="tool-icon">矩</i>
						</span>
					</div>
					<div class="span4">
						<span class="tag-panl-polygon tag-panl-span" title="自由面标绘"
							onclick="activate(P.PlotTypes.FREEHAND_POLYGON)"> <i
							class="tool-icon">自</i>
						</span>
					</div>
					<div class="span4">
						<span class="tag-panl-polygon tag-panl-span" title="聚集地标绘"
							onclick="activate(P.PlotTypes.GATHERING_PLACE)"> <i
							class="tool-icon">聚</i>
						</span>
					</div>
				</div>
			</div>
			<!-- 箭头 -->
			<div class="tag-panl" id="ArrowBox">
				<div class="row-fluid tag-panl-header">
					<div class="span10">
						<span style="margin-left: 5px">标绘</span>
					</div>
					<div class="span2 tag-panl-close">
						<i class="icon-remove"></i>
					</div>
				</div>
				<div class="row-fluid tag-panl-center">
					<div class="span3">
						<span class="tag-panl-polygon tag-panl-span" title="钳击标绘"
							onclick="activate(P.PlotTypes.DOUBLE_ARROW)"> <i
							class="tool-icon ">钳</i>
						</span>

					</div>
					<div class="span3">
						<span class="tag-panl-polygon tag-panl-span" title="直箭头圆标绘"
							onclick="activate(P.PlotTypes.STRAIGHT_ARROW)"> <i
							class="tool-icon">直</i>
						</span>
					</div>
					<div class="span3">
						<span class="tag-panl-polygon tag-panl-span" title="细直箭头标绘"
							onclick="activate(P.PlotTypes.FINE_ARROW)"> <i
							class="tool-icon">细</i>
						</span>
					</div>
					<div class="span3">
						<span class="tag-panl-polygon tag-panl-span" title="突击方向标绘"
							style="margin-left: -3px;"
							onclick="activate(P.PlotTypes.ASSAULT_DIRECTION)"> <i
							class="tool-icon">突</i>
						</span>
					</div>
				</div>
				<div class="row-fluid tag-panl-center">

					<div class="span3">
						<span class="tag-panl-polygon tag-panl-span" title="进攻方向面标绘"
							onclick="activate(P.PlotTypes.ATTACK_ARROW)"> <i
							class="tool-icon ">进</i>
						</span>

					</div>
					<div class="span3">
						<span class="tag-panl-polygon tag-panl-span" title="进攻方向（尾）标绘"
							onclick="activate(P.PlotTypes.TAILED_ATTACK_ARROW)"> <i
							class="tool-icon">尾</i>
						</span>
					</div>
					<div class="span3">
						<span class="tag-panl-polygon tag-panl-span" title="分队战斗行动标绘"
							onclick="activate(P.PlotTypes.SQUAD_COMBAT)"> <i
							class="tool-icon">分</i>
						</span>
					</div>
					<div class="span3">
						<span class="tag-panl-polygon tag-panl-span" title="分队战斗行动（尾）标绘"
							onclick="activate(P.PlotTypes.TAILED_SQUAD_COMBAT)"> <i
							class="tool-icon">尾</i>
						</span>
					</div>
				</div>
			</div>

			<!-- 文字  -->
			<div class="tag-panl" id="TextBox">
				<div class="row-fluid tag-panl-header">
					<div class="span10">
						<span style="margin-left: 5px">文字</span>
					</div>
					<div class="span2 tag-panl-close">
						<i class="icon-remove"></i>
					</div>
				</div>
				<div class="row-fluid tag-panl-center">
					<div class="span4"></div>
					<div class="span4">
						<span class="tag-panl-polygon tag-panl-span" title="文字标绘"
							onclick="Map.drawText(true)"> <i
							class="tool-icon  tool-text"></i>
						</span>

					</div>
					<div class="span4"></div>
				</div>
			</div>

			<!-- 图层列表 -->
			<!-- <div class="tag-panl" id="LayersBox"
				style="min-width: 250px; width: 250px;">
				<div class="row-fluid tag-panl-header">
					<div class="span10">
						<span style="margin-left: 5px">图层列表</span>
					</div>
					<div class="span2 tag-panl-close">
						<i class="icon-remove"></i>
					</div>
				</div>
				<div class="row-fluid tag-panl-center">
					<div class="span1"></div>
					<div class="span11">
						<ul id="tree" class="ztree"></ul>
					</div>
				</div>
			</div> -->
			<!-- 图层列表 -->
		</div>
		<!-- 工具栏 -->
		<div id="delete-wrapper">
			<div id="btn-delete" style="display: none;">删 除</div>
		</div>
		<!--  工具栏-->
		<div id="map" class="map" tabindex="0">
		</div>
	
		<div id="popup" class="ol-popup">
			<div class="">
				<a href="#" id="popup-closer" class="ol-popup-closer  icon-remove "
					title="关闭" onmouseover="$(this).addClass('jbox-close-hover');"
					onmouseout="$(this).removeClass('jbox-close-hover');"
					style="position: absolute; display: block; cursor: pointer; top: 4px; right: 11px; width: 15px; height: 15px;color:#666"></a>
				<div class="jbox-title-panel" style="height: 25px;">
					<div class="jbox-title">信息</div>
				</div>
				<div class="jbox-state">
					<div id="popup-content" style="padding: 8px 15px;"></div>
				</div>
			</div>
		</div>
		
	
	</div>
	</div>    
     <!-- /content -->
    </div>  


	<script type="text/javascript">
	   // 人员定位
		var leftWidth = 0; // 左侧窗口大小
		var htmlObj = $("html"), mainObj = $("#main");
		var frameObj = $("#left, #openClose, #right, #right iframe");
		
		function wSize() {
			var strs = getWindowSize().toString().split(",");
			htmlObj.css({
				"overflow-x" : "auto",
				"overflow-y" : "auto"
			});
			mainObj.css("width", "auto");
			frameObj.height(strs[0] - 5);
			var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
			$("#right").width(
					$("#content").width() - leftWidth - $("#openClose").width()
							- 5);
	

		}
		function wSizeWidth() {
			if (!$("#openClose").is(":hidden")) {
				var leftWidth = ($("#left").width() < 0 ? 0 : $("#left")
						.width());
				$("#right").width(
						$("#content").width() - leftWidth
								- $("#openClose").width() - 4);
			} else {
				$("#right").width("100%");
			}
		}// <c:if test="${tabmode eq '1'}"> 
		function openCloseClickCallBack(b) {
			$.fn.jerichoTab.resize();
		} // </c:if>
		

		
	</script>
     <script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>