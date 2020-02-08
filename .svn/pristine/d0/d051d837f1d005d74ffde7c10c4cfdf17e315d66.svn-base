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

    //





    var context = $(".context").attr("content");

    $.post(context + "/shake/ccmShakePovertyPeople/getComprehensiveData","",function (data) {
        $("#CameraTotal").text(data["AGPOP"]);
        $("#OnLineRate").text(data["OnLineRate"]);
       // Rural
        $("#item-one").html(data["AGPOP"]+'<span class="jianfont">人</span>');
        $("#item-two").html(data["Rural"]+'<span class="shijian-common-jian">个</span>');
        $("#item-three").html(data["Poor"]+'<span class="shijian-common-jian">个</span>');
        $("#item-four").html(data["Document"][0]+'<span class="shijian-common-jian">户</span>'+data["Document"][1]+'<span class="shijian-common-jian">人</span>');
        $("#item-five").html(data["Cumulative"][0]+'<span class="shijian-common-jian">户</span>'+data["Cumulative"][1]+'<span class="shijian-common-jian">人</span>');
        $("#item-six").html(data["Notout"][0]+'<span class="shijian-common-jian">户</span>'+data["Notout"][1]+'<span class="shijian-common-jian">人</span>');
        $("#item-seven").html(data["Incidence"]+'<span class="shijian-common-jian">%</span>');
    })


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
    PopFollowEchartsFun();
    PopsNumEchartsFun();
    HealthyEchartsFun();
    SkillEchartsFun();

    // 实有人口总数、新增CameraTotal/OnLineRate
    function CameraTotalFun(numPopData) {
        //console.log(numPopData);
        $("#CameraTotal").html(numPopData[0]);
        $("#OnLineRate").html(numPopData[1]);
    }


    function PopKeyEchartsFun(data) {

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
                data: ['0-18岁', '19-60岁', '60岁以上'],
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
                data: ['颜旗村', '上头铺村', '杨家桥村', '石板村', '四旗村',
                    '陶官村', '石塔村', '三合村', '羊场村', '菜支村',
                    '青方村', '龙井村', '荡上村', '两河村', '大屯村',
                    '邵小村', '板凳山村', '尚兴村', '鲍家村', '潘苑村',
                    '磊跨村', '小屯村', '幺铺居委会', '清源村', '红龙村',
                    '白马村', '南山村', '兴铺村', '安湖村', '张胜村'
                ],

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
                name: '人数',
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
                name: '0-18岁',
                type: 'bar',
                stack: '总量',
                //barWidth: 1,
                data: [32, 12, 20, 14, 31,
                    19, 25, 22, 37, 31,
                    60, 135, 31, 20, 33,
                    23, 34, 17, 5, 48,
                    54, 25, 29, 22, 15,
                    25, 42, 10, 19, 45
                ],
                itemStyle: {
                    normal: {
                        color: '#71588f'
                    }
                }
            },
                {
                    name: '19-60岁',
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: {
                            color: '#4298af'
                        }
                    },
                    data: [66, 28, 53, 31, 60,
                        80, 44, 31, 92, 102,
                        110, 309, 67, 47, 113,
                        60, 51, 42, 8, 130,
                        101, 81, 65, 63, 47,
                        32, 108, 28, 45, 92
                    ]
                }
                ,
                {
                    name: '60岁以上',
                    type: 'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: {
                            color: '#db843d'
                        }
                    },
                    data: [12, 6, 10, 7, 20,
                        27, 22, 1, 17, 29,
                        24, 114, 19, 12, 35,
                        20, 12, 3, 2, 34,
                        31, 23, 21, 15, 14,
                        7, 48, 11, 21, 37
                    ]
                }
            ]
        };
        var context = $(".context").attr("content");
        // 初始化参数
        $.post(context+"/shake/ccmShakePovertyPeople/BirNumlist","",function (data) {
            var list=data["list"];
            var s1Arr=[];
            var s2Arr=[];
            var s3Arr=[];
            var titleArr=[];
            for (var i = 0; i <list.length; i++) {
                s1Arr[i]=list[i]["baob"]
                s2Arr[i]=list[i]["baow"]
                s3Arr[i]=list[i]["baqo"]

                titleArr[i]=list[i]["townName"]
            }
                option.series[0].data=s1Arr;
                option.series[1].data=s2Arr;
                option.series[2].data=s3Arr;
                option.xAxis.data=titleArr;
                // console.log(option)
            var myChart = echarts.init(document.getElementById("PopKeyEcharts"));
            myChart.setOption(option, true);
        })




    }

    //本月人口信息PopFollowPop
    function PopFollowPopFun(numPopFollowPop) {
        //console.log(numPopData);
        $("#numPopFollowPop201").html(numPopFollowPop[1]["value1"]);
        $("#numPopFollowPop202").html(numPopFollowPop[1]["value2"]);
        $("#numPopFollowPop203").html(numPopFollowPop[1]["value3"]);
        $("#numPopFollowPop204").html(numPopFollowPop[1]["value4"]);
        $("#numPopFollowPop205").html(numPopFollowPop[1]["value5"]);
        $("#numPopFollowPop206").html(numPopFollowPop[1]["value6"]);
        $("#numPopFollowPop207").html(numPopFollowPop[1]["value7"]);
        $("#numPopFollowPop208").html(numPopFollowPop[1]["value8"]);
        $("#numPopFollowPop209").html(numPopFollowPop[1]["value9"]);
        $("#numPopFollowPop210").html(numPopFollowPop[1]["value10"]);
        $("#numPopFollowPop211").html(numPopFollowPop[1]["value17"]);//常住
        $("#numPopFollowPop212").html(numPopFollowPop[1]["value11"]);//重点上访
        $("#numPopFollowPop213").html(numPopFollowPop[1]["value12"]);//涉教人员
        $("#numPopFollowPop214").html(numPopFollowPop[1]["value13"]);//危险品从业人员
        $("#numPopFollowPop215").html(numPopFollowPop[1]["value14"]);//特殊关怀人员
        $("#numPopFollowPop216").html(numPopFollowPop[1]["value15"]);//老年人
        $("#numPopFollowPop217").html(numPopFollowPop[1]["value16"]);//党员
        //
        $("#numPopFollowPop101").html(numPopFollowPop[0]["value1"]);
        $("#numPopFollowPop102").html(numPopFollowPop[0]["value2"]);
        $("#numPopFollowPop103").html(numPopFollowPop[0]["value3"]);
        $("#numPopFollowPop104").html(numPopFollowPop[0]["value4"]);
        $("#numPopFollowPop105").html(numPopFollowPop[0]["value5"]);
        $("#numPopFollowPop106").html(numPopFollowPop[0]["value6"]);
        $("#numPopFollowPop107").html(numPopFollowPop[0]["value7"]);
        $("#numPopFollowPop108").html(numPopFollowPop[0]["value8"]);
        $("#numPopFollowPop109").html(numPopFollowPop[0]["value9"]);
        $("#numPopFollowPop110").html(numPopFollowPop[0]["value10"]);
        $("#numPopFollowPop111").html(numPopFollowPop[0]["value17"]);//常住
        $("#numPopFollowPop112").html(numPopFollowPop[0]["value11"]);//重点上访
        $("#numPopFollowPop113").html(numPopFollowPop[0]["value12"]);//涉教人员
        $("#numPopFollowPop114").html(numPopFollowPop[0]["value13"]);//危险品从业人员
        $("#numPopFollowPop115").html(numPopFollowPop[0]["value14"]);//特殊关怀人员
        $("#numPopFollowPop116").html(numPopFollowPop[0]["value15"]);//老年人
        $("#numPopFollowPop117").html(numPopFollowPop[0]["value16"]);//党员
    }


    //重点青少年统计


    // 地图
    function map() {
        // 地图默认数据设置
        var defaultPrams = {
            id: 'map',
            centerCoordinate: centerCoordinatePop,
            //zoom : zoomPop,
            zoom: 14,
            maxZoom: 18,
            minZoom: 9.8,
            baseUrl: [],

//			urlArr : [ {
//				'name' : '银川地图 ',
//				'url' : 'http://192.168.1.22:6080/arcgis/rest/services/YinChuan/MapServer',
//				'isShow' : true
//			} ],
            zoomShowOrHide: false,// 缩小放大
            overviewMap: false,
            selectPointerFlag: true
            // 鹰眼图
        }
        var Map = new ArjMap.Map(defaultPrams);
        // 加载地图
        Map.init();


        $.getJSON(context + "/sys/map/orgAreaMap?type=1", function (data) {
            Map.addJSON1([{
                'type': 'communitys',
                'data': data,
                'isShow': true
            }])
        });


    }
})

