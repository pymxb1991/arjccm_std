/**
 * Created by oHa on 2017/12/28.
 */
/*Object.assign() 之 IE 兼容 （TypeError: 对象不支持“assign”属性或方法）*/
if (typeof Object.assign != 'function') {
    Object.assign = function(target) {
        'use strict';
        if (target == null) {
            throw new TypeError('Cannot convert undefined or null to object');
        }

        target = Object(target);
        for (var index = 1; index < arguments.length; index++) {
            var source = arguments[index];
            if (source != null) {
                for (var key in source) {
                    if (Object.prototype.hasOwnProperty.call(source, key)) {
                        target[key] = source[key];
                    }
                }
            }
        }
        return target;
    };
}

var windowsHeight, _fontSize = 14, _fontSize1 = 26, _fontSize2 = 24, _fontSize3 = 20, breakData = 8,
    legendTop = '30%', radiusData = ['40%', '65%'], lengthECharts = 30, mapHeigth = '90%', zoom = 14.75,
    centerCoordinate = [117.663920, 39.035650], echarts3X = "48%", echarts3y = "40%", ItemGap = 20;

var streetFlag, vccmorgFlag, communityFlag, gridFlag, buildFlag, eventFlag, partsFlag, landsFlag, videoFlag,
    broadcastFlag, policeroomFlag, workstationFlag, schoolPlaceFlag, keyPlaceFlag, keyPersonFlag, rentingPersonFlag,
    publicPlaceFlag, popLocationFlag, SetTopBoxFlag;
streetFlag = true;
communityFlag = false;
gridFlag = false;
// 基础颜色表
var color = ['#CA410B', '#12B3B6', '#A269C6', '#883CBD', '#401EC3', '#4366D9',
    '#D26320', '#06AC7D', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];
$(function () {
    //年月日
    $('#calendarYear').html(year)
    $('#calendarMonth').html(month)
    $('#calendarDay').html(day)

    var context = $(".context").attr("content");

    // var test =
    //     document.getElementById('iframe的id').contentWindow.document.getElementById('iframe里面要获取的元素');
    // $("#jbox-iframe").contents().find("body").addClass("aa");
    // console.info("abc",document.getElementById('jbox-iframe'))
    // document.getElementById('jbox-iframe').contentWindow.document.body;
    // document.getElementById('jbox-iframe').contentWindow.document.body.style.backgroundColor="#f00";
    // console.info("jbox-iframe", $("#jbox-iframe"));
    // console.info("contents", $("#jbox-iframe").contents().context.body);
    // console.info("body", $("#jbox-iframe").contents().find("body"));

    $(".menu a").click(function () {
        $(".menu a").removeClass("active");
        $(this).addClass("active");
    })
    showWangGeGuanLi();

    function isbegin() {
        $("#wanggeguanli").attr("disabled", true).css("pointer-events", "none");
        $("#guanzhuduixiang").attr("disabled", true).css("pointer-events", "none");
        $("#zhiantaishi").attr("disabled", true).css("pointer-events", "none");
        $("#anquanshengchan").attr("disabled", true).css("pointer-events", "none");
        $("#xuelianggongcheng").attr("disabled", true).css("pointer-events", "none");
        setTimeout(function () {
            $("#wanggeguanli").attr("disabled", false).css("pointer-events", "auto");
            $("#guanzhuduixiang").attr("disabled", false).css("pointer-events", "auto");
            $("#zhiantaishi").attr("disabled", false).css("pointer-events", "auto");
            $("#anquanshengchan").attr("disabled", false).css("pointer-events", "auto");
            $("#xuelianggongcheng").attr("disabled", false).css("pointer-events", "auto");
        }, 1000);
    }

    $("#wanggeguanli").click(function () {
        isbegin();
        $('.tubiao').show();
        $('#leftCol').animate({width: 'toggle'});
        $('#rightCol').animate({width: 'toggle'});
        showWangGeGuanLi();
        $('#leftCol').animate({width: 'toggle'});
        $('#rightCol').animate({width: 'toggle'});
    });

    $("#guanzhuduixiang").click(function () {
        isbegin();
        $('.tubiao').hide();
        $('#leftCol').animate({width: 'toggle'});
        $('#rightCol').animate({width: 'toggle'});
        showGuanZhuDuiXiang();
        $('#leftCol').animate({width: 'toggle'});
        $('#rightCol').animate({width: 'toggle'});
    });
    $("#zhiantaishi").click(function () {
        isbegin();
        $('.tubiao').hide();
        $('#leftCol').animate({width: 'toggle'});
        $('#rightCol').animate({width: 'toggle'});
        showZhiAnTaiShi();
        $('#leftCol').animate({width: 'toggle'});
        $('#rightCol').animate({width: 'toggle'});
    });
    $("#anquanshengchan").click(function () {
        isbegin();
        $('.tubiao').hide();
        $('#leftCol').animate({width: 'toggle'});
        $('#rightCol').animate({width: 'toggle'});
        showAnQuanShengChan();
        $('#leftCol').animate({width: 'toggle'});
        $('#rightCol').animate({width: 'toggle'});
    });

    $("#xuelianggongcheng").click(function () {
        isbegin();
        $('.tubiao').hide();
        $('#leftCol').animate({width: 'toggle'});
        $('#rightCol').animate({width: 'toggle'});
        showXueLiangGongCheng();
        $('#leftCol').animate({width: 'toggle'});
        $('#rightCol').animate({width: 'toggle'});
    });

    //网格管理
    function showWangGeGuanLi() {
        hideDivChat(0);
        var divWidth = $("#leftContent1Body").width();
        var divHeight = $("#leftContent1Body").outerHeight(true);
        $('#echLeftContent1').css("width", divWidth + "px");
        $('#echLeftContent1').css("height", divHeight + "px");
        var divWidth = $("#leftContent2Body").width();
        var divHeight = $("#leftContent2Body").outerHeight(true);
        $('#echleftContent2').css("width", divWidth + "px");
        $('#echleftContent2').css("height", divHeight + "px");
        var divWidth = $("#leftContent3Body").width();
        var divHeight = $("#leftContent3Body").outerHeight(true);
        $('#echLeftContent3').css("width", divWidth + "px");
        $('#echLeftContent3').css("height", divHeight + "px");
        var divWidth = $("#rightContent3Body").width();
        var divHeight = $("#rightContent3Body").outerHeight(true);
        $('#echRightContent3').css("width", divWidth + "px");
        $('#echRightContent3').css("height", divHeight + "px");
        var divWidth = $("#rightContent1Body").width();
        var divHeight = $("#rightContent1Body").outerHeight(true);
        $('#echRightContent1').css("width", divWidth + "px");
        $('#echRightContent1').css("height", divHeight + "px");
        var divWidth = $("#rightContent2Body").width();
        var divHeight = $("#rightContent2Body").outerHeight(true);
        $('#echRightContent2').css("width", divWidth + "px");
        $('#echRightContent2').css("height", divHeight + "px");
        resizeChat();

        //基础数据
        var noCache = new Date();
        getPeopleData(noCache);//基础数据
        getPeopleCurrent(noCache);//人口趋势图
        getCitycomponents(noCache);//城市部件状态
        getHouseData(noCache);//基本信息
        getWorkForce(noCache);//工作力量
        getKeyenterprisesshow(noCache);//重点企业
    }

    //关注对象
    function showGuanZhuDuiXiang() {
        hideDivChat(1);
        var divWidth1 = $("#leftContent1Body").width();
        var divHeight1 = $("#leftContent1Body").outerHeight(true);
        $('#echLeftContent1').css("width", divWidth1 + "px");
        $('#echLeftContent1').css("height", divHeight1 + "px");

        var divWidth2 = $("#leftContent2Body").width();
        var divHeight2 = $("#leftContent2Body").outerHeight(true);
        $('#echleftContent2').css("width", divWidth2 + "px");
        $('#echleftContent2').css("height", divHeight2 + "px");

        var divWidth3 = $("#rightContent3Body").width();
        var divHeight3 = $("#rightContent3Body").outerHeight(true);
        $('#echRightContent3').css("width", divWidth3 + "px");
        $('#echRightContent3').css("height", divHeight3 + "px");

        //重点青少年帮扶方式
        $.getJSON(context + "/report/ccmPeopleAmount/getNumKymByAssistMethod", function (
            data) {
            getHelpingWay(data);
        });
        $.getJSON(context + "/report/ccmPeopleAmount/getnumPopFollowPopQL", function (
            data) {
            // 接收参数
            getPeopleCount(data.num);
        });
        $.getJSON(context + "/report/ccmPeopleAmount/getSpecialPopDataQL", function (
            data) {
            // 接收参数
            SpecialPopByType("echRightContent3", data);
        });
        $.getJSON(context + "/report/ccmPeopleAmount/getnumPopFlowTableQL", function (
            data) {
            // 接收参数
            KeyYouthByType("echleftContent2", data); //重点青少年分类
            getTeenagersAnalysis(data); //重点青少年分析
        });
    }

    $.getJSON(context + "/org/ccmOrgNpse/getSafePubData", function (
        data) {
        // 接收参数
        var riskRank1 = "0";	//高风险单位;
        var riskRank2 = "0";	//较大风险单位
        var riskRank3 = "0";	//一般风险单位
        var riskRank4 = "0";	//低风险单位
        for (var one in data) {
            if (data[one]["type"] == "高风险单位") {
                riskRank1 = data[one]["value"];
            }
            if (data[one]["type"] == "较大风险单位") {
                riskRank2 = data[one]["value"];
            }
            if (data[one]["type"] == "一般风险单位") {
                riskRank3 = data[one]["value"];
            }
            if (data[one]["type"] == "低风险单位") {
                riskRank4 = data[one]["value"];
            }
        }
        $("#riskRank1").html(riskRank1);//高风险单位
        $("#riskRank2").html(riskRank2);//较大风险单位
        $("#riskRank3").html(riskRank3);//一般风险单位
        $("#riskRank4").html(riskRank4);//低风险单位
    });

    //治安态势
    function showZhiAnTaiShi() {
        hideDivChat(2);

        getTodayEvent();//今日事件

        //showEventProcessing();//案事件处理统计
        showSafetyAccident();//安全事故分析
        showEventDeal();//事件处理情况
        getAreaEch();//事件区域分布
    }

    //安全生产
    function showAnQuanShengChan() {
        hideDivChat(3);
        var divWidth = $("#leftContent1Body").width();
        var divHeight = $("#leftContent1Body").outerHeight(true);
        $('#echLeftContent1').css("width", (divWidth * 0.4) + "px");
        $('#echLeftContent1').css("height", divHeight + "px");

        var divWidth = $("#leftContent2Body").width();
        var divHeight = $("#leftContent2Body").outerHeight(true);
        $('#echleftContent2').css("width", divWidth + "px");
        $('#echleftContent2').css("height", divHeight + "px");
        var divWidth = $("#leftContent3Body").width();
        var divHeight = $("#leftContent3Body").outerHeight(true);
        $('#echLeftContent3').css("width", divWidth + "px");
        $('#echLeftContent3').css("height", divHeight + "px");
        getKeyEnterprises();//安全生产重点企业
        var noCache = new Date();

        //安全生产防范检查
        $.getJSON(context + "/know/ccmKnowInspect/getTypeSafeDataeCharts", {"noCache": noCache}, function (
            data) {
            getPreventive(data);
        });

        $.getJSON(context + "/event/ccmEventIncident/getSafeDisDataecharts?eventTypes=01&eventTypes=03", {"noCache": noCache}, function (
            data) {
            getDistribution(data);
        });
        // getDistribution();//安全事故分布

        getAnalysis();//安全事故分析

        getEnterprise();//重点企业分布
    }

    //雪亮工程
    function showXueLiangGongCheng() {
        hideDivChat(4);
        var divWidth = $("#leftContent1Body").width();
        var divHeight = $("#leftContent1Body").outerHeight(true);
        $('#echLeftContent1').css("width", divWidth + "px");
        $('#echLeftContent1').css("height", divHeight + "px");
        var divWidth = $("#rightContent3Body").width();
        var divHeight = $("#rightContent3Body").outerHeight(true);
        $('#echRightContent3').css("width", divWidth + "px");
        $('#echRightContent3').css("height", divHeight + "px");
        resizeChat();
        var noCache = new Date();
        getVideoControl(noCache);//点位建设趋势
        getAreaDistribution(noCache); //视频区域分布
        $.getJSON(context + "/ccmsys/ccmDevice/selectByInstallType", {"noCache": noCache}, function (
            data) {
            var coutnum = 0;
            for (var i = 0; i < data.length; i++) {
                coutnum += Number(data[i].value);
            }
            $('#zongshu').text(coutnum);
            $('#jiguan').text(data[0] != undefined ? data[0].value : 0);
            $('#zhugandao').text(data[1] != undefined ? data[1].value : 0);
            $('#xuexiao').text(data[2] != undefined ? data[2].value : 0);
            $('#xiaoqu').text(data[3] != undefined ? data[3].value : 0);
            $('#ditie').text(data[4] != undefined ? data[4].value : 0);
            $('#shangchang').text(data[5] != undefined ? data[5].value : 0);
            $('#jiayouzhan').text(data[6] != undefined ? data[6].value : 0);
            $('#qita').text(data[7] != undefined ? data[7].value : 0);
        });


        $.getJSON(context + "/ccmsys/ccmDevice/selectByType", {"noCache": noCache}, function (
            data) {
            $('#jiankong').text(data[0] != undefined ? data[0].value : 0);
            $('#kakou').text(data[1] != undefined ? data[1].value : 0);
            $('#renlian').text(data[2] != undefined ? data[2].value : 0);
        });

    }

    function hideDivChat(bool) {
        if (bool == 0) {
            $("#leftContent1").find('.contentTitle').text('基础数据');
            $("#leftContent2").find('.contentTitle').text('人口趋势图');
            $("#leftContent3").find('.contentTitle').text('城市部件状态');
            $("#leftContent0").hide();
            $("#leftContent1").show();
            $("#leftContent1Body").hide();
            $("#echLeftContent1").hide();
            $("#leftContent1BodyWg").show();
            $("#test").hide();
            $("#leftContent2").show();
            $("#leftContent3").show();
            $("#leftContent4").hide();
            $("#rightContent1").find('.contentTitle').text('基本信息');
            $("#rightContent2").find('.contentTitle').text('工作力量');
            $("#rightContent3").find('.contentTitle').text('重点企业');
            $("#rightContent0").hide();
            $("#rightContent1").show();
            $("#rightContent2").show();
            $("#rightContent3").show();
            $("#eventScale").hide();
            $("#videoDiv").hide();
            $("#rightContent1Body").show();
            $("#rightContent2Body").show();
            $("#videoTypeDiv").hide();
            $("#safeKeyDiv").hide();
        } else if (bool == 1) {
            $("#leftContent1Body").show();
            $("#leftContent1BodyWg").hide();
            $("#leftContent1").find('.contentTitle').text('重点青少年帮扶方式');
            $("#echLeftContent1").show();
            $("#test").hide();
            $("#leftContent2").find('.contentTitle').text('重点青少年分类统计');
            $("#leftContent3").find('.contentTitle').text('重点青少年分析');
            $("#leftContent0").hide();
            $("#leftContent1").show();
            $("#leftContent2").show();
            $("#leftContent3").show();
            $("#leftContent4").hide();
            $("#rightContent0").find('.contentTitle').text('重点关注人群');
            $("#rightContent3").find('.contentTitle').text('特殊人群分析');
            $("#rightContent0").show();
            $("#rightContent0Body").hide();
            $("#videoCountDivPop").show();
            $("#rightContent1").hide();
            $("#rightContent2").hide();
            $("#rightContent3").show();
            $("#safeKeyDiv").hide();
        } else if (bool == 2) {

            $.ajax({
                type: "get",
                url: ctx + "/report/ccmIncidentBegin/findEventGuiMoData",
                async: false,
                success: function (data) {
                    $('#eventSpan').text(data[3].value);
                    $('#eventSpan1').text(data[0].value);
                    $('#eventSpan2').text(data[1].value);
                    $('#eventSpan3').text(data[2].value);
                }

            })


            $.ajax({
                type: "get",
                url: ctx + "/event/ccmEventCasedeal/getNumByHandelStatus",
                async: false,
                success: function (data) {
                    $('#tobe').text(data.tobe);
                    $('#ing').text(data.ing);
                    $('#done').text(data.done);
                }

            })

            $("#leftContent1Body").show();
            $("#leftContent1BodyWg").hide();

            $("#leftContent1").find('.contentTitle').text('今日事件');
            $("#leftContent4").find('.contentTitle').text('事件分析');
            $("#leftContent0").hide();
            $("#leftContent1").show();
            $("#echLeftContent1").hide();
            $("#test").show();
            $("#leftContent2").hide();
            $("#leftContent3").hide();
            $("#leftContent4").show();
            $("#rightContent1").find('.contentTitle').text('事件纠纷统计');
            $("#rightContent2").find('.contentTitle').text('事件处理情况');
            $("#rightContent3").find('.contentTitle').text('事件发生区域TOP5');
            $("#rightContent0").hide();
            $("#rightContent1").show();
            $("#rightContent2").show();
            $("#rightContent3").show();
            $("#eventScale").show();
            $("#videoDiv").hide();
            $("#rightContent1Body").hide();
            $("#rightContent2Body").show();
            $("#videoTypeDiv").hide();
            $("#safeKeyDiv").hide();
        } else if (bool == 3) {
            $("#leftContent1Body").show();
            $("#leftContent1BodyWg").hide();

            $("#leftContent1").find('.contentTitle').text('安全生产重点企业');
            $("#echLeftContent1").show();
            $("#test").hide();
            $("#leftContent2").find('.contentTitle').text('安全生产防范检查');
            $("#leftContent3").find('.contentTitle').text('安全事故分布');
            $("#leftContent0").hide();
            $("#leftContent1").show();
            $("#leftContent2").show();
            $("#leftContent3").show();
            $("#leftContent4").hide();
            $("#rightContent0").find('.contentTitle').text('安全事故分析');
            $("#rightContent3").find('.contentTitle').text('重点企业分布');
            $("#rightContent0").show();
            $("#rightContent0Body").show();
            $("#videoCountDivPop").hide();
            $("#rightContent1").hide();
            $("#rightContent2").hide();
            $("#rightContent3").show();
            $("#safeKeyDiv").show();
        } else if (bool == 4) {
            $("#leftContent1Body").show();
            $("#leftContent1BodyWg").hide();

            $("#leftContent0").find('.contentTitle').text('视频分类');
            $("#leftContent1").find('.contentTitle').text('点位建设趋势');
            $("#echLeftContent1").show();
            $("#test").hide();
            $("#leftContent0").show();
            $("#leftContent1").show();
            $("#leftContent2").hide();
            $("#leftContent3").hide();
            $("#leftContent4").hide();
            $("#rightContent1").find('.contentTitle').text('实时监控');
            $("#rightContent2").find('.contentTitle').text('监控设备类型');
            $("#rightContent3").find('.contentTitle').text('视频区域分布');
            $("#rightContent0").hide();
            $("#rightContent1").show();
            $("#rightContent2").show();
            $("#rightContent3").show();
            $("#eventScale").hide();
            $("#videoDiv").show();
            $("#rightContent1Body").hide();
            $("#leftContent0Body").hide();
            $("#videoCountDiv").show();
            $("#rightContent2Body").hide();
            $("#videoTypeDiv").show();
            $("#safeKeyDiv").hide();
        } else {
            $("#leftContent1BodyWg").hide();
            $("#leftContent1").find('.contentTitle').text('');
            $("#leftContent2").find('.contentTitle').text('');
            $("#leftContent3").find('.contentTitle').text('');
            $("#leftContent4").find('.contentTitle').text('');
            $("#leftContent0").show();
            $("#leftContent1").show();
            $("#leftContent2").show();
            $("#leftContent3").show();
            $("#leftContent4").show();
            $("#rightContent0").find('.contentTitle').text('');
            $("#rightContent1").find('.contentTitle').text('');
            $("#rightContent2").find('.contentTitle').text('');
            $("#rightContent3").find('.contentTitle').text('');
            $("#rightContent0").show();
            $("#rightContent1").show();
            $("#rightContent2").show();
            $("#rightContent3").show();
            $("#eventScale").hide();
            $("#videoDiv").hide();
            $("#rightContent1Body").show();
            $("#leftContent0Body").show();
            $("#videoCountDiv").hide();
            $("#rightContent2Body").show();
            $("#videoTypeDiv").hide();
            $("#safeKeyDiv").hide();
        }
    }


    /********************地图***********************/
    //地图
    map();
    //街道画线
    // lightLine();
    //初始化网格
    initorgAreaMap();

    $('#main').height($(window).height());
    $('.height100').height($('#main').height() - 70);
    windowsHeight = $(window).width();
    if (windowsHeight >= 1680 && windowsHeight < 1920) {
        _fontSize = 14;
        radiusData = ['40%', '60%'];
        echarts3X = "48%";
        echarts3y = "40%";
        zoom = 14.7;
        centerCoordinate = [117.662920, 39.035650]
        lengthECharts = 30;
        _fontSize1 = 26;
        breakData = 8;
        mapHeigth = '90%'
    } else if (windowsHeight >= 1600 && windowsHeight < 1680) {
        _fontSize = 12;
        radiusData = ['40%', '60%'];
        echarts3X = "48%";
        echarts3y = "35%";
        zoom = 14.4;
        ItemGap = 0;
        centerCoordinate = [117.662920, 39.035650]
        lengthECharts = 30;
        _fontSize1 = 26;
        breakData = 8;
        mapHeigth = '90%'
    } else if (windowsHeight >= 1440 && windowsHeight < 1660) {
        _fontSize = 12;
        radiusData = ['40%', '58%'];
        zoom = 14.4;
        centerCoordinate = [117.662920, 39.035650]
        lengthECharts = 30;
        _fontSize1 = 26;
        ItemGap = 0;
        _fontSize2 = 18,
            _fontSize3 = 16,
            breakData = 8;
        mapHeigth = '90%'
    } else if (windowsHeight < 1440) {
        _fontSize = 12;
        radiusData = ['40%', '58%'];
        zoom = 14.1;
        centerCoordinate = [117.662920, 39.035650]
        lengthECharts = 30;
        _fontSize1 = 26;
        ItemGap = -10;
        _fontSize2 = 16,
            _fontSize3 = 14,
            breakData = 8;
        mapHeigth = '90%'
    }

    // 地图
    function map() {
        // var centerCoordinate = [113.48940277,34.526999206];    //新密中心点
        //  var centerCoordinate = [109.40, 18.53];    //三亚中心点centerCoordinateSituation

        // 地图默认数据设置
        var defaultPrams = {
            id: 'mapMask',
            centerCoordinate: centerCoordinateSituation,
            zoom: zoomPop,
            maxZoom: maxZoom,
            minZoom: minZoom,
            baseUrl: baseUrlT,
            zoomShowOrHide: false,// 缩小放大
            overviewMap: false,
            selectPointerFlag: false
            // 鹰眼图
        }

        viewSituationFun(centerCoordinateSituation, zoomPop)  //view 初始化
        Map = new ArjMap.Map(defaultPrams);
        // 加载地图
        Map.drawMapSituationKeShiHua();
        Pubmap = Map.map;
        /*	var zuobiao=[ 117.655920, 39.038050 ];
            var zuobiao1=[ 117.653920, 39.035050 ];
            var zuobiao2=[ 117.663920, 39.042050 ];
            Map.postcomposeOlIndex('紧急',zuobiao,"123",'0')
            Map.postcomposeOlIndex('紧急',zuobiao1,"1234",'0')
            Map.postcomposeOlIndex('紧急',zuobiao2,"12345",'0')*/

        // 监听地图层级变化
        Pubmap.getView().on('change:resolution', checkZoom);// checkZoom为调用的函数
        function checkZoom(map_level) {
            var zoom = Pubmap.getView().getZoom();
            if (Pubmap.getView().getZoom() <= 10) {
                Map.layersIsShow('communitys', false);
                Map.layersIsShow('streets', true);
                Map.layersIsShow('grids', false);
                communityFlag = false;
                streetFlag = true;
                gridFlag = false;
            } else if (Pubmap.getView().getZoom() <= 13
                && Pubmap.getView().getZoom() > 10) {
                Map.layersIsShow('communitys', true);
                Map.layersIsShow('streets', false);
                Map.layersIsShow('grids', false);
                communityFlag = true;
                streetFlag = false;
                gridFlag = false;
            } else if (Pubmap.getView().getZoom() > 13
                && Pubmap.getView().getZoom() <= 16) {
                Map.layersIsShow('communitys', false);
                Map.layersIsShow('streets', false);
                Map.layersIsShow('grids', true);
                communityFlag = false;
                streetFlag = false;
                gridFlag = true;
            } else if (Pubmap.getView().getZoom() > 16) {
                Map.layersIsShow('communitys', false);
                Map.layersIsShow('streets', false);
                Map.layersIsShow('grids', false);
                communityFlag = false;
                streetFlag = false;
                gridFlag = false;
            }
        }
    }


    // EchartType 转换成 Echart所需要的类型
    $.ToConvertA = function (object) {
        var ajaxData = new Array();
        for (var one in object) {
            ajaxData.push({
                "name": object[one]["type"],
                "value": Number(object[one]["value"])
            });
        }
        return ajaxData;
    }

    Map.selectQueryInit(); //框选查询
    document.onkeydown = function (e) {
        //屏蔽F11 改为调用全屏方法
        if (e && e.keyCode == 122) {
            Map.fullScreen();
            event.returnValue = false;
        }
    }

    $('#main').height($(window).height());
    $('.height100').height($('#main').height() - 70);
    windowsHeight = $(window).width();
    if (windowsHeight >= 1680 && windowsHeight < 1920) {
        _fontSize = 14;
        radiusData = ['40%', '60%'];
        echarts3X = "48%";
        echarts3y = "40%";
        zoom = 14.7;
        centerCoordinate = [117.662920, 39.035650]
        lengthECharts = 30;
        _fontSize1 = 26;
        breakData = 8;
        mapHeigth = '90%'
    } else if (windowsHeight >= 1600 && windowsHeight < 1680) {
        _fontSize = 12;
        radiusData = ['40%', '60%'];
        echarts3X = "48%";
        echarts3y = "35%";
        zoom = 14.4;
        ItemGap = 0;
        centerCoordinate = [117.662920, 39.035650]
        lengthECharts = 30;
        _fontSize1 = 26;
        breakData = 8;
        mapHeigth = '90%'
    } else if (windowsHeight >= 1440 && windowsHeight < 1660) {
        _fontSize = 12;
        radiusData = ['40%', '58%'];
        zoom = 14.4;
        centerCoordinate = [117.662920, 39.035650]
        lengthECharts = 30;
        _fontSize1 = 26;
        ItemGap = 0;
        _fontSize2 = 18,
            _fontSize3 = 16,
            breakData = 8;
        mapHeigth = '90%'
    } else if (windowsHeight < 1440) {
        _fontSize = 12;
        radiusData = ['40%', '58%'];
        zoom = 14.1;
        centerCoordinate = [117.662920, 39.035650]
        lengthECharts = 30;
        _fontSize1 = 26;
        ItemGap = -10;
        _fontSize2 = 16,
            _fontSize3 = 14,
            breakData = 8;
        mapHeigth = '90%'
    }
})

