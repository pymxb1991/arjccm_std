<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit|ie-comp|ie-stand" />
<title>社会网格化管理信息平台</title>
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
	href="${ctxStatic}/common/index/css/statIndex.css">
<script type="text/javascript">
	var ctxStatic = '${ctxStatic}',ctx = '${ctx}';
</script>
<script src="${ctxStatic}/ol/ol.js"></script>
<script src="${ctxStatic}/d3/d3.v4.min.js"></script>
<script src="${ctxStatic}/jquery/jquery.cookie.js"></script>
<script src="${ctxStatic}/modules/map/js/mapconfig.js"></script>
<script src="${ctxStatic}/modules/map/js/commonMap.js"></script>
<script src="${ctxStatic}/highcharts/highcharts.js"></script>
<script src="${ctxStatic}/highcharts/highcharts-3d.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script src="${ctxStatic}/custom/date/date.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/statIndex.js"></script>
<style>
.left-side {
	width: 30%;
	overflow: hidden;
	opacity: 1
}

.center-side {
	width: 40%;
}

.right-side {
	width: 30%;
	overflow: hidden;
	opacity: 1
}

.opacity-row, .opacity-row1 {
	opacity: 0;
	cursor: pointer;
}

#mapDetails {
	right: -74%;
}
/*  .opacity-row:hover{
  opacity: 1;
   transition: opacity 2s
}  */

.header-nav {
	left: 40%;
}

