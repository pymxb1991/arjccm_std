<%@ page import="com.arjjs.ccm.modules.ccm.rest.web.CcmRestOrgArea" %>
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
<script src="${ctxStatic}/common/index/Scripts/js/gridManagement.js"></script>
<style>
.results {
	margin-top: 2%;
}
.common-pading{
width: auto;

}
.grid-group{
 margin-top: 13%;
}
.grid-group h1{
    width: 100%;
    height: 30px;
    line-height: 30px;
    font-size: 24px;
    text-align: center;
    letter-spacing: 10px;
    color: #f28c4a;
    margin: 25px 0 0 0;
}
.grid-ranks{
    margin-top: 6%;
}
.grid-ranks h2 {
    width: 100%;
    height: 26px;
    font-size: 18px;
    text-align: center;
    letter-spacing: 10px;
    color: #f28c4a;
    margin: 0px 0 2px 0;
    line-height: 26px;
}
.grid-ranks p {
    width: 100%;
    font-size: 18px;
    text-align: center;
    margin: 0px 0 20px 0;
    color: #92c0a6;
}
.grid-divide p {
    width:90%;
    font-size: 16px;
    line-height: 30px;
    letter-spacing: 8px;
    font-weight: bold;
    margin: 10% auto 0;
    color: #f28c4a;
    text-align: justify;
}
#detailsDialog{
   width: 99%;
    left: 0;
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
		
		<div class="row-fluid header" style="">
					<%--<div class="span1">
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
				</div>--%>

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
					  <li ><a href="${ctx}/sys/map/statPop">关注对象</a></li>
					      <li><a href="${ctx}/sys/map/shakeOffPoverty">脱贫攻坚</a></li>
					      <li><a href="${ctx}/sys/map/statIncidentStatistics">治安态势</a></li>
					      <li  class="active"><a href="${ctx}/sys/map/gridManagement" >网格管理</a></li>
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
			
			<div class="span9  height100">
				<div class="height66" style="height:100%;">
						<div class="common-header">
									<div>网格管理</div>
								</div>
					<div class="common-pading shadow" style="height:95.5%;padding:10px;">
						
						<div id="map" style="width: 100%; height: 100%">
						<div id="detailsDialog"></div>
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
			</div>
		</div>
	</div>
						</div>
					</div>
				</div>
		

			</div>
			<div class="span3  height100">
				<div class="height33">
						<div class="common-header">
									<div>网格化管理、组团式服务</div>
								</div>
					<div class="common-pading shadow">
						<div class="grid-group">
								<h1>党委领导、政府负责、</h1>
								<h1>社会协同、公众参与、</h1>
								<h1>专群结合、共建共享。</h1>
						</div>
					</div>
				</div>
					<div class="height33">
						<div class="common-header">
									<div>"两支队伍"+"一个阵地"</div>
								</div>
					<div class="common-pading shadow">
						<div class="grid-ranks">
										<div>
											<h2>专职网格员</h2>
											<p>网格配备合理、工作职能明确。</p>
										</div>
										<div>
											<h2>网格志愿者</h2>
											<p>将优质的社会力量或资源引入到社会管理服务。</p>
										</div>
										<div>
											<h2>"四有标准"</h2>
											<p>有办公场所、有标志标识、有办公设备、有办公人员</p>
										</div>
									</div>
					</div>
				</div>
					<div class="height33">
						<div class="common-header">
									<div>网格划分</div>
								</div>
					<div class="common-pading shadow" style="height:85.5%">
						<div class="grid-divide">
							<p>按照“边界明晰、责任明晰、管理明晰”和“一村一网格”原则划分网格，共计“${ count }”基础网格。</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>