//框选查询
function boxSelectionDevice(data, type) {
    var url = "";
    if (type == "circle") {
        url = ctx + '/sys/map/showSelect?x=' + data.centerX + '&y=' + data.centerY + '&radius=' + data.radius;
    } else {
        url = ctx + '/sys/map/showSelect?points1=' + data.xyList;
    }
    $.getJSON(url, function (val) {
        var html = "";
        html += '<div class="layer-common" style="width: 100%;height: 100%; position: relative;padding: 14px 0 0 0;">'
        html += '<div class="layer-common-header" style="top: 1px; left: 36px;display: inline-block; padding: 5px 30px; border: 1px solid #0343a3; transform: skew(-20deg); background: #0343a3;color: #fff; font-weight: bold; position: absolute; z-index: 9999;">'
        html += '<div style=" transform: skew(20deg); white-space: nowrap;font-size: 15px;">框选查询</div>'
        html += '</div>'
        html += '<div class="layer-show  layer-common-center" style="padding: 15px 10px 5px 10px; width: 100%;height: 100%;  border: 1px solid #10559a;background: rgba(36,105,187, 0.6) url('
            + ctxStatic
            + '/common/index/images/showbg.png);background-size: 100% 100%;">'
        html += '<table style="width:100%;height:100%;">';
        html += '<tr>';
        html += '<td>框选区域总人口数：</td>';
        html += '<td>' + val.sumPeople + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td>重点人员数量：</td>';
        html += '<td>' + val.pNum + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td>学校数量：</td>';
        html += '<td>' + val.sumSchool + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td>警务工作站数量：</td>';
        html += '<td>' + val.sumPoliceRoom + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td>工作人员数量：</td>';
        html += '<td>' + val.sumPolice + '</td>';
        html += '</tr>';
        html += '</table>';
        html += '</div>'
        html += '</div>'
        layer.open({
            type: 1,
            shade: false,
            title: false, // 不显示标题
            area: ["300", "200px"],
            skin: 'myskin',
            move: '.layer-common-header',
            resize: false,
            fixed: false,
            id: "showSelect",
            content: html,
            cancel: function () {
                Map.clearShape();
                // 关闭事件

            }
        });

    })
}


/*function addconver(data) {
    var fts = new ol.format.GeoJSON().readFeatures(data);
    var ft = fts[0];
    var converGeom = erase(ft.getGeometry());

    var convertFt = new ol.Feature({
        geometry: converGeom
    })
    converLayer.getSource().addFeature(convertFt);

}


function erase(geom) {
    var extent = [-180,-90,180,90];
    var polygonRing = ol.geom.Polygon.fromExtent(extent);
    var coords = geom.getCoordinates();
    coords.forEach(coord =>{
        var linearRing = new ol.geom.LinearRing(coord[0]);
        polygonRing.appendLinearRing(linearRing);
    })
    return polygonRing;
}*/

//地图网格数据初始化
function initorgAreaMap() {
    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type=1', function (data) {
        Map.addJSON1([{
            'type': 'communitys',
            'data': data,
            'isShow': communityFlag,
        }]);
        // addconver(data); //添加遮罩层
    })

    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type=2', function (data) {
        Map.addJSON1([{
            'type': 'grids',
            'data': data,
            'isShow': gridFlag
        }])
    })

    $.getJSON('' + ctx + '/sys/map/orgAreaMap?type=4', function (data) {
        Map.addJSON1([{
            'type': 'streets',
            'data': data,
            'isShow': streetFlag
        }])
    })
}


/********************地图End***********************/
/********************下方菜单***********************/
var xuexiaoFlag = true;
var idArrxuexiao = [];

function xuexiaoFun(_this) {

    var centpoint = [];
    if (xuexiaoFlag) {
        $.getJSON('' + ctx + '/sys/map/ccmOrgNpseMap?type=32', function (data) {
            centpoint = data.centpoint;
            var features = data.features;
            var len = features.length;
            idArrxuexiao = [];
            if (len > 0) {
                for (var i = 0; i < len; i++) {
                    idArrxuexiao.push(features[i].id);
                }
            }
            Map.addJSON3([{
                'type': 'Shortcut',
                'data': data,
                'id': 'xuexiao',
                'isShow': true
            }])

        })
        $(_this).css('border', '1px solid #0e54a9')
        //Map.goTo(centpoint)
    } else {
        $(_this).css('border', '1px solid transparent');
        $.each(idArrxuexiao, function (index, val) {
            Pubmap.removeOverlay(Map['' + val + 'Overlay'])
        });
        Map.removeLayer('xuexiao');
    }
    xuexiaoFlag = !xuexiaoFlag;
}

//医院
var yiyuanFlag = true;
var publicPlaceFlag = true;
var idArryiyuan = [];

function yiyuanFun(_this) {
    if (yiyuanFlag) {
        $.getJSON('' + ctx + '/sys/map/ccmOrgCommonalityMap?type=2', function (
            data) {
            var features = data.features;
            var len = features.length;
            idArryiyuan = [];
            if (len > 0) {
                for (var i = 0; i < len; i++) {
                    idArryiyuan.push(features[i].id);
                }
            }
            Map.addJSON3([{
                'type': 'Shortcut',
                'data': data,
                'id': 'yiyuan',
                'isShow': true
            }])

        })
        $(_this).css('border', '1px solid #0e54a9')
        /*	Map.goTo([ "113.36105768169675", "34.54275331326893" ])*/
    } else {
        $.each(idArryiyuan, function (index, val) {
            Pubmap.removeOverlay(Map['' + val + 'Overlay'])
        });
        $(_this).css('border', '1px solid transparent');
        Map.removeLayer('yiyuan');
    }
    yiyuanFlag = !yiyuanFlag;
}

var jingcheFlag = true;

function jingcheFun(_this) {
    if (jingcheFlag) {
        $.getJSON('' + ctx + '/sys/map/ccmOrgCommonalityMap?type=2', function (
            data) {
            Map.addJSON1([{
                'type': 'DanDian',
                'data': data,
                'id': 'jingche',
                'isShow': true
            }])
        })

        $(_this).css('border', '1px solid #0e54a9')
    } else {
        $(_this).css('border', '1px solid transparent')
        Map.removeLayer('jingche');
    }
    jingcheFlag = !jingcheFlag;
}

//警员
var jingyuanFlag = true;

function jingyuanFun(_this) {
    if (jingyuanFlag) {
        $.getJSON('' + ctx + '/sys/map/deviceMobileMap', function (data) {
            Map.addJSON1([{
                'type': 'PopLocation',
                'id': 'jingyuan',
                'data': data,
                'isShow': true
            }]);
        })
        Map.layersIsShow('PopLocation', true);
        $(_this).css('border', '1px solid #0e54a9');
    } else {
        $(_this).css('border', '1px solid transparent')
        Map.removeLayer('jingyuan');
    }
    jingyuanFlag = !jingyuanFlag;
}

var jiayouzhanFlag = true;
var idArrjiayouzhan = [];

function jiayouzhanFun(_this) {
    if (jiayouzhanFlag) {
        $.getJSON('' + ctx + '/sys/map/findMapIndustry?type=1', function (data) {
            if (data != null) {
                var features = data.features;
                var len = features.length;
                idArrjiayouzhan = [];
                if (len > 0) {
                    for (var i = 0; i < len; i++) {
                        idArrjiayouzhan.push(features[i].id);
                    }
                }
            }
            Map.addJSON3([{
                'type': 'Shortcut',
                'id': 'jiayouzhan',
                'data': data,
                'isShow': true
            }])
        })

        $(_this).css('border', '1px solid #0e54a9');
        //Map.goTo([ "113.39035820960999", "34.528061628341675" ])
    } else {
        $.each(idArrjiayouzhan, function (index, val) {
            Pubmap.removeOverlay(Map['' + val + 'Overlay'])
        });
        $(_this).css('border', '1px solid transparent')
        Map.removeLayer('jiayouzhan');
    }
    jiayouzhanFlag = !jiayouzhanFlag;
}

//商场超市
var shangchangFlag = true;
var idArrshangchang = [];

function shangchangFun(_this) {
    if (shangchangFlag) {
        $.getJSON('' + ctx + '/sys/map/findMapIndustry?type=2', function (data) {
            if (data != null) {
                var features = data.features;
                var len = features.length;
                idArrshangchang = [];
                if (len > 0) {
                    for (var i = 0; i < len; i++) {
                        idArrshangchang.push(features[i].id);
                    }
                }
            }
            Map.addJSON3([{
                'type': 'Shortcut',
                'id': 'shangchang',
                'data': data,
                'isShow': true
            }])
        })
        $(_this).css('border', '1px solid #0e54a9');
    } else {
        $.each(idArrshangchang, function (index, val) {
            Pubmap.removeOverlay(Map['' + val + 'Overlay'])
        });
        $(_this).css('border', '1px solid transparent')
        Map.removeLayer('shangchang');
    }
    shangchangFlag = !shangchangFlag;
}

//娱乐场所
var yuleFlag = true;
var idArryule = [];

function yuleFun(_this) {
    if (yuleFlag) {
        $.getJSON('' + ctx + '/sys/map/findMapIndustry?type=3', function (data) {
            if (data != null) {
                var features = data.features;
                var len = features.length;
                idArryule = [];
                if (len > 0) {
                    for (var i = 0; i < len; i++) {
                        idArryule.push(features[i].id);
                    }
                }
            }
            Map.addJSON3([{
                'type': 'Shortcut',
                'id': 'yule',
                'data': data,
                'isShow': true
            }])
        })
        $(_this).css('border', '1px solid #0e54a9');
    } else {
        $.each(idArryule, function (index, val) {
            Pubmap.removeOverlay(Map['' + val + 'Overlay'])
        });
        $(_this).css('border', '1px solid transparent')
        Map.removeLayer('yule');
    }
    yuleFlag = !yuleFlag;
}

//酒店宾馆
var binguanFlag = true;
var idArrbinguan = [];

function binguanFun(_this) {
    var id = 0;
    if (binguanFlag) {
        $.getJSON('' + ctx + '/sys/map/findMapIndustry?type=4', function (data) {
            if (data != null) {
                id = data.id;
                var features = data.features;
                var len = features.length;
                idArrbinguan = [];
                if (len > 0) {
                    for (var i = 0; i < len; i++) {
                        idArrbinguan.push(features[i].id);
                    }
                }
            }
            Map.addJSON3([{
                'type': 'Shortcut',
                'id': 'binguan',
                'data': data,
                'isShow': true
            }])
        })
        $(_this).css('border', '1px solid #0e54a9');
    } else {
        $.each(idArrbinguan, function (index, val) {
            Pubmap.removeOverlay(Map['' + val + 'Overlay'])
        });
        $(_this).css('border', '1px solid transparent');
        Map.removeLayer('binguan');
    }

    binguanFlag = !binguanFlag;
}

