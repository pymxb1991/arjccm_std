<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>预案管理</title>
	<meta name="decorator" content="default" />
	<script type="text/javascript">
		var ctx="${ctx}";
		var ctxStatic="${ctxStatic}";
	</script>
	<link rel="stylesheet" href="${ctxStatic}/layim/layui/css/layui.css" />
	<link rel="stylesheet" href="${ctxStatic}/flat/realtimeAlarmSituation/css/jquery.eeyellow.Timeline.css" />
	<link rel="stylesheet" href="${ctxStatic}/layer-v3.1.1/layer/theme/default/layer.css" />
	<link rel="stylesheet" href="${ctxStatic}/ol/ol.css" type="text/css">
	<link rel="stylesheet" href="${ctxStatic}/modules/map/js/draw/css/p-ol3.min.css" type="text/css">
	<link rel="stylesheet" href="${ctxStatic}/modules/map/css/mapFlat.css" type="text/css">
	<link rel="stylesheet" href="${ctxStatic}/modules/map/css/pop-info-animate.css"/>
	<link rel="stylesheet" href="${ctxStatic}/modules/map/css/publicinstitutions.css" />
	<link rel="stylesheet" href="${ctxStatic}/flat/planManage/css/reset.css" />
	<link rel="stylesheet" href="${ctxStatic}/flat/toolCommon.css" />
	<link rel="stylesheet" href="${ctxStatic}/flat/planManage/css/index.css" />
	<link rel="stylesheet" href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" type="text/css" />
	<link rel="stylesheet" href="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.css" />
	<link rel="stylesheet" href="${ctxStatic}/audio/audio.css" />
	<link rel="stylesheet" href="${ctxStatic}/flat/planManage/css/yuan.css" type="text/css" />
	<link rel="stylesheet" href="${ctxStatic}/flat/layerCommon.css" />
	<link rel="stylesheet" href="${ctxStatic}/flat/planManage/css/timeline.css">
<%--<link rel="stylesheet" href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome.min.css"/>--%>
	<script src="${ctxStatic}/audio/audio.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/layim/layui/layui.js" type="text/javascript"></script>
	<%-- <script src="${ctxStatic}/flat/planManage/js/timeline.js" type="text/javascript"></script> --%>
	<script type="text/html" id="barDemo">
		<i class="icon-phone" id="callPhone" onclick="callPhone(this)" style="color: deepskyblue;font-size: 16px; margin-left: 10px; cursor: pointer;" title="拨打电话"></i>
		<i class="icon-comment" id="sendNote" onclick="sendNote(this)" style="color: deepskyblue;font-size: 16px; margin-left: 10px; cursor: pointer;" title="发送信息"></i>
		<i class="icon-map-marker" id="locationConstable" onclick="locationConstable(this)" style="color: deepskyblue;font-size: 16px; margin-left: 10px; cursor: pointer;" title="定位"></i>
	</script>
<script type="text/html" id="barPeo">
	<i class="icon-phone" id="callPhone" onclick="callPhone(this)" style="color: deepskyblue;font-size: 16px; margin-left: 10px; cursor: pointer;" title="拨打电话"></i>
  	<i class="icon-comment" id="sendNote" onclick="sendNote(this)" style="color: deepskyblue;font-size: 16px; margin-left: 10px; cursor: pointer;" title="发送信息"></i>
  	<i class="icon-map-marker" id="locationConstable" onclick="locationConstable(this)" style="color: deepskyblue;font-size: 16px; margin-left: 10px; cursor: pointer;" title="定位"></i>
