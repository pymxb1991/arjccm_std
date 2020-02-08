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
<link rel="stylesheet"
	href="${ctxStatic}/iconfont/iconfont.css">
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
<script src="${ctxStatic}/common/index/Scripts/js/shakeOffPoverty.js"></script>
<style>
.results {
	margin-top: 2%;
}
.common-pading{
width: auto;

}
.shoulishijian {
	font-size: 20px;
    margin-left: 40px;
    margin-top: 10px;
}

.shoulishijiannum {
	color: #A53A52;
	font-size: 45px;
    margin-left: 50px;
}

.jianfont {
	marfin-top: 5px;
	font-size: 16px;
	color: #A53A52;
}

.shijian-common {
	font-size: 20px;
    margin-left: 40px;
    line-height: 40px;
    margin-top: 10px;
}

.shijian-common-num {
	margin-left: 7px;
	color: #5AAFB2;
	font-size: 28px;
}

.shijian-common-jian {
	color: #fff;
	font-size: 14px;
}

.shijian1 {
	border: 1px solid #10559a;
	    width: 260px;
    height: 113px;
	line-height: 40px;
	    margin-top: 20px;
}

.shijian2 {
	border: 1px solid #10559a;
	width: 200px;
	height: 100px;
	line-height: 24px;
	position: absolute;
	left: 376px;
	top: 53px;
}

.shijian3 {
    border: 1px solid #10559a;
    width: 200px;
    height: 100px;
    line-height: 24px;
    position: absolute;
    left: 685px;
    top: 53px;
}

.shijian4 {
	border: 1px solid #10559a;
	width: 200px;
	height: 100px;
	line-height: 24px;
	position: absolute;
	left: 30px;
	 top: 220px;
}

.shijian5 {
	border: 1px solid #10559a;
	width: 200px;
	height:100px;
	line-height: 24px;
	position: absolute;
	left: 247px;
    top: 220px;
}