//涉危涉爆
var sheweishebaoFlag = true;
var idArrsheweishebao = [];

function sheweishebaoFun(_this) {
    if (sheweishebaoFlag) {
        $.getJSON('' + ctx + '/sys/map/findMapIndustry?type=5', function (data) {
            if (data != null) {
                var features = data.features;
                var len = features.length;
                idArrsheweishebao = [];
                if (len > 0) {
                    for (var i = 0; i < len; i++) {
                        idArrsheweishebao.push(features[i].id);
                    }
                }
            }
            Map.addJSON3([{
                'type': 'Shortcut',
                'id': 'sheweishebao',
                'data': data,
                'isShow': true
            }])
        })
        $(_this).css('border', '1px solid #0e54a9');
    } else {
        $.each(idArrsheweishebao, function (index, val) {
            Pubmap.removeOverlay(Map['' + val + 'Overlay'])
        });
        $(_this).css('border', '1px solid transparent')
        Map.removeLayer('sheweishebao');
    }
    sheweishebaoFlag = !sheweishebaoFlag;
}

//警务室
var jingwushiFlag = true;
var idArrjingwushi = [];

function jingwushiFun(_this) {
    if (jingwushiFlag) {
        Map.removeLayer('policeroom');
        $.getJSON('' + ctx + '/sys/map/orgCommonlityMap?type=10', function (data) {
            // var features=data.features;
            // var len=features.length;
            // idArrjingwushi=[];
            // if(len>0){
            // 	for(var i=0;i<len;i++){
            // 		idArrjingwushi.push(features[i].id);
            // 	}
            // }
            Map.addJSON1([{
                'type': 'policeroom',
                'id': 'jingwushi',
                'data': data,
                'isShow': true
            }])
        })
        $(_this).css('border', '1px solid #0e54a9');
    } else {
        // $.each(idArrjingwushi,function(index,val){
        // 	Pubmap.removeOverlay(Map[''+val+'Overlay'])
        // });
        $(_this).css('border', '1px solid transparent');
        Map.removeLayer('jingwushi');
    }
    jingwushiFlag = !jingwushiFlag;
}

//工作站
var gongzuozhanFlag = true;
var idArrgongzuozhan = [];

function gongzuozhanFun(_this) {
    if (gongzuozhanFlag) {
        Map.removeLayer('workstation');
        $.getJSON('' + ctx + '/sys/map/orgCommonlityMap?type=11', function (data) {
            var features = data.features;
            var len = features.length;
            idArrgongzuozhan = [];
            if (len > 0) {
                for (var i = 0; i < len; i++) {
                    idArrgongzuozhan.push(features[i].id);
                }
            }
            Map.addJSON1([{
                'type': 'workstation',
                'id': 'gongzuozhan',
                'data': data,
                'isShow': true
            }])
        })
        $(_this).css('border', '1px solid #0e54a9');
    } else {
        $.each(idArrgongzuozhan, function (index, val) {
            Pubmap.removeOverlay(Map['' + val + 'Overlay'])
        });
        $(_this).css('border', '1px solid transparent')
        Map.removeLayer('gongzuozhan');
    }

    gongzuozhanFlag = !gongzuozhanFlag;

}

//视频监控
var shipinjiankongFlag = true;

function shipinjiankongFun(_this) {
    if (shipinjiankongFlag) {
        $.getJSON('' + ctx + '/sys/map/deviceiveMap', function (data) {
            Map.addJSON1([{
                'type': 'videos',
                'id': 'shipinjiankong',
                'data': data,
                'isShow': true
            }])
        })
        $(_this).css('border', '1px solid #0e54a9');
        $(_this).attr('VideoFlagAttr', true);
    } else {
        $(_this).css('border', '1px solid transparent');
        $(_this).attr('VideoFlagAttr', false);
        Map.removeLayer('shipinjiankong');
    }
    shipinjiankongFlag = !shipinjiankongFlag;
}

//广播站
var broadcastFlag = true;

function guangbozhanFun(_this) {
    if (broadcastFlag) {
        $.getJSON('' + ctx + '/sys/map/deviceBroadcastMap', function (data) {
            Map.addJSON1([{
                'type': 'broadcast',
                'id': 'guangbozhan',
                'data': data,
                'isShow': true
            }])
        })
        $(_this).css('border', '1px solid #0e54a9');
    } else {
        $(_this).css('border', '1px solid transparent');
        Map.removeLayer('guangbozhan');
    }
    broadcastFlag = !broadcastFlag;
}

//机顶盒
var SetTopBoxFlag = true;

function SetTopBoxFun(_this) {
    if (SetTopBoxFlag) {
        $.getJSON('' + ctx + '/sys/map/buildBox', function (data) {
            Map.addJSON1([{
                'type': 'topBox',
                'id': 'SetTopBoxFlag',
                'data': data,
                'isShow': true
            }])
        })
        $(_this).css('border', '1px solid #0e54a9');
    } else {
        $(_this).css('border', '1px solid transparent');
        Map.removeLayer('SetTopBoxFlag');
    }
    SetTopBoxFlag = !SetTopBoxFlag;
}

