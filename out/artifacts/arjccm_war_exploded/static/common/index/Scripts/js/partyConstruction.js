/**
 * Created by oHa on 2017/12/28.
 */
var windowsHeight, _fontSize = 14, _fontSize1 = 26, breakData = 8;
legendTop = '30%', radiusData = [90, 65], lengthECharts = 30,
    mapHeigth = '90%';
// 基础颜色表
var color = ['#D87A82', '#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d',
    '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];
$(function () {

    //初始化查询第一级标签
    $.post("getPartyStructureNodeOneData", "", function (data) {
        var html = '';
        for (var i = 0; i < data["data"].length; i++) {

            html += '<li class="first-item ">' +
                '<a href="javascript:void(0)" value="' + data["data"][i]["id"] + '">' + data["data"][i]["name"] + '</a></li>';
        }
        //代码覆盖到设置好的容器里
        $("#first-node").html(html);
        //调用代码给class 上上监听
        clickFirstItem();
        //第一个默认选中 模拟点中
        $(".first-item").eq(0).click();
        //个数
        $("#first-count").text("(" + data['data'].length + "个)")
    })

    function clickFirstItem() {
        //一级联动点击效果
        $(".first-item").click(function () {
            $(".first-item").removeClass("common_tab_current_tit")
            $(this).addClass("common_tab_current_tit")
            var value = $(this).children().attr("value")
            console.log(value)
            //点击后查询二级标签
            $.post("getPartyStructureNodeTwoData", {"id": value}, function (data) {
                var html = '';
                for (var i = 0; i < data["data"].length; i++) {
                    // console.log("为什么没有")
                    html += '<li class="second-item clearfix">' +
                        '<a href="javascript:void(0)" value="' + data["data"][i]["id"] + '" >' + data["data"][i]["name"] + '</a></li>';
                }
                // $("#second-title").css("width", data["data"].length*96+"px").css("height","150px")
                $("#second-node").css("width", data["data"].length * 96 + "px").css("height", "150px")

                //代码覆盖到设置好的容器里
                $("#second-node").html(html);

                secondItemClick();
                //第一个默认选中
                $(".second-item").eq(0).children().click()
                //个数
                $("#second-count").text("(" + data['data'].length + "个)")
            })

        })
    }


    //导航按钮左滑
    $("#swiper-button-next").click(function () {
        var divLength = $("#second-node").css("width").split("px")[0];
        var MarginLeft = parseInt($("#second-node").css("margin-left").split("px")[0]);
        if (-divLength < (MarginLeft - 870) && !$("#second-node").is(":animated")) {
            $("#second-node").animate({marginLeft: MarginLeft - 870 + "px"}, 1000)
        }


    })
    //导航按钮右划
    $("#swiper-button-prev").click(function () {
        var divLength = $("#second-node").css("width").split("px")[0];
        var MarginLeft = parseInt($("#second-node").css("margin-left").split("px")[0]);
        if (0 >= (MarginLeft + 870) && !$("#second-node").is(":animated")) {
            $("#second-node").animate({marginLeft: MarginLeft + 870 + "px"}, 1000)
        }


    })

    //导航按钮左滑 三级
    $("#third-button-next").click(function () {
        var divLength = $("#third-node").css("width").split("px")[0];
        var MarginLeft = parseInt($("#third-node").css("margin-left").split("px")[0]);
        if (-divLength < (MarginLeft - 870) && !$("#third-node").is(":animated")) {
            $("#third-node").animate({marginLeft: MarginLeft - 870 + "px"}, 1000)
        }


    })
    //导航按钮右划 三级
    $("#third-button-prev").click(function () {
        var divLength = $("#third-node").css("width").split("px")[0];
        var MarginLeft = parseInt($("#third-node").css("margin-left").split("px")[0]);
        if (0 >= (MarginLeft + 870) && !$("#third-node").is(":animated")) {
            $("#third-node").animate({marginLeft: MarginLeft + 870 + "px"}, 1000)
        }


    })

    function secondItemClick() {
        //二级联动点击效果
        $(".second-item").click(function () {
            $(".second-item").removeClass("common_tab_current_tit")
            $(this).addClass("common_tab_current_tit")
            var value = $(this).children().attr("value")
            //点击后查询三级标签
            $.post("getPartyStructureNodeThreeData", {"id": value}, function (data) {
                var html = '';
                for (var i = 0; i < data["data"].length; i++) {
                    // console.log("为什么没有")
                    html += '<li class="third-item clearfix">' +
                        '<a href="javascript:void(0)" value="' + data["data"][i]["id"] + '" >' + data["data"][i]["name"] + '</a></li>';
                }
                // $("#second-title").css("width", data["data"].length*96+"px").css("height","150px")
                $("#third-node").css("width", data["data"].length * 96 + "px").css("height", "150px")

                //代码覆盖到设置好的容器里
                $("#third-node").html(html);
                //第一个默认选中
                $(".third-item").eq(0).addClass("common_tab_current_tit");
                //个数
                // $("#second-count").text("(" + data['data'].length + "个)")
            })

        })
    }
    var moralIndex=0;
    //道德模范自动轮播
    function moralInit(){

        var html="";
        console.log(html);
        $.post("getMoralData","",function (data) {
            data=data["data"];
            var dataLength=data.length;
            for (var i = 0; i <dataLength; i++) {
                 html+='   <div index="'+i+'" style="display: none;" class="moral-item ">\n' +
                    '                                <dl>\n' +
                    '                                    <dt>\n' +
                    '                                        <img src="'+location.origin+data[i]["headPhoto"]+'"/>\n' +
                    '                                        <h1>'+data[i]["title"]+'</h1>\n' +
                    '                                        <h3>'+data[i]["name"]+'</h3>\n' +
                    '                                    </dt>\n' +
                    '                                    <dd style="width: 250px;height: 256px;">\n' +
                    '                                        <p style="line-height: 18px;margin-top: 8px;">\n' +
                    '                                            '+data[i]["content"]+'</p>\n' +
                    '                                    </dd>\n' +
                    '                                </dl>\n' +
                    '                            </div>'
            }

            $("#moral").html(html);
            $(".moral-item").eq(moralIndex).show(100);
            moralIndex++;

            setInterval(function () {
                $(".moral-item").hide(500);
                $(".moral-item").eq(moralIndex).show(500)
                if(moralIndex<dataLength-1){
                    moralIndex++;
                }else{
                    moralIndex=0;
                }

            },5000);


        })
    }
    moralInit();

    $(window).resize(function () {
        window.location.reload()
    })
    //
    $('#main').height($(window).height());
    $('.height100').height($('#main').height() - 86);
    windowsHeight = $(window).width();
    if (windowsHeight > 1600) {
        _fontSize = 14;
        legendTop = '20%';
        legendRight = '8%';
        radiusData = [90, 65];
        lengthECharts = 30;
        _fontSize1 = 26;
        breakData = 8;
        mapHeigth = '90%'
    } else {
        _fontSize = 10;
        legendTop = '15%';
        legendRight = '0%';
        radiusData = [60, 45];
        lengthECharts = 20;
        _fontSize1 = 14;
        breakData = 6;
        mapHeigth = '90%'
    }
    RightTypeEchartsFun();
    PopKeyEchartsFun();
    PopFlowTableFun();
    PopsNumEchartsFun();

    /*道德模范滚动-省*/
    // var ddmf_swiper = new Swiper('#p1_r_3',{
    //     observer: true,
    //     observeParents: true,
    //     autoplay: true,
    //     loop: true,
    //     speed: 2000,
    //     navigation: {
    //         nextEl: '.shengji>.swiper-button-next',
    //         prevEl: '.shengji>.swiper-button-prev',
    //     },
    // });
    /*区政府组织架构*/
    // var ddmf_swiper = new Swiper('.qzf_zzjg_list', {
    //     observer: true,
    //     observeParents: true,
    //     slidesPerView: 10,
    //     spaceBetween: 23,
    //     autoplay: true,
    //     loop: true,
    //     speed: 500,
    //     navigation: {
    //         nextEl: '.swiper-button-next',
    //         prevEl: '.swiper-button-prev',
    //     },
    // });

    /*幺铺镇_村居组织架构*/
    // var ypz_swiper = new Swiper('.cj_list', {
    //     observer: true,
    //     observeParents: true,
    //     slidesPerView: 10,
    //     spaceBetween: 0,
    //     //		    	autoplay: true,
    //     //		    	loop:true,
    //     //		    	speed:500,
    //     navigation: {
    //         nextEl: '.swiper-button-next',
    //         prevEl: '.swiper-button-prev',
    //     },
    // });
    $(".common_tab_tit > li > a").click(function (e) {
        if (e.target == this) {
            var tabs = $(this).parent().parent().children("li");
            var panels = $(this).parent().parent().parent().parent().find(".common_tab_cont");
            var index = $.inArray(this, $(this).parent().parent().find("a"));
            if (panels.eq(index)[0]) {
                tabs.removeClass("common_tab_current_tit").eq(index).addClass("common_tab_current_tit");
                panels.addClass("fohide").eq(index).removeClass("fohide");
            }
        }
    });


    function PopKeyEchartsFun(data) {
        var option = {
            tooltip: {
                trigger: 'axis'
            },
            color: ['#D87A82', '#4573a7'],
            legend: {
                x: 'right',
                y: 15,
                icon: 'rect',
                textStyle: {
                    fontWeight: 'normal', //标题颜色
                    color: '#fff'
                },
                data: ['党员', '非党员']
            },
            calculable: true,
            grid: {
                borderWidth: 0,
                left: '10%',
                right: '10%',
                bottom: '3%',
                containLabel: true,
            },
            xAxis: [{
                type: 'category',
                boundaryGap: false,
                scale: true,
                splitLine: {
                    show: false
                },
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                axisLine: {
                    lineStyle: {
                        color: '#fff',
                        width: 2
                    }
                },
                data: ['幺铺镇', '宋旗镇', '西航办事处', '星光社区', '春雷社区']
            }],
            yAxis: [{
                type: 'value',
                'name': '人数',
                scale: true,
                textStyle: {
                    color: '#fff'
                },
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                axisLine: {
                    lineStyle: {
                        color: '#fff',
                        width: 2
                    }
                },
                splitLine: {
                    show: false
                }
            }],
            series: [{
                name: '党员',
                type: 'line',
                smooth: true,
                itemStyle: {
                    normal: {
                        areaStyle: {
                            type: 'default',
                            color: '#D87A82'
                        },
                        lineStyle: {
                            width: 1, //折线宽度
                            color: "#D87A82" //折线颜色
                        }
                    }
                },
                data: [58, 95, 56, 17, 17]
            },
                {
                    name: '非党员',
                    type: 'line',
                    smooth: true,
                    itemStyle: {
                        normal: {
                            areaStyle: {
                                type: 'default',
                                color: '#4573a7'
                            },
                            lineStyle: {
                                width: 1, //折线宽度
                                color: "#4573a7" //折线颜色
                            }
                        }
                    },
                    data: [68, 33, 14, 11, 13]
                }
            ]
        };
        $.post("getTownshipStatisticsData", "", function (data) {
            option.xAxis[0].data = data["name"];
            option.series[0].data = data["partyMembers"];
            option.series[1].data = data["notPartyMembers"];
            console.log(data);
            // 初始化参数

            var myChart = echarts.init(document.getElementById('PopKeyEcharts'));
            myChart.setOption(option, true);

        })

    }
})

function RightTypeEchartsFun() {

    var option = {
        tooltip: {
            trigger: 'item',
            //formatter: "{a} <br/>{b} : {c} ({d}%)"
            formatter: function (a, b, c, d) {
                return (a['name'] + ": " + a['value'] + "人");
            }
        },
        legend: {
            x: 'center',
            y: 'top',
            textStyle: {
                fontWeight: 'normal', //标题颜色
                color: '#fff'
            },
            data: ['区直机关单位', '教育系统', '镇办社区', '村居', '非公组织']
        },
        calculable: true,
        series: [{
            itemStyle: {
                normal: {
                    label: {
                        show: true,
                        formatter: function (a, b, c) {
                            return a['value'] + "人";
                        }
                    },
                    labelLine: {
                        show: true,
                        length: 1,
                        lenght2: 1
                    },
                    color: function (params) {
                        // build a color map as your need.
                        var colorList = ['#D87A82', '#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d',
                            '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];
                        return colorList[params.dataIndex]
                    }
                }
            },
            name: '在职党员',
            type: 'pie',
            radius: [20, 80],
            center: ['50%', '60%'],
            roseType: 'area',
            x: '10%', // for funnel
            // for funnel
            sort: 'ascending', // for funnel
            data: [{
                value: '20',
                name: '区直机关单位'
            },
                {
                    value: '10',
                    name: '教育系统'
                },
                {
                    value: '20',
                    name: '镇办社区'
                },
                {
                    value: '20',
                    name: '村居'
                },
                {
                    value: '10',
                    name: '非公组织'
                }
            ]
        }]
    };
    $.post("getDSOPMAALData", "", function (data) {
        option.legend.data = data["title"];
        option.series[0].data = data["data"];
        // console.log(data)
        var myChart = echarts.init(document.getElementById("RightTypeEcharts"));
        myChart.setOption(option);
    })


}

function PopFlowTableFun() {
    p1l3_x_data = ['颜旗村', '上头铺村', '杨家桥村', '石板村', '四旗村',
        '陶官村', '石塔村', '三合村', '羊场村', '菜支村',
        '青方村', '龙井村', '荡上村', '两河村', '大屯村',
        '邵小村', '板凳山村', '尚兴村', '鲍家村', '潘苑村',
        '磊跨村', '小屯村', '幺铺居委会', '清源村', '红龙村',
        '白马村', '南山村', '兴铺村', '安湖村', '张胜村'
    ];
    p1l3_y_data1 = [17, 20, 22, 25, 21,
        38, 25, 22, 36, 38,
        32, 45, 44, 35, 26,
        36, 19, 14, 25, 53,
        33, 31, 31, 33, 31,
        34, 23, 38, 46, 28
    ];
    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            x: 'center',
            y: 'top',
            data: ['男'],
            textStyle: {
                fontWeight: 'normal', //标题颜色
                color: '#fff'
            },
        },
        grid: {
            borderWidth: 0,
            left: '3%',
            right: '4%',
            bottom: 0,
            containLabel: true
        },
        xAxis: {
            type: 'category',
            data: p1l3_x_data,
            //min:0,
            //max: 250000,
            textStyle: {
                color: '#fff'
            },
            axisLine: {
                lineStyle: {
                    color: '#fff',
                    width: 2
                }
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#fff'
                },
                formatter: function (value) {
                    var ret = ""; //拼接加\n返回的类目项
                    var maxLength = 1; //每项显示文字个数
                    var valLength = value.length; //X轴类目项的文字个数
                    var rowN = Math.ceil(valLength / maxLength); //类目项需要换行的行数
                    if (rowN > 1) //如果类目项的文字大于3,
                    {
                        for (var i = 0; i < rowN; i++) {
                            var temp = ""; //每次截取的字符串
                            var start = i * maxLength; //开始截取的位置
                            var end = start + maxLength; //结束截取的位置
                            //这里也可以加一个是否是最后一行的判断，但是不加也没有影响，那就不加吧
                            temp = value.substring(start, end) + "\n";
                            ret += temp; //凭借最终的字符串
                        }
                        return ret;
                    } else {
                        return value;
                    }
                }
            },
            splitLine: {
                show: false
            }
        },
        yAxis: {
            type: 'value',

            splitLine: {
                show: false
            },
            axisLine: {
                lineStyle: {
                    color: '#fff',
                    width: 2
                }
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#fff'
                }
            }
        },
        series: [{
            name: '人数',
            type: 'bar',
            stack: '总量',
            //barWidth: 1,
            data: p1l3_y_data1,
            itemStyle: {
                normal: {
                    color: '#89a54e'
                }
            }
        }
        ]
    };
    $.post("getVillagePartyStatisticsData", "", function (data) {
        option.xAxis.data = data["name"];
        option.series[0].data = data["partyMembers"];
        console.log(data);
        var myChart = echarts.init(document.getElementById("PopFlowTable"));
        myChart.setOption(option, true);
    })

}

