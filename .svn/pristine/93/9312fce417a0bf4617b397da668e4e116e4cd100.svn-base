<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="renderer" content="webkit|ie-comp|ie-stand" />
	<title>宗教全时空防控系统</title>
	<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js"></script>
	<link rel="stylesheet" href="${ctxStatic}/bootstrap/bootstrap3.0/css/bootstrap.min.css">
	<script src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
	<link rel="stylesheet" href="${ctxStatic}/bootstrap/2.3.1/awesome/font-awesome.min.css">
	<link rel="stylesheet" href="${ctxStatic}/bootstrap/animate.min.css">
	<!--[if lte IE 7]>
	<link rel="stylesheet" href="../bootstrap/2.3.1/awesome/font-awesome-ie7.css">
	<![endif]-->
	<!--[if lte IE 6]>
	<link rel="stylesheet" href="../bootstrap/bsie/css/bootstrap-ie6.min.css">
	<script src="../bootstrap/bsie/js/bootstrap-ie.min.js"></script>
	<![endif]-->
	<link rel="stylesheet" href="${ctxStatic}/asidenav/asidenav.css">
	<link href="${ctxStatic}/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" />
	<link href="${ctxStatic}/layer-v3.1.1/layer/theme/default/layer.css" rel="stylesheet" />
	<link rel="stylesheet" href="${ctxStatic}/ol/ol.css" type="text/css">
	<link rel="stylesheet" href="${ctxStatic}/modules/map/css/map.css" type="text/css">
	<link rel="stylesheet" href="${ctxStatic}/layui/css/layui.css">
	<link rel="stylesheet" href="${ctxStatic}/supermapopenlayers/iclient-openlayers.min.css" type="text/css">
	<link rel="stylesheet" href="${ctxStatic}/common/index/css/indexCommon.css">
	<link rel="stylesheet" href="${ctxStatic}/modules/map/css/publicinstitutions.css">
	<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
	<link href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="${ctxStatic}/common/index/css/statIndexZj.css">
	<link rel="stylesheet" href="${ctxStatic}/common/index/css/awesome-bootstrap-checkbox.min.css">
	<link rel="stylesheet" href="${ctxStatic}/common/index/css/keyPersonal.css">
	<script type="text/javascript">
		var ctxStatic = '${ctxStatic}',
				ctx = '${ctx}';
	</script>

	<script src="${ctxStatic}/ol/ol.js"></script>
	<script src="${ctxStatic}/d3/d3.v4.min.js"></script>
	<script src="${ctxStatic}/modules/map/js/mapconfig.js"></script>
	<script src="${ctxStatic}/modules/map/js/commonMap.js"></script>
	<script src="${ctxStatic}/mapv/mapv.min.js"></script>
	<script src="${ctxStatic}/supermapopenlayers/iclient-openlayers.min.js"></script>
	<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
	<script src="${ctxStatic}/layer-v3.1.1/layer/layer.js"></script>
	<script type="text/javascript" src="${ctxStatic}/echarts/echarts-4.2.1/echarts.min.js"></script>
	<script src="${ctxStatic}/custom/date/date.js"></script>
	<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.js" type="text/javascript"></script>
	<script src="${ctxStatic}/common/index/Scripts/js/statIndexZjkeypeople.js"></script>
	<script src="${ctxStatic}/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="${ctxStatic}/common/index/Scripts/js/echarts-liquidfill.min.js"></script>

	<style type="text/css">
		.table-info td {
			padding-top: 12px;
		}

		.active {
			background: url("${ctxStatic}/bootstrap/2.3.1/img/nav-xz.png") center no-repeat;
			background-size: 100% 100%;
			color: #fff !important;
			cursor: pointer;
			font-weight: bold;
		}

		.nav li a:hover {
			background: url("${ctxStatic}/bootstrap/2.3.1/img/nav-xt.png") center no-repeat;
			background-size: 100% 100%;
			color: #fff !important;
			cursor: pointer;
		}

		.nav li a:focus {
			background-color: #53CEFF;
			border-color: #53CEFF;
			color: #fff;
			background-image: linear-gradient(to right, rgba(98, 178, 250, 1), rgba(165, 213, 245, 1), rgba(98, 178, 250, 1));
			text-shadow: 0 0 5px #fff, 0 0 10px #fff, 0 0 15px #fff, 0 0 20px #228DFF, 0 0 35px #228DFF, 0 0 40px #228DFF, 0 0 50px #228DFF, 0 0 75px #228DFF;
		}

		.tubiao i {
			display: block;
			position: absolute;
		}
		{
			width: 200px;
		}

		input::-webkit-input-placeholder {
			color: white;
		}

		input::-moz-placeholder {
			/* Mozilla Firefox 19+ */
			color: white;
		}

		input:-moz-placeholder {
			/* Mozilla Firefox 4 to 18 */
			color: white;
		}

		input:-ms-input-placeholder {
			/* Internet Explorer 10-11 */
			color: white;
		}

		.pub-flag {
			color: #fff;
		}

		.pub-name {
			color: #79B1E3;
		}

		.echarts {
			width: 450px;
			height: 200px;
		}

		.common-pading {
			width: 100%;
			padding: 0.25% 5.5%;
		}

		.ol-popup:before {
			border-top-color: unset;
		}
		.ol-popup:after, .ol-popup:before{
			display:none !important;
		}
		body .mySkin .layui-layer-title {
			color: #fff;
			border: none;
		}

		body .mySkin .layui-layer-btn {
			border-top: 1px solid #E9E7E7
		}

		body .mySkin .layui-layer-btn a {
			background: #333;
		}

		body .mySkin .layui-layer-btn .layui-layer-btn1 {
			background: #999;
		}

		#echLeftContent1{
			display: inline-block!important;
		}
		.menu a{
			height: 72px !important;
			padding-top: 22px !important;
		}
		.liuG{
			width: 270px;
			height: 70px;
			display: block;
			background: url(${ctxStatic}/bootstrap/2.3.1/img/lg3.png) no-repeat left bottom;
			content: "";
			animation-name: logoLight;
			animation-duration: 4s;
			animation-iteration-count: infinite;
			position: absolute;
			left: 0;
			top: 26px;
			z-index: 9999;
		}

		@keyframes logoLight {
			0% {
				margin-left: -180px;
				opacity: 1,
			}
			40% {
				opacity: 1;
			}
			50% {
				opacity: 0.1;
			}
			100% {
				opacity: 0;
				margin-left: 240px;
			}
		}
	</style>
	<script>
		$(function() {
			$('#main').height($(window).height());

			$('.container-center').height($('#main').height() - 70);

		})
	</script>
	<script language="javascript">
		function videoSubmit() {
			document.getElementById("loginForm").action = "${dz_hangzhoudao_link_video}";
			document.getElementById("loginForm").submit();
		}

		function pbsSubmit() {
			document.getElementById("loginForm").action = "${dz_hangzhoudao_link_pbs}";
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
	<div id="FullBody">
		<div class="row-fluid header" style="position: absolute;z-index:2;top: -2px;">
			<div>
				<!-- 菜单 -->
				<div style="z-index: 9999;position: absolute;width:100%; top: 0px;left: 26px;">
					<div style="float: right;" class="Logout">
						<a style="font-size:unset;color: unset;display: inline;" href="${ctx}/sys/map/projectIndex"><img src="/arjccm/static/common/index/images/statIndexCool/home.png"></a>
						<img style="display: inline;" src="/arjccm/static/common/index/images/statIndexCool/vertical.png">
						<a style="font-size:unset;color: unset;display: inline;" href="${ctx}/logout"><img src="/arjccm/static/common/index/images/statIndexCool/exit.png"></a>
					</div>
					<div style="float: left;width: 26%;text-align: left;margin-left: 20px;padding-top: 1%">
						<div class="liuG"></div>
						<img class="logo" src="/arjccm/static/common/index/images/statIndexZj/xmzj_logo.png" style="margin-left: -20px;width:46px;height:46px;vertical-align:baseline;">
						<span id="productName" style="height:45px;vertical-align:baseline;width:300px;font-size:30px;font-family:MicrosoftYaHei;font-weight:400;color:rgba(255,255,255,1);line-height:27px;text-shadow:0px 3px 7px rgba(0, 0, 0, 0.3);position: relative;top: -70px; left: 40px;background: none;">宗教全时空立体化防控</span>
					</div>
					<div>
						<ul class="nav pm-links">
							<li class="menu">
								<a href="${ctx}/sys/map/statIndexForZj">数据展示</a>
							</li>
							<li class="menu">
								<a class="active" style="margin-left: 20px; " href="${ctx}/sys/map/keyPersonal">重点人员专题</a>
							</li>
							<li class="menu">
								<a style="margin-left: 20px;" href="${ctx}/sys/map/religionIndex">宗教专题</a>
							</li>
					<%--		<li class="menu">
								<a style="margin-left: 20px; " id="anquanshengchan">安全生产</a>
							</li>
							<li class="menu">
								<a style="margin-left: 20px;" id="xuelianggongcheng">雪亮工程</a>
							</li>--%>
						</ul>
					</div>
				</div>

			</div>

			<div>
				<h5 class="header-logo"></h5>
			</div>
		</div>
		<div class="row-fluid" style="width: 100%;height: 100%;position: absolute;padding-top: 90px;">
			<div class="left-area">
				<div class="title">人员分布</div>
				<div class="content">
					<div class="search-box">
						<div class="form-group has_dark has-feedback">
							<input type="text" class="form-control" id="">
							<span class="glyphicon glyphicon-search form-control-feedback" aria-hidden="true"></span>
						</div>
					</div>
					<div class="checkbox-box">
						<div class="tit">数据类型</div>

						<div class="ckbx clearfix">
							<div class="checkbox">
								<input type="checkbox" id="checkbox1">
								<label for="checkbox1">
									车辆卡口
								</label>
							</div>
							<div class="checkbox">
								<input type="checkbox" id="checkbox2">
								<label for="checkbox2">
									人脸卡口
								</label>
							</div>
							<div class="checkbox">
								<input type="checkbox" id="checkbox3">
								<label for="checkbox3">
									RFID
								</label>
							</div>
							<div class="checkbox">
								<input type="checkbox" id="checkbox4">
								<label for="checkbox4">
									电子围栏
								</label>
							</div>
							<div class="checkbox">
								<input type="checkbox" id="checkbox5">
								<label for="checkbox5">
									wifi探针
								</label>
							</div>
						</div>
					</div>
					<div class="checkbox-box">
						<div class="tit">人员类型</div>

						<div class="ckbx clearfix">
							<div class="checkbox">
								<input type="checkbox" id="checkbox6">
								<label for="checkbox6">
									社教人员
								</label>
							</div>
							<div class="checkbox">
								<input type="checkbox" id="checkbox7">
								<label for="checkbox7">
									重点人员
								</label>
							</div>
							<div class="checkbox">
								<input type="checkbox" id="checkbox8">
								<label for="checkbox8">
									流动人员
								</label>
							</div>
							<div class="checkbox">
								<input type="checkbox" id="checkbox9">
								<label for="checkbox9">
									其他人员
								</label>
							</div>
						</div>
					</div>
					<div class="date-box clearfix">
						<div class="tit">时间</div>
						<div class="form-group has_dark has-feedback timebox">
							<input name="beginHappenDate"
							type="text" readonly="readonly" maxlength="20"
							class="input-medium form-control"
							value="开始时间"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
							<span class="glyphicon glyphicon-calendar form-control-feedback" aria-hidden="true"></span>
						</div>
						<div style="padding: 0 2px;">-</div>
						<div class="form-group has_dark has-feedback timebox">
							<input name="beginHappenDate"
								   type="text" readonly="readonly" maxlength="20"
								   class="input-medium form-control"
								   value="结束时间"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
							<span class="glyphicon glyphicon-calendar form-control-feedback" aria-hidden="true"></span>
						</div>
					</div>
					<div class="btn-box">
						<button >最近1小时</button>
						<button >最近3小时</button>
						<button >最近1天</button>
					</div>
					<div class="table-box">
						<table class="table table-condensed">
							<tr>
								<th>人员</th>
								<th>时间</th>
								<th>位置</th>
								<th>操作</th>
							</tr>
							<tr>
								<td>张三四</td>
								<td>01-07 13:01:32</td>
								<td>xx街xx路交口</td>
								<td class="clearfix">
									<a class="dangan"></a>
									<a class="guiji"></a>
									<a class="dingwei"></a>
								</td>
							</tr>
							<tr>
								<td>张三四</td>
								<td>01-07 13:01:32</td>
								<td>xx街xx路交口</td>
								<td class="clearfix">
									<a class="dangan"></a>
									<a class="guiji"></a>
									<a class="dingwei"></a>
								</td>
							</tr>
							<tr>
								<td>张三四</td>
								<td>01-07 13:01:32</td>
								<td>xx街xx路交口</td>
								<td class="clearfix">
									<a class="dangan"></a>
									<a class="guiji"></a>
									<a class="dingwei"></a>
								</td>
							</tr>
						</table>
					</div>

				</div>
			</div>

			<div id="pubMap"></div>
			<div id="mapMask" class="map"></div>

		</div>
	</div>
</div>
</body>

</html>