function XiangQingFun(type) {
    $('.pubMapDialog').show()
    var XiangQingData = {
        "长乐路警务室": {
            "民警": [{
                '姓名': '慕观卿',
                '警号': '103802',
                '性别': '男',
                '电话': '17603711723',
                "src": 'muguanqing.JPG'
            }, {
                '姓名': '许小五',
                '警号': '103803',
                '性别': '男',
                '电话': '17603711728',
                "src": 'xuxiaowu.jpg'
            }, {
                '姓名': '郑首守',
                '警号': '103804',
                '性别': '男',
                '电话': '17503711759',
                "src": 'zhebgshoushou.jpg'
            }],
            "辅警": [{
                '姓名': '蒋尚涵',
                '警号': '103805',
                '性别': '男',
                '电话': '13674919298',
                "src": 'jiangshanghan.jpg'
            }, {
                '姓名': '李格',
                '警号': '103806',
                '性别': '女',
                '电话': '13509856056',
                "src": 'lige.JPG'
            }, {
                '姓名': '许伟强',
                '警号': '103807',
                '性别': '男',
                '电话': '13603986786',
                "src": 'xuweiqiang.jpg'
            }]
        },
        "于家岗警务工作站": {
            "民警": [{
                '姓名': '郭亚凯',
                '警号': '103808',
                '性别': '男',
                '电话': '15515991957',
                "src": 'guoyakai.jpg'
            }, {
                '姓名': '慕观卿',
                '警号': '103802',
                '性别': '男',
                '电话': '17603711723',
                "src": 'muguanqing.JPG'
            }, {
                '姓名': '吕一帆',
                '警号': '103809',
                '性别': '男',
                '电话': '13673992608',
                'src': 'lvyifan.jpg'
            }],
            "辅警": [{
                '姓名': '梁炎龙',
                '警号': '103810',
                '性别': '男',
                '电话': '18539436116',
                "src": 'lianyanlong.jpg'
            }, {
                '姓名': '楚褤铭',
                '警号': '103811',
                '性别': '男',
                '电话': '15803817833',
                "src": 'chuming.jpg'
            }, {
                '姓名': '申奥',
                '警号': '103812',
                '性别': '男',
                '电话': '15890009001',
                "src": 'shenao.jpg'
            }]
        },
        "嵩阳路警务工作站": {
            "民警": [{
                '姓名': '蒋尚涵',
                '警号': '103805',
                '性别': '男',
                '电话': '13674919298',
                "src": 'jiangshanghan.jpg'
            }, {
                '姓名': '马双科',
                '警号': '103813',
                '性别': '男',
                '电话': '15093337726',
                "src": 'mashuangke.JPG'
            }, {
                '姓名': '侯鹏',
                '警号': '103814',
                '性别': '男',
                '电话': '18437188688',
                "src": 'houpeng.JPG'
            }],
            "辅警": [{
                '姓名': '高志阳',
                '警号': '103815',
                '性别': '男',
                '电话': '15136205957',
                "src": 'gaozhiyang.jpg'
            }, {
                '姓名': '楚褤铭',
                '警号': '103811',
                '性别': '男',
                '电话': '15803817833',
                "src": 'chuming.jpg'
            }, {
                '姓名': '侯林山',
                '警号': '103817',
                '性别': '男',
                '电话': '15290869958',
                'src': 'houlinshan.jpg'
            }]
        },
        "广场警务工作站": {
            "民警": [{
                '姓名': '吕一帆',
                '警号': '103809',
                '性别': '男',
                '电话': '13673992608',
                'src': 'lvyifan.jpg'
            }, {
                '姓名': '慕观卿',
                '警号': '103802',
                '性别': '男',
                '电话': '17603711723',
                "src": 'muguanqing.JPG'
            }, {
                '姓名': '郭亚凯',
                '警号': '103808',
                '性别': '男',
                '电话': '15515991957',
                "src": 'guoyakai.jpg'
            }],
            "辅警": [{
                '姓名': '梁炎龙',
                '警号': '103810',
                '性别': '男',
                '电话': '18539436116',
                "src": 'lianyanlong.jpg'
            }, {
                '姓名': '高志阳',
                '警号': '103815',
                '性别': '男',
                '电话': '15136205957',
                "src": 'gaozhiyang.jpg'
            }, {
                '姓名': '侯林山',
                '警号': '103817',
                '性别': '男',
                '电话': '15290869958',
                'src': 'houlinshan.jpg'
            }]
        }
    }
    if (type == '长乐路警务室' || type == '于家岗警务工作站' || type == '嵩阳路警务工作站'
        || type == '广场警务工作站') {
        var data = XiangQingData[type];
        console.log(data)
        var data1 = data['民警'];
        var data2 = data['辅警'];
        var len1 = data1.length;
        var len2 = data2.length;
        var html = "";
        html += '<div class="pubMapDialog-center1"><div> 民警： </div>';
        for (var i = 0; i < len1; i++) {
            html += '<div class="well-item">'
            html += '<div  class="jingcha-name correct">';
            html += '<div  class="jiachatouxiang" style="width:100%;height:100%;"><img src="/arjccm/static/modules/map/images/pub/'
                + data1[i]['src'] + '" /></div>';
            html += ' <p>' + data1[i]['姓名'] + '</p>';
            html += '<p>' + data1[i]['警号'] + '</p>';
            html += ' </div>'
            html += '<div class="opposite"><div>';
            html += '<table>';
            html += '<tr>';
            html += '<td align="right" >姓名：</td><td align="left" >'
                + data1[i]['姓名'] + '</td>';
            html += '</tr>';
            html += '<tr>';
            html += '<td align="right" >性别：</td><td align="left">'
                + data1[i]['性别'] + '</td>';
            html += '</tr>';
            html += '<tr>';
            html += '<td align="right" >警号：</td><td align="left">'
                + data1[i]['警号'] + '</td>';
            html += '</tr>';
            html += '<tr>';
            html += '<td align="right" >电话：</td><td align="left">'
                + data1[i]['电话'] + '</td>';
            html += '</tr>';
            html += '</table>';
            html += '</div></div>';
            html += '</div>'
        }
        html += ' </div>'
        html += '<div  class="pubMapDialog-center2"><div>辅警： </div>';
        for (var i = 0; i < len2; i++) {
            html += '<div class="well-item">'
            html += '<div  class="jingcha-name correct">';
            html += '<div  class="jiachatouxiang"  style="width:100%;height:100%;"><img src="/arjccm/static/modules/map/images/pub/'
                + data2[i]['src'] + '" /></div>';
            html += ' <p>' + data2[i]['姓名'] + '</p>';
            html += '<p>' + data2[i]['警号'] + '</p>';
            html += ' </div>';
            html += '<div class="opposite"><div>';
            html += '<table>';
            html += '<tr>';
            html += '<td  align="right" >姓名：</td><td align="left">'
                + data2[i]['姓名'] + '</td>';
            html += '</tr>';
            html += '<tr>';
            html += '<td align="right" >性别：</td><td align="left">'
                + data2[i]['性别'] + '</td>';
            html += '</tr>';
            html += '<tr>';
            html += '<td align="right" >警号：</td><td align="left">'
                + data2[i]['警号'] + '</td>';
            html += '</tr>';
            html += '<tr>';
            html += '<td align="right" >电话：</td><td align="left">'
                + data2[i]['电话'] + '</td>';
            html += '</tr>';
            html += '</table>';
            html += '</div></div>';
            html += '</div>'
        }
        html += ' </div>'

        $('.pubMapDialog-center-center').html(html)

        $(".well-item").hover(function () {
            $(this).find(".correct").children().removeClass('test');
            $(this).find(".correct").children().removeClass('test2');
            $(this).find(".opposite").children().removeClass('test');
            $(this).find(".opposite").children().removeClass('test2');
            $(this).find(".correct").children().addClass("test");
            $(this).find(".opposite").children().addClass('test2');
        }, function () {
            $(this).find(".correct").children().removeClass('test');
            $(this).find(".opposite").children().removeClass('test');
            $(this).find(".correct").children().removeClass("test2");
            $(this).find(".opposite").children().removeClass("test2");
            $(this).find(".correct").children().addClass("test2");
            $(this).find(".opposite").children().addClass('test');
        });

    } else if (type == '骨科医院' || type == "眼科医院") {
        var data = {
            "骨科医院": {
                "级别": "一级医院",
                "经营性质": "县市区直属",
                "重点部位": "化验生化室、放射科",
                "基本情况": "新密市骨科医院是经郑州市卫生局批准执业的骨科专业机构，是以骨科、显微外科、普外为特色优势，集创伤急救、医疗、保健、功能康复为一体的专科医院。",
                'src': 'guke.jpg',
            },
            "眼科医院": {
                "级别": "一级医院",
                "经营性质": "县市区直属",
                "重点部位": "病房、治疗室",
                "基本情况": "新密市眼科医院是全省最大的一所县（市）级眼科专科医院。该医院位于新密市嵩山大道123号总建筑面积6000余平方米，医务人员100多人，其中副主任医师4人，中级职称人员30余人。 ",
                'src': 'yanke.jpg',
            },
        }
        var html = "";
        html += '<table style="height:100%">';
        html += '<tr>';
        html += '<td  class="textcenterright" style="width:135px"><img src="/arjccm/static/modules/map/images/pub/'
            + data[type]["src"] + '" /></td>';
        html += '<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'
            + type + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td class="textcenterright">级别：</td>';
        html += '<td class="textcenterleft">' + data[type]["级别"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">经营性质：</td>';
        html += '<td class="textcenterleft">' + data[type]["经营性质"] + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td  class="textcenterright">重点部位：</td>';
        html += '<td class="textcenterleft">' + data[type]["重点部位"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">基本情况：</td>';
        html += '<td class="textcenterleft">' + data[type]["基本情况"] + '</td>';
        html += '</tr>';

        html += '</table>';
        $('.pubMapDialog-center-center').html(html)
    } else if (type == '金凤路小学' || type == "金凤路初中") {
        var data = {
            "金凤路小学": {
                "性质": "消防重点单位",
                "学校地址": "西大街与金凤路交叉口北150米",
                "重点部位": "教室、办公室",
                "基本情况": "新密市金凤路小学是隶属于新密市教体局的一所市直学校,占地面积近24亩,建筑面积10398平方米。学校环境优美,教学设施齐全,设有梦想教室、美术教室等",
                'src': 'xiaoxue.jpg'
            },
            "金凤路初中": {
                "性质": "消防重点单位",
                "学校地址": "金凤路与青屏大街交叉口西南150米",
                "重点部位": "教室、办公室",
                "基本情况": "金凤路初中学校建有教学综合楼、科技实验楼、综合办公楼、体育馆、运动场占地四十余亩，是一所教育教学设施一流的现代化的城市初级中学 ",
                'src': 'xuexiao2.png'
            },
        }
        var html = "";
        html += '<table style="height:100%">';
        html += '<tr>';
        html += '<td  class="textcenterright" style="width:135px"><img src="/arjccm/static/modules/map/images/pub/'
            + data[type]["src"] + '" /></td>';
        html += '<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'
            + type + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td class="textcenterright">性质：</td>';
        html += '<td class="textcenterleft">' + data[type]["性质"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">重点部位：</td>';
        html += '<td class="textcenterleft">' + data[type]["重点部位"] + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td  class="textcenterright">学校地址：</td>';
        html += '<td class="textcenterleft">' + data[type]["学校地址"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">基本情况：</td>';
        html += '<td class="textcenterleft">' + data[type]["基本情况"] + '</td>';
        html += '</tr>';
        html += '</table>';
        $('.pubMapDialog-center-center').html(html)
    } else if (type == '郑交运集团加油站(新密分公司)' || type == "亚立石化加油站(开阳路)") {
        var data = {
            "郑交运集团加油站(新密分公司)": {
                "性质": "消防重点单位",
                "重点部位": "油库、加油作业区",
                "单位地址": "郑交运集团加油站(新密分公司)",
                "基本情况": "成立于1996年7月。主要经营汽油、柴油、润滑油销售等。",
                'src': 'zhengjiao.jpg'
            },
            "亚立石化加油站(开阳路)": {
                "性质": "消防重点单位",
                "重点部位": "油库、加油作业区",
                "单位地址": "河南省郑州市新密市嵩山大道669",
                "基本情况": "河南亚立石油化工有限公司，成立于2001年9月。主要经营车用乙醇汽油、柴油、煤油零售与批发，预包装食品零售，化工产品经销，仓储，燃料油、润滑油、日用百货的销售，经营进出口贸易等业务。 ",
                'src': 'yali.jpg'
            },
        }
        var html = "";
        html += '<table style="height:100%">';
        html += '<tr>';
        html += '<td  class="textcenterright" style="width:135px"><img src="/arjccm/static/modules/map/images/pub/'
            + data[type]["src"] + ' " /></td>';
        html += '<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'
            + type + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td class="textcenterright">性质：</td>';
        html += '<td class="textcenterleft">' + data[type]["性质"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">重点部位：</td>';
        html += '<td class="textcenterleft">' + data[type]["重点部位"] + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td  class="textcenterright">单位地址：</td>';
        html += '<td class="textcenterleft">' + data[type]["单位地址"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">基本情况：</td>';
        html += '<td class="textcenterleft">' + data[type]["基本情况"] + '</td>';
        html += '</tr>';
        html += '</table>';
        $('.pubMapDialog-center-center').html(html)
    } else if (type == '承誉德大酒店' || type == "鑫海湾假日酒店" || type == "福海商务宾馆") {
        var data = {
            "承誉德大酒店": {
                "性质": "消防重点单位",
                "单位地址": "郑州市新密市西大街88号",
                "重点部位": "客房、餐厅",
                "基本情况": "郑州承誉德大酒店是一家集餐饮、客房、娱乐、休闲洗浴及商务会议活动为一体的多功能大型商务涉外酒店。",
                'src': 'chengyude.png',
            },
            "鑫海湾假日酒店": {
                "性质": "消防重点单位",
                "单位地址": "郑州市新密市平安路225号",
                "重点部位": "客房、餐厅",
                "基本情况": "新密鑫海湾假日酒店融顶尖豪华酒店设计理念、艺术构筑于一体，采用新型绿色环保材料、安全健康科技的人文智能控制系统，拥有各式舒适客房，并设有茶室、棋牌室、足浴等配套服务设施。",
                'src': 'xinhaiwan.jpg',
            },
            "福海商务宾馆": {
                "性质": "消防重点单位",
                "单位地址": "郑州市新密市鑫苑花园东楼",
                "重点部位": "客房、餐厅",
                "基本情况": "福海商务宾馆，于2008年正式营业，是一家住宿、休闲、餐饮为一体的经营式现代企业。",
                'src': 'fuhai.jpg',
            },
        }
        var html = "";
        html += '<table style="height:100%">';
        html += '<tr>';
        html += '<td  class="textcenterright" style="width:135px"><img src="/arjccm/static/modules/map/images/pub/'
            + data[type]["src"] + '" /></td>';
        html += '<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'
            + type + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td class="textcenterright">性质：</td>';
        html += '<td class="textcenterleft">' + data[type]["性质"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">重点部位：</td>';
        html += '<td class="textcenterleft">' + data[type]["重点部位"] + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td  class="textcenterright">单位地址：</td>';
        html += '<td class="textcenterleft">' + data[type]["单位地址"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">基本情况：</td>';
        html += '<td class="textcenterleft">' + data[type]["基本情况"] + '</td>';
        html += '</tr>';
        html += '</table>';
        $('.pubMapDialog-center-center').html(html)
    } else if (type == '金博大购物中心') {
        var data = {
            "金博大购物中心": {
                "性质": "消防重点单位",
                "商场地址": "郑州市新密市西大街276号",
                "重点部位": "仓库、逃生通道",
                "基本情况": "新密金博大购物中心有限公司于2007年06月14日在新密市工商行政管理局登记成立。法定代表人楚文杰，公司经营范围包括百货、家用电器、服装、鞋帽、五金交电、皮革制品等。",
                'src': 'jingboda.jpg'
            }
        }
        var html = "";
        html += '<table style="height:100%">';
        html += '<tr>';
        html += '<td  class="textcenterright" style="width:135px"><img style="width:48px;height:48px" src="/arjccm/static/modules/map/images/pub/'
            + data[type]["src"] + '" /></td>';
        html += '<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'
            + type + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td class="textcenterright">性质：</td>';
        html += '<td class="textcenterleft">' + data[type]["性质"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">重点部位：</td>';
        html += '<td class="textcenterleft">' + data[type]["重点部位"] + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td  class="textcenterright">商场地址：</td>';
        html += '<td class="textcenterleft">' + data[type]["商场地址"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">基本情况：</td>';
        html += '<td class="textcenterleft">' + data[type]["基本情况"] + '</td>';
        html += '</tr>';
        html += '</table>';
        $('.pubMapDialog-center-center').html(html)
    } else if (type == '麦克疯量贩KTV' || type == '糖果娱乐空间') {
        var data = {
            "麦克疯量贩KTV": {
                "性质": "治安消防单位",
                "单位地址": "新密市长乐路515号",
                "重点部位": "客房、配电室",
                "基本情况": "麦克疯量贩KTV新密店以人性化的布局，奢华的装饰装潢，让消费者感受贴心的服务和自由的欢唱；麦克疯量贩KTV新密店本着独特的消费方式及完善的服务理念，致力于为您打造价格低、音响好、歌曲全、服务优的健康纯K歌场所。",
                'src': 'maikefeng.jpg'
            },
            "糖果娱乐空间": {
                "性质": "治安消防单位",
                "单位地址": "新密市西大街368号",
                "重点部位": "客房、配电室",
                "基本情况": "糖果娱乐空间内部装饰时尚、典雅、舒适，消费价格低廉。是好友聚会、家人娱乐、生日庆典、商务休闲的一个极佳的文化娱乐场所。 ",
                'src': 'tanguo.jpg'
            },
        }
        var html = "";
        html += '<table style="height:100%">';
        html += '<tr>';
        html += '<td  class="textcenterright" style="width:135px"><img src="/arjccm/static/modules/map/images/pub/'
            + data[type]["src"] + '" /></td>';
        html += '<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'
            + type + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td class="textcenterright">性质：</td>';
        html += '<td class="textcenterleft">' + data[type]["性质"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">重点部位：</td>';
        html += '<td class="textcenterleft">' + data[type]["重点部位"] + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td  class="textcenterright">单位地址：</td>';
        html += '<td class="textcenterleft">' + data[type]["单位地址"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">基本情况：</td>';
        html += '<td class="textcenterleft">' + data[type]["基本情况"] + '</td>';
        html += '</tr>';
        html += '</table>';

        $('.pubMapDialog-center-center').html(html)
    } else if (type == '烟花爆竹专营店(新华路三店)') {
        var data = {
            "烟花爆竹专营店(新华路三店)": {
                "性质": "涉危涉爆重点单位",
                "重点部位": "仓库",
                "单位地址": "郑州市新密市南密新路168号",
                "基本情况": "烟花爆竹专营店已取得取得《烟花爆竹经营（批发）许可证》和《烟花爆竹经营（零售）许可证》的规范专营店",
                'src': 'baozhu.jpg'
            }
        }
        var html = "";
        html += '<table style="height:100%">';
        html += '<tr>';
        html += '<td  class="textcenterright" style="width:135px"><img src="/arjccm/static/modules/map/images/pub/'
            + data[type]["src"] + '" /></td>';
        html += '<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'
            + type + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td class="textcenterright">性质：</td>';
        html += '<td class="textcenterleft">' + data[type]["性质"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">重点部位：</td>';
        html += '<td class="textcenterleft">' + data[type]["重点部位"] + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td  class="textcenterright">单位地址：</td>';
        html += '<td class="textcenterleft">' + data[type]["单位地址"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">基本情况：</td>';
        html += '<td class="textcenterleft">' + data[type]["基本情况"] + '</td>';
        html += '</tr>';
        html += '</table>';
        $('.pubMapDialog-center-center').html(html)
    }

}

function shipinjiankongXiangqingFun(src, name) {
    $('.pubMapDialog').show()
    if (name == '视频监控1' || name == '视频监控2' || name == '视频监控3'
        || name == '视频监控4') {
        var html = '';
        html += '<video width="440" height="250"  controls="controls" autoplay="autoplay" loop="loop" class="videoBtn">';
        html += '<source src="/arjccm/static/modules/map/images/pub/video/'
            + src + '" type="video/mp4">';
        html += '</video>';
        $('.pubMapDialog-center-center').html(html)
    } else if (name == "豫A0S81Q" || name == "豫A0519H") {
        var data = {
            "豫A0S81Q": {
                "任务": "治安巡逻",
                "重点路线": "长乐路",
                "所属单位": "广场警务工作站",
                "目前位置": "新密市西大街西段1号"
            },
            "豫A0519H": {
                "任务": "治安巡逻",
                "重点路线": "长乐路",
                "所属单位": "长乐路警务工作站",
                "目前位置": "新密市西大街西段1号"
            }
        }
        var html = '';
        html += '<table style="height:100%">';
        html += '<tr>';
        html += '<td  class="textcenterright" style="width:135px"><img src="/arjccm/static/modules/map/images/pub/jingche2.png" /></td>';
        html += '<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'
            + name + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td class="textcenterright">任务：</td>';
        html += '<td class="textcenterleft">' + data[name]["任务"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">重点路线：</td>';
        html += '<td class="textcenterleft">' + data[name]["重点路线"] + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td  class="textcenterright">所属单位：</td>';
        html += '<td class="textcenterleft">' + data[name]["所属单位"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">目前位置：</td>';
        html += '<td class="textcenterleft">' + data[name]["目前位置"] + '</td>';
        html += '</tr>';
        html += '</table>';
        $('.pubMapDialog-center-center').html(html)

    } else if (name == "吕一凡" || name == "王庆豪") {
        var data = {
            "吕一凡": {
                "任务": "治安巡逻",
                "重点路线": "长乐路",
                "所属单位": "广场警务工作站",
                "目前位置": "新密市大鸿路159号"
            },
            "王庆豪": {
                "任务": "治安巡逻",
                "重点路线": "长乐路",
                "所属单位": "长乐路警务工作站",
                "目前位置": "新密市大鸿路159号"
            }
        }
        var html = '';
        html += '<table style="height:100%">';
        html += '<tr>';
        html += '<td  class="textcenterright" style="width:135px"><img src="/arjccm/static/modules/map/images/pub/jingcha2.png" /></td>';
        html += '<td  class="textcenterleft" style="font-size:20px;padding-left:24px">'
            + name + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td class="textcenterright">任务：</td>';
        html += '<td class="textcenterleft">' + data[name]["任务"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">重点路线：</td>';
        html += '<td class="textcenterleft">' + data[name]["重点路线"] + '</td>';
        html += '</tr>';
        html += '<tr class="l-grid-row-alt">';
        html += '<td  class="textcenterright">所属单位：</td>';
        html += '<td class="textcenterleft">' + data[name]["所属单位"] + '</td>';
        html += '</tr>';
        html += '<tr>';
        html += '<td  class="textcenterright">目前位置：</td>';
        html += '<td class="textcenterleft">' + data[name]["目前位置"] + '</td>';
        html += '</tr>';
        html += '</table>';
        $('.pubMapDialog-center-center').html(html)
    }
}

/***************************下方菜单End****************************/
/***************************加载echarts图End****************************/
var leftContent0Charts = null;
var leftContent1Charts = null;
var leftContent2Charts = null;
var leftContent3Charts = null;
var leftContent4Charts = null;
var rightContent0Charts = null;
var rightContent01Charts = null;
var rightContent1Charts = null;
var rightContent2Charts = null;
var rightContent3Charts = null;

function resizeChat() {
    if (leftContent0Charts == null) {
        leftContent0Charts = echarts.init(document.getElementById('echLeftContent0'));
    }
    leftContent0Charts.clear();
    leftContent0Charts.resize();
    if (leftContent1Charts == null) {
        leftContent1Charts = echarts.init(document.getElementById('echLeftContent1'));
    }
    leftContent1Charts.clear();
    leftContent1Charts.resize();
    if (leftContent2Charts == null) {
        leftContent2Charts = echarts.init(document.getElementById('echleftContent2'));
    }
    leftContent2Charts.clear();
    leftContent2Charts.resize();
    if (leftContent3Charts == null) {
        leftContent3Charts = echarts.init(document.getElementById('echLeftContent3'));
    }
    leftContent3Charts.clear();
    leftContent3Charts.resize();
    if (leftContent4Charts == null) {
        leftContent4Charts = echarts.init(document.getElementById('echLeftContent4'));
    }
    leftContent4Charts.clear();
    leftContent4Charts.resize();
    if (rightContent0Charts == null) {
        rightContent0Charts = echarts.init(document.getElementById('echRightContent0'));
    }
    rightContent0Charts.clear();
    rightContent0Charts.resize();
    if (rightContent01Charts == null) {
        rightContent01Charts = echarts.init(document.getElementById('echRightContent01'));
    }
    rightContent01Charts.clear();
    rightContent01Charts.resize();
    if (rightContent1Charts == null) {
        rightContent1Charts = echarts.init(document.getElementById('echRightContent1'));
    }
    rightContent1Charts.clear();
    rightContent1Charts.resize();
    if (rightContent2Charts == null) {
        rightContent2Charts = echarts.init(document.getElementById('echRightContent2'));
    }
    rightContent2Charts.clear();
    rightContent2Charts.resize();
    if (rightContent3Charts == null) {
        rightContent3Charts = echarts.init(document.getElementById('echRightContent3'));
    }
    rightContent3Charts.clear();
    rightContent3Charts.resize();
}

/***************************加载echarts图End****************************/

//重点青少年分析
function getTeenagersAnalysis(data) {
    var dataname = data.name;
    var datacount1 = data.redMAX;
    var datanum1 = data.redvalue;
    var datascale1 = data.redProportion;
    var datacount2 = data.addMAX;
    var datanum2 = data.addvalue;
    var datascale2 = data.addProportion;
    var TeenagersChart = echarts.init(document.getElementById('echLeftContent3'));
    TeenagersChart.resize();
    TeenagersChart.clear();
    var option = {
        title: [
            {
                text: '人员类型',
                left: '19%',
                top: '2%',
                textAlign: 'center',
                textStyle: {
                    fontSize: 14,
                    color: '#9DCCFF'
                },
            },
            {
                text: '总数',
                left: '50%',
                top: '2%',
                textAlign: 'center',
                textStyle: {
                    fontSize: 14,
                    color: '#9DCCFF'
                },
            },
            {
                text: '违法犯罪人数',
                left: '77%',
                top: '2%',
                textAlign: 'center',
                textStyle: {
                    fontSize: 14,
                    color: '#9DCCFF'
                },
            },
        ],
        grid: {
            left: '3%',
            right: '3%',
            bottom: '10%',
            top: '12%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            show: false,
            axisLabel: {
                show: false
            },
            splitLine: {
                show: false
            },
            axisTick: {
                show: false
            }
        },
        yAxis: {
            type: 'category',
            inverse: true,
            axisLine: {
                show: false,
            },
            axisTick: {
                show: false,
            },
            show: true,
            // data : ['闲散青少年','不良行为青少年','流浪乞讨未成年','服刑人员未成年子女','农村留守儿童','其他'], //dataname
            data: dataname,
            axisLabel: {
                color: "#FFFFFF",
                fontSize: 12,
                padding: [0, 30, 0, 0]
            },
        },
        series: [
            {
                type: 'bar',
                barWidth: 11,
                // data: [500, 400, 300, 200, 100, 500],//datascale1
                data: datascale1,
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                            offset: 0,
                            color: '#FFC802'
                        }, {
                            offset: 1,
                            color: '#e19209'
                        }]),
                        barBorderRadius: [0, 6, 6, 0],
                    }
                }
            },
            {  // 灰色背景柱状图
                type: 'bar',
                barGap: '-100%',
                barWidth: 11,
                itemStyle: {
                    normal: {
                        color: '#1F4C89',
                    }
                },
                label: {
                    show: true,
                    formatter: function (params) {
                        // return [1233, 400, 300, 200, 100, 100][params.dataIndex];//datanum2
                        return datanum2 [params.dataIndex];
                    },
                    position: 'left',
                    textStyle: {
                        fontSize: 16,
                        color: '#199FFF'
                    },
                    padding: [0, 5, 0, 0]
                },
                z: -10,
                // data: [-500, -500,-500, -500,-500,-500] //datacount2
                data: datacount2
            },
            {
                type: 'bar',
                barWidth: 11,
                // data: [-233, -400, -300, -200, -100, -100],//datascale2
                data: datascale2,
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                            offset: 0,
                            color: '#008BEF'
                        }, {
                            offset: 1,
                            color: '#48C2F4'
                        }]),
                        barBorderRadius: [6, 0, 0, 6],
                    }
                }
            },
            {  // 灰色背景柱状图
                type: 'bar',
                barGap: '-100%',
                barWidth: 11,
                itemStyle: {
                    normal: {
                        color: '#1F4C89',
                    }
                },
                label: {
                    show: true,
                    formatter: function (params) {
                        // return [3256, 400, 300, 200, 100, 100][params.dataIndex]; //datanum1
                        return datanum1[params.dataIndex];
                    },
                    position: 'right',
                    textStyle: {
                        fontSize: 16,
                        color: '#ECC31C'
                    },
                    padding: [0, 0, 0, 5]
                },
                z: -10,
                // data: [500, 500, 500, 500, 500, 500] //datacount1
                data: datacount1
            },
        ]
    };
    TeenagersChart.setOption(option);
}

//重点青少年分类统计
function KeyYouthByType(model, data) {
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)",
            confine: true
        },
        legend: {
            orient: 'vertical',
            right: 60,
            y: 'center',
            itemWidth: 15,
            textStyle: {
                color: '#fff'
            },
            data: data.name
        },
        color: color,
        grid: {
            left: '3%',
            right: '3%',
            bottom: '3%',
            top: '3%',
            containLabel: true
        },
        series: [{
            name: '',
            labelLine: {
                normal: {
                    length: 5
                }
            },
            type: 'pie',
            radius: ['50%', '80%'],
            center: ['25%', '50%'],
            label: {
                show: false
                /*normal: {
                    formatter: '{b|{b}：}{c}  ',
                    borderRadius: 4,
                    textStyle: {
                        color: '#fff',
                        width: 22
                    },
                    rich: {
                        a: {
                            lineHeight: 22,
                            align: 'center'
                        },
                        per: {
                            color: '#eee',
                            backgroundColor: '#334455',
                            padding: [2, 4],
                            borderRadius: 2
                        }
                    }
                }*/
            },
            data: data.resultlist
        }]
    };
    // 实例化 对象
    var Barchart = echarts.init(document.getElementById(model));
    Barchart.resize();
    Barchart.clear();
    // 填充数据
    Barchart.setOption(option);
}

//特殊人群分析
function SpecialPopByType(model, data) {
	var option = {
            tooltip: {
                trigger: 'item',
                formatter: "{b}: {c} ({d}%)"
            },
	        legend: {
	            orient: 'vertical',
	            right: '-6%',
	            top: '7%',
	            itemWidth: 10,
	            itemHeight: 10,
	            height: 180,

	            textStyle: {
	                lineHeight: 22,
	                color: '#fff',
	                fontsize: 6,
	            },
	            data: data.name

	        },
	        series: [
	            {
	                color: color,
	                type: 'pie',
	                radius: ['55', '80'],
	                center: ['27%', '45%'],
	                data: data.list,
	                label: {
	                    show: false
	                }
	            },
	            {
	                color: ['#32bcfe'],
	                type: 'pie',
	                radius: ['90', '91'],
	                center: ['27%', '45%'],
	                hoverAnimation: false,
	                data: [100],
	                label: {
	                    show: false
	                }
	            },
	            {
	                color: ['#095471'],
	                type: 'pie',
	                radius: ['39', '40'],
	                center: ['27%', '45%'],
	                hoverAnimation: false,
	                data: [100],
	                label: {
	                    show: false
	                }
	            },
	            {
	                type: 'gauge',
	                splitNumber: 15, //刻度数量
	                min: 0,
	                max: 20,
	                radius: '45%',
	                center: ['27%', '45%'],
	                startAngle: 90,
	                endAngle: -269.9999,
	                axisLine: {
	                    show: true,
	                    lineStyle: {
	                        width: 0,
	                        shadowBlur: 0,
	                        color: [
	                            [0, '#095471'],
	                            [1, '#095471']
	                        ]
	                    }
	                },
	                axisTick: {
	                    show: true,
	                    lineStyle: {
	                        color: '#095471',
	                        width: 2
	                    },
	                    length: 6,
	                    splitNumber: 5
	                },
	                splitLine: {
	                    show: true,
	                    length: 6,
	                    lineStyle: {
	                        color: '#095471',
	                    }
	                },
	                axisLabel: {
	                    show: false
	                },
	                detail: {
	                    show: false
	                }
	            }
	        ]
	    };
    // 实例化 对象
    var Barchart = echarts.init(document.getElementById(model));
    Barchart.resize();
    Barchart.clear();
    // 填充数据
    Barchart.setOption(option);
}

/***************************网格管理Start****************************/
//基础数据
function getPeopleData(noCache) {
    $.getJSON(ctx + "/report/ccmPeopleAmount/getAnalyzePopNewData", {"noCache": noCache}, function (data) {
        for(var i=0;i<data.length;i++){
            if(data[i]>99999){
                data[i]=(parseInt(data[i]/10000.)+'万');
            }
        }
        var dataList = [{
            value: data[0],
            name: '户籍人口'
        },
            {
                value: data[1],
                name: '流动人口'
            },
            {
                value: data[2],
                name: '境外人口'
            },
            {
                value: data[3],
                name: '未落户人口'
            }];
        var dataMap = {
            '户籍人口': data[0],
            '流动人口': data[1],
            '境外人口': data[2],
            '未落户人口': data[3]
        };
        $(".rksj1 .rknum").html(dataList[0].value)
        $(".rksj1 .rktitle").html(dataList[0].name)
        $(".rksj2 .rknum").html(dataList[1].value)
        $(".rksj2 .rktitle").html(dataList[1].name)
        $(".rksj3 .rknum").html(dataList[2].value)
        $(".rksj3 .rktitle").html(dataList[2].name)
        $(".rksj4 .rknum").html(dataList[3].value)
        $(".rksj4 .rktitle").html(dataList[3].name)
        var count=(parseInt)(data[0])+(parseInt)(data[1])+(parseInt)(data[2])+(parseInt)(data[3])
        $("#rkt1 .rkzs").html(count);
    });

}

//基础数据的option
function showPeopleData(dataList, dataMap, count) {
   /* var option = {
        color: ['#0F8CDB', '#F86422', '#E7BA1B', '#3E64D5'],
        tooltip: {
            trigger: 'item',
            formatter: "{b}: {c} ({d}%)"
        },
        title: {
            text: '{title|人口数据}{value|' + count + '}{unit|人}',
            textStyle: {
                rich: {
                    title: {
                        fontFamily: 'Microsoft YaHei',
                        fontWeight: 400,
                        fontSize: 12,
                        lineHeight: 39,
                        color: "#FFFFFF"
                    },
                    value: {
                        fontFamily: 'DIN Alternate',
                        fontWeight: 'bold',
                        fontSize: 30,
                        lineHeight: 39,
                        padding: [0, 9, 0, 30],
                        color: "#F9CB42"
                    },
                    unit: {
                        fontFamily: 'Microsoft YaHei',
                        fontWeight: 400,
                        fontSize: 14,
                        lineHeight: 39,
                        color: "#406CA9"
                    }
                }
            },
            x: 'center'
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data: ['户籍人口', '流动人口', '境外人口', '未落户人口'],
            left: '50%',
            top: '20%',
            itemWidth: 14,
            formatter: function (name) {
                return "{title|" + name + "}{value|" + (dataMap[name]) + "}{unit|人}"
            },
            textStyle: {
                rich: {
                    title: {
                        fontFamily: 'Microsoft YaHei',
                        fontWeight: 400,
                        width: 59,
                        fontSize: 12,
                        lineHeight: 25,
                        color: "#FFFFFF"
                    },
                    value: {
                        fontFamily: 'DIN Alternate',
                        fontWeight: 'bold',
                        fontSize: 30,
                        lineHeight: 25,
                        padding: [0, 6, 0, 15],
                        color: "#F9CB42"
                    },
                    unit: {
                        fontFamily: 'Microsoft YaHei',
                        fontWeight: 400,
                        fontSize: 14,
                        lineHeight: 25,
                        color: "#406CA9"
                    }
                }
            }
        },
        graphic: [
            {
                type: 'image',
                id: 'logo',
                left: '30%',
                top: '7%',
                bounding: 'raw',
                origin: [75, 75],
                style: {
                    image: '/arjccm/static/common/index/images/statIndexCool/renkou.png',
                    width: 18,
                    height: 14,
                    opacity: 0.4
                }
            },

        ],
        series: [
            {
                type: 'pie',
                radius: ['40%', '55%'],
                avoidLabelOverlap: false,
                center: ['30%', '50%'],
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: dataList
            }
        ]
    };
    leftContent1Charts.setOption(option);*/
}

//人口趋势图
function getPeopleCurrent(noCache) {
    $.getJSON(ctx + "/report/ccmPeopleStat/findPeopleNewSum", {"noCache": noCache}, function (data) {
        showPeopleCurrent(data);
    });
}

//人口趋势图的option
function showPeopleCurrent(data) {
    var option = {
        tooltip: {
            trigger: 'item',
        },
        legend: {
            data: ['人数', '增长人数'],
            textStyle: {
                color: '#9DCCFF'
            },
        },
        grid: {
            top: '12%',
            left: '3%',
            right: '5%',
            bottom: '10%',
            containLabel: true
        },
        xAxis: [{
            type: 'category',
            // data: ['2019-04', '2019-05', '2019-06', '2019-07', '2019-08', '2019-09'],
            data: data['日期'],
            axisLabel: {
                show: true,
                color: '#9DCCFF',
                fontsize: '12px'
            },
            axisLine: {
                lineStyle: {
                    color: '#FFFFFF',
                    opacity: 0.2
                }
            },
            axisTick: {
                show: false,
            },
        }],
        yAxis: [{
            type: 'value',
            axisLabel: {
                formatter: '{value}',
                color: '#9DCCFF',
                fontsize: '12px'
            },
            splitLine: {
                show: false,
            },
            axisLine: {
                lineStyle: {
                    color: '#FFFFFF',
                    opacity: 0.2
                }
            },
            axisTick: {
                show: true,
                lineStyle: {
                    color: '#FFF',
                    opacity: 0.2
                }
            },
        },
            {
                type: 'value',
                splitLine: {
                    show: false
                },
                axisLabel: {
                    formatter: '{value}',
                    color: '#9DCCFF',
                    fontsize: '12px'
                },
                axisLine: {
                    lineStyle: {
                        color: '#FFFFFF',
                        opacity: 0.2
                    }
                },
                axisTick: {
                    show: true,
                    lineStyle: {
                        color: '#FFF',
                        opacity: 0.2
                    }
                },
            }
        ],
        series: [{
            name: '人数',
            type: 'bar',
            barWidth: '25%',
            data: data['人数'],
            itemStyle: {
                normal: {
                    color: '#02B6BC'
                }
            },
        },
            {
                name: '增长人数',
                type: 'line',
                yAxisIndex: 1,
                // data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2],
                data: data['新增人数'],
                itemStyle: {
                    normal: {
                        color: '#EBBE1B'
                    }
                },
                symbolSize: 8,
            }
        ]
    };
    leftContent2Charts.setOption(option);
}

function formatFloat(src, pos) {
    return Math.round(src * Math.pow(10, pos)) / Math.pow(10, pos);
}


//城市部件状态
function getCitycomponents(noCache) {
    $.getJSON(ctx + "/citycomponents/ccmCityComponents/getCitycomponents", {"noCache": noCache}, function (data) {
        var data1 = data["公共设施"];
        var dataSum1 = data1["01"].value + data1["02"].value + data1["03"].value + data1["04"].value;
        var dataValue101 = data1["01"]; //完好
        var dataValue102 = data1["02"]; //破损
        var data2 = data["道路环境"];
        var dataSum2 = data2["01"].value + data2["02"].value + data2["03"].value + data2["04"].value;
        var dataValue201 = data2["01"]; //完好
        var dataValue202 = data2["02"]; //破损
        var data3 = data["市容环境"];
        var dataSum3 = data3["01"].value + data3["02"].value + data3["03"].value + data3["04"].value;
        var dataValue301 = data3["01"]; //完好
        var dataValue302 = data3["02"]; //破损
        var dataname = ['完好', '完好', '完好'];
        var dataname2 = ['破损', '破损', '破损'];
        var datascale1 = new Array();
        var datascale2 = new Array();



        if (dataSum1 == 0) {
            var datascale101 = {
                value: 0,
                name: dataValue101["value"]
            };

            var datascale201 = {
                value: 0,
                name: dataValue102["value"]
            };

        } else {
            var datascale101 = {
                value: -data1["01"].value / dataSum1 * 100,
                name: dataValue101["value"]
            };

            var datascale201 = {
                value: dataValue102.value / dataSum1 * 100,
                name: dataValue102["value"]
            };

        }
        if (dataSum2 == 0) {
            var datascale102 = {
                value: 0,
                name: dataValue201["value"]
            };

            var datascale202 = {
                value: 0,
                name: dataValue202["value"]
            };

        } else {
            var datascale102 = {
                value: -data2["01"].value / dataSum2 * 100,
                name: dataValue201["value"]
            };

            var datascale202 = {
                value: dataValue202.value / dataSum2 * 100,
                name: dataValue202["value"]
            };

        }
        if (dataSum3 == 0) {
            var datascale103 = {
                value: 0,
                name: dataValue301["value"]
            };

            var datascale203 = {
                value: 0,
                name: dataValue302["value"]
            };

        } else {
            var datascale103 = {
                value: -data3["01"].value / dataSum1 * 100,
                name: dataValue301["value"]
            };

            var datascale203 = {
                value: dataValue302.value / dataSum1 * 100,
                name: dataValue302["value"]
            };

        }


        datascale1.push(datascale101);
        datascale1.push(datascale102);
        datascale1.push(datascale103);

        datascale2.push(datascale201);
        datascale2.push(datascale202);
        datascale2.push(datascale203);






        /*      var datascale101 = {
                  value: -data1["01"].value / dataSum1 * 100,
                  name: dataValue101["value"]
              };
              datascale1.push(datascale101);
              var datascale102 = {
                  value: -data2["01"].value / dataSum2 * 100,
                  name: dataValue201["value"]
              };
              datascale1.push(datascale102);
              var datascale103 = {
                  value: -data3["01"].value / dataSum3 * 100,
                  name: dataValue301["value"]
              };
              datascale1.push(datascale103);

        var datascale2 = new Array();
        var datascale201 = {
            value: dataValue102.value / dataSum1 * 100,
            name: dataValue102["value"]
        };
        datascale2.push(datascale201);
        var datascale202 = {
            value: dataValue202.value / dataSum2 * 100,
            name: dataValue202["value"]
        };
        datascale2.push(datascale202);
        var datascale203 = {
            value: dataValue302.value / dataSum3 * 100,
            name: dataValue302["value"]
        };
        datascale2.push(datascale203);*/



        var datacount1 = new Array();
        datacount1.push({value: 100, name: Math.round(datascale201.value) + "%"});
        datacount1.push({value: 100, name: Math.round(datascale202.value) + "%"});
        datacount1.push({value: 100, name: Math.round(datascale203.value) + "%"});
        var datacount2 = new Array();

        datacount2.push({value: -100, name: Math.round(-datascale101.value) + "%"});
        datacount2.push({value: -100, name: Math.round(-datascale102.value) + "%"});
        datacount2.push({value: -100, name: Math.round(-datascale103.value) + "%"});
        var dataRes = {
            "sum": {
                "sum1": dataSum1,
                "sum2": dataSum2,
                "sum3": dataSum3
            },
            "count1": datacount1,
            "count2": datacount2,
            "name1": dataname,
            "name2": dataname2,
            "scale1": datascale1,
            "scale2": datascale2
        };
        showCitycomponents(dataRes);
    });
}

//城市部件状态的option
function showCitycomponents(dataRes) {
    var option = {
        title: [
            {
                text: '井盖',
                left: '12%',
                top: '2%',
                textAlign: 'center',
                textStyle: {
                    fontSize: 12,
                    color: '#9DCCFF'
                },
            },
            {
                text: dataRes["sum"]["sum1"],
                left: '25%',
                top: '2%',
                textAlign: 'center',
                textStyle: {
                    fontSize: 16,
                    color: '#FFFFFF'
                },
            },
            {
                text: '个',
                left: '33%',
                top: '2%',
                textAlign: 'center',
                textStyle: {
                    fontSize: 14,
                    color: '#0062E6'
                },
            },
            {
                text: '路灯',
                left: '12%',
                top: '34%',
                textAlign: 'center',
                textStyle: {
                    fontSize: 12,
                    color: '#9DCCFF'
                },
            },
            {
                text: dataRes["sum"]["sum2"],
                left: '25%',
                top: '34%',
                textAlign: 'center',
                textStyle: {
                    fontSize: 16,
                    color: '#FFFFFF'
                },
            },
            {
                text: '个',
                left: '33%',
                top: '34%',
                textAlign: 'center',
                textStyle: {
                    fontSize: 14,
                    color: '#0062E6'
                },
            },
            {
                text: '垃圾桶',
                left: '12%',
                top: '62%',
                textAlign: 'center',
                textStyle: {
                    fontSize: 12,
                    color: '#9DCCFF'
                },
            },
            {
                text: dataRes["sum"]["sum3"],
                left: '25%',
                top: '62%',
                textAlign: 'center',
                textStyle: {
                    fontSize: 16,
                    color: '#FFFFFF'
                },
            },
            {
                text: '个',
                left: '33%',
                top: '62%',
                textAlign: 'center',
                textStyle: {
                    fontSize: 14,
                    color: '#406CA9'
                },
            },
        ],
        grid: {
            top: '8%',
            left: '5%',
            right: '5%',
            bottom: '0%',
            containLabel: true,
        },
        xAxis: {
            type: 'value',
            show: false,
            axisLabel: {
                show: false
            },
            splitLine: {
                show: false
            },
            axisTick: {
                show: false
            },
        },
        yAxis: [
            {
            type: 'category',
            top: '10%',
            inverse: true,
            axisLine: {
                show: false,
            },
            axisTick: {
                show: false,
            },
            show: true,
            data: dataRes["name1"],
            axisLabel: {
                color: "#9DCCFF",
                fontSize: 12,
                padding: [0, 55, 0, 0],
                interval:0
            },
            }, {
            type: 'category',
            top: '10%',
            inverse: true,
            axisLine: {
                show: false,
            },
            axisTick: {
                show: false,
            },
            show: true,
            data: dataRes["name2"],
            axisLabel: {
                color: "#9DCCFF",
                fontSize: 12,
                padding: [0, 0, 0, 45]
            },
        }],
        graphic: [
            {
                type: 'image',
                id: 'logo',
                top: '2%',
                left: '4%',
                bounding: 'raw',
                origin: [75, 75],
                style: {
                    image: '/arjccm/static/common/index/images/statIndexCool/jinggai2.png',
                    width: 18,
                    height: 17,
                    opacity: 1
                }
            },
            {
                type: 'image',
                id: 'logo2',
                top: '34%',
                left: '4%',
                bounding: 'raw',
                origin: [75, 75],
                style: {
                    image: '/arjccm/static/common/index/images/statIndexCool/ludeng2.png',
                    width: 13,
                    height: 21,
                    opacity: 1
                }
            },
            {
                type: 'image',
                id: 'logo3',
                top: '62%',
                left: '4%',
                bounding: 'raw',
                origin: [75, 75],
                style: {
                    image: '/arjccm/static/common/index/images/statIndexCool/lajitong2.png',
                    width: 12,
                    height: 17,
                    opacity: 1
                }
            },

        ],
        series: [
            {
                type: 'bar',
                barWidth: 11,
                data: dataRes["scale2"],
                label: {
                    show: true,
                    formatter: function (params) {
                        return params.data.name + '个';
                    },
                    position: 'insideTopRight',
                    textStyle: {
                        fontSize: 14,
                        color: '#FFFFFF'
                    },
                    position: 'top',
                    offset: [16, 0]
                },
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                            offset: 0,
                            color: '#FFC802'
                        }, {
                            offset: 1,
                            color: '#e19209'
                        }]),
                        barBorderRadius: [0, 6, 6, 0],
                    }
                },
            },

            {  // 灰色背景柱状图
                type: 'bar',
                barGap: '-100%',
                barWidth: 11,
                itemStyle: {
                    normal: {
                        color: '#1F4C89',
                    }
                },
                label: {
                    show: true,
                    formatter: function (params) {
                        return params.data.name;
                    },
                    position: 'left',
                    textStyle: {
                        fontSize: 16,
                        color: '#0360E0',
                        fontWeight:'bold'
                    },
                    padding: [0, 5, 0, 0]
                },
                z: -10,
                data: dataRes["count2"]
            },
            {
                type: 'bar',
                barWidth: 11,
                label: {
                    show: true,
                    formatter: function (params) {
                        return params.data.name + '个';
                    },
                    position: 'insideTopLeft',
                    textStyle: {
                        fontSize: 14,
                        color: '#FFFFFF'
                    },
                    position: 'top',
                    offset: [-16, 0]
                },
                data: dataRes["scale1"],
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                            offset: 0,
                            color: '#008BEF'
                        }, {
                            offset: 1,
                            color: '#48C2F4'
                        }]),
                        barBorderRadius: [6, 0, 0, 6],
                    }
                }
            },
            {  // 灰色背景柱状图
                type: 'bar',
                barGap: '-100%',
                barWidth: 11,
                itemStyle: {
                    normal: {
                        color: '#1F4C89',
                    }
                },
                label: {
                    show: true,
                    formatter: function (params) {
                        return params.data.name;
                    },
                    position: 'right',
                    textStyle: {
                        fontSize: 16,
                        color: '#ECC31C'
                    },
                    padding: [0, 0, 0, 5]
                },
                z: -10,
                data: dataRes["count1"]
            },


        ]
    };
    leftContent3Charts.setOption(option);
}

