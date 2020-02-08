<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit|ie-comp|ie-stand" />
<title>城市管理</title>
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
	<link rel="stylesheet" href="${ctxStatic}/common/index/css/index.css">

<%-- <link rel="stylesheet" href="${ctxStatic}/common/index/css/PopInfo.css"> --%>
<link rel="stylesheet"
	href="${ctxStatic}/common/index/css/statSituationStatistics.css">
	<link rel="stylesheet"
	href="${ctxStatic}/common/index/css/statCityManage.css">
<script type="text/javascript">
    var ctxStatic = '${ctxStatic}',ctx = '${ctx}';
</script>
<script src="${ctxStatic}/ol/ol.js"></script>
<script src="${ctxStatic}/d3/d3.v4.min.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/scroll.js"></script>
<script src="${ctxStatic}/modules/map/js/mapconfig.js"></script>
<script src="${ctxStatic}/modules/map/js/commonMap.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script src="${ctxStatic}/highcharts/highcharts.js"></script>
<script src="${ctxStatic}/highcharts/highcharts-3d.js"></script>
<script src="${ctxStatic}/icharts/ichart.1.2.1.min.js"></script>
<script src="${ctxStatic}/custom/date/date.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/statCityMange.js"></script>
<script>
	$(function() {
		$('#main').height($(window).height());

		$('.container-center').height($('#main').height() - 70);

	})
</script>
  <script language="javascript">
	   function videoSubmit(){
		   document.getElementById("loginForm").action="${dz_hangzhoudao_link_video}";
		   document.getElementById("loginForm").submit();
	   }
	   function pbsSubmit(){
		   document.getElementById("loginForm").action="${dz_hangzhoudao_link_pbs}";
		   document.getElementById("loginForm").submit();
	   }
  </script>
</head>
<body>
	<div id="main">
		<form id="loginForm" class="form-signin" action="" method="post">
			<input type="hidden" id="username" name="username" value="${user.loginName}">
			<input type="hidden" id="password" name="password" value="${user.newPassword}">
		</form>
		<div class="context" content="${ctx}" style="display: none"></div>
		<div class="container">
			<!--     <div class="row height10">
            <div class="col-xs-12">
                <div class="header">社会网格化管理信息系统</div>
            </div>
        </div> -->

			<div class="row-fluid header" style="">
				<div class="col-xs-1">
                   <!-- 菜单 -->
			       	<div class="head-button" >
							<div class="">
                                <span style="float: left;margin-left: 100px"><a href="${ctx}"><i class=""></i>主面板</a></span>
                                <span style="float: left;margin-left: 20px"><a href="${ctx}/sys/map/statLivelihoodQuLiang">民计民生</a></span>
                                <span style="float: left;margin-left: 20px"><a href="${ctx}/sys/map/statPublicSecurityQuLiang">平安建设</a></span>
                                <span style="float: left;margin-left: 20px"><a href="${ctx}/sys/map/statNpseSafeQuLiang">安全生产</a></span>
                                <span style="float: left;margin-left: 20px"><a href="${ctx}/sys/map/statConstructionEconomicsQuLiang">经济建设</a></span>
                                <span style="float: left;margin-left: 20px"><a href="${ctx}/sys/map/statCityManageQuLiang">城市管理</a></span>