</script>
	<script type="text/html" id="sexTpl">
		{{#  if(d.state === '离线'){ }}
		<div style="color: #f5f5f500;background-color: #928d8d;height: 100%;width: 10px;">{{ d.state }}</div>
		{{#  } else if(d.state === '备勤') { }}
		<div style="color: #f5f5f500;background-color: #17a056;height: 100%;width: 10px;">{{ d.state }}</div>
		{{#  } else if(d.state === '忙碌') { }}
		<div style="color: #f5f5f500;background-color: #f7cb49;height: 100%;width: 10px;">{{ d.state }}</div>
		{{# } }}
	</script>
	<style type="text/css">
		#delete-wrapper {
			position: absolute;
			bottom: 0px;
			width: 60px;
			text-align: center;
			padding-bottom: 8px;
			z-index: 99999;
			left:50%;
			margin-left:-30px;
		}

		#delete-wrapper #btn-delete {
			display: inline-block;
			color: rgb(255, 255, 255);
			cursor: pointer;
			padding: 8px 16px;
			background: #19a7f0;
		}

		.zhi {
			cursor: pointer;
		}

		.audiojs {
			width: 150px;
		}

		.audiojs p {
			width: 13px;
		}

		.audiojs .scrubber {
			width: 28px;
		}

		.audiojs .error-message {
			width: 150px;
		}

		.audiojs .time {
			margin-left: 0;
			padding-left: 0;
			border-left: 0;
		}

		.audiojs .play-pause {
			border-right: 0;
		}

		.unfold {
			left: 270px;
			bottom: 43px;
		}

		.checkbox .police-type {
			display: inline-block;
		}

		.checkbox label.police-label {
			padding-left: 0px;
			width: 45px;
		}
		.checkbox label.police-label:before {
			display: block;
			position: relative;
			width: 20px;
			height: 20px;
			left: 38px;
			margin-left: -34px;
			border: 1px solid #cccccc;
			border-radius: 25%;
		}

		.checkbox label.police-label::after {
			width: 25px;
			height: 25px;
			left: 36px;
			margin-left: -34px;
			border-radius: 50%;
			font-size: 20px;
			padding-top: 2px;
		}

		.step-hap {
			color: #e10602;
			float: left;
			line-height: 60px;
			margin-left: 20px;
		}

		.step-hap-arrow {
			float: left;
		}

		.step-hap-arrow i {
			font-size: 60px;
			color: #07e8ee;
		}

		.step-name {
			color: #e10602;
			float: left;
			line-height: 18px;
			min-width: 64px;
			text-align: center;
			margin-left: 10px;
			border: 3px #07e8ee solid;
			padding: 5px;
			margin-top: 14px;
		}

		.step-arrow {
			float: left;
		}

		.step-arrow i {
			font-size: 26px;
			color: #07e8ee;
			margin-left: 10px;
			line-height: 67px;
		}

		.step-name-cus {
			color: #e96614;
			float: left;
			line-height: 18px;
			min-width: 64px;
			text-align: center;
			margin-left: 10px;
			border: 3px #07e8ee solid;
			padding: 5px;
			margin-top: 14px;
			float: left;
			line-height: 18px;
			min-width: 64px;
			text-align: center;
			margin-left: 10px;
			border: 3px #07e8ee solid;
			padding: 5px;
		}

		.step-name-sus {
			color: #3ac828;
			float: left;
			line-height: 18px;
			min-width: 64px;
			text-align: center;
			margin-left: 10px;
			border: 3px #07e8ee solid;
			padding: 5px;
			margin-top: 14px;
		}
		.bottom-table .layui-table td{
			border-color:#07e8ee;
		}
		#contactId span{
			cursor: pointer;
			display: block;
			float: left;
			position: relative;
			padding: 5px 15px 5px 10px;
			background-color: #ebebeb;
			color: #000;
			font-size: 12px;
			margin: 3px;
		}
		.layui-table-header{
			display:none;
		}
		.layui-table-view,.layui-table-view .layui-table td{
			border:none;
		}
		.layui-table-cell{
			padding: 0 0px;
		}
		.layui-table-grid-down{
			display: none;
		}
.right-side{
overflow:hidden;
}
.liveStation.container{
overflow-y:auto
 }
.details {
  /*  height: 2.62rem; */
   padding-top: 0.05rem;
   overflow:auto !important;
}
h5 i {
    display: block;
    position: absolute;
    width: 0.14rem;
    height: 0.08rem;
    background:url(/arjccm/static/flat/planManage/images/arrow1.png) center no-repeat;
    background-size: 100% 100%;
    right:0.1rem;
    top:0.12rem;
    cursor: pointer;
}
h5 i.active {
	transform: rotate(90deg);
	-ms-transform: rotate(90deg); /* IE 9 */
	-moz-transform: rotate(90deg); /* Firefox */
	-webkit-transform: rotate(90deg); /* Safari and Chrome */
	-o-transform: rotate(90deg); /* Opera */
    background:url(/arjccm/static/flat/planManage/images/arrow.png) center no-repeat;
    background-size: 100% 100%;
}
	</style>
	<link rel="stylesheet" href="${ctxStatic}/audio/audio.css" />
	<script src="${ctxStatic}/flat/alarmCommon.js" type="text/javascript"></script>
</head>
<body>
<div id="content" class="row-fluid">
	<input type="hidden" id="usersname" name="usersname" value="${fns:getUser().name}">
	<div id="right">
		<div class="container wrapper">
			<div class="left-side">
				<div id="alarmInfoDiv">
					<h5>
						警情详情<i class="ic1"></i>
					</h5>
					<div class="details" id="alarmInfoDivDetails">
					</div>
				</div>
				<div id="planInfoDiv">
					<h5>
						预案处理<i class="ic2"></i>
					</h5>
					<div class="processing clearfix">
						<ul id="planInfoLi" >

						</ul>
						<div id="stepInfoDiv" class="active">

						</div>
					</div>
				</div>
				<div id="contactInfoDiv">
					<h5>
						关联动作<i class="ic3"></i>
					</h5>
					<div class="correlation">
						<%--<div class="police-station active" style="height:222px;">
							<div>
								<i title="联系人" onclick="contact()"></i>
								<i title="发送" onclick="sendContactAndMessage()"></i>
							</div>
							<div id="contactId" style="width:235px;height:50px;border:1px solid #528fd4;margin-left:10px;overflow: auto"></div>
							<textarea id="contactMessageId" class="contactMessage"></textarea>
						</div>--%>
						<%--<div class="communication">
							<div class="phone-number" >
								<input type="number" value="" maxlength="11" />
								<div class="delete-key"></div>
							</div>
							<ul class="telephone clearfix">
								<li onclick="number(this)" value="1">1</li>
								<li onclick="number(this)" value="2">2</li>
								<li onclick="number(this)" value="3">3</li>
								<li onclick="number(this)" value="4">4</li>
								<li onclick="number(this)" value="5">5</li>
								<li onclick="number(this)" value="6">6</li>
								<li onclick="number(this)" value="7">7</li>
								<li onclick="number(this)" value="8">8</li>
								<li onclick="number(this)" value="9">9</li>
								<li onclick="number(this)" value="*">*</li>
								<li onclick="number(this)" value="0">0</li>
								<li onclick="number(this)" value="#">#</li>
								<li><img src="${ctxStatic}/flat/planManage/images/key.png" /></li>
								<li class="dial"><div class="img"></div></li>
								<li><img src="${ctxStatic}/flat/planManage/images/key3.png" /></li>
							</ul>
						</div>--%>
						<div class="circumambient active" style="height:222px;">
							<div class="resource clearfix">
								<div>
									<div style="float:left;padding-top: 15px;">
										<input style="width: 0.55rem;" class="complete" type="checkbox" />
										<div style="width: 0.55rem;text-align: center; margin-top: 0.119rem;color: #6c8bc1;">全选</div>
									</div>
									<dl type-val="0">
										<dt><a href="###"><img src="${ctxStatic}/flat/planManage/images/icon10.png" /></a></dt>
										<dd>车辆</dd>
									</dl>
									<dl type-val="0">
										<dt><a href="###"><img src="${ctxStatic}/flat/planManage/images/icon2.png" /></a></dt>
										<dd>人员</dd>
									</dl>
									<dl type-val="0">
										<dt><a href="###"><img src="${ctxStatic}/flat/planManage/images/icon8.png" /></a></dt>
										<dd>视频</dd>
									</dl>
								</div>
								<div style="float:left;">
									<label style = "padding-left:16px;padding-top: 20px;">范围：</label>
									<div class="radio radio-success" style="display: inline-block;">
										<input type="radio" id="TodayRadio_A" value="300" name="distance-a" checked="checked">
										<label for="TodayRadio_A">300m</label>
									</div>
									<div class="radio  radio-success" style="display: inline-block;">
										<input type="radio" id="WeekRadio_A" value="500" name="distance-a">
										<label for="WeekRadio_A">500m</label>
									</div>
									<div class="radio  radio-success" style="display: inline-block;">
										<input type="radio" id="YearRadio_A" value="1000" name="distance-a">
										<label for="YearRadio_A">1000m</label>
									</div>
									<div class="radio  radio-success" style="display: inline-block;padding-left: 40px;margin-top: 15px;">
										<input type="radio" id="customRadio_A" value="custom" name="distance-a">
										<label for="customRadio_A">自定义范围：</label>
										<input type="number" min="0" id="customKM_A" value="" style="width:65px;" name="distance-a" disabled="disabled">m
									</div>
								</div>
								<div>
									<input id="btnSubmit" xNum="" yNum="" class="" style="color: #fff;background-color: #528fd4;border-radius: 5px;width: 50px;height: 30px;margin-left: 98px;margin-top: 47px;" type="button" value="查询" onclick="nearSearch_A(this)"/>
								</div>
							</div>
						</div>
						<div class="indicating">
							<div class="detailed clearfix" data-id="" style="height:222px;">
								<span style="${cookie.theme.value eq 'gradient' ? '#edf5ff' : '#0e2a4c'}" class="${cookie.theme.value eq 'gradient' ? 'secondary-location' : 'secondary-location2'}" type="alarm-location" onclick="locationP_er(this)"><i></i>二次定位</span>
								<span class="${cookie.theme.value eq 'gradient' ? 'nearby-search' : 'nearby-search2'}" type="alarm-search"  onclick="nearSearch(this)"><i></i>周边查询</span>
								<!-- <span class="intelligent-dispatch" type="alarm-dispatch" data-id="" onclick="controlDialog(this)"><i></i>智能布控</span> -->
								<span class="${cookie.theme.value eq 'gradient' ? 'analysis' : 'analysis2'}" type="alarm-analysis" data-id="" onclick="findHandleLog(this)"><i></i>处置分析</span>
								<span class="${cookie.theme.value eq 'gradient' ? 'file' : 'file2'}" type="alarm-file" data-id="" onclick="alarmkArchive(this)"><i></i>事后归档</span>
							</div>
						</div>
						<div class="activity clearfix" style="display: block;">
							<%--<div class="active">消息推送</div>
							<div>融合通讯</div>--%>
								<div class="active">周边资源</div>
							<div>辅助决策</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 新警情 -->
			<div class="map-wrapper">
				<div id="map" class="map">
					<!-- 图层 -->
					<div id="onparentdiv" style="position: absolute;width: 1230px;height: 30px;background-color: white;left: 280px;z-index: 9;border-radius: 5px;top: 10px;border: 1px solid #ccc;">
						<!-- 视频监控 -->
						<div class="pub-flag" onclick="shipinjiankongFun(this)" id="v_videoMon" style="width: 8%;" >
							<span class="pub-icon icon-shipin"></span> <span class="pub-name">视频监控</span>
						</div>
						<!-- 应急广播 -->
						<div class="pub-flag" onclick="guangbozhanFun(this)" id="v_broadCast" style="width: 8%;" >
							<span class="pub-icon icon-guangbozhan"></span> <span class="pub-name">广播站</span>
						</div>
						<!-- 警员-->
						<div class="pub-flag" onclick="jingyuanFun(this)" id="v_police" style="width: 8%;" >
							<span class="pub-icon icon-jingyuan"></span> <span class="pub-name">工作人员</span>
						</div>
						<!-- 警车-->
						<div class="pub-flag" onclick="jingcheFun(this)" id="v_policeCar" style="width: 6%;" >
							<span class="pub-icon icon-jingche"></span> <span class="pub-name">车辆</span>
						</div>
						<!--学校  -->
						<div class="pub-flag" onclick="xuexiaoFun(this)" id="v_scoole" style="width: 6%;" >
							<span class="pub-icon icon-xuexiao"></span> <span class="pub-name">学校</span>
						</div>
						<!-- 医院 -->
						<div class="pub-flag" onclick="yiyuanFun(this)" id="v_hospital" style="width: 6%;" >
							<span class="pub-icon icon-yiyuan"></span> <span class="pub-name">医院</span>
						</div>
						<!-- 加油站 -->
						<div class="pub-flag" onclick="jiayouzhanFun(this)" id="v_gasStation" style="width: 8%;" >
							<span class="pub-icon icon-jiayouzhan"></span> <span class="pub-name">加油站</span>
						</div>
						<!-- 商场超市 -->
						<div class="pub-flag" onclick="shangchangFun(this)" id="v_store" style="width: 8%;" >
							<span class="pub-icon icon-shangchang"></span> <span class="pub-name">商场超市</span>
						</div>
						<!-- 娱乐场所 -->
						<div class="pub-flag" onclick="yuleFun(this)" id="v_entertainment" style="width: 8%;" >
							<span class="pub-icon icon-yule"></span> <span class="pub-name">娱乐场所</span>
						</div>
						<!-- 宾馆 -->
						<div class="pub-flag" onclick="binguanFun(this)" id="v_hotel" style="width: 8%;" >
							<span class="pub-icon icon-bingguan"></span> <span class="pub-name">酒店宾馆</span>
						</div>
						<!-- 涉危涉爆单位 -->
						<div class="pub-flag" onclick="sheweishebaoFun(this)" id="v_danger" style="width: 8%;" >
							<span class="pub-icon icon-sheweishebao"></span> <span class="pub-name">涉危涉爆</span>
						</div>
						<!-- 警务室-->
						<div class="pub-flag" onclick="jingwushiFun(this)" id="v_policeRoom" style="width: 7%;" >
							<span class="pub-icon icon-jingwushi"></span> <span class="pub-name">警务室</span>
						</div>
						<!-- 工作站-->
						<div class="pub-flag" onclick="gongzuozhanFun(this)" id="v_workstation" style="width: 8%;" >
							<span class="pub-icon icon-gongzuozhan"></span> <span class="pub-name">工作站</span>
						</div>
					</div>
					<!-- 工具栏 -->
					<div id="delete-wrapper">
						<div id="btn-delete" style="display: none;">查询</div>
					</div>
					<!-- 开关 -->
					<a href="#" id="alarmDMS" class="toggle toggle--on"
					   style="position: absolute; left: 279px;  bottom: 865px; top: 50px; z-index: 99">
					</a>
					<!-- 						<a href="#" id="videoDMS" class="toggle toggle--off" -->
					<!-- 							style="position: absolute; left: 320px; bottom: 170px; top: auto; z-index: 99"> -->
					<!-- 						</a>  -->
					<!-- 						<a href="#" id="policeDMS" class="toggle toggle--off" -->
					<!-- 							style="position: absolute; left: 320px; bottom: 135px; top: auto; z-index: 99"> -->
					<!-- 						</a>  -->
					<!-- 						<a href="#" id="carDMS" class="toggle toggle--off" -->
					<!-- 							style="position: absolute; left: 320px; bottom: 100px; top: auto; z-index: 99"> -->
					<!-- 						</a> -->
					<!-- 开关 -->
					<!-- 弹框 -->
					<div id="popup" class="ol-popup">
						<div class="popup-top">
							<span class="popup-title"></span> <a href="#" id="popup-closer" class="ol-popup-closer"></a>
						</div>
						<div class="popup-content" id="popup-content"></div>
					</div>
					<div id="pubMap"></div>
					<!-- 弹框 -->
					<!-- 工具栏 -->
					<span id="floatingLayer" style="margin-bottom: 30px;">工具</span>
					<!-- 						 <span class="refurbish left-length" onclick="window.location.reload()">刷新</span> -->
					<div class="mymovement">
						<div class="movement"></div>
					</div>
					<div class="toolmenu" id="toolMenu">
						<div class="toolbar" id="toolbar">
							<div class="all-map">
								<span>全屏</span>
							</div>
							<div class="return-map">
								<span>返回</span>
							</div>
							<div class="Tools">
								<span>工具</span>
							</div>
							<div class="range" id="range">
								<span>范围</span>
							</div>
							<div class="plotting" id="plotting">
								<span>标绘</span>
							</div>
							<div class="historyCoordinate" id="historyCoordinate">
								<span>暂无</span>
							</div>
							<div id="clear">
								<span onclick="clearAllGraphic()">清除</span>
							</div>
							<div id="turn-off">
								<span>关闭</span>
							</div>
						</div>
						<div class="inner-menu">
							<ul class="toolbars">
								<li><span id="lengthMeasure" title="测线" onclick="Map.measureMap('length')"></span></li>
								<li><span id="pointMeasure" title="测面" onclick="Map.measureMap('area')"></span></li>
								<li><span id="vector" title="地图切换" onclick="Map.switchMap()"></span></li>
							</ul>
							<ul class="range-query">
								<li><span id="circleSelect" title="圈选" onclick="Map.selectQuery('Circle')"></span></li>
								<li><span id="rectangSelect" title="框选" style="display: none"></span></li>
								<li><span id="polygonSelect" title="多边形选" style="display: none"></span></li>
								<li></li>
								<li></li>
							</ul>
							<ul class="plottingbar">
								<li><span id="labelCircle" title="圆形" onclick="activate(P.PlotTypes.CIRCLE)"></span></li>
								<li><span id="labelEclipse" title="椭圆" onclick="activate(P.PlotTypes.ELLIPSE)"></span></li>
								<li><span id="labelRect" title="矩形" onclick="activate(P.PlotTypes.RECTANGLE)"></span></li>
								<li><span id="labelFreePolygon" title="自由面" onclick="activate(P.PlotTypes.FREEHAND_POLYGON)"></span></li>
								<li><span id="labelFreePolyline" title="自由线" onclick="activate(P.PlotTypes.FREEHAND_LINE)"></span></li>
								<li><span id="labelLeftArrow" title="细直箭头" onclick="activate(P.PlotTypes.FINE_ARROW)"></span></li>
								<li><span id="labelRightArrow" title="钳击" onclick="activate(P.PlotTypes.DOUBLE_ARROW)"></span></li>
								<li><span id="labelUpArrow" title="直箭头" onclick="activate(P.PlotTypes.STRAIGHT_ARROW)"></span></li>
								<li><span id="labelDownArrow" title="突击方向标绘" onclick="activate(P.PlotTypes.ASSAULT_DIRECTION)"></span></li>
								<li><span id="labelSave" title="弧线" onclick="activate(P.PlotTypes.ARC)"></span></li>
								<li><span id="labelHistory" title="折线" onclick="activate(P.PlotTypes.POLYLINE)"></span></li>
								<li><span id="labelStop" title="曲线" onclick="activate(P.PlotTypes.CURVE)"></span></li>
							</ul>
						</div>
					</div>
					<!-- 工具栏 -->
				</div>
			</div>
			<div class="right-side">
				<h4 class="res-list">结果列表</h4>
				<div class="lists-nav clearfix">
					<div>
						<p id="liveStation">直播台</p>
						<i></i>
					</div>
					<div>
						<p id="police-force">全部人员</p>
						<i></i>
					</div>
					<div>
						<p id="result">查询结果</p>
						<i></i>
					</div>
				</div>
				<div class="liveStation container">
					<ul id="timeline" class="timeline"></ul>
				</div>
				<div class="police">
					<div class="police-city">
						<div class="inner-nav clearfix">
							<span class="police-man blue"><p>人员</p> <i></i></span>
							<span class="police-cat"><p>车辆</p> <i></i></span>
							<span class="police-video"><p>视频</p> <i></i></span>
						</div>
						<ul class="police-list" id="policeAll">
							<div class="checkbox checkbox-success radio-div tab-content-bg" style="margin-top: 5px;">
								<div class="police-type">
									<input type="checkbox" name="police" value="01" class="type-input" id="AuxiliaryPolice">
									<label for="AuxiliaryPolice" class="police-label" >刑侦</label>
								</div>
								<div class="police-type">
									<input type="checkbox" name="police" value="02" class="type-input" id="PeoplePolice">
									<label for="PeoplePolice" class="police-label">治安</label>
								</div>
								<div class="police-type">
									<input type="checkbox" name="police" value="03" class="type-input" id="TrafficPolice">
									<label for="TrafficPolice"  class="police-label">交警</label>
								</div>
								<div class="police-type">
									<input type="checkbox" name="police" value="04" class="type-input" id="SWAT">
									<label for="SWAT" class="police-label">特警</label>
								</div>
								<div class="police-type">
									<input type="checkbox" name="police" value="05" class="type-input" id="ArmedPolice">
									<label for="ArmedPolice" class="police-label">巡警</label>
								</div>
							</div>
							<div id="ztreePolice" class="ztree" style="margin-left: 10px;"></div>
						</ul>
						<ul class="cat-list" id="carAll">
							<div id="carAllUl" class="ztree"></div>
						</ul>
						<ul class="inner-video" id="videoAll">
							<div class="input-append" style="margin-top: 5px;margin-left:10px;">
								<input id="secVideo" name="secVideo" class="input-medium"
									   type="text" value="" maxlength="100"
									   style="width: 160px; height: 30px;margin-top:0px; margin-left: 1px;" />
								<a id="videoButton" href="javascript:" class="btn"
								   style="border-radius: 0 14px 14px 0;height: 30px;">&nbsp;<i
										class="icon-search"></i>&nbsp;
								</a>&nbsp;&nbsp;
							</div>
							<div id="ztreeVideo" class="ztree"></div>
						</ul>
					</div>
					<div class="box dispatch hidden">
						<div class="hd">
							<h5>调度区域</h5>
						</div>
						<div class="bd clearfix" id="dispatch">

						</div>
						<input id="btncontrolpolice" type="button" onclick="policeClick()" style="margin-left: 66px;" class="btn btn-primary" value="出警" />
					</div>
				</div>
				<div class="periphery">
					<div class="police-result">
						<div class="inner-nav clearfix" >
							<span class="police-man blue"><p>人员结果</p> <i></i></span>
							<span class="police-cat"><p>车辆结果</p> <i></i></span>
							<span class="police-video"><p>视频结果</p> <i></i></span>
						</div>
						<ul class="police-list ztree" id="policeResult">
							<table class="layui-hide" id="queryPolice" lay-filter="test">

							</table>
						</ul>
						<ul id="carResult" class="cat-list ztree"></ul>
						<ul class="inner-video ztree" id="videoResult">
							<div id="ztree" class="ztree"></div>
						</ul>
					</div>
					<div class="box dispatch hidden">
						<div class="hd">
							<h5>调度区域</h5>
						</div>
						<div class="bd clearfix" id="dispatch">

						</div>
						<input id="btncontrolpolice" type="button" onclick="policeClick()" style="margin-left: 12px;" class="btn btn-primary" value="出警"/>
					</div>
				</div>
			</div>
			<!--下侧  -->
			<div class="bottom-side">
				<div calss="bottom-side-head"><h5 class="res-list">案件处置流程<i class="ic3"></i></h5></div>
				<div class="bottom-side-center">
					<div class="bottom-record" style="line-height: 12px;text-align:center;padding:5px;border:3px #07e8ee solid;color:#c7621d;position: absolute;top:42px;right:55px;cursor: pointer;" onclick="findHanleLog()">过程记录</div>
					<div class="bottom-bnstructions" style="line-height: 12px;text-align:center;padding:5px;border:3px #07e8ee solid;color:#c7621d;position: absolute;top:42px;right:10px;cursor: pointer;" onclick="Instructions()">批示</div>
					<div class="bottom-flow clearfix" style="margin-top: 20px;" id="bottom-flow"></div>
					<div class="bottom-table">
						<table class="layui-hide" id="flowTable"></table>
					</div>
				</div>
			</div>
			<!--下侧 -->
		</div>
	</div>
	<!-- 周边查询弹框 -->
	<div id="nearSearchDialog" style="display: none;margin: 15px 8px 0 8px">
		<div>
			<label>图层：</label>
			<div class="checkbox checkbox-success radio-div tab-content-bg" style="display: inline-block;margin-bottom: 5px;padding-top: 5px;padding-bottom: 5px;">
				<input type="checkbox" name="layer" value="1"  class="type-input" id="carLayer" checked="checked">
				<label for="carLayer" style="padding:0;width:65px;"><i title="车辆" class="icon iconfont icon-cheliangguanli"></i>车辆</label>
				<input type="checkbox" name="layer" value="1" class="type-input" id="peoLayer" checked="checked">
				<label for="peoLayer" style="padding:0;width:65px;"><i title="人员" class="icon iconfont icon-xingren"></i>人员</label>
				<input type="checkbox" name="layer" value="1" class="type-input" id="videoLayer" checked="checked">
				<label for="videoLayer" style="padding:0;width:65px;"><i title="视频" class="icon iconfont icon-shipinjiankong1"></i>视频</label>
			</div>
		</div>
		<div>
			<label>范围：</label>
			<div class="radio  radio-success" style="display: inline-block;">
				<input type="radio" id="TodayRadio" value="300" name="distance">
				<label for="TodayRadio">300m</label>
			</div>
			<div class="radio  radio-success" style="display: inline-block;">
				<input type="radio" id="WeekRadio" value="500" name="distance">
				<label for="WeekRadio">500m</label>
			</div>
			<div class="radio  radio-success" style="display: inline-block;">
				<input type="radio" id="YearRadio" value="1000" name="distance" checked="checked">
				<label for="YearRadio">1000m</label>
			</div>
			<div class="radio  radio-success" style="display: inline-block;">
				<input type="radio" id="customRadio" value="custom" name="distance">
				<label for="customRadio">自定义</label>
				<input type="number" min="0" id="customKM" value="" style="width:65px;" name="distance" disabled="disabled">m
			</div>
		</div>
	</div>
	<!-- 智能布控弹框 -->
	<div id="setControlDialog" style="display: none; margin: 15px 8px 0 8px">
		<div class="control-group">
			<div class="controls" style="display: inline-block;padding:5px;">
				<label>案发时间：</label>
				<label id="alarmTime"></label>
			</div>
			<div class="controls" style="display: inline-block;padding: 5px;">
				<label class="laycontrol-labelui-form-label">逃跑时长：</label>
				<input id="escapeTime" type="number" min='0' value="" name="escapeTime" style='width: 174px; height: 25px'>
				<label>分钟</label>
			</div>
			<div class="controls" style="display: inline-block;padding:5px;">
				<label>逃跑方式：</label>
				<select id="runWay" class="input-medium" style='width: 175px;'>
					<option selected="selected" value="">请选择</option>
				</select>
			</div>
		</div>
	</div>

	<!-- 周边查询弹框 -->
	<div style="display: none;">
		<sys:treeselect id="areaSelect" name="" value="" labelName="" labelValue="" title="所属区域"  url="/sys/area/treeData" cssClass="" allowClear="flase" notAllowSelectParent="true"/>
	</div>
	<script src="${ctxStatic}/layer-v3.1.1/layer/layer.js" type="text/javascript"></script>
	<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.js" type="text/javascript"></script>
	<script src="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.js" type="text/javascript"></script>
	<script src="${ctxStatic}/ol/ol.js" type="text/javascript"></script>
	<script src="${ctxStatic}/modules/map/js/draw/js/p-ol3.debug.js" type="text/javascript"></script>
	<script src="${ctxStatic}/modules/map/js/mapconfig.js" type="text/javascript"></script>
	<script src="${ctxStatic}/modules/map/js/commonMapFlat.js" type="text/javascript"></script>
	<script src="${ctxStatic}/common/common.js" type="text/javascript"></script>
	<script src="${ctxStatic}/common/alarm.js" type="text/javascript"></script>
	<script src="${ctxStatic}/flat/alarmCommon.js" type="text/javascript"></script>
	<script src="${ctxStatic}/flat/layerCommon.js" type="text/javascript"></script>
	<script src="${ctxStatic}/flat/toolCommon.js" type="text/javascript"></script>
	<script src="${ctxStatic}/common/ztreeSech.js" type="text/javascript"></script>
	<script src="${ctxStatic}/jquery/hammer.js" type="text/javascript"></script>
	<script src="${ctxStatic}/flat/planManage/planManageTree.js" type="text/javascript"></script>
	<script src="${ctxStatic}/flat/realtimeAlarmSituation/video.js" type="text/javascript"></script>
	<script src="${ctxStatic}/flat/planManage/planManageIndex.js" type="text/javascript"></script>
	<script src="${ctxStatic}/flat/planManage/planManage.js" type="text/javascript"></script>
	<script src="${ctxStatic}/flat/planManage/planManageMap.js" type="text/javascript"></script>
	<script type="text/javascript">
		var leftWidth = 0; // 左侧窗口大小
		var htmlObj = $("html"), mainObj = $("#main"),frameObj = $("#right,.wrapper,.map-wrapper"),headObj=$("#header"),slidesWrap=$('.slides-wrap');
		function wSize(){
			var strs = getWindowSize().toString().split(",");
			frameObj.height(strs[0]-5);
			//var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
			frameObj.width($("#content").width()- leftWidth);
			slidesWrap.height(frameObj.height()-$('.left-lists').height()- $('.status-explain').height());
			var ulHeight = frameObj.height()  - $('.res-list').height()- $('.lists-nav').height()  -294;
			$(".right-side div>ul").height(ulHeight);
			$("#timeline").css('height','auto');
			if(alarmId!=''){
				//$('#alarmInfoDiv').height($('.left-side').height()-$('#planInfoDiv').height()-$('#contactInfoDiv').height()-5);
				$('#alarmInfoDivDetails').height($('.left-side').height()-$('#planInfoDiv').height()-$('#contactInfoDiv').height()-$('#alarmInfoDiv h5').height()-10);
			}
			/*var messageHeight =  frameObj.height() - $('.right-lists').height() - $('.information-input').height() - $('.police-go').height() - $('.right-nav').height() - $('.police-interaction').height();
            $(".message-list").height(messageHeight);  */
			$('.live').height(frameObj.height() -$('.lists-nav').height()-$('.res-list').height()-$('#sending').height())
			$('.live > div:first-of-type').height(frameObj.height() -$('.lists-nav').height()-$('.res-list').height()-$('#sending').height()-30);
			$('.bottom-side').width(frameObj.width()-$('.left-side').width()-$('.right-side').width()-5)
		}
	</script>
	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>