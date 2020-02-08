<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>巡逻报告</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript"
            src="${ctxStatic}/echarts/echarts.common.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/echarts/walden.js"></script>
    <script type="text/javascript"
            src="${ctxStatic}/echarts/echarsCommon.js"></script>
    <link href="${ctxStatic}/layer-v3.1.1/layer/theme/default/layer.css"
          rel="stylesheet"/>
    <script src="${ctxStatic}/layer-v3.1.1/layer/layer.js"></script>
    <script src="${ctxStatic}/jquery/jquery.cookie.js" type="text/javascript"></script>
    <script type="text/javascript"
            src="${ctxStatic}/common/HasSecret.js"></script>
    <style>
        #ech2 {
            height: 420px;
        }

    </style>
</head>
<body>
<input type="hidden" id="hasPermission" value="${fns:getUser().hasPermission}"/>
<div class="context" content="${ctx}"></div>
<ul class="nav nav-tabs">
    <li class="active"><a href="#">数据统计</a></li>
</ul>

<div class="row-fluid">
    <div class="">
        <div class="help-inline">
            <label class="control-label">开始时间:</label>
            <div class="controls">
                <input type="text" id="startDate" onchange="changeDate()" readonly="readonly" class="input-medium Wdate"
                       onclick="WdatePicker()"/>
                <label for="startDate" style="display: none;" class="error">开始时间不能大于结束时间</label>
            </div>

        </div>
        <div class="help-inline">
            <label class="control-label">结束时间:</label>
            <div class="controls">
                <input type="text" id="endDate" onchange="changeDate()" readonly="readonly" class="input-medium Wdate" onclick="WdatePicker()"/>
                <label for="endDate" style="display: none;" class="error">开始时间和结束时间不能超过10天</label>
            </div>

        </div>
        <div class="help-inline" style="margin-top: 10px;">
            <input class="btn btn-primary" type="button" onclick="changeCharData()" value="查询"/>
            <%--<input class="btn btn-info export" type="button" value="导出测试"/>--%>
        </div>
    </div>
    <div id="ech2" class="span11"></div>
</div>
<div class="row-fluid">
    <div class="ToAuto">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>日期</th>
                <th title="参与单位">参与单位</th>
                <th title="任务数">任务数</th>
                <th title="操作">操作</th>
            </tr>
            </thead>
            <tbody class="body">
            </tbody>
        </table>
    </div>
</div>
<script>
    // $(function(){
    //     $('.export').click(function(){
    //         $.ajax({
    //             url:'exportWord',
    //             type:'post',
    //             data:{
    //                 dateStr: '2019-01-01',
    //                 baseStr:'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'
    //             },
    //             dateType:'json',
    //             success:function(res){
    //                 window.location.href="download/"+res.data+"/自定义的文件名称.doc";
    //             }
    //         })
    //     })
    // })

    Date.prototype.format = function(fmt) {
        var o = {
            "M+" : this.getMonth()+1,                 //月份
            "d+" : this.getDate(),                    //日
            "h+" : this.getHours(),                   //小时
            "m+" : this.getMinutes(),                 //分
            "s+" : this.getSeconds(),                 //秒
            "q+" : Math.floor((this.getMonth()+3)/3), //季度
            "S"  : this.getMilliseconds()             //毫秒
        };
        if(/(y+)/.test(fmt)) {
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        }
        for(var k in o) {
            if(new RegExp("("+ k +")").test(fmt)){
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
            }
        }
        return fmt;
    }

    var color;

    var theme=$.cookie('theme');
    if(theme=='gradient'){
         color = [ '#25B8FE', '#2BE0D5', '#B180E4', '#FD93B6', '#53D5FF', '#F8C73C', '#1F8BFA', '#F77136',//循环一遍
             '#25B8FE', '#2BE0D5', '#B180E4', '#FD93B6', '#53D5FF', '#F8C73C', '#1F8BFA', '#F77136' ];
    }
    else{

         color = ['#D87A82', '#4573a7', '#89a54e', '#71588f', '#4298af', '#db843d',
            '#93a9d0', '#d09392', '#b9ce96', '#a99bbc', '#92c3d4', '#ffdf5f'];
    }
    option = {
        title: {
            text: '巡逻报告',
            subtext: '数据统计'
        },
        color: color,
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['...', '...']
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable: true,
        xAxis: [
            {
                name:"日期",
                type: 'category',
                data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
            }
        ],
        yAxis: [
            {
                name:"数量",
                type: 'value'
            }
        ],
        series: [
            {

                type: 'bar',
                data: [0, 0,0, 0, 0, 0,0, 0,0,0]

            }
        ]
    };
    //判断参数合法性
    function changeDate(){
        if($("#startDate").val() && $("#endDate").val()){
            var startDate=new Date($("#startDate").val()).getTime();
            var endDate=new Date($("#endDate").val()).getTime();
            if(startDate>endDate){
                $("label[for='startDate']").show()
            }else{
                $("label[for='startDate']").hide()
                //如果大于十天
                if((endDate-startDate)>864000000){
                    console.log( $("label[for='endDate']"))
                    $("label[for='endDate']").show()
                }else{
                    $("label[for='endDate']").hide()
                    return true;
                }
            }


        }
        return false;
    }
    //申请数据
    function changeCharData(strDate,endDate,tf) {
        if(!tf){
            strDate=$("#startDate").val()
            endDate=$("#endDate").val()
        }
        if(changeDate() || tf){
            $.post("getChartData", {"startDate": strDate, "endDate": endDate}, function (data) {
                var data=data["data"];
                var intArr=data["intArr"];
                var stringList=data["stringList"];
                var i=0;
                option.xAxis[0].data=data['key'];
                option.legend.data=data['officeSet'];
                for (var x in intArr) {
                    option.series[i]={
                        name: x,
                        type: 'bar',
                        data: intArr[x]
                    }
                    i++;
                }
                //给统计图添加数据
                myChart.setOption(option, true)

                var html="";
                for (var i = 0; i <stringList.length ; i++) {
                    var split=stringList[i].split("|");
                    var a="<a class='span3' onclick=\"parent.LayerDialog('${ctx}/patrol/patrolReport/ListByDate?date="+split[0]+"', '任务列表', '1200px', '900px')\">任务列表</a>";
                    a+="<a onclick=\"exportWord('"+split[0]+"',this)\">下载报告</a>";
                    html+="<tr><td>"+split[0]+"</td>"+"<td>"+split[1]+"</td>"+"<td>"+split[2]+"</td><td>"+a+"</td></tr>";
                }
                //给表格添加数据
                $("tbody[class='body']").html(html);
            })
        }

    }






    var myChart = echarts.init(document.getElementById("ech2"));

    var endDate=new Date(new Date(new Date().getFullYear(),new Date().getMonth(),new Date().getDate()).getTime()-864000000).format("yyyy-MM-dd");
    var strDate=new Date().format("yyyy-MM-dd");
    changeCharData(endDate,strDate,true);
    //导出word
    function exportWord(date){
        // var mycanvas = $("#ech2").find("canvas")[0];
        // var base64Str=mycanvas.toDataURL("image/jpeg",1);
        var base64Str=myChart.getConnectedDataURL({
            type: "png",
            backgroundColor: '#fff',
            excludeComponents:['toolbox'],
            pixelRatio: 1
        });
        base64Str=base64Str.split(",")[1];
        $.post("exportWord",{"dateStr":date,"baseStr":base64Str},function(data){
            var a=document.createElement("a");
            a.href="${ctx}/patrol/patrolReport/download/"+date+"/巡逻报告.doc";
            a.click()
        })
    }


</script>
</body>
</html>