//基本信息
function getHouseData(noCache) {
    $.getJSON(ctx + "/report/ccmPeopleAmount/getListTypeAll", {"noCache": noCache}, function (data) {
        var value = Number(data[0].value);
        var value1 = Number(data[1].value);
        var value2 = Number(data[2].value);
        var value3 = Number(data[3].value);
        var count = Number(value) + Number(value1) + Number(value2) + Number(value3);
        var dataList = [{
            value: value,
            name: '自住'
        },
            {
                value: value1,
                name: '出租'
            },
            {
                value: value2,
                name: '空置'
            },
            {
                value: value3,
                name: '其他'
            }
        ];
        var dataMap = {
            '自住': value,
            '出租': value1,
            '空置': value2,
            '其他': value3
        };
        showHouseData(dataList, dataMap, count);
    });
}

//基本信息的option
function showHouseData(dataList, dataMap, count) {
    var option = {
        color: ['#0F8CDB', '#F86422', '#E7BA1B', '#3E64D5'],
        tooltip: {
            trigger: 'item',
            formatter: "{b}: {c} ({d}%)"
        },
        title: {
            text: '{title|   房屋数据}{value|' + count + '}{unit|间}',
            x: '160',
            y: 'top',
            textStyle: {
                rich: {
                    title: {
                        fontFamily: 'Microsoft YaHei',
                        fontWeight: 400,
                        fontSize: 12,
                        lineHeight: 39,
                        color: "#FFFFFF"
                    },
                    value: {
                        fontFamily: 'DIN Alternate',
                        fontWeight: 'bold',
                        fontSize: 30,
                        lineHeight: 39,
                        padding: [0, 9, 0, 30],
                        color: "#F9CB42"
                    },
                    unit: {
                        fontFamily: 'Microsoft YaHei',
                        fontWeight: 400,
                        fontSize: 14,
                        lineHeight: 39,
                        color: "#406CA9"
                    }
                }
            },
        },
        legend: {
            orient: 'vertical',
            x: 'right',
            data: ['自住', '出租', '空置', '其他'],
            left: '50%',
            top: '20%',
            formatter: function (name) {
                return "{title|" + name + "}{value|" + (dataMap[name]) + "}{unit|  间}"
            },
            textStyle: {
                align:'right',
                rich: {
                    align:'right',
                    title: {
                        fontFamily: 'Microsoft YaHei',
                        fontWeight: 400,
                        width: 59,
                        fontSize: 12,
                        lineHeight: 25,
                        color: "#FFFFFF"
                    },
                    value: {
                        fontFamily: 'DIN Alternate',
                        fontWeight: 'bold',
                        fontSize: 20,
                        lineHeight: 25,
                        padding: [0, 6, 0, 10],
                        color: "#F9CB42",
                        width: 30,
                        align:'right'
                    },
                    unit: {
                        fontFamily: 'Microsoft YaHei',
                        fontWeight: 400,
                        fontSize: 14,
                        lineHeight: 25,
                        color: "#406CA9"
                    }
                }
            }
        },
        graphic: [
            {
                type: 'image',
                id: 'logo',
                left: '29%',
                top: '6%',
                bounding: 'raw',
                origin: [75, 75],
                style: {
                    image: '/arjccm/static/common/index/images/statIndexCool/fangwu2.png',
                    width: 18,
                    height: 18,
                    opacity: 1
                }
            },

        ],
        series: [
            {
                type: 'pie',
                radius: ['40%', '55%'],
                avoidLabelOverlap: false,
                center: ['30%', '50%'],
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: dataList
            }
        ]
    };
    rightContent1Charts.setOption(option);
}

