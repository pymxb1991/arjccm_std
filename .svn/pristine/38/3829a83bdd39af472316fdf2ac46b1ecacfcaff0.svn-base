<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>实时动态感知</title>
<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js"></script>
<link href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css" />
<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.js" type="text/javascript"></script>
<!-- 地图资源引入 -->
<link rel="stylesheet" href="${ctxStatic}/modules/map/css/map.css" type="text/css">
<link rel="stylesheet" href="${ctxStatic}/modules/map/css/house.css" type="text/css">
<link rel="stylesheet" href="${ctxStatic}/ol/ol.css" type="text/css">
<script>
	var ctx = "${ctx}",ctxStatic="${ctxStatic}";
</script>
<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css"rel="stylesheet" />
<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.js" type="text/javascript"></script>
<script src="${ctxStatic}/ol/ol.js"></script>
<script src="${ctxStatic}/modules/map/js/mapconfig.js"></script>
<script src="${ctxStatic}/modules/map/js/commonMap.js"></script>
<script src="${ctxStatic}/common/ztreeSech.js"></script>
<script src="${ctxStatic}/ccm/sys/js/mapLeftTree.js"></script>
<script src="${ctxStatic}/modules/map/js/mapBuildSpe.js"></script>
<script src="${ctxStatic}/ccm/sys/js/mapIndexIot.js"></script>
<!-- 地图资源引入 -->
<style type="text/css">
.nav-tabs>li{
	float: left;
}
#right{
padding:0px!important;
position: relative;
}
#assetTree{
 width:100%!important
}
#ztreeLeft li ul.line {
    background: inherit;
}
#treeLeft_1_ul{
 background: inherit;
 padding-left: 8px
}
#treeLeft_1_switch{
 background: inherit;
}
#treeLeft_1_ul li .switch {
   display: none;
}
#treeLeft_1 >span ,#treeLeft_1_a{
 display: none;
}
#treeLeft_1_ul li{
  display: inline;
}
.hideOrShowDivR-header{
 width: 100%;
 padding-left:10px;
 line-height:40px;
 background: #fff;
  border-bottom:1px solid #ccc;
}
.waring{
width: 100%;
height:100px;
 z-index: 99;
 margin-bottom: 10px;
}
.waring-left{
  width:10%;
  background-color:#1351a7;
  float: left;
  line-height: 100px;
  height:100px;
  text-align: center;
}
.waring-right{
    width: 84%;
    float: left;
    margin-left: 5px;
}
.waring-right>div{
 line-height: 30px;
 padding-left: 5px;
 white-space: nowrap;
 background-color: #1351a7;
}
.waring-right>div.waring-name{
 line-height: 33px;
 margin-bottom: 6px;
}
.waring-time{
 float: right;
}
#PeopleWarning .waring-details{
 color:#ff0000;
}
#PlaceWarning .waring-details{
 color:#ff8000;
}
#CarWarning .waring-details{
 color:#c1c420;
}
#VideoleWarning .waring-details{
 color:#c85190;
}
#EventWarning .waring-details{
 color:#d34413;
}
.waring-address{
  color:#4595c6;
}
.waring-right .icon-map-marker{
    font-size: 17px;
    margin-right: 3px;
}
.waring-right a{
text-decoration: none
}
.tab-content table td{
line-height: 24px;
}
#TableConB .tab-content,#TableConB1 .tab-content{
  padding:5px;
  background: #1351a7;
  width: 93.8%;
}
#LeftConL .tab-content{
  padding:5px;
  width: 92.8%;
}
.tab-content-bg{
  background: #1351a7;
}
#TableConB .nav-tabs,#TableConB1 .nav-tabs,#LeftConL .nav-tabs{
 border:none
}
.tab-content-color>tr>td{
	color: #fff;
}
 .tab-content{
  padding:5px;
}
#TableConB .nav-tabs>li>a,#TableConB1 .nav-tabs>li>a,#LeftConL .nav-tabs>li>a{
	color: #fff;
	background-color: #1351a7;
	border-radius: 4px;
	padding: 5px 9px;
	margin-right: 3px;
	margin-left: 0px;
}