</style>
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
				<div class="col-xs-1">
					<!-- 菜单 -->
					<!-- 
			       	<div style="z-index: 9999;position: absolute;width:1000px; top: 21px;left: 106px;" >
							<div class="">
								<span style="float: left;margin-left: 100px"><a href="${ctx}"><i class=""></i>主面板</a></span>
								<span style="float: left;margin-left: 30px"><a href=""><i class=""></i>电子沙盘</a></span>
								<span style="float: left;margin-left: 30px"><a href=""><i class=""></i>视频融合</a></span>
								<span style="float: left;margin-left: 30px"><a href="${ctx}/index"><i class=""></i>网格化首页</a></span>
							</div>
					</div> 
                    -->
					<!-- 菜单 -->
					<div style="z-index: 10000; display: block; position: absolute;">
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
						<div class="aside-nav bounceInUp animated" id="aside-nav">
							<label for="" class="aside-menu" title=""><a
								href="${ctx}" class="menu-item">主面板</a></label> <a href="${ctx}/sys/map/partyConstruction"
								title="首页" class="menu-item menu-second">首页</a> <a
								href="${ctx}/sys/map/statPop" title="人口"
								class="menu-item menu-third">人口</a> <a
								href="${ctx}/sys/map/statIncidentStatistics" title="事件"
								class="menu-item  menu-fourth">事件</a>
						</div>
					</div>
				</div>

				<div class="col-xs-10">

					<h5 class="header-logo">社会网格化管理信息平台</h5>
				</div>
				
				
				<div class="header-nav" >
				   <ul>
				      <%-- <li><a href="${ctx}">主面板</a> --%>
                   <!--- <li><a href="${ctx}/index">地图首页</a></li>  临时删除--->
				      <li><a href="${ctx}/sys/map/partyConstruction">党建架构</a></li>
				      <li class="active" ><a href="${ctx}/sys/map/statIndexCom">基本组成</a></li> 
				   </ul>
				</div>
				<div class="header-nav1" >
				<ul>
				  	  <li><a href="${ctx}/sys/map/statPop">关注对象</a></li>
				      <li><a href="${ctx}/sys/map/shakeOffPoverty">脱贫攻坚</a></li>
				      <li><a href="${ctx}/sys/map/statIncidentStatistics">治安态势</a></li>
				      <li><a href="${ctx}/sys/map/gridManagement">网格管理</a></li>
				</ul>
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
			<div class="row container-center" style="position: relative">
				<div id="StatIndexMap" class="StatIndexMap">
					<div id="pubMap"></div>
				</div>
				<div class="col-xs-3 height-100  margin0 left-side">
					<div class="row height30   margin0 row-bg opacity-row"
						style="height: 49%">

						<div class="col-xs-12 height-100  margin0">
							<div class="common common-small-left">
								<div class="common-header">
									<div>人口数据汇总</div>
								</div>
								<div class="show height-100 common-center"
									style="padding: 10px 10px 5px 10px;">
									<div class="row   margin0 row-line" style="height: 30%">
										<div class="col-xs-8 height-100  margin0">
											<div class=" height-100 common-center">
												<div class="pop-tal" id="popAll">	
												</div>
											</div>
										</div>
										<div class="col-xs-4 height-100  margin0">
											<div class=" height-100">
												<table style="width: 100%; height: 100%">
													<tbody>
														<tr>
															<td style="height: 100%">
																<div id="RightTypeEcharts">
																	<!-- <div id="RightTypeEcharts" class="echarts"></div> -->
																	<div
																		class="RightTypeEcharts-common RightTypeEcharts-left1">
																		<table style="width: 100%; height: 100%">
																			<tbody>
																				<tr>
																					<td>
																						<div class="common-color-common common-color1 top"
																							id="registerPop"></div>
																						<div class="RightTypeEcharts-border top"></div>
																						<div>户籍人口</div>
																					</td>
																				</tr>
																			</tbody>
																		</table>
																	</div>
																	<div
																		class="RightTypeEcharts-common  RightTypeEcharts-left2">
																		<table style="width: 100%; height: 100%">
																			<tbody>
																				<tr>
																					<td>
																						<div class="RightTypeEcharts-name">境外人口</div>
																						<div class="RightTypeEcharts-border"></div>
																						<div class="common-color-common bottom"
																							id="overseasPop"></div>
																					</td>
																				</tr>
																			</tbody>
																		</table>
																	</div>
																	<div
																		class="RightTypeEcharts-common  RightTypeEcharts-right1">
																		<table style="width: 100%; height: 100%">
																			<tbody>
																				<tr>
																					<td>
																						<div class="common-color-common top"
																							id="permanentPop"></div>
																						<div class="RightTypeEcharts-border top"></div>
																						<div>常住人口</div>
																					</td>
																				</tr>
																			</tbody>
																		</table>
																	</div>
																	<div
																		class="RightTypeEcharts-common  RightTypeEcharts-right2">
																		<table style="width: 100%; height: 100%">
																			<tbody>
																				<tr>
																					<td>
																						<div class="RightTypeEcharts-name">流动人口</div>
																						<div class="RightTypeEcharts-border"></div>
																						<div
																							class="common-color-common common-color2 bottom"
																							id="flowPop"></div>
																					</td>
																				</tr>
																			</tbody>
																		</table>
																	</div>
																</div>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="row   margin0 row-line" style="height: 35%">
										<!-- <div class="col-xs-6 height-100  margin0">
											<div class=" height-100 common-center"
												style="padding: 10px 10px 5px 10px;">
												<div id="PopKeyEcharts" class="echarts"></div>
											</div>
										</div>
										<div class="col-xs-6 height-100  margin0">
											<div class=" height-100 common-center"
												style="padding: 10px 10px 5px 10px;">
												<div id="PopsNumEcharts" class="echarts"></div>
											</div>
										</div> -->
										<div class="col-xs-12 height-100  margin0">
											<div class=" height-100 common-center"
												style="padding: 10px 10px 5px 10px;">
												<div id="popEcharts" class="echarts"></div>
											</div>
										</div>
									</div>
									<div class="row   margin0" style="height: 35%">
										<div class="col-xs-12 height-100  margin0">
											<div class=" height-100 common-center"
												style="padding: 5px 10px 5px 10px;">
												<!-- <div id="StreetPopEcharts" class="echarts"></div> -->
												<div id="renyuanqingkuang" class="echarts"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
					<div class="row height30   margin0 row-bg opacity-row"
						style="height: 25%">
						<div class="col-xs-12 height-100  margin0">
							<div class="common common-small-left">
								<div class="common-header">
									<div>组织队伍建设</div>
								</div>
								<div class="show height-100 common-center">
									<div class="row    margin0 height-100 ">
										<div class="col-xs-3 height-100  margin0">
											<div class="height-100 common-center"
												style="padding: 3px 3px 5px 5px">
												<ul class="fengxian-danwei clearfix org">
													<li class="maodn-li1">
														<div class="fengxian-color4">区县</div>
														<div>
															<span class="fengxian-num" id="jiedao">0</span>
														</div>
													</li>
													<li class="maodn-li2">
														<div class="fengxian-color4">网格</div>
														<div>
															<span class="fengxian-num" id="wangge">0</span>
														</div>
													</li>
													<li class="maodn-li3">
														<div class="fengxian-color4">社区</div>
														<div>
															<span class="fengxian-num" id="shequ">0</span>
														</div>
													</li>
													<li class="maodn-li4">
														<div class="fengxian-color4">网格员</div>
														<div>
															<span class="fengxian-num" id="wanggeyuan">0</span>
														</div>
													</li>
												</ul>
											</div>
										</div>
										<div class="col-xs-3 height-100  margin0">
											<div class="height-100 common-center"
												style="padding: 3px 3px 5px 5px">
												<table style="width: 100%; height: 100%">
													<tr>
														<td>群防群治：</td>
														<td class="org-num" id="zuzi">0</td>
													</tr>
													<tr>
														<td>队伍人数：</td>
														<td class="org-num" id="duiwu">0</td>
													</tr>
													<tr>
														<td>综治中心：</td>
														<td class="org-num" id="zhongxin">0</td>
													</tr>
												</table>
											</div>
										</div>
										<div class="col-xs-3 height-100  margin0">
											<div class="height-100 common-center"
												style="padding: 10px 3px 10px 5px">
												<div id="OrgEcharts" class="echarts"></div>
											</div>
										</div>
										<div class="col-xs-3 height-100  margin0">
											<div class="height-100 common-center"
												style="padding: 10px 3px 10px 5px; position: relative;">
												<div id="RanksEcharts" class="echarts"></div>
												<div style="text-align: center; margin-top: -22px;">群防群治队伍构成</div>
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>


					</div>
					<div class="row height40   margin0 row-bg opacity-row"
						style="height: 25%">

						<div class="common common-small-left">
							<div class="common-header">
								<div>城市部件数据一览</div>
							</div>
							<div class="show height-100 common-center"
								style="padding: 10px 10px 5px 10px;">

								<div class="row    margin0 height-100 ">
									<div class="col-xs-2 height-100  margin0">
										<div class=" height-100 common-center"
											style="padding: 20px 0px 5px 0px; position: relative;">
											<ul class="fengxian-danwei clearfix city">
												<li class="maodn-li1">
													<div class="fengxian-color4">各类公共设施</div>
													<div>
														<span class="fengxian-num" id="city1">0</span>
													</div>
												</li>
												<li class="maodn-li2">
													<div class="fengxian-color4">视频监控</div>
													<div>
														<span class="fengxian-num" id="city2">0</span>
													</div>
												</li>
												<li class="maodn-li3">
													<div class="fengxian-color4">护路护线</div>
													<div>
														<span class="fengxian-num" id="city3">0</span>
													</div>
												</li>

											</ul>
										</div>

									</div>
									<div class="col-xs-10 height-100  margin0">
										<div class="row    margin0 height-100 ">
											<div class="col-xs-3 height-100  margin0">
												<div class=" height-100 common-center"
													style="padding: 20px 0px 5px 0px;">
													<div id="ChengShiBuJian" class="echarts"></div>
												</div>
											</div>
											<div class="col-xs-3 height-100  margin0">
												<div class=" height-100 common-center"
													style="padding: 20px 0px 5px 0px;">
													<div id="DaoLuJiaoTong" class="echarts"></div>
												</div>
											</div>
											<div class="col-xs-3 height-100  margin0">
												<div class=" height-100 common-center"
													style="padding: 20px 0px 5px 0px;">
													<div id="ShiRongHuangjing" class="echarts"></div>
												</div>
											</div>
											<div class="col-xs-3 height-100  margin0">
												<div class=" height-100 common-center"
													style="padding: 20px 10px 5px 0px;">
													<div id="CityBuEcharts" class="echarts"></div>
												</div>

											</div>
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>


				</div>


				<div class="col-xs-6  margin0 center-side"
					style="position: relative;">
					<div id="mapDetails">
						<div class="row height30   margin0 row-bg"
							style="height: 100%; overflow: hidden">
							<div class="col-xs-12 height-100  margin0">
								<div class="common">
									<div class="common-header">
										<div>
											<select id="treeType" class="form-control">
												<optgroup>

												</optgroup>
											</select>
										</div>
									</div>
									<div class="show height-100 common-center"
										style="padding: 10px 10px 5px 10px;">
										<table style="width: 100%; height: 100%">
											<tr>
												<td class="td1"><span class="mapDetails-icon xiaoqu"></span></td>
												<td class="td2">社区</td>
												<td id="mapDetails-SheQu">0</td>
											</tr>
											<tr>
												<td class="td1"><span class="mapDetails-icon xiaoqu"></span></td>
												<td class="td2">楼栋</td>
												<td id="mapDetails-LouDong">0</td>
											</tr>
											<tr>
												<td class="td1"><span class="mapDetails-icon xiaoqu"></span></td>
												<td class="td2">单元</td>
												<td id="mapDetails-DanYuan">0</td>
											</tr>
											<tr>
												<td class="td1"><span class="mapDetails-icon xiaoqu"></span></td>
												<td class="td2">房屋</td>
												<td id="mapDetails-FangWu">0</td>
											</tr>
											<tr>
												<td class="td1"><span class="mapDetails-icon xuexiao"></span></td>
												<td class="td2">学校</td>
												<td id="mapDetails-XueXiao">0</td>
											</tr>
											<tr>
												<td class="td1"><span class="mapDetails-icon renkou"></span></td>
												<td class="td2">人口</td>
												<td id="mapDetails-RenKou">0</td>
											</tr>
											<tr>
												<td class="td1"><span
													class="mapDetails-icon zhongdianchangsuo"></span></td>
												<td class="td2">重点场所</td>
												<td id="mapDetails-ZhongDian">0</td>
											</tr>
										</table>
									</div>
								</div>

							</div>
						</div>

					</div>

				</div>
				<div class="col-xs-3 height-100  margin0 right-side">
					<div class="row height30   margin0 row-bg opacity-row1"
						style="height: 49%">

						<div class="col-xs-12 height-100  margin0">
							<div class="common common-small-left">
								<div class="common-header">
									<div>事件数据汇总</div>
								</div>
								<div class="show height-100 common-center"
									style="padding: 10px 10px 5px 10px;">

									<div class="row   margin0 row-line" style="height: 30%">
										<div class="col-xs-5 height-100  margin0">
											<div class="height-100 common-center"
												style="padding: 26px 0 5px 10px">
												<table style="width: 93%; height: 100%">
													<tbody>
														<tr>
															<td>
																<div class="row">
																	<div class="col-xs-4">
																		<div class="alarm-rotate" style="position: relative;">
																			<div class="alarm-rotate-div">
																				<div class="alarm-rotate-img alarm-rotate-img1"></div>
																				<div class="alarm-rotate-img alarm-rotate-img2"></div>
																				<div
																					class="alarm-num common-color-common common-color2"
																					id="HandlindToday">0</div>
																			</div>
																			<div class="alarm-name">待处理</div>
																		</div>
																	</div>
																	<div class="col-xs-4">
																		<div class="alarm-rotate " style="position: relative;">
																			<div class="alarm-rotate-div">
																				<div class="alarm-rotate-img alarm-rotate-img1"></div>
																				<div class="alarm-rotate-img alarm-rotate-img2"></div>
																				<div class="alarm-num common-color-common"
																					id="TotalToday">0</div>
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
																					class="alarm-num common-color-common common-color1"
																					id="HandledToday">0</div>
																			</div>
																			<div class="alarm-name">已处理</div>
																		</div>
																	</div>
																</div>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
										<div class="col-xs-7 height-100  margin0">
											<div class="height-100 common-center"
												style="padding: 3px 3px 5px 5px">
												<div id="EventEcharts1" class="echarts"></div>
											</div>
										</div>
									</div>
									<div class="row   margin0 row-line" style="height: 35%">
										<div class="col-xs-6 height-100  margin0">
											<div class="height-100 common-center"
												style="padding: 3px 3px 5px 5px">
												<div id="SafeDisEcharts" class="echarts"></div>
											</div>
										</div>
										<div class="col-xs-6 height-100  margin0">
											<div class="height-100 common-center"
												style="padding: 3px 3px 5px 5px">
												<div id="SafeHappenEcharts" class="echarts"></div>
											</div>
										</div>

									</div>
									<div class="row   margin0" style="height: 35%">
										<!-- <div class="col-xs-3 height-100  margin0">
											<div class="height-100 common-center"
												style="padding: 3px 3px 5px 5px">
												<div id="SafeTypeEcharts" class="echarts"></div>
											</div>
										</div>
										<div class="col-xs-3 height-100  margin0">
											<div class="height-100 common-center"
												style="padding: 3px 3px 5px 5px">
												<div id="EventTypeLevelEcharts" class="echarts"></div>
											</div>
										</div>
										<div class="col-xs-3 height-100  margin0">
											<div class="height-100 common-center"
												style="padding: 3px 3px 5px 5px">
												<div id="SafeLevelEcharts" class="echarts"></div>
											</div>
										</div>
										<div class="col-xs-3 height-100  margin0">
											<div class="height-100 common-center"
												style="padding: 3px 3px 5px 5px">
												<div id="EventFenLeiEcharts" class="echarts"></div>
											</div>
										</div> -->
										<div class="col-xs-12 height-100  margin0">
											<div class="height-100 common-center"
												style="padding: 3px 3px 5px 5px">
												<div id="findEventYearMap" class="echarts"></div>
											</div>
										</div>
									</div>
								</div>

							</div>
						</div>

					</div>
					<div class="row height30   margin0 row-bg opacity-row"
						style="height: 25%">
						<div class="col-xs-12 height-100  margin0">
							<div class="common common-small-left">
								<div class="common-header">
									<div>案事件流程</div>
								</div>
								<div class="show height-100 common-center">
									<div id="AlarmFlow" class="echarts" style="height: 99%"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="row height40   margin0 row-bg opacity-row"
						style="height: 25%">
						<div class="col-xs-12 height-100  margin0">
							<div class="common common-small-left">
								<div class="common-header">
									<div>风险单位数据分析</div>
								</div>
								<div class="show height-100 common-center">
									<div class="row   margin0   height-100 ">
										<div class="col-xs-4 height-100  margin0">
											<div class="height-100 common-center"
												style="padding: 3px 3px 5px 5px">
												<ul class="fengxian-danwei clearfix">
													<li class="maodn-li1">
														<div class="fengxian-color14">高风险单位</div>
														<div>
															<span class="fengxian-num" id="gao">13</span>
														</div>
													</li>
													<li class="maodn-li2">
														<div class="fengxian-color4">较大风险单位</div>
														<div>
															<span class="fengxian-num" id="da">3</span>
														</div>
													</li>
													<li class="maodn-li3">
														<div class="fengxian-color4">一般风险单位</div>
														<div>
															<span class="fengxian-num" id="yi">6</span>
														</div>
													</li>
													<li class="maodn-li4">
														<div class="fengxian-color4">低风险单位</div>
														<div>
															<span class="fengxian-num" id="di">2</span>
														</div>
													</li>
												</ul>

											</div>
										</div>
										<div class="col-xs-8 height-100  margin0">
											<div class="height-100 common-center"
												style="padding: 3px 3px 5px 5px">
												<div id="whatEcharts" class="echarts"></div>
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>
						<!-- <div class="col-xs-12 height-100  margin0">
							<div class="common common-small-left">
								<div class="common-header">
									<div>非公企业数据分析</div>
								</div>
								<div class="show height-100 common-center"
									style="padding: 10px 10px 5px 10px;">
									<div class="row   margin0   height-100 " style="height: 10%">
										<div class="col-xs-4 height-100  margin0">
											<div class="height-100 common-center"
												style="padding: 12px 5px 5px 5px;">
												非公企业总数：<span class="fei-num" id="qiyeNum">0</span>
											</div>
										</div>
										<div class="col-xs-4 height-100  margin0">
											<div class="height-100 common-center"
												style="padding: 12px 5px 5px 5px;">
												累计纳税：<span class="fei-num" id="leiji">0</span>万元
											</div>
										</div>
										<div class="col-xs-4 height-100  margin0">
											<div class="height-100 common-center"
												style="padding: 12px 5px 5px 5px;">
												同比<span class="fei-num" id="tongbi">+0.0%</span>
											</div>
										</div>

									</div>
									<div class="row   margin0   height-100 " style="height: 90%">
										<div class="col-xs-12 height-100  margin0">
											<div class="height-100 common-center"
												style="padding: 12px 5px 5px 5px;">
												<div id="AlarmInfoWeekEcharts" class="echarts"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div> -->


					</div>
				</div>


			</div>

			<!-- 1 -->
			<div id="index1"
				style="position: absolute; height: 500px; width: 1200px; background: rgba(3, 18, 49, .6); display: none; left: 360px; top: 212px; padding: 20px; z-index: 99">
				<div
					style="position: absolute; top: 10px; right: 10px; width: 40px; height: 40px; color: #fff; font-size: 24px; cursor: pointer"
					class="indexclose">X</div>
				<div id="StreetPopEchartsbck" class="echarts"></div>
			</div>
			<!-- 1 -->
			<!-- 2 -->
			<div id="index2"
				style="display: none; position: absolute; height: 500px; width: 1200px; background: rgba(3, 18, 49, .6); left: 360px; top: 212px;; padding: 20px; z-index: 99">
				<div
					style="position: absolute; top: 10px; right: 10px; width: 40px; height: 40px; color: #fff; font-size: 24px; cursor: pointer;"
					class="indexclose">X</div>
				<div id="whatEchartsbak" class="echarts"></div>
			</div>

			<!-- 2 -->
		</div>
		<script>
			$(".opacity-row").hover(function() {
				$(this).animate({
					opacity : "1"
				}, 1000);
			}, function() {
				$(this).animate({
					opacity : "0"
				}, 100);
			});
				$(".opacity-row1").hover(function(){
					$("#mapDetails").animate({right:"10px"},1000);
					 $(this).animate({opacity:"1"},1000);

				},function(){
					$(this).animate({opacity:"0"},100);
					$("#mapDetails").animate({right:"-73%"},100);
				});

			$(".header-logo").hover(function() {
				$("#mapDetails").animate({
					right : "10px"
				}, 1000);
				$(".opacity-row1").animate({
					opacity : "1"
				}, 1000);
				$(".opacity-row").animate({
					opacity : "1"
				}, 1000);
			}, function() {
				$(".opacity-row1").animate({
					opacity : "0"
				}, 100);
				$(".opacity-row").animate({
					opacity : "0"
				}, 100);
				$("#mapDetails").animate({
					right : "-73%"
				}, 100);
			});
		</script>
</body>
</html>