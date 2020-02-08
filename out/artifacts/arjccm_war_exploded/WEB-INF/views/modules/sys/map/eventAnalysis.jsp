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
<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css"
	rel="stylesheet" />

<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js"
	type="text/javascript"></script>
<script src="${ctxStatic}/ol/ol.js"></script>
<script src="${ctxStatic}/modules/map/js/draw/js/p-ol3.debug.js"></script>
<link href="${ctxStatic}/modules/map/css/pop-info-animate.css"
	rel="stylesheet" />
<link href="${ctxStatic}/layer-v3.1.1/layer/theme/default/layer.css"
	rel="stylesheet" />
	<link href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet">

<script src="${ctxStatic}/layer-v3.1.1/layer/layer.js"></script>
<script src="${ctxStatic}/modules/map/js/mapconfig.js"></script>
<script src="${ctxStatic}/modules/map/js/commonMap.js"></script>
<script src="${ctxStatic}/ccm/sys/js/GISCommon.js"></script>
<script src="${ctxStatic}/common/common.js" type="text/javascript"></script>
<script src="${ctxStatic}/ccm/sys/js/EventAnalysis.js"></script>
<style>
.checkbox-success label{
    -o-user-select: none;
    -moz-user-select: none; /* firefox*/
    -webkit-user-select: none; /*webkit*/
    -ms-user-select: none; /*IE10+*/
    -khtml-user-select :none; /**/
    user-select: none; 
}
.fourColorsLegend{
	width: 100%;
	float: left;
	line-height: 26px;
}
.color-bg{
	width: 160px;
	height:20px;
	float: left;
	margin-top: 2px;
    margin-right: 5px;
}
.color-bg1{
	background: #21c909;
}
.color-bg2{
	background: #ffec05;
}
.color-bg3{
	background: #ffa408;
}
.color-bg4{
	background: #ff390d;
}
.query-criteria{
	background-size: 100% 100%;
	min-height: 200px;
	overflow: hidden;
}
#CaseTYpe>div{
	float:left;
	width:42%;
	overflow: hidden; 
	white-space: nowrap; 
	text-overflow: ellipsis;
}
.presentation-mode{
	background-size: 100% 100%;
	min-height: 155px;
	overflow: hidden;
}
#okBtn.btn-primary,#okBtn.btn-primary:hover,#removeBtn.btn-primary,#removeBtn.btn-primary:hover {
    color: #fff;
    border-color: none;
    border:none;
    margin-left: 10px;
    margin-top: 10px;
}
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
	 height: calc(100% - 64px - 57px - 25px);
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
width: 113px;
}
.input-append {
   margin-bottom: 0px;
}
</style>
</head>
<body  style="overflow: hidden;">
    <div id="content" class="row-fluid">
     <div id="left" class="accordion-group mapIndex">
				 			
		</div>
     <div  id="right" style="overflow: hidden;"> 
          <div id="showMapKey" style="width: 0px; height: 0px; display: none;">
			<img src="${ctxStatic}/images/GIS_list.png">
		</div>
       <div id="mapKey">
			<div class="mapView">
					<p id="mapView-p">数据采集分析<span style="float: right; font-size: 20px; padding-right: 10px; color: #FFF; cursor: pointer;">×</span></p>
					<div class="mapView-p-head">
						<div class="layui-tab-content">
							<div class="nav-common-center">
	            	<label style="color:#0f53ab;margin-left:10px;margin-top:10px;">查询条件</label>
	              	<div style="margin: 5px;" class="query-criteria">
		              	<div style="margin: 10px;">
		                  	<div class="radio  radio-success" style="display: inline-block;">
		                  		<input type="radio" id="TodayRadio" value="1" name="time" checked="checked">
		                  		<label for="TodayRadio" ><span style="border: 1px solid #04c629;color:#04c629; padding: 2px;">今日</span></label>
		              		</div>
		              		<div class="radio  radio-success" style="display: inline-block;">
		                  		<input type="radio" id="WeekRadio" value="2" name="time" >
		                  		<label for="WeekRadio" ><span style="border: 1px solid #0687fd;color:#0687fd; padding: 2px;">本月</span></label>
		              		</div>
		                	<div class="radio  radio-success" style="display: inline-block;">
		                  		<input type="radio" id="YearRadio" value="3" name="time" >
		                  		<label for="YearRadio" ><span style="border: 1px solid #03d9d1;color:#03d9d1; padding: 2px;">本年</span></label>
		              		</div>
	                 	</div>
                 		<input name="beginOrEndTime" id="beginTime" type="text" readonly="readonly" maxlength="20" style="width: 110px;height:30px;margin-bottom: 0; margin-left: 10px;" class="input-medium Wdate" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,onpicked:getTimeChange,maxDate:'#F{$dp.$D(\'endTime\')||\'%y-%M\'}'});">
						- 
						<input name="beginOrEndTime" id="endTime" type="text"  style="width: 110px;height:30px;margin-bottom: 0;" readonly="readonly" maxlength="20" class="input-medium Wdate" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,onpicked:getTimeChange,minDate:'#F{$dp.$D(\'beginTime\')}',maxDate:'%y-%M'});">
	                	<div style="margin: 5px 5px 10px 10px;">
	               		<label>案件类型</label><br />
		                 	<div class="checkbox checkbox-success radio-div tab-content-bg" style="display: inline-block;margin-bottom: 5px;padding-top: 5px;padding-bottom: 5px;">
								<input type="checkbox" value="16" class="type-input" id="warninput" onchange="selectAll('warninput', 'warnCheckbox')" />
								<label	for="warninput" style="padding:0;width:80px;" >所有</label>
							</div>
							<div id="CaseTYpe" class="clearfix">
							
							</div>
						</div>	
	              	</div>
	               	<label style="color:#0f53ab;margin-left:10px;">呈现方式</label>
	               	<div style="margin: 5px;" class="presentation-mode">
	                    <div style="margin: 10px;">
	                        <div class="radio  radio-success" style="display: inline-block;">
			                    <input type="radio" id="HeatRadio" value="1" name="chartType" checked="checked">
			                    <label for="HeatRadio">热力图</label>
			                </div>
			                <div class="radio  radio-success" style="display: inline-block;">
			                    <input type="radio" id="PolymerizationRadio" value="2" name="chartType" >
			                    <label for="PolymerizationRadio">聚合图</label>
			                </div>
			                <div class="radio  radio-success" style="display: inline-block;">
			                    <input type="radio" id="FourColorsRadio" value="3" name="chartType" >
			                    <label for="FourColorsRadio">四色图</label>
			                </div>
	                    </div>
	                    <div>
	                        <div style="margin:10px" class="clearfix">
	                            <div class="fourColorsLegend"><div class="color-bg color-bg1"></div><span id="FourColor1">0-100</span></div>
	                            <div class="fourColorsLegend"><div class="color-bg color-bg2"></div><span id="FourColor2">101-200</span></div>
	                            <div class="fourColorsLegend"><div class="color-bg color-bg3"></div><span id="FourColor3">201-300</span></div>
	                            <div class="fourColorsLegend"><div class="color-bg color-bg4"></div><span id="FourColor4">301-400</span></div>
	                        </div>
	                    </div>
	                </div>
	                <div style="margin:5px">
	                    <a id="okBtn" class="btn btn-primary" style="background: #0687fd !important;" href="javascript:;" onclick="okFun()"><i class="icon-plus"></i>生成</a>
	                    <a id="removeBtn" class="btn btn-primary" style="background: #f50b4b !important;" href="javascript:;" onclick="removeBtnFun()"><i class="icon-remove"></i>清除</a>
	                </div>
            	</div>	
						</div>
					</div>
				</div>
		</div>  
	      <div id="map" class="map" tabindex="0"></div>
	
	</div>    
     <!-- /content -->
    </div>  

	<script type="text/javascript">
		var leftWidth = 0; // 左侧窗口大小
		var htmlObj = $("html"), mainObj = $("#main");
		var frameObj = $("#left,  #right, #right iframe");
		function wSize() {
			var strs = getWindowSize().toString().split(",");
			htmlObj.css({
				"overflow-x" : "auto",
				"overflow-y" : "auto"
			});
			mainObj.css("width", "auto");
			frameObj.height(strs[0] - 5);
			var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
			$("#right").width($("#content").width());

	
	
		}
		function wSizeWidth() {
			$("#right").width("100%");
		}
		

		
	</script>
     <script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>