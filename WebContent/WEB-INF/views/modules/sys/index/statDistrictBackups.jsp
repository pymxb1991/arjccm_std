<!DOCTYPE html>

<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="renderer" content="webkit|ie-comp|ie-stand" /> 
<title></title>
<style type="text/css">
/* reset */
body, div, p, h1, h2, a, span, img, ul, li { margin: 0; padding: 0; }
ul { list-style: none; }
/* general */
html, body { width: 100%; height: 100%; }
/* html { background: #111 url(data:image/jpeg;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAAAECAIAAAAmkwkpAAAAGklEQVR42mMQhAEJCQkGOAtIMsBZIA6cBQQAW5wDhYzvi1MAAAAASUVORK5CYII%3D); color: #fff; }
 */
 html { background: #111 url(/arjccm/static/common/index/areaIndex/images/bg.png); color: #fff;background-size: 100% 100% }
 body { font-size: 100%; font-family: 'OpenSansLight', sans-serif; line-height: 1em; -webkit-font-smoothing: antialiased; -webkit-text-size-adjust: 100%; }
@media (min-width: 1601px) and (max-width:1680px)  {
  body { font-size: 90%;}
}
@media (min-width: 1441px) and (max-width:1600px)   {
  body { font-size: 80%;}
}
@media (min-width: 1367px) and (max-width:1440px)  {
 body { font-size: 70%;}
}
@media  (max-width:1366px)  {
 body { font-size: 60%;}
}
a, a img { border: none; }
img, object, embed { max-width: 100%; height: auto; }
.clearfix:before, .clearfix:after { content: ""; display: table; }
.clearfix:after { clear: both; }
.clearfix { zoom: 1; }

/* metro layout */
.metro-layout { width: 100%; height: 100%; }
.metro-layout .header { height: 20%; position: relative; }
.metro-layout .header h1 { position: absolute; top: 50%; margin-top: -0.6em; left: 50px; padding-left: 0.1em; font-size: 2.6em; line-height: 1em; }
.metro-layout .header .controls { position: absolute; top: 50%; height: 48px; margin-top: -24px; right: 50px; }
.metro-layout .header .controls span { float: right; width: 48px; height: 48px; margin-left: 5px; opacity: 0.25; filter: alpha(opacity=25); cursor: pointer; background: url(../${ctxStatic}/common/index/areaIndex/images/sprite.png) no-repeat -999px 0; }
.metro-layout .header .controls span:hover { opacity: 1; filter: none; }
.metro-layout .header .controls span.prev { background-position: 0 0; }
.metro-layout .header .controls span.next { background-position: -48px 0; }
.horizontal .header .controls span.toggle-view { background-position: -96px 0; }
.vertical .header .controls span.toggle-view { background-position: -144px 0; }
.metro-layout .header .controls span.up { background-position: -192px 0; }
.metro-layout .header .controls span.down { background-position: -240px 0; }
.horizontal .header .controls span.up, .horizontal .header .controls span.down, .vertical .header .controls span.prev, .vertical .header .controls span.next { display: none; }
.metro-layout .content { height: 80%; overflow: hidden; }
.metro-layout .content .items {  position: relative; overflow: hidden; margin:auto;   margin-top: 5em; }
.horizontal .content .items { height: 80%; }
.vertical .content .items { padding-bottom: 50px; }
.metro-layout .box { float: left; position: relative; margin: 0.5em; padding: 0.5em; background: #555; width: 13em; height: 9em; text-decoration: none; cursor: pointer; overflow: hidden; color: #fff; background: #00a8ec; z-index: 9; }
.metro-layout .box:hover { opacity: 0.85; filter: alpha(opacity=85); }
.metro-layout .box span { position: absolute; left: 0.5em; bottom: 0.5em; font-size: 1.4em; font-weight: normal; z-index: 8; }
.metro-layout .box img.icon { position: absolute; left: 50%; top: 50%; margin-left: -32px; margin-top: -32px; z-index: 7; }
.metro-layout .box img.big { margin-left: -64px; margin-top: -64px; }
.metro-layout .box img.cover { position: absolute; left: 0; top: 0; width: 100%; z-index: 6; }
.metro-layout .width2 { width: 28em; }
.metro-layout .width3 { width: 49em; }
.metro-layout .width4 { width: 66em; }
.metro-layout .height2 { height: 20em; }
.metro-layout .height3 { height: 28em; }
.metro-layout .height4 { height: 38em; }

@media screen and (-webkit-min-device-pixel-ratio:0) {
	.metro-layout .content { overflow: auto; }
	::-webkit-scrollbar { width: 10px; height: 10px; }
	::-webkit-scrollbar-track:enabled { background-color: #0d0d0d; }
	::-webkit-scrollbar-thumb:vertical { background-color: #fff; }
	::-webkit-scrollbar-thumb:horizontal { background-color: #fff; }
}

::selection { background: #158ca0; color: #fff; }
::-moz-selection { background: #158ca0; color: #fff; }

/* mobile support */
@media handheld, only screen and (max-width: 1024px){
	.metro-layout .header h1 { font-size: 2.2em; margin-top: -0.5em; left: 20px; }
	.metro-layout .header .controls { right: 20px; }
	.metro-layout .content .items { padding: 0 20px; }
}
/* mobile support */
@media handheld, only screen and (max-width: 480px){
	.metro-layout .header { position: relative; text-align: center; }
	.metro-layout .header h1 { left: 5px; font-size: 1.5em; margin-top: -0.5em; }
	.metro-layout .header .controls { position: static; width: 100%; height: auto; margin-top: 0; }
	.metro-layout .header .controls span { display: none !important; }
	.metro-layout .content .items { padding: 0 5px; }
	.vertical .content .items { padding-bottom: 0; }
}
</style>

  <!--[if lt IE 9]>
     <script src="/arjccm/static/jquery/html5shiv.js" type="text/javascript"></script>
  <![endif]-->

<script src="${ctxStatic}/common/index/areaIndex/js/jquery.min.js"></script>
<script src="${ctxStatic}/common/index/areaIndex/js/jquery.plugins.min.js"></script>
<script src="${ctxStatic}/common/index/areaIndex/js/metro.js"></script>
</head>
<body sytle="overflow-x:hidden;">
<!-- <base target="_blank" /> -->
<base />
<!-- 代码 开始 -->
<div class="metro-layout horizontal">
	<div class="header" style="height:10em; line-height:25px;  ">
                           
		<h1>${fns:getConfig('showName')}综治信息平台</h1>
		<!-- div class="controls">
			<span class="down" title="Scroll down"></span>
			<span class="up" title="Scroll up"></span>
			<span class="next" title="向右滚动"></span>
			<span class="prev" title="向左滚动"></span>
			<span class="toggle-view" title="切换滚动方式"></span>
		</div-->
	</div>
	<div class="content clearfix" style="height:45em;margin-top: 2em;">
		<div class="items" >
                                        <!--href="" style="height:16em;"-->
			<a href="${ctx}/index"  class="box width2" ><span>地图首页</span><img class="icon" style="height:80px;width:80px; " src="${ctxStatic}/common/index/areaIndex/images/地图管理.png" /></a>
<%-- 			<a onclick="TiaoZhuan(120000,'${ctx}/view/vCcmOrg/index')"  class="box " style="background: #43b51f;"><span>综治机构</span><img class="icon big" style="height:120px;width:120px; " src="${ctxStatic}/common/index/areaIndex/images/综治机构.png" /></a> --%>		
 			<a href="http://m.huiminanju.com/rent/house" target="_blank" class="box " style="background: #43b51f;"><span>房屋出租管理系统</span><img class="icon" style="height:70px;width:70px; " src="${ctxStatic}/common/index/areaIndex/images/出租屋管理.png" /></a>
 
			<%-- <a onclick="TiaoZhuan(130000,'${ctx}/org/ccmOrgSocialorg')" class="box"><span>社会组织</span><img class="icon " style="height:65px;width:70px; " src="${ctxStatic}/common/index/areaIndex/images/社会组织.png" /></a> --%>
			
			<a href="http://60.169.77.187:10088/drug"  target="_blank"  class="box"><span>禁毒管理</span><img class="icon " style="height:65px;width:70px; " src="${ctxStatic}/common/index/areaIndex/images/禁毒.png" /></a>

<%-- 			<a onclick="TiaoZhuan(120000,'${ctx}/view/vCcmTeam/index')" class="box" style="background: #3d2cd3;"><span>综治队伍</span><img class="icon big" style="height:130px;width:130px; " src="${ctxStatic}/common/index/areaIndex/images/综治队伍.png" /></a>
 --%>		
 			<a href="http://m.huiminanju.com/passport/dispute_login" target="_blank" class="box" style="background: #3d2cd3;"><span>矛盾纠纷化解平台</span><img class="icon" style="height:70px;width:70px; " src="${ctxStatic}/common/index/areaIndex/images/矛盾纠纷排查.png" /></a>
			<a onclick="TiaoZhuan(130000,'${ctx}/report/ccmReportOthers/organization')" class="box" style="background: #00f591;"><span>非公有制经济组织</span><img class="icon big" style="height:134px;width:130px; " src="${ctxStatic}/common/index/areaIndex/images/非公有制经济组织.png" /></a>
			<a href="${ctx}/sys/map/statIndexCom" class="box width2" style="background: #640f6c;" ><span>大数据可视化</span><img class="icon" style="height:70px;width:70px; " src="${ctxStatic}/common/index/areaIndex/images/可视化.png" /></a>
			<a onclick="TiaoZhuan(130000,'${ctx}/report/ccmReportOthers/houseAndBuild')" class="box" style="background: #00aeef;"><span>楼栋管理</span><img class="icon" style="height:70px;width:70px; " src="${ctxStatic}/common/index/areaIndex/images/室内重点.png" /></a>
			<a onclick="TiaoZhuan(130000,'${ctx}/report/ccmReportOthers/keyPlace')" class="box" style="background: #4c5e51;"><span>重点场所</span><img class="icon" style="height:82px;width:85px; " src="${ctxStatic}/common/index/areaIndex/images/重点场所.png" /></a>
			<a onclick="TiaoZhuan(130000,'${ctx}/report/ccmPeopleStat/statisticsPage?title=ccmPeopleStatPerson')" class="box" style="background: #60ff08;"><span>实有人口管理</span><img class="icon" style="height:70px;width:70px; " src="${ctxStatic}/common/index/areaIndex/images/实有人口管理.png" /></a>
			<a onclick="TiaoZhuan(130000,'${ctx}/report/ccmPeopleStat/statisticsPage?title=ccmPeopleStatTeenager')" class="box" style="background: #00a9ec;"><span>重点人群</span><img class="icon " style="height:80px;width:80px; " src="${ctxStatic}/common/index/areaIndex/images/重点人群.png" /></a>
			<a onclick="TiaoZhuan(130000,'${ctx}/report/ccmPeopleStat/statisticsPage?title=ccmPeopleStatRelease')" class="box" style="background: #3c5b9b;"><span>特殊人群</span><img class="icon" style="height:80px;width:80px; " src="${ctxStatic}/common/index/areaIndex/images/特殊人群.png" /></a>
			<%-- <a onclick="TiaoZhuan(170000,'${ctx}/report/ccmPeopleStat/statisticsPage?title=ccmPeopleStatPerson')" class="box" style="background: #f874a4;"><span>实有人口分析</span><img class="icon" style="height:70px;width:70px; " src="${ctxStatic}/common/index/areaIndex/images/实有人口.png" /></a>
			<a onclick="TiaoZhuan(170000,'${ctx}/report/ccmPeopleStat/statisticsPage?title=ccmPeopleStatBehind')" class="box height2"><span>特殊人群分析</span><img class="icon" style="height:80px;width:80px; " src="${ctxStatic}/common/index/areaIndex/images/特殊人群服务管理.png" /></a>
			<a onclick="TiaoZhuan(170000,'${ctx}/report/ccmPeopleStat/statisticsPage?title=ccmPeopleStatTeenager')" class="box" style="background: #6b6b6b;"><span>重点人员分析</span><img class="icon" style="height:65px;width:65px; " src="${ctxStatic}/common/index/areaIndex/images/重点人员管控.png" /></a> --%>
			<a onclick="TiaoZhuan(130000,'${ctx}/report/ccmPeopleStat/statisticsPage?title=ccmPeopleStatPerson')" class="box" style="background: #f874a4;"><span>实有人口分析</span><img class="icon" style="height:70px;width:70px; " src="${ctxStatic}/common/index/areaIndex/images/实有人口.png" /></a>
			<a onclick="TiaoZhuan(130000,'${ctx}/report/ccmPeopleStat/statisticsPage?title=ccmPeopleStatRelease')" class="box height2"><span>特殊人群分析</span><img class="icon" style="height:80px;width:80px; " src="${ctxStatic}/common/index/areaIndex/images/特殊人群服务管理.png" /></a>
			<a onclick="TiaoZhuan(130000,'${ctx}/report/ccmPeopleStat/statisticsPage?title=ccmPeopleStatTeenager')" class="box" style="background: #6b6b6b;"><span>重点人员分析</span><img class="icon" style="height:65px;width:65px; " src="${ctxStatic}/common/index/areaIndex/images/重点人员管控.png" /></a>
			
			<a onclick="TiaoZhuan(170000,'${ctx}/report/ccmIncidentBegin/police')" class="box" style="background: #43b51f;"><span>案事件统计</span><img class="icon" style="height:80px;width:80px; " src="${ctxStatic}/common/index/areaIndex/images/案事件统计.png" /></a>
			<a onclick="TiaoZhuan(150000,'${ctx}/event/ccmEventIncident/list')"  class="box"><span>事件管理</span><img class="icon" style="height:80px;width:80px; " src="${ctxStatic}/common/index/areaIndex/images/受理事件.png" /></a>
			<a onclick="TiaoZhuan(150000,'${ctx}/event/ccmEventCasedeal/')"   class="box height2"  style="background: #00f591;"><span>事件处理</span><img class="icon" style="height:80px;width:80px; " src="${ctxStatic}/common/index/areaIndex/images/事件处理.png" /></a>
<%-- 			<a class="box" style="background: #00aeef;"><span>应急预案</span><img class="icon" style="height:80px;width:80px; " src="${ctxStatic}/common/index/areaIndex/images/应急预案.png" /></a>
			<a class="box" style="background: #640f6c;"><span>地图管理</span><img class="icon" style="height:80px;width:80px; " src="${ctxStatic}/common/index/areaIndex/images/地图管理.png" /></a>
			<a class="box" style="background: #d32c2c;"><span>地图工具</span><img class="icon big" style="height:80px;width:80px; " src="${ctxStatic}/common/index/areaIndex/images/地图工具.png" /></a>
			<a class="box" style="background: #3c5b9b;"><span>运维管理</span><img class="icon" style="height:80px;width:80px; " src="${ctxStatic}/common/index/areaIndex/images/运维管理.png" /></a> --%>
		
		</div>
	</div>
</div>
<!-- 代码 结束 -->


<script>
function TiaoZhuan(id,url){
	
	//href="${ctx}/view/vCcmOrg/index" 
	 window.location.href="${ctx}/sys/map/indexJump?id="+id+"&url="+url;
	 
}
</script>
</body>
</html>