//工作力量
function getWorkForce(noCache) {
    $.getJSON(ctx + "/report/ccmReportOthers/getnumOfWorkPower", {"noCache": noCache}, function (data) {
        var option = showDistribution(data.dataX, data.bgNum, data.dataY);
        rightContent2Charts.setOption(option);
    });
}

//重点企业
function getKeyenterprisesshow(noCache) {
    $.getJSON(ctx + "/report/ccmReportOthers/findKeyPlace", {"noCache": noCache}, function (data) {
        var dataList = JSON.parse(data[0]);
        if (dataList.length > 0) {
            var dataType = new Array();
            var dataSeries = new Array();
            var maxData = 100;
            var maxDataList = new Array();
            for (var i = 0; i < dataList.length; i++) {
                if (dataList[i].type != '无') {
                    dataType.push(dataList[i].type);
                    dataSeries.push(dataList[i].value);
                    var series = {
                        value: maxData,
                        name: dataList[i].value
                    };
                    maxDataList.push(series);
                }
            }
            var sumData = eval(dataSeries.join("+"));
            for (var i = 0; i < dataSeries.length; i++) {
                dataSeries[i] = dataSeries[i] / sumData * maxData;
            }
            var option = showKeyenterprisesshow(dataType, dataSeries, maxDataList);
            rightContent3Charts.setOption(option);
        }
    });
}

//重点企业的option
function showKeyenterprisesshow(dataType, dataSeries, maxDataList) {
    var option = {
        grid: {
            left: '6%',
            right: '10%',
            bottom: '5%',
            top: '6%',
            containLabel: true,
        },
        xAxis: {
            type: 'value',
            show: false,
            axisLabel: {
                show: false
            },
            splitLine: {
                show: false
            },
            axisTick: {
                show: false
            }
        },
        yAxis: {
            type: 'category',
            inverse: true,
            axisLine: {
                show: false,
            },
            axisTick: {
                show: false,
            },
            show: true,
            data: dataType,
            axisLabel: {
                color: "#FFFFFF",
                fontSize: 12,
                interval: 0,
                padding: [3, 10, 3, 0]
            },
            padding: [0, 0, 0, 0]
        },
        series: [
            {
                type: 'bar',
                stack: '总量',
                barWidth: 11,
                data: dataSeries,
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                            offset: 0,
                            color: '#1DF1AC'
                        }, {
                            offset: 1,
                            color: '#0271FA'
                        }]),
                    }
                }
            },
            {
                type: 'bar',
                barGap: '-100%',
                barWidth: 11,
                itemStyle: {
                    normal: {
                        color: '#1D5293',
                    }
                },
                label: {
                    show: true,
                    formatter: function (params) {
                        return params.data.name + '{unity| 个 }';
                    },
                    position: 'right',
                    textStyle: {
                        color: '#F9CB42',
                        fontSize: 16,
                        fontWeight: 'normal',
                        padding: [0, 0, 0, 10],
                        rich:{
                            unity: {
                                fontFamily: 'Microsoft YaHei',
                                fontWeight: 400,
                                fontSize: 14,
                                lineHeight: 25,
                                color: "#406CA9"
                            },
                        },
                    },
                },
                z: -10,
                data: maxDataList
            }
        ]
    };
    return option;
}

/***************************网格管理End****************************/
/***************************安全生产Start****************************/
var keyEnterprisesChart = null;
var preventiveChart = null;
var distributionChart = null;
var enterpriseChart = null;
var analysisChart = null;
var colorList = ['#724AC3', '#1E88C8', '#F57751', '#ABC31A', '#04FEF9', '#77ED4D', '#48A3FF', '#FFE822', '#FF61D8', '#FCCF77'];

//安全生产重点企业
function getKeyEnterprises() {
    keyEnterprisesChart = echarts.init(document.getElementById('echLeftContent1'));
    keyEnterprisesChart.resize();
    keyEnterprisesChart.clear();
    var noCache =  new Date();
    $.getJSON(ctx + "/org/ccmOrgNpse/getDangCompData", {"noCache": noCache}, function (response) {
        var yes = response[1].value;
        var no = response[0].value;
        var num = (Number(yes) / (Number(yes) + Number(no)) * 100);
        num = num.toFixed(1);
        var persent = num + '%'
        showKeyEnterprises(persent, no, yes);
    });
}

//安全生产防范检查
function getPreventive(data) {
    preventiveChart = echarts.init(document.getElementById('echleftContent2'));
    preventiveChart.resize();
    preventiveChart.clear();
    // var dataX = ['2019-05', '2019-06', '2019-07', '2019-08', '2019-09', '2019-10'];
    // var dataY = [120, 132, 101, 164, 90, 130];
    var dataX = data['data'];
    var dataY = data['value'];
    showPreventive(dataX, dataY);
}

//安全事故分布
function getDistribution(data) {
    distributionChart = echarts.init(document.getElementById('echLeftContent3'));
    distributionChart.resize();
    distributionChart.clear();
    var dataX = data['type'];
    var dataY = data['data'];
    var bgNum = data['Maxnum'];
    // var dataX = ['庙祝警务室', '上庄警务室', '集聚区警务室', '窦沟警务室'];
    // var dataY = [100, 152, 200, 334];
    // var bgNum = [390, 390, 390, 390];
    var option = showDistribution(dataX, bgNum, dataY);
    distributionChart.setOption(option);
}

//安全事故分析
function getAnalysis() {
    var divWidth = $("#rightContent0Body").width();
    var divHeight = $("#rightContent0Body").outerHeight(true);
    $('#echRightContent0').css("width", divWidth + "px");
    $('#echRightContent0').css("height", divHeight + "px");
    analysisChart = echarts.init(document.getElementById('echRightContent0'));
    analysisChart.resize();
    analysisChart.clear();
    var noCache =  new Date();
    $.getJSON(ctx + "/event/ccmEventIncident/getSafeAnalysisData", {"noCache": noCache}, function (data) {
        var happen = data["happen"];
        var dataX = new Array();
        var dataY = new Array();
        for (var i = 0; i < happen.length; i++) {
            var type = happen[i]["type"];
            var value = happen[i]["value"];
            dataX.push(type);
            dataY.push(value);
        }
        var typeData = data["type"];
        var dataListA = new Array();
        var legendListA = new Array();
        for (var i = 0; i < typeData.length; i++) {
            var type = typeData[i]["type"];
            var value = typeData[i]["value"];
            dataListA.push({
                value: value,
                name: type,
            });
            legendListA.push(type);
        }
        var level = data["level"];
        var dataListB = new Array();
        var legendListB = new Array();
        for (var i = 0; i < level.length; i++) {
            var type = level[i]["type"];
            var value = level[i]["value"];
            dataListB.push({
                value: value,
                name: type,
            });
            legendListB.push(type);
        }
        showAnalysis(dataListA, legendListA, dataListB, legendListB, dataX, dataY);
    });
}

//重点企业分布
function getEnterprise() {

    var arr = new Array();
    var arr1 = new Array();

    $.ajax({
        type: "get",
        url: ctx + "/org/ccmOrgNpse/getCompImpoTypeTrue",
        async: false,
        success: function (data) {
            data = jQuery.parseJSON(data);
            for (var j = 0; j < data.length; j++) {
                arr.push({
                    name: data[j].type,
                    value: data[j].value
                })
                arr1.push(data[j].type);
            }

        }

    })


    var divWidth = $("#rightContent3Body").width();
    var divHeight = $("#rightContent3Body").outerHeight(true);
    $('#echRightContent3').css("width", divWidth + "px");
    $('#echRightContent3').css("height", divHeight + "px");
    enterpriseChart = echarts.init(document.getElementById('echRightContent3'));
    enterpriseChart.resize();
    enterpriseChart.clear();
    var colorListEnterprise = ['#F97952', '#4361CF', '#EBBE1B', '#12B8BB', '#B04DF5', '#0095EF', '#1F78B4', '#A6CEE3', '#B2DF8A', '#33A02C', '#FB9A99', '#E31A1C'];
    var dataX = arr1;
    var dataList = arr;
    var option = showEnterprise(colorListEnterprise, dataX, dataList);
    enterpriseChart.setOption(option);
}

/***************************安全生产End****************************/
/***************************治安态势Start****************************/
//今日事件
function getTodayEvent(){


    var dataList = [
        {
            name: '待办事件',
            value: ''
        },{
            name: '办理中事件',
            value: ''
        },{
            name: '已办事件',
            value: ''
}]
    var count=0;
    $.ajax({
        type:"get",
        url:ctx+"/event/ccmEventCasedeal/getNumByHandelStatus",
        async:false,
        success:function (data) {
           if(count){
               count=(parseInt)(data.tobe)+(parseInt)(data.done)+(parseInt)(data.ing);
               dataList[0].value=data.tobe;
               dataList[1].value=data.done;
               dataList[2].value=data.ing;
           }else {
               count=0;
               for(var i=0;i<dataList.length;i++){
                   dataList[i].value=0;
               }
           }

        }
    })
    //console.log(count);
    //console.log(dataList)
    showTodayEvent(dataList,count);
}
function showTodayEvent(data,count){

    var titleArr = [],
        seriesArr = [];
    colors = [
        [{type: 'linear',x: 0,y: 0,x2: 0.4,y2: 1, colorStops: [{offset: 0,color: '#CD50F2'}, {
                offset: 1,color: '#4043FF'}],globalCoord: false}, '#414A58'],
        [{type: 'linear',x: 0,y: 0,x2: 0.4,y2: 1, colorStops: [{offset: 0,color: '#FF8C2D'}, {
                offset: 1,color: '#F33758'}],globalCoord: false}, '#414A58'],
        [{type: 'linear',x: 0,y: 0,x2: 0.4,y2: 1, colorStops: [{offset: 0,color: '#00CAF0'}, {
                offset: 1,color: '#23FA9D'}],globalCoord: false}, '#414A58']
    ]
    //console.info( colors[0][0]['colorStops'][0]['color'])
    data.forEach(function(item, index) {
        titleArr.push({
            text: item.name,
            left: index * 30 + 20 + '%',
            top: 170,
            textAlign: 'center',
            textStyle: {
                fontWeight: 'normal',
                fontSize: '16',
                color: colors[index][0]['colorStops'][0]['color'],
                textAlign: 'center',
            },
        });
        seriesArr.push({
            name: item.name,
            type: 'pie',
            clockWise: false,
            radius: [42,53],
            itemStyle: {
                normal: {
                    color: colors[index][0],
                    shadowColor: colors[index][0],
                    shadowBlur: 0,
                    label: {
                        show: false
                    },
                    labelLine: {
                        show: false
                    },
                }
            },
            hoverAnimation: false,
            center: [index * 30 + 20 + '%', 90],
            data: [{
                value: item.value,
                label: {
                    normal: {
                        formatter: function(params) {
                            return params.value ;
                        },
                        position: 'center',
                        show: true,
                        textStyle: {
                            fontSize: '16',
                            fontWeight: 'bold',
                            color: colors[index][0]['colorStops'][0]['color']
                        }
                    }
                },
            }, {
                value: count - item.value,
                name: 'invisible',
                itemStyle: {
                    normal: {
                        color: colors[index][1]
                    },
                    emphasis: {
                        color: colors[index][1]
                    }
                }
            }]
        })
    });

    var option = {
        //backgroundColor: "#fff",
        title: titleArr,
        series: seriesArr
    }
    var divWidth = $("#leftContent1Body").width();
    var divHeight = $("#leftContent1Body").outerHeight(true);
    $('#echLeftContent1').css("width", divWidth + "px");
    $('#echLeftContent1').css("height", divHeight + "px");
    var echLeftContent = echarts.init(document.getElementById('echLeftContent1'));
    echLeftContent.resize();
    echLeftContent.clear();
    echLeftContent.setOption(option);
}
//案事件处理统计
function showEventProcessing() {
    var echLeftContent = echarts.init(document.getElementById('echLeftContent1'));
    echLeftContent.resize();
    echLeftContent.clear();

}

//安全事故分析
function showSafetyAccident() {


    var arr1 = new Array();
    var arr2 = new Array();
    var arr3 = new Array();
    var arr4 = new Array();

    $.ajax({
        type: "get",
        url: ctx + "/report/ccmIncidentBegin/getItemByProperty",
        async: false,
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                arr1.push({
                    name: data[i].type,
                    value: data[i].value
                })
            }
        }

    })

    $.ajax({
        type: "get",
        url: ctx + "/report/ccmIncidentBegin/getItemByScale",
        async: false,
        success: function (data) {
            for (var i = 0; i < data.result.length; i++) {
                arr2.push({
                    name: data.result[i].type,
                    value: data.result[i].value
                })
            }
        }

    })
    $.ajax({
        type: "get",
        url: ctx + "/report/ccmIncidentBegin/findSumByEventType",
        async: false,
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                arr3.push({
                    name: data[i].type,
                    value: data[i].value
                })
            }
        }

    })
    $.ajax({
        type: "get",
        url: ctx + "/report/ccmIncidentBegin/findEventFenLeiData",
        async: false,
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                arr4.push({
                    name: data[i].type,
                    value: data[i].value
                })
            }
        }

    })

    var dateList, dataList;

    $.ajax({
        type: "get",
        url: ctx + "/event/ccmEventIncident/getCountEventByNumber",
        async: false,
        success: function (data) {
            dateList = data.dateList;
            dataList = data.dataList;
        }

    })
    var piebg = {
        name: '相关背景',
        type: 'pie',
        labelLine: {
            normal: {
                show: false
            }
        },
        data: [{
            value: 0
        }],
        avoidLabelOverlap: false,
        animation: false
    };
    var option = {
        title: [{
            text: '事故性质',
            textStyle: {
                fontSize: 14,
                color: "#9DCCFF"
            },
            textAlign: "center",
            x: '18%',
            y: '38%',
        }, {
            text: '事件分级',
            textStyle: {
                fontSize: 14,
                color: "#9DCCFF"
            },
            textAlign: "center",
            x: '48%',
            y: '38%',
        }, {
            text: '事件类型',
            textStyle: {
                fontSize: 14,
                color: "#9DCCFF"
            },
            textAlign: "center",
            x: '78%',
            y: '38%',
        }, {
            text: '事故发生统计',
            textStyle: {
                fontSize: 16,
                color: "#9DCCFF"
            },
            textAlign: "center",
            x: '14%',
            y: '50%',
        }],
        tooltip: {
            show: true,
            trigger: 'item'
        },
        grid: {
            left: '6%',
            top: '64%',
            right: '6%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [
            {
                data: dateList,
                axisTick: {
                    alignWithLabel: true,
                    show: false,
                },
                axisLine: {
                    lineStyle: {
                        color: '#FFFFFF',
                        opacity: 0.2
                    }
                },
                axisLabel: {
                    color: '#9DCCFF',
                    fontsize: '12px',
                    rotate:60,
                }
            }
        ],
        yAxis: [
            {
                type: 'value', splitLine: {
                    show: false
                },
                axisLine: {
                    lineStyle: {
                        color: '#FFFFFF',
                        opacity: 0.2
                    }
                },
                axisLabel: {
                    color: '#9DCCFF',
                    fontsize: '12px'
                },
                axisTick: {
                    alignWithLabel: true,
                    show: true,
                    lineStyle: {
                        color: '#FFF',
                        opacity: 0.2
                    }

                },
            },
        ],
        series: [
            {
                name: '事件性质',
                type: 'pie',
                radius: [0, 40],
                center: ['20%', '22%'],
                roseType: 'radius',
                color: ['#C14F3A', '#EBBE1B', '#12B8BB', '#1E88C8', '#1E88C8'],
                itemStyle: {
                    normal: {
                        label: {
                            show: false
                        },
                        labelLine: {
                            show: false
                        },
                    }
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{b} : {c}<br/> ({d}%)",
                    position: ['65%', '65%']
                },
                data: arr1
            },

            Object.assign({}, piebg, {
                radius: ['0', '51'],
                center: ['20%', '22%'],
                itemStyle: {
                    normal: {
                        color: {
                            type: 'radial',
                            x: 0.5,
                            y: 0.5,
                            r: 0.5,
                            colorStops: [{
                                offset: 0,
                                color: 'rgba(0,0,0,0.5)' // 0% 处的颜色
                            },
                                {
                                    offset: 0.9,
                                    color: 'rgba(0,0,0,0.23)' // 0% 处的颜色
                                },
                                {
                                    offset: 1,
                                    color: 'rgba(160,247,255,0.23)' // 100% 处的颜色
                                }
                            ],
                            globalCoord: false // 缺省为 false
                        }
                    },
                },
                z: -10,
            }),

            {
                name: '事件分级',
                type: 'pie',
                color: ['#4361CF', '#EBBE1B', '#12B8BB', '#ABC317'],
                radius: [0, 40],
                center: ['50%', '22%'],
                roseType: 'radius',
                itemStyle: {
                    normal: {
                        label: {
                            show: false
                        },
                        labelLine: {
                            show: false
                        },
                    }
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{b} : {c}<br/> ({d}%)",
                    position: ['65%', '65%']
                },
                data: arr2
            },
            Object.assign({}, piebg, {
                radius: ['0', '51'],
                center: ['50%', '22%'],
                itemStyle: {
                    normal: {
                        color: {
                            type: 'radial',
                            x: 0.5,
                            y: 0.5,
                            r: 0.5,
                            colorStops: [{
                                offset: 0,
                                color: 'rgba(0,0,0,0.5)' // 0% 处的颜色
                            },
                                {
                                    offset: 0.9,
                                    color: 'rgba(0,0,0,0.23)' // 0% 处的颜色
                                },
                                {
                                    offset: 1,
                                    color: 'rgba(160,247,255,0.23)' // 100% 处的颜色
                                }
                            ],
                            globalCoord: false // 缺省为 false
                        }
                    },
                },
                z: -10,
            }),
            {
                name: '事件类型',
                type: 'pie',
                color: ['#E47800', '#EBBE1B', '#12B8BB', '#1E88C0'],
                radius: [0, 40],
                roseType: 'radius',
                center: ['80%', '22%'],
                tooltip: {
                    trigger: 'item',
                    formatter: "{b} : {c}<br/> ({d}%)",
                    position: ['65%', '65%']
                },
                itemStyle: {
                    normal: {
                        label: {
                            show: false
                        },
                        labelLine: {
                            show: false
                        },
                    }
                },

                data: arr3
            },
            Object.assign({}, piebg, {
                radius: ['0', '51'],
                center: ['80%', '22%'],
                itemStyle: {
                    normal: {
                        color: {
                            type: 'radial',
                            x: 0.5,
                            y: 0.5,
                            r: 0.5,
                            colorStops: [{
                                offset: 0,
                                color: 'rgba(0,0,0,0.5)' // 0% 处的颜色
                            },
                                {
                                    offset: 0.9,
                                    color: 'rgba(0,0,0,0.23)' // 0% 处的颜色
                                },
                                {
                                    offset: 1,
                                    color: 'rgba(160,247,255,0.23)' // 100% 处的颜色
                                }
                            ],
                            globalCoord: false // 缺省为 false
                        }
                    },
                },
                z: -10,
            }),{
                name: '事故发生',
                color: ['#F97952'],
                type: 'bar',
                barWidth: '25%',
                data: dataList
            },
        ]
    };
    var divWidth = $("#leftContent4Body").width();
    var divHeight = $("#leftContent4Body").outerHeight(true);
    $('#echLeftContent4').css("width", divWidth + "px");
    $('#echLeftContent4').css("height", divHeight + "px");
    var echLeftContent = echarts.init(document.getElementById('echLeftContent4'));
    echLeftContent.resize();
    echLeftContent.clear();
    echLeftContent.setOption(option);
}

