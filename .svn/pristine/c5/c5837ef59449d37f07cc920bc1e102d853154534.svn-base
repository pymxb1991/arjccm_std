<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>人口信息</title>
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
<script src="${ctxStatic}/layer-v3.1.1/layer/layer.js"></script>
<link rel="stylesheet"
	href="${ctxStatic}/bootstrap/2.3.1/css_default/bootstrap.min.css">

<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css"
	rel="stylesheet" />
<link href="${ctxStatic}/bootstrap/checkbox-radio.css" type="text/css"
	rel="stylesheet" />

<script src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome.min.css">

<!--[if lte IE 7]>
    <link rel="stylesheet" href="../bootstrap/2.3.1/awesome/font-awesome-ie7.css">
    <![endif]-->
<!--[if lte IE 6]>
    <link rel="stylesheet" href="../bootstrap/bsie/css/bootstrap-ie6.min.css">
    <script src="../bootstrap/bsie/js/bootstrap-ie.min.js"></script>
    <![endif]-->
<link rel="stylesheet" href="${ctxStatic}/asidenav/asidenav.css">
<script type="text/javascript">
        var ctx = '${ctx}', ctxStatic = '${ctxStatic}';
    </script>
<script src="${ctxStatic}/asidenav/asidenav.js"></script>
<script src="${ctxStatic}/asidenav/jquery.min.js"></script>
<link rel="stylesheet" href="${ctxStatic}/ol/ol.css" type="text/css">
<link rel="stylesheet" href="${ctxStatic}/modules/map/css/map.css"
	type="text/css">
<link rel="stylesheet"
	href="${ctxStatic}/common/index/css/indexCommon.css">
<link rel="stylesheet"
	href="${ctxStatic}/common/index/css/statSituationStatisticsXinmi.css">
	<link rel="stylesheet" href="${ctxStatic}/common/index/css/FanZhuan-animate.css">
<link rel="stylesheet" href="${ctxStatic}/common/index/css/indexXinmi.css">
<link rel="stylesheet" href="${ctxStatic}/modules/map/css/house.css"
	type="text/css">
<link rel="stylesheet"
	href="${ctxStatic}/modules/map/css/publicinstitutions-1.css">
<script src="${ctxStatic}/ol/ol.js"></script>
<script src="${ctxStatic}/d3/d3.v4.min.js"></script>
<script src="${ctxStatic}/jquery-ui-1.12.1/jquery-ui.min.js"
	type="text/javascript"></script>
	<script src="${ctxStatic}/jquery/jquery.cookie.js" type="text/javascript"></script>
	
	<script src="${ctxStatic}/modules/map/js/mapconfig.js"></script>
<script src="${ctxStatic}/modules/map/js/commonMap.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/scroll.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script src="${ctxStatic}/jquery/jquery.SuperSlide.2.1.1.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/tongrenshi.js"></script>
<script src="${ctxStatic}/custom/date/date.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/indexXinmi.js"></script>
<script src="${ctxStatic}/modules/map/js/publicinstitutions-1.js"></script>

<style>
.map-1   .results {
	margin-top: 2%;
}

.common-pading {
	width: auto;
}

.map-1    .common-color {
	font-family: "Helvetica Neue", Helvetica, "Microsoft YaHei", Arial,
		sans-serif;
}

.radio-div {
	margin-top: -2px !important;
	margin-left: 10px;
}

#myModal table td {
	color: #555
}

.map-1  .calendar-center div.alarm-name {
	text-align: center;
	line-height: 30px;
}

.map-1  .calendar-center div.alarm-num {
	font-size: 32px;
}

.header h5 {
	line-height: 45px;
	letter-spacing: 1px;
	font-size: 21px;
}

.common-color {
	font-family: "Helvetica Neue", Helvetica, "Microsoft YaHei", Arial,
		sans-serif;
}
</style>
<script>
        $(function(){
            $("#indexhover,#indexNav").hover(function(){
                $('#indexNav').show();
            },function(){
                $('#indexNav').hide();
            })
        })

    </script>