function RightTypeEchartsFun() {
    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            x: 'center',
            y: 10,
            textStyle: {
                fontWeight: 'normal', //标题颜色
                color: '#fff'
            },
            data: ['返贫', '未脱贫', '脱贫']
        },
        grid: {
            borderWidth: 0,
            left: '4%',
            right: '10%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
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
                    return value;
                }
            },
            splitLine: {
                show: false
            }
        },
        yAxis: {
            type: 'category',
            data: ['幺铺镇', '西航办事处', '宋旗镇'],
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
            name: '返贫',
            type: 'bar',
            barMinHeight: 10,
            stack: '总量',
            barWidth: 18,
            data: [31, 10],
            itemStyle: {
                normal: {
                    label: {
                        show: false,
                        position: 'insideRight',
                        textStyle: {
                            fontSize: 12
                        }
                    },
                    color: '#93a9d0'
                }
            }
        }
        
        ]
    };
    var context = $(".context").attr("content");
    $.post(context + "/shake/ccmShakePovertyPeople/SpTownlist", "", function (data) {
        var result = data["result"];
        var type = data["sysDicts"];
        var typeArr = [];
        var strArr = [];
        var typeData = {};
        for (var i = 0; i < result.length; i++) {
        	strArr[i] = result[i]["title"];
        }
		for (var i = 0; i < type.length; i++) {
			var list = new Array();
			for (var j = 0; j < result.length; j++) {
				var num = result[j]["num"];
				list.push(num[i]);
			}
			option.series[i] = {
				name: type[i]["label"],
				type: 'bar',
				stack: '总量',
				label: {
					normal: {
						show: false,
						position: 'insideRight'
					}
				},
				itemStyle: {
					normal: {
						color: color[i]
					}
				},
				data: list
			}
			typeArr[i]=type[i]["label"]
		}
        option.yAxis.data = strArr;
        option.legend.data=typeArr;
        var myChart = echarts.init(document.getElementById("RightTypeEcharts"));
        myChart.setOption(option);
    })
}

