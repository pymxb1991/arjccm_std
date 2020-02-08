<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="${ctxStatic}/statis/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/statis/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/statis/css/style.css" />
    <!--<link rel="stylesheet" type="text/css" href="${ctxStatic}/statis/css/map.css">-->
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/statis/css/progress.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/statis/css/index.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/common/jeesite.css" />

    <style type="text/css">
        .tdtitle {
            font-weight: bolder;
            width: 100px;
        }

        #contentTable {
            max-width: 500px;
        }
    </style>
    <title></title>
</head>

<body>
<div id="main">
    <div id="ctx" class="hide" ctx="${ctx}"></div>
    <div id="ctxStatic" class="hide" ctx="${ctxStatic}"></div>
    <!-- <div id="header">头部导航条</div> -->
    <div class="container-fluid height90">
        <div class="content row height100" style="height: 104%">
            <div class="col-xs-4 height100" style="margin-right: 0; padding-right: 0; margin-top: 7px;">
                <div class="col-xs-12 height50 Bordereffect">
                    <div class="echarts" id="EchartsOrgNumber"></div>
                </div>

                <div class="col-xs-12 height50 margin0 Bordereffect">
                    <div class="echarts" id="EchartsNation"></div>
                </div>

            </div>

            <div class="col-xs-4 height100" style="margin-top: 7px;">
                <div class="col-xs-12 height50 Bordereffect">
                    <div class="echarts" id="EchartsSex"></div>
                </div>

                <div class="col-xs-12 height50 margin0 Bordereffect">
                    <div class="echarts" id="EchartsItemByScale"></div>
                </div>
            </div>

            <div class="col-xs-4 height100" style="margin-top: 7px;">
                <div class="col-xs-12 height50 margin0 Bordereffect">
                    <div class="echarts" id="EchartsoOrganization"></div>
                </div>

                <div class="col-xs-12 height50 margin0 Bordereffect">
                    <div class="echarts" id="EchartsPolicyType"></div>
                </div>
            </div>

        </div>
    </div>
</div>
<script type="text/javascript" src="${ctxStatic}/statis/js/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/statis/js/ol.js"></script>
<script src="${ctxStatic}/echarts/theme/${not empty cookie.theme.value ? cookie.theme.value : 'black'}.js"></script>
<script>
    ctxStatic='${ctxStatic}'
    ctx = '${ctx}'
</script>
<%-- <script type="text/javascript" src="${ctxStatic}/statis/js/echarts.min.js"></script> --%>
<script type="text/javascript" src="${ctxStatic}/echarts/echarts.common.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/dma/orgAnalysis/js/analysis.js"></script>
</body>

</html>