</head>
<body>
	<div class="container-fluid slideTxtBox"
		style="height: 100%; overflow: hidden; position: relative;" id="main">
		<div class="context" content="${ctx}"></div>
		<div class="row-fluid header">
			<div class="span1" style="position: relative; margin-top: 5px">
				<!-- 菜单 -->
				<div
					style="z-index: 9999; display: block; position: absolute;">
					<svg width="0" height="0">
	                    <defs>
	                        <filter id="goo">
	                            <fegaussianblur in="SourceGraphic"
								stdDeviation="10" result="blur"></fegaussianblur>
	                            <fecolormatrix in="blur" mode="matrix"
								values="1 0 0 0 0  0 1 0 0 0  0 0 1 0 0  0 0 0 19 -9"
								result="goo"></fecolormatrix>
	                            <fecomposite in="SourceGraphic" in2="goo"
								operator="atop"></fecomposite>
	                        </filter>
	                    </defs>
	                </svg>
					<%-- <div class="aside-nav bounceInUp animated" id="aside-nav">
						<label for="" class="aside-menu" title=""><a href="${ctx}" class="menu-item">主面板</a></label>
						 <a
							href="/arjccm/a/index" title="首页" class="menu-item menu-second">首页</a>
						<a href="/arjccm/a/sys/map/statIncidentStatistics" title="事件"
							class="menu-item menu-third">事件</a> <a href="${ctx}/sys/map/statIndexCom"
							title="概况" class="menu-item  menu-fourth">概况</a>
					</div> --%>
				</div>
				<!-- 菜单 -->
				<!-- <div class="index-nav-common">
                <span id="indexhover"> <a href="###"> <i
                        class="icon-th-list align-top bigger-125"></i> 导航
                </a>
                </span>
            </div>
            <div class="clearfix" id="indexNav">
                <ul class="indexnav clearfix">
                    <li><a href="/arjccm/a/index"> <i
                            class="icon-home align-top bigger-125"></i> 首页
                    </a></li>
                    <li class="lastli"><a href="/arjccm/a"> <i
                            class="icon-folder-close align-top bigger-125"></i> 事件
                    </a></li>
                </ul>
            </div> -->
			</div>
			<div class="span9 " style="position: relative;">
				<%-- <h5>${fns:getConfig('productName')}</h5> --%>
				<!-- <h5>新密市公安局"一村(格)一警"信息系统</h5> -->
				<a href="/arjccm/a/index" title="首页">
					<h5 class="header-logo">${fns:getConfig('productName')}</h5>
				</a>

			</div>
			<div class="span1">
				<div class="Logout" style="margin-left: 202px">
					<span> <a href="/arjccm/a/logout"> <i
							class="icon-off align-top bigger-125"></i> 退出
					</a>
					</span>
				</div>
			</div>
		</div>
		<div class="bd">

			<div class="ca-container">
				<div class="map-1">
					<div class="row-fluid height100" style="margin-top: 5px;">
						<div class="span9 shadow height100" style="position: relative;">

							<!-- 缩放控件-->
							<div id="zoombar" class="zoombar"
								style="position: absolute; bottom: -88px; right: 122px; height: 300px; z-index: 9">
								<!-- 	<div style="position: absolute; width: 63px; height: 62px;">
			<img style="position: relative; width: 63px; height: 62px" src="/arjccm/static/modules/map/images/zoom/zoompanbar_bg.png">
	 	<div id="Control.PanZoomBar.panup" style="position: absolute; left: 24px; top: 5px; width: 16px; height: 16px; cursor: pointer;" class="olButton olpanup" onclick="Map.panDirection('north')">
				<img id="Control.PanZoomBar.panup_innerImage" style="position: relative; width: 16px; height: 16px;" class="olAlphaImg" src="/arjccm/static/modules/map/images/zoom/north-mini.png">
			</div>
			<div id="Control.PanZoomBar.panleft" style="position: absolute; left: 6px; top: 23px; width: 16px; height: 16px; cursor: pointer;" class="olButton olpanleft" onclick="Map.panDirection('west')">
				<img id="Control.PanZoomBar.panleft_innerImage" style="position: relative; width: 16px; height: 16px;" class="olAlphaImg" src="/arjccm/static/modules/map/images/zoom/west-mini.png">
			</div>
			<div id="Control.PanZoomBar.panright" style="position: absolute; left: 42px; top: 23px; width: 16px; height: 16px; cursor: pointer;" class="olButton olpanright" onclick="Map.panDirection('east')">
				<img id="Control.PanZoomBar.panright_innerImage" style="position: relative; width: 16px; height: 16px;" class="olAlphaImg" src="/arjccm/static/modules/map/images/zoom/east-mini.png">
			</div>
			<div id="Control.PanZoomBar.pandown" style="position: absolute; left: 24px; top: 39px; width: 16px; height: 16px; cursor: pointer;" class="olButton olpandown" onclick="Map.panDirection('south')">
				<img id="Control.PanZoomBar.pandown_innerImage" style="position: relative; width: 16px; height: 16px;" class="olAlphaImg" src="/arjccm/static/modules/map/images/zoom/south-mini.png">
			</div>
		</div>  -->
								<div id="Control.PanZoomBar.zoomin"
									style="position: absolute; left: 24px; top: 20px; width: 16px; height: 16px; cursor: pointer;"
									class="olButton olzoomin" onclick="Map.zoomInOut('in')">
									<img id="Control.PanZoomBar.zoomin_innerImage"
										style="position: relative; width: 16px; height: 16px;"
										class="olAlphaImg"
										src="/arjccm/static/modules/map/images/zoom/zoom-plus-mini.png">
								</div>
								<div
									style="background-image: url('/arjccm/static/modules/map/images/zoom/zoombar.png'); background-size: 50% 100%; background-repeat-y: no-repeat; left: 24px; top: 39px; width: 16px; height: 135px; position: absolute; cursor: pointer;"
									id="ControlPanZoomBar" class="olButton olPanZoomBar">
									<div id="PanZoomBar"
										class="PanZoomBar ui-draggable ui-draggable-handle"
										style="position: absolute; left: 0px; top: 116px; width: 16px; height: 16px; cursor: move;">
										<img id="Control.PanZoomBar.OpenLayers.Map_7_innerImage"
											style="position: relative; width: 16px; height: 16px;"
											class="olAlphaImg"
											src="/arjccm/static/modules/map/images/zoom/slider.png">
									</div>
									<div id="ControlPanZoomIndex"
										style="position: absolute; width: 66px; height: 161px; left: 17px; top: 0px; overflow: hidden">
										<img id=""
											style="position: absolute; left: 0px; top: 120px; width: 65px; height: 16px;"
											src="/arjccm/static/modules/map/images/zoom/city-index.png">
										<img id=""
											style="position: absolute; left: 0px; top: 82px; width: 65px; height: 16px;"
											src="/arjccm/static/modules/map/images/zoom/district-index.png">
										<img id=""
											style="position: absolute; left: 0px; top: 44px; width: 65px; height: 16px;"
											src="/arjccm/static/modules/map/images/zoom/street-index.png">
										<img id=""
											style="position: absolute; left: 0px; top: 5px; width: 65px; height: 16px;"
											src="/arjccm/static/modules/map/images/zoom/community-index.png">
										<!-- 				<img id="" style="position: absolute; left: 0px; top: 29px; width: 65px; height: 16px;" src="/arjccm/static/modules/map/images/zoom/grid-index.png">
 -->
									</div>
								</div>
								<div id="Control.PanZoomBar.zoomout"
									style="position: absolute; left: 24px; top: 171px; width: 16px; height: 16px; cursor: pointer;"
									class="olButton olzoomout" onclick="Map.zoomInOut('out')">
									<img id="Control.PanZoomBar.zoomout_innerImage"
										style="position: relative; width: 16px; height: 16px;"
										class="olAlphaImg"
										src="/arjccm/static/modules/map/images/zoom/zoom-minus-mini.png">
								</div>
							</div>
							<!-- 缩放控件-->


							<div class="yuzhitu"></div>
							<div class="yuzhitu0"></div>
							<div class="yuzhitu1"></div>
							<div class="yuzhitu2"></div>
							<div class="yuzhitu3"></div>
							<!--  <div class="yuzhitu4"></div> -->

							<div class="total-zongshu">

								<table style="height: 100%; width: 100%">
									<tbody>
										<tr>
											<td>
												<p class="table-info-title" id="table-info1">新密市</p>
												<table class="map-table">

													<tbody>
														<tr>
															<td align="left" class="table-info-name">实有人口：</td>
															<td><div class="map-calss1 common-color map-calss"
																	style="color: #fff" id="eachAll11">
																	&nbsp;<span id="shiyourenkou1">901867</span>
																</div></td>
														</tr>
														<tr>
															<td align="left" class="table-info-name">实有房屋：</td>
															<td><div class="map-calss6 common-color map-calss"
																	style="color: #fff" id="eachAll41">
																	&nbsp;<span id="shiyoufangwu1">192924</span>
																</div></td>
														</tr>
														<tr>
															<td align="left" class="table-info-name">实有单位：</td>
															<td><div class="map-calss3 common-color map-calss"
																	style="color: #fff" id="eachAll41">
																	&nbsp;<span id="shiyoudanwei1">2924</span>
																</div></td>
														</tr>
														<tr>
															<td align="left" class="table-info-name">常住人口：</td>
															<td><div class="map-calss2 common-color map-calss"
																	style="color: #fff" id="eachAll21">
																	&nbsp;<span id="cahngzhurenkou1">759249</span>
																</div></td>
														</tr>
														<tr>
															<td align="left" class="table-info-name">流动人口：</td>
															<td><div class="map-calss3 common-color map-calss"
																	style="color: #fff" id="eachAll31">
																	&nbsp;<span id="liudongrenkou1">140012</span>
																</div></td>
														</tr>
														<tr>
															<td align="left" class="table-info-name">重点人口：</td>
															<td><div class="map-calss4 common-color map-calss"
																	style="color: #fff" id="eachAll41">
																	&nbsp;<span id="zhongdianrenkou1">1420
																</div></td>
														</tr>

														<tr>
															<td align="left" class="table-info-name">社区民警：</td>
															<td onclick="sshequminjingbFun('民警')"><div
																	class="map-calss5 common-color map-calss"
																	style="color: #fff" id="eachAll51">
																	&nbsp;<span id="shequminjing1">60</span>
																</div></td>
														</tr>
														<tr>
															<td align="left" class="table-info-name">包村辅警：</td>
															<td><div class="map-calss7 common-color map-calss"
																	style="color: #fff" id="eachAll61">
																	&nbsp;<span id="baocunfujing1">290</span>
																</div></td>
														</tr>
													</tbody>
												</table>
											</td>
										</tr>
									</tbody>
								</table>

							</div>

							<div class="xinmimap"></div>
							<!--    <div class="quxian">
                        <ul style="cursor: pointer;">
                            <li class="xiangzhenli">乡/镇</li>
                            <li class="shequli">社区</li>
                            <li class="loudongli">楼栋</li>
                        </ul>
                    </div> -->
							<div class="shequ1">
								<div class="shequxinxi">
									<div class="paichusuo">
										<table style="width: 100%; height: 100%">
											<tr>
												<td align="left"
													style="color: red; font-size: 16px; font-weight: bold;">青屏苑小区</td>
											</tr>
											<tr>
												<td align="left">实有人口：</td>
												<td align="left"><span style="color: red">3120</span>人</td>
											</tr>
											<tr>
												<td align="left">常住人口：</td>
												<td align="left"><span style="color: red">2900</span>人</td>
											</tr>
											<tr>
												<td align="left">重点人员：</td>
												<td align="left"><span style="color: red">20</span>人</td>
											</tr>
										</table>
									</div>
								</div>
							</div>

							<div class="loudong1">
								<div class="loudongxinxi">
									<div class="paichusuo">
										<table style="width: 100%; height: 100%">
											<tr>
												<td align="left"
													style="color: red; font-size: 16px; font-weight: bold;">青屏苑20号</td>
											</tr>
											<tr>
												<td align="left">实有人口：</td>
												<td align="left"><span style="color: red">651</span>人</td>
											</tr>
											<tr>
												<td align="left">常住人口：</td>
												<td align="left"><span style="color: red">650</span>人</td>
											</tr>
											<tr>
												<td align="left">重点人员：</td>
												<td align="left"><span style="color: red">6</span>人</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
							<div class="paichusuosuoyou">

								<div class="jianshanzhen sxinxib"  onclick="sxinxibFun('尖山派出所')" attr-name="尖山派出所" attr-shiyou="11020" attr-changzhu="11020" attr-zhongdian="21">
									<!--<div class="paichusuo">
										 <table style="width: 100%; height: 100%">
											<tr>
												<td align="left"
													style="color: red; font-size: 16px; font-weight: bold;">尖山派出所</td>
											</tr>
											<tr>
												<td align="left">实有人口：</td>
												<td align="left"><span style="color: red">11020</span>人</td>
											</tr>
											<tr>
												<td align="left">常住人口：</td>
												<td align="left"><span style="color: red">11020</span>人</td>
											</tr>
											<tr>
												<td align="left">重点人员：</td>
												<td align="left"><span style="color: red">21</span>人</td>
											</tr>
										</table> 
									</div>-->
								</div>
								<div class="micun" onclick="sxinxibFun('米村派出所')"  attr-name="米村派出所" attr-shiyou="38132" attr-changzhu="38132" attr-zhongdian="60">
									<!-- <div class="paichusuo" onclick="sxinxibFun('米村派出所')" >
										<table style="width: 100%; height: 100%">
											<tr>
												<td align="left"
													style="color: red; font-size: 16px; font-weight: bold;">米村派出所</td>
											</tr>
											<tr>
												<td align="left">实有人口：</td>
												<td align="left"><span style="color: red">38132</span>人</td>
											</tr>
											<tr>
												<td align="left">常住人口：</td>
												<td align="left"><span style="color: red">38132</span>人</td>
											</tr>
											<tr>
												<td align="left">重点人员：</td>
												<td align="left"><span style="color: red">60</span>人</td>
											</tr>
										</table>
									</div> -->
								</div>
								<div class="yuanzhuang"  onclick="sxinxibFun('袁庄派出所')" attr-name="袁庄派出所" attr-shiyou="26110" attr-changzhu="24110" attr-zhongdian="106">
									<!-- <div class="paichusuo" onclick="sxinxibFun('袁庄派出所')">
										<table style="width: 100%; height: 100%">
											<tr>
												<td align="left"
													style="color: red; font-size: 16px; font-weight: bold;">袁庄派出所</td>
											</tr>
											<tr>
												<td align="left">实有人口：</td>
												<td align="left"><span style="color: red">26110</span>人</td>
											</tr>
											<tr>
												<td align="left">常住人口：</td>
												<td align="left"><span style="color: red">24110</span>人</td>
											</tr>
											<tr>
												<td align="left">重点人员：</td>
												<td align="left"><span style="color: red">106</span>人</td>
											</tr>
										</table> 
									</div>-->
								</div>
								<div class="baizhai"  onclick="sxinxibFun('白寨派出所')" attr-name="白寨派出所" attr-shiyou="71000" attr-changzhu="70003" attr-zhongdian="88">
									<!-- <div class="paichusuo" onclick="sxinxibFun('白寨派出所')">
										<table style="width: 100%; height: 100%">
											<tr>
												<td align="left"
													style="color: red; font-size: 16px; font-weight: bold;">白寨派出所</td>
											</tr>
											<tr>
												<td align="left">实有人口：</td>
												<td align="left"><span style="color: red">71000</span>人</td>
											</tr>
											<tr>
												<td align="left">常住人口：</td>
												<td align="left"><span style="color: red">70003</span>人</td>
											</tr>
											<tr>
												<td align="left">重点人员：</td>
												<td align="left"><span style="color: red">88</span>人</td>
											</tr>
										</table>
									</div> -->
								</div>
								<div class="xinmichengqu" onclick="sxinxibFun('青屏街派出所')"  attr-name="青屏街派出所" attr-shiyou="140354" attr-changzhu="130594" attr-zhongdian="108">
									<!-- <div class="paichusuo" onclick="sxinxibFun('青屏街派出所')">
								
									
										<table style="width: 100%; height: 100%">
											<tr class="paichusuo-header">
												<td align="left" >新密城区</td>
												<td></td>
											</tr>
											<tr>
												<td align="left">实有人口：</td>
												<td align="left"><span style="color: red">140354</span>人</td>
											</tr>
											<tr>
												<td align="left">常住人口：</td>
												<td align="left"><span style="color: red">130594</span>人</td>
											</tr>
											<tr>
												<td align="left">重点人员：</td>
												<td align="left"><span style="color: red">108</span>人</td>
											</tr>
										</table>
									</div> -->
								</div>
								<div class="niudian"  onclick="sxinxibFun('牛店派出所')"  attr-name="牛店派出所" attr-shiyou="51024" attr-changzhu="50024" attr-zhongdian="97">
								<!-- 	<div class="paichusuo" onclick="sxinxibFun('牛店派出所')">
										<table style="width: 100%; height: 100%">
											<tr>
												<td align="left"
													style="color: red; font-size: 16px; font-weight: bold;">牛店派出所</td>
											</tr>
											<tr>
												<td align="left">实有人口：</td>
												<td align="left"><span style="color: red">51024</span>人</td>
											</tr>
											<tr>
												<td align="left">常住人口：</td>
												<td align="left"><span style="color: red">50024</span>人</td>
											</tr>
											<tr>
												<td align="left">重点人员：</td>
												<td align="left"><span style="color: red">97</span>人</td>
											</tr>
										</table>
									</div> -->
								</div>
								<div class="chenguan"  onclick="sxinxibFun('城关派出所')"  attr-name="城关派出所" attr-shiyou="34793" attr-changzhu="31793" attr-zhongdian="40">
									<!-- <div class="paichusuo" onclick="sxinxibFun('城关派出所')">
										<table style="width: 100%; height: 100%">
											<tr>
												<td align="left"
													style="color: red; font-size: 16px; font-weight: bold;">城关派出所</td>
											</tr>
											<tr>
												<td align="left">实有人口：</td>
												<td align="left"><span style="color: red">34793</span></td>
											</tr>
											<tr>
												<td align="left">常住人口：</td>
												<td align="left"><span style="color: red">31793</span>人</td>
											</tr>
											<tr>
												<td align="left">重点人员：</td>
												<td align="left"><span style="color: red">40</span>人</td>
											</tr>
										</table>
									</div> -->

								</div>
								<div class="yuecun"  onclick="sxinxibFun('岳村派出所')"  attr-name="岳村派出所" attr-shiyou="43527" attr-changzhu="41523" attr-zhongdian="70" >
									<!-- <div class="paichusuo" onclick="sxinxibFun('岳村派出所')">
										<table style="width: 100%; height: 100%">
											<tr>
												<td align="left"
													style="color: red; font-size: 16px; font-weight: bold;">岳村派出所</td>
											</tr>
											<tr>
												<td align="left">实有人口：</td>
												<td align="left"><span style="color: red">43527</span>人</td>
											</tr>
											<tr>
												<td align="left">常住人口：</td>
												<td align="left"><span style="color: red">41523</span>人</td>
											</tr>
											<tr>
												<td align="left">重点人员：</td>
												<td align="left"><span style="color: red">70</span>人</td>
											</tr>
										</table>
									</div> -->

								</div>



								<div class="laiji"  onclick="sxinxibFun('来集派出所')"  attr-name="来集派出所" attr-shiyou="60308" attr-changzhu="58008" attr-zhongdian="77">
									<!-- <div class="paichusuo" onclick="sxinxibFun('来集派出所')">
										<table style="width: 100%; height: 100%">
											<tr>
												<td align="left"
													style="color: red; font-size: 16px; font-weight: bold;">来集派出所</td>
											</tr>
											<tr>
												<td align="left">实有人口：</td>
												<td align="left"><span style="color: red">60308</span>人</td>
											</tr>
											<tr>
												<td align="left">常住人口：</td>
												<td align="left"><span style="color: red">58008</span>人</td>
											</tr>
											<tr>
												<td align="left">重点人员：</td>
												<td align="left"><span style="color: red">77</span>人</td>
											</tr>
										</table>
									</div> -->

								</div>
								<div class="pingmo"  onclick="sxinxibFun('平陌派出所')"  attr-name="平陌派出所" attr-shiyou="42351" attr-changzhu="42330" attr-zhongdian="58">
									<!-- <div class="paichusuo" onclick="sxinxibFun('平陌派出所')">
										<table style="width: 100%; height: 100%">
											<tr>
												<td align="left"
													style="color: red; font-size: 16px; font-weight: bold;">平陌派出所</td>
											</tr>
											<tr>
												<td align="left">实有人口：</td>
												<td align="left"><span style="color: red">42351</span>人</td>
											</tr>
											<tr>
												<td align="left">常住人口：</td>
												<td align="left"><span style="color: red">42330</span>人</td>
											</tr>
											<tr>
												<td align="left">重点人员：</td>
												<td align="left"><span style="color: red">58</span>人</td>
											</tr>
										</table>
									</div> -->
								</div>
								<div class="chaohua"  onclick="sxinxibFun('超化派出所')"   attr-name="超化派出所" attr-shiyou="79767" attr-changzhu="75060" attr-zhongdian="148">
								<!-- 	<div class="paichusuo" onclick="sxinxibFun('超化派出所')">
										<table style="width: 100%; height: 100%">
											<tr>
												<td align="left"
													style="color: red; font-size: 16px; font-weight: bold;">超化派出所</td>
											</tr>
											<tr>
												<td align="left">实有人口：</td>
												<td align="left"><span style="color: red">79767</span>人</td>
											</tr>
											<tr>
												<td align="left">常住人口：</td>
												<td align="left"><span style="color: red">75060</span>人</td>
											</tr>
											<tr>
												<td align="left">重点人员：</td>
												<td align="left"><span style="color: red">148</span>人</td>
											</tr>
										</table>
									</div> -->
								</div>
								<div class="liuzhai"  onclick="sxinxibFun('刘寨派出所')"  attr-name="刘寨派出所" attr-shiyou="52033" attr-changzhu="51030" attr-zhongdian="86">
									<!-- <div class="paichusuo" onclick="sxinxibFun('刘寨派出所')">
										<table style="width: 100%; height: 100%">
											<tr>
												<td align="left"
													style="color: red; font-size: 16px; font-weight: bold;">刘寨派出所</td>
											</tr>
											<tr>
												<td align="left">实有人口：</td>
												<td align="left"><span style="color: red">52033</span>人</td>
											</tr>
											<tr>
												<td align="left">常住人口：</td>
												<td align="left"><span style="color: red">51030</span>人</td>
											</tr>
											<tr>
												<td align="left">重点人员：</td>
												<td align="left"><span style="color: red">86</span>人</td>
											</tr>
										</table>
									</div> -->
								</div>
								<div class="quliang"  onclick="sxinxibFun('曲梁派出所')"  attr-name="曲梁派出所" attr-shiyou="86124" attr-changzhu="76100" attr-zhongdian="104">
									<!-- <div class="paichusuo" onclick="sxinxibFun('曲梁派出所')">
										<table style="width: 100%; height: 100%">
											<tr>
												<td align="left"
													style="color: red; font-size: 16px; font-weight: bold;">曲梁派出所</td>
											</tr>
											<tr>
												<td align="left">实有人口：</td>
												<td align="left"><span style="color: red">86124</span>人</td>
											</tr>
											<tr>
												<td align="left">常住人口：</td>
												<td align="left"><span style="color: red">76100</span>人</td>
											</tr>
											<tr>
												<td align="left">重点人员：</td>
												<td align="left"><span style="color: red">104</span>人</td>
											</tr>
										</table>
									</div> -->
								</div>
								<div class="dahuai" onclick="sxinxibFun('大隗派出所')"  attr-name="大隗派出所" attr-shiyou="65299" attr-changzhu="63250" attr-zhongdian="88">
									<!-- <div class="paichusuo" onclick="sxinxibFun('大隗派出所')">
										<table style="width: 100%; height: 100%">
											<tr>
												<td align="left"
													style="color: red; font-size: 16px; font-weight: bold;">大隗派出所</td>
											</tr>
											<tr>
												<td align="left">实有人口：</td>
												<td align="left"><span style="color: red">65299</span>人</td>
											</tr>
											<tr>
												<td align="left">常住人口：</td>
												<td align="left"><span style="color: red">63250</span>人</td>
											</tr>
											<tr>
												<td align="left">重点人员：</td>
												<td align="left"><span style="color:red">88</span>人</td>
                                           </tr>
                                </table>
                            </div> -->
                        </div>
                        <div class="goutang"  onclick="sxinxibFun('苟堂派出所')"  attr-name="苟堂派出所" attr-shiyou="59757" attr-changzhu="53758" attr-zhongdian="82">
                            <!--<div class="paichusuo"  onclick="sxinxibFun('苟堂派出所')">
                                 <table style="width:100%;height:100%">
                                    <tr>
                                        <td  align="left"  style="color:red;font-size:16px;font-weight: bold;">苟堂派出所</td>
                                    </tr>
                                    <tr>
                                        <td  align="left">实有人口：</td>
                                        <td  align="left"><span style="color:red">59757</span>人</td>
                                    </tr>
                                    <tr>
                                        <td  align="left">常住人口：</td>
                                        <td  align="left"><span style="color:red">53758</span>人</td>
                                    </tr>
                                    <tr>
                                        <td  align="left">重点人员：</td>
                                        <td  align="left" ><span style="color:red">82</span>人</td>
                                    </tr>
                                </table>
                                </div> -->
                        </div>
                    </div>
                    <div id="dialog4" class="dialog4">
                    <div class="dialog4-header">
                        
                      <span class=""><img src="/arjccm/static/common/index/images/pop/paichusuo.png" alt=""></span>
                      <b class="dialog4-header-name"></b>
                    </div>
                    <div class="dialog4-center" >
                     
                    </div>
                    <div class="dialog4-bottom"></div>
                    </div>
                    <div class="radar"></div>

                    <div class="rhombs">
                        <div class="rhomb-wrapper flip-container" style="position: absolute;left:10%;top:25%">
                            <div class="rhomb flipper" >
                                <div class="front">
                                    <div class="front-content">
                                        <img src="/arjccm/static/common/index/images/pop/zhian.png" alt="">
                                    </div>
                                </div>
                                <div class="back">
                                    <div class="back-content">
                                        <div class="color">
                                            治安
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="rhomb-wrapper flip-container" style="position: absolute;left:50%;top:27%">
                            <div class="rhomb flipper">
                                <div class="front">
                                    <div class="front-content">
                                        <img src="/arjccm/static/common/index/images/pop/zhian.png" alt="">
                                    </div>
                                </div>
                                <div class="back">
                                    <div class="back-content">
                                        <div class="color">
                                            治安
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="rhomb-wrapper flip-container" style="position: absolute;left:13%;top:10%">
                            <div class="rhomb flipper">
                                <div class="front">
                                    <div class="front-content">
                                        <img src="/arjccm/static/common/index/images/pop/zhian.png" alt="">
                                    </div>
                                </div>
                                <div class="back">
                                    <div class="back-content">
                                        <div class="color">
                                            打架
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="span3 shadow height100" style="position: relative;">
                    <div style="width:100%;height:20%" >
                        <div class="header-common">今日事件</div>
                        <div class="common-pading" style="padding:0px 10px">
                           <!--  <div class="echarts" id="shequtongji"></div> -->
                           <div class=" ">
									<div class="calendar">
										
										<div class="calendar-center">
											<table style="width: 100%; height: 100%">
												<tbody><tr>
													<td>
														<div class="row-fluid">
															<div class="span4" style="margin-left:-11px">
																<div class="alarm-rotate" style="position: relative;">
																	<div class="alarm-rotate-div">
																		<div class="alarm-rotate-img alarm-rotate-img1"></div>
																		<div class="alarm-rotate-img alarm-rotate-img2"></div>
																		<div class="alarm-num common-color-common common-color2" id="HandlindToday1">2</div>
																	</div>
																	<div class="alarm-name">待处理</div>
																</div>
															</div>
															<div class="span4" style="margin-left:25px">
																<div class="alarm-rotate " style="position: relative;">
																	<div class="alarm-rotate-div">
																		<div class="alarm-rotate-img alarm-rotate-img1"></div>
																		<div class="alarm-rotate-img alarm-rotate-img2"></div>
																		<div class="alarm-num common-color-common" id="TotalToday1">3</div>
																	</div>
																	<div class="alarm-name">处理中</div>
																</div>
															</div>
															<div class="span4" style="margin-right:-25px">
																<div class="alarm-rotate " style="position: relative;">
																	<div class="alarm-rotate-div">
																		<div class="alarm-rotate-img alarm-rotate-img1"></div>
																		<div class="alarm-rotate-img alarm-rotate-img2"></div>
																		<div class="alarm-num common-color-common common-color1" id="HandledToday1">35</div>
																	</div>
																	<div class="alarm-name">已处理</div>
																</div>
															</div>
														</div>
													</td>
												</tr>
											</tbody></table>
										</div>

									</div>
								</div>
                        </div>
                    </div>
                     <div style="width:100%;height:22%" >
                        <div class="header-common">本月事件统计</div>
                        <div class="common-pading" style="position:relative">
                            <div class="shijian1">
                               <div class="shoulishijian" style="">累计受理警情</div>
                               <div class="shoulishijiannum">5133<span class="jianfont">件</span></div>
                            <div>
                           <div  class="shijian2 ">
                               <div class="shijian-common">受理行政案件</div>
                                 <div class="shijian-common-num">186<span class="shijian-common-jian">件</span></div>
                           </div>
                            <div  class="shijian3">
                               <div class="shijian-common">邻里纠纷</div>
                                 <div class="shijian-common-num">2680<span class="shijian-common-jian">件</span></div>
                            </div>
                             <div  class="shijian4">
                                <div class="shijian-common">受理刑事案件</div>
                                 <div class="shijian-common-num">193<span class="shijian-common-jian">件</span></div>
                             
                             </div>
                              <div  class="shijian5">
                                  <div class="shijian-common">交通事故</div>
                                 <div class="shijian-common-num">2033<span class="shijian-common-jian">件</span></div>
                              </div>
                               <!-- <div  class="shijian6">
                                     <div class="shijian-common">其他</div>
                                 <div class="shijian-common-num">70<span class="shijian-common-jian">件</span></div>
                               </div> -->
                            
                        </div>
                        </div>
                        </div>
            </div>
                    <div style="width:100%;height:26%">
                                           <div class="header-common">最近七周事件发生环比</div>
                        <div class="common-pading">
                            <div id="AlarmInfoWeekEcharts" class="echarts"></div>
                        </div>
                     
                    </div>

                    <div style="width:100%;height:39%" >
                        <div class="header-common">重点人员统计</div>
                        <div class="common-pading" style="padding:0;position: relative;">
                           <div class="zhongdianrenyuantongji"></div>
                           <div class="shangfangrenyuan zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num">5</div>
                                 <div class="zhongdianrenyuan-common" style="margin-left: -8px" >非正常上访</div>
                           </div>
                             <div class="aizibingrenyuan zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num">46</div>
                                 <div class="zhongdianrenyuan-common" style="margin-left: -18px">重性精神病</div>
                           </div>
                              <div class="xidurenyuan zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num">66</div>
                                 <div class="zhongdianrenyuan-common">吸毒人员</div>
                           </div>
                              <div class="fanzuiqianke zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num" style="margin-left: -8px">9</div>
                                 <div class="zhongdianrenyuan-common" style="margin-left: -8px">肇事、肇祸<br>&nbsp;<span style="margin-left: 10px">精神病</span></div>
                           </div>
                            <div class="jingshenbingrenyuan zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num" style="margin-left: -23px">58</div>
                                 <div class="zhongdianrenyuan-common" style="margin-left: -23px">危害国家安全<br>&nbsp;<span style="margin-left: 10px">活动嫌疑</span></div>
                           </div>
                             <div class="shejiaorenyuan zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num" style="margin-left: -15px">0</div>
                                 <div class="zhongdianrenyuan-common" style="margin-left: -5px" >严重刑事犯<br>&nbsp;<span style="margin-left: 10px">罪嫌疑</span></div>
                           </div>
                           
                           <div class="liushourenyuan zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num" style="margin-left: -15px">0</div>
                                 <div class="zhongdianrenyuan-common" style="margin-left: -15px">矛盾纠纷激化</div>
                           </div>
                           
                           
                           <div class="zhongdianqingshaonian zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num">996</div>
                                 <div class="zhongdianrenyuan-common">刑满释放不<br>&nbsp;<span style="margin-left: 10px">足5年</span></div>
                           </div>
                           
                            <div class="quanburenyuan zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num">1255</div>
                                 <div class="zhongdianrenyuan-common" style="width:56px;text-align: center">全部</div>
                           </div>
                           
                        </div>
                    </div>
                </div>
            </div>
            </div>
             <!--map-1  -->
          
            
        </div>
         <div class="ca-container" style=" position: relative;">
          <div class="map-4" style="width:100%;height:100%">
              <div class="row-fluid container-center height100">
                 <div class="span12 height-100 margin0" style="position:relative">
                       <div id="pubMap" ></div>
                       <div class="pubMapDialog">
                           <div class="pubMapDialog-bg1"></div>
                            <div class="pubMapDialog-bg2"></div>
                            <div class="pubMapDialog-center">
                            <!-- <div class="pubMapDialog-header">骨科医院</div> -->
                            <div class="pubMapDialog-close">X</div>
                             <div class="pubMapDialog-center-center">
	                             <!--   <div class="pubMapDialog-center1">
	                                    <div> 民警： </div>
	                                    <div  class="jingcha-name">
	                                       <div class="jiachatouxiang"></div>
	                                       <p>朱文力</p>
	                                       <p>103802</p>
	                                    </div>
	                                     <div  class="jingcha-name">
	                                       <div class="jiachatouxiang"></div>
	                                       <p>杨君生</p>
	                                       <p>013476</p>
	                                    </div>
	                                     <div  class="jingcha-name">
	                                       <div class="jiachatouxiang"></div>
	                                       <p>赵新鹏</p>
	                                       <p>103779</p>
	                                    </div>
	                               </div>
	                                <div  class="pubMapDialog-center2">
	                                    <div>辅警： </div>
	                                 <div  class="jingcha-name">
	                                       <div class="jiachatouxiang"></div>
	                                       <p>李亚东</p>
	                                       <p>103803</p>
	                                    </div>
	                                    <div  class="jingcha-name">
	                                       <div class="jiachatouxiang"></div>
	                                       <p>魏志峰</p>
	                                       <p>103804</p>
	                                    </div>
	                                     <div  class="jingcha-name">
	                                       <div class="jiachatouxiang"></div>
	                                       <p>刘晓楠</p>
	                                       <p>103805</p>
	                                    </div>
	                               </div> -->
                                </div> 
                            </div>
                       </div>
                 </div>
              </div>
          </div>
                   <!-- 公共机构 -->
                   <!-- 
                   <div class="unfold" >
			            <div class="relevance-bg" style="display: block;">
			                <div class="br-yuan"></div>
			            </div>
			            <div class="relevance" style="width: 0px; height: 0px;">
			                <div class="re-header">
			                <div class="re-close"></div>
			                </div>
			                <div class="re-center clearfix">
			                   <div>
			                        
			                     <div class="pub-flag" onclick="xuexiaoFun(this)">
			                        <span class="pub-icon icon-xuexiao"></span>
			                        <span class="pub-name">学校</span>
			                     </div>
	
			                     <div class="pub-flag" onclick="yiyuanFun(this)">
			                        <span class="pub-icon icon-yiyuan"></span>
			                        <span class="pub-name">医院</span>
			                     </div>
		
			                     <div class="pub-flag" onclick="jiayouzhanFun(this)">
			                        <span class="pub-icon icon-jiayouzhan"></span>
			                        <span class="pub-name">加油站</span>
			                     </div>
			      
			                     <div class="pub-flag" onclick="shangchangFun(this)">
			                        <span class="pub-icon icon-shangchang"></span>
			                        <span class="pub-name">商场超市</span>
			                     </div>
			
			                     <div class="pub-flag" onclick="yuleFun(this)">
			                        <span class="pub-icon icon-yule"></span>
			                        <span class="pub-name">娱乐场所</span>
			                     </div>
			 
			                     <div class="pub-flag" onclick="binguanFun(this)">
			                        <span class="pub-icon icon-bingguan"></span>
			                        <span class="pub-name">酒店宾馆</span>
			                     </div>
			
			                     <div class="pub-flag" onclick="sheweishebaoFun(this)">
			                        <span class="pub-icon icon-sheweishebao"></span>
			                        <span class="pub-name">涉危涉爆</span>
			                     </div>
			                   
		
			                     <div class="pub-flag" onclick="shipinjiankongFun(this)">
			                        <span class="pub-icon icon-shipin"></span>
			                        <span class="pub-name">视频监控</span>
			                     </div>
			
			                     <div class="pub-flag" onclick="jingwushiFun(this)">
			                        <span class="pub-icon icon-jingwushi"></span>
			                        <span class="pub-name">警务室</span>
			                     </div>
			     
			                     <div class="pub-flag" onclick="gongzuozhanFun(this)">
			                        <span class="pub-icon icon-gongzuozhan"></span>
			                        <span class="pub-name">工作站</span>
			                     </div>
			
			                     <div class="pub-flag" onclick="jingcheFun(this)">
			                        <span class="pub-icon icon-jingche"></span>
			                        <span class="pub-name">警车</span>
			                     </div>
			
			                     <div class="pub-flag" onclick="jingyuanFun(this)">
			                        <span class="pub-icon icon-jingyuan"></span>
			                        <span class="pub-name">警员</span>
			                     </div>
			                   </div>
			                </div>
						</div>
					</div>  -->

                   <!-- 公共机构 -->
                   <!--map-2  -->
             <div class="map-2">
                 <div class="row-fluid container-center height100">
				<div class="span8 height-100 margin0">
					<!--左侧-->
					<div class="row-fluid  height60  margin0" style="height: 68.9%">
						<div class="common" style="padding: 0">
							<!-- 	<div class="common-header">
								<div>概况</div>
							</div> -->
							<div class=" height-100 common-center">
								<div class="row-fluid height-100  margin0">
									<div class="span12  height-100 margin0 show" style="position: relative; height: 97.54%;">
										
											<div id="mapMask" class="map"></div>
										<div id="map" style="width: 100%; height: 100%;" class="map">
											<div id="popup1" class="ol-popup  bb">
												<div class="">
													<a href="#" id="popup-closer1"
														class="ol-popup-closer  icon-remove " title="关闭"
														onmouseover="$(this).addClass('jbox-close-hover');"
														onmouseout="$(this).removeClass('jbox-close-hover');"
														style="position: absolute; display: block; cursor: pointer; top: 11px; right: 11px; width: 15px; height: 15px; color: #fff"></a>
												<!-- 	<div class="jbox-title-panel" style="height: 25px;">
														<div class="jbox-title">信息</div>
													</div> -->
													<div class="jbox-state">
														<div id="popup-content1" style="padding: 8px 15px;"></div>
													</div>
												</div>
											</div>
										</div>
			
										<div class="span3   height-100 margin0 table-info">
											<table style="height: 100%; width: 90%">
												<tbody><tr>
													<td>
												
														<table class="map-table">

															<tbody><tr>
																<td align="left" class="table-info-name">实有人口：</td>
																<td><div class="map-calss1 common-color map-calss" style="color: #fff" id="eachAll12">&nbsp;30940</div></td>
															</tr>
															<tr>
																<td align="left" class="table-info-name">常住人口：</td>
																<td><div class="map-calss2 common-color map-calss" style="color: #fff" id="eachAll22">&nbsp;30243</div></td>
															</tr>
															<tr>
																<td align="left" class="table-info-name">流动人员：</td>
																<td><div class="map-calss3 common-color map-calss" style="color: #fff" id="eachAll32">&nbsp;697</div></td>
															</tr>
															<tr>
																<td align="left" class="table-info-name">重点人员：</td>
																<td><div class="map-calss4 common-color map-calss" style="color: #fff" id="eachAll42">&nbsp;40</div></td>
															</tr>
																<tr>
																<td align="left" class="table-info-name">社区总数：</td>
																<td><div class="map-calss1 common-color map-calss" style="color: #fff" id="eachAll52">&nbsp;14</div></td>
															</tr>
																<tr>
																<td align="left" class="table-info-name">楼栋总数：</td>
																<td><div class="map-calss3 common-color map-calss" style="color: #fff" id="eachAll62">&nbsp;280</div></td>
															</tr>
																<tr>
																<td align="left" class="table-info-name">单元总数：</td>
																<td><div class="map-calss2 common-color map-calss" style="color: #fff" id="eachAll72">&nbsp;560</div></td>
															</tr>
																<tr>
																<td align="left" class="table-info-name">房屋总数：</td>
																<td><div class="map-calss4 common-color map-calss" style="color: #fff" id="eachAll82">&nbsp;8698</div></td>
															</tr>
															<tr>
																<td align="left" class="table-info-name">社区民警：</td>
																<td><div class="map-calss5 common-color map-calss" style="color: #fff" id="eachAll92">&nbsp;4</div></td>
															</tr>
															<tr>
																<td align="left" class="table-info-name">包村辅警：</td>
																<td><div class="map-calss6 common-color map-calss" style="color: #fff" id="eachAll102">&nbsp;5</div></td>
															</tr> 
														</tbody></table>
													</td>
												</tr>
											</tbody></table>
										</div>
									</div>

								</div>
							</div>
						</div>

					</div>
					<div class="row-fluid height40  margin0" style="height: 33.1%">
						<div class="span4  height-100 margin0" style="margin-left:7px;">
							<div class="common common-small-left" style="padding-right: 10px">
								<div class="common-header">
									<div>重点场所统计</div>
								</div>
								<div class="show height-100 common-center" style="height: 86%;">
                                  <div id="PubNumEcharts" class="echarts"></div>
                            </div>
								
								</div>
								</div>
						<div class="span4  height-100 margin0" style="margin-left:10px;">
							<div class="common common-small-right">
								<div class="common-header">
									<div>重点人员统计</div>
								</div>
								<div class="show height-100 common-center"  style="height: 86%;">
									  <div class="common-pading" style="padding:0;position: relative;height:100%" >
                           <div class="zhongdianrenyuantongji"></div>
                            <div class="shangfangrenyuan zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num">0</div>
                                 <div class="zhongdianrenyuan-common" style="margin-left: -8px" >非正常上访</div>
                           </div>
                             <div class="aizibingrenyuan zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num">4</div>
                                 <div class="zhongdianrenyuan-common" style="margin-left: -10px">重性精神病</div>
                           </div>
                              <div class="xidurenyuan zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num">20</div>
                                 <div class="zhongdianrenyuan-common">吸毒人员</div>
                           </div>
                              <div class="fanzuiqianke zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num" style="margin-left: -8px">3</div>
                                 <div class="zhongdianrenyuan-common" style="margin-left: -8px">肇事、肇祸<br>&nbsp;<span style="margin-left: 10px">精神病</span></div>
                           </div>
                            <div class="jingshenbingrenyuan zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num" style="margin-left: -23px">17</div>
                                 <div class="zhongdianrenyuan-common" style="margin-left: -23px">危害国家安全<br>&nbsp;<span style="margin-left: 10px">活动嫌疑</span></div>
                           </div>
                             <div class="shejiaorenyuan zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num" style="margin-left: -15px">0</div>
                                 <div class="zhongdianrenyuan-common" style="margin-left: -5px" >严重刑事犯<br>&nbsp;<span style="margin-left: 10px">罪嫌疑</span></div>
                           </div>
                           
                           <div class="liushourenyuan zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num" style="margin-left: -15px">0</div>
                                 <div class="zhongdianrenyuan-common" style="margin-left: -15px">矛盾纠纷激化</div>
                           </div>
                           
                           
                           <div class="zhongdianqingshaonian zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num">23</div>
                                 <div class="zhongdianrenyuan-common">刑满释放不<br>&nbsp;<span style="margin-left: 10px">足5年</span></div>
                           </div>
                            <div class="quanburenyuan zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num">72</div>
                                 <div class="zhongdianrenyuan-common" style="width:56px;text-align: center">全部</div>
                           </div>
                           
                        </div>
								</div>
							</div>
						</div>
						<div class="span4  height-100 margin0" style="margin-left:23px;">
							<div class="common common-small-left" style="padding-right: 10px">
								<div class="common-header">
									<div>流动人口中重点关注</div>
								</div>
								<div class="show height-100 common-center" style="height: 86%;">
								  <div class="row-fluid" style="height:100%">
								  <div class="span5" style="height:100%">
								     <table style="width:100%;height:100%">
								        <tr>
								        <td></td>
								        </tr>
								     
									      <tr>
								      <td class="liudong"><span class="liudongnum common-color">697</span></td>
								     </tr>
								       <tr>
								      <td class="liudongname" style="text-align: center;height:24px;">流动人口</td>
								         </tr>
								              <tr>
								        <td></td>
								        </tr>
									</table>
								     
								  </div>
								   <div class="span7" style="height:100%">
								         <table style="width:100%;height:100%">
								      <tr>
								      <td class="liudong"><span class="liudongnum">3%</span></td>
								      <td class="liudongname">上访人员</td>
								      <td><span class="liudongcommon liudongsahngfang"></span></td>
								      </tr>
								          <tr>
								      <td class="liudong"><span class="liudongnum">1%</span></td>
								      <td class="liudongname">留守人员</td>
								      <td><span class="liudongcommon liudongliushou"></span></td>
								      </tr>
								      
								            <tr>
								      <td class="liudong"><span class="liudongnum">1%</span></td>
								      <td class="liudongname">犯罪前科</td>
								      <td><span class="liudongcommon liudongfanzui"></span></td>
								      </tr>
								      
								           <tr>
								      <td class="liudong"><span class="liudongnum">2%</span></td>
								      <td class="liudongname">涉教人员</td>
								      <td><span class="liudongcommon liudongshejiao"></span></td>
								      </tr>
								      
								      
								           <tr>
								      <td class="liudong"><span class="liudongnum">1%</span></td>
								      <td class="liudongname">精神病</td>
								      <td><span class="liudongcommon liudongjingshenbing"></span></td>
								      </tr>
								      
								          <tr>
								      <td class="liudong"><span class="liudongnum">1%</span></td>
								      <td class="liudongname">艾滋病</td>
								      <td><span class="liudongcommon liudongaizibing"></span></td>
								      </tr>
								      
								           <tr>
								      <td  class="liudong"><span class="liudongnum">2%</span></td>
								      <td class="liudongname">吸毒人员</td>
								      <td><span class="liudongcommon liudongxidu"></span></td>
								      </tr>
								      
								           <tr >
								      <td  class="liudong"><span class="liudongnum">1%</span></td>
								      <td class="liudongname">重点青少年</td>
								      <td><span class="liudongcommon liudongzhongdianqingshaonian"></span></td>
								      </tr>
								   </table>
								   </div>
								  </div>
								</div>
							</div>
						</div>
						
					
						<!-- 	<div class="span4  height-100 margin0">
							<div class="common common-small-left">
							
							</div>
						</div> -->
					</div>
				</div>
				<div class="span4 height-100  margin0">
					<div class="row-fluid height30   margin0" style="padding: 10px 0 0 0px; height: 23%;width:100%">

						<div class="span12 height-100  margin0">
							<div class="common common-small-left">
								<div class="common-header">
									<div>今日事件</div>
								</div>
								<div class="show height-100 common-center" style="height:79%;width:96%">
									<div class="calendar">

										<div class="calendar-center">
											<table style="width: 100%; height: 100%">
												<tbody><tr>
													<td>
														<div class="row" style="margin-left:0">
															<div class="span4">
																<div class="alarm-rotate" style="position: relative;">
																	<div class="alarm-rotate-div">
																		<div class="alarm-rotate-img alarm-rotate-img1"></div>
																		<div class="alarm-rotate-img alarm-rotate-img2"></div>
																		<div class="alarm-num common-color-common common-color2" id="HandlindToday1">0</div>
																	</div>
																	<div class="alarm-name">待处理</div>
																</div>
															</div>
															<div class="span4">
																<div class="alarm-rotate " style="position: relative;">
																	<div class="alarm-rotate-div">
																		<div class="alarm-rotate-img alarm-rotate-img1"></div>
																		<div class="alarm-rotate-img alarm-rotate-img2"></div>
																		<div class="alarm-num common-color-common" id="TotalToday1">2</div>
																	</div>
																	<div class="alarm-name">处理中</div>
																</div>
															</div>
															<div class="span4">
																<div class="alarm-rotate " style="position: relative;">
																	<div class="alarm-rotate-div">
																		<div class="alarm-rotate-img alarm-rotate-img1"></div>
																		<div class="alarm-rotate-img alarm-rotate-img2"></div>
																		<div class="alarm-num common-color-common common-color1" id="HandledToday1">10</div>
																	</div>
																	<div class="alarm-name">已处理</div>
																</div>
															</div>
														</div>
													</td>
												</tr>
											</tbody></table>
										</div>

									</div>
								</div>
							</div>
						</div>

					</div>
					<div class="row-fluid height30   margin0" style="height: 21%">
						<div class="span12 height-100  margin0">
							<div class="common common-small-right" style="padding-left:3px">
								<div class="common-header">
									<div>本月事件统计</div>
								</div>
								<div class="show height-100 common-center" style="height: 77%;position:relative">
							<div class="shijian1">
                               <div class="shoulishijian" style="">累计受理警情</div>
                               <div class="shoulishijiannum">642<span class="jianfont">件</span></div>
                            <div>
                           <div  class="shijian2 ">
                               <div class="shijian-common">受理行政案件</div>
                                 <div class="shijian-common-num">24<span class="shijian-common-jian">件</span></div>
                           </div>
                            <div  class="shijian3">
                               <div class="shijian-common">邻里纠纷</div>
                                 <div class="shijian-common-num">335<span class="shijian-common-jian">件</span></div>
                            </div>
                             <div  class="shijian4">
                                <div class="shijian-common">受理刑事案件</div>
                                 <div class="shijian-common-num">25<span class="shijian-common-jian">件</span></div>
                             
                             </div>
                              <div  class="shijian5">
                                  <div class="shijian-common">交通事故</div>
                                 <div class="shijian-common-num">255<span class="shijian-common-jian">件</span></div>
                              </div>
                               <!-- <div  class="shijian6">
                                     <div class="shijian-common">其他</div>
                                 <div class="shijian-common-num">70<span class="shijian-common-jian">件</span></div>
                               </div> -->
                        </div>
                        </div>
								</div>
							</div>
						</div>
			

					</div>
						<div class="row-fluid height30   margin0" style="height: 24%">
						   <div class="span12 height-100  margin0">
							<div class="common common-small-left">
								<div class="common-header">
									<div>最近七周事件发生环比</div>
								</div>
								<div class="show height-100 common-center" style="height: 80%;">
								  	<div id="ccmOrgNpseCompImpoTypeEvent" class="echarts" ></div>
								</div>
							</div>
						</div>
						</div>
					<div class="row-fluid height40   margin0" style="height: 34%">
						<div class="span12 height-100  margin0">
							<div class="common common-small-right" style="padding-left:3px">
								<div class="common-header">
									<div>户籍人口情况</div>
								</div>
								<div class="show height-100 common-center" style="height: 83%;padding:20px 5px 5px 5px">

                                 <div id="renyuanqingkuang" class="echarts"></div>


	
                        </div>
							</div>
						</div>
				<!-- 		<div class="span6 height-100  margin0">
							<div class="common common-small-left">
								<div class="common-header">
									<div>重点场所统计</div>
								</div>
								<div class="show height-100 common-center" style="height: 83%;">
								  <div id="PubNumEcharts" class="echarts"></div>
								</div>
							</div>
						</div> -->

					</div>
				</div>

			</div>
             </div>
               <!-- map-2 -->
         </div>
             <div class="ca-container">
                <!-- map-3 -->
                      <div class="map-2">
                 <div class="row-fluid container-center height100">
				<div class="span8 height-100 margin0">
					<!--左侧-->
					<div class="row-fluid  height60  margin0" style="height: 68.9%">
						<div class="common" style="padding: 0">

							<div class=" height-100 common-center">
								<div class="row-fluid height-100  margin0">
									<div class="span12  height-100 margin0 show" style="position: relative; height: 97.54%;">
											<div id="mapMaskloudong" class="map"></div>
										
										<div id="maploudong" style="width: 100%; height: 100%;" class="map">
										
											<div id="popup2" class="ol-popup  bb">
												<div class="">
													<a href="#" id="popup-closer2"
														class="ol-popup-closer  icon-remove " title="关闭"
														onmouseover="$(this).addClass('jbox-close-hover');"
														onmouseout="$(this).removeClass('jbox-close-hover');"
														style="position: absolute; display: block; cursor: pointer; top: 11px; right: 11px; width: 15px; height: 15px; color: #fff"></a>
													<!-- <div class="jbox-title-panel" style="height: 25px;">
														<div class="jbox-title">信息</div>
													</div> -->
													<div class="jbox-state">
														<div id="popup-content2" style="padding: 8px 15px;"></div>
													</div>
												</div>
											</div>
										</div>
			
										<div class="span3   height-100 margin0 table-info">
											<table style="height: 100%; width: 90%">
												<tbody><tr>
													<td>
												
														<table class="map-table">

															<tbody><tr>
																<td align="left" class="table-info-name">实有人口：</td>
																<td><div class="map-calss1 common-color map-calss" style="color: #fff" id="eachAll13">&nbsp;8136</div></td>
															</tr>
															<tr>
																<td align="left" class="table-info-name">常住人口：</td>
																<td><div class="map-calss2 common-color map-calss" style="color: #fff" id="eachAll23">&nbsp;8014</div></td>
															</tr>
															<tr>
																<td align="left" class="table-info-name">流动人员：</td>
																<td><div class="map-calss3 common-color map-calss" style="color: #fff" id="eachAll33">&nbsp;122</div></td>
															</tr>
															<tr>
																<td align="left" class="table-info-name">重点人员：</td>
																<td><div class="map-calss4 common-color map-calss" style="color: #fff" id="eachAll43">&nbsp;34</div></td>
															</tr>
																
																<tr>
																<td align="left" class="table-info-name">楼栋总数：</td>
																<td><div class="map-calss3 common-color map-calss" style="color: #fff" id="eachAll53">&nbsp;36</div></td>
															</tr>
																<tr>
																<td align="left" class="table-info-name">单元总数：</td>
																<td><div class="map-calss2 common-color map-calss" style="color: #fff" id="eachAll63">&nbsp;69</div></td>
															</tr>
																<tr>
																<td align="left" class="table-info-name">房屋总数：</td>
																<td><div class="map-calss4 common-color map-calss" style="color: #fff" id="eachAll73">&nbsp;1524</div></td>
															</tr>
															<tr>
																<td align="left" class="table-info-name">包村辅警：</td>
																<td><div class="map-calss6 common-color map-calss" style="color: #fff" id="eachAll83">&nbsp;4</div></td>
															</tr> 
														</tbody></table>
													</td>
												</tr>
											</tbody></table>
										</div>
									</div>

								</div>
							</div>
						</div>

					</div>
					<div class="row-fluid height40  margin0" style="height: 33.1%">
						<div class="span4  height-100 margin0" style="margin-left:7px;">
							<div class="common common-small-left" style="padding-right: 10px">
								<div class="common-header">
									<div>房屋状态统计</div>
								</div>
								<div class="show height-100 common-center" style="height: 86%;">
                                  <div id="ccmPopTenantHouseType" class="echarts"></div>
                            </div>
								
								</div>
								</div>
						<div class="span4  height-100 margin0" style="margin-left:10px;">
							<div class="common common-small-right">
								<div class="common-header">
									<div>房屋中重点关注</div>
								</div>
								<div class="show height-100 common-center"  style="height: 86%;">
									  <div class="common-pading" style="padding:0;position: relative;height:100%" >
                           <div class="zhongdianrenyuantongji"></div>
                           <<div class="shangfangrenyuan zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num">0</div>
                                 <div class="zhongdianrenyuan-common" style="margin-left: -8px" >非正常上访</div>
                           </div>
                             <div class="aizibingrenyuan zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num">0</div>
                                 <div class="zhongdianrenyuan-common" style="margin-left: -10px">重性精神病</div>
                           </div>
                              <div class="xidurenyuan zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num">2</div>
                                 <div class="zhongdianrenyuan-common">吸毒人员</div>
                           </div>
                              <div class="fanzuiqianke zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num" style="margin-left: -8px">1</div>
                                 <div class="zhongdianrenyuan-common" style="margin-left: -8px">肇事、肇祸<br>&nbsp;<span style="margin-left: 10px">精神病</span></div>
                           </div>
                            <div class="jingshenbingrenyuan zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num" style="margin-left: -23px">2</div>
                                 <div class="zhongdianrenyuan-common" style="margin-left: -23px">危害国家安全<br>&nbsp;<span style="margin-left: 10px">活动嫌疑</span></div>
                           </div>
                             <div class="shejiaorenyuan zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num" style="margin-left: -15px">0</div>
                                 <div class="zhongdianrenyuan-common" style="margin-left: -5px" >严重刑事犯<br>&nbsp;<span style="margin-left: 10px">罪嫌疑</span></div>
                           </div>
                           
                           <div class="liushourenyuan zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num" style="margin-left: -15px">0</div>
                                 <div class="zhongdianrenyuan-common" style="margin-left: -15px">矛盾纠纷激化</div>
                           </div>
                           
                           
                           <div class="zhongdianqingshaonian zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num">3</div>
                                 <div class="zhongdianrenyuan-common">刑满释放不<br>&nbsp;<span style="margin-left: 10px">足5年</span></div>
                           </div>
                            <div class="quanburenyuan zhongdianrenyuan">
                                <div class="zhongdianrenyuan-num">8</div>
                                 <div class="zhongdianrenyuan-common" style="width:56px;text-align: center">全部</div>
                           </div>
                           
                        </div>
								</div>
							</div>
						</div>
						<div class="span4  height-100 margin0" style="margin-left:23px;">
							<div class="common common-small-left" style="padding-right: 10px">
								<div class="common-header">
									<div>房屋建筑用途统计</div>
								</div>
								<div class="show height-100 common-center" style="height: 86%;">
								  <div class="row-fluid" style="height:100%">
								  <div class="span5" style="height:100%">
								     <table style="width:100%;height:100%">
								        <tr>
								        <td></td>
								        </tr>
								     
									      <tr>
								      <td class="liudong"><span class="liudongnum common-color">1524</span></td>
								     </tr>
								       <tr>
								      <td class="liudongname" style="text-align: center;height:24px;">房屋总数</td>
								         </tr>
								              <tr>
								        <td></td>
								        </tr>
									</table>
								     
								  </div>
								   <div class="span7" style="height:100%">
								         <table style="width:100%;height:100%">
								      <tr>
								      <td class="liudong"><span class="liudongnum">88.0%</span></td>
								      <td class="liudongname">住宅</td>
								      <td><span class="liudongcommon fangwuzhuzhai"></span></td>
								      </tr>
								          <tr>
								      <td class="liudong"><span class="liudongnum">2.00%</span></td>
								      <td class="liudongname">商业</td>
								      <td><span class="liudongcommon fangwushangye"></span></td>
								      </tr>
								      
								            <tr>
								      <td class="liudong"><span class="liudongnum">3.00%</span></td>
								      <td class="liudongname">办公</td>
								      <td><span class="liudongcommon fangwubangong"></span></td>
								      </tr>
								      
								           <tr>
								      <td class="liudong"><span class="liudongnum">2.00%</span></td>
								      <td class="liudongname">工业</td>
								      <td><span class="liudongcommon fangwugongye"></span></td>
								      </tr>
								      
								      
								           <tr>
								      <td class="liudong"><span class="liudongnum">2.00%</span></td>
								      <td class="liudongname">仓储</td>
								      <td><span class="liudongcommon fangwucangchu"></span></td>
								      </tr>
								      
								          <tr>
								      <td class="liudong"><span class="liudongnum">2.00%</span></td>
								      <td class="liudongname">商住混用</td>
								      <td><span class="liudongcommon fangwushanzhu"></span></td>
								      </tr>
								      
								           <tr>
								      <td class="liudong"><span class="liudongnum">1.00%</span></td>
								      <td class="liudongname">其他</td>
								      <td><span class="liudongcommon fangwuqita"></span></td>
								      </tr>
								      
								   </table>
								   </div>
								  </div>
								</div>
							</div>
						</div>
						
					</div>
				</div>
				<div class="span4 height-100  margin0">
					<div class="row-fluid height30   margin0" style="padding: 10px 0 0 0px; height: 23%;width:100%">

						<div class="span12 height-100  margin0">
							<div class="common common-small-left">
								<div class="common-header">
									<div>今日事件</div>
								</div>
								<div class="show height-100 common-center" style="height:79%;width:96%">
									<div class="calendar">

										<div class="calendar-center">
											<table style="width: 100%; height: 100%">
												<tbody><tr>
													<td>
														<div class="row" style="margin-left:0">
															<div class="span4">
																<div class="alarm-rotate" style="position: relative;">
																	<div class="alarm-rotate-div">
																		<div class="alarm-rotate-img alarm-rotate-img1"></div>
																		<div class="alarm-rotate-img alarm-rotate-img2"></div>
																		<div class="alarm-num common-color-common common-color2" id="HandlindToday1">0</div>
																	</div>
																	<div class="alarm-name">待处理</div>
																</div>
															</div>
															<div class="span4">
																<div class="alarm-rotate " style="position: relative;">
																	<div class="alarm-rotate-div">
																		<div class="alarm-rotate-img alarm-rotate-img1"></div>
																		<div class="alarm-rotate-img alarm-rotate-img2"></div>
																		<div class="alarm-num common-color-common" id="TotalToday1">0</div>
																	</div>
																	<div class="alarm-name">处理中</div>
																</div>
															</div>
															<div class="span4">
																<div class="alarm-rotate " style="position: relative;">
																	<div class="alarm-rotate-div">
																		<div class="alarm-rotate-img alarm-rotate-img1"></div>
																		<div class="alarm-rotate-img alarm-rotate-img2"></div>
																		<div class="alarm-num common-color-common common-color1" id="HandledToday1">3</div>
																	</div>
																	<div class="alarm-name">已处理</div>
																</div>
															</div>
														</div>
													</td>
												</tr>
											</tbody></table>
										</div>

									</div>
								</div>
							</div>
						</div>

					</div>
					<div class="row-fluid height30   margin0" style="height: 21%">
						<div class="span12 height-100  margin0">
							<div class="common common-small-right" style="padding-left:3px">
								<div class="common-header">
									<div>本月警情统计</div>
								</div>
								<div class="show height-100 common-center" style="height: 77%;position:relative">
                           <div class="shijian1">
                               <div class="shoulishijian" style="">累计受理警情</div>
                               <div class="shoulishijiannum">83<span class="jianfont">件</span></div>
                            <div>
                           <div  class="shijian2 ">
                               <div class="shijian-common">受理行政案件</div>
                                 <div class="shijian-common-num">3<span class="shijian-common-jian">件</span></div>
                           </div>
                            <div  class="shijian3">
                               <div class="shijian-common">邻里纠纷</div>
                                 <div class="shijian-common-num">42<span class="shijian-common-jian">件</span></div>
                            </div>
                             <div  class="shijian4">
                                <div class="shijian-common">受理刑事案件</div>
                                 <div class="shijian-common-num">4<span class="shijian-common-jian">件</span></div>
                             
                             </div>
                              <div  class="shijian5">
                                  <div class="shijian-common">交通事故</div>
                                 <div class="shijian-common-num">32<span class="shijian-common-jian">件</span></div>
                              </div>
                               <!-- <div  class="shijian6">
                                     <div class="shijian-common">其他</div>
                                 <div class="shijian-common-num">70<span class="shijian-common-jian">件</span></div>
                               </div> -->
                        </div>
                        </div>
								</div>
							</div>
						</div>
			

					</div>
						<div class="row-fluid height30   margin0" style="height: 24%">
						   <div class="span12 height-100  margin0">
							<div class="common common-small-left">
								<div class="common-header">
									<div>最近七周事件发生环比</div>
								</div>
								<div class="show height-100 common-center" style="height: 80%;">
								  	<div id="ccmOrgNpseCompImpoTypeEvent1" class="echarts" ></div>
								</div>
							</div>
						</div>
						</div>
					<div class="row-fluid height40   margin0" style="height: 34%">
						<div class="span12 height-100  margin0">
							<div class="common common-small-right" style="padding-left:3px">
								<div class="common-header">
									<div>房屋隐患类型统计</div>
								</div>
								<div class="show height-100 common-center" style="height: 83%;padding:20px 5px 5px 5px">

                                 <div id="fangwuyinhuanleixing" class="echarts"></div>


	
                        </div>
							</div>
						</div>

					</div>
				</div>

			</div>
             </div>  
                    
                
                
                <!-- map-3 -->
             </div>
             
       <!--  <div class="ca-container">
            <div class="row-fluid height100" style="margin-top:5px;">
                <div class="span12 shadow height100" style="position: relative;">
                    <div id="shujujikong" class="echarts"></div>
                    <div class="header-common wanggehua" >网格化信息</div>
                    <div class="header-common shipinjiankong">视频监控信息</div>
                    <div class="header-common sanweidili">地图信息</div>
                    <div class="header-common dingwei">定位信息</div>

                    <div class="header-common renliankakou">人脸卡口</div>
                    <div class="header-common zhiankakou">治安卡口</div>
                    <div class="header-common dianziwielan">电子围栏</div>
                    <div class="header-common wifitanzhen">wifi探针</div>
                </div>
                <div class="span3 shadow height100">
                    <div class="height33">
                        <div class="top-header">网格化信息</div>
                        <div class="common-pading">

                        </div>
                    </div>
                    <div class="height33" id="DutyUL">
                        <div class="top-header">视频监控信息</div>
                        <div class="common-pading">

                        </div>
                    </div>
                    <div class="height33">
                        <div class="top-header">三维地理信息</div>
                        <div class="common-pading" style="position: relative;">

                </div>
                </div>
                </div>
                <div class="span6 shadow height100">
                    <div class="height15">
                        <div class="top-header">人脸卡口</div>
                        <div class="common-pading" style="height:91%">

                        </div>
                    </div>



                </div>
                <div class="span3 shadow height100">
                    <div class="height33">
                        <div class="top-header">治安卡扣</div>
                        <div class="common-pading">

                        </div>
                    </div>
                    <div class="height33">
                        <div class="top-header">电子围栏</div>
                        <div class="common-pading">

                        </div>
                    </div>
                    <div class="height33">
                        <div class="top-header">wifi探针</div>
                        <div class="common-pading breakdown ">
                        </div>
                    </div>

                </div>
