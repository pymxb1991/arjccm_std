/**
 * 初始化
 */
$(document).ready(function () {
     initcountStatistics();
     initOrgNpseBysysdicts();
     initOrgNpseBysysArea();
     initOrgNpseregisteredBysysArea();
});


/**
 *行业类型统计
 */
function initOrgNpseBysysdicts() {
    $.ajax({
        type: "POST",
        url: ctx+"/statistics/countStatistics/getOrgNpseBysysdicts",
        dataType: "json",
        cache: false,
        async: true,
        success: function (data) {
            showOrgNps(data);
        }
    });
}

function showOrgNps(data){
    var option = {
        title : {
            text: '行业类型统计'
        },
        tooltip : {
            trigger: 'axis'
        },
        xAxis : [
            {
                type : 'category',
                data :  data["name"],
                axisLabel: {
                    interval: 0,
                    rotate: 18,
                    textStyle: {    //文字样式
                        fontSize: 10,
                        // fontFamily: 'Microsoft YaHei'
                    }
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'所属行业',
                type:'bar',
                data:data["value"],
                markPoint : {
                    data : [

                    ]
                },
                itemStyle:{
                    normal: {
                        color: function(params) {
                            // build a color map as your need.
                            var colorList = [
                                '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
                                '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                                '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
                            ];
                            return colorList[params.dataIndex]
                        },
                        label: {
                            show: true,
                            position: 'top',
                            formatter: '{c}'
                        }
                    }
                }
            }
        ]
    };

    var Barchart = echarts.init(document.getElementById('orgnpsEcharts'));
    Barchart.setOption(option);
}

/**
 * 场所类型统计
 */
function initcountStatistics(){
    $.ajax({
        type: "POST",
        url: ctx+"/statistics/countStatistics/getCountbyplaceType",
        dataType: "json",
        cache: false,
        async: true,
        success: function (data) {
            showInfo(data);
        }
    });
}



function showInfo(data){
    var option = {
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data: data["name"]
        },
        toolbox: {
            show : true,
            feature : {
                dataView : {show: true, readOnly: false},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        series : [
            {
                name:'场所类型统计',
                type:'pie',
                radius : ['40%', '70%'],
                itemStyle : {
                    normal : {
                        label : {
                            show : true
                        },
                        labelLine : {
                            show : false
                        }
                    },
                    emphasis : {
                        label : {
                            show : true,
                            position : 'center',
                            textStyle : {
                                fontSize : '18',
                                fontWeight : 'bold'
                            }
                        }
                    }
                },
                data: data["value"]
            }
        ]
    };

    var Barchart = echarts.init(document.getElementById('statisticsEcharts'));
    Barchart.setOption(option);
}



/**
 * 各区域企业分布
 */
function initOrgNpseBysysArea() {
    $.ajax({
        type: "POST",
        url: ctx+"/statistics/countStatistics/getOrgNpseBysysArea",
        dataType: "json",
        cache: false,
        async: true,
        success: function (data) {
            showOrgNpsArea(data);
        }
    });
}

function showOrgNpsArea(data){
    var option = {
        title : {
            text: '各区域企业分布统计'
        },
        tooltip : {
            trigger: 'axis'
        },
        xAxis : [
            {
                type : 'category',
                data :  data["name"],
                axisLabel: {
                    interval: 0,
                    rotate: 30
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'区域',
                type:'bar',
                data:data["value"],
                markPoint : {
                    data : [

                    ]
                },
                itemStyle:{
                    normal: {
                        color: function(params) {
                            // build a color map as your need.
                            var colorList = [
                                '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
                                '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                                '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
                            ];
                            return colorList[params.dataIndex]
                        },
                        label: {
                            show: true,
                            position: 'top',
                            formatter: '{c}'
                        }
                    }
                }
            }
        ]
    };

    var Barchart = echarts.init(document.getElementById('areaEcharts'));
    Barchart.setOption(option);
}

function initOrgNpseregisteredBysysArea() {
    $.ajax({
        type: "POST",
        url: ctx+"/statistics/countStatistics/getOrgNpseregisteredBysysArea",
        dataType: "json",
        cache: false,
        async: true,
        success: function (data) {
            showOrgNpseregisteredBysysArea(data);
        }
    });
}

function showOrgNpseregisteredBysysArea(data){
    var option = {
        title : {
            text: '企业注册资本情况统计'
        },
        tooltip : {
            trigger: 'axis'
        },
        xAxis : [
            {
                type : 'category',
                data :  data["name"],
                axisLabel: {
                    interval: 0,
                    rotate: 30
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'区域',
                type:'bar',
                data:data["value"],
                markPoint : {
                    data : [

                    ]
                },
                itemStyle:{
                    normal: {
                        color: function(params) {
                            // build a color map as your need.
                            var colorList = [
                                '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0',
                                '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                                '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',

                            ];
                            return colorList[params.dataIndex]
                        },
                        label: {
                            show: true,
                            position: 'top',
                            formatter: '{c}'
                        }
                    }
                }
            }
        ]
    };

    var Barchart = echarts.init(document.getElementById('registeredEcharts'));
    Barchart.setOption(option);
}