.shijian6 {
	border: 1px solid #10559a;
	width: 200px;
	height: 100px;
	line-height: 24px;
	position: absolute;
	left: 466px;
 top: 220px;
}
.shijian7 {
	border: 1px solid #10559a;
	width: 200px;
	height: 100px;
	line-height: 24px;
	position: absolute;
	    left: 685px;
	 top: 220px;
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
					  <li><a href="${ctx}/sys/map/statPop">关注对象</a></li>
					      <li class="active" ><a href="${ctx}/sys/map/shakeOffPoverty">脱贫攻坚</a></li>
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
						<div class="common-header">
									<div>贫困人口乡镇及脱贫属性</div>
								</div>
					<div class="common-pading shadow">
						<div id="RightTypeEcharts" class="echarts"></div>
					</div>
				</div>
				<div class="height33" id="DutyUL">
				<!-- 	<div class="top-header">重点青少年分类统计</div> -->
						<div class="common-header">
									<div>各镇贫困人口及年龄统计</div>
								</div>
					<div class="common-pading shadow">
						<div id="PopKeyEcharts" class="echarts"></div>
					</div>
				</div>
				<div class="height33">
					<!-- <div class="top-header">重点青少年分析</div> -->
						<div class="common-header">
									<div>贫困户属性分布</div>
								</div>
					<div class="common-pading shadow" style="height:87%">
						<div id="PopFlowTable"  class="echarts"></div>
					</div>
				</div>
			</div>
			<div class="span6  height100">
				<div class="height66">
						<div class="common-header">
									<div>贫困人口统计</div>
								</div>
					<div class="common-pading shadow" style="height:95%">
						<div class="row-fluid results">
							<div class="span6">
								<div class="common-pading" style="text-align: center">
									<b class="common-color" id="CameraTotal">0</b> <br>&nbsp;贫困人口总数
								</div>
							</div>
							<div class="span6">
								<div class="common-pading" style="text-align: center">
									<b class="common-color" id="OnLineRate">0</b> <br>&nbsp;本月下降贫困人数<i class=" icon-arrow-down align-top bigger-125" style="color:#f23f40"></i>
								</div>
							</div>
						</div>
						<div class="common-pading" style="position:relative;height:78%;">
                            <div class="shijian1">
                            <i class=" iconfont icon-nongminbao" style="float: left;font-size: 60px; margin-top: 20px;margin-left: 10px; margin-right: 10px;"></i>
                               <div class="shoulishijian" style="">农业人口</div>
                               <div class="shoulishijiannum" id="item-one" >0<span class="jianfont">人</span></div>
                            <div>
                           <div class="shijian2 ">
                            <i class=" iconfont icon-cunzhuang" style="float: left;font-size: 68px;  margin-top: 13px;margin-left: 10px; margin-right: 10px;"></i>
                               <div class="shijian-common">涉农村居</div>
                                 <div class="shijian-common-num" id="item-two" >0<span class="shijian-common-jian">个</span></div>
                           </div>
                            <div class="shijian3">
                             <i class=" iconfont icon-pinkuncun-1" style="float: left;font-size: 60px; margin-top: 20px;margin-left: 10px; margin-right: 10px;"></i>
                               <div class="shijian-common">贫困村</div>
                                 <div class="shijian-common-num" id="item-three">0<span class="shijian-common-jian">个</span></div>
                            </div>
                             <div class="shijian4">
                                <i class=" iconfont icon-jiandangliqia" style="float: left;    font-size: 55px; margin-top: 22px;margin-left: 10px; margin-right: 10px;"></i>
                             
                                <div class="shijian-common" style="margin-top:0px;">建档立卡</div>
                                 <div class="shijian-common-num" id="item-four">0<span class="shijian-common-jian">户</span>0<span class="shijian-common-jian">人</span></div>
                             
                             </div>
                              <div class="shijian5">
                                <i class=" iconfont icon-tubiao" style="float: left;font-size: 60px; margin-top: 20px;margin-left: 10px; margin-right: 10px;"></i>
                                  <div class="shijian-common"  style="margin-top:0px;">累计脱贫</div>
                                 <div class="shijian-common-num"id="item-five" >0<span class="shijian-common-jian">户</span>0<span class="shijian-common-jian">人</span></div>
                              </div>
                                <div  class="shijian6">
                                     <i class=" iconfont icon-pinkunhu" style="float: left;font-size:53px; margin-top: 26px;margin-left: 10px; margin-right: 10px;"></i>
                                     <div class="shijian-common"  style="margin-top:0px;">未脱贫</div>
                                 <div class="shijian-common-num" id="item-six">0<span class="shijian-common-jian">户</span> 0<span class="shijian-common-jian">人</span></div>
                               </div> 
                             <div  class="shijian7">
                                    <i class=" iconfont icon-gaishuai" style="float: left;font-size: 53px; margin-top: 25px;margin-left: 10px; margin-right: 10px;"></i>
                                     <div class="shijian-common">贫困发生率</div>
                                 <div class="shijian-common-num" id="item-seven" >0<span class="shijian-common-jian">%</span></div>
                               </div> 
                        </div>
                        </div>
                        </div>
						
					</div>
				</div>
				<div class="height33">
						<div class="common-header">
									<div>脱贫户户均收入情况统计</div>
								</div>
					<div class="common-pading shadow"  style="height:86%">
						<div id="PopFollowEcharts" class="echarts"></div>
					</div>
				</div>

			</div>
			<div class="span3  height100">
				<div class="height33">
	
						<div class="common-header">
									<div>致贫原因统计</div>
								</div>
					<div class="common-pading shadow">
						<div id="PopsNumEcharts" class="echarts"></div>
					</div>
				</div>
					<div class="height33">
	
						<div class="common-header">
									<div>贫困人员健康状况</div>
								</div>
					<div class="common-pading shadow">
						<div id="HealthyEcharts" class="echarts"></div>
					</div>
				</div>
						<div class="height33">
	
						<div class="common-header">
									<div>贫困人口劳动技能状况</div>
								</div>
					<div class="common-pading shadow" style="height:87%">
						<div id="SkillEcharts" class="echarts"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>