//事件处理情况
function showEventDeal() {

    var dateList, dataList, lineList;

    $.ajax({
        type: "get",
        url: ctx + "/event/ccmEventIncident/getCountEventByNumber",
        async: false,
        success: function (data) {
            dateList = data.dateList;
            dataList = data.dataList;
        }

    })
    $.ajax({
        type: "get",
        url: ctx + "/event/ccmEventIncident/getCountEventByLine",
        async: false,
        success: function (data) {
            lineList = data.lineList;
        }

    })


    var option = {
        tooltip: {
            trigger: 'item',
        },
        legend: {
            data: ['案事件数', '处理率'],
            textStyle: {
                color: '#9DCCFF'
            }
        },
        xAxis: [{
            type: 'category',
            data: dateList,
            axisLabel: {
                show: true,
                color: '#9DCCFF',
                fontsize: '12px',
                rotate:60,
            },
            axisLine: {
                lineStyle: {
                    color: '#FFFFFF',
                    opacity: 0.2,
                }
            },
            axisTick: {
                show:false,
                alignWithLabel: true,
            }
        }],
        yAxis: [{
            type: 'value',
            axisLabel: {
                formatter: '{value} 件',
                color: '#9DCCFF',
                fontsize: '12px'
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#FFF',
                    opacity: 0.2
                }
            },
            axisTick: {
                lineStyle: {
                    color: '#FFFFFF',
                    opacity: 0.2
                }
            },
            splitLine: {
                show: false,
            },
        },
            {
                type: 'value',
                splitLine: {
                    show: false
                },
                axisLabel: {
                    formatter: '{value} °%',
                    color: '#9DCCFF',
                    fontsize: '12px'
                },
                axisTick: {
                    lineStyle: {
                        color: '#FFFFFF',
                        opacity: 0.2
                    }
                },
                axisLine: {
                    show: true,
                    lineStyle: {
                        color: '#FFF',
                        opacity: 0.2
                    }
                },
            }
        ],
        series: [{
            name: '案事件数',
            type: 'bar',
            barWidth: '25%',
            data: dataList,
            itemStyle: {
                normal: {
                    color: '#724AC3'
                }
            },
        },
            {
                name: '处理率',
                type: 'line',
                yAxisIndex: 1,
                data: lineList,
                itemStyle: {
                    normal: {
                        color: '#EBBE1B'
                    }
                },
                symbolSize: 8,
            }
        ]
    };
    var divWidth = $("#rightContent2Body").width();
    var divHeight = $("#rightContent2Body").outerHeight(true);
    $('#echRightContent2').css("width", divWidth + "px");
    $('#echRightContent2').css("height", divHeight + "px");
    var echRightContent = echarts.init(document.getElementById('echRightContent2'));
    echRightContent.resize();
    echRightContent.clear();
    echRightContent.setOption(option);

}

//事件区域分布
function getAreaEch() {

    var arr1 = new Array();
    var arr2 = new Array();

    $.ajax({
        type: "get",
        url: ctx + "/event/ccmEventIncident/getSafeDisData",
        async: false,
        success: function (data) {
            data = jQuery.parseJSON(data);
            for (var j = 0; j < data.length; j++) {
                arr1.push(data[j].type);
                arr2.push(data[j].value);
            }

        }

    })

    var divWidth = $("#rightContent3Body").width();
    var divHeight = $("#rightContent3Body").outerHeight(true);
    $('#echRightContent3').css("width", divWidth + "px");
    $('#echRightContent3').css("height", divHeight + "px");
    distributionChart = echarts.init(document.getElementById('echRightContent3'));
    distributionChart.resize();
    distributionChart.clear();
    var dataX = arr1;
    var dataY = arr2;
    var bgNum = [arr2[0], arr2[0], arr2[0], arr2[0],arr2[0]];
    var option = showDistribution(dataX, bgNum, dataY);
    distributionChart.setOption(option);
}

function compareNumbers(a, b) {
    return a - b;
}

/***************************治安态势End****************************/
//视频区域分布
function getAreaDistribution(noCache) {
    $.getJSON(ctx + "/ccmsys/ccmDevice/selectDeviceByArea", {"noCache": noCache}, function (data) {
        var dataX = data['type'];
        var dataY = data['data'];
        var bgNum = data['Maxnum'];
        var option = showDistribution(dataX, bgNum, dataY);
        rightContent3Charts.setOption(option);
    });
}

//重点青少年帮扶方式
function getHelpingWay(data) {
    distributionChart = echarts.init(document.getElementById('echLeftContent1'));
    distributionChart.resize();
    distributionChart.clear();
    var option = showDistribution(data.dataX, data.bgNum, data.dataY);
    distributionChart.setOption(option);
}

//点位建设趋势
function getVideoControl(noCache) {
    $.getJSON(ctx + "/ccmsys/ccmDevice/selectByCreateDate", {"noCache": noCache}, function (data) {
        var colorListEnterprise = ['#F97952', '#4361CF', '#EBBE1B', '#12B8BB', '#B04DF5', '#0095EF', '#1F78B4', '#A6CEE3', '#B2DF8A', '#33A02C', '#FB9A99', '#E31A1C'];
        var dataX = data['data'];
        var dataList = data['value'];
        var option = showEnterprise(colorListEnterprise, dataX, dataList);
        leftContent1Charts.setOption(option);
    });
}

//本月人口信息
function getPeopleCount(data) {
    console.info(data);
    $('#azbj').text(data[0]);
    $('#sqjz').text(data[1]);
    $('#jsza').text(data[2]);
    $('#drug').text(data[3]);
    $('#aids').text(data[4]);
    $('#visit').text(data[5]);
    $('#sjry').text(data[6]);
    $('#dangures').text(data[7]);
    $('#lsry').text(data[8]);
    $('#key').text(data[9]);
    $('#care').text(data[10]);
    $('#older').text(data[11]);
}

function showKeyEnterprises(persent, no, yes) {
    var val1data2 = [{
        value: no,
        name: '',
    },
        {
            value: yes,
            name: '',
        }
    ]
    var sum=(Number(yes) + Number(no));
    var arr = ['middleLost', yes, val1data2, '今日完成进度']
    var piebg = {
        type: 'pie',

        data: [{
            value: 0
        }],
    };
    option = {

        title: [{
            top: '28%',
            left: '26%',


            subtext: (arr[1] * 100 / sum).toFixed(1) + '%',
            subtextStyle: {
                color: '#fff',
                fontSize: 22
            }
        }, {
            text: '危化企业',
            x: '22%',
            y: '75%',
            textStyle: {
                color: '#9DCCFF',
                fontSize: '18'
            }
        }],
        series: [{
            type: 'liquidFill',
            radius: '45%',
            data: [{
                value: (Number(arr[1])  / sum).toFixed(1),
                itemStyle: {
                    normal: {
                        color: '#53d5ff',
                        opacity: 0.6
                    }
                }
            }],
            color: ['#C24FF3'],
            center: ['45%', '37%'],
            backgroundStyle: {
                color: '#3572D0'
            },
            label: {
                normal: {
                    formatter: '',
                    textStyle: {
                        fontSize: 12
                    }
                }
            },
            outline: {
                itemStyle: {
                    borderWidth: 0
                },
                borderDistance: 0
            }
        }, {
            type: 'pie',
            radius: ['55%', '65%'],
            color: [{
                type: 'linear',
                x: 0,
                y: 0,
                x2: 0.4,
                y2: 1,
                colorStops: [{
                    offset: 0,
                    color: '#CD50F2'
                }, {
                    offset: 1,
                    color: '#4043FF'
                }],
                globalCoord: false
            }, '#404D5D'],
            center: ['45%', '37%'],
            hoverAnimation: false, ////设置饼图默认的展开样式
            label: {
                show: false,
                normal: {

                    position: ''
                },
            },
            labelLine: {
                normal: {
                    show: false
                }
            },

            itemStyle: { // 此配置
                normal: {
                    borderWidth: 0,
                    borderColor: '#fff',

                },

            },
            data: arr[2]
        }, Object.assign({}, piebg, {
            radius: ['0%', '74%'],
            center: ['45%', '37%'],
            itemStyle: {
                normal: {
                    color: {
                        type: 'radial',
                        x: 0.5,
                        y: 0.5,
                        r: 0.5,
                        colorStops: [{
                            offset: 0,
                            color: 'rgba(0,0,0,0.5)' // 0% 处的颜色
                        },
                            {
                                offset: 0.9,
                                color: 'rgba(0,0,0,0.23)' // 0% 处的颜色
                            },
                            {
                                offset: 1,
                                color: 'rgba(160,247,255,0.23)' // 100% 处的颜色
                            }
                        ],
                        globalCoord: false // 缺省为 false
                    }
                },
            },
            z: -10,
        }),

        ]
    }

    keyEnterprisesChart.setOption(option);
}

function showPreventive(dataX, dataY) {
    var option = {
        grid: {
            left: '6%',
            top: '16%',
            right: '6%',
            bottom: '7%',
            containLabel: true
        },
        tooltip: {
            show: true,
            trigger: 'item'
        },
        xAxis: [{
            type: 'category',
            axisLabel: {
                color: '#9DCCFF',
                fontSize: 12,
                fontFamily: 'MicrosoftYaHei'
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#FFF',
                    opacity: 0.2
                }
            },
            axisTick: {
                show: true,
                lineStyle: {
                    color: '#FFF',
                    opacity: 0.2
                }
            },
            splitLine: {
                show: true,
                lineStyle: {
                    color: '#FFF',
                    opacity: 0.2
                }
            },
            data: dataX
        }],
        yAxis: [{
            type: 'value',
            axisLabel: {
                formatter: '{value}',
                textStyle: {
                    color: 'rgba(255,255,255,0.5)',
                    fontSize: 12,
                    fontFamily: 'MicrosoftYaHei'
                }
            },
            max: function (value) {
                return (value.max + 25)/10*10;
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#FFF',
                    opacity: 0.2
                }
            },
            axisTick: {
                show: true,
                lineStyle: {
                    color: '#FFF',
                    opacity: 0.2
                }
            },
            splitLine: {
                show: true,
                lineStyle: {
                    color: '#FFF',
                    opacity: 0.2
                }
            }
        }],
        series: [{
            name: '安全生产防范检查',
            type: 'line',
            stack: '总量',
            symbol: 'circle',
            symbolSize: 10,
            itemStyle: {
                normal: {
                    color: '#1FF4A7',
                    lineStyle: {
                        color: "#1FF4A7",
                        width: 2
                    },
                    areaStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(31,244,167,1)'
                        }, {
                            offset: 1,
                            color: 'rgba(3,206,233,0)'
                        }])
                    },
                    shadowColor: 'rgba(3,240,254,1)',
                    shadowBlur: 20,
                }
            },

            markPoint: {
                symbol:'image:///arjccm/static/images/rect.png',
                symbolKeepAspect:true,
                symbolSize:[40,40],
                symbolOffset:[0, -25] ,
                data: [{
                    type: 'max',
                    name: '最大值'
                },
                    {
                        type: 'min',
                        name: '最小值'
                    }
                ],
                label: {
                    color: '#FFFFFF ',
                    fontSize: 16,
                    fontFamily: 'MicrosoftYaHei',
                    padding:[0,0,8,0],
                },
                itemStyle: {
                    normal: {
                        color: '#1FF4A7'
                    }
                }
            },
            data: dataY
        }]
    };
    preventiveChart.setOption(option);
}

function showDistribution(dataX, bgNum, dataY) {
    var option = {
        color: colorList,
        grid: {
            left: '6%',
            top: '16%',
            right: '6%',
            bottom: '7%',
            containLabel: true
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        xAxis: [{
            type: 'category',
            data: dataX,
            axisTick: {
                show: false,
                alignWithLabel: true
            },
            axisLine: {
                lineStyle: {
                    color: '#FFF',
                    opacity: 0.2
                }
            },
            axisLabel: {
                color: '#9DCCFF'
            }
        }],
        yAxis: {
            type: 'value',
            axisLine: {
                lineStyle: {
                    color: '#FFF',
                    opacity: 0.2
                }
            },
            axisTick: {
                show: true,
                lineStyle: {
                    color: '#FFF',
                    opacity: 0.2
                }
            },
            splitLine: {
                show: false
            },
            axisLabel: {
                color: 'rgba(255,255,255,0.5)',
                fontSize: 12,
                fontFamily: 'MicrosoftYaHei'
            }
        },
        series: [{
            tooltip: {
                show: false
            },
            name: 'bg',
            type: 'pictorialBar',
            barWidth: '60%',
            silent: true,
            symbol: 'rect',
            symbolRepeat: true,
            symbolMargin: 1,
             barMinHeight: 150,
            symbolSize: [25, 10],
            itemStyle: {
                normal: {
                    show: true,
                    color: function (params) {
                        return colorList[params.dataIndex]
                    },
                    barBorderRadius: 50,
                    borderWidth: 0,
                    opacity: 0.3,
                    borderColor: '#333',
                }
            },
            data: bgNum
        }, {
            type: 'pictorialBar',
            animation: true,
            animationDuration: 600,
            symbol: 'rect',
            symbolRepeat: true,
            symbolSize: [25, 10],
            symbolMargin: 1,
            barWidth: '60%',
            itemStyle: {
                normal: {
                    color: function (params) {
                        return colorList[params.dataIndex]
                    },
                    label: {
                        show: false
                    }
                }
            },
            data: dataY
        }]
    };
    return option;
}

function showEnterprise(colorListEnterprise, dataX, dataList) {
    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        grid: {
            left: '6%',
            top: '16%',
            right: '6%',
            bottom: '7%',
            containLabel: true
        },
        xAxis: [{
            type: 'category',
            axisTick: {
                show: false
            },
            axisLine: {
                lineStyle: {
                    color: '#FFF',
                    opacity: 0.2
                }
            },
            axisLabel: {
                textStyle: {
                    fontSize: 12,
                    color: '#9DCCFF'
                }
            },
            data: dataX,
        }],
        yAxis: {
            type: 'value',
            axisLine: {
                lineStyle: {
                    color: '#FFF',
                    opacity: 0.2
                }
            },
            axisTick: {
                show: true,
                lineStyle: {
                    color: '#FFF',
                    opacity: 0.2
                }
            },
            splitLine: {
                show: false
            },
            axisLabel: {
                color: 'rgba(255,255,255,0.5)',
                fontSize: 12,
                fontFamily: 'MicrosoftYaHei'
            }
        },
        series: [{
            name: '分布数量',
            stack: '总量',
            type: 'bar',
            barWidth: '30%',
            itemStyle: {
                normal: {
                    show: true,
                    color: function (params) {
                        return colorListEnterprise[params.dataIndex]
                    },
                    shadowColor: 'rgba(0, 0, 0, 0.75)',
                    shadowBlur: 2,
                    shadowOffsetX: 1,
                    shadowOffsetY: 1
                }
            },
            data: dataList
        }]
    };
    return option;
}