function PopFlowTableFun() {
    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            x: 'center',
            y: 10,
            data: ['幺铺镇', '西航办事处', '宋旗镇'],
            textStyle: {
                fontWeight: 'normal', //标题颜色
                color: '#fff'
            },
        },
        grid: {
            borderWidth: 0,
            left: '3%',
            right: '4%',
            bottom: '5%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            data: ['一般农户', '一般贫困户', '低保户', '低保贫困户', '五保贫困户'
            ],

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
                }
            },
            splitLine: {
                show: true
            }
        },
        yAxis: {
            type: 'value',
            name: '人数',
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
        series: []

    };
    var context = $(".context").attr("content");
    $.post(context + "/shake/ccmShakePovertyPeople/getPovertyHouseholdAttributeData", "", function (data) {
        var count = data["count"];
        var title = data["title"];
        var type = data["type"];
        option.legend.data = title;
        option.xAxis.data = type[0];
        for (var i = 0; i < title.length; i++) {
            option.series[i] = {
                name: title[i],
                type: 'bar',
                itemStyle: {
                    normal: {
                        color: color[i]
                    }
                },
                data: count[i]
            }
        }
        // console.log(option)
        var myChart = echarts.init(document.getElementById("PopFlowTable"));
        myChart.setOption(option, true);
    })


}

function PopFollowEchartsFun(numPopFollowEcharts) {
    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            x: 'center',
            y: 0,
            width: 500,
            data: ['生产经营性收入', '务工收入', '财产性收入', '转移性收入'],
            textStyle: {
                fontWeight: 'normal', //标题颜色
                color: '#fff'
            },
        },
        color: ['#e7841f', '#688fa7', '#95b4a2', '#c1859a', '#e26565', '#f28c4a', '#92c0a6', '#d2785e', '#72a181', '#5ea2a5'],
        grid: {
            borderWidth: 0,
            left: '3%',
            right: '4%',
            bottom: '5%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            data: ['幺铺镇', '宋旗镇', '西航办', '星光社区', '春雷社区'],
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
                }
            },
            splitLine: {
                show: false
            }
        },
        yAxis: [{
            type: 'value',
            name: '金额',
            position: 'left',
            textStyle: {
                color: '#fff'
            },
            splitLine: {
                show: false
            },
            axisLine: {
                lineStyle: {
                    color: '#7ee6ff',
                    width: 2
                }
            },
            axisLabel: {
                formatter: '{value} '
            }
        },
            {
                type: 'value',
                //min:-60,
                name: '人数',
                position: 'right',
                textStyle: {
                    color: '#fff'
                },
                splitLine: {
                    show: false
                },
                axisLine: {
                    lineStyle: {
                        color: '#7ee6ff',
                        width: 2
                    }
                },
                axisLabel: {
                    formatter: '{value}'
                }
            },
        ],
        series: [
            {
                name: '生产经营性收入',
                type: 'bar',
                barMinHeight: 10,
                stack: '总量',
                barWidth: 45,
                data: [2548.48, 2918.01, 9067.14, 0, 0]
            }
        ]
    };
    var context = $(".context").attr("content");
    $.post(context+"/shake/ccmShakePovertyPeople/getPovertyIncomeData","",function (data) {
        var count = data["count"];
        var title = data["title"];
        var type = data["type"];
        option.legend.data = title;
        option.xAxis.data = type[0];
        for (var i = 0; i < title.length; i++) {
            option.series[i] = {
                name: title[i],
                type: 'bar',
                itemStyle: {
                    normal: {
                        color: color[i]
                    }
                },
                data: count[i]
            }
        }
        var myChart = echarts.init(document.getElementById('PopFollowEcharts'));
        myChart.setOption(option);
    })

}

