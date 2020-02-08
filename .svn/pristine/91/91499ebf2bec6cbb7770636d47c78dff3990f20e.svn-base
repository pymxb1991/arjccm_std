/**
 * Created by cdz on 2019/9/3.
 */

$(function() {
    // 基础颜色表
    var color = ['#aa4644', '#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];
    // 基础参数
    var windowsHeight, _fontSize = 14,

        _fontSize1 = 26,
        breakData = 8;
    legendTop = '30%',
        radiusData = [90, 65],
        lengthECharts = 30;

    //
    $('#main').height($(window).height());

    function getCookie(cname)
    {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for(var i=0; i<ca.length; i++) 
        {
          var c = ca[i].trim();
          if (c.indexOf(name)==0) return c.substring(name.length,c.length);
        }
        return "";
      }
    $('.height100').height($('#main').height() - 75);
    var FontColor = "#999", backgroundColor = "#fff";
     var theme=getCookie('theme');
     if(theme=="black"){
         FontColor="#fff";
         backgroundColor="#0e2a4c";
     }
    windowsHeight = $(window).width();

    if (windowsHeight > 1366) {
        _fontSize = 14;
        legendTop = '15%';
        radiusData = [90, 65];
        lengthECharts = 20;
        _fontSize1 = 26;
        breakData = 8;
        legendRight = "4%"
    } else {
        _fontSize = 12;
        legendTop = '15%';
        radiusData = [60, 45];
        lengthECharts = 5;
        _fontSize1 = 14;
        breakData = 6;
        legendRight = "2%"
    }
    var ctx = $("#ctx").attr("ctx");


    /**
     * 综治机构统计
     */
    $.getJSON(ctx + "/view/CcmOrgAnalysis/getByOrgType", function(
        data) {
        // 接收参数
        $.EchartsOrgNumberAjax("EchartsOrgNumber", data);
    });

    $.EchartsOrgNumberAjax = function (model, jsondata) {

        option = {
            title: {
                text: '综治机构统计',
                textStyle: {
                    fontSize: 15
                }
            },
            color: ['#4E80BC'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                top: "40",
                left: '3%',
                right: '30',
                bottom: '40',
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                data: $.ToConvertLegendA(jsondata),
                axisLine: {
                    lineStyle: {
                        color: ['#4E80BC'],
                    }
                },
                axisTick: {
                    alignWithLabel: true
                },

                axisLabel: {
                    show: true,
                    interval: 0, //设置为 1，表示『隔一个标签显示一个标签』
                    rotate: -35,
                    textStyle: {
                        fontSize: _fontSize,
                        color: ['#4E80BC'],
                    },
                }

            }
            ],
            yAxis: [{
                type: 'value',
                splitLine: {show: false},//去除网格线
                axisLine: {
                    lineStyle: {
                        color: ['#4E80BC'],
                    }
                },
                axisLabel: {
                    show:true,
                    textStyle :
                        {
                            color: ['#4E80BC'],
                        }
                }
            }
            ],
            series : [{
                name: '机构数量',
                type: 'bar',
                barWidth: '60%',
                data: jsondata,
                itemStyle: {
                    normal: {
                        color: function(params) {
                            // build a color map as your need.
                            var colorList = [
                                '#89a54e','#F0805A','#27727B','#E87C25','#FE8463','#9BCA63','#FCCE10'
                            ];
                            return colorList[params.dataIndex]
                        },
                        label: {
                            show: true,
                            position: 'top',
                            formatter: '{c}'
                        }
                    }
                },
            }
            ]
        };

        // 初始化参数
        var myChart = echarts.init(document.getElementById(model));
        myChart.setOption(option, true);
    }


// 综治组织队伍性别
    $.getJSON(ctx + "/view/CcmOrgAnalysis/getBySex",
        function (data) {
            // 填充数据
            $.EchartsSexFun("EchartsSex", $.ToConvertA(data));
        });
    $(window).resize(function () {
        window.location.reload()
    })

    /**
     * 综治队伍性别统计情况
     */
    $.EchartsSexFun = function (model, data) {
        var nameArr = [],
            DataArr = [];

        var option = {
            title: {
                text: '综治队伍性别统计',
                textStyle: {
                    fontSize: 15
                }
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)",
                confine: true
            },
            legend: {
                // type: 'scroll',
                orient: 'vertical',
                top: legendTop,
                right: legendRight,
                textStyle: {
                    color: FontColor,
                    fontSize: _fontSize,
                },
                data: data
                // data: nameArr
            },
            series: [{
                name: '性别类型',
                type: 'pie',
                radius: '60%',
                center: ['50%', '50%'],
                color: color,
                label: {
                    normal: {
                        show: false
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: data
            }]
        };
        var Barchart = echarts.init(document.getElementById(model));
        Barchart.setOption(option);
        Barchart.on('click',
            function (params) {
            });
    }

    //根据群防群治组织类型统计情况
    $.getJSON(ctx + "/view/CcmOrgAnalysis/getByOrgpreventComType", function (
        data) {
        // 接收参数
        $.EchartsoOrganizationFun("EchartsoOrganization", $.ToConvertA(data));
    })
    /**
     * 根据群防群治组织类型统计情况
     */
    $.EchartsoOrganizationFun = function (model, dataService) {
        var option = {
            title: {
                text: '群防群治组织类型统计',
                textStyle: {
                    fontSize: 15
                }
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)",
                confine: true
            },
            color: color,
            grid: {
                right: 0,
                top: 0,
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
                radius: ['48%', '60%'],
                center: ['50%', '50%'],
                label: {
                    normal: {
                        formatter: '{b|{b}：}{c}  ',
                        borderRadius: 4,
                        rich: {
                            a: {
                                color: '#999',
                                lineHeight: 22,
                                align: 'center'
                            },
                            b: {
                                fontSize: _fontSize,
                                lineHeight: 18
                            },
                            per: {
                                color: '#eee',
                                backgroundColor,
                                padding: [2, 4],
                                borderRadius: 2
                            }
                        }
                    }
                },
                data: dataService
            }]
        };
        // 实例化 对象
        var Barchart = echarts.init(document.getElementById(model));
        // 填充数据
        Barchart.setOption(option);
    }

    /**
     * 综治组织队伍民族
     */
    $.getJSON(ctx + "/view/CcmOrgAnalysis/getByNation", function(
        data) {
        // 接收参数
        $.EchartsNationAjax("EchartsNation", data);
    });

    $.EchartsNationAjax = function (model, jsondata) {

        option = {
            title: {
                text: '群防群治队伍  民族统计',
                textStyle: {
                    fontSize: 15
                }
            },
            color: ['#4E80BC'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                top: "40",
                left: '3%',
                right: '30',
                bottom: '40',
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                data: $.ToConvertLegendA(jsondata),
                axisLine: {
                    lineStyle: {
                        color: ['#4E80BC'],
                    }
                },
                axisTick: {
                    alignWithLabel: true
                },

                axisLabel: {
                    show: true,
                    interval: 0, //设置为 1，表示『隔一个标签显示一个标签』
                    rotate: -35,
                    textStyle: {
                        fontSize: _fontSize,
                        color: ['#4E80BC'],
                    },
                }

            }
            ],
            yAxis: [{
                type: 'value',
                splitLine: {show: false},//去除网格线
                axisLine: {
                    lineStyle: {
                        color: ['#4E80BC'],
                    }
                },
                axisLabel: {
                    show:true,
                    textStyle :
                        {
                            color: ['#4E80BC'],
                        }
                }
            }
            ],
            series : [{
                name: '人数',
                type: 'bar',
                barWidth: '60%',
                data: jsondata,
                itemStyle: {
                    normal: {
                        color: function(params) {
                            // build a color map as your need.
                            var colorList = [
                                '#89a54e','#F0805A','#27727B','#E87C25','#FE8463','#9BCA63','#FCCE10'
                            ];
                            return colorList[params.dataIndex]
                        },
                        label: {
                            show: true,
                            position: 'top',
                            formatter: '{c}'
                        }
                    }
                },
            }
            ]
        };

        // 初始化参数
        var myChart = echarts.init(document.getElementById(model));
        myChart.setOption(option, true);
    }

    //根据重大案件分级统计情况
    $.getJSON(ctx + "/view/CcmOrgAnalysis/getItemByScale", function (
        data) {
        // 接收参数
        $.EchartsItemByScaleFun("EchartsItemByScale", $.ToConvertA(data.result), data.name);
    })

    $.EchartsItemByScaleFun = function (model, dataService, name) {
        var option = {
            title: {
                text: '重大案件分级统计',
                textStyle: {
                    fontSize: 15
                }
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)",
                confine: true
            },
            color: color,
            grid: {
                right: 0,
                top: 0,
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
                radius: ['48%', '60%'],
                center: ['50%', '50%'],
                label: {
                    normal: {
                        formatter: '{b|{b}：}{c}  ',
                        borderRadius: 4,
                        rich: {
                            a: {
                                color: '#999',
                                lineHeight: 22,
                                align: 'center'
                            },
                            b: {
                                fontSize: _fontSize,
                                lineHeight: 18
                            },
                            per: {
                                color: '#eee',
                                backgroundColor,
                                padding: [2, 4],
                                borderRadius: 2
                            }
                        }
                    }
                },
                data: dataService
            }]
        };
        // 实例化 对象
        var Barchart = echarts.init(document.getElementById(model));
        // 填充数据
        Barchart.setOption(option);
    }



    // 根据综治领导责任制统计情况
    $.getJSON(ctx + "/view/CcmOrgAnalysis/getByPolicyType",
        function (data) {
            // 填充数据
            $.EchartsPolicyTypeFun("EchartsPolicyType", $.ToConvertA(data));
        });
    $(window).resize(function () {
        window.location.reload()
    })

    $.EchartsPolicyTypeFun = function (model, data) {
        var nameArr = [],
            DataArr = [];

        var option = {
            title: {
                text: '综治领导责任制  实施主体层级分类统计',
                textStyle: {
                    fontSize: 15
                }
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)",
                confine: true
            },
            legend: {
                // type: 'scroll',
                orient: 'vertical',
                top: legendTop,
                right: legendRight,
                textStyle: {
                    color: FontColor,
                    fontSize: _fontSize,
                },
                data: data
                // data: nameArr
            },
            series: [{
                name: '实施主体类型',
                type: 'pie',
                radius: '60%',
                center: ['40%', '60%'],
                color: color,
                label: {
                    normal: {
                        show: false
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: data
            }]
        };
        var Barchart = echarts.init(document.getElementById(model));
        Barchart.setOption(option);
        Barchart.on('click',
            function (params) {
            });
    }

    // EchartType 转换成 Echart所需要的类型
    $.ToConvertA = function(object) {
        var ajaxData = new Array();
        for (var one in object) {
            ajaxData.push({
                "name": object[one]["type"],
                "value": Number(object[one]["value"])
            });
        }
        return ajaxData;
    }

// EchartType 转换成 Echart legend  所需要的类型
    $.ToConvertLegendA = function(object) {
        var ajaxData = new Array();
        for (var one in object) {
            ajaxData.push(
                object[one]["type"]
            );
        }
        return ajaxData;
    }
});
