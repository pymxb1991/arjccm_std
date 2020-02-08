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
    GridRankingEchartsFun();
    ChangeEchartsFun();
    //地图
    map();
    HelpTimesEchartsFun();
    //城市部件丢失率TOP5
    HelpRateEchartsFun();

})
//网格排名TOP5
function GridRankingEchartsFun(){
    var data = [17, 20, 45, 56, 60, 64];
    var xMax = 100;
    var option = {
        tooltip: {
            show: true,
            formatter: "{b} {c}"
        },
        grid: {
            left: '17%',
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
            data: [ '第一网格', '第四网格', '第二网格', '第五网格', '第三网格'],
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
    var myChart = echarts.init(document.getElementById('GridRankingEcharts'));
    myChart.setOption(option);
}
//三年变化量
var color = ['#4573a7', '#D87A82', '#89a54e', '#71588f', '#4298af', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];

function ChangeEchartsFun(){
 var option = {
     color:color,
     tooltip: {
         trigger: 'axis'
     },
     legend: {
         data:['2015','2016','2017'],
         textStyle: {
             color: '#fff',
             fontSize: _fontSize
         }
     },
     grid: {
         left: '3%',
         right: '3%',
         bottom: '3%',
         containLabel: true
     },
     xAxis: {
         type: 'category',
         boundaryGap: false,
         data: ['1','2','3','4','5','6','7','8','9','10','11','12'],
         axisLabel: {
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
         axisPointer: {
             type: 'shadow'
         },
         axisTick: {
             show: false,
         },
     },
     yAxis: {
         type: 'value',
         axisLabel: {
             textStyle: {
                 color: '#fff',
                 fontSize: _fontSize
             }
         },
         splitLine: {
             show: false
         },
         axisLine: {
             lineStyle: {
                 color: '#fff'
             }
         },
     },
     series: [
         {
             name:'2015',
             type:'line',
             data:[120, 132, 101, 134, 90, 230, 210, 101, 134, 90, 230, 210]
         },
         {
             name:'2016',
             type:'line',
             data:[220, 182, 191, 234, 290, 330, 310, 191, 234, 290, 330, 310]
         },
         {
             name:'2017',
             type:'line',
             data:[150, 232, 201, 154, 190, 330, 410, 201, 154, 190, 330, 410]
         }
     ]
 };
    var myChart = echarts.init(document.getElementById('ChangeEcharts'));
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

//年度帮扶次数
function HelpTimesEchartsFun(){
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
            top: '5%',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                data: ['1','2','3','4','5','6','7','8','9','10','11','12'],
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
                    formatter: '{value}',
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
                data: [90,89,56,87,91,66,90,89,56,87,91,66],
                barWidth: 15,
                itemStyle: {
                    normal: {
                        color: '#89a54e'
                    }
                }
            },
        ]
    };
    var myChart = echarts.init(document.getElementById('HelpTimesEcharts'));
    myChart.setOption(option);
}
//年度帮扶率
function HelpRateEchartsFun(){
   var option = {
       grid: {
           left: '3%',
           right: '3%',
           bottom: '5%',
           top: '5%',
           containLabel: true
       },
       tooltip: {
           trigger: 'axis'
       },
       xAxis: {
           type: 'category',
           boundaryGap: false, //坐标轴两边留空白策略
           axisTick: {
               alignWithLabel: true //刻度线与标签对其
           },
           axisPointer: {
               type: 'shadow'
           },
           axisLine: {
               show: true,
               lineStyle: {
                   color: '#ffffff'
                   //  width:8,//这里是为了突出显示加上的，可以去掉
               }
           },
           //  改变x轴字体颜色和大小
           axisLabel: {
               textStyle: {
                   color: '#ffffff'
               }
           },
           data: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12']
       },
       yAxis: {
           type: 'value',
           splitLine: {
               show: false
           },
         //  scale: true, //脱离0的束缚
           axisLabel: {
               formatter: '{value}%',
               textStyle: {
                   color: '#ffffff'
               }
           },
           //  改变x轴颜色
           axisLine: {
               lineStyle: {
                   color: '#ffffff'
                   //                            width:8,//这里是为了突出显示加上的，可以去掉
               }
           }
       },
       series: [{
           name: '年度帮扶率',
           type: 'line',
           stacked: true,
           smooth: true, //是否平滑曲线显示
           symbol: 'circle', //标记的图形。ECharts 提供的标记类型包括 'circle', 'rect', 'roundRect', 'triangle', 'diamond', 'pin', 'arrow'
           symbolSize: 5, //标记的大小，可以设置成诸如 10 这样单一的数字，也可以用数组分开表示宽和高，例如 [20, 10] 表示标记宽为20，高为10[ default: 4 ]
           showSymbol: true, //是否显示 symbol, 如果 false 则只有在 tooltip hover 的时候显示
           lineStyle: { //线条样式
               normal: {
                   width: 1 //线宽。[ default: 2 ]
               }
           },
           areaStyle: { //区域填充样式
               normal: {
                   color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{ //填充的颜色。
                       offset: 0, // 0% 处的颜色
                       color: 'rgba(104, 221, 109, 0.3)'
                   }, {
                       offset: 0.8, // 80% 处的颜色
                       color: 'rgba(104, 221, 109, 0.3)'
                   }], false),
                   shadowColor: 'rgba(0, 0, 0, 0.1)', //阴影颜色。支持的格式同color
                   shadowBlur: 10 //图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX, shadowOffsetY 一起设置图形的阴影效果
               }
           },
           itemStyle: {
               normal: {
                   color: '#68DD77',
                   borderColor: 'rgba(104, 221, 109, 0.27)', //图形的描边颜色。支持的格式同 color
                   borderWidth: 6 //描边线宽。为 0 时无描边。[ default: 0 ]
               }
           },
           data: [22, 23, 33, 34, 36, 38, 39, 41, 42, 43, 32, 44]
       }
       ]
   };
    var myChart = echarts.init(document.getElementById('HelpRateEcharts'));
    myChart.setOption(option, true);
}