#TableConB .nav-tabs>.active>a, .nav-tabs>.active>a:hover, .nav-tabs>.active>a:focus,#TableConB1 .nav-tabs>.active>a, .nav-tabs>.active>a:hover, .nav-tabs>.active>a:focus,#LeftConL .nav-tabs>.active>a, .nav-tabs>.active>a:hover, .nav-tabs>.active>a:focus{
     background-color: #09aaeb;
}
#TableConB1 .tab-content .tab-pane,#TableConB .tab-content .tab-pane{
 height:180px;
 position: relative;
 overflow: hidden;
}
.hideDiv{
    position: absolute;
    right: 37px;
    transition: bottom 1s ease 0s;
    border: 1px solid #0ea7de;
    background: #1351a7;
    width: 20px;
    height: 13px
}
.hideDiv span{
    display: inline-block;
    width: 0;
    height: 0;
    vertical-align: top;
    border-top: 4px solid #000;
    border-right: 4px solid transparent;
    border-left: 4px solid transparent;
    content: "";
     border-top-color: #fff;
    border-bottom-color: #fff;
    display: inline-block;
    width: 0;
    height: 0;
    vertical-align: top;
    border-top: 5px solid #000;
    border-right: 5px solid transparent;
    border-left: 5px solid transparent;
    content: "";
    border-top-color: #fff;
    border-bottom-color: #fff;
    margin-left: 5px;
    margin-top: 5px;
  }
  .checkbox label::after{
    width: 11px!important;
    height: 11px!important;
    top: -3px;
    padding-left: 1px;
    font-size: 10px;
}
.checkbox label::before{
    width: 11px!important;
    height: 11px!important;
}
.checkbox-success input[type="checkbox"]:checked + label::after, .checkbox-success input[type="radio"]:checked + label::after {
    color: #21a121!important;
}
.checkbox-success input[type="checkbox"]:checked + label::before, .checkbox-success input[type="radio"]:checked + label::before {
    background-color: #fff!important;
    border-color: #fff!important;
}

.accordion-heading{
	width: 96%;
}

.type_bayonet{
		padding: 0px;
		margin-right: 30px;
}
	.timeButton{
		height: 24px;
		float: left;
		display: block;
		background-color: #1351a7;
		color: #f2faff;
		border-radius: 5px;
		margin-right: 8px;
		border: 0px;
	}

</style>
</head>
<body  style="overflow: hidden;">
<div id="FullBody"></div>
	  <div id="content" class="row-fluid">
		  <div id="right">
		     <div id="map" class="map"></div>
		     <!-- 弹框 -->
			  <div id="popup" class="ol-popup">
				  <div class="">
					  <a href="#" id="popup-closer" class="ol-popup-closer  icon-remove "
						 title="关闭" onmouseover="$(this).addClass('jbox-close-hover');"
						 onmouseout="$(this).removeClass('jbox-close-hover');"
						 style="position: absolute; display: block; cursor: pointer; top: 10px; right: 11px; width: 15px; height: 15px;color:#666"></a>
					  <div class="jbox-title-panel" style="height: 25px;">
						  <div class="jbox-title">详细信息</div>
					  </div>
					  <div class="jbox-state">
						  <div id="popup-content" style="padding: 8px 15px;"></div>
					  </div>
				  </div>
			  </div>
			<!-- 弹框 -->
			<!-- 浮层 -->
			<div id="hideOrShowDivLFloat" style="color:#fff;position: absolute; top: 20px; left: 3px;border: 1px solid #5fcceb; box-shadow: inset 0px 0px 5px 1px #5fcceb;padding:5px 5px;background: #1351a7;cursor: pointer;"><i class="icon iconfont icon-tuceng1" style="font-size: 24px;"></i></div>
			<div id="hideOrShowDivL" style="position: absolute; top: 10px;display:none; left: 3px; height:570px; transition: width 1s ease 0s; width: 320px;background:url(${ctxStatic}/images/2-iot.png) no-repeat center; background-size: 100% 100%;">
				<!-- 收起按钮 -->
				<div style="  bottom: 560px; right: 29px;transition: bottom 1s;cursor: pointer;"  class="hideDiv"  id="hideDivL"  onclick="ShowHideDivL(this)" >
