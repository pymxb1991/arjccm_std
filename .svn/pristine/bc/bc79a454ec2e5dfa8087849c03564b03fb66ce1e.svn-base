/**
 * Created by oHa on 2017/12/28.
 */
var windowsHeight, _fontSize = 14, _fontSize1 = 26, _fontSize2 = 24, _fontSize3 = 20, breakData = 8,
    legendTop = '30%', radiusData = ['40%', '65%'], lengthECharts = 30, mapHeigth = '90%', zoom = 14.75,
    centerCoordinate = [117.663920, 39.035650], echarts3X = "48%", echarts3y = "40%", ItemGap = 20;

var streetFlag, vccmorgFlag, communityFlag, gridFlag, buildFlag, eventFlag, partsFlag, landsFlag, videoFlag,
    broadcastFlag, policeroomFlag, workstationFlag, schoolPlaceFlag, keyPlaceFlag, keyPersonFlag, rentingPersonFlag,
    publicPlaceFlag, popLocationFlag, SetTopBoxFlag,zongjiaoFunflag;
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
    $(".menu a").click(function () {
        $(".menu a").removeClass("active");
        $(this).addClass("active");
    })










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













/***************************网格管理End****************************/
/***************************安全生产Start****************************/
var keyEnterprisesChart = null;
var preventiveChart = null;
var distributionChart = null;
var enterpriseChart = null;
var analysisChart = null;
var colorList = ['#724AC3', '#1E88C8', '#F57751', '#ABC31A', '#04FEF9', '#77ED4D', '#48A3FF', '#FFE822', '#FF61D8', '#FCCF77'];



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
    var sum = (Number(yes) + Number(no));
    var arr = ['middleLost', yes, val1data2, '今日完成进度']

    option = {
        title: [{
            top: '28%',
            left: '21%',


            subtext: (arr[1] * 100 / sum).toFixed(1) + '%',
            subtextStyle: {
                color: '#fff',
                fontSize: 22
            }
        }, {
            text: '危化企业',
            x: '18%',
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
                value: (Number(arr[1]) / sum).toFixed(1),
                itemStyle: {
                    normal: {
                        color: '#53d5ff',
                        opacity: 0.6
                    }
                }
            }],
            color: ['#C24FF3'],
            center: ['40%', '37%'],
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
            center: ['40%', '37%'],
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
        },
            {
                name: '外边框',
                type: 'pie',
                clockWise: false, //顺时加载
                hoverAnimation: false, //鼠标移入变大
                center: ['40%', '37%'],
                radius: ['75%', '75%'],
                label: {
                    normal: {
                        show: false
                    }
                },
                data: [{
                    value: 9,
                    name: '',
                    itemStyle: {
                        normal: {
                            borderWidth: 2,
                            borderColor: '#1FF4A7',
                            shadowColor: {
                                type: 'linear',
                                x: 0,
                                y: 0,
                                x2: 0.4,
                                y2: 1,
                                colorStops: [{
                                    offset: 0,
                                    color: '#1DE89F'
                                }, {
                                    offset: 1,
                                    color: '#1FF4A7'
                                }],
                                globalCoord: false
                            },
                            shadowBlur: 20,
                            shadowOffsetX: 20
                        }
                    }
                }]
            }
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
                return value.max + 20;
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
                symbol: 'image:///arjccm/static/images/rect.png',
                symbolKeepAspect: true,
                symbolOffset: [0, -30],
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
                    fontFamily: 'MicrosoftYaHei'
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
            max: function (value) {
                return value.max + 20;
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
                }
            },
            color: ['#5085f2', '#e75fc3', '#f87be2', '#f2719a', '#fca4bb', '#f59a8f', '#fdb301', '#57e7ec', '#cf9ef1', '#57e7ec', '#cf9ef1', '#7349F2', '#8A3CBE', '#E16E4E', '#BB9815', '#8d7fec',],
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
                symbol: 'image:///arjccm/static/images/rect2.png',
                symbolKeepAspect: true,
                symbolOffset: [0, -30],
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
                    fontFamily: 'MicrosoftYaHei'
                },
                itemStyle: {
                    normal: {
                        color: '#C24FF3'
                    }
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
        skin: "mySkin"
    });

}

function getMapInfo() {
    var formType = $("#formType").val();
    var areaId = $("#areaId").val();
    var formName = $("#formName").val();
    if (formType == 1 || formType == 2) {
        if ($('#areaName').val() == null || $('#areaName').val() == "" || $('#areaName').val() == undefined) {
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