<%--								<span style="float: left;margin-left: 100px"><a href="${ctx}"><i class=""></i>主面板</a></span>
								<span style="float: left;margin-left: 30px"><a href="#" target="_blank" onclick="pbsSubmit();"><i class=""></i>智慧党建</a></span>
								<span style="float: left;margin-left: 30px"><a href="#" target="_blank" onclick="videoSubmit();"><i class=""></i>视频融合</a></span>
								<span style="float: left;margin-left: 30px"><a href="${ctx}/index"><i class=""></i>网格化首页</a></span>--%>
							</div>
					</div>

				</div>
				<div class="col-xs-10">

					<h5 class="header-logo" style="text-align: center"><b>社会网格化管理信息系统：</b>城市管理</h5>
				</div>
				<div class="col-xs-1">
					<div class="Logout">
						<span> <a href="${ctx}/logout"> <i
								class="icon-off align-top bigger-125"></i> 退出
						</a>
						</span>
					</div>
				</div>
			</div>
			<div class="row container-center">
				<div class="col-xs-9 height-100 margin0">
					<!--左侧-->
					<div class="row  height60  margin0" style="height: 70%">
						<div class="common" style="padding: 0">
							<!-- 	<div class="common-header">
								<div>概况</div>
							</div> -->
							<div class=" height-100 common-center">
								<div class="row height-100  margin0">
								<div class="col-xs-3 height-100 margin0 " 	style="position: relative; height: 99%;width:20%;margin-top: -10px;">
										<div class="row height30   margin0" style="height: 50%">
											<div class="col-xs-12 height-100  margin0">
												<div class="common common-small-left">
													<div class="common-header">
														<div>城市人口</div>
													</div>
													<div class="show height-100 common-center">
													  <table style="width: 100%; height: 100%">
										<tbody><tr>
											<td style="height:100%">
												<div id="RightTypeEcharts">
													<!-- <div id="RightTypeEcharts" class="echarts"></div> -->
													<div class="RightTypeEcharts-common RightTypeEcharts-left1">
														<table style="width: 100%; height: 100%">
															<tbody><tr>
																<td>
																	<div class="common-color-common common-color1" id="registerPop">0</div>
																	<div class="RightTypeEcharts-border"></div>
																	<div>户籍人口</div>
																</td>
															</tr>
														</tbody></table>
													</div>
													<div class="RightTypeEcharts-common  RightTypeEcharts-left2">
														<table style="width: 100%; height: 100%">
															<tbody><tr>
																<td>
																	<div class="RightTypeEcharts-name">流动人口</div>
																	<div class="RightTypeEcharts-border"></div>
																	<div class="common-color-common" id="flowPop">0</div>
																</td>
															</tr>
														</tbody></table>
													</div>
													<div class="RightTypeEcharts-common  RightTypeEcharts-right1">
														<table style="width: 100%; height: 100%">
															<tbody><tr>
																<td>
																	<div class="common-color-common" id="permanentPop">0</div>
																	<div class="RightTypeEcharts-border"></div>
																	<div>常住人口</div>
																</td>
															</tr>
														</tbody></table>
													</div>
													<div class="RightTypeEcharts-common  RightTypeEcharts-right2">
														<table style="width: 100%; height: 100%">
															<tbody><tr>
																<td>
																	<div class="RightTypeEcharts-name">境外人口</div>
																	<div class="RightTypeEcharts-border"></div>
																	<div class="common-color-common common-color2" id="overseasPop">0</div>
																</td>
															</tr>
														</tbody></table>
													</div>
												</div>
											</td>
										</tr>
									</tbody></table>
													</div>
												</div>
											</div>
										</div>
											<div class="row height30   margin0" style="height: 52%">
											<div class="col-xs-12 height-100  margin0">
												<div class="common common-small-left" style="padding-bottom: 2px;">
													<div class="common-header">
														<div>房屋租住情况</div>
													</div>
													<div class="show height-100 common-center">
													    <div id="ccmPopTenantHouseType" class="echarts"></div>
													</div>
												</div>
											</div>
										</div>

									</div>

									<div class="col-xs-9  height-100 margin0 " 	style="position: relative; height: 99%;width:80%">

									<div style="width:100%;height:100%;position: relative;" class="show">
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
											<table style="height: 100%; width: 90%">
												<tr>
													<td>
														<p class="table-info-title">新密市曲梁镇</p>
														<table class="map-table">

															<tr>
																<td align="right" class="table-info-name">人口：</td>
																<td><div class="map-calss1 common-color map-calss"
																		style="color: #fff" id="eachAll1">0</div></td>
															</tr>
															<tr>
																<td align="right" class="table-info-name">楼栋：</td>
																<td><div class="map-calss2 common-color map-calss"
																		style="color: #fff" id="eachAll2">0</div></td>
															</tr>
															<tr>
																<td align="right" class="table-info-name">房屋：</td>
																<td><div class="map-calss3 common-color map-calss"
																		style="color: #fff" id="eachAll3">0</div></td>
															</tr>
															<tr>
																<td align="right" class="table-info-name">网格：</td>
																<td><div class="map-calss4 common-color map-calss"
																		style="color: #fff" id="eachAll4">0</div></td>
															</tr>
															<tr>
																<td align="right" class="table-info-name">网格员：</td>
																<td><div class="map-calss5 common-color map-calss"
																		style="color: #fff" id="eachAll5">0</div></td>
															</tr>
															<tr>
																<td align="right" class="table-info-name">城市部件：</td>
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

					</div>
					<div class="row height40  margin0" style="height:30%">
						<div class="col-xs-6   height-100 margin0">
							<div class="common common-small-right">
								<div class="common-header">
 							<div>城管行政处罚情况</div>
								</div>
								<div class="show height-100 common-center">
								 <div id="XingZhengChuFa" class="echarts"></div>

								</div>
							</div>
						</div>
						<div class="col-xs-6  height-100 margin0">
							<div class="common common-small-left" style="padding-right: 10px">
								<div class="common-header">
									<div>城市部件状态</div>
								</div>
								<div class="show height-100 common-center">

								<div class="row height30   margin0" style=" height: 100%">
                							<div class="col-xs-4 height-100  margin0 common-center">
                							 	<div id="ChengShiBuJian" class="echarts"></div>
                							</div>
                							<div class="col-xs-4 height-100  margin0 common-center">
                							   <div id="DaoLuJiaoTong" class="echarts"></div>
                							</div>
                							<div class="col-xs-4 height-100  margin0 common-center">
                							     <div id="ShiRongHuangjing" class="echarts"></div>
                							</div>

								</div>


								</div>

							</div>
						</div>

					</div>
				</div>
				<div class="col-xs-3 height-100  margin0">
					<div class="row height30   margin0"
						style="padding: 10px 0 0 0px; height: 25%">

						<div class="col-xs-12 height-100  margin0">
							<div class="common common-small-left">
								<div class="common-header">
									<div>城市管理事件</div>
								</div>
								<div class="show height-100 common-center" style="padding: 20px 10px 5px 10px;">
									<div style="position: relative;overflow: hidden;height: 100%">
										<div class="shijian1">
											<div class="shoulishijian" style="">累计受理事件</div>
											<div class="shoulishijiannum"><span id="event1">0</span>
												<span class="jianfont">件</span>
											</div>
											<div>
												<div class="shijian2 ">
													<div class="shijian-common">安全事故</div>
													<div class="shijian-common-num"><span id="event2">0</span>
														<span class="shijian-common-jian">件</span>
													</div>
												</div>
												<div class="shijian3">
													<div class="shijian-common">群体性事件</div>
													<div class="shijian-common-num"><span id="event3">0</span>
														<span class="shijian-common-jian">件</span>
													</div>
												</div>
												<div class="shijian4">
													<div class="shijian-common">食品安全事故</div>
													<div class="shijian-common-num"><span id="event4">0</span>
														<span class="shijian-common-jian">件</span>
													</div>

												</div>
												<div class="shijian5">
													<div class="shijian-common">有关刑事案件</div>
													<div class="shijian-common-num"><span id="event5">0</span>
														<span class="shijian-common-jian">件</span>
													</div>
												</div>
												<div class="shijian6">
													<div class="shijian-common">其他</div>
													<div class="shijian-common-num"><span id="event6">0</span>
														<span class="shijian-common-jian">件</span>
													</div>
												</div>

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
					<div class="row height30   margin0" style="height: 25%">
						<div class="col-xs-12 height-100  margin0">
							<div class="common common-small-left">
								<div class="common-header">
									<div>城市管理事务统计</div>
								</div>
								<div class="show height-100 common-center">
								<div id="AlarmInfoWeekEcharts" class="echarts"></div>

								</div>
							</div>
						</div>


					</div>
					<div class="row height40   margin0" style="height: 50%">
						<div class="col-xs-12 height-100  margin0">
							<div class="common common-small-left">
								<div class="common-header">
									<div>网格员实时上报</div>
								</div>
								<div class="show height-100 common-center list_lh"  style="overflow: hidden;padding-top:30px">
									<ul id="AlarmUL"></ul>
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