<!-- 					<input onclick="ShowHideDivL(this)" style="background-color: #19a7f0 !important;border: 0px;" class="btn btn-primary" type="button" value="收起">
 -->				<span ></span>
				</div>
				<!-- 内容列表 -->
			 	<div id="LeftConL" style="width: 100%;height:100%;overflow: hidden;" class="accordion-group">
			 	<div style="color:#fff;padding: 16px 16px 3px;"></div>
				 	<div class="accordion-heading">
					<ul class="nav nav-tabs">
						<li id="devPage" class="active"><a href="#devTree" data-toggle="tab">人员聚集</a></li>
						<li id="distributedPage"><a href="#distributedTree" data-toggle="tab">人员分布</a></li>
						<li id="TrackPage"><a href="#TrackTree" data-toggle="tab">人员轨迹</a></li>
						<li id="analysisPage"><a href="#analysisTree" data-toggle="tab">轨迹分析</a></li>
					</ul>
				   </div>

				   <div id="myTabContent" class="tab-content">
					<div class="tab-pane fade in active tab-content-bg" id="devTree">
					    <div  id="treeLeft" class="ztree" style="overflow: auto;width:100%;"> </div>
						<div class="input-append" style="margin-top: 10px">
							<input id="secuDev" name="secuPlace" class="input-medium"
								type="text" value="" maxlength="100"
								style="width: 240px; height: 28px; padding: 14px 5px; margin-left: 2px;">
							<a id="searchButton" href="javascript:" class="btn"	style="border-radius: 0 14px 14px 0;">&nbsp;
								<i class="icon-search"></i>&nbsp;
							</a>&nbsp;&nbsp;
						</div>
						<div id="assetTree" class="ztree"  style="overflow: auto;width:100%;"></div>
					 </div>
					<div class="tab-pane fade" id="distributedTree">
						<div>
							<div class="checkbox checkbox-success radio-div tab-content-bg" style="color:#fff;margin-bottom: 5px;padding-top: 5px;padding-bottom: 5px;">

								<span style="font-size: 14px; text-align: center; display: block">数据类型</span>
								<br />
