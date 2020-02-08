<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit|ie-comp|ie-stand"/>
    <style>
        .header {
            display: block;
            font-size: 13px;
        }
        .echarts {
            margin-left:30px;
            margin-top: 10px;
            width: 100%;
            height: 45%;
        }

        .echarts1 {
            margin-left:30px;
            width: 100%;
            height: 45%;
        }
        .container-fluid {
            padding: 10px 0 0 10px;
        }

    </style>
    <title>个人信息统计</title>
    <script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
    <script src="${ctxStatic}/common/index/Scripts/js/echarts.js"></script>
    <script type="text/javascript"
            src="${ctxStatic}/echarts/echarts.common.min.js"></script>
    <link rel="stylesheet"
          href="${ctxStatic}/bootstrap/bootstrap3.0/css/bootstrap.min.css">


</head>

<body>
<div class="container-fluid">
    <div class="header">
        <%--<img src="{ctx}" style="width:16px;height:14px;">--%>
        <%--当前位置：数据统计分析>个人信息统计--%>

        <div class="row-fluid">
            <div class="span11">
                <div id="personalEcharts" class="echarts"></div>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span11">
                <div id="loginTimes" class="echarts1"></div>
            </div>
        </div>
    </div>
</div>
<%--<table>
    <div>


    </div>
    <tr>
        <div id="personalEcharts" class="echarts"></div>
    </tr>
    <tr>
        <div id="loginTimes" class="echarts1"></div>
    </tr>
</table>--%>
<script type="text/javascript">
    $.ajax({
        type: "GET",
        url: "${ctx}/analyst/countUseTime",
        dataType: 'json',
        success: function (data) {
            var dateList = data.dateList;
            var timeList = data.timeList;
            var timesList = data.timesList;
            // var objs = eval(data); //解析json对象
            init1(dateList, timeList, timesList);
        },
        error: function () {
            alert("数据请求失败==");
        }
    });

    function init1(dateList, timeList, timesList) {
        var colors = ['#FF7453', '#25B8FE'];
        option = {
            title: {
                text: '当前用户APP登录次数与使用时长'
            },
            color: colors,
            // grid: {
            //     right: '3%',
            //     left: '7%',
            // },
            legend: {
                data: ['登录次数', '使用时长'],
                textStyle: {
                    color: '#0F0F0F',
                }
            },
            tooltip: {},
            xAxis: [{
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                axisLine: {
                    lineStyle: {
                        color: '#0F0F0F'
                    }
                },
                data: dateList
            }],
            yAxis: [{
                type: 'value',
                name: '登录次数（次）',
                position: 'left',
                axisLine: {
                    lineStyle: {
                        color: '#0F0F0F'
                    }
                },
                axisLabel: {
                    formatter: '{value}'
                }
            }, {
                type: 'value',
                name: '使用时长（h）',
                position: 'right',
                axisLine: {
                    lineStyle: {
                        color: '#0F0F0F'
                    }
                },
                axisLabel: {
                    formatter: '{value}'
                }
            }],
            series: [{
                name: '登录次数',
                type: 'bar',
                barWidth: '30%',
                itemStyle: {
                    color: '#b1ff00'
                },
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                data: timesList
            }, {
                name: '使用时长',
                type: 'line',
                smooth: true,
                yAxisIndex: 1,
                data: timeList
            }]
        };
        var Barchart = echarts.init(document.getElementById('loginTimes'));
        Barchart.setOption(option);

    }
</script>
<script type="text/javascript">
    $.ajax({
        type: "GET",
        url: "${ctx}/analyst/getDataForPeople",
        dataType: 'json',
        // async : false,
        success: function (data) {
            var data1 = data.dateList;
            var data2 = data.timesList;
            var loginName = data.user;
            // var objs = eval(data); //解析json对象
            init(data1, data2, loginName);
        },
        error: function () {
            alert("数据请求失败==");
        }
    });

    function init(data1, data2, loginName) {
        option = {
            title: {
                text: '当前用户查询记录'
            },
            tooltip: {
                trigger: 'axis'
            },
            color: ['#00BFFF'],
            // grid: {
            //     left: '3%',
            //     right: '4%',
            //     bottom: '7%',
            //     containLabel: true
            // },
            toolbox: {
                feature: {
                    saveAsImage: {},
                    mark: {show:false}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: true,
                data: data1
            },
            yAxis: {
                type: 'value',
                name: '查询次数（次）'
            },
            series: [{
                name: loginName,
                type: 'bar',
                barWidth: '30%',
                stack: '查询次数',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                data: data2
            }]
        };

        var myChart = echarts.init(document
            .getElementById('personalEcharts'));
        myChart.setOption(option);
    }
</script>
</body>