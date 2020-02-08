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
        .echarts {
            margin-top: 50px;
            width: 100%;
            height: 50%;
        }
    </style>
    <title>单位信息统计</title>
    <script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
    <script src="${ctxStatic}/common/index/Scripts/js/echarts.js"></script>
    <script type="text/javascript" src="${ctxStatic}/echarts/echarts.common.min.js"></script>
    <link rel="stylesheet" href="${ctxStatic}/bootstrap/bootstrap3.0/css/bootstrap.min.css">
    <script type="text/javascript">

        $.ajax({
            type: "GET",
            url: "${ctx}/analyst/getDataByDept",
            dataType: 'json',
            success: function (data) {
                var queryList = data.queryList;
                var timesList = data.timesList;
                var nameList = data.nameList;
                var loginList = data.loginList;
                init(queryList, nameList, timesList, loginList);
            },
            error: function () {
                alert("数据请求失败！！！");
            }
        });


        function init(queryList, nameList, timesList, loginList) {
            var option = {
                title: {
                    left: 'left',
                    text: '单位信息统计',
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross',
                        crossStyle: {
                            color: '#999'
                        }
                    }
                },
                toolbox: {
                    feature: {
                        dataView: {show: true, readOnly: false},
                        magicType: {show: true, type: ['line', 'bar']},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                legend: {
                    data: ['查询次数', '登录次数', '使用时长(天)']
                },
                xAxis: [
                    {
                        type: 'category',
                        data: nameList,
                        axisPointer: {
                            type: 'shadow'
                        }
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '次数',
                        axisLabel: {
                            formatter: '{value} '
                        }
                    },
                    {
                        type: 'value',
                        name: '时长',
                        axisLabel: {
                            formatter: '{value} min'
                        }
                    }
                ],
                series: [
                    {
                        name: '查询次数',
                        type: 'bar',
                        data: queryList
                    },
                    {
                        name: '登录次数',
                        type: 'bar',
                        data: timesList
                    },
                    {
                        name: '使用时长(天)',
                        type: 'bar',
                        yAxisIndex: 1,
                        data: loginList
                    }
                ]
            };

            var barChart = echarts.init(document.getElementById('deptBar'));
            barChart.setOption(option);
        }
    </script>
</head>
<body>
<div class="row-fluid">
    <div class="span11">
        <div id="deptBar" class="echarts"></div>
    </div>
</div>
</body>
</html>