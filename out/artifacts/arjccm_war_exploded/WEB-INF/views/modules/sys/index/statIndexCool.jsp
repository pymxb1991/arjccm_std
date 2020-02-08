<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

	<head>

		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="renderer" content="webkit|ie-comp|ie-stand" />
		<title>社会网格化管理信息系统</title>
		<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js"></script>
		<link rel="stylesheet" href="${ctxStatic}/bootstrap/bootstrap3.0/css/bootstrap.min.css">
		<%--<script src="${ctxStatic}/bootstrap/bootstrap3.0/js/bootstrap.min.js"></script>--%>
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
		<%--<script src="${ctxStatic}/asidenav/asidenav.js"></script>--%>
		<%--<script src="${ctxStatic}/asidenav/jquery.min.js"></script>--%>
		<link rel="stylesheet" href="${ctxStatic}/ol/ol.css" type="text/css">
		<link rel="stylesheet" href="${ctxStatic}/modules/map/css/map.css" type="text/css">
		<link rel="stylesheet" href="${ctxStatic}/layui/css/layui.css">
		<link rel="stylesheet" href="${ctxStatic}/supermapopenlayers/iclient-openlayers.min.css" type="text/css">
		<link rel="stylesheet" href="${ctxStatic}/common/index/css/indexCommon.css">
		<link rel="stylesheet" href="${ctxStatic}/modules/map/css/publicinstitutions.css">
		<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
		<link href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css" />
		<%-- <link rel="stylesheet" href="${ctxStatic}/common/index/css/PopInfo.css"> --%>
		<%--<link rel="stylesheet"--%>
		<%--	href="${ctxStatic}/common/index/css/statSituationStatistics.css">--%>
		<link rel="stylesheet" href="${ctxStatic}/common/index/css/statIndexCool.css">
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
		<script src="${ctxStatic}/common/index/Scripts/js/echarts-liquidfill/echarts.min.js"></script>
		<script src="${ctxStatic}/layer-v3.1.1/layer/layer.js"></script>
		<%--<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>--%>

		<script type="text/javascript" src="${ctxStatic}/echarts/echarts-4.2.1/echarts.min.js"></script>
		<script src="${ctxStatic}/custom/date/date.js"></script>
		<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
		<script src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.js" type="text/javascript"></script>
		<script src="${ctxStatic}/common/index/Scripts/js/statIndexCool.js"></script>
		<script src="${ctxStatic}/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script src="${ctxStatic}/common/index/Scripts/js/echarts-liquidfill/echarts-liquidfill.min.js"></script>
		<style type="text/css">
			.table-info td {
				padding-top: 12px;
			}

			.active {
				background: url("${ctxStatic}/bootstrap/2.3.1/img/nav-xz.png") center no-repeat;
				background-size: 100% 100%;
				/*background-color: #53CEFF;
            border-color: #53CEFF;*/
				color: #fff !important;
				/*background-image: linear-gradient(to right, rgba(98, 178, 250, 1), rgba(157, 203, 245, 1), rgba(98, 178, 250, 1));*/
				/*text-shadow: 0 0 5px #fff,
            0 0 10px #fff,
            0 0 15px #fff,
            0 0 20px #228DFF,
            0 0 35px #228DFF,
            0 0 40px #228DFF,
            0 0 50px #228DFF,
            0 0 75px #228DFF;*/
				cursor: pointer;
				font-weight: bold;
			}

			.nav li a:hover {
				background: url("${ctxStatic}/bootstrap/2.3.1/img/nav-xt.png") center no-repeat;
				background-size: 100% 100%;
				/*background-color: #53CEFF;
            border-color: #53CEFF;*/
				color: #fff !important;
				cursor: pointer;
				/*background-image: linear-gradient(to right, rgba(98, 178, 250, 1), rgba(157, 203, 245, 1), rgba(98, 178, 250, 1));*/
				/*text-shadow: 0 0 5px #fff,
            0 0 10px #fff,
            0 0 15px #fff,
            0 0 20px #228DFF,
            0 0 35px #228DFF,
            0 0 40px #228DFF,
            0 0 50px #228DFF,
            0 0 75px #228DFF;*/
			}
			/*#leftContent1Body1 i {
        	display: block;
        	width: 18px;
        	height: 18px;
        	position: absolute;
        	left: 155px;
            top: 67px;
        	background: url("/arjccm/static/common/index/images/statIndexCool/renkou.png") center no-repeat;
        }
        #rightContent1Body i {
        	display: block;
        	width: 18px;
        	height: 18px;
        	position: absolute;
        	left: 162px;
            top: 67px;
        	background: url("/arjccm/static/common/index/images/statIndexCool/fangwu.png") center no-repeat;
        }*/

			.nav li a:focus {
				background-color: #53CEFF;
				border-color: #53CEFF;
				color: #fff;
				background-image: linear-gradient(to right, rgba(98, 178, 250, 1), rgba(165, 213, 245, 1), rgba(98, 178, 250, 1));
				text-shadow: 0 0 5px #fff, 0 0 10px #fff, 0 0 15px #fff, 0 0 20px #228DFF, 0 0 35px #228DFF, 0 0 40px #228DFF, 0 0 50px #228DFF, 0 0 75px #228DFF;
			}

			.kuang {
				position: absolute;
				display: block;
				width: 142px;
				height: 142px;
				border: 1px solid rgba(50, 229, 224, 1);
				border-radius: 50%;
				left: 35px;
				top: 69px;
			}

			.kuang1 {
				position: fixed;
				display: block;
				width: 100px;
				height: 100px;
				border: 1px solid rgba(50, 229, 224, 1);
				border-radius: 50%;
				left: 90px;
				top: 455px;
			}

			.kuang2 {
				position: fixed;
				display: block;
				width: 100px;
				height: 100px;
				border: 1px solid rgba(50, 229, 224, 1);
				border-radius: 50%;
				left: 234px;
				top: 455px;
			}

			.kuang3 {
				position: fixed;
				display: block;
				width: 100px;
				height: 100px;
				border: 1px solid rgba(50, 229, 224, 1);
				border-radius: 50%;
				left: 378px;
				top: 455px;
			}
			.tubiao i {
				display: block;
				position: absolute;
			}
