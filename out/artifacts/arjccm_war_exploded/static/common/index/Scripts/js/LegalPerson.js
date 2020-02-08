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
        legendTop='20%';
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
    LegalPersonEchartsFun();
    GridTypeEchartsFun();
    //地图
    map();
    OrgPopEchartsFun();
    HiddenDangerEchartsFun();

})
//部件破损率
function LegalPersonEchartsFun(){
    var option = {
        tooltip: {
            show: "true",
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '3%',
            bottom: '5%',
            top: '15%',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                data: ['机关 ','事业单位','企业','民办非企业','社会团体','基金会'],
                axisPointer: {
                    type: 'shadow'
                },
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                },
                axisLabel: {
                    // formatter: '{value} %',
                    textStyle: {
                        color: '#fff',
                        fontSize: _fontSize
                    }
                },
            }
        ],
        yAxis: [
            {
                type: 'value',
                min: 0,
                axisLabel: {
                    formatter: '{value} %',
                    textStyle: {
                        color: '#fff',
                        fontSize: _fontSize
                    }
                },
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                },
                splitLine: {
                    show: false
                }
                // max: 250,
                // interval: 50,
            }
        ],
        series: [
            {
                name: '',
                type: 'bar',
                data: [90,89,56,87,91,66],
                barWidth: 23,
                itemStyle: {
                    normal: {
                        color: '#89a54e'
                    }
                }
            },
        ]
    };
    var myChart = echarts.init(document.getElementById('LegalPersonEcharts'));
    myChart.setOption(option);
}
//网格类型
var color = ['#4573a7', '#D87A82', '#89a54e', '#71588f', '#4298af', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];

function GridTypeEchartsFun(){

    var option;
    var myChart = echarts.init(document.getElementById('GridTypeEcharts'));
    var data = [
        {
            value: 90,
            name: '商品房社区'
        }, {
            value: 67,
            name: '工业园区'
        }, {
            value: 46,
            name: '旅游商贸区'
        }, {
            value: 21,
            name: '地下管网'
        }, {
            value: 11,
            name: '驻镇单位'
        }];
    option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            type: 'scroll',
            orient: 'vertical',
            top: legendTop,
            right: '10%',
            textStyle: {
                color: '#fff',
                fontSize: _fontSize,
            },
            data: ['商品房社区', '工业园区', '旅游商贸区', '地下管网', '驻镇单位']
        },
        series: [{
            name: '房屋占比',
            type: 'pie',
            radius: '80%',
            center: ['35%', '52%'],
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

//组织人员统计
function OrgPopEchartsFun(){
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        grid: {
            left: '10%',
            right: '3%',
            bottom: '3%',
            top: '3%',
            containLabel: true
        },
        series: [
            {
                name:'',
                type:'pie',
                radius: ['40%', '55%'],
                color: color,
                label: {
                    normal: {
                     //   formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c}  {per|{d}%}  ',
                        formatter: '{b|{b}：}{c}  ',
                        //  backgroundColor: '#eee',
                        // borderColor: '#aaa',
                        //  borderWidth: 1,
                        borderRadius: 4,
                        // shadowBlur:3,
                        // shadowOffsetX: 2,
                        // shadowOffsetY: 2,
                        // shadowColor: '#999',
                        // padding: [0, 7],
                        rich: {
                            a: {
                                color: '#999',
                                lineHeight: 22,
                                align: 'center'
                            },
                            // abg: {
                            //     backgroundColor: '#333',
                            //     width: '100%',
                            //     align: 'right',
                            //     height: 22,
                            //     borderRadius: [4, 4, 0, 0]
                            // },
                            // hr: {
                            //   //  borderColor: '#aaa',
                            //     width: '100%',
                            //     borderWidth: 0.5,
                            //     height: 0
                            // },
                            b: {
                                fontSize: _fontSize,
                                lineHeight: 18
                            },
                            per: {
                                color: '#eee',
                                backgroundColor: '#334455',
                                padding: [2, 4],
                                borderRadius: 2
                            }
                        }
                    }
                },
                data:[
                    {value:335, name:'党组织'},
                    {value:310, name:'工会'},
                    {value:234, name:'共青团'},
                    {value:135, name:'妇联'},
                    {value:48, name:'志愿者'},
                    {value:251, name:'社会团体'},
                ]
            }
        ]
    };
    var myChart = echarts.init(document.getElementById('OrgPopEcharts'));
    myChart.setOption(option);
}
//安全隐患关注程度
function HiddenDangerEchartsFun(){
    var data = [17, 20, 45, 56, 60, 64];
    var xMax = 100;
    var option = {
        tooltip: {
            show: true,
            formatter: "{b} {c}"
        },
        grid: {
            left: '15%',
            top: '3%',
            bottom: '3%',
            right: '3%'
        },
        xAxis: [{
            max: xMax,
            type: 'value',
            axisTick: {
                show: false,
            },
            axisLine: {
                show: false,
            },
            axisLabel: {
                show: false
            },
            splitLine: {
                show: false
            }
        }],
        yAxis: [{
            type: 'category',
            data: [ '一级', '二级', '三级', '四级', '五级'],
            axisLabel: {
                textStyle: {
                    color: '#fff',
                    fontSize: _fontSize
                }
            },
            axisTick: {
                show: false,
            },
            axisLine: {
                show: false,
            }
        }],
        series: [
            {
                name: ' ',
                type: 'bar',
                barWidth: 16,
                label: {
                    normal: {
                        show: true,
                        position: 'right',
                        formatter: '{c}%',
                    }
                },
                data: [
                    {
                        value: 20,
                        itemStyle: {
                            normal: {
                                color: '#acd680'
                            }
                        }
                    }, {
                        value: 45,
                        itemStyle: {
                            normal: {
                                color: '#88e186'
                            }
                        }
                    }, {
                        value: 56,
                        itemStyle: {
                            normal: {
                                color: '#81e7cf'
                            }
                        }
                    }, {
                        value: 60,
                        itemStyle: {
                            normal: {
                                color: '#82dae6'
                            }
                        }
                    }, {
                        value: 64,
                        itemStyle: {
                            normal: {
                                color: '#80cbc4'
                            }
                        }
                    }],
            }]
    };
    var myChart = echarts.init(document.getElementById('HiddenDangerEcharts'));
    myChart.setOption(option, true);
}