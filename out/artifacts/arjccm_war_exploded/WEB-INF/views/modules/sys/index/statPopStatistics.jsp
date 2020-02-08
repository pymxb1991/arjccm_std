<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit|ie-comp|ie-stand" /> 
<title>${fns:getConfig('productName')}</title>
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
<link rel="stylesheet"
	href="${ctxStatic}/bootstrap/2.3.1/css_default/bootstrap.min.css">
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
<link rel="stylesheet" href="${ctxStatic}/common/index/css/PopInfo.css">
<script src="${ctxStatic}/ol/ol.js"></script>
<script src="${ctxStatic}/modules/map/js/mapconfig.js"></script>
<script src="${ctxStatic}/modules/map/js/commonMap.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/tongrenshi.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/PopInfo.js"></script>
<style>
.results {
	margin-top: 2%;
}
.common-pading{
width: auto;

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
	<div class="container-fluid" style="height: 100%; overflow: hidden"
		id="main">
		<div class="context" content="${ctx}"></div>
		<%-- <div class="row-fluid header">
			<div class="span3" style="position: relative;margin-top: 5px">
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
							 <a  href="${ctx}/index" title="首页" class="menu-item menu-second">首页</a>
							 <a href="${ctx}/sys/map/statIncidentStatistics" title="事件" class="menu-item menu-third">事件</a> 
						     <a href="${ctx}/sys/map/statIndexCom" title="概况" class="menu-item  menu-fourth">概况</a>
						</div>
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
						<li><a href="${ctx}/index"> <i
								class="icon-home align-top bigger-125"></i> 首页
						</a></li>
						<li class="lastli"><a href="${ctx}"> <i
								class="icon-folder-close align-top bigger-125"></i> 事件
						</a></li>
					</ul>
				</div> -->
			</div>
			<div class="span6">
				<h5>${fns:getConfig('productName')}</h5>
			</div>
			<div class="span3">
				<div class="Logout">
					<span> <a href="${ctx}/logout"> <i
							class="icon-off align-top bigger-125"></i> 退出
					</a>
					</span>
				</div>
			</div>
		</div> --%>
		<div class="row-fluid header" style="">
				<%-- <div class="span1">
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
								href="${ctx}" class="menu-item">主面板</a></label> <a href="${ctx}/index"
								title="首页" class="menu-item menu-second">首页</a> <a
								href="${ctx}/sys/map/statPop" title="人口"
								class="menu-item menu-third">人口</a> <a
								href="${ctx}/sys/map/statIncidentStatistics" title="事件"
								class="menu-item  menu-fourth">事件</a>
						</div>
					</div>
				</div> --%>

				<div class="span11">
					<h5 class="header-logo" style="font-size:18px;">${fns:getConfig('productName')}</h5>
				</div>
					<div class="header-nav" >
					   <ul>
					      <li><a href="${ctx}">主面板</a>
                    <!--- <li><a href="${ctx}/index">地图首页</a></li>  临时删除--->
					      <li><a href="${ctx}/sys/map/partyConstruction">党建架构</a></li>
					      <li><a href="${ctx}/sys/map/statIndexCom">基本组成</a></li> 
					   </ul>
					</div>
					<div class="header-nav1" >
					<ul>
					  <li class="active" ><a href="${ctx}/sys/map/statPop">关注对象</a></li>
					      <li><a href="${ctx}/sys/map/shakeOffPoverty">脱贫攻坚</a></li>
					      <li><a href="${ctx}/sys/map/statIncidentStatistics">治安态势</a></li>
					      <li><a href="${ctx}/sys/map/gridManagement">网格管理</a></li>
					</ul>
					</div>
				<div class="span1">
					<div class="Logout">
						<span> <a href="${ctx}/logout"> <i
								class="icon-off align-top bigger-125"></i> 退出
						</a>
						</span>
					</div>
				</div>
			</div>
		<div class="row-fluid height100" style="margin-top:5px;">
			<div class="span3  height100">
				<div class="height33">
					<!-- <div class="top-header">实有人口分析</div> -->
						<div class="common-header">
									<div>实有人口分析</div>
								</div>
					<div class="common-pading shadow">
						<div id="RightTypeEcharts" class="echarts"></div>
					</div>
				</div>
				<div class="height33" id="DutyUL">
				<!-- 	<div class="top-header">重点青少年分类统计</div> -->
						<div class="common-header">
									<div>重点青少年分类统计</div>
								</div>
					<div class="common-pading shadow">
						<div id="PopKeyEcharts" class="echarts"></div>
					</div>
				</div>
				<div class="height33">
					<!-- <div class="top-header">重点青少年分析</div> -->
						<div class="common-header">
									<div>重点青少年分析</div>
								</div>
					<div class="common-pading shadow" style="height:87%">
						<div id="PopFlowTable">
							<table>
								<thead>
									<tr class="l-grid-row-alt">
										<td>人员类型</td>
										<td>总数</td>
										<td>违法犯罪人数</td>
									</tr>
								</thead>
								<tbody id="">
									<tr>
										<td>闲散青少年</td>
										<td id="PopFlowTable01">0</td>
										<td id="PopFlowTable02">0</td>
									</tr>
									<tr class="l-grid-row-alt">
										<td>不良行为青少年</td>
										<td id="PopFlowTable03">0</td>
										<td id="PopFlowTable04">0</td>
									</tr>
									<tr>
										<td>流浪乞讨未成年</td>
										<td id="PopFlowTable05">0</td>
										<td id="PopFlowTable06">0</td>
									</tr>
									<tr class="l-grid-row-alt">
										<td>服刑人员未成年子女</td>
										<td id="PopFlowTable07">0</td>
										<td id="PopFlowTable08">0</td>
									</tr>
									<tr>
										<td>农村留守儿童</td>
										<td id="PopFlowTable09">0</td>
										<td id="PopFlowTable10">0</td>
									</tr>
									<tr class="l-grid-row-alt">
										<td>其他</td>
										<td id="PopFlowTable11">0</td>
										<td id="PopFlowTable12">0</td>
									</tr>

								</tbody>
							</table>
						</div>

					</div>
				</div>
			</div>
			<div class="span6  height100">
				<div class="height66">
					<!-- <div class="top-header">人口统计</div> -->
						<div class="common-header">
									<div>人口统计</div>
								</div>
					<div class="common-pading shadow" style="height:95%">
						<div class="row-fluid results">
							<div class="span6">
								<div class="common-pading" style="text-align: center">
									<b class="common-color" id="CameraTotal">0</b> <br>&nbsp;总数
								</div>
							</div>
							<div class="span6">
								<div class="common-pading" style="text-align: center">
									<b class="common-color" id="OnLineRate">0</b> <br>&nbsp;本月新增人数<i class=" icon-arrow-up align-top bigger-125" style="color:#f23f40"></i>
								</div>
							</div>
						</div>
						
						<div id="map" style="width: 97.5%; height: 100%">
						<div id="popup" class="ol-popup  bb">
		<div class="">
			<a href="#" id="popup-closer" class="ol-popup-closer  icon-remove "
				title="关闭" onmouseover="$(this).addClass('jbox-close-hover');"
				onmouseout="$(this).removeClass('jbox-close-hover');"
				style="position: absolute; display: block; cursor: pointer; top: 11px; right: 11px; width: 15px; height: 15px;color:#fff" ></a>
			<!-- <div class="jbox-title-panel" style="height: 25px;">
				<div class="jbox-title">信息</div>
			</div> -->
			<div class="jbox-state">
				<div id="popup-content" style="padding: 8px 15px;"></div>
				<!-- <div class="jbox-button-panel"
					style="height: 25px; padding: 0 15px 20px; text-align: right;">
					<span class="jbox-bottom-text"
						style="float: left; display: block; line-height: 25px;"></span>
					<button class="jbox-button" id="popup-closer1"
						style="padding: 0px 10px 0px 10px;">关闭</button>
				</div> -->
			</div>
		</div>
	</div>
						</div>
					</div>
				</div>
				<div class="height33">
					<!-- <div class="top-header">重点青少年统计TOP10</div> -->
						<div class="common-header">
									<div>重点青少年统计</div>
								</div>
					<div class="common-pading shadow"  style="height:86%">
						<div id="PopFollowEcharts" class="echarts"></div>
					</div>
				</div>

			</div>
			<div class="span3  height100">
				<div class="height33">
					<!-- <div class="top-header">特殊人群分析</div> -->
						<div class="common-header">
									<div>特殊人群分析</div>
								</div>
					<div class="common-pading shadow">
						<div id="PopsNumEcharts" class="echarts"></div>
					</div>
				</div>
				<div class="height66" style="height:64%">
					<!-- <div class="top-header">本月人口信息</div> -->
						<div class="common-header">
									<div>本月人口信息</div>
								</div>
					<div class="common-pading shadow" id="PopFollowPop" style="overflow:initial;height:96.4%">
						<div class="table-div" style="height:95%">
							<table class="table-bg">
								<thead>
									<tr class="tr-bg">
										<td class="tr-bg" style="text-align: center">分类</td>
										<td>类型</td>
										<td><span>本月新增人数</span></td>
										<td><span>总数</span></td>
									</tr>
									<tr>
										<td colspan="4"></td>
									</tr>
								</thead>
								<tbody>
								<tr>
										<td colspan="4"></td>
									</tr>
									<tr class="tr-bg">
										<td rowspan="4" class="tr-bg" style="text-align: center">
											实有人口</td>
										<td>户籍人口</td>
										<td><span class="color-blue" id="numPopFollowPop101">0</span>
										</td>
										<td><span class="color-blue" id="numPopFollowPop201">0</span>
										</td>
									</tr>
									<tr>
										<td>流动人口</td>
										<td><span class="color-blue" id="numPopFollowPop102">0</span>
										</td>
										<td><span class="color-blue" id="numPopFollowPop202">0</span>
										</td>
									</tr>
									<tr class="tr-bg">
										<td>境外人口</td>
										<td><span class="color-blue" id="numPopFollowPop103">0</span>
										</td>
										<td><span class="color-blue" id="numPopFollowPop203">0</span>
										</td>
									</tr>
									<tr>
										<td>常住人口</td>
										<td><span class="color-blue" id="numPopFollowPop111">0</span>
										</td>
										<td><span class="color-blue" id="numPopFollowPop211">0</span>
										</td>
									</tr>
									<tr>
										<td colspan="4"></td>
									</tr>
									<tr class="tr-bg">
										<td rowspan="9" class="tr-bg" style="text-align: center">
											特殊人群</td>
											<td>安置帮教人员</td>
										<td><span class="color-blue" id="numPopFollowPop108">0</span>
										</td>
										<td><span class="color-blue" id="numPopFollowPop208">0</span>
										</td>
										
									</tr>
									<tr>
										<td>社区矫正人员</td>
										<td><span class="color-blue" id="numPopFollowPop105">0</span>
										</td>
										<td><span class="color-blue" id="numPopFollowPop205">0</span>
										</td>
									</tr>
									<tr class="tr-bg">
										<td>肇事肇祸等严重精神障碍患者</td>
										<td><span class="color-blue" id="numPopFollowPop106">0</span>
										</td>
										<td><span class="color-blue" id="numPopFollowPop206">0</span>
										</td>
									</tr>
									<tr>
										<td>吸毒人员</td>
										<td><span class="color-blue" id="numPopFollowPop107">0</span>
										</td>
										<td><span class="color-blue" id="numPopFollowPop207">0</span>
										</td>
									</tr>
									<tr class="tr-bg">
									<td>艾滋病危险人员</td>
										<td><span class="color-blue" id="numPopFollowPop109">0</span>
										</td>
										<td><span class="color-blue" id="numPopFollowPop209">0</span>
										</td>
									</tr>
									<tr>
									<td>重点上访人员</td>
										<td><span class="color-blue" id="numPopFollowPop112">0</span>
										</td>
										<td><span class="color-blue" id="numPopFollowPop212">0</span>
										</td>

									</tr>
									<tr class="tr-bg">
										<td>涉教人员</td>
										<td><span class="color-blue" id="numPopFollowPop113">0</span>
										</td>
										<td><span class="color-blue" id="numPopFollowPop213">0</span>
										</td>
									</tr>
									<tr>
										<td>危险品从业人员</td>
										<td><span class="color-blue" id="numPopFollowPop114">0</span>
										</td>
										<td><span class="color-blue" id="numPopFollowPop214">0</span>
										</td>
									</tr>
									<tr class="tr-bg">
										<td>留守人员</td>
										<td><span class="color-blue" id="numPopFollowPop104">0</span>
										</td>
										<td><span class="color-blue" id="numPopFollowPop204">0</span>
										</td>
									</tr>
									<tr>
										<td colspan="4"></td>
									</tr>
									<tr class="tr-bg">
										<td rowspan="4" class="tr-bg" style="text-align: center">
											重点人员</td>
										<td>重点青少年</td>
										<td><span class="color-blue" id="numPopFollowPop110">0</span>
										</td>
										<td><span class="color-blue" id="numPopFollowPop210">0</span>
										</td>
									</tr>
									<tr>
										<td>特殊关怀人员</td>
										<td><span class="color-blue" id="numPopFollowPop115">0</span>
										</td>
										<td><span class="color-blue" id="numPopFollowPop215">0</span>
										</td>
									</tr>
									<tr class="tr-bg">
										<td>老年人</td>
										<td><span class="color-blue" id="numPopFollowPop116">0</span>
										</td>
										<td><span class="color-blue" id="numPopFollowPop216">0</span>
										</td>
									</tr>
									<tr>
										<td>党员</td>
										<td><span class="color-blue" id="numPopFollowPop117">0</span>
										</td>
										<td><span class="color-blue" id="numPopFollowPop217">0</span>
										</td>
									</tr>
								</tbody>
							</table>
						</div>

					</div>

				</div>
			</div>
		</div>
	</div>
</body>
</html>