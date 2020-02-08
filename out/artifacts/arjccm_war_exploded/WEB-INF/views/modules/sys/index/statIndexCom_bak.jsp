<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit|ie-comp|ie-stand" /> 
<title>概况信息</title>
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
<link rel="stylesheet"
	href="${ctxStatic}/bootstrap/bootstrap3.0/css/bootstrap.min.css">
<script src="${ctxStatic}/bootstrap/bootstrap3.0/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome.min.css">
<link rel="stylesheet" href="${ctxStatic}/bootstrap/animate.min.css">
<!--[if lte IE 7]>
    <link rel="stylesheet" href="../bootstrap/2.3.1/awesome/font-awesome-ie7.css">
    <![endif]-->
<!--[if lte IE 6]>
    <link rel="stylesheet" href="../bootstrap/bsie/css/bootstrap-ie6.min.css">
    <script src="../bootstrap/bsie/js/bootstrap-ie.min.js"></script>
    <![endif]-->
<link rel="stylesheet" href="${ctxStatic}/asidenav/asidenav.css">
<script src="${ctxStatic}/asidenav/asidenav.js"></script>
<script src="${ctxStatic}/asidenav/jquery.min.js"></script>
<link rel="stylesheet" href="${ctxStatic}/ol/ol.css" type="text/css">
<link rel="stylesheet" href="${ctxStatic}/modules/map/css/map.css"
	type="text/css">
<link rel="stylesheet"
	href="${ctxStatic}/common/index/css/indexCommon.css">
<%-- <link rel="stylesheet" href="${ctxStatic}/common/index/css/PopInfo.css"> --%>
<link rel="stylesheet"
	href="${ctxStatic}/common/index/css/statSituationStatistics.css">
<script type="text/javascript">
	var ctxStatic = '${ctxStatic}';
</script>
<script src="${ctxStatic}/ol/ol.js"></script>
<script src="${ctxStatic}/d3/d3.v4.min.js"></script>
<script src="${ctxStatic}/modules/map/js/commonMap.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script src="${ctxStatic}/custom/date/date.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/Situation.js"></script>
<script>
	$(function() {
		$('#main').height($(window).height());

		$('.container-center').height($('#main').height() - 70);

	})