<%--								<input type="checkbox" value="16" class="type-input" id="warninput" onchange="selectAll('warninput', 'warnCheckbox')" />
								<label	for="warninput" style="padding:0" >异常预警</label>--%>

								<%--<label>数据类型</label>--%>
								<input type="checkbox" class="type_bayonet" id="carBayonet">
								<label for="carBayonet" style="padding: 0px; margin-right: 40px;margin-bottom: 15px;">车辆卡口</label>
								<input type="checkbox" class="type-bayonet" id="faceBayonet">
								<label for="faceBayonet" style="padding: 0; margin-right: 40px;margin-bottom: 15px;">人脸卡口</label>
								<input type="checkbox" class="type-bayonet" id="rfidBayonet">
								<label for="rfidBayonet" style="padding: 0; margin-right: 40px;margin-bottom: 15px;">RFID</label>
								<input type="checkbox" class="type-bayonet" id="eBayonet">
								<label for="eBayonet" style="padding: 0; margin-right: 40px;margin-bottom: 5px;">电子围栏</label>
								<input type="checkbox" class="type-bayonet" id="wifiBayonet">
								<label for="wifiBayonet" style="padding: 0; margin-right: 40px;margin-bottom: 5px;">WiFi-探针</label>

							</div>
							<div class="checkbox checkbox-success radio-div tab-content-bg" style="color:#fff;margin-bottom: 5px;padding-top: 5px;padding-bottom: 5px;">

								<span style="font-size: 14px; text-align: center; display: block">人员类型</span>
								<br />
								<input type="checkbox" class="type_bayonet" id="sjpop">
								<label for="sjpop" style="padding: 0px; margin-right: 40px;margin-bottom: 15px;">涉教人员</label>
								<input type="checkbox" class="type-bayonet" id="zdpop">
								<label for="zdpop" style="padding: 0; margin-right: 40px;margin-bottom: 15px;float: left;">重点人员</label>
								<input type="checkbox" class="type-bayonet" id="ldpop">
								<label for="ldpop" style="padding: 0; margin-right: 40px;margin-bottom: 15px;float: left;">流动人员</label>
								<input type="checkbox" class="type-bayonet" id="qtpop">
								<label for="qtpop" style="padding: 0; margin-right: 40px;margin-bottom: 5px;">其他人员</label>

							</div>

							<div class="checkbox checkbox-success radio-div tab-content-bg" style="color:#fff;margin-bottom: 5px;padding-top: 5px;padding-bottom: 5px;">

								<span style="font-size: 14px; text-align: center; display: block">时间</span>
								<br />
								<input style="float: left; width: 45%;margin-left: -10px" type="date"><span style="display: block; float: left">&nbsp;至&nbsp; </span><input style="float: left;width: 45%;" type="date"><i title="日期" class="icon iconfont"></i>
								<br />
								<input style="width: 30%;margin-left: -15px;" class="timeButton" type="button" value="最近1小时">
								<input style="width: 30%" class="timeButton" type="button" value="最近3小时">
								<input style="width: 30%" class="timeButton" type="button" value="最近1天">


							</div>


<%--                            <div  class="checkbox checkbox-success radio-div tab-content-bg" style="color:#fff;padding-top: 5px;padding-bottom: 5px;">
                              <input type="checkbox" value="16" class="type-input" name="warnCheckbox"  id="popinput"  onchange="oneToAll('warninput','warnCheckbox')" />
							  <label	for="popinput" style="padding:0;width:80px;"  ><i title="人员" class="icon iconfont icon-xingren"></i>人员</label>
							  <input type="checkbox" value="16" class="type-input" name="warnCheckbox"  id="placeinput" onchange="oneToAll('warninput','warnCheckbox')"/>
							  <label	for="placeinput" style="padding:0;width:80px;"  ><i title="场所"  class="icon iconfont icon-loudong"></i>场所</label>
							  <input type="checkbox" value="16" class="type-input" name="warnCheckbox"  id="carinput" onchange="oneToAll('warninput','warnCheckbox')"/>
							  <label	for="carinput" style="padding:0;width:80px;"  ><i title="车辆"  class="icon iconfont icon-cheliangguanli"></i>车辆</label>
							  <input type="checkbox" value="16" class="type-input" name="warnCheckbox"  id="videoinput"  onchange="oneToAll('warninput','warnCheckbox')"/>
							  <label	for="videoinput" style="padding:0;width:80px;"  ><i title="视频"  class="icon iconfont icon-shipinjiankong1"></i>视频</label>
							  <input type="checkbox" value="16" class="type-input" name="warnCheckbox"  id="eventinput"  onchange="oneToAll('warninput','warnCheckbox')" />
							  <label	for="eventinput" style="padding:0;width:80px;"><i title="事件"  class="icon iconfont icon-anquanshijiangaojingguize"></i>事件</label>
                            </div>--%>
						</div>
					</div>
			 	</div>
			</div>
			</div>
			<div id="hideOrShowDivBFloat" style="color:#fff;position: absolute; bottom: 10px; left: 3px;border: 1px solid #5fcceb; box-shadow: inset 0px 0px 5px 1px #5fcceb;padding:5px 10px;background: #1351a7;cursor: pointer;">数据接入</div>
			<div id="hideOrShowDivB" style="position: absolute;bottom: 43px; left: 3px; height: 270px; transition: display 1s ease 0s; width: 400px;background:url(${ctxStatic}/images/4-iot.png) no-repeat center; background-size: 100% 100%;">
				<!-- 收起按钮 -->
				<div style=" bottom:261px;cursor: pointer;"  class="hideDiv" id="hideDivB"  onclick="ShowHideDivB(this)" >
