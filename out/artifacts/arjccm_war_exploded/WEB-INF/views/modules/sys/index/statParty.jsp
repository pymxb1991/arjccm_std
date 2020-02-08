<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit|ie-comp|ie-stand" /> 
<script type="text/javascript">
	var ctxStatic = '${ctxStatic}';
</script>
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
	<link rel="stylesheet" href="${ctxStatic}/common/index/css/index.css">
	
<%-- <link rel="stylesheet" href="${ctxStatic}/common/index/css/PopInfo.css"> --%>
<link rel="stylesheet"
	href="${ctxStatic}/common/index/css/statSituationStatistics.css">
	<link rel="stylesheet"
	href="${ctxStatic}/common/index/css/statParty.css">
<script type="text/javascript">
	var ctxStatic = '${ctxStatic}',ctx = '${ctx}';
</script>
<script src="${ctxStatic}/ol/ol.js"></script>
<script src="${ctxStatic}/d3/d3.v4.min.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/scroll.js"></script>
<script src="${ctxStatic}/modules/map/js/mapconfig.js"></script>
<script src="${ctxStatic}/modules/map/js/commonMap.js"></script>
<script src="${ctxStatic}/common/index/Scripts/js/echarts.min.js"></script>
<script src="${ctxStatic}/highcharts/highcharts.js"></script>
<script src="${ctxStatic}/highcharts/highcharts-3d.js"></script>
<script src="${ctxStatic}/icharts/ichart.1.2.1.min.js"></script>
<script src="${ctxStatic}/custom/date/date.js"></script>

<title>智慧党建</title>
<style>
    .alarm-num {
        position: absolute;
        margin: auto;
        left: 0;
        top: 0;
        width: 100px;
        height: 100px;
        text-align: center;
        line-height: 100px;
    }
    .alarm-name {
        text-align: center;
        line-height: 56px;
    }
</style>
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
                <h5 class="header-logo"><b>社会网格化管理信息系统：</b>智慧党建</h5>
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
                                               <!--  <div class="jbox-title-panel" style="height: 25px;">
                                                    <div class="jbox-title">信息</div>
                                                </div> -->
                                                <div class="jbox-state">
                                                    <div id="popup-content" style="padding: 8px 15px;"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-xs-3   height-100 margin0 table-info">
                                        <table style="height: 100%; width: 87%">
                                            <tr>
                                                <td>
                                                    <p class="table-info-title">滨海新区杭州道街道</p>
                                                    <table class="map-table">

                                                        <tr>
                                                            <td align="left" class="table-info-name">兰庭社区党委：</td>
                                                            <td><div class="map-calss1 common-color map-calss"
                                                                     style="color: #fff" id="eachAll1">223</div></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left" class="table-info-name">毓园社区党委：</td>
                                                            <td><div class="map-calss2 common-color map-calss"
                                                                     style="color: #fff" id="eachAll2">301</div></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left" class="table-info-name">米兰社区党委：</td>
                                                            <td><div class="map-calss3 common-color map-calss"
                                                                     style="color: #fff" id="eachAll3">233</div></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left" class="table-info-name">弘泽城社区党委：</td>
                                                            <td><div class="map-calss4 common-color map-calss"
                                                                     style="color: #fff" id="eachAll4">199</div></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left" class="table-info-name">吉宁里社区党委：</td>
                                                            <td><div class="map-calss5 common-color map-calss"
                                                                     style="color: #fff" id="eachAll5">287</div></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left" class="table-info-name">吉庆里社区党委：</td>
                                                            <td><div class="map-calss6 common-color map-calss"
                                                                     style="color: #fff" id="eachAll6">269</div></td>
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
                                <div>三会一课</div>
                            </div>
                            <div class="show height-100 common-center">
                                <div id="SessionsEcharts" class="echarts"></div>

                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6  height-100 margin0">
                        <div class="common common-small-left" style="padding-right: 10px">
                            <div class="common-header">
                                <div>党建宣传</div>
                            </div>
                            <div class="show height-100 common-center" style="padding: 20px 10px 15px 10px;">
                                <div id="PropagandaEcharts" class="echarts"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-4 height-100  margin0">
                <div class="row height30   margin0"
                     style="padding: 10px 0 0 0px; height: 25%">

                    <div class="col-xs-12 height-100  margin0">
                        <div class="common common-small-left">
                            <div class="common-header">
                                <div>党员组成分布</div>
                            </div>
                            <div class="show height-100 common-center" style="padding: 10px 10px 5px 10px;">
                                <div class="row  height-100  margin0">
                                    <div class="col-xs-4 height-100  margin0">
                                        <div class="height-100 common-center"  style="padding:20px 10px 5px 10px;">
                                                <table class="Party-branch" >
                                                    <tr >
                                                        <td>全部</td>
                                                    </tr>
                                                    <tr>
                                                        <td>兰庭社区党委</td>
                                                    </tr>
                                                    <tr class="active">
                                                        <td>毓园社区党委</td>
                                                    </tr>
                                                    <tr>
                                                        <td>米兰社区党委</td>
                                                    </tr>
                                                    <tr>
                                                        <td>弘泽城社区党委</td>
                                                    </tr>
                                                    <tr>
                                                        <td>吉宁里社区党委</td>
                                                    </tr>
                                                    <tr>
                                                        <td>吉庆里社区党委</td>
                                                    </tr>
                                                </table>
                                        </div>
                                    </div>
                                    <div class="col-xs-8 height-100  margin0">
                                        <div class="height-100 common-center"  style="padding: 10px 10px 5px 10px;">
                                            <div id="PartyMemberEcharts" class="echarts"></div>
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
                                <div>党务工作处理</div>
                            </div>
                            <div class="show height-100 common-center">
                                <table style="width: 100%; height: 100%;margin-top: 18px;">
                                    <tbody><tr>
                                        <td>
                                            <div class="row">
                                                <div class="col-xs-4">
                                                    <div class="alarm-rotate" style="position: relative;">
                                                        <div class="alarm-rotate-div">
                                                            <div class="alarm-rotate-img alarm-rotate-img1"></div>
                                                            <div class="alarm-rotate-img alarm-rotate-img2"></div>
                                                            <div class="alarm-num common-color-common common-color2" id="HandlindToday">25</div>
                                                        </div>
                                                        <div class="alarm-name">待处理</div>
                                                    </div>
                                                </div>
                                                <div class="col-xs-4">
                                                    <div class="alarm-rotate " style="position: relative;">
                                                        <div class="alarm-rotate-div">
                                                            <div class="alarm-rotate-img alarm-rotate-img1"></div>
                                                            <div class="alarm-rotate-img alarm-rotate-img2"></div>
                                                            <div class="alarm-num common-color-common" id="TotalToday">0</div>
                                                        </div>
                                                        <div class="alarm-name">处理中</div>
                                                    </div>
                                                </div>
                                                <div class="col-xs-4">
                                                    <div class="alarm-rotate " style="position: relative;">
                                                        <div class="alarm-rotate-div">
                                                            <div class="alarm-rotate-img alarm-rotate-img1"></div>
                                                            <div class="alarm-rotate-img alarm-rotate-img2"></div>
                                                            <div class="alarm-num common-color-common common-color1" id="HandledToday">0</div>
                                                        </div>
                                                        <div class="alarm-name">已处理</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    </tbody></table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row height40   margin0" style="height: 50%">
                    <div class="col-xs-12 height-100  margin0">
                        <div class="common common-small-left">
                            <div class="common-header">
                                <div>交流考核</div>
                            </div>
                            <div class="show height-100 common-center" >
                                <div class="row   margin0 height-100">
                                    <div class="col-xs-5 height-100  margin0">
                                        <div class=" height-100 common-center" >
                                                <div id="BranchEcharts" class="echarts"></div>
                                        </div>
                                    </div>
                                    <div class="col-xs-5 height-100  margin0">
                                        <div class=" height-100 common-center" >
                                            <div id="RankingsEcharts" class="echarts"></div>
                                        </div>
                                    </div>
                                    <div class="col-xs-2 height-100  margin0">
                                        <div class=" height-100 common-center" style="padding: 20px 0px 5px 0px;">
                                            <ul class="fengxian-danwei clearfix">
                                                <li>
                                                    <div class="fengxian-color1">问卷调查</div>
                                                    <div class="fengxian-num fengxian-color1" id="riskRank1">5</div>
                                                </li>
                                                <li>
                                                    <div  class="fengxian-color2">献言建策</div>
                                                    <div class="fengxian-num fengxian-color2"  id="riskRank2">20</div>
                                                </li>
                                                <li>
                                                    <div  class="fengxian-color3">思想汇报</div>
                                                    <div class="fengxian-num fengxian-color3" id="riskRank3">30</div>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div id="popupDialogName"></div>
    </div>
</div>
<script src="${ctxStatic}/common/index/Scripts/js/statParty.js"></script>
</body>
</html>