</script>
</head>
<body>
	<div id="main">
		<div class="context" content="${ctx}" style="display: none"></div>
		<div class="container">
			<!--     <div class="row height10">
            <div class="col-xs-12">
                <div class="header">社会网格化管理信息系统</div>
            </div>
        </div> -->

			<div class="row-fluid header" style="">
				
				<div class="col-xs-2" style="position: relative;">
                   <!-- 菜单 -->
			       	<div style="z-index: 9999;display: block;position: absolute;">
						<svg width="0" height="0">
					        <defs>
					            <filter id="goo">
					                <fegaussianblur in="SourceGraphic" stdDeviation="10"
													result="blur"></fegaussianblur>
					                <fecolormatrix in="blur" mode="matrix"
													values="1 0 0 0 0  0 1 0 0 0  0 0 1 0 0  0 0 0 19 -9"
													result="goo"></fecolormatrix>
					                <fecomposite in="SourceGraphic" in2="goo"
													operator="atop"></fecomposite>
					            </filter>
					        </defs>
					    </svg>
						<div class="aside-nav bounceInUp animated" id="aside-nav">
							<label for="" class="aside-menu" title=""><a href="${ctx}" class="menu-item">主面板</a></label>
							 <a href="${ctx}/index" title="首页" class="menu-item menu-second">首页</a>
							 <a href="${ctx}/sys/map/statPop" title="人口" class="menu-item menu-third">人口</a> 
						     <a href="${ctx}/sys/map/statIncidentStatistics" title="事件" class="menu-item  menu-fourth">事件</a>
						</div>
					</div> 
                    <!-- 菜单 -->
					<!-- <div class="index-nav-common clearfix">
						<span id="indexhover"> <a href="###"> <i
								class="icon-th-list align-top bigger-125"></i> 导航
						</a>
						</span>
					</div>
					<div class="clearfix" id="indexNav" style="display: none;">
						<ul class="indexnav clearfix">
							<li><a href="${ctx}/index"> <i
									class="icon-home align-top bigger-125"></i> 首页
							</a></li>
							<li class="lastli"><a href="${ctx}/sys/map/statPop">
									<i class="icon-globe align-top bigger-125"></i> 人口
							</a></li>
							<li class="lastli"><a href="${ctx}/sys/map/statSituation">
									<i class="icon-globe align-top bigger-125"></i> 概况
							</a></li>
						</ul>
					</div> -->
				</div>

				<div class="col-xs-8">

					<h5 class="header-logo">社会网格化管理信息系统</h5>
				</div>
				<div class="col-xs-2">
					<div class="Logout">
						<span> <a href="${ctx}/logout"> <i
								class="icon-off align-top bigger-125"></i> 退出
						</a>
						</span>
					</div>
				</div>
			</div>
			<div class="row container-center">
				<div class="col-xs-8 height-100 margin0">
					<!--左侧-->
					<div class="row  height60  margin0" style="height: 66%">
						<div class="common" style="padding: 0">
							<!-- 	<div class="common-header">
								<div>概况</div>
							</div> -->
							<div class=" height-100 common-center">
								<div class="row height-100  margin0">
									<div class="col-xs-12  height-100 margin0 show"
										style="position: relative; height: 99%;">
										<div id="mapMask" class="map"></div>
										<div id="map" style="width: 100%; height: 100%;" class="map">
											<div id="popup" class="ol-popup  bb">
												<div class="">
													<a href="#" id="popup-closer"
														class="ol-popup-closer  icon-remove " title="关闭"
														onmouseover="$(this).addClass('jbox-close-hover');"
														onmouseout="$(this).removeClass('jbox-close-hover');"
														style="position: absolute; display: block; cursor: pointer; top: 11px; right: 11px; width: 15px; height: 15px; color: #fff"></a>
													<!-- <div class="jbox-title-panel" style="height: 25px;">
														<div class="jbox-title">信息</div>
													</div> -->
													<div class="jbox-state">
														<div id="popup-content" style="padding: 8px 15px;"></div>
													</div>
												</div>
											</div>
										</div>

										<div class="col-xs-3   height-100 margin0 table-info">
											<table style="height: 100%; width: 77%">
												<tr>
													<td>
														<p class="table-info-title">滨海新区杭州道街道</p>
														<table class="map-table">

															<tr>
																<td align="left" class="table-info-name">实有人口：</td>
																<td><div class="map-calss1 common-color map-calss"
																		style="color: #fff" id="eachAll1">0</div></td>
															</tr>
															<tr>
																<td align="left" class="table-info-name">房屋总数：</td>
																<td><div class="map-calss2 common-color map-calss"
																		style="color: #fff" id="eachAll2">0</div></td>
															</tr>
															<tr>
																<td align="left" class="table-info-name">楼栋总数：</td>
																<td><div class="map-calss3 common-color map-calss"
																		style="color: #fff" id="eachAll3">0</div></td>
															</tr>
															<tr>
																<td align="left" class="table-info-name">网格总数：</td>
																<td><div class="map-calss4 common-color map-calss"
																		style="color: #fff" id="eachAll4">0</div></td>
															</tr>
															<tr>
																<td align="left" class="table-info-name">网格人员：</td>
																<td><div class="map-calss5 common-color map-calss"
																		style="color: #fff" id="eachAll5">0</div></td>
															</tr>
															<tr>
																<td align="left" class="table-info-name">重点企业：</td>
																<td><div class="map-calss6 common-color map-calss"
																		style="color: #fff" id="eachAll6">0</div></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
									</div>

								</div>
							</div>
						</div>

					</div>
					<div class="row height40  margin0" style="height: 34%">
						<div class="col-xs-4   height-100 margin0">
							<div class="common common-small-right">
								<div class="common-header">
									<div>实有人口分析</div>
								</div>
								<div class="show height-100 common-center">
									<table style="width: 100%; height: 100%">
										<tr>
											<td style="height:100%">
												<div id="RightTypeEcharts">
													<!-- <div id="RightTypeEcharts" class="echarts"></div> -->
													<div class="RightTypeEcharts-common RightTypeEcharts-left1">
														<table style="width: 100%; height: 100%">
															<tr>
																<td>
																	<div class="common-color-common common-color1" id="registerPop">0</div>
																	<div class="RightTypeEcharts-border"></div>
																	<div>户籍人口</div>
																</td>
															</tr>
														</table>
													</div>
													<div
														class="RightTypeEcharts-common  RightTypeEcharts-left2">
														<table style="width: 100%; height: 100%">
															<tr>
																<td>
																	<div class="RightTypeEcharts-name">境外人口</div>
																	<div class="RightTypeEcharts-border"></div>
																	<div class="common-color-common"  id="overseasPop">0</div>
																</td>
															</tr>
														</table>
													</div>
													<div
														class="RightTypeEcharts-common  RightTypeEcharts-right1">
														<table style="width: 100%; height: 100%">
															<tr>
																<td>
																	<div class="common-color-common" id="flowPop">0</div>
																	<div class="RightTypeEcharts-border"></div>
																	<div>流动人口</div>
																</td>
															</tr>
														</table>
													</div>
													<div
														class="RightTypeEcharts-common  RightTypeEcharts-right2">
														<table style="width: 100%; height: 100%">
															<tr>
																<td>
																	<div class="RightTypeEcharts-name">未落户人口</div>
																	<div class="RightTypeEcharts-border"></div>
																	<div class="common-color-common common-color2"  id="noRegisterPop">0</div>
																</td>
															</tr>
														</table>
													</div>
												</div>
											</td>
										</tr>
									</table>

								</div>
							</div>
						</div>
						<div class="col-xs-8  height-100 margin0">
							<div class="common common-small-left" style="padding-right: 10px">
								<div class="common-header">
									<div>事件流程</div>
								</div>
								<div class="show height-100 common-center">
									<div id="AlarmFlow" class="echarts" style="height: 99%"></div>
								</div>
								<!--  fuxinshuang修改
								<div class="common-header">
									<div>各街道人口事件汇总</div>
								</div>
								<div class="show height-100 common-center">
									<div id="allEventAndPeople" class="echarts" style="height: 99%"></div>
								</div>
								-->
							</div>
						</div>
						<!-- 	<div class="col-xs-4  height-100 margin0">
							<div class="common common-small-left">
							
							</div>
						</div> -->
					</div>
				</div>
				<div class="col-xs-4 height-100  margin0">
					<div class="row height30   margin0"
						style="padding: 10px 0 0 10px; height: 32%">

						<div class="col-xs-12 height-100  margin0">
							<div class="common common-small-left">
								<div class="common-header">
									<div>今日案事件</div>
								</div>
								<div class="show height-100 common-center">
									<div class="calendar">
										<div class="calendar-header">
											<span class="glyphicon  glyphicon-calendar"
												aria-hidden="true"></span> <span id="calendarYear">2018</span>年<span
												class="common-color-common" style="font-size: 18px" id="calendarMonth">04</span>月<span
												class="common-color-common" style="font-size: 18px" id="calendarDay">06</span>日&nbsp;&nbsp;
										</div>
										<div class="calendar-center">
											<table style="width: 100%; height: 100%">
												<tr>
													<td>
														<div class="row">
															<div class="col-xs-4">
																<div class="alarm-rotate" style="position: relative;">
																	<div class="alarm-rotate-div">
																		<div class="alarm-rotate-img alarm-rotate-img1"></div>
																		<div class="alarm-rotate-img alarm-rotate-img2"></div>
																		<div
																			class="alarm-num common-color-common common-color2" id="HandlindToday">0</div>
																	</div>
																	<div class="alarm-name">待处理</div>
																</div>
															</div>
															<div class="col-xs-4">
																<div class="alarm-rotate " style="position: relative;">
																	<div class="alarm-rotate-div">
																		<div class="alarm-rotate-img alarm-rotate-img1"></div>
																		<div class="alarm-rotate-img alarm-rotate-img2"></div>
																		<div class="alarm-num common-color-common" id="TotalToday">0</div>
																	</div>
																	<div class="alarm-name">处理中</div>
																</div>
															</div>
															<div class="col-xs-4">
																<div class="alarm-rotate " style="position: relative;">
																	<div class="alarm-rotate-div">
																		<div class="alarm-rotate-img alarm-rotate-img1"></div>
																		<div class="alarm-rotate-img alarm-rotate-img2"></div>
																		<div
																			class="alarm-num common-color-common common-color1"  id="HandledToday">0</div>
																	</div>
																	<div class="alarm-name">已处理</div>
																</div>
															</div>
														</div>
													</td>
												</tr>
											</table>
										</div>

									</div>
								</div>
							</div>
						</div>

					</div>
					<div class="row height30   margin0" style="height: 34%">
						<div class="col-xs-6 height-100  margin0">
							<div class="common common-small-right">
								<div class="common-header">
									<div>非公有制组织类型统计</div>
								</div>
								<div class="show height-100 common-center">
									<div id="ccmOrgNpseCompType" class="echarts"></div>
								</div>
							</div>
						</div>
						<div class="col-xs-6 height-100  margin0">
							<div class="common common-small-left">
								<div class="common-header">
									<div>重点类型统计</div>
								</div>
								<div class="show height-100 common-center">
									<div id="ccmOrgNpseCompImpoType" class="echarts"></div>
								</div>
							</div>
						</div>

					</div>
					<div class="row height40   margin0" style="height: 34%">
						<div class="col-xs-6 height-100  margin0">
							<div class="common common-small-right">
								<div class="common-header">
									<div>特殊人群分析</div>
								</div>
								<div class="show height-100 common-center">
									<div id="PopsNumEcharts" class="echarts"></div>
								</div>
							</div>
						</div>
						<div class="col-xs-6 height-100  margin0">
							<div class="common common-small-left">
								<div class="common-header">
									<div>房屋状态类型统计</div>
								</div>
								<div class="show height-100 common-center">
									<div id="ccmPopTenantHouseType" class="echarts"
										style="margin-top: -4%"></div>
								</div>
							</div>
						</div>

					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>