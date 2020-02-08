var windowsHeight,_fontSize=14,_fontSize1=26,breakData=8;legendTop='30%',radiusData=[90, 65],lengthECharts=30;
$(document).ready(function () {
    windowsHeight= $(window).width();
if(windowsHeight>1366){
    _fontSize=14;
    legendTop='30%';
    radiusData=[90, 65];
    lengthECharts=30;
    _fontSize1=26;
    breakData=8;
}else{
    _fontSize=12;
    legendTop='15%';
    radiusData=[60, 45];
    lengthECharts=20;
    _fontSize1=14;
    breakData=6;
}
//社区事务
    ShequShiwu();
    //近七周报警
    AlarmWeekInfo()
//风险评估
    GetWorkSheets();
    //案事件报警
    AnShiJian();
    //通知公告
   // NoticeFun();//1
    //获取值班信息
    GetDutyToday();//1
    //总数 在线率 完好率
    Rate();//1
    //分期在线率完好率
    AlarmInfo()
    //最新告警
    GetAlarmTopN();//1
     //告警一览
    //GetAlarmHtml();//1
    //GetAlarm();
    //initGetAlarm('0')
    //近七周告警统计图
    // InitSevenWeekRate()
    //运行总览
   // FaultData();//1
    //告警分期
 //  AlarmQiShu()
    //仪表盘
    //enforce();
    // initCabinet();
   // 工单按级别统计图
   // initGetWorkSheets();
    //今日运维情势
   GetSituationToday()
   // OnlineRateReport();
});
//总数 在线率 完好率
function Rate() {
    $.ajax({
        type: "POST",
        url: "/TongRen/GetAllOnlineRate",
        data: {},
        dataType: "json",
        async: true,
        success: function (data) {
            $('#CameraTotal').html(data.Total);
            $('#OnLineRate').html('' + data.OnlineRate + '%')
            $('#OkRate').html('' + data.IntactRate + '%')
        },
        error: function () {

        }
    })
}
//今日运维情势
function GetSituationToday() {
    $.ajax({
        type: "POST",
        url: "/TongRen/SituationToday",
        data: {},
        dataType: "json",
        async: true,
        success: function (data) {
            if (data) {
                $("#alarmnum").html(data.AlarmNum);
                $("#wsnum").html(data.WSNum);
                $("#endwsnum").html(data.EndWSNum);
                $("#wsrate").html(roundFun(data.WSRate, 3) * 100 + "%");
            }
        }
    });
}
//获取时间
function getBeforeDate(n) {
    var n = n;
    var d = new Date();
    var year = d.getFullYear();
    var mon = d.getMonth() + 1;
    var day = d.getDate();
    if (day <= n) {
        if (mon > 1) {
            mon = mon - 1;
        } else {
            year = year - 1;
            mon = 12;
        }
    }
    d.setDate(d.getDate() - n);
    year = d.getFullYear();
    mon = d.getMonth() + 1;
    day = d.getDate();
    s = (mon < 10 ? ('0' + mon) : mon) + "-" + (day < 10 ? ('0' + day) : day);
    return s;
}
//获取时间
function GetDate(date) {
    if (date == null) {
        date = '';
        return date;
    }
    var da = new Date(parseInt(date.replace("/Date(", "").replace(")/", "").split("+")[0])),
                     getMonth = (da.getMonth() + 1),
                     getDate = da.getDate(),
                     getHours = da.getHours(),
                     getMinutes = da.getMinutes(),
                     getSeconds = da.getSeconds();
    if (getMonth < 10) {
        getMonth = '0' + getMonth;
    }
    if (getDate < 10) {
        getDate = '0' + getDate;
    }
    if (getHours < 10) {
        getHours = '0' + getHours;
    }
    if (getMinutes < 10) {
        getMinutes = '0' + getMinutes;
    }
    if (getSeconds < 10) {
        getSeconds = '0' + getSeconds;
    }

    return da.getFullYear() + "-" + getMonth + "-" + getDate + " " + getHours + ":" + getMinutes + ":" + getSeconds;
}
//保留小数，四舍五入，保留位数为roundDigit  
function roundFun(numberRound, roundDigit) {
    if (numberRound >= 0) {
        var tempNumber = parseInt((numberRound * Math.pow(10, roundDigit) + 0.5)) / Math.pow(10, roundDigit);
        return tempNumber;
    }
    else {
        numberRound1 = -numberRound
        var tempNumber = parseInt((numberRound1 * Math.pow(10, roundDigit) + 0.5)) / Math.pow(10, roundDigit);
        return -tempNumber;
    }
}
//在线率
function BG(value) {
    var Grade = '#2cc345';
    if (value >= 90) {
        Grade = '#2cc345';
    } else if (value < 90 && value >= 80) {
        Grade = '#ffa92e';
    } else if (value < 80) {
        Grade = '#f46565';
    }
    return Grade;
}
//离线率
function BGOut(value) {
    var Grade = '#2cc345';
    if (value < 10) {
        Grade = '#2cc345';
    } else if (value < 20 && value >= 10) {
        Grade = '#ffa92e';
    } else if (value >= 20) {
        Grade = '#f46565';
    }
    return Grade;
}
function enforce() {
    radialIndicator.defaults.radius = 85;
    radialIndicator.defaults.barWidth = 12;
    radialIndicator.defaults.percentage = true;
    radialIndicator.defaults.roundCorner = true;
    radialIndicator.defaults.barColor = {
        0: '#FF0000',
        33: '#FFFF00',
        66: '#0066FF',
        100: '#33CC33'
    };
    var radialObj1 = $('#echarts1-1').radialIndicator({
    }).data('radialIndicator');
    radialObj1.value(95);
    var radialObj2 = $('#echarts1-2').radialIndicator({
    }).data('radialIndicator');
    radialObj2.value(75);
    var radialObj3 = $('#echarts1-3').radialIndicator({
    }).data('radialIndicator');
    radialObj3.value(80);
    var radialObj4 = $('#echarts1-4').radialIndicator({
    }).data('radialIndicator');
    radialObj4.value(90);
}
//各辖区视频设备在线率排名
function ranking() {
    
    //var option = {
    //    grid: {
    //        left: '0',
    //        right: '6%',
    //        bottom: '3%',
    //        top: '3%',
    //        containLabel: true
    //    },
    //    tooltip: {
    //        formatter: '{b}<br />{a}: {c}%'
    //    },
    //    legend: {
    //        data: ['在线率'],
    //        show: false
    //    },
    //    xAxis: {
    //        min: 0,
    //        max: 100,
    //        type: 'value',
    //        axisLine:{
    //            lineStyle: {
    //                color:'#f3f3f3'
    //            }
    //        },
    //        axisLabel: {
    //            show: true,
    //            formatter: '{value} %',
    //            textStyle: {
    //                color: '#fff',
    //                fontSize:14,
    //            }
    //        }
    //    },
    //    yAxis: [{
    //        type: 'category',
    //        axisLabel: {
    //            show: true,
    //            textStyle: {
    //                color: '#fff',
    //                fontSize: 14,
    //            }
    //        },
    //        axisLine: {
    //            lineStyle: {
    //                color: '#f3f3f3'
    //            }
    //        },
    //        data: ['铜仁市局', '江口分局', '松桃分局', '德江分局', '石阡分局', '沿河分局', '印江分局', '玉屏分局', '思南分局','万山分局' ]
    //    }],
    //    series: [{
    //        name: '在线率',
    //        type: 'bar',
    //        stack: '总量',
    //        barWidth: '40%',
    //        itemStyle: {
    //            normal: {
    //                color: '#1aa1ef'
    //            }
    //        },
    //        data: [95, 93, 90, 88, 85, 82, 80,78,75,70]
    //    }]
    //};
    var color = ['#4573a7', '#aa4644', '#89a54e', '#71588f', '#4298af', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];
    option = {
        tooltip: {
            //trigger: 'item',
            //formatter: "{a}: {c} <br/>{b} "
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            type: 'scroll',
            orient: 'vertical',
            top: '23%',
            left: '15%',
            textStyle: {
                color: '#fff',
                fontSize: _fontSize,
            },
            data: ['存储设备', '网络设备', '服务器', '数据库', '中间件', '应用服务']
        },
        calculable: true,
        series:
            {
                name: '资产占比',
                type: 'pie',
                radius: [30, 125],
                center: ['60%', '60%'],
                roseType: 'area',
                color: color,
                label: {
                    normal: {
                        show: false
                    }
                },
                lableLine: {
                    normal: {
                        show: false
                    }
                },
                data: [
                    { value: 35, name: '存储设备' },
                    { value: 30, name: '网络设备' },
                    { value: 25, name: '服务器' },
                    { value: 20, name: '数据库' },
                    { value: 15, name: '中间件' },
                    { value: 10, name: '应用服务' },
                ]
            }
    };
    var Barchart = echarts.init(document.getElementById('echarts-zc'));
    Barchart.setOption(option);
}
//今日告警情势
function AlarmToday() {
    $.ajax({
        type: "POST",
        url: "/TongRen/GeChangAlarm",
        data: {},
        dataType: "json",
        async: true,
        success: function (data) {
            var arrName = [];
            var arrData = [];
            if (data) {

                var len = data.length;
                for (var i = 0; i < len; i++) {
                    arrName[i] = data[i].Area
                    arrData[i] = {
                        value: data[i].Total,
                        name: data[i].Area
                    }
                }
                report(arrName, arrData)
            }
        }
    });
}
function report(arrName, data) {
    var color = ['#4573a7', '#aa4644', '#89a54e', '#71588f', '#4298af', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];   
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            //type: 'scroll',
            orient: 'vertical',
            right: 'right',
            top: 15,
            right: '10%',
            textStyle: {
                color: '#fff',
                fontSize: _fontSize,
            },
            data: arrName
        },
        series: [{
            name: '告警情况',
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
    var Barchart = echarts.init(document.getElementById('Prison'));
    Barchart.setOption(option);
}
function FaultData() {

    $.ajax({
        type: "POST",
        url: "/TongRen/DayEquipRate",
        data: {},
        dataType: "json",
        async: true,
        success: function (data) {
            fault(data)
        },
        error: function () {

        }
    })
}
//资源运行总览
function fault(data) {

    var bgColor = '#0078ff';
    var containers = getClassNames('echarts3-2', 'div');
    function getClassNames(classStr, tagName) {
        if (document.getElementsByClassName) {
            return document.getElementsByClassName(classStr)
        } else {
            var nodes = document.getElementsByTagName(tagName),
				ret = [];
            for (i = 0; i < nodes.length; i++) {
                if (hasClass(nodes[i], classStr)) {
                    ret.push(nodes[i])
                }
            }
            return ret;
        }
    }
    function hasClass(tagStr, classStr) {
        var arr = tagStr.className.split(/\s+/); //这个正则表达式是因为class可以有多个,判断是否包含  
        for (var i = 0; i < arr.length; i++) {
            if (arr[i] == classStr) {
                return true;
            }
        }
        return false;
    }
    options = [{
        title: {
            text: data[0].Count,
            // text: '300',
            x: '48%',
            y: '35%',
            textAlign: "center",
            itemGap: 20,
            textStyle: {
                fontWeight: 'bold',
                fontSize: 35,
                color: '#eea807'
            },
            subtextStyle: {
                fontWeight: 'bold',
                fontSize: 25,
                color: '#eea807'
            },
            subtext: '75%',
        },
        series: [{
            name: ' ',
            type: 'pie',
            radius: ['75%', '100%'],
            startAngle: 225,
            color: [new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: '#00a2ff'
            }, {
                offset: 1,
                color: '#70ffac'
            }]), "transparent"],
            hoverAnimation: false,
            legendHoverLink: false,
            itemStyle: {
                normal: {
                    borderColor: "transparent",
                    borderWidth: "10"
                },
                emphasis: {
                    borderColor: "transparent",
                    borderWidth: "10"
                }
            },
            z: 10,
            labelLine: {
                normal: {
                    show: false
                }
            },
            data: [{
                value: 70
            }, {
                value: 25
            }]
        },
        {
            name: '',
            type: 'pie',
            radius: ['75%', '100%'],
            startAngle: 225,
            color: ["#153664", "transparent"],
            hoverAnimation: false,
            labelLine: {
                normal: {
                    show: false
                }
            },
            data: [{
                value: 75
            }, {
                value: 25
            }]
        }

        ]
    },
    {
        title: {
            text: data[1].Count,
            //text: '400',
            x: '48%',
            y: '35%',
            textAlign: "center",
            itemGap: 20,
            textStyle: {
                fontWeight: 'bold',
                fontSize: 35,
                color: '#eea807'
            },
            subtextStyle: {
                fontWeight: 'bold',
                fontSize: 25,
                color: '#eea807'
            },
            subtext: '75%',
        },
        series: [{
            name: ' ',
            type: 'pie',
            radius: ['75%', '100%'],
            startAngle: 225,
            color: [new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: '#00a2ff'
            }, {
                offset: 1,
                color: '#70ffac'
            }]), "transparent"],
            hoverAnimation: false,
            legendHoverLink: false,
            itemStyle: {
                normal: {
                    borderColor: "transparent",
                    borderWidth: "10"
                },
                emphasis: {
                    borderColor: "transparent",
                    borderWidth: "10"
                }
            },
            z: 10,
            labelLine: {
                normal: {
                    show: false
                }
            },
            data: [{
                value: 70
            }, {
                value: 25
            }]
        },
        {
            name: '',
            type: 'pie',
            radius: ['75%', '100%'],
            startAngle: 225,
            color: ["#153664", "transparent"],
            hoverAnimation: false,
            labelLine: {
                normal: {
                    show: false
                }
            },
            data: [{
                value: 75
            }, {
                value: 25
            }]
        }

        ]
    },
    //{
    //    title: {
    //        //text: data[2].Count,
    //       // text: '300',
    //        x: '48%',
    //        y: '35%',
    //        textAlign: "center",
    //        itemGap: 20,
    //        textStyle: {
    //            fontWeight: 'bold',
    //            fontSize: 24,
    //            color: '#eea807'
    //        },
    //        subtextStyle: {
    //            fontWeight: 'bold',
    //            fontSize: 20,
    //            color: '#eea807'
    //        },
    //        subtext: '75%',
    //    },
    //    series: [{
    //        name: ' ',
    //        type: 'pie',
    //        radius: ['75%', '100%'],
    //        startAngle: 225,
    //        color: [new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
    //            offset: 0,
    //            color: '#00a2ff'
    //        }, {
    //            offset: 1,
    //            color: '#70ffac'
    //        }]), "transparent"],
    //        hoverAnimation: false,
    //        legendHoverLink: false,
    //        itemStyle: {
    //            normal: {
    //                borderColor: "transparent",
    //                borderWidth: "10"
    //            },
    //            emphasis: {
    //                borderColor: "transparent",
    //                borderWidth: "10"
    //            }
    //        },
    //        z: 10,
    //        labelLine: {
    //            normal: {
    //                show: false
    //            }
    //        },
    //        data: [{
    //            value: 90
    //        }, {
    //            value: 25
    //        }]
    //    }, {
    //        name: '',
    //        type: 'pie',
    //        radius: ['75%', '100%'],
    //        startAngle: 225,
    //        color: ["#153664", "transparent"],
    //        hoverAnimation: false,
    //        labelLine: {
    //            normal: {
    //                show: false
    //            }
    //        },
    //        data: [{
    //            value: 75
    //        }, {
    //            value: 25
    //        }]
    //    }

    //    ]
    //},
    //{
    //    title: {
    //        // text: data[3].Count,
    //        text: '10',
    //        x: '48%',
    //        y: '35%',
    //        textAlign: "center",
    //        itemGap: 20,
    //        textStyle: {
    //            fontWeight: 'bold',
    //            fontSize: 24,
    //            color: '#eea807'
    //        },
    //        subtextStyle: {
    //            fontWeight: 'bold',
    //            fontSize: 20,
    //            color: '#eea807'
    //        },
    //        subtext: '75%',
    //    },
    //    series: [{
    //        name: ' ',
    //        type: 'pie',
    //        radius: ['75%', '100%'],
    //        startAngle: 225,
    //        color: [new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
    //            offset: 0,
    //            color: '#00a2ff'
    //        }, {
    //            offset: 1,
    //            color: '#70ffac'
    //        }]), "transparent"],
    //        hoverAnimation: false,
    //        legendHoverLink: false,
    //        itemStyle: {
    //            normal: {
    //                borderColor: "transparent",
    //                borderWidth: "10"
    //            },
    //            emphasis: {
    //                borderColor: "transparent",
    //                borderWidth: "10"
    //            }
    //        },
    //        z: 10,
    //        labelLine: {
    //            normal: {
    //                show: false
    //            }
    //        },
    //        data: [{
    //            value: 100
    //        }, {
    //            value: 25
    //        }]
    //    }, {
    //        name: '',
    //        type: 'pie',
    //        radius: ['75%', '100%'],
    //        startAngle: 225,
    //        color: ["#153664", "transparent"],
    //        hoverAnimation: false,
    //        labelLine: {
    //            normal: {
    //                show: false
    //            }
    //        },
    //        data: [{
    //            value: 75
    //        }, {
    //            value: 25
    //        }]
    //    }

    //    ]
    //},
    ];


    var charts = [];
    for (var i = 0; i < options.length; ++i) {
        var chart = echarts.init(containers[i]);
        if (i > 0) {
            options[i].series[0].silent = true;
        }
        //var value = parseInt(Math.random() * 100) + 1,
        //    value_ = 75 * value / 100;
        var value = data[i].Rate,
                value_ = 75 * value / 100;
        options[i].title.subtext = value + "%";
        options[i].series[0].data[0].value = value_;
        options[i].series[0].data[1].value = 100 - value_;
        chart.setOption(options[i]);
        charts.push(chart);
    }
}
//获取值班信息
function GetDutyToday() {

    $.ajax({
        type: "POST",
        url: "/SysManager/Duty/GetDutySetToday",
        data: {},
        dataType: "json",
        async: true,
        success: function (data) {
            
            if (data != '') {
                var html = '';
                html += '<li style="margin-left:5%">';
                html += '<i></i>';
                html += '<b title="值班时间：' + GetDate(data.StartTime) + '&nbsp;至&nbsp;' + GetDate(data.EndTime) + '">运维值班:&nbsp;&nbsp;' + data.DutyName + '</b>';
                html += '</li>';
                html += '<li>';


                if (data.TelePhone != '' && data.TelePhone != null) {
                    html += '<i></i>';
                    html += '<b title="值班时间：' + GetDate(data.StartTime) + '&nbsp;至&nbsp;' + GetDate(data.EndTime) + '">值班电话:&nbsp;&nbsp;' + data.TelePhone + '</b>';
                }
                html += '</li>';
                html += '<li  style="margin-left:5%">';
                html += '<i></i>';
                html += '<b title="值班时间：' + GetDate(data.StartTime) + '&nbsp;至&nbsp;' + GetDate(data.EndTime) + '">带班领导:&nbsp;&nbsp;' + data.DutyLeader + '</b>';
                html += '</li>';
                html += '<li>';
                html += '<i></i>';
                html += '<b  title="值班时间：' + GetDate(data.StartTime) + '&nbsp;至&nbsp;' + GetDate(data.EndTime) + '">值班人员:&nbsp;&nbsp;' + data.DutyPerson + '</b>';
                html += '</li>';
                $("#DutyUL").append(html);
                //if (data.TelePhone != '' && data.TelePhone != null) {
                //    $("#CurrentTime").html('值班电话:&nbsp;&nbsp;' + data.TelePhone + '');

                //}
            } else {
                $("#DutyUL").html('<li style="line-height:30px;color:#fff">今日暂无值班</li>')
            }
        },
        error: function () {
            
        }
    });
}
//工单按级别统计图
function initGetWorkSheets() {
    $.ajax({
        type: "POST",
        url: "/DaQing/GetWorkSheets",
        data: {},
        dataType: "json",
        cache: false,
        async: true,
        success: function (data) {
            if (data) {

                //GetWorkSheets(data);
            }
        }, error: function () {

        }
    });
}
function GetWorkSheets() {
    var nameArr = [], DataArr = [];
   /* for (var i = 0; i < data.length; i++) {
        nameArr.push(data[i].Police);
        DataArr.push({
            value: data[i].Total,
            name: data[i].Police
        });
    }*/
    var color = ['#aa4644', '#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];
    var data = [{
       value: 28,
       name: '重大事项'
    }, {
       value: 10,
       name: '风险事件'
    }, {
       value: 18,
       name: '稳评案例'
    }, {
       value: 20,
       name: '其他事件'
    }
    ];
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            //type: 'scroll',
            orient: 'vertical',
            top: legendTop,
            right: '15%',
            textStyle: {
                color: '#fff',
                fontSize: _fontSize,
            },
            data: data
           // data: nameArr
        },
        series: [{
            name: '风险评估',
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
    var Barchart = echarts.init(document.getElementById('echarts3-1'));
    Barchart.setOption(option);
    Barchart.on('click', function (params) {

    });

}
//各区域视频在线率统计
function OnlineRateReport() {
    $.ajax({
        type: "POST",
        url: "/TongRen/GetGeChangAssetOnline",
        dataType: "json",
        async: true,
        success: function (data) {
            
            var len = data.length;
            if (len != 0) {
                $('#OnlineRateReport').html('');
                var html = '';
                var ClassName = 'l-grid-row-alt'
                for (var i = 0; i < len; i++) {
                    if (i % 2 == 0) {
                        ClassName = ''
                    } else {
                        ClassName = 'l-grid-row-alt '
                    }
                    html += '<tr class="' + ClassName + '">'
                    html += ' <td>' + data[i].Area + '</td>';
                    html += '<td>' + data[i].Total + '</td>';
                    html += '<td>';
                    html += '<div class="Bar">';
                    html += '<div style="width: ' + data[i].Online + '%; border-radius:8px;"><span>' + data[i].Online + '%</span></div>';
                    html += '</div>';
                    html += '</td>';
                    html += '<td>';
                    html += '<div class="Bar">';
                    html += '<div style="width: ' + data[i].Intact + '%; border-radius:8px;"><span>' + data[i].Intact + '%</span></div>';
                    html += '</div>';
                    html += '</td>';
                    html += '<td>' + (i + 1) + '</td>';
                    html += '</tr>';
                    if (i == 7) {
                        break
                    }

                }
                
            }

            $('#OnlineRateReport').html(html)
        },
        error: function () {

        }
    })
}
//各厂区运维统计报表
function YunweiReport() {
    $.ajax({
        type: "POST",
        url: "/TongRen/GeChangAlarmAndWorkSheet",
        data: {},
        dataType: "json",
        async: true,
        success: function (data) {
            var len = data.length;
            if (len != 0) {

                $('#YnweiReport').html('');
                var html = '';
                var ClassName = 'l-grid-row-alt'
                for (var i = 0; i < len; i++) {
                    if (i % 2 == 0) {
                        ClassName = 'l-grid-row-alt'
                    } else {
                        ClassName = ' '
                    }
                    html += '<tr class="' + ClassName + '">'
                    html += ' <td>' + data[i].Area + '</td>';
                    html += '<td>' + data[i].AlarmNum + '</td>';
                    html += '<td>' + data[i].WorkNum + '</td>';
                    html += '<td>' + data[i].CompleteNum + '</td>';
                    html += '<td>';
                    html += '<div class="Bar">';
                    html += '<div style="width: ' + data[i].Intact + '%; border-radius:8px;"><span>' + data[i].Intact + '%</span></div>';
                    html += '</div>';
                    html += '</td>';

                    html += '</tr>';
                }
            }
            $('#YnweiReport').html(html)
        },
        error: function () {

        }
    })
}
function NoticeFun() {
    $.ajax({
        type: "POST",
        url: "/SysManager/BaseSet/GetNotice",
        data: {},
        dataType: "json",
        async: true,
        success: function (data) {

            var len = data.length;
            if (len != 0) {
                $('.notice').html(data.Content)
            } else {
                $('.notice').html('暂无通知公告')
            }
        },
        error: function () {
            
        }
    })
}



function Close(_this) {
    $('#dialog').hide()
}
var setIn;
function power1() {
    option = {
        color: ['#3398DB'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            top: '3%',
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            //boundaryGap: false,
            data: ['9-24', '9-23', '9-22', '9-21', '9-20', '9-19', '9-18'],
            nameTextStyle: {
                color: '#fff'
            },
            axisLabel: {
                show: true,
                interval: 0,
                textStyle: {
                    color: '#fff',
                    fontSize: _fontSize,
                }
            },
            axisLine: {
                lineStyle: {
                    color: '#fff'
                }
            },
            splitLine: {
                show: false
            },
        },
        yAxis: {
            min: 0,
            max: 100,
            type: 'value',
            axisLine: {
                lineStyle: {
                    color: '#fff'
                }
            },
            splitLine: {
                show: false
            },
            axisLabel: {
                formatter: '{value}',
                textStyle: {
                    color: '#fff',
                    fontSize: _fontSize,
                }
            }
        },
        series: [
            {
                name: '机房输出功率',
                type: 'bar',
                barWidth: '40%',
                data: [48, 52, 70, 45, 60, 78, 50]
            }
        ]
    };
    var power = echarts.init(document.getElementById('room-power'));
    power.setOption(option);
    setIn = setInterval(function () {
        var arr = ["20", "21", "22", "23", "24", "25"];
        var _arr = ["35", "36", "37", "38", "39", "40"];
        var index = Math.floor((Math.random() * arr.length));
        var _index = Math.floor((Math.random() * _arr.length));
        $('.temp b').html(arr[index]);
        $('.humidity b').html(_arr[_index]);
    }, 3000);
}
function jingmikongtiao() {
    $("#Day").html(show());
    $("#Time").html(showTime());
}
//获取当前时间
function show() {
    var str, mydate, month, getDate;
    mydate = new Date();
    month = (mydate.getMonth() + 1);
    getDate = mydate.getDate();
    if (month < 10) {
        month = '0' + month;
    }
    if (getDate < 10) {
        getDate = '0' + getDate;
    }
    str = mydate.getFullYear() + "/" + month + "/" + getDate;
    return str;
}
function showTime() {
    var str, time, getHours, getMinutes, getSeconds;
    time = new Date();
    getHours = time.getHours();
    getMinutes = time.getMinutes();
    getSeconds = time.getSeconds();
    if (getHours < 10) {
        getHours = '0' + getHours;
    }
    if (getMinutes < 10) {
        getMinutes = '0' + getMinutes;
    }
    if (getSeconds < 10) {
        getSeconds = '0' + getSeconds;
    }
    str = getHours + ":" + getMinutes + ":" + getSeconds;
    return str;
}


function AlarmInfoData() {
    $.ajax({
        type: "POST",
        url: "/TongRen/AlarmTopNList",
        dataType: "json",
        cache: false,
        async: true,
        success: function (data) {
            
            AlarmInfo(data)
        }
    })
}

//分期在线率完好率
function AlarmInfo() {
    $.ajax({
        type: "POST",
        url: "/TongRen/GetFenqiData",
        data: { },
        dataType: "json",
        cache: false,
        async: true,
        success: function (data) {
            
            var qishuname = new Array();
            var qishuonline = new Array();
            var qishuinrate = new Array();
            for (var i =0,j= data.length;i<j;i++)
            {
                qishuname[i] = data[i].Item1;
                qishuonline[i] = data[i].Item2;
                qishuinrate[i] = data[i].Item3;
            }
            var color = ['#4573a7', '#aa4644', '#89a54e', '#71588f', '#4298af', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];

            Barchart.on('click', function (params) {
                console.log(params);
            });
        },
        error: function () {
           
        }
    })
}
function ShequShiwu(){
    var option = {
        tooltip: {},
        /*legend: {
         textStyle: {
         color: '#fff',
         fontSize: 14
         },
         data: ['在线率', '完好率'],
         top: 30
         },*/
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
                data: ['X1网格','X2网格','X3网格','X4网格','X5网格','X6网格'],
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
                name: '在线率',
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
    var Barchart = echarts.init(document.getElementById('AlarmInfoEcharts'));
    Barchart.setOption(option);
}
function GetAlarmTopN() {
    $.ajax({
        type: "POST",
        url: "/TongRen/AlarmTopNList",
        data: { "number": 15 },
        dataType: "json",
        cache: false,
        async: true,
        success: function (data) {
            
            if (data!='') {
                for (var i = 0; i < data.length; i++) {
                    var cls = "", level = "一般", address = "", description = "";
                    if (data[i].AlarmLevel == 1) {
                        cls = "alarm-urgent";
                        level = "紧急";
                    } else if (data[i].AlarmLevel == 2) {
                        cls = "alarm-important";
                        level = "重要";
                    } else if (data[i].AlarmLevel == 3) {
                        cls = "alarm-remindful";
                        level = "提醒";
                    }
                    if (data[i].Address != null) {
                        address = data[i].Address;
                    }
                    if (data[i].AlarmDescription != null) {
                        description = data[i].AlarmDescription;
                    }
                    var AssetIp=data[i].AssetIp
                    var html = "<li onclick='AlarmDetails(\"" + AssetIp + "\")' class=" + cls + " style='cursor:pointer'><b>【" + level + "】</b>【" + address + "】" + description + "</li>";
                    $("#AlarmUL").append(html);
                }

                $("div.list_lh").myScroll({
                    speed: 80, //数值越大，速度越慢
                    rowHeight: 28 //li的高度
                });
            } else {
                var html = "<li class='color:#fff'>暂无告警</li>";
                $("#AlarmUL").append(html);
            }
        }
    });
}
//告警详情
function AlarmDetails(ip) {
    window.location.href = "/Monitor/AlarmManage/Alarm?IP="+ip
}
//运行设备总览
function radialIndicatorFUn() {
    for (var i = 0; i < 18; i++) {
        $('#radialIndicator' + i + '').radialIndicator({
            barColor: '#4573a7',
            barWidth: 10,
            initValue: 95,
            radius: 45,
            roundCorner: true,
            percentage: true
        });
    }

}
//告警分期
function AlarmQiShu() {
    var color = ['#aa4644', '#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];
    var data = [{
        value: 28,
        name: '一期'
    }, {
        value: 10,
        name: '二期'
    }, {
        value: 18,
        name: '三期'
    }, {
        value: 20,
        name: '四期'
    }
    ];
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            //type: 'scroll',
            orient: 'vertical',
            right: 'right',
            top: '30%',
            right: '15%',
            textStyle: {
                color: '#fff',
                fontSize: _fontSize,
            },
            data: ['一期', '二期', '三期', '四期']
        },
        series: [{
            name: '告警情况',
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
    var Barchart = echarts.init(document.getElementById('echarts3-1'));
    Barchart.setOption(option);
}
//告警一览
function GetAlarmHtml() {
    $('.echartsAlarm-tab>ul>li').click(function () {
        $(this).addClass('active').siblings().removeClass("active");
        $('#echartsAlarmBao').html(' <div id="echartsAlarm" style="width:100%;height:200px;margin-top: 13px;"></div>')
        initGetAlarm($(this).index())
    })
}
function initGetAlarm(typeId) {
    $.ajax({
        type: "POST",
        url: "/TongRen/GetGaoJingYiLan",
        data: { 'Type': typeId },
        dataType: "json",
        cache: false,
        async: true,
        success: function (data) {
            if (data) {

                GetAlarm(data);
            }
        }, error: function () {

        }
    });
}
function GetAlarm(data) {

    var colorList = [
                '#aa4644', '#cc7b33', '#89a54e'
    ];

    var SumData = data[0].AlamNum;
    var SumDataLen = SumData.length;
    var Sum, originalData = [];
    Sum = SumData[0] + SumData[1] + SumData[2];
    $('#level1').html(SumData[0])
    $('#level2').html(SumData[1])
    $('#level3').html(SumData[2])
    for (var i = 0; i < SumDataLen; i++) {
        originalData.push({
            value: SumData[i],
            name: (Sum == 0 ? 0 : (SumData[i] / Sum) * 100).toFixed(1) + '%'
        })
    }
    // 总和
  /*  var total = {
        name: '',
        value: Sum
    }
*/
    //var originalData = [{
    //    value: 50,
    //    name: '25%'
    //}, {
    //    value: 50,
    //    name: '25%'
    //}, {
    //    value: 100,
    //    name: "50%"
    //}];
    //echartsAlarmFun(originalData, total);
    echarts.util.each(originalData, function (item, index) {
        item.itemStyle = {
            normal: {
                color: colorList[index]
            }
        };
    });
    var option = {
        grid: {
            top: '10%',
            left: '40%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        title: [{
            text: '',
            left: '49%',
            top: '46%',
            textAlign: 'center',
            textBaseline: 'middle',
            textStyle: {
                fontWeight: 'bold',
                fontSize: 26,
                color: '#fff'
            }
        }, {
            text: total.value,
            left: '64%',
            top: '50%',
            textAlign: 'center',
            textBaseline: 'middle',
            textStyle: {
                fontWeight: 'bold',
                fontSize: 26,
                //color: '#eea807'
                color: '#fff'
            }
        }],
        series: [{
            hoverAnimation: false, //设置饼图默认的展开样式
            radius: [90, 65],
            center: ['65%', '52%'],
            name: 'pie',
            type: 'pie',
            selectedMode: 'single',
            selectedOffset: 16, //选中是扇区偏移量
            clockwise: true,
            startAngle: 90,
            label: {
                normal: {
                    textStyle: {
                        fontWeight: 'normal',
                        fontSize:_fontSize,
                        color: '#fff'
                    }
                }
            },
            labelLine: {
                normal: {
                    lineStyle: {
                        color: '#fff',
                    },
                    length: 20
                }
            },
            data: originalData
        }]
    };
    var myChart = echarts.init(document.getElementById('echartsAlarm'));
    myChart.setOption(option, true);

    var len = data.length;
    var html = '';
    for (var i = 1; i < len; i++) {
        if (i % 2 == 0) {
            html += '<tr class="l-grid-row-alt">';
            html += '<td style="width:60%;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">' + data[i].Name + '</td>';
            html += '<td>' + data[i].AlamNum[0] + '</td>';
            html += '<td>' + data[i].AlamNum[1] + '</td>';
            html += '<td>' + data[i].AlamNum[2] + '</td>';
            html += ' </tr>';
        } else {
            html += '<tr>';
            html += '<td style="width:60%;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">' + data[i].Name + '</td>';
            html += '<td>' + data[i].AlamNum[0] + '</td>';
            html += '<td>' + data[i].AlamNum[1] + '</td>';
            html += '<td>' + data[i].AlamNum[2] + '</td>';
            html += ' </tr>';
        }
        if (i == breakData) {
            break
        }


    }
    $('#alarmTbody').html(html)

}


//近七周告警统计图
function InitSevenWeekRate() {

    $.ajax({
        type: "POST",
        url: "/DaQing/SevenWeekRate",
        data: {},
        dataType: "json",
        cache: false,
        async: true,
        success: function (data) {
            if (data) {
                
                AlarmWeekInfo(data);
            }
        }, error: function () {

        }
    });
}
//近七天报警
function AlarmWeekInfo() {
  /*  var ydata = [], jdata = [], hdata = [];
    for (var i = 0; i < data.length; i++) {
        ydata.push(GetDate1(data[i].ThisWeekDay));
        jdata.push(data[i].AccessNumber);
        hdata.push(data[i].Ringratio)
    }*/
    
    var color = ['#4573a7', '#aa4644', '#89a54e', '#71588f', '#4298af', '#db843d', '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];

    var option = {
        tooltip: {
            trigger: 'axis',
            formatter: '{b}<br/>{a}: {c0}<br/>{a1}:{c1}%',
        },
        grid: {
            top: '18%',
            left: '4%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        legend: {
            data: ['报警数', '环比值'],
            textStyle: {
                color: '#fff',
                fontSize: _fontSize
            }
        },

        xAxis: [
            {
                type: 'category',
                // data: ydata,
                data: ['8-11', '8-18', '8-25', '9-01', '9-08', '9-22', '9-29'],
               // data:ydata,
                axisPointer: {
                    type: 'shadow'
                },
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                },
                axisLabel: {
                    // formatter: '{value} ',
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
                name: '',
                min: 0,
                //max: data[1].Onlinenum + 80,
                //interval: 50,
                axisLabel: {
                    formatter: '{value} ',
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
            },
            {
                type: 'value',
                name: '',
                //min: 0,
                //max: 100,
                interval: 25,
                axisLabel: {
                    formatter: '{value} %'
                },
                axisLine: {
                    lineStyle: {
                        color: '#fff'
                    }
                },
                splitLine: {
                    show: false
                }
            }
        ],
        series: [
            {
                name: '报警数',
                type: 'bar',
               // data: jdata,
                data: [20, 40, 50, 20, 50, 30, 40],
                itemStyle: {
                    normal: {
                        color: "#14c1c0",
                    },
                    textStyle: {
                        color: '#fff',
                        fontSize: _fontSize
                    }
                },
                barWidth: 23,
                boundaryGap: true
            },
            {
                name: '环比值',
                type: 'line',
                yAxisIndex: 1,
                //data: hdata,
                data: [0, 40, 60, 70, 20, 80, 100],
                itemStyle: {
                    normal: {
                        color: "#ffea00"
                    }
                },
                textStyle: {
                    color: '#fff',
                    fontSize: _fontSize
                }
            }
        ]
    };
    var Barchart = echarts.init(document.getElementById('AlarmInfoWeekEcharts'));
    Barchart.setOption(option);
    Barchart.on('click', function (params) {
        console.log(params);
    });
}
//获取时间日期
function GetDate1(date) {
    if (date == null) {
        date = '';
        return date;
    }
    var da = new Date(parseInt(date.replace("/Date(", "").replace(")/", "").split("+")[0])),
                     getMonth = (da.getMonth() + 1),
                     getDate = da.getDate(),
                     getHours = da.getHours(),
                     getMinutes = da.getMinutes(),
                     getSeconds = da.getSeconds();
    if (getMonth < 10) {
        getMonth = '0' + getMonth;
    }
    if (getDate < 10) {
        getDate = '0' + getDate;
    }
    if (getHours < 10) {
        getHours = '0' + getHours;
    }
    if (getMinutes < 10) {
        getMinutes = '0' + getMinutes;
    }
    if (getSeconds < 10) {
        getSeconds = '0' + getSeconds;
    }

    return getMonth + "-" + getDate;
}


//案事件报警一览
function AnShiJian(){
    var colorList = [
        '#aa4644', '#cc7b33', '#89a54e'
    ];
    // 总和
    var total = {
        name: '',
        value: '200'
    }

    var originalData = [{
        value: 50,
        name: '25%'
    }, {
        value: 50,
        name: '25%'
    }, {
        value: 100,
        name: "50%"
    }];

    echarts.util.each(originalData, function (item, index) {
        item.itemStyle = {
            normal: {
                color: colorList[index]
            }
        };
    });

    var option = {
        title: [{
            text: '',
            left: '49%',
            top: '46%',
            textAlign: 'center',
            textBaseline: 'middle',
            textStyle: {
                fontWeight: 'bold',
                fontSize: 26,
                color: '#fff'
            }
        }, {
            text: total.value,
            left: '64%',
            top: '50%',
            textAlign: 'center',
            textBaseline: 'middle',
            textStyle: {
                fontWeight: 'bold',
                fontSize: 26,
                //color: '#eea807'
                color: '#fff'
            }
        }],
        series: [{
            hoverAnimation: false, //设置饼图默认的展开样式
            radius: radiusData,
            center: ['65%', '52%'],
            name: 'pie',
            type: 'pie',
            selectedMode: 'single',
            selectedOffset: 16, //选中是扇区偏移量
            clockwise: true,
            startAngle: 90,
            label: {
                normal: {
                    textStyle: {
                        fontWeight: 'normal',
                        fontSize: _fontSize,
                        color: '#fff'
                    }
                }
            },
            labelLine: {
                normal: {
                    lineStyle: {
                        color: '#fff',
                    },
                    length: lengthECharts

                }
            },
            data: originalData
        }]
    };
    var myChart = echarts.init(document.getElementById('echartsAlarm'));
    myChart.setOption(option, true);

}