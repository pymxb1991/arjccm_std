<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit|ie-comp|ie-stand" /> 
<title>安全生产</title>
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
	href="${ctxStatic}/common/index/css/statNpseSafe.css">
<script type="text/javascript">
	var ctxStatic = '${ctxStatic}',ctx = '${ctx}';
</script>
<script src="${ctxStatic}/ol/ol.js"></script>
<script src="${ctxStatic}/d3/d3.v4.min.js"></script>
<script src="${ctxStatic}/modules/map/js/mapconfig.js"></script>
<script src="${ctxStatic}/modules/map/js/commonMap.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script src="${ctxStatic}/custom/date/date.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/statNpseSafe.js"></script>
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

					<h5 class="header-logo"><b>社会网格化管理信息系统：</b>安全生产</h5>
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
												<!-- 	<div class="jbox-title-panel" style="height: 25px;">
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
																<td align="left" class="table-info-name">安全生产重点企业：</td>
																<td><div class="map-calss1 common-color map-calss"
																		style="color: #fff" id="eachAll1">0</div></td>
															</tr>
															<tr>
																<td align="left" class="table-info-name">消防安全重点企业：</td>
																<td><div class="map-calss2 common-color map-calss"
																		style="color: #fff" id="eachAll2">0</div></td>
															</tr>
															<tr>
																<td align="left" class="table-info-name">物流安全重点企业：</td>
																<td><div class="map-calss3 common-color map-calss"
																		style="color: #fff" id="eachAll3">0</div></td>
															</tr>
															<!--<tr>
																<td align="left" class="table-info-name">安检人员总数：</td>
																<td><div class="map-calss4 common-color map-calss"
																		style="color: #fff" id="eachAll4">0</div></td>
															</tr>  -->
															<tr>
																<td align="left" class="table-info-name">消防站：</td>
																<td><div class="map-calss5 common-color map-calss"
																		style="color: #fff" id="eachAll5">0</div></td>
															</tr>
															<tr>
																<td align="left" class="table-info-name">消防设施总数：</td>
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
						<div class="col-xs-6   height-100 margin0">
							<div class="common common-small-right">
								<div class="common-header">
									<div>重点企业分布</div>
								</div>
								<div class="show height-100 common-center">
								 <div id="KeyCompyDisEcharts" class="echarts"></div>
 
								</div>
							</div>
						</div>
						<div class="col-xs-6  height-100 margin0">
							<div class="common common-small-left" style="padding-right: 10px">
								<div class="common-header">
									<div>安全事故分布</div>
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
				<div class="col-xs-4 height-100  margin0">
					<div class="row height30   margin0"
						style="padding: 10px 0 0 10px; height: 25%">

						<div class="col-xs-12 height-100  margin0">
							<div class="common common-small-left">
								<div class="common-header">
									<div>安全生产重点企业</div>
								</div>
								<div class="show height-100 common-center" style="padding: 10px 10px 5px 10px;">
								   <div class="row  height-100  margin0">
								     <div class="col-xs-5 height-100  margin0">
								     <div class="height-100 common-center"  style="padding:7px 10px 5px 10px;">
								        <div id="WeiHuaEcharts" class="echarts" style="height:87%"></div>
								        <div style="text-align: center;margin-top: -9px;">危化企业</div>
								     </div>
								     </div>
								     <div class="col-xs-7 height-100  margin0">
								       <div class="height-100 common-center"  style="padding: 10px 10px 5px 10px;">
								          <ul class="fengxian-danwei clearfix">
								            <li>
								              <div class="fengxian-color1">高风险单位</div>
								              <div class="fengxian-num" id="riskRank1">0</div>
								            </li>
								            <li>
								             <div  class="fengxian-color2">较大风险单位</div>
								              <div class="fengxian-num" id="riskRank2">0</div>
								            </li>
								            <li>
								             <div  class="fengxian-color3">一般风险单位</div>
								              <div class="fengxian-num" id="riskRank3">0</div>
								            </li>
								            <li>
								              <div  class="fengxian-color4">低风险单位</div>
								             <div  class="fengxian-num" id="riskRank4">0</div>
								            </li>
								          </ul>
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
									<div>安全生产防范检查</div>
								</div>
								<div class="show height-100 common-center">
								<div id="SafePoDisEcharts" class="echarts"></div>
									
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
								   <div class="col-xs-6 height-100  margin0">
								      <div class=" height-100 common-center"  style="padding:0px 10px 5px 10px;">
								      <div id="SafeTypeEcharts" class="echarts"></div>
								      <div style="text-align: center;margin-top: -17px;">事故性质</div>
								      
								   </div>
								   </div>
								   <div class="col-xs-6 height-100  margin0">
								      <div class=" height-100 common-center"  style="padding:0px 10px 5px 10px;">
								     
		                                   <div id="SafeLevelEcharts" class="echarts"></div>
		                                   <div style="text-align: center;margin-top: -17px;">事故级别</div>
		                                   
								   </div>
								   </div>
								</div>
								<div class="row   margin0" style="height: 50%">
								   <div class="col-xs-12 height-100  margin0">
								   <div class=" height-100 common-center"  style="padding:10px 10px 5px 10px;">
								      <div id="SafeHappenEcharts" class="echarts"></div>
								  	  <div class="ShiGU-fasheng-qushi">事故发生趋势</div>
								  
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