</div>
        </div> -->
            <div class="ca-container" style="display: none">
            <div class="row-fluid height100" style="margin-top:5px;">
                <div class="span3 shadow height100">
                    <div class="height33">
                        <div class="top-header">数据来源</div>
                        <div class="common-pading">
                            <table style="width:100%;height:100%">
                               <tr>
                               <td>视频网:</td>
                               <td><span class="jiankong"></span></td>
                               <td><span class="kakou"></span></td>
                               <td><span class="relian"></span></td>
                                 <td><span class="wifi"></span></td>
                                   <td><span class="zhian"></span></td>
                               </tr>
                                <tr>
                               <td>公安网:</td>
                               <td><span class="huoche"></span></td>
                               <td><span class="qiche"></span></td>
                               <td><span class="lvdian"></span></td>
                               <td><span class="duijiang"></span></td>
                               </tr>
                                 <tr>
                               <td>网安网:</td>
                               <td><span class="qqqq"></span></td>
                               <td><span class="weibo"></span></td>
                               <td><span class="wangye"></span></td>
                               <td><span class="weixin"></span></td>
                               </tr>
                                    <tr>
                               <td>技侦网:</td>
                               <td><span class="shoujiweilan"></span></td>
                               </tr>
                            </table>
                        </div>
                    </div>
                    <div class="height33" id="DutyUL">
                        <div class="top-header">电子档案</div>
                        <div class="common-pading">
                            <div class="dianzi">
                             <ul class="clearfix">
                             <li>
                             <table>
                                      <tr>
                                        <td><span class="shekong"></span></td>
                                         </tr>
                                         <tr>
                                         <td>涉恐涉稳</td>
                                        </tr>
                                     </table>
                            </li>
                             <li>
                             <table>
                                      <tr>
                                        <td><span class="xingshi"></span></td>
                                         </tr>
                                         <tr>
                                         <td>刑事犯罪</td>
                                        </tr>
                                     </table>
                            </li>
                             <li>
                             <table>
                                      <tr>
                                        <td><span class="maodun"></span></td>
                                         </tr>
                                         <tr>
                                         <td> 矛盾纠纷</td>
                                        </tr>
                                     </table>
                            </li>
                             <li>
                             <table>
                                      <tr>
                                        <td><span class="weifa"></span></td>
                                         </tr>
                                         <tr>
                                         <td> 犯罪前科</td>
                                        </tr>
                                     </table>
                            </li>
                            <li>
                             <table>
                                      <tr>
                                        <td><span class="xidu"></span></td>
                                         </tr>
                                         <tr>
                                         <td> 吸毒</td>
                                        </tr>
                                     </table>
                            </li>
                            <li>
                             <table>
                                      <tr>
                                        <td><span class="shangfang"></span></td>
                                         </tr>
                                         <tr>
                                         <td> 非正常上访</td>
                                        </tr>
                                     </table>
                            </li>
                            <li>
                             <table>
                                      <tr>
                                        <td><span class="jingshen"></span></td>
                                         </tr>
                                         <tr>
                                         <td>重性精神病</td>
                                        </tr>
                                     </table>
                            </li>
                            <li>
                             <table>
                                      <tr>
                                        <td><span class="shejun"></span></td>
                                         </tr>
                                         <tr>
                                         <td>涉军群体</td>
                                        </tr>
                                     </table>
                            </li>
                             </ul>
                            </div>
                        </div>
                    </div>
                    <div class="height33">
                        <div class="top-header">积分预警</div>
                        <div class="common-pading" style="position: relative;">
                            <div class="wrap active1">
    <svg class="bulb" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="128px"
         height="128px" viewBox="0 0 128 128" enable-background="new 0 0 128 128" xml:space="preserve">
    <g id="s-bulb">
        <g>
            <path fill-rule="evenodd" clip-rule="evenodd" d="M104.145,42.922c-0.258-9.541-3.436-19.458-10.576-27.913
                C88.695,9.245,82.706,5.05,75.47,2.746c-3.798-1.209-7.682-1.965-11.683-1.916c-3.553,0.043-7.022,0.693-10.406,1.747
                c-9.279,2.88-16.414,8.597-21.776,16.625C28.182,24.328,26.107,29.98,24.958,36c-0.742,3.904-1.014,7.844-0.657,11.804
                c0.858,9.513,4,18.144,10.17,25.542c2.009,2.413,4.117,4.748,5.545,7.566c1.062,2.094,1.989,4.256,2.942,6.4
                c1.076,2.427,2.774,4.205,5.371,4.859c1.925,0.485,3.702,1.281,5.499,2.063c2.902,1.265,5.809,2.526,8.709,3.795
                c2.195,0.955,4.388,1.918,6.581,2.884c2.589,1.138,5.17,2.287,7.762,3.413c1.31,0.57,2.841,0.235,3.577-0.708
                c1.095-1.397,0.673-3.48-1.087-4.271c-1.669-0.753-3.345-1.488-5.019-2.222c-2.038-0.891-4.08-1.776-6.12-2.666
                c-2.604-1.136-5.216-2.268-7.821-3.411c-2.003-0.878-4-1.771-6.005-2.644c-1.102-0.479-2.207-0.95-3.326-1.39
                c-0.417-0.161-0.891-0.179-1.308-0.344c-0.764-0.298-1.278-0.869-1.553-1.646c-1.768-4.971-4.392-9.464-7.759-13.508
                c-1.509-1.809-3.09-3.554-4.336-5.57c-3.335-5.383-5.46-11.171-6.052-17.516c-0.915-9.789,1.291-18.79,6.863-26.889
                c4.957-7.208,11.708-12.015,20.204-14.157c6.392-1.611,12.689-1.011,18.773,1.573c9.279,3.942,15.587,10.785,19.488,19.984
                c2.296,5.405,3.22,11.05,3.011,16.889c-0.317,8.925-3.272,16.896-8.946,23.792c-2.068,2.513-4.124,5.015-5.803,7.807
                c-1.402,2.332-2.552,4.787-3.472,7.349c-0.474,1.314-1.366,2.028-2.757,2.188c-0.928,0.106-1.761,0.435-2.275,1.309
                c-0.89,1.508-0.273,3.811,1.588,4.196c1.883,0.39,3.575-0.188,5.144-1.113c1.738-1.026,2.893-2.557,3.605-4.471
                c1.734-4.653,4.226-8.836,7.528-12.581c4.964-5.618,8.227-12.141,9.869-19.48C103.665,51.369,104.143,47.883,104.145,42.922z"/>
            <path fill-rule="evenodd" clip-rule="evenodd" d="M77.257,116.188c0.884,0.346,1.702,0.202,2.46-0.188
                c0.947-0.494,1.366-1.401,1.348-2.405c-0.02-1.136-0.615-2.013-1.687-2.491c-1.944-0.869-3.899-1.721-5.848-2.575
                c-1.709-0.747-3.419-1.491-5.13-2.234c-2.676-1.171-5.354-2.338-8.028-3.509c-2.347-1.031-4.684-2.087-7.038-3.102
                c-0.792-0.343-1.614-0.626-2.435-0.891c-0.897-0.287-2.432,0.154-2.961,1.172c-0.715,1.37-0.362,3.355,1.264,4.016
                c2.498,1.021,4.943,2.155,7.415,3.23c2.935,1.281,5.875,2.56,8.81,3.84c2.003,0.878,3.997,1.774,6.002,2.646
                c0.324,0.139,0.452,0.309,0.4,0.68c-0.46,3.388-2.759,5.917-6.027,6.749c-3.471,0.886-7.294-1.126-8.787-4.443
                c-0.481-1.063-0.592-2.182-0.792-3.296c-0.254-1.407-1.227-2.001-2.529-2.222c-0.908-0.151-2.159,0.46-2.663,1.422
                c-0.438,0.83-0.415,1.74-0.272,2.663c0.696,4.489,2.935,7.963,6.953,10.109c3.115,1.666,6.446,2.109,9.915,1.108
                c5.003-1.449,8.154-4.682,9.476-9.707C77.146,116.592,77.19,116.428,77.257,116.188z"/>
        </g>
    </g>
    <g id="www-filament">
        <path fill-rule="evenodd" clip-rule="evenodd" d="M82.985,29.716c0.897-0.62,1.849-0.822,2.957-0.547
            c1.471,0.363,2.109,1.95,1.953,3.176c-0.134,1.05-0.666,1.823-1.564,2.383c-0.25,0.155-0.498,0.438-0.585,0.712
            c-0.553,1.734-1.059,3.483-1.584,5.222c-1.027,3.403-2.059,6.806-3.089,10.205c-0.604,1.997-1.211,3.989-1.814,5.985
            c-1.007,3.331-2.012,6.67-3.021,10.001c-0.741,2.449-1.484,4.895-2.227,7.345c-1.016,3.353-2.025,6.708-3.051,10.062
            c-0.217,0.716-0.948,1.105-1.604,0.909c-0.719-0.215-1.057-0.821-0.822-1.608c1.04-3.506,2.104-7.005,3.151-10.508
            c0.364-1.205,0.724-2.423,1.126-3.771c-0.78,0.522-1.44,0.955-2.093,1.4c-0.971,0.659-2.496,0.685-3.463-0.059
            c-1.034-0.789-2.072-1.576-3.105-2.36c-1.011,0.771-2.009,1.495-2.968,2.274c-1.044,0.847-2.709,0.815-3.751,0.048
            c-0.78-0.574-1.604-1.091-2.516-1.7c0.321,1.07,0.595,2.012,0.879,2.952c1.155,3.807,2.311,7.614,3.46,11.425
            c0.084,0.283,0.161,0.6,0.131,0.887c-0.055,0.601-0.562,1.034-1.146,1.081c-0.543,0.045-1.157-0.374-1.344-0.949
            c-0.223-0.688-0.418-1.38-0.628-2.069c-1.357-4.498-2.712-8.995-4.074-13.494c-2.142-7.073-4.292-14.143-6.432-21.218
            c-0.945-3.118-1.818-6.26-2.834-9.356c-0.494-1.505-0.604-3.163-1.934-4.375c-0.766-0.701-0.747-1.802-0.425-2.832
            c0.275-0.885,0.818-1.444,1.703-1.732c1.167-0.381,2.189-0.102,3.155,0.579c0.963,0.675,1.942,1.331,2.909,2.001
            c1.774,1.23,3.548,2.467,5.34,3.713c0.854-0.667,1.69-1.326,2.526-1.98c2.092-1.639,4.174-3.288,6.276-4.914
            c0.789-0.609,2.453-0.664,3.224-0.089c1.265,0.942,2.491,1.93,3.729,2.9c1.731,1.358,3.462,2.715,5.197,4.082
            C77.429,33.558,80.204,31.632,82.985,29.716z M76.578,46.931c0.969-0.69,1.972-1.112,3.223-0.72
            c0.871-2.883,1.733-5.738,2.598-8.592c-0.037-0.027-0.071-0.052-0.107-0.082c-0.538,0.37-1.078,0.729-1.615,1.101
            c-1.452,1.002-2.898,2.017-4.356,3.011c-1.11,0.759-2.634,0.747-3.716-0.083c-1.969-1.515-3.91-3.071-5.864-4.604
            c-0.852-0.669-1.703-1.334-2.583-2.02c-1.043,0.813-2.09,1.621-3.129,2.439c-1.82,1.425-3.612,2.896-5.469,4.276
            c-0.99,0.738-2.432,0.763-3.523,0.02c-1.913-1.306-3.813-2.632-5.719-3.947c-0.295-0.206-0.593-0.406-1.044-0.714
            c0.96,3.179,1.871,6.201,2.794,9.26c0.205-0.032,0.395-0.032,0.566-0.086c1.027-0.336,1.94-0.049,2.781,0.529
            c1.647,1.127,3.29,2.26,4.912,3.415c0.35,0.255,0.56,0.217,0.875-0.037c1.671-1.344,3.368-2.654,5.042-3.993
            c1.141-0.916,2.937-0.785,4.069,0.181c1.643,1.404,3.388,2.689,5.091,4.024c0.042,0.033,0.102,0.047,0.187,0.083
            C73.242,49.245,74.926,48.105,76.578,46.931z M58.842,56.22c-1.109,0.887-2.681,0.887-3.8,0.103
            c-1.462-1.027-2.936-2.034-4.404-3.048c-0.141-0.099-0.283-0.189-0.567-0.375c0.981,3.251,1.921,6.36,2.869,9.502
            c0.791-0.181,1.538-0.45,2.304-0.103c0.256,0.118,0.54,0.186,0.773,0.334c0.95,0.628,1.891,1.271,2.826,1.924
            c0.253,0.175,0.43,0.189,0.693-0.025c0.866-0.709,1.776-1.363,2.642-2.074c1.126-0.928,2.846-0.918,3.977,0.019
            c0.864,0.716,1.772,1.371,2.641,2.077c0.246,0.201,0.415,0.177,0.647,0.017c0.734-0.512,1.478-1,2.207-1.517
            c0.595-0.423,1.191-0.828,1.945-0.873c0.422-0.024,0.845-0.004,1.382-0.004c0.854-2.827,1.741-5.768,2.629-8.712
            c-0.035-0.022-0.074-0.045-0.11-0.066c-1.198,0.829-2.402,1.652-3.601,2.488c-0.624,0.432-1.232,0.851-2.021,0.982
            c-0.781,0.128-1.497-0.009-2.094-0.452c-1.885-1.412-3.73-2.875-5.633-4.353C62.366,53.453,60.59,54.814,58.842,56.22z
             M49.028,49.956c2.344,1.648,4.709,3.274,7.077,4.887c0.628,0.428,1.043,0.387,1.659-0.094c1.708-1.326,3.414-2.657,5.121-3.987
            c0.839-0.653,1.699-0.653,2.537,0c1.704,1.331,3.409,2.661,5.121,3.987c0.615,0.481,1.028,0.522,1.658,0.094
            c2.369-1.613,4.725-3.247,7.086-4.874c0.473-0.325,0.651-0.772,0.525-1.338c-0.091-0.433-0.369-0.79-0.793-0.757
            c-0.429,0.026-0.88,0.21-1.245,0.443c-0.899,0.564-1.756,1.198-2.628,1.804c-0.794,0.555-1.589,1.109-2.388,1.659
            c-0.772,0.534-1.678,0.551-2.419-0.02c-1.791-1.381-3.56-2.781-5.33-4.185c-0.543-0.429-1.167-0.429-1.71,0
            c-1.771,1.404-3.541,2.804-5.328,4.181c-0.742,0.575-1.648,0.562-2.425,0.024c-1.653-1.146-3.304-2.295-4.958-3.439
            c-0.204-0.143-0.413-0.278-0.636-0.376c-0.814-0.355-1.507,0.114-1.61,1.089C48.567,49.361,48.733,49.747,49.028,49.956z
             M69.706,69.17c1.593-1.068,3.174-2.148,4.762-3.23c0.433-0.293,0.533-0.718,0.451-1.198c-0.075-0.439-0.348-0.77-0.781-0.783
            c-0.331-0.012-0.712,0.114-0.997,0.293c-0.946,0.599-1.859,1.252-2.787,1.878c-0.884,0.597-1.77,0.554-2.615-0.106
            c-0.926-0.729-1.854-1.457-2.781-2.18c-0.52-0.405-1.094-0.403-1.619,0.008c-0.927,0.722-1.851,1.449-2.779,2.176
            c-0.841,0.661-1.728,0.694-2.615,0.096c-0.913-0.617-1.818-1.245-2.732-1.857c-0.725-0.484-1.3-0.452-1.658,0.066
            c-0.386,0.562-0.22,1.265,0.432,1.712c1.502,1.037,3.008,2.06,4.521,3.081c0.596,0.396,1.035,0.381,1.624-0.062
            c0.955-0.717,1.889-1.463,2.849-2.173c0.768-0.572,1.585-0.569,2.355,0.003c0.96,0.716,1.895,1.462,2.854,2.174
            c0.232,0.173,0.526,0.271,0.787,0.399C69.262,69.355,69.511,69.304,69.706,69.17z"/>
    </g>
    </svg>
          <div class="light"><span class="glow"></span><span class="flare"></span><div>
    </div>

                        </div>
                    </div>
                    <div class="jifen-div">
                    <ul>
                    <li>比对模型</li>
                    <li>比对映射</li>
                    <li>比对专题库</li>
                    <li>预警积分调整</li>
                    </ul>
                    </div>
                </div>
                </div>
                </div>
                <div class="span6 shadow height100">
                    <div class="height15">
                        <div class="top-header">重点人员管控</div>
                        <div class="common-pading" style="height:91%">
                            <div class="row-fluid results">
                                <div class="span6">
                                    <div class="common-pading" style="text-align: center;overflow: visible">
                                        <b class="common-color" id="CameraTotal1">1420</b> <br>&nbsp;总数
                                    </div>
                                </div>
                                <div class="span6">
                                    <div class="common-pading" style="text-align: center;overflow: visible">
                                        <b class="common-color" id="OnLineRate">2</b> <br>&nbsp;本月新增人数<i class=" icon-arrow-up align-top bigger-125" style="color:#f23f40"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="height80">
                            </div>

                        </div>
                    </div>
                            <div class="height80">
                             <div id="map111" style="width: 100%; height: 100%"></div>
                               <div id="map1" style="width:100%;height:600px;display:none" ></div>


                    </div>


                </div>
                <div class="span3 shadow height100">
                    <div class="height33">
                        <div class="top-header">预警管理</div>
                        <div class="common-pading contradiction">
                             <div class="breakdown-gc">
                                        <h6>积分预警</h6>
                                        <p id="alarmnum" class="jifenyujing"></p>
                                    </div>
                                    <div class="breakdown-gd">
                                        <h6>比对碰撞</h6>
                                        <p id="wsnum" class="bidui"></p>
                                    </div>
                                    <div class="breakdown-wcs">
                                        <h6>预警处置</h6>
                                        <p id="endwsnum" class="yujingyuan"></p>
                                    </div>
                        </div>
                    </div>
                    <div class="height33">
                        <div class="top-header">布控管理</div>
                        <div class="common-pading">
                              <div class="echarts" id="bukong"></div>
                        </div>
                    </div>
                    <div class="height33">
                        <div class="top-header">预警信息上报</div>
                        <div class="common-pading breakdown ">
                           <div id="yujingxinxi" class="echarts"></div>
                        </div>
                    </div>

                </div>
            </div>
            </div>
            </div>
    <div class="hd">
                <span class="arrow arrow1"><a class="next"></a></span>
                <!-- <span class="arrow arrow2"><a class="prev"><i></i></a></span> -->
            </div>
            
               <div class="calendar-header">
											<span class="glyphicon  glyphicon-calendar" aria-hidden="true"></span> <span id="calendarYear">2018</span>&nbsp;年&nbsp;<span class="common-color-common" style="font-size: 40px" id="calendarMonth">05</span>&nbsp;月&nbsp;<span class="common-color-common" style="font-size: 40px" id="calendarDay">12</span>&nbsp;日&nbsp;&nbsp;
					</div>
					<div class="calendar-header1">
											<span class="glyphicon  glyphicon-calendar" aria-hidden="true"></span> <span   class="common-color-common" style="font-size: 40px"  id="calendarHH">00</span>&nbsp;:&nbsp;<span class="common-color-common" style="font-size: 40px" id="calendarMM">02</span>&nbsp;:&nbsp;<span class="common-color-common" style="font-size: 40px" id="calendarSS">24</span>&nbsp;&nbsp;
					</div>
					
					<div id="popupDialog" style=""></div>
					<div id="popupDialogName" style="display: none"></div>
                    <button class="bulidclick" style="position: absolute;right:0;z-index: 9999;display: none" >222</button>

                 <!-- 楼栋住户信息 -->
	<button type="button" data-toggle="modal" data-target="#myModal"
		id="buildBtn" style="display: none"></button>
	<div id="myModal" class="modal hide fade jbox" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
		style="width: 1300px; margin-left: -33.85%;">
		<div class="jbox-container">
			<a href="#" id="popup-closer" class="ol-popup-closer jbox-close"
				title="关闭" onmouseover="$(this).addClass('jbox-close-hover');"
				onmouseout="$(this).removeClass('jbox-close-hover');"
				style="position: absolute; display: block; cursor: pointer; top: 4px; right: 11px; width: 15px; height: 15px;"
				data-dismiss="modal" aria-hidden="true"></a>
			<!-- <div class="jbox-title-panel" style="height: 25px;">
				<div class="jbox-title">信息</div>
			</div> -->
			<!-- 楼栋 -->
			<div class="jbox-state">
				<div id="popup-content">
					<div class="modal-body" id="build-details"
						style="padding: 3px 0px 0 0;"></div>
					<!-- 房屋 -->
					<div class="modal-body" id="house-details"
						style="padding: 3px 15px;"></div>
					<!--人口-->
					<div class="modal-body" id="pop-details"></div>
				</div>
				<div class="jbox-button-panel"
					style="height: 4px; padding: 0 15px 20px;text-align: right;"
					id="modal-footer">
					<button class="jbox-button" style="padding: 0px 10px 0px 10px;"
						data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</div>
		</div>

	</div>
	<!-- 楼栋住户信息 -->
</div>
    </body>
    </html>