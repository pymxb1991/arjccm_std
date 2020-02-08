/**
 * Created by oHa on 2017/12/28.
 */
var windowsHeight,_fontSize=14,_fontSize1=26,breakData=8;legendTop='30%',radiusData=[90, 65],lengthECharts=30,mapHeigth='90%';

$(function () {
    $('#main').height($(window).height());
    $('.height100').height($('#main').height()-75);
    windowsHeight= $(window).width();
    if(windowsHeight>1366){
        _fontSize=14;
        legendTop='15%';
        radiusData=[90, 65];
        lengthECharts=30;
        _fontSize1=26;
        breakData=8;
        mapHeigth='90%'
    }else{
        _fontSize=10;
        legendTop='15%';
        radiusData=[60, 45];
        lengthECharts=20;
        _fontSize1=14;
        breakData=6;
        mapHeigth='90%'
    }
    //各网格流动人口量
    PopFlowEchartsFun();
    //流动人口来源
    PopFlowSourceEchartsFun();
    //地图
    map();
    //流动人口统计信息
    PopInfoTypeFun();
})
//各网格流动人口量
function PopFlowEchartsFun(){
    var xAxisData = ['商品房社区', '村和村级社区', '工业园区', '旅游商贸区', '村和村级社区', '工业园区', '旅游商贸区'];
    var firstLineData = [90, 50, 39, 50, 120, 85, '_'];
    var firstLineDottedData = ['_', '_', '_', '_', '_', 85, 100];
    var dataValue = 100;
    var myChart = echarts.init(document.getElementById('PopFlowEcharts'));
    var lineitemStyle = {
        normal: {
            label: {
                formatter: function(params) {
                    return dataValue - params.value;
                },
                fontSize: 40,
                padding: [90, 0, 0, 0],
                color: '#fff',
                textStyle: {
                    baseline: 'top'
                }
            },
            color: '#01f2ee',
            borderColor: 'rgba(1, 242, 238, 0.5)',
            borderWidth: 10
        }
    };

    option = {
        tooltip: {
            show: true,
            trigger: 'item'
        },
        title: {
            text: '',
            subtext: '',
            x: 'center'
        },
        grid: {
            left: '3%',
            right: '3%',
            top: '5%',
            bottom: '1%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: true,
            axisTick: {
                show: false
            },
            axisLine: {
                lineStyle: {
                    color: '#fff'
                },
                onZero: true
            },
            axisLabel: {
                fontSize: _fontSize,
                color: '#fff'
            },
            splitLine: {
                show: true,
                lineStyle: {
                    type: 'dotted',
                    color: '#1978D9'
                }
            },
            data: xAxisData
        },
        yAxis: {
            type: 'value',
            axisTick: {
                show: false
            },
            axisLine: {
                lineStyle: {
                    color: '#fff'
                },
                onZero: false
            },
            axisLabel: {
                fontSize: _fontSize,
                color: '#fff'
            },
            splitLine: {
                show: true,
                lineStyle: {
                    type: 'dotted',
                    color: '#1978D9'
                }
            },
            scale: true
        },
        series: [{
            name: '第一产业',
            smooth: true,
            type: 'line',
            symbolSize: 5,
            symbol: 'circle',
            itemStyle: lineitemStyle,
            data: firstLineData
        }
        , {
            name: '第一产业',
            smooth: true,
            type: 'line',
            symbolSize: 5,
            symbol: 'circle',
            itemStyle: lineitemStyle,
            lineStyle: {
                normal: {
                    type: 'dotted'
                }
            },
            tooltip: {
                formatter: '{b0},{a0}: {c0}<br />{b2}'
            },
            data: firstLineDottedData
        },
        ]
    };
    myChart.setOption(option);
}
//流动人口来源
function PopFlowSourceEchartsFun(){

    var myChart = echarts.init(document.getElementById('PopFlowSourceEcharts'));
/*    var  option = {
        //color: ['#3398DB'],
        tooltip: {
            trigger: 'axis',
            backgroundColor:'rgba(255,255,255,0.8)',
            extraCssText: 'box-shadow: 0 0 8px rgba(0, 0, 0, 0.3);',
            textStyle:{
                color:'#fff',
            },

        },
        grid: {
            left: '15%',
            right: '3%',
            top: '3%',
            bottom: '3%',
        },
        yAxis: [{
            type: 'category',
            data: ['河北省','山东省','甘肃省','四川省','黑龙江省'],
            axisTick: {
                alignWithLabel: true,
            },
            axisLabel: {
                margin: 10,
                textStyle: {
                    fontSize: 14,
                    color:'#fff'
                }
            },
            axisLine: {
                lineStyle: {
                    color: '#dbe0e6'
                }
            },

        }],
        xAxis: [{
            type: 'value',
            axisLabel: {
                margin: 10,
                textStyle: {
                    fontSize: 14,
                    color:'#fff'
                }
            },
            axisLine: {
                lineStyle: {
                    color: '#fff'
                }
            },
            splitLine: {
                show: false,
                lineStyle: {
                    color: '#dbe0e6'
                }
            }
        }],
        series: [{
            name: 'Top5',
            type: 'bar',
            barWidth:20,
            data: [ 16000, 18400, 20500, 22600, 24700],
            label: {
                normal: {
                    show: true,
                    position: 'insideRight',
                    textStyle: {
                        color: 'white' //color of value
                    }
                }
            },
            itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                        offset: 0,
                        color: '#64bdc8' // 0% 处的颜色
                    }, {
                        offset: 1,
                        color: '#1978D9' // 100% 处的颜色
                    }], false),
                    barBorderRadius: [0, 15,15, 0],
                    shadowColor: 'rgba(0,0,0,0.1)',
                    shadowBlur: 3,
                    shadowOffsetY: 3
                }
            }
        }]
    };*/

    var color = ['#4573a7', '#D87A82', '#89a54e', '#71588f', '#4298af', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];
    var data = [{
        value: 28,
        name: '河北省'
    }, {
        value: 10,
        name: '山东省'
    }, {
        value: 18,
        name: '四川省'
    }, {
        value: 20,
        name: '甘肃省'
    }, {
        value: 20,
        name: '黑龙江省'
    }];
   var  option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            type: 'scroll',
            orient: 'vertical',
            top: legendTop,
            right:'10%',
            textStyle: {
                color: '#fff',
                fontSize:_fontSize,
            },
            data: ['河北省', '山东省', '四川省', '甘肃省','黑龙江省']
        },
        series: [{
            name: '告警情况',
            type: 'pie',
            radius: '80%',
            center : ['35%', '52%'],
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
    myChart.setOption(option, true);
}
//地图
function map() {
    var chart = echarts.init(document.getElementById('map'));
    var option = {
        //title: {
        //    text: '总数： 3000\n\n在线率：80%\n\n完好率：78%',
        //    right: '10%',
        //    top:'5%',
        //    textStyle: {
        //        color:'#fff'
        //    },
        //    backgroundColor: "rgba(128, 128, 128, 0.5)",//标题背景色，默认透明，颜色可以使用 RGB 表示，比如 'rgb(128, 128, 128)' ，如果想要加上 alpha 通道，可以使用 RGBA，比如 'rgba(128, 128, 128, 0.5)'，也可以使用十六进制格式，比如 '#ccc'
        //    borderColor: "red",//标题的边框颜色，颜色格式支持同backgroundColor
        //    borderWidth: 2,//标题边框线宽，默认为0，可自行设置
        //    shadowBlur: 10,//图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX, shadowOffsetY 一起设置图形的阴影效果。
        //    shadowColor: "black",
        //},
        tooltip: {        //显示悬浮窗口
            trigger: 'item',
            textStyle: {
                color: '#fff',
                fontSize: _fontSize,
            },
            formatter: function (params) {
                //定义一个res变量来保存最终返回的字符结果,并且先把地区名称放到里面
                var res = params.name + '<br />';
                //定义一个变量来保存series数据系列
                var myseries = option.series;
                //循环遍历series数据系列
                for (var i = 0; i < myseries.length; i++) {
                    //在内部继续循环series[i],从data中判断：当地区名称等于params.name的时候就将当前数据和名称添加到res中供显示
                    for (var k = 0; k < myseries[i].data.length; k++) {
                        //console.log(myseries[i].data[k].name);
                        //如果data数据中的name和地区名称一样
                        if (myseries[i].data[k].name == params.name) {
                            //将series数据系列每一项中的name和数据系列中当前地区的数据添加到res中
                            if (myseries[i].name == '在线率' || myseries[i].name == '完好率') {
                                res += myseries[i].name + ':' + myseries[i].data[k].value + '%<br />';
                            } else {
                                res += myseries[i].name + ':' + myseries[i].data[k].value + '<br />';

                            }
                        }
                    }
                }
                //返回res
                //console.log(res);
                return res;
            },


        },
        series: [
            {
                type: 'map',
                map: '铜仁市',
                name: '总数',
                layoutCenter: ['50%', '45%'],
                layoutSize: mapHeigth,
                aspectScale:1,
                itemStyle: {
                    normal: {
                        label: {
                            show: true,
                            textStyle: {
                                color: "#fff",
                                fontSize:_fontSize,
                            }
                        }
                    }
                },
                data: [
                    { name: '第一网格', value: 500 },
                    { name: '第二网格', value: 600 },
                    { name: '第三网格', value: 600 },
                    { name: '第四网格', value: 400 },
                    { name: '第五网格', value: 500 },
                    { name: '第六网格', value: 550 },
                    { name: '第七网格', value: 700 },
                    { name: '第八网格', value: 800 },
                    { name: '第九网格', value: 400 },
                    { name: '第十网格', value: 300 },
                ]

            },
            {
                type: 'map',
                map: '铜仁市',
                name: '在线率',
                layoutSize: '100%',
                layoutCenter: ['50%', '50%'],
                aspectScale: 1,
                label: {
                    normal: {
                        show: true,
                    }
                },
                itemStyle: {
                    normal: {
                        label: {
                            show: true,
                            textStyle: {
                                color: "#fff",
                                fontSize: _fontSize,
                            }
                        }
                    }
                },
                data: [
                    { name: '碧江区', value: 80 },
                    { name: '万山区', value: 82 },
                    { name: '江口县', value: 83 },
                    { name: '玉屏县', value: 80 },
                    { name: '石阡县', value: 75 },
                    { name: '思南县', value: 78 },
                    { name: '德江县', value: 73 },
                    { name: '印江县', value: 82 },
                    { name: '沿河县', value: 80 },
                    { name: '松桃县', value: 75 },
                ]

            },
            {
                type: 'map',
                map: '铜仁市',
                name: '完好率',
                layoutSize: '100%',
                layoutCenter: ['50%', '50%'],
                aspectScale: 1,
                itemStyle: {
                    normal: {
                        label: {
                            show: true,
                            textStyle: {
                                color: "#fff",
                                fontSize: _fontSize,
                            }
                        }
                    }
                },
                data: [
                    { name: '碧江区', value: 70 },
                    { name: '万山区', value: 72 },
                    { name: '江口县', value: 75 },
                    { name: '玉屏县', value: 76 },
                    { name: '石阡县', value: 78 },
                    { name: '思南县', value: 78 },
                    { name: '德江县', value: 76 },
                    { name: '印江县', value: 50 },
                    { name: '沿河县', value: 42 },
                    { name: '松桃县', value: 40 },
                ]

            }
        ],
        visualMap: {
            min: 10,
            max: 1000,
            text: ['高', '低'],
            realtime: false,
            calculable: false,
            inRange: {
                color: ['#e0ffff', '#006edd']
            },
            show: false,
            hoverLink: false,

            seriesIndex: 0,
            textStyle: {
                color: '#fff',
                fontSize:_fontSize
            },
            formatter: function (value,a,b) {
                
                return value; // 范围标签显示内容。
            }
        },
    };
    
    chart.setOption(option)
}

//流动人口统计信息
function PopInfoTypeFun(){
    colors = ["#4C72B0", "#55A868", "#C44E52", "#8172B2", "#CCB974", "#64B5CD"]
    deemph_colors = ['#8DA6CE', '#A0CFAB']
    bar_category_gap = '35%'
    bar_width = '50%'
    axis_line_color = 'rgb(135,135,135)'
    average_line_color = 'rgb(128,128,128)'
// 字体取决于图片、屏幕大小
// 标题 小图18，大图20
// 坐标轴 小图12，大图15
    title_font_size = 20
    category_font_size = 13
    label_emph_fontsize = 14,
        label_emph_style = {
            show: true,
            position: 'insideRight',
            formatter: '{c}',
        }

    categories = [

        '本镇有房','单位内部', '门脸店铺', '工地现场', '学校', '租赁住房', '其他',
        '临时', '短租', '长租', '长期', '定居', '公务员', '其他','企事业职工', '务工',
        '学生', '务农', '经商', '服务', '无业',
    ]

    data = [70,
        68, 67, 66, 65, 65, 64, 64,
        64, 63, 62, 62, 61, 96,
        55, 58, 50.4, 61, 55, 52, 50,
    ]
    var option = {
        grid: {
            left: 80,
        },
        tooltip: {
            trigger: 'item',
        },
        yAxis: {
            type: 'category',
            data: categories,
            inverse: true,
            axisTick: {
                show: false,
            },
            axisLine: {
                show: false,
            },

            splitLine: {
                show: true,
                interval: 6,
                lineStyle: {
                    width: 1.5,
                    color:'#fff'
                }
            },
            axisLabel: {
                textStyle: {
                    fontSize: _fontSize,
                    color:'fff'
                },
            },
        },
        xAxis: {
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#fff',
                    width: 1.5,
                }
            },
            axisTick: {
                length: 7
            },
            axisLabel: {
                formatter: '{value}',
                textStyle: {
                    fontSize: _fontSize,
                },
            },
            splitLine: {
                show: false
            },
            name: '人',
            nameLocation: 'end',
            position: 'top',
        },
        series: [{
            type: 'bar',
            data: data,
            // barWidth: 30,
            barCategoryGap: bar_category_gap,
            label: {
                normal: {
                    show: true,
                    position: 'insideRight',
                    formatter: '{c}',
                }
            },
            itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                        offset: 0,
                        color: '#64bdc8' // 0% 处的颜色
                    }, {
                        offset: 1,
                        color: '#1978D9' // 100% 处的颜色
                    }], false),
                    barBorderRadius: [0, 15,15, 0],
                    shadowColor: 'rgba(0,0,0,0.1)',
                    shadowBlur: 3,
                    shadowOffsetY: 3
                }
            },
        }]
    };
    var myChart = echarts.init(document.getElementById('PopInfoType'));
    myChart.setOption(option);
}