<!-- 					<input onclick="ShowHideDivB(this)" style="background-color: #19a7f0 !important;border: 0px;" class="btn btn-primary" type="button" value="展开">
 -->				<span></span>
				</div>
				<!-- grid列表 -->
			 	<div id="TableConB" style="width: 100%;overflow: hidden;">
			 	    <div style="color:#fff;padding: 16px 16px 3px;">数据接入</div>
			 	    <div class="accordion-heading">
						<ul class="nav nav-tabs">
							<li id="" class="active"><a href="#SurveyTab" data-toggle="tab">一标三实</a></li>
							<li id="" ><a href="#PeopleTab" data-toggle="tab">人口布控</a></li>
							<li id="" ><a href="#CarTab" data-toggle="tab">车辆布控</a></li>
							<li id="" ><a href="#fenceControl" data-toggle="tab">电子围栏布控</a></li>
						</ul>
				   </div>
				   <div  class="tab-content tab-content-bg">
					<div class="tab-pane fade in active" id="SurveyTab">
						<div>
						  <table style="color:#fff;">
						    <tr>
						      <td>标准地址：</td><td><span id="standardAddress"></span></td>
						    </tr>
						     <tr>
						      <td>实有人口：</td><td><span id="realPopulation"></span></td>
						    </tr>
						     <tr>
						      <td>实有房屋：</td><td><span id="realHouse"></span></td>
						    </tr>
						      <tr>
						      <td>实有单位：</td><td><span id="realCompany"></span></td>
						    </tr>
						  </table>
						</div>
<%--						<div style="position: absolute;bottom:5px;right:5px;"><a>统计分析</a></div>--%>
					 </div>
					<div class="tab-pane fade" id="PeopleTab">
						<div>
							<table style="color:#fff;">
								<tr>
									<td>实有人口：</td><td><span id="realPopulation2"></span></td>
								</tr>
								<tr>
									<td>动态库：</td><td><span id="dynamicLibrary"></span></td>
								</tr>
								<tr>
									<td>静态库：</td><td><span id="staticLibrary"></span></td>
								</tr>
								<tr>
									<td>人脸抓拍机：</td><td><span id="faceGrabber"></span></td>
								</tr>
							</table>
						</div>
					</div>
				   <div class="tab-pane fade" id="CarTab">
					   <div>
						   <table style="color:#fff;">
							   <tr>
								   <td>卡口：</td><td><span id="bayonet"></span></td>
							   </tr>
							   <tr>
								   <td>布控车辆：</td><td><span id="carControl"></span></td>
							   </tr>
						   </table>
					   </div>
				   </div>
					<div class="tab-pane fade" id="fenceControl">
						<div>
							<table style="color:#fff;">
								<tr>
									<td>RFID布控：</td><td><span id="rfidControl"></span></td>
								</tr>
								<tr>
									<td>WIFI布控：</td><td><span id="wifiContol"></span></td>
								</tr>
								<tr>
									<td>手机电子围栏：</td><td><span id="phoneControl"></span></td>
								</tr>
								<tr>
									<td>GPS定位：</td><td><span id="gpsControl"></span></td>
								</tr>
							</table>
						</div>
					</div>
			 	</div>
			    </div>
			</div>
			<div id="hideOrShowDivB1Float" style="color:#fff;position: absolute; bottom: 10px; left:85px;border: 1px solid #5fcceb; box-shadow: inset 0px 0px 5px 1px #5fcceb;padding:5px 10px;background: #1351a7;cursor: pointer;">动态感知</div>
			<div id="hideOrShowDivB1" style="position: absolute; bottom: 43px; left: 410px; height: 270px; transition: height 1s ease 0s; width: 400px;background:url(${ctxStatic}/images/4-iot.png) no-repeat center; background-size: 100% 100%;">
				<!-- 收起按钮 -->
				<div style=" bottom:261px;transition: bottom 1s;cursor: pointer;"  class="hideDiv" id="hideDivB1"  onclick="ShowHideDivB1(this)" >