function PopsNumEchartsFun(specialPopData) {

    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            x: 'center',
            y: 10,
            data: ['幺铺镇', '西航办事处', '宋旗镇'],
            textStyle: {
                fontWeight: 'normal', //标题颜色
                color: '#fff'
            },
        },
        grid: {
            borderWidth: 0,
            left: '3%',
            right: '4%',
            bottom: '10%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            data: ['缺技术', '缺劳动力', '缺资金', '因病', '因残', '因学', '因灾', '自身动力不足'],
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
                interval: 0,
                rotate: 20,
                margin: 10,
                textStyle: {
                    color: '#fff'
                }
            },
            splitLine: {
                show: false
            }
        },
        yAxis: {
            type: 'value',
            name: '人数',
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
        series: [
            {
                name: '幺铺镇',
                type: 'bar',
                stack: '总量',
                barWidth: 30,
                data: [8, 28, 1, 23, 33, 7, 1, 29],
                itemStyle: {
                    normal: {
                        color: '#e88029'
                    }
                }
            }
        ]
    };
    var context = $(".context").attr("content");
    $.post(context+"/shake/ccmShakePovertyPeople/getCausesPovertyData","",function (data) {
        var count = data["count"];
        var title = data["title"];
        var type = data["type"];
        option.legend.data = title;
        option.xAxis.data = type[0];
        for (var i = 0; i < title.length; i++) {
            option.series[i] = {
                name: title[i],
                type: 'bar',
                itemStyle: {
                    normal: {
                        color: color[i]
                    }
                },
                data: count[i]
            }
        }
        var myChart = echarts.init(document.getElementById("PopsNumEcharts"));
        myChart.setOption(option,true);
    })

}

function HealthyEchartsFun() {
    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            borderWidth: 0,
            top: '5%',
            left: '3%',
            right: '4%',
            bottom: '10%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            data: ['健康', '残疾', '患有大病', '大病残疾', '长期慢性病', '长期慢性病及残疾'],

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
                interval: 0,
                rotate: 20,
                margin: 10,
                textStyle: {
                    color: '#fff'
                }
            },
            splitLine: {
                show: false
            }
        },
        yAxis: {
            type: 'value',
            name: '人数',
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
        series: [
            {
                name: '人数',
                type: 'bar',
                barMinHeight: 2,
                barWidth: 30,
                data: [4179, 532, 63, 6, 283, 24],
                itemStyle: {
                    normal: {
                        color: '#D87A82'
                    }
                }
            }
        ]
    };

    var context = $(".context").attr("content");
    $.post(context + "/shake/ccmShakePovertyPeople/Healthlist", "", function (data) {
        option.series[0].data=data['result']['num'];
        option.xAxis.data=data['result']['name'];
        var myChart = echarts.init(document.getElementById('HealthyEcharts'));
        myChart.setOption(option);
    })

}

function SkillEchartsFun() {
    var option = {
        title: {
            subtext: '',
            x: '46%',
            y: '51%',
            subtextStyle: {
                color: '#fff' // 副标题文字颜色
            }
        },
        tooltip: {
            trigger: 'item',
            //formatter: "{a} <br/>{b} : {c} ({d}%)"
            formatter: function (a) {
                return (a['name'] + ": " +  a['value'] + "人");
            }
        },
        legend: {
            x: 'center',
            y: 'top',
            textStyle: {
                fontWeight: 'normal', //标题颜色
                color: '#fff'
            },
            data: ['技能劳动力', '普通劳动力', '丧失劳动力', '无劳动力']
        },
        calculable: true,
        series: [{
            itemStyle: {
                normal: {
                    label: {
                        show: true,
                        formatter: function (a, b, c) {
                            return  a['value'] + "人";
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
            type: 'pie',
            radius: [20, 80],
            center: ['50%', '60%'],
            roseType: 'area',
            x: '10%', // for funnel
            // for funnel
            sort: 'ascending', // for funnel
            data: []
        }]
    };
    var context = $(".context").attr("content");
    $.post(context + "/shake/ccmShakePovertyPeople/skilllist", "", function (data) {
        var result=data['result'];
        for (var i = 0; i <result['name'].length ; i++) {
            option.series[0].data[i] = {
                value: result['value'][i],
                name: result['name'][i]
            }
        }
        option.legend.data = result['name'];
        var myChart = echarts.init(document.getElementById('SkillEcharts'));
        myChart.setOption(option);
    })

}