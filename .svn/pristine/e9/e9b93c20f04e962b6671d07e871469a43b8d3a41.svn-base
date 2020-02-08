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
    <link rel="stylesheet" href="${ctxStatic}/common/index/css/religionIndex.css">
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
  <%--  <script src="${ctxStatic}/common/index/Scripts/js/statIndexZj.js"></script>--%>
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
        .left_part li:nth-of-type(1) .l_part{
            background: url('${ctxStatic}/common/index/images/statIndexZj/jidu.png') no-repeat center;
            background-size: 100% 100%;
        }
        .left_part li:nth-of-type(2) .l_part{
            background: url('${ctxStatic}/common/index/images/statIndexZj/tianzhu.png') no-repeat center;
            background-size: 100% 100%;
        }
        .left_part li:nth-of-type(3) .l_part{
            background: url('${ctxStatic}/common/index/images/statIndexZj/yisilan.png') no-repeat center;
            background-size: 100% 100%;
        }
        .left_part li:nth-of-type(4) .l_part{
            background: url('${ctxStatic}/common/index/images/statIndexZj/fo.png') no-repeat center;
            background-size: 100% 100%;
        }
        .left_part li:nth-of-type(5) .l_part{
            background: url('${ctxStatic}/common/index/images/statIndexZj/dao.png') no-repeat center;
            background-size: 100% 100%;
        }
        a:hover { text-decoration:none}
    </style>
    <script>
        $(function() {
            $('#main').height($(window).height());
            $('.container-center').height($('#main').height() - 70);
            var num = $('#type').val()
            $('#' + num + '').attr('class','select');
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
        $(".left_part").find("li").on("click",function(){
            $(this).addClass("select")

        })


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
                        <span id="productName" style="height:45px;vertical-align:baseline;width:300px;font-size:30px;font-family:MicrosoftYaHei;font-weight:400;color:rgba(255,255,255,1);line-height:27px;text-shadow:0px 3px 7px rgba(0, 0, 0, 0.3);position: relative;top: -70px; left: 40px;background: none;">宗教立体化治安防控</span>
                    </div>
                    <div>
                        <ul class="nav pm-links">
                            <li class="menu">
                                <a href="${ctx}/sys/map/statIndexForZj">数据展示</a>
                            </li>
                            <li class="menu">
                                <a style="margin-left: 20px; " href="${ctx}/sys/map/keyPersonal">重点人员专题</a>
                            </li>
                            <li class="menu">
                                <a class="active" style="margin-left: 20px;" href="${ctx}/sys/map/religionIndex?religiontype=01">宗教专题</a>
                            </li>
                        </ul>
                    </div>
                </div>

            </div>

            <div>
                <h5 class="header-logo"></h5>
            </div>
        </div>
        <div class="row-fluid clearfix" style="width: 100%;height: 100%;padding-top: 90px;">
            <input type="hidden" id="type" name="type" value="${type}">
            <ul id="left_part" class="left_part">
                <li id="01">
                    <a href="${ctx}/sys/map/religionIndex?religiontype=01">
                        <div class="l_part">
                            <p class="l_title">基督教 （<c:if test="${map.get('01')==null}">0</c:if><c:if test="${map.get('01')!=null}">${map.get('01')}</c:if>）</p>
                            <p class="l_title_English">THE CHRISTIAN RELIGION</p>
                        </div>
                    </a>
                </li>
                <li id="05">
                    <a href="${ctx}/sys/map/religionIndex?religiontype=05">
                    <div class="l_part">
                        <p class="l_title">天主教 （<c:if test="${map.get('05')==null}">0</c:if><c:if test="${map.get('05')!=null}">${map.get('05')}</c:if>）</p>
                        <p class="l_title_English">THE CATHOLICISM RELIGION</p>
                    </div>
                    </a>
                </li>
                <li id="02">
                    <a href="${ctx}/sys/map/religionIndex?religiontype=02">
                    <div class="l_part">
                        <p class="l_title">伊斯兰教 （<c:if test="${map.get('02')==null}">0</c:if><c:if test="${map.get('02')!=null}">${map.get('02')}</c:if>）</p>
                        <p class="l_title_English">THE ISLAMISM RELIGION</p>
                    </div>
                    </a>
                </li>
                <li id="03">
                    <a href="${ctx}/sys/map/religionIndex?religiontype=03">
                    <div class="l_part">
                        <p class="l_title">佛教 （<c:if test="${map.get('03')==null}">0</c:if><c:if test="${map.get('03')!=null}">${map.get('03')}</c:if>）</p>
                        <p class="l_title_English">THE BUDDHISM RELIGION</p>
                    </div>
                    </a>
                </li>
                <li id="04">
                    <a href="${ctx}/sys/map/religionIndex?religiontype=04">
                    <div class="l_part" >
                        <p class="l_title">道教 （<c:if test="${map.get('04')==null}">0</c:if><c:if test="${map.get('04')!=null}">${map.get('04')}</c:if>）</p>
                        <p class="l_title_English">THE TAOISM RELIGION</p>
                    </div>
                    </a>
                </li>

            </ul>

            <div id="right_part" class="right_part">
                <div class="r_part">
                    <ul class="clearfix">
                        <c:forEach items="${list}" var="religion">
                        <li>
                            <div class="imgbox"><img src="${religion.ccmBasePlace.placePicture}"></div>
                            <div class="title">${religion.placeName}</div>
                            <div class="text"><div class="line-clamp">${religion.remarks}</div></div>
                        </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>


        </div>
    </div>
</div>
<script>
    $(document).ready(function(){
        var imgBox = $(".right_part li");
        for (var i=0; i<imgBox.length; i++) {
            if(imgBox.eq(i).find("img").attr("src")){

            }else{
                imgBox.eq(i).find("img").remove();
            }
        }
    })

</script>
</body>

</html>