<!-- 					<input onclick="ShowHideDivB1(this)" style="background-color: #19a7f0 !important;border: 0px;" class="btn btn-primary" type="button" value="展开">
 -->				<span></span>
 </div>
				<!-- grid列表 -->
			 	<div id="TableConB1" style="width: 100%;overflow: hidden;">
			 	 <div style="color:#fff;padding: 16px 16px 3px;">动态感知</div>
			 	   <div class="accordion-heading">
						<ul class="nav nav-tabs">
							<li id="" class="active"><a href="#TodayTab" data-toggle="tab">今天</a></li>
							<li id="" ><a href="#WeekTab" data-toggle="tab">近7天</a></li>
						</ul>
				   </div>
				    <div  class="tab-content tab-content-bg">
						<div class="tab-pane fade in active" id="TodayTab">
							<div>
							  <table style="color:#fff;">
							    <tr>
									<td>人脸预警：</td><td><span id="faceAlarm"></span></td>
							    </tr>
							     <tr>
							      <td>车辆预警：</td><td><span id="carAlarm"></span></td>
							    </tr>
								  <tr>
							      <td>rfid预警：</td><td><span id="rfidAlarm"></span></td>
							    </tr>
							      <tr>
							      <td>wifi预警：</td><td><span id="wifiAlarm"></span></td>
							    </tr>
							     <tr>
							      <td>手机电子围栏预警：</td><td><span id="phoneAlarm"></span></td>
							    </tr>
								  <tr>
									  <td>GPS定位预警：</td><td><span id="gpsAlarm"></span></td>
								  </tr>
							  </table>
						   </div>
<%--						    <div style="position: absolute;bottom:5px;right:5px;"><a>统计分析</a></div>--%>
						 </div>
						<div class="tab-pane fade" id="WeekTab">
							<div>
								<table style="color:#fff;">
									<tr>
										<td>人脸预警：</td><td><span id="faceAlarm2"></span></td>
									</tr>
									<tr>
										<td>车辆预警：</td><td><span id="carAlarm2"></span></td>
									</tr>
									<tr>
										<td>rfid预警：</td><td><span id="rfidAlarm2"></span></td>
									</tr>
									<tr>
										<td>wifi预警：</td><td><span id="wifiAlarm2"></span></td>
									</tr>
									<tr>
										<td>手机电子围栏预警：</td><td><span id="phoneAlarm2"></span></td>
									</tr>
									<tr>
										<td>GPS定位预警：</td><td><span id="gpsAlarm2"></span></td>
									</tr>
								</table>
							</div>
						</div>
			 	  </div>
			 	</div>
			</div>
			<div id="hideOrShowDivRFloat" style="color:#fff;position: absolute; bottom: 10px; right: 200px;border: 1px solid #5fcceb; box-shadow: inset 0px 0px 5px 1px #5fcceb;padding:5px 20px 5px 10px;background: #1351a7;cursor: pointer;">异常预警<i class="icon-envelope" style=" font-size: 23px;margin-left: 7px;color: #fd0442;"></i><span id="alarmCount" class="badge badge-warning" style="    padding-right: 5px;  padding-left: 5px;  -webkit-border-radius: 9px; -moz-border-radius: 9px; border-radius: 10px;position: absolute;right: 6px;top: 5px;">0</span></div>
			<div id="hideOrShowDivR" style="position: absolute; bottom:49px; right: 3px; height: 590px; transition: width 1s ease 0s; width: 300px;background:url(${ctxStatic}/images/2-iot.png) no-repeat center; background-size: 100% 100%;">
				<!-- 收起按钮 -->
				<div style="     bottom: 580px; right: 29px;transition: bottom 1s;cursor: pointer;"  class="hideDiv" id="hideDivR"  onclick="ShowHideDivR(this)">
