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
    //产权类型
    RightTypeEchartsFun();
    //流动人口来源
    HousesNumEchartsFun();
    //地图
    map();
    //房屋关注程度
    HouseFollowEchartsFun();

})
//产权类型
function RightTypeEchartsFun(){
    var option = {
        grid: {
            left: '3%',
            right: '3%',
            bottom: '3%',
            top:'3%',
            containLabel: true
        },
        tooltip: {
            show: "true",
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        yAxis: {
            type: 'value',
            axisTick: {
                show: false
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#fff',
                }
            },
            splitLine: {
                show: false,
                lineStyle: {
                    color: '#aaa',
                }
            },
        },
        xAxis: [{
            type: 'category',
            axisTick: {
                show: false
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#fff',
                }
            },
            data: ['私产', '小产权', '单位资管产权','直管公产','其他']
        },
            {
            type: 'category',
            axisLine: {
                show: false
            },
            axisLabel: {
                show: false
            },
                data: ['私产', '小产权', '单位资管产权','直管公产','其他']
        },
        ],
        series: [
            {
            name: '总数',
            type: 'bar',
            xAxisIndex: 1,
            itemStyle: {
                normal: {
                    show: true,
                    color: '#277ace',
                    barBorderRadius: 0,
                    borderWidth: 0,
                    borderColor: '#333',
                }
            },
            barWidth: '30%',
            data: [100, 100, 100,100,100]
        }, {
                name: '',
                type: 'bar',
                itemStyle: {
                    normal: {
                        show: true,
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: '#00FFE6'
                        }, {
                            offset: 1,
                            color: '#007CC6'
                        }]),
                        barBorderRadius: 0,
                        borderWidth: 0,
                        borderColor: '#333',
                    }
                },
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        textStyle: {
                            color: '#fff'
                        }
                    }
                },
                barWidth: '30%',
                data: [95, 85, 55,79,86]
            },
        ]
    };
    var myChart = echarts.init(document.getElementById('RightTypeEcharts'));
    myChart.setOption(option);
}
//房屋数量分布
function HousesNumEchartsFun(){

    var myChart = echarts.init(document.getElementById('HousesNumEcharts'));
    var color = ['#4573a7', '#D87A82', '#89a54e', '#71588f', '#4298af', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];
    var data = [{
        value: 28,
        name: '自住房'
    }, {
        value: 10,
        name: '出租房'
    }, {
        value: 18,
        name: '空房'
    }, {
        value: 20,
        name: '其他'
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
            data: ['自住房', '出租房', '空房', '其他']
        },
        series: [{
            name: '房屋占比',
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

//房屋关注程度
function HouseFollowEchartsFun(){
    var colorArray = [
        {
            top: 'rgba(173,39,49,1)',//红shen
            bottom: 'rgba(173,39,49, 0.3)'
        },
        {
            top: 'rgba(195,175,49,1)',//黄
            bottom: 'rgba(195,175,49, 0.3)'
        },{
            top: 'rgba(40,203,195,1)',//蓝
            bottom: 'rgba(40,203,195, 0.3)'
        },{
            top: 'rgba(56,215,48,1)',//绿
            bottom: 'rgba(56,215,48, 0.3)'
        },
        {
            top: 'rgba(42,45,221,1)',//深蓝
            bottom: 'rgba(42,45,221, 0.3)'
        },
        {
            top: 'rgba(215,47,215,1)',//粉
            bottom: 'rgba(215,47,215, 0.3)'
        }
    ];
    var option = {
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            top: '3%',
            containLabel: true
        },
        tooltip: {
            show:"true",
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        xAxis:  {
            type: 'value',
            axisLabel: {
                formatter: '{value} %',
                textStyle: {
                    color: '#fff',
                }
            },
            position:'bottom',
            axisTick : {show: false},
            axisLine: {
                show: false,
                lineStyle:{
                    color:'#fff',
                }
            },
            splitLine: {
                show: false
            },
        },
        yAxis: [
            {
                type: 'category',
                axisTick : {
                    show: true,
                    alignWithLabel: true,
                    length:5,

                },
                inverse:'true', //排序
                axisLine: {
                    show: false,
                    lineStyle:{
                        color:'#fff',
                    }
                },
                data: ['高危','关注','一般','放心']
            }
        ],
        series: [
            {
                name: '关注度',
                type: 'bar',
                itemStyle:{
                    normal: {
                        show: true,
                        color: function (params) {
                            var num = colorArray.length;
                            return{
                                type: 'linear',
                                colorStops: [
                                    {
                                    offset: 0, color: colorArray[params.dataIndex%num].bottom
                                },
                                    {
                                    offset: 1, color: colorArray[params.dataIndex%num].top
                                },
                                    {
                                    offset: 0, color: colorArray[params.dataIndex%num].bottom
                                },
                                    {
                                    offset: 1, color: colorArray[params.dataIndex%num].top
                                },
                                    {
                                    offset: 0, color: colorArray[params.dataIndex%num].bottom
                                },
                                    {
                                    offset: 1, color: colorArray[params.dataIndex%num].top
                                },
                                    {
                                    offset: 0, color: colorArray[params.dataIndex%num].bottom
                                },
                                    {
                                    offset: 1, color: colorArray[params.dataIndex%num].top
                                },
                                    {
                                    offset: 0, color: colorArray[params.dataIndex%num].bottom
                                },
                                    {
                                    offset: 1, color: colorArray[params.dataIndex%num].top
                                },
                                    {
                                    offset: 0, color: colorArray[params.dataIndex%num].bottom
                                },
                                    {
                                    offset: 1, color: colorArray[params.dataIndex%num].top
                                }
                                ],
                                //globalCoord: false
                            }
                        },
                        barBorderRadius:50,
                        borderWidth:0,
                        borderColor:'#333',
                    }
                },
                barGap:'0%',
                barWidth: '38%',
                barCategoryGap:'30%',
                data: [80, 62, 20, 20]
            }
        ]
    };
    var myChart = echarts.init(document.getElementById('HouseFollowEcharts'));
    myChart.setOption(option);
}