function PopsNumEchartsFun(specialPopData) {
    var option = {
        color: ['#00c2ff', '#f9cf67', '#e92b77',"#00FF00","#CC00FF"],
        legend: {
            show: true,
            // icon: 'circle',//图例形状
            y: 'top',
            x: 'center',
            textStyle: {
                fontSize: 14,
                color: '#fff'
            },
            data: ['西航街道', '幺铺镇', '宋旗镇'],
        },
        radar: [{

                indicator: [{
                    text: '扶贫主任',
                    max: 100
                },
                {
                    text: '驻村第一书记',
                    max: 100
                },
                {
                    text: '警务助理',
                    max: 100
                },
                {
                    text: '政法派驻员',
                    max: 100
                },
                {
                    text: '民生监督员',
                    max: 100
                }
            ],

            textStyle: {
                color: 'red'
            },
            center: ['50%', '60%'],
            radius: '70%',
            startAngle: 90,
            splitNumber: 3,
            orient: 'horizontal', // 图例列表的布局朝向,默认'horizontal'为横向,'vertical'为纵向.
            // shape: 'circle',
            // backgroundColor: {
            //     image:imgPath[0]
            // },
            name: {
                formatter: '{value}',
                textStyle: {
                    fontSize: 14, //外圈标签字体大小
                    color: '#fff' //外圈标签字体颜色
                }
            },
            splitArea: { // 坐标轴在 grid 区域中的分隔区域，默认不显示。
                show: true,
                areaStyle: { // 分隔区域的样式设置。
                    color: ['#141c42', '#141c42'], // 分隔区域颜色。分隔区域会按数组中颜色的顺序依次循环设置颜色。默认是一个深浅的间隔色。
                }
            },
            // axisLabel:{//展示刻度
            //     show: true
            // },
            axisLine: { //指向外圈文本的分隔线样式
                lineStyle: {
                    color: '#153269'
                }
            },
            splitLine: {
                lineStyle: {
                    color: '#113865', // 分隔线颜色
                    width: 1, // 分隔线线宽
                }
            }
        },],
        series: [{
            name: '雷达图',
            type: 'radar',
            itemStyle: {
                emphasis: {
                    lineStyle: {
                        width: 4
                    }
                }
            },
            data: []
        },]
    };
        var popData={
            name: '西航街道',
            value: [85, 65, 55, 90, 82],
            areaStyle: {
                normal: { // 单项区域填充样式
                    color: {
                        type: 'linear',
                        x: 0, //右
                        y: 0, //下
                        x2: 1, //左
                        y2: 1, //上
                        colorStops: [{
                            offset: 0,
                            color: '#00c2ff'
                        }, {
                            offset: 0.5,
                            color: 'rgba(0,0,0,0)'
                        }, {
                            offset: 1,
                            color: '#00c2ff'
                        }],
                        globalCoord: false
                    },
                    opacity: 1 // 区域透明度
                }
            },
            symbolSize: 2.5, // 单个数据标记的大小，可以设置成诸如 10 这样单一的数字，也可以用数组分开表示宽和高，例如 [20, 10] 表示标记宽为20，高为10。
            label: {                    // 单个拐点文本的样式设置
                normal: {
                    show: true,             // 单个拐点文本的样式设置。[ default: false ]
                    position: 'top',        // 标签的位置。[ default: top ]
                    distance: 2,            // 距离图形元素的距离。当 position 为字符描述值（如 'top'、'insideRight'）时候有效。[ default: 5 ]
                    color: '#fff',          // 文字的颜色。如果设置为 'auto'，则为视觉映射得到的颜色，如系列色。[ default: "#fff" ]
                    fontSize: 14,           // 文字的字体大小
                    formatter: function (params) {
                        return params.value;
                    }
                }
            },
            itemStyle: {
                normal: { //图形悬浮效果
                    borderColor: '#00c2ff',
                    borderWidth: 2.5
                }
            },
            // lineStyle: {
            //     normal: {
            //         opacity: 0.5// 图形透明度
            //     }
            // }
        }
    // var coloArr=["#00c2ff","#f9cf67", "#e92b77"];
    $.post("getFiveMemberManagementData", "", function (data) {
        var strArr=[];
        for (var i = 0; i <data["data"].length; i++) {
            strArr[i]=data["data"][i]["name"];
            var popData= {};
            popData.name=strArr[i];
            popData.value=data["data"][i]["num"];
            // popData.areaStyle.normal.color.colorStops[0].color=coloArr[i];
            // popData.areaStyle.normal.color.colorStops[2].color=coloArr[i];
            option.series[0].data[i]=popData
        }
        option.legend.data=strArr;
        var myChart = echarts.init(document.getElementById("PopsNumEcharts"));
        myChart.setOption(option, true);
        // console.log(data);
        // var myChart = echarts.init(document.getElementById("PopFlowTable"));
        // myChart.setOption(option, true);
    })
    // myChart.setOption(option, true);
}