/*			.fangwu {
				width: 18px;
        	    height: 18px;
        	    position: absolute;
        	    right: 370px;
                top: 147px;
        	    background: url("/arjccm/static/common/index/images/statIndexCool/fangwu.png") center no-repeat;
			}*/
/*			.renkou {
				width: 18px;
        	    height: 18px;
        	    left: 167px;
                top: 148px;
        	    background: url("/arjccm/static/common/index/images/statIndexCool/renkou.png") center no-repeat;
			}*/
/*			.jinggai {
				width: 15px;
				height: 18px;
				left: 62px;
                top: 696px;
				background: url("/arjccm/static/common/index/images/statIndexCool/jinggai.png") center no-repeat;
			}
			.ludeng {
				width: 17px;
				height: 14px;
				left: 60px;
                top: 754px;
				background: url("/arjccm/static/common/index/images/statIndexCool/ludeng.png") center no-repeat;
			}
			.lajitong {
				width: 19px;
				height: 19px;
				left: 57px;
                top: 803px;
				background: url("/arjccm/static/common/index/images/statIndexCool/lajitong.png") center no-repeat;
			}*/
			.input-medium.Wdate {
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

			.myskin {
				background-color: transparent;
			}

			.jbox-content {
				overflow: unset !important;
			}

			.jbox-button-panel {
				height: unset !important;
			}

			.jbox-title-panel {
				height: 35px !important;
			}

			#areaName {
				width:221px;
				height:35px!important;
				margin-top: -2px;
				margin-bottom: 18px;
				border:1px solid rgb(0,164,190);
				background:linear-gradient(0deg,rgb(0,106,146),
				rgb(0,94,130));
				opacity:1;border-radius:2px;
				background-color:#184d8b;


				/*width:221px;*/
				/*height:35px;*/
				/*border:1px solid rgba(73,220,254,1);*/
				/*background:linear-gradient(0deg,rgba(6,151,248,1),*/
				/*rgba(23,75,185,1));*/
				/*opacity:0.6;border-radius:2px;*/
				/*margin-right: 25px;*/
				/*//*/
				/*border-color: rgb(11, 124, 199);*/
				/*background: rgba(36, 105, 187, 0.6);*/
				/*border-width: 1px;*/
				/*!*width: 14% !important;*!*/
				/*height: 30px !important;*/
				/*border-color: rgb(11, 124, 199)!important;*/
			}

			.input-append {
				display: inline-block !important;
			}

			#areaButton {
                 margin-bottom: 2px;
				 height:35px !important;
				 border:1px solid rgb(0,164,190);
				 background:linear-gradient(0deg,rgb(0,106,146),
				 rgb(0,94,130));
				 opacity:1;
				 margin-right: 15px;
			 }

			.echarts {
				width: 450px;
				height: 200px;
			}

			.common-pading {
				width: 100%;
				padding: 0.25% 5.5%;
			}

			.icon-search {
				color: #fff !important;
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

			.form-signin-heading {
				font-family: 微软雅黑;
				font-size: 28px;
				color: #ffffff;
				width: 50%;
				text-align: left;
				position: absolute;
				top: 17px;
				left: 80px;
			}
			#echLeftContent1{
				display: inline-block!important;
			}
            #echLeftContent1>div{
                position: absolute!important;
            }
            /*导航a标签宽度修改*/
            .menu a{
                height: 72px !important;
                 padding: 0!important;
                 padding: 22px 28px 10px !important;
            }
			.liuG{
				width: 240px;
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
					opacity: 1;
				}
				40% {
					opacity: 1;
				}
				50% {
					opacity: 0.1;
				}
				100% {
					opacity: 0;
					margin-left: 145px;
				}
			}
            /*Z-Tree位置*/
            #jbox{
                top: 130px !important;
                left: 790px !important;
            }
            .btn{
                background:url("${ctxStatic}/bootstrap/2.3.1/img/cyan-cz.png") no-repeat;
                background-size: 100% 100%;
            }
            .btn:hover{
                color: #fff;
                background:url("${ctxStatic}/bootstrap/2.3.1/img/cyan-jh.png") no-repeat;
                background-size: 100% 100%;
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
				<!--     <div class="row height10">
        <div class="col-xs-12">
            <div class="header">社会网格化管理信息系统</div>
        </div>
    </div> -->

				<div class="row-fluid header" style="position: absolute;z-index:2;top: -2px;">
					<div>
						<!-- 菜单 -->
						<div style="z-index: 9999;position: absolute;width:100%; top: 0px;left: 26px;">
							<div style="float: right; padding-top: 13px;"  class="Logout">
								<a style="font-size:unset;color: unset;display: inline;" href="${ctx}/sys/map/projectIndex"><img src="/arjccm/static/common/index/images/statIndexCool/home.png"></a>
								<img style="display: inline;" src="/arjccm/static/common/index/images/statIndexCool/vertical.png">
								<a style="font-size:unset;color: unset;display: inline;" href="${ctx}/logout"><img src="/arjccm/static/common/index/images/statIndexCool/exit.png"></a>
								<%--							<span> <a href="${ctx}/logout"> <i--%>
								<%--									class="icon-off align-top bigger-125"></i> 退出--%>
								<%--							</a>--%>
								<%--							</span>--%>
							</div>
							<div style="float: left;width: 26%;text-align: left;margin-left: 20px;padding-top: 1%">
								<div class="liuG"></div>
								<img class="logo" src="/arjccm/static/images/logo.png" style="width:46px;height:46px;vertical-align:baseline;">
								<%--<img class="head" src="/arjccm/static/images/head.png" style="margin-top: -106px; margin-left: 45px">--%>
								<%--<h1 class="form-signin-heading">${fns:getConfig('showName')}</h1>--%>
								<span id="productName" style="width:190px;font-size:26px;font-family:MicrosoftYaHei;font-weight:400;color:rgba(255,255,255,1);line-height:27px;text-shadow:0px 3px 7px rgba(0, 0, 0, 0.3);position: relative;top: -80px; left: 55px;background: none;">${fns:getConfig('productName_part1')}</span>
								<span style="display: block; top: -90px;left: 25px;width:220px;color:rgba(204, 204, 204, 1); font-size: 17px;font-weight: bold;position: relative;background: none;">${fns:getConfig('productName_part2')}</span>
							</div>
							<div>
								<%--								<span style="float: left;margin-left: 100px"><a href="${ctx}"><i class=""></i>主面板</a></span>
                                                        <span style="float: left;margin-left: 20px"><a href="#" target="_blank" onclick="pbsSubmit();"><i class=""></i>智慧党建</a></span>
                                                        <span style="float: left;margin-left: 20px"><a href="#" target="_blank" onclick="videoSubmit();"><i class=""></i>视频融合</a></span>
                                                        <span style="float: left;margin-left: 20px"><a href="${ctx}/index"><i class=""></i>网格化首页</a></span>	--%>
								<ul class="nav pm-links">
									<li class="menu">
										<a  class="active" id="wanggeguanli">网格管理</a>
									</li>
									<li class="menu">
										<a  id="guanzhuduixiang">关注对象</a>
									</li>
									<li class="menu">
										<a  id="zhiantaishi">治安态势</a>
									</li>
									<li class="menu">
										<a  id="anquanshengchan">安全生产</a>
									</li>
									<li class="menu">
										<a  id="xuelianggongcheng">雪亮工程</a>
									</li>
								</ul>
								<%--                                <span style="float: left;margin-left: 200px;font-size: 18px;font-family: MicrosoftYaHei;font-weight: normal;" id="cba"><i class=""></i>网格管理</span>--%>
								<%--								<span style="float: left;margin-left: 20px"><a href="${ctx}/sys/map/statLivelihoodQuLiang">关注对象</a></span>--%>
								<%--								<span style="float: left;margin-left: 20px"><a href="${ctx}/sys/map/statPublicSecurityQuLiang">治安态势</a></span>--%>
								<%--								<span style="float: left;margin-left: 20px"><a href="${ctx}/sys/map/statNpseSafeQuLiang">安全生产</a></span>--%>
								<%--								<span style="float: left;margin-left: 20px"><a href="${ctx}/sys/map/statConstructionEconomicsQuLiang">雪亮工程</a></span>--%>
								<%--	<div  id="abc">--%>
								<%--		<span style="float: left;margin-left: 20px">cccccccccccccccccccccccccccccccccccccc</span>--%>
								<%--	</div>--%>

							</div>
						</div>

					</div>

					<div>
						<h5 class="header-logo"></h5>
					</div>
				</div>
				<div class="row-fluid" style="width: 100%;height: 100%;position: absolute;padding-top: 90px;">
					<div id="leftCol" class="leftCol">
						<div id="leftContent0" class="showBigContent">
							<div class="contentTitle"><span id="leftTitle0"></span></div>
							<div class="common-pading" id="leftContent0Body">
								<div id="echLeftContent0" class="echarts"></div>
							</div>
							<div class="mainVideo" id="videoCountDiv">
								<div class="zongshu">
									<h5>视频总数</h5>
									<b id="zongshu">5560</b> 个
								</div>
								<ul>
									<li class="jiguan">
										<i></i>
										<span></span>
										<div>
											<h6>党政机关</h6>
											<div style="text-align: center; top: 30px"><b id="jiguan">5560</b>个</div>
										</div>
									</li>
									<li class="zhugandao">
										<i></i>
										<span></span>
										<div>
											<h6>主干道</h6>
											<div style="text-align: center; top: 30px"><b id="zhugandao">5560</b>个</div>
										</div>
									</li>
									<li class="xuexiao">
										<i></i>
										<span></span>
										<div>
											<h6>学校</h6>
											<div style="text-align: center; top: 30px"><b id="xuexiao" style="text-align: right">5560</b>个</div>
										</div>
									</li>
									<li class="xiaoqu">
										<i></i>
										<span></span>
										<div>
											<h6>小区</h6>
											<div style="text-align: center; top: 30px"><b id="xiaoqu" style="text-align: right">5560</b>个</div>
										</div>
									</li>
									<li class="ditie">
										<i></i>
										<span></span>
										<div>
											<h6>地铁口</h6>
											<div style="text-align: center; top: 30px"><b id="ditie">5560</b>个</div>
										</div>
									</li>
									<li class="shangchang">
										<i></i>
										<span></span>
										<div>
											<h6>商场</h6>
											<div style="text-align: center; top: 30px"><b id="shangchang">5560</b>个</div>
										</div>
									</li>
									<li class="jiayouzhan">
										<i></i>
										<span></span>
										<div>
											<h6>加油站</h6>
											<div style="text-align: center; top: 30px"><b id="jiayouzhan">5560</b>个</div>
										</div>
									</li>
									<li class="qita">
										<i></i>
										<span></span>
										<div>
											<h6>其他</h6>
											<div style="text-align: center; top: 30px"><b id="qita">5560</b>个</div>
										</div>
									</li>
								</ul>
							</div>
						</div>
						<div id="leftContent1"  class="showContent">
							<div class="contentTitle" ><span id="leftTitle1"></span><i></i></div>
							<div class="common-pading" id="leftContent1Body">
								<div id="echLeftContent1" class="echarts" ></div>
							</div>
							<div class="common-pading" id="leftContent1BodyWg">
								<div id="rkt1">
									<i class="rklogo"><img src="${ctxStatic}/common/index/images/statIndexCool/renkou2.png"/> </i>
									<span>人口数据</span>
									<span class="rkzs"></span>
									<span>人</span>
								</div>
								<div id="rkt2">
									<div class="rksj1">
										<i class="liuguangl"><img src="${ctxStatic}/images/lg.png"/></i>
										<span class="rknum"></span>
										<i class="liuguang2"><img src="${ctxStatic}/images/lg.png"/></i>
										<span class="rktitle"></span>
									</div>
									<div class="rksj2">
										<i class="liuguangl"><img src="${ctxStatic}/images/lg.png"/></i>
										<span class="rknum"></span>
										<i class="liuguang2"><img src="${ctxStatic}/images/lg.png"/></i>
										<span class="rktitle"></span>
									</div>
									<div class="rksj3">
										<i class="liuguangl"><img src="${ctxStatic}/images/lg.png"/></i>
										<span class="rknum"></span>
										<i class="liuguang2"><img src="${ctxStatic}/images/lg.png"/></i>
										<span class="rktitle"></span>
									</div>
									<div class="rksj4">
										<i class="liuguangl"><img src="${ctxStatic}/images/lg.png"/></i>
										<span class="rknum"></span>
										<i class="liuguang2"><img src="${ctxStatic}/images/lg.png"/></i>
										<span class="rktitle"></span>
									</div>
								</div>

							</div>
							<div class="security-main" id="safeKeyDiv">

								<div class="under">

								</div>
								<div class="frame frame-tl">
									<i class="npesrisktype1">
								<div  class="npesrisktypeDiv1">
									<h6>高风险单位</h6>
									<b  id="riskRank1">5560</b>
								</div>
							</i>
								</div>
								<div class="frame frame-tr">
									<i class="npesrisktype2">
								<div class="npesrisktypeDiv2">
									<h6>较大风险单位</h6>
										<b id="riskRank2">5560</b>
								</div>
							</i>
								</div>
								<div class="frame frame-bl">
									<i class="npesrisktype3">
								<div class="npesrisktypeDiv3">
									<h6>一般风险单位</h6>
									<b id="riskRank3">5560</b>
								</div>
							</i>
								</div>
								<div class="frame frame-br">
									<i class="npesrisktype4">
								<div class="npesrisktypeDiv4">
									<h6>低风险单位</h6>
									<b id="riskRank4">5560</b>
								</div>
							</i>
								</div>
							</div>
						</div>
						<div id="leftContent2" style="display: none;" class="showContent">
							<div class="contentTitle"><span id="leftTitle2"></span></div>
							<div class="common-pading" id="leftContent2Body">
								<div id="echleftContent2" class="echarts"></div>
							</div>
						</div>
						<div id="leftContent3" style="display: none" class="showContent">
							<div class="contentTitle"><span id="leftTitle3"></span></div>
							<div class="common-pading" id="leftContent3Body">
								<div id="echLeftContent3" class="echarts"></div>
							</div>
						</div>
						<div id="leftContent4" class="showBigContent" style="display: none;">
							<div class="contentTitle"><span id="leftTitle4"></span></div>
							<div class="common-pading" id="leftContent4Body">
								<div id="echLeftContent4" class="echarts"></div>
							</div>
						</div>
						<div class="tubiao">
							<i class="fangwu"></i>
							<i class="renkou"></i>
							<i class="jinggai"></i>
							<i class="ludeng"></i>
							<i class="lajitong"></i>
						</div>
					</div>
					<div id="rightCol" class="rightCol">
						<div id="rightContent0" class="showBigContent" style="display:none;">
							<div class="contentTitle"><span id="rightTitle1"></span></div>
							<div class="common-pading" id="rightContent0Body" style="height: 90%;">
								<div id="echRightContent0" class="echarts"></div>
								<div id="echRightContent01" class="echarts"></div>
							</div>
							<div class="mainVideoPop" id="videoCountDivPop">
								<ul>
									<li class="azbj mianLeftDiv" style="margin-top: 40px" >
										<i></i>
										<span></span>
										<div>
											<h6>安置帮教人员</h6>
											<b id="azbj">5560</b>个
										</div>
									</li>
									<li class="sqjz mianCenterDiv" style="margin-top: 40px">
										<i></i>
										<span></span>
										<div>
											<h6>社区矫正人员</h6>
											<b id="sqjz">5560</b>个
										</div>
									</li>
									<li class="jsza mianRightDiv" style="margin-top: 40px">
										<i></i>
										<span></span>
										<div>
											<h6 style="margin-top: -10px;">肇事肇祸等严重精神障碍患者</h6>
											<b id="jsza">5560</b>个
										</div>
									</li>
									<li class="drug mianLeftDiv">
										<i></i>
										<span></span>
										<div>
											<h6>吸毒人员</h6>
											<b id="drug">5560</b>个
										</div>
									</li>
									<li class="aids mianCenterDiv">
										<i></i>
										<span></span>
										<div>
											<h6>艾滋病危险人员</h6>
											<b id="aids">5560</b>个
										</div>
									</li>
									<li class="visit mianRightDiv">
										<i></i>
										<span></span>
										<div>
											<h6>重点上访人员</h6>
											<b id="visit">5560</b>个
										</div>
									</li>
									<li class="sjry mianLeftDiv">
										<i></i>
										<span></span>
										<div>
											<h6>涉教人员</h6>
											<b id="sjry">5560</b>个
										</div>
									</li>
									<li class="dangures mianCenterDiv">
										<i></i>
										<span></span>
										<div>
											<h6>危险品从业人员</h6>
											<b id="dangures">5560</b>个
										</div>
									</li>
									<li class="lsry mianRightDiv">
										<i></i>
										<span></span>
										<div>
											<h6>留守人员</h6>
											<b id="lsry">5560</b>个
										</div>
									</li>
									<li class="key mianLeftDiv">
										<i></i>
										<span></span>
										<div>
											<h6>重点青少年</h6>
											<b id="key">5560</b>个
										</div>
									</li>
									<li class="care mianCenterDiv">
										<i></i>
										<span></span>
										<div>
											<h6>特殊关怀人员</h6>
											<b id="care">5560</b>个
										</div>
									</li>
									<li class="older mianRightDiv">
										<i></i>
										<span></span>
										<div>
											<h6>老年人</h6>
											<b id="older">5560</b>个
										</div>
									</li>
								</ul>
							</div>
						</div>
						<div id="rightContent1" class="showContent">
							<div class="contentTitle"><span id="rightTitle2"></span></div>
							<div class="common-pading" id="rightContent1Body">
								<i></i>
								<div id="echRightContent1" class="echarts"></div>
							</div>
							<div class="common-pading" id="videoDiv">
								<div style="text-align: right;width: 100%;">
									<sys:treeselect id="video" name="videoId" value="" labelName="videoName" placeholder="监控点 ..." labelValue="" title="监控点" url="/tree/ccmTree/treeDataNew?type=camera&fontCss={color:'#fff'}" cssClass="inputVideoBox" allowClear="true" notAllowSelectParent="true" cssStyle="" />
								</div>
								<iframe id="ccmLiveVideoVisualized" name="ccmLiveVideo" src="${ctx}/ccmsys/ccmDevice/getDeviceMap?id=${ccmDevice.id}" style="overflow: visible;" scrolling="yes" frameborder="no" width="479" height="185" allowfullscreen="true" allowtransparency="true"></iframe>
							</div>
							<div id="eventScale" class="layui-container" style="display: none;">
<%--								<i class="kuang1"></i>
								<i class="kuang2"></i>
								<i class="kuang3"></i>--%>
								<div class="layui-row">
									<div class="layui-col-xs6">
										<div class="scale">
											<div class="divClass1"><span id="eventSpan" class="span1" >1322</span><span class="span2">件</span>
											</div>
											<div class="scaleTitle">累计受理纠纷</div>
											<div class="scaleImage">
												<img src="/arjccm/static/images/eventScale2.png" />
											</div>
										</div>
									</div>
									<div class="layui-col-xs6">
										<div class="scale">
											<div class="divClass1"><span id="eventSpan1" class="span1">802</span><span class="span2">件</span>
											</div>
											<div class="scaleTitle">个体性事件</div>
											<div class="scaleImage">
												<img src="/arjccm/static/images/eventScale2.png" />
											</div>
										</div>
									</div>
								</div>
								<div class="layui-row">
									<div class="layui-col-xs6">
										<div class="scale s2">
											<div class="divClass1"><span id="eventSpan2" class="span1">405</span><span class="span2">件</span>
											</div>
											<div class="scaleTitle">一般个体性事件</div>
											<div class="scaleImage">
												<img src="/arjccm/static/images/eventScale2.png" />
											</div>
										</div>
									</div>
									<div class="layui-col-xs6">
										<div class="scale s2">
											<div class="divClass1"><span id="eventSpan3" class="span1">115</span><span class="span2">件</span>
											</div>
											<div class="scaleTitle">重大个体性事件</div>
											<div class="scaleImage">
												<img src="/arjccm/static/images/eventScale2.png" />
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div id="rightContent2" class="showContent">
							<div class="contentTitle"><span id="rightTitle3"></span></div>
							<div class="common-pading" id="rightContent2Body">
								<div id="echRightContent2" class="echarts"></div>
							</div>
							<div class="mainVideoType" id="videoTypeDiv">
								<div class="ptjk">
									<div style="margin-top: 9%">
										<b id="jiankong" style="font-size: 25px;color:#ECC31C">5560</b><b style="color:#0062E6">&nbsp;个</b>
									</div>
									<i></i>
									<span></span>
									<h6 class="jk-h6"  style="font-size: 15px;">普通监控</h6>
								</div>
								<div class="kkjk">
									<div style="margin-top: 9%">
										<b id="kakou" style="font-size: 25px;color:#ECC31C">5560</b><b style="color:#0062E6">&nbsp;个</b>
									</div>
									<i></i>
									<span></span>
									<h6  class="jk-h6" style="font-size: 15px;">卡口监控</h6>
								</div>
								<div class="rljk">
									<div style="margin-top: 9%">
										<b id="renlian" style="font-size: 25px;color:#ECC31C">5560</b><b style="color:#0062E6">&nbsp;个</b>
									</div>
									<i></i>
									<span></span>
									<h6 class="jk-h6" style="font-size: 15px;">人脸监控</h6>
								</div>
							</div>
						</div>
						<div id="rightContent3" class="showContent">
							<div class="contentTitle"><span id="rightTitle4"></span></div>
							<div class="common-pading" id="rightContent3Body">
								<div id="echRightContent3" class="echarts"></div>
							</div>
						</div>
					</div>
					<div id="topCol" class="topCol">
						<%--                    <input type="text" >--%>
						<select id="formType" style="width:200px;height:35px!important;border:1px solid rgb(0,164,190);background:linear-gradient(0deg,rgb(0,106,146),rgb(0,94,130));opacity:1;border-radius:2px;background-color:#184d8b; margin-right: 15px;margin-bottom: 18px">
							<option value="1" >人口</option>
							<option value="2">楼栋</option>
							<option value="3">出租屋</option>
							<option value="4">城市部件</option>
							<option value="5">企业</option>
							<option value="6">重点人员</option>
							<option value="7">事件</option>
							<option value="8">视频</option>
						</select>
						<sys:treeselect id="area" name="area" value="" labelName="area.name" placeholder="请选择辖区范围 ..." labelValue="" title="区域" url="/sys/area/treeData?fontCss={color:'#fff'}" cssClass="" allowClear="true" notAllowSelectParent="true" cssStyle="height:30px;width:190px;"  />
						<%--                    <input placeholder="请选择开始时间" autocomplete="off"--%>
						<%--                           name="beginHappenDateEvent" id="beginHappenDateEvent"--%>
						<%--                           type="text" maxlength="20" class="input-medium date"--%>
						<%--                           value=""--%>
						<%--                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});">--%>
						<%--	<span style="color: rgb(31,154,192)">—</span>--%>
						<%--					<input placeholder="请选择结束时间" name="endHappenDateEvent" autocomplete="off"--%>
						<%--                        id="endHappenDateEvent" type="text" maxlength="20"--%>
						<%--                        class="input-medium date" value=""--%>
						<%--                        onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});">--%>
						<input id="formName" style="width:200px;height:35px!important;border:1px solid rgb(0,164,190);background:linear-gradient(0deg,rgb(6,106,146),rgb(0,94,130));opacity:1;border-radius:2px;margin-bottom: 18px ;margin-right: 15px;"  />

						<button class="btn" style="border:1px solid rgb(0,164,190);width:60px;height:35px!important;/*background:linear-gradient(0deg,rgba(6,151,248,1),rgba(0,190,190,0.5));*/border-radius:2px; " onclick="getMapInfo()">查询
                </button>
					</div>
					<div id="bottomCol" class="bottomCol">
						<div style="padding-left: 50px;padding-top: 20px;">
							<!--学校  -->
							<div class="pub-flag" onclick="xuexiaoFun(this)">
								<span class="pub-icon icon-xuexiao"></span> <span class="pub-name">学校</span>
							</div>
							<!-- 医院 -->
							<div class="pub-flag" onclick="yiyuanFun(this)">
								<span class="pub-icon icon-yiyuan"></span> <span class="pub-name">医院</span>
							</div>
							<!-- 加油站 -->
							<div class="pub-flag" onclick="jiayouzhanFun(this)">
								<span class="pub-icon icon-jiayouzhans"></span> <span class="pub-name">加油站</span>
							</div>
							<!-- 商场超市 -->
							<div class="pub-flag" onclick="shangchangFun(this)">
								<span class="pub-icon icon-shangchang"></span> <span class="pub-name">商场超市</span>
							</div>
							<!-- 娱乐场所 -->
							<div class="pub-flag" onclick="yuleFun(this)">
								<span class="pub-icon icon-yule"></span> <span class="pub-name">娱乐场所</span>
							</div>
							<!-- 宾馆 -->
							<div class="pub-flag" onclick="binguanFun(this)">
								<span class="pub-icon icon-bingguan"></span> <span class="pub-name">酒店宾馆</span>
							</div>
							<!-- 涉危涉爆单位 -->
							<div class="pub-flag" onclick="sheweishebaoFun(this)">
								<span class="pub-icon icon-sheweishebao"></span> <span class="pub-name">涉危涉爆</span>
							</div>

							<!-- 视频监控 -->
							<div class="pub-flag" onclick="shipinjiankongFun(this)" id="VideoFlag" VideoFlagAttr="false">
								<span class="pub-icon icon-shipins"></span> <span class="pub-name">视频监控</span>
							</div>
							<!-- 警员-->
							<div class="pub-flag" onclick="jingyuanFun(this)">
								<span class="pub-icon icon-jingyuan"></span> <span class="pub-name">工作人员</span>
							</div>
							<%--<c:if test="${sysConfig.objId eq 'xinmishi'}">--%>
							<!-- 警务室-->
							<div class="pub-flag" onclick="jingwushiFun(this)">
								<span class="pub-icon icon-jingwushi"></span> <span class="pub-name">警务室</span>
							</div>
							<!-- 工作站-->
							<div class="pub-flag" onclick="gongzuozhanFun(this)">
								<span class="pub-icon icon-gongzuozhan"></span> <span class="pub-name">工作站</span>
							</div>
							<!-- 广播站-->
							<div class="pub-flag" onclick="guangbozhanFun(this)">
								<span class="pub-icon icon-guangbozhan"></span> <span class="pub-name">广播站</span>
							</div>
							<!-- 警车-->
							<!-- <div class="pub-flag" onclick="jingcheFun(this)">
							   <span class="pub-icon icon-jingche"></span> <span
								   class="pub-name">警车</span>
						    </div>  -->
							<%--</c:if>--%>
							<!--机顶盒  -->
							<div class="pub-flag" onclick="SetTopBoxFun(this)">
								<span class="pub-icon icon-Settopbox"></span> <span class="pub-name">机顶盒</span>
							</div>
						</div>
					</div>
					<div id="toolCol" class="toolCol">
						<div style="height: 40%;text-align: center;margin-top: 30%;" onclick="Map.selectQuery('Polygon')">
							<img src="/arjccm/static/common/index/images/statIndexCool/draw.png">
							<p>标绘</p>
						</div>
						<div style="height: 40%;text-align: center;" onclick="Map.fullScreen()">
							<img src="/arjccm/static/common/index/images/statIndexCool/allScreen.png">
							<p>全屏</p>
						</div>
					</div>
					<div id="popup" class="ol-popup  popup-ksh">
						<%--					<div class="popup-top">--%>
						<%--						<span class="popup-title"></span> <a href="#" id="popup-closer" class="ol-popup-closer"></a>--%>
						<%--					</div>--%>
						<%--					<div class="popup-content" id="popup-content"></div>--%>
						<div class="">
							<%--<a href="#" id="popup-closer" class="ol-popup-closer  icon-remove " title="关闭" onmouseover="$(this).addClass('jbox-close-hover');" onmouseout="$(this).removeClass('jbox-close-hover');" style="position: absolute; display: block; cursor: pointer; top: 4px; right: 11px; width: 15px; height: 15px; color: #fff"></a>--%>
							<%--<div class="jbox-title-panel" style="height: 25px;">
								<div class="jbox-title">信息</div>
							</div>--%>
							<div class="jbox-state">
								<div id="popup-content" style="padding: 8px 15px;"></div>
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