<!-- 					<input onclick="ShowHideDivR(this)" style="background-color: #19a7f0 !important;border: 0px;" class="btn btn-primary" type="button" value="展开">
 -->				<span></span>
 </div>
				<!-- grid列表 -->
			 	<div id="TableConR" style="width: 100%;overflow:hidden;">
			 	    <div style="color:#fff;padding: 16px 16px 3px;">异常预警</div>
                    <input type="hidden" id="userid" name="userid" value="${fns:getUser()}">
			 	   <div id="abnormalWarning" style="width:300px;height:555px;color:#fff;overflow:auto;" class="hideOrShowDivR-content">
<%--			 	      <div  id="PeopleWarning"  class="waring">--%>
<%--			 	         <div class="waring-left"><i title="人员" style="font-size:24px;margin-top: 40px;display: inline-block;" class="icon iconfont icon-xingren"></i></div>--%>
<%--			 	         <div class="waring-right">--%>
<%--			 	            <!-- <div class="waring-name">张三  <div class="waring-time">2018-10-24 12:34:56</div></div>--%>
<%--			 	            <div class="waring-details">在逃人员出现</div>--%>
<%--			 	            <div class="waring-address"><i class="icon-map-marker"></i>清平街平安路中医院门口</div> -->--%>
<%--			 	         </div>--%>
<%--			 	      </div>--%>
<%--			 	      <div  id="PlaceWarning"   class="waring">--%>
<%--			 	         <div class="waring-left"><i title="场所"  style="font-size:24px;margin-top: 40px;    display: inline-block;"  class="icon iconfont icon-loudong"></i></div>--%>
<%--			 	         <div class="waring-right">--%>
<%--			 	             <!-- <div class="waring-name">张三 、张四  <div class="waring-time">2018-10-24 12:34:56</div></div>--%>
<%--			 	            <div class="waring-details">涉毒人员聚集</div>--%>
<%--			 	            <div class="waring-address"><i class="icon-map-marker"></i>清平街XXX酒店</div> -->--%>
<%--			 	         </div>--%>
<%--			 	      </div>--%>
<%--			 	      <div  id="CarWarning"   class="waring">--%>
<%--			 	         <div class="waring-left"><i  title="车辆"  style="font-size:24px;margin-top: 40px;    display: inline-block;"  class="icon iconfont icon-cheliangguanli"></i></div>--%>
<%--			 	         <div class="waring-right">--%>
<%--                            <!--  <div class="waring-name">京A234322  <div class="waring-time">2018-10-24 12:34:56</div></div>--%>
<%--			 	            <div class="waring-details">可疑车辆布控</div>--%>
<%--			 	            <div class="waring-address"><i class="icon-map-marker"></i>清平街XXX路口</div> -->--%>
<%--			 	         </div>--%>
<%--			 	      </div>--%>
<%--			 	      <div  id="VideoleWarning" class="waring">--%>
<%--			 	          <div class="waring-left"><i  title="视频"  style="font-size:24px;margin-top: 40px;    display: inline-block;"   class="icon iconfont icon-shipinjiankong1"></i></div>--%>
<%--			 	          <div class="waring-right">--%>
<%--			 	           <!--  <div class="waring-name">XXX摄像头  <div class="waring-time">2018-10-24 12:34:56</div></div>--%>
<%--			 	            <div class="waring-details">视频移动告警</div>--%>
<%--			 	            <div class="waring-address"><i class="icon-map-marker"></i>清平街XXX路口</div> -->--%>
<%--			 	          </div>--%>
<%--			 	      </div>--%>
<%--			 	      <div  id="EventWarning"   class="waring">--%>
<%--			 	          <div class="waring-left"><i title="事件"  style="font-size:24px;margin-top: 40px;    display: inline-block;"   class="icon iconfont icon-anquanshijiangaojingguize"></i></div>--%>
<%--			 	          <div class="waring-right">--%>
<%--			 	            <!-- <div class="waring-name">民警：王五  <div class="waring-time">2018-10-24 12:34:56</div></div>--%>
<%--			 	            <div class="waring-details">发现可疑人员</div>--%>
<%--			 	            <div class="waring-address"><i class="icon-map-marker"></i>清平街XXX路口</div> -->--%>
<%--			 	          </div>--%>
<%--			 	      </div>--%>
			 	   </div>
			 	</div>
			</div>
			<!-- 浮层 -->
			<!-- 楼栋住户信息 -->
		<button type="button" data-toggle="modal" data-target="#myModal"
			id="buildBtn" style="display: none"></button>
		<div id="myModal" class="modal hide fade jbox" tabindex="-1"	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
			style="width: 1300px; margin-left: -33.85%;">
			<div class="jbox-container">
				<a href="#" id="popup-closer2" class="ol-popup-closer jbox-close"
					title="关闭" onmouseover="$(this).addClass('jbox-close-hover');"
					onmouseout="$(this).removeClass('jbox-close-hover');"
					style="position: absolute; display: block; cursor: pointer; top: 4px; right: 11px; width: 15px; height: 15px;"
					data-dismiss="modal" aria-hidden="true"></a>
				<div class="jbox-title-panel" style="height: 25px;">
					<div class="jbox-title">信息</div>
				</div>
				<!-- 楼栋 -->
				<div class="jbox-state">
					<div id="popup-content2">
						<div class="modal-body" id="build-details"
							style="padding: 3px 0px 0 0;"></div>
						<!-- 房屋 -->
						<div class="modal-body" id="house-details"
							style="padding: 3px 15px;"></div>
						<!--人口-->
						<div class="modal-body" id="pop-details"></div>
					</div>
					<div class="jbox-button-panel"
						style="height: 4px; padding: 0 15px 20px; text-align: right;"
						id="modal-footer">
						<button class="jbox-button" style="padding: 0px 10px 0px 10px;"
							data-dismiss="modal" aria-hidden="true">关闭</button>
					</div>
				</div>
			</div>

		</div>
		<!-- 楼栋住户信息 -->
		  </div>
	</div>

	<script type="text/javascript">
	   // 人员定位
		var leftWidth = 0; // 左侧窗口大小
		var htmlObj = $("html"), mainObj = $("#main");
		var frameObj = $("#left, #openClose, #right, #right iframe");
		function wSize() {
			var strs = getWindowSize().toString().split(",");
			htmlObj.css({
				"overflow-x" : "auto",
				"overflow-y" : "auto"
			});
			mainObj.css("width", "auto");
			frameObj.height(strs[0] - 5);
			var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
			$("#right").width($("#content").width() - leftWidth - $("#openClose").width()- 5);
			$(".ztree").width(leftWidth - 10).height(frameObj.height() - 160);
			wSizeWidth();
			$("#treeLeft").width('auto').height('auto');
		}
		function wSizeWidth() {
			if (!$("#openClose").is(":hidden")) {
				var leftWidth = ($("#left").width() < 0 ? 0 : $("#left")
						.width());
				$("#right").width(
						$("#content").width() - leftWidth
								- $("#openClose").width() - 4);
			} else {
				$("#right").width("100%");
			}
		}// <c:if test="${tabmode eq '1'}">
		function openCloseClickCallBack(b) {
			$.fn.jerichoTab.resize();
		} // </c:if>

		$(function() {
			$("#treeLeft").width('auto').height('auto');
		})

	</script>
     <script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>