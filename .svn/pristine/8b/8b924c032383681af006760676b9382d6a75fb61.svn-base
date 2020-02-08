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
<script src="${ctxStatic}/asidenav/asidenav.js"></script>
<script src="${ctxStatic}/asidenav/jquery.min.js"></script>
<link rel="stylesheet"
	href="${ctxStatic}/common/index/css/indexCommon.css">
<link rel="stylesheet" href="${ctxStatic}/common/index/css/index.css">
<style>

</style>
<script type="text/javascript">
	var ctxStatic = '${ctxStatic}';
</script>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script src="${ctxStatic}/d3/d3.v4.min.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/shine.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/scroll.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/indexCommon.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/index.js"></script>

<script src="${ctxStatic}/layer-v3.1.1/layer/layer.js"></script>
<script type="text/javascript">
function LayerDialog1(id,src, title, height, width){
	layer.open({
	  type: 2,
	  title: title,
	  id:id||'',
	  area: [height, width],
	  fixed: true, //固定
	  maxmin: true,
	  btn: ['关闭'], //可以无限个按钮
	  content: src
	});
}
function cli(id){
	parent.LayerDialog1('','../../event/ccmEventIncident/detail?id='+id, '案事件详情', '1100px', '700px')
}
</script>
</head>
<body>
	<div class="container-fluid" style="height: 100%; overflow: hidden"
		id="main">
		<div class="context" content="${ctx}"></div>
		<%-- <div class="row-fluid header">
			<div class="span3"  style="position: relative; margin-top:5px;">
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
							 <a href="${ctx}/sys/map/statPop" title="人口" class="menu-item menu-third">人口</a> 
						     <a href="${ctx}/sys/map/statIndexCom" title="概况" class="menu-item  menu-fourth">概况</a>
						</div>
					</div> 
                    <!-- 菜单 -->
				<div class="index-nav-common">
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
						<li class="lastli"><a
							href="${ctx}/sys/map/statPop"> <i
								class="icon-globe align-top bigger-125"></i> 人口
						</a></li>
						<li class="lastli"><a
							href="${ctx}/sys/map/statSituation"> <i
								class="icon-globe align-top bigger-125"></i> 概况
						</a></li>
					</ul>
				</div>
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
					<!---~<li><a href="${ctx}/index">地图首页</a></li>  临时删除--->
					      <li><a href="${ctx}/sys/map/partyConstruction">党建架构</a></li>
					      <li><a href="${ctx}/sys/map/statIndexCom">基本组成</a></li> 
					   </ul>
					</div>
					<div class="header-nav1" >
					<ul>
					  <li ><a href="${ctx}/sys/map/statPop">关注对象</a></li>
					      <li><a href="${ctx}/sys/map/shakeOffPoverty">脱贫攻坚</a></li>
					      <li class="active" ><a href="${ctx}/sys/map/statIncidentStatistics">治安态势</a></li>
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
					<!-- <div class="top-header">案事件性质统计情况</div> -->
						<div class="common-header">
									<div>案事件性质统计</div>
								</div>
					<div class="common-pading shadow">
						<div id="AlarmNatureEcharts" class="echarts" ></div>
					</div>
				</div>
				<div class="height33">
					<div class="common-header">
									<div>案事件级别统计</div>
								</div>
				<!-- 	<div class="top-header">案事件级别统计</div> -->
					<div class="common-pading shadow alarm-height">

							<!-- <div class="span4">
								<ul class="echartsAlarm-value">
									<li><span class="alarm-name1 alarm-name">特别重大</span> <span
										class="alarm-value" id="level1">50</span></li>
									<li><span class="alarm-name2 alarm-name">重大</span> <span
										class="alarm-value" id="level2">50</span></li>
									<li><span class="alarm-name3 alarm-name">较大</span> <span
										class="alarm-value" id="level3">100</span></li>
									<li><span class="alarm-name4 alarm-name">一般</span> <span
										class="alarm-value" id="level4">100</span></li>
								</ul>
							</div> -->
					<!-- 		<div class="span12"> -->
								<div id="echartsAlarm"  class="echarts"></div>
						<!-- 	</div> -->
					</div>
				</div>
				
				<div  class="height33">
						<!-- <div class="top-header">案事件级别统计表</div> -->
						<div class="common-header">
									<div>案事件级别统计表</div>
								</div>
							<div class="row-fluid common-pading  alarm-row-fluid2" style="display:none">
							<div class="span12">
								<ul class="echartsAlarm-tab clearfix shadow">
									<li class="active">全部</li>
									<li>特重大</li>
									<li>涉及师生</li>
									<li>涉及路线</li>
								</ul>
							</div>
						</div>
							<div class="common-pading shadow alarm-row-fluid3" style="height:83.9%;">
							<div id="alarmTable" style="overflow: auto;">
								<table>
									<thead>
										<tr class="l-grid-row-alt">
											<td>名称</td>
											<td>重特大</td>
											<td>重大</td>
											<td>较大</td>
											<td>一般</td>
										</tr>
									</thead>
									<tbody id="alarmTbody">
									</tbody>
								</table>
							</div>
						</div>
				</div>
			</div>
			<div class="span6  height100">
				<div class="height33">
					<!-- <div class="top-header">案事件统计</div> -->
						<div class="common-header">
									<div>案事件统计</div>
								</div>
					<div class="common-pading shadow">
						<div class="row-fluid results">
							<div class="span4">
								<b class="common-color" id="CameraTotal"></b> <br>&nbsp;<span style='font-size:16px'>总数</span>
							</div>
							<div class="span4">
								<b class="common-color" id="OnLineRate"></b> <br>&nbsp;<span style='font-size:16px'>已处理</span>
							</div>
							<div class="span4">
								<b class="common-color" id="OkRate"></b> <br>&nbsp;<span style='font-size:16px'>待处理</span>
							</div>
						</div>
						
						<div style=" height: 60%;">
						<div id="ChuEcharts" class="echarts" ></div>
						</div>
					</div>
				</div>
				<div class="height33">
					<!-- <div class="top-header">本月案事件处理率TOP10</div> -->
					<!-- <div class="top-header">案事件报警情况</div> -->
					<div class="common-header">
							<div>案事件报警情况</div>
					</div>
					<div class="common-pading shadow">
						<!-- <div id="AlarmInfoEcharts" class="echarts"></div> -->
						<div id="AlarmInfoWeekEcharts" class="echarts"></div>
					</div>
				</div>
				<div class="height33">
					<!-- <div class="top-header">案事件流程</div> -->
					<div class="common-header">
							<div>案事件流程</div>
					</div>
					<div class="common-pading shadow" style="height:84%">
							<div id="AlarmFlow" class="echarts" style="height: 99%"></div>
					</div>
				</div>
			</div>
			<div class="span3 height100">
				<div class="height66">
					<!-- <div class="top-header">最新案事件报警详情</div> -->
					<div class="common-header">
							<div>最新案事件报警详情</div>
					</div>
					<div class="common-pading shadow" style="height:94.9%;">
						<div class="list_lh" style="overflow: hidden;">
							<ul id="AlarmUL">
								<li class="alarm-urgent"><b>【紧急】</b>【XXX街道】发现吸毒人员</li>
								<li class="alarm-important"><b>【一般】</b>【XXX厂区】发现打架斗殴情况。</li>
								<li class="alarm-remindful"><b>【紧急】</b>【XXX小区】车辆丢失。</li>
								<li class="alarm-important"><b>【一般】</b>【XXX厂区】疑似矛盾纠纷事件</li>
								<li class="alarm-urgent"><b>【紧急】</b>【XX车间】聚众吸毒人。携带大量毒品</li>
								<li class="alarm-important"><b>【一般】</b>【XXX办公楼一层】员工发生矛盾纠纷</li>
								<li class="alarm-remindful"><b>【提醒】</b>【XXX街道】精神病人发生肇事肇祸肇事肇祸</li>
								<li class="alarm-important"><b>【一般】</b>【XXXX街道】发生社会治安治安问题</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="height33">
					<!-- <div class="top-header">案事件类型统计情况</div> -->
					<div class="common-header">
							<div>案事件类型统计情况</div>
					</div>
					<div class="common-pading shadow" style="height:83.1%">
						<div id="echarts3-1" class="echarts" ></div>
					</div>
				</div>
				<%--<div class="height33">--%>
				<%--<div class="top-header">矛盾纠纷排查调处</div>--%>
				<%--<div class="common-pading contradiction">--%>
				<%--<div class="breakdown-gc">--%>
				<%--<h6>总数</h6>--%>
				<%--<p id="">180</p>--%>
				<%--</div>--%>
				<%--<div class="breakdown-gd">--%>
				<%--<h6>已处理</h6>--%>
				<%--<p id="">100</p>--%>
				<%--</div>--%>
				<%--<div class="breakdown-wcs">--%>
				<%--<h6>待处理</h6>--%>
				<%--<p id="">80</p>--%>
				<%--</div>--%>
				<%--<div class="breakdown-wcl">--%>
				<%--<h6>完成率</h6>--%>
				<%--<p id="">55%</p>--%>
				<%--</div>--%>
				<%--</div>--%>
				<%--</div>--%>
			</div>
		</div>
	</div>
</body>
</html>