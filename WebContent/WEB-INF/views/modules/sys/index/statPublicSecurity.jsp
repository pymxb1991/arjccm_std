<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit|ie-comp|ie-stand" /> 
<title>平安建设</title>
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
	<link rel="stylesheet"
	href="${ctxStatic}/common/index/css/statPubliceSe.css">
<script type="text/javascript">
	var ctxStatic = '${ctxStatic}',ctx = '${ctx}';
</script>
<script src="${ctxStatic}/ol/ol.js"></script>
<script src="${ctxStatic}/d3/d3.v4.min.js"></script>
<script src="${ctxStatic}/modules/map/js/mapconfig.js"></script>
<script src="${ctxStatic}/modules/map/js/commonMap.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script src="${ctxStatic}/custom/date/date.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/statPubliceSe.js"></script>
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
			       	<div style="z-index: 9999;position: absolute;width:1000px; top: 21px;left: 106px;" >
							<div class="">
								<span style="float: left;margin-left: 100px"><a href="${ctx}"><i class=""></i>主面板</a></span>
								<span style="float: left;margin-left: 30px"><a href="#" target="_blank" onclick="pbsSubmit();"><i class=""></i>智慧党建</a></span>
								<span style="float: left;margin-left: 30px"><a href="#" target="_blank" onclick="videoSubmit();"><i class=""></i>视频融合</a></span>
								<span style="float: left;margin-left: 30px"><a href="${ctx}/index"><i class=""></i>网格化首页</a></span>
							</div>
					</div> 
                   
				</div>
				<div class="col-xs-10">

					<h5 class="header-logo"><b>社会网格化管理信息系统：</b>平安建设</h5>
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
														<div>矛盾纠纷化解</div>
													</div>
													<div class="show height-100 common-center">
													  <ul class="fengxian-danwei clearfix">
											            <li class="maodn-li1">
											              <div class="fengxian-color1">累计受理纠纷</div>
											              <div><span  class="fengxian-num" id="huajie1">0</span>件</div>
											            </li>
											            <li  class="maodn-li2">
											             <div  class="fengxian-color4">个体性事件</div>
											              <div><span  class="fengxian-num" id="huajie2">0</span>件</div>
											            </li>
											            <li  class="maodn-li3">
											             <div  class="fengxian-color4">一般群体性事件</div>
											              <div><span  class="fengxian-num" id="huajie3">0</span>件</div>
											            </li>
											            <li  class="maodn-li4">
											              <div  class="fengxian-color4">重大群体性事件</div>
											             <div ><span  class="fengxian-num" id="huajie4">0</span>件</div>
											            </li>
											          </ul>
													</div>
												</div>
											</div>
										</div>
											<div class="row height30   margin0" style="height: 52%">
											<div class="col-xs-12 height-100  margin0">
												<div class="common common-small-left" style="padding-bottom: 2px;">
													<div class="common-header">
														<div>治安重点场所</div>
													</div>
													<div class="show height-100 common-center">
													    <div id="SafePubEcharts" class="echarts"></div>
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
											<table  class="table-info-table">
												<tr>
													<td>
														<p class="table-info-title">滨海新区杭州道街道</p>
														<table class="map-table">

															<tr>
																<td align="right" class="table-info-name">社区民警/辅警：</td>
																<td><div class="map-calss1 common-color map-calss"
																		style="color: #fff" id="eachAll1">0</div></td>
															</tr>
															<tr>
																<td align="right" class="table-info-name">公安警务站：</td>
																<td><div class="map-calss2 common-color map-calss"
																		style="color: #fff" id="eachAll2">0</div></td>
															</tr>
															<tr>
																<td align="right" class="table-info-name">网格员：</td>
																<td><div class="map-calss3 common-color map-calss"
																		style="color: #fff" id="eachAll3">0</div></td>
															</tr>
															<tr>
																<td align="right" class="table-info-name">视频监控：</td>
																<td><div class="map-calss4 common-color map-calss"
																		style="color: #fff" id="eachAll4">0</div></td>
															</tr>
															<tr>
																<td align="right" class="table-info-name">应急避难场所：</td>
																<td><div class="map-calss5 common-color map-calss"
																		style="color: #fff" id="eachAll5">0</div></td>
															</tr>
															<tr>
																<td align="right" class="table-info-name">应急物资：</td>
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
 							<div>特殊人群分布</div>
								</div>
								<div class="show height-100 common-center">
								 <div id="PopsNumEcharts" class="echarts"></div>
 
								</div>
							</div>
						</div>
						<div class="col-xs-6  height-100 margin0">
							<div class="common common-small-left" style="padding-right: 10px">
								<div class="common-header">
									<div>事件区域分布</div>
								</div>
								<div class="show height-100 common-center">
									<div id="SafeDisEcharts" class="echarts"></div>
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
				<div class="col-xs-3 height-100  margin0">
					<div class="row height30   margin0"
						style="padding: 10px 0 0 0px; height: 25%">

						<div class="col-xs-12 height-100  margin0">
							<div class="common common-small-left">
								<div class="common-header">
									<div>今日事件</div>
								</div>
								<div class="show height-100 common-center" style="padding: 10px 10px 5px 10px;">
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
																		<div class="alarm-num common-color-common common-color2" id="HandlindToday">0</div>
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
					<div class="row height30   margin0" style="height: 25%">
						<div class="col-xs-12 height-100  margin0">
							<div class="common common-small-left">
								<div class="common-header">
									<div>事件处理</div>
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
									<div>安全事故分析</div>
								</div>
								<div class="show height-100 common-center"  style="padding:10px 10px 5px 10px;">
								<div class="row   margin0" style="height: 50%">
								   <div class="col-xs-4 height-100  margin0">
								      <div class=" height-100 common-center"  style="padding:0px 10px 5px 10px;">
								      <div id="SafeTypeEcharts" class="echarts"></div>
								      <div style="text-align: center;margin-top: -17px;">事件性质</div>
								      
								   </div>
								   </div>
								      <div class="col-xs-4 height-100  margin0">
								      <div class=" height-100 common-center"  style="padding:0px 10px 5px 10px;">
								      <div id="EventTypeLevelEcharts" class="echarts"></div>
								      <div style="text-align: center;margin-top: -17px;">事件分级</div>
								      
								   </div>
								   </div>
								   <div class="col-xs-4 height-100  margin0">
								      <div class=" height-100 common-center"  style="padding:0px 10px 5px 10px;">
								     
		                                   <div id="SafeLevelEcharts" class="echarts"></div>
		                                   <div style="text-align: center;margin-top: -17px;">事件类型</div>
		                                   
								   </div>
								   </div>
								</div>
								<div class="row   margin0" style="height: 50%">
								<div class="col-xs-4 height-100  margin0">
								   <div class=" height-100 common-center"  style="padding:10px 10px 5px 10px;">
								      <div id="EventFenLeiEcharts" class="echarts"></div>
		                              <div style="text-align: center;margin-top: -25px;">事件分类</div>
								       
								   </div>
								   </div>
								   <div class="col-xs-8 height-100  margin0">
								   <div class=" height-100 common-center"  style="padding:10px 10px 5px 10px;">
								      <div id="SafeHappenEcharts" class="echarts"></div>
								  	  <div style="text-align: center;margin-top: -25px;">事故发生趋势</div>
								  
								   </div>
								    
								      
								   </div>
								</div>
<!-- 									<div id="PopsNumEcharts" class="echarts"></div>
 -->								</div>
							</div>
						</div>
					

					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>