function showAnalysis(dataListA, legendListA, dataListB, legendListB, dataX, dataY) {
    var option = {
        title: [{
            text: '事故性质',
            textStyle: {
                fontSize: 12,
                color: "#9DCCFF"
            },
            textAlign: "center",
            x: '10.5%',
            y: '12.5%',
        }, {
            text: '事故级别',
            textStyle: {
                fontSize: 12,
                color: "#9DCCFF"
            },
            textAlign: "center",
            x: '72.0%',
            y: '12.5%',
        }, {
            text: '事故发生趋势',
            textStyle: {
                fontSize: 16,
                color: "#9DCCFF"
            },
            textAlign: "center",
            x: '11.5%',
            y: '35%',
        }],
        grid: {
            left: '6%',
            top: '45%',
            right: '6%',
            bottom: '3%',
            containLabel: true
        },
        legend: [{
            orient: 'vertical',
            left: '23%',
            align: 'left',
            top: '1%',
            textStyle: {
                color: '#fff'
            },
            itemWidth: 14,
            height: 150,
            data: legendListA
        }, {
            orient: 'vertical',
            left: '85%',
            align: 'left',
            top: '3%',
            textStyle: {
                color: '#fff'
            },
            itemWidth: 14,
            height: 150,
            data: legendListB
        }],
        tooltip: {
            show: true,
            trigger: 'item',
        },
        xAxis: [{
            type: 'category',
            axisLabel: {
                color: '#9DCCFF',
                fontSize: 12,
                fontFamily: 'MicrosoftYaHei'
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#FFF',
                    opacity: 0.2
                }
            },
            axisTick: {
                show: true,
                lineStyle: {
                    color: '#FFF',
                    opacity: 0.2
                }
            },
            splitLine: {
                show: true,
                lineStyle: {
                    color: '#FFF',
                    opacity: 0.2
                }
            },
            data: dataX
        }],
        yAxis: [{
            type: 'value',
            max: function (value) {
                return (value.max + 25)/10*10;
            },
            axisLabel: {
                formatter: '{value}',
                textStyle: {
                    color: 'rgba(255,255,255,0.5)',
                    fontSize: 12,
                    fontFamily: 'MicrosoftYaHei'
                }
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#FFF',
                    opacity: 0.2
                }
            },
            axisTick: {
                show: true,
                lineStyle: {
                    color: '#FFF',
                    opacity: 0.2
                }
            },
            splitLine: {
                show: true,
                lineStyle: {
                    color: '#FFF',
                    opacity: 0.2
                }
            },
        }],
        series: [{
            name: '事故性质',
            type: 'pie',
            center: ['12%', '15%'],
            radius: ['15%', '22%'],
            clockwise: false, //饼图的扇区是否是顺时针排布
            avoidLabelOverlap: false,
            itemStyle: {
                normal: {
                    label: {
                        show: false
                    },
                    labelLine: {
                        show: false
                    }
                }
            },
            tooltip: {
                formatter: function (parms) {
                    var str = parms.data.name + "</br>" +
                        "数量：" + parms.data.value + "</br>" +
                        "占比：" + parms.percent + "%";
                    return str;
                }
            },
            color: ['#CA410B', '#A269C6', '#401EC3', '#D26320', '#0042DA', '#008DE3', '#12B3B6', '#883CBD', '#4366D9', '#06AC7D', '#E3B71A', '#8d7fec', '#5085f2', '#e75fc3', '#f87be2', '#f2719a', '#fca4bb', '#f59a8f', '#fdb301', '#57e7ec', '#cf9ef1', '#57e7ec', '#cf9ef1'],
            data: dataListA
        }, {
            name: '事故级别',
            type: 'pie',
            center: ['73%', '15%'],
            radius: ['15%', '22%'],
            clockwise: false, //饼图的扇区是否是顺时针排布
            avoidLabelOverlap: false,
            itemStyle: {
                normal: {
                    label: {
                        show: false
                    },
                    labelLine: {
                        show: false
                    }
                }
            },
            tooltip: {
                trigger: 'item',
                formatter: function (parms) {
                    var str = parms.data.name + "</br>" +
                        "数量：" + parms.data.value + "</br>" +
                        "占比：" + parms.percent + "%";
                    return str;
                },
            },
            color: [ '#5085f2', '#e75fc3', '#f87be2', '#f2719a', '#fca4bb', '#f59a8f', '#fdb301', '#57e7ec', '#cf9ef1', '#57e7ec', '#cf9ef1','#7349F2', '#8A3CBE', '#E16E4E', '#BB9815', '#8d7fec',],
            data: dataListB
        }, {
            name: '事故发生趋势',
            type: 'line',
            stack: '总量',
            symbol: 'circle',
            symbolSize: 10,
            itemStyle: {
                normal: {
                    color: '#C24FF3',
                    lineStyle: {
                        color: "#C24FF3",
                        width: 2
                    },
                    areaStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(194,79,243,0.7)'
                        }, {
                            offset: 1,
                            color: 'rgba(77,68,254,0)'
                        }])
                    },
                    shadowColor: 'rgba(3,240,254,1)',
                    shadowBlur: 20,
                }
            },
            markPoint: {
                symbol:'image:///arjccm/static/images/rect2.png',
                symbolKeepAspect:true,
                symbolSize:[40,40],
                symbolOffset:[0, -25] ,
                data: [{
                    type: 'max',
                    name: '最大值'
                },
                    {
                        type: 'min',
                        name: '最小值'
                    }
                ],
                label: {
                    color: '#FFFFFF',
                    fontSize: 16,
                    fontFamily: 'MicrosoftYaHei',
                    padding:[0,0,8,0],
                },
                itemStyle: {
                    normal: {
                        color: '#C24FF3',
                    },
                }
            },
            data: dataY
        }]
    };
    analysisChart.setOption(option);
}
// parent.layer.config({
//     extend: 'layer.css', //加载您的扩展样式
//     skin: 'layer-ext-yourskin'
// });
//详情弹框--不刷新父页面
function LayerDialog(src, title, height, width) {
    layerIndex = parent.layer.open({
        type: 2,
        title: title,
        area: [height, width],
        fixed: true, //固定
        maxmin: true,
        id: 'LayerDialog',
        //btn: [ '确定',  '关闭'], //可以无限个按钮
        content: src,
        zIndex: '1992',
        skin:"mySkin"
});

}

function getMapInfo() {
    var formType = $("#formType").val();
    var areaId = $("#areaId").val();
    var formName = $("#formName").val();
    if(formType == 1 || formType == 2){
        if($('#areaName').val() == null || $('#areaName').val() == "" || $('#areaName').val() == undefined){
            // parent.layer.msg("请选择辖区范围!");
            $.jBox.tip('请选择辖区范围!');
            return;
        }
    }
    //清除已有图层
    removeMapvList();

    switch (formType) {
        //renkou
        case "1":
            var methods = {
                click: function (item) {
                    if (item) {
                        LayerDialog(ctx + '/pop/ccmPeople/mapvForm?id=' + item.id, '人员信息', '1100px', '300px');
                    }
                }
            };
            simpleColor(formType, areaId, formName, methods);
            return;
        //loudong
        case "2":
            var methods = {
                click: function (item) {
                    if (item) {
                        LayerDialog(ctx + '/house/ccmHouseBuildmanage/mapvForm?id=' + item.id, '楼栋信息', '1100px', '750px');
                    }
                }
            };
            simpleColor(formType, areaId, formName, methods);
            return;
        //chuzuwu
        case "3":
            var methods = {
                click: function (item) {
                    if (item) {
                        LayerDialog(ctx + '/pop/ccmPopTenant/mapvForm?id=' + item.id, '出租屋信息', '1100px', '750px');
                    }
                }
            };
            simpleColor(formType, areaId, formName, methods);
            return;
        //chengshibujian
        case "4":
            var methods = {
                click: function (item) {
                    if (item) {
                        LayerDialog(ctx + '/citycomponents/ccmCityComponents/mapvForm?id=' + item.id, '城市部件信息', '1100px', '600px');
                    }
                }
            };
            moreColor(formType, areaId, formName, methods);
            return;
        //qiye
        case "5":
            var methods = {
                click: function (item) {
                    if (item) {
                        LayerDialog(ctx + '/org/ccmOrgNpse/mapvForm?id=' + item.id, '企业信息', '1100px', '700px');
                    }
                }
            };
            moreColor(formType, areaId, formName, methods);
            return;
        //zhongdianrenyuan
        case "6":
            var methods = {
                click: function (item) {
                    if (item) {
                        var title = '重点人员信息';
                        console.info("item", item);
                        // 是否留守
                        if (item.type == 1) {
                            title = "留守人员信息";
                        }
                        // 是否安置帮教
                        if (item.type == 2) {
                            title = "安置帮教人员信息";
                        }
                        // 是否社区矫正
                        if (item.type == 3) {
                            title = "社区矫正人员信息";
                        }
                        // 是否艾滋病患者
                        if (item.type == 4) {
                            title = "艾滋病患者人员信息";
                        }
                        // 是否肇事肇祸等严重精神障碍患者
                        if (item.type == 5) {
                            title = "肇事肇祸等严重精神障碍患者信息";
                        }
                        // 是否重点青少年
                        if (item.type == 6) {
                            title = "重点青少年人员信息";
                        }
                        // 是否吸毒
                        if (item.type == 7) {
                            title = "吸毒人员信息";
                        }
                        //是否危害国家安全
                        if (item.type == 8) {
                            title = "危害国家安全人员信息";
                        }
                        //是否故意犯法释放
                        if (item.type == 9) {
                            title = "故意犯法释放人员信息";
                        }
                        //是否严重犯罪嫌疑
                        if (item.type == 10) {
                            title = "严重犯罪嫌疑人员信息";
                        }
                        //是否在逃
                        if (item.type == 11) {
                            title = "在逃人员信息";
                        }
                        // 是否重点上访
                        if (item.type == 12) {
                            title = "重点上访人员信息";
                        }
                        // 是否涉教
                        if (item.type == 13) {
                            title = "涉教人员信息";
                        }
                        // 是否危险品从业人员
                        if (item.type == 14) {
                            title = "危险品从业人员信息";
                        }
                        LayerDialog(ctx + '/pop/ccmPeople/mapvForm?id=' + item.id, title, '1100px', '300px');
                    }
                }
            };
            moreColor(formType, areaId, formName, methods);
            return;
        //shijian
        case "7":
            var methods = {
                click: function (item) {
                    if (item) {
                        LayerDialog(ctx + '/event/ccmEventIncident/mapvForm?id=' + item.id, '事件信息', '1100px', '600px');
                    }
                }
            };
            moreColor(formType, areaId, formName, methods);
            return;
        //shipin
        case "8":
            var methods = {
                click: function (item) {
                    if (item) {
                        LayerDialog(ctx + '/ccmsys/ccmDevice/mapvForm?id=' + item.id, '监控信息', '1100px', '700px');
                    }
                }
            };
            simpleColor(formType, areaId, formName, methods);
            return;
    }
}

function simpleColor(formType, areaId, formName, methods) {
    //查询人口信息
    $.getJSON(ctx + '/sys/map/queryAreaPoint?type=' + formType + '&areaId=' + areaId + '&name=' + formName, function (data) {
        if (data == null || data == "" || data == undefined) {
            $.jBox.tip('暂无数据！');
            return;
        }
        ;
        var mapData = [];
        if (data.length > 0) {
            for (var i = 0; i < data.length; i++) {
                if (data[i].id && data[i].areaPoint) {
                    var areaPoint = new Array(); //定义一数组
                    areaPoint = data[i].areaPoint.split(",");
                    mapData.push({
                        geometry: {
                            type: 'Point',
                            coordinates: areaPoint,
                        },
                        id: data[i].id,
                        // count: parseInt(Math.random() * 10)
                    });
                }
            }
            simplePoint(mapData, methods);
        }
    })
}

var mapvOptionsBlue = {
    fillStyle: 'rgba(50, 50, 255, 0.6)',
    shadowColor: 'rgba(50, 50, 255, 1)',
    shadowBlur: 30,
    globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsRed = {
    fillStyle: 'rgba(255, 50, 50, 0.6)',
    shadowColor: 'rgba(255, 50, 50, 1)',
    shadowBlur: 30,
    globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsBlueGreen = {
    fillStyle: 'rgba(0,245,222, 0.6)',
    shadowColor: 'rgba(0,245,222, 1)',
    shadowBlur: 30,
    // globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsYellow = {
    fillStyle: 'rgba(255, 255, 0, 0.6)',
    shadowColor: 'rgba(255, 255, 0, 1)',
    shadowBlur: 30,
    // globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsPink = {
    fillStyle: 'rgba(255, 146, 149, 0.6)',
    shadowColor: 'rgba(255, 146, 149, 1)',
    shadowBlur: 30,
    // globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsFlesh = {
    fillStyle: 'rgba(255, 241, 193, 0.6)',
    shadowColor: 'rgba(255, 241, 193, 1)',
    shadowBlur: 30,
    // globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsDarkBlue = {
    fillStyle: 'rgba(110, 176, 253, 0.6)',
    shadowColor: 'rgba(110, 176, 253, 1)',
    shadowBlur: 30,
    // globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsColor1 = {
    fillStyle: 'rgba(55, 162, 218, 0.6)',
    shadowColor: 'rgba(55, 162, 218, 1)',
    shadowBlur: 30,
    // globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsColor2 = {
    fillStyle: 'rgba(50, 197, 233, 0.6)',
    shadowColor: 'rgba(50, 197, 233, 1)',
    shadowBlur: 30,
    // globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsColor3 = {
    fillStyle: 'rgba(103, 224, 227, 0.6)',
    shadowColor: 'rgba(103, 224, 227, 1)',
    shadowBlur: 30,
    // globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsColor4 = {
    fillStyle: 'rgba(159, 230, 184, 0.6)',
    shadowColor: 'rgba(159, 230, 184, 1)',
    shadowBlur: 30,
    // globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsColor5 = {
    fillStyle: 'rgba(255, 219, 92, 0.6)',
    shadowColor: 'rgba(255, 219, 92, 1)',
    shadowBlur: 30,
    // globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsColor6 = {
    fillStyle: 'rgba(255, 159, 127, 0.6)',
    shadowColor: 'rgba(255, 159, 127, 1)',
    shadowBlur: 30,
    // globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsColor7 = {
    fillStyle: 'rgba(251, 114, 147, 0.6)',
    shadowColor: 'rgba(251, 114, 147, 1)',
    shadowBlur: 30,
    // globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsColor8 = {
    fillStyle: 'rgba(224, 98, 174, 0.6)',
    shadowColor: 'rgba(224, 98, 174, 1)',
    shadowBlur: 30,
    // globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsColor9 = {
    fillStyle: 'rgba(238, 114, 229, 0.6)',
    shadowColor: 'rgba(238, 114, 229, 1)',
    shadowBlur: 30,
    // globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsColor10 = {
    fillStyle: 'rgba(231, 188, 243, 0.6)',
    shadowColor: 'rgba(231, 188, 243, 1)',
    shadowBlur: 30,
    // globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsColor11 = {
    fillStyle: 'rgba(157, 150, 245, 0.6)',
    shadowColor: 'rgba(157, 150, 245, 1)',
    shadowBlur: 30,
    // globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsColor12 = {
    fillStyle: 'rgba(131, 120, 184, 0.6)',
    shadowColor: 'rgba(131, 120, 184, 1)',
    shadowBlur: 30,
    // globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsColor13 = {
    fillStyle: 'rgba(150, 190, 255, 0.6)',
    shadowColor: 'rgba(150, 190, 255, 1)',
    shadowBlur: 30,
    // globalCompositeOperation: 'lighter',
    methods: {
        click: function (item) {
            console.log(item);
        }
    },
    size: 5,
    draw: 'simple'
}
var mapvOptionsList = [mapvOptionsBlue, mapvOptionsRed, mapvOptionsBlueGreen, mapvOptionsYellow,
    mapvOptionsPink, mapvOptionsFlesh, mapvOptionsDarkBlue, mapvOptionsColor1, mapvOptionsColor2,
    mapvOptionsColor3, mapvOptionsColor4, mapvOptionsColor5, mapvOptionsColor6, mapvOptionsColor7,
    mapvOptionsColor8, mapvOptionsColor9, mapvOptionsColor10, mapvOptionsColor11, mapvOptionsColor12, mapvOptionsColor13];

function moreColor(formType, areaId, formName, methods) {

    //查询人口信息
    $.getJSON(ctx + '/sys/map/queryAreaPoint?type=' + formType + '&areaId=' + areaId + '&name=' + formName, function (data) {
        if (data == null || data == "" || data == undefined) {
            $.jBox.tip('暂无数据！');
            return;
        }
        ;
        var mapData = [];
        // var colorList = [];
        var colorCount = 0;//颜色列表计数器
        var layerCount = 0;//图层计数器
        var dataList = [];
        if (data.length > 0) {
            for (var i = 0; i < data.length; i++) {
                if (data[i].id && data[i].areaPoint) {
                    if (data[i].type) {
                        // var pushTemp = 0;//记录要插入的数组
                        if (dataList.hasOwnProperty(data[i].type) == false) {
                            // colorList.push(data[i].type);
                            // colorList[data[i].type] = mapvOptionsList[colorCount];
                            dataList[data[i].type] = [];
                            // pushTemp =data[i].type;
                            // colorCount++;
                            // layerCount++
                        } else {
                            // pushTemp = data[i].type;
                        }
                        var areaPoint = new Array(); //定义一数组
                        areaPoint = data[i].areaPoint.split(",");
                        dataList[data[i].type].push({
                            geometry: {
                                type: 'Point',
                                coordinates: areaPoint,
                            },
                            id: data[i].id,
                            type: data[i].type,
                            // count: parseInt(Math.random() * 10)
                        });
                    }
                }
            }
            console.info("dataList", dataList);
            // colorList.forEach(function (value, key, map) {
            //     alert("参数1="+value+",参数2="+key);
            // });
            var colorTemp = 0;
            for (var key in dataList) {
                mapvOptionsList[colorTemp].methods = methods;
                simplePointMoreColor(dataList[key], mapvOptionsList[colorTemp]);
                if (colorTemp >= mapvOptionsList.length) {
                    colorTemp = 0;
                } else {
                    colorTemp++;
                }

                // var map2 = {};
                // map2.value = data2[key];
                // map2.name = key;
                // data2y[i] = map2;
                // i++;
            }
            // simplePoint(mapData);
        }
    })
}
function areaChangeJboxStyle(h) {
    document.getElementById('jbox-iframe').contentWindow.document.body.style.backgroundColor = "transparent";
    document.getElementById('jbox-iframe').contentWindow.document.body.style.color="white";
}
function videoChangeJboxStyle(h) {
    document.getElementById('jbox-iframe').contentWindow.document.body.style.backgroundColor = "transparent";
    document.getElementById('jbox-iframe').contentWindow.document.body.style.color="white";
}

function videoTreeselectCallBack(v, h, f){
	if (v=="ok"){
		var tree = h.find("iframe")[0].contentWindow.tree;//h.find("iframe").contents();
		var ids = [], names = [], nodes = [];
		if ("${checked}" == "true"){
			nodes = tree.getCheckedNodes(true);
		}else{
			nodes = tree.getSelectedNodes();
		}
		for(var i=0; i<nodes.length; i++) {
			if(nodes[i].type == "camera"){
				ids.push(nodes[i].id);
				names.push(nodes[i].name);
			}else{
				top.$.jBox.tip("不能选择非监控节点（"+nodes[i].name+"）请重新选择。");
				$("#videoId").val("");
				$("#videoName").val("");
				return false;
			}
		}
		var name = names.join(",");
		var id = ids.join(",").replace(/u_/ig,"");
		if(name == null || name == "" || name == undefined){
			$("#videoId").val("");
			$("#videoName").val("");
			return false;
		}else{
			$("#videoId").val(id);
			$("#videoName").val(names.join(","));
			$('#ccmLiveVideoVisualized').attr("src","/arjccm/a/ccmsys/ccmDevice/getDeviceMap?id=" + id);
			return true;
		}
	}
}