<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit|ie-comp|ie-stand"/>
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
    <link rel="stylesheet" href="${ctxStatic}/jquery/swiper/swiper-4.3.2.min.css">
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
    <script src="${ctxStatic}/jquery/swiper/swiper-4.3.2.min.js"></script>
    <script src="${ctxStatic}/jquery/swiper/swiper.animate1.0.3.min.js"></script>
    <script src="${ctxStatic}/common/index/Scripts/js/partyConstruction.js"></script>
    <style>
        .results {
            margin-top: 2%;
        }

        .common-pading {
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
            height: 100px;
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

        #second-title{
            display: inline-flex;
            height: 145px;
        }
        .swiper-button-prev{
            width: 30px;
            height: 28px;
            line-height: 37px;
            margin-top: 61px;
            /*margin-right: 2px;*/
            cursor: pointer;
        }
        .swiper-button-next{
            width: 30px;
            height: 28px;
            line-height: 37px;
            margin-top: 61px;
            cursor: pointer;
        }

    </style>
    <script>
        $(function () {
            $("#indexhover,#indexNav").hover(function () {
                $('#indexNav').show();
            }, function () {
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
        <div class="header-nav">
            <ul>
                <li><a href="${ctx}">主面板</a>
                    <!--- <li><a href="${ctx}/index">地图首页</a></li>  临时删除--->
                <li class="active"><a href="${ctx}/sys/map/partyConstruction">党建架构</a></li>
                <li><a href="${ctx}/sys/map/statIndexCom">基本组成</a></li>
            </ul>
        </div>
        <div class="header-nav1">
            <ul>
                <li><a href="${ctx}/sys/map/statPop">关注对象</a></li>
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
                <div class="common-header">
                    <div>各级组织党员分布统计</div>
                </div>
                <div class="common-pading shadow">
                    <div id="RightTypeEcharts" class="echarts"></div>
                </div>
            </div>
            <div class="height33" id="DutyUL">
                <div class="common-header">
                    <div>镇办在职人员及党员统计</div>
                </div>
                <div class="common-pading shadow">
                    <div id="PopKeyEcharts" class="echarts"></div>
                </div>
            </div>
            <div class="height33">
                <!-- <div class="top-header">重点青少年分析</div> -->
                <div class="common-header">
                    <div>村居党员统计</div>
                </div>
                <div class="common-pading shadow" style="height:87%">
                    <div id="PopFlowTable" class="echarts"></div>
                </div>
            </div>
        </div>
        <div class="span6  height100">
            <div class="height66" style="height:100%">
                <div class="common-header">
                    <div>党建架构</div>
                </div>
                <div class="common-pading shadow" style="height:95.5%">
                    <div class="qzf_zzjg ani  flipInY animated" swiper-animate-effect="flipInY"
                         swiper-animate-duration="1s" swiper-animate-delay="0s" swiper-animate-style-cache=" "
                         style="visibility: visible;animation-duration:1s;-webkit-animation-duration:1s;animation-delay:0s;-webkit-animation-delay:0s;">
                        <h1>
                            组织架构
                            <span id="first-count"
                                  style="position: absolute;top: 26px; left: 93px; font-weight: normal; font-size: 14px;">...</span>
                        </h1>
                        <div class="qzf_zzjg_list1">
                            <div class="qzf_zzjg_list ">
                                <ol id="first-node" class="common_tab_tit clearfix">
                                    <li class="first-item ">
                                        <a href="javascript:void(0)">...</a>
                                    </li>

                                </ol>
                                <!-- 如果需要导航按钮 -->

                                <span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span></div>
                        </div>
                    </div>
                    <div  class="zb_cj_zzjg ani">
                        <h1>镇办组织架构情况
                            <span id="second-count"
                                  style="position: absolute; top: 26px;left:92px; font-weight: normal; font-size: 14px;">(...)</span>
                        </h1>
                        <div id="second-title" class="common_tab">
                            <div id="swiper-button-prev" class="swiper-button-prev" tabindex="0" role="button" aria-label="Previous slide"
                                 aria-disabled="true">
                                <i  style="font-size: 30px" class="iconfont">&#xe68e;</i>
                            </div>
                            <div style="width: 870px;overflow: hidden">
                            <ol id="second-node" class=" common_tab_tit clearfix">
                                <li>
                                    <a href="javascript:void(0)">... </a>
                                </li>
                            </ol>
                            </div>

                            <div id="swiper-button-next" class="swiper-button-next" tabindex="0" role="button" aria-label="Next slide"
                            aria-disabled="true">
                               <i style="font-size: 30px" class="iconfont">&#xe690;</i>
                            </div>

                        </div>
                        <div style="margin-top: 0px" class="common_tab_cont ">
                            <h1 style="margin-top: 0">村居组织架构情况
                                <span id="second-count1" style="position: absolute; top: 355px;left:340px; font-weight: normal; font-size: 14px;">(...)</span>
                            </h1>
                            <div class="cj_list swiper-container-horizontal" style="display: inline-flex;width: 100%">
                                <div id="third-button-prev" class="swiper-button-prev" tabindex="0" role="button" aria-label="Previous slide"
                                     aria-disabled="true">
                                    <i  style="font-size: 30px" class="iconfont">&#xe68e;</i>
                                </div>
                                <div style="width: 872px;overflow: hidden">
                                    <ol id="third-node" class=" common_tab_tit clearfix">
                                        <li>
                                            <a href="javascript:void(0)">... </a>
                                        </li>
                                        <li>
                                            <a href="javascript:void(0)">... </a>
                                        </li>
                                        <li>
                                            <a href="javascript:void(0)">... </a>
                                        </li>
                                    </ol>
                                </div>

                                <div id="third-button-next" class="swiper-button-next" tabindex="0" role="button" aria-label="Next slide"
                                     aria-disabled="true">
                                    <i style="font-size: 30px" class="iconfont">&#xe690;</i>
                                </div>

                                <span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="span3  height100">
            <div class="height33">

                <div class="common-header">
                    <div>五员式管理</div>
                </div>
                <div class="common-pading shadow">
                    <div id="PopsNumEcharts" class="echarts"></div>
                </div>
            </div>
            <div class="height33">

                <div class="common-header">
                    <div>七个一工作法</div>
                </div>
                <div class="common-pading shadow">
                    <!-- <div id="HealthyEcharts" class="echarts"></div> -->
                    <div id="p1_r_2">
                        <ul>
                            <li>
                                <h3>民意汇集</h3>
                                <div class="m_line">
                                    <a href="javascript:void(0)" title="2018-08-01"></a>
                                    <a href="javascript:void(0)" title="2018-08-02"></a>
                                    <a href="javascript:void(0)" title="2018-08-03"></a>
                                    <a href="javascript:void(0)" title="2018-08-04"></a>
                                    <a href="javascript:void(0)" title="2018-08-05"></a>
                                    <a href="javascript:void(0)" title="2018-08-06"></a>
                                    <a href="javascript:void(0)" title="2018-08-07"></a>
                                    <a href="javascript:void(0)" title="2018-08-08"></a>
                                    <a href="javascript:void(0)" title="2018-08-09"></a>
                                    <a href="javascript:void(0)" title="2018-08-10"></a>
                                    <a href="javascript:void(0)" title="2018-08-11"></a>
                                    <a href="javascript:void(0)" title="2018-08-12"></a>
                                    <a href="javascript:void(0)" title="2018-08-13"></a>
                                    <a href="javascript:void(0)" title="2018-08-14"></a>
                                    <a href="javascript:void(0)" title="2018-08-15"></a>
                                    <a href="javascript:void(0)" title="2018-08-16"></a>
                                    <a href="javascript:void(0)" title="2018-08-17"></a>
                                    <a href="javascript:void(0)" title="2018-08-18"></a>
                                    <a href="javascript:void(0)" title="2018-08-19"></a>
                                    <a href="javascript:void(0)" title="2018-08-20"></a>
                                    <a href="javascript:void(0)" title="2018-08-21"></a>
                                    <a href="javascript:void(0)" title="2018-08-22"></a>
                                    <a href="javascript:void(0)" title="2018-08-23"></a>
                                    <a href="javascript:void(0)" title="2018-08-24"></a>
                                    <a href="javascript:void(0)" title="2018-08-25"></a>
                                    <a href="javascript:void(0)" title="2018-08-26"></a>
                                    <a href="javascript:void(0)" title="2018-08-27"></a>
                                    <a href="javascript:void(0)" title="2018-08-28"></a>
                                    <a href="javascript:void(0)" title="2018-08-29"></a>
                                    <a href="javascript:void(0)" title="2018-08-30"></a>
                                </div>
                                <span>(天)</span>
                            </li>
                            <li>
                                <h3>村民议事会</h3>
                                <div class="m_line">
                                    <a href="javascript:void(0)" title=""></a>
                                    <a href="javascript:void(0)"></a>
                                    <a href="javascript:void(0)"></a>
                                    <a href="javascript:void(0)" title=""></a>
                                </div>
                                <span>(周)</span>
                            </li>
                            <li>
                                <h3>公益活动</h3>
                                <div class="m_line">
                                    <a href="javascript:void(0)" title=""></a>
                                    <a href="javascript:void(0)" title=""></a>
                                    <a href="javascript:void(0)" title=""></a>
                                </div>
                                <span>(旬)</span>
                            </li>
                            <li>
                                <h3>理财会</h3>
                                <div class="m_line">
                                    <a href="javascript:void(0)" title=""></a>
                                    <a href="javascript:void(0)" title=""></a>
                                </div>
                                <span>(月)</span>
                            </li>
                            <li>
                                <h3>绩效考核</h3>
                                <div class="y_line">
                                    <a href="javascript:void(0)" title="一季度绩效考核"></a>
                                    <a href="javascript:void(0)" title="二季度绩效考核"></a>
                                    <a href="javascript:void(0)" title="三季度绩效考核"></a>
                                    <a href="javascript:void(0)" title="四季度绩效考核"></a>
                                </div>
                                <span>(季度)</span>
                            </li>
                            <li>
                                <h3>谈心会</h3>
                                <div class="y_line">
                                    <a href="javascript:void(0)" title=""></a>
                                    <a href="javascript:void(0)" title=""></a>
                                    <a href="javascript:void(0)" title=""></a>
                                </div>
                                <span>(半年)</span>
                            </li>
                            <li>
                                <h3>述职述廉</h3>
                                <div class="y_line">
                                    <a href="javascript:void(0)" title=""></a>
                                    <a href="javascript:void(0)" title=""></a>
                                </div>
                                <span>(年)</span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="height33">

                <div class="common-header">
                    <div>先进模范展示</div>
                </div>
                <div class="common-pading shadow" style="height:87%">
                    <div id="p1_r_3" class="shengji">
                        <div id="moral" class="swiper-wrapper">
                            <div class="swiper-slide">
                                <dl>
                                    <dt>
                                        <img src="${ctxStatic}/images/mofan/方鹏.jpg"/>
                                        <h1>敬业奉献道德模范</h1>
                                        <h3>方鹏</h3>
                                    </dt>
                                    <dd>
                                        <p>
                                            方鹏是开发区西航办事处派出所所长，作为一名人民警察，从警14年来，13年在外过春节，在党和人民群众需要的时候，就是我挺身而出的时候。作为一名年轻的共产党员，他爱岗敬业，积极奉献，将青春和热血投入到所爱的公安工作中，是贵州省第二届全省敬业奉献道德模范</p>
                                    </dd>
                                </dl>
                            </div>

                        </div>
                        <!-- 如果需要导航按钮 -->
                        <div class="swiper-button-prev"></div>